import Cards.Card;
import Cards.WeaponCard;

import java.util.*;

/**
 * Class for the "Board" the game is played on.
 * 24x25 (24 tiles across, 25 tiles down)
 *
 *      Board layout
 *
 *      |01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|
 *     A|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     B|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     C|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     D|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     E|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     F|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     G|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     H|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     I|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     J|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     K|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     L|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     M|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     N|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     O|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     P|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     Q|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     R|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     S|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     T|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     U|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     V|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     W|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     U|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 *     V|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|
 */

//North and south walls for all main rooms DONE.
public class Board {
    private HashMap<String,Room> rooms;
    private Tile[][] board;  //[Yaxis][xAxis]
    private static HashMap<String, Weapon> weapons;

    /**
     * Initialises the playing board
     */
    public Board() {
        board = new Tile[26][25]; //[0][0] Not used to allow for easier indexing - A1 == [1][1]
        rooms = new HashMap<>();
        weapons = new HashMap<String,Weapon>();
        //Load rooms//
        loadKitchen();
        loadBallroom();
        loadConservatory();
        loadDiningRoom();
        loadBilliardRoom();
        loadLibrary();
        loadLounge();
        loadHall();
        loadStudy();
        //Set shortcuts
        setShortcuts();
        //Add cellar and corridors
        loadCellar();
        loadCorridor();
        printBoard();
        //TODO - East/West walls
        //TODO - corridor walls
    }

    public String printBoard() {
        StringBuilder sb = new StringBuilder();
        char Y = 'A';
        sb.append("  |");
        for(int i=1; i < board[0].length; i++)
            sb.append(String.format("%2d|", i));
        sb.append("\n");
        for(int y=1; y < board.length; y++) {
            sb.append(Y + " |");
            for(int x=1; x < board[y].length; x++){
                sb.append(board[y][x]);
                sb.append("|");
            }
            Y++;
            sb.append("\n");
        }
        sb.append("  |");
        for(int i=1; i < board[0].length; i++)
            sb.append(String.format("%2d|", i));
        sb.append("\n");
        return sb.toString();
    }

    public Tile getTile(int y, int x) { return board[y][x]; }

    public HashMap<String,Room> getRooms() {return rooms; }

    /**
     * Loads the Kitchen tiles, and adds Kitchen to the rooms list
     */
    private void loadKitchen() {
        Room room = new Room("Kitchen", "KI", true);
        rooms.put(room.getName(), room);

        //tiles in the kitchen
        int[] B = {1, 2, 3, 4, 5, 6}; //every tile here will have a north wall
        int[] C = {1, 2, 3, 4, 5, 6};
        int[] D = {1, 2, 3, 4, 5, 6};
        int[] E = {1, 2, 3, 4, 5, 6};
        int[] F = {1, 2, 3, 4, 5, 6};
        int[] G =    {2, 3, 4, 5, 6}; //every tile here will have a south wall, 5 is door

        //TODO - East and West walls
        //North and South done

        //creating the kitchen tiles
        for (int i: B) {
            board[charToInt('B')][i] = new RoomTile(room);
            //set all the tiles to have a North Wall
            board[charToInt('B')][i].setWall(Direction.NORTH);
        }
        for (int i: C)
            board[(charToInt('C'))][i] = new RoomTile(room);
        for (int i: D)
            board[(charToInt('D'))][i] = new RoomTile(room);
        for (int i: E)
            board[(charToInt('E'))][i] = new RoomTile(room);
        for (int i: F) {
            board[charToInt('F')][i] = new RoomTile(room);
            // F1 has a south wall
            if (i == 1)
                board[charToInt('F')][i].setWall(Direction.SOUTH);
        }
        for (int i: G) {
            board[charToInt('G')][i] = new RoomTile(room);
            //set all the tiles to have a South Wall, except 5 because its a door
            if (i == 5)
                continue;
            board[charToInt('G')][i].setWall(Direction.SOUTH);
        }

        //List of arrays
        //if the row has a door, dont put it into the list.
        List<int[]> walls = Arrays.asList(B,C,D,E,F,G);
        loadEastWalls('B', walls);
        loadWestWalls('B', walls);
    }

    /**
     * Loads the Ballroom tiles, and adds Ballroom to the rooms list
     */
    private void loadBallroom() {
        Room room = new Room("Ballroom", "BA", false);
        rooms.put(room.getName(),room);

        int[] B =        {11, 12, 13, 14}; //all north walls
        int[] C = {9, 10, 11, 12, 13, 14, 15, 16};
        int[] D = {9, 10, 11, 12, 13, 14, 15, 16};
        int[] E = {9, 10, 11, 12, 13, 14, 15, 16};
        int[] F = {9, 10, 11, 12, 13, 14, 15, 16};
        int[] G = {9, 10, 11, 12, 13, 14, 15, 16};
        int[] H = {9, 10, 11, 12, 13, 14, 15, 16}; //every tile here will have a south wall, 10 and 15 is door

        //TODO - East and West walls
        //North and South done

        for (int i: B) {
            board[charToInt('B')][i] = new RoomTile(room);
            //set all the tiles to have a North Wall
            board[charToInt('B')][i].setWall(Direction.NORTH);
        }
        for (int i: C) {
            board[charToInt('C')][i] = new RoomTile(room);
            if (i == 9 || i == 10 || i == 15 || i == 16)
                board[charToInt('C')][i].setWall(Direction.NORTH);
        }
        for (int i: D)
            board[(charToInt('D'))][i] = new RoomTile(room);
        for (int i: E)
            board[(charToInt('E'))][i] = new RoomTile(room);
        for (int i: F)
            board[(charToInt('F'))][i] = new RoomTile(room);
        for (int i: G)
            board[(charToInt('G'))][i] = new RoomTile(room);
        for (int i: H) {
            board[charToInt('H')][i] = new RoomTile(room);
            //set all the tiles to have a South Wall, except 10 and 15 (doors)
            if (i == 10 || i == 15)
                continue;
            board[charToInt('H')][i].setWall(Direction.SOUTH);
        }

        //needs only 1 list, just call both wall methods on it and exclude F
    }

    /**
     * Loads the Conservatory tiles, and adds Conservatory to the rooms list
     */
    private void loadConservatory() {
        Room room = new Room("Conservatory","CO",true);
        rooms.put(room.getName(),room);

        int[] B = {19, 20, 21, 22, 23, 24};
        int[] C = {19, 20, 21, 22, 23, 24};
        int[] D = {19, 20, 21, 22, 23, 24};
        int[] E = {19, 20, 21, 22, 23, 24};
        int[] F =     {20, 21, 22, 23};

        //E{19} has a South Door
        //TODO - East and West walls
        //North and South done

        for (int i: B) {
            board[charToInt('B')][i] = new RoomTile(room);
            //set all the tiles to have a North Wall
            board[charToInt('B')][i].setWall(Direction.NORTH); //set all the tiles in B to have a North Wall
        }
        for (int i: C)
            board[(charToInt('C'))][i] = new RoomTile(room);
        for (int i: D)
            board[(charToInt('D'))][i] = new RoomTile(room);
        for (int i: E) {
            board[charToInt('E')][i] = new RoomTile(room);
            // E24 has a south wall
            if (i == 24)
                board[charToInt('E')][i].setWall(Direction.SOUTH);
        }
        for (int i: F) {
            board[charToInt('F')][i] = new RoomTile(room);
            //set all the tiles to have a South Wall
            board[charToInt('F')][i].setWall(Direction.SOUTH);
        }
    }

    /**
     * Loads the Dining Room tiles, and adds Dining Room to the rooms list
     */
    private void loadDiningRoom() {
        Room room = new Room("Dining Room","DR",false);
        rooms.put(room.getName(),room);

        int[] J = {1, 2, 3, 4, 5};
        int[] K = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] L = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] M = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] N = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] O = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] P = {1, 2, 3, 4, 5, 6, 7, 8};

        //TODO - East and West walls
        //North and South done

        for (int i: J) {
            board[charToInt('J')][i] = new RoomTile(room);
            //set all the tiles to have a North Wall
            board[charToInt('J')][i].setWall(Direction.NORTH); //set all the tiles in J to have a North Wall
        }
        for (int i: K) {
            board[charToInt('K')][i] = new RoomTile(room);
            //Sets K{6,7,8} to have north walls
            if (i >= 6)
                board[charToInt('K')][i].setWall(Direction.NORTH);
        }
        for (int i: L)
            board[(charToInt('L'))][i] = new RoomTile(room);
        for (int i: M)
            board[(charToInt('M'))][i] = new RoomTile(room);
        for (int i: N)
            board[(charToInt('N'))][i] = new RoomTile(room);
        for (int i: O)
            board[(charToInt('O'))][i] = new RoomTile(room);
        for (int i: P) {
            board[charToInt('P')][i] = new RoomTile(room);
            //set all the tiles to have a South Wall, except 7(door)
            if (i == 7)
                continue;
            board[charToInt('P')][i].setWall(Direction.SOUTH);
        }
    }

    /**
     * Loads the Billiard Room tiles, and adds Billiard Room to the rooms list
     */
    private void loadBilliardRoom() {
        Room room = new Room("Billiard Room", "BR",false);
        rooms.put(room.getName(),room);

        int[] I = {19, 20, 21, 22, 23, 24};
        int[] J = {19, 20, 21, 22, 23, 24};
        int[] K = {19, 20, 21, 22, 23, 24};
        int[] L = {19, 20, 21, 22, 23, 24};
        int[] M = {19, 20, 21, 22, 23, 24};

        //TODO - East and West walls
        //North and South done

        for (int i : I){
            board[charToInt('I')][i] = new RoomTile(room);
            //set all the tiles to have a North Wall
            board[charToInt('I')][i].setWall(Direction.NORTH); //set all the tiles in I to have a North Wall
        }
        for (int i: J)
            board[(charToInt('J'))][i] = new RoomTile(room);
        for (int i: K)
            board[(charToInt('K'))][i] = new RoomTile(room);
        for (int i: L)
            board[(charToInt('L'))][i] = new RoomTile(room);
        for (int i: M) {
            board[charToInt('M')][i] = new RoomTile(room);
            //set all the tiles to have a South Wall, except 23(door)
            if (i == 23)
                continue;
            board[charToInt('M')][i].setWall(Direction.SOUTH);
        }
    }

    /**
     * Loads the Library tiles, and adds Library to the rooms list
     */
    private void loadLibrary() {
        Room room = new Room("Library", "LI",false);
        rooms.put(room.getName(),room);

        int[] O =     {19, 20, 21, 22, 23};
        int[] P = {18, 19, 20, 21, 22, 23, 24};
        int[] Q = {18, 19, 20, 21, 22, 23, 24};
        int[] R = {18, 19, 20, 21, 22, 23, 24};
        int[] S =     {19, 20, 21, 22, 23};

        //TODO - East and West walls
        //North and South done

        for (int i: O) {
            board[charToInt('O')][i] = new RoomTile(room);
            //set all the tiles to have a North Wall, except 21
            if (i == 21)
                continue;
            board[charToInt('O')][i].setWall(Direction.NORTH);
        }
        for (int i: P) {
            board[charToInt('P')][i] = new RoomTile(room);
            //Sets P{18,24} to have north walls
            if (i == 18 || i == 24)
                board[charToInt('P')][i].setWall(Direction.NORTH);
        }
        for (int i: Q)
            board[(charToInt('Q'))][i] = new RoomTile(room);
        for (int i: R) {
            board[charToInt('R')][i] = new RoomTile(room);
            //R{18,24} have a south wall
            if (i == 18 || i == 24)
                board[charToInt('R')][i].setWall(Direction.SOUTH);
        }
        for (int i: S) {
            board[charToInt('S')][i] = new RoomTile(room);
            //set all the tiles to have a South Wall
            board[charToInt('S')][i].setWall(Direction.SOUTH);
        }
    }

    /**
     * Loads the Lounge tiles, and adds Lounge to the rooms list
     */
    private void loadLounge() {
        Room room = new Room("Lounge","LO",true);
        rooms.put(room.getName(),room);

        int[] T = {1, 2, 3, 4, 5, 6, 7};
        int[] U = {1, 2, 3, 4, 5, 6, 7};
        int[] V = {1, 2, 3, 4, 5, 6, 7};
        int[] W = {1, 2, 3, 4, 5, 6, 7};
        int[] X = {1, 2, 3, 4, 5, 6, 7};
        int[] Y = {1, 2, 3, 4, 5, 6};

        //TODO - East and West walls
        //North and South done

        for (int i: T) {
            board[charToInt('T')][i] = new RoomTile(room);
            //set all the tiles to have a North Wall except 7 (door)
            if (i == 7)
                continue;
            board[charToInt('T')][i].setWall(Direction.NORTH);
        }
        for (int i: U)
            board[(charToInt('U'))][i] = new RoomTile(room);
        for (int i: V)
            board[(charToInt('V'))][i] = new RoomTile(room);
        for (int i: W)
            board[(charToInt('W'))][i] = new RoomTile(room);
        for (int i: X) {
            board[charToInt('X')][i] = new RoomTile(room);
            //X{7} has a south wall
            if (i == 7)
                board[charToInt('X')][i].setWall(Direction.SOUTH);
        }
        for (int i: Y) {
            board[charToInt('Y')][i] = new RoomTile(room);
            //set all the tiles to have a South Wall
            board[charToInt('Y')][i].setWall(Direction.SOUTH);
        }
    }

    /**
     * Loads the Hall tiles, and adds Hall to the rooms list
     */
    private void loadHall() {
        Room room = new Room("Hall","HA",false);
        rooms.put(room.getName(),room);

        int[] S = {10, 11, 12, 13, 14, 15};
        int[] T = {10, 11, 12, 13, 14, 15};
        int[] U = {10, 11, 12, 13, 14, 15};
        int[] V = {10, 11, 12, 13, 14, 15};
        int[] W = {10, 11, 12, 13, 14, 15};
        int[] X = {10, 11, 12, 13, 14, 15};
        int[] Y = {10, 11, 12, 13, 14, 15}; //the map looks deceiving but is actually a square room

        //TODO - East and West walls
        //North and South done

        for (int i : S){
            board[charToInt('S')][i] = new RoomTile(room);
            //set all the tiles to have a North Wall except 12 and 13 (door)
            if (i == 12 || i == 13)
                continue;
            board[charToInt('S')][i].setWall(Direction.NORTH);
        }
        for (int i: T)
            board[(charToInt('T'))][i] = new RoomTile(room);
        for (int i: U)
            board[(charToInt('U'))][i] = new RoomTile(room);
        for (int i: V)
            board[(charToInt('V'))][i] = new RoomTile(room);
        for (int i: W)
            board[(charToInt('W'))][i] = new RoomTile(room);
        for (int i: X)
            board[(charToInt('X'))][i] = new RoomTile(room);
        for (int i: Y) {
            board[charToInt('Y')][i] = new RoomTile(room);
            //set all the tiles to have a South Wall
            board[charToInt('Y')][i].setWall(Direction.SOUTH);
        }

    }

    /**
     * Loads the Study tiles, and adds Study to the rooms list
     */
    private void loadStudy() {
        Room room = new Room("Study","ST",true);
        rooms.put(room.getName(),room);

        int[] V = {18, 19, 20, 21, 22, 23, 24};
        int[] W = {18, 19, 20, 21, 22, 23, 24};
        int[] X = {18, 19, 20, 21, 22, 23, 24};
        int[] Y =     {19, 20, 21, 22, 23, 24};

        //TODO - East and West walls
        //North and South done

        for (int i: V) {
            board[charToInt('V')][i] = new RoomTile(room);
            //set all the tiles to have a North Wall except 18(door)
            if (i == 18)
                continue;
            board[charToInt('V')][i].setWall(Direction.NORTH);
        }
        for (int i: W)
            board[charToInt('W')][i] = new RoomTile(room);
        for (int i: X) {
            board[charToInt('X')][i] = new RoomTile(room);
            //X{18} has a south wall
            if (i == 18)
                board[charToInt('X')][i].setWall(Direction.SOUTH);
        }
        for (int i: Y) {
            board[charToInt('Y')][i] = new RoomTile(room);
            //set all the tiles to have a South Wall
            board[charToInt('Y')][i].setWall(Direction.SOUTH);
        }
    }

    private void setShortcuts() {
        Room KI = rooms.get("Kitchen");
        Room CO = rooms.get("Conservatory");
        Room LO = rooms.get("Lounge");
        Room ST = rooms.get("Study");

        KI.setShortcut(ST);
        ST.setShortcut(KI);

        CO.setShortcut(LO);
        LO.setShortcut(CO);
    }

    /**
     * Loads the Cellar tiles, but does not add to rooms list
     * Also loads all null tiles as cellar, as they are also unplayable
     */
    private void loadCellar() {
        Room cellar = new Room("Cellar", "XX", false);
        for(int y = charToInt('K'); y <= charToInt('Q'); y++) {
            for (int x = 11; x <= 15; x++)
                board[y][x] = new RoomTile(cellar);
        }
        //Top row
        for(int i = 1; i < board[1].length; i++) {
            if (i == 10 || 1 == 15)
                continue;
            board[1][i] = new RoomTile(cellar);
        }
        board[charToInt('G')][1] = new RoomTile(cellar);
        board[charToInt('I')][1] = new RoomTile(cellar);
        board[charToInt('Q')][1] = new RoomTile(cellar);
        board[charToInt('S')][1] = new RoomTile(cellar);
        board[charToInt('Y')][7] = new RoomTile(cellar);
        board[charToInt('Y')][9] = new RoomTile(cellar);
        board[charToInt('Y')][16] = new RoomTile(cellar);
        board[charToInt('Y')][18] = new RoomTile(cellar);
        board[charToInt('U')][24] = new RoomTile(cellar);
        board[charToInt('S')][24] = new RoomTile(cellar);
        board[charToInt('O')][24] = new RoomTile(cellar);
        board[charToInt('N')][24] = new RoomTile(cellar);
        board[charToInt('H')][24] = new RoomTile(cellar);
        board[charToInt('F')][24] = new RoomTile(cellar);
    }

    /**
     * Fills in board with Corridor tiles
     */
    private void loadCorridor() {
        //[B -> X][[2 -> 23]
        for(int y = 1; y < board.length; y++)
            for(int x = 1; x < board[x].length; x++)
                if(board[y][x] == null)
                    board[y][x] = new CorridorTile();
    }

    /**
     * Sets the last tile in each array to have a East wall
     * @param y     - the first Y coordinate of the list
     * @param tiles - room tile X coordinates
     */
    private void loadEastWalls(char y, List<int[]> tiles) {
        int yValue = charToInt(y);

        for (int[] i: tiles) {
            board[yValue][i[i.length-1]].setWall(Direction.EAST);
            yValue++;
        }
    }

    /**
     * Sets the first tile in each array to have a West wall
     * @param y     - the first Y coordinate of the list
     * @param tiles - room tile X coordinates
     */
    private void loadWestWalls(char y, List<int[]> tiles) {
        int yValue = charToInt(y);

        for (int[] i: tiles) {
            board[yValue][i[0]].setWall(Direction.WEST);
            yValue++;
        }
    }

    public void addWeapons(ArrayList<Card> weps) {
        for(Card card : weps) {
            String name = ((WeaponCard) card).getWeaponName();
            weapons.put(name, new Weapon(name));
        }
    }

    /**
     * Converts character coordinate to integer index
     * @param c - the board Y coordinate
     * @return  - the corresponding Y index
     */
    public int charToInt(char c) { return c-64; }

}
