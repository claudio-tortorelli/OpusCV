package claudiosoft.opusCV.common;

/**
 *
 * @author Claudio
 */
public enum ObjectTypeName {
    DUMMY_OBJECT("DummyObject"),
    DUMMY_STEP("DummyStep"),
    RESIZE_IMAGE_STEP("ResizeImageStep"),
    MACRO_STEP("MacroStep");

    private String name;

    private ObjectTypeName(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
