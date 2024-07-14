package org.silverbullet.entity;

import lombok.Getter;

@Getter
public enum TestStatus {
	NOT_YET(0),
	PASS(1),
	FAIL(2);

	private int status;

	TestStatus(int i) {
		this.status = i;
	}
}
