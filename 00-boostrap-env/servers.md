# Ubuntu

# Tomcat Docker

```
# Add Docker's official GPG key:
sudo apt-get update
sudo apt-get install ca-certificates curl
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

# Add the repository to Apt sources:
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

sudo adduser ubuntu docker
```

- Install tomcat version 9
https://charith.xyz/docker/accessing-tomcat-manager-of-an-app-deployed-on-docker/
https://hub.docker.com/_/tomcat
```
docker run -it --rm -p 8888:8080 tomcat:9.0
```
https://octopus.com/blog/deployable-tomcat-docker-containers
https://charith.xyz/docker/accessing-tomcat-manager-of-an-app-deployed-on-docker/

## k3s

```
curl -sfL https://get.k3s.io |  INSTALL_K3S_EXEC="--tls-san volcamp.opsrel.io" sh -s -
# Check for Ready node, takes ~30 seconds 
sudo k3s kubectl get node

sudo cat /etc/rancher/k3s/k3s.yaml
```
