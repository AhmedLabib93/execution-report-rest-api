package com.springboot.execution.report.taskexecutionreportrestapi.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;

import com.springboot.execution.report.taskexecutionreportrestapi.entity.TaskBaseEntity;
import com.springboot.execution.report.taskexecutionreportrestapi.entity.TaskExecutionReport;
import com.springboot.execution.report.taskexecutionreportrestapi.entity.TaskStepExecutionReport;

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
