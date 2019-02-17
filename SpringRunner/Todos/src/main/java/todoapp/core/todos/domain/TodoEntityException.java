package todoapp.core.todos.domain;

import todoapp.commons.SystemException;

public class TodoEntityException extends SystemException {

    public TodoEntityException(String format, Object... args) {
        super(String.format(format, args));
    }

}
