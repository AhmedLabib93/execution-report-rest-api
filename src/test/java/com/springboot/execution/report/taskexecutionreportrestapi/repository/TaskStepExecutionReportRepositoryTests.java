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
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.execution.report.taskexecutionreportrestapi.entity.TaskExecutionReport;
import com.springboot.execution.report.taskexecutionreportrestapi.entity.TaskStepExecutionReport;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@DataJpaTest
public class TaskStepExecutionReportRepositoryTests {

	@Autowired
	private TaskExecutionReportRepository taskRepository;
	
	@Autowired
	private TaskStepExecutionReportRepository taskStepRepository;

	@DisplayName("JUnit test save TaskStepExecutionReport")
	@Test
	public void givenTaskObject_whenSave_thenReturnSavedTask() {
		TaskExecutionReport task = new TaskExecutionReport();
		task.setTaskId(1L);
		TaskStepExecutionReport taskStep = new TaskStepExecutionReport();
		taskStep.setTaskExecutionId(1L);
		
		task.getTaskStepExecutionReports().add(taskStep);
		taskStep.setTaskExecutionReport(task);
		
		taskRepository.save(task);
		TaskStepExecutionReport savedTask = taskStepRepository.save(taskStep);

		assertThat(savedTask).isNotNull();
		assertThat(savedTask.getId()).isEqualTo(1L);
		//
		TaskStepExecutionReport taskStep2 = new TaskStepExecutionReport();
		taskStep2.setTaskExecutionId(2L);
		task.getTaskStepExecutionReports().add(taskStep2);
		taskStep2.setTaskExecutionReport(task);
		
		TaskStepExecutionReport savedTask2 = taskStepRepository.save(taskStep2);

		assertThat(savedTask2).isNotNull();
		assertThat(savedTask2.getId()).isEqualTo(2L);
	}

	@DisplayName("JUnit test findByTaskExecutionReport TaskStepExecutionReport")
	@Test
	public void givenTaskList_whenFindAll_thenTaskList() {
		TaskExecutionReport task = new TaskExecutionReport();
		task.setTaskId(1L);
		
		TaskStepExecutionReport taskStep1 = new TaskStepExecutionReport();
		taskStep1.setTaskExecutionId(1L);
		TaskStepExecutionReport taskStep2 = new TaskStepExecutionReport();
		taskStep2.setTaskExecutionId(2L);

		task.getTaskStepExecutionReports().add(taskStep1);
		task.getTaskStepExecutionReports().add(taskStep2);
		taskStep1.setTaskExecutionReport(task);
		taskStep2.setTaskExecutionReport(task);
		
		taskRepository.save(task);
		taskStepRepository.save(taskStep1);
		taskStepRepository.save(taskStep2);

		List<TaskStepExecutionReport> tasks = taskStepRepository.findByTaskExecutionReportId(1L);

		assertThat(tasks).isNotNull();
		assertThat(tasks.size()).isEqualTo(2);
	}

	@DisplayName("JUnit test findById TaskStepExecutionReport")
	@Test
	public void givenTaskObject_whenFindById_thenSavedTask() {
		TaskExecutionReport task = new TaskExecutionReport();
		task.setTaskId(1L);
		TaskStepExecutionReport taskStep = new TaskStepExecutionReport();
		taskStep.setTaskExecutionId(15L);
		taskStep.setStepName("Step1");

		task.getTaskStepExecutionReports().add(taskStep);
		taskStep.setTaskExecutionReport(task);
		
		taskRepository.save(task);
		taskStepRepository.save(taskStep);

		TaskStepExecutionReport savedTaskStep = taskStepRepository.findById(1L).get();

		assertThat(savedTaskStep).isNotNull();
		assertThat(savedTaskStep.getTaskExecutionId()).isEqualTo(15L);
		assertThat(savedTaskStep.getStepName()).isEqualTo("Step1");
	}

	@DisplayName("JUnit test update TaskStepExecutionReport")
	@Test
	public void givenTaskObject_whenUpdate_thenUpdatedTask() {
		TaskExecutionReport task = new TaskExecutionReport();
		task.setTaskId(1L);
		TaskStepExecutionReport taskStep = new TaskStepExecutionReport();
		taskStep.setTaskExecutionId(15L);
		taskStep.setStepName("Step1");

		task.getTaskStepExecutionReports().add(taskStep);
		taskStep.setTaskExecutionReport(task);
		
		taskRepository.save(task);
		TaskStepExecutionReport savedTaskStep1 = taskStepRepository.save(taskStep);
		System.out.println(savedTaskStep1.getId());

		TaskStepExecutionReport savedTaskStep = taskStepRepository.findById(1L).get();
		assertThat(savedTaskStep).isNotNull();
		assertThat(savedTaskStep.getTaskExecutionId()).isEqualTo(15L);
		assertThat(savedTaskStep.getStepName()).isEqualTo("Step1");

		taskStep.setTaskExecutionId(10L);
		taskStep.setStepName("Step Max");
		taskStepRepository.save(taskStep);
		savedTaskStep = taskStepRepository.findById(1L).get();
		assertThat(savedTaskStep).isNotNull();
		assertThat(savedTaskStep.getTaskExecutionId()).isEqualTo(10L);
		assertThat(savedTaskStep.getStepName()).isEqualTo("Step Max");
	}

	@DisplayName("JUnit test delete TaskStepExecutionReport")
	@Test
	public void givenTaskObject_whenDelete_thenNoTask() {
		TaskExecutionReport task = new TaskExecutionReport();
		task.setTaskId(1L);
		TaskStepExecutionReport taskStep = new TaskStepExecutionReport();
		taskStep.setTaskExecutionId(15L);
		
		task.getTaskStepExecutionReports().add(taskStep);
		taskStep.setTaskExecutionReport(task);
		
		taskRepository.save(task);		
		TaskStepExecutionReport savedTask = taskStepRepository.findById(1L).get();
		assertThat(savedTask).isNotNull();

		taskStepRepository.delete(savedTask);

		Optional<TaskStepExecutionReport> deletedTask = taskStepRepository.findById(1L);
		assertThat(deletedTask).isEmpty();
	}
}
