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

# Download sdkman
pi "# Download and install sdkman"
pe "curl -s https://get.sdkman.io | bash"
pei "source $HOME/.sdkman/bin/sdkman-init.sh"
pei "sdk version"

# Install sdk versions
pi "# Install some java"
pe "sdk install java 17-open"
pei "sdk install java 21.0.2-open"
pei "sdk install quarkus"
pei "sdk install java 21.0.2-graalce"

pi "# Use java 17"
pe "sdk use java 17-open"
pei "java -version"

# to set the default version of java
pi "# Use java 21, as default"
pe "sdk default java 21.0.2-open"
pei "sdk use java 21.0.2-open"
pei "java -version"

pi "# End"
# Return to the default PWD
cd ${_PWD}

pe ""