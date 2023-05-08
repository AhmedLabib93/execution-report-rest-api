package com.execution.report.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;

import com.execution.report.entity.TaskBase;
import com.execution.report.entity.TaskExecutionReport;
import com.execution.report.entity.TaskStepExecutionReport;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskExecutionReportDto {

	private Long id;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private Long executionTimeSeconds;
	private String errorMessage;
	private Long taskId;
	private List<TaskStepExecutionReport> taskStepExecutionReports = new ArrayList<>();

}
