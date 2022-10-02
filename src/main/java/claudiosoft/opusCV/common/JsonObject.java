package claudiosoft.opusCV.common;

/**
 *
 * @author Claudio
 */
public abstract class JsonObject {

    public static final String OBJ_TYPENAME = "objTypeName";

    private String objTypeName;

    public JsonObject(ObjectTypeName objName) {
        this.objTypeName = objName.get();
    }

    public String getObjTypeName() {
        return objTypeName;
    }

    public void setObjTypeName(String objTypeName) {
        this.objTypeName = objTypeName;
    }
}
