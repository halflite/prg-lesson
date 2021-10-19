package web.inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.Test;

public class EnvModuleTest {

  @Test
  public void testConf() throws Exception {
    Config config = ConfigProvider.getConfig();
    assertNotNull(config);
    
    Integer value = config.getValue("PORT", Integer.class);
    assertNotNull(value);
    assertEquals(value, 8080);
  }
}