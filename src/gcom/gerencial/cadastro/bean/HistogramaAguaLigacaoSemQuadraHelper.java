package gcom.gerencial.cadastro.bean;

/**
 * classe responsável por ajudar o caso de uso [UC1057] Gerar Histograma Agua e Esgoto Sem Quadras
 *
 * @author Ivan Sergio
 * @date 09/08/2010
 */
public class HistogramaAguaLigacaoSemQuadraHelper {
	
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idElo;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	private Integer idTipoCategoria;
	private Integer idCategoria;
	private Integer idLigacaoMista;
	private Integer idContaCaregoria;
	private Integer idPerfilImovel;
	private Integer idEsferaPoder;
	private Integer idSituacaoLigacaoAgua;
	private Integer idConsumoReal;
	private Integer idExistenciaHidrometro;
	private Integer idExistenciaPoco;
	private Integer idExistenciaVolumeFixoAgua;
	private Integer quantidadeConsumo = 0;
	private Integer quantidadeLigacao = 0;
	private Integer quantidadeEconomiaLigacao = 0;
	private Float valorFaturadoLigacao = 0f;
	private Integer volumeFaturadoLigacao = 0;
	private Integer idSubCategoria;
	
	public HistogramaAguaLigacaoSemQuadraHelper() {}
	
	public HistogramaAguaLigacaoSemQuadraHelper(
			Integer idGerenciaRegional, 
			Integer idUnidadeNegocio, 
			Integer idElo, 
			Integer idLocalidade, 
			Integer idSetorComercial, 
			Integer codigoSetorComercial, 
			Integer idTipoCategoria, 
			Integer idCategoria,
			Integer idSubCategoria,
			Integer idLigacaoMista, 
			Integer idContaCaregoria, 
			Integer idPerfilImovel, 
			Integer idEsferaPoder, 
			Integer idSituacaoLigacaoAgua, 
			Integer idConsumoReal, 
			Integer idExistenciaHidrometro, 
			Integer idExistenciaPoco, 
			Integer idExistenciaVolumeFixoAgua,
			Integer quantidadeConsumo) {
		super();
		
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idElo = idElo;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.codigoSetorComercial = codigoSetorComercial;
		this.idTipoCategoria = idTipoCategoria;
		this.idCategoria = idCategoria;
		this.idSubCategoria = idSubCategoria;
		this.idLigacaoMista = idLigacaoMista;
		this.idContaCaregoria = idContaCaregoria;
		this.idPerfilImovel = idPerfilImovel;
		this.idEsferaPoder = idEsferaPoder;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idConsumoReal = idConsumoReal;
		this.idExistenciaHidrometro = idExistenciaHidrometro;
		this.idExistenciaPoco = idExistenciaPoco;
		this.idExistenciaVolumeFixoAgua = idExistenciaVolumeFixoAgua;
		this.quantidadeConsumo = quantidadeConsumo;
	}
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
	public Integer getIdContaCaregoria() {
		return idContaCaregoria;
	}
	public void setIdContaCaregoria(Integer idContaCaregoria) {
		this.idContaCaregoria = idContaCaregoria;
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
	public Integer getIdExistenciaVolumeFixoAgua() {
		return idExistenciaVolumeFixoAgua;
	}
	public void setIdExistenciaVolumeFixoAgua(Integer idExistenciaVolumeFixoAgua) {
		this.idExistenciaVolumeFixoAgua = idExistenciaVolumeFixoAgua;
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
	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}
	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
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
	public Integer getVolumeFaturadoLigacao() {
		return volumeFaturadoLigacao;
	}
	public void setVolumeFaturadoLigacao(Integer volumeFaturadoLigacao) {
		this.volumeFaturadoLigacao = volumeFaturadoLigacao;
	}
	
	public Integer getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(Integer idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
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
		if ( !( obj instanceof HistogramaAguaLigacaoSemQuadraHelper ) ){
			return false;			
		} else {
			HistogramaAguaLigacaoSemQuadraHelper resumoTemp = ( HistogramaAguaLigacaoSemQuadraHelper ) obj;
			
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return 
				propriedadesIguais( this.idGerenciaRegional , resumoTemp.idGerenciaRegional )&&
				propriedadesIguais( this.idUnidadeNegocio , resumoTemp.idUnidadeNegocio )&&
				propriedadesIguais( this.idElo , resumoTemp.idElo )&&
				propriedadesIguais( this.idLocalidade , resumoTemp.idLocalidade )&&
				propriedadesIguais( this.idSetorComercial , resumoTemp.idSetorComercial )&&
				propriedadesIguais( this.codigoSetorComercial , resumoTemp.codigoSetorComercial )&&
				propriedadesIguais( this.idTipoCategoria , resumoTemp.idTipoCategoria )&&
				propriedadesIguais( this.idCategoria , resumoTemp.idCategoria )&&
				propriedadesIguais( this.idSubCategoria , resumoTemp.idSubCategoria )&&
				propriedadesIguais( this.idLigacaoMista , resumoTemp.idLigacaoMista )&&
				propriedadesIguais( this.idContaCaregoria , resumoTemp.idContaCaregoria )&&
				propriedadesIguais( this.idPerfilImovel , resumoTemp.idPerfilImovel )&&
				propriedadesIguais( this.idEsferaPoder , resumoTemp.idEsferaPoder )&&
				propriedadesIguais( this.idSituacaoLigacaoAgua , resumoTemp.idSituacaoLigacaoAgua )&&
				propriedadesIguais( this.idConsumoReal , resumoTemp.idConsumoReal )&&
				propriedadesIguais( this.idExistenciaHidrometro , resumoTemp.idExistenciaHidrometro )&&
				propriedadesIguais( this.idExistenciaPoco , resumoTemp.idExistenciaPoco )&&
				propriedadesIguais( this.idExistenciaVolumeFixoAgua , resumoTemp.idExistenciaVolumeFixoAgua )&&
				propriedadesIguais( this.quantidadeConsumo , resumoTemp.quantidadeConsumo );		
		}	
	}
	
}

