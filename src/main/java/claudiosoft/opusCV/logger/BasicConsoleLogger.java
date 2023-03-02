package claudiosoft.opusCV.logger;

import claudiosoft.opusCV.common.Configuration;
import claudiosoft.opusCV.common.OpusCVException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Claudio
 */
public class BasicConsoleLogger {

    static {
        // console logger formatting: 2020-04-04 19:42:45 INFO - msg
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS - %5$s%6$s%n");
    }

    public enum LogLevel {
        NORMAL,
        DEBUG
    }
    private final Logger internalLogger;
    private final LogLevel level;
    private static BasicConsoleLogger consoleLogger = null;

    public static BasicConsoleLogger get() throws OpusCVException {
        return get(Configuration.get().getLogLevel());
    }

    public static BasicConsoleLogger get(LogLevel level) throws OpusCVException {
        return get(Configuration.get().getLogLevel(), "OpusLogger");
    }

    public static BasicConsoleLogger get(LogLevel level, String logName) {
        if (consoleLogger == null) {
            consoleLogger = new BasicConsoleLogger(level, logName);
        }
        return consoleLogger;
    }

    private BasicConsoleLogger(LogLevel level, String logName) {
        internalLogger = Logger.getLogger(logName);
        internalLogger.setLevel(Level.FINER);
        this.level = level;
    }

    public void info(String message) {
        internalLogger.log(Level.INFO, "[INFO] {0}", message);
    }

    public void error(String message) {
        error(message, null);
    }

    public void error(String message, Exception ex) {
        if (ex != null) {
            internalLogger.log(Level.SEVERE, "[SEVERE] " + message, ex);
        } else {
            internalLogger.log(Level.SEVERE, "[SEVERE] {0}", message);
        }
    }

    public void debug(String message) {
        if (level == LogLevel.DEBUG) {
            internalLogger.log(Level.INFO, "[DEBUG] {0}", message);
        }
    }
}
