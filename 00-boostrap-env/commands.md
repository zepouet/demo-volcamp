# SDKMAN

## Installation

```
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk version
```

```
sdk install quarkus
sdk install java 17-open
sdk install java 21.0.2-open
```

Later for second part
```
sdk install java 21.0.2-graalce
```

## Configuration

```
# to set the default version of java
sdk default java 21.0.2-open

# to set the current version of java for this shell
sdk use java 21.0.2-open
```

# QUARKUS

On IntelliJ with OpenJDK
```
sdk use java 21.0.2-open

[ ] ./mvnw package -Dnative 
[x] ./mvnw package -Dnative -Dquarkus.native.container-build=true
[ ] ./target/getting-start-1.0.0-SNAPSHOT-runner
[x] docker run -it -v ./target/getting-start-1.0.0-SNAPSHOT-runner:/myexec ubuntu bash

```
In a shell with GraalCE
```
sdk use java 21.0.2-graalce

[x] ./mvnw package -Dnative 
[x] ./target/getting-start-1.0.0-SNAPSHOT-runner
```

Compare the boot





