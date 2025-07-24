package handlers;
import com.sun.net.httpserver.HttpHandler;

import model.Deploy;
import model.Log;

import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import services.DeployService;
import services.FileHandler;
import services.JsonManager;
import services.LogWriter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

public class DeployHandler implements HttpHandler {
	final String DIRPATH;
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public DeployHandler(String DIRPATH) { this.DIRPATH = DIRPATH; }
	
	@Override
	public void handle(HttpExchange exchange) {
		long timeElapsed = System.currentTimeMillis();
		int code = 400;
		String method = exchange.getRequestMethod();
		String body = "";
		String query = exchange.getRequestURI().getQuery();
		OutputStream output;
		Optional<Deploy> deploy = Optional.empty();
		exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
		
		if(method.equals("POST")) {
		   try {
				body = handlePost(exchange);
				deploy = Optional.of(gson.fromJson(body, Deploy.class));
				code = 200;
			} catch (IOException e) {
				body = "{\"error\": \"Invalid format, check if it's missing any information on the request body.\"}";
			}
		} else if (method.equals("GET")) {
			  try {
					body = handleGet(query);
					code = 200;
				} catch (DateTimeParseException e) {
					body = "{\"error\": \"Invalid format given on query, it should be yyyy-mm-dd.\"}";
				}
		}

		try {
			exchange.sendResponseHeaders(code, body.length());
			output = exchange.getResponseBody();
			output.write(body.getBytes());
			output.flush();
		} catch (Exception e) {
			/* last tentative */
			try {
				body = "\"error\": \"Internal Server Error.\"";
				code = 500;
				exchange.sendResponseHeaders(code, body.length());
				output = exchange.getResponseBody();
				output.write(body.getBytes());
				output.flush();
			} catch (IOException excep) {}
		} 
		
		timeElapsed -= System.currentTimeMillis() - timeElapsed;
		Log myLog = new Log(method, exchange.getRequestURI().getPath().toString(), code, timeElapsed, exchange.getRemoteAddress().getAddress().getHostAddress().toString(), deploy.orElse(null));
		LogWriter.appendLog(DIRPATH,(LocalDate.now().toString()), gson, myLog);
		exchange.close();
	}

	private String handleGet(String query) throws DateTimeParseException {
		LocalDate date = DeployService.queryDateParser(query);
		List<Deploy> data = JsonManager.readData(
			new TypeToken<List<Deploy>>() {}, 
			DIRPATH + date.toString() + ".json", 
			gson
		);
		return gson.toJson(data);
	}

	private String handlePost(HttpExchange exchange) throws IOException {
		String body = FileHandler.inputStreamIntoString(exchange);
		Deploy deploy = DeployService.writeToJson(body, DIRPATH, gson);
		return gson.toJson(deploy);
	}
}
