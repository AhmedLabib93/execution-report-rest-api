package com.springboot.execution.report.taskexecutionreportrestapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.execution.report.taskexecutionreportrestapi.entity.TaskStepExecutionReport;

public interface TaskStepExecutionReportRepository extends JpaRepository<TaskStepExecutionReport, Long> {

	List<TaskStepExecutionReport> findByTaskExecutionReport(long taskId);
	
}
