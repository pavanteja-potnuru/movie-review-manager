package reviewmanager.model;

public enum Role {
    Viewer (1),
    Critic (2),
    Expert (4),
    Admin (8);

    /**
     * weightage 
     */
    private final int weightage;
    private Role(int weightage) {
        this.weightage = weightage;
    }

    public int getWeightage() {
        return weightage;
    }

    public Role nextRole() {
        switch(this) {
            case Viewer: return Critic;
            case Critic: return Expert;
            case Expert: return Admin;
            default: return Admin;
        }
    }

    /**
     * Strategy review limit in current role is 3^rol.weightage
     * @return
     */
    public int getReviewLimit() {
        return (int) Math.pow(3, this.getWeightage());
    }
}
