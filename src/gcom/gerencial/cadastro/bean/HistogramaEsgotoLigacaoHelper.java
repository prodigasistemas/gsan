package gcom.gerencial.cadastro.bean;

/**
 * classe responsável por ajudar o caso de uso [UC0566] Gerar Histograma Agua e Esgoto
 *
 * @author Bruno Barros
 * @date 14/05/2007
 */
public class HistogramaEsgotoLigacaoHelper {
	
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idElo;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	private Integer idQuadra;
	private Integer idNumeroQuadra;
	private Integer idTipoCategoria;
	private Integer idCategoria;
	private Integer idLigacaoMista;
	private Integer idConsumoTarifa;
	private Integer idPerfilImovel;
	private Integer idEsferaPoder;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idConsumoReal;
	private Integer idExistenciaHidrometro;
	private Integer idExistenciaPoco;
	private Integer idExistenciaVolumeFixoEsgoto;
	private Float   percentualColetaEsgoto;
	private Integer quantidadeConsumo;
	private Integer quantidadeLigacao;
	private Integer quantidadeEconomiaLigacao;
	private Float valorFaturadoLigacao;
	private Integer volumeFaturadoLigacao;
	
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Integer getIdConsumoReal() {
		return idConsumoReal;
	}
	public void setIdConsumoReal(Integer idConsumoReal) {
		this.idConsumoReal = idConsumoReal;
	}
	public Integer getIdConsumoTarifa() {
		return idConsumoTarifa;
	}
	public void setIdConsumoTarifa(Integer idConsumoTarifa) {
		this.idConsumoTarifa = idConsumoTarifa;
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
	public Integer getIdExistenciaHidrometro() {
		return idExistenciaHidrometro;
	}
	public void setIdExistenciaHidrometro(Integer idExistenciaHidrometro) {
		this.idExistenciaHidrometro = idExistenciaHidrometro;
	}
	public Integer getIdExistenciaPoco() {
		return idExistenciaPoco;
	}
	public void setIdExistenciaPoco(Integer idExistenciaPoco) {
		this.idExistenciaPoco = idExistenciaPoco;
	}
	public Integer getIdExistenciaVolumeFixoEsgoto() {
		return idExistenciaVolumeFixoEsgoto;
	}
	public void setIdExistenciaVolumeFixoEsgoto(Integer idExistenciaVolumeFixoEsgoto) {
		this.idExistenciaVolumeFixoEsgoto = idExistenciaVolumeFixoEsgoto;
	}
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public Integer getIdLigacaoMista() {
		return idLigacaoMista;
	}
	public void setIdLigacaoMista(Integer idLigacaoMista) {
		this.idLigacaoMista = idLigacaoMista;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public Integer getIdNumeroQuadra() {
		return idNumeroQuadra;
	}
	public void setIdNumeroQuadra(Integer idNumeroQuadra) {
		this.idNumeroQuadra = idNumeroQuadra;
	}
	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}
	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}
	public Integer getIdQuadra() {
		return idQuadra;
	}
	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}
	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}
	public Integer getIdTipoCategoria() {
		return idTipoCategoria;
	}
	public void setIdTipoCategoria(Integer idTipoCategoria) {
		this.idTipoCategoria = idTipoCategoria;
	}
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	public Float getPercentualColetaEsgoto() {
		return percentualColetaEsgoto;
	}
	public void setPercentualColetaEsgoto(Float percentualColetaEsgoto) {
		this.percentualColetaEsgoto = percentualColetaEsgoto;
	}
	public Integer getQuantidadeConsumo() {
		return quantidadeConsumo;
	}
	public void setQuantidadeConsumo(Integer quantidadeConsumo) {
		this.quantidadeConsumo = quantidadeConsumo;
	}
	public Integer getQuantidadeEconomiaLigacao() {
		return quantidadeEconomiaLigacao;
	}
	public void setQuantidadeEconomiaLigacao(Integer quantidadeEconomiaLigacao) {
		this.quantidadeEconomiaLigacao = quantidadeEconomiaLigacao;
	}
	public Integer getQuantidadeLigacao() {
		return quantidadeLigacao;
	}
	public void setQuantidadeLigacao(Integer quantidadeLigacao) {
		this.quantidadeLigacao = quantidadeLigacao;
	}
	public Float getValorFaturadoLigacao() {
		return valorFaturadoLigacao;
	}
	public void setValorFaturadoLigacao(Float valorFaturadoLigacao) {
		this.valorFaturadoLigacao = valorFaturadoLigacao;
	}
	public HistogramaEsgotoLigacaoHelper(Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idElo, Integer idLocalidade, Integer idSetorComercial, Integer codigoSetorComercial, Integer idQuadra, Integer idNumeroQuadra, Integer idTipoCategoria, Integer idCategoria, Integer idLigacaoMista, Integer idConsumoTarifa, Integer idPerfilImovel, Integer idEsferaPoder, Integer idSituacaoLigacaoEsgoto, Integer idConsumoReal, Integer idExistenciaHidrometro, Integer idExistenciaPoco, Integer idExistenciaVolumeFixoEsgoto, Float percentualColetaEsgoto, Integer quantidadeConsumo) {
		super();
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idElo = idElo;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.codigoSetorComercial = codigoSetorComercial;
		this.idQuadra = idQuadra;
		this.idNumeroQuadra = idNumeroQuadra;
		this.idTipoCategoria = idTipoCategoria;
		this.idCategoria = idCategoria;
		this.idLigacaoMista = idLigacaoMista;
		this.idConsumoTarifa = idConsumoTarifa;
		this.idPerfilImovel = idPerfilImovel;
		this.idEsferaPoder = idEsferaPoder;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idConsumoReal = idConsumoReal;
		this.idExistenciaHidrometro = idExistenciaHidrometro;
		this.idExistenciaPoco = ( idExistenciaPoco == null ? 0 : idExistenciaPoco );
		this.idExistenciaVolumeFixoEsgoto = idExistenciaVolumeFixoEsgoto;
		this.percentualColetaEsgoto = ( percentualColetaEsgoto == null ? 0 : percentualColetaEsgoto );
		this.quantidadeConsumo = quantidadeConsumo;
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
	 * Compara duas properiedades inteiras, comparando
	 * seus valores para descobrirmos se sao iguais
	 * @param pro1 Primeira propriedade
	 * @param pro2 Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais( Float pro1, Float pro2 ){
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
		
		if ( !( obj instanceof HistogramaEsgotoLigacaoHelper ) ){
			return false;			
		} else {
			HistogramaEsgotoLigacaoHelper resumoTemp = ( HistogramaEsgotoLigacaoHelper ) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return 
				propriedadesIguais( this.idGerenciaRegional , resumoTemp.idGerenciaRegional ) &&
				propriedadesIguais( this.idUnidadeNegocio , resumoTemp.idUnidadeNegocio ) &&
				propriedadesIguais( this.idElo , resumoTemp.idElo ) &&
				propriedadesIguais( this.idLocalidade , resumoTemp.idLocalidade ) &&
				propriedadesIguais( this.idSetorComercial , resumoTemp.idSetorComercial ) &&
				propriedadesIguais( this.codigoSetorComercial , resumoTemp.codigoSetorComercial ) &&
				propriedadesIguais( this.idQuadra , resumoTemp.idQuadra ) &&
				propriedadesIguais( this.idNumeroQuadra , resumoTemp.idNumeroQuadra ) &&
				propriedadesIguais( this.idTipoCategoria , resumoTemp.idTipoCategoria ) &&
				propriedadesIguais( this.idCategoria , resumoTemp.idCategoria ) &&
				propriedadesIguais( this.idLigacaoMista , resumoTemp.idLigacaoMista ) &&
				propriedadesIguais( this.idConsumoTarifa , resumoTemp.idConsumoTarifa ) &&
				propriedadesIguais( this.idPerfilImovel , resumoTemp.idPerfilImovel ) &&
				propriedadesIguais( this.idEsferaPoder , resumoTemp.idEsferaPoder ) &&
				propriedadesIguais( this.idSituacaoLigacaoEsgoto , resumoTemp.idSituacaoLigacaoEsgoto ) &&
				propriedadesIguais( this.idConsumoReal , resumoTemp.idConsumoReal ) &&
				propriedadesIguais( this.idExistenciaHidrometro , resumoTemp.idExistenciaHidrometro ) &&
				propriedadesIguais( this.idExistenciaPoco , resumoTemp.idExistenciaPoco ) &&
				propriedadesIguais( this.idExistenciaVolumeFixoEsgoto , resumoTemp.idExistenciaVolumeFixoEsgoto ) &&
				propriedadesIguais( this.percentualColetaEsgoto , resumoTemp.percentualColetaEsgoto ) &&
				propriedadesIguais( this.quantidadeConsumo , resumoTemp.quantidadeConsumo );
		}	
	}
	public Integer getVolumeFaturadoLigacao() {
		return volumeFaturadoLigacao;
	}
	public void setVolumeFaturadoLigacao(Integer volumeFaturadoLigacao) {
		this.volumeFaturadoLigacao = volumeFaturadoLigacao;
	}
	
}
	
