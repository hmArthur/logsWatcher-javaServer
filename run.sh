#!/bin/bash

javac -cp lib/gson-2.13.1.jar -d out $(find src -name "*.java")
java -cp "out:lib/gson-2.13.1.jar" App 8080
