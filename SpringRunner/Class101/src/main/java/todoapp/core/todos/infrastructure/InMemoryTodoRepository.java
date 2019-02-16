package todoapp.core.todos.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Repository;
import todoapp.core.todos.domain.Todo;
import todoapp.core.todos.domain.TodoRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 메모리 기반 할 일 저장소 구현체
 *
 * @author springrunner.kr@gmail.com
 */
@Repository("inMemoryTodoRepository")
public class InMemoryTodoRepository implements TodoRepository, ApplicationRunner {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private List<Todo> todos = new CopyOnWriteArrayList<Todo>();

  @Override
  public List<Todo> findAll() {
    return Collections.unmodifiableList(todos);
  }

  @Override
  public List<Todo> findByUsername(String username) {
    throw new UnsupportedOperationException("unimplemented feature for InMemoryTodoRepository");
  }

  public Optional<Todo> findById(Long id) {
    return todos.stream()
            .filter(todo -> Objects.equals(id, todo.getId()))
            .findFirst();
  }

  @Override
  public Todo save(Todo todo) {
    if (todos.contains(todo) == false) {
      todos.add(todo);
    }
    return todo;
  }

  @Override
  public void delete(Todo todo) {
    todos.remove(todo);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    todos.add(Todo.create("Task One!"));
    todos.add(Todo.create("Task Two!"));
  }

}