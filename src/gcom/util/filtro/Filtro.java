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
package gcom.util.filtro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * Representa o filtro básico do sistema
 * 
 * @author rodrigo
 */
public abstract class Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Coleção dos parâmetros que fazem parte do filtro
	 */
	protected Collection parametros = new ArrayList();

	/**
	 * Description of the Field
	 */
	protected Collection colecaoCaminhosParaCarregamentoEntidades = new TreeSet();

	/**
	 * Description of the Field
	 */
	protected boolean consultaSemLimites = false;
	
	protected boolean initializeLazy = false;

	/**
	 * Ordenação do filtro
	 */
	protected List<String> camposOrderBy = new ArrayList();

	protected String campoOrderBy;

	protected String campoDistinct;

	/**
	 * Retorna o valor de parametros
	 * 
	 * @return O valor de parametros
	 */
	public Collection getParametros() {
		return parametros;
	}

	/**
	 * Método para adicionar parâmetros no filtro
	 * 
	 * @param filtroParametro
	 *            Descrição do parâmetro
	 */
	public void adicionarParametro(FiltroParametro filtroParametro) {
		this.parametros.add(filtroParametro);
	}

	/**
	 * Método para adicionar um campo de coleção à lista das coleções que serão
	 * carregadas pelo sistema
	 * 
	 * @param campoColecao
	 *            nome do campo
	 */
	public void adicionarCaminhoParaCarregamentoEntidade(String campoColecao) {
		this.colecaoCaminhosParaCarregamentoEntidades.add(campoColecao);
	}

	/**
	 * Seta o valor de parametros
	 * 
	 * @param parametros
	 *            O novo valor de parametros
	 */
	public void setParametros(Collection parametros) {
		this.parametros = parametros;
	}

	/**
	 * Retorna o valor de campoOrderBy
	 * 
	 * @return O valor de campoOrderBy
	 */
	public List<String> getCamposOrderBy() {
		ArrayList<String> list = new ArrayList();
		list.addAll(camposOrderBy);
		if (campoOrderBy != null && !campoOrderBy.trim().equals("")) {
			list.add(campoOrderBy);
		}
		return list;
	}

	/**
	 * Seta o valor de campoOrderBy
	 * 
	 * @param campoOrderBy
	 *            O novo valor de campoOrderBy
	 */
	public void setCampoOrderBy(String... campoOrderBy) {
		camposOrderBy.addAll(Arrays.asList(campoOrderBy));
	}

	public void setCampoOrderBy(String campoOrderBy) {
		camposOrderBy.add(campoOrderBy);
	}

	public void limparCamposOrderBy() {
		campoOrderBy = null;
		camposOrderBy.clear();

	}
	
	public void limparColecaoCaminhosParaCarregamentoEntidades() {
		colecaoCaminhosParaCarregamentoEntidades.clear();

	}

	/**
	 * @return Returns the colecaoCaminhosParaCarregamentoEntidades.
	 */
	public Collection getColecaoCaminhosParaCarregamentoEntidades() {
		Collection colecao = new TreeSet();
		colecao.addAll(colecaoCaminhosParaCarregamentoEntidades);
		return colecao;
	}
	
	public void setColecaoCaminhosParaCarregamentoEntidades(Collection colecaoCaminhosParaCarregamentoEntidades) {
		this.colecaoCaminhosParaCarregamentoEntidades.addAll(colecaoCaminhosParaCarregamentoEntidades);
	}

	/**
	 * < <Descrição do método>>
	 */
	public void limparListaParametros() {
		parametros.clear();
	}

	/**
	 * Retorna o valor de consultaSemLimites
	 * 
	 * @return O valor de consultaSemLimites
	 */
	public boolean isConsultaSemLimites() {
		return consultaSemLimites;
	}

	/**
	 * Seta o valor de consultaSemLimites
	 * 
	 * @param consultaSemLimites
	 *            O novo valor de consultaSemLimites
	 */
	public void setConsultaSemLimites(boolean consultaSemLimites) {
		this.consultaSemLimites = consultaSemLimites;
	}

	public String getCampoDistinct() {
		return campoDistinct;
	}

	public void setCampoDistinct(String campoDistinct) {
		this.campoDistinct = campoDistinct;
	}

	public boolean isInitializeLazy() {
		return initializeLazy;
	}

	public void setInitializeLazy(boolean initializeLazy) {
		this.initializeLazy = initializeLazy;
	}

}
