package wildflyswarm.filelogger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/")
public class MyController {

  private static final Logger logger = Logger.getLogger(MyController.class.getName());

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String get() {
    logger.fine(() -> "sql logger!");
    return "{\"value\":\"Hello\"}";
  }

}
