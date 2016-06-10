#!/bin/bash

echo ${SPARK_HOME}/sbin/start-master.sh


version=1.0-SNAPSHOT
input=$HOME/workspace/spark-rdd-ncdc/src/test/resources/1901.txt
jar=$HOME/workspace/spark-rdd-ncdc/target/scala-2.11/spark-rdd-ncdc_2.11-1.0-SNAPSHOT.jar

${SPARK_HOME}/bin/spark-submit \
        --class io.peartree.NcdcRddJob \
        --master spark://terra.localdomain:7077 \
        --deploy-mode cluster \
        ${jar} ${input} /tmp/1901-output.txt
