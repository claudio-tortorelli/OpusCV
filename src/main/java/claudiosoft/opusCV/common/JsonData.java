package claudiosoft.opusCV.common;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;

/**
 *
 * @author Claudio
 */
public interface JsonData {

    public abstract String toJson() throws OpusCVException;

    public abstract JsonObject fromJson(String json) throws JsonException;

}
