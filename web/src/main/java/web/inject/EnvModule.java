package web.inject;

import java.util.Map;

import com.google.inject.AbstractModule;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

/** Inect Config/Enviroment values module */
public class EnvModule extends AbstractModule {

  @Override
  protected void configure() {
    binder().bind(Config.class).toInstance(ConfigProvider.getConfig());
  }

  public Map<String, String> providesConfigMap() {
    return null;
  }
}
