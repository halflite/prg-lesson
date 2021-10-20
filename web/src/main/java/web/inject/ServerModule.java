package web.inject;

import java.util.EnumSet;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContextListener;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;


/** Inject Server */
public class ServerModule extends AbstractModule {

  @Provides
  @Singleton
  public Server providesServer(
      @Named("appContextListener") ServletContextListener appContextListener,
      @Named("server.port") Integer port) {
    Server server = new Server(port);

    ServletContextHandler contextHandler =
        new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
    contextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
    contextHandler.addEventListener(appContextListener);

    return server;
  }

  @Provides
  @Singleton
  @Named("appContextListener")
  public ServletContextListener providesAppContextListener(@Named("env.stage") String stage) {
    return new GuiceServletContextListener() {
      @Override
      protected Injector getInjector() {
        return Guice.createInjector(Stage.valueOf(stage), new AppServletModule());
      }
    };
  }
}
