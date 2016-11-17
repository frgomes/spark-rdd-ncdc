## Overview

This project is playground on Spark RDD and lots of random things alike.

## For the impatient

    # download data
    mkdir -p ~/tmp/ncdc && cd ~/tmp/ncdc
    wget https://www.ncdc.noaa.gov/orders/qclcd/QCLCD200705.zip
    unzip QCLCD200705.zip

    # download this project
    mkdir -p ~/workspace && cd ~/workspace
    git clone https://github.com/frgomes/spark-rdd-ncdc
    cd spark-rdd-ncdc

    # download SBT runner and builds this project
    # NOTE: this will DOWNLOAD HALF OF THE INTERNET and will take A LOT OF TIME to complete!
    curl -s https://raw.githubusercontent.com/paulp/sbt-extras/master/sbt > sbt && chmod 0755 sbt
    ./sbt clean package assembly

    # start Spark's master
    ${SPARK_HOME}/sbin/start-master.sh

    # find your own IP
    IP=$(ip -o addr show | fgrep "scope global" | sed -r "s/[ \t]+/ /g" | fgrep "eth0 inet " | cut -d" " -f4 | cut -d/ -f1)

    # submit a spark job using deploy-mode=client
    $SPARK_HOME/bin/spark-submit --class io.peartree.ncdc.NcdcRddJob \
                                 --master spark://${IP}:7077 \
                                 --deploy-mode client \
                                 ./target/scala-2.11/spark-rdd-ncdc-1.0.jar

    # look for the output directory that should appear as YYYY-MM-DD_HH-mm-ss
    # ls -al ~/tmp
