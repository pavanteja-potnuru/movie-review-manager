package reviewmanager.utils;

import java.util.LinkedList;
import reviewmanager.model.Color;

public class ServiceLogger implements IServiceLogger {

    private LinkedList<String> logs;

    public ServiceLogger() {
        logs = new LinkedList<String>();
    }

    public void printLogs() {
        logs.stream().forEach((log) -> {
           System.out.println(log);
        }); 
    }

    public void logInfo(String message) {
        logInfo(message, Color.ANSI_GREEN);
    }
    public void logInfo(String message, String color) {
        logs.add(String.format("Log: %s", color + message + Color.ANSI_RESET));
    }

    public void logError(String message) {
        logError(message, Color.ANSI_RED);
    }
    public void logError(String message, String color) {
        logs.add(String.format("Error: %s", color + message + Color.ANSI_RESET));
    }
}
