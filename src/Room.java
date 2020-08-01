public class Room {
    private boolean isCrimeScene = false;
    private final String roomName;

    public Room(String name) {
        roomName = name;
    }

    public void setCrimeScene() { isCrimeScene = true; }

    public boolean isCrimeScene() { return  isCrimeScene; }
}
