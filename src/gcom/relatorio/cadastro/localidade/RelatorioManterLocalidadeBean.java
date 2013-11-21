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
package gcom.relatorio.cadastro.localidade;

import gcom.relatorio.RelatorioBean;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gestão Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Rafael Corrêa
 * @version 1.0
 */

public class RelatorioManterLocalidadeBean implements RelatorioBean {
	private String codigo;

	private String gerenciaRegional;

	private String unidadeNegocio;

	private String nome;

	private String indicadorUso;

	/**
	 * Construtor da classe RelatorioManterLogradouroBean
	 * 
	 * @param codigo
	 *            Descrição do parâmetro
	 * @param gerenciaRegional
	 *            Descrição do parâmetro
	 * @param nome
	 *            Descrição do parâmetro
	 * @param indicadorUso
	 *            Descrição do parâmetro
	 */

	public RelatorioManterLocalidadeBean(String codigo,
			String gerenciaRegional, String unidadeNegocio, String nome,
			String indicadorUso) {
		this.codigo = codigo;
		this.gerenciaRegional = gerenciaRegional;
		this.unidadeNegocio = unidadeNegocio;
		this.nome = nome;
		this.indicadorUso = indicadorUso;
	}

	/**
	 * Retorna o valor de codigo
	 * 
	 * @return O valor de codigo
	 */

	public String getCodigo() {
		return codigo;
	}

	/**
	 * Retorna o valor de gerenciaRegional
	 * 
	 * @return O valor de gerenciaRegional
	 */

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	/**
	 * Seta o valor de codigo
	 * 
	 * @param codigo
	 *            O novo valor de codigo
	 */

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Seta o valor de gerenciaRegional
	 * 
	 * @param gerenciaRegional
	 *            O novo valor de gerenciaRegional
	 */

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	/**
	 * Seta o valor de indicadorUso
	 * 
	 * @param indicadorUso
	 *            O novo valor de indicadorUso
	 */

	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * Seta o valor de nome
	 * 
	 * @param nome
	 *            O novo valor de nome
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o valor de nome
	 * 
	 * @return O valor de nome
	 */

	public String getNome() {
		return nome;
	}

	/**
	 * Retorna o valor de indicadorUso
	 * 
	 * @return O valor de indicadorUso
	 */

	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	/**
	 * @param unidadeNegocio
	 *            O unidadeNegocio a ser setado.
	 */
	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

}