package gcom.gerencial.cobranca.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Classe responsável por ajudar o caso de uso [UC0335] Gerar Resumo da Pendencia
 * [SB0001A] 
 *
 * @author Bruno Barros
 * @date 19/09/2007
 */
public class ResumoPendenciaDebitosACobrarGerenciaHelper {	
	
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
	private Integer idTipoFinanciamento;	
	private Integer quantidadeLigacoes = 0;
	private Integer quantidadeDocumentos = 1;
	private BigDecimal valorPendenteDebito = new BigDecimal(0);
	private Integer idTipoTarifaConsumo;
	private Short cdRota;
	
	public Short getCdRota() {
		return cdRota;
	}
	public void setCdRota(Short cdRota) {
		this.cdRota = cdRota;
	}
	public Integer getIdTipoTarifaConsumo() {
		return idTipoTarifaConsumo;
	}
	public void setIdTipoTarifaConsumo(Integer idTipoTarifaConsumo) {
		this.idTipoTarifaConsumo = idTipoTarifaConsumo;
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
	public BigDecimal getValorPendenteDebito() {
		return valorPendenteDebito;
	}
	public void setValorPendenteDebito(BigDecimal valorPendenteDebito) {
		if ( valorPendenteDebito != null ){
			this.valorPendenteDebito = valorPendenteDebito;
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
	 * @param idReferenciaVencimentoConta
	 */
	public ResumoPendenciaDebitosACobrarGerenciaHelper(
			Integer idGerenciaRegional, 
			Integer idUnidadeNegocio, 
			Integer idElo, 
			Integer idLocalidade, 
			Integer idSetorComercial, 
			Integer idRota, 
			Integer idQuadra, 
			Integer codigoSetorComercial, 
			Integer numeroQuadra, 
			Integer idPerfilImovel, 
			Integer idSituacaoLigacaoAgua, 
			Integer idSituacaoLigacaoEsgoto, 
			Integer idPerfilLigacaoAgua, 
			Integer idPerfilLigacaoEsgoto, 
			Integer idHidrometro,
			Integer idVolFixadoAgua, 
			Integer idVolFixadoEsgoto, 
			Integer idTipoDocumento, 
			Integer anoMesReferenciaDocumento,			
			Integer idTipoFinanciamento,
			BigDecimal valorPendenteDebito,
			Integer idTipoTarifaConsumo,
			Short cdRota) {			
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
		this.idTipoFinanciamento = idTipoFinanciamento;				
		this.valorPendenteDebito = ( valorPendenteDebito == null ? new BigDecimal(0) : valorPendenteDebito );
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
	    
		ResumoPendenciaDebitosACobrarGerenciaHelper resumoTemp = ( ResumoPendenciaDebitosACobrarGerenciaHelper ) obj;
		  
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
			  .append( this.idTipoFinanciamento, resumoTemp.idTipoFinanciamento )
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
	public Integer getIdTipoFinanciamento() {
		return idTipoFinanciamento;
	}
	public void setIdTipoFinanciamento(Integer idTipoFinanciamento) {
		this.idTipoFinanciamento = idTipoFinanciamento;
	}	
}
