package nomelin.enums;

public enum Term {
    F1("大一上"),
    L1("大一下"),
    F2("大二上"),
    L2("大二下"),
    F3("大三上"),
    L3("大三下"),
    F4("大四上"),
    L4("大四下");

    private final String label;

    Term(String label) {
        this.label = label;
    }

    public String term() {
        return label;
    }
}
