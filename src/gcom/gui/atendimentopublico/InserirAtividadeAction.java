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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
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
 * [UC0773] Inserir Atividade
 * 
 * @author Vinícius Medeiros
 * @date 17/04/2008
 */
public class InserirAtividadeAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Atividade
	 * 
	 * [UC0773] Inserir Atividade
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 28/04/2008
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

		InserirAtividadeActionForm inserirAtividadeActionForm = (InserirAtividadeActionForm) actionForm;

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String descricao = inserirAtividadeActionForm.getDescricao();
		String descricaoAbreviada = inserirAtividadeActionForm.getDescricaoAbreviada();
		String indicadorAtividadeUnica = inserirAtividadeActionForm.getIndicadorAtividadeUnica();
		
		Atividade atividade = new Atividade();
		Collection colecaoPesquisa = null;

		// Verifica se a Descrição foi passada
		if (!"".equals(inserirAtividadeActionForm.getDescricao())) {
			atividade.setDescricao(inserirAtividadeActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
		
		// Verifica se o Indicador de Atividade Única foi passado
        if (indicadorAtividadeUnica == null 
        		|| indicadorAtividadeUnica.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de atividade única");
        }else{  
        	atividade.setIndicadorAtividadeUnica(new Short(indicadorAtividadeUnica));
        }
		
		atividade.setDescricao(descricao);
		atividade.setDescricaoAbreviada(descricaoAbreviada);
		atividade.setUltimaAlteracao(new Date());
		atividade.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		FiltroAtividade filtroAtividade = new FiltroAtividade();

		filtroAtividade.adicionarParametro(
			new ParametroSimples(
				FiltroAtividade.DESCRICAO, 
				atividade.getDescricao()));
		
		filtroAtividade.adicionarParametro(
			new ParametroSimples(
				FiltroAtividade.DESCRICAOABREVIADA, 
				atividade.getDescricaoAbreviada()));
		
		colecaoPesquisa = 
			(Collection) this.getFachada().pesquisar(
				filtroAtividade, Atividade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			
			// Caso já haja uma Atividade com a Descrição e a Descrição Abreviada passada
			throw new ActionServletException("atencao.atividade_ja_cadastrada", 
				null,atividade.getDescricao());
		} else {

			Integer idAtividade = (Integer) this.getFachada().inserir(atividade);

			montarPaginaSucesso(httpServletRequest,
				"Atividade " + descricao+ " inserido com sucesso.",
				"Inserir outra Atividade",
				"exibirInserirAtividadeAction.do?menu=sim",
				"exibirAtualizarAtividadeAction.do?idRegistroAtualizacao="+ idAtividade,
				"Atualizar Atividade Inserida");

			sessao.removeAttribute("InserirAtividadeActionForm");

			return retorno;
		}

	}
}