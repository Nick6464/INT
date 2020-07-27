package Cards;

public class RoomCard implements Card {
    private String roomName;

    public RoomCard(String roomName) {
    this.roomName = roomName;
    }

    public String toString() {
        return "Room: " + roomName;
    }
}
