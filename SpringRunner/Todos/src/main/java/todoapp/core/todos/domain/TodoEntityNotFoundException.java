package todoapp.core.todos.domain;

public class TodoEntityNotFoundException extends TodoEntityException {

	private Long id;
	
	public TodoEntityNotFoundException(Long id) {
        super("엔티티를 찾을 수 없습니다. (id: %d)", id);
        this.id = id;
    }

	@Override
	public Object[] getArguments() {
		return new Object[] { String.valueOf(id) };
	}

}
