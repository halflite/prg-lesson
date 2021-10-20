package web.inject;

import java.util.HashMap;
import java.util.Map;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import freemarker.ext.servlet.FreemarkerServlet;
import web.servlet.IndexServlet;

public class AppServletModule extends ServletModule {

  @Override
  protected void configureServlets() {
    // install modules
    install(new EnvModule());
    install(new ServerModule());

    bind(FreemarkerServlet.class).in(Singleton.class);

    serve("/").with(IndexServlet.class);

    // FreeMarker Params
    Map<String, String> fp = new HashMap<>();
    fp.put("TemplatePath", "classpath:views");
    fp.put("NoCache", "true");
    fp.put("ResponseCharacterEncoding", "fromTemplate");
    fp.put("ExceptionOnMissingTemplate", "true");
    fp.put("incompatible_improvements", "2.3.31");
    fp.put("template_exception_handler", "rethrow");
    fp.put("template_update_delay", "0");
    fp.put("default_encoding", "UTF-8");
    fp.put("output_encoding", "UTF-8");
    fp.put("locale", "ja_JP");
    fp.put("number_format", "0.##########");
    serve("*.ftl").with(FreemarkerServlet.class, fp);
  }
}
