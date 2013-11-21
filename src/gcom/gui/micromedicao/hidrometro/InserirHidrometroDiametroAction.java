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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroDiametro;
import gcom.micromedicao.hidrometro.HidrometroDiametro;
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
 * @date 16/05/2008
 */
public class InserirHidrometroDiametroAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Diametro do Hidrometro
	 * 
	 * [UC0791] Inserir Diametro do Hidrometro
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 16/05/2008
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

		InserirHidrometroDiametroActionForm inserirHidrometroDiametroActionForm = (InserirHidrometroDiametroActionForm) actionForm;

		// Mudar quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirHidrometroDiametroActionForm.getDescricao();
		String descricaoAbreviada = inserirHidrometroDiametroActionForm
				.getDescricaoAbreviada();
		
		HidrometroDiametro hidrometroDiametro = new HidrometroDiametro();
		Collection colecaoPesquisa = null;

		// Descrição
		if (!"".equals(inserirHidrometroDiametroActionForm.getDescricao())) {
			hidrometroDiametro.setDescricao(inserirHidrometroDiametroActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
		
		// Descrição Abreviada
		if (!"".equals(inserirHidrometroDiametroActionForm.getDescricaoAbreviada())) {
			hidrometroDiametro.setDescricaoAbreviada(inserirHidrometroDiametroActionForm
					.getDescricaoAbreviada());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição Abreviada");
		}

		// Número de Ordem
		if (!"".equals(inserirHidrometroDiametroActionForm.getNumeroOrdem())) {
			hidrometroDiametro.setNumeroOrdem(new Short (inserirHidrometroDiametroActionForm
					.getNumeroOrdem()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Número de ordem");
		}

		
		
		hidrometroDiametro.setUltimaAlteracao(new Date());

		Short iu = 1;
		hidrometroDiametro.setIndicadorUso(iu);

		FiltroHidrometroDiametro filtroHidrometroDiametro = new FiltroHidrometroDiametro();

		filtroHidrometroDiametro.adicionarParametro(new ParametroSimples(
				FiltroHidrometroDiametro.DESCRICAO, hidrometroDiametro.getDescricao()));
		
		colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroHidrometroDiametro, HidrometroDiametro.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// Caso já haja um Diametro do Hidrometro com os dados passados 
			throw new ActionServletException(
					"atencao.diametrohidrometro_ja_cadastrada", null,
					hidrometroDiametro.getDescricao());
		} else {
			hidrometroDiametro.setDescricao(descricao);
			hidrometroDiametro.setDescricaoAbreviada(descricaoAbreviada);

			Integer idHidrometroDiametro = (Integer) fachada
					.inserir(hidrometroDiametro);

			montarPaginaSucesso(httpServletRequest,
					"Diâmetro do Hidrômetro " + descricao
							+ " inserido com sucesso.",
					"Inserir outro Diâmetro do Hidrômetro",
					"exibirInserirHidrometroDiametroAction.do?menu=sim",
					"exibirAtualizarHidrometroDiametroAction.do?idRegistroAtualizacao="
							+ idHidrometroDiametro,
					"Atualizar Diâmetro do Hidrômetro Inserida");

			sessao.removeAttribute("InserirHidrometroDiametroActionForm");

			return retorno;
		}

	}
}