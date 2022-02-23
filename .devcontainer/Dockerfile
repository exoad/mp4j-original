FROM mcr.microsoft.com/vscode/devcontainers/universal:1-focal
USER root
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \ && apt-get -y install --no-install-recommends <your-package-list-here>
USER codespace