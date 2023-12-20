package nomelin.enums;

public enum Type {
    C("必修"),
    O("选修");

    private final String label;

    Type(String label) {
        this.label = label;
    }

    public String type() {
        return label;
    }
}
