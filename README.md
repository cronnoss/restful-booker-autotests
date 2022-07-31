# API testing for restful-booker

in Project used:
- _RoboPOJOGenerator_ IntelliJ IDEA/Android studio plugin for generate POJO files from JSON 
(https://awesomeopensource.com/project/robohorse/RoboPOJOGenerator);

- _LOMBOK_ (https://projectlombok.org/) with enabled settings "annotation processing" for Compiler;

- _OWNER_ (https://owner.aeonbits.org/) for ENV and Credentials configuration  with file.properties based.

_enter your token:_ in ```main/resources/dev_env.properties```

_for run not parallel, use:_ ```junit.jupiter.execution.parallel.enabled=false``` in ```test/resources/junit-platform.properties```