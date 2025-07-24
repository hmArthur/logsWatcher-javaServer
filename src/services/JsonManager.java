package services;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.List;
import com.google.gson.Gson;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;

public final class JsonManager {
	/*generalistic way to read a array of objetcs inside a json */
	public static <T> List<T> readData(TypeToken<List<T>> typeToken, String fileName, Gson gson) {
			File file = new File(fileName);
			
			/*if doesn't find the file or its empty, it should 
			 * return a empty array, then in the function insertData
			 * the file finally is created
			 */
			if (!file.exists() || file.length() == 0) {
				return new ArrayList<>();
			}

		
			try (FileReader reader = new FileReader(file)) {
				return gson.fromJson(reader, typeToken.getType());
			} catch (Exception e) {
				return new ArrayList<>();
			}
			
	}
	
	/* 
	 yes i know that re-write the entire file again may not be a good solution,
	 but serves its purpose for now... 
	 */
	public static void insertData(List list, Gson gson, String fileName) throws IOException {
		FileWriter file;
	
		file = new FileWriter(fileName);
		gson.toJson(list, file);	
		file.flush();
		file.close();
	}
}
