#!/bin/bash
. /home/loanteam/.bash_profile
host=`hostname -i`
VAR=""
HBM_APP=`ps -aef | grep "FlowManager-0.0.4.jar"| grep -v grep`
if [ "$HBM_APP" != "$VAR" ]
then
        echo "$HBM_APP"
        echo Already Running.........
else
cd /home/loanteam/TESTSETUP/FullFilment/binary

 echo "STARTED ON `date`"  >>redirectLogs.txt

java -Dspring.config.location=./application.properties -jar FlowManager-0.0.4.jar 1>> redirectLogs.txt 2>> redirectLogs.txt &
echo Started
fi
