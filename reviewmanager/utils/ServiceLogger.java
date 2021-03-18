package reviewmanager.utils;

import java.util.LinkedList;

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
        logs.add(String.format("Log: %s", message));
    }

    public void logError(String message) {
        logs.add(String.format("Error: %s", message));
    }
}
