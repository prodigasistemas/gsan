package gcom.cobranca.bean;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

public class ObterOpcoesDeParcelamentoHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idResolucaoDiretoria; 
	
	private Integer idImovel;
	
	private BigDecimal valorEntradaInformado;
	
	private Integer idLigacaoAguaSituacao;
	
	private Integer idLigacaoEsgotoSituacao;
	
	private Integer idImovelPerfil;
	
	private String inicioIntervaloParcelamento;
	
	private Integer indicadorRestabelecimento;
	
	private Collection colecaoContaValores;
	
	private BigDecimal valorDebitoAtualizado;
	
	private BigDecimal valorTotalMultas;
	
	private BigDecimal valorTotalJurosMora;
	
	private BigDecimal valorTotalAtualizacoesMonetarias;
	
	private Integer numeroReparcelamentoConsecutivos;
	
	private Collection<GuiaPagamento> colecaoGuiaPagamento;
	
	private Usuario usuario;
	
	private BigDecimal valorDebitoACobrarParcelamentoImovel;
	
	private Integer anoMesInicialReferenciaDebito;
	
	private Integer anoMesFinalReferenciaDebito;
	
	private IndicadoresParcelamentoHelper indicadoresParcelamentoHelper;
	
	private BigDecimal valorCreditoARealizarParcelamentoImovel;
	
	//UTILIZADO APENAS VIA EXTRATO DE DÉBITO
	private Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores;
	
	public ObterOpcoesDeParcelamentoHelper(){}
	
	public ObterOpcoesDeParcelamentoHelper(Integer resolucaoDiretoria,
			Integer codigoImovel, BigDecimal valorEntradaInformado, Integer situacaoAguaId, Integer situacaoEsgotoId,
			Integer perfilImovel, String inicioIntervaloParcelamento, Integer indicadorRestabelecimento,
			Collection colecaoContaValoresNegociacao, BigDecimal valorDebitoTotalAtualizado, 
			BigDecimal valorTotalMultas, BigDecimal valorTotalJurosMora, BigDecimal valorTotalAtualizacoesMonetarias,
			Integer numeroReparcelamentoConsecutivos, Collection colecaoGuiaPagamento, Usuario  usuario,
			BigDecimal valorDebitoACobrarParcelamentoImovelBigDecimal, Integer inicioIntervaloParcelamentoFormatado,
			Integer fimIntervaloParcelamentoFormatado, IndicadoresParcelamentoHelper indicadoresParcelamentoHelper,
			BigDecimal valorCreditoARealizarParcelamentoImovel){
		
		this.setIdResolucaoDiretoria(resolucaoDiretoria);
		this.setIdImovel(codigoImovel);
		this.setValorEntradaInformado(valorEntradaInformado);
		this.setIdLigacaoAguaSituacao(situacaoAguaId);
		this.setIdLigacaoEsgotoSituacao(situacaoEsgotoId);
		this.setIdImovelPerfil(perfilImovel);
		this.setInicioIntervaloParcelamento(inicioIntervaloParcelamento);
		this.setIndicadorRestabelecimento(indicadorRestabelecimento);
		this.setColecaoContaValores(colecaoContaValoresNegociacao);
		this.setValorDebitoAtualizado(valorDebitoTotalAtualizado);
		this.setValorTotalMultas(valorTotalMultas);
		this.setValorTotalJurosMora(valorTotalJurosMora);
		this.setValorTotalAtualizacoesMonetarias(valorTotalAtualizacoesMonetarias);
		this.setNumeroReparcelamentoConsecutivos(numeroReparcelamentoConsecutivos);
		this.setColecaoGuiaPagamento(colecaoGuiaPagamento);
		this.setUsuario(usuario);
		this.setValorDebitoACobrarParcelamentoImovel(valorDebitoACobrarParcelamentoImovelBigDecimal);
		this.setAnoMesInicialReferenciaDebito(inicioIntervaloParcelamentoFormatado);
		this.setAnoMesFinalReferenciaDebito(fimIntervaloParcelamentoFormatado);
		this.setIndicadoresParcelamentoHelper(indicadoresParcelamentoHelper);
		this.setValorCreditoARealizarParcelamentoImovel(valorCreditoARealizarParcelamentoImovel);
	}

	public Integer getAnoMesFinalReferenciaDebito() {
		return anoMesFinalReferenciaDebito;
	}

	public void setAnoMesFinalReferenciaDebito(Integer anoMesFinalReferenciaDebito) {
		this.anoMesFinalReferenciaDebito = anoMesFinalReferenciaDebito;
	}

	public Integer getAnoMesInicialReferenciaDebito() {
		return anoMesInicialReferenciaDebito;
	}

	public void setAnoMesInicialReferenciaDebito(
			Integer anoMesInicialReferenciaDebito) {
		this.anoMesInicialReferenciaDebito = anoMesInicialReferenciaDebito;
	}

	public Collection getColecaoContaValores() {
		return colecaoContaValores;
	}

	public void setColecaoContaValores(Collection colecaoContaValores) {
		this.colecaoContaValores = colecaoContaValores;
	}

	public Collection<GuiaPagamento> getColecaoGuiaPagamento() {
		return colecaoGuiaPagamento;
	}

	public void setColecaoGuiaPagamento(
			Collection<GuiaPagamento> colecaoGuiaPagamento) {
		this.colecaoGuiaPagamento = colecaoGuiaPagamento;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdImovelPerfil() {
		return idImovelPerfil;
	}

	public void setIdImovelPerfil(Integer idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	public Integer getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}

	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}

	public Integer getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}

	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}

	public Integer getIdResolucaoDiretoria() {
		return idResolucaoDiretoria;
	}

	public void setIdResolucaoDiretoria(Integer idResolucaoDiretoria) {
		this.idResolucaoDiretoria = idResolucaoDiretoria;
	}

	public IndicadoresParcelamentoHelper getIndicadoresParcelamentoHelper() {
		return indicadoresParcelamentoHelper;
	}

	public void setIndicadoresParcelamentoHelper(
			IndicadoresParcelamentoHelper indicadoresParcelamentoHelper) {
		this.indicadoresParcelamentoHelper = indicadoresParcelamentoHelper;
	}

	public Integer getIndicadorRestabelecimento() {
		return indicadorRestabelecimento;
	}

	public void setIndicadorRestabelecimento(Integer indicadorRestabelecimento) {
		this.indicadorRestabelecimento = indicadorRestabelecimento;
	}

	public String getInicioIntervaloParcelamento() {
		return inicioIntervaloParcelamento;
	}

	public void setInicioIntervaloParcelamento(String inicioIntervaloParcelamento) {
		this.inicioIntervaloParcelamento = inicioIntervaloParcelamento;
	}

	public Integer getNumeroReparcelamentoConsecutivos() {
		return numeroReparcelamentoConsecutivos;
	}

	public void setNumeroReparcelamentoConsecutivos(
			Integer numeroReparcelamentoConsecutivos) {
		this.numeroReparcelamentoConsecutivos = numeroReparcelamentoConsecutivos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getValorDebitoACobrarParcelamentoImovel() {
		return valorDebitoACobrarParcelamentoImovel;
	}

	public void setValorDebitoACobrarParcelamentoImovel(
			BigDecimal valorDebitoACobrarParcelamentoImovel) {
		this.valorDebitoACobrarParcelamentoImovel = valorDebitoACobrarParcelamentoImovel;
	}

	public BigDecimal getValorDebitoAtualizado() {
		return valorDebitoAtualizado;
	}

	public void setValorDebitoAtualizado(BigDecimal valorDebitoAtualizado) {
		this.valorDebitoAtualizado = valorDebitoAtualizado;
	}

	public BigDecimal getValorEntradaInformado() {
		return valorEntradaInformado;
	}

	public void setValorEntradaInformado(BigDecimal valorEntradaInformado) {
		this.valorEntradaInformado = valorEntradaInformado;
	}

	public BigDecimal getValorTotalAtualizacoesMonetarias() {
		return valorTotalAtualizacoesMonetarias;
	}

	public void setValorTotalAtualizacoesMonetarias(
			BigDecimal valorTotalAtualizacoesMonetarias) {
		this.valorTotalAtualizacoesMonetarias = valorTotalAtualizacoesMonetarias;
	}

	public BigDecimal getValorTotalJurosMora() {
		return valorTotalJurosMora;
	}

	public void setValorTotalJurosMora(BigDecimal valorTotalJurosMora) {
		this.valorTotalJurosMora = valorTotalJurosMora;
	}

	public BigDecimal getValorTotalMultas() {
		return valorTotalMultas;
	}

	public void setValorTotalMultas(BigDecimal valorTotalMultas) {
		this.valorTotalMultas = valorTotalMultas;
	}

	public BigDecimal getValorCreditoARealizarParcelamentoImovel() {
		return valorCreditoARealizarParcelamentoImovel;
	}

	public void setValorCreditoARealizarParcelamentoImovel(
			BigDecimal valorCreditoARealizarParcelamentoImovel) {
		this.valorCreditoARealizarParcelamentoImovel = valorCreditoARealizarParcelamentoImovel;
	}

	public Collection<GuiaPagamentoValoresHelper> getColecaoGuiaPagamentoValores() {
		return colecaoGuiaPagamentoValores;
	}

	public void setColecaoGuiaPagamentoValores(
			Collection<GuiaPagamentoValoresHelper> colecaoGuiaPagamentoValores) {
		this.colecaoGuiaPagamentoValores = colecaoGuiaPagamentoValores;
	}
}
