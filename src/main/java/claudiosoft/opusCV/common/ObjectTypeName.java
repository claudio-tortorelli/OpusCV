package claudiosoft.opusCV.common;

/**
 *
 * @author Claudio
 */
public enum ObjectTypeName {
    BASE_STEP("BaseStep"),
    DUMMY_OBJECT("DummyObject"),
    DUMMY_STEP("DummyStep"),
    MACRO_STEP("MacroStep");

    private String name;

    public static final String OBJ_NAME_ID = "objTypeName";

    private ObjectTypeName(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
