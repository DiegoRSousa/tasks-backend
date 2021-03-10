package br.ce.wcaquino.taskbackend.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {

	private Task task;
	@Mock
	private TaskRepo taskRepo;
	@InjectMocks
	private TaskController taskController;
		
	@Before
	public void setup() {
		task = new Task();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		task.setDueDate(LocalDate.now());
		try {
			taskController.save(task);
			Assert.fail("Não deveria chegar nesse ponto");
		} catch (ValidationException e) {
			assertEquals("Fill the task description", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		task.setTask("descrição");
		try {
			taskController.save(task);
			Assert.fail("Não deveria chegar nesse ponto");
		} catch (ValidationException e) {
			assertEquals("Fill the due date", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		task.setTask("descrição");
		task.setDueDate(LocalDate.of(2020, 10, 10));
		try {
			taskController.save(task);
			Assert.fail("Não deveria chegar nesse ponto");
		} catch (ValidationException e) {
			assertEquals("Due date must not be in past", e.getMessage());
		}
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws ValidationException {
		task.setTask("descrição");
		task.setDueDate(LocalDate.now());
		ResponseEntity<Task> response = taskController.save(task);
		assertEquals(201, response.getStatusCodeValue());
		Mockito.verify(taskRepo).save(task);
	}
}