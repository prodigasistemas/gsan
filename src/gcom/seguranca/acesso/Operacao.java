package gcom.seguranca.acesso;

import gcom.seguranca.transacao.TabelaColuna;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Operacao extends TabelaAuxiliarAbreviada {

	private static final long serialVersionUID = 1L;

	public static final Integer OPERACAO_AVISO_BANCARIO_PESQUISAR = new Integer(1);
	public static final Integer OPERACAO_AVISO_BANCARIO_REMOVER = new Integer(2);
	public static final Integer OPERACAO_AVISO_BANCARIO_ATUALIZAR = new Integer(3);
	public static final Integer OPERACAO_AVISO_BANCARIO_INSERIR = new Integer(4);
	public static final Integer OPERACAO_ATUALIZAR_AVISO_BANCARIO = new Integer(1714);
	public static final Integer OPERACAO_REMOVER_AVISO_BANCARIO = new Integer(501);
	public static final Integer OPERACAO_LOGRADOURO_PESQUISAR = new Integer(5);
	public static final Integer OPERACAO_IMOVEL_INSERIR = new Integer(9);
	public static final Integer OPERACAO_LOCALIDADE_INSERIR = new Integer(10);
	public static final Integer OPERACAO_LOCALIDADE_REMOVER = new Integer(11);
	public static final Integer OPERACAO_LOCALIDADE_ATUALIZAR = new Integer(12);
	public static final Integer OPERACAO_LIGACAO_AGUA_EFETUAR = new Integer(257);
	public static final int OPERACAO_LIGACAO_AGUA_EFETUAR_INT = 257;
	public static final Integer OPERACAO_ACESSO_USUARIO_ATUALIZAR = new Integer(14);
	public static final Integer OPERACAO_QUADRA_ATUALIZAR = new Integer(15);
	public static final Integer OPERACAO_QUADRA_INSERIR = new Integer(16);
	public static final Integer OPERACAO_IMOVEL_ATUALIZAR = new Integer(17);
	public static final Integer OPERACAO_IMOVEL_ATUALIZAR_FATURAMENTO = new Integer(1705);
	public static final Integer OPERACAO_TRANSFERIR_IMOVEL_LOGRADOURO = new Integer(1831);
	public static final Integer OPERACAO_QUADRA_REMOVER = new Integer(18);
	public static final Integer OPERACAO_SETOR_COMERCIAL_INSERIR = new Integer(19);
	public static final Integer OPERACAO_SETOR_COMERCIAL_ATUALIZAR = new Integer(20);
	public static final Integer OPERACAO_HIDROMETRO_INSERIR = new Integer(21);
	public static final Integer OPERACAO_HIDROMETRO_ATUALIZAR = new Integer(245);
	public static final Integer OPERACAO_SETOR_COMERCIAL_REMOVER = new Integer(23);
	public static final Integer OPERACAO_HIDROMETRO_REMOVER = new Integer(244);
	public static final Integer OPERACAO_ROTA_INSERIR = new Integer(25);
	public static final Integer OPERACAO_ROTA_ATUALIZAR = new Integer(26);
	public static final Integer OPERACAO_ROTA_REMOVER = new Integer(27);
	public static final Integer OPERACAO_CLIENTE_INSERIR = new Integer(28);
	public static final Integer OPERACAO_SUBCATEGORIA_INSERIR = new Integer(32);
	public static final Integer OPERACAO_SUBCATEGORIA_ATUALIZAR = new Integer(33);
	public static final Integer OPERACAO_SUBCATEGORIA_REMOVER = new Integer(34);
	public static final Integer OPERACAO_CATEGORIA_INSERIR = new Integer(35);
	public static final Integer OPERACAO_CATEGORIA_ATUALIZAR = new Integer(36);
	public static final Integer OPERACAO_CATEGORIA_REMOVER = new Integer(37);
	public static final Integer OPERACAO_CLIENTE_ATUALIZAR = new Integer(38);
	public static final Integer OPERACAO_CLIENTE_REMOVER = new Integer(39);
	public static final Integer OPERACAO_LOGRADOURO_INSERIR = new Integer(41);
	public static final Integer OPERACAO_LOGRADOURO_ATUALIZAR = new Integer(42);
	public static final Integer OPERACAO_LOGRADOURO_REMOVER = new Integer(43);
	public static final Integer OPERACAO_BAIRRO_INSERIR = new Integer(44);
	public static final Integer OPERACAO_BAIRRO_ATUALIZAR = new Integer(45);
	public static final Integer OPERACAO_BAIRRO_REMOVER = new Integer(46);
	public static final Integer OPERACAO_REMANEJAMENTO_HIDROMETRO_EFETUAR = new Integer(47);
	public static final int OPERACAO_REMANEJAMENTO_HIDROMETRO_EFETUAR_INT = 47;
	public static final Integer OPERACAO_CORTE_LIGACAO_AGUA_EFETUAR = new Integer(48);
	public static final int OPERACAO_CORTE_LIGACAO_AGUA_EFETUAR_INT = 48;
	public static final Integer OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR = new Integer(49);
	public static final int OPERACAO_INSTALACAO_HIDROMETRO_EFETUAR_INT = 49;
	public static final Integer OPERACAO_RELIGACAO_AGUA_EFETUAR = new Integer(50);
	public static final int OPERACAO_RELIGACAO_AGUA_EFETUAR_INT = 50;
	public static final Integer OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_EFETUAR = new Integer(51);
	public static final int OPERACAO_RESTABELECIMENTO_LIGACAO_AGUA_EFETUAR_INT = 51;
	public static final Integer OPERACAO_EFETUAR_ALTERACAO_SENHA = new Integer(52);
	public static final Integer OPERACAO_INSERIR_CONTA = new Integer(53);
	public static final Integer OPERACAO_INSERIR_VENCIMENTO_ALTERNATIVO = new Integer(54);
	public static final Integer OPERACAO_EXCLUIR_VENCIMENTO_ALTERNATIVO = new Integer(55);
	public static final Integer OPERACAO_COLOCAR_CONTA_REVISAO = new Integer(56);
	public static final Integer OPERACAO_RETIRAR_CONTA_REVISAO = new Integer(57);
	public static final Integer OPERACAO_USUARIO_INSERIR = new Integer(58);
	public static final Integer OPERACAO_USUARIO_ATUALIZAR = new Integer(59);
	public static final Integer OPERACAO_USUARIO_REMOVER = new Integer(60);
	public static final Integer OPERACAO_USUARIO_ALTERAR_SENHA = new Integer(52);
	public static final Integer OPERACAO_USUARIO_ALTERAR_SENHA_LOGIN = new Integer(818);
	public static final Integer OPERACAO_USUARIO_CONTROLAR_PERMISSOES_ESPECIAIS = new Integer(100);
	public static final Integer OPERACAO_USUARIO_CONTROLAR_ACESSO = new Integer(61);
	public static final Integer OPERACAO_CREDITO_A_REALIZAR_INSERIR = new Integer(62);
	public static final Integer OPERACAO_CREDITO_A_REALIZAR_CANCELAR = new Integer(66);
	public static final Integer OPERACAO_CREDITO_A_REALIZAR_ATUALIZAR = new Integer(67);
	public static final Integer OPERACAO_DEBITO_A_COBRAR_INSERIR = new Integer(70);
	public static final Integer OPERACAO_DEBITO_A_COBRAR_CANCELAR = new Integer(71);
	public static final Integer OPERACAO_OPERACAO_INSERIR = new Integer(72);
	public static final Integer OPERACAO_PERFIL_PARCELAMENTO_INSERIR = new Integer(80);
	public static final Integer OPERACAO_CONTA_MENSAGEM_INSERIR = new Integer(81);
	public static final Integer OPERACAO_PERFIL_PARCELAMENTO_ATUALIZAR = new Integer(83);
	public static final Integer OPERACAO_PERFIL_PARCELAMENTO_REMOVER = new Integer(84);
	public static final Integer OPERACAO_CONTA_MENSAGEM_ATUALIZAR = new Integer(85);
	public static final Integer OPERACAO_CONTA_MENSAGEM_REMOVER = new Integer(86);
	public static final Integer OPERACAO_OPERACAO_ATUALIZAR = new Integer(88);
	public static final Integer OPERACAO_OPERACAO_REMOVER = new Integer(91);
	public static final Integer OPERACAO_INSERIR_CRONOGRAMA_FATURAMENTO = new Integer(102);
	public static final Integer OPERACAO_ATUALIZAR_CRONOGRAMA_FATURAMENTO = new Integer(106);
	public static final Integer OPERACAO_INSERIR_CRONOGRAMA_COBRANCA = new Integer(108);
	public static final Integer OPERACAO_GUIA_PAGAMENTO_INSERIR = new Integer(115);
	public static final Integer OPERACAO_GUIA_PAGAMENTO_CANCELAR = new Integer(116);
	public static final Integer OPERACAO_ATUALIZAR_CRONOGRAMA_COBRANCA = new Integer(117);
	public static final Integer OPERACAO_CONJUNTO_HIDROMETRO_ATUALIZAR = new Integer(120);
	public static final Integer OPERACAO_DEVOLUCOES_INSERIR = new Integer(126);
	public static final Integer OPERACAO_DEVOLUCOES_ATUALIZAR = new Integer(127);
	public static final Integer OPERACAO_DEVOLUCOES_REMOVER = new Integer(128);
	public static final Integer OPERACAO_IMOVEL_SITUACAO_INSERIR = new Integer(162);
	public static final Integer OPERACAO_RESOLUCAO_DIRETORIA_INSERIR = new Integer(138);
	public static final Integer OPERACAO_LIGACAO_ESGOTO_EFETUAR = new Integer(272);
	public static final int OPERACAO_LIGACAO_ESGOTO_EFETUAR_INT = 272;
	public static final Integer OPERACAO_CRITERIO_COBRANCA_INSERIR = new Integer(139);
	public static final Integer OPERACAO_INSCRICAO_IMOVEL_ALTERAR = new Integer(142);
	public static final Integer OPERACAO_CRITERIO_COBRANCA_ATUALIZAR = new Integer(143);
	public static final Integer OPERACAO_CRITERIO_COBRANCA_REMOVER = new Integer(144);
	public static final Integer OPERACAO_RESOLUCAO_DIRETORIA_ATUALIZAR = new Integer(145);
	public static final Integer OPERACAO_RESOLUCAO_DIRETORIA_REMOVER = new Integer(146);
	public static final Integer OPERACAO_INSERIR_COMANDO_ACAO_COBRANCA_EVENTUAL_ROTA = new Integer(528);
	public static final Integer OPERACAO_INSERIR_COMANDO_ACAO_COBRANCA_EVENTUAL_COMANDO = new Integer(529);
	public static final Integer OPERACAO_INSERIR_COMANDO_ACAO_COBRANCA_CRONOGRAMA = new Integer(153);
	public static final Integer OPERACAO_REMOVER_COMANDO_ACAO_COBRANCA_CRONOGRAMA = new Integer(154);
	public static final Integer OPERACAO_REMOVER_COMANDO_ACAO_COBRANCA_EVENTUAL = new Integer(155);
	public static final Integer OPERACAO_ATUALIZAR_COMANDO_ACAO_COBRANCA_EVENTUAL_COMANDO = new Integer(156);
	public static final Integer OPERACAO_ATUALIZAR_COMANDO_ACAO_COBRANCA_EVENTUAL_ROTA = new Integer(157);
	public static final Integer OPERACAO_FILTRAR_RELACAO_CLIENTE_IMOVEL = new Integer(158);
	public static final Integer OPERACAO_PAGAMENTO_INSERIR = new Integer(167);
	public static final Integer OPERACAO_PAGAMENTO_ATUALIZAR = new Integer(178);
	public static final Integer OPERACAO_MOVIMENTAR_HIDROMETRO = new Integer(179);
	public static final Integer OPERACAO_PAGAMENTO_REMOVER = new Integer(180);
	public static final Integer OPERACAO_SUPRESSAO_LIGACAO_AGUA_EFETUAR = new Integer(685);
	public static final int OPERACAO_SUPRESSAO_LIGACAO_AGUA_EFETUAR_INT = 685;
	public static final Integer OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR = new Integer(335);
	public static final int OPERACAO_SUBSTITUICAO_HIDROMETRO_EFETUAR_INT = 335;
	public static final Integer OPERACAO_RETIRADA_HIDROMETRO_EFETUAR = new Integer(296);
	public static final int OPERACAO_RETIRADA_HIDROMETRO_EFETUAR_INT = 296;
	public static final Integer OPERACAO_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO = new Integer(247);
	public static final int OPERACAO_MUDANCA_SITUACAO_FATURAMENTO_LIGACAO_ESGOTO_INT = 247;
	public static final Integer OPERACAO_EQUIPE_INSERIR = new Integer(248);
	public static final Integer OPERACAO_VOLUME_MINIMO_LIGACAO_ESGOTO_ATUALIZAR = new Integer(277);
	public static final int OPERACAO_VOLUME_MINIMO_LIGACAO_ESGOTO_ATUALIZAR_INT = 277;
	public static final Integer OPERACAO_INSTALACAO_HIDROMETRO_ATUALIZAR = new Integer(281);
	public static final Integer OPERACAO_CONSUMO_MINIMO_LIGACAO_AGUA_ATUALIZAR = new Integer(393);
	public static final int OPERACAO_CONSUMO_MINIMO_LIGACAO_AGUA_ATUALIZAR_INT = 393;
	public static final Integer OPERACAO_LIGACAO_ESGOTO_ATUALIZAR = new Integer(430);
	public static final Integer OPERACAO_INSERIR_ORDEM_SERVICO_PROGRAMACAO_ACOMPANHAMENTO_ROTEIRO = new Integer(252);
	public static final Integer OPERACAO_REGISTRO_ATENDIMENTO_ENCERRAR = new Integer(463);
	public static final Integer OPERACAO_INSERIR_IMOVEL_DOACAO = new Integer(282);
	public static final Integer OPERACAO_CANCELAR_IMOVEL_DOACAO = new Integer(293);
	public static final Integer OPERACAO_VALOR_COBRANCA_SERVICO_INSERIR = new Integer(514);
	public static final Integer OPERACAO_REGISTRO_ATENDIMENTO_TRAMITAR = new Integer(462);
	public static final Integer OPERACAO_SERVICO_TIPO_PRIORIDADE_INSERIR = new Integer(568);
	public static final Integer OPERACAO_MATERIAL_INSERIR = new Integer(515);
	public static final Integer OPERACAO__TIPO_RETORNO_OS_REFERIDA_INSERIR = new Integer(565);
	public static final Integer OPERACAO_SERVICO_TIPO_REFERENCIA_INSERIR = new Integer(567);
	public static final Integer OPERACAO_VALOR_COBRANCA_SERVICO_ATUALIZAR = new Integer(647);
	public static final Integer OPERACAO_VALOR_COBRANCA_SERVICO_REMOVER = new Integer(648);
	public static final Integer OPERACAO_TIPO_PERFIL_SERVICO_ATUALIZAR = new Integer(651);
	public static final Integer OPERACAO_TIPO_PERFIL_SERVICO_REMOVER = new Integer(662);
	public static final Integer OPERACAO_ESPECIFICACAO_SITUACAO_IMOVEL_REMOVER = new Integer(668);
	public static final Integer OPERACAO_ESPECIFICACAO_SITUACAO_IMOVEL_ATUALIZAR = new Integer(672);
	public static final Integer OPERACAO_EQUIPE_REMOVER = new Integer(674);
	public static final Integer OPERACAO_EQUIPE_ATUALIZAR = new Integer(676);
	public static final Integer OPERACAO_PROGRAMACAO_ABASTECIMENTO_MANUTENCAO_ATUALIZAR = new Integer(687);
	public static final Integer OPERACAO_MATERIAL_REMOVER = new Integer(690);
	public static final Integer OPERACAO_ALTERAR_DADOS_FATURAMENTO = new Integer(612);
	public static final Integer OPERACAO_ALTERAR_DADOS_DO_FATURAMENTO = new Integer(436);
	public static final Integer OPERACAO_MATERIAL_ATUALIZAR = new Integer(692);
	public static final Integer OPERACAO_UNIDADE_ORGANIZACIONAL_REMOVER = new Integer(704);
	public static final Integer OPERACAO_UNIDADE_ORGANIZACIONAL_ATUALIZAR = new Integer(702);
	public static final Integer OPERACAO_UNIDADE_ORGANIZACIONAL_INSERIR = new Integer(689);
	public static final Integer OPERACAO_EFETUAR_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO = new Integer(706);
	public static final int OPERACAO_EFETUAR_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT = 706;
	public static final Integer OPERACAO_ALTERACAO_HISTORICO_INSERIR = new Integer(714);
	public static final Integer OPERACAO_OCORRENCIA_ANORMALIDADE_INSERIR = new Integer(698);
	public static final Integer OPERACAO_MUNICIPIO_INSERIR = new Integer(726);
	public static final Integer OPERACAO_IMOVEL_COBRANCA_SITUACAO_INSERIR = new Integer(719);
	public static final Integer OPERACAO_IMOVEL_COBRANCA_SITUACAO_RETIRAR = new Integer(720);
	public static final Integer OPERACAO_TIPO_SERVICO_INSERIR = new Integer(564);
	public static final Integer OPERACAO_GERENCIA_REGIONAL_INSERIR = new Integer(730);
	public static final Integer OPERACAO_MUNICIPIO_REMOVER = new Integer(735);
	public static final Integer OPERACAO_MUNICIPIO_ATUALIZAR = new Integer(739);
	public static final Integer OPERACAO_GERENCIA_REGIONAL_ATUALIZAR = new Integer(745);
	public static final Integer OPERACAO_SISTEMA_PARAMETROS_INSERIR = new Integer(733);
	public static final Integer OPERACAO_GERENCIA_REGIONAL_REMOVER = new Integer(748);
	public static final Integer OPERACAO_FERIADO_INSERIR = new Integer(749);
	public static final Integer OPERACAO_FERIADO_ATUALIZAR = new Integer(770);
	public static final Integer OPERACAO_FERIADO_REMOVER = new Integer(768);
	public static final Integer OPERACAO_AGENCIA_BANCARIA_INSERIR = new Integer(772);
	public static final Integer OPERACAO_DISTRITO_OPERACIONAL_INSERIR = new Integer(778);
	public static final Integer OPERACAO_ARRECADADOR_INSERIR = new Integer(773);
	public static final Integer OPERACAO_ARRECADADOR_REMOVER = new Integer(797);
	public static final Integer OPERACAO_ARRECADADOR_ATUALIZAR = new Integer(792);
	public static final Integer OPERACAO_DISTRITO_OPERACIONAL_ATUALIZAR = new Integer(778);
	public static final Integer OPERACAO_SERVICO_TIPO_PRIORIDADE_REMOVER = new Integer(789);
	public static final Integer OPERACAO_INSERIR_TARIFA_SOCIAL = new Integer(595);
	public static final Integer OPERACAO_MANTER_TARIFA_SOCIAL = new Integer(754);
	public static final Integer OPERACAO_ANORMALIDADE_LEITURA_INSERIR = new Integer(832);
	public static final Integer OPERACAO_TIPO_CREDITO_INSERIR = new Integer(850);
	public static final Integer OPERACAO_SITUACAO_LIGACAO_ALTERAR = new Integer(857);
	public static final Integer OPERACAO_TIPO_CREDITO_ATUALIZAR = new Integer(860);
	public static final Integer OPERACAO_TIPO_CREDITO_REMOVER = new Integer(862);
	public static final Integer OPERACAO_NAO_ENTREGA_DOCUMENTOS_INFORMAR = new Integer(864);
	public static final Integer OPERACAO_TIPO_RETORNO_OS_REFERIDA_REMOVER = new Integer(694);
	public static final Integer OPERACAO_FUNCIONARIO_INSERIR = new Integer(869);
	public static final Integer OPERACAO_FUNCIONARIO_ATUALIZAR = new Integer(872);
	public static final Integer OPERACAO_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_DE_HIDROMETRO = new Integer(879);
	public static final Integer OPERACAO_EFETUAR_RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_DE_HIDROMETRO_INT = 879;
	public static final Integer OPERACAO_TIPO_SERVICO_REMOVER = new Integer(747);
	public static final Integer OPERACAO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA_EFETUAR = new Integer(358);
	public static final int OPERACAO_CORTE_ADMINISTRATIVO_LIGACAO_AGUA_EFETUAR_INT = 358;
	public static final Integer RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO = new Integer(879);
	public static final int RESTABELECIMENTO_LIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT = 879;
	public static final Integer OPERACAO_LEITURA_FISCALIZACAO_INFORMAR = new Integer(889);
	public static final Integer OPERACAO_ARRECADADOR_CONTRATO_ATUALIZAR = new Integer(1044);
	public static final Integer OPERACAO_CANCELAMENTO_RETIFICACAO_CONTA_DESFAZER = new Integer(361);
	public static final Integer OPERACAO_CONTA_RETIFICAR = new Integer(261);
	public static final Integer OPERACAO_INSERIR_SISTEMA_ESGOTO = new Integer(824);
	public static final Integer OPERACAO_SISTEMA_ESGOTO_ATUALIZAR = new Integer(841);
	public static final Integer OPERACAO_SISTEMA_ESGOTO_REMOVER = new Integer(835);
	public static final Integer OPERACAO_TIPO_DEBITO_ATUALIZAR = new Integer(830);
	public static final Integer OPERACAO_RA_DADOS_AGENCIA_REGULADORA_INFORMAR = new Integer(867);
	public static final Integer OPERACAO_CLIENTE_TIPO_INSERIR = new Integer(945);
	public static final Integer OPERACAO_CLIENTE_TIPO_ATUALIZAR = new Integer(949);
	public static final Integer OPERACAO_INSERIR_MARCA_HIDROMETRO = new Integer(940);
	public static final Integer OPERACAO_REMOVER_MARCA_HIDROMETRO = new Integer(970);
	public static final Integer OPERACAO_ATUALIZAR_MARCA_HIDROMETRO = new Integer(971);
	public static final Integer OPERACAO_CLIENTE_TIPO_REMOVER = new Integer(952);
	public static final Integer OPERACAO_COBRANCA_CRONOGRAMA_REMOVER = new Integer(449);
	public static final Integer OPERACAO_CAPACIDADE_HIDROMETRO_INSERIR = new Integer(953);
	public static final Integer OPERACAO_CONTRATO_DEMANDA_ATUALIZAR = new Integer(963);
	public static final Integer OPERACAO_CONTRATO_DEMANDA_REMOVER = new Integer(964);
	public static final Integer OPERACAO_ROTEIRO_EMPRESA_INSERIR = new Integer(987);
	public static final Integer OPERACAO_LEITURISTA_INSERIR = new Integer(999);
	public static final Integer OPERACAO_QUALIDADE_AGUA_INSERIR = new Integer(990);
	public static final Integer OPERACAO_LIGACAO_ESGOTO__SEM_RA_EFETUAR = new Integer(1002);
	public static final Integer OPERACAO_LIGACAO_AGUA_SEM_RA_EFETUAR = new Integer(882);
	public static final Integer OPERACAO_INSERIR_ATIVIDADE_COBRANCA = new Integer(1009);
	public static final Integer OPERACAO_COBRANCA_ACAO_INSERIR = new Integer(1010);
	public static final Integer OPERACAO_ROTEIRO_EMPRESA_ATUALIZAR = new Integer(1010);
	public static final Integer OPERACAO_ROTEIRO_EMPRESA_REMOVER = new Integer(1011);
	public static final Integer OPERACAO_INFORMAR_INDICES_ACRESCIMOS_IMPONTUALIDADE = new Integer(1017);
	public static final Integer OPERACAO_ALTERAR_VENCIMENTO_CONTA = new Integer(412);
	public static final Integer OPERACAO_CANCELAR_CONTA = new Integer(230);
	public static final Integer OPERACAR_RETIRAR_CONTA_REVISAO = new Integer(57);
	public static final Integer OPERACAO_IMOVEL_REMOVER = new Integer(292);
	public static final Integer OPERACAO_COBRANCA_ACAO_REMOVER = new Integer(1040);
	public static final Integer OPERACAO_COBRANCA_ACAO_ATUALIZAR = 1043;
	public static final Integer OPERACAO_ORDEM_SERVICO_ATUALIZAR = 260;
	public static final Integer OPERACAO_ORDEM_SERVICO_ENCERRAR = 297;
	public static final Integer OPERACAO_DESASSOCIAR_CONJUNTO_ROTAS_CRITERIO_COBRANCA = 1082;
	public static final Integer OPERACAO_ASSOCIAR_CONJUNTO_ROTAS_CRITERIO_COBRANCA = 1083;
	public static final Integer OPERACAO_INSERIR_NEGATIVADOR = new Integer(1092);
	public static final Integer OPERACAO_ATUALIZAR_NEGATIVADOR = new Integer(1093);
	public static final Integer OPERACAO_REMOVER_NEGATIVADOR = new Integer(1094);
	public static final Integer OPERACAO_INSERIR_CONTRATO_NEGATIVADOR = new Integer(1091);
	public static final Integer OPERACAO_ATUALIZAR_CONTRATO_NEGATIVADOR = new Integer(1095);
	public static final Integer OPERACAO_REMOVER_CONTRATO_NEGATIVADOR = new Integer(1096);
	public static final Integer OPERACAO_INSERIR_NEGATIVADOR_EXCLUSAO_MOTIVO = new Integer(1107);
	public static final Integer OPERACAO_ATUALIZAR_NEGATIVADOR_EXCLUSAO_MOTIVO = new Integer(1108);
	public static final Integer OPERACAO_INSERIR_NEGATIVADOR_RETORNO_MOTIVO = new Integer(1113);
	public static final Integer OPERACAO_ATUALIZAR_NEGATIVADOR_RETORNO_MOTIVO = new Integer(1114);
	public static final Integer OPERACAO_REMOVER_NEGATIVADOR_RETORNO_MOTIVO = new Integer(1115);
	public static final Integer OPERACAO_INSERIR_NEGATIVADOR_REGISTRO_TIPO = new Integer(1118);
	public static final Integer OPERACAO_ATUALIZAR_NEGATIVADOR_REGISTRO_TIPO = new Integer(1119);
	public static final Integer OPERACAO_REMOVER_NEGATIVADOR_REGISTRO_TIPO = new Integer(1122);
	public static final Integer GERAR_MOVIMENTO_EXCLUSAO_NEGATIVACAO = new Integer(1090);
	public static final Integer GERAR_RESUMO_DIARIO_NEGATIVACAO = new Integer(1128);
	public static final Integer EXECUTAR_COMANDO_NEGATIVACAO = new Integer(1129);
	public static final Integer EXCLUIR_NEGATIVACAO_ON_LINE = new Integer(133);
	public static final Integer OPERACAO_EFETUAR_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO = new Integer(1130);
	public static final int OPERACAO_EFETUAR_RELIGACAO_AGUA_COM_INSTALACAO_HIDROMETRO_INT = 1130;
	public static final Integer OPERACAO_INFORMAR_UNIDADE_NEGOCIO_TESTEMUNHA = new Integer(1323);
	public static final Integer OPERACAO_ATUALIZAR_DADOS_REGISTRO = new Integer(1183);
	public static final Integer OPERACAO_ATUALIZAR_LIGACAO_AGUA = new Integer(422);
	public static final Integer OPERACAO_CONSUMO_AREA_INFORMAR = new Integer(1300);
	public static final Integer OPERACAO_INSERIR_UNIDADE_NEGOCIO = new Integer(1439);
	public static final Integer OPERACAO_INSERIR_EMPRESA = new Integer(1304);
	public static final Integer OPERACAO_ATUALIZAR_EMPRESA = new Integer(1311);
	public static final Integer OPERACAO_INSERIR_LIGACAO_ESGOTO_PERFIL = new Integer(1445);
	public static final Integer OPERACAO_ATUALIZAR_LIGACAO_ESGOTO_PERFIL = new Integer(1452);
	public static final Integer OPERACAO_INFORMAR_CONTAS_EM_COBRANCA = new Integer(1455);
	public static final Integer OPERACAO_CANCELAR_DOCUMENTOS_COBRANCA = new Integer(1472);
	public static final Integer OPERACAO_EXCLUIR_NEGATIVACAO_ONLINE = new Integer(1132);
	public static final Integer OPERACAO_GERAR_COMANDO_CONTAS_COBRANCA_EMPRESA = new Integer(1482);
	public static final Integer OPERACAO_REMOVER_AUTO_INFRACAO = new Integer(1501);
	public static final Integer OPERACAO_ATUALIZAR_AUTO_INFRACAO = new Integer(1500);
	public static final Integer OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_INSERIR = new Integer(280);
	public static final Integer OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_REMOVER = new Integer(577);
	public static final Integer OPERACAO_SOLICITACAO_TIPO_ESPECIFICACAO_ATUALIZAR = new Integer(664);
	public static final Integer OPERACAO_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL = new Integer(1502);
	public static final int OPERACAO_ALTERAR_TIPO_CORTE_INT = 1503;
	public static final Integer OPERACAO_INFORMAR_SITUACAO_ESPECIAL_COBRANCA = 468;
	public static final Integer OPERACAO_RETIRAR_SITUACAO_ESPECIAL_COBRANCA = 470;
	public static final Integer OPERACAO_INFORMAR_SITUACAO_ESPECIAL_FATURAMENTO = 435;
	public static final Integer OPERACAO_RETIRAR_SITUACAO_ESPECIAL_FATURAMENTO = 477;
	public static final int OPERACAO_ATUALIZAR_SITUACAO_ESPECIAL_FATURAMENTO = 1533;
	public static final Integer OPERACAO_CONFIRMAR_PARCELAMENTO_CARTAO_CREDITO = new Integer(1522);
	public static final Integer OPERACAO_SISTEMA_ABASTECIMENTO_INSERIR = 976;
	public static final Integer TRANSFERIR_ROTA_ENTRE_GRUPO_EMPRESA = new Integer(1559);
	public static final Integer OPERACAO_ATUALIZAR_TIPO_RATEIO = new Integer(313);
	public static final Integer OPERACAO_ESTABELECER_VINCULO = new Integer(308);
	public static final Integer OPERACAO_DESFAZER_VINCULO = new Integer(315);
	public static final Integer OPERACAO_SISTEMA_ABASTECIMENTO_FILTRAR = 977;
	public static final Integer OPERACAO_SISTEMA_ABASTECIMENTO_ATUALIZAR = 978;
	public static final Integer OPERACAO_SISTEMA_ABASTECIMENTO_Remover = 979;
	public static final Integer OPERACAO_RAMO_ATIVIDADE_INSERIR = 1571;
	public static final Integer OPERACAO_RAMO_ATIVIDADE_FILTRAR = 1572;
	public static final Integer OPERACAO_RAMO_ATIVIDADE_ATUALIZAR = 1573;
	public static final Integer OPERACAO_RAMO_ATIVIDADE_REMOVER = 1574;
	public static final Integer OPERACAO_ANALISAR_EXCECOES_LEITURAS_CONSUMO = 353;
	public static final Integer OPERACAO_INSERIR_DEBITO_TIPO_VIGENCIA = 1598;
	public static final Integer OPERACAO_ATUALIZAR_DEBITO_TIPO_VIGENCIA = 1602;
	public static final Integer OPERACAO_EXCLUIR_DEBITO_TIPO_VIGENCIA = 1603;
	public static final Integer OPERACAO_ATUALIZAR_PARCELAMENTO_PAGAMENTO_CARTAO_CREDITO = 1594;
	public static final Integer OPERACAO_ATUALIZAR_ORDEM_REPAVIMENTACAO_PROCESSO_ACEITE = 1630;
	public static final Integer OPERACAO_INFORMAR_ITEM_SERVICO_CONTRATO = 1661;
	public static final Integer OPERACAO_RETORNO_CONTROLE_HIDROMETRO_INSERIR = 1660;
	public static final Integer OPERACAO_RETORNO_CONTROLE_HIDROMETRO_ATUALIZAR = 1666;
	public static final Integer OPERACAO_RETORNO_CONTROLE_HIDROMETRO_REMOVER = 1667;
	public static final Integer OPERACAO_INSERIR_ITEM_SERVICO = 1663;
	public static final Integer OPERACAO_MANTER_ITEM_SERVICO = 1668;
	public static final Integer OPERACAO_REMOVER_ITEM_SERVICO = 1669;
	public static final Integer OPERACAO_INSERIR_GRUPO = 6;
	public static final Integer OPERACAO_REMOVER_GRUPO = 7;
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
	public static final Integer OPERACAO_INSERIR_CUSTO_PAVIMENTO = 1734;
	public static final Integer OPERACAO_ATUALIZAR_CUSTO_PAVIMENTO = 1736;
	public static final Integer OPERACAO_EXCLUIR_CUSTO_PAVIMENTO = 1737;
	public static final Integer OPERACAO_INSERIR_CONTRATO_PARCELAMENTO_RD = 1755;
	public static final Integer OPERACAO_ATUALIZAR_CONTRATO_PARCELAMENTO_RD = 1763;
	public static final Integer OPERACAO_EXCLUIR_CONTRATO_PARCELAMENTO_RD = 1761;
	public static final Integer OPERACAO_AUTORIZAR_ALTERACAO_INSCRICAO_IMOVEL = 1776;
	public static final Integer OPERACAO_INSERIR_CONTRATO_PARCELAMENTO_POR_CLIENTE = 1773;
	public static final Integer OPERACAO_CONSUMO_PARAMETRO_INFORMAR = new Integer(1798);
	public static final Integer OPERACAO_CANCELAR_CONTRATO_PARCELAMENTO_CLIENTE = 1794;
	public static final Integer OPERACAO_ATUALIZAR_DADOS_IMOVEL_ATUALIZACAO_CADASTRAL = 1509;
	public static final Integer OPERACAO_INSERIR_MOTIVO_NAO_ACEITACAO_ENCERRAMENTO_OS = 1799;
	public static final Integer OPERACAO_INFORMAR_PAGAMENTO_CONTRATO_PARCELAMENTO_POR_CLIENTE = 1804;
	public static final Integer ATUALIZAR_IMPORTANCIA_LOGRADOURO = 1816;
	public static final Integer ATUALIZAR_IMPORTANCIA_TIPO_SERVICO = 1819;

	public static final int OPERACAO_SUBSTITUIR_RAMAL_DE_AGUA_EFETUAR = 16043;

	private String descricaoAbreviada;
	private Short indicadorRegistraTransacao;
	private String caminhoUrl;
	private Funcionalidade funcionalidade;
	private OperacaoTipo operacaoTipo;
	private Operacao idOperacaoPesquisa;
	private TabelaColuna tabelaColuna;
	private TabelaColuna argumentoPesquisa;

	public Operacao(String descricao, String descricaoAbreviada, String caminhoUrl, Date ultimaAlteracao, Funcionalidade funcionalidade,
			OperacaoTipo operacaoTipo, Operacao idOperacaoPesquisa, TabelaColuna tabelaColuna, TabelaColuna argPesquisa) {
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

	public Operacao(String descricao, String descricaoAbreviada, String caminhoUrl, Date ultimaAlteracao, Funcionalidade funcionalidade) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.caminhoUrl = caminhoUrl;
		this.ultimaAlteracao = ultimaAlteracao;
		this.funcionalidade = funcionalidade;
	}

	public Operacao() {
	}

	public Operacao(Integer idOperacao) {
		this.id = idOperacao;
	}

	public Operacao(Funcionalidade funcionalidade) {
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

	public Funcionalidade getFuncionalidade() {
		return this.funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public Operacao getIdOperacaoPesquisa() {
		return idOperacaoPesquisa;
	}

	public void setIdOperacaoPesquisa(Operacao idOperacaoPesquisa) {
		this.idOperacaoPesquisa = idOperacaoPesquisa;
	}

	public OperacaoTipo getOperacaoTipo() {
		return operacaoTipo;
	}

	public void setOperacaoTipo(OperacaoTipo operacaoTipo) {
		this.operacaoTipo = operacaoTipo;
	}

	public TabelaColuna getTabelaColuna() {
		return tabelaColuna;
	}

	public void setTabelaColuna(TabelaColuna tabelaColuna) {
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

		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("funcionalidade");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("operacaoTipo");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("idOperacaoPesquisa");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade("tabelaColuna");
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.ARGUMENTO_PESQUISA);
		filtroOperacao.adicionarCaminhoParaCarregamentoEntidade(FiltroOperacao.ARGUMENTO_PESQUISA_TABELA);

		filtroOperacao.adicionarParametro(new ParametroSimples(FiltroOperacao.ID, this.getId()));
		return filtroOperacao;
	}

	public TabelaColuna getArgumentoPesquisa() {
		return argumentoPesquisa;
	}

	public void setArgumentoPesquisa(TabelaColuna argumentoPesquisa) {
		this.argumentoPesquisa = argumentoPesquisa;
	}
}
