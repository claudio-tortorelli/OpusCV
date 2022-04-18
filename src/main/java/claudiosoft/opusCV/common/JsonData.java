package claudiosoft.opusCV.common;

/**
 *
 * @author Claudio
 */
public class JsonData {

    private String key;
    private Object value;
    private Object defaultValue;

    public JsonData(String key, Object defaultValue) {
        this(key, null, defaultValue);
    }

    public JsonData(String key, Object value, Object defaultValue) {
        this.key = key;
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

}
