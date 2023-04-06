package com.c8h10n4o2;

public enum TaskStatus {

	WAITING("WAITING"), RUNNING("RUNNING"), STOPPED("STOPPED"), PROBLEM("PROBLEM");

	private final String code;

	TaskStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static TaskStatus get(String code) {
		if (code != null) {
			for (TaskStatus item : TaskStatus.values()) {
				if (item.getCode().equals(code)) {
					return item;
				}
			}
		}

		return null;
	}
}
