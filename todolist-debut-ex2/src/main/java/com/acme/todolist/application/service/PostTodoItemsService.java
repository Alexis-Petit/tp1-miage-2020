package com.acme.todolist.application.service;

import java.util.List;

import javax.inject.Inject;

import com.acme.todolist.application.port.out.UpdateTodoItem;
import com.acme.todolist.domain.TodoItem;

public class PostTodoItemsService {
	private UpdateTodoItem updateTodoItem;
	
	@Inject
	public PostTodoItemsService(UpdateTodoItem updateTodoItem) {
		this.updateTodoItem = updateTodoItem;
	}
	
	public void createTodoItem(TodoItem todoItem) {
		this.updateTodoItem.storeNewTodoItem(todoItem);;
	}
}


