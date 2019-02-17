package todoapp.web.todos;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractView;

import todoapp.commons.domain.Spreadsheet;
import todoapp.core.todos.application.TodoFinder;
import todoapp.core.todos.domain.Todo;
import todoapp.web.model.SiteProperties;

@Controller
public class TodoController {
	
	private TodoFinder finder;
	private SiteProperties site;
	
	public TodoController(TodoFinder finder, SiteProperties site) {
		this.finder = finder;
		this.site = site;
	}

	/*
	@ModelAttribute("site")
	public SiteProperties SiteProperties() {
		return site;
	}
	*/
	
	@RequestMapping("/todos")
	public void todos() {

	}
	
	@RolesAllowed("ROLE_USER")
	@RequestMapping(value = "/todos", produces = { "text/csv" })
	public Spreadsheet downloadTodos() {
		List<Todo> todos = finder.getAll();
		
		Spreadsheet.Row header = new Spreadsheet.Row()
					.addCell("id")
					.addCell("titile")
					.addCell("completed");
		
		List<Spreadsheet.Row> rows = todos.stream()
				.map(todo -> new Spreadsheet.Row()
											.addCell(todo.getId())
											.addCell(todo.getTitle())
											.addCell(todo.isCompleted()))
				.collect(Collectors.toList());
		
		return new Spreadsheet("todos", header, rows);
	}
	
	
	@Deprecated
	public static class TodoCsvView extends AbstractView {

		private Logger log = LoggerFactory.getLogger(this.getClass());
		
		public TodoCsvView() {
			setContentType("text/csv");
		}

		@Override
		protected boolean generatesDownloadContent() {
			return true;
		}

		@Override
		protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			
			log.debug("write resposne to csv");
			
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"todos.csv\"");
			response.getWriter().println("id,title,completed");
			
			// CSV 형식으로 출력
			List<Todo> todos = (List<Todo>) model.getOrDefault("todos", Collections.emptyList());
			for(Todo todo : todos) {
				String line = String.format("%d,%s,%s", todo.getId(), todo.getTitle(), todo.isCompleted());
				response.getWriter().println(line);
			}
			
			response.flushBuffer();
		}		
		
	}
	
	@Deprecated
	public static class TodoCsvViewResolver implements ViewResolver {

		@Override
		public View resolveViewName(String viewName, Locale locale) throws Exception {
			if (Objects.equals("todos", viewName)) {
				return new TodoCsvView();				
			}
			return null;
		}
		
	}

}
