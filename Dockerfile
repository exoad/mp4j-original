FROM mcr.microsoft.com/vscode/devcontainers/universal:1-focal
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \ && apt-get -y install build-essential
USER root
