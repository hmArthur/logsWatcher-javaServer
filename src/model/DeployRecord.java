package model;

public record DeployRecord(
    String app,
    String status,
    String environment,
    String triggeredBy,
    String hashCommit,
    String timestamp
) {}