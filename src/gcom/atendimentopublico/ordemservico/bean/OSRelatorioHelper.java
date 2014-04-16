package gcom.atendimentopublico.ordemservico.bean;

import gcom.micromedicao.bean.AnaliseConsumoRelatorioOSHelper;
import gcom.micromedicao.bean.HidrometroRelatorioOSHelper;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC0450] Filtrar Registro de Atendimento
 * 
 * Classe facilitadora para o retorno do filtro a ser usado no manter.
 * 
 * @author Rafael Pinto
 * @date 18/08/2006
 */
public class OSRelatorioHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idOrdemServico;

	private Date dataGeracao;

	private String inscricaoImovel;

	private Integer idImovel;

	private String categoria;

	private int quantidadeEconomias;

	private String unidadeGeracao;

	private String situacaoAgua;

	private String situacaoEsgoto;

	private Integer esgotoFixo;

	private String pavimentoRua;

	private String pavimentoCalcada;

	private String meio;

	private String nomeAtendente;

	private Integer idAtendente;

	private String endereco;

	private String pontoReferencia;

	private String telefone;

	private Integer idServicoSolicitado;

	private String descricaoServicoSolicitado;

	private String valorServicoSolicitado;

	private String localOcorrencia;

	private Date previsao;

	private Integer idRA;

	private String observacaoRA;

	private String observacaoOS;

	private Integer idSolicitante;

	private String nomeSolicitante;

	private Integer idUnidade;

	private String descricaoUnidade;

	private String especificao;

	private Short tempoMedioExecucao;

	private String origem;

	private Integer idLocalidade;

	private Integer sequencialRota;

	private Short codigoRota;

	private Integer idServicoTipoReferencia;

	private String descricaoServicoTipoReferencia;

	private Date dataEncerramento;

	private String parecerEncerramento;

	private String nomeUsuarioImovel;

	private String cpfCliente;

	private String cnpjCliente;

	private Date dataEmissao;

	private String idCobrancaDocumento;

	private AnaliseConsumoRelatorioOSHelper analiseConsumoRelatorioOSHelper;

	private HidrometroRelatorioOSHelper hidrometroRelatorioOSHelper;
	
	private String nomeProjeto;

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public OSRelatorioHelper() {
	}

	public String getIdCobrancaDocumento() {
		return idCobrancaDocumento;
	}

	public void setIdCobrancaDocumento(String idCobrancaDocumento) {
		this.idCobrancaDocumento = idCobrancaDocumento;
	}

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getSequencialRota() {
		return sequencialRota;
	}

	public void setSequencialRota(Integer sequencialRota) {
		this.sequencialRota = sequencialRota;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public Short getTempoMedioExecucao() {
		return tempoMedioExecucao;
	}

	public void setTempoMedioExecucao(Short tempoMedioExecucao) {
		this.tempoMedioExecucao = tempoMedioExecucao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getEsgotoFixo() {
		return esgotoFixo;
	}

	public void setEsgotoFixo(Integer esgotoFixo) {
		this.esgotoFixo = esgotoFixo;
	}

	public Integer getIdAtendente() {
		return idAtendente;
	}

	public void setIdAtendente(Integer idAtendente) {
		this.idAtendente = idAtendente;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public Integer getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(Integer idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public String getObservacaoOS() {
		return observacaoOS;
	}

	public void setObservacaoOS(String observacaoOS) {
		this.observacaoOS = observacaoOS;
	}

	public String getObservacaoRA() {
		return observacaoRA;
	}

	public void setObservacaoRA(String observacaoRA) {
		this.observacaoRA = observacaoRA;
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	public String getLocalOcorrencia() {
		return localOcorrencia;
	}

	public void setLocalOcorrencia(String localOcorrencia) {
		this.localOcorrencia = localOcorrencia;
	}

	public String getMeio() {
		return meio;
	}

	public void setMeio(String meio) {
		this.meio = meio;
	}

	public String getNomeAtendente() {
		return nomeAtendente;
	}

	public void setNomeAtendente(String nomeAtendente) {
		this.nomeAtendente = nomeAtendente;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
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

	public Date getPrevisao() {
		return previsao;
	}

	public void setPrevisao(Date previsao) {
		this.previsao = previsao;
	}

	public int getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	public void setQuantidadeEconomias(int quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	public String getDescricaoServicoSolicitado() {
		return descricaoServicoSolicitado;
	}

	public void setDescricaoServicoSolicitado(String descricaoServicoSolicitado) {
		this.descricaoServicoSolicitado = descricaoServicoSolicitado;
	}

	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getUnidadeGeracao() {
		return unidadeGeracao;
	}

	public void setUnidadeGeracao(String unidadeGeracao) {
		this.unidadeGeracao = unidadeGeracao;
	}

	public Integer getIdServicoSolicitado() {
		return idServicoSolicitado;
	}

	public void setIdServicoSolicitado(Integer idServicoSolicitado) {
		this.idServicoSolicitado = idServicoSolicitado;
	}

	public Integer getIdRA() {
		return idRA;
	}

	public void setIdRA(Integer idRA) {
		this.idRA = idRA;
	}

	/**
	 * @return Retorna o campo descricaoUnidade.
	 */
	public String getDescricaoUnidade() {
		return descricaoUnidade;
	}

	/**
	 * @param descricaoUnidade
	 *            O descricaoUnidade a ser setado.
	 */
	public void setDescricaoUnidade(String descricaoUnidade) {
		this.descricaoUnidade = descricaoUnidade;
	}

	/**
	 * @return Retorna o campo idUnidade.
	 */
	public Integer getIdUnidade() {
		return idUnidade;
	}

	/**
	 * @param idUnidade
	 *            O idUnidade a ser setado.
	 */
	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getEspecificao() {
		return especificao;
	}

	public void setEspecificao(String especificao) {
		this.especificao = especificao;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo descricaoServicoTipoReferencia.
	 */
	public String getDescricaoServicoTipoReferencia() {
		return descricaoServicoTipoReferencia;
	}

	/**
	 * @param descricaoServicoTipoReferencia
	 *            O descricaoServicoTipoReferencia a ser setado.
	 */
	public void setDescricaoServicoTipoReferencia(
			String descricaoServicoTipoReferencia) {
		this.descricaoServicoTipoReferencia = descricaoServicoTipoReferencia;
	}

	/**
	 * @return Retorna o campo idServicoTipoReferencia.
	 */
	public Integer getIdServicoTipoReferencia() {
		return idServicoTipoReferencia;
	}

	/**
	 * @param idServicoTipoReferencia
	 *            O idServicoTipoReferencia a ser setado.
	 */
	public void setIdServicoTipoReferencia(Integer idServicoTipoReferencia) {
		this.idServicoTipoReferencia = idServicoTipoReferencia;
	}

	public AnaliseConsumoRelatorioOSHelper getAnaliseConsumoRelatorioOSHelper() {
		return analiseConsumoRelatorioOSHelper;
	}

	public void setAnaliseConsumoRelatorioOSHelper(
			AnaliseConsumoRelatorioOSHelper analiseConsumoRelatorioOSHelper) {
		this.analiseConsumoRelatorioOSHelper = analiseConsumoRelatorioOSHelper;
	}

	public HidrometroRelatorioOSHelper getHidrometroRelatorioOSHelper() {
		return hidrometroRelatorioOSHelper;
	}

	public void setHidrometroRelatorioOSHelper(
			HidrometroRelatorioOSHelper hidrometroRelatorioOSHelper) {
		this.hidrometroRelatorioOSHelper = hidrometroRelatorioOSHelper;
	}

	public String getValorServicoSolicitado() {
		return valorServicoSolicitado;
	}

	public void setValorServicoSolicitado(String valorServicoSolicitado) {
		this.valorServicoSolicitado = valorServicoSolicitado;
	}

	/**
	 * @return Retorna o campo dataEncerramento.
	 */
	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	/**
	 * @param dataEncerramento
	 *            O dataEncerramento a ser setado.
	 */
	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	/**
	 * @return Retorna o campo parecerEncerramento.
	 */
	public String getParecerEncerramento() {
		return parecerEncerramento;
	}

	/**
	 * @param parecerEncerramento
	 *            O parecerEncerramento a ser setado.
	 */
	public void setParecerEncerramento(String parecerEncerramento) {
		this.parecerEncerramento = parecerEncerramento;
	}

	public String getNomeUsuarioImovel() {
		return nomeUsuarioImovel;
	}

	public void setNomeUsuarioImovel(String nomeUsuarioImovel) {
		this.nomeUsuarioImovel = nomeUsuarioImovel;
	}

	public String getCnpjCliente() {
		return cnpjCliente;
	}

	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

}
