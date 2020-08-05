import java.util.ArrayList;

public class UI {

    //TODO - initialiseGame(), asks the basic beginning questions, how many players? how is what char?
    //TODO - displayBoard()
    //TODO - displayCards() - prints what cards you have in your hand
    //TODO - Questions at the beginning of each turn, where do you want to move?
    //TODO - describeTile(), when you move a tile, describe what is around you
    //TODO - describeRoom(), when you enter a room, describe room name, what weapons and players are already in the room
    //TODO - ask for who you suspect(CONDITION: if player are in a room)
    //TODO - declareAccusation() - the final declaration of who it is with what wep in what room





    public void initialiseGame() {

    }


    /**
     *
     */
    public void displayBoard() {
        String b =
        "\n   |01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|" +
        "\n A |//|//|//|//|//|//|//|//|//|MW|//|//|//|//|MG|//|//|//|//|//|//|//|//|//|" +
        "\n B |KI|KI|KI|KI|KI|KI|//|__|__|__|BA|BA|BA|BA|__|__|__|//|CN|CN|CN|CN|CN|CN|" +
        "\n C |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|CN|CN|CN|CN|CN|CN|" +
        "\n D |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|CN|CN|CN|CN|CN|CN|" +
        "\n E |KI|KI|KI|KI|KI|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|[]|CN|CN|CN|CN|CN|" +
        "\n F |KI|KI|KI|KI|KI|KI|__|__|[]|BA|BA|BA|BA|BA|BA|[]|__|__|__|CN|CN|CN|CN|//|" +
        "\n G |//|KI|KI|KI|[]|KI|__|__|BA|BA|BA|BA|BA|BA|BA|BA|__|__|__|__|__|__|__|MP|" +
        "\n H |__|__|__|__|__|__|__|__|BA|[]|BA|BA|BA|BA|[]|BA|__|__|__|__|__|__|__|//|" +
        "\n I |//|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|BI|BI|BI|BI|BI|BI|" +
        "\n J |DR|DR|DR|DR|DR|__|__|__|__|__|__|__|__|__|__|__|__|__|[]|BI|BI|BI|BI|BI|" +
        "\n K |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|BI|BI|BI|BI|BI|BI|" +
        "\n L |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|BI|BI|BI|BI|BI|BI|" +
        "\n M |DR|DR|DR|DR|DR|DR|DR|[]|__|__|CC|CC|CC|CC|CC|__|__|__|BI|BI|BI|BI|[]|BI|" +
        "\n N |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|__|__|__|__|__|//|" +
        "\n O |DR|DR|DR|DR|DR|DR|DR|DR|__|__|CC|CC|CC|CC|CC|__|__|__|LI|LI|[]|LI|LI|//|" +
        "\n P |DR|DR|DR|DR|DR|DR|[]|DR|__|__|CC|CC|CC|CC|CC|__|__|LI|LI|LI|LI|LI|LI|LI|" +
        "\n Q |//|__|__|__|__|__|__|__|__|__|CC|CC|CC|CC|CC|__|__|[]|LI|LI|LI|LI|LI|LI|" +
        "\n R |CM|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|__|LI|LI|LI|LI|LI|LI|LI|" +
        "\n S |//|__|__|__|__|__|__|__|__|HA|HA|[]|[]|HA|HA|__|__|__|LI|LI|LI|LI|LI|//|" +
        "\n T |LO|LO|LO|LO|LO|LO|[]|__|__|HA|HA|HA|HA|HA|HA|__|__|__|__|__|__|__|__|PP|" +
        "\n U |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|[]|__|__|__|__|__|__|__|__|//|" +
        "\n V |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|[]|ST|ST|ST|ST|ST|ST|" +
        "\n W |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|ST|ST|ST|ST|ST|ST|ST|" +
        "\n U |LO|LO|LO|LO|LO|LO|LO|__|__|HA|HA|HA|HA|HA|HA|__|__|ST|ST|ST|ST|ST|ST|ST|" +
        "\n V |LO|LO|LO|LO|LO|LO|//|MS|//|HA|HA|HA|HA|HA|HA|//|__|//|ST|ST|ST|ST|ST|ST|";
    }


}