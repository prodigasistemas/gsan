package gcom.faturamento.credito;

import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.faturamento.conta.IConta;
import gcom.financeiro.lancamento.LancamentoItemContabil;

import java.math.BigDecimal;
import java.util.Date;

public interface ICreditoRealizado {

	public Integer getId();
	public Date getCreditoRealizado();
	public Integer getCodigoSetorComercial();
	public Integer getNumeroQuadra();
	public Short getNumeroLote();
	public Short getNumeroSubLote();
	public Integer getAnoMesReferenciaCredito();
	public Integer getAnoMesCobrancaCredito();
	public BigDecimal getValorCredito();
	public Short getNumeroPrestacao();
	public Short getNumeroPrestacaoCredito();
	public Date getUltimaAlteracao();
	public IConta getConta();
	public Quadra getQuadra();
	public Localidade getLocalidade();
	public CreditoTipo getCreditoTipo();
	public LancamentoItemContabil getLancamentoItemContabil();
	public CreditoOrigem getCreditoOrigem();
	public Short getNumeroParcelaBonus();
	public CreditoARealizarGeral getCreditoARealizarGeral();
	public short getNumeroTotalParcelasMenosBonus();

	public void setId(Integer id);
    public void setCreditoRealizado(Date creditoRealizado);
    public void setCodigoSetorComercial(Integer codigoSetorComercial);
    public void setNumeroQuadra(Integer numeroQuadra);
    public void setNumeroLote(Short numeroLote);
    public void setNumeroSubLote(Short numeroSubLote);
    public void setAnoMesReferenciaCredito(Integer anoMesReferenciaCredito);
    public void setAnoMesCobrancaCredito(Integer anoMesCobrancaCredito);
    public void setValorCredito(BigDecimal valorCredito);
    public void setNumeroPrestacao(Short numeroPrestacao);
    public void setNumeroPrestacaoCredito(Short numeroPrestacaoCredito);
    public void setUltimaAlteracao(Date ultimaAlteracao);
    public void setConta(IConta conta);
    public void setQuadra(Quadra quadra);
    public void setLocalidade(Localidade localidade);
    public void setCreditoTipo(CreditoTipo creditoTipo);
    public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil);
    public void setCreditoOrigem(CreditoOrigem creditoOrigem);
    public void setNumeroParcelaBonus(Short numeroParcelaBonus);
    public void setCreditoARealizarGeral(CreditoARealizarGeral creditoARealizarGeral);

}
