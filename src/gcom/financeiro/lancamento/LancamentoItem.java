package gcom.financeiro.lancamento;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class LancamentoItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final Integer AGUA = new Integer(1);
	public static final Integer ESGOTO = new Integer(2);
	public static final Integer GRUPO_CONTABIL = new Integer(3);
	public static final Integer RECEITA_BRUTA = new Integer(4);
	public static final Integer JUROS = new Integer(5);
	public static final Integer RECEITA_LIQUIDA = new Integer(6);
	public static final Integer FINANCIAMENTOS_TRANSFERIDOS_CURTO_PRAZO = new Integer(7);
	public static final Integer DOCUMENTOS_EMITIDOS = new Integer(8);
	public static final Integer FINANCIAMENTOS_A_COBRAR_CURTO_PRAZO = new Integer(9);
	public static final Integer FINANCIAMENTOS_A_COBRAR_LONGO_PRAZO = new Integer(10);
	public static final Integer PARCELAMENTOS_A_COBRAR_CURTO_PRAZO = new Integer(11);
	public static final Integer PARCELAMENTOS_A_COBRAR_LONGO_PRAZO = new Integer(12);
	public static final Integer JUROS_COBRADOS = new Integer(13);
	public static final Integer JUROS_CANCELADOS = new Integer(14);
	public static final Integer DEBITOS_ANTERIORES_PARA_RECOBRANCA = new Integer(15);
	public static final Integer PARCELAMENTOS_TRANSFERIDOS_PARA_CURTO_PRAZO = new Integer(16);
	public static final Integer CONTAS_PAGA_EM_DUPLICIDADE_EXCESSO = new Integer(17);
	public static final Integer TOTAL_COBRADO_CONTAS = new Integer(18);
	public static final Integer IMPOSTO_RENDA = new Integer(19);
	public static final Integer COFINS = new Integer(20);
	public static final Integer CSLL = new Integer(21);
	public static final Integer PIS_PASEP = new Integer(22);
	public static final Integer FINANCIAMENTOS_COBRADOS = new Integer(23);
	public static final Integer DOCUMENTOS_PAGOS_EM_DUPLICIDADE_EXCESSO = new Integer(24);
	public static final Integer VALORES_COBRADOS_INDEVIDAMENTE = new Integer(25);
	public static final Integer DESCONTOS_CONCEDIDOS = new Integer(26);
	public static final Integer TOTAL_CREDITOS_REALIZADOS = new Integer(27);
	public static final Integer ENTRADAS_PARCELAMENTO = new Integer(28);
	public static final Integer VALOR_NAO_CONFERE = new Integer(29);
	public static final Integer PAGAMENTO_EM_DUPLICIDADE_EXCESSO_NAO_ENCONTRADO = new Integer(30);
	public static final Integer GUIA_DEVOLUCAO_NAO_INFORMADA = new Integer(31);
	public static final Integer PAGAMENTO_EM_DUPLICIDADE = new Integer(32);
	public static final Integer DOCUMENTO_INEXISTENTE = new Integer(33);
	public final static Integer TOTAL_DOS_RECEBIMENTOS_DE_CONTA_CLASSIFICADOS = new Integer(34);
	public final static Integer TOTAL_DOS_RECEBIMENTOS_NAO_CLASSIFICADOS = new Integer(35);
	public final static Integer TOTAL_DOS_RECEBIMENTOS_CLASSIFICADOS = new Integer(36);
	public final static Integer TOTAL_DOS_RECEBIMENTOS = new Integer(37);
	public final static Integer TOTAL_DAS_DEVOLUCOES_NAO_CLASSIFICADOS = new Integer(38);
	public final static Integer TOTAL_DOS_RECEBIMENTOS_DE_MESES_ANTERIORES_CLASSIFICADOS_NO_MES = new Integer(39);
	public final static Integer TOTAL_DAS_DEVOLUCOES_DE_MESES_ANTERIORES_CLASSIFICADAS_NO_MES = new Integer(40);
	public final static Integer TOTAL_DAS_DEVOLUCOES_CLASSIFICADAS = new Integer(41);
	public final static Integer TOTAL_DOS_RECEBIMENTOS_NAO_CLASSIFICADOS_BAIXADOS = new Integer(42);
	public final static Integer TOTAL_DAS_DEVOLUCOES = new Integer(43);
	public final static Integer ARRECADACAO_LIQUIDA = new Integer(44);
	public final static Integer VALORES_CONTABILIZADOS_COMO_PERDAS = new Integer(45);
	public static final Integer DESCONTOS_CONDICIONAIS = new Integer(46);
	public static final Integer DESCONTOS_INCONDICIONAIS = new Integer(47);
	public static final Integer AJUSTES_PARA_ZERAR_CONTA = new Integer(48);
	public static final Integer CREDITOS_PARA_COBRANCA_INDEVIDA_CANCELADOS = new Integer(49);
	public static final Integer DESCONTOS_INCONDICIONAIS_CANCELADOS = new Integer(50);
	public static final Integer CREDITOS_PARA_COBRANCA_INDEVIDA_INCLUIDOS = new Integer(51);
	public static final Integer DESCONTOS_INCONDICIONAIS_INCLUIDOS = new Integer(52);
	public static final Integer TOTAL_RECEITA_CANCELADA = new Integer(53);
	public static final Integer DESCONTOS_CONCEDIDOS_NO_PARCELAMENTO = new Integer(56);
	public final static Integer TOTAL_VALORES_DEVOLVIDOS_NAS_CONTAS = new Integer(57);
	public static final Integer POR_REFATURAMENTO = new Integer(58);
	public static final Integer POR_PARCELAMENTO = new Integer(59);
	public static final Integer CREDITOS_PARA_COBRANCA_INDEVIDA = new Integer(60);
	public final static Integer TOTAL_DEBITOS_CANCELADOS_POR_PRESCRICAO = new Integer(61);
	public static final Integer PARCELAMENTOS_COBRADOS = new Integer(62);
	public static final Integer GUIAS_PAGAMENTO = new Integer(63);
	public static final Integer IMPOSTOS_DEDUZIDOS = new Integer(64);
	public final static Integer DESCONTOS_PAGAMENTO_A_VISTA = new Integer(65);	
	public final static Integer DESCONTOS_CREDITOS = new Integer(66);
	public final static Integer TOTAL_DESCONTOS = new Integer(67);
	public final static Integer BONUS_CONCEDIDOS_PARCELAMENTO = new Integer(68);
	public final static Integer VALORES_DA_CAMPANHA_DA_CRIANCA = new Integer(69);
	public final static Integer PELA_CAMPANHA_DA_CRIANCA = new Integer(70);
	public final static Integer OUTROS = new Integer(71);
	public final static Integer VALOR_CAMPANHA_DA_CRIANCA_COM_DIREITO_AO_DESCONTO = new Integer(72);
	public final static Integer TOTAL_POR_PAGAMENTO_A_VISTA = new Integer(73);
	public final static Integer RECEBIMENTOS_NAO_IDENTIFICADOS = new Integer(74);
	public static final Integer INDIRETA = new Integer(75);
	public static final Integer TOTAL = new Integer(76);
	public final static Integer DOCUMENTO_INEXISTENTE_DEBITO_PRESCRITO = new Integer(78);
	public final static Integer DOCUMENTO_INEXISTENTE_CONTA_PARCELADA = new Integer(79);
	public final static Integer DOCUMENTO_INEXISTENTE_CONTA_CANCELADA = new Integer(80);
	public final static Integer DOCUMENTO_INEXISTENTE_ERRO_PROCESSAMENTO = new Integer(81);
	public final static Integer CONTAS_PAGAS_EM_EXCESSO = new Integer(82);
	public final static Integer RECUPERACAO_CREDITO_CONTA_CANCELADA = new Integer(83);
	public final static Integer RECUPERACAO_CREDITO_CONTA_PARCELADA = new Integer(84);
	public final static Integer RECUPERACAO_CREDITO_DUPLICIDADE = new Integer(85);
	public final static Integer RECUPERACAO_CREDITO_CANCELADO = new Integer(86);
	public final static Integer BONUS_SOCIAL = new Integer(87);
	public final static Integer DESCONTOS_CONCEDIDOS_PARCELAMENTO_FAIXA_CONTA = new Integer(88);
	public final static Integer DESCONTOS_CONCEDIDOS_PARCELAMENTO_CREDITOS_ANTERIORES_CURTO_PRAZO = new Integer(123);
	public final static Integer DESCONTOS_CONCEDIDOS_PARCELAMENTO_CREDITOS_ANTERIORES_LONGO_PRAZO = new Integer(128);
	
	public final static Integer DEVOLUCAO_JUROS_PARCELAMENTO = new Integer(124);
	public final static Integer DEVOLUCAO_TARIFA_AGUA = new Integer(125);
	public final static Integer DEVOLUCAO_TARIFA_ESGOTO = new Integer(126);
	public final static Integer SERVICOS_INDIRETOS_PAGOS_INDEVIDAMENTE = new Integer(127);

    private Integer id;
    private String descricao;
    private String descricaoAbreviada;
    private Short indicadorItemContabil;
    private Date ultimaAlteracao;
    
    public LancamentoItem() {
    }

    public LancamentoItem(Integer id) {
    	this.id = id;
    }
    
    public LancamentoItem(String descricao, String descricaoAbreviada, Short indicadorItemContabil, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorItemContabil = indicadorItemContabil;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Short getIndicadorItemContabil() {
        return this.indicadorItemContabil;
    }

    public void setIndicadorItemContabil(Short indicadorItemContabil) {
        this.indicadorItemContabil = indicadorItemContabil;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

}
