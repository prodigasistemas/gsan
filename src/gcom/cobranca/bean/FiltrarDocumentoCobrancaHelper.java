package gcom.cobranca.bean;

import java.math.BigDecimal;

/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de Documentos de Cobranca
 *
 * @author Anderson Italo
 * @date 14/08/2009
 */
public class FiltrarDocumentoCobrancaHelper {
	
	private Integer idImovel;
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidadeInicial;
	private Integer idLocalidadeFinal;
	private Integer idSetorComercialInicial;
	private Integer idSetorComercialFinal;
	private Integer idQuadraInicial;
	private Integer idQuadraFinal;
	private Integer[] idsDocumentoEmissaoForma;
	private Integer[] idsCobrancaAcao;
	private Integer[] idsCobrancaAcaoAtividadeCronograma;
	private String dataEmissaoInicial;
	private String dataEmissaoFinal;
	private BigDecimal valorDocumentoInicial;
	private BigDecimal valorDocumentoFinal;
	private Integer[] idsMotivoNaoEntrega;
	private Integer[] idsImovelPerfil;
	private Integer[] idsImovelCategoria;
	private Integer[] idsEmpresa;
	private Integer numeroPagina;
	private String ciclo;
	private Integer[] idsDebitoSituacao;
	private Integer[] idsAcaoSituacao;
	private Integer[] idsDocumentoCobranca;

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public FiltrarDocumentoCobrancaHelper(){}
	
	public String getDataEmissaoFinal() {
		return dataEmissaoFinal;
	}
	
	public void setDataEmissaoFinal(String dataEmissaoFinal) {
		this.dataEmissaoFinal = dataEmissaoFinal;
	}
	
	public String getDataEmissaoInicial() {
		return dataEmissaoInicial;
	}
	
	public void setDataEmissaoInicial(String dataEmissaoInicial) {
		this.dataEmissaoInicial = dataEmissaoInicial;
	}

	public Integer getIdImovel() {
		return idImovel;
	}
	
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}
	
	public Integer getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	
	public void setIdLocalidadeFinal(Integer idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	
	public Integer getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	
	public void setIdLocalidadeInicial(Integer idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}

	public Integer getIdSetorComercialInicial() {
		return idSetorComercialInicial;
	}
	
	public void setIdSetorComercialInicial(Integer idSetorComercialInicial) {
		this.idSetorComercialInicial = idSetorComercialInicial;
	}
	
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	
	public BigDecimal getValorDocumentoInicial() {
		return valorDocumentoInicial;
	}
	
	public void setValorDocumentoInicial(BigDecimal valorDocumentoInicial) {
		this.valorDocumentoInicial = valorDocumentoInicial;
	}

	public BigDecimal getValorDocumentoFinal() {
		return valorDocumentoFinal;
	}

	public void setValorDocumentoFinal(BigDecimal valorDocumentoFinal) {
		this.valorDocumentoFinal = valorDocumentoFinal;
	}

	public Integer getIdQuadraFinal() {
		return idQuadraFinal;
	}

	public void setIdQuadraFinal(Integer idQuadraFinal) {
		this.idQuadraFinal = idQuadraFinal;
	}

	public Integer getIdQuadraInicial() {
		return idQuadraInicial;
	}

	public void setIdQuadraInicial(Integer idQuadraInicial) {
		this.idQuadraInicial = idQuadraInicial;
	}

	public Integer[] getIdsCobrancaAcao() {
		return idsCobrancaAcao;
	}

	public void setIdsCobrancaAcao(Integer[] idsCobrancaAcao) {
		this.idsCobrancaAcao = idsCobrancaAcao;
	}

	public Integer[] getIdsDocumentoEmissaoForma() {
		return idsDocumentoEmissaoForma;
	}

	public void setIdsDocumentoEmissaoForma(
			Integer[] idsDocumentoEmissaoForma) {
		this.idsDocumentoEmissaoForma = idsDocumentoEmissaoForma;
	}

	public Integer[] getIdsEmpresa() {
		return idsEmpresa;
	}

	public void setIdsEmpresa(Integer[] idsEmpresa) {
		this.idsEmpresa = idsEmpresa;
	}

	public Integer getIdSetorComercialFinal() {
		return idSetorComercialFinal;
	}

	public void setIdSetorComercialFinal(Integer idSetorComercialFinal) {
		this.idSetorComercialFinal = idSetorComercialFinal;
	}

	public Integer[] getIdsImovelCategoria() {
		return idsImovelCategoria;
	}

	public void setIdsImovelCategoria(Integer[] idsImovelCategoria) {
		this.idsImovelCategoria = idsImovelCategoria;
	}

	public Integer[] getIdsImovelPerfil() {
		return idsImovelPerfil;
	}

	public void setIdsImovelPerfil(Integer[] idsImovelPerfil) {
		this.idsImovelPerfil = idsImovelPerfil;
	}

	public Integer[] getIdsMotivoNaoEntrega() {
		return idsMotivoNaoEntrega;
	}

	public void setIdsMotivoNaoEntrega(Integer[] idsMotivoNaoEntrega) {
		this.idsMotivoNaoEntrega = idsMotivoNaoEntrega;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	
	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public Integer[] getIdsCobrancaAcaoAtividadeCronograma() {
		return idsCobrancaAcaoAtividadeCronograma;
	}

	public void setIdsCobrancaAcaoAtividadeCronograma(
			Integer[] idsCobrancaAcaoAtividadeCronograma) {
		this.idsCobrancaAcaoAtividadeCronograma = idsCobrancaAcaoAtividadeCronograma;
	}

	public Integer[] getIdsAcaoSituacao() {
		return idsAcaoSituacao;
	}

	public void setIdsAcaoSituacao(Integer[] idsAcaoSituacao) {
		this.idsAcaoSituacao = idsAcaoSituacao;
	}

	public Integer[] getIdsDebitoSituacao() {
		return idsDebitoSituacao;
	}

	public void setIdsDebitoSituacao(Integer[] idsDebitoSituacao) {
		this.idsDebitoSituacao = idsDebitoSituacao;
	}
	
	public Integer[] getIdsDocumentoCobranca() {
		return idsDocumentoCobranca;
	}

	public void setIdsDocumentoCobranca(Integer[] idsDocumentoCobranca) {
		this.idsDocumentoCobranca = idsDocumentoCobranca;
	}

}
