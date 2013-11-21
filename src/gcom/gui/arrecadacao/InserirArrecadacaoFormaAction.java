/**
 * 
 */
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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Vinícius Medeiros
 * @date 27/03/2008
 */
public class InserirArrecadacaoFormaAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Forma de Arrecadacao
	 * 
	 * [UC0757] Inserir Forma de Arrecadacao
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 08/04/2008
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirArrecadacaoFormaActionForm inserirArrecadacaoFormaActionForm = (InserirArrecadacaoFormaActionForm) actionForm;

		// Mudar isso quando houver um esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();


		String descricao = inserirArrecadacaoFormaActionForm.getDescricao();
		
		ArrecadacaoForma arrecadacaoForma = new ArrecadacaoForma();
		Collection colecaoPesquisa = null;

		// Verifica se a Descrição foi preenchida.
		if (!"".equals(inserirArrecadacaoFormaActionForm.getDescricao())&& !"".equals(
				inserirArrecadacaoFormaActionForm.getCodigoArrecadacaoForma())) {
			
			arrecadacaoForma.setDescricao(inserirArrecadacaoFormaActionForm.getDescricao());
			arrecadacaoForma.setCodigoArrecadacaoForma(inserirArrecadacaoFormaActionForm.getCodigoArrecadacaoForma());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descricao");
		}
		
		// Seta a Ultima alteração
		arrecadacaoForma.setUltimaAlteracao(new Date());

		FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();

		filtroArrecadacaoForma.adicionarParametro(new ParametroSimples(
				FiltroArrecadacaoForma.DESCRICAO, arrecadacaoForma.getDescricao()));
	
		colecaoPesquisa = (Collection) fachada.pesquisar(filtroArrecadacaoForma,
				ArrecadacaoForma.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// Caso já haja uma Forma de Arrecadação com a descrição passada
			throw new ActionServletException(
					"atencao.arrecadacao_forma_ja_cadastrada", null, arrecadacaoForma
							.getDescricao());
		} else {
			arrecadacaoForma.setDescricao(descricao);

			Integer idArrecadacaoForma = (Integer) fachada.inserir(arrecadacaoForma);
			
			montarPaginaSucesso(httpServletRequest,
					"Forma de Arrecadação de descrição " + descricao
							+ " inserido com sucesso.",
					"Inserir outra Forma de Arrecadação",
					"exibirInserirArrecadacaoFormaAction.do?menu=sim",
					"exibirAtualizarArrecadacaoFormaAction.do?idRegistroAtualizacao="
							+ idArrecadacaoForma,
					"Atualizar Forma de Arrecadação Inserido");

			sessao.removeAttribute("InserirArrecadacaoFormaActionForm");

			return retorno;
		}

	}
}