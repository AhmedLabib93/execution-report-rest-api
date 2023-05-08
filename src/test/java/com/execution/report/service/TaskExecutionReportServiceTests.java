package com.execution.report.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import com.execution.report.constant.ExecutionReportConstants;
import com.execution.report.dto.TaskExecutionReportDto;
import com.execution.report.entity.TaskExecutionReport;
import com.execution.report.entity.TaskStepExecutionReport;
import com.execution.report.repository.TaskExecutionReportRepository;
import com.execution.report.service.TaskExecutionReportService;
import com.execution.report.service.impl.TaskExecutionReportServiceImpl;

public class TaskExecutionReportServiceTests {

	private TaskExecutionReportRepository taskRepository;

	private TaskExecutionReportService taskService;

	private ModelMapper modelMapper;

	@BeforeEach
	public void setUp() {
		modelMapper = new ModelMapper();
		taskRepository = Mockito.mock(TaskExecutionReportRepository.class);
		taskService = new TaskExecutionReportServiceImpl(taskRepository, modelMapper);
	}

	@DisplayName("JUnit test save TaskExecutionReportService")
	@Test
	public void givenTaskObject_whenSave_thenReturnSavedTask() {
		TaskExecutionReport task = new TaskExecutionReport();
		task.setId(1L);
		task.setTaskId(3L);
		BDDMockito.given(taskRepository.findById(1L)).willReturn(Optional.empty());
		BDDMockito.given(taskRepository.save(task)).willReturn(task);

		TaskExecutionReportDto savedTaskDto = taskService
				.createTaskExecutionReport(modelMapper.map(task, TaskExecutionReportDto.class));

		assertThat(savedTaskDto).isNotNull();
		assertThat(savedTaskDto.getTaskId()).isEqualTo(3L);
	}

	@DisplayName("JUnit test update TaskExecutionReportService")
	@Test
	public void givenTaskObject_whenUpdate_thenReturnUpdatedTask() {
		TaskExecutionReport task = new TaskExecutionReport();
		task.setTaskId(3L);
		task.setErrorMessage("Error Msg");
		BDDMockito.given(taskRepository.save(task)).willReturn(task);

		task.setTaskId(2L);
		task.setErrorMessage("No Error");

		TaskExecutionReportDto updatedTaskDto = taskService
				.createTaskExecutionReport(modelMapper.map(task, TaskExecutionReportDto.class));

		assertThat(updatedTaskDto).isNotNull();
		assertThat(updatedTaskDto.getTaskId()).isEqualTo(2L);
		assertThat(updatedTaskDto.getErrorMessage()).isEqualTo("No Error");
	}

	@DisplayName("JUnit test getAll TaskExecutionReportService")
	@Test
	public void givenTaskList_whenGetAll_thenReturnTaskList() {
		TaskExecutionReport task1 = new TaskExecutionReport();
		task1.setId(1L);
		task1.setTaskId(3L);
		task1.setExecutionTimeSeconds(100L);
		TaskExecutionReport task2 = new TaskExecutionReport();
		task2.setId(2L);
		task2.setTaskId(2L);
		task2.setExecutionTimeSeconds(50L);
		TaskExecutionReport task3 = new TaskExecutionReport();
		task3.setId(3L);
		task3.setTaskId(1L);
		task3.setExecutionTimeSeconds(150L);

		// default get all sorted by id
		Sort sort = Sort.by(ExecutionReportConstants.DEFAULT_SORT_BY).ascending();
		BDDMockito.given(taskRepository.findAll(sort)).willReturn(List.of(task1, task2, task3));
		List<TaskExecutionReportDto> tasks = taskService.getAllTaskExecutionReports(
				ExecutionReportConstants.DEFAULT_SORT_BY, ExecutionReportConstants.DEFAULT_SORT_DIR);

		assertThat(tasks).isNotNull();
		assertThat(tasks.size()).isEqualTo(3);
		assertThat(tasks.get(0).getExecutionTimeSeconds()).isEqualTo(100L);
		assertThat(tasks.get(1).getExecutionTimeSeconds()).isEqualTo(50L);
		assertThat(tasks.get(2).getExecutionTimeSeconds()).isEqualTo(150L);
	}

	@DisplayName("JUnit test getAllSortByExecutionTime TaskExecutionReportService")
	@Test
	public void givenTaskList_whenGetAllSortByExecutionTime_thenReturnSortedTaskList() {
		TaskExecutionReport task1 = new TaskExecutionReport();
		task1.setId(1L);
		task1.setTaskId(3L);
		task1.setExecutionTimeSeconds(100L);
		TaskExecutionReport task2 = new TaskExecutionReport();
		task2.setId(2L);
		task2.setTaskId(2L);
		task2.setExecutionTimeSeconds(150L);
		TaskExecutionReport task3 = new TaskExecutionReport();
		task3.setId(3L);
		task3.setTaskId(1L);
		task3.setExecutionTimeSeconds(50L);

		// default get all sorted by id
		Sort sort = Sort.by("executionTimeSeconds").ascending();
		BDDMockito.given(taskRepository.findAll(sort)).willReturn(List.of(task3, task1, task2));
		List<TaskExecutionReportDto> tasks = taskService.getAllTaskExecutionReports("executionTimeSeconds",
				ExecutionReportConstants.DEFAULT_SORT_DIR);

		assertThat(tasks).isNotNull();
		assertThat(tasks.size()).isEqualTo(3);
		assertThat(tasks.get(0).getExecutionTimeSeconds()).isEqualTo(50L);
		assertThat(tasks.get(1).getExecutionTimeSeconds()).isEqualTo(100L);
		assertThat(tasks.get(2).getExecutionTimeSeconds()).isEqualTo(150L);
	}

	@DisplayName("JUnit test getById TaskExecutionReportService")
	@Test
	public void givenTaskObject_whenGetById_thenReturnTask() {
		TaskExecutionReport task = new TaskExecutionReport();
		task.setId(1L);
		task.setTaskId(3L);
		task.setErrorMessage("Error Msg");
		BDDMockito.given(taskRepository.findById(1L)).willReturn(Optional.of(task));
		TaskExecutionReportDto taskDto = taskService.getTaskExecutionReportById(1L);
		assertThat(taskDto).isNotNull();
		assertThat(taskDto.getTaskId()).isEqualTo(task.getTaskId());
		assertThat(taskDto.getErrorMessage()).isEqualTo(task.getErrorMessage());
	}

	@DisplayName("JUnit test deleteById TaskExecutionReportService")
	@Test
	public void givenTaskObject_whenDeleteById_thenNothing() {
		Long taskId = 1L;

		BDDMockito.willDoNothing().given(taskRepository).deleteById(taskId);

		taskService.deleteTaskExecutionReportById(taskId);

		verify(taskRepository, times(1)).deleteById(taskId);
	}

	@DisplayName("JUnit test getAllStatus TaskExecutionReportService")

	@Test
	public void givenTaskList_whenGetAllStatus_thenReturnTaskList() {
		TaskExecutionReport task1 = new TaskExecutionReport();
		task1.setId(1L);
		task1.setTaskId(1L);
		TaskExecutionReport task2 = new TaskExecutionReport();
		task2.setId(2L);
		task2.setTaskId(2L);
		TaskExecutionReport task3 = new TaskExecutionReport();
		task3.setId(3L);
		task3.setTaskId(3L);
		
		TaskStepExecutionReport taskStep1 = new TaskStepExecutionReport();
		taskStep1.setId(1L);
		taskStep1.setTaskExecutionId(1L);
		taskStep1.setStatus("SUCCESS");
		TaskStepExecutionReport taskStep2 = new TaskStepExecutionReport();
		taskStep2.setId(1L);
		taskStep2.setTaskExecutionId(1L);
		taskStep2.setStatus("RUNNING");
		TaskStepExecutionReport taskStep3 = new TaskStepExecutionReport();
		taskStep3.setId(1L);
		taskStep3.setTaskExecutionId(1L);
		taskStep3.setStatus("SUCCESS");
		TaskStepExecutionReport taskStep4 = new TaskStepExecutionReport();
		taskStep4.setId(1L);
		taskStep4.setTaskExecutionId(1L);
		taskStep4.setStatus("FAILURE");
		
		task1.getTaskStepExecutionReports().add(taskStep1);
		task1.getTaskStepExecutionReports().add(taskStep3);
		task2.getTaskStepExecutionReports().add(taskStep1);
		task2.getTaskStepExecutionReports().add(taskStep2);
		task2.getTaskStepExecutionReports().add(taskStep3);
		task3.getTaskStepExecutionReports().add(taskStep1);
		task3.getTaskStepExecutionReports().add(taskStep3);
		task3.getTaskStepExecutionReports().add(taskStep4);

		BDDMockito.given(taskRepository.findAll()).willReturn(List.of(task1));
		List<TaskExecutionReportDto> tasks1 = taskService.getAllTaskExecutionReportsByStatus("SUCCESS");
		assertThat(tasks1).isNotNull();
		assertThat(tasks1.get(0).getId()).isEqualTo(1L);
		
		BDDMockito.given(taskRepository.findAll()).willReturn(List.of(task2));
		List<TaskExecutionReportDto> tasks2 = taskService.getAllTaskExecutionReportsByStatus("RUNNING");
		assertThat(tasks2).isNotNull();
		assertThat(tasks2.get(0).getId()).isEqualTo(2L);
		
		BDDMockito.given(taskRepository.findAll()).willReturn(List.of(task3));
		List<TaskExecutionReportDto> tasks3 = taskService.getAllTaskExecutionReportsByStatus("FAILURE");
		assertThat(tasks3).isNotNull();
		assertThat(tasks3.get(0).getId()).isEqualTo(3L);
	}

}
