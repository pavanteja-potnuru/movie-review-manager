package reviewmanager.model;

public class User {
    /**
     * user's name
     */
    private String name;

    /**
     * email id
     */
    private String email;

    /**
     * user role
     */
    private Role role;


    /**
     * 
     * @param name
     */

    /**
     * Constructor
     * @param name
     */
    public User(String name) {
        this.name = name;
        this.role = Role.Viewer;
        this.email = null;
    }

    /**
     * get name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * set email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get role
     * @return
     */
    public Role getRole() {
        return role;
    }

    /**
     * set email
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
