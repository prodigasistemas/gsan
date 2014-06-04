#!bin/bash
rm -rf $JBOSS_GSAN/server/default/work
rm -rf $JBOSS_GSAN/server/default/tmp
rm -rf $JBOSS_GSAN/server/default/deploy/gcom*.ear
rm -rf $JBOSS_GSAN/server/default/deploy/gsan*.ear
cd $GSAN_PATH
#mvn clean install
ant -Dfile.encoding=ISO-8859-1
