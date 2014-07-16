package gcom.faturamento.debito;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cobranca.ParcelamentoGrupo;
import gcom.faturamento.conta.IConta;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;

import java.math.BigDecimal;
import java.util.Date;

public interface IDebitoCobrado {

	public Integer getId();
	public void setId(Integer id);

	public Date getDebitoCobrado();
	public void setDebitoCobrado(Date debitoCobrado);

	public Integer getCodigoSetorComercial();
	public void setCodigoSetorComercial(Integer codigoSetorComercial);

	public Integer getNumeroQuadra();
	public void setNumeroQuadra(Integer numeroQuadra);

	public Short getNumeroLote();
	public void setNumeroLote(Short numeroLote);

	public Short getNumeroSubLote();
	public void setNumeroSubLote(Short numeroSubLote);

	public Integer getAnoMesReferenciaDebito();
	public void setAnoMesReferenciaDebito(Integer anoMesReferenciaDebito);

	public Integer getAnoMesCobrancaDebito();
	public void setAnoMesCobrancaDebito(Integer anoMesCobrancaDebito);

	public BigDecimal getValorPrestacao();
	public void setValorPrestacao(BigDecimal valorPrestacao);

	public Short getNumeroPrestacao();
	public void setNumeroPrestacao(Short numeroPrestacao);

	public Short getNumeroPrestacaoDebito();
	public void setNumeroPrestacaoDebito(Short numeroPrestacaoDebito);

	public Date getUltimaAlteracao();
	public void setUltimaAlteracao(Date ultimaAlteracao);

	public LancamentoItemContabil getLancamentoItemContabil();
	public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil);

	public IConta getConta();
	public void setConta(IConta conta);

	public FinanciamentoTipo getFinanciamentoTipo();
	public void setFinanciamentoTipo(FinanciamentoTipo financiamentoTipo);

	public Quadra getQuadra();
	public void setQuadra(Quadra quadra);

	public Localidade getLocalidade();
	public void setLocalidade(Localidade localidade);

	public DebitoTipo getDebitoTipo();
	public void setDebitoTipo(DebitoTipo debitoTipo);

	public ParcelamentoGrupo getParcelamentoGrupo();
	public void setParcelamentoGrupo(ParcelamentoGrupo parcelamentoGrupo);

    public Short getNumeroParcelaBonus();
    public void setNumeroParcelaBonus(Short numeroParcelaBonus);

    public short getNumeroTotalParcelasMenosBonus();
	
    public DebitoACobrarGeral getDebitoACobrarGeral();
	public void setDebitoACobrarGeral(DebitoACobrarGeral debitoACobrarGeral);

}
