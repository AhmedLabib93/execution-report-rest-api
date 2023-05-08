package com.execution.report.service;

import java.util.List;

import com.execution.report.dto.TaskStepExecutionReportDto;
import com.execution.report.entity.TaskStepExecutionReport;

public interface TaskStepExecutionReportService {

	TaskStepExecutionReportDto createTaskStepExecutionReport(Long taskId, TaskStepExecutionReportDto taskStepDto);

	TaskStepExecutionReportDto getTaskStepExecutionReportById(Long taskId, Long taskStepId);

	List<TaskStepExecutionReportDto> getTaskStepExecutionReportByTaskId(Long taskId, String sortBy, String sortDir);

	TaskStepExecutionReportDto updateTaskStepExecutionReport(Long taskId, Long taskStepId,
			TaskStepExecutionReportDto taskStepDto);

	void deleteTaskStepExecutionReportById(Long taskId, Long taskStepId);
}
