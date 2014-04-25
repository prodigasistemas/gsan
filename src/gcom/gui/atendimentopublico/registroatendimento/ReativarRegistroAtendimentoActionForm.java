package gcom.gui.atendimentopublico.registroatendimento;

import java.util.Date;

import gcom.atendimentopublico.registroatendimento.LocalOcorrencia;
import gcom.atendimentopublico.registroatendimento.MeioSolicitacao;
import gcom.atendimentopublico.registroatendimento.RaMotivoReativacao;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.operacional.DivisaoEsgoto;
import gcom.util.Util;

import org.apache.struts.validator.ValidatorForm;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para realização
 * da reativação de um R.A 
 *
 * @author Ana Maria
 * @date 17/08/2006
 */
public class ReativarRegistroAtendimentoActionForm extends ValidatorForm{
	private static final long serialVersionUID = 1L;
	//Dados Gerais do RA
	// Dados Gerais, Dados do Solicitante & Dados do Local de Ocorrência
    private String numeroRA;
    private String situacaoRA;
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
    private String municipioId;
    private String municipioDescricao;
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
    private String protocoloAtendimento;
    
	//Dados Novo Registro de Atendimento
    private String tipoAtendimento;
    private String dataAtendimentoReativado;
    private String horaAtendimentoReativado;
    private String tempoEsperaInicial;
    private String tempoEsperaFinal;
    private String dataPrevistaReativado;
    private String meioSolicitacao;
    
    private String idUnidadeAtendimento;
    private String unidadeAtendimento;
    
    private String motivoReativacao;
    
    private String idUnidadeDestino;
    private String unidadeDestino;
    private String parecerUnidadeDestino;
    private String observacao;
    private String numeroImovel;
    
    // Controle
	private String validaUnidadeDestino = "false";
	private String validaUnidadeAtendimento = "false";
	private String resetarReativar = "false";
	
	public void resetarReativar(){
	    this.setSituacaoRA(null);
	    this.setNumeroRaAssociado(null);
	    this.setSituacaoRaAssociado(null);
	    this.setTipoSolicitacaoId(null);
	    this.setTipoSolicitacaoDescricao(null);
	    this.setEspecificacaoId(null);
	    this.setEspecificacaoDescricao(null);    
	    this.setMeioSolicitacaoId(null);
	    this.setMeioSolicitacaoDescricao(null);    
	    this.setMatriculaImovel(null);
	    this.setInscricaoImovel(null);
	    this.setDataAtendimento(null);
	    this.setHoraAtendimento(null);
	    this.setDataPrevista(null);
	    this.setDataEncerramento(null);
	    this.setIdMotivoEncerramento(null);
	    this.setMotivoEncerramento(null);
	    this.setIdRaSolicitante(null);
	    this.setIdClienteSolicitante(null);
	    this.setClienteSolicitante(null);
	    this.setIdUnidadeSolicitante(null);
	    this.setUnidadeSolicitante(null);
	    this.setNomeSolicitante(null);
	    this.setEnderecoOcorrencia(null);
	    this.setPontoReferencia(null);
	    this.setMunicipioId(null);
	    this.setMunicipioDescricao(null);
	    this.setBairroId(null);
	    this.setBairroDescricao(null);
	    this.setAreaBairroId(null);
	    this.setAreaBairroDescricao(null);
	    this.setLocalidadeId(null);
	    this.setLocalidadeDescricao(null);
	    this.setSetorComercialId(null);
	    this.setSetorComercialCodigo(null);
	    this.setQuadraId(null);
	    this.setQuadraNumero(null);
	    this.setDivisaoEsgotoId(null);
	    this.setDivisaoEsgotoDescricao(null);
	    this.setUnidadeAtendimentoId(null);
	    this.setUnidadeAtendimentoDescricao(null);
	    this.setUnidadeAtualId(null);
	    this.setUnidadeAtualDescricao(null);
	    this.setLogradouroBairro(null);
	    this.setLogradouroCep(null);
	    this.setComplementoEndereco(null);
	    this.setLocalOcorrencia(null);
	    this.setPavimentoRua(null);
	    this.setPavimentoCalcada(null);
	    this.setDescricaoLocalOcorrencia(null);
	    
	    this.setTipoAtendimento(null);
	    this.setDataAtendimentoReativado(null);
	    this.setHoraAtendimentoReativado(null);
	    this.setTempoEsperaInicial(null);
	    this.setTempoEsperaFinal(null);
	    this.setDataPrevistaReativado(null);
	    this.setMeioSolicitacao(null);
	    
	    this.setIdUnidadeAtendimento(null);
	    this.setUnidadeAtendimento(null);
	    
	    this.setMotivoReativacao(null);
	    
	    this.setIdUnidadeDestino(null);
	    this.setUnidadeDestino(null);
	    this.setParecerUnidadeDestino(null);
	    this.setObservacao(null);
	    
		this.setEspecificacaoId(null);
		this.setNumeroImovel(null);
		this.setValidaUnidadeDestino("false");
		this.setValidaUnidadeAtendimento("false");
		this.setResetarReativar("false");
	}

	public String getDataAtendimentoReativado() {
		return dataAtendimentoReativado;
	}
	public void setDataAtendimentoReativado(String dataAtendimentoReativado) {
		this.dataAtendimentoReativado = dataAtendimentoReativado;
	}
	public String getDataPrevistaReativado() {
		return dataPrevistaReativado;
	}
	public void setDataPrevistaReativado(String dataPrevistaReativado) {
		this.dataPrevistaReativado = dataPrevistaReativado;
	}
	public String getHoraAtendimentoReativado() {
		return horaAtendimentoReativado;
	}
	public void setHoraAtendimentoReativado(String horaAtendimentoReativado) {
		this.horaAtendimentoReativado = horaAtendimentoReativado;
	}
	public String getIdUnidadeAtendimento() {
		return idUnidadeAtendimento;
	}
	public void setIdUnidadeAtendimento(String idUnidadeAtendimento) {
		this.idUnidadeAtendimento = idUnidadeAtendimento;
	}
	public String getMeioSolicitacao() {
		return meioSolicitacao;
	}
	public void setMeioSolicitacao(String meioSolicitacao) {
		this.meioSolicitacao = meioSolicitacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getTempoEsperaFinal() {
		return tempoEsperaFinal;
	}
	public void setTempoEsperaFinal(String tempoEsperaFinal) {
		this.tempoEsperaFinal = tempoEsperaFinal;
	}
	public String getTempoEsperaInicial() {
		return tempoEsperaInicial;
	}
	public void setTempoEsperaInicial(String tempoEsperaInicial) {
		this.tempoEsperaInicial = tempoEsperaInicial;
	}
	public String getTipoAtendimento() {
		return tipoAtendimento;
	}
	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}
	public String getUnidadeAtendimento() {
		return unidadeAtendimento;
	}
	public void setUnidadeAtendimento(String unidadeAtendimento) {
		this.unidadeAtendimento = unidadeAtendimento;
	}
	public String getIdUnidadeDestino() {
		return idUnidadeDestino;
	}
	public void setIdUnidadeDestino(String idUnidadeDestino) {
		this.idUnidadeDestino = idUnidadeDestino;
	}
	public String getUnidadeDestino() {
		return unidadeDestino;
	}
	public void setUnidadeDestino(String unidadeDestino) {
		this.unidadeDestino = unidadeDestino;
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
	public String getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
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
	public String getMunicipioDescricao() {
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
	}
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
	public String getParecerUnidadeDestino() {
		return parecerUnidadeDestino;
	}
	public void setParecerUnidadeDestino(String parecerUnidadeDestino) {
		this.parecerUnidadeDestino = parecerUnidadeDestino;
	}
	public String getMotivoReativacao() {
		return motivoReativacao;
	}
	public void setMotivoReativacao(String motivoReativacao) {
		this.motivoReativacao = motivoReativacao;
	}
    
	public String getComplementoEndereco() {
		return complementoEndereco;
	}
	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}
	public String getDescricaoLocalOcorrencia() {
		return descricaoLocalOcorrencia;
	}
	public void setDescricaoLocalOcorrencia(String descricaoLocalOcorrencia) {
		this.descricaoLocalOcorrencia = descricaoLocalOcorrencia;
	}
	public Integer getLocalOcorrencia() {
		return localOcorrencia;
	}
	public void setLocalOcorrencia(Integer localOcorrencia) {
		this.localOcorrencia = localOcorrencia;
	}
	public Integer getLogradouroCep() {
		return logradouroCep;
	}
	public void setLogradouroCep(Integer logradouroCep) {
		this.logradouroCep = logradouroCep;
	}
	public Integer getLogradouroBairro() {
		return logradouroBairro;
	}
	public void setLogradouroBairro(Integer logradouroBairro) {
		this.logradouroBairro = logradouroBairro;
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
	public Integer getIdRaSolicitante() {
		return idRaSolicitante;
	}
	public void setIdRaSolicitante(Integer idRaSolicitante) {
		this.idRaSolicitante = idRaSolicitante;
	}
	public String getNumeroImovel() {
		return numeroImovel;
	}
	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public RegistroAtendimento setFormValues(RegistroAtendimento ra) {
		
		/*
		 * Dados Gerais do Registro Atendimento
		 */

		ra.setIndicadorAtendimentoOnline(Short.parseShort(getTipoAtendimento()));
		// Data e Hora do atendimento
		String dataHoraAtendimento = getDataAtendimentoReativado() + " " + getHoraAtendimentoReativado() +":00";
		Date dataHoraAtendimentoObjetoDate = Util.converteStringParaDateHora(dataHoraAtendimento);
		ra.setRegistroAtendimento(dataHoraAtendimentoObjetoDate);
		// Tempo de espera inicial
		if (getTempoEsperaInicial() != null && !getTempoEsperaInicial().equals("")) {
			Date dataEsperaInicial = Util.converteStringParaDateHora(getDataAtendimentoReativado()
							+ " " + getTempoEsperaInicial()+":00");
			ra.setDataInicioEspera(dataEsperaInicial);
			Date dataEsperaFinal = Util.converteStringParaDateHora(getDataAtendimentoReativado()
							+ " " + getTempoEsperaFinal()+":00");
			ra.setDataFimEspera(dataEsperaFinal);
		}
		// Meio de Solicitação
		MeioSolicitacao meioSolicitacao = new MeioSolicitacao();
		meioSolicitacao.setId(Integer.parseInt(getMeioSolicitacaoId()));
		ra.setMeioSolicitacao(meioSolicitacao);
		// Especificação
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = new SolicitacaoTipoEspecificacao();
		solicitacaoTipoEspecificacao.setId(Integer.parseInt(getEspecificacaoId()));
		ra.setSolicitacaoTipoEspecificacao(solicitacaoTipoEspecificacao);
		// Data Original e Data Prevista
		Date dataPrevistaObjetoDate = Util.converteStringParaDate(getDataPrevistaReativado());
		ra.setDataPrevistaOriginal(dataPrevistaObjetoDate);
		ra.setDataPrevistaAtual(dataPrevistaObjetoDate);
		// Observação
		if(getObservacao() != null && !getObservacao().trim().equals("")){
		  ra.setObservacao(getObservacao());
		}  
		
		/*
		 * Dados do Local da ocorrência
		 */			
		// Imóvel
		if (getMatriculaImovel() != null && !getMatriculaImovel().equalsIgnoreCase("")) {
			Imovel imovel = new Imovel();
			imovel.setId(Integer.parseInt(getMatriculaImovel()));
			ra.setImovel(imovel);
		}
		// Logradouro Bairro
		if (getLogradouroBairro() != null && !getLogradouroBairro().equals("")) {
			LogradouroBairro logradouroBairro = new LogradouroBairro();
			logradouroBairro.setId(getLogradouroBairro());
			ra.setLogradouroBairro(logradouroBairro);
		}
		// Logradouro Cep
		if (getLogradouroCep() != null) {
			LogradouroCep logradouroCep = new LogradouroCep();
			logradouroCep.setId(getLogradouroCep());
			ra.setLogradouroCep(logradouroCep);
		}
		// Complemento do Endereço
		ra.setComplementoEndereco(getComplementoEndereco());
		
		//Numero Imovel
		if ( getNumeroImovel() != null ) {
			ra.setNumeroImovel( getNumeroImovel() );
		}
		// Ponto de Referência
		if(getPontoReferencia() != null && !getPontoReferencia().trim().equals("")){
		  ra.setPontoReferencia(getPontoReferencia());
		}
		// Área do Bairro
		if (getAreaBairroId() != null && !getAreaBairroId().equals("")) {
			BairroArea bairroArea = new BairroArea();
			bairroArea.setId(Integer.parseInt(getAreaBairroId()));
			ra.setBairroArea(bairroArea);
		}
		// Localidade
		if (getLocalidadeId() != null && !getLocalidadeId().equals("")) {
			Localidade localidade = new Localidade();
			localidade.setId(Integer.parseInt(getLocalidadeId()));
			ra.setLocalidade(localidade);
		}
		// Setor Comercial
		if (getSetorComercialId() != null && !getSetorComercialId().trim().equals("")) {
			SetorComercial setorComercial = new SetorComercial();
			setorComercial.setId(Integer.parseInt(getSetorComercialId()));
			ra.setSetorComercial(setorComercial);
		}
		// Quadra
		if (getQuadraId() != null && !getQuadraId().equals("")) {
			Quadra quadra = new Quadra();
			quadra.setId(Integer.parseInt(getQuadraId()));
			ra.setQuadra(quadra);
		}
		// Divisão de Esgoto
		if (getDivisaoEsgotoId() != null && !getDivisaoEsgotoId().equals("")) {
			DivisaoEsgoto divisaoEsgoto = new DivisaoEsgoto();
			divisaoEsgoto.setId(Integer.parseInt(getDivisaoEsgotoId()));
			ra.setDivisaoEsgoto(divisaoEsgoto);
		}
		// Local de ocorrência
		if (getLocalOcorrencia() != null && !getLocalOcorrencia().equals("")) {
			LocalOcorrencia localOcorrencia = new LocalOcorrencia();
			localOcorrencia.setId(getLocalOcorrencia());
			ra.setLocalOcorrencia(localOcorrencia);
		}
		// Pavimento Rua
		if (getPavimentoRua() != null && !getPavimentoRua().equals("")) {
			PavimentoRua pavimentoRua = new PavimentoRua();
			pavimentoRua.setId(getPavimentoRua());
			ra.setPavimentoRua(pavimentoRua);
		}
		// Pavimento Calçada
		if (getPavimentoCalcada() != null && !getPavimentoCalcada().equals("")) {
			PavimentoCalcada pavimentoCalcada = new PavimentoCalcada();
			pavimentoCalcada.setId(getPavimentoCalcada());
			ra.setPavimentoCalcada(pavimentoCalcada);
		}
		// Descrição Local de ocorrência
		ra.setDescricaoLocalOcorrencia(getDescricaoLocalOcorrencia());

		/*
		 * Outros Dados do RA
		 */
		//Motivo de reativação
		if (getMotivoReativacao() != null) {
			RaMotivoReativacao raMotivoReativacao = new RaMotivoReativacao();
			raMotivoReativacao.setId(Integer.parseInt(getMotivoReativacao()));
			ra.setRaMotivoReativacao(raMotivoReativacao);
		}
		//Ra Reativado
		if (getNumeroRA() != null) {
			RegistroAtendimento raReativacao = new RegistroAtendimento();
			raReativacao.setId(Integer.parseInt(getNumeroRA()));
			ra.setRegistroAtendimentoReativacao(raReativacao);
		}		
		//Código da Situação
		ra.setCodigoSituacao(RegistroAtendimento.SITUACAO_PENDENTE);
		
		// inclusão da coluna unidade atual na tabela REGISTRO_ATENDIMENTO
		// Vivianne Sousa 10/06/2008 analista:Fátima Sampaio
		ra.setUnidadeAtual(null);
		
		// Última alteração
		ra.setUltimaAlteracao(new Date());
		
	  return ra;
	}
	public String getValidaUnidadeAtendimento() {
		return validaUnidadeAtendimento;
	}
	public void setValidaUnidadeAtendimento(String validaUnidadeAtendimento) {
		this.validaUnidadeAtendimento = validaUnidadeAtendimento;
	}
	public String getValidaUnidadeDestino() {
		return validaUnidadeDestino;
	}
	public void setValidaUnidadeDestino(String validaUnidadeDestino) {
		this.validaUnidadeDestino = validaUnidadeDestino;
	}
	/**
	 * @return Retorna o campo resetarReativar.
	 */
	public String getResetarReativar() {
		return resetarReativar;
	}
	/**
	 * @param resetarReativar O resetarReativar a ser setado.
	 */
	public void setResetarReativar(String resetarReativar) {
		this.resetarReativar = resetarReativar;
	}

	public String getProtocoloAtendimento() {
		return protocoloAtendimento;
	}

	public void setProtocoloAtendimento(String protocoloAtendimento) {
		this.protocoloAtendimento = protocoloAtendimento;
	}
}
