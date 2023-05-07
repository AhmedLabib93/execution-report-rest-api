package com.springboot.execution.report.taskexecutionreportrestapi.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.springboot.execution.report.taskexecutionreportrestapi.entity.TaskBaseEntity;
import com.springboot.execution.report.taskexecutionreportrestapi.entity.TaskExecutionReport;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class TaskStepExecutionReportDto extends TaskBaseEntity {

	private long taskExecutionId;
	private String stepName;
	private String status;
}
