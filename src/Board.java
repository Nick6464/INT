import Cards.Card;
import Cards.WeaponCard;

import java.util.*;

/**
 * Class for the "Board" the game is played on.
 * 24x25 (24 tiles across, 25 tiles down)
 */
//****************************** Board Layout *****************************//
//  |01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|
//A |//|//|//|//|//|//|//|//|//|mw|//|//|//|//|mg|//|//|//|//|//|//|//|//|//|
//B |KI|KI|KI|KI|KI|KI|//|__|__|__|BA|BA|BA|BA|__|__|__|//|CO|CO|CO|CO|CO|CO|
//C |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|CO|CO|CO|CO|CO|CO|
//D |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|CO|CO|CO|CO|CO|CO|
//E |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|[]|CO|CO|CO|CO|CO|
//F |KI|KI|KI|KI|KI|KI|__|__|[]|BA|BA|BA|BA|BA|BA|[]|__|__|__|CO|CO|CO|CO|//|
//G |//|KI|KI|KI|[]|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|__|__|__|__|__|mp|
//H |__|__|__|__|__|__|__|__|BA|[]|BA|BA|BA|BA|[]|BA|__|__|__|__|__|__|__|//|
//I |//|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|BI|BI|BI|BI|BI|BI|
//J |DR|DR|DR|DR|DR|__|__|__|__|__|__|__|__|__|__|__|__|__|[]|BI|BI|BI|BI|BI|
//K |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|BI|BI|BI|BI|BI|BI|
//L |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|BI|BI|BI|BI|BI|BI|
//M |DR|DR|DR|DR|DR|DR|DR|[]|__|__|CC|CC|CC|CC|CC|__|__|__|BI|BI|BI|BI|[]|BI|
//N |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|__|__|__|__|__|//|
//O |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|LI|LI|[]|LI|LI|//|
//P |DR|DR|DR|DR|DR|DR|[]|DR|__|__|CC|CC|CC|CC|CC|__|__|LI|LI|LI|LI|LI|LI|LI|
//Q |//|__|__|__|__|__|__|__|__|__|CC|CC|CC|CC|CC|__|__|[]|LI|LI|LI|LI|LI|LI|
//R |cm|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|LI|LI|LI|LI|LI|LI|LI|
//S |//|__|__|__|__|__|__|__|__|HA|HA|[]|[]|HA|HA|__|__|__|LI|LI|LI|LI|LI|//|
//T |LO|LO|LO|LO|LO|LO|[]|__|__|HA|HA|HA|HA|HA|HA|__|__|__|__|__|__|__|__|pp|
//U |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|[]|__|__|__|__|__|__|__|__|//|
//V |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|[]|ST|ST|ST|ST|ST|ST|
//W |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|ST|ST|ST|ST|ST|ST|ST|
//X |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|ST|ST|ST|ST|ST|ST|ST|
//Y |LO|LO|LO|LO|LO|LO|//|ms|//|HA|HA|HA|HA|HA|HA|//|__|//|ST|ST|ST|ST|ST|ST|
//  |01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|


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
        loadDoorways();
        loadCorridor();
        loadCornerDoorWalls();
        rooms.remove("Cellar");
    }

    /**
     * Prints out the playing board (without players)
     * @return  A String representation of the board
     */
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

    /**
     * Prints a 7x7 display of the board centred around the player
     * @param p - the current player
     * @return  - A String representation of the board area
     */
    public String printArea(Player p) {
        int x = p.getLocation().getX();
        int y = p.getLocation().getYIndex();
        //X Range
        int left = Math.max(1, x-3);
        int right = Math.min(board[0].length-1, left+6);
            left = Math.min(left, right-6);
        //Y Range
        int top = Math.max(1,y-3);
        int bot = Math.min(board.length-1, top+6);
            top = Math.min(top, bot-6);

        StringBuilder sb = new StringBuilder();
        int printX = left;
        while(top <= bot) {
            sb.append("|");
            while (printX <= right) {
                if(top==y && printX==x)
                    sb.append(p.initials).append("|");
                else
                    sb.append(board[top][printX]).append("|");
                printX++;
            }
            sb.append("\n");
            printX = left;
            top++;
        }
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

        int[] B = {1, 2, 3, 4, 5, 6};
        int[] C = {1, 2, 3, 4, 5, 6};
        int[] D = {1, 2, 3, 4, 5, 6};
        int[] E = {1, 2, 3, 4, 5, 6};
        int[] F = {1, 2, 3, 4, 5, 6};
        int[] G =    {2, 3, 4, 5, 6};

        //creating the kitchen tiles
        for (int i: B)
            board[charToInt('B')][i] = new RoomTile(room);
        for (int i: C)
            board[(charToInt('C'))][i] = new RoomTile(room);
        for (int i: D)
            board[(charToInt('D'))][i] = new RoomTile(room);
        for (int i: E)
            board[(charToInt('E'))][i] = new RoomTile(room);
        for (int i: F)
            board[charToInt('F')][i] = new RoomTile(room);
        for (int i: G)
            board[charToInt('G')][i] = new RoomTile(room);
    }

    /**
     * Loads the Ballroom tiles, and adds Ballroom to the rooms list
     */
    private void loadBallroom() {
        Room room = new Room("Ballroom", "BA", false);
        rooms.put(room.getName(),room);

        int[] B =        {11, 12, 13, 14};
        int[] C = {9, 10, 11, 12, 13, 14, 15, 16};
        int[] D = {9, 10, 11, 12, 13, 14, 15, 16};
        int[] E = {9, 10, 11, 12, 13, 14, 15, 16};
        int[] F = {9, 10, 11, 12, 13, 14, 15, 16};
        int[] G = {9, 10, 11, 12, 13, 14, 15, 16};
        int[] H = {9, 10, 11, 12, 13, 14, 15, 16};

        for (int i: B)
            board[charToInt('B')][i] = new RoomTile(room);
        for (int i: C)
            board[charToInt('C')][i] = new RoomTile(room);
        for (int i: D)
            board[(charToInt('D'))][i] = new RoomTile(room);
        for (int i: E)
            board[(charToInt('E'))][i] = new RoomTile(room);
        for (int i: F)
            board[(charToInt('F'))][i] = new RoomTile(room);
        for (int i: G)
            board[(charToInt('G'))][i] = new RoomTile(room);
        for (int i: H)
            board[charToInt('H')][i] = new RoomTile(room);
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

        for (int i: B)
            board[charToInt('B')][i] = new RoomTile(room);
        for (int i: C)
            board[(charToInt('C'))][i] = new RoomTile(room);
        for (int i: D)
            board[(charToInt('D'))][i] = new RoomTile(room);
        for (int i: E)
            board[charToInt('E')][i] = new RoomTile(room);
        for (int i: F)
            board[charToInt('F')][i] = new RoomTile(room);
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

        for (int i: J)
            board[charToInt('J')][i] = new RoomTile(room);
        for (int i: K)
            board[charToInt('K')][i] = new RoomTile(room);
        for (int i: L)
            board[(charToInt('L'))][i] = new RoomTile(room);
        for (int i: M)
            board[(charToInt('M'))][i] = new RoomTile(room);
        for (int i: N)
            board[(charToInt('N'))][i] = new RoomTile(room);
        for (int i: O)
            board[(charToInt('O'))][i] = new RoomTile(room);
        for (int i: P)
            board[charToInt('P')][i] = new RoomTile(room);
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

        for (int i : I)
            board[charToInt('I')][i] = new RoomTile(room);
        for (int i: J)
            board[(charToInt('J'))][i] = new RoomTile(room);
        for (int i: K)
            board[(charToInt('K'))][i] = new RoomTile(room);
        for (int i: L)
            board[(charToInt('L'))][i] = new RoomTile(room);
        for (int i: M)
            board[charToInt('M')][i] = new RoomTile(room);
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

        for (int i: O)
            board[charToInt('O')][i] = new RoomTile(room);
        for (int i: P)
            board[charToInt('P')][i] = new RoomTile(room);
        for (int i: Q)
            board[(charToInt('Q'))][i] = new RoomTile(room);
        for (int i: R)
            board[charToInt('R')][i] = new RoomTile(room);
        for (int i: S)
            board[charToInt('S')][i] = new RoomTile(room);
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

        for (int i: T)
            board[charToInt('T')][i] = new RoomTile(room);
        for (int i: U)
            board[(charToInt('U'))][i] = new RoomTile(room);
        for (int i: V)
            board[(charToInt('V'))][i] = new RoomTile(room);
        for (int i: W)
            board[(charToInt('W'))][i] = new RoomTile(room);
        for (int i: X)
            board[charToInt('X')][i] = new RoomTile(room);
        for (int i: Y)
            board[charToInt('Y')][i] = new RoomTile(room);
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
        int[] Y = {10, 11, 12, 13, 14, 15};

        for (int i : S)
            board[charToInt('S')][i] = new RoomTile(room);
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
        for (int i: Y)
            board[charToInt('Y')][i] = new RoomTile(room);
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

        for (int i: V)
            board[charToInt('V')][i] = new RoomTile(room);
        for (int i: W)
            board[charToInt('W')][i] = new RoomTile(room);
        for (int i: X)
            board[charToInt('X')][i] = new RoomTile(room);
        for (int i: Y)
            board[charToInt('Y')][i] = new RoomTile(room);
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
     * Loads the Cellar tiles
     * Also loads all null tiles as cellar, as they are also unplayable
     */
    private void loadCellar() {
        Room cellar = new Room("Cellar", "XX", false);
        rooms.put(cellar.getName(), cellar);
        for(int y = charToInt('K'); y <= charToInt('Q'); y++) {
            for (int x = 11; x <= 15; x++)
                board[y][x] = new RoomTile(cellar);
        }
        //Top row
        for(int i = 1; i < board[1].length; i++) {
            if (i == 10 || i == 15)
                continue;
            board[1][i] = new RoomTile(cellar);
        }

        //Outer board pieces
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
        for(int y = 1; y < board.length; y++)
            for(int x = 1; x < board[y].length; x++)
                if(board[y][x] == null) {
                    board[y][x] = new CorridorTile();
                    //West walls
                    if(x == 1)
                        board[y][x].setWall(Direction.WEST);
                    if(board[y][x-1] instanceof RoomTile)
                        if(!((RoomTile) board[y][x-1]).isDoorway())
                            board[y][x].setWall(Direction.WEST);
                    //East walls
                    if(x == board[y].length-1)
                        board[y][x].setWall(Direction.EAST);
                    else if(board[y][x+1] instanceof RoomTile)
                        if(!((RoomTile) board[y][x+1]).isDoorway())
                            board[y][x].setWall(Direction.EAST);
                    //South walls
                    if(y == board.length-1)
                        board[y][x].setWall(Direction.SOUTH);
                    else if (board[y+1][x] instanceof RoomTile)
                        if(!((RoomTile) board[y+1][x]).isDoorway())
                            board[y][x].setWall(Direction.SOUTH);
                    //North walls
                    if (y == 1)
                        board[y][x].setWall(Direction.NORTH);
                    if (board[y-1][x] instanceof RoomTile)
                        if (!((RoomTile) board[y-1][x]).isDoorway())
                            board[y][x].setWall(Direction.NORTH);
                    //Collections.sort(board[y][x].getWalls());
                }
    }

    private void loadCornerDoorWalls() {
        board[charToInt('E')][18].setWall(Direction.EAST);
        board[charToInt('T')][8].setWall(Direction.WEST);
        board[charToInt('V')][17].setWall(Direction.EAST);
    }

    private void loadDoorways() {
        //Kitchen
        ((RoomTile)board[charToInt('G')][5]).setDoorway();
        //Ballroom
        ((RoomTile)board[charToInt('F')][9]).setDoorway();
        ((RoomTile)board[charToInt('H')][10]).setDoorway();
        ((RoomTile)board[charToInt('H')][15]).setDoorway();
        ((RoomTile)board[charToInt('F')][16]).setDoorway();
        //Conservatory
        ((RoomTile)board[charToInt('E')][19]).setDoorway();
        //Dining Room
        ((RoomTile)board[charToInt('M')][8]).setDoorway();
        ((RoomTile)board[charToInt('P')][7]).setDoorway();
        //Billiard Room
        ((RoomTile)board[charToInt('J')][19]).setDoorway();
        ((RoomTile)board[charToInt('M')][23]).setDoorway();
        //Library
        ((RoomTile)board[charToInt('O')][21]).setDoorway();
        ((RoomTile)board[charToInt('Q')][18]).setDoorway();
        //Lounge
        ((RoomTile)board[charToInt('T')][7]).setDoorway();
        //Hall
        ((RoomTile)board[charToInt('S')][12]).setDoorway();
        ((RoomTile)board[charToInt('S')][13]).setDoorway();
        ((RoomTile)board[charToInt('U')][15]).setDoorway();
        //Study
        ((RoomTile)board[charToInt('V')][18]).setDoorway();
    }

    public void addWeapons(ArrayList<Card> weps) {
        for(Card card : weps) {
            String name = ((WeaponCard) card).getWeaponName();
            weapons.put(name, new Weapon(name));
        }
        rooms.get("Lounge").getPotentialWeapons().add(weapons.get("Dagger"));
        rooms.get("Dining Room").getPotentialWeapons().add(weapons.get("Candlestick"));
        rooms.get("Study").getPotentialWeapons().add(weapons.get("Revolver"));
        rooms.get("Ballroom").getPotentialWeapons().add(weapons.get("Rope"));
        rooms.get("Conservatory").getPotentialWeapons().add(weapons.get("Lead Piping"));
        rooms.get("Kitchen").getPotentialWeapons().add(weapons.get("Spanner"));

    }

    /**
     * Converts character coordinate to integer index
     * @param c - the board Y coordinate
     * @return  - the corresponding Y index
     */
    public int charToInt(char c) { return c-64; }

}
