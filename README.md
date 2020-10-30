# cpu monitor

> > ```
> >  monitor jvm cpu and push data to kafka with springboot-stater patterns
> > ```

## usage

> >
> >
> >1. mvn install
> >
> >2. pom.xml 
> >
> >   ***
> >
> >   ```
> >   <dependency>
> >       <groupId>com.awifi.capacity</groupId>
> >       <artifactId>cpu-monitor</artifactId>
> >       <version>1.0.0</version>
> >   </dependency>
> >   ```
> >
> >   3.application.properties
> >
> >   ***
> >
> >   ```
> >   awifi.capacity.monitor.enable=true
> >   awifi.capacity.monitor.kafka-topic=test
> >   ```

## dependency

> >spring-kafka
> >
> >spring-logback

