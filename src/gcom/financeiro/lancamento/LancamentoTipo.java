package gcom.financeiro.lancamento;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LancamentoTipo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final static int AGUA_INT = 1;
	public final static int ESGOTO_INT = 2;
	public final static int FINANCIAMENTOS_INCLUIDOS_CURTO_PRAZO_INT = 3;
	public final static int FINANCIAMENTOS_INCLUIDOS_LONGO_PRAZO_INT = 4;
	public final static int FATURAMENTO_ADICIONAL_GUIA_PAGAMENTO_INT = 5;
	public final static int RECEITA_BRUTA_INT = 6;
	public final static int FINANCIAMENTOS_CANCELADOS_CURTO_PRAZO_INT = 7;
	public final static int FINANCIAMENTOS_CANCELADOS_LONGO_PRAZO_INT = 8;
	public final static int CANCELAMENTOS_POR_REFATURAMENTO_INT = 9;
	public final static int PARCELAMENTOS_COBRADOS_SUP_CANCELAMENTOS_POR_REFATURAMENTO_INT = 10;
	public final static int INCLUSOES_POR_REFATURAMENTO_INT = 11;
	public final static int RECEITA_LIQUIDA_INT = 12;
	public final static int FINANCIAMENTOS_COBRADOS_INT = 13;
	public final static int FINANCIAMENTOS_TRANSFERIDOS_CURTO_PRAZO_INT = 14;
	public final static int PARCELAMENTOS_REALIZADOS_CURTO_PRAZO_INT = 15;
	public final static int PARCELAMENTOS_REALIZADOS_LONGO_PRAZO_INT = 16;

	public final static int PARCELAMENTOS_CANCELADOS_CURTO_PRAZO_INT = 18;
	public final static int PARCELAMENTOS_CANCELADOS_LONGO_PRAZO_INT = 19;
	public final static int PARCELAMENTOS_COBRADOS_INT = 20;
	public final static int PARCELAMENTOS_TRASFERIDOS_PARA_CURTO_PRAZO_INT = 21;
	public final static int DEBITOS_ANTERIORES_COBRADOS_INT = 22;
	public final static int DEVOLUCAO__VALORES_EM_CONTA_INT = 23;
	public final static int VALORES_COBRADOS_INDEVIDAMENTE_INT = 24;
	public final static int TOTAL_COBRADO_NAS_CONTAS_INT = 25;
	public final static int IMPOSTOS_DEDUZIDOS_EM_CONTA_INT = 26;
	public final static int CONTAS_INT = 27;
	public final static int PARCELAMENTOS_COBRADOS_SUP_CONTAS_INT = 28;
	public final static int CREDITOS_REALIZADOS_SUP_CONTAS_INT = 29;
	public final static int TOTAL_CREDITOS_REALIZADOS_INT = 30;
	public final static int GUIAS_PAGAMENTO_INT = 31;
	public final static int DEBITOS_A_COBRAR_INT = 32;
	public final static int PAGAMENTO_EM_DUPLICIDADE_INT = 33;
	public final static int IMPOSTOS_RETIDOS_NAS_CONTAS_RECEBIDAS_INT = 34;
	public final static int DOCUMENTO_INEXISTENTE_INT = 35;
	public final static int VALOR_NAO_CONFERE_INT = 36;
	public final static int DOCUMENTOS_PAGOS_EM_DUPLICIDADE_EXCESSO_INT = 37;
	public final static int PAGAMENTO_EM_DUPLICIDADE_EXCESSO_NAO_ENCONTRADO_INT = 38;
	public final static int GUIA_DEVOLUCAO_NAO_INFORMADA_INT = 39;

	// constantes de tipo de lançamento >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public final static Integer AGUA = new Integer(1);
	public final static Integer ESGOTO = new Integer(2);
	public final static Integer FINANCIAMENTOS_INCLUIDOS_CURTO_PRAZO = new Integer(3);
	public final static Integer FINANCIAMENTOS_INCLUIDOS_LONGO_PRAZO = new Integer(4);
	public final static Integer FATURAMENTO_ADICIONAL_GUIA_PAGAMENTO = new Integer(5);
	public final static Integer RECEITA_BRUTA = new Integer(6);
	public final static Integer FINANCIAMENTOS_CANCELADOS_CURTO_PRAZO = new Integer(7);
	public final static Integer FINANCIAMENTOS_CANCELADOS_LONGO_PRAZO = new Integer(8);
	public final static Integer CANCELAMENTOS_POR_REFATURAMENTO = new Integer(9);
	public final static Integer PARCELAMENTOS_COBRADOS_SUP_CANCELAMENTOS_POR_REFATURAMENTO = new Integer(10);
	public final static Integer INCLUSOES_POR_REFATURAMENTO = new Integer(11);
	public final static Integer RECEITA_LIQUIDA = new Integer(12);
	public final static Integer FINANCIAMENTOS_COBRADOS = new Integer(13);
	public final static Integer FINANCIAMENTOS_TRANSFERIDOS_CURTO_PRAZO = new Integer(14);
	public final static Integer PARCELAMENTOS_REALIZADOS_CURTO_PRAZO = new Integer(15);
	public final static Integer PARCELAMENTOS_REALIZADOS_LONGO_PRAZO = new Integer(16);

	public final static Integer PARCELAMENTOS_CANCELADOS_CURTO_PRAZO = new Integer(18);
	public final static Integer PARCELAMENTOS_CANCELADOS_LONGO_PRAZO = new Integer(19);
	public final static Integer PARCELAMENTOS_COBRADOS = new Integer(20);
	public final static Integer PARCELAMENTOS_TRASFERIDOS_PARA_CURTO_PRAZO = new Integer(21);
	public final static Integer DEBITOS_ANTERIORES_COBRADOS = new Integer(22);
	public final static Integer DEVOLUCAO__VALORES_EM_CONTA = new Integer(23);
	public final static Integer CREDITOS_REALIZADOS = new Integer(24);
	public final static Integer TOTAL_COBRADO_NAS_CONTAS = new Integer(25);
	public final static Integer IMPOSTOS_DEDUZIDOS_EM_CONTA = new Integer(26);
	public final static Integer CONTAS = new Integer(27);
	public final static Integer PARCELAMENTOS_COBRADOS_SUP_CONTAS = new Integer(28);
	public final static Integer CREDITOS_REALIZADOS_SUP_CONTAS = new Integer(29);
	public final static Integer TOTAL_CREDITOS_REALIZADOS = new Integer(30);
	public final static Integer GUIAS_PAGAMENTO = new Integer(31);
	public final static Integer DEBITOS_A_COBRAR = new Integer(32);
	public final static Integer PAGAMENTO_EM_DUPLICIDADE = new Integer(33);
	public final static Integer IMPOSTOS_RETIDOS_NAS_CONTAS_RECEBIDAS = new Integer(34);
	public final static Integer DOCUMENTO_INEXISTENTE = new Integer(35);
	public final static Integer VALOR_NAO_CONFERE = new Integer(36);
	public final static Integer DOCUMENTOS_PAGOS_EM_DUPLICIDADE_EXCESSO = new Integer(37);
	public final static Integer PAGAMENTO_EM_DUPLICIDADE_EXCESSO_NAO_ENCONTRADO = new Integer(38);
	public final static Integer GUIA_DEVOLUCAO_NAO_INFORMADA = new Integer(39);
	public final static Integer TOTAL_DOS_RECEBIMENTOS_DE_CONTA_CLASSIFICADOS = new Integer(40);
	public final static Integer TOTAL_DOS_RECEBIMENTOS_NAO_CLASSIFICADOS = new Integer(41);
	public final static Integer TOTAL_DOS_RECEBIMENTOS_CLASSIFICADOS = new Integer(42);
	public final static Integer TOTAL_DOS_RECEBIMENTOS = new Integer(43);
	public final static Integer TOTAL_DAS_DEVOLUCOES_NAO_CLASSIFICADOS = new Integer(44);
	public final static Integer IMPOSTOS_RETIDOS_NAS_CONTAS_DE_MESES_ANTERIORES_CLASSIFICADOS_NO_MES = new Integer(45);
	public final static Integer TOTAL_DOS_RECEBIMENTOS_DE_MESES_ANTERIORES_CLASSIFICADOS_NO_MES = new Integer(46);
	public final static Integer TOTAL_DAS_DEVOLUCOES_DE_MESES_ANTERIORES_CLASSIFICADAS_NO_MES = new Integer(47);
	public final static Integer TOTAL_DAS_DEVOLUCOES_CLASSIFICADAS = new Integer(48);
	public final static Integer TOTAL_DOS_RECEBIMENTOS_NAO_CLASSIFICADOS_BAIXADOS = new Integer(49);
	public final static Integer TOTAL_DAS_DEVOLUCOES = new Integer(50);
	public final static Integer ARRECADACAO_LIQUIDA = new Integer(51);
	public final static Integer VALORES_CONTABILIZADOS_COMO_PERDAS = new Integer(52);
	public final static Integer VALORES_COBRADOS_INDEVIDAMENTE = new Integer(53);
	public final static Integer DOACOES_COBRADAS_EM_CONTA = new Integer(54);
	public final static Integer DOACOES_RECEBIDAS_EM_CONTA = new Integer(55);

	public final static Integer CREDITOS_A_REALIZAR_POR_COBRANCA_INDEVIDA_CONCELADO = new Integer(56);
	public final static Integer DESCONTOS_INCONDICIONAIS_A_REALIZAR_CANCELADOS = new Integer(57);
	public final static Integer GUIAS_DEVOLUCAO_VALORES_COBRADOS_INDEVIDAMENTE_CANCELADOS = new Integer(58);
	public final static Integer CREDITOS_REALIZADOS_CONTAS_INCLUIDAS = new Integer(59);
	public final static Integer CREDITOS_A_REALIZAR_POR_COBRANCA_INDEVIDA_INCLUIDOS = new Integer(60);
	public final static Integer DESCONTOS_INCONDICIONAIS_INCLUIDOS = new Integer(61);
	public final static Integer GUIAS_DEVOLUCAO_VALORES_COBRADOS_INDEVIDAMENTE_INCLUIDOS = new Integer(62);
	public final static Integer TOTAL_RECEITA_CANCELADA = new Integer(63);
	public final static Integer OUTROS_CREDITOS_A_REALIZAR_INCLUIDOS = new Integer(64);
	public final static Integer OUTROS_CREDITOS_A_REALIZAR_CANCELADOS = new Integer(65);
	public final static Integer TOTAL_VALORES_DEVOLVIDOS_NAS_CONTAS = new Integer(66);
	public final static Integer IMPOSTOS_CANCELADOS_REFATURAMENTO = new Integer(67);
	public final static Integer IMPOSTOS_INCLUIDOS_REFATURAMENTO = new Integer(68);
	public final static Integer OUTROS_CREDITOS_CANCELADOS_POR_REFATURAMENTO = new Integer(69);
	public final static Integer OUTROS_CREDITOS_CONCEDIDOS_POR_REFATURAMENTO = new Integer(70);
	public final static Integer EXCLUSAO_INADIMPLENCIA_RECUPERADA = new Integer(71);
	public final static Integer CANCELAMENTO_POR_PRESCRICAO_DEB_JA_EXC_INADIMPLENCIA = new Integer(72);
	public final static Integer CANCELAMENTO_POR_PRESCRICAO_DEB_NAO_EXC_INADIMPLENCIA = new Integer(73);
	public final static Integer PARCELAMENTOS_COBRADOS_SUP_CANCELAMENTO_POR_PRESCRICAO_DEB_EXC_INADIMPLENCIA = new Integer(74);
	public final static Integer CREDITOS_CONCEDIDOS_SUP_CANCELAMENTO_POR_PRESCRICAO_DEB_JA_EXC_INADIMPLENCIA = new Integer(75);
	public final static Integer IMPOSTOS_RETIDOS_SUP_CANCELAMENTO_POR_PRESCRICAO_DEB_JA_EXC_INADIMPLENCIA = new Integer(76);
	public final static Integer PARCELAMENTOS_COBRADOS_SUP_CANCELAMENTO_POR_PRESCRICAO_DEB_NAO_EXC_INADIMPLENCIA = new Integer(77);
	public final static Integer CREDITOS_CONCEDIDOS_SUP_CANCELAMENTO_POR_PRESCRICAO_DEB_NAO_EXC_INADIMPLENCIA = new Integer(78);
	public final static Integer IMPOSTOS_RETIDOS_SUP_CANCELAMENTO_POR_PRESCRICAO_DEB_NAO_EXC_INADIMPLENCIA = new Integer(79);
	public final static Integer TOTAL_DEBITOS_CANCELADOS_POR_PRESCRICAO = new Integer(80);
	public final static Integer FINANCIAMENTOS_A_COBRAR = new Integer(81);
	public final static Integer PARCELAMENTOS_A_COBRAR_CURTO_PRAZO = new Integer(82);
	public final static Integer PARCELAMENTOS_A_COBRAR_LONGO_PRAZO = new Integer(83);
	public final static Integer CREDITOS_A_REALIZAR = new Integer(84);
	public final static Integer DOCUMENTOS_EMITIDOS = new Integer(85);
	public final static Integer VALOR_REFERENTE_VOLUMES_NAO_FATURADOS = new Integer(86);
	public final static Integer DESCONTOS_PAGAMENTO_A_VISTA = new Integer(87);
	public final static Integer DESCONTOS_CREDITOS = new Integer(88);
	public final static Integer TOTAL_DESCONTOS = new Integer(89);
	public final static Integer BONUS_CONCEDIDOS_PARCELAMENTO = new Integer(90);
	public final static Integer VALORES_DA_CAMPANHA_DA_CRIANCA = new Integer(91);
	public final static Integer PELA_CAMPANHA_DA_CRIANCA = new Integer(92);
	public final static Integer OUTROS = new Integer(93);
	public final static Integer RECEBIMENTOS_NAO_IDENTIFICADOS = new Integer(94);
	public final static Integer AVISO_BANCARIO = new Integer(95);
	public final static Integer DEVOLUCAO_DE_VALORES_EM_CONTA_ATE_31_12_2012 = new Integer(96);
	public final static Integer OUTROS_CREDITOS_A_REALIZAR_INCLUIDOS_ATE_31_12_2012 = new Integer(97);
	public final static Integer OUTROS_CREDITOS_CONCEDIDOS_POR_REFATURAMENTO_ATE_31_12_2012 = new Integer(98);
	
	public final static Integer DEVOLUCAO_VALORES_RECUPERACAO_CREDITO = new Integer(99);
	public final static Integer OUTROS_CREDITOS_CANCELADOS_POR_RECUPERACAO_CREDITO = new Integer(100);
	public final static Integer OUTROS_CREDITOS_CONCEDIDOS_POR_RECUPERACAO_CREDITO = new Integer(101);
	
	public final static Integer OUTROS_CREDITOS_A_REALIZAR_POR_RECUPERACAO_CREDITO_DEB_PRESCRITO = new Integer(102);
	public final static Integer OUTROS_CREDITOS_A_REALIZAR_POR_RECUPERACAO_CREDITO_INCLUIDOS = new Integer(103);
	public final static Integer OUTROS_CREDITOS_A_REALIZAR_POR_RECUPERACAO_CREDITO_CANCELADOS = new Integer(104);
	
	public final static Short INDICADOR_RESUMIDO_ATIVO = new Short("1");
	public final static Short INDICADOR_RESUMIDO_DESATIVO = new Short("2");
	
	private Integer id;
	private String descricao;
	private String descricaoAbreviada;
	private Short indicadorImpressao;
	private Short codigoTipoContabil;
	private Short indicadorTotal;
	private Date ultimaAlteracao;
	private Short indicadorResumido;

	private LancamentoTipo lancamentoTipo;

	public LancamentoTipo() {
	}
	
	public LancamentoTipo(Integer id) {
		this.id =id;
	}

	public LancamentoTipo(String descricao, String descricaoAbreviada,
			Short indicadorImpressao, Short codigoTipoContabil,
			Short indicadorTotal, Date ultimaAlteracao,
			LancamentoTipo lancamentoTipo) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorImpressao = indicadorImpressao;
		this.codigoTipoContabil = codigoTipoContabil;
		this.indicadorTotal = indicadorTotal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.lancamentoTipo = lancamentoTipo;
	}


	/** minimal constructor */
	public LancamentoTipo(
			gcom.financeiro.lancamento.LancamentoTipo lancamentoTipo) {
		this.lancamentoTipo = lancamentoTipo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorTotal() {
		return indicadorTotal;
	}

	public void setIndicadorTotal(Short indicadorTotal) {
		this.indicadorTotal = indicadorTotal;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return this.descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Short getIndicadorImpressao() {
		return this.indicadorImpressao;
	}

	public void setIndicadorImpressao(Short indicadorImpressao) {
		this.indicadorImpressao = indicadorImpressao;
	}

	public Short getCodigoTipoContabil() {
		return this.codigoTipoContabil;
	}

	public void setCodigoTipoContabil(Short codigoTipoContabil) {
		this.codigoTipoContabil = codigoTipoContabil;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.financeiro.lancamento.LancamentoTipo getLancamentoTipo() {
		return this.lancamentoTipo;
	}

	public void setLancamentoTipo(
			gcom.financeiro.lancamento.LancamentoTipo lancamentoTipo) {
		this.lancamentoTipo = lancamentoTipo;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Short getIndicadorResumido() {
		return indicadorResumido;
	}

	public void setIndicadorResumido(Short indicadorResumido) {
		this.indicadorResumido = indicadorResumido;
	}

}
