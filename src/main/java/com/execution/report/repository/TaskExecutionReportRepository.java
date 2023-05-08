package com.execution.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.execution.report.entity.TaskExecutionReport;

public interface TaskExecutionReportRepository extends JpaRepository<TaskExecutionReport, Long> {

}
