package gcom.gerencial.cadastro.bean;

import java.math.BigDecimal;

/**
 * Classe bean para agrupamento dos historicos 
 * de consumo com as quebras solicitadas
 *  
 *
 * @author Fernando Fontelles
 * @date 24/05/2010
 */

public class ResumoConsumoAguaPorAnoHelper {
	
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidade;
	private Integer idElo;
	private Integer idSetorComercial;
	//private Integer idRota;
	//private Integer idQuadra;		
	private Integer codigoSetorComercial;		
	//private Integer numeroQuadra;
	private Integer idImovelPerfil;
    private Integer idLigacaoAguaSituacao;
    private Integer idLigacaoEsgotoSituacao;	
	private Integer idCategoria;
	private Integer idSubCategoria;    
	private Integer idEsferaPoder;
	private Integer idClienteTipo;
    private Integer idLigacaoAguaPerfil;
    private Integer idLigacaoEsgotoPerfil;
    private Integer idConsumoTipo;
    private Integer quantidadeConsumoAgua  = new Integer(0);
	private Integer quantidadeLigacoes = new Integer(0);
	private Integer quantidadeEconomias = new Integer(0);
	private Integer quantidadeConsumoAguaExcedente = new Integer(0);
	private Integer idVolumeExcedente;
	private Integer idHidrometro;
	private Integer volumeFaturado;
	private BigDecimal valorAgua;
	
	private Integer indicadorLigacaoFaturada;
	
	public Integer getIdVolumeExcedente() {
		return idVolumeExcedente;
	}
	public void setIdVolumeExcedente(Integer indicadorVolumeExcedente) {
		this.idVolumeExcedente = indicadorVolumeExcedente;
	}
	public void setQuantidadeConsumoAguaExcedente(
			Integer quantidadeConsumoAguaExcedente) {
		this.quantidadeConsumoAguaExcedente = quantidadeConsumoAguaExcedente;
	}
	public Integer getIdClienteTipo() {
		return idClienteTipo;
	}
	public void setIdClienteTipo(Integer idClienteTipo) {
		this.idClienteTipo = idClienteTipo;
	}
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setIdCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public Integer getIdConsumoTipo() {
		return idConsumoTipo;
	}
	public void setIdConsumoTipo(Integer idConsumoTipo) {
		this.idConsumoTipo = idConsumoTipo;
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
	public Integer getIdImovelPerfil() {
		return idImovelPerfil;
	}
	public void setIdImovelPerfil(Integer idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLcalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Integer getIdLigacaoAguaPerfil() {
		return idLigacaoAguaPerfil;
	}
	public void setIdLigacaoAguaPerfil(Integer idLigacaoAguaPerfil) {
		this.idLigacaoAguaPerfil = idLigacaoAguaPerfil;
	}
	public Integer getIdLigacaoAguaSituacao() {
		return idLigacaoAguaSituacao;
	}
	public void setIdLigacaoAguaSituacao(Integer idLigacaoAguaSituacao) {
		this.idLigacaoAguaSituacao = idLigacaoAguaSituacao;
	}
	public Integer getIdLigacaoEsgotoPerfil() {
		return idLigacaoEsgotoPerfil;
	}
	public void setIdLigacaoEsgotoPerfil(Integer idLigacaoEsgotoPerfil) {
		this.idLigacaoEsgotoPerfil = idLigacaoEsgotoPerfil;
	}
	public Integer getIdLigacaoEsgotoSituacao() {
		return idLigacaoEsgotoSituacao;
	}
	public void setIdLigacaoEsgotoSituacao(Integer idLigacaoEsgotoSituacao) {
		this.idLigacaoEsgotoSituacao = idLigacaoEsgotoSituacao;
	}
//	public Integer getNumeroQuadra() {
//		return numeroQuadra;
//	}
//	public void setIdNumeroQuadra(Integer numeroQuadra) {
//		this.numeroQuadra = numeroQuadra;
//	}
//	public Integer getIdQuadra() {
//		return idQuadra;
//	}
//	public void setIdQuadra(Integer idQuadra) {
//		this.idQuadra = idQuadra;
//	}
//	public Integer getIdRota() {
//		return idRota;
//	}
//	public void setIdRota(Integer idRota) {
//		this.idRota = idRota;
//	}
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public Integer getQuantidadeConsumoAgua() {
		return quantidadeConsumoAgua;
	}
	public void setQuantidadeConsumoAgua(Integer quantidadeConsumoAgua) {
		this.quantidadeConsumoAgua = quantidadeConsumoAgua;
	}
	public Integer getQuantidadeConsumoAguaExcedente() {
		return this.quantidadeConsumoAguaExcedente;
	}
	public Integer getQuantidadeEconomias() {
		return quantidadeEconomias;
	}
	public void setQuantidadeEconomias(Integer quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}
	
	/**
	 * Construtor com a sequencia correta de quebras para o 
	 * caso de uso UC[0570] - Gerar resumo do consumo de agua
	 * 
	 * OBS - Duas quebras adicionais nao foram passadas neste contrutor,
	 * a saber, idCategoria e idSubCatergoria, pois no momento da criacao deste objeto
	 * essas informacoes nao estao disponiveis. 
	 *
	 * @param idGerenciaRegional
	 * param  idUnidadeNegocio
	 * @param idLocalidade
	 * @param idElo
	 * @param idSetorComercial
	 * @param codigoSetorComercial
	 * @param idImovelPerfil
	 * @param idSituacaoLigacaoAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param idPerfilLigacaoAgua
	 * @param idPerfilLigacaoEsgoto
	 * @param idTipoConsumo
	 * @param qtdConsumoAgua
	 */
	public ResumoConsumoAguaPorAnoHelper( 
			Integer idGerenciaRegional,
			Integer idUnidadeNegocio,
			Integer idLocalidade,
			Integer idElo,
			Integer idSetorComercial,
			//Integer idRota,
			//Integer idQuadra,
			Integer codigoSetorComercial,
			//Integer numeroQuadra,
			Integer idImovelPerfil,
			Integer idSituacaoLigacaoAgua,
			Integer idSituacaoLigacaoEsgoto,			
			Integer idPerfilLigacaoAgua,
			Integer idPerfilLigacaoEsgoto,
			Integer idTipoConsumo
            ){
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idLocalidade = idLocalidade;
		this.idElo = idElo;
		this.idSetorComercial = idSetorComercial;
//		this.idRota = idRota;
//		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
//		this.numeroQuadra = numeroQuadra; 
		this.idImovelPerfil = idImovelPerfil;
		this.idLigacaoAguaSituacao = idSituacaoLigacaoAgua;
		this.idLigacaoEsgotoSituacao = idSituacaoLigacaoEsgoto;
		this.idLigacaoAguaPerfil = idPerfilLigacaoAgua;
		this.idLigacaoEsgotoPerfil = idPerfilLigacaoEsgoto;
		this.idConsumoTipo = idTipoConsumo;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Integer getIdSubCategoria() {
		return idSubCategoria;
	}
	public void setIdSubCategoria(Integer idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	
	/**
	 * Compara duas properiedades inteiras, comparando
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
	
    /**
     * Compara dois objetos levando em consideracao apenas as propriedades
     * que identificam o agrupamento
     * 
     * @param obj Objeto a ser comparado com a instancia deste objeto 
     */
	public boolean equals( Object obj ){
		
		if ( !( obj instanceof ResumoConsumoAguaPorAnoHelper ) ){
			return false;			
		} else {
			ResumoConsumoAguaPorAnoHelper resumoTemp = ( ResumoConsumoAguaPorAnoHelper ) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return 
			  propriedadesIguais( this.idGerenciaRegional, resumoTemp.idGerenciaRegional ) &&
			  propriedadesIguais( this.idUnidadeNegocio, resumoTemp.idUnidadeNegocio ) &&
			  propriedadesIguais( this.idLocalidade, resumoTemp.idLocalidade ) &&
			  propriedadesIguais( this.idElo, resumoTemp.idElo ) &&
			  propriedadesIguais( this.idSetorComercial, resumoTemp.idSetorComercial ) &&
//			  propriedadesIguais( this.idRota, resumoTemp.idRota ) &&
//			  propriedadesIguais( this.idQuadra, resumoTemp.idQuadra ) &&
			  propriedadesIguais( this.codigoSetorComercial, resumoTemp.codigoSetorComercial ) &&
//			  propriedadesIguais( this.numeroQuadra, resumoTemp.numeroQuadra ) &&
			  propriedadesIguais( this.idImovelPerfil, resumoTemp.idImovelPerfil ) &&
			  propriedadesIguais( this.idLigacaoAguaSituacao, resumoTemp.idLigacaoAguaSituacao ) &&
			  propriedadesIguais( this.idLigacaoEsgotoSituacao, resumoTemp.idLigacaoEsgotoSituacao ) &&
			  propriedadesIguais( this.idCategoria, resumoTemp.idCategoria ) &&
			  propriedadesIguais( this.idSubCategoria, resumoTemp.idSubCategoria ) &&			  
			  propriedadesIguais( this.idEsferaPoder, resumoTemp.idEsferaPoder ) &&
			  propriedadesIguais( this.idClienteTipo, resumoTemp.idClienteTipo ) &&
			  propriedadesIguais( this.idLigacaoAguaPerfil, resumoTemp.idLigacaoAguaPerfil ) &&
			  propriedadesIguais( this.idLigacaoEsgotoPerfil, resumoTemp.idLigacaoEsgotoPerfil ) &&
			  propriedadesIguais( this.idConsumoTipo, resumoTemp.idConsumoTipo ) &&
			  propriedadesIguais( this.idVolumeExcedente, resumoTemp.idVolumeExcedente) &&
			  propriedadesIguais( this.idHidrometro, resumoTemp.idHidrometro);
		}	
	}
	
	public Integer getIdHidrometro() {
	
		return idHidrometro;
	}
	
	public void setIdHidrometro(Integer idHidrometro) {
	
		this.idHidrometro = idHidrometro;
	}
	
	public Integer getVolumeFaturado() {
	
		return volumeFaturado;
	}
	
	public void setVolumeFaturado(Integer volumeFaturado) {
	
		this.volumeFaturado = volumeFaturado;
	}
	/**
	 * @return Retorna o campo indicadorLigacaoFaturada.
	 */
	public Integer getIndicadorLigacaoFaturada() {
		return indicadorLigacaoFaturada;
	}
	/**
	 * @param indicadorLigacaoFaturada O indicadorLigacaoFaturada a ser setado.
	 */
	public void setIndicadorLigacaoFaturada(Integer indicadorLigacaoFaturada) {
		this.indicadorLigacaoFaturada = indicadorLigacaoFaturada;
	}
	public BigDecimal getValorAgua() {
		return valorAgua;
	}
	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}
}

