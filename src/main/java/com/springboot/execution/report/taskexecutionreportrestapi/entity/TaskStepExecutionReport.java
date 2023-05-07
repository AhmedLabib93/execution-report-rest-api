package com.springboot.execution.report.taskexecutionreportrestapi.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class TaskStepExecutionReport extends TaskBaseEntity {

	@Column(unique = true)
	@NotNull(message = "TaskStepExecutionReport TaskId can't be null")
	private long taskExecutionId;

	private String stepName;

	private String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_execution_report_id", referencedColumnName = "id", nullable = false)
	private TaskExecutionReport taskExecutionReport;

}
