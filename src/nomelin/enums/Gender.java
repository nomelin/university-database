package nomelin.enums;

public enum Gender {
    M("男"),
    F("女");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public String gender() {
        return label;
    }

}
