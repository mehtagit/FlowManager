#!/bin/bash
. /root/.bash_profile
host=`hostname -i`
VAR=""
HBM_APP=`ps -aef | grep "Fulfillment-0.0.1-SNAPSHOT.jar"| grep -v grep`
if [ "$HBM_APP" != "$VAR" ]
then
        echo "$HBM_APP"
        echo Already Running.........
else
cd /root/arun/Fulfillment/binary

 echo "STARTED ON `date`"  >>redirectLogs.txt

java -Dcom.sun.management.jmxremote \
     -Dcom.sun.management.jmxremote.port=1617 \
     -Dcom.sun.management.jmxremote.authenticate=false \
     -Dcom.sun.management.jmxremote.ssl=false \
     -Dlog4j.configuration=file:./log4j.properties \
     -jar Fulfillment-0.0.1-SNAPSHOT.jar 1>> redirectLogs.txt 2>> redirectLogs.txt &
echo Started
fi
