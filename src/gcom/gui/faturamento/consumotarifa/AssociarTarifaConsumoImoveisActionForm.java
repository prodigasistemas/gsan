package gcom.gui.faturamento.consumotarifa;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 07/12/2006
 */
public class AssociarTarifaConsumoImoveisActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String idImovel;

	private String matricula;

	private String localidadeOrigemID;

	private String localidadeDestinoID;

	private String nomeLocalidadeOrigem;

	private String nomeLocalidadeDestino;

	private String setorComercialOrigemCD;

	private String setorComercialDestinoCD;

	private String setorComercialOrigemID;

	private String setorComercialDestinoID;

	private String nomeSetorComercialOrigem;

	private String nomeSetorComercialDestino;

	private String quadraOrigemNM;

	private String quadraDestinoNM;

	private String quadraMensagemOrigem;

	private String quadraMensagemDestino;

	private String quadraOrigemID;

	private String quadraDestinoID;

	private String loteOrigem;

	private String loteDestino;

	private String subloteOrigem;

	private String subloteDestino;

	private String quantidadeImoveisAtualizados;

	private String tipoSituacaoEspecialFaturamento;

	private String motivoSituacaoEspecialFaturamento;

	private String mesAnoReferenciaFaturamentoInicial;

	private String mesAnoReferenciaFaturamentoFinal;

	private String inscricaoTipo;

	private String idFaturamentoSituacaoTipo;

	private String idFaturamentoSituacaoMotivo;

	private String liberarBotoes;

	private String endereco;

	private String inscricaoImovel;

	private String quantidadeDeImoveis;

	private String tarifaAnterior;

	private String tarifaAtual;
	
	private String tarifaAnteriorHidden;

	/**
	 * @return Retorna o campo tarifaAnteriorHidden.
	 */
	public String getTarifaAnteriorHidden() {
		return tarifaAnteriorHidden;
	}

	/**
	 * @param tarifaAnteriorHidden O tarifaAnteriorHidden a ser setado.
	 */
	public void setTarifaAnteriorHidden(String tarifaAnteriorHidden) {
		this.tarifaAnteriorHidden = tarifaAnteriorHidden;
	}

	/**
	 * @return Retorna o campo tarifaAnterior.
	 */
	public String getTarifaAnterior() {
		return tarifaAnterior;
	}

	/**
	 * @param tarifaAnterior
	 *            O tarifaAnterior a ser setado.
	 */
	public void setTarifaAnterior(String tarifaAnterior) {
		this.tarifaAnterior = tarifaAnterior;
	}

	/**
	 * @return Retorna o campo tarifaAtual.
	 */
	public String getTarifaAtual() {
		return tarifaAtual;
	}

	/**
	 * @param tarifaAtual
	 *            O tarifaAtual a ser setado.
	 */
	public void setTarifaAtual(String tarifaAtual) {
		this.tarifaAtual = tarifaAtual;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo idFaturamentoSituacaoMotivo.
	 */
	public String getIdFaturamentoSituacaoMotivo() {
		return idFaturamentoSituacaoMotivo;
	}

	/**
	 * @param idFaturamentoSituacaoMotivo
	 *            O idFaturamentoSituacaoMotivo a ser setado.
	 */
	public void setIdFaturamentoSituacaoMotivo(
			String idFaturamentoSituacaoMotivo) {
		this.idFaturamentoSituacaoMotivo = idFaturamentoSituacaoMotivo;
	}

	/**
	 * @return Retorna o campo idFaturamentoSituacaoTipo.
	 */
	public String getIdFaturamentoSituacaoTipo() {
		return idFaturamentoSituacaoTipo;
	}

	/**
	 * @param idFaturamentoSituacaoTipo
	 *            O idFaturamentoSituacaoTipo a ser setado.
	 */
	public void setIdFaturamentoSituacaoTipo(String idFaturamentoSituacaoTipo) {
		this.idFaturamentoSituacaoTipo = idFaturamentoSituacaoTipo;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel
	 *            O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel
	 *            O inscricaoImovel a ser setado.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Retorna o campo inscricaoTipo.
	 */
	public String getInscricaoTipo() {
		return inscricaoTipo;
	}

	/**
	 * @param inscricaoTipo
	 *            O inscricaoTipo a ser setado.
	 */
	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}

	/**
	 * @return Retorna o campo liberarBotoes.
	 */
	public String getLiberarBotoes() {
		return liberarBotoes;
	}

	/**
	 * @param liberarBotoes
	 *            O liberarBotoes a ser setado.
	 */
	public void setLiberarBotoes(String liberarBotoes) {
		this.liberarBotoes = liberarBotoes;
	}

	/**
	 * @return Retorna o campo localidadeDestinoID.
	 */
	public String getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	/**
	 * @param localidadeDestinoID
	 *            O localidadeDestinoID a ser setado.
	 */
	public void setLocalidadeDestinoID(String localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	/**
	 * @return Retorna o campo localidadeOrigemID.
	 */
	public String getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	/**
	 * @param localidadeOrigemID
	 *            O localidadeOrigemID a ser setado.
	 */
	public void setLocalidadeOrigemID(String localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	/**
	 * @return Retorna o campo loteDestino.
	 */
	public String getLoteDestino() {
		return loteDestino;
	}

	/**
	 * @param loteDestino
	 *            O loteDestino a ser setado.
	 */
	public void setLoteDestino(String loteDestino) {
		this.loteDestino = loteDestino;
	}

	/**
	 * @return Retorna o campo loteOrigem.
	 */
	public String getLoteOrigem() {
		return loteOrigem;
	}

	/**
	 * @param loteOrigem
	 *            O loteOrigem a ser setado.
	 */
	public void setLoteOrigem(String loteOrigem) {
		this.loteOrigem = loteOrigem;
	}

	/**
	 * @return Retorna o campo matricula.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula
	 *            O matricula a ser setado.
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return Retorna o campo mesAnoReferenciaFaturamentoFinal.
	 */
	public String getMesAnoReferenciaFaturamentoFinal() {
		return mesAnoReferenciaFaturamentoFinal;
	}

	/**
	 * @param mesAnoReferenciaFaturamentoFinal
	 *            O mesAnoReferenciaFaturamentoFinal a ser setado.
	 */
	public void setMesAnoReferenciaFaturamentoFinal(
			String mesAnoReferenciaFaturamentoFinal) {
		this.mesAnoReferenciaFaturamentoFinal = mesAnoReferenciaFaturamentoFinal;
	}

	/**
	 * @return Retorna o campo mesAnoReferenciaFaturamentoInicial.
	 */
	public String getMesAnoReferenciaFaturamentoInicial() {
		return mesAnoReferenciaFaturamentoInicial;
	}

	/**
	 * @param mesAnoReferenciaFaturamentoInicial
	 *            O mesAnoReferenciaFaturamentoInicial a ser setado.
	 */
	public void setMesAnoReferenciaFaturamentoInicial(
			String mesAnoReferenciaFaturamentoInicial) {
		this.mesAnoReferenciaFaturamentoInicial = mesAnoReferenciaFaturamentoInicial;
	}

	/**
	 * @return Retorna o campo motivoSituacaoEspecialFaturamento.
	 */
	public String getMotivoSituacaoEspecialFaturamento() {
		return motivoSituacaoEspecialFaturamento;
	}

	/**
	 * @param motivoSituacaoEspecialFaturamento
	 *            O motivoSituacaoEspecialFaturamento a ser setado.
	 */
	public void setMotivoSituacaoEspecialFaturamento(
			String motivoSituacaoEspecialFaturamento) {
		this.motivoSituacaoEspecialFaturamento = motivoSituacaoEspecialFaturamento;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeDestino.
	 */
	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	/**
	 * @param nomeLocalidadeDestino
	 *            O nomeLocalidadeDestino a ser setado.
	 */
	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	/**
	 * @return Retorna o campo nomeLocalidadeOrigem.
	 */
	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	/**
	 * @param nomeLocalidadeOrigem
	 *            O nomeLocalidadeOrigem a ser setado.
	 */
	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	/**
	 * @return Retorna o campo nomeSetorComercialDestino.
	 */
	public String getNomeSetorComercialDestino() {
		return nomeSetorComercialDestino;
	}

	/**
	 * @param nomeSetorComercialDestino
	 *            O nomeSetorComercialDestino a ser setado.
	 */
	public void setNomeSetorComercialDestino(String nomeSetorComercialDestino) {
		this.nomeSetorComercialDestino = nomeSetorComercialDestino;
	}

	/**
	 * @return Retorna o campo nomeSetorComercialOrigem.
	 */
	public String getNomeSetorComercialOrigem() {
		return nomeSetorComercialOrigem;
	}

	/**
	 * @param nomeSetorComercialOrigem
	 *            O nomeSetorComercialOrigem a ser setado.
	 */
	public void setNomeSetorComercialOrigem(String nomeSetorComercialOrigem) {
		this.nomeSetorComercialOrigem = nomeSetorComercialOrigem;
	}

	/**
	 * @return Retorna o campo quadraDestinoID.
	 */
	public String getQuadraDestinoID() {
		return quadraDestinoID;
	}

	/**
	 * @param quadraDestinoID
	 *            O quadraDestinoID a ser setado.
	 */
	public void setQuadraDestinoID(String quadraDestinoID) {
		this.quadraDestinoID = quadraDestinoID;
	}

	/**
	 * @return Retorna o campo quadraDestinoNM.
	 */
	public String getQuadraDestinoNM() {
		return quadraDestinoNM;
	}

	/**
	 * @param quadraDestinoNM
	 *            O quadraDestinoNM a ser setado.
	 */
	public void setQuadraDestinoNM(String quadraDestinoNM) {
		this.quadraDestinoNM = quadraDestinoNM;
	}

	/**
	 * @return Retorna o campo quadraMensagemDestino.
	 */
	public String getQuadraMensagemDestino() {
		return quadraMensagemDestino;
	}

	/**
	 * @param quadraMensagemDestino
	 *            O quadraMensagemDestino a ser setado.
	 */
	public void setQuadraMensagemDestino(String quadraMensagemDestino) {
		this.quadraMensagemDestino = quadraMensagemDestino;
	}

	/**
	 * @return Retorna o campo quadraMensagemOrigem.
	 */
	public String getQuadraMensagemOrigem() {
		return quadraMensagemOrigem;
	}

	/**
	 * @param quadraMensagemOrigem
	 *            O quadraMensagemOrigem a ser setado.
	 */
	public void setQuadraMensagemOrigem(String quadraMensagemOrigem) {
		this.quadraMensagemOrigem = quadraMensagemOrigem;
	}

	/**
	 * @return Retorna o campo quadraOrigemID.
	 */
	public String getQuadraOrigemID() {
		return quadraOrigemID;
	}

	/**
	 * @param quadraOrigemID
	 *            O quadraOrigemID a ser setado.
	 */
	public void setQuadraOrigemID(String quadraOrigemID) {
		this.quadraOrigemID = quadraOrigemID;
	}

	/**
	 * @return Retorna o campo quadraOrigemNM.
	 */
	public String getQuadraOrigemNM() {
		return quadraOrigemNM;
	}

	/**
	 * @param quadraOrigemNM
	 *            O quadraOrigemNM a ser setado.
	 */
	public void setQuadraOrigemNM(String quadraOrigemNM) {
		this.quadraOrigemNM = quadraOrigemNM;
	}

	/**
	 * @return Retorna o campo quantidadeDeImoveis.
	 */
	public String getQuantidadeDeImoveis() {
		return quantidadeDeImoveis;
	}

	/**
	 * @param quantidadeDeImoveis
	 *            O quantidadeDeImoveis a ser setado.
	 */
	public void setQuantidadeDeImoveis(String quantidadeDeImoveis) {
		this.quantidadeDeImoveis = quantidadeDeImoveis;
	}

	/**
	 * @return Retorna o campo quantidadeImoveisAtualizados.
	 */
	public String getQuantidadeImoveisAtualizados() {
		return quantidadeImoveisAtualizados;
	}

	/**
	 * @param quantidadeImoveisAtualizados
	 *            O quantidadeImoveisAtualizados a ser setado.
	 */
	public void setQuantidadeImoveisAtualizados(
			String quantidadeImoveisAtualizados) {
		this.quantidadeImoveisAtualizados = quantidadeImoveisAtualizados;
	}

	/**
	 * @return Retorna o campo setorComercialDestinoCD.
	 */
	public String getSetorComercialDestinoCD() {
		return setorComercialDestinoCD;
	}

	/**
	 * @param setorComercialDestinoCD
	 *            O setorComercialDestinoCD a ser setado.
	 */
	public void setSetorComercialDestinoCD(String setorComercialDestinoCD) {
		this.setorComercialDestinoCD = setorComercialDestinoCD;
	}

	/**
	 * @return Retorna o campo setorComercialDestinoID.
	 */
	public String getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}

	/**
	 * @param setorComercialDestinoID
	 *            O setorComercialDestinoID a ser setado.
	 */
	public void setSetorComercialDestinoID(String setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}

	/**
	 * @return Retorna o campo setorComercialOrigemCD.
	 */
	public String getSetorComercialOrigemCD() {
		return setorComercialOrigemCD;
	}

	/**
	 * @param setorComercialOrigemCD
	 *            O setorComercialOrigemCD a ser setado.
	 */
	public void setSetorComercialOrigemCD(String setorComercialOrigemCD) {
		this.setorComercialOrigemCD = setorComercialOrigemCD;
	}

	/**
	 * @return Retorna o campo setorComercialOrigemID.
	 */
	public String getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}

	/**
	 * @param setorComercialOrigemID
	 *            O setorComercialOrigemID a ser setado.
	 */
	public void setSetorComercialOrigemID(String setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}

	/**
	 * @return Retorna o campo subloteDestino.
	 */
	public String getSubloteDestino() {
		return subloteDestino;
	}

	/**
	 * @param subloteDestino
	 *            O subloteDestino a ser setado.
	 */
	public void setSubloteDestino(String subloteDestino) {
		this.subloteDestino = subloteDestino;
	}

	/**
	 * @return Retorna o campo subloteOrigem.
	 */
	public String getSubloteOrigem() {
		return subloteOrigem;
	}

	/**
	 * @param subloteOrigem
	 *            O subloteOrigem a ser setado.
	 */
	public void setSubloteOrigem(String subloteOrigem) {
		this.subloteOrigem = subloteOrigem;
	}

	/**
	 * @return Retorna o campo tipoSituacaoEspecialFaturamento.
	 */
	public String getTipoSituacaoEspecialFaturamento() {
		return tipoSituacaoEspecialFaturamento;
	}

	/**
	 * @param tipoSituacaoEspecialFaturamento
	 *            O tipoSituacaoEspecialFaturamento a ser setado.
	 */
	public void setTipoSituacaoEspecialFaturamento(
			String tipoSituacaoEspecialFaturamento) {
		this.tipoSituacaoEspecialFaturamento = tipoSituacaoEspecialFaturamento;
	}
}
