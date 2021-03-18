package reviewmanager.utils;

public interface IServiceLogger {
    public void printLogs();
    public void logInfo(String message);
    public void logError(String message);
}
