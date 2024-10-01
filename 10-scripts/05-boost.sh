#!/bin/bash
########################
# include the magic
# https://github.com/paxtonhare/demo-magic
########################
. demo-magic.sh
clear

# Set demo-magic options
TYPE_SPEED=50 # Accelerate typing
DEMO_CMD_COLOR="" # No bold
DEMO_PROMPT="${PURPLE}$ ${COLOR_RESET}"
DEMO_COMMENT_COLOR=$CYAN

# Save original PWD
_PWD=${PWD}

cd ../05-boost

# Init : delete boost
kubectl delete -f manifests/boost.yaml 2> /dev/null
kubectl -n remi rollout restart deployment my-app

pi "# Describe pod and check boot time"
pe "kubectl -n remi get pods -l app.kubernetes.io/name=my-app -w"
pe "kubectl -n remi describe pods -l app.kubernetes.io/name=my-app | grep \"Limits:\" -A5"
pe "kubectl -n remi get pods -l app.kubernetes.io/name=my-app -o name | xargs kubectl logs -f"

pi "# Set boost !"
pe "cat manifests/boost.yaml"
pei "kubectl apply -f manifests/boost.yaml"

pi "# Restart application and check boost"
pe "kubectl -n remi rollout restart deployment my-app"
pe "kubectl -n remi get pods -l app.kubernetes.io/name=my-app -w"
pe "kubectl -n remi describe pods -l app.kubernetes.io/name=my-app | grep \"Limits:\" -A5"
pe "kubectl -n remi get pods -l app.kubernetes.io/name=my-app -o name | xargs kubectl logs -f"
pe "kubectl -n kube-startup-cpu-boost-system get pods -o name | xargs kubectl -n kube-startup-cpu-boost-system logs --tail=2"

pi "# End"
kubectl delete -f manifests/boost.yaml 2> /dev/null

# Return to the default PWD
cd ${_PWD}

pe ""