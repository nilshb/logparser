package nhb.logparser;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class StatsServer {

    public Stats stats;

    public StatsServer(Stats stats) {
        this.stats = stats;
    }

    private Map<String, Object> createModel() {
        Map<String, Object> model = new HashMap<>();
        model.put("count", stats.getRequestCount());
        model.put("file", stats.getFilename());
        model.put("browsers", stats.getBrowserUsage());
        model.put("os", stats.getOsUsage());
        model.put("resources", stats.getResourceUsage());
        model.put("tool", new VelocityTools());
        return model;
    }

    public void start(int port) {
        port(port);
        staticFileLocation("/public");

        get("/stats", (request, response) -> {
            return new ModelAndView(createModel(), "stats.vm");
        }, new VelocityTemplateEngine());
    }
}
