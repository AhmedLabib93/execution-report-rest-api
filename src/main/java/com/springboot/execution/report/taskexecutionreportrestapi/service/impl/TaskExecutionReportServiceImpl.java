package com.springboot.execution.report.taskexecutionreportrestapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.execution.report.taskexecutionreportrestapi.dto.TaskExecutionReportDto;
import com.springboot.execution.report.taskexecutionreportrestapi.entity.TaskExecutionReport;
import com.springboot.execution.report.taskexecutionreportrestapi.exception.ResourceNotFoundException;
import com.springboot.execution.report.taskexecutionreportrestapi.repository.TaskExecutionReportRepository;
import com.springboot.execution.report.taskexecutionreportrestapi.service.TaskExecutionReportService;

@Service
public class TaskExecutionReportServiceImpl implements TaskExecutionReportService {

	private TaskExecutionReportRepository taskRepository;
	private ModelMapper modelMapper;

	public TaskExecutionReportServiceImpl(TaskExecutionReportRepository taskRepository, ModelMapper modelMapper) {
		super();
		this.taskRepository = taskRepository;
		this.modelMapper = modelMapper;
	}

	private TaskExecutionReportDto mapToDto(TaskExecutionReport task) {
		TaskExecutionReportDto taskDto = modelMapper.map(task, TaskExecutionReportDto.class);
		return taskDto;
	}

	private TaskExecutionReport mapToEntity(TaskExecutionReportDto taskDto) {
		TaskExecutionReport task = modelMapper.map(taskDto, TaskExecutionReport.class);
		return task;
	}

	@Override
	public TaskExecutionReportDto createTaskExecutionReport(TaskExecutionReportDto taskDto) {
		TaskExecutionReport task = mapToEntity(taskDto);
		TaskExecutionReport newTask = taskRepository.save(task);
		TaskExecutionReportDto response = mapToDto(newTask);

		return response;
	}

	@Override
	public List<TaskExecutionReportDto> getAllTaskExecutionReports(String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		List<TaskExecutionReport> tasks = taskRepository.findAll(sort);
		return tasks.stream().map(task -> mapToDto(task)).collect(Collectors.toList());
	}

	@Override
	public List<TaskExecutionReportDto> getAllTaskExecutionReportsByStatus(String status) {
		List<TaskExecutionReport> tasks = taskRepository.findAll();
		List<TaskExecutionReport> result = new ArrayList<>();
		for (TaskExecutionReport task : tasks) {
			if (task.getStatus().equals(status))
				result.add(task);
		}
		return result.stream().map(task -> mapToDto(task)).collect(Collectors.toList());
	}

	@Override
	public TaskExecutionReportDto getTaskExecutionReportById(Long taskId) {
		TaskExecutionReport task = taskRepository.findById(taskId).orElseThrow(() -> new  ResourceNotFoundException("TaskExecutionReport", "Id", taskId));
		return mapToDto(task);
	}

	@Override
	public TaskExecutionReportDto updateTaskExecutionReport(TaskExecutionReportDto taskDto, Long taskId) {
		TaskExecutionReport task = taskRepository.findById(taskId).orElseThrow(() -> new  ResourceNotFoundException("TaskExecutionReport", "Id", taskId));
		task.setTaskId(taskDto.getTaskId());
		task.setErrorMessage(taskDto.getErrorMessage());
		task.setEndDateTime(taskDto.getEndDateTime());
		taskRepository.save(task);
		return mapToDto(task);

	}

	@Override
	public void deleteTaskExecutionReportById(Long taskId) {
		taskRepository.deleteById(taskId);
	}
}
