package gcom.gui.atendimentopublico.registroatendimento;


import gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.RaDadosAgenciaReguladora;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.util.Util;

import org.apache.struts.validator.ValidatorForm;


/**
 * [UC0459] Informar Dados da Agencia Reguladora
 *
 * @author Kássia Albuquerque
 * @date 27/03/2007
 */

public class InformarRaDadosAgenciaReguladoraActionForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	Dados Gerais do RA
	// Dados Gerais, Dados do Solicitante & Dados do Local de Ocorrência
    private String numeroRA;
    private String numeroRADados;
    private String situacaoRA;
    private String numeroSituacaoRA;
    private String numeroRaAssociado;
    private String situacaoRaAssociado;
    private String tipoSolicitacaoId;
    private String tipoSolicitacaoDescricao;
    private String especificacaoId;
    private String especificacaoDescricao;    
    private String meioSolicitacaoId;
    private String meioSolicitacaoDescricao;    
    private String matriculaImovel;
    private String inscricaoImovel;
    private String dataAtendimento;
    private String horaAtendimento;
    private String dataPrevista;
    private String dataEncerramento;
    private String idMotivoEncerramento;
    private String motivoEncerramento;
    private Integer idRaSolicitante;
    private String idClienteSolicitante;
    private String clienteSolicitante;
    private String idUnidadeSolicitante;
    private String unidadeSolicitante;
    private String nomeSolicitante;
    private String enderecoOcorrencia;
    private String pontoReferencia;
  /*  private String municipioId;
    private String municipioDescricao;*/
    private String bairroId;
    private String bairroDescricao;
    private String areaBairroId;
    private String areaBairroDescricao;
    private String localidadeId;
    private String localidadeDescricao;
    private String setorComercialId;
    private String setorComercialCodigo;
    private String quadraId;
    private String quadraNumero;
    private String divisaoEsgotoId;
    private String divisaoEsgotoDescricao;
    private String unidadeAtendimentoId;
    private String unidadeAtendimentoDescricao;
    private String unidadeAtualId;
    private String unidadeAtualDescricao;
    private Integer logradouroBairro;
    private Integer logradouroCep;
    private String complementoEndereco;
    private Integer localOcorrencia;
    private Integer pavimentoRua;
    private Integer pavimentoCalcada;
    private String descricaoLocalOcorrencia;
    
	//Dados Novos da Reclamação do Registro de Atendimento 
    private String motivoReclamacao;
    private String numeroRegistroAgenciaReguladora;
    private String dataPrevisaoOriginal;
    private String dataPrevisaoAtual;
    private String reclamacao;
    private String codigoSituacao;
    
	public String getCodigoSituacao() {
		return codigoSituacao;
	}
	public void setCodigoSituacao(String codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}
	public String getAreaBairroDescricao() {
		return areaBairroDescricao;
	}
	public void setAreaBairroDescricao(String areaBairroDescricao) {
		this.areaBairroDescricao = areaBairroDescricao;
	}
	public String getAreaBairroId() {
		return areaBairroId;
	}
	public void setAreaBairroId(String areaBairroId) {
		this.areaBairroId = areaBairroId;
	}
	public String getBairroDescricao() {
		return bairroDescricao;
	}
	public void setBairroDescricao(String bairroDescricao) {
		this.bairroDescricao = bairroDescricao;
	}
	public String getBairroId() {
		return bairroId;
	}
	public void setBairroId(String bairroId) {
		this.bairroId = bairroId;
	}
	public String getClienteSolicitante() {
		return clienteSolicitante;
	}
	public void setClienteSolicitante(String clienteSolicitante) {
		this.clienteSolicitante = clienteSolicitante;
	}
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	public String getDataAtendimento() {
		return dataAtendimento;
	}
	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}
	public String getDataEncerramento() {
		return dataEncerramento;
	}
	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}
	public String getDataPrevisaoAtual() {
		return dataPrevisaoAtual;
	}
	public void setDataPrevisaoAtual(String dataPrevisaoAtual) {
		this.dataPrevisaoAtual = dataPrevisaoAtual;
	}
	public String getDataPrevisaoOriginal() {
		return dataPrevisaoOriginal;
	}
	public void setDataPrevisaoOriginal(String dataPrevisaoOriginal) {
		this.dataPrevisaoOriginal = dataPrevisaoOriginal;
	}
	public String getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public String getDescricaoLocalOcorrencia() {
		return descricaoLocalOcorrencia;
	}
	public void setDescricaoLocalOcorrencia(String descricaoLocalOcorrencia) {
		this.descricaoLocalOcorrencia = descricaoLocalOcorrencia;
	}
	public String getDivisaoEsgotoDescricao() {
		return divisaoEsgotoDescricao;
	}
	public void setDivisaoEsgotoDescricao(String divisaoEsgotoDescricao) {
		this.divisaoEsgotoDescricao = divisaoEsgotoDescricao;
	}
	public String getDivisaoEsgotoId() {
		return divisaoEsgotoId;
	}
	public void setDivisaoEsgotoId(String divisaoEsgotoId) {
		this.divisaoEsgotoId = divisaoEsgotoId;
	}
	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}
	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}
	public String getEspecificacaoDescricao() {
		return especificacaoDescricao;
	}
	public void setEspecificacaoDescricao(String especificacaoDescricao) {
		this.especificacaoDescricao = especificacaoDescricao;
	}
	public String getEspecificacaoId() {
		return especificacaoId;
	}
	public void setEspecificacaoId(String especificacaoId) {
		this.especificacaoId = especificacaoId;
	}
	public String getHoraAtendimento() {
		return horaAtendimento;
	}
	public void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}
	public String getIdClienteSolicitante() {
		return idClienteSolicitante;
	}
	public void setIdClienteSolicitante(String idClienteSolicitante) {
		this.idClienteSolicitante = idClienteSolicitante;
	}
	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}
	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}
	public String getIdUnidadeSolicitante() {
		return idUnidadeSolicitante;
	}
	public void setIdUnidadeSolicitante(String idUnidadeSolicitante) {
		this.idUnidadeSolicitante = idUnidadeSolicitante;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}
	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}
	public String getLocalidadeId() {
		return localidadeId;
	}
	public void setLocalidadeId(String localidadeId) {
		this.localidadeId = localidadeId;
	}
	public Integer getLocalOcorrencia() {
		return localOcorrencia;
	}
	public void setLocalOcorrencia(Integer localOcorrencia) {
		this.localOcorrencia = localOcorrencia;
	}
	public Integer getLogradouroBairro() {
		return logradouroBairro;
	}
	public void setLogradouroBairro(Integer logradouroBairro) {
		this.logradouroBairro = logradouroBairro;
	}
	public Integer getLogradouroCep() {
		return logradouroCep;
	}
	public void setLogradouroCep(Integer logradouroCep) {
		this.logradouroCep = logradouroCep;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getMeioSolicitacaoDescricao() {
		return meioSolicitacaoDescricao;
	}
	public void setMeioSolicitacaoDescricao(String meioSolicitacaoDescricao) {
		this.meioSolicitacaoDescricao = meioSolicitacaoDescricao;
	}
	public String getMeioSolicitacaoId() {
		return meioSolicitacaoId;
	}
	public void setMeioSolicitacaoId(String meioSolicitacaoId) {
		this.meioSolicitacaoId = meioSolicitacaoId;
	}
	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}
	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}
	public String getMotivoReclamacao() {
		return motivoReclamacao;
	}
	public void setMotivoReclamacao(String motivoReclamacao) {
		this.motivoReclamacao = motivoReclamacao;
	}
/*	public String getMunicipioDescricao() {
		return municipioDescricao;
	}
	public void setMunicipioDescricao(String municipioDescricao) {
		this.municipioDescricao = municipioDescricao;
	}
	public String getMunicipioId() {
		return municipioId;
	}
	public void setMunicipioId(String municipioId) {
		this.municipioId = municipioId;
	}*/
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public String getNumeroRA() {
		return numeroRA;
	}
	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}
	public String getNumeroRaAssociado() {
		return numeroRaAssociado;
	}
	public void setNumeroRaAssociado(String numeroRaAssociado) {
		this.numeroRaAssociado = numeroRaAssociado;
	}
	public String getNumeroRegistroAgenciaReguladora() {
		return numeroRegistroAgenciaReguladora;
	}
	public void setNumeroRegistroAgenciaReguladora(
			String numeroRegistroAgenciaReguladora) {
		this.numeroRegistroAgenciaReguladora = numeroRegistroAgenciaReguladora;
	}
	public Integer getPavimentoCalcada() {
		return pavimentoCalcada;
	}
	public void setPavimentoCalcada(Integer pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}
	public Integer getPavimentoRua() {
		return pavimentoRua;
	}
	public void setPavimentoRua(Integer pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}
	public String getPontoReferencia() {
		return pontoReferencia;
	}
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}
	public String getQuadraId() {
		return quadraId;
	}
	public void setQuadraId(String quadraId) {
		this.quadraId = quadraId;
	}
	public String getQuadraNumero() {
		return quadraNumero;
	}
	public void setQuadraNumero(String quadraNumero) {
		this.quadraNumero = quadraNumero;
	}
	public String getReclamacao() {
		return reclamacao;
	}
	public void setReclamacao(String reclamacao) {
		this.reclamacao = reclamacao;
	}
	public String getSetorComercialCodigo() {
		return setorComercialCodigo;
	}
	public void setSetorComercialCodigo(String setorComercialCodigo) {
		this.setorComercialCodigo = setorComercialCodigo;
	}
	public String getSetorComercialId() {
		return setorComercialId;
	}
	public void setSetorComercialId(String setorComercialId) {
		this.setorComercialId = setorComercialId;
	}
	public String getSituacaoRA() {
		return situacaoRA;
	}
	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}
	public String getSituacaoRaAssociado() {
		return situacaoRaAssociado;
	}
	public void setSituacaoRaAssociado(String situacaoRaAssociado) {
		this.situacaoRaAssociado = situacaoRaAssociado;
	}
	public String getTipoSolicitacaoDescricao() {
		return tipoSolicitacaoDescricao;
	}
	public void setTipoSolicitacaoDescricao(String tipoSolicitacaoDescricao) {
		this.tipoSolicitacaoDescricao = tipoSolicitacaoDescricao;
	}
	public String getTipoSolicitacaoId() {
		return tipoSolicitacaoId;
	}
	public void setTipoSolicitacaoId(String tipoSolicitacaoId) {
		this.tipoSolicitacaoId = tipoSolicitacaoId;
	}
	public String getUnidadeAtendimentoDescricao() {
		return unidadeAtendimentoDescricao;
	}
	public void setUnidadeAtendimentoDescricao(String unidadeAtendimentoDescricao) {
		this.unidadeAtendimentoDescricao = unidadeAtendimentoDescricao;
	}
	public String getUnidadeAtendimentoId() {
		return unidadeAtendimentoId;
	}
	public void setUnidadeAtendimentoId(String unidadeAtendimentoId) {
		this.unidadeAtendimentoId = unidadeAtendimentoId;
	}
	public String getUnidadeAtualDescricao() {
		return unidadeAtualDescricao;
	}
	public void setUnidadeAtualDescricao(String unidadeAtualDescricao) {
		this.unidadeAtualDescricao = unidadeAtualDescricao;
	}
	public String getUnidadeAtualId() {
		return unidadeAtualId;
	}
	public void setUnidadeAtualId(String unidadeAtualId) {
		this.unidadeAtualId = unidadeAtualId;
	}
	public String getUnidadeSolicitante() {
		return unidadeSolicitante;
	}
	public void setUnidadeSolicitante(String unidadeSolicitante) {
		this.unidadeSolicitante = unidadeSolicitante;
	}
	public Integer getIdRaSolicitante() {
		return idRaSolicitante;
	}
	public void setIdRaSolicitante(Integer idRaSolicitante) {
		this.idRaSolicitante = idRaSolicitante;
	}
   
	public String getNumeroRADados() {
		return numeroRADados;
	}
	public void setNumeroRADados(String numeroRADados) {
		this.numeroRADados = numeroRADados;
	}
	
	public String getNumeroSituacaoRA() {
		return numeroSituacaoRA;
	}
	public void setNumeroSituacaoRA(String numeroSituacaoRA) {
		this.numeroSituacaoRA = numeroSituacaoRA;
	}
   
	
	public RaDadosAgenciaReguladora setDadosAgenciaReguladora(RaDadosAgenciaReguladora raDadosAgenciaReguladora) {
		
		
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		registroAtendimento.setId(new Integer(getNumeroRADados()));
		raDadosAgenciaReguladora.setRegistroAtendimento(registroAtendimento);
		
		AgenciaReguladoraMotReclamacao agenciaReguladoraMotReclamacao = new AgenciaReguladoraMotReclamacao();
		agenciaReguladoraMotReclamacao.setId(new Integer(getMotivoReclamacao()));
		raDadosAgenciaReguladora.setAgenciaReguladoraMotReclamacao(agenciaReguladoraMotReclamacao);
		
		if (getIdMotivoEncerramento() != null && !getIdMotivoEncerramento().equalsIgnoreCase("")  ){
			
			AtendimentoMotivoEncerramento atendimentoMotivoEncerramento = new AtendimentoMotivoEncerramento();
			atendimentoMotivoEncerramento.setId(new Integer(getIdMotivoEncerramento()));
			raDadosAgenciaReguladora.setAtendimentoMotivoEncerramento(atendimentoMotivoEncerramento);
		}
		
		raDadosAgenciaReguladora.setAgenciaReguladora(getNumeroRegistroAgenciaReguladora());
		raDadosAgenciaReguladora.setDataPrevisaoOriginal(Util.converteStringParaDate(getDataPrevisaoOriginal()));
		
		raDadosAgenciaReguladora.setDataPrevisaoAtual(Util.converteStringParaDate(getDataPrevisaoAtual()));
		raDadosAgenciaReguladora.setDescricaoReclamacao(getReclamacao());
		
		raDadosAgenciaReguladora.setCodigoSituacaoArpe(new Short(getNumeroSituacaoRA()));		
		raDadosAgenciaReguladora.setCodigoSituacao(RegistroAtendimento.SITUACAO_PENDENTE);
		
	  return raDadosAgenciaReguladora;

	}
	
	
	
	}
