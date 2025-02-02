package todoapp.repository;

import todoapp.dto.TodoRequest;
import todoapp.dto.TodoResponse;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryTodoRepository implements TodoRepository {
    static long count = 0L;
    private final Map<Long, TodoResponse> store = new ConcurrentHashMap<>();

    @Override
    public TodoResponse save(TodoRequest request) {
        TodoResponse response = new TodoResponse(count, request.getTitle(), request.getContent());
        store.put(count++, response);
        return response;
    }

    @Override
    public TodoResponse update(TodoResponse response) {
        store.put(response.getId(), response);
        return response;
    }

    @Override
    public Optional<TodoResponse> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<TodoResponse> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(long id) {
        store.remove(id);
    }
}
