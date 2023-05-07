package com.springboot.execution.report.taskexecutionreportrestapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.springboot.execution.report.taskexecutionreportrestapi.entity.TaskExecutionReport;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@DataJpaTest
public class TaskExecutionReportRepositoryTests {

	@Autowired
	private TaskExecutionReportRepository taskRepository;
	
	@DisplayName("JUnit test save TaskExecutionReport")
	@Test
	public void givenTaskObject_whenSave_thenReturnSavedTask() {
		TaskExecutionReport task = new TaskExecutionReport();
		task.setTaskId(1L);
		
		TaskExecutionReport savedTask = taskRepository.save(task);
		
		assertThat(savedTask).isNotNull();
		assertThat(savedTask.getId()).isEqualTo(1L);
		//
		TaskExecutionReport task2 = new TaskExecutionReport();
		task2.setTaskId(2L);
		
		TaskExecutionReport savedTask2 = taskRepository.save(task2);
		
		assertThat(savedTask2).isNotNull();
		assertThat(savedTask2.getId()).isEqualTo(2L);
	}
	
	@DisplayName("JUnit test findAll TaskExecutionReport")
	@Test
	public void givenTaskList_whenFindAll_thenTaskList() {
		TaskExecutionReport task1 = new TaskExecutionReport();
		task1.setTaskId(1L);
		TaskExecutionReport task2 = new TaskExecutionReport();
		task2.setTaskId(2L);
		
		taskRepository.save(task1);
		taskRepository.save(task2);
		
		List<TaskExecutionReport> tasks = taskRepository.findAll();
		
		assertThat(tasks).isNotNull();
		assertThat(tasks.size()).isEqualTo(2);
	}
	
	@DisplayName("JUnit test findById TaskExecutionReport")
	@Test
	public void givenTaskObject_whenFindById_thenSavedTask() {
		TaskExecutionReport task1 = new TaskExecutionReport();
		task1.setTaskId(15L);
		task1.setErrorMessage("Error Msg");
		
		taskRepository.save(task1);
		
		TaskExecutionReport savedTask = taskRepository.findById(1L).get();
		
		assertThat(savedTask).isNotNull();
		assertThat(savedTask.getTaskId()).isEqualTo(15L);
		assertThat(savedTask.getErrorMessage()).isEqualTo("Error Msg");
	}
	
	@DisplayName("JUnit test update TaskExecutionReport")
	@Test
	public void givenTaskObject_whenUpdate_thenUpdatedTask() {
		TaskExecutionReport task1 = new TaskExecutionReport();
		task1.setTaskId(15L);
		task1.setErrorMessage("Error Msg");
		taskRepository.save(task1);
		TaskExecutionReport savedTask = taskRepository.findById(1L).get();
		assertThat(savedTask).isNotNull();
		assertThat(savedTask.getTaskId()).isEqualTo(15L);
		assertThat(savedTask.getErrorMessage()).isEqualTo("Error Msg");
		
		task1.setTaskId(10L);
		task1.setErrorMessage("No Msg");
		taskRepository.save(task1);
		savedTask = taskRepository.findById(1L).get();
		assertThat(savedTask).isNotNull();
		assertThat(savedTask.getTaskId()).isEqualTo(10L);
		assertThat(savedTask.getErrorMessage()).isEqualTo("No Msg");
	}
	
	@DisplayName("JUnit test delete TaskExecutionReport")
	@Test
	public void givenTaskObject_whenDelete_thenNoTask() {
		TaskExecutionReport task1 = new TaskExecutionReport();
		task1.setTaskId(1L);
		taskRepository.save(task1);
		Optional<TaskExecutionReport> savedTask = taskRepository.findById(1L);
		
		assertThat(savedTask).isNotEmpty();
		
		taskRepository.delete(task1);

		Optional<TaskExecutionReport> deletedTask = taskRepository.findById(1L);
		assertThat(deletedTask).isEmpty();
	}
}
