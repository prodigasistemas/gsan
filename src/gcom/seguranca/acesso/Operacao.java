package gcom.seguranca.acesso;

import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Operacao extends TabelaAuxiliarAbreviada {

	private static final long serialVersionUID = 1L;

	/** AVISO_BANCARIO_PESQUISAR */
	public static final Integer OPERACAO_AVISO_BANCARIO_PESQUISAR = new Integer(
			1);

	/** AVISO_BANCARIO_PESQUISAR */
	public static final Integer OPERACAO_AVISO_BANCARIO_REMOVER = new Integer(2);

	/** AVISO_BANCARIO_PESQUISAR */
	public static final Integer OPERACAO_AVISO_BANCARIO_ATUALIZAR = new Integer(
			3);

	/** ATUALIZAR_AVISO_BANCARIO */
	public static final Integer OPERACAO_ATUALIZAR_AVISO_BANCARIO = new Integer(
			1714);
	
	/** ATUALIZAR_AVISO_BANCARIO */
	public static final Integer OPERACAO_REMOVER_AVISO_BANCARIO = new Integer(
			501);

	/** AVISO_BANCARIO_INSERIR */
	public static final Integer OPERACAO_AVISO_BANCARIO_INSERIR = new Integer(4);

	/** LOGRADOURO PESQUISAR */
	public static final Integer OPERACAO_LOGRADOURO_PESQUISAR = new Integer(5);

	/** IMOVEL INSERIR */
	public static final Integer OPERACAO_IMOVEL_INSERIR = new Integer(9);

	/** LOCALIDADE INSERIR */
	public static final Integer OPERACAO_LOCALIDADE_INSERIR = new Integer(10);

	/** LOCALIDADE REMOVER */
	public static final Integer OPERACAO_LOCALIDADE_REMOVER = new Integer(11);

	/** LOCALIDADE ATUALIZAR */
	public static final Integer OPERACAO_LOCALIDADE_ATUALIZAR = new Integer(12);

	/** LIGACAO AGUA EFETUAR */
	public static final Integer OPERACAO_LIGACAO_AGUA_EFETUAR = new Integer(257);

	public static final int OPERACAO_LIGACAO_AGUA_EFETUAR_INT = 257;

	/** BLOQUEAR DESBLOQUEAR ACESSO USUARIO */
	public static final Integer OPERACAO_ACESSO_USUARIO_ATUALIZAR = new Integer(
			14);

	/** QUADRA ATUALIZAR */
	public static final Integer OPERACAO_QUADRA_ATUALIZAR = new Integer(15);

	/** QUADRA INSERIR */
	public static final Integer OPERACAO_QUADRA_INSERIR = new Integer(16);

	/** IMOVEL ATUALIZAR */
	public static final Integer OPERACAO_IMOVEL_ATUALIZAR = new Integer(17);

	/** IMOVEL ATUALIZAR */
	public static final Integer OPERACAO_IMOVEL_ATUALIZAR_FATURAMENTO = new Integer(1705);
	
	/** IMOVEL TRANSFERIR **/
	public static final Integer OPERACAO_TRANSFERIR_IMOVEL_LOGRADOURO = new Integer(1831);

	/** QUADRA REMOVER */
	public static final Integer OPERACAO_QUADRA_REMOVER = new Integer(18);

	/** SETOR_COMERCIAL_INSERIR */
	public static final Integer OPERACAO_SETOR_COMERCIAL_INSERIR = new Integer(
			19);

	/** SETOR_COMERCIAL_ATUALIZAR */
	public static final Integer OPERACAO_SETOR_COMERCIAL_ATUALIZAR = new Integer(
			20);

	/** HIDROMETRO_INSERIR */
	public static final Integer OPERACAO_HIDROMETRO_INSERIR = new Integer(21);

	/** HIDROMETRO_ATUALIZAR */
	public static final Integer OPERACAO_HIDROMETRO_ATUALIZAR = new Integer(245);

	/** SETOR_COMERCIAL_REMOVER */
	public static final Integer OPERACAO_SETOR_COMERCIAL_REMOVER = new Integer(
			23);

	/** HIDROMETRO_REMOVER */
	public static final Integer OPERACAO_HIDROMETRO_REMOVER = new Integer(244);

	/** ROTA INSERIR */
	public static final Integer OPERACAO_ROTA_INSERIR = new Integer(25);

	/** ROTA ATUALIZAR */
	public static final Integer OPERACAO_ROTA_ATUALIZAR = new Integer(26);

	/** ROTA REMOVER */
	public static final Integer OPERACAO_ROTA_REMOVER = new Integer(27);

	/** CLIENTE INSERIR */
	public static final Integer OPERACAO_CLIENTE_INSERIR = new Integer(28);

	/** SUBCATEGORIA INSERIR */
	public static final Integer OPERACAO_SUBCATEGORIA_INSERIR = new Integer(32);

	/** SUBCATEGORIA ATUALIZAR */
	public static final Integer OPERACAO_SUBCATEGORIA_ATUALIZAR = new Integer(
			33);

	/** SUBCATEGORIA REMOVER */
	public static final Integer OPERACAO_SUBCATEGORIA_REMOVER = new Integer(34);

	/** CATEGORIA INSERIR */
	public static final Integer OPERACAO_CATEGORIA_INSERIR = new Integer(35);

	/** CATEGORIA ATUALIZAR */
	public static final Integer OPERACAO_CATEGORIA_ATUALIZAR = new Integer(36);

	/** CATEGORIA REMOVER */
	public static final Integer OPERACAO_CATEGORIA_REMOVER = new Integer(37);

	/** CLIENTE ATUALIZAR */
	public static final Integer OPERACAO_CLIENTE_ATUALIZAR = new Integer(38);

	/** CLIENTE REMOVER */
	public static final Integer OPERACAO_CLIENTE_REMOVER = new Integer(39);

	/** LOGRADOURO INSERIR */
	public static final Integer OPERACAO_LOGRADOURO_INSERIR = new Integer(41);

	/** LOGRADOURO ATUALIZAR */
	public static final Integer OPERACAO_LOGRADOURO_ATUALIZAR = new Integer(42);

	/** LOGRADOURO REMOVER */
	public static final Integer OPERACAO_LOGRADOURO_REMOVER = new Integer(43);

	/** BAIRRO_INSERIR */
	public static final Integer OPERACAO_BAIRRO_INSERIR = new Integer(44);

	/** BAIRRO_ATUALIZAR */
	public static final Integer OPERACAO_BAIRRO_ATUALIZAR = new Integer(45);

	/** BAIRRO_REMOVER */
	public static final Integer OPERACAO_BAIRRO_REMOVER = new Integer(46);

	/** REMANEJAMENTO HIDROMETRO EFETUAR */
	public static final Integer OPERACAO_REMANEJAMENTO_HIDROMETRO_EFETUAR = new Integer(
			47);

	public static final int OPERACAO_REMANEJAMENTO_HIDROMETRO_EFETUAR_INT = 47;

	/** CORTE DE LIGACAO AGUA EFETUAR */
	public static final Integer OPERACAO_CORTE_LIGACAO_AGUA_EFETUAR = new Integer(
			48);

	public static final int OPERACAO_CORTE_LIGACAO_AGUA_EFETUAR_INT = 48;

	/** LIGACAO ESGOTO EFETUAR */
	public static final Integer OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR = new Integer(
			49);

	public static final int OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR_INT = 49;

	/** RELIGAÇÃO AGUA EFETUAR */
	public static final Integer OPERACAO_RELIGACAO_AGUA_EFETUAR = new Integer(
			50);

	public static final int OPERACAO_RELIGACAO_AGUA_EFETUAR_INT = 50;

	/** RESTABELECIMENTO LIGAÇÃO AGUA EFETUAR */
	public static final Integer OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_EFETUAR = new Integer(
			51);

	public static final int OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_EFETUAR_INT = 51;

	/** EFETUA ALTERAÇÃO DE SENHA */
	public static final Integer OPERACAO_EFETUAR_ALTERACAO_SENHA = new Integer(
			52);

	/** INSERIR CONTA */
	public static final Integer OPERACAO_INSERIR_CONTA = new Integer(53);

	/** INSERIR VENCIMENTO ALTERNATIVO */
	public static final Integer OPERACAO_INSERIR_VENCIMENTO_ALTERNATIVO = new Integer(
			54);

	/** EXCLUIR VENCIMENTO ALTERNATIVO */
	public static final Integer OPERACAO_EXCLUIR_VENCIMENTO_ALTERNATIVO = new Integer(
			55);

	/** COLOCAR CONTA EM REVISÃO */
	public static final Integer OPERACAO_COLOCAR_CONTA_REVISAO = new Integer(56);

	/** RETIRAR CONTA DE REVISÃO */
	public static final Integer OPERACAO_RETIRAR_CONTA_REVISAO = new Integer(57);

	/** INSERIR USUÁIO **/
	public static final Integer OPERACAO_USUARIO_INSERIR = new Integer(58);

	/** ATUALIZAR USUÁIO **/
	public static final Integer OPERACAO_USUARIO_ATUALIZAR = new Integer(59);

	/** REMOVER USUÁIO **/
	public static final Integer OPERACAO_USUARIO_REMOVER = new Integer(60);

	/** USUÁIO EFETUAR ALTERAÇÃO SENHA **/
	public static final Integer OPERACAO_USUARIO_ALTERAR_SENHA = new Integer(52);

	/** USUÁIO EFETUAR ALTERAÇÃO SENHA **/
	public static final Integer OPERACAO_USUARIO_ALTERAR_SENHA_LOGIN = new Integer(
			818);

	/** USUÁIO CONTROLAR ACESSO - PERMISSÕES ESPECIAIS **/
	public static final Integer OPERACAO_USUARIO_CONTROLAR_PERMISSOES_ESPECIAIS = new Integer(
			100);

	public static final Integer OPERACAO_USUARIO_CONTROLAR_ACESSO = new Integer(
			61);

	/** CREDITO A REALIZAR INSERIR */
	public static final Integer OPERACAO_CREDITO_A_REALIZAR_INSERIR = new Integer(
			62);

	/** CREDITO A REALIZAR CANCELAR */
	public static final Integer OPERACAO_CREDITO_A_REALIZAR_CANCELAR = new Integer(
			66);

	/** CREDITO A REALIZAR ATUALIZAR */
	public static final Integer OPERACAO_CREDITO_A_REALIZAR_ATUALIZAR = new Integer(
			67);

	/** INSERIR DEBITO A COBRAR */
	public static final Integer OPERACAO_DEBITO_A_COBRAR_INSERIR = new Integer(
			70);

	/** CANCELAR DEBITO A COBRAR */
	public static final Integer OPERACAO_DEBITO_A_COBRAR_CANCELAR = new Integer(
			71);

	/** OPERACAO INSERIR */
	public static final Integer OPERACAO_OPERACAO_INSERIR = new Integer(72);

	/** PERFIL PARCELAMENTO INSERIR */
	public static final Integer OPERACAO_PERFIL_PARCELAMENTO_INSERIR = new Integer(
			80);

	/** CONTA MENSAGEM INSERIR */
	public static final Integer OPERACAO_CONTA_MENSAGEM_INSERIR = new Integer(
			81);

	/** PERFIL PARCELAMENTO INSERIR */
	public static final Integer OPERACAO_PERFIL_PARCELAMENTO_ATUALIZAR = new Integer(
			83);

	/** PERFIL PARCELAMENTO INSERIR */
	public static final Integer OPERACAO_PERFIL_PARCELAMENTO_REMOVER = new Integer(
			84);

	/** CONTA MENSAGEM INSERIR */
	public static final Integer OPERACAO_CONTA_MENSAGEM_ATUALIZAR = new Integer(
			85);

	/** CONTA MENSAGEM INSERIR */
	public static final Integer OPERACAO_CONTA_MENSAGEM_REMOVER = new Integer(
			86);

	/** OPERACAO ATUALIZAR */
	public static final Integer OPERACAO_OPERACAO_ATUALIZAR = new Integer(88);

	/** OPERACAO REMOVER */
	public static final Integer OPERACAO_OPERACAO_REMOVER = new Integer(91);

	/** INSERIR CRONOGRAMA DE FATURAMENTO */
	public static final Integer OPERACAO_INSERIR_CRONOGRAMA_FATURAMENTO = new Integer(
			102);

	/** INSERIR CRONOGRAMA DE FATURAMENTO */
	public static final Integer OPERACAO_ATUALIZAR_CRONOGRAMA_FATURAMENTO = new Integer(
			106);

	/** INSERIR CRONOGRAMA DE COBRANCA */
	public static final Integer OPERACAO_INSERIR_CRONOGRAMA_COBRANCA = new Integer(
			108);

	/** INSERIR GUIA PAGAMENTO */
	public static final Integer OPERACAO_GUIA_PAGAMENTO_INSERIR = new Integer(
			115);

	/** INSERIR GUIA PAGAMENTO */
	public static final Integer OPERACAO_GUIA_PAGAMENTO_CANCELAR = new Integer(
			116);

	/** INSERIR CRONOGRAMA DE COBRANCA */
	public static final Integer OPERACAO_ATUALIZAR_CRONOGRAMA_COBRANCA = new Integer(
			117);

	/** CONJUNTO_HIDROMETRO_ATUALIZAR */
	public static final Integer OPERACAO_CONJUNTO_HIDROMETRO_ATUALIZAR = new Integer(
			120);

	/** DEVOLUCOES_INSERIR */
	public static final Integer OPERACAO_DEVOLUCOES_INSERIR = new Integer(126);

	/** DEVOLUCOES_ATUALIZAR */
	public static final Integer OPERACAO_DEVOLUCOES_ATUALIZAR = new Integer(127);

	/** DEVOLUCOES_REMOVER */
	public static final Integer OPERACAO_DEVOLUCOES_REMOVER = new Integer(128);

	/** IMSERIR SITUACAO IMOVEL */
	public static final Integer OPERACAO_IMOVEL_SITUACAO_INSERIR = new Integer(
			162);

	/** IMSERIR RESOLUÇÃO DE DIRETORIA */
	public static final Integer OPERACAO_RESOLUCAO_DIRETORIA_INSERIR = new Integer(
			138);

	/** LIGACAO ESGOTO EFETUAR */
	public static final Integer OPERACAO_LIGACAO_ESGOTO_EFETUAR = new Integer(
			272);

	public static final int OPERACAO_LIGACAO_ESGOTO_EFETUAR_INT = 272;

	/** COBRANCA_CRITERIO_INSERIR */
	public static final Integer OPERACAO_CRITERIO_COBRANCA_INSERIR = new Integer(
			139);

	/** ALTERAR INSCRICAO IMOVEL */
	public static final Integer OPERACAO_INSCRICAO_IMOVEL_ALTERAR = new Integer(
			142);

	/** COBRANCA_CRITERIO_ATUALIZAR */
	public static final Integer OPERACAO_CRITERIO_COBRANCA_ATUALIZAR = new Integer(
			143);

	/** COBRANCA_CRITERIO_ATUALIZAR */
	public static final Integer OPERACAO_CRITERIO_COBRANCA_REMOVER = new Integer(
			144);

	/** ATUALIZAR RESOLUÇÃO DE DIRETORIA */
	public static final Integer OPERACAO_RESOLUCAO_DIRETORIA_ATUALIZAR = new Integer(
			145);

	/** REMOVER RESOLUÇÃO DE DIRETORIA */
	public static final Integer OPERACAO_RESOLUCAO_DIRETORIA_REMOVER = new Integer(
			146);

	/** REMOVER RESOLUÇÃO DE DIRETORIA */
	public static final Integer OPERACAO_INSERIR_COMANDO_ACAO_COBRANCA_EVENTUAL_ROTA = new Integer(
			528);

	/** REMOVER RESOLUÇÃO DE DIRETORIA */
	public static final Integer OPERACAO_INSERIR_COMANDO_ACAO_COBRANCA_EVENTUAL_COMANDO = new Integer(
			529);

	/** REMOVER RESOLUÇÃO DE DIRETORIA */
	public static final Integer OPERACAO_INSERIR_COMANDO_ACAO_COBRANCA_CRONOGRAMA = new Integer(
			153);

	/** REMOVER RESOLUÇÃO DE DIRETORIA */
	public static final Integer OPERACAO_REMOVER_COMANDO_ACAO_COBRANCA_CRONOGRAMA = new Integer(
			154);

	/** REMOVER RESOLUÇÃO DE DIRETORIA */
	public static final Integer OPERACAO_REMOVER_COMANDO_ACAO_COBRANCA_EVENTUAL = new Integer(
			155);

	/** REMOVER RESOLUÇÃO DE DIRETORIA */
	public static final Integer OPERACAO_ATUALIZAR_COMANDO_ACAO_COBRANCA_EVENTUAL_COMANDO = new Integer(
			156);

	/** REMOVER RESOLUÇÃO DE DIRETORIA */
	public static final Integer OPERACAO_ATUALIZAR_COMANDO_ACAO_COBRANCA_EVENTUAL_ROTA = new Integer(
			157);

	/** FILTRAR RELACAO CLIENTE IMOVEL */
	public static final Integer OPERACAO_FILTRAR_RELACAO_CLIENTE_IMOVEL = new Integer(
			158);

	/** PAGAMENTO INSERIR */
	public static final Integer OPERACAO_PAGAMENTO_INSERIR = new Integer(167);

	/** PAGAMENTO ATUALIZAR */
	public static final Integer OPERACAO_PAGAMENTO_ATUALIZAR = new Integer(178);

	/** FILTRAR RELACAO CLIENTE IMOVEL */
	public static final Integer OPERACAO_MOVIMENTAR_HIDROMETRO = new Integer(
			179);

	/** PAGAMENTO REMOVER */
	public static final Integer OPERACAO_PAGAMENTO_REMOVER = new Integer(180);

	/** SUPRESSAO LIGACAO AGUA EFETUAR */
	public static final Integer OPERACAO_SUPRESSAO_LIGACAO_AGUA_EFETUAR = new Integer(
			685);

	public static final int OPERACAO_SUPRESSAO_LIGACAO_AGUA_EFETUAR_INT = 685;

	/** SUBSTITUICAO LIGACAO AGUA EFETUAR */
	public static final Integer OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR = new Integer(
			335);

	public static final int OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR_INT = 335;

	/** RETIRADA LIGACAO AGUA EFETUAR */
	public static final Integer OPERACAO_RETIRADA_HIDROMETRO_EFETUAR = new Integer(
			296);

	public static final int OPERACAO_RETIRADA_HIDROMETRO_EFETUAR_INT = 296;

	/** EFETUAR MUDANÇA DE SITUAÇÃO DE FATURAMENTO DA LIGAÇÃO DE ESGOTO */
	public static final Integer OPERACAO_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO = new Integer(
			247);

	public static final int OPERACAO_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO_INT = 247;

	/** EQUIPE INSERIR */
	public static final Integer OPERACAO_EQUIPE_INSERIR = new Integer(248);

	/** VOLUME MINIMO ATUALIZAR */
	public static final Integer OPERACAO_VOLUME_MINIMO_LIGACAO_ESGOTO_ATUALIZAR = new Integer(
			277);

	public static final int OPERACAO_VOLUME_MINIMO_LIGACAO_ESGOTO_ATUALIZAR_INT = 277;

	/** INSTALACAO HIDROMETRO ATUALIZAR * */
	public static final Integer OPERACAO_INSTALACAO_HIDROMETRO_ATUALIZAR = new Integer(
			281);

	/** CONSUMO MINIMO ATUALIZAR */
	public static final Integer OPERACAO_CONSUMO_MINIMO_LIGACAO_AGUA_ATUALIZAR = new Integer(
			393);

	public static final int OPERACAO_CONSUMO_MINIMO_LIGACAO_AGUA_ATUALIZAR_INT = 393;

	/** LIGACAO ESGOTO ATUALIZAR */
	public static final Integer OPERACAO_LIGACAO_ESGOTO_ATUALIZAR = new Integer(
			430);

	/** LIGACAO ESGOTO ATUALIZAR */
	public static final Integer OPERACAO_INSERIR_ORDEM_SERVICO_PROGRAMACAO_ACOMPANHAMENTO_ROTEIRO = new Integer(
			252);

	/** REGISTRO ATENDIMENTO ENCERRAR */
	public static final Integer OPERACAO_REGISTRO_ATENDIMENTO_ENCERRAR = new Integer(
			463);

	/** IMOVEL DOACAO INSERIR */
	public static final Integer OPERACAO_INSERIR_IMOVEL_DOACAO = new Integer(
			282);

	/** IMOVEL DOACAO CANCELAR */
	public static final Integer OPERACAO_CANCELAR_IMOVEL_DOACAO = new Integer(
			293);

	/** VALOR COBRANÇA SERVIÇO INSERIR */
	public static final Integer OPERACAO_VALOR_COBRANCA_SERVICO_INSERIR = new Integer(
			514);

	/** REGISTRO ATENDIMENTO TRAMITAR */
	public static final Integer OPERACAO_REGISTRO_ATENDIMENTO_TRAMITAR = new Integer(
			462);

	/** INSERIR PRIORIDADE DO TIPO DE SERVIÇO */
	public static final Integer OPERACAO_SERVICO_TIPO_PRIORIDADE_INSERIR = new Integer(
			568);

	/** INSERIR MATERIAL */
	public static final Integer OPERACAO_MATERIAL_INSERIR = new Integer(515);

	/** INSERIR TIPO RETORNO DA OS REFERIDA */
	public static final Integer OPERACAO__TIPO_RETORNO_OS_REFERIDA_INSERIR = new Integer(
			565);

	/** INSERIR SERVICO TIPO REFERENCIA */
	public static final Integer OPERACAO_SERVICO_TIPO_REFERENCIA_INSERIR = new Integer(
			567);

	/** ATUALIZAR VALOR DE COBRANCA DE SERVICO */
	public static final Integer OPERACAO_VALOR_COBRANCA_SERVICO_ATUALIZAR = new Integer(
			647);

	/** REMOVER VALOR DE COBRANCA DE SERVICO */
	public static final Integer OPERACAO_VALOR_COBRANCA_SERVICO_REMOVER = new Integer(
			648);

	/** ATUALIZAR TIPO PERFIL SERVIÇO */
	public static final Integer OPERACAO_TIPO_PERFIL_SERVICO_ATUALIZAR = new Integer(
			651);

	/** REMOVER TIPO PERFIL SERVIÇO */
	public static final Integer OPERACAO_TIPO_PERFIL_SERVICO_REMOVER = new Integer(
			662);

	/** REMOVER ESPECIFICAÇÃO DA SITUAÇÃO DO IMÓVEL */
	public static final Integer OPERACAO_ESPECIFICACAO_SITUACAO_IMOVEL_REMOVER = new Integer(
			668);

	/** REMOVER ESPECIFICAÇÃO DA SITUAÇÃO DO IMÓVEL */
	public static final Integer OPERACAO_ESPECIFICACAO_SITUACAO_IMOVEL_ATUALIZAR = new Integer(
			672);

	/** REMOVER EQUIPE */
	public static final Integer OPERACAO_EQUIPE_REMOVER = new Integer(674);

	/** ATUALIZAR EQUIPE */
	public static final Integer OPERACAO_EQUIPE_ATUALIZAR = new Integer(676);

	/** ATUALIZAR */
	public static final Integer OPERACAO_PROGRAMACAO_ABASTECIMENTO_MANUTENCAO_ATUALIZAR = new Integer(
			687);

	/** REMOVER MATERIAL */
	public static final Integer OPERACAO_MATERIAL_REMOVER = new Integer(690);

	/** ALTERAR DADOS FATURAMENTO */
	public static final Integer OPERACAO_ALTERAR_DADOS_FATURAMENTO = new Integer(
			612);
	
	/** ALTERAR DADOS DO FATURAMENTO */
	public static final Integer OPERACAO_ALTERAR_DADOS_DO_FATURAMENTO = new Integer(
			436);

	/** ATUALIZAR MATERIAL */
	public static final Integer OPERACAO_MATERIAL_ATUALIZAR = new Integer(692);

	/** UNIDADE ORGANIZACIONAL REMOVER */
	public static final Integer OPERACAO_UNIDADE_ORGANIZACIONAL_REMOVER = new Integer(
			704);

	/** ATUALIZAR UNIDADE ORGANIZACIONAL */
	public static final Integer OPERACAO_UNIDADE_ORGANIZACIONAL_ATUALIZAR = new Integer(
			702);

	/** INSERIR UNIDADE ORGANIZACIONAL */
	public static final Integer OPERACAO_UNIDADE_ORGANIZACIONAL_INSERIR = new Integer(
			689);

	public static final Integer OPERACAO_EFETUAR_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO = new Integer(
			706);

	public static final int OPERACAO_EFETUAR_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT = 706;

	/** INSERIR HISTORICO ALTERAÇÕES SISTEMA */
	public static final Integer OPERACAO_ALTERACAO_HISTORICO_INSERIR = new Integer(
			714);

	/** INSERIR OCORRENCIA/ANORMALIDADE DE IMOVEL */
	public static final Integer OPERACAO_OCORRENCIA_ANORMALIDADE_INSERIR = new Integer(
			698);

	/** INSERIR MUNICIPIO */
	public static final Integer OPERACAO_MUNICIPIO_INSERIR = new Integer(726);

	/** INSERIR SITUACAO DE COBRANCA DO IMOVEL */
	public static final Integer OPERACAO_IMOVEL_COBRANCA_SITUACAO_INSERIR = new Integer(
			719);

	/** RETIRAR SITUACAO DE COBRANCA DO IMOVEL */
	public static final Integer OPERACAO_IMOVEL_COBRANCA_SITUACAO_RETIRAR = new Integer(
			720);

	/** INSERIR TIPO DE SERVICO */
	public static final Integer OPERACAO_TIPO_SERVICO_INSERIR = new Integer(564);

	/** INSERIR GERÊNCIA REGIONAL */
	public static final Integer OPERACAO_GERENCIA_REGIONAL_INSERIR = new Integer(
			730);

	/** REMOVER MUNICIPIO */
	public static final Integer OPERACAO_MUNICIPIO_REMOVER = new Integer(735);

	/** ATUALIZAR MUNICIPIO */
	public static final Integer OPERACAO_MUNICIPIO_ATUALIZAR = new Integer(739);

	/** ATUALIZAR GERÊNCIA REGIONAL */
	public static final Integer OPERACAO_GERENCIA_REGIONAL_ATUALIZAR = new Integer(
			745);

	/** SISTEMA PARAMETROS INSERIR */
	public static final Integer OPERACAO_SISTEMA_PARAMETROS_INSERIR = new Integer(
			733);

	/** GERÊNCIA REGIONAL REMOVER */
	public static final Integer OPERACAO_GERENCIA_REGIONAL_REMOVER = new Integer(
			748);

	/** INSERIR FERIADO */
	public static final Integer OPERACAO_FERIADO_INSERIR = new Integer(749);

	/** ATUALIZAR FERIADO */
	public static final Integer OPERACAO_FERIADO_ATUALIZAR = new Integer(770);

	/** REMOVER FERIADO */
	public static final Integer OPERACAO_FERIADO_REMOVER = new Integer(768);

	/** INSERIR AGÊNCIA BANCARIA */
	public static final Integer OPERACAO_AGENCIA_BANCARIA_INSERIR = new Integer(
			772);

	/** INSERIR DISTRITO OPERACIONAL */
	public static final Integer OPERACAO_DISTRITO_OPERACIONAL_INSERIR = new Integer(
			778);

	/** INSERIR ARRECADADOR */
	public static final Integer OPERACAO_ARRECADADOR_INSERIR = new Integer(773);

	/** REMOVER ARRECADADOR */
	public static final Integer OPERACAO_ARRECADADOR_REMOVER = new Integer(797);

	/** ATUALIZAR ARRECADADOR */
	public static final Integer OPERACAO_ARRECADADOR_ATUALIZAR = new Integer(
			792);

	/** ATUALIZAR DISTRITO OPERACIONAL */
	public static final Integer OPERACAO_DISTRITO_OPERACIONAL_ATUALIZAR = new Integer(
			778);

	/** REMOVER SERVICO TIPO PRIORIDADE */
	public static final Integer OPERACAO_SERVICO_TIPO_PRIORIDADE_REMOVER = new Integer(
			789);

	/** INSERIR DADOS TARIFA SOCIAL */
	public static final Integer OPERACAO_INSERIR_TARIFA_SOCIAL = new Integer(
			595);

	/** MANTER DADOS TARIFA SOCIAL */
	public static final Integer OPERACAO_MANTER_TARIFA_SOCIAL = new Integer(754);

	/** INSERIR CONTRATO DEMANDA */
	// public static final Integer OPERACAO_CONTRATO_DEMANDA_INSERIR = new
	// Integer(778);
	/** INSERIR ANORMALIDADE LEITURA */
	public static final Integer OPERACAO_ANORMALIDADE_LEITURA_INSERIR = new Integer(
			832);

	/** INSERIR TIPO DE CREDITO */
	public static final Integer OPERACAO_TIPO_CREDITO_INSERIR = new Integer(850);

	/** ALTERAR SITUACAO LIGACAO */
	public static final Integer OPERACAO_SITUACAO_LIGACAO_ALTERAR = new Integer(
			857);

	/** ATUALIZAR TIPO DE CREDITO */
	public static final Integer OPERACAO_TIPO_CREDITO_ATUALIZAR = new Integer(
			860);

	/** REMOVER TIPO DE CREDITO */
	public static final Integer OPERACAO_TIPO_CREDITO_REMOVER = new Integer(862);

	/** INFORMAR NAO ENTREGA DE DOCUMENTOS */
	public static final Integer OPERACAO_NAO_ENTREGA_DOCUMENTOS_INFORMAR = new Integer(
			864);

	/** REMOVER O TIPO DE RETORNO DA OS_REFERIDA */
	public static final Integer OPERACAO_TIPO_RETORNO_OS_REFERIDA_REMOVER = new Integer(
			694);

	/** INSERIR FUNCIONARIO */
	public static final Integer OPERACAO_FUNCIONARIO_INSERIR = new Integer(869);

	/** ATUALIZAR FUNCIONARIO */
	public static final Integer OPERACAO_FUNCIONARIO_ATUALIZAR = new Integer(
			872);

	/** EFETUAR RESTABELECIMENTO DA LIGAÇÃO DE ÁGUA COM INSTALAÇÂO DE HIDRÔMETRO */
	public static final Integer OPERACAO_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_DE_HIDROMETRO = new Integer(
			879);

	/** EFETUAR RESTABELECIMENTO DA LIGAÇÃO DE ÁGUA COM INSTALAÇÂO DE HIDRÔMETRO */
	public static final Integer OPERACAO_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_DE_HIDROMETRO_INT = 879;

	/** REMOVER TIPO DE SERVICO */
	public static final Integer OPERACAO_TIPO_SERVICO_REMOVER = new Integer(747);

	/** CORTE ADMINISTRATIVO LIGACAO AGUA EFETUAR */
	public static final Integer OPERACAO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA_EFETUAR = new Integer(
			358);

	public static final int OPERACAO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA_EFETUAR_INT = 358;

	/** RESTABELECIMENTO LIGACAO AGUA COM INSTALACAO HIDROMETRO EFETUAR */
	public static final Integer RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO = new Integer(
			879);

	public static final int RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT = 879;

	/** INFORMAR LEITURA DE FISCALIZACAO */
	public static final Integer OPERACAO_LEITURA_FISCALIZACAO_INFORMAR = new Integer(
			889);

	/** ATUALIZA CONTRATO DE ARRECADADOR */
	public static final Integer OPERACAO_ARRECADADOR_CONTRATO_ATUALIZAR = new Integer(
			1044);

	/** Desfazer Cancelamento e/ou Retificacao da Conta */
	public static final Integer OPERACAO_CANCELAMENTO_RETIFICACAO_CONTA_DESFAZER = new Integer(
			361);

	/** Retificar Conta */
	public static final Integer OPERACAO_CONTA_RETIFICAR = new Integer(261);

	/** INSERIR SISTEMA DE ESGOTO */

	public static final Integer OPERACAO_INSERIR_SISTEMA_ESGOTO = new Integer(
			824);

	/** ATUALIZAR SISTEMA DE ESGOTO */

	public static final Integer OPERACAO_SISTEMA_ESGOTO_ATUALIZAR = new Integer(
			841);

	/** REMOVER SISTEMA DE ESGOTO */

	public static final Integer OPERACAO_SISTEMA_ESGOTO_REMOVER = new Integer(
			835);

	/** ATUALIZAR TIPO DE DÉBITO */

	public static final Integer OPERACAO_TIPO_DEBITO_ATUALIZAR = new Integer(
			830);

	/** INFORMAR RA DADOS AGENCIA REGULADORA */
	public static final Integer OPERACAO_RA_DADOS_AGENCIA_REGULADORA_INFORMAR = new Integer(
			867);

	/** INSERIR CLIENTE TIPO */
	public static final Integer OPERACAO_CLIENTE_TIPO_INSERIR = new Integer(945);

	/** ATUALIZAR CLIENTE TIPO */
	public static final Integer OPERACAO_CLIENTE_TIPO_ATUALIZAR = new Integer(
			949);

	/** INSERIR MARCA DE HIDROMETRO */
	public static final Integer OPERACAO_INSERIR_MARCA_HIDROMETRO = new Integer(
			940);

	/** REMOVER MARCA DE HIDROMETRO */
	public static final Integer OPERACAO_REMOVER_MARCA_HIDROMETRO = new Integer(
			970);

	/** ATUALIZAR MARCA DE HIDROMETRO */
	public static final Integer OPERACAO_ATUALIZAR_MARCA_HIDROMETRO = new Integer(
			971);

	/** REMOVER CLIENTE TIPO */
	public static final Integer OPERACAO_CLIENTE_TIPO_REMOVER = new Integer(952);

	/** COBRANCA_CRONOGRAMA_REMOVER */
	public static final Integer OPERACAO_COBRANCA_CRONOGRAMA_REMOVER = new Integer(
			449);

	/** INSERIR CAPACIDADE DE HIDROMETRO */
	public static final Integer OPERACAO_CAPACIDADE_HIDROMETRO_INSERIR = new Integer(
			953);

	/** ATUALIZAR CONTRATO DE DEMANDA */
	public static final Integer OPERACAO_CONTRATO_DEMANDA_ATUALIZAR = new Integer(
			963);

	/** REMOVER CONTRATO DE DEMANDA */
	public static final Integer OPERACAO_CONTRATO_DEMANDA_REMOVER = new Integer(
			964);

	/** ROTEIRO EMPRESA INSERIR */
	public static final Integer OPERACAO_ROTEIRO_EMPRESA_INSERIR = new Integer(
			987);

	/** INSERIR LEITURISTA */
	public static final Integer OPERACAO_LEITURISTA_INSERIR = new Integer(999);

	/** QUALIDADE AGUA INSERIR */
	public static final Integer OPERACAO_QUALIDADE_AGUA_INSERIR = new Integer(
			990);

	public static final Integer OPERACAO_LIGACAO_ESGOTO__SEM_RA_EFETUAR = new Integer(
			1002);

	public static final Integer OPERACAO_LIGACAO_AGUA_SEM_RA_EFETUAR = new Integer(
			882);

	public static final Integer OPERACAO_INSERIR_ATIVIDADE_COBRANCA = new Integer(
			1009);

	/** IMSERIR Cobranca ação */
	public static final Integer OPERACAO_COBRANCA_ACAO_INSERIR = new Integer(
			1010);

	public static final Integer OPERACAO_ROTEIRO_EMPRESA_ATUALIZAR = new Integer(
			1010);

	public static final Integer OPERACAO_ROTEIRO_EMPRESA_REMOVER = new Integer(
			1011);

	public static final Integer OPERACAO_INFORMAR_INDICES_ACRESCIMOS_IMPONTUALIDADE = new Integer(
			1017);

	public static final Integer OPERACAO_ALTERAR_VENCIMENTO_CONTA = new Integer(
			412);

	public static final Integer OPERACAO_CANCELAR_CONTA = new Integer(230);

	public static final Integer OPERACAR_RETIRAR_CONTA_REVISAO = new Integer(57);

	public static final Integer OPERACAO_IMOVEL_REMOVER = new Integer(292);

	public static final Integer OPERACAO_COBRANCA_ACAO_REMOVER = new Integer(
			1040);

	public static final Integer OPERACAO_COBRANCA_ACAO_ATUALIZAR = 1043;

	public static final Integer OPERACAO_ORDEM_SERVICO_ATUALIZAR = 260;

	public static final Integer OPERACAO_ORDEM_SERVICO_ENCERRAR = 297;

	public static final Integer OPERACAO_DESASSOCIAR_CONJUNTO_ROTAS_CRITERIO_COBRANCA = 1082;

	public static final Integer OPERACAO_ASSOCIAR_CONJUNTO_ROTAS_CRITERIO_COBRANCA = 1083;

	public static final Integer OPERACAO_INSERIR_NEGATIVADOR = new Integer(1092);

	public static final Integer OPERACAO_ATUALIZAR_NEGATIVADOR = new Integer(
			1093);

	public static final Integer OPERACAO_REMOVER_NEGATIVADOR = new Integer(1094);

	public static final Integer OPERACAO_INSERIR_CONTRATO_NEGATIVADOR = new Integer(
			1091);

	public static final Integer OPERACAO_ATUALIZAR_CONTRATO_NEGATIVADOR = new Integer(
			1095);

	public static final Integer OPERACAO_REMOVER_CONTRATO_NEGATIVADOR = new Integer(
			1096);

	public static final Integer OPERACAO_INSERIR_NEGATIVADOR_EXCLUSAO_MOTIVO = new Integer(
			1107);

	public static final Integer OPERACAO_ATUALIZAR_NEGATIVADOR_EXCLUSAO_MOTIVO = new Integer(
			1108);

	public static final Integer OPERACAO_INSERIR_NEGATIVADOR_RETORNO_MOTIVO = new Integer(
			1113);

	public static final Integer OPERACAO_ATUALIZAR_NEGATIVADOR_RETORNO_MOTIVO = new Integer(
			1114);

	public static final Integer OPERACAO_REMOVER_NEGATIVADOR_RETORNO_MOTIVO = new Integer(
			1115);

	public static final Integer OPERACAO_INSERIR_NEGATIVADOR_REGISTRO_TIPO = new Integer(
			1118);

	public static final Integer OPERACAO_ATUALIZAR_NEGATIVADOR_REGISTRO_TIPO = new Integer(
			1119);

	public static final Integer OPERACAO_REMOVER_NEGATIVADOR_REGISTRO_TIPO = new Integer(
			1122);

	public static final Integer GERAR_MOVIMENTO_EXCLUSAO_NEGATIVACAO = new Integer(
			1090);

	public static final Integer GERAR_RESUMO_DIARIO_NEGATIVACAO = new Integer(
			1128);

	public static final Integer EXECUTAR_COMANDO_NEGATIVACAO = new Integer(1129);

	public static final Integer EXCLUIR_NEGATIVACAO_ON_LINE = new Integer(133);

	public static final Integer OPERACAO_EFETUAR_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO = new Integer(
			1130);

	public static final int OPERACAO_EFETUAR_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT = 1130;

	public static final Integer OPERACAO_INFORMAR_UNIDADE_NEGOCIO_TESTEMUNHA = new Integer(
			1323);

	public static final Integer OPERACAO_ATUALIZAR_DADOS_REGISTRO = new Integer(
			1183);

	public static final Integer OPERACAO_ATUALIZAR_LIGACAO_AGUA = new Integer(
			422);

	public static final Integer OPERACAO_CONSUMO_AREA_INFORMAR = new Integer(
			1300);

	public static final Integer OPERACAO_INSERIR_UNIDADE_NEGOCIO = new Integer(
			1439);

	public static final Integer OPERACAO_INSERIR_EMPRESA = new Integer(1304);

	public static final Integer OPERACAO_ATUALIZAR_EMPRESA = new Integer(1311);

	public static final Integer OPERACAO_INSERIR_LIGACAO_ESGOTO_PERFIL = new Integer(
			1445);

	public static final Integer OPERACAO_ATUALIZAR_LIGACAO_ESGOTO_PERFIL = new Integer(
			1452);

	public static final Integer OPERACAO_INFORMAR_CONTAS_EM_COBRANCA = new Integer(
			1455);

	public static final Integer OPERACAO_CANCELAR_DOCUMENTOS_COBRANCA = new Integer(
			1472);

	public static final Integer OPERACAO_EXCLUIR_NEGATIVACAO_ONLINE = new Integer(
			1132);

	public static final Integer OPERACAO_GERAR_COMANDO_CONTAS_COBRANCA_EMPRESA = new Integer(
			1482);

	public static final Integer OPERACAO_REMOVER_AUTO_INFRACAO = new Integer(
			1501);

	public static final Integer OPERACAO_ATUALIZAR_AUTO_INFRACAO = new Integer(
			1500);

	public static final Integer OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR = new Integer(
			280);

	public static final Integer OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER = new Integer(
			577);

	public static final Integer OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR = new Integer(
			664);

	public static final Integer OPERACAO_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL = new Integer(
			1502);

	public static final int OPERACAO_ALTERAR_TIPO_CORTE_INT = 1503;

	public static final Integer OPERACAO_INFORMAR_SITUACAO_ESPECIAL_COBRANCA = 468;

	public static final Integer OPERACAO_RETIRAR_SITUACAO_ESPECIAL_COBRANCA = 470;

	public static final Integer OPERACAO_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO = 435;

	public static final Integer OPERACAO_RETIRAR_SITUACAO_ESPECIAL_FATURAMENTO = 477;

	public static final int OPERACAO_ATUALIZAR_SITUACAO_ESPECIAL_FATURAMENTO = 1533;

	public static final Integer OPERACAO_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO = new Integer(
			1522);

	/** Inserir Sistema de Abastecimento **/
	public static final Integer OPERACAO_SISTEMA_ABASTECIMENTO_INSERIR = 976;

	public static final Integer TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA = new Integer(
			1559);

	public static final Integer OPERACAO_ATUALIZAR_TIPO_RATEIO = new Integer(
			313);

	public static final Integer OPERACAO_ESTABELECER_VINCULO = new Integer(308);

	public static final Integer OPERACAO_DESFAZER_VINCULO = new Integer(315);

	/** Filtrar Sistema de Abastecimento **/
	public static final Integer OPERACAO_SISTEMA_ABASTECIMENTO_FILTRAR = 977;

	/** Atualizar Sistema de Abastecimento **/
	public static final Integer OPERACAO_SISTEMA_ABASTECIMENTO_ATUALIZAR = 978;

	/** Remover Sistema de Abastecimento **/
	public static final Integer OPERACAO_SISTEMA_ABASTECIMENTO_Remover = 979;

	/** Inserir Ramo de Atividade **/
	public static final Integer OPERACAO_RAMO_ATIVIDADE_INSERIR = 1571;

	/** Filtrar Ramo de Atividade **/
	public static final Integer OPERACAO_RAMO_ATIVIDADE_FILTRAR = 1572;

	/** Atualizar Ramo de Atividade **/
	public static final Integer OPERACAO_RAMO_ATIVIDADE_ATUALIZAR = 1573;

	/** Remover Ramo de Atividade **/
	public static final Integer OPERACAO_RAMO_ATIVIDADE_REMOVER = 1574;

	/** Remover Ramo de Atividade **/
	public static final Integer OPERACAO_ANALISAR_EXCECOES_LEITURAS_CONSUMO = 353;

	/** Inserir Debito Tipo Vigencia **/
	public static final Integer OPERACAO_INSERIR_DEBITO_TIPO_VIGENCIA = 1598;

	/** Atualizar Debito Tipo Vigencia **/
	public static final Integer OPERACAO_ATUALIZAR_DEBITO_TIPO_VIGENCIA = 1602;

	/** Excluir Debito Tipo Vigencia **/
	public static final Integer OPERACAO_EXCLUIR_DEBITO_TIPO_VIGENCIA = 1603;

	/** Atualizar Parcelamento Pagamento Cartao Credito **/
	public static final Integer OPERACAO_ATUALIZAR_PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO = 1594;

	/** Atualizar Parcelamento Pagamento Cartao Credito **/
	public static final Integer OPERACAO_ATUALIZAR_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE = 1630;
	/** Informar Item Servico Contrato **/
	public static final Integer OPERACAO_INFORMAR_ITEM_SERVICO_CONTRATO = 1661;

	/** Inserir Retorno Controle Hidrometro **/
	public static final Integer OPERACAO_RETORNO_CONTROLE_HIDROMETRO_INSERIR = 1660;

	/** Atualizar Retorno Controle Hidrometro **/
	public static final Integer OPERACAO_RETORNO_CONTROLE_HIDROMETRO_ATUALIZAR = 1666;

	/** Remover Retorno Controle Hidrometro **/
	public static final Integer OPERACAO_RETORNO_CONTROLE_HIDROMETRO_REMOVER = 1667;

	/** Inserir Item de Serviço **/
	public static final Integer OPERACAO_INSERIR_ITEM_SERVICO = 1663;

	/** Atualizar Item de Serviço **/
	public static final Integer OPERACAO_MANTER_ITEM_SERVICO = 1668;

	/** Remover Item de Serviço **/
	public static final Integer OPERACAO_REMOVER_ITEM_SERVICO = 1669;

	/** Inserir Grupo **/
	public static final Integer OPERACAO_INSERIR_GRUPO = 6;

	/** Remover Grupo **/
	public static final Integer OPERACAO_REMOVER_GRUPO = 7;

	/** Manter Grupo **/
	public static final Integer OPERACAO_ATUALIZAR_GRUPO = 75;

	public static final Integer OPERACAO_INSERIR_CONTROLE_LIBERACAO_PERMISSAO_ESPECIAL = 1654;

	public static final Integer OPERACAO_MANTER_CONTROLE_LIBERACAO_PERMISSAO_ESPECIAL = 1673;

	public static final Integer OPERACAO_ATUALIZAR_DADOS_CLIENTE_PROMAIS = 1662;

	public static final Integer OPERACAO_ATUALIZAR_DADOS_IMOVEL_PROMAIS = 1681;
    
	public static final Integer OPERACAO_TIPO_CORTE_INSERIR = 1724;
    
    public static final Integer OPERACAO_TIPO_CORTE_ATUALIZAR = new Integer(1728);
    
    public static final Integer OPERACAO_TIPO_CORTE_REMOVER = new Integer(1726);
    
    public static final Integer OPERACAO_ATUALIZAR_CALIBRAGEM = new Integer(1813);

	public static final Integer OPERACAO_RELIGAR_IMOVEIS_CORTADOS_COM_CONSUMO_REAL = 1685;

	public static final Integer OPERACAO_IMOVEL_PERFIL_INSERIR = 1687;

	public static final Integer OPERACAO_IMOVEL_PERFIL_ATUALIZAR = 1702;

	public static final Integer OPERACAO_IMOVEL_PERFIL_REMOVER = 1698;

	public static final Integer OPERACAO_SOLICITACAO_ACESSO_SITUACAO_INSERIR = 1707;

	public static final Integer OPERACAO_SOLICITACAO_ACESSO_SITUACAO_ATUALIZAR = 1710;

	public static final Integer OPERACAO_SOLICITACAO_ACESSO_SITUACAO_REMOVER = 1709;
	
	/** Inserir Custo de Pavimento por Repavimentadora **/
	public static final Integer OPERACAO_INSERIR_CUSTO_PAVIMENTO = 1734;

	/** Atualizar Custo de Pavimento por Repavimentadora **/
	public static final Integer OPERACAO_ATUALIZAR_CUSTO_PAVIMENTO = 1736;

	/** Excluir Custo de Pavimento por Repavimentadora **/
	public static final Integer OPERACAO_EXCLUIR_CUSTO_PAVIMENTO = 1737;
	
	public static final Integer OPERACAO_INSERIR_CONTRATO_PARCELAMENTO_RD = 1755;
	
	public static final Integer OPERACAO_ATUALIZAR_CONTRATO_PARCELAMENTO_RD = 1763;
	
	public static final Integer OPERACAO_EXCLUIR_CONTRATO_PARCELAMENTO_RD = 1761;
	
	public static final Integer OPERACAO_AUTORIZAR_ALTERACAO_INSCRICAO_IMOVEL = 1776;
	
	public static final Integer OPERACAO_INSERIR_CONTRATO_PARCELAMENTO_POR_CLIENTE = 1773;
	

	public static final Integer OPERACAO_CONSUMO_PARAMETRO_INFORMAR = new Integer(
			1798);
	
	public static final Integer OPERACAO_CANCELAR_CONTRATO_PARCELAMENTO_CLIENTE = 1794;
	
	public static final Integer OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL = 1509;
	
	public static final Integer OPERACAO_INSERIR_MOTIVO_NAO_ACEITACAO_ENCERRAMENTO_OS = 1799;
	
	public static final Integer OPERACAO_INFORMAR_PAGAMENTO_CONTRATO_PARCELAMENTO_POR_CLIENTE = 1804;
	
	public static final Integer ATUALIZAR_IMPORTANCIA_LOGRADOURO = 1816;
	
	public static final Integer ATUALIZAR_IMPORTANCIA_TIPO_SERVICO = 1819;
	
	/** nullable persistent field */
	private String descricaoAbreviada;
	
	private Short indicadorRegistraTransacao; 

	/** nullable persistent field */
	private String caminhoUrl;

	/** persistent field */
	private gcom.seguranca.acesso.Funcionalidade funcionalidade;

	/** persistent field */
	private gcom.seguranca.acesso.OperacaoTipo operacaoTipo;

	/** persistent field */
	private gcom.seguranca.acesso.Operacao idOperacaoPesquisa;

	/** persistent field */
	private gcom.seguranca.transacao.TabelaColuna tabelaColuna;

	private gcom.seguranca.transacao.TabelaColuna argumentoPesquisa;

	/** full constructor */
	public Operacao(String descricao, String descricaoAbreviada,
			String caminhoUrl, Date ultimaAlteracao,
			gcom.seguranca.acesso.Funcionalidade funcionalidade,
			gcom.seguranca.acesso.OperacaoTipo operacaoTipo,
			gcom.seguranca.acesso.Operacao idOperacaoPesquisa,
			gcom.seguranca.transacao.TabelaColuna tabelaColuna,
			gcom.seguranca.transacao.TabelaColuna argPesquisa) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.caminhoUrl = caminhoUrl;
		this.ultimaAlteracao = ultimaAlteracao;
		this.funcionalidade = funcionalidade;
		this.operacaoTipo = operacaoTipo;
		this.idOperacaoPesquisa = idOperacaoPesquisa;
		this.tabelaColuna = tabelaColuna;
		this.argumentoPesquisa = argPesquisa;
	}

	public Operacao(String descricao, String descricaoAbreviada,
			String caminhoUrl, Date ultimaAlteracao,
			gcom.seguranca.acesso.Funcionalidade funcionalidade) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.caminhoUrl = caminhoUrl;
		this.ultimaAlteracao = ultimaAlteracao;
		this.funcionalidade = funcionalidade;
	}

	/** default constructor */
	public Operacao() {

	}

	/** default constructor */
	public Operacao(Integer idOperacao) {
		this.id = idOperacao;
	}

	/** minimal constructor */
	public Operacao(gcom.seguranca.acesso.Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Short getIndicadorRegistraTransacao() {
		return indicadorRegistraTransacao;
	}

	public void setIndicadorRegistraTransacao(Short indicadorRegistraTransacao) {
		this.indicadorRegistraTransacao = indicadorRegistraTransacao;
	}

	public String getCaminhoUrl() {
		return this.caminhoUrl;
	}

	public void setCaminhoUrl(String caminhoUrl) {
		this.caminhoUrl = caminhoUrl;
	}

	public gcom.seguranca.acesso.Funcionalidade getFuncionalidade() {
		return this.funcionalidade;
	}

	public void setFuncionalidade(
			gcom.seguranca.acesso.Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	/**
	 * @return Retorna o campo idOperacaoPesquisa.
	 */
	public gcom.seguranca.acesso.Operacao getIdOperacaoPesquisa() {
		return idOperacaoPesquisa;
	}

	/**
	 * @param idOperacaoPesquisa
	 *            O idOperacaoPesquisa a ser setado.
	 */
	public void setIdOperacaoPesquisa(
			gcom.seguranca.acesso.Operacao idOperacaoPesquisa) {
		this.idOperacaoPesquisa = idOperacaoPesquisa;
	}

	/**
	 * @return Retorna o campo operacaoTipo.
	 */
	public gcom.seguranca.acesso.OperacaoTipo getOperacaoTipo() {
		return operacaoTipo;
	}

	/**
	 * @param operacaoTipo
	 *            O operacaoTipo a ser setado.
	 */
	public void setOperacaoTipo(gcom.seguranca.acesso.OperacaoTipo operacaoTipo) {
		this.operacaoTipo = operacaoTipo;
	}

	/**
	 * @return Retorna o campo tabelaColuna.
	 */
	public gcom.seguranca.transacao.TabelaColuna getTabelaColuna() {
		return tabelaColuna;
	}

	/**
	 * @param tabelaColuna
	 *            O tabelaColuna a ser setado.
	 */
	public void setTabelaColuna(
			gcom.seguranca.transacao.TabelaColuna tabelaColuna) {
		this.tabelaColuna = tabelaColuna;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroOperacao filtroOperacao = new FiltroOperacao();

		filtroOperacao
				.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("operacaoTipo");
		filtroOperacao
				.adicionarCaminhoParaCarregamentoEntidade("idOperacaoPesquisa");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("tabelaColuna");
		filtroOperacao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.ARGUMENTO_PESQUISA);
		filtroOperacao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.ARGUMENTO_PESQUISA_TABELA);

		filtroOperacao.adicionarParametro(new ParametroSimples(
				FiltroOperacao.ID, this.getId()));
		return filtroOperacao;
	}

	/**
	 * @return Returns the argumentoPesquisa.
	 */
	public gcom.seguranca.transacao.TabelaColuna getArgumentoPesquisa() {
		return argumentoPesquisa;
	}

	/**
	 * @param argumentoPesquisa
	 *            The argumentoPesquisa to set.
	 */
	public void setArgumentoPesquisa(
			gcom.seguranca.transacao.TabelaColuna argumentoPesquisa) {
		this.argumentoPesquisa = argumentoPesquisa;
	}
}
