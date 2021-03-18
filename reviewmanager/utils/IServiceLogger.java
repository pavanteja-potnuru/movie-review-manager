package reviewmanager.utils;
public interface IServiceLogger {
    /**
     * Print all logs
     */
    public void printLogs();

    /**
     * Info log with default color
     * @param message
     */
    public void logInfo(String message);

    /**
     * Info log with given color
     * @param message
     */
    public void logInfo(String message, String color);
    
    /**
     * Error log with default color
     * @param message
     */
    public void logError(String message);
    
    /**
     * Error log with given color
     * @param message
     */
    public void logError(String message, String color);
}
