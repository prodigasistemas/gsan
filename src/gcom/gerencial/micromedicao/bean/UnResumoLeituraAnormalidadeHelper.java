package gcom.gerencial.micromedicao.bean;


/**
 * Classe responsável por ajudar o caso de uso [UC0551] Gerar Resumo de Leitura Anormalidade 
 *
 * @author Ivan Sérgio
 * @date 20/04/2007
 * @Alteracao: 05/12/2008 - CRC719 - Adicionado o campo idLeituraAnormalidadeInformada e quantidadeLeituraInformada
 */
public class UnResumoLeituraAnormalidadeHelper {

	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idCodigoElo;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer idRota;
	private Integer idQuadra;
	private Integer idPerfilImovel;
	private Integer idEsfera;
	private Integer idTipoClienteResponsavel;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idPrincipalCategoria;
	private Integer idPrincipalSubCategoria;
	private Integer idPerfilLigacaoAgua;
	private Integer idPerfilLigacaoEsgoto;
	private Integer idTipoMedicao;
	private Integer idLeituraAnormalidade;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Integer quantidadeLeitura = new Integer(0);
	private Integer idEmpresa;
	private Integer idSituacaoLeitura;
	private Integer idConsumoTarifa;
	private Integer idFaturamentoGrupo;
	
	private Integer idLeituraAnormalidadeInformada;
	private Integer quantidadeLeituraInformada = new Integer(0);
	
	/**
	 * Construtor com a sequencia correta de quebras para o 
	 * caso de uso UC[0551] - Gerar resumo de Leitura Anormalidade
	 * 
	 * OBS - 4 quebras adicionais nao foram passadas neste contrutor,
	 * a saber, idPrincipalCategoria, idPrincipalSubCategoria, idEsfera, idTipoClienteResponsavel e
	 * idSituacaoLigacaoAgua, pois no momento da criacao deste objeto essas informacoes 
	 * nao estão disponiveis. 
	 * 
	 * @param idGerenciaRegional
	 * @param idUnidadeNegocio
	 * @param idCodigoElo
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param idRota
	 * @param idQuadra
	 * @param idPerfilImovel
	 * @param idSituacaoLigacaoAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param idPerfilLigacaoAgua
	 * @param idPerfilLigacaoEsgoto
	 * @param idTipoMedicao
	 * @param idLeituraAnormalidade
	 * @param codigoSetorComercial
	 * @param numeroQuadra
	 * @param idEmpresa
	 * @param idSituacaoLeitura
	 */
	
	public UnResumoLeituraAnormalidadeHelper( 
			Integer idGerenciaRegional,
			Integer idUnidadeNegocio,
			Integer idCodigoElo,
			Integer idLocalidade,
			Integer idSetorComercial,
			Integer idRota,
			Integer idQuadra,
			Integer idPerfilImovel,
			Integer idSituacaoLigacaoEsgoto,
			Integer idPerfilLigacaoAgua,
			Integer idPerfilLigacaoEsgoto,
			Integer idTipoMedicao,
			Integer idLeituraAnormalidade,
			Integer codigoSetorComercial,
			Integer numeroQuadra,
			Integer idEmpresa,
			Integer idSituacaoLeitura){
		
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idCodigoElo = idCodigoElo;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.idPerfilImovel = idPerfilImovel;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;
		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;
		this.idTipoMedicao = idTipoMedicao;
		this.idLeituraAnormalidade = idLeituraAnormalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.idEmpresa = idEmpresa;
		this.idSituacaoLeitura = idSituacaoLeitura;
	}
	
	public UnResumoLeituraAnormalidadeHelper() {
		
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
		if ( !( obj instanceof UnResumoLeituraAnormalidadeHelper ) ){
			return false;
		} else {
			UnResumoLeituraAnormalidadeHelper resumoTemp = ( UnResumoLeituraAnormalidadeHelper ) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return 
			  propriedadesIguais( this.idGerenciaRegional, resumoTemp.idGerenciaRegional ) &&
			  propriedadesIguais( this.idUnidadeNegocio, resumoTemp.idUnidadeNegocio ) &&
			  propriedadesIguais( this.idCodigoElo, resumoTemp.idCodigoElo ) &&
			  propriedadesIguais( this.idLocalidade, resumoTemp.idLocalidade ) &&
			  propriedadesIguais( this.idSetorComercial, resumoTemp.idSetorComercial ) &&
			  propriedadesIguais( this.idRota, resumoTemp.idRota ) &&
			  propriedadesIguais( this.idQuadra, resumoTemp.idQuadra ) &&
			  propriedadesIguais( this.idPerfilImovel, resumoTemp.idPerfilImovel ) &&
			  propriedadesIguais( this.idEsfera, resumoTemp.idEsfera ) &&
			  propriedadesIguais( this.idTipoClienteResponsavel, resumoTemp.idTipoClienteResponsavel ) &&
			  propriedadesIguais( this.idSituacaoLigacaoAgua, resumoTemp.idSituacaoLigacaoAgua ) &&
			  propriedadesIguais( this.idSituacaoLigacaoEsgoto, resumoTemp.idSituacaoLigacaoEsgoto ) &&
			  propriedadesIguais( this.idPrincipalCategoria, resumoTemp.idPrincipalCategoria ) &&
			  propriedadesIguais( this.idPrincipalSubCategoria, resumoTemp.idPrincipalSubCategoria ) &&
			  propriedadesIguais( this.idPerfilLigacaoAgua, resumoTemp.idPerfilLigacaoAgua ) &&
			  propriedadesIguais( this.idPerfilLigacaoEsgoto, resumoTemp.idPerfilLigacaoEsgoto ) &&
			  propriedadesIguais( this.idTipoMedicao, resumoTemp.idTipoMedicao ) &&
			  propriedadesIguais( this.idLeituraAnormalidade, resumoTemp.idLeituraAnormalidade ) &&
			  propriedadesIguais( this.codigoSetorComercial, resumoTemp.codigoSetorComercial ) &&
			  propriedadesIguais( this.numeroQuadra, resumoTemp.numeroQuadra ) &&
			  propriedadesIguais( this.idEmpresa, resumoTemp.idEmpresa ) &&
			  propriedadesIguais( this.idSituacaoLeitura, resumoTemp.idSituacaoLeitura ) &&
			  propriedadesIguais( this.idConsumoTarifa, resumoTemp.idConsumoTarifa) &&
			  propriedadesIguais( this.idFaturamentoGrupo, resumoTemp.idFaturamentoGrupo) &&
			  propriedadesIguais( this.idLeituraAnormalidadeInformada, resumoTemp.idLeituraAnormalidadeInformada);
		}
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getIdCodigoElo() {
		return idCodigoElo;
	}

	public void setIdCodigoElo(Integer idCodigoElo) {
		this.idCodigoElo = idCodigoElo;
	}

	public Integer getIdEsfera() {
		return idEsfera;
	}

	public void setIdEsfera(Integer idEsfera) {
		this.idEsfera = idEsfera;
	}

	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	public Integer getIdLeituraAnormalidade() {
		return idLeituraAnormalidade;
	}

	public void setIdLeituraAnormalidade(Integer idLeituraAnormalidade) {
		this.idLeituraAnormalidade = idLeituraAnormalidade;
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

	public Integer getIdTipoMedicao() {
		return idTipoMedicao;
	}

	public void setIdTipoMedicao(Integer idTipoMedicao) {
		this.idTipoMedicao = idTipoMedicao;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public Integer getQuantidadeLeitura() {
		return quantidadeLeitura;
	}

	public void setQuantidadeLeitura(Integer quantidadeLeitura) {
		this.quantidadeLeitura = quantidadeLeitura;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdSituacaoLeitura() {
		return idSituacaoLeitura;
	}

	public void setIdSituacaoLeitura(Integer idSituacaoLeitura) {
		this.idSituacaoLeitura = idSituacaoLeitura;
	}

	public Integer getIdConsumoTarifa() {
		return idConsumoTarifa;
	}

	public void setIdConsumoTarifa(Integer idConsumoTarifa) {
		this.idConsumoTarifa = idConsumoTarifa;
	}
	
	public Integer getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}
	
	public void setIdFaturamentoGrupo(Integer idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}
	
	/**
	 * @return Retorna o campo idLeituraAnormalidadeInformada.
	 */
	public Integer getIdLeituraAnormalidadeInformada() {
		return idLeituraAnormalidadeInformada;
	}

	/**
	 * @param idLeituraAnormalidadeInformada O idLeituraAnormalidadeInformada a ser setado.
	 */
	public void setIdLeituraAnormalidadeInformada(
			Integer idLeituraAnormalidadeInformada) {
		this.idLeituraAnormalidadeInformada = idLeituraAnormalidadeInformada;
	}

	/**
	 * @return Retorna o campo quantidadeLeituraInformada.
	 */
	public Integer getQuantidadeLeituraInformada() {
		return quantidadeLeituraInformada;
	}

	/**
	 * @param quantidadeLeituraInformada O quantidadeLeituraInformada a ser setado.
	 */
	public void setQuantidadeLeituraInformada(Integer quantidadeLeituraInformada) {
		this.quantidadeLeituraInformada = quantidadeLeituraInformada;
	}
	
}
