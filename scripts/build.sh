#!/bin/bash
./mvnw clean package -DskipTests || mvn clean package -DskipTests
