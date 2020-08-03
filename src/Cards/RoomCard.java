package Cards;

public class RoomCard implements Card {
    private final String roomName;

    public RoomCard(String roomName) {
    this.roomName = roomName;
    }

    public String getRoomName() { return roomName; }

    public String toString() {
        return "Room: " + roomName;
    }
}
