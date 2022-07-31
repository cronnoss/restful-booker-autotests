package api;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:dev_env.properties")
public interface ProjectConfig extends Config {

    @Key("baseURI")
    String baseURI();

    @DefaultValue("en")
    @Key("locale")
    String locale();

    @Key("logging")
    Boolean logging();

    @Key("token")
    String token();
}
