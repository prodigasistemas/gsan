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

* [Java 1.5 / 1.6](https://github.com/prodigasistemas/gsan/wiki/Instala%C3%A7%C3%A3o-do-Java)
* [JBoss 4.0.1sp1](https://github.com/prodigasistemas/gsan/wiki/Instala%C3%A7%C3%A3o-do-JBoss)
* [PostgreSQL 9.3.4](https://github.com/prodigasistemas/gsan/wiki/Instala%C3%A7%C3%A3o-do-PostgreSQL)

Configurações do Banco de Dados
===

Para a configuração do banco de dados utilizamos o MyBatis Migration, onde as configurações da biblioteca se encontram na [Wiki de migração de banco de dados](https://github.com/prodigasistemas/gsan/wiki/Criando-Migra%C3%A7%C3%B5es-na-Base-de-Dados).

Depois de configurar o MyBatis Migration, você deve realizar o download do projeto gsan-migracoes e executá-las para os bancos comercial e gerencial. [Os detalhes estão na página do projeto](https://github.com/prodigasistemas/gsan-migracoes)

Configurações no JBoss
===

O primeiro passo é adicionar as configurações do datasource para a conexão com o banco de dados.

[Veja as instruções na Wiki](https://github.com/prodigasistemas/gsan/wiki/Instala%C3%A7%C3%A3o-do-JBoss#configura%C3%A7%C3%B5es-do-jboss)

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
