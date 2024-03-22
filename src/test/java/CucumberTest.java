import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.sgcib.mowitnow")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber_report.html")
public class CucumberTest {
}
