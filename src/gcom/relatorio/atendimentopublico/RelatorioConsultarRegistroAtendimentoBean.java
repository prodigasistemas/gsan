package gcom.relatorio.atendimentopublico;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import gcom.relatorio.RelatorioBean;

public class RelatorioConsultarRegistroAtendimentoBean implements RelatorioBean {

	/**
	 * @author Rafael Corrêa
	 * @date 18/09/2006
	 */
	private String numeroRA;

	private String situacaoRA;

	private String indicadorAssociado;

	private String numeroRAAssociado;

	private String situacaoRAAssociado;

	private String tipoSolicitacao;

	private String especificacao;

	private String dataAtendimento;

	private String dataPrevista;

	private String meioSolicitacao;

	private String unidadeAtendimento;

	private String unidadeAtual;

	private String observacao;

	private String matriculaImovel;

	private String inscricaoImovel;

	private String enderecoOcorrencia;

	private String pontoReferencia;

	private String municipio;

	private String bairro;

	private String areaBairro;

	private String localidadeSetorQuadra;

	private String divisaoEsgoto;

	private String localOcorrencia;

	private String pavimentoRua;

	private String pavimentoCalcada;

	private String descricaoLocalOcorrencia;

	private String codigoCliente;

	private String nomeCliente;

	private String unidadeSolicitante;

	private String codigoFuncionario;

	private String nomeFuncionario;

	private String nomeSolicitante;

	private String quantidade;

	private String dataReiteracao;

	private String motivoEncerramento;

	private String numeroRAReferencia;

	private String situacaoRAReferencia;

	private String dataEncerramento;

	private String unidadeEncerramento;

	private String usuarioEncerramento;

	private String parecerEncerramento;
	
	private String rota;
	
	private String sequencialRota;
	
	private String protocoloAtendimento;
	
	private JRBeanCollectionDataSource arrayJRDetail;
	
	private ArrayList arrayRelatorioConsultarRegistroAtendimentoDetailBean;

	

	/**
	 * Construtor de RelatorioConsultarRegistroAtendimentoBean
	 */
	public RelatorioConsultarRegistroAtendimentoBean(String numeroRA,
			String situacaoRA, String indicadorAssociado,
			String numeroRAAssociado, String situacaoRAAssociado,
			String tipoSolicitacao, String especificacao,
			String dataAtendimento, String dataPrevista,
			String meioSolicitacao, String unidadeAtendimento,
			String unidadeAtual, String observacao, String matriculaImovel,
			String inscricaoImovel, String enderecoOcorrencia,
			String pontoReferencia, String municipio, String bairro,
			String areaBairro, String localidadeSetorQuadra,
			String divisaoEsgoto, String localOcorrencia, String pavimentoRua,
			String pavimentoCalcada, String descricaoLocalOcorrencia,
			String codigoCliente, String nomeCliente,String protocolo,
			String unidadeSolicitante, String codigoFuncionario,
			String nomeFuncionario, String nomeSolicitante, 
			Collection colecaoDetail, String motivoEncerramento,
			String numeroRAReferencia, String situacaoRAReferencia,
			String dataEncerramento, String unidadeEncerramento,
			String usuarioEncerramento, String parecerEncerramento,
			String rota, String sequencialRota) {
		
		this.numeroRA = numeroRA;
		this.situacaoRA = situacaoRA;
		this.indicadorAssociado = indicadorAssociado;
		this.numeroRAAssociado = numeroRAAssociado;
		this.situacaoRAAssociado = situacaoRAAssociado;
		this.tipoSolicitacao = tipoSolicitacao;
		this.especificacao = especificacao;
		this.dataAtendimento = dataAtendimento;
		this.dataPrevista = dataPrevista;
		this.meioSolicitacao = meioSolicitacao;
		this.unidadeAtendimento = unidadeAtendimento;
		this.unidadeAtual = unidadeAtual;
		this.observacao = observacao;
		this.matriculaImovel = matriculaImovel;
		this.inscricaoImovel = inscricaoImovel;
		this.enderecoOcorrencia = enderecoOcorrencia;
		this.pontoReferencia = pontoReferencia;
		this.municipio = municipio;
		this.bairro = bairro;
		this.areaBairro = areaBairro;
		this.localidadeSetorQuadra = localidadeSetorQuadra;
		this.divisaoEsgoto = divisaoEsgoto;
		this.localOcorrencia = localOcorrencia;
		this.pavimentoRua = pavimentoRua;
		this.pavimentoCalcada = pavimentoCalcada;
		this.descricaoLocalOcorrencia = descricaoLocalOcorrencia;
		this.codigoCliente = codigoCliente;
		this.nomeCliente = nomeCliente;
		this.protocoloAtendimento = protocolo;
		this.unidadeSolicitante = unidadeSolicitante;
		this.codigoFuncionario = codigoFuncionario;
		this.nomeFuncionario = nomeFuncionario;
		this.nomeSolicitante = nomeSolicitante;
		this.arrayRelatorioConsultarRegistroAtendimentoDetailBean = new ArrayList();
		this.arrayRelatorioConsultarRegistroAtendimentoDetailBean.addAll(colecaoDetail);
		this.arrayJRDetail = new JRBeanCollectionDataSource(
				this.arrayRelatorioConsultarRegistroAtendimentoDetailBean);
		this.motivoEncerramento = motivoEncerramento;
		this.numeroRAReferencia = numeroRAReferencia;
		this.situacaoRAReferencia = situacaoRAReferencia;
		this.dataEncerramento = dataEncerramento;
		this.unidadeEncerramento = unidadeEncerramento;
		this.usuarioEncerramento = usuarioEncerramento;
		this.parecerEncerramento = parecerEncerramento;
		this.rota = rota;
		this.sequencialRota = sequencialRota;
		
	}
	
	public String getProtocoloAtendimento() {
		return protocoloAtendimento;
	}

	public void setProtocoloAtendimento(String protocoloAtendimento) {
		this.protocoloAtendimento = protocoloAtendimento;
	}

	/**
	 * @return Retorna o campo rota.
	 */
	public String getRota() {
		return rota;
	}

	/**
	 * @param rota O rota a ser setado.
	 */
	public void setRota(String rota) {
		this.rota = rota;
	}

	/**
	 * @return Retorna o campo sequencialRota.
	 */
	public String getSequencialRota() {
		return sequencialRota;
	}

	/**
	 * @param sequencialRota O sequencialRota a ser setado.
	 */
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public String getAreaBairro() {
		return areaBairro;
	}

	public void setAreaBairro(String areaBairro) {
		this.areaBairro = areaBairro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoFuncionario() {
		return codigoFuncionario;
	}

	public void setCodigoFuncionario(String codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
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

	public String getDataReiteracao() {
		return dataReiteracao;
	}

	public void setDataReiteracao(String dataReiteracao) {
		this.dataReiteracao = dataReiteracao;
	}

	public String getDescricaoLocalOcorrencia() {
		return descricaoLocalOcorrencia;
	}

	public void setDescricaoLocalOcorrencia(String descricaoLocalOcorrencia) {
		this.descricaoLocalOcorrencia = descricaoLocalOcorrencia;
	}

	public String getDivisaoEsgoto() {
		return divisaoEsgoto;
	}

	public void setDivisaoEsgoto(String divisaoEsgoto) {
		this.divisaoEsgoto = divisaoEsgoto;
	}

	public String getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}

	public void setEnderecoOcorrencia(String enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public String getIndicadorAssociado() {
		return indicadorAssociado;
	}

	public void setIndicadorAssociado(String indicadorAssociado) {
		this.indicadorAssociado = indicadorAssociado;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getLocalidadeSetorQuadra() {
		return localidadeSetorQuadra;
	}

	public void setLocalidadeSetorQuadra(String localidadeSetorQuadra) {
		this.localidadeSetorQuadra = localidadeSetorQuadra;
	}

	public String getLocalOcorrencia() {
		return localOcorrencia;
	}

	public void setLocalOcorrencia(String localOcorrencia) {
		this.localOcorrencia = localOcorrencia;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String getMeioSolicitacao() {
		return meioSolicitacao;
	}

	public void setMeioSolicitacao(String meioSolicitacao) {
		this.meioSolicitacao = meioSolicitacao;
	}

	public String getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(String motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
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

	public String getNumeroRAAssociado() {
		return numeroRAAssociado;
	}

	public void setNumeroRAAssociado(String numeroRAAssociado) {
		this.numeroRAAssociado = numeroRAAssociado;
	}

	public String getNumeroRAReferencia() {
		return numeroRAReferencia;
	}

	public void setNumeroRAReferencia(String numeroRAReferencia) {
		this.numeroRAReferencia = numeroRAReferencia;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getParecerEncerramento() {
		return parecerEncerramento;
	}

	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}

	public String getPavimentoCalcada() {
		return pavimentoCalcada;
	}

	public void setPavimentoCalcada(String pavimentoCalcada) {
		this.pavimentoCalcada = pavimentoCalcada;
	}

	public String getPavimentoRua() {
		return pavimentoRua;
	}

	public void setPavimentoRua(String pavimentoRua) {
		this.pavimentoRua = pavimentoRua;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getSituacaoRA() {
		return situacaoRA;
	}

	public void setSituacaoRA(String situacaoRA) {
		this.situacaoRA = situacaoRA;
	}

	public String getSituacaoRAAssociado() {
		return situacaoRAAssociado;
	}

	public void setSituacaoRAAssociado(String situacaoRAAssociado) {
		this.situacaoRAAssociado = situacaoRAAssociado;
	}

	public String getSituacaoRAReferencia() {
		return situacaoRAReferencia;
	}

	public void setSituacaoRAReferencia(String situacaoRAReferencia) {
		this.situacaoRAReferencia = situacaoRAReferencia;
	}

	public String getTipoSolicitacao() {
		return tipoSolicitacao;
	}

	public void setTipoSolicitacao(String tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}

	public String getUnidadeAtendimento() {
		return unidadeAtendimento;
	}

	public void setUnidadeAtendimento(String unidadeAtendimento) {
		this.unidadeAtendimento = unidadeAtendimento;
	}

	public String getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(String unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public String getUnidadeEncerramento() {
		return unidadeEncerramento;
	}

	public void setUnidadeEncerramento(String unidadeEncerramento) {
		this.unidadeEncerramento = unidadeEncerramento;
	}

	public String getUnidadeSolicitante() {
		return unidadeSolicitante;
	}

	public void setUnidadeSolicitante(String unidadeSolicitante) {
		this.unidadeSolicitante = unidadeSolicitante;
	}

	public String getUsuarioEncerramento() {
		return usuarioEncerramento;
	}

	public void setUsuarioEncerramento(String usuarioEncerramento) {
		this.usuarioEncerramento = usuarioEncerramento;
	}

	public JRBeanCollectionDataSource getArrayJRDetail() {
		return arrayJRDetail;
	}

	public void setArrayJRDetail(JRBeanCollectionDataSource arrayJRDetail) {
		this.arrayJRDetail = arrayJRDetail;
	}

	public ArrayList getArrayRelatorioConsultarRegistroAtendimentoDetailBean() {
		return arrayRelatorioConsultarRegistroAtendimentoDetailBean;
	}

	public void setArrayRelatorioConsultarRegistroAtendimentoDetailBean(
			ArrayList arrayRelatorioConsultarRegistroAtendimentoDetailBean) {
		this.arrayRelatorioConsultarRegistroAtendimentoDetailBean = arrayRelatorioConsultarRegistroAtendimentoDetailBean;
	}
	
	

}
