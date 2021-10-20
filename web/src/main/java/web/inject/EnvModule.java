package web.inject;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.eclipse.microprofile.config.ConfigProvider;

/** Bind Config/Enviroment values module */
public class EnvModule extends AbstractModule {

  @Override
  protected void configure() {
    final Map<String, String> props = new HashMap<>();
    StreamSupport.stream(ConfigProvider.getConfig().getConfigSources().spliterator(), false)
        .forEach(conf -> props.putAll(conf.getProperties()));
    Names.bindProperties(binder(), props);
  }
}
