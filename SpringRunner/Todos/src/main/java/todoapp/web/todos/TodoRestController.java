package todoapp.web.todos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import todoapp.core.todos.application.TodoEditor;
import todoapp.core.todos.application.TodoFinder;
import todoapp.core.todos.domain.Todo;

import java.util.List;

@RestController
public class TodoRestController {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  private TodoFinder todoFinder;
  private TodoEditor todoEditor;

  public TodoRestController(TodoFinder todoFinder, TodoEditor todoEditor) {
    this.todoFinder = todoFinder;
    this.todoEditor = todoEditor;
  }

  // @RequestMapping(value = "/api/todos", method = RequestMethod.GET)
  @GetMapping("/api/todos")
  public List<Todo> todos() {
    return todoFinder.getAll();
  }

  @PostMapping("/api/todos")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody TodoEditorCommand command) {
    log.info("request command: {}", command);

    // 할 일 생성 로직 수행
    todoEditor.create(command.getTitle());
  }

  @PutMapping("/api/todos/{id}")
  public void update(@PathVariable Long id, @RequestBody TodoEditorCommand command) {
    log.info("request id: {}, command: {}", id, command);

    // 할 일 수정 로직 수행, 여러분에게 맡기겠습니다!
    todoEditor.update(id, command.getTitle(), command.isCompleted());
  }

  // 할 일 삭제 API 구현, 여러분에게 맡기겠습니다.
  @DeleteMapping("/api/todos/{id}")
  public void delete(@PathVariable Long id) {
    // 할 일 삭제 로직 수행
    todoEditor.delete(id);
  }


  public static class TodoEditorCommand {

    private String title;
    private boolean completed;

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public boolean isCompleted() {
      return completed;
    }

    public void setCompleted(boolean completed) {
      this.completed = completed;
    }

    @Override
    public String toString() {
      return "TodoEditorCommand [title=" + title + ", completed=" + completed + "]";
    }

  }

}
