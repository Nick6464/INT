import Cards.RoomCard;

import java.util.ArrayList;
import java.util.HashMap;

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
public class Board {
    private HashMap<String,Room> rooms;
    private Tile[][] board;

    public Board() {
        board = new Tile[25][24];
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
        loadCorridor();
        loadWalls();
    }

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
            board[((int)'B'-65)][i] = new RoomTile(rooms.get(room));
        for (int i: C)
            board[((int)'C'-65)][i] = new RoomTile(rooms.get(room));
        for (int i: D)
            board[((int)'D'-65)][i] = new RoomTile(rooms.get(room));
        for (int i: E)
            board[((int)'E'-65)][i] = new RoomTile(rooms.get(room));
        for (int i: F)
            board[((int)'F'-65)][i] = new RoomTile(rooms.get(room));
    }

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
            board[((int)'B'-65)][i] = new RoomTile(rooms.get(room));
        for (int i: C)
            board[((int)'C'-65)][i] = new RoomTile(rooms.get(room));
        for (int i: D)
            board[((int)'D'-65)][i] = new RoomTile(rooms.get(room));
        for (int i: E)
            board[((int)'E'-65)][i] = new RoomTile(rooms.get(room));
        for (int i: F)
            board[((int)'F'-65)][i] = new RoomTile(rooms.get(room));
        for (int i: G)
            board[((int)'G'-65)][i] = new RoomTile(rooms.get(room));
        for (int i: H)
            board[((int)'H'-65)][i] = new RoomTile(rooms.get(room));
    }

    private void loadConservatory() {
        int[] B = {19,20,21,22,23,24};
        int[] C = {19,20,21,22,23,24};
        int[] D = {19,20,21,22,23,24};
        int[] E = {19,20,21,22,23,24};
        int[] F = {20,21,22,23};
    }

    private void loadDiningRoom() {
        int[] J = {1,2,3,4,5};
        int[] K = {1,2,3,4,5,6,7,8};
        int[] L = {1,2,3,4,5,6,7,8};
        int[] M = {1,2,3,4,5,6,7,8};
        int[] N = {1,2,3,4,5,6,7,8};
        int[] O = {1,2,3,4,5,6,7,8};
        int[] P = {1,2,3,4,5,6,7,8};
    }

    private void loadBilliardRoom() {
        int[] I = {19,20,21,22,23,24};
        int[] J = {19,20,21,22,23,24};
        int[] K = {19,20,21,22,23,24};
        int[] L = {19,20,21,22,23,24};
        int[] M = {19,20,21,22,23,24};
    }

    private void loadLibrary() {
        int[] O = {19,20,21,22,23};
        int[] P = {18,19,20,21,22,23,24};
        int[] Q = {18,19,20,21,22,23,24};
        int[] R = {18,19,20,21,22,23,24};
        int[] S = {19,20,21,22,23};
    }

    private void loadLounge() {
        int[] T = {1,2,3,4,5,6,7};
        int[] U = {1,2,3,4,5,6,7};
        int[] V = {1,2,3,4,5,6,7};
        int[] W = {1,2,3,4,5,6,7};
        int[] X = {1,2,3,4,5,6,7};
        int[] Y = {1,2,3,4,5,6};
    }

    private void loadHall() {
        int[] S = {10,11,12,13,14,15};
        int[] T = {10,11,12,13,14,15};
        int[] U = {10,11,12,13,14,15};
        int[] V = {10,11,12,13,14,15};
        int[] W = {10,11,12,13,14,15};
        int[] X = {10,11,12,13,14,15};
        int[] Y = {11,12,13,14};
    }

    private void loadStudy() {
        int[] V = {18,19,20,21,22,23,24};
        int[] W = {18,19,20,21,22,23,24};
        int[] X = {18,19,20,21,22,23,24};
        int[] Y = {19,20,21,22,23,24};
    }

    private void loadCorridor() {}

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
