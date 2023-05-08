package com.execution.report.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.execution.report.dto.TaskExecutionReportDto;

public interface TaskExecutionReportService {

	public TaskExecutionReportDto createTaskExecutionReport(TaskExecutionReportDto taskDto);

	public List<TaskExecutionReportDto> getAllTaskExecutionReports(String sortBy, String sortDir);
	
	public List<TaskExecutionReportDto> getAllTaskExecutionReportsByStatus(String status);

	public TaskExecutionReportDto getTaskExecutionReportById(Long id);

	public TaskExecutionReportDto updateTaskExecutionReport(TaskExecutionReportDto taskDto, Long id);

	public void deleteTaskExecutionReportById(Long id);
}