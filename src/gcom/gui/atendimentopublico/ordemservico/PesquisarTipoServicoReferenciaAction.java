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
package gcom.gui.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipoReferencia;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipoReferencia;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório
 * @date 05/08/2006
 */
public class PesquisarTipoServicoReferenciaAction extends GcomAction {

	/**
	 * Este caso de uso permite Pesquisar um Tipo de Servicço
	 * 
	 * [UC0437] Pesquisar Tipo de Serviço de Referência
	 * 
	 * 
	 * @author Thiago Tenório
	 * @date 31/07/2006
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

		ActionForward retorno = actionMapping
				.findForward("exibirResultadoPesquisaTipoServicoReferencia");

		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarTipoServicoReferenciaActionForm pesquisarTipoServicoReferenciaActionForm = (PesquisarTipoServicoReferenciaActionForm) actionForm;

		FiltroServicoTipoReferencia filtroServicoTipoReferencia = new FiltroServicoTipoReferencia();

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;

		String descricao = pesquisarTipoServicoReferenciaActionForm
				.getDescricao();

		String descricaoAbreviada = pesquisarTipoServicoReferenciaActionForm
				.getDescricaoAbreviada();

		String indicadorExistenciaOsReferencia = pesquisarTipoServicoReferenciaActionForm
				.getIndicadorExistenciaOsReferencia();

		// Verifica se o campo Descrição foi informado

		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoTipoReferencia.adicionarParametro(new ComparacaoTexto(
					FiltroServicoTipoReferencia.DESCRICAO, descricao));

		}

		// Verifica se o campo descrição abreviada foi informado

		if (descricaoAbreviada != null
				&& !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroServicoTipoReferencia.adicionarParametro(new ComparacaoTexto(
					FiltroServicoTipoReferencia.DESCRICAO_ABREVIADA,
					descricaoAbreviada));

		}

		// Verifica se o campo Indicador de existência da OS de Referência foi informado

		if (indicadorExistenciaOsReferencia != null && !indicadorExistenciaOsReferencia.equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroServicoTipoReferencia
					.adicionarParametro(new ParametroSimples(
							FiltroServicoTipoReferencia.INDICADOR_EXISTENCIA_OS_REFERENCIA,
							indicadorExistenciaOsReferencia));

		} 
		
		String idTipoServico = pesquisarTipoServicoReferenciaActionForm
		.getIdTipoServico();

		if (idTipoServico != null && !idTipoServico.trim().equals("")) {

			peloMenosUmParametroInformado = true;
			
			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(
					FiltroServicoTipo.ID, idTipoServico));
			
			filtroServicoTipo.adicionarCaminhoParaCarregamentoEntidade("servicoTipoReferencia");
		
			Collection colecaoTipoServico = fachada.pesquisar(
					filtroServicoTipo, ServicoTipo.class.getName());
		
			if (colecaoTipoServico != null && !colecaoTipoServico.isEmpty()) {
				
				ServicoTipo servicoTipo = (ServicoTipo) colecaoTipoServico
						.iterator().next();
				if(servicoTipo.getServicoTipoReferencia() != null){
				filtroServicoTipoReferencia.adicionarParametro(new ParametroSimples(
						FiltroServicoTipoReferencia.ID,servicoTipo.getServicoTipoReferencia().getId()));
				}else{
						throw new ActionServletException("atencao.naocadastrado", null,
								"Serviço Referência");
				}
				
				
			}else{
				
				throw new ActionServletException("atencao.tipo_servico_inexistente");
				
			}
			
		}
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		filtroServicoTipoReferencia
				.adicionarCaminhoParaCarregamentoEntidade("servicoTipo");

		Collection colecaoServicoTipoReferencia = (Collection) fachada
				.pesquisar(filtroServicoTipoReferencia,
						ServicoTipoReferencia.class.getName());

		if (colecaoServicoTipoReferencia == null
				|| colecaoServicoTipoReferencia.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado","Serviço Referência");
		}

		sessao.setAttribute("colecaoServicoTipoReferencia",
				colecaoServicoTipoReferencia);

		return retorno;
	}

}