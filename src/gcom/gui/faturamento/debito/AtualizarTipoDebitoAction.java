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
package gcom.gui.faturamento.debito;import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/** *  * Descrição da classe *  *  *  * @author Rômulo Aurélio *  * @date 15/03/2007 *  */public class AtualizarTipoDebitoAction extends GcomAction {	/**	 * 	 * Este caso de uso permite alterar e remover um Tipo de Débito	 * 	 * 	 * 	 * [UC0530] Manter Tipo de Débito	 * 	 * 	 * 	 * 	 * 	 * @author Rômulo Aurélio	 * 	 * @date 15/03/2007	 * 	 * 	 * 	 * @param actionMapping	 * 	 * @param actionForm	 * 	 * @param httpServletRequest	 * 	 * @param httpServletResponse	 * 	 * @return	 * 	 */	public ActionForward execute(ActionMapping actionMapping,	ActionForm actionForm, HttpServletRequest httpServletRequest,	HttpServletResponse httpServletResponse) {		// Seta o retorno		ActionForward retorno = actionMapping.findForward("telaSucesso");		// Obtém a instância da fachada		Fachada fachada = Fachada.getInstancia();		HttpSession sessao = httpServletRequest.getSession(false);		AtualizarTipoDebitoActionForm form = (AtualizarTipoDebitoActionForm) actionForm;		Usuario usuarioLogado = (Usuario) sessao				.getAttribute(Usuario.USUARIO_LOGADO);		String id = (String) sessao.getAttribute("idTipoDebito");		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();		filtroDebitoTipo.adicionarParametro(new ParametroSimples(				FiltroDebitoTipo.ID, id));//		filtroDebitoTipo.adicionarParametro(new ParametroSimples(//				FiltroDebitoTipo.INDICADOR_USO,//				ConstantesSistema.INDICADOR_USO_ATIVO));		Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo,				DebitoTipo.class.getName());		DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator()				.next();		String descricao = form.getDescricao();		String descricaoAbreviada = form.getDescricaoAbreviada();		String idTipoFinanciamento = form.getFinanciamentoTipo();		String indicadorGeracaoDebitoAutomatica = form				.getIndicadorGeracaoDebitoAutomatica();		String indicadorGeracaoDebitoConta = form				.getIndicadorGeracaoDebitoConta();		String indicadorUso = form.getIndicadorUso();		String idLancamentoItemContabil = form.getLancamentoItemContabil();		String valorLimeteDebito = form.getValorLimiteDebito();
		String valorSugerido = form.getValorSugerido();				String indicadorDebitoCartaoCredito = form.getIndicadorDebitoCartaoCredito();				String indicadorJurosParCliente = form.getIndicadorJurosParCliente();		fachada.atualizarDebitoTipo(debitoTipo, id, descricao,				descricaoAbreviada, idTipoFinanciamento,				indicadorGeracaoDebitoAutomatica, indicadorGeracaoDebitoConta,				idLancamentoItemContabil, valorLimeteDebito, indicadorUso,				usuarioLogado,valorSugerido, indicadorDebitoCartaoCredito, indicadorJurosParCliente);		montarPaginaSucesso(httpServletRequest, "Tipo de Débito de código "				+ id + " atualizado com sucesso.",				"Realizar outra Manutenção Tipo de Débito",				"exibirFiltrarTipoDebitoAction.do?menu=sim");		return retorno;	}}