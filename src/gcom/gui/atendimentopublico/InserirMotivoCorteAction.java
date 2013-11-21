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
package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoagua.FiltroMotivoCorte;
import gcom.atendimentopublico.ligacaoagua.MotivoCorte;
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
public class InserirMotivoCorteAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um motivo de corte
	 * 
	 * [UC0754] Inserir Motivo de Corte
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 27/03/2008
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		InserirMotivoCorteActionForm inserirMotivoCorteActionForm = (InserirMotivoCorteActionForm) actionForm;
				
		String descricao = inserirMotivoCorteActionForm.getDescricao();

		MotivoCorte motivoCorte = new MotivoCorte();
		Collection colecaoPesquisa = null;

		// Verifica se o campo Descrição está preenchido
		if (!"".equals(inserirMotivoCorteActionForm.getDescricao())) {
			motivoCorte.setDescricao(inserirMotivoCorteActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
		
		// Ultima alteração
		motivoCorte.setUltimaAlteracao(new Date());
		
		// Indicador de uso
		Short iu = 1;
		motivoCorte.setIndicadorUso(iu);

		FiltroMotivoCorte filtroMotivoCorte = new FiltroMotivoCorte();

		filtroMotivoCorte.adicionarParametro(new ParametroSimples(
				FiltroMotivoCorte.DESCRICAO, motivoCorte.getDescricao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(filtroMotivoCorte,
				MotivoCorte.class.getName());

		// Caso já haja um Motivo Corte com a descriçao passada
		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.motivo_corte_ja_cadastrado", null, motivoCorte
							.getDescricao());
		} else {
			// Caso não haja, irá inserir
			motivoCorte.setDescricao(descricao);

			Integer idMotivoCorte = (Integer) fachada.inserir(motivoCorte);

			montarPaginaSucesso(httpServletRequest,
					"Motivo de Corte de código " + idMotivoCorte
							+ " inserido com sucesso.",
					"Inserir outro Motivo de Corte",
					"exibirInserirMotivoCorteAction.do?menu=sim",
					"exibirAtualizarMotivoCorteAction.do?idRegistroAtualizacao="
							+ idMotivoCorte,
					"Atualizar Motivo de Corte Inserido");

			sessao.removeAttribute("InserirMotivoCorteActionForm");

			return retorno;
		}

	}
}