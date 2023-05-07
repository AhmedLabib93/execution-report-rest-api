package com.springboot.execution.report.taskexecutionreportrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.execution.report.taskexecutionreportrestapi.entity.TaskExecutionReport;

public interface TaskExecutionReportRepository extends JpaRepository<TaskExecutionReport, Long> {

}
