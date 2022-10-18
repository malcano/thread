#!/bin/bash
if [ $# -ne 1 ]
then
    echo "Usage: ./build.sh [FileName]"
    echo "e.g., ./build.sh Ball.java"
    exit
fi

if [[ "$OSTYPE" == "linux-gnu"* || "$OSTYPE" == "darwin"* ]]; then 
    echo "Linux or macOS"
    LIB="lib/*:."
elif [[ "$OSTYPE" == "cygwin" || "$OSTYPE" == "msys" ]];then
    echo "Windows"
    LIB="lib/*;."
fi

javac -encoding utf-8 -cp $LIB -d target src/$1