import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;

import handlers.DeployHandler;

public class App {
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
		final String DIRPATH =  "./out/data/";
		final int THREAD_SIZE = 100;
		InetSocketAddress addr = new InetSocketAddress("localhost", port);
		Executor maxPool = (Executor) Executors.newFixedThreadPool(THREAD_SIZE);
		DeployHandler deplHand = new DeployHandler(DIRPATH);
		HttpServer server;

		try {
			server = HttpServer.create(addr, 0);
			server.setExecutor(maxPool);
			server.createContext("/deploys", deplHand);
			server.start();
			System.out.println("Running on port: " + port);
		} catch (IOException e) {
			System.out.println("Fail on starting the server.");
		}
    }
}
