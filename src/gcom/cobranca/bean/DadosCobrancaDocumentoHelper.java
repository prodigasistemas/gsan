package gcom.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author Sávio Luiz, Ivan Sergio
 * @since 14/05/2007, 23/12/2010
 */
public class DadosCobrancaDocumentoHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idFiscalizacao;

	private Short indicadorAcimaAbaixo;

	private Short indicadorAcimaLimite;

	private int quantidadeDocumentos;

	private BigDecimal valorDocumentos;

	private Integer idCobrancaAcaoSituacao;

	private Integer idSituacaoDebito;

	private Integer idCategoria;

	private Integer idEsferaPoder;
	
	private int numeroQuadra;
	private int codigoSetorComercial;
	
	private Integer idGerenciaRegional;
	
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer idRota;
	private Integer idQuadra;
	private Integer idImovelPerfil;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idEmpresa;
	private Integer idCobrancaCriterio;
	private Integer idCobrancaGrupo;
	
	private Integer idAtendimentoMotivoEncerramento;
	
	private Integer idUnidadeNegocio;
	
	private Integer idDocumentoEmissaoForma;
	
	//*************************************************
	// RM3323
	// Autor: Ivan Sergio
	// Data: 23/12/2010
	// Alteracao para gerar consulta de tipo de corte;
	//*************************************************
	private Integer idCorteTipo;
	//*************************************************
	
	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	/**
	 * @param idUnidadeNegocio
	 *            O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public Integer getIdAtendimentoMotivoEncerramento() {
		return idAtendimentoMotivoEncerramento;
	}

	public void setIdAtendimentoMotivoEncerramento(
			Integer idAtendimentoMotivoEncerramento) {
		this.idAtendimentoMotivoEncerramento = idAtendimentoMotivoEncerramento;
	}

	/**
	 * 
	 */
	public DadosCobrancaDocumentoHelper() {

	}

	public DadosCobrancaDocumentoHelper(Integer idFiscalizacao,
			Short indicadorAcimaAbaixo, Short indicadorAcimaLimite,
			Integer idCobrancaAcaoSituacao, Integer idSituacaoDebito,
			Integer idCategoria,Integer idEsferaPoder,Integer idCobrancaCriterio,Integer idGerenciaRegional,Integer idLocalidade,
			Integer idSetorComercial,Integer idRota,Integer idQuadra,
			int numeroQuadra,int codigoSetorComercial,
			Integer idImovelPerfil,Integer idSituacaoLigacaoAgua,Integer idSituacaoLigacaoEsgoto,Integer idEmpresa,
			Integer idAtendimentoMotivoEncerramento, Integer idUnidadeNegocio, 
			int quantidadeDocumentos,BigDecimal valorDocumentos) {
		
		this.idFiscalizacao = idFiscalizacao;
		this.indicadorAcimaAbaixo = indicadorAcimaAbaixo;
		this.indicadorAcimaLimite = indicadorAcimaLimite;
		this.idCobrancaAcaoSituacao = idCobrancaAcaoSituacao;
		this.idSituacaoDebito = idSituacaoDebito;
		this.idCategoria = idCategoria;
		this.idEsferaPoder = idEsferaPoder;
		this.idCobrancaCriterio = idCobrancaCriterio;
		this.idGerenciaRegional = idGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.numeroQuadra = numeroQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.idImovelPerfil = idImovelPerfil;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idEmpresa = idEmpresa;
		this.quantidadeDocumentos = quantidadeDocumentos;
		this.valorDocumentos = valorDocumentos;
		setIdAtendimentoMotivoEncerramento(idAtendimentoMotivoEncerramento);
		setIdUnidadeNegocio(idUnidadeNegocio);
	}
	
	public DadosCobrancaDocumentoHelper(Integer idFiscalizacao,
			Short indicadorAcimaAbaixo, Short indicadorAcimaLimite,
			Integer idCobrancaAcaoSituacao, Integer idSituacaoDebito,
			Integer idCategoria,Integer idEsferaPoder,Integer idCobrancaCriterio,Integer idGerenciaRegional,Integer idLocalidade,
			Integer idSetorComercial,Integer idRota,Integer idQuadra,
			int numeroQuadra,int codigoSetorComercial,
			Integer idImovelPerfil,Integer idSituacaoLigacaoAgua,Integer idSituacaoLigacaoEsgoto,Integer idEmpresa,
			Integer idAtendimentoMotivoEncerramento, Integer idUnidadeNegocio, Integer idDocumentoEmissaoForma,
			int quantidadeDocumentos,BigDecimal valorDocumentos) {
		
		this.idFiscalizacao = idFiscalizacao;
		this.indicadorAcimaAbaixo = indicadorAcimaAbaixo;
		this.indicadorAcimaLimite = indicadorAcimaLimite;
		this.idCobrancaAcaoSituacao = idCobrancaAcaoSituacao;
		this.idSituacaoDebito = idSituacaoDebito;
		this.idCategoria = idCategoria;
		this.idEsferaPoder = idEsferaPoder;
		this.idCobrancaCriterio = idCobrancaCriterio;
		this.idGerenciaRegional = idGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.numeroQuadra = numeroQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.idImovelPerfil = idImovelPerfil;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idEmpresa = idEmpresa;
		this.quantidadeDocumentos = quantidadeDocumentos;
		this.valorDocumentos = valorDocumentos;
		setIdAtendimentoMotivoEncerramento(idAtendimentoMotivoEncerramento);
		setIdUnidadeNegocio(idUnidadeNegocio);
		setIdDocumentoEmissaoForma(idDocumentoEmissaoForma);
	}
	
	/**
	 * @param conta
	 * @param valorPago
	 * @param valorMulta
	 * @param valorJurosMora
	 * @param valoratualizacaoMonetaria
	 */
	public DadosCobrancaDocumentoHelper(Integer idFiscalizacao,
			Short indicadorAcimaAbaixo, Short indicadorAcimaLimite,
			Integer idCobrancaAcaoSituacao, Integer idSituacaoDebito,
			Integer idCategoria,Integer idEsferaPoder,Integer idCobrancaCriterio,Integer idCobrancaGrupo,Integer idGerenciaRegional,Integer idLocalidade,
			Integer idSetorComercial,Integer idRota,Integer idQuadra,
			int numeroQuadra,int codigoSetorComercial,
			Integer idImovelPerfil,Integer idSituacaoLigacaoAgua,Integer idSituacaoLigacaoEsgoto,Integer idEmpresa,
			Integer idAtendimentoMotivoEncerramento, Integer idUnidadeNegocio,
			int quantidadeDocumentos,BigDecimal valorDocumentos) {
		this.idFiscalizacao = idFiscalizacao;
		this.indicadorAcimaAbaixo = indicadorAcimaAbaixo;
		this.indicadorAcimaLimite = indicadorAcimaLimite;
		this.idCobrancaAcaoSituacao = idCobrancaAcaoSituacao;
		this.idSituacaoDebito = idSituacaoDebito;
		this.idCategoria = idCategoria;
		this.idEsferaPoder = idEsferaPoder;
		this.idCobrancaCriterio = idCobrancaCriterio;
		this.idCobrancaGrupo = idCobrancaGrupo;
		this.idGerenciaRegional = idGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.numeroQuadra = numeroQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.idImovelPerfil = idImovelPerfil;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idEmpresa = idEmpresa;
		this.idAtendimentoMotivoEncerramento = idAtendimentoMotivoEncerramento;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.quantidadeDocumentos = quantidadeDocumentos;
		this.valorDocumentos = valorDocumentos;

	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdCobrancaAcaoSituacao() {
		return idCobrancaAcaoSituacao;
	}

	public void setIdCobrancaAcaoSituacao(Integer idCobrancaAcaoSituacao) {
		this.idCobrancaAcaoSituacao = idCobrancaAcaoSituacao;
	}

	public Integer getIdFiscalizacao() {
		return idFiscalizacao;
	}

	public void setIdFiscalizacao(Integer idFiscalizacao) {
		this.idFiscalizacao = idFiscalizacao;
	}

	public Integer getIdSituacaoDebito() {
		return idSituacaoDebito;
	}

	public void setIdSituacaoDebito(Integer idSituacaoDebito) {
		this.idSituacaoDebito = idSituacaoDebito;
	}

	public Short getIndicadorAcimaAbaixo() {
		return indicadorAcimaAbaixo;
	}

	public void setIndicadorAcimaAbaixo(Short indicadorAcimaAbaixo) {
		this.indicadorAcimaAbaixo = indicadorAcimaAbaixo;
	}

	public Short getIndicadorAcimaLimite() {
		return indicadorAcimaLimite;
	}

	public void setIndicadorAcimaLimite(Short indicadorAcimaLimite) {
		this.indicadorAcimaLimite = indicadorAcimaLimite;
	}

	public int getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	public void setQuantidadeDocumentos(int quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	public BigDecimal getValorDocumentos() {
		return valorDocumentos;
	}

	public void setValorDocumentos(BigDecimal valorDocumentos) {
		this.valorDocumentos = valorDocumentos;
	}

	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}

	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	public int getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(int codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public int getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(int numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getIdImovelPerfil() {
		return idImovelPerfil;
	}

	public void setIdImovelPerfil(Integer idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}

	public Integer getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}

	public void setIdSituacaoLigacaoAgua(Integer idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}

	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}

	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}

	public Integer getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdCobrancaCriterio() {
		return idCobrancaCriterio;
	}

	public void setIdCobrancaCriterio(Integer idCobrancaCriterio) {
		this.idCobrancaCriterio = idCobrancaCriterio;
	}

	public Integer getIdCobrancaGrupo() {
		return idCobrancaGrupo;
	}

	public void setIdCobrancaGrupo(Integer idCobrancaGrupo) {
		this.idCobrancaGrupo = idCobrancaGrupo;
	}

	public Integer getIdDocumentoEmissaoForma() {
		return idDocumentoEmissaoForma;
	}

	public void setIdDocumentoEmissaoForma(Integer idDocumentoEmissaoForma) {
		this.idDocumentoEmissaoForma = idDocumentoEmissaoForma;
	}

	public Integer getIdCorteTipo() {
		return idCorteTipo;
	}

	public void setIdCorteTipo(Integer idCorteTipo) {
		this.idCorteTipo = idCorteTipo;
	}
}
