#!/bin/bash
CURRENT_PATH=$(pwd)

# Limpa o JBoss e apaga a versao atual
rm -rf $JBOSS_GSAN/server/default/work/*
rm -rf $JBOSS_GSAN/server/default/tmp/*
rm -rf $JBOSS_GSAN/server/default/deploy/gcom*.ear
rm -rf $JBOSS_GSAN/server/default/deploy/gsan*.ear

echo "> Compilando"
cd $GSAN_PATH

ant -Dfile.encoding=ISO-8859-1

if [ -z "$VERSAO" ]; then
  echo "Versao do build (1.0.0):"
  read versao
  
  [ -z "$versao" ] && versao=1.0.0
else
  versao=$VERSAO
fi

[ -z "$SUFIXO" ] && SUFIXO=-

echo "> Compactando"
cd $JBOSS_GSAN/server/default/deploy

mv $JBOSS_GSAN/server/default/deploy/gcom.ear $JBOSS_GSAN/server/default/deploy/gsan$SUFIXO$versao.ear

chmod 775 -R $JBOSS_GSAN/server/default/deploy/gsan$SUFIXO$versao.ear

zip -9yqr gsan$SUFIXO$versao.ear.zip gsan$SUFIXO$versao.ear/

# Transfere o build para o servidor de homologacao
if [ -z "$PORTA" ]; then
  echo "Porta SSH (22):"
  read porta
  
  [ -z "$porta" ] && porta=22
else
  porta=$PORTA
fi

if [ -z "$USUARIO" ]; then
  echo "Usuario Remoto ($USER):"
  read usuario
  
  [ -z "$usuario" ] && usuario=$USER
else
  usuario=$USUARIO
fi

if [ -z "$IP_REMOTO" ]; then
  echo "IP Remoto (127.0.0.1):"
  read ip_remoto
  
  [ -z "$ip_remoto" ] && ip_remoto=127.0.0.1
else
  ip_remoto=$IP_REMOTO
fi

if [ -z "$CAMINHO_REMOTO" ]; then
  echo "Caminho Remoto (/tmp):"
  read caminho_remoto
  
  [ -z "$caminho_remoto" ] && caminho_remoto=/
else
  caminho_remoto=$CAMINHO_REMOTO
fi

scp -P $porta $JBOSS_GSAN/server/default/deploy/gsan$SUFIXO$versao.ear.zip $usuario@$ip_remoto:$caminho_remoto

# Apaga o build transferido e volta ao local inicial
rm -rf gsan$SUFIXO$versao.ear.zip

cd $CURRENT_PATH

exit 0
