package gcom.gui.faturamento;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SituacaoEspecialFaturamentoActionForm extends ActionForm {
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

	private String quantidadeImoveisCOMSituacaoEspecialFaturamento;

	private String quantidadeImoveisSEMSituacaoEspecialFaturamento;

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
	
	private String cdRotaInicial;
	
	private String sequencialRotaInicial;
	
	private String cdRotaFinal;
	
	private String sequencialRotaFinal;
	
	private String consumoFixoNaoMedido;
	
	private String consumoFixoMedido;
	
	private String volumeFixoNaoMedido;
	
	private String volumeFixoMedido;
	
	private String observacaoInforma;
	
	private String observacaoRetira;
	
	private String[] idsCategoria;
	
	private String indicadorConsumoImovel;

	

	public String getIndicadorConsumoImovel() {
		return indicadorConsumoImovel;
	}

	public void setIndicadorConsumoImovel(String indicadorConsumoImovel) {
		this.indicadorConsumoImovel = indicadorConsumoImovel;
	}

	public String[] getIdsCategoria() {
		return idsCategoria;
	}

	public void setIdsCategoria(String[] idsCategoria) {
		this.idsCategoria = idsCategoria;
	}

	public String getLiberarBotoes() {
		return liberarBotoes;
	}

	public void setLiberarBotoes(String liberarBotoes) {
		this.liberarBotoes = liberarBotoes;
	}

	public String getIdFaturamentoSituacaoMotivo() {
		return idFaturamentoSituacaoMotivo;
	}

	public void setIdFaturamentoSituacaoMotivo(
			String idFaturamentoSituacaoMotivo) {
		this.idFaturamentoSituacaoMotivo = idFaturamentoSituacaoMotivo;
	}

	public String getIdFaturamentoSituacaoTipo() {
		return idFaturamentoSituacaoTipo;
	}

	public void setIdFaturamentoSituacaoTipo(String idFaturamentoSituacaoTipo) {
		this.idFaturamentoSituacaoTipo = idFaturamentoSituacaoTipo;
	}

	public String getInscricaoTipo() {
		return inscricaoTipo;
	}

	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getQuantidadeImoveisCOMSituacaoEspecialFaturamento() {
		return quantidadeImoveisCOMSituacaoEspecialFaturamento;
	}

	public void setQuantidadeImoveisCOMSituacaoEspecialFaturamento(
			String quantidadeImoveisCOMSituacaoEspecialFaturamento) {
		this.quantidadeImoveisCOMSituacaoEspecialFaturamento = quantidadeImoveisCOMSituacaoEspecialFaturamento;
	}

	public String getQuantidadeImoveisSEMSituacaoEspecialFaturamento() {
		return quantidadeImoveisSEMSituacaoEspecialFaturamento;
	}

	public void setQuantidadeImoveisSEMSituacaoEspecialFaturamento(
			String quantidadeImoveisSEMSituacaoEspecialFaturamento) {
		this.quantidadeImoveisSEMSituacaoEspecialFaturamento = quantidadeImoveisSEMSituacaoEspecialFaturamento;
	}

	public String getSubloteDestino() {
		return subloteDestino;
	}

	public void setSubloteDestino(String subloteDestino) {
		this.subloteDestino = subloteDestino;
	}

	public String getSubloteOrigem() {
		return subloteOrigem;
	}

	public void setSubloteOrigem(String subloteOrigem) {
		this.subloteOrigem = subloteOrigem;
	}

	public String getMesAnoReferenciaFaturamentoFinal() {
		return mesAnoReferenciaFaturamentoFinal;
	}

	public void setMesAnoReferenciaFaturamentoFinal(
			String mesAnoReferenciaFaturamentoFinal) {
		this.mesAnoReferenciaFaturamentoFinal = mesAnoReferenciaFaturamentoFinal;
	}

	public String getMesAnoReferenciaFaturamentoInicial() {
		return mesAnoReferenciaFaturamentoInicial;
	}

	public void setMesAnoReferenciaFaturamentoInicial(
			String mesAnoReferenciaFaturamentoInicial) {
		this.mesAnoReferenciaFaturamentoInicial = mesAnoReferenciaFaturamentoInicial;
	}

	public String getMotivoSituacaoEspecialFaturamento() {
		return motivoSituacaoEspecialFaturamento;
	}

	public void setMotivoSituacaoEspecialFaturamento(
			String motivoSituacaoEspecialFaturamento) {
		this.motivoSituacaoEspecialFaturamento = motivoSituacaoEspecialFaturamento;
	}

	public String getQuantidadeImoveisAtualizados() {
		return quantidadeImoveisAtualizados;
	}

	public void setQuantidadeImoveisAtualizados(
			String quantidadeImoveisAtualizados) {
		this.quantidadeImoveisAtualizados = quantidadeImoveisAtualizados;
	}

	public String getTipoSituacaoEspecialFaturamento() {
		return tipoSituacaoEspecialFaturamento;
	}

	public void setTipoSituacaoEspecialFaturamento(
			String tipoSituacaoEspecialFaturamento) {
		this.tipoSituacaoEspecialFaturamento = tipoSituacaoEspecialFaturamento;
	}

	public String getLocalidadeDestinoID() {
		return localidadeDestinoID;
	}

	public void setLocalidadeDestinoID(String localidadeDestinoID) {
		this.localidadeDestinoID = localidadeDestinoID;
	}

	public String getLocalidadeOrigemID() {
		return localidadeOrigemID;
	}

	public void setLocalidadeOrigemID(String localidadeOrigemID) {
		this.localidadeOrigemID = localidadeOrigemID;
	}

	public String getNomeLocalidadeDestino() {
		return nomeLocalidadeDestino;
	}

	public void setNomeLocalidadeDestino(String nomeLocalidadeDestino) {
		this.nomeLocalidadeDestino = nomeLocalidadeDestino;
	}

	public String getNomeLocalidadeOrigem() {
		return nomeLocalidadeOrigem;
	}

	public void setNomeLocalidadeOrigem(String nomeLocalidadeOrigem) {
		this.nomeLocalidadeOrigem = nomeLocalidadeOrigem;
	}

	public String getNomeSetorComercialDestino() {
		return nomeSetorComercialDestino;
	}

	public void setNomeSetorComercialDestino(String nomeSetorComercialDestino) {
		this.nomeSetorComercialDestino = nomeSetorComercialDestino;
	}

	public String getNomeSetorComercialOrigem() {
		return nomeSetorComercialOrigem;
	}

	public void setNomeSetorComercialOrigem(String nomeSetorComercialOrigem) {
		this.nomeSetorComercialOrigem = nomeSetorComercialOrigem;
	}

	public String getQuadraDestinoID() {
		return quadraDestinoID;
	}

	public void setQuadraDestinoID(String quadraDestinoID) {
		this.quadraDestinoID = quadraDestinoID;
	}

	public String getQuadraDestinoNM() {
		return quadraDestinoNM;
	}

	public void setQuadraDestinoNM(String quadraDestinoNM) {
		this.quadraDestinoNM = quadraDestinoNM;
	}

	public String getQuadraMensagemDestino() {
		return quadraMensagemDestino;
	}

	public void setQuadraMensagemDestino(String quadraMensagemDestino) {
		this.quadraMensagemDestino = quadraMensagemDestino;
	}

	public String getQuadraMensagemOrigem() {
		return quadraMensagemOrigem;
	}

	public void setQuadraMensagemOrigem(String quadraMensagemOrigem) {
		this.quadraMensagemOrigem = quadraMensagemOrigem;
	}

	public String getQuadraOrigemID() {
		return quadraOrigemID;
	}

	public void setQuadraOrigemID(String quadraOrigemID) {
		this.quadraOrigemID = quadraOrigemID;
	}

	public String getQuadraOrigemNM() {
		return quadraOrigemNM;
	}

	public void setQuadraOrigemNM(String quadraOrigemNM) {
		this.quadraOrigemNM = quadraOrigemNM;
	}

	public String getSetorComercialDestinoCD() {
		return setorComercialDestinoCD;
	}

	public void setSetorComercialDestinoCD(String setorComercialDestinoCD) {
		this.setorComercialDestinoCD = setorComercialDestinoCD;
	}

	public String getSetorComercialDestinoID() {
		return setorComercialDestinoID;
	}

	public void setSetorComercialDestinoID(String setorComercialDestinoID) {
		this.setorComercialDestinoID = setorComercialDestinoID;
	}

	public String getSetorComercialOrigemCD() {
		return setorComercialOrigemCD;
	}

	public void setSetorComercialOrigemCD(String setorComercialOrigemCD) {
		this.setorComercialOrigemCD = setorComercialOrigemCD;
	}

	public String getSetorComercialOrigemID() {
		return setorComercialOrigemID;
	}

	public void setSetorComercialOrigemID(String setorComercialOrigemID) {
		this.setorComercialOrigemID = setorComercialOrigemID;
	}

	public String getLoteDestino() {
		return loteDestino;
	}

	public void setLoteDestino(String loteDestino) {
		this.loteDestino = loteDestino;
	}

	public String getLoteOrigem() {
		return loteOrigem;
	}

	public void setLoteOrigem(String loteOrigem) {
		this.loteOrigem = loteOrigem;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	
	

	public String getQuantidadeDeImoveis() {
		return quantidadeDeImoveis;
	}

	public void setQuantidadeDeImoveis(String quantidadeDeImoveis) {
		this.quantidadeDeImoveis = quantidadeDeImoveis;
	}

	@Override
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {

		idImovel = null;

		matricula = null;

		localidadeOrigemID = null;

		localidadeDestinoID = null;

		nomeLocalidadeOrigem = null;

		nomeLocalidadeDestino = null;

		setorComercialOrigemCD = null;

		setorComercialDestinoCD = null;

		setorComercialOrigemID = null;

		setorComercialDestinoID = null;

		nomeSetorComercialOrigem = null;

		nomeSetorComercialDestino = null;

		quadraOrigemNM = null;

		quadraDestinoNM = null;

		quadraMensagemOrigem = null;

		quadraMensagemDestino = null;

		quadraOrigemID = null;

		quadraDestinoID = null;

		loteOrigem = null;

		loteDestino = null;

		subloteOrigem = null;

		subloteDestino = null;

		quantidadeImoveisCOMSituacaoEspecialFaturamento = null;

		quantidadeImoveisSEMSituacaoEspecialFaturamento = null;

		quantidadeImoveisAtualizados = null;

		tipoSituacaoEspecialFaturamento = null;

		motivoSituacaoEspecialFaturamento = null;

		mesAnoReferenciaFaturamentoInicial = null;

		mesAnoReferenciaFaturamentoFinal = null;

		inscricaoTipo = null;

		idFaturamentoSituacaoTipo = null;

		idFaturamentoSituacaoMotivo = null;

		liberarBotoes = null;

		endereco = null;

		inscricaoImovel = null;
		
		quantidadeDeImoveis = null;
		
		cdRotaInicial = null;
		
		sequencialRotaInicial = null;
		
		cdRotaFinal = null;
		
		sequencialRotaFinal = null;
	}

	public String getCdRotaFinal() {
		return cdRotaFinal;
	}

	public void setCdRotaFinal(String cdRotaFinal) {
		this.cdRotaFinal = cdRotaFinal;
	}

	public String getCdRotaInicial() {
		return cdRotaInicial;
	}

	public void setCdRotaInicial(String cdRotaInicial) {
		this.cdRotaInicial = cdRotaInicial;
	}

	public String getSequencialRotaFinal() {
		return sequencialRotaFinal;
	}

	public void setSequencialRotaFinal(String sequencialRotaFinal) {
		this.sequencialRotaFinal = sequencialRotaFinal;
	}

	public String getSequencialRotaInicial() {
		return sequencialRotaInicial;
	}

	public void setSequencialRotaInicial(String sequencialRotaInicial) {
		this.sequencialRotaInicial = sequencialRotaInicial;
	}

	public String getConsumoFixoMedido() {
		return consumoFixoMedido;
	}

	public void setConsumoFixoMedido(String consumoFixoMedido) {
		this.consumoFixoMedido = consumoFixoMedido;
	}

	public String getConsumoFixoNaoMedido() {
		return consumoFixoNaoMedido;
	}

	public void setConsumoFixoNaoMedido(String consumoFixoNaoMedido) {
		this.consumoFixoNaoMedido = consumoFixoNaoMedido;
	}

	public String getVolumeFixoMedido() {
		return volumeFixoMedido;
	}

	public void setVolumeFixoMedido(String volumeFixoMedido) {
		this.volumeFixoMedido = volumeFixoMedido;
	}

	public String getVolumeFixoNaoMedido() {
		return volumeFixoNaoMedido;
	}

	public void setVolumeFixoNaoMedido(String volumeFixoNaoMedido) {
		this.volumeFixoNaoMedido = volumeFixoNaoMedido;
	}

	public String getObservacaoInforma() {
		return observacaoInforma;
	}

	public void setObservacaoInforma(String observacaoInforma) {
		this.observacaoInforma = observacaoInforma;
	}

	public String getObservacaoRetira() {
		return observacaoRetira;
	}

	public void setObservacaoRetira(String observacaoRetira) {
		this.observacaoRetira = observacaoRetira;
	}
	
	

}
