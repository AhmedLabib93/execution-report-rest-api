package com.execution.report.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class TaskBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	private LocalDateTime startDateTime;

	@UpdateTimestamp
	private LocalDateTime endDateTime;

	@ReadOnlyProperty
	private Long executionTimeSeconds;

	private String errorMessage;

	public Long getExecutionTimeSeconds() {
		if (startDateTime != null && endDateTime != null)
			executionTimeSeconds = ChronoUnit.SECONDS.between(startDateTime, endDateTime);
		return executionTimeSeconds;
	}
}
