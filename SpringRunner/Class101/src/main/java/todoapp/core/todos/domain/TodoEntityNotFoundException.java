package todoapp.core.todos.domain;

public class TodoEntityNotFoundException extends TodoEntityException {

    public TodoEntityNotFoundException(Long id) {
        super("등록된 할 일을 찾을 수 없습니다. (id: %d)", id);
    }

}
