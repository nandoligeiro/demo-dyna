#!/bin/sh

if [ -f /opt/dynatrace/oneagent/agent/lib64/liboneagentloader.so ]; then
    echo "OneAgent encontrado, iniciando com monitoramento..."
    exec java -agentpath:/opt/dynatrace/oneagent/agent/lib64/liboneagentloader.so -jar app.jar
else
    echo "OneAgent não encontrado, iniciando sem monitoramento..."
    exec java -jar app.jar
fi
