package todoapp.core.todos.domain;

public class TodoCreationException extends TodoEntityException {

  public TodoCreationException(String format, Object... args) {
    super(format, args);
  }

}
