package gcom.gerencial.cobranca.bean;

import java.math.BigDecimal;


/**
 * Classe responsável por ajudar o caso de uso [UC0335] Gerar Resumo da Pendencia
 * [SB0001B] 
 *
 * @author Bruno Barros
 * @date 01/08/2007
 */
public class ResumoPendenciaContasRegiaoHelper {
	
	private Integer anoMesReferencia;
	private Integer idRegiao;
	private Integer idMicroRegiao;
	private Integer idMunicipio;
	private Integer idBairro;
	private Integer idPerfilImovel;
	private Integer idEsferaPoder;
	private Integer idTipoClienteResponsavel;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idPrincipalCategoria;
	private Integer idPrincipalSubCategoria;
	private Integer idHidrometro;
	private Integer idVolFixoAgua;
	private Integer idVolFixoEsgoto;
	private Integer idTipoDocumento;
	private Integer referenciaDocumento;
	private Integer idTipoFinanciamento;
	private Integer idReferenciaVencimentoConta;
	private Integer quantidadeLigacoes = 1;
	private Integer quantidadeDocumentos = 1;
	private BigDecimal valorPendenteAgua = new BigDecimal(0);
	private BigDecimal valorPendenteEsgoto = new BigDecimal(0);
	private BigDecimal valorPendenteDebito = new BigDecimal(0);
	private BigDecimal valorPendenteCredito = new BigDecimal(0);
	private BigDecimal valorPendenteImposto = new BigDecimal(0);
	
	public ResumoPendenciaContasRegiaoHelper(
			Integer idRegiao,   // 1
			Integer idMicroRegiao,   // 2
			Integer idMunicipio,   // 3
			Integer idBairro,   // 4
			Integer idPerfilImovel,   // 5
			Integer idSituacaoLigacaoAgua,  // 6 
			Integer idSituacaoLigacaoEsgoto,   // 7
			Integer idHidrometro,   // 8
			Integer idVolFixoAgua,   // 9
			Integer idVolFixoEsgoto,   // 10
			Integer referenciaDocumento,   // 11
			Integer idTipoFinanciamento,   // 12			
			Integer idReferenciaVencimentoConta, //13			 
			BigDecimal valorPendenteAgua, //14
			BigDecimal valorPendenteEsgoto, //15
			BigDecimal valorPendenteDebito, //16
			BigDecimal valorPendenteCredito, //17
			BigDecimal valorPendenteImposto ) {	 //18					
		super();
		this.idRegiao = idRegiao;
		this.idMicroRegiao = idMicroRegiao;
		this.idMunicipio = idMunicipio;
		this.idBairro = idBairro;
		this.idPerfilImovel = idPerfilImovel;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idHidrometro = idHidrometro;
		this.idVolFixoAgua = idVolFixoAgua;
		this.idVolFixoEsgoto = idVolFixoEsgoto;
		this.referenciaDocumento = referenciaDocumento;
		this.idTipoFinanciamento = idTipoFinanciamento;
		this.idReferenciaVencimentoConta = idReferenciaVencimentoConta;
		this.valorPendenteAgua = ( valorPendenteAgua == null ? new BigDecimal(0) : valorPendenteAgua ); 
		this.valorPendenteEsgoto = ( valorPendenteEsgoto == null ? new BigDecimal(0) : valorPendenteEsgoto );
		this.valorPendenteDebito = ( valorPendenteDebito == null ? new BigDecimal(0) : valorPendenteDebito );
		this.valorPendenteCredito = ( valorPendenteCredito == null ? new BigDecimal(0) : valorPendenteCredito );
		this.valorPendenteImposto = ( valorPendenteImposto == null ? new BigDecimal(0) : valorPendenteImposto );			
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}


	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}


	public Integer getIdBairro() {
		return idBairro;
	}


	public void setIdBairro(Integer idBairro) {
		this.idBairro = idBairro;
	}


	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}


	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}


	public Integer getIdHidrometro() {
		return idHidrometro;
	}


	public void setIdHidrometro(Integer idHidrometro) {
		this.idHidrometro = idHidrometro;
	}


	public Integer getIdMicroRegiao() {
		return idMicroRegiao;
	}


	public void setIdMicroRegiao(Integer idMicroRegiao) {
		this.idMicroRegiao = idMicroRegiao;
	}


	public Integer getIdMunicipio() {
		return idMunicipio;
	}


	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}


	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}


	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}


	public Integer getIdPrincipalCategoria() {
		return idPrincipalCategoria;
	}


	public void setIdPrincipalCategoria(Integer idPrincipalCategoria) {
		this.idPrincipalCategoria = idPrincipalCategoria;
	}


	public Integer getIdPrincipalSubCategoria() {
		return idPrincipalSubCategoria;
	}


	public void setIdPrincipalSubCategoria(Integer idPrincipalSubCategoria) {
		this.idPrincipalSubCategoria = idPrincipalSubCategoria;
	}


	public Integer getIdReferenciaVencimentoConta() {
		return idReferenciaVencimentoConta;
	}


	public void setIdReferenciaVencimentoConta(Integer idReferenciaVencimentoConta) {
		this.idReferenciaVencimentoConta = idReferenciaVencimentoConta;
	}


	public Integer getIdRegiao() {
		return idRegiao;
	}


	public void setIdRegiao(Integer idRegiao) {
		this.idRegiao = idRegiao;
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


	public Integer getIdTipoFinanciamento() {
		return idTipoFinanciamento;
	}


	public void setIdTipoFinanciamento(Integer idTipoFinanciamento) {
		this.idTipoFinanciamento = idTipoFinanciamento;
	}


	public Integer getIdVolFixoAgua() {
		return idVolFixoAgua;
	}


	public void setIdVolFixoAgua(Integer idVolFixoAgua) {
		this.idVolFixoAgua = idVolFixoAgua;
	}


	public Integer getIdVolFixoEsgoto() {
		return idVolFixoEsgoto;
	}


	public void setIdVolFixoEsgoto(Integer idVolFixoEsgoto) {
		this.idVolFixoEsgoto = idVolFixoEsgoto;
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


	public Integer getReferenciaDocumento() {
		return referenciaDocumento;
	}


	public void setReferenciaDocumento(Integer referenciaDocumento) {
		this.referenciaDocumento = referenciaDocumento;
	}


	public BigDecimal getValorPendenteAgua() {
		return valorPendenteAgua;
	}


	public void setValorPendenteAgua(BigDecimal valorPendenteAgua) {
		this.valorPendenteAgua = valorPendenteAgua;
	}


	public BigDecimal getValorPendenteCredito() {
		return valorPendenteCredito;
	}


	public void setValorPendenteCredito(BigDecimal valorPendenteCredito) {
		this.valorPendenteCredito = valorPendenteCredito;
	}


	public BigDecimal getValorPendenteDebito() {
		return valorPendenteDebito;
	}


	public void setValorPendenteDebito(BigDecimal valorPendenteDebito) {
		this.valorPendenteDebito = valorPendenteDebito;
	}


	public BigDecimal getValorPendenteEsgoto() {
		return valorPendenteEsgoto;
	}


	public void setValorPendenteEsgoto(BigDecimal valorPendenteEsgoto) {
		this.valorPendenteEsgoto = valorPendenteEsgoto;
	}


	public BigDecimal getValorPendenteImposto() {
		return valorPendenteImposto;
	}


	public void setValorPendenteImposto(BigDecimal valorPendenteImposto) {
		this.valorPendenteImposto = valorPendenteImposto;
	}


	/**
	 * Compara duas properiedades Integereiras, comparando
	 * seus valores para descobrirmos se sao iguais
	 * @param pro1 Primeira propriedade
	 * @param pro2 Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais( Integer pro1, Integer pro2 ){
	  if ( pro2 != null ){
		  if ( !pro2.equals( pro1 ) ){
			  return false;
		  }
	  } else if ( pro1 != null ){
		  return false;
	  }
	  
	  // Se chegou ate aqui quer dizer que as propriedades sao iguais
	  return true;
	}
	
	public boolean equals( Object obj ){
		if ( !( obj instanceof ResumoPendenciaContasRegiaoHelper ) ){
			return false;			
		} else {
			ResumoPendenciaContasRegiaoHelper resumoTemp = ( ResumoPendenciaContasRegiaoHelper ) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return
			  propriedadesIguais( this.idRegiao, resumoTemp.idRegiao ) &&
			  propriedadesIguais( this.idMicroRegiao, resumoTemp.idMicroRegiao ) &&
			  propriedadesIguais( this.idMunicipio, resumoTemp.idMunicipio ) &&
			  propriedadesIguais( this.idBairro, resumoTemp.idBairro ) &&
			  propriedadesIguais( this.idPerfilImovel, resumoTemp.idPerfilImovel ) &&
			  propriedadesIguais( this.idEsferaPoder, resumoTemp.idEsferaPoder ) &&
			  propriedadesIguais( this.idTipoClienteResponsavel, resumoTemp.idTipoClienteResponsavel ) &&
			  propriedadesIguais( this.idSituacaoLigacaoAgua, resumoTemp.idSituacaoLigacaoAgua ) &&
			  propriedadesIguais( this.idSituacaoLigacaoEsgoto, resumoTemp.idSituacaoLigacaoEsgoto ) &&
			  propriedadesIguais( this.idPrincipalCategoria, resumoTemp.idPrincipalCategoria ) &&
			  propriedadesIguais( this.idPrincipalSubCategoria, resumoTemp.idPrincipalSubCategoria ) &&			  
			  propriedadesIguais( this.idHidrometro, resumoTemp.idHidrometro ) &&			  
			  propriedadesIguais( this.idVolFixoAgua, resumoTemp.idVolFixoAgua ) &&
			  propriedadesIguais( this.idVolFixoEsgoto, resumoTemp.idVolFixoEsgoto ) &&		
			  propriedadesIguais( this.referenciaDocumento, resumoTemp.referenciaDocumento ) &&
			  propriedadesIguais( this.idTipoFinanciamento, resumoTemp.idTipoFinanciamento ) &&
			  propriedadesIguais( this.idReferenciaVencimentoConta, resumoTemp.idReferenciaVencimentoConta );			  			  
		}
	}
}
