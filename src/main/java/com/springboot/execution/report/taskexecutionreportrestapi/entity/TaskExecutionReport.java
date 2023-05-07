package com.springboot.execution.report.taskexecutionreportrestapi.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.ReadOnlyProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TaskExecutionReport extends TaskBaseEntity {

	@Column(unique = true)
	@NotNull(message = "TaskExecutionReport TaskId can't be null")
	private Long taskId;

	@OneToMany(mappedBy = "taskExecutionReport", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TaskStepExecutionReport> taskStepExecutionReports = new ArrayList<>();

	/**
	 * Return Status depending of list of steps
	 * 
	 * @return String
	 */
	public String getStatus() {
		String status = "SUCCESS";
		for (TaskStepExecutionReport taskStep : taskStepExecutionReports) {
			if (taskStep.getStatus().equals("RUNNING")) {
				return "RUNNING";
			} else if (taskStep.getStatus().equals("FAILAURE")) {
				status = "FAILURE";
			}
		}
		return status;
	}

}
