package services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Deploy;
import model.Log;

public interface LogWriter {
    public static void appendLog(String DIRPATH, String fileName, Gson gson, Log log) {
        String filePath = DIRPATH+"log-"+fileName+".json";
        List<Log> list = JsonManager.readData(new TypeToken<List<Log>>() {}, filePath, gson);
        list.add(log);
        
        try {
            JsonManager.insertData(list, gson, filePath);
        } catch (IOException e) {
            System.err.println("Error writing log to " + filePath + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
} 