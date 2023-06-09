package com.execution.report.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.execution.report.dto.TaskStepExecutionReportDto;
import com.execution.report.entity.TaskExecutionReport;
import com.execution.report.entity.TaskStepExecutionReport;
import com.execution.report.exception.ExecutionReportException;
import com.execution.report.exception.ResourceNotFoundException;
import com.execution.report.repository.TaskExecutionReportRepository;
import com.execution.report.repository.TaskStepExecutionReportRepository;
import com.execution.report.service.TaskStepExecutionReportService;

@Service
public class TaskStepExecutionReportServiceImpl implements TaskStepExecutionReportService {

	private TaskStepExecutionReportRepository taskStepRepository;
	private TaskExecutionReportRepository taskRepository;
	private ModelMapper modelMapper;

	public TaskStepExecutionReportServiceImpl(TaskStepExecutionReportRepository taskStepRepository,
			TaskExecutionReportRepository taskRepository, ModelMapper modelMapper) {
		super();
		this.taskStepRepository = taskStepRepository;
		this.taskRepository = taskRepository;
		this.modelMapper = modelMapper;
	}

	private TaskStepExecutionReportDto mapToDto(TaskStepExecutionReport taskStep) {
		return modelMapper.map(taskStep, TaskStepExecutionReportDto.class);
	}

	private TaskStepExecutionReport mapToEntity(TaskStepExecutionReportDto taskStepDto) {
		return modelMapper.map(taskStepDto, TaskStepExecutionReport.class);
	}

	@Override
	public TaskStepExecutionReportDto createTaskStepExecutionReport(Long taskId,
			TaskStepExecutionReportDto taskStepDto) {
		TaskStepExecutionReport taskStep = mapToEntity(taskStepDto);
		TaskExecutionReport task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("TaskExecutionReport", "Id", taskId));
		taskStep.setTaskExecutionReport(task);
		TaskStepExecutionReport newTaskReport = taskStepRepository.save(taskStep);
		return mapToDto(newTaskReport);
	}

	@Override
	public List<TaskStepExecutionReportDto> getTaskStepExecutionReportByTaskId(Long taskId, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		List<TaskStepExecutionReport> taskSteps = taskStepRepository.findByTaskExecutionReportId(taskId);
		return taskSteps.stream().map(taskStep -> mapToDto(taskStep)).collect(Collectors.toList());
	}
	

	@Override
	public TaskStepExecutionReportDto getTaskStepExecutionReportById(Long taskId, Long taskStepId) {
		TaskExecutionReport task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("TaskExecutionReport", "Id", taskId));
		TaskStepExecutionReport taskStep = taskStepRepository.findById(taskStepId)
				.orElseThrow(() -> new ResourceNotFoundException("TaskStepExecutionReport", "Id", taskStepId));
		if (task.getId() != taskStep.getTaskExecutionReport().getId()) {
			throw new ExecutionReportException(HttpStatus.BAD_REQUEST, "Step does not belong to Task");
		}
		return mapToDto(taskStep);
	}

	@Override
	public TaskStepExecutionReportDto updateTaskStepExecutionReport(Long taskId, Long taskStepId,
			TaskStepExecutionReportDto taskStepDto) {
		TaskExecutionReport task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("TaskExecutionReport", "Id", taskId));
		TaskStepExecutionReport taskStep = taskStepRepository.findById(taskStepId)
				.orElseThrow(() -> new ResourceNotFoundException("TaskStepExecutionReport", "Id", taskStepId));
		if (task.getId() != taskStep.getTaskExecutionReport().getId()) {
			throw new ExecutionReportException(HttpStatus.BAD_REQUEST, "Step does not belong to Task");
		}
		
		taskStep.setStepName(taskStepDto.getStepName());
		taskStep.setTaskExecutionId(taskStepDto.getTaskExecutionId());
		taskStep.setEndDateTime(taskStepDto.getEndDateTime());
		taskStep.setErrorMessage(taskStepDto.getErrorMessage());
		taskStep.setStatus(taskStepDto.getStatus());
		taskStepRepository.save(taskStep);
		return mapToDto(taskStep);
	}

	@Override
	public void deleteTaskStepExecutionReportById(Long taskId, Long taskStepId) {
		TaskExecutionReport task = taskRepository.findById(taskId)
				.orElseThrow(() -> new ResourceNotFoundException("TaskExecutionReport", "Id", taskId));
		TaskStepExecutionReport taskStep = taskStepRepository.findById(taskStepId)
				.orElseThrow(() -> new ResourceNotFoundException("TaskStepExecutionReport", "Id", taskStepId));
		if (task.getId() != taskStep.getTaskExecutionReport().getId()) {
			throw new ExecutionReportException(HttpStatus.BAD_REQUEST, "Step does not belong to Task");
		}
		taskStepRepository.deleteById(taskStepId);
	}
}
