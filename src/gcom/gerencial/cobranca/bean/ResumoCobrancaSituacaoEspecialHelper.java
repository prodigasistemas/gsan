package gcom.gerencial.cobranca.bean;


/**
 * Classe responsável por ajudar o caso de uso [UC0275] Gerar Resumo das Ligacoes/Economias 
 *
 * @author Thiago Toscano
 * @date 20/04/2006
 */
public class ResumoCobrancaSituacaoEspecialHelper {
	
	private Integer idImovel;
	private Integer idGerenciaRegional;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer idRota;
	private Integer idQuadra;
	private Integer codigoSetorComercial;
	private Integer numeroQuadra;
	private Integer idPerfilImovel;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idCategoria;
	private Integer idEsfera;

	private Integer idEspecialCobranca;
	private Integer idMotivoSituacaoEspecialCobranca;
	private Integer anoMesInicioSituacaoEspecial;
	private Integer anoMesFinalSituacaoEspecial;
	private int quantidadeImovel = 1;


	public boolean equals(Object obj) {

		if (obj instanceof ResumoCobrancaSituacaoEspecialHelper) {
			ResumoCobrancaSituacaoEspecialHelper objeto = (ResumoCobrancaSituacaoEspecialHelper) obj;

			if (objeto.getIdGerenciaRegional() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdGerenciaRegional().equals(this.getIdGerenciaRegional())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdGerenciaRegional() != null) {
				return false;
			}

			if (objeto.getIdLocalidade() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdLocalidade().equals(this.getIdLocalidade())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdLocalidade() != null) {
				return false;
			}

			if (objeto.getIdSetorComercial() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdSetorComercial().equals(this.getIdSetorComercial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdSetorComercial() != null) {
				return false;
			}

			if (objeto.getIdRota() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdRota().equals(this.getIdRota())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdRota() != null) {
				return false;
			}

			if (objeto.getIdQuadra() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdQuadra().equals(this.getIdQuadra())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdQuadra() != null) {
				return false;
			}

			if (objeto.getCodigoSetorComercial() != null) {
				// se os atributos forem diferentes
				if (!objeto.getCodigoSetorComercial().equals(this.getCodigoSetorComercial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getCodigoSetorComercial() != null) {
				return false;
			}

			if (objeto.getNumeroQuadra() != null) {
				// se os atributos forem diferentes
				if (!objeto.getNumeroQuadra().equals(this.getNumeroQuadra())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getNumeroQuadra() != null) {
				return false;
			}

			if (objeto.getIdPerfilImovel() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdPerfilImovel().equals(this.getIdPerfilImovel())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdPerfilImovel() != null) {
				return false;
			}

			if (objeto.getIdSituacaoLigacaoAgua() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdSituacaoLigacaoAgua().equals(this.getIdSituacaoLigacaoAgua())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdSituacaoLigacaoAgua() != null) {
				return false;
			}

			if (objeto.getIdSituacaoLigacaoEsgoto() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdSituacaoLigacaoEsgoto().equals(this.getIdSituacaoLigacaoEsgoto())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdSituacaoLigacaoEsgoto() != null) {
				return false;
			}

			if (objeto.getIdCategoria() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdCategoria().equals(this.getIdCategoria())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdCategoria() != null) {
				return false;
			}

			if (objeto.getIdEsfera() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdEsfera().equals(this.getIdEsfera())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdEsfera() != null) {
				return false;
			}

			if (objeto.getIdEspecialCobranca() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdEspecialCobranca().equals(this.getIdEspecialCobranca())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdEspecialCobranca() != null) {
				return false;
			}

			if (objeto.getIdMotivoSituacaoEspecialCobranca() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdMotivoSituacaoEspecialCobranca().equals(this.getIdMotivoSituacaoEspecialCobranca())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdMotivoSituacaoEspecialCobranca() != null) {
				return false;
			}

			if (objeto.getAnoMesInicioSituacaoEspecial() != null) {
				// se os atributos forem diferentes
				if (!objeto.getAnoMesInicioSituacaoEspecial().equals(this.getAnoMesInicioSituacaoEspecial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getAnoMesInicioSituacaoEspecial() != null) {
				return false;
			}

			if (objeto.getAnoMesFinalSituacaoEspecial() != null) {
				// se os atributos forem diferentes
				if (!objeto.getAnoMesFinalSituacaoEspecial().equals(this.getAnoMesFinalSituacaoEspecial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getAnoMesFinalSituacaoEspecial() != null) {
				return false;
			}
		} else {
			// se o objeto passado nao for do tipo ImovelResumoLigacaoEconomiaHelper 
			return false;
		}

		// todos os parametros sao iguais
		return true;
	}
	
	public int hashCode() {
		
		String retorno =  
		
		this.getIdGerenciaRegional() + "sdf" +
		this.getIdLocalidade() + "sdf" +
		this.getIdSetorComercial() + "sdf" +
		this.getIdRota() + "sdf" +
		this.getIdQuadra() + "sdf" +
		this.getCodigoSetorComercial() + "sdf" +
		this.getNumeroQuadra() + "sdf" +
		this.getIdPerfilImovel() + "sdf" +
		this.getIdSituacaoLigacaoAgua() + "sdf" +
		this.getIdSituacaoLigacaoEsgoto() + "sdf" +
		this.getIdCategoria() + "sdf" +
		this.getIdEsfera() + "sdf" +
		this.getIdEspecialCobranca() + "sdf" +
		this.getIdMotivoSituacaoEspecialCobranca() + "sdf" +
		this.getAnoMesFinalSituacaoEspecial() + "sdf" +
		this.getAnoMesInicioSituacaoEspecial() + "sdf";

		return retorno.hashCode();
	}
	
	/**
	 * @return Retorna o campo anoMesFinalSituacaoEspecial.
	 */
	public Integer getAnoMesFinalSituacaoEspecial() {
		return anoMesFinalSituacaoEspecial;
	}

	/**
	 * @param anoMesFinalSituacaoEspecial O anoMesFinalSituacaoEspecial a ser setado.
	 */
	public void setAnoMesFinalSituacaoEspecial(Integer anoMesFinalSituacaoEspecial) {
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
	}

	/**
	 * @return Retorna o campo anoMesInicioSituacaoEspecial.
	 */
	public Integer getAnoMesInicioSituacaoEspecial() {
		return anoMesInicioSituacaoEspecial;
	}

	/**
	 * @param anoMesInicioSituacaoEspecial O anoMesInicioSituacaoEspecial a ser setado.
	 */
	public void setAnoMesInicioSituacaoEspecial(Integer anoMesInicioSituacaoEspecial) {
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
	}

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
	 * @return Retorna o campo idEspecialCobranca.
	 */
	public Integer getIdEspecialCobranca() {
		return idEspecialCobranca;
	}

	/**
	 * @param idEspecialCobranca O idEspecialCobranca a ser setado.
	 */
	public void setIdEspecialCobranca(Integer idEspecialCobranca) {
		this.idEspecialCobranca = idEspecialCobranca;
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
	 * @return Retorna o campo idMotivoSituacaoEspecialCobranca.
	 */
	public Integer getIdMotivoSituacaoEspecialCobranca() {
		return idMotivoSituacaoEspecialCobranca;
	}

	/**
	 * @param idMotivoSituacaoEspecialCobranca O idMotivoSituacaoEspecialCobranca a ser setado.
	 */
	public void setIdMotivoSituacaoEspecialCobranca(
			Integer idMotivoSituacaoEspecialCobranca) {
		this.idMotivoSituacaoEspecialCobranca = idMotivoSituacaoEspecialCobranca;
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

	/**
	 * @return Retorna o campo quantidadeImovel.
	 */
	public int getQuantidadeImovel() {
		return quantidadeImovel;
	}

	/**
	 * @param quantidadeImovel O quantidadeImovel a ser setado.
	 */
	public void setQuantidadeImovel(int quantidadeImovel) {
		this.quantidadeImovel = quantidadeImovel;
	}

	public ResumoCobrancaSituacaoEspecialHelper (Integer idImovel, Integer idGerenciaRegional, Integer idLocalidade, Integer idSetorComercial, Integer idRota, Integer idQuadra, Integer codigoSetorComercial, Integer numeroQuadra, Integer idPerfilImovel, Integer idSituacaoLigacaoAgua, Integer idSituacaoLigacaoEsgoto, Integer idCategoria, Integer idEsfera, Integer idEspecialCobranca, Integer idMotivoSituacaoEspecialCobranca, Integer anoMesInicioSituacaoEspecial, Integer anoMesFinalSituacaoEspecial) {
		// TODO Auto-generated constructor stub
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
		this.idCategoria = idCategoria;
		this.idEsfera = idEsfera;
		this.idEspecialCobranca = idEspecialCobranca;
		this.idMotivoSituacaoEspecialCobranca = idMotivoSituacaoEspecialCobranca;
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
	}

	public ResumoCobrancaSituacaoEspecialHelper (Integer idImovel, Integer idGerenciaRegional, Integer idLocalidade, Integer idSetorComercial, Integer idRota, Integer idQuadra, Integer codigoSetorComercial, Integer numeroQuadra, Integer idPerfilImovel, Integer idSituacaoLigacaoAgua, Integer idSituacaoLigacaoEsgoto, Integer idEsfera, Integer idEspecialCobranca, Integer idMotivoSituacaoEspecialCobranca, Integer anoMesInicioSituacaoEspecial, Integer anoMesFinalSituacaoEspecial) {
		// TODO Auto-generated constructor stub
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
		this.idEspecialCobranca = idEspecialCobranca;
		this.idMotivoSituacaoEspecialCobranca = idMotivoSituacaoEspecialCobranca;
		this.anoMesInicioSituacaoEspecial = anoMesInicioSituacaoEspecial;
		this.anoMesFinalSituacaoEspecial = anoMesFinalSituacaoEspecial;
	}
}
