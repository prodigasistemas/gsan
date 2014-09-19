package gcom.gerencial.faturamento.bean;

import java.math.BigDecimal;

/**
 * Classe responsável por ajudar o caso de uso [UC0715] Gerar Resumo do Refaturamento 
 *
 * @author Roberto Barbalho
 * @date 09/11/2007
 */
public class ResumoReFaturamentoHelper {

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
	private Integer anoMesReferenciaConta;
	private Integer idConta;

	private Integer consumoAgua = 0;
	private Integer consumoEsgoto = 0;
	private BigDecimal valorAgua = new BigDecimal(0);
	private BigDecimal valorEsgoto = new BigDecimal(0);
	private Integer quantidadeFaturamento = 0;
	
	private Integer qtContasRetificadas = 0;
	private Integer qtContasCanceladas = 0;
	private Integer qtContasIncluidas = 0;

	private Integer qtGuiasIncluidas = 0;
	private Integer qtGuiasCanceladas = 0;
	
	private BigDecimal vlCanceladoDebitos = new BigDecimal(0);
	private BigDecimal vlCanceladoCreditos = new BigDecimal(0);
	private BigDecimal vlIncluidoDebitos = new BigDecimal(0);
	private BigDecimal vlIncluidoCreditos = new BigDecimal(0);

	private BigDecimal vlCanceladoImpostos = new BigDecimal(0);
	private BigDecimal vlCanceladoGuias = new BigDecimal(0);
	private BigDecimal vlIncluidoImpostos = new BigDecimal(0);
	private BigDecimal vlIncluidoGuias = new BigDecimal(0);
	
	
	private BigDecimal vlCanceladoAgua = new BigDecimal(0);
	private BigDecimal vlCanceladoEsgoto = new BigDecimal(0);
	private BigDecimal vlIncluidoAgua = new BigDecimal(0);
	private BigDecimal vlIncluidoEsgoto = new BigDecimal(0);

	private Integer voCanceladoAgua = 0;
	private Integer voCanceladoEsgoto = 0;
	private Integer voIncludoAgua = 0;
	private Integer voIncluidoEsgoto = 0;
	
	private Integer Gempresa;


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
	
	public boolean equals(Object obj) {

		if (obj instanceof ResumoReFaturamentoHelper) {
			ResumoReFaturamentoHelper irleh = (ResumoReFaturamentoHelper) obj;


			// VERIFICACAO DE AGRUPAMENTO ***** GERENCIA REGIONAL
			if (irleh.getIdGerenciaRegional() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdGerenciaRegional().equals(this.getIdGerenciaRegional())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdGerenciaRegional() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** UNIDADE DE NEGOCIO
			if (irleh.getIdUnidadeNegocio() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdUnidadeNegocio().equals(this.getIdUnidadeNegocio())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdUnidadeNegocio() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** LOCALIDADE
			if (irleh.getIdLocalidade() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdLocalidade().equals(this.getIdLocalidade())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdLocalidade() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** SETOR COMERCIAL
			if (irleh.getIdSetorComercial() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdSetorComercial().equals(this.getIdSetorComercial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdSetorComercial() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** ROTA
			if (irleh.getIdRota() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdRota().equals(this.getIdRota())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdRota() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** QUADRA
			if (irleh.getIdQuadra() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdQuadra().equals(this.getIdQuadra())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdQuadra() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** CODIGO DO SETOR COMERCIAL
			if (irleh.getCodigoSetorComercial() != null) {
				// se os atributos forem diferentes
				if (!irleh.getCodigoSetorComercial().equals(this.getCodigoSetorComercial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getCodigoSetorComercial() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** NUMERO DA QUADRA
			if (irleh.getNumeroQuadra() != null) {
				// se os atributos forem diferentes
				if (!irleh.getNumeroQuadra().equals(this.getNumeroQuadra())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getNumeroQuadra() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** PERFIL DO IMOVEL
			if (irleh.getIdPerfilImovel() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdPerfilImovel().equals(this.getIdPerfilImovel())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdPerfilImovel() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** ESFERA DE PODER
			if (irleh.getIdEsfera() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdEsfera().equals(this.getIdEsfera())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdEsfera() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** TIPO DE CLIENTE
			if (irleh.getIdTipoClienteResponsavel() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdTipoClienteResponsavel().equals(this.getIdTipoClienteResponsavel())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdTipoClienteResponsavel() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** SITUACAO DA LIGACAO DA AGUA
			if (irleh.getIdSituacaoLigacaoAgua() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdSituacaoLigacaoAgua().equals(this.getIdSituacaoLigacaoAgua())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdSituacaoLigacaoAgua() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** SITUACAO DA LIGACAO DO ESGOTO
			if (irleh.getIdSituacaoLigacaoEsgoto() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdSituacaoLigacaoEsgoto().equals(this.getIdSituacaoLigacaoEsgoto())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdSituacaoLigacaoEsgoto() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** CATEGORIA
			if (irleh.getIdCategoria() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdCategoria().equals(this.getIdCategoria())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdCategoria() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** SUB CATEGORIA
			if (irleh.getIdSubCategoria() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdSubCategoria().equals(this.getIdSubCategoria())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdSubCategoria() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** PERFIL DA LIGACAO DA AGUA
			if (irleh.getIdPerfilLigacaoAgua() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdPerfilLigacaoAgua().equals(this.getIdPerfilLigacaoAgua())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdPerfilLigacaoAgua() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** PERFIL DA LIGACAO DO ESGOTO
			if (irleh.getIdPerfilLigacaoEsgoto() != null) {
				// se os atributos forem diferentes
				if (!irleh.getIdPerfilLigacaoEsgoto().equals(this.getIdPerfilLigacaoEsgoto())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdPerfilLigacaoEsgoto() != null) {
				return false;
			}
		
			// VERIFICACAO DE AGRUPAMENTO ***** ANO MES REFERENCIA 
			if (irleh.getAnoMesReferencia() != null) {
				// se os atributos forem diferentes
				if (!irleh.getAnoMesReferencia ().equals(this.getAnoMesReferencia() )) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getAnoMesReferencia() != null) {
				return false;
			}
		
			// VERIFICACAO DE AGRUPAMENTO ***** ANO MES REFERENCIA CONTA REFATURADA
			if (irleh.getAnoMesReferenciaConta() != null) {
				// se os atributos forem diferentes
				if (!irleh.getAnoMesReferenciaConta().equals(this.getAnoMesReferenciaConta() )) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getAnoMesReferenciaConta() != null) {
				return false;
			}
		
			
			
			
		
		} else {
			// se o objeto passado nao for do tipo ImovelResumoLigacaoEconomiaHelper 
			return false;
		}

		// todos os parametros sao iguais
		return true;
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
	
	public ResumoReFaturamentoHelper(Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idElo, Integer idLocalidade,
			Integer idSetorComercial, Integer idRota, Integer idQuadra,
			Integer codigoSetorComercial, Integer numeroQuadra,
			Integer idPerfilImovel, Integer idSituacaoLigacaoAgua,
			Integer idSituacaoLigacaoEsgoto, Integer idPerfilLigacaoAgua, Integer idPerfilLigacaoEsgoto) {
		super();
		
		
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
	}

	public ResumoReFaturamentoHelper(Integer idGerenciaRegional, Integer idLocalidade, Integer idSetorComercial, Integer idRota, Integer idQuadra, Integer codigoSetorComercial, Integer numeroQuadra, Integer idPerfilImovel, Integer idSituacaoLigacaoAgua, Integer idSituacaoLigacaoEsgoto, Integer idEsfera) {
		super();
		
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

	public Integer getQtContasCanceladas() {
		return qtContasCanceladas;
	}

	public void setQtContasCanceladas(Integer qtContasCanceladas) {
		this.qtContasCanceladas = qtContasCanceladas;
	}

	public Integer getQtContasIncluidas() {
		return qtContasIncluidas;
	}

	public void setQtContasIncluidas(Integer qtContasIncluidas) {
		this.qtContasIncluidas = qtContasIncluidas;
	}

	public Integer getQtContasRetificadas() {
		return qtContasRetificadas;
	}

	public void setQtContasRetificadas(Integer qtContasRetificadas) {
		this.qtContasRetificadas = qtContasRetificadas;
	}

	public BigDecimal getVlCanceladoAgua() {
		return vlCanceladoAgua;
	}

	public void setVlCanceladoAgua(BigDecimal vlCanceladoAgua) {
		this.vlCanceladoAgua = vlCanceladoAgua;
	}

	public BigDecimal getVlCanceladoEsgoto() {
		return vlCanceladoEsgoto;
	}

	public void setVlCanceladoEsgoto(BigDecimal vlCanceladoEsgoto) {
		this.vlCanceladoEsgoto = vlCanceladoEsgoto;
	}

	public BigDecimal getVlIncluidoAgua() {
		return vlIncluidoAgua;
	}

	public void setVlIncluidoAgua(BigDecimal vlIncluidoAgua) {
		this.vlIncluidoAgua = vlIncluidoAgua;
	}

	public BigDecimal getVlIncluidoEsgoto() {
		return vlIncluidoEsgoto;
	}

	public void setVlIncluidoEsgoto(BigDecimal vlIncluidoEsgoto) {
		this.vlIncluidoEsgoto = vlIncluidoEsgoto;
	}

	public Integer getVoCanceladoAgua() {
		return voCanceladoAgua;
	}

	public void setVoCanceladoAgua(Integer voCanceladoAgua) {
		this.voCanceladoAgua = voCanceladoAgua;
	}

	public Integer getVoCanceladoEsgoto() {
		return voCanceladoEsgoto;
	}

	public void setVoCanceladoEsgoto(Integer voCanceladoEsgoto) {
		this.voCanceladoEsgoto = voCanceladoEsgoto;
	}

	public Integer getVoIncludoAgua() {
		return voIncludoAgua;
	}

	public void setVoIncludoAgua(Integer voIncludoAgua) {
		this.voIncludoAgua = voIncludoAgua;
	}

	public Integer getVoIncluidoEsgoto() {
		return voIncluidoEsgoto;
	}

	public void setVoIncluidoEsgoto(Integer voIncluidoEsgoto) {
		this.voIncluidoEsgoto = voIncluidoEsgoto;
	}

	public Integer getAnoMesReferenciaConta() {
		return anoMesReferenciaConta;
	}

	public void setAnoMesReferenciaConta(Integer anoMesReferenciaConta) {
		this.anoMesReferenciaConta = anoMesReferenciaConta;
	}

	public Integer getQtGuiasCanceladas() {
		return qtGuiasCanceladas;
	}

	public void setQtGuiasCanceladas(Integer qtGuiasCanceladas) {
		this.qtGuiasCanceladas = qtGuiasCanceladas;
	}

	public Integer getQtGuiasIncluidas() {
		return qtGuiasIncluidas;
	}

	public void setQtGuiasIncluidas(Integer qtGuiasIncluidas) {
		this.qtGuiasIncluidas = qtGuiasIncluidas;
	}

	public BigDecimal getVlCanceladoCreditos() {
		return vlCanceladoCreditos;
	}

	public void setVlCanceladoCreditos(BigDecimal vlCanceladoCreditos) {
		this.vlCanceladoCreditos = vlCanceladoCreditos;
	}

	public BigDecimal getVlCanceladoDebitos() {
		return vlCanceladoDebitos;
	}

	public void setVlCanceladoDebitos(BigDecimal vlCanceladoDebitos) {
		this.vlCanceladoDebitos = vlCanceladoDebitos;
	}

	public BigDecimal getVlCanceladoGuias() {
		return vlCanceladoGuias;
	}

	public void setVlCanceladoGuias(BigDecimal vlCanceladoGuias) {
		this.vlCanceladoGuias = vlCanceladoGuias;
	}

	public BigDecimal getVlCanceladoImpostos() {
		return vlCanceladoImpostos;
	}

	public void setVlCanceladoImpostos(BigDecimal vlCanceladoImpostos) {
		this.vlCanceladoImpostos = vlCanceladoImpostos;
	}

	public BigDecimal getVlIncluidoCreditos() {
		return vlIncluidoCreditos;
	}

	public void setVlIncluidoCreditos(BigDecimal vlIncluidoCreditos) {
		this.vlIncluidoCreditos = vlIncluidoCreditos;
	}

	public BigDecimal getVlIncluidoDebitos() {
		return vlIncluidoDebitos;
	}

	public void setVlIncluidoDebitos(BigDecimal vlIncluidoDebitos) {
		this.vlIncluidoDebitos = vlIncluidoDebitos;
	}

	public BigDecimal getVlIncluidoGuias() {
		return vlIncluidoGuias;
	}

	public void setVlIncluidoGuias(BigDecimal vlIncluidoGuias) {
		this.vlIncluidoGuias = vlIncluidoGuias;
	}

	public BigDecimal getVlIncluidoImpostos() {
		return vlIncluidoImpostos;
	}

	public void setVlIncluidoImpostos(BigDecimal vlIncluidoImpostos) {
		this.vlIncluidoImpostos = vlIncluidoImpostos;
	}
}
