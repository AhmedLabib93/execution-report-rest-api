package com.springboot.execution.report.taskexecutionreportrestapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.springboot.execution.report.taskexecutionreportrestapi.dto.TaskStepExecutionReportDto;
import com.springboot.execution.report.taskexecutionreportrestapi.service.TaskExecutionReportService;
import com.springboot.execution.report.taskexecutionreportrestapi.service.TaskStepExecutionReportService;
import com.springboot.execution.report.taskexecutionreportrestapi.utils.ExecutionReportConstants;

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

	@PutMapping("/tasks/{taskId}/steps/{stepIdsteps}")
	public ResponseEntity<TaskStepExecutionReportDto> updateComment(@PathVariable long taskId,
			@PathVariable long stepId, @RequestBody TaskStepExecutionReportDto taskStepDto) {

		TaskStepExecutionReportDto updatedTaskStep = taskStepService.updateTaskStepExecutionReport(taskId, stepId,
				taskStepDto);
		return ResponseEntity.ok(updatedTaskStep);
	}

	@DeleteMapping("/tasks/{taskId}/steps/{stepId}")
	public ResponseEntity<String> deleteTaskStepExecutionReport(@PathVariable long taskId, @PathVariable long stepId) {
		taskStepService.deleteTaskStepExecutionReportById(taskId, stepId);
		return ResponseEntity.ok("Task Step deleted successfully.");
	}
}
