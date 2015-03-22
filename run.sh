#!/usr/bin/env bash

# compile the java code into class files
javac WordCount.java
javac RunningMedian.java

# finally I'll execute my programs, for this challenge directories are hard coded, normally
# they would be passed in as arguments
java -cp . WordCount
java -cp . RunningMedian