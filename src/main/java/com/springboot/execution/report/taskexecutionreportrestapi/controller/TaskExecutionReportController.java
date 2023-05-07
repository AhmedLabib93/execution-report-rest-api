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

import com.springboot.execution.report.taskexecutionreportrestapi.dto.TaskExecutionReportDto;
import com.springboot.execution.report.taskexecutionreportrestapi.service.TaskExecutionReportService;
import com.springboot.execution.report.taskexecutionreportrestapi.utils.ExecutionReportConstants;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "TaskExceutionReport CRUD Rest API")
@RestController
@RequestMapping("execution/report/tasks")
public class TaskExecutionReportController {

	private TaskExecutionReportService taskService;

	public TaskExecutionReportController(TaskExecutionReportService taskService) {
		super();
		this.taskService = taskService;
	}

	@PostMapping
	public ResponseEntity<TaskExecutionReportDto> createTaskExecutionReport(
			@RequestBody TaskExecutionReportDto taskDto) {
		return new ResponseEntity<>(taskService.createTaskExecutionReport(taskDto), HttpStatus.CREATED);
	}

	@GetMapping
	public List<TaskExecutionReportDto> getAllTaskExecutionReports(
			@RequestParam(value = "sortBy", defaultValue = ExecutionReportConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = ExecutionReportConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {
		return taskService.getAllTaskExecutionReports(sortBy, sortDir);
	}

	@GetMapping("/status")
	public List<TaskExecutionReportDto> getAllTaskExecutionReportsByStatus(
			@RequestParam(value = "status", required = true) String status) {
		return taskService.getAllTaskExecutionReportsByStatus(status.toUpperCase());
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<TaskExecutionReportDto> getTaskExecutionReportById(@PathVariable long taskId) {
		return ResponseEntity.ok(taskService.getTaskExecutionReportById(taskId));
	}

	@PutMapping("/{taskId}")
	public ResponseEntity<TaskExecutionReportDto> updatePost(@RequestBody TaskExecutionReportDto taskDto,
			@PathVariable long taskId) {
		return new ResponseEntity<TaskExecutionReportDto>(taskService.updateTaskExecutionReport(taskDto, taskId),
				HttpStatus.OK);
	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<String> deleteTaskExecutionReportById(@PathVariable long taskId) {
		taskService.deleteTaskExecutionReportById(taskId);
		return new ResponseEntity<String>("Task entity deleted successfully", HttpStatus.OK);
	}
}
