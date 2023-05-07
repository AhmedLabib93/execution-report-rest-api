package com.springboot.execution.report.taskexecutionreportrestapi.service;

import java.util.List;

import com.springboot.execution.report.taskexecutionreportrestapi.dto.TaskStepExecutionReportDto;
import com.springboot.execution.report.taskexecutionreportrestapi.entity.TaskStepExecutionReport;

public interface TaskStepExecutionReportService {

	TaskStepExecutionReportDto createTaskStepExecutionReport(long taskId, TaskStepExecutionReportDto taskStepDto);

	TaskStepExecutionReportDto getTaskStepExecutionReportById(long taskId, long taskStepId);

	List<TaskStepExecutionReportDto> getTaskStepExecutionReportByTaskId(long taskId, String sortBy, String sortDir);

	TaskStepExecutionReportDto updateTaskStepExecutionReport(long taskId, long taskStepId,
			TaskStepExecutionReportDto taskStepDto);

	void deleteTaskStepExecutionReportById(long taskId, long taskStepId);
}
