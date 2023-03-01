package claudiosoft.opusCV.common;

/**
 *
 * @author Claudio
 */
public enum ErrorCode {
    OK("0000", "OK"),
    //////// initialization
    INIT_ERROR("0100", "generic initialization error"),
    INIT_OPENCV_ERROR("0101", "opencv initialization error"),
    INIT_OPENCV_NOT_LOADED("0102", "OpenCVNative not loaded"),
    //////// steps parsing
    JSON_WRITE("0200", "Error writing object to json"),
    JSON_READ("0201", "Error reading object from json"),
    UNSUPPORTED_JSON_OBJ("0202", "Unsupported json object"),
    INCOMPLETE_MACRO_OBJ("0203", "Incomplete macro object"),
    //////// providers
    UNSUPPORTED_PROVIDER("0300", "Unsupported provider"),
    //////// utility
    UTILITY_DEL_FOLDER("0400", "Only process subfolders can be deleted"),
    //////// images
    IMG_INVALID_IMAGE("1000", "invalid image"),
    IMG_ALREADY_EXISTS("1001", "output image already exists"),
    IMG_OUTPUT_NOT_DEFINED("1002", "output image not defined"),
    //////// video
    VID_CANNOT_OPEN("2000", "cannot open the video file"),
    VID_INVALID_FRAME_RANGE("2001", "invalid frame range"),
    VID_INVALID_FRAME_STEP("2002", "invalid frame step value"),
    //////// generic
    GENERIC_ERROR("9999", "generic error");

    private String code;
    private String message;

    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getCodeMessage() {
        return String.format("%s-%s", code, message);
    }

    public String getCodeMessage(String msg) {
        return String.format("%s-%s", code, msg);
    }

}
