package gcom.faturamento.credito;

import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class CreditoTipo extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

	public final static Integer CREDITO_NITRATO = new Integer(0);
	public final static Integer DESCONTO_ACRESCIMOS_IMPONTUALIDADE = new Integer(1);
	public final static Integer DESCONTO_ANTIGUIDADE_DEBITO = new Integer(2);
	public final static Integer DESCONTO_INATIVIDADE_LIGACAO_AGUA = new Integer(3);
	public final static Integer CREDITOS_ANTERIORES_CURTO_PRAZO = new Integer(4);
	public final static Integer DEVOLUCAO_PAGAMENTOS_DUPLICIDADE = new Integer(5);
	public final static Integer DEVOLUCAO_OUTROS_VALORES = new Integer(7);
	public final static Integer DESCONTOS_CONCEDIDOS = new Integer(8);
	public final static Integer DEVOLUCAO_ACRESCIMOS_IMPONTUALIDADE = new Integer(9);
	public final static Integer DESCONTO_SANCOES = new Integer(10);
	public final static Integer DESCONTO_TARIFA_SOCIAL = new Integer(11);
	public final static Integer PAGAMENTO_PARCIAL = new Integer(856);
	public final static Integer PAGAMENTO_NAO_CONFERE = new Integer(860);
	public final static Integer DESCONTO_FAIXA_REFERENCIA_CONTA = new Integer(863);
	public final static Integer CREDITOS_ANTERIORES_LONGO_PRAZO = new Integer(864);
	public final static Integer CREDITO_BOLSA_AGUA = new Integer(871);
	public final static Integer BAIXA_FATURAMENTO_INFERIOR_MINIMO = new Integer(872);
	
	private Integer id;
	private String descricao;
	private String descricaoAbreviada;
	private Integer indicadorUso;
	private Date ultimaAlteracao;
	private BigDecimal valorLimite;
	private Short indicadorGeracaoAutomatica;
	private LancamentoItemContabil lancamentoItemContabil;
    private Integer codigoConstante;

    public CreditoTipo(String descricao, String descricaoAbreviada,
			Integer indicadorUso, Date ultimaAlteracao, BigDecimal valorLimite,
			Short indicadorGeracaoAutomatica,
			LancamentoItemContabil lancamentoItemContabil) {
		this.descricao = descricao;
		this.descricaoAbreviada = descricaoAbreviada;
		this.indicadorUso = indicadorUso;
		this.ultimaAlteracao = ultimaAlteracao;
		this.valorLimite = valorLimite;
		this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public CreditoTipo() {
	}
	
	public CreditoTipo(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public CreditoTipo(Integer id) {
		this.id = id;
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

	public Integer getIndicadorUso() {
		return this.indicadorUso;
	}

	public void setIndicadorUso(Integer indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public BigDecimal getValorLimite() {
		return this.valorLimite;
	}

	public void setValorLimite(BigDecimal valorLimite) {
		this.valorLimite = valorLimite;
	}

	public Short getIndicadorGeracaoAutomatica() {
		return this.indicadorGeracaoAutomatica;
	}

	public void setIndicadorGeracaoAutomatica(Short indicadorGeracaoAutomatica) {
		this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public LancamentoItemContabil getLancamentoItemContabil() {
		return lancamentoItemContabil;
	}

	public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil) {
		this.lancamentoItemContabil = lancamentoItemContabil;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();

		filtroCreditoTipo.adicionarParametro(new ParametroSimples(FiltroCreditoTipo.ID, this.getId()));
		filtroCreditoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");

		return filtroCreditoTipo;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}

    public Integer getCodigoConstante() {
        return codigoConstante;
    }

    public void setCodigoConstante(Integer codigoConstante) {
        this.codigoConstante = codigoConstante;
    }

	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		
		if (obj instanceof CreditoTipo) {
			CreditoTipo castOther = (CreditoTipo) obj;
			retorno = this.getId().compareTo(castOther.getId())==0;
		}
		return retorno;
	}

	@Override
	public int hashCode() {
		return this.getId();
	}
}
