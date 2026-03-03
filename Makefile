# 1. Windows Compatibility Setup
# This forces 'make' to use Windows Command Prompt instead of searching for 'sh.exe'
ifeq ($(OS),Windows_NT)
    SHELL := cmd.exe
    MVN := mvnw.cmd
    OPEN := start
else
    SHELL := /bin/sh
    MVN := ./mvnw
    OPEN := open
endif

# 2. Variables
IMAGE_NAME=currency-service
CONTAINER_NAME=currency-app
API_KEY=0ebe82e0216e065b303a8a03

# 3. Development Commands
.PHONY: build up down test quality redeploy clean status

# Default command (runs if you just type 'make')
all: build

# Builds the JAR and then the Docker Image
build:
	$(MVN) clean package -DskipTests
	docker compose up -d --build

# Simply starts the existing containers
up:
	docker compose up -d

# Stops and removes the containers (frees up RAM)
down:
	docker compose down

# Runs your JUnit tests
test:
	$(MVN) test

# Runs Checkstyle and automatically opens the report
quality:
	$(MVN) checkstyle:checkstyle
	cmd /c start target\reports\checkstyle.html

# Cleans the target folder
clean:
	$(MVN) clean

# Quick shortcut to see what is running
status:
	docker ps
	docker stats --no-stream