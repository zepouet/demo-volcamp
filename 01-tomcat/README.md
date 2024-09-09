# Create from scratch

```
mvn archetype:generate -DgroupId=com.example -DartifactId=volcanix-webapp -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

# Update POM

```
<project>
  ...
  <packaging>war</packaging>
  ...
  <dependencies>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
    </dependency>
  </dependencies>

  <build>
    <finalName>${project.artifactId}</finalName>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.3.1</version>
      </plugin>
    </plugins>
  </build>
</project>
```

# COMPOSE

## RUN

```
docker compose up -d
```

## EXEC

```
psql -U admin -h postgres volcanix
```

# Webapp

## Calls

The current routes are
* http://localhost:8080/volcanix-webapp/admin
* http://localhost:8080/volcanix-webapp/hello

