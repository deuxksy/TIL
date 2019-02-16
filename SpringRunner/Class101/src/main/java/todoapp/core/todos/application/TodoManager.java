package todoapp.core.todos.application;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private TodoRepository repository;
    
    public TodoManager(TodoRepository repository) {
		this.repository = repository;
	}

	@Override
    public List<Todo> getAll() {
        return repository.findAll();
    }

	@Override
    public Todo create(String title) {
        return repository.save(Todo.create(title));
    }

    @Override
    public Todo update(Long id, String title, boolean completed) {
        return repository.findById(id)
                         .map(todo -> todo.update(title, completed))
                         .map(repository::save)
                         .orElseThrow(() -> new TodoEntityNotFoundException(id));
    }

    @Override
    public Todo delete(Long id) {
        Optional<Todo> todo = repository.findById(id);
        todo.ifPresent(repository::delete);

        return todo.orElseThrow(() -> new TodoEntityNotFoundException(id));
    }

}
