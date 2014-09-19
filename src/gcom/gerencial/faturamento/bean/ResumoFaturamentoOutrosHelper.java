package gcom.gerencial.faturamento.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Classe responsável por ajudar o caso de uso [UC0272] Gerar Resumo Faturamento Outros 
 *
 * @author Marcio Roberto
 * @date 20/04/2006
 */
public class ResumoFaturamentoOutrosHelper {

	private Integer idImovel;
	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidade;
	private Integer idElo;
	private Integer idSetorComercial;
	private Integer idRota;
	private Integer idQuadra;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Integer idPerfilImovel;
	private Integer idEsfera;
	private Integer idTipoClienteResponsavel = 0;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idCategoria;
	private Integer idSubCategoria;
	private Integer idPerfilLigacaoAgua;
	private Integer idPerfilLigacaoEsgoto;
	private Integer anoMesReferencia;
	private Integer idConta;
	private Integer consumoAgua = 0;
	private Integer consumoEsgoto = 0;
	private BigDecimal valorAgua = new BigDecimal(0);
	private BigDecimal valorEsgoto = new BigDecimal(0);
	private Integer quantidadeFaturamento = 0;
	private Integer Gempresa;
	private Integer idFinanciamentoTipo;
	private Integer lictId;
	private BigDecimal valorDocumentosFaturados = new BigDecimal(0);
	private Integer quantidadeDocumentosFaturados = 0;
	private Integer idDocumentoTipo;
	private Short codigoRota;
	
	private Short indicadorHidrometro;
	
	private Integer consumoTarifa = 0;
	private Integer grupoFaturamento = 0;
	

	public Short getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Short codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getGrupoFaturamento() {
		return grupoFaturamento;
	}

	public void setGrupoFaturamento(Integer grupoFaturamento) {
		this.grupoFaturamento = grupoFaturamento;
	}

	public Short getIndicadorHidrometro() {
		return indicadorHidrometro;
	}

	public void setIndicadorHidrometro(Short indicadorHidrometro) {
		this.indicadorHidrometro = indicadorHidrometro;
	}

	public Integer getIdFinanciamentoTipo() {
		return idFinanciamentoTipo;
	}

	public void setIdFinanciamentoTipo(Integer idFinanciamentoTipo) {
		this.idFinanciamentoTipo = idFinanciamentoTipo;
	}

	public Integer getLictId() {
		return lictId;
	}

	public void setLictId(Integer lictId) {
		this.lictId = lictId;
	}

	public Integer getGempresa() {
		return Gempresa;
	}

	public void setGempresa(Integer gempresa) {
		Gempresa = gempresa;
	}


	public Integer getQuantidadeFaturamento() {
		return quantidadeFaturamento;
	}

	public void setQuantidadeFaturamento(Integer quantidadeFaturamento) {
		this.quantidadeFaturamento = quantidadeFaturamento;
	}

	public Integer getConsumoAgua() {
		return consumoAgua;
	}

	public void setConsumoAgua(Integer consumoAgua) {
		this.consumoAgua = consumoAgua;
	}

	public Integer getConsumoEsgoto() {
		return consumoEsgoto;
	}

	public void setConsumoEsgoto(Integer consumoEsgoto) {
		this.consumoEsgoto = consumoEsgoto;
	}
	
	
	/**
	 * Compara duas properiedades inteiras, comparando
	 * seus valores para descobrirmos se sao iguais
	 * @param pro1 Primeira propriedade
	 * @param pro2 Segunda propriedade
	 * @return Se iguais, retorna true
	 */
//	private boolean propriedadesIguais( Integer pro1, Integer pro2 ){
//	  if ( pro2 != null ){
//		  if ( !pro2.equals( pro1 ) ){
//			  return false;
//		  }
//	  } else if ( pro1 != null ){
//		  return false;
//	  }
//	  
//	  // Se chegou ate aqui quer dizer que as propriedades sao iguais
//	  return true;
//	}	
	
    /**
     * Compara dois objetos levando em consideracao apenas as propriedades
     * que identificam o agrupamento
     * 
     * @param obj Objeto a ser comparado com a instancia deste objeto 
     */
	
	public boolean equals(Object obj) {
        if ((this == obj)) {
            return true;
        }
        if (!(obj instanceof ResumoFaturamentoOutrosHelper)) {
            return false;
        }
        ResumoFaturamentoOutrosHelper resumoTemp = (ResumoFaturamentoOutrosHelper) obj;

        return new EqualsBuilder()
        .append(this.getIdGerenciaRegional(), resumoTemp.getIdGerenciaRegional())
        .append(this.getIdUnidadeNegocio(), resumoTemp.getIdUnidadeNegocio())
        .append(this.getIdElo(), resumoTemp.getIdElo())
        .append(this.getIdLocalidade(), resumoTemp.getIdLocalidade())
        .append(this.getIdSetorComercial(), resumoTemp.getIdSetorComercial())
        .append(this.getIdRota(), resumoTemp.getIdRota())                
        .append(this.getIdQuadra(), resumoTemp.getIdQuadra())
        .append(this.getCodigoSetorComercial(), resumoTemp.getCodigoSetorComercial())
        .append(this.getNumeroQuadra(), resumoTemp.getNumeroQuadra())
        .append(this.getIdPerfilImovel(), resumoTemp.getIdPerfilImovel())
        .append(this.getIdEsfera(), resumoTemp.getIdEsfera())
        .append(this.getIdTipoClienteResponsavel(), resumoTemp.getIdTipoClienteResponsavel())
        .append(this.getIdSituacaoLigacaoAgua(), resumoTemp.getIdSituacaoLigacaoAgua())
        .append(this.getIdSituacaoLigacaoEsgoto(), resumoTemp.getIdSituacaoLigacaoEsgoto())
        .append(this.getIdPerfilLigacaoAgua(), resumoTemp.getIdPerfilLigacaoAgua())
        .append(this.getIdPerfilLigacaoEsgoto(), resumoTemp.getIdPerfilLigacaoEsgoto())
        .append(this.getIdCategoria(), resumoTemp.getIdCategoria())
        .append(this.getIdSubCategoria(), resumoTemp.getIdSubCategoria())
        .append(this.getIdFinanciamentoTipo(), resumoTemp.getIdFinanciamentoTipo())
        .append(this.getLictId(), resumoTemp.getLictId())
        .append(this.getIndicadorHidrometro(), resumoTemp.getIndicadorHidrometro())
        .append(this.getConsumoTarifa(), resumoTemp.getConsumoTarifa())
        .append(this.getGrupoFaturamento(), resumoTemp.getGrupoFaturamento())
        .isEquals();
    }
	
//	public boolean equals( Object obj ){
//		
//		if ( !( obj instanceof ResumoFaturamentoOutrosHelper ) ){
//			return false;			
//		} else {
//			ResumoFaturamentoOutrosHelper resumoTemp = ( ResumoFaturamentoOutrosHelper ) obj;
//		  
//		    // Verificamos se todas as propriedades que identificam o objeto sao iguais
//			return 
//			  propriedadesIguais( this.idGerenciaRegional, resumoTemp.idGerenciaRegional ) &&
//			  propriedadesIguais( this.idUnidadeNegocio, resumoTemp.idUnidadeNegocio ) &&
//			  propriedadesIguais( this.idElo, resumoTemp.idElo ) &&
//			  propriedadesIguais( this.idLocalidade, resumoTemp.idLocalidade ) &&
//			  propriedadesIguais( this.idSetorComercial, resumoTemp.idSetorComercial ) &&
//			  propriedadesIguais( this.idRota, resumoTemp.idRota ) &&
//			  propriedadesIguais( this.idQuadra, resumoTemp.idQuadra ) &&
//			  propriedadesIguais( this.codigoSetorComercial, resumoTemp.codigoSetorComercial ) &&
//			  propriedadesIguais( this.numeroQuadra, resumoTemp.numeroQuadra ) &&
//			  propriedadesIguais( this.idPerfilImovel, resumoTemp.idPerfilImovel ) &&
//			  propriedadesIguais( this.idEsfera, resumoTemp.idEsfera ) &&
//			  propriedadesIguais( this.idTipoClienteResponsavel, resumoTemp.idTipoClienteResponsavel ) &&
//			  propriedadesIguais( this.idSituacaoLigacaoAgua, resumoTemp.idSituacaoLigacaoAgua ) &&
//			  propriedadesIguais( this.idSituacaoLigacaoEsgoto, resumoTemp.idSituacaoLigacaoEsgoto ) &&
//			  propriedadesIguais( this.idPerfilLigacaoAgua, resumoTemp.idPerfilLigacaoAgua ) &&
//			  propriedadesIguais( this.idPerfilLigacaoEsgoto, resumoTemp.idPerfilLigacaoEsgoto ) &&
//			  propriedadesIguais( this.idCategoria, resumoTemp.idCategoria ) &&
//			  propriedadesIguais( this.idSubCategoria, resumoTemp.idSubCategoria )&&
//			  propriedadesIguais( this.idFinanciamentoTipo, resumoTemp.idFinanciamentoTipo )&&
//			  propriedadesIguais( this.lictId, resumoTemp.lictId );
//		}		
//	}
	
	
	
	
	
	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo idCategoria.
	 */
	public Integer getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria O idCategoria a ser setado.
	 */
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return Retorna o campo idEsfera.
	 */
	public Integer getIdEsfera() {
		return idEsfera;
	}

	/**
	 * @param idEsfera O idEsfera a ser setado.
	 */
	public void setIdEsfera(Integer idEsfera) {
		this.idEsfera = idEsfera;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public Integer getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idPerfilImovel.
	 */
	public Integer getIdPerfilImovel() {
		return idPerfilImovel;
	}

	/**
	 * @param idPerfilImovel O idPerfilImovel a ser setado.
	 */
	public void setIdPerfilImovel(Integer idPerfilImovel) {
		this.idPerfilImovel = idPerfilImovel;
	}

	/**
	 * @return Retorna o campo idQuadra.
	 */
	public Integer getIdQuadra() {
		return idQuadra;
	}

	/**
	 * @param idQuadra O idQuadra a ser setado.
	 */
	public void setIdQuadra(Integer idQuadra) {
		this.idQuadra = idQuadra;
	}

	/**
	 * @return Retorna o campo idRota.
	 */
	public Integer getIdRota() {
		return idRota;
	}

	/**
	 * @param idRota O idRota a ser setado.
	 */
	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo idSituacaoLigacaoAgua.
	 */
	public Integer getIdSituacaoLigacaoAgua() {
		return idSituacaoLigacaoAgua;
	}

	/**
	 * @param idSituacaoLigacaoAgua O idSituacaoLigacaoAgua a ser setado.
	 */
	public void setIdSituacaoLigacaoAgua(Integer idSituacaoLigacaoAgua) {
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
	}

	/**
	 * @return Retorna o campo idSituacaoLigacaoEsgoto.
	 */
	public Integer getIdSituacaoLigacaoEsgoto() {
		return idSituacaoLigacaoEsgoto;
	}

	/**
	 * @param idSituacaoLigacaoEsgoto O idSituacaoLigacaoEsgoto a ser setado.
	 */
	public void setIdSituacaoLigacaoEsgoto(Integer idSituacaoLigacaoEsgoto) {
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
	}

	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public Integer getNumeroQuadra() {
		return numeroQuadra;
	}

	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(Integer numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public int hashCode() {
		String retorno =  
		this.getIdGerenciaRegional() + "sdf" +
		this.getIdUnidadeNegocio() + "sdf" +
		this.getIdLocalidade() + "sdf" +
		this.getIdElo() + "sdf" +
		this.getIdSetorComercial() + "sdf" +
		this.getIdRota() + "sdf" +
		this.getIdQuadra() + "sdf" +
		this.getCodigoSetorComercial() + "sdf" +
		this.getNumeroQuadra() + "sdf" +
		this.getIdPerfilImovel() + "sdf" +
		this.getIdSituacaoLigacaoAgua() + "sdf" +
		this.getIdSituacaoLigacaoEsgoto() + "sdf" +
		this.getIdPerfilLigacaoAgua() + "sdf" +
		this.getIdPerfilLigacaoEsgoto() + "sdf" +
		this.getIdEsfera() + "sdf";
		return retorno.hashCode();
	}
	
	public ResumoFaturamentoOutrosHelper(Integer idImovel,
			Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idElo, Integer idLocalidade,
			Integer idSetorComercial, Integer idRota, Integer idQuadra,
			Integer codigoSetorComercial, Integer numeroQuadra,
			Integer idPerfilImovel, Integer idSituacaoLigacaoAgua,
			Integer idSituacaoLigacaoEsgoto, Integer idPerfilLigacaoAgua, Integer idPerfilLigacaoEsgoto, 
			Short codigoRota) {
		super();
		
		
		this.idImovel = idImovel;
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
		this.codigoRota = codigoRota;
	}

	public ResumoFaturamentoOutrosHelper(Integer idImovel, Integer idGerenciaRegional, Integer idLocalidade, Integer idSetorComercial, Integer idRota, Integer idQuadra, Integer codigoSetorComercial, Integer numeroQuadra, Integer idPerfilImovel, Integer idSituacaoLigacaoAgua, Integer idSituacaoLigacaoEsgoto, Integer idEsfera) {
		super();
		
		this.idImovel = idImovel;
		this.idGerenciaRegional = idGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.codigoSetorComercial = codigoSetorComercial;
		this.numeroQuadra = numeroQuadra;
		this.idPerfilImovel = idPerfilImovel;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		if (idEsfera != null && idEsfera.intValue() != 0)
			this.idEsfera = idEsfera;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public Integer getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(Integer idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public BigDecimal getValorAgua() {
		return valorAgua;
	}

	public void setValorAgua(BigDecimal valorAgua) {
		this.valorAgua = valorAgua;
	}

	public BigDecimal getValorEsgoto() {
		return valorEsgoto;
	}

	public void setValorEsgoto(BigDecimal valorEsgoto) {
		this.valorEsgoto = valorEsgoto;
	}

	public Integer getIdElo() {
		return idElo;
	}

	public void setIdElo(Integer idElo) {
		this.idElo = idElo;
	}

	public Integer getIdTipoClienteResponsavel() {
		return idTipoClienteResponsavel;
	}

	public void setIdTipoClienteResponsavel(Integer idTipoClienteResponsavel) {
		this.idTipoClienteResponsavel = idTipoClienteResponsavel;
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

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public Integer getQuantidadeDocumentosFaturados() {
		return quantidadeDocumentosFaturados;
	}

	public void setQuantidadeDocumentosFaturados(
			Integer quantidadeDocumentosFaturados) {
		this.quantidadeDocumentosFaturados = quantidadeDocumentosFaturados;
	}

	public BigDecimal getValorDocumentosFaturados() {
		return valorDocumentosFaturados;
	}

	public void setValorDocumentosFaturados(BigDecimal valorDocumentosFaturados) {
		this.valorDocumentosFaturados = valorDocumentosFaturados;
	}

	public Integer getConsumoTarifa() {
		return consumoTarifa;
	}

	public void setConsumoTarifa(Integer consumoTarifa) {
		this.consumoTarifa = consumoTarifa;
	}

	public Integer getIdDocumentoTipo() {
		return idDocumentoTipo;
	}

	public void setIdDocumentoTipo(Integer idDocumentoTipo) {
		this.idDocumentoTipo = idDocumentoTipo;
	}
}
