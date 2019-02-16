package todoapp.core.todos.application;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todoapp.core.todos.domain.Todo;
import todoapp.core.todos.domain.TodoEntityNotFoundException;
import todoapp.core.todos.domain.TodoRepository;

/**
 * 할 일 검색기({@link TodoFinder}), 편집기(@{@link TodoEditor}) 인터페이스 구현체
 *
 * @author springrunner.kr@gmail.com
 */
@Service
public class TodoManager implements TodoFinder, TodoEditor {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  /**
   * interface TodoRepository 를 상속 받는 2개의 Class 가 있을때는 TodoRepository todoRepository @Autowired 안됨
  private TodoRepository todoRepository;

  public TodoManager(TodoRepository todoRepository) {
  this.todoRepository = todoRepository;
  }
   */

  /**
   * 생성자를 할때 각각의 이름 TodoRepositry 를 @Autowired 한다.
   */
  private TodoRepository inMemoryTodoRepository;
  private TodoRepository otherTodoRepository;

  public TodoManager(TodoRepository inMemoryTodoRepository, TodoRepository otherTodoRepository) {
    this.inMemoryTodoRepository = inMemoryTodoRepository;
    this.otherTodoRepository = otherTodoRepository;
  }

  @Override
  public List<Todo> getAll() {
    return inMemoryTodoRepository.findAll();
  }

  @Override
  public Todo create(String title) {
    return inMemoryTodoRepository.save(Todo.create(title));
  }

  @Override
  public Todo update(Long id, String title, boolean completed) {
    return inMemoryTodoRepository.findById(id)
            .map(todo -> todo.update(title, completed))
            .map(inMemoryTodoRepository::save)
            .orElseThrow(() -> new TodoEntityNotFoundException(id));
  }

  @Override
  public Todo delete(Long id) {
    Optional<Todo> todo = inMemoryTodoRepository.findById(id);
    todo.ifPresent(inMemoryTodoRepository::delete);

    return todo.orElseThrow(() -> new TodoEntityNotFoundException(id));
  }

}
