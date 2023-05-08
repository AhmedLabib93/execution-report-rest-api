package com.execution.report.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.execution.report.constant.ExecutionReportConstants;
import com.execution.report.dto.TaskStepExecutionReportDto;
import com.execution.report.service.TaskExecutionReportService;
import com.execution.report.service.TaskStepExecutionReportService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "TaskStepExceutionReport CRUD Rest API")
@RestController
@RequestMapping("execution/report")
public class TaskStepExecutionReportController {

	private TaskStepExecutionReportService taskStepService;

	public TaskStepExecutionReportController(TaskStepExecutionReportService taskStepService) {
		super();
		this.taskStepService = taskStepService;
	}

	@PostMapping("/tasks/{taskId}/steps")
	public ResponseEntity<TaskStepExecutionReportDto> createTaskStepExecutionReport(@PathVariable long taskId,
			@RequestBody TaskStepExecutionReportDto taskStepDto) {
		return new ResponseEntity<TaskStepExecutionReportDto>(
				taskStepService.createTaskStepExecutionReport(taskId, taskStepDto), HttpStatus.CREATED);
	}

	@GetMapping("/tasks/{taskId}/steps")
	public List<TaskStepExecutionReportDto> getAllTaskStepExecutionReports(@PathVariable long taskId,
			@RequestParam(value = "sortBy", defaultValue = ExecutionReportConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = ExecutionReportConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {
		return taskStepService.getTaskStepExecutionReportByTaskId(taskId, sortBy, sortDir);
	}

	@GetMapping("/tasks/{taskId}/steps/{stepId}")
	public ResponseEntity<TaskStepExecutionReportDto> getTaskStepExecutionReportById(@PathVariable long taskId,
			@PathVariable long stepId) {
		return new ResponseEntity<>(taskStepService.getTaskStepExecutionReportById(taskId, stepId), HttpStatus.OK);
	}

	@PutMapping("/tasks/{taskId}/steps/{stepId}")
	public ResponseEntity<TaskStepExecutionReportDto> updateComment(@PathVariable long taskId,
			@PathVariable long stepId, @RequestBody TaskStepExecutionReportDto taskStepDto) {

		TaskStepExecutionReportDto updatedTaskStep = taskStepService.updateTaskStepExecutionReport(taskId, stepId,
				taskStepDto);
		return new ResponseEntity<TaskStepExecutionReportDto>(updatedTaskStep, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/tasks/{taskId}/steps/{stepId}")
	public ResponseEntity<String> deleteTaskStepExecutionReport(@PathVariable long taskId, @PathVariable long stepId) {
		taskStepService.deleteTaskStepExecutionReportById(taskId, stepId);
		return new ResponseEntity<String>("Task Step deleted successfully.", HttpStatus.NO_CONTENT);
	}
}
