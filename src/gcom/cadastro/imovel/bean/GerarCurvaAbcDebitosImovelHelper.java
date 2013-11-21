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
package gcom.cadastro.imovel.bean;

/**
 * Débitos de um imóvel
 * @author Ivan Sérgio
 * @since 11/09/2007
 */
public class GerarCurvaAbcDebitosImovelHelper {
	
	private String idImovel; 
	private String descricaoCategoria;
	private String descricaoSubCategoria;
	private String idGerenciaRegional;
	private String nomeGerenciaRegional;
	private String gerenciaRegional;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String codigoSetorComercial;
	private String descricaoSetorComercial;
	
	public GerarCurvaAbcDebitosImovelHelper(
			String idimovel,
			String idGerenciaRegional,
			String gerenciaRegional,
			String nomeGerenciaRegional,
			String idLocalidade,
			String descricaoLocalidade,
			String codigoSetorComercial, 
			String descricaoSetorComercial,			
			String descricaoCategoria, 
			String descricaoSubCategoria) {
		
		// TODO Auto-generated constructor stub
		this.idImovel = idimovel;
		this.idGerenciaRegional = idGerenciaRegional;
		this.gerenciaRegional = gerenciaRegional;
		this.nomeGerenciaRegional = nomeGerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.descricaoLocalidade = descricaoLocalidade;
		this.codigoSetorComercial = codigoSetorComercial;
		this.descricaoSetorComercial = descricaoSetorComercial;
		this.descricaoCategoria = descricaoCategoria;
		this.descricaoSubCategoria = descricaoSubCategoria;
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
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}
	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	/**
	 * @return Retorna o campo nomeGerenciaRegional.
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}
	/**
	 * @param nomeGerenciaRegional O nomeGerenciaRegional a ser setado.
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}
	/**
	 * @return Retorna o campo descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}
	/**
	 * @param descricaoLocalidade O descricaoLocalidade a ser setado.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}
	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	/**
	 * @return Retorna o campo gerenciaRegional.
	 */
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	/**
	 * @param gerenciaRegional O gerenciaRegional a ser setado.
	 */
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}
	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}
	public String getDescricaoSubCategoria() {
		return descricaoSubCategoria;
	}
	public void setDescricaoSubCategoria(String descricaoSubCategoria) {
		this.descricaoSubCategoria = descricaoSubCategoria;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
}
