public class Room {
    boolean isCrimeScene = false;
    private final String roomName;

    public Room(String name) {
        roomName = name;
    }

    public void isCrimeScene() { isCrimeScene = true; }
}
