package gcom.financeiro.lancamento;

import java.math.BigDecimal;

public class LancamentoContabilItemTO {

	private Integer idLancamentoContabil;

	private Integer idLocalidade;

	private BigDecimal valor;

	private Integer idFormaArrecadacao;

	public LancamentoContabilItemTO() {
		super();
	}

	public LancamentoContabilItemTO(Integer idLancamentoContabil, Integer idLocalidade, BigDecimal valor, Integer idFormaArrecadacao) {
		super();
		this.idLancamentoContabil = idLancamentoContabil;
		this.idLocalidade = idLocalidade;
		this.valor = valor;
		this.idFormaArrecadacao = idFormaArrecadacao;
	}

	public Integer getIdLancamentoContabil() {
		return idLancamentoContabil;
	}

	public void setIdLancamentoContabil(Integer idLancamentoContabil) {
		this.idLancamentoContabil = idLancamentoContabil;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}

	public void setIdFormaArrecadacao(Integer idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}
}
