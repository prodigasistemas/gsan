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
package gcom.gui.cadastro;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.EmpresaCobrancaFaixa;
import gcom.cadastro.empresa.EmpresaContratoCobranca;
import gcom.cadastro.empresa.FiltroEmpresaCobrancaFaixa;
import gcom.cadastro.empresa.FiltroEmpresaContratoCobranca;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Arthur Carvalho
 * @date 15/05/2008
 */

public class RemoverEmpresaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;

		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}

		if (ids != null && ids.length != 0) {
			// remove todas as unidade executoras informadas
			for (int i = 0; i < ids.length; i++) {
				// atribui a variável "id" o código da unidade executora para
				// remoção
				int id = Integer.parseInt(ids[i]);
				
				FiltroEmpresaContratoCobranca filtroEmpresaCobranca = new FiltroEmpresaContratoCobranca();

				filtroEmpresaCobranca.adicionarParametro(new ParametroSimples(
						FiltroEmpresaContratoCobranca.EMPRESA_ID, id));

				Collection colecaoEmpresaCobranca = fachada.pesquisar(
						filtroEmpresaCobranca, EmpresaContratoCobranca.class.getName());

				if (colecaoEmpresaCobranca != null
						&& !colecaoEmpresaCobranca.isEmpty()) {

					EmpresaContratoCobranca empresaCobranca = (EmpresaContratoCobranca) colecaoEmpresaCobranca
							.iterator().next();

					FiltroEmpresaCobrancaFaixa filtroEmpresaCobrancaFaixa = new FiltroEmpresaCobrancaFaixa();

					filtroEmpresaCobrancaFaixa.adicionarParametro(new ParametroSimples(
							FiltroEmpresaCobrancaFaixa.EMPRESA_CONTRATO_COBRANCA_ID, empresaCobranca.getId()));

					Collection colecaoEmpresaCobrancaFaixa = fachada.pesquisar(
							filtroEmpresaCobrancaFaixa, EmpresaCobrancaFaixa.class.getName());

					if (colecaoEmpresaCobrancaFaixa != null
							&& !colecaoEmpresaCobrancaFaixa.isEmpty()) {

						Iterator iterator = colecaoEmpresaCobrancaFaixa.iterator();
						
						while (iterator.hasNext()) {
							EmpresaCobrancaFaixa empresaCobrancaFaixa = (EmpresaCobrancaFaixa) iterator.next();
							fachada.remover(empresaCobrancaFaixa);
						}

					}
					
					fachada.remover(empresaCobranca);

				}

			}

		}

		fachada.remover(ids, Empresa.class.getName(), null, null);

		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest, ids.length
					+ " Empresa(s) removida(s) com sucesso.",
					"Realizar outra Manutenção de Empresa",
					"exibirFiltrarEmpresaAction.do?menu=sim");
		}

		return retorno;

	}
}
