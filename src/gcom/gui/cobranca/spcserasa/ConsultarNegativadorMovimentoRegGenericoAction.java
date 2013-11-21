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
 * Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;


import gcom.cobranca.Negativador;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorMovimentoReg;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Chama o o caso de uso correspondente ao do negativador passado.
 * [UC0683]- Consultar Dados do Registro SPC ou
 * [UC0684]- Consultar Dados do Registro SERASA
 * 
 * @author Yara Taciane de Souza
 * @date 23/01/2008
 */
public class ConsultarNegativadorMovimentoRegGenericoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		NegativadorMovimentoReg negativadorMovimentoReg = null;

		Fachada fachada = Fachada.getInstancia();
		HttpSession sessao = httpServletRequest.getSession(false);

		String confirmado = httpServletRequest.getParameter("confirmado");

		String idNegativadorMovimentoReg = null;

		if (httpServletRequest.getParameter("reload") == null
				|| httpServletRequest.getParameter("reload").equalsIgnoreCase(
						"") && (confirmado == null || confirmado.equals(""))) {

			if (httpServletRequest.getParameter("idRegistro") != null) {
				idNegativadorMovimentoReg = httpServletRequest
						.getParameter("idRegistro");
				httpServletRequest.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistro", idNegativadorMovimentoReg);
			} else if (httpServletRequest.getParameter("idRegistro") == null) {
				idNegativadorMovimentoReg = (String) sessao
						.getAttribute("idRegistro");
				httpServletRequest.setAttribute("voltar", "filtrar");
			} else if (httpServletRequest.getParameter("idRegistro") != null) {
				idNegativadorMovimentoReg = httpServletRequest
						.getParameter("idRegistro");
				httpServletRequest.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistro", idNegativadorMovimentoReg);
			}
		} else {
			idNegativadorMovimentoReg = (String) sessao
					.getAttribute("idRegistro");
		}
	

		if ((idNegativadorMovimentoReg != null && !idNegativadorMovimentoReg
				.equals(""))
				&& (httpServletRequest.getParameter("desfazer") == null)
				&& (httpServletRequest.getParameter("reload") == null || httpServletRequest
						.getParameter("reload").equalsIgnoreCase(""))) {


			FiltroNegativadorMovimentoReg filtroNegativadorMovimentoReg = new FiltroNegativadorMovimentoReg();
			filtroNegativadorMovimentoReg
					.adicionarParametro(new ParametroSimples(
							FiltroNegativadorMovimentoReg.ID,
							idNegativadorMovimentoReg));
			
			filtroNegativadorMovimentoReg
			.adicionarCaminhoParaCarregamentoEntidade("negativadorRegistroTipo");		
			filtroNegativadorMovimentoReg.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimento.negativador.cliente");
			filtroNegativadorMovimentoReg.adicionarCaminhoParaCarregamentoEntidade("imovel");
		
			Collection<NegativadorMovimentoReg> collectionNegativadorMovimentoReg = fachada
					.pesquisar(filtroNegativadorMovimentoReg,
							NegativadorMovimentoReg.class.getName());
			
		   //------------------------------------------------------------------------------------------------------------	

			if (collectionNegativadorMovimentoReg != null) {
				
				negativadorMovimentoReg = (NegativadorMovimentoReg) Util
						.retonarObjetoDeColecao(collectionNegativadorMovimentoReg);

				if (negativadorMovimentoReg.getNegativadorMovimento() != null
						&& negativadorMovimentoReg.getNegativadorMovimento()
								.getNegativador() != null) {

					Negativador negativador = negativadorMovimentoReg
							.getNegativadorMovimento().getNegativador();

					if (negativador.getId().equals(Negativador.NEGATIVADOR_SPC)) {
						retorno = actionMapping
								.findForward("consultarDadosRegistroSPC");
					} else if (negativador.getId().equals(
							Negativador.NEGATIVADOR_SERASA)) {
						retorno = actionMapping
								.findForward("consultarDadosRegistroSERASA");
					} else {
						throw new ActionServletException(
						"atencao.negativador.nao.selecionado");
					}
					
				sessao.setAttribute("negativadorMovimentoReg", negativadorMovimentoReg);
					

				} else {
					throw new ActionServletException("atencao.pesquisa.nenhumresultado");
				}
				

			}

		}

		return retorno;
	}
}
