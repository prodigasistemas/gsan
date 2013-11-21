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
* Ivan Sérgio Virginio da Silva Júnior
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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroTipoServico;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0734] Encerrar Ordem de Servico Vencida
 * 
 * @author Ivan Sérgio
 * 
 * @date 11/01/2008
 */
public class ExibirEncerrarOrdemServicoVencidaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirEncerrarOrdemServicoVencidaAction");
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);

		// Form
		EncerrarOrdemServicoVencidaActionForm encerrarOrdemServicoVencida = 
			(EncerrarOrdemServicoVencidaActionForm) actionForm;
		
		// Limpa os Campos
		if (httpServletRequest.getParameter("menu") != null) {
			encerrarOrdemServicoVencida.setTipoOrdem("");
			encerrarOrdemServicoVencida.setQuantidadeDias("");
		}
		
		// Pesquisa o Tipo de Servico
		if(sessao.getAttribute("colecaoTipoServico") == null){
			pesquisarTipoServico(encerrarOrdemServicoVencida, fachada, sessao, httpServletRequest);
		}
		
		return retorno;
	}
	
	/**
	 * 
	 * @param encerrarOrdemServicoVencida
	 * @param fachada
	 * @param sessao
	 * @param httpServletRequest
	 */
	private void pesquisarTipoServico(
			EncerrarOrdemServicoVencidaActionForm encerrarOrdemServicoVencida,
			Fachada fachada,
			HttpSession sessao,
			HttpServletRequest httpServletRequest) {
		
		FiltroTipoServico filtro = new FiltroTipoServico();
		filtro.adicionarParametro(new ParametroSimples(
				FiltroTipoServico.ID, ServicoTipo.TIPO_EFETUAR_INSTALACAO_HIDROMETRO, ParametroSimples.CONECTOR_OR));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroTipoServico.ID, ServicoTipo.TIPO_EFETUAR_SUBSTITUICAO_HIDROMETRO, ParametroSimples.CONECTOR_OR));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroTipoServico.ID, ServicoTipo.TIPO_CORTE_DE_AGUA_POR_DEBITO));
		filtro.adicionarParametro(new ParametroSimples(
				FiltroTipoServico.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtro.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		
		Collection<ServicoTipo> colecaoTipoServico = 
			fachada.pesquisar(filtro, ServicoTipo.class.getName());
		
		// [FS0001 - Verificar Existencia de dados]
		if ( (colecaoTipoServico == null) || (colecaoTipoServico.isEmpty()) ) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null, ServicoTipo.class.getName());
		}else {
			sessao.setAttribute("colecaoTipoServico", colecaoTipoServico);
		}
	}
}