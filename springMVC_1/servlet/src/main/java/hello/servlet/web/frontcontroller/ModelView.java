package hello.servlet.web.frontcontroller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;

import java.util.HashMap;
import java.util.Map;

public class ModelView {

    private String viewName;
    public Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
