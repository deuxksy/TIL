package todoapp.core.todos.domain;

public class TodoEntityException extends RuntimeException {

    public TodoEntityException(String format, Object... args) {
        super(String.format(format, args));
    }

}
