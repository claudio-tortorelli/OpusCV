package claudiosoft.opusCV.common;

/**
 *
 * @author Claudio
 */
public enum ErrorCode {
    OK("0000", "OK"),
    INIT_ERROR("0100", "generic initialization error"),
    INIT_OPENCV_ERROR("0101", "opencv initialization error"),
    INIT_OPENCV_NOT_LOADED("0102", "OpenCVNative not loaded"),
    UNSUPPORTED_ENGINE("0150", "Unsupported engine"),
    IMG_INVALID_IMAGE("0200", "invalid image"),
    IMG_ALREADY_EXISTS("0201", "output image already exists"),
    IMG_OUTPUT_NOT_DEFINED("0202", "output image not defined"),
    VID_CANNOT_OPEN("0300", "cannot open the video file"),
    VID_INVALID_FRAME_RANGE("0301", "invalid frame range"),
    VID_INVALID_FRAME_STEP("0302", "invalid frame step value"),
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

}
