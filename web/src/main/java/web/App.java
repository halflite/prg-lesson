package web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.inject.AppServletModule;

/**
 * Hello world!
 *
 */
public class App {

  private static final Logger LOG = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new AppServletModule());
    final Server server = injector.getInstance(Server.class);

    try {
      LOG.info("sever start.");

      server.start();
    } catch (Exception e0) {
      LOG.warn("sever start exception:{}", e0);
      try {
        server.stop();
      } catch (Exception e1) {
        LOG.warn("sever stop exception:{}", e1);
      }
    }
  }
}
