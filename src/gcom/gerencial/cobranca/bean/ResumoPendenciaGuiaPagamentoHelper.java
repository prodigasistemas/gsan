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
package gcom.gerencial.cobranca.bean;

import java.math.BigDecimal;

/**
 * Este caso de uso gera o resumo da pendência
 *
 * [UC0335] Gerar Resumo da Pendência
 *
 * @author Roberta Costa
 * @date 19/05/2006
 */
public class ResumoPendenciaGuiaPagamentoHelper {
	
	private Integer idGuiaPagamento;
	private Integer idGerenciaRegional;
	private Integer idLocalidade;
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	private Integer idRota;
	private Integer idQuadra;
	private Integer numeroQuadra;
	private Integer idPerfilImovel;
	private Integer idSituacaoLigacaoAgua;
	private Integer idSituacaoLigacaoEsgoto;
	private Integer idCategoria;
	private Integer idEsferaPoder;
	private Integer indicadorExistenciaHidrometro;
	private Integer idImovel;
	private Integer idDocumentoTipo;
	private Integer idFinanciamentoTipo;
	private Short indicadorVencido;

	private int quantidadeLigacoes = 1;
	private int quantidadeDocumentos = 1;
	private BigDecimal valorPendente = new BigDecimal("0.00");

	public boolean equal(Object obj) {
		if (obj instanceof ResumoPendenciaGuiaPagamentoHelper) {
			ResumoPendenciaGuiaPagamentoHelper objeto = (ResumoPendenciaGuiaPagamentoHelper) obj;

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

			if (objeto.getCodigoSetorComercial() != null) {
				// se os atributos forem diferentes
				if (!objeto.getCodigoSetorComercial().equals(this.getCodigoSetorComercial())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getCodigoSetorComercial() != null) {
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

			if (objeto.getIdEsferaPoder() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdEsferaPoder().equals(this.getIdEsferaPoder())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdEsferaPoder() != null) {
				return false;
			}
			
			if (objeto.getIndicadorExistenciaHidrometro() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIndicadorExistenciaHidrometro().equals(this.getIndicadorExistenciaHidrometro())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIndicadorExistenciaHidrometro() != null) {
				return false;
			}

			if (objeto.getIdImovel() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdImovel().equals(this.getIdImovel())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdImovel() != null) {
				return false;
			}

			if (objeto.getIdDocumentoTipo() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdDocumentoTipo().equals(this.getIdDocumentoTipo())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdDocumentoTipo() != null) {
				return false;
			}

			if (objeto.getIdFinanciamentoTipo() != null) {
				// se os atributos forem diferentes
				if (!objeto.getIdFinanciamentoTipo().equals(this.getIdFinanciamentoTipo())) {
					return false;
				}
				// se o atributo no obj for  null e nesse objeto for diferente de null, entao retorna falso
			} else if (this.getIdFinanciamentoTipo() != null) {
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
		
		this.getIdGuiaPagamento() + "sdf" +
		this.getIdGerenciaRegional() + "sdf" +
		this.getIdLocalidade() + "sdf" +
		this.getIdSetorComercial() + "sdf" +
		this.getCodigoSetorComercial() + "sdf" +
		this.getIdRota() + "sdf" +
		this.getIdQuadra() + "sdf" +
		this.getNumeroQuadra() + "sdf" +
		this.getIdPerfilImovel() + "sdf" +
		this.getIdSituacaoLigacaoAgua() + "sdf" +
		this.getIdSituacaoLigacaoEsgoto() + "sdf" +
		this.getIdCategoria() + "sdf" +
		this.getIdEsferaPoder() + "sdf" +
		this.getIndicadorExistenciaHidrometro() + "sdf" +
		this.getIdImovel() + "sdf" +
		this.getIdDocumentoTipo() + "sdf" +
		this.getIdFinanciamentoTipo() + "sdf";

		return retorno.hashCode();
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
	 * @return Retorna o campo idConta.
	 */
	public Integer getIdGuiaPagamento() {
		return idGuiaPagamento;
	}

	/**
	 * @param idImovel O idImovel a ser setado.
	 */
	public void setIdGuiaPagamento(Integer idGuiaPagamento) {
		this.idGuiaPagamento = idGuiaPagamento;
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

	/**
	 * @return Retorna o campo idEsferaPoder.
	 */
	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}

	/**
	 * @param idEsferaPoder O idEsferaPoder a ser setado.
	 */
	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	/**
	 * @return Retorna o campo indicadorExistenciaHidrometro.
	 */
	public Integer getIndicadorExistenciaHidrometro() {
		return indicadorExistenciaHidrometro;
	}

	/**
	 * @param indicadorExistenciaHidrometro O indicadorExistenciaHidrometro a ser setado.
	 */
	public void setIndicadorExistenciaHidrometro(
			Integer indicadorExistenciaHidrometro) {
		this.indicadorExistenciaHidrometro = indicadorExistenciaHidrometro;
	}

	/**
	 * @return Retorna o campo quantidadeDocumentos.
	 */
	public int getQuantidadeDocumentos() {
		return quantidadeDocumentos;
	}

	/**
	 * @param quantidadeDocumentos O quantidadeDocumentos a ser setado.
	 */
	public void setQuantidadeDocumentos(int quantidadeDocumentos) {
		this.quantidadeDocumentos = quantidadeDocumentos;
	}

	/**
	 * @return Retorna o campo quantidadeLigacoes.
	 */
	public int getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	/**
	 * @param quantidadeLigacoes O quantidadeLigacoes a ser setado.
	 */
	public void setQuantidadeLigacoes(int quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	/**
	 * @return Retorna o campo valorPendente.
	 */
	public BigDecimal getValorPendente() {
		return valorPendente;
	}

	/**
	 * @param valorPendente O valorPendente a ser setado.
	 */
	public void setValorPendente(BigDecimal valorPendente) {
		this.valorPendente = valorPendente;
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
	 * @return Retorna o campo idDocumentoTipo.
	 */
	public Integer getIdDocumentoTipo() {
		return idDocumentoTipo;
	}

	/**
	 * @param idDocumentoTipo O idDocumentoTipo a ser setado.
	 */
	public void setIdDocumentoTipo(Integer idDocumentoTipo) {
		this.idDocumentoTipo = idDocumentoTipo;
	}

	/**
	 * @return Retorna o campo idFinanciamentoTipo.
	 */
	public Integer getIdFinanciamentoTipo() {
		return idFinanciamentoTipo;
	}

	/**
	 * @param idFinanciamentoTipo O idFinanciamentoTipo a ser setado.
	 */
	public void setIdFinanciamentoTipo(Integer idFinanciamentoTipo) {
		this.idFinanciamentoTipo = idFinanciamentoTipo;
	}
	
	/**
	 * @return Retorna o campo indicadorVencido.
	 */
	public Short getIndicadorVencido() {
		return indicadorVencido;
	}

	/**
	 * @param indicadorVencido O indicadorVencido a ser setado.
	 */
	public void setIndicadorVencido(Short indicadorVencido) {
		this.indicadorVencido = indicadorVencido;
	}

	/**
	 * Construtor de ResumoPendenciaGuiaPagamentoHelper 
	 * 
	 * @param idGuiaPagamento
	 * @param idGerenciaRegional
	 * @param idLocalidade
	 * @param idSetorComercial
	 * @param codigoSetorComercial
	 * @param idRota
	 * @param idQuadra
	 * @param numeroQuadra
	 * @param idPerfilImovel
	 * @param idSituacaoLigacaoAgua
	 * @param idSituacaoLigacaoEsgoto
	 * @param idCategoria
	 * @param idEsferaPoder
	 * @param indicadorExistenciaHidrometro
	 * @param idImovel
	 * @param idDocumentoTipo
	 * @param idFinanciamentoTipo
	 * @param quantidadeLigacoes
	 * @param quantidadeDocumentos
	 * @param valorPendente
	 */
	public ResumoPendenciaGuiaPagamentoHelper(Integer idImovel, Integer idGuiaPagamento, Integer idGerenciaRegional, 
			Integer idLocalidade, Integer idSetorComercial, Integer codigoSetorComercial, Integer idRota, 
			Integer idQuadra, Integer numeroQuadra, Integer idPerfilImovel, Integer idSituacaoLigacaoAgua, 
			Integer idSituacaoLigacaoEsgoto, Integer idEsferaPoder, Integer indicadorExistenciaHidrometro, 
			Integer idDocumentoTipo, Integer idFinanciamentoTipo ) {
		super();
		// TODO Auto-generated constructor stub
		this.idImovel = idImovel;
		this.idGuiaPagamento = idGuiaPagamento;
		this.idGerenciaRegional = idGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.idSetorComercial = idSetorComercial;
		this.codigoSetorComercial = codigoSetorComercial;
		this.idRota = idRota;
		this.idQuadra = idQuadra;
		this.numeroQuadra = numeroQuadra;
		this.idPerfilImovel = idPerfilImovel;
		this.idSituacaoLigacaoAgua = idSituacaoLigacaoAgua;
		this.idSituacaoLigacaoEsgoto = idSituacaoLigacaoEsgoto;
		if (idEsferaPoder != null && idEsferaPoder.intValue() != 0){
			this.idEsferaPoder = idEsferaPoder;
		}	
		this.indicadorExistenciaHidrometro = indicadorExistenciaHidrometro;
		this.idDocumentoTipo = idDocumentoTipo;
		this.idFinanciamentoTipo = idFinanciamentoTipo;
	}
}