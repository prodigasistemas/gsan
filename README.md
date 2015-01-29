Projeto GSAN
====

O Sistema Integrado de Gestão de Serviços e Saneamento (GSAN) tem como finalidade atender às necessidades das empresas e companhias de saneamento no que diz respeito à gestão comercial, financeira e de serviços. A solução integra diversas áreas como contabilidade, operacional e atendimento ao público, possibilitando ao gestor o acesso imediato a informações
analíticas ou consolidadas, resultando em mais agilidade na tomada de decisões e execução dos processos de negócio.

O GSAN é um software é livre e foi desenvolvido e disponibilizado pelo Ministério das Cidades, com o seguinte objetivo: "Sistema integrado de gestão de serviços de saneamento, desenvolvido com o objetivo de elevar o nível de desempenho e eficiência das empresas nacionais de abastecimento de água e coleta de esgotos." (Fonte: Portal do Software Público Brasileiro).

Módulos do GSAN
====

* Módulo de Segurança
* Módulo de Cadastro
* Módulo de Micromedição
* Módulo de Faturamento
* Módulo de Cobrança
* Módulo de Arrecadação
* Módulo de Atendimento ao Público

Requisitos de Infra-Estrutura
===

* PostgreSQL 9.3.4
* JBoss 4.0.1sp1
* Java 1.5 / 1.6

Configurações do Banco de Dados
===

Para a configuração do banco de dados utilizamos o MyBatis Migration, onde as configurações da biblioteca se encontram na Wiki - https://github.com/prodigasistemas/gsan/wiki/Criando-Migra%C3%A7%C3%B5es-na-Base-de-Dados - após configurar a biblioteca basta seguir as instruções abaixo:

A partir da pasta gsan/migrations-start/ executar os comandos:

``> migrate bootstrap``

``> migrate up``

Esses comandos irão executar os scripts SQL para iniciar a base de dados do “comercial”.

Configurações no JBoss
===

O primeiro passo é adicionar as configurações do datasource para a conexão com o banco de dados.

Para configurar o datasource, crie o arquivo postgres-ds.xml:

	<?xml version="1.0" encoding="UTF-8"?>

	<datasources>
		<local-tx-datasource>
			<jndi-name>PostgresDS</jndi-name>
			<connection-url>jdbc:postgresql://0.0.0.0/gsan_comercial</connection-url>
		    	<driver-class>org.postgresql.Driver</driver-class>
			<user-name>login</user-name>
			<password>senha</password>
			<min-pool-size>5</min-pool-size>
			<max-pool-size>200</max-pool-size>
			<idle-timeout-minutes>2</idle-timeout-minutes>
			<prepared-statement-cache-size>40</prepared-statement-cache-size>
		</local-tx-datasource>

 		<local-tx-datasource>
			<jndi-name>PostgresGerencialDS</jndi-name>
			<connection-url>jdbc:postgresql://0.0.0.0/gsan_gerencial</connection-url>
		    	<driver-class>org.postgresql.Driver</driver-class>
			<user-name>login</user-name>
			<password>senha</password>
		</local-tx-datasource>
	</datasources>

Substitua os dados de conexão do banco de dados, como *ip*, *porta*, *login* e *senha*.

Scripts de Build
===

Atualmente o GSAN possui um script Ant [build.xml](https://github.com/prodigasistemas/gsan/blob/master/build.xml) que realiza o processo de build para o Jboss que será utilizado para inicializar a aplicação.
Para o build.xml ser executado, é necessário criar o arquivo build.properties e configurá-lo com os caminhos necessários para a execução do build. Na raiz do projeto existe o arquivo [build.properties.exemplo](https://github.com/prodigasistemas/gsan/blob/master/build.properties.exemplo) que possui as variáveis necessárias para o script.

No projeto foram incluídos alguns scripts que automatizam algumas fases do processo de build e podem ser executados para realizar o build local e build remoto, para outros servidores, como ambientes de Homologação e Produção.
Para execução desses builds é necessário exportar as variáveis de ambiente JBOSS_GSAN e GSAN_PATH.

    export JBOSS_GSAN=<caminho_do_jboss_local>
    export GSAN_PATH=<caminho_do_projeto_gsan>

Os scripts de build se encontram na pasta scripts/build, o arquivo build_gcom.sh é o script para build local e o build_gcom_remote.sh para build em outro servidor.

Mais Informações
====

Portal do Software Público - http://www.softwarepublico.gov.br

Link para o espaço da comunidade GSAN - http://www.softwarepublico.gov.br/ver-comunidade?community_id=1593449
