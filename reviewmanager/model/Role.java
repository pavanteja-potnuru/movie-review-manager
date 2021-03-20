package reviewmanager.model;

public enum Role {
    viewer (1),
    critic (2),
    expert (4),
    admin (8);

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
            case viewer: return critic;
            case critic: return expert;
            case expert: return admin;
            default: return admin;
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
