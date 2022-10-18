#!/bin/bash
if [ $# -ne 1 ]
then
    echo "Usage: ./run.sh [TargetClassName]"
    echo "e.g., ./run.sh Ball"
    exit
fi

if [[ "$OSTYPE" == "linux-gnu"* || "$OSTYPE" == "darwin"* ]]; then 
    echo "Linux or MacOS"
    CLASSPATH="target:lib/solution.jar:lib/tester.jar"
elif [[ "$OSTYPE" == "cygwin" || "$OSTYPE" == "msys" ]];then
    echo "Windows"
    CLASSPATH="target;lib/solution.jar;lib/tester.jar"
fi

TEST_CLASS="test.$1Test"

java -XX:MaxJavaStackTraceDepth=10 -jar lib/junit-platform-console-standalone-1.9.0.jar -class-path $CLASSPATH \
--select-class $TEST_CLASS --disable-banner --details=tree --details-theme=ascii