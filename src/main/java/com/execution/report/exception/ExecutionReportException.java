package com.execution.report.exception;

import org.springframework.http.HttpStatus;

public class ExecutionReportException extends RuntimeException {

	private HttpStatus status;
	private String message;

	public ExecutionReportException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ExecutionReportException(HttpStatus status, String message, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}

	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

}
