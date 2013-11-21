package gcom.gerencial.cadastro.bean;

/**
 * Classe bean para agrupamento dos historicos 
 * de consumo com as quebras solicitadas
 *  
 *
 * @author Bruno Barros
 * @date 20/04/2007
 */

public class ResumoColetaEsgotoHelper {
	
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidade;
	private Integer idElo;
	private Integer idSetorComercial;
	private Integer idRota;
	private Integer idQuadra;		
	private Integer codigoSetorComercial;		
	private Integer numeroQuadra;
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
    
    private Integer quantidadeColetaEsgoto  = new Integer(0); // ------------------
	private Integer quantidadeLigacoes = new Integer(0);  // ------------------
	private Integer quantidadeEconomias = new Integer(0); // ------------------
	private Short indicadorColetaEsgotoExcedente = 0; // ------------------
	private Integer volumeExcedente;
	
	private Short indicadorHidrometro = 0; // ------------------
	private Short poco = 0; // ------------------
	private Short medidoFonteAlternativa = 0; // ------------------
	private Short icVlExcedente = 0; // ------------------
	private float pcEsgoto = 0f;
	private float pcColeta = 0f;
	private Short icVlMinimoEsgoto = 0; // ------------------
	private Integer voFaturado = 0;
	
	private Integer anoMesReferencia = 0;
	private Short codigoRota;
	
	private Short indicadorFaturamento;
	private Integer faturamentoSituacao;
	private Integer motivoFaturamentoSituacao;
	
	public Integer getFaturamentoSituacao() {
		return faturamentoSituacao;
	}
	public void setFaturamentoSituacao(Integer faturamentoSituacao) {
		this.faturamentoSituacao = faturamentoSituacao;
	}
	public Short getIndicadorFaturamento() {
		return indicadorFaturamento;
	}
	public void setIndicadorFaturamento(Short indicadorFaturamento) {
		this.indicadorFaturamento = indicadorFaturamento;
	}
	public Integer getMotivoFaturamentoSituacao() {
		return motivoFaturamentoSituacao;
	}
	public void setMotivoFaturamentoSituacao(Integer motivoFaturamentoSituacao) {
		this.motivoFaturamentoSituacao = motivoFaturamentoSituacao;
	}
	public Short getCodigoRota() {
		return codigoRota;
	}
	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}
	public float getPcEsgoto() {
		return pcEsgoto;
	}
	public void setPcEsgoto(float pcEsgoto) {
		this.pcEsgoto = pcEsgoto;
	}
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
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
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}
	public void setIdNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
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
	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
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
77	 * 
	 * OBS - Duas quebras adicionais nao foram passadas neste contrutor,
	 * a saber, idCategoria e idSubCatergoria, pois no momento da criacao deste objeto
	 * essas informacoes nao estao disponiveis. 
	 *
	 * @param idGerenciaRegional
	 * param  idUnidadeNegocio
	 * @param idLocalidade
	 * @param idElo
	 * @param idSetorComercial
	 * @param idRota
	 * @param idQuadra
	 * @param codigoSetorComercial
	 * @param numeroQuadra
	 * @param idImovelPerfil
	 * @param idSituacaoLigacaoAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param idPerfilLigacaoAgua
	 * @param idPerfilLigacaoEsgoto
	 * @param idTipoConsumo
	 * @param qtdConsumoAgua
	 */
	public ResumoColetaEsgotoHelper( 
			Integer idGerenciaRegional,
			Integer idUnidadeNegocio,
			Integer idLocalidade,
			Integer idElo,
			Integer idSetorComercial,
			Integer idRota,
			Integer idQuadra,
			Integer codigoSetorComercial,
			Integer numeroQuadra,
			Integer idImovelPerfil,
			Integer idSituacaoLigacaoAgua,
			Integer idSituacaoLigacaoEsgoto,			
			Integer idPerfilLigacaoAgua,
			Integer idPerfilLigacaoEsgoto,
			Integer idTipoConsumo,
			Short codigoRota){
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idLocalidade = idLocalidade;
		this.idElo = idElo;
		this.idSetorComercial = idSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra; 
		this.idImovelPerfil = idImovelPerfil;
		this.idLigacaoAguaSituacao = idSituacaoLigacaoAgua;
		this.idLigacaoEsgotoSituacao = idSituacaoLigacaoEsgoto;
		this.idLigacaoAguaPerfil = idPerfilLigacaoAgua;
		this.idLigacaoEsgotoPerfil = idPerfilLigacaoEsgoto;
		this.idConsumoTipo = idTipoConsumo;
		this.codigoRota = codigoRota;
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
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}
	
	/**
	 * Compara duas properiedades inteiras, comparando
	 * seus valores para descobrirmos se sao iguais
	 * @param pro1 Primeira propriedade
	 * @param pro2 Segunda propriedade
	 * @return Se iguais, retorna true
	 */
	private boolean propriedadesIguais( Object pro1, Object pro2 ){
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
		
		if ( !( obj instanceof ResumoColetaEsgotoHelper ) ){
			return false;			
		} else {
			ResumoColetaEsgotoHelper resumoTemp = ( ResumoColetaEsgotoHelper ) obj;
		  
		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
			return 
			  propriedadesIguais( this.idGerenciaRegional, resumoTemp.idGerenciaRegional ) &&
			  propriedadesIguais( this.idUnidadeNegocio, resumoTemp.idUnidadeNegocio ) &&
			  propriedadesIguais( this.idLocalidade, resumoTemp.idLocalidade ) &&
			  propriedadesIguais( this.idElo, resumoTemp.idElo ) &&
			  propriedadesIguais( this.idSetorComercial, resumoTemp.idSetorComercial ) &&
			  propriedadesIguais( this.idRota, resumoTemp.idRota ) &&
			  propriedadesIguais( this.idQuadra, resumoTemp.idQuadra ) &&
			  propriedadesIguais( this.codigoSetorComercial, resumoTemp.codigoSetorComercial ) &&
			  propriedadesIguais( this.numeroQuadra, resumoTemp.numeroQuadra ) &&
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
			  propriedadesIguais( this.indicadorColetaEsgotoExcedente, resumoTemp.indicadorColetaEsgotoExcedente ) &&
			  ////////////////////////////////////////////////////////////////////////////
			  propriedadesIguais( this.poco, resumoTemp.poco ) &&
			  propriedadesIguais( this.medidoFonteAlternativa, resumoTemp.medidoFonteAlternativa ) &&
			  propriedadesIguais( this.icVlExcedente, resumoTemp.icVlExcedente ) &&
			  propriedadesIguais( this.icVlMinimoEsgoto, resumoTemp.icVlMinimoEsgoto ) &&
			  propriedadesIguais( this.indicadorHidrometro, resumoTemp.indicadorHidrometro ) &&
			  propriedadesIguais( this.pcEsgoto, resumoTemp.pcEsgoto ) &&
			  propriedadesIguais( this.pcColeta, resumoTemp.pcColeta ) &&
			  propriedadesIguais( this.indicadorFaturamento, resumoTemp.indicadorFaturamento);
		}	
	}
	public Integer getQuantidadeColetaEsgoto() {
		return quantidadeColetaEsgoto;
	}
	public void setQuantidadeColetaEsgoto(Integer quantidadeColetaEsgoto) {
		this.quantidadeColetaEsgoto = quantidadeColetaEsgoto;
	}
	public float getPcColeta() {
		return pcColeta;
	}
	public void setPcColeta(float pcColeta) {
		this.pcColeta = pcColeta;
	}
	public Short getIcVlExcedente() {
		return icVlExcedente;
	}
	public void setIcVlExcedente(Short icVlExcedente) {
		this.icVlExcedente = icVlExcedente;
	}
	public Short getIcVlMinimoEsgoto() {
		return icVlMinimoEsgoto;
	}
	public void setIcVlMinimoEsgoto(Short icVlMinimoEsgoto) {
		this.icVlMinimoEsgoto = icVlMinimoEsgoto;
	}
	public Short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}
	public void setIndicadorHidrometro(Short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}
	public Short getMedidoFonteAlternativa() {
		return medidoFonteAlternativa;
	}
	public void setMedidoFonteAlternativa(Short medidoFonteAlternativa) {
		this.medidoFonteAlternativa = medidoFonteAlternativa;
	}
	public Short getPoco() {
		return poco;
	}
	public void setPoco(Short poco) {
		this.poco = poco;
	}
	public Short getIndicadorColetaEsgotoExcedente() {
		return indicadorColetaEsgotoExcedente;
	}
	public void setIndicadorColetaEsgotoExcedente(
			Short indicadorColetaEsgotoExcedente) {
		this.indicadorColetaEsgotoExcedente = indicadorColetaEsgotoExcedente;
	}
	public Integer getVolumeExcedente() {
		return volumeExcedente;
	}
	public void setVolumeExcedente(Integer volumeExcedente) {
		this.volumeExcedente = volumeExcedente;
	}
	public Integer getVoFaturado() {
		return voFaturado;
	}
	public void setVoFaturado(Integer voFaturado) {
		this.voFaturado = voFaturado;
	}
}

