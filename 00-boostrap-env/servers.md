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

- Activation du TLS pour accéder de l'extérieur
- FeatureGate InPlacePodVerticalScaling

```
curl -sfL https://get.k3s.io |  INSTALL_K3S_EXEC="--tls-san volcamp.opsrel.io --kube-apiserver-arg feature-gates=InPlacePodVerticalScaling=true" sh -s -
# Check for Ready node, takes ~30 seconds 
sudo k3s kubectl get node

sudo cat /etc/rancher/k3s/k3s.yaml
```

### cert-manager
```
helm upgrade cert-manager jetstack/cert-manager -n cert-manager --set installCRDs=true --install --create-namespace
kubectl apply -f manifests/letsencrypt-prod.yaml
```

### ArgoCD

https://argocd.volcamp.opsrel.io/
Compte : admin
Mot de passe dans le secret 
```
kubectl get secret argocd-initial-admin-secret -n argocd -o jsonpath="{.data.password}" | base64 --decode
```

### otel

```
kubectl apply -f https://raw.githubusercontent.com/grafana/docker-otel-lgtm/main/k8s/lgtm.yaml
```

- url : https://lgtm.volcamp.opsrel.io/
- login : admin
- mdp : changeme

```
kubectl port-forward --namespace otel svc/lgtm 4317:4317
``̀

### Helm

Voir https://docs.quarkiverse.io/quarkus-helm/dev/index.html

### Reste à faire

- Déploiement appli standard
