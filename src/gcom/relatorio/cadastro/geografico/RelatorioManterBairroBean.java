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
package gcom.relatorio.cadastro.geografico;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioManterBairroBean implements RelatorioBean {

	private String codigo;

	private String nome;

	private String municipio;

	private Integer codPref;

	private String indicadorUso;

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioManterBairroBean(String codigo, String nome,
			String municipio, Integer codPref, String indicadorUso) {
		this.codigo = codigo;
		this.nome = nome;
		this.municipio = municipio;
		this.codPref = codPref;
		this.indicadorUso = indicadorUso;

	}

	/**
	 * Gets the codigo attribute of the RelatorioManterBairroBean object
	 * 
	 * @return The codigo value
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo attribute of the RelatorioManterBairroBean object
	 * 
	 * @param codigo
	 *            The new codigo value
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the codPref attribute of the RelatorioManterBairroBean object
	 * 
	 * @return The codPref value
	 */
	public Integer getCodPref() {
		return this.codPref;
	}

	/**
	 * Gets the codPrefRelatorio attribute of the RelatorioManterBairroBean
	 * object
	 * 
	 * @return The codPrefRelatorio value
	 */
	public String getCodPrefRelatorio() {
		return (this.codPref == null) ? "" : "" + this.codPref;
	}

	/**
	 * Sets the codPref attribute of the RelatorioManterBairroBean object
	 * 
	 * @param codPref
	 *            The new codPref value
	 */
	public void setCodPref(Integer codPref) {
		this.codPref = codPref;
	}

	/**
	 * Gets the municicio attribute of the RelatorioManterBairroBean object
	 * 
	 * @return The municicio value
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * Sets the municicio attribute of the RelatorioManterBairroBean object
	 * 
	 * @param municipio
	 *            The new municipio value
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * Gets the nome attribute of the RelatorioManterBairroBean object
	 * 
	 * @return The nome value
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome attribute of the RelatorioManterBairroBean object
	 * 
	 * @param nome
	 *            The new nome value
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the indicadorUso attribute of the RelatorioManterBairroBean object
	 * 
	 * @return The indicadorUso value
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * Sets the indicadorUso attribute of the RelatorioManterBairroBean object
	 * 
	 * @param indicadorUso
	 *            The new indicadorUso value
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

}
