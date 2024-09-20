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


@@```
In a shell with GraalCE or Mandrel
```
# L'activation du GraalCE permet de compiler des binaires pour MAC 
sdk use java 21.0.2-graalce

[x] ./mvnw clean package -Dnative -DskipTests 
     => build native image for MacOS
    ./target/getting-start-1.0.0-SNAPSHOT-runner ==> OK
[x] ./mvnw clean package -Dnative -Dquarkus.native.container-build=true 
     => build native linux/amr64 binary and the image
     [x] docker run -it --platform linux/arm64 -v $PWD/target/qute-quickstart-1.0.1-SNAPSHOT-runner:/binary ubuntu
         uname -a
         /binary 
     [ ] docker run -it --platform linux/amd64 -v $PWD/target/qute-quickstart-1.0.1-SNAPSHOT-runner:/binary ubuntu
     N'a pas de sens car on demande à docker en mode rosetta de faire tourner un aarch64 dans x86. Seul l'inverse est possible 
[ ] ./mvnw clean package -DskipTests -Dnative -Dquarkus.native.container-build=true -Dquarkus.docker.buildx.platform=linux/amd64
    [x] docker run -it --platform linux/aarch64 -v $PWD/target/qute-quickstart-1.0.1-SNAPSHOT-runner:/xxx ubuntu
    [ ] docker run -it --platform linux/amd64 -v $PWD/target/qute-quickstart-1.0.1-SNAPSHOT-runner:/xxx ubuntu
    N'a pas de sens car on demande à docker en mode rosetta de faire tourner un aarch64 dans x86. Seul l'inverse est possible
```

In a shell with OpenJDK
```
# L'activiation du OpenKJDK permet d'utiliser classiquement la JVM mais aussi de compiler des images amd64'
sdk use java 21.0.2-open

[ ] ./mvnw clean package -Dnative -DskipTests
    Echec direct car pas possible de compiler sans GraalVM
[x] ./mvnw clean package -DskipTests -Dnative -Dquarkus.native.container-build=true -Dquarkus.docker.buildx.platform=linux/arm64 -Dquarkus.container-image.build=true 
    Utilise une image GraalVM ARM64 dans Docker puis enchaine sur Dockerfile.native    
    En sortie on a un binaire linux/arm64        
    [ ] ./target/qute-quickstart-1.0.1-SNAPSHOT-runner
        vi ./target/qute-quickstart-1.0.1-SNAPSHOT-runner ==> binaire linux/aarch64
    [ ] docker run --platform linux/amd64 -it -v ./target/qute-quickstart-1.0.1-SNAPSHOT-runner:/binary centos bash
        Ne peut pas marcher car binaire linux/aarch64 et non pas amd64
    [x] docker run --platform linux/aarch64 -it -v ./target/qute-quickstart-1.0.1-SNAPSHOT-runner:/binary ubuntu bash
        uname -a
        ./xxx        
[x] ./mvnw clean package -DskipTests -Dquarkus.docker.buildx.platform=linux/amd64 -Dquarkus.container-image.build=true 
    [x] docker run --platform linux/amd64 -it -v ./target/qute-quickstart-1.0.1-SNAPSHOT-runner:/binary centos bash
    [ ] docker run --platform linux/arm64 -it -v ./target/qute-quickstart-1.0.1-SNAPSHOT-runner:/binary ubuntu bash
        
A-t-on besoin dans notre cas de GraalVM ? Non car on déploie sous Linux notre prod et donc OpenJDK suffit et grace à Quarkus
on peut utiliser GraalVM AMD64 dans Linux xHyve pour faire une image x86


## Links

- https://github.com/lwitkowski/quarkus-grafana-dashboard
- <quarkus.helm.name>monapp-quarkus</quarkus.helm.name>
- ./mvnw clean package -Dquarkus.container-image.build=true -Dquarkus.container-image.push=false -Dquarkus.container-image.registry=foo -Dquarkus.container-image.group=XXX
- https://docs.quarkiverse.io/quarkus-helm/dev/index.html#helm-expressions
- https://docs.quarkiverse.io/quarkus-helm/dev/index.html#helm-profiles
- http://quarkus.volcamp.opsrel.io/hello
- https://quarkus.io/guides/config-reference