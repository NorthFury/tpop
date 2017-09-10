package north.tpop.mock;

import north.tpop.mock.transformer.ResourceFileTransformer;
import spark.ExceptionHandler;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class ServerStarter {

    public static void main(String[] args) {
        Spark.setPort(8085);

        Spark.exception(Exception.class, new ExceptionHandler() {

            @Override
            public void handle(Exception exception, Request request, Response response) {
                response.body(exception.toString());
            }
        });

        Spark.before(new Filter() {

            @Override
            public void handle(Request request, Response response) {
                try {
                    String delayString = request.queryParams("delay");
                    long delay = Long.parseLong(delayString);
                    Thread.sleep(delay);
                } catch (NumberFormatException | InterruptedException e) {
                }
            }
        });

        Spark.get("/view/:view", new Route() {

            @Override
            public Object handle(Request request, Response response) {
                return request.params(":view");
            }
        }, new ResourceFileTransformer("/views/"));
    }
}
