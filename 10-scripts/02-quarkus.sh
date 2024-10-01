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

cd ../02-quarkus/
source $HOME/.sdkman/bin/sdkman-init.sh

pi "# Activate Graalvm"
pe "sdk use java 21.0.2-graalce"
pei "java -version"

# Build image
pi "# Build Native image"
pe "./mvnw clean package -Dnative -DskipTests"
pei " ./target/qute-quickstart-1.0.1-SNAPSHOT-runner"

pi "# Build native image using container"
pe "./mvnw clean package -Dnative -Dquarkus.native.container-build=true"

pi "# End"
# Return to the default PWD
cd ${_PWD}

pe ""