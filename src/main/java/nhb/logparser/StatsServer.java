package nhb.logparser;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class StatsServer {

    private Stats stats;

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void start(int port) {

        port(port);
        staticFileLocation("/public");

        get("/stats", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("count", stats.getRequestCount());
            model.put("file", stats.getFilename());
            model.put("browsers", stats.getBrowserUsage());
            model.put("os", stats.getOsUsage());
            model.put("resources", stats.getResourceUsage());
            model.put("tool", new VelocityTools());

            return new ModelAndView(model, "stats.vm");
        }, new VelocityTemplateEngine());
    }
}
