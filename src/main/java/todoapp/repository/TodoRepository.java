package todoapp.repository;

import todoapp.dto.TodoRequest;
import todoapp.dto.TodoResponse;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    TodoResponse save(TodoRequest request);

    TodoResponse update(TodoResponse response);

    Optional<TodoResponse> findById(long id);

    List<TodoResponse> findAll();

    void delete(long id);
}
