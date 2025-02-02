package todoapp.service;

import todoapp.dto.TodoRequest;
import todoapp.dto.TodoResponse;
import todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository repository;

    public TodoResponse createTodo(TodoRequest request) {
        return repository.save(request);
    }

    public TodoResponse findTodo(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 id입니다! 조회 id : " + id));
    }

    public List<TodoResponse> getAllTodos() {
        return repository.findAll();
    }

    public TodoResponse editTodo(Long id, TodoRequest request) {
        TodoResponse response = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 id입니다! 조회 id : " + id));

        response.update(request.getTitle(), request.getContent());
        return repository.update(response);
    }

    public void deleteTodo(long id) {
        repository.delete(id);
    }

}
