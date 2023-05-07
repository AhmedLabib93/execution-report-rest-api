package com.springboot.execution.report.taskexecutionreportrestapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.springboot.execution.report.taskexecutionreportrestapi.dto.TaskExecutionReportDto;

public interface TaskExecutionReportService {

	public TaskExecutionReportDto createTaskExecutionReport(TaskExecutionReportDto taskDto);

	public List<TaskExecutionReportDto> getAllTaskExecutionReports(String sortBy, String sortDir);
	
	public List<TaskExecutionReportDto> getAllTaskExecutionReportsByStatus(String status);

	public TaskExecutionReportDto getTaskExecutionReportById(long id);

	public TaskExecutionReportDto updateTaskExecutionReport(TaskExecutionReportDto taskDto, long id);

	public void deleteTaskExecutionReportById(long id);
}