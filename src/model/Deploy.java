package model;

import java.util.UUID;


public class Deploy {
	private String id;
	private String app;
	private String status;
	private String environment;
	private String triggeredBy;
	private String hashCommit;
	private String timestamp;

	public Deploy(DeployRecord data) {
		this.id = UUID.randomUUID().toString();
		this.timestamp = data.timestamp();
		this.app = data.app();
		this.status = data.status();
		this.environment = data.environment();
		this.triggeredBy = data.triggeredBy();
		this.hashCommit = data.hashCommit();
	}

	public String getId() { return this.id; }
	public String getApp() { return this.app; }
	public String getStatus() { return this.status; }
	public String getEnvironment() { return this.environment; }
	public String getTriggeredBy() { return this.triggeredBy; }
	public String getHashCommit() { return this.hashCommit; }
	public String getTimestamp() { return this.timestamp; }

}
