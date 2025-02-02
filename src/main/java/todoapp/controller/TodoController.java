package todoapp.controller;

import todoapp.dto.TodoRequest;
import todoapp.dto.TodoResponse;
import todoapp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RequestMapping("/api/todos")
@RestController
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public TodoResponse createTodo(@RequestBody TodoRequest request) {
        return todoService.createTodo(request);
    }

    @GetMapping("/{id}")
    public TodoResponse getTodo(@PathVariable Long id) {
        return todoService.findTodo(id);
    }

    @GetMapping
    public List<TodoResponse> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PutMapping("/{id}")
    public TodoResponse editTodo(@PathVariable Long id, @RequestBody TodoRequest request) {
        return todoService.editTodo(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
