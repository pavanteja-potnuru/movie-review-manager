package reviewmanager.model;

public enum Role {
    Viewer (1),
    Critic (2),
    Expert (4),
    Admin (8);

    private final int value;
    private Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
