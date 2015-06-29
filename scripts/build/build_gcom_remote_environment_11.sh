SUFIXO=_environment-desenvolvimento
PORTA=22
USUARIO=jboss
IP_REMOTO=192.168.1.11
CAMINHO_REMOTO=/home/jboss/servers
VERSAO=1.0.0
DATE=`date +%d%m%Y%H%M%S`
.  $GSAN_PATH/scripts/build/build_gcom_remote.sh
#.  $GSAN_PATH/scripts/build/start_stop_gcom_remote.sh