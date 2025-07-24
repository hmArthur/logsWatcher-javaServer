package services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Deploy;
import model.DeployRecord;

public class DeployService {
    public static Deploy writeToJson(String body, String DIRPATH, Gson gson) throws IOException {
		Deploy deploy = new Deploy(gson.fromJson(body, DeployRecord.class));
        String filename = (DIRPATH+deploy.getTimestamp()+".json");
		List<Deploy> list = JsonManager.readData(new TypeToken<List<Deploy>>() {}, filename, gson);
		list.add(deploy);
		JsonManager.insertData(list, gson, filename);
        return deploy;
    }

    public static LocalDate queryDateParser(String query) {
        int indexOfDate = query.indexOf("date");
		String dateStr = query.substring(indexOfDate+5, indexOfDate+15);

        LocalDate date = LocalDate.parse(dateStr);
        return date; 
    }
}
