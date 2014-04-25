package gcom.util;

/**
 *Esta classe tem como objetivo mapear as chaves do arquivo application.properties.
 *Dessa forma, evita-se escrever strings diretamente no código e/ou JSP's,
 *mesmo que essas strings representem chaves do properties em questão.
 *
 * @author Marlon Patrick
 * @since 08/10/2009
 */
public final class ConstantesInterfaceGSAN {

	//Botoes gerais

	public static final String BOTAO_GSAN_LIMPAR = "botao.gsan.limpar";

	public static final String BOTAO_GSAN_VOLTAR = "botao.gsan.voltar";

	public static final String BOTAO_GSAN_GERAR = "botao.gsan.gerar";

	public static final String BOTAO_GSAN_CANCELAR = "botao.gsan.cancelar";

	public static final String BOTAO_GSAN_IMPRIMIR = "botao.gsan.imprimir";

	//Label's gerais
	
	public static final String LABEL_GSAN_MES_ANO_FATURAMENTO = "label.gsan.mes_ano_faturamento";

	public static final String LABEL_GSAN_UNIDADE_NEGOCIO = "label.gsan.unidade_negocio";
	
	public static final String LABEL_GSAN_LOCALIDADE = "label.gsan.localidade";

	public static final String LABEL_GSAN_LOCALIDADE_INICIAL = "label.gsan.localidade_inicial";

	public static final String LABEL_GSAN_LOCALIDADE_FINAL = "label.gsan.localidade_final";

	public static final String LABEL_GSAN_CATEGORIA = "label.gsan.categoria";

	public static final String LABEL_GSAN_TIPO_DEBITO = "label.gsan.tipo_debito";

	public static final String LABEL_GSAN_PERFIL_IMOVEL = "label.gsan.perfil_imovel";
	
	public static final String LABEL_GSAN_ESFERA_PODER = "label.gsan.esfera_poder";

	public static final String LABEL_GSAN_DATA_CANCELAMENTO = "label.gsan.data_cancelamento";

	public static final String LABEL_GSAN_DATA_CANCELAMENTO_INICIAL = "label.gsan.data_cancelamento_inicial";

	public static final String LABEL_GSAN_DATA_CANCELAMENTO_FINAL = "label.gsan.data_cancelamento_final";

	public static final String LABEL_GSAN_USUARIO = "label.gsan.usuario";

	public static final String LABEL_GSAN_ANORMALIDADE_LEITURA = "label.gsan.anormalidade_leitura";

	public static final String LABEL_GSAN_GRUPO_FATURAMENTO = "label.gsan.grupo_faturamento";

	public static final String LABEL_GSAN_DATA_LEITURA = "label.gsan.data_leitura";

	public static final String LABEL_GSAN_DATA_LEITURA_INICIAL = "label.gsan.data_leitura_inicial";

	public static final String LABEL_GSAN_DATA_LEITURA_FINAL = "label.gsan.data_leitura_final";

	public static final String LABEL_GSAN_SETOR_COMERCIAL = "label.gsan.setor_comercial";

	public static final String LABEL_GSAN_SETOR_COMERCIAL_INICIAL = "label.gsan.setor_comercial_inicial";

	public static final String LABEL_GSAN_SETOR_COMERCIAL_FINAL = "label.gsan.setor_comercial_final";

	public static final String LABEL_GSAN_ROTA = "label.gsan.rota";

	public static final String LABEL_GSAN_ROTA_INICIAL = "label.gsan.rota_inicial";

	public static final String LABEL_GSAN_ROTA_FINAL = "label.gsan.rota_final";
	
	public static final String LABEL_GSAN_SEQUENCIAL_ROTA = "label.gsan.sequencial_rota";

	public static final String LABEL_GSAN_SEQUENCIAL_ROTA_INICIAL = "label.gsan.sequencial_rota_inicial";

	public static final String LABEL_GSAN_SEQUENCIAL_ROTA_FINAL = "label.gsan.sequencial_rota_final";
	
	public static final String LABEL_GSAN_GERENCIA_REGIONAL = "label.gsan.gerencia_regional";

	//Hint gerais
	
	public static final String HINT_GSAN_PESQUISAR = "hint.gsan.pesquisar";

	public static final String HINT_GSAN_APAGAR = "hint.gsan.apagar";

	public static final String HINT_GSAN_EXIBIR_CALENDARIO = "hint.gsan.exibir_calendario";

	//Mensagens gerais
	
	public static final String MSG_GSAN_RELATORIO_INFORMAR_DADOS_ABAIXO = "msg.gsan.relatorio.informar_dados_abaixo";


	//Msg de atenção gerais
	public static final String ATENCAO_GSAN_CAMPO_DEVE_SER_INFORMADO = "atencao.campo.informado";

	public static final String ATENCAO_GSAN_CAMPO_DEVE_SER_INFORMADA = "atencao.campo.informada";
	
	public static final String ATENCAO_GSAN_CAMPO1_INEXISTENTE_NO_CAMPO2_INFORMADO = "atencao.gsan.campo1_inexistente_no_campo2_informado";

	public static final String ATENCAO_GSAN_CAMPO1_INEXISTENTE_NA_CAMPO2_INFORMADA = "atencao.gsan.campo1_inexistente_na_campo2_informada";

	public static final String ATENCAO_PESQUISA_INEXISTENTE = "atencao.pesquisa_inexistente";
	
	public static final String ATENCAO_GSAN_CAMPO_FORMATO_INVALIDO = "atencao.gsan.campo_formato_invalido";
	
	public static final String ATENCAO_GSAN_CAMPO_FINAL_MENOR_CAMPO_INICIAL = "atencao.gsan.campo_final_menor_campo_inicial";
	
	public static final String ATENCAO_NAO_CADASTRADO = "atencao.naocadastrado";

	public static final String ATENCAO_GSAN_INFORME_O_CAMPO = "atencao.gsan.informe_o_campo";

	public static final String ATENCAO_GSAN_INFORME_A_CAMPO = "atencao.gsan.informe_a_campo";

	public static final String ATENCAO_ENTIDADE_SEM_DADOS_PARA_SELECAO = "atencao.entidade_sem_dados_para_selecao";

	public static final String ATENCAO_PESQUISA_NENHUM_RESULTADO = "atencao.pesquisa.nenhumresultado";

	//Msg de erro gerais
	public static final String ERRO_GSAN_ERRO_GRAVAR_RELATORIO_SISTEMA = "erro.gsan.erro_gravar_relatorio_sistema";
	
	//Relatorio de juros, multas e debitos cancelados

	public static final String TITULO_RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS = "titulo.relatorio_juros_multas_debitos_cancelados";

	public static final String LABEL_RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS_PERIODO_CANCELAMENTO = "label.relatorio_juros_multas_debitos_cancelados.periodo_cancelamento";

	public static final String LABEL_RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS_RESPONSAVEL_CANCELAMENTO= "label.relatorio_juros_multas_debitos_cancelados.responsavel_cancelamento";

	public static final String ATENCAO_RELATORIO_JUROS_MULTAS_DEBITOS_CANCELADOS_NENHUM_DEBITO_CANCELADO= "atencao.relatorio_juros_multas_debitos_cancelados.nenhum_debito_cancelado";

	//Relatório de Anormalidade de Leitura por Período

	public static final String TITULO_RELATORIO_ANORMALIDADE_LEITURA_PERIODO = "titulo.relatorio_anormalidade_leitura_periodo";

	public static final String LABEL_RELATORIO_ANORMALIDADE_LEITURA_PERIODO_PERIODO_LEITURA = "label.relatorio_anormalidade_leitura_periodo.periodo_leitura";

	public static final String ATENCAO_RELATORIO_ANORMALIDADE_LEITURA_PERIODO_PERIODO_LEITURA_DEVE_SER_QUATRO_MESES = "atencao.relatorio_anormalidade_leitura_periodo.periodo_leitura_deve_ser_quatro_meses";
}
