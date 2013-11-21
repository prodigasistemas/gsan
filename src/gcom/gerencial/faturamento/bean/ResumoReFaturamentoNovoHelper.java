/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gerencial.faturamento.bean;

import java.math.BigDecimal;

/**
 * Classe responsável por ajudar o caso de uso [UC0572] Gerar Resumo do Refaturamento NOVO
 *
 * @author Fernando Fontelles
 * @date 29/06/2010
 */
public class ResumoReFaturamentoNovoHelper {	private Integer idGerenciaRegional;
	private Integer idUnidadeNegocio;
	private Integer idLocalidade;
	private Integer idElo;
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	private Integer idPerfilImovel;		private Integer idSituacaoLigacaoAgua;	private Integer idSituacaoLigacaoEsgoto;		private Integer idCategoria;	private Integer idSubCategoria;
	private Integer idEsfera;
	private Integer idTipoClienteResponsavel;	
	private Integer idPerfilLigacaoAgua;
	private Integer idPerfilLigacaoEsgoto;		private Integer consumoTarifa;		private Short hidrometro;		private Integer anoMesReferencia;
	private Integer anoMesReferenciaConta;		private Integer creditoOrigem = null;		private Integer lancamentoItemContabil;	private Integer documentoTipo;	
	private Integer idConta;		private Integer qtContasRetificadas = 0;		private Integer qtContasCanceladas = 0;		private Integer qtContasIncluidas = 0;		private BigDecimal vlCanceladoAgua = new BigDecimal(0);		private BigDecimal vlCanceladoEsgoto = new BigDecimal(0);		private BigDecimal vlCanceladoOutro = new BigDecimal(0);		private BigDecimal vlCanceladoCreditos = new BigDecimal(0);
	private Integer consumoAgua = 0;
	private Integer consumoEsgoto = 0;
	private BigDecimal valorAgua = new BigDecimal(0);
	private BigDecimal valorEsgoto = new BigDecimal(0);
	private Integer quantidadeFaturamento = 0;
	private BigDecimal vlCanceladoImpostos = new BigDecimal(0);
	private Integer voCanceladoAgua = 0;
	private Integer voCanceladoEsgoto = 0;
	private BigDecimal vlIncluidoAgua = new BigDecimal(0);
	private BigDecimal vlIncluidoEsgoto = new BigDecimal(0);		private BigDecimal vlIncluidoOutros = new BigDecimal(0);		private BigDecimal vlIncluidoCreditos = new BigDecimal(0);		private BigDecimal vlIncluidoImpostos = new BigDecimal(0);		private Integer voIncludoAgua = 0;		private Integer voIncluidoEsgoto = 0;		private BigDecimal vlCanceladoGuias = new BigDecimal(0);		private Integer qtGuiasCanceladas = 0;	private Short icExistenciaContaCanceladaRetificacao;		//------------------------------------------------	//Criado na correcao dos erros encontrados por Ary		private BigDecimal vlAguaRetificado = new BigDecimal(0);		private BigDecimal vlEsgotoRetificado = new BigDecimal(0);		private BigDecimal vlDebitoRetificado = new BigDecimal(0);		private BigDecimal vlCreditoRetificado = new BigDecimal(0);		private BigDecimal vlImpostoRetificado = new BigDecimal(0);		private Integer consumoAguaRetificado = 0;		private Integer consumoEsgotoRetificado = 0;	//-------------------------------------------------		public Short getIcExistenciaContaCanceladaRetificacao() {		return icExistenciaContaCanceladaRetificacao;	}	public void setIcExistenciaContaCanceladaRetificacao(			Short icExistenciaContaCanceladaRetificacao) {		this.icExistenciaContaCanceladaRetificacao = icExistenciaContaCanceladaRetificacao;	}

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

		if (obj instanceof ResumoReFaturamentoNovoHelper) {
			ResumoReFaturamentoNovoHelper helper = (ResumoReFaturamentoNovoHelper) obj;
			// VERIFICACAO DE AGRUPAMENTO ***** GERENCIA REGIONAL
			if (helper.getIdGerenciaRegional() != null) {
				// se os atributos forem diferentes
				if (!helper.getIdGerenciaRegional().equals(this.getIdGerenciaRegional())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdGerenciaRegional() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** UNIDADE DE NEGOCIO
			if (helper.getIdUnidadeNegocio() != null) {
				// se os atributos forem diferentes
				if (!helper.getIdUnidadeNegocio().equals(this.getIdUnidadeNegocio())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdUnidadeNegocio() != null) {
				return false;
			}
//			VERIFICACAO DE AGRUPAMENTO ***** ELO			if (helper.getIdElo() != null) {				// se os atributos forem diferentes				if (!helper.getIdElo().equals(this.getIdElo())) {					return false;				}				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso			} else if (this.getIdElo() != null) {				return false;			}
			// VERIFICACAO DE AGRUPAMENTO ***** LOCALIDADE
			if (helper.getIdLocalidade() != null) {
				// se os atributos forem diferentes
				if (!helper.getIdLocalidade().equals(this.getIdLocalidade())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdLocalidade() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** SETOR COMERCIAL
			if (helper.getIdSetorComercial() != null) {
				// se os atributos forem diferentes
				if (!helper.getIdSetorComercial().equals(this.getIdSetorComercial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdSetorComercial() != null) {
				return false;
			}
			// VERIFICACAO DE AGRUPAMENTO ***** CODIGO DO SETOR COMERCIAL
			if (helper.getCodigoSetorComercial() != null) {
				// se os atributos forem diferentes
				if (!helper.getCodigoSetorComercial().equals(this.getCodigoSetorComercial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getCodigoSetorComercial() != null) {
				return false;
			}
			// VERIFICACAO DE AGRUPAMENTO ***** PERFIL DO IMOVEL
			if (helper.getIdPerfilImovel() != null) {
				// se os atributos forem diferentes
				if (!helper.getIdPerfilImovel().equals(this.getIdPerfilImovel())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdPerfilImovel() != null) {
				return false;
			}
//			 VERIFICACAO DE AGRUPAMENTO ***** SITUACAO DA LIGACAO DA AGUA			if (helper.getIdSituacaoLigacaoAgua() != null) {				// se os atributos forem diferentes				if (!helper.getIdSituacaoLigacaoAgua().equals(this.getIdSituacaoLigacaoAgua())) {					return false;				}				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso			} else if (this.getIdSituacaoLigacaoAgua() != null) {				return false;			}			// VERIFICACAO DE AGRUPAMENTO ***** SITUACAO DA LIGACAO DO ESGOTO			if (helper.getIdSituacaoLigacaoEsgoto() != null) {				// se os atributos forem diferentes				if (!helper.getIdSituacaoLigacaoEsgoto().equals(this.getIdSituacaoLigacaoEsgoto())) {					return false;				}				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso			} else if (this.getIdSituacaoLigacaoEsgoto() != null) {				return false;			}			//			 VERIFICACAO DE AGRUPAMENTO ***** CATEGORIA			if (helper.getIdCategoria() != null) {				// se os atributos forem diferentes				if (!helper.getIdCategoria().equals(this.getIdCategoria())) {					return false;				}				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso			} else if (this.getIdCategoria() != null) {				return false;			}			// VERIFICACAO DE AGRUPAMENTO ***** SUB CATEGORIA			if (helper.getIdSubCategoria() != null) {				// se os atributos forem diferentes				if (!helper.getIdSubCategoria().equals(this.getIdSubCategoria())) {					return false;				}				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso			} else if (this.getIdSubCategoria() != null) {				return false;			}
			// VERIFICACAO DE AGRUPAMENTO ***** ESFERA DE PODER
			if (helper.getIdEsfera() != null) {
				// se os atributos forem diferentes
				if (!helper.getIdEsfera().equals(this.getIdEsfera())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdEsfera() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** TIPO DE CLIENTE
			if (helper.getIdTipoClienteResponsavel() != null) {
				// se os atributos forem diferentes
				if (!helper.getIdTipoClienteResponsavel().equals(this.getIdTipoClienteResponsavel())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdTipoClienteResponsavel() != null) {
				return false;
			}
			// VERIFICACAO DE AGRUPAMENTO ***** PERFIL DA LIGACAO DA AGUA
			if (helper.getIdPerfilLigacaoAgua() != null) {
				// se os atributos forem diferentes
				if (!helper.getIdPerfilLigacaoAgua().equals(this.getIdPerfilLigacaoAgua())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdPerfilLigacaoAgua() != null) {
				return false;
			}

			// VERIFICACAO DE AGRUPAMENTO ***** PERFIL DA LIGACAO DO ESGOTO
			if (helper.getIdPerfilLigacaoEsgoto() != null) {
				// se os atributos forem diferentes
				if (!helper.getIdPerfilLigacaoEsgoto().equals(this.getIdPerfilLigacaoEsgoto())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdPerfilLigacaoEsgoto() != null) {
				return false;
			}
		
			// VERIFICACAO DE AGRUPAMENTO ***** ANO MES REFERENCIA 
			if (helper.getAnoMesReferencia() != null) {
				// se os atributos forem diferentes
				if (!helper.getAnoMesReferencia ().equals(this.getAnoMesReferencia() )) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getAnoMesReferencia() != null) {
				return false;
			}
		
			// VERIFICACAO DE AGRUPAMENTO ***** ANO MES REFERENCIA CONTA REFATURADA
			if (helper.getAnoMesReferenciaConta() != null) {
				// se os atributos forem diferentes
				if (!helper.getAnoMesReferenciaConta().equals(this.getAnoMesReferenciaConta() )) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getAnoMesReferenciaConta() != null) {
				return false;
			}									//			 VERIFICACAO DE AGRUPAMENTO ***** TARIFA DE CONSUMO			if (helper.getConsumoTarifa() != null) {				// se os atributos forem diferentes				if (!helper.getConsumoTarifa().equals(this.getConsumoTarifa())) {					return false;				}				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso			} else if (this.getConsumoTarifa() != null) {				return false;			}			//			 VERIFICACAO DE AGRUPAMENTO ***** Existencia Hidrometro			if (helper.getHidrometro() != null) {				// se os atributos forem diferentes				if (!helper.getHidrometro().						equals(this.getHidrometro())) {					return false;				}				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso			} else if (this.getHidrometro() != null) {				return false;			}//			 VERIFICACAO DE AGRUPAMENTO ***** Documento Tipo			if (helper.getDocumentoTipo() != null) {				// se os atributos forem diferentes				if (!helper.getDocumentoTipo().						equals(this.getDocumentoTipo())) {					return false;				}				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso			} else if (this.getDocumentoTipo() != null) {				return false;			}
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
	public int hashCode() {
		String retorno =  
		this.getIdGerenciaRegional() + "sdf" +
		this.getIdUnidadeNegocio() + "sdf" +
		this.getIdLocalidade() + "sdf" +
		this.getIdElo() + "sdf" +
		this.getIdSetorComercial() + "sdf" +
		this.getCodigoSetorComercial() + "sdf" +
		this.getIdPerfilImovel() + "sdf" +
		this.getIdSituacaoLigacaoAgua() + "sdf" +
		this.getIdSituacaoLigacaoEsgoto() + "sdf" +				this.getIdCategoria() + "sdf" +				this.getIdSubCategoria() + "sfd" +				this.getIdTipoClienteResponsavel() + "sdf" +
		this.getIdPerfilLigacaoAgua() + "sdf" +
		this.getIdPerfilLigacaoEsgoto() + "sdf" +
		this.getIdEsfera() + "sdf" +				this.getConsumoTarifa() + "sdf" +				this.getHidrometro() + "sdf" +				this.getDocumentoTipo() + "sdf";
		return retorno.hashCode();
	}
	
	public ResumoReFaturamentoNovoHelper(Integer idGerenciaRegional, Integer idUnidadeNegocio, Integer idElo, Integer idLocalidade,
			Integer idSetorComercial, 
			Integer codigoSetorComercial, 
			Integer idPerfilImovel, Integer idSituacaoLigacaoAgua,
			Integer idSituacaoLigacaoEsgoto, Integer idPerfilLigacaoAgua, Integer idPerfilLigacaoEsgoto) {
		super();
		// TODO Auto-generated constructor stub
		
		this.idGerenciaRegional = idGerenciaRegional;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.idElo = idElo;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.codigoSetorComercial = codigoSetorComercial;
		this.idPerfilImovel = idPerfilImovel;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		this.idPerfilLigacaoAgua = idPerfilLigacaoAgua;
		this.idPerfilLigacaoEsgoto = idPerfilLigacaoEsgoto;
	}

	public ResumoReFaturamentoNovoHelper(Integer idGerenciaRegional, Integer idLocalidade, Integer idSetorComercial, Integer codigoSetorComercial, Integer idPerfilImovel, Integer idSituacaoLigacaoAgua, Integer idSituacaoLigacaoEsgoto, Integer idEsfera) {
		super();
		// TODO Auto-generated constructor stub
		this.idGerenciaRegional = idGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.codigoSetorComercial = codigoSetorComercial;
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

	
	public BigDecimal getVlCanceladoCreditos() {
		return vlCanceladoCreditos;
	}

	public void setVlCanceladoCreditos(BigDecimal vlCanceladoCreditos) {
		this.vlCanceladoCreditos = vlCanceladoCreditos;
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
	public BigDecimal getVlIncluidoImpostos() {
		return vlIncluidoImpostos;
	}

	public void setVlIncluidoImpostos(BigDecimal vlIncluidoImpostos) {
		this.vlIncluidoImpostos = vlIncluidoImpostos;
	}	public Integer getConsumoTarifa() {		return consumoTarifa;	}	public void setConsumoTarifa(Integer consumoTarifa) {		this.consumoTarifa = consumoTarifa;	}	public Short getHidrometro() {		return hidrometro;	}	public void setHidrometro(Short hidrometro) {		this.hidrometro = hidrometro;	}	public Integer getDocumentoTipo() {		return documentoTipo;	}	public Integer getCreditoOrigem() {		return creditoOrigem;	}	public void setCreditoOrigem(Integer creditoOrigem) {		this.creditoOrigem = creditoOrigem;	}	public Integer getLancamentoItemContabil() {		return lancamentoItemContabil;	}	public void setLancamentoItemContabil(Integer lancamentoItemContabil) {		this.lancamentoItemContabil = lancamentoItemContabil;	}	public BigDecimal getVlCanceladoOutro() {		return vlCanceladoOutro;	}	public void setVlCanceladoOutro(BigDecimal vlCanceladoOutro) {		this.vlCanceladoOutro = vlCanceladoOutro;	}	public BigDecimal getVlIncluidoOutros() {		return vlIncluidoOutros;	}	public void setVlIncluidoOutros(BigDecimal vlIncluidoOutros) {		this.vlIncluidoOutros = vlIncluidoOutros;	}	public void setDocumentoTipo(Integer documentoTipo) {		this.documentoTipo = documentoTipo;	}	public Integer getConsumoAguaRetificado() {		return consumoAguaRetificado;	}	public void setConsumoAguaRetificado(Integer consumoAguaRetificado) {		this.consumoAguaRetificado = consumoAguaRetificado;	}	public Integer getConsumoEsgotoRetificado() {		return consumoEsgotoRetificado;	}	public void setConsumoEsgotoRetificado(Integer consumoEsgotoRetificado) {		this.consumoEsgotoRetificado = consumoEsgotoRetificado;	}	public BigDecimal getVlAguaRetificado() {		return vlAguaRetificado;	}	public void setVlAguaRetificado(BigDecimal vlAguaRetificado) {		this.vlAguaRetificado = vlAguaRetificado;	}	public BigDecimal getVlCreditoRetificado() {		return vlCreditoRetificado;	}	public void setVlCreditoRetificado(BigDecimal vlCreditoRetificado) {		this.vlCreditoRetificado = vlCreditoRetificado;	}	public BigDecimal getVlDebitoRetificado() {		return vlDebitoRetificado;	}	public void setVlDebitoRetificado(BigDecimal vlDebitoRetificado) {		this.vlDebitoRetificado = vlDebitoRetificado;	}	public BigDecimal getVlEsgotoRetificado() {		return vlEsgotoRetificado;	}	public void setVlEsgotoRetificado(BigDecimal vlEsgotoRetificado) {		this.vlEsgotoRetificado = vlEsgotoRetificado;	}	public BigDecimal getVlImpostoRetificado() {		return vlImpostoRetificado;	}	public void setVlImpostoRetificado(BigDecimal vlImpostoRetificado) {		this.vlImpostoRetificado = vlImpostoRetificado;	}
}