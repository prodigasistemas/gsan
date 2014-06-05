#!bin/bash

CURRENT_PATH=$(pwd)

# Limpa o JBoss e apaga a versão atual
rm -rf $JBOSS_GSAN/server/default/work
rm -rf $JBOSS_GSAN/server/default/tmp
rm -rf $JBOSS_GSAN/server/default/deploy/gcom*.ear
rm -rf $JBOSS_GSAN/server/default/deploy/gsan*.ear

# Constroi o build
cd $GSAN_PATH
#mvn clean install
ant -Dfile.encoding=ISO-8859-1

echo "Versao do build (1.0.0):"
read versao
if [ -z $versao ]; then
  versao=1.0.0
fi

if [ -z $SUFIXO ]; then
  SUFIXO=-
fi

# Compacta o build
cd $JBOSS_GSAN/server/default/deploy

mv $JBOSS_GSAN/server/default/deploy/gcom.ear $JBOSS_GSAN/server/default/deploy/gsan$SUFIXO$versao.ear

zip -vr gsan$SUFIXO$versao.ear.zip gsan$SUFIXO$versao.ear/

# Transfere o build para o servidor de homologacao
echo "Porta SSH (22):"
if [ -z $PORTA ]; then
  read porta
  if [ -z $porta ]; then
    porta=22
  fi
else
  porta=$PORTA
fi

echo "Usuario Remoto ($USER):"
if [ -z $USUARIO ]; then
  read usuario
  if [ -z $usuario ]; then
    usuario=$USER
  fi
else
  usuario=$USUARIO
fi

echo "IP Remoto (127.0.0.1):"
if [ -z $IP_REMOTO ]; then
  read ip_remoto
  if [ -z $ip_remoto ]; then
    ip_remoto=127.0.0.1
  fi
else
  ip_remoto=$IP_REMOTO
fi

echo "Caminho Remoto (/tmp):"
if [ -z $CAMINHO_REMOTO ]; then
  read caminho_remoto
  if [ -z $caminho_remoto ]; then
    caminho_remoto=/
  fi
else
  caminho_remoto=$CAMINHO_REMOTO
fi

scp -P $porta $JBOSS_GSAN/server/default/deploy/gsan$SUFIXO$versao.ear.zip $usuario@$ip_remoto:$caminho_remoto

# Apaga o build transferido e volta ao local inicial
rm -rf gsan$SUFIXO$versao.ear.zip

cd $CURRENT_PATH
