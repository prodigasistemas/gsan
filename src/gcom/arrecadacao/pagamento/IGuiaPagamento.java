package gcom.arrecadacao.pagamento;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.parcelamento.Parcelamento;
import gcom.faturamento.GuiaPagamentoGeral;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.DebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.seguranca.acesso.usuario.Usuario;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public interface IGuiaPagamento {

	public Usuario getUsuario();
	public void setUsuario(Usuario usuario);

	public String toString();

	public Integer getAnoMesReferenciaContabil();
	public void setAnoMesReferenciaContabil(Integer anoMesReferenciaContabil);

	public Cliente getCliente();
	public void setCliente(Cliente cliente);

	public Date getDataEmissao();
	public void setDataEmissao(Date dataEmissao);

	public Date getDataVencimento();
	public void setDataVencimento(Date dataVencimento);

	/*
	public DebitoCreditoSituacao getDebitoCreditoSituacaoByDcstIdanterior();
	public void setDebitoCreditoSituacaoByDcstIdanterior(DebitoCreditoSituacao debitoCreditoSituacaoByDcstIdanterior);

	public DebitoCreditoSituacao getDebitoCreditoSituacaoByDcstIdatual();
	public void setDebitoCreditoSituacaoByDcstIdatual(DebitoCreditoSituacao debitoCreditoSituacaoByDcstIdatual);
	 */
	
	public DebitoTipo getDebitoTipo();
	public void setDebitoTipo(DebitoTipo debitoTipo);

	public DocumentoTipo getDocumentoTipo();
	public void setDocumentoTipo(DocumentoTipo documentoTipo);

	public FinanciamentoTipo getFinanciamentoTipo();
	public void setFinanciamentoTipo(FinanciamentoTipo financiamentoTipo);

	public GuiaPagamentoGeral getGuiaPagamentoGeral();
	public void setGuiaPagamentoGeral(GuiaPagamentoGeral guiaPagamentoGeral);

	public Integer getId();
	public void setId(Integer id);

	public Imovel getImovel();
	public void setImovel(Imovel imovel);

	public LancamentoItemContabil getLancamentoItemContabil();
	public void setLancamentoItemContabil(LancamentoItemContabil lancamentoItemContabil);

	public Localidade getLocalidade();
	public void setLocalidade(Localidade localidade);

	public OrdemServico getOrdemServico();
	public void setOrdemServico(OrdemServico ordemServico);

	public Parcelamento getParcelamento();
	public void setParcelamento(Parcelamento parcelamento);

	public RegistroAtendimento getRegistroAtendimento();
	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento);

	public Date getUltimaAlteracao();
	public void setUltimaAlteracao(Date ultimaAlteracao);

	public BigDecimal getValorDebito();
	public void setValorDebito(BigDecimal valorDebito);

	public GuiaPagamentoGeral getOrigem();
	public void setOrigem(GuiaPagamentoGeral origem);

	public Short getNumeroPrestacaoDebito();
	public void setNumeroPrestacaoDebito(Short numeroPrestacaoDebito);

	public Short getNumeroPrestacaoTotal();
	public void setNumeroPrestacaoTotal(Short numeroPrestacaoTotal);

	public String getPrestacaoFormatada();
	
	public Short getIndicadorEmitirObservacao();
	public void setIndicadorEmitirObservacao(Short indicadorEmitirObservacao);
	
	public String getObservacao();
	public void setObservacao(String observacao);

	public String getNumeroGuiaFatura();
	public void setNumeroGuiaFatura(String numeroGuiaFatura);
	
}
