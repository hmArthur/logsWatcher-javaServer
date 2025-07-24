package model;

import java.time.LocalDateTime;

import com.google.gson.annotations.Expose;

public class Log {
	private final String timestamp;
	private final String method;
	private final Deploy deploy;
	private final String endpoint;
	private final int status;
	private final float timeElapsedMs;
	private final String ip;
	
	public Log(String method, String endpoint, int status, float timeElapsed, String ip, Deploy deploy) {
		this.method = method;
		this.endpoint = endpoint;
		this.status = status;
		this.timeElapsedMs = timeElapsed;
		this.ip = ip;
		this.timestamp = LocalDateTime.now().toString();
		this.deploy = deploy;
	}

	public String getTimestamp() { return this.timestamp; }
	public String getMethod() { return this.method; }
	public String getEndpoint() { return this.endpoint; }
	public int getStatus() { return this.status; }
	public float getTimeElapsedMs() { return this.timeElapsedMs; }
	public String getIp() { return this.ip; }
	public Deploy getDeploy() { return this.deploy; }
}
