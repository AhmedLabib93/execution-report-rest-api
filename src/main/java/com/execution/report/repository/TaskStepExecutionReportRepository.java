package com.execution.report.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.execution.report.entity.TaskStepExecutionReport;

public interface TaskStepExecutionReportRepository extends JpaRepository<TaskStepExecutionReport, Long> {

	List<TaskStepExecutionReport> findByTaskExecutionReportId(Long taskId);
	
}
