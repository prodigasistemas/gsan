package gcom.faturamento;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LancamentoAgenciaReguladora implements Serializable {

	private static final long serialVersionUID = 380968030324872884L;
	
	public static final int AGUA_ESGOTO = 1;
	public static final int INCLUSOES_POR_REFATURAMENTO = 2;
	public static final int CANCELAMENTOS_POR_REFATURAMENTO = 3;
	
	private Integer id;
	private Localidade localidade;
	private SetorComercial setorComercial;
	private int anoMesReferencia;
	private BigDecimal valorAgua;
	private BigDecimal valorEsgoto;
	private Integer tipoLancamento;
	private Date ultimaAlteracao;
	
	public LancamentoAgenciaReguladora() { }
	
	public LancamentoAgenciaReguladora(Localidade localidade, SetorComercial setorComercial, int anoMesReferencia, BigDecimal valorAgua,
			BigDecimal valorEsgoto,Integer tipoLancamento) {
		super();
		this.localidade = localidade;
		this.setorComercial = setorComercial;
		this.anoMesReferencia = anoMesReferencia;
		this.valorAgua = valorAgua;
		this.valorEsgoto = valorEsgoto;
		this.tipoLancamento = tipoLancamento;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Localidade getLocalidade() {
		return localidade;
	}
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}
	public SetorComercial getSetorComercial() {
		return setorComercial;
	}
	public void setSetorComercial(SetorComercial setorComercial) {
		this.setorComercial = setorComercial;
	}
	public int getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(int anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}
	public BigDecimal getValorAgua() {
		return valorAgua;
	}
	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}
	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}
	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public Integer getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(Integer tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	

}
