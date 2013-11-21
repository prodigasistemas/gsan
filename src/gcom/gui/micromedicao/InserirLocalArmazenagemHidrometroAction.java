/* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.micromedicao;


import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirLocalArmazenagemHidrometroAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um local de armazenagem do hidrômetro
	 * 
	 * [UC0834] Inserir Inserir Local de Armazenagem do Hidrometro
	 * 
	 * @author Arthur Carvalho
	 * @date 06/08/2008
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirLocalArmazenagemHidrometroActionForm inserirLocalArmazenagemHidrometroActionForm = (InserirLocalArmazenagemHidrometroActionForm) actionForm;

		String descricao = inserirLocalArmazenagemHidrometroActionForm.getDescricao();
		String descricaoAbreviada = inserirLocalArmazenagemHidrometroActionForm.getDescricaoAbreviada();
		Short indicadorOficina = inserirLocalArmazenagemHidrometroActionForm.getIndicadorOficina();
		
		Collection colecaoPesquisa = null;

		// Descrição
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		//Descrição Abreviada
		if (descricaoAbreviada == null || "".equals(descricaoAbreviada)) {
			throw new ActionServletException("atencao.required", null,"Descrição Abreviada");
		}
		
		//Indicador de Oficina
		if (indicadorOficina == null || "".equals(indicadorOficina)) {
			throw new ActionServletException("atencao.required", null,"Indicador de Oficina ");
		}
		
		FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem= new FiltroHidrometroLocalArmazenagem();
			filtroHidrometroLocalArmazenagem.adicionarParametro(
					new ParametroSimples(FiltroHidrometroLocalArmazenagem.DESCRICAO,descricao));
		
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroHidrometroLocalArmazenagem, HidrometroLocalArmazenagem.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.hidrometro_ja_existente", null,descricao);
		} else {
		

			HidrometroLocalArmazenagem hidrometroLocalArmazenagem = new HidrometroLocalArmazenagem();
			hidrometroLocalArmazenagem.setDescricao(descricao);
			hidrometroLocalArmazenagem.setDescricaoAbreviada(descricaoAbreviada);
			hidrometroLocalArmazenagem.setIndicadorOficina(indicadorOficina);
			hidrometroLocalArmazenagem.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			hidrometroLocalArmazenagem.setUltimaAlteracao(new Date());
			

			Integer idHidrometroLocalArmazenagem = (Integer) this.getFachada().inserir(hidrometroLocalArmazenagem);

			montarPaginaSucesso(httpServletRequest,
					"Local de Armazenagem do Hidrômetro " + descricao
							+ " inserido com sucesso.",
					"Inserir outro Local de Armazenagem do Hidrômetro",
					"exibirInserirLocalArmazenagemHidrometroAction.do?menu=sim",
					"exibirAtualizarLocalArmazenagemHidrometroAction.do?idRegistroAtualizacao="
							+ idHidrometroLocalArmazenagem,
					"Atualizar Local de Armazenagem do Hidrômetro Inserida");

			this.getSessao(httpServletRequest).removeAttribute("InserirLocalArmazenagemHidrometroActionForm");

			return retorno;
		}

	}
}		
