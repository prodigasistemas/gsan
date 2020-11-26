#!/bin/bash
rm -rf $JBOSS_GSAN/server/default/work/*
rm -rf $JBOSS_GSAN/server/default/tmp/*
rm -rf $JBOSS_GSAN/server/default/data/*
rm -rf $JBOSS_GSAN/server/default/deploy/gcom*.ear
rm -rf $JBOSS_GSAN/server/default/deploy/gsan*.ear

[ ! -e "$GSAN_PATH/bin" ] && mkdir $GSAN_PATH/bin

cd $GSAN_PATH

ant -Dfile.encoding=ISO-8859-1

echo  ---- FIM ----
date
