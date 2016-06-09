## Overview

This project is playground on Spark RDD and lots of random things alike.

## For the impatient

    sbt clean run

    # starts spark's master
    ${SPARK_HOME}/sbin/start-master.sh

    # submit a spark job using the stand alone cluster
    version=1.0-SNAPSHOT
    input=$(readlink -f src/test/resources/1901.txt)
    jar=target/scala-2.11/spark-rdd-ncdc_2.11-1.0-SNAPSHOT.jar
    ${SPARK_HOME}/bin/spark-submit \
        --class io.peartree.NcdcRddJob \
        --master spark://localhost:7077 \
        --deploy-mode cluster \
        ${jar} ${input} /tmp/1901-output.txt
