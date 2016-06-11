package wildflyswarm.filelogger;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.config.logging.Level;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.logging.LoggingFraction;

public class App {

  private static final String FILE_HANDLER_KEY = "SQL_FILE";

  public static void main(String[] args) throws Exception {
    Container container = new Container();

    LoggingFraction loggingFraction = new LoggingFraction()
        .defaultFormatter()
        .consoleHandler(Level.INFO, "PATTERN")
        .fileHandler(FILE_HANDLER_KEY, "sql-file.log", Level.FINE, "%d{HH:mm:ss,SSS} %-5p [%c] (%t) %s%e%n")
        .rootLogger(Level.INFO, "CONSOLE")
        .logger("wildflyswarm.filelogger", l -> l
            .level(Level.FINE)
            .handler(FILE_HANDLER_KEY)
            .useParentHandlers(false));

    container.fraction(loggingFraction);

    JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
    deployment.addPackage(App.class.getPackage());

    container.start().deploy(deployment);
  }

}
