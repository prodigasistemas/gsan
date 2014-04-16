package gcom.gui.cobranca;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SituacaoEspecialCobrancaActionForm extends ActionForm {
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

	private String quantidadeImoveisCOMSituacaoEspecialCobranca;

	private String quantidadeImoveisSEMSituacaoEspecialCobranca;

	private String quantidadeImoveisAtualizados;

	private String tipoSituacaoEspecialCobranca;

	private String motivoSituacaoEspecialCobranca;

	private String mesAnoReferenciaCobrancaInicial;

	private String mesAnoReferenciaCobrancaFinal;

	private String inscricaoTipo;

	private String idCobrancaSituacaoTipo;

	private String idCobrancaSituacaoMotivo;

	private String liberarBotoes;

	private String endereco;

	private String inscricaoImovel;

	private String quantidadeDeImoveis;
	
	private String cdRotaInicial;
	
	private String sequencialRotaInicial;
	
	private String cdRotaFinal;
	
	private String sequencialRotaFinal;
	
	private String observacaoInforma;
	
	private String observacaoRetira;
	
	private String indicadorInformarDataFimSituacao;
	
	private String dataFimSituacao;
	
	private String[] idsCategoria;

	public String getIndicadorInformarDataFimSituacao() {
		return indicadorInformarDataFimSituacao;
	}

	public void setIndicadorInformarDataFimSituacao(
			String indicadorInformarDataFimSituacao) {
		this.indicadorInformarDataFimSituacao = indicadorInformarDataFimSituacao;
	}

	public String[] getIdsCategoria() {
		return idsCategoria;
	}

	public void setIdsCategoria(String[] idsCategoria) {
		this.idsCategoria = idsCategoria;
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

	public String getLiberarBotoes() {
		return liberarBotoes;
	}

	public void setLiberarBotoes(String liberarBotoes) {
		this.liberarBotoes = liberarBotoes;
	}

	public String getIdCobrancaSituacaoMotivo() {
		return idCobrancaSituacaoMotivo;
	}

	public void setIdCobrancaSituacaoMotivo(String idCobrancaSituacaoMotivo) {
		this.idCobrancaSituacaoMotivo = idCobrancaSituacaoMotivo;
	}

	public String getIdCobrancaSituacaoTipo() {
		return idCobrancaSituacaoTipo;
	}

	public void setIdCobrancaSituacaoTipo(String idCobrancaSituacaoTipo) {
		this.idCobrancaSituacaoTipo = idCobrancaSituacaoTipo;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getInscricaoTipo() {
		return inscricaoTipo;
	}

	public void setInscricaoTipo(String inscricaoTipo) {
		this.inscricaoTipo = inscricaoTipo;
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMesAnoReferenciaCobrancaFinal() {
		return mesAnoReferenciaCobrancaFinal;
	}

	public void setMesAnoReferenciaCobrancaFinal(
			String mesAnoReferenciaCobrancaFinal) {
		this.mesAnoReferenciaCobrancaFinal = mesAnoReferenciaCobrancaFinal;
	}

	public String getMesAnoReferenciaCobrancaInicial() {
		return mesAnoReferenciaCobrancaInicial;
	}

	public void setMesAnoReferenciaCobrancaInicial(
			String mesAnoReferenciaCobrancaInicial) {
		this.mesAnoReferenciaCobrancaInicial = mesAnoReferenciaCobrancaInicial;
	}

	public String getMotivoSituacaoEspecialCobranca() {
		return motivoSituacaoEspecialCobranca;
	}

	public void setMotivoSituacaoEspecialCobranca(
			String motivoSituacaoEspecialCobranca) {
		this.motivoSituacaoEspecialCobranca = motivoSituacaoEspecialCobranca;
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

	public String getQuantidadeImoveisAtualizados() {
		return quantidadeImoveisAtualizados;
	}

	public void setQuantidadeImoveisAtualizados(
			String quantidadeImoveisAtualizados) {
		this.quantidadeImoveisAtualizados = quantidadeImoveisAtualizados;
	}

	public String getQuantidadeImoveisCOMSituacaoEspecialCobranca() {
		return quantidadeImoveisCOMSituacaoEspecialCobranca;
	}

	public void setQuantidadeImoveisCOMSituacaoEspecialCobranca(
			String quantidadeImoveisCOMSituacaoEspecialCobranca) {
		this.quantidadeImoveisCOMSituacaoEspecialCobranca = quantidadeImoveisCOMSituacaoEspecialCobranca;
	}

	public String getQuantidadeImoveisSEMSituacaoEspecialCobranca() {
		return quantidadeImoveisSEMSituacaoEspecialCobranca;
	}

	public void setQuantidadeImoveisSEMSituacaoEspecialCobranca(
			String quantidadeImoveisSEMSituacaoEspecialCobranca) {
		this.quantidadeImoveisSEMSituacaoEspecialCobranca = quantidadeImoveisSEMSituacaoEspecialCobranca;
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

	public String getTipoSituacaoEspecialCobranca() {
		return tipoSituacaoEspecialCobranca;
	}

	public void setTipoSituacaoEspecialCobranca(
			String tipoSituacaoEspecialCobranca) {
		this.tipoSituacaoEspecialCobranca = tipoSituacaoEspecialCobranca;
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

		quantidadeImoveisCOMSituacaoEspecialCobranca = null;

		quantidadeImoveisSEMSituacaoEspecialCobranca = null;

		quantidadeImoveisAtualizados = null;

		tipoSituacaoEspecialCobranca = null;

		motivoSituacaoEspecialCobranca = null;

		mesAnoReferenciaCobrancaInicial = null;

		mesAnoReferenciaCobrancaFinal = null;

		inscricaoTipo = null;

		idCobrancaSituacaoTipo = null;

		idCobrancaSituacaoMotivo = null;

		liberarBotoes = null;

		endereco = null;

		inscricaoImovel = null;

		quantidadeDeImoveis = null;
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

	public String getDataFimSituacao() {
		return dataFimSituacao;
	}

	public void setDataFimSituacao(String dataFimSituacao) {
		this.dataFimSituacao = dataFimSituacao;
	}

	
}
