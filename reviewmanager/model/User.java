package reviewmanager.model;

public class User {
    /**
     * user's name
     */
    private String name;

    /**
     * user role
     */
    private Role role;

    /**
     * reviewcount mod(role.getReviewLimit()) in current role
     * Stores the remainder
     */
    private int reviewCount;

    /**
     * Constructor
     * @param name
     */
    public User(String name) {
        this.name = name;
        this.role = Role.viewer;
        this.reviewCount = 0;
    }

    /**
     * get name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * get role
     * @return
     */
    public Role getRole() {
        return role;
    }

    /**
     * increments review count in current role
     */
    public void incrementReviewCount() {
        reviewCount++;
        if(reviewCount == role.getReviewLimit()) {
            role = role.nextRole();
            reviewCount = 0;
        }
    }
}
