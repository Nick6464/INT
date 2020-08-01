import java.util.HashMap;

/**
 * Class for the "Board" the game is played on.
 * 24x25 (24 tiles across, 25 tiles down)
 * (int)'A'-64 converts char A to 1
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
public class Board {
    private HashMap<String,Room> rooms;
    private Tile[][] board;

    /**
     * Initialises the playing board
     */
    public Board() {
        board = new Tile[26][25]; //[0][0] Not used to allow for easier indexing - A1 == [1][1]
        rooms = new HashMap<>();
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
        //Add corridors and walls
        loadCellar();
        loadCorridor();
        loadWalls();
    }

    public Tile[][] getBoard() { return board; }

    public HashMap<String,Room> getRooms() {return rooms; }

    /**
     * Loads the Kitchen tiles, and adds Kitchen to the rooms list
     */
    private void loadKitchen() {
        String room = "Kitchen";
        rooms.put(room, new Room(room));

        int[] B = {1,2,3,4,5,6}; //every tile here will have a north wall
        int[] C = {1,2,3,4,5,6};
        int[] D = {1,2,3,4,5,6};
        int[] E = {1,2,3,4,5,6};
        int[] F = {1,2,3,4,5,6};
        int[] G = {2,3,4,5,6};   //every tile here will have a south wall

        for (int i: B)
            board[((int)'B'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: C)
            board[((int)'C'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: D)
            board[((int)'D'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: E)
            board[((int)'E'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: F)
            board[((int)'F'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: G)
            board[((int)'G'-64)][i] = new RoomTile(rooms.get(room));
    }

    /**
     * Loads the Ballroom tiles, and adds Ballroom to the rooms list
     */
    private void loadBallroom() {
        String room = "Ballroom";
        rooms.put(room, new Room(room));

        int[] B = {11,12,13,14};
        int[] C = {9,10,11,12,13,14,15,16};
        int[] D = {9,10,11,12,13,14,15,16};
        int[] E = {9,10,11,12,13,14,15,16};
        int[] F = {9,10,11,12,13,14,15,16};
        int[] G = {9,10,11,12,13,14,15,16};
        int[] H = {9,10,11,12,13,14,15,16};

        for (int i: B)
            board[((int)'B'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: C)
            board[((int)'C'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: D)
            board[((int)'D'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: E)
            board[((int)'E'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: F)
            board[((int)'F'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: G)
            board[((int)'G'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: H)
            board[((int)'H'-64)][i] = new RoomTile(rooms.get(room));
    }

    /**
     * Loads the Conservatory tiles, and adds Conservatory to the rooms list
     */
    private void loadConservatory() {
        String room = "Conservatory";
        rooms.put(room, new Room(room));

        int[] B = {19,20,21,22,23,24};
        int[] C = {19,20,21,22,23,24};
        int[] D = {19,20,21,22,23,24};
        int[] E = {19,20,21,22,23,24};
        int[] F = {20,21,22,23};

        for (int i: B)
            board[((int)'B'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: C)
            board[((int)'C'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: D)
            board[((int)'D'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: E)
            board[((int)'E'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: F)
            board[((int)'F'-64)][i] = new RoomTile(rooms.get(room));
    }

    /**
     * Loads the Dining Room tiles, and adds Dining Room to the rooms list
     */
    private void loadDiningRoom() {
        String room = "Dining Room";
        rooms.put(room, new Room(room));

        int[] J = {1,2,3,4,5};
        int[] K = {1,2,3,4,5,6,7,8};
        int[] L = {1,2,3,4,5,6,7,8};
        int[] M = {1,2,3,4,5,6,7,8};
        int[] N = {1,2,3,4,5,6,7,8};
        int[] O = {1,2,3,4,5,6,7,8};
        int[] P = {1,2,3,4,5,6,7,8};

        for (int i: J)
            board[((int)'J'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: K)
            board[((int)'K'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: L)
            board[((int)'L'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: M)
            board[((int)'M'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: N)
            board[((int)'N'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: O)
            board[((int)'O'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: P)
            board[((int)'P'-64)][i] = new RoomTile(rooms.get(room));
    }

    /**
     * Loads the Billiard Room tiles, and adds Billiard Room to the rooms list
     */
    private void loadBilliardRoom() {
        String room = "Billiard Room";
        rooms.put(room, new Room(room));

        int[] I = {19,20,21,22,23,24};
        int[] J = {19,20,21,22,23,24};
        int[] K = {19,20,21,22,23,24};
        int[] L = {19,20,21,22,23,24};
        int[] M = {19,20,21,22,23,24};

        for (int i: I)
            board[((int)'I'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: J)
            board[((int)'J'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: K)
            board[((int)'K'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: L)
            board[((int)'L'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: M)
            board[((int)'M'-64)][i] = new RoomTile(rooms.get(room));
    }

    /**
     * Loads the Library tiles, and adds Library to the rooms list
     */
    private void loadLibrary() {
        String room = "Library";
        rooms.put(room, new Room(room));

        int[] O = {19,20,21,22,23};
        int[] P = {18,19,20,21,22,23,24};
        int[] Q = {18,19,20,21,22,23,24};
        int[] R = {18,19,20,21,22,23,24};
        int[] S = {19,20,21,22,23};

        for (int i: O)
            board[((int)'O'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: P)
            board[((int)'P'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: Q)
            board[((int)'Q'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: R)
            board[((int)'R'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: S)
            board[((int)'S'-64)][i] = new RoomTile(rooms.get(room));
    }

    /**
     * Loads the Lounge tiles, and adds Lounge to the rooms list
     */
    private void loadLounge() {
        String room = "Lounge";
        rooms.put(room, new Room(room));

        int[] T = {1,2,3,4,5,6,7};
        int[] U = {1,2,3,4,5,6,7};
        int[] V = {1,2,3,4,5,6,7};
        int[] W = {1,2,3,4,5,6,7};
        int[] X = {1,2,3,4,5,6,7};
        int[] Y = {1,2,3,4,5,6};

        for (int i: T)
            board[((int)'T'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: U)
            board[((int)'U'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: V)
            board[((int)'V'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: W)
            board[((int)'W'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: X)
            board[((int)'X'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: Y)
            board[((int)'Y'-64)][i] = new RoomTile(rooms.get(room));
    }

    /**
     * Loads the Hall tiles, and adds Hall to the rooms list
     */
    private void loadHall() {
        String room = "Hall";
        rooms.put(room, new Room(room));

        int[] S = {10,11,12,13,14,15};
        int[] T = {10,11,12,13,14,15};
        int[] U = {10,11,12,13,14,15};
        int[] V = {10,11,12,13,14,15};
        int[] W = {10,11,12,13,14,15};
        int[] X = {10,11,12,13,14,15};
        int[] Y = {11,12,13,14};

        for (int i: S)
            board[((int)'S'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: T)
            board[((int)'T'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: U)
            board[((int)'U'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: V)
            board[((int)'V'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: W)
            board[((int)'W'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: X)
            board[((int)'X'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: Y)
            board[((int)'Y'-64)][i] = new RoomTile(rooms.get(room));
    }

    /**
     * Loads the Study tiles, and adds Study to the rooms list
     */
    private void loadStudy() {
        String room = "Study";
        rooms.put(room, new Room(room));

        int[] V = {18,19,20,21,22,23,24};
        int[] W = {18,19,20,21,22,23,24};
        int[] X = {18,19,20,21,22,23,24};
        int[] Y = {19,20,21,22,23,24};

        for (int i: V)
            board[((int)'V'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: W)
            board[((int)'W'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: X)
            board[((int)'X'-64)][i] = new RoomTile(rooms.get(room));
        for (int i: Y)
            board[((int)'Y'-64)][i] = new RoomTile(rooms.get(room));
    }

    /**
     * Loads the Cellar tiles, but does not add to rooms list
     */
    private void loadCellar() {
        Room cellar = new Room("Cellar");
        for(int y = (int)'K'-64; y <= (int)'Q'-64; y++)
            for(int x = 11; x <= 15; x++)
                board[y][x] = new RoomTile(cellar);
    }

    /**
     * Fills in board with Corridor tiles
     */
    private void loadCorridor() {
        //[B -> X][[2 -> 23]
        for(int y = 2; y < board.length; y++)
            for(int x = 2; x < board[x].length; x++)
                if(board[y][x] == null)
                    board[y][x] = new CorridorTile();
        //Outlier corridor tiles (mostly used for starting points)
        board[(int)'A'-64][10]  = new CorridorTile();
        board[(int)'A'-64][15]  = new CorridorTile();
        board[(int)'G'-64][24]  = new CorridorTile();
        board[(int)'H'-64][1]   = new CorridorTile();
        board[(int)'R'-64][1]   = new CorridorTile();
        board[(int)'T'-64][24]  = new CorridorTile();
        board[(int)'y'-64][8]   = new CorridorTile();
        board[(int)'Y'-64][17]  = new CorridorTile();
    }

    private void loadWalls() {
        //North Walls//
        //South Walls//
        //East Walls//
        //West Walls//

        //for each room the first array of every room will have a north wall
        //1. iterate through the array of tiles
        //2. set the wall direction to North
        //3. set the wall direction for the tiles above as south.

    }
}
