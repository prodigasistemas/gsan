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

/**
 * Classe responsável por ajudar o caso de uso [UC0275] Gerar Resumo das Ligacoes/Economias 
 *
 * @author Thiago Toscano
 * @date 20/04/2006
 */
public class ConsultarResumoFaturamentoSituacaoEspecialHelper {

	private String idGerenciaRegional;
	private String idUnidadeNegocio;
	private String idLocalidadeInicial;
	private String codigoSetorComercialInicial;
	private String codigoRotaInicial;
	private String idLocalidadeFinal;
	private String codigoSetorComercialFinal;
	private String codigoRotaFinal;
	private Integer[] situacaoTipo;
	private Integer[] situacaoMotivo;
	
	/**
	 * @return Retorna o campo codigoRotaFinal.
	 */
	public String getCodigoRotaFinal() {
		return codigoRotaFinal;
	}
	/**
	 * @param codigoRotaFinal O codigoRotaFinal a ser setado.
	 */
	public void setCodigoRotaFinal(String codigoRotaFinal) {
		this.codigoRotaFinal = codigoRotaFinal;
	}
	/**
	 * @return Retorna o campo codigoRotaInicial.
	 */
	public String getCodigoRotaInicial() {
		return codigoRotaInicial;
	}
	/**
	 * @param codigoRotaInicial O codigoRotaInicial a ser setado.
	 */
	public void setCodigoRotaInicial(String codigoRotaInicial) {
		this.codigoRotaInicial = codigoRotaInicial;
	}
	/**
	 * @return Retorna o campo codigoSetorComercialFinal.
	 */
	public String getCodigoSetorComercialFinal() {
		return codigoSetorComercialFinal;
	}
	/**
	 * @param codigoSetorComercialFinal O codigoSetorComercialFinal a ser setado.
	 */
	public void setCodigoSetorComercialFinal(String codigoSetorComercialFinal) {
		this.codigoSetorComercialFinal = codigoSetorComercialFinal;
	}
	/**
	 * @return Retorna o campo codigoSetorComercialInicial.
	 */
	public String getCodigoSetorComercialInicial() {
		return codigoSetorComercialInicial;
	}
	/**
	 * @param codigoSetorComercialInicial O codigoSetorComercialInicial a ser setado.
	 */
	public void setCodigoSetorComercialInicial(String codigoSetorComercialInicial) {
		this.codigoSetorComercialInicial = codigoSetorComercialInicial;
	}
	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	/**
	 * @return Retorna o campo idLocalidadeFinal.
	 */
	public String getIdLocalidadeFinal() {
		return idLocalidadeFinal;
	}
	/**
	 * @param idLocalidadeFinal O idLocalidadeFinal a ser setado.
	 */
	public void setIdLocalidadeFinal(String idLocalidadeFinal) {
		this.idLocalidadeFinal = idLocalidadeFinal;
	}
	/**
	 * @return Retorna o campo idLocalidadeInicial.
	 */
	public String getIdLocalidadeInicial() {
		return idLocalidadeInicial;
	}
	/**
	 * @param idLocalidadeInicial O idLocalidadeInicial a ser setado.
	 */
	public void setIdLocalidadeInicial(String idLocalidadeInicial) {
		this.idLocalidadeInicial = idLocalidadeInicial;
	}
	/**
	 * @return Retorna o campo idUnidadeNegocio.
	 */
	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}
	/**
	 * @param idUnidadeNegocio O idUnidadeNegocio a ser setado.
	 */
	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}
	/**
	 * @return Retorna o campo situacaoMotivo.
	 */
	public Integer[] getSituacaoMotivo() {
		return situacaoMotivo;
	}
	/**
	 * @param situacaoMotivo O situacaoMotivo a ser setado.
	 */
	public void setSituacaoMotivo(Integer[] situacaoMotivo) {
		this.situacaoMotivo = situacaoMotivo;
	}
	/**
	 * @return Retorna o campo situacaoTipo.
	 */
	public Integer[] getSituacaoTipo() {
		return situacaoTipo;
	}
	/**
	 * @param situacaoTipo O situacaoTipo a ser setado.
	 */
	public void setSituacaoTipo(Integer[] situacaoTipo) {
		this.situacaoTipo = situacaoTipo;
	}

}