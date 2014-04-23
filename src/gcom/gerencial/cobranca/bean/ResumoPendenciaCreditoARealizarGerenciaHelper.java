package gcom.gerencial.cobranca.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Classe responsável por ajudar o caso de uso [UC0335] Gerar Resumo da Pendencia
 * [SB0004A] 
 *
 * @author Bruno Barros
 * @date 07/08/2007
 */
public class ResumoPendenciaCreditoARealizarGerenciaHelper {	
	
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idElo;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer idRota;
	private Integer idQuadra;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Integer idPerfilImovel;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idPrincipalCategoriaImovel;
	private Integer idPrincipalSubCategoriaImovel;
	private Integer idEsferaPoder;
	private Integer idTipoClienteResponsavel;
	private Integer idPerfilLigacaoAgua;
	private Integer idPerfilLigacaoEsgoto;
	private Integer idHidrometro;
	private Integer idVolFixadoAgua;
	private Integer idVolFixadoEsgoto;
	private Integer idTipoDocumento;
	private Integer anoMesReferenciaDocumento;
	private Integer quantidadeLigacoes = 0;
	private Integer quantidadeDocumentos = 1;
	private BigDecimal valorPendenteCredito = new BigDecimal(0);
	private Integer idTipoTarifaConsumo;
	private Short cdRota;
	
	public Short getCdRota() {
		return cdRota;
	}
	public void setCdRota(Short cdRota) {
		this.cdRota = cdRota;
	}
	public Integer getAnoMesReferenciaDocumento() {
		return anoMesReferenciaDocumento;
	}
	public void setAnoMesReferenciaDocumento(Integer anoMesReferenciaDocumento) {
		this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
	}
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComericial(Integer codigoSetorComericial) {
		this.codigoSetorComercial = codigoSetorComericial;
	}
	public Integer getIdElo() {
		return idElo;
	}
	public void setIdElo(Integer idElo) {
		this.idElo = idElo;
	}
	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}
	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
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
	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}
	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}
	public Integer getIdPerfilLigacaoAgua() {
		return idPerfilLigacaoAgua;
	}
	public void setIdPerfilLigacaoAgua(Integer idPerfilLigacaoAgua) {
		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;
	}
	public Integer getIdPerfilLigacaoEsgoto() {
		return idPerfilLigacaoEsgoto;
	}
	public void setIdPerfilLigacaoEsgoto(Integer idPerfilLigacaoEsgoto) {
		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;
	}
	public Integer getIdPrincipalCategoriaImovel() {
		return idPrincipalCategoriaImovel;
	}
	public void setIdPrincipalCategoriaImovel(Integer idPrincipalCategoriaImovel) {
		this.idPrincipalCategoriaImovel = idPrincipalCategoriaImovel;
	}
	public Integer getIdPrincipalSubCategoriaImovel() {
		return idPrincipalSubCategoriaImovel;
	}
	public void setIdPrincipalSubCategoriaImovel(Integer idPrincipalSubCategoriaImovel) {
		this.idPrincipalSubCategoriaImovel = idPrincipalSubCategoriaImovel;
	}
	public Integer getIdQuadra() {
		return idQuadra;
	}
	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
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
	public Integer getIdTipoClienteResponsavel() {
		return idTipoClienteResponsavel;
	}
	public void setIdTipoClienteResponsavel(Integer idTipoClienteResponsavel) {
		this.idTipoClienteResponsavel = idTipoClienteResponsavel;
	}
	public Integer getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public Integer getIdVolFixadoAgua() {
		return idVolFixadoAgua;
	}
	public void setIdVolFixadoAgua(Integer idVolFixadoAgua) {
		this.idVolFixadoAgua = idVolFixadoAgua;
	}
	public Integer getIdVolFixadoEsgoto() {
		return idVolFixadoEsgoto;
	}
	public void setIdVolFixadoEsgoto(Integer idVolFixadoEsgoto) {
		this.idVolFixadoEsgoto = idVolFixadoEsgoto;
	}
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	public Integer getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}
	public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}
	public BigDecimal getValorPendenteCredito() {
		return valorPendenteCredito;
	}
	public void setValorPendenteCredito(BigDecimal valorPendenteCredito) {
		if (valorPendenteCredito != null ){
			this.valorPendenteCredito = valorPendenteCredito;
		}
	}
	
	/**
	 * Construtor com os campos iniciais do objeto
	 * 
	 * @param idGerenciaRegional
	 * @param idUnidadeNegocio
	 * @param idElo
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param idRota
	 * @param idQuadra
	 * @param codigoSetorComericial
	 * @param numeroQuadra
	 * @param idPerfilImovel
	 * @param idSituacaoLigacaoAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param idPerfilLigacaoAgua
	 * @param idPerfilLigacaoEsgoto
	 * @param idVolFixadoAgua
	 * @param idVolFixadoEsgoto
	 * @param idTipoDocumento
	 * @param anoMesReferenciaDocumento
	 */
	public ResumoPendenciaCreditoARealizarGerenciaHelper(
			Integer idGerenciaRegional, // 0
			Integer idUnidadeNegocio, // 1
			Integer idElo, // 2
			Integer idLocalidade,// 3 
			Integer idSetorComercial,// 4 
			Integer idRota, // 5
			Integer idQuadra, // 6
			Integer codigoSetorComercial,// 7 
			Integer numeroQuadra, // 8
			Integer idPerfilImovel, // 9
			Integer idSituacaoLigacaoAgua,// 10 
			Integer idSituacaoLigacaoEsgoto, // 11
			Integer idPerfilLigacaoAgua, // 12
			Integer idPerfilLigacaoEsgoto, // 13
			Integer idHidrometro,// 14
			Integer idVolFixadoAgua, // 15
			Integer idVolFixadoEsgoto, // 16
			Integer idTipoDocumento, // 17
			Integer anoMesReferenciaDocumento,// 18 
			BigDecimal valorPendenteCredito,// 19
			Integer idTipoTarifaConsumo,//20
			Short cdRota) {//21
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idElo = idElo;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.idPerfilImovel = idPerfilImovel;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;
		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;
		this.idHidrometro = idHidrometro;
		this.idVolFixadoAgua = idVolFixadoAgua;
		this.idVolFixadoEsgoto = idVolFixadoEsgoto;
		this.idTipoDocumento = idTipoDocumento;
		this.anoMesReferenciaDocumento = anoMesReferenciaDocumento;
		this.valorPendenteCredito = ( valorPendenteCredito == null ? new BigDecimal(0) : valorPendenteCredito );
		this.idTipoTarifaConsumo = idTipoTarifaConsumo;
		this.cdRota = cdRota;
	}
	
    /**
	* Compara dois objetos levando em consideracao apenas as propriedades
    * que identificam o agrupamento
    * 
    * @param obj Objeto a ser comparado com a instancia deste objeto 
    */
	public boolean equals( Object obj ){
		
	    if (obj == null) { 
		    return false; 
	    }
	   
	    if (obj == this){ 
	 	    return true; 
	    }
	   
	    if (obj.getClass() != getClass()) {
	      return false;
	    }
		
	    ResumoPendenciaCreditoARealizarGerenciaHelper resumoTemp = ( ResumoPendenciaCreditoARealizarGerenciaHelper ) obj;
		  
	   	// Verificamos se todas as propriedades que identificam o objeto sao iguais
		return 
			new EqualsBuilder()
				.append( this.idGerenciaRegional, resumoTemp.idGerenciaRegional )
				.append( this.idUnidadeNegocio, resumoTemp.idUnidadeNegocio )
				.append( this.idLocalidade, resumoTemp.idLocalidade )
				.append( this.idElo, resumoTemp.idElo )
				.append( this.idSetorComercial, resumoTemp.idSetorComercial )
				.append( this.idRota, resumoTemp.idRota )
				.append( this.idQuadra, resumoTemp.idQuadra )
				.append( this.codigoSetorComercial, resumoTemp.codigoSetorComercial )
				.append( this.numeroQuadra, resumoTemp.numeroQuadra )
				.append( this.idPerfilImovel, resumoTemp.idPerfilImovel )
				.append( this.idEsferaPoder, resumoTemp.idEsferaPoder )
				.append( this.idTipoClienteResponsavel, resumoTemp.idTipoClienteResponsavel )
				.append( this.idSituacaoLigacaoAgua, resumoTemp.idSituacaoLigacaoAgua )
				.append( this.idSituacaoLigacaoEsgoto, resumoTemp.idSituacaoLigacaoEsgoto )
				.append( this.idPrincipalCategoriaImovel, resumoTemp.idPrincipalCategoriaImovel )
				.append( this.idPrincipalSubCategoriaImovel, resumoTemp.idPrincipalSubCategoriaImovel )
				.append( this.idPerfilLigacaoAgua, resumoTemp.idPerfilLigacaoAgua )
				.append( this.idPerfilLigacaoEsgoto, resumoTemp.idPerfilLigacaoEsgoto )
				.append( this.idHidrometro, resumoTemp.idHidrometro )
				.append( this.idVolFixadoAgua, resumoTemp.idVolFixadoAgua )
				.append( this.idVolFixadoEsgoto, resumoTemp.idVolFixadoEsgoto ) 
				.append( this.idTipoDocumento, resumoTemp.idTipoDocumento )		
				.append( this.anoMesReferenciaDocumento, resumoTemp.anoMesReferenciaDocumento )
				.append( this.idTipoTarifaConsumo, resumoTemp.idTipoTarifaConsumo ).isEquals();
	}
	public Integer getIdHidrometro() {
		return idHidrometro;
	}
	public void setIdHidrometro(Integer idHidrometro) {
		this.idHidrometro = idHidrometro;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public Integer getIdTipoTarifaConsumo() {
		return idTipoTarifaConsumo;
	}
	public void setIdTipoTarifaConsumo(Integer idTipoTarifaConsumo) {
		this.idTipoTarifaConsumo = idTipoTarifaConsumo;
	}	
}
