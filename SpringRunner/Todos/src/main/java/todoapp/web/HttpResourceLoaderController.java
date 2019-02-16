package todoapp.web;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HttpResourceLoaderController {

  private ResourceLoader resourceLoader;

  public HttpResourceLoaderController(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @RequestMapping("/google")
  @ResponseBody
  public Resource google() {
    return this.resourceLoader.getResource("https://google.com");
  }

}
