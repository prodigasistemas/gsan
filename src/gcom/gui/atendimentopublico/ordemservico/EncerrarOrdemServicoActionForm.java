package gcom.gui.atendimentopublico.ordemservico;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class EncerrarOrdemServicoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	// Dados Gerais
	private String numeroOS;

	private String situacaoOS;

	private String situacaoOSId;

	private String numeroRA;

	private String situacaoRA;

	private String numeroDocumentoCobranca;

	private String dataGeracao;

	private String numeroOSReferencia;

	private String tipoServicoOSId;

	private String tipoServicoOSDescricao;

	private String tipoServicoReferenciaId;

	private String tipoServicoReferenciaDescricao;

	private String retornoOSReferida;

	private String observacao;

	private String valorServicoOriginal;

	private String valorServicoAtual;

	private String prioridadeOriginal;

	private String prioridadeAtual;

	private String unidadeGeracaoId;

	private String unidadeGeracaoDescricao;

	private String usuarioGeracaoId;

	private String usuarioGeracaoNome;

	private String dataUltimaEmissao;

	private String idMotivoEncerramento;

	private String dataEncerramento;

	private Date ultimaAlteracao;

	private String indicadorExecucao;

	private String observacaoEncerramento;

	private String situacaoOSReferencia;

	private String pavimento;

	private String indicadorPavimento;

	private String servicoTipoReferenciaDescricao;

	private String indicadorDeferimento;

	private String idTipoRetornoReferida;

	private String indicadorTrocaServico;

	private String idServicoTipo;

	private String descricaoServicoTipo;
	
	private String servicoTipoObrigatorio;
	
	private String servicoTipoReferenciaOS;
	
	private String servicoTipoReferenciaOSDescricao;
	
	private String dataRoteiro;
	
	private String indicadorAtualizaComercial;
	
    private String indicadorVistoriaServicoTipo;
	
	private String codigoRetornoVistoriaOs;
	
	private String horaEncerramento;
    
    private String indicadorDiagnostico;
    
    private String indicadorPavimentoCalcada;
    
    private String indicadorPavimentoRua;
    
    private String idPavimentoCalcada;
    
    private String idPavimentoRua;
    
    private String metragemPavimentoCalcada;
    
    private String metragemPavimentoRua;
    
    private String mostrarAlert;
    
    private String idUnidadeRepavimentadora;
    
    private String descricaoUnidadeRepavimentadora;

    private String indicadorServicoAceito;

    private String indicadorExistePavimento;
    
    private String qtdeReposicaoAsfalto;
    
    private String qtdeReposicaoParalelo;
    
    private String qtdeReposicaoCalcada;
    
    private String tipoServicoReferenciaIndicadorFiscalizacao;
    
    //hidden
    private String exibeIndicadorExistePavimento;
    private String exibeQtdeReposicaoAsfalto;
    private String exibeQtdeReposicaoCalcada;
    private String exibeQtdeReposicaoParalelo;
	/**
	 * @return Returns the descricaoUnidadeRepavimentadora.
	 */
	public String getDescricaoUnidadeRepavimentadora() {
		return descricaoUnidadeRepavimentadora;
	}

	/**
	 * @param descricaoUnidadeRepavimentadora The descricaoUnidadeRepavimentadora to set.
	 */
	public void setDescricaoUnidadeRepavimentadora(
			String descricaoUnidadeRepavimentadora) {
		this.descricaoUnidadeRepavimentadora = descricaoUnidadeRepavimentadora;
	}

	/**
	 * @return Retorna o campo mostrarAlert.
	 */
	public String getMostrarAlert() {
		return mostrarAlert;
	}

	/**
	 * @param mostrarAlert O mostrarAlert a ser setado.
	 */
	public void setMostrarAlert(String mostrarAlert) {
		this.mostrarAlert = mostrarAlert;
	}

	/**
	 * @return Retorna o campo horaEncerramento.
	 */
	public String getHoraEncerramento() {
		return horaEncerramento;
	}

	/**
	 * @param horaEncerramento O horaEncerramento a ser setado.
	 */
	public void setHoraEncerramento(String horaEncerramento) {
		this.horaEncerramento = horaEncerramento;
	}

	public String getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getDataUltimaEmissao() {
		return dataUltimaEmissao;
	}

	public void setDataUltimaEmissao(String dataUltimaEmissao) {
		this.dataUltimaEmissao = dataUltimaEmissao;
	}

	public String getNumeroDocumentoCobranca() {
		return numeroDocumentoCobranca;
	}

	public void setNumeroDocumentoCobranca(String numeroDocumentoCobranca) {
		this.numeroDocumentoCobranca = numeroDocumentoCobranca;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getNumeroOSReferencia() {
		return numeroOSReferencia;
	}

	public void setNumeroOSReferencia(String numeroOSReferencia) {
		this.numeroOSReferencia = numeroOSReferencia;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getPrioridadeAtual() {
		return prioridadeAtual;
	}

	public void setPrioridadeAtual(String prioridadeAtual) {
		this.prioridadeAtual = prioridadeAtual;
	}

	public String getPrioridadeOriginal() {
		return prioridadeOriginal;
	}

	public void setPrioridadeOriginal(String prioridadeOriginal) {
		this.prioridadeOriginal = prioridadeOriginal;
	}

	public String getRetornoOSReferida() {
		return retornoOSReferida;
	}

	public void setRetornoOSReferida(String retornoOSReferida) {
		this.retornoOSReferida = retornoOSReferida;
	}

	public String getSituacaoOS() {
		return situacaoOS;
	}

	public void setSituacaoOS(String situacaoOS) {
		this.situacaoOS = situacaoOS;
	}

	public String getSituacaoRA() {
		return situacaoRA;
	}

	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}

	public String getTipoServicoReferenciaDescricao() {
		return tipoServicoReferenciaDescricao;
	}

	public void setTipoServicoReferenciaDescricao(
			String tipoServicoReferenciaDescricao) {
		this.tipoServicoReferenciaDescricao = tipoServicoReferenciaDescricao;
	}

	public String getTipoServicoReferenciaId() {
		return tipoServicoReferenciaId;
	}

	public void setTipoServicoReferenciaId(String tipoServicoReferenciaId) {
		this.tipoServicoReferenciaId = tipoServicoReferenciaId;
	}

	public String getUnidadeGeracaoDescricao() {
		return unidadeGeracaoDescricao;
	}

	public void setUnidadeGeracaoDescricao(String unidadeGeracaoDescricao) {
		this.unidadeGeracaoDescricao = unidadeGeracaoDescricao;
	}

	public String getUnidadeGeracaoId() {
		return unidadeGeracaoId;
	}

	public void setUnidadeGeracaoId(String unidadeGeracaoId) {
		this.unidadeGeracaoId = unidadeGeracaoId;
	}

	public String getUsuarioGeracaoId() {
		return usuarioGeracaoId;
	}

	public void setUsuarioGeracaoId(String usuarioGeracaoId) {
		this.usuarioGeracaoId = usuarioGeracaoId;
	}

	public String getUsuarioGeracaoNome() {
		return usuarioGeracaoNome;
	}

	public void setUsuarioGeracaoNome(String usuarioGeracaoNome) {
		this.usuarioGeracaoNome = usuarioGeracaoNome;
	}

	public String getValorServicoAtual() {
		return valorServicoAtual;
	}

	public void setValorServicoAtual(String valorServicoAtual) {
		this.valorServicoAtual = valorServicoAtual;
	}

	public String getValorServicoOriginal() {
		return valorServicoOriginal;
	}

	public void setValorServicoOriginal(String valorServicoOriginal) {
		this.valorServicoOriginal = valorServicoOriginal;
	}

	public String getSituacaoOSId() {
		return situacaoOSId;
	}

	public void setSituacaoOSId(String situacaoOSId) {
		this.situacaoOSId = situacaoOSId;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String getIndicadorExecucao() {
		return indicadorExecucao;
	}

	public void setIndicadorExecucao(String indicadorExecucao) {
		this.indicadorExecucao = indicadorExecucao;
	}

	public String getObservacaoEncerramento() {
		return observacaoEncerramento;
	}

	public void setObservacaoEncerramento(String observacaoEncerramento) {
		this.observacaoEncerramento = observacaoEncerramento;
	}

	public String getSituacaoOSReferencia() {
		return situacaoOSReferencia;
	}

	public void setSituacaoOSReferencia(String situacaoOSReferencia) {
		this.situacaoOSReferencia = situacaoOSReferencia;
	}

	public String getPavimento() {
		return pavimento;
	}

	public void setPavimento(String pavimento) {
		this.pavimento = pavimento;
	}

	public String getIndicadorPavimento() {
		return indicadorPavimento;
	}

	public void setIndicadorPavimento(String indicadorPavimento) {
		this.indicadorPavimento = indicadorPavimento;
	}

	public String getServicoTipoReferenciaDescricao() {
		return servicoTipoReferenciaDescricao;
	}

	public void setServicoTipoReferenciaDescricao(
			String servicoTipoReferenciaDescricao) {
		this.servicoTipoReferenciaDescricao = servicoTipoReferenciaDescricao;
	}

	public String getIndicadorDeferimento() {
		return indicadorDeferimento;
	}

	public void setIndicadorDeferimento(String indicadorDeferimento) {
		this.indicadorDeferimento = indicadorDeferimento;
	}

	public String getIdTipoRetornoReferida() {
		return idTipoRetornoReferida;
	}

	public void setIdTipoRetornoReferida(String idTipoRetornoReferida) {
		this.idTipoRetornoReferida = idTipoRetornoReferida;
	}

	public String getIndicadorTrocaServico() {
		return indicadorTrocaServico;
	}

	public void setIndicadorTrocaServico(String indicadorTrocaServico) {
		this.indicadorTrocaServico = indicadorTrocaServico;
	}

	public String getDescricaoServicoTipo() {
		return descricaoServicoTipo;
	}

	public void setDescricaoServicoTipo(String descricaoServicoTipo) {
		this.descricaoServicoTipo = descricaoServicoTipo;
	}

	public String getIdServicoTipo() {
		return idServicoTipo;
	}

	public void setIdServicoTipo(String idServicoTipo) {
		this.idServicoTipo = idServicoTipo;
	}

	public String getServicoTipoObrigatorio() {
		return servicoTipoObrigatorio;
	}

	public void setServicoTipoObrigatorio(String servicoTipoObrigatorio) {
		this.servicoTipoObrigatorio = servicoTipoObrigatorio;
	}
	
	 public String getServicoTipoReferenciaOS() {
		return servicoTipoReferenciaOS;
	}

	public void setServicoTipoReferenciaOS(String servicoTipoReferenciaOS) {
		this.servicoTipoReferenciaOS = servicoTipoReferenciaOS;
	}
	
	

	public String getDataRoteiro() {
		return dataRoteiro;
	}

	public void setDataRoteiro(String dataRoteiro) {
		this.dataRoteiro = dataRoteiro;
	}
	
	

	public String getTipoServicoOSDescricao() {
		return tipoServicoOSDescricao;
	}

	public void setTipoServicoOSDescricao(String tipoServicoOSDescricao) {
		this.tipoServicoOSDescricao = tipoServicoOSDescricao;
	}

	public String getTipoServicoOSId() {
		return tipoServicoOSId;
	}

	public void setTipoServicoOSId(String tipoServicoOSId) {
		this.tipoServicoOSId = tipoServicoOSId;
	}
	
	
	public String getIndicadorAtualizaComercial() {
		return indicadorAtualizaComercial;
	}

	public void setIndicadorAtualizaComercial(String indicadorAtualizaComercial) {
		this.indicadorAtualizaComercial = indicadorAtualizaComercial;
	}
	
	

	public String getServicoTipoReferenciaOSDescricao() {
		return servicoTipoReferenciaOSDescricao;
	}

	public void setServicoTipoReferenciaOSDescricao(
			String servicoTipoReferenciaOSDescricao) {
		this.servicoTipoReferenciaOSDescricao = servicoTipoReferenciaOSDescricao;
	}

	public void resetarConsultarDadosOSPopup() {
	    	this.numeroOS = null;
	    	this.situacaoOS = null;
	    	this.situacaoOSId = null;
	        this.numeroRA = null;
	        this.situacaoRA = null;
	        this.numeroDocumentoCobranca = null;
	        this.dataGeracao = null;
	        this.numeroOSReferencia = null;
	        this.tipoServicoOSId = null;
	        this.tipoServicoOSDescricao = null;
	        this.tipoServicoReferenciaId = null;
	        this.tipoServicoReferenciaDescricao = null;
	        this.retornoOSReferida = null;
	        this.observacao = null;
	        this.valorServicoOriginal = null;
	        this.valorServicoAtual = null;
	        this.prioridadeOriginal = null;
	        this.prioridadeAtual = null;
	        this.unidadeGeracaoId = null;
	        this.unidadeGeracaoDescricao = null;
	        this.usuarioGeracaoId = null;
	        this.usuarioGeracaoNome = null;
	        this.dataUltimaEmissao = null;
	        this.dataEncerramento = null;
	        this.idMotivoEncerramento = null;
	        this.dataEncerramento = null;
	        this.ultimaAlteracao = null;
	        this.indicadorExecucao = null;
	        this.observacaoEncerramento = null;
	        this.situacaoOSReferencia = null;
	        this.pavimento = null;
	        this.indicadorPavimento = null;
	        this.servicoTipoReferenciaDescricao = null;
	        this.indicadorDeferimento = null;
	        this.idTipoRetornoReferida = null;
	        this.indicadorTrocaServico = null;
	        this.idServicoTipo = null;
	        this.descricaoServicoTipo = null;
	        this.servicoTipoObrigatorio = null;
	        this.servicoTipoReferenciaOS = null;
	        this.dataRoteiro = null;
	        this.indicadorAtualizaComercial = null;
	        this.servicoTipoReferenciaOSDescricao = null;
	        this.codigoRetornoVistoriaOs = null;
	        this.indicadorVistoriaServicoTipo = null;
            this.indicadorDiagnostico = null;
            this.indicadorPavimentoCalcada=null;
            this.indicadorPavimentoRua = null;            
            this.idPavimentoCalcada=null;
            this.idPavimentoRua=null;            
            this.metragemPavimentoCalcada=null;
            this.metragemPavimentoRua=null;
            this.mostrarAlert=null;
	      
	    }

	public String getCodigoRetornoVistoriaOs() {
		return codigoRetornoVistoriaOs;
	}

	public void setCodigoRetornoVistoriaOs(String codigoRetornoVistoriaOs) {
		this.codigoRetornoVistoriaOs = codigoRetornoVistoriaOs;
	}

	public String getIndicadorVistoriaServicoTipo() {
		return indicadorVistoriaServicoTipo;
	}

	public void setIndicadorVistoriaServicoTipo(String indicadorVistoriaServicoTipo) {
		this.indicadorVistoriaServicoTipo = indicadorVistoriaServicoTipo;
	}

    public String getIndicadorDiagnostico() {
        return indicadorDiagnostico;
    }

    public void setIndicadorDiagnostico(String indicadorDiagnostico) {
        this.indicadorDiagnostico = indicadorDiagnostico;
    }

	/**
	 * @return Retorna o campo idPavimentoCalcada.
	 */
	public String getIdPavimentoCalcada() {
		return idPavimentoCalcada;
	}

	/**
	 * @param idPavimentoCalcada O idPavimentoCalcada a ser setado.
	 */
	public void setIdPavimentoCalcada(String idPavimentoCalcada) {
		this.idPavimentoCalcada = idPavimentoCalcada;
	}

	/**
	 * @return Retorna o campo idPavimentoRua.
	 */
	public String getIdPavimentoRua() {
		return idPavimentoRua;
	}

	/**
	 * @param idPavimentoRua O idPavimentoRua a ser setado.
	 */
	public void setIdPavimentoRua(String idPavimentoRua) {
		this.idPavimentoRua = idPavimentoRua;
	}

	/**
	 * @return Retorna o campo indicadorPavimentoCalcada.
	 */
	public String getIndicadorPavimentoCalcada() {
		return indicadorPavimentoCalcada;
	}

	/**
	 * @param indicadorPavimentoCalcada O indicadorPavimentoCalcada a ser setado.
	 */
	public void setIndicadorPavimentoCalcada(String indicadorPavimentoCalcada) {
		this.indicadorPavimentoCalcada = indicadorPavimentoCalcada;
	}

	/**
	 * @return Retorna o campo indicadorPavimentoRua.
	 */
	public String getIndicadorPavimentoRua() {
		return indicadorPavimentoRua;
	}

	/**
	 * @param indicadorPavimentoRua O indicadorPavimentoRua a ser setado.
	 */
	public void setIndicadorPavimentoRua(String indicadorPavimentoRua) {
		this.indicadorPavimentoRua = indicadorPavimentoRua;
	}

	/**
	 * @return Retorna o campo metragemPavimentoCalcada.
	 */
	public String getMetragemPavimentoCalcada() {
		return metragemPavimentoCalcada;
	}

	/**
	 * @param metragemPavimentoCalcada O metragemPavimentoCalcada a ser setado.
	 */
	public void setMetragemPavimentoCalcada(String metragemPavimentoCalcada) {
		this.metragemPavimentoCalcada = metragemPavimentoCalcada;
	}

	/**
	 * @return Retorna o campo metragemPavimentoRua.
	 */
	public String getMetragemPavimentoRua() {
		return metragemPavimentoRua;
	}

	/**
	 * @param metragemPavimentoRua O metragemPavimentoRua a ser setado.
	 */
	public void setMetragemPavimentoRua(String metragemPavimentoRua) {
		this.metragemPavimentoRua = metragemPavimentoRua;
	}

	/**
	 * @return Returns the idUnidadeRepavimentadora.
	 */
	public String getIdUnidadeRepavimentadora() {
		return idUnidadeRepavimentadora;
	}

	/**
	 * @param idUnidadeRepavimentadora The idUnidadeRepavimentadora to set.
	 */
	public void setIdUnidadeRepavimentadora(String idUnidadeRepavimentadora) {
		this.idUnidadeRepavimentadora = idUnidadeRepavimentadora;
	}

	public String getIndicadorExistePavimento() {
		return indicadorExistePavimento;
	}

	public void setIndicadorExistePavimento(String indicadorExistePavimento) {
		this.indicadorExistePavimento = indicadorExistePavimento;
	}

	public String getQtdeReposicaoAsfalto() {
		return qtdeReposicaoAsfalto;
	}

	public void setQtdeReposicaoAsfalto(String qtdeReposicaoAsfalto) {
		this.qtdeReposicaoAsfalto = qtdeReposicaoAsfalto;
	}

	public String getQtdeReposicaoCalcada() {
		return qtdeReposicaoCalcada;
	}

	public void setQtdeReposicaoCalcada(String qtdeReposicaoCalcada) {
		this.qtdeReposicaoCalcada = qtdeReposicaoCalcada;
	}

	public String getQtdeReposicaoParalelo() {
		return qtdeReposicaoParalelo;
	}

	public void setQtdeReposicaoParalelo(String qtdeReposicaoParalelo) {
		this.qtdeReposicaoParalelo = qtdeReposicaoParalelo;
	}

	public String getExibeIndicadorExistePavimento() {
		return exibeIndicadorExistePavimento;
	}

	public void setExibeIndicadorExistePavimento(
			String exibeIndicadorExistePavimento) {
		this.exibeIndicadorExistePavimento = exibeIndicadorExistePavimento;
	}

	public String getExibeQtdeReposicaoAsfalto() {
		return exibeQtdeReposicaoAsfalto;
	}

	public void setExibeQtdeReposicaoAsfalto(String exibeQtdeReposicaoAsfalto) {
		this.exibeQtdeReposicaoAsfalto = exibeQtdeReposicaoAsfalto;
	}

	public String getExibeQtdeReposicaoCalcada() {
		return exibeQtdeReposicaoCalcada;
	}

	public void setExibeQtdeReposicaoCalcada(String exibeQtdeReposicaoCalcada) {
		this.exibeQtdeReposicaoCalcada = exibeQtdeReposicaoCalcada;
	}

	public String getExibeQtdeReposicaoParalelo() {
		return exibeQtdeReposicaoParalelo;
	}

	public void setExibeQtdeReposicaoParalelo(String exibeQtdeReposicaoParalelo) {
		this.exibeQtdeReposicaoParalelo = exibeQtdeReposicaoParalelo;
	}

	public String getIndicadorServicoAceito() {
		return indicadorServicoAceito;
	}

	public void setIndicadorServicoAceito(String indicadorServicoAceito) {
		this.indicadorServicoAceito = indicadorServicoAceito;
	}

	public String getTipoServicoReferenciaIndicadorFiscalizacao() {
		return tipoServicoReferenciaIndicadorFiscalizacao;
	}

	public void setTipoServicoReferenciaIndicadorFiscalizacao(
			String tipoServicoReferenciaIndicadorFiscalizacao) {
		this.tipoServicoReferenciaIndicadorFiscalizacao = tipoServicoReferenciaIndicadorFiscalizacao;
	}
	
	

}
