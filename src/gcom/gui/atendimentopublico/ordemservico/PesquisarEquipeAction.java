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

import gcom.atendimentopublico.ordemservico.Equipe;
import gcom.atendimentopublico.ordemservico.FiltroEquipe;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0377] Pesquisar Equipe
 * 
 * @author Leonardo Regis
 * @date 31/07/2006
 * 
 */
public class PesquisarEquipeAction extends GcomAction {

	/**
	 * [UC0377] Pesquisar Equipe
	 * 
	 * @author Leonardo Regis
	 * @date 31/07/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirResultadoPesquisaEquipe");

		HttpSession sessao = httpServletRequest.getSession(false);

		PesquisarEquipeActionForm pesquisarEquipeActionForm = (PesquisarEquipeActionForm) actionForm;

		FiltroEquipe filtroEquipe = new FiltroEquipe();

		Fachada fachada = Fachada.getInstancia();

		boolean peloMenosUmParametroInformado = false;
		
		// Verifica se o campo id foi informado
		if (pesquisarEquipeActionForm.getEquipeId() != null 
				&& !pesquisarEquipeActionForm.getEquipeId().trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID, pesquisarEquipeActionForm.getEquipeId()));
		}
		// Verifica se o campo nome foi informado
		if (pesquisarEquipeActionForm.getNomeEquipe() != null 
				&& !pesquisarEquipeActionForm.getNomeEquipe().trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ComparacaoTexto(FiltroEquipe.NOME, pesquisarEquipeActionForm.getNomeEquipe()));
		}
		// Verifica se o campo placaVeiculo foi informado
		if (pesquisarEquipeActionForm.getPlacaVeiculo() != null 
				&& !pesquisarEquipeActionForm.getPlacaVeiculo().trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ComparacaoTexto(FiltroEquipe.PLACA_VEICULO,	pesquisarEquipeActionForm.getPlacaVeiculo()));
		}
		// Verifica se o campo cargaTrabalho foi informado
		if (pesquisarEquipeActionForm.getCargaTrabalhoDia() != null 
				&& !pesquisarEquipeActionForm.getCargaTrabalhoDia().trim().equals("")) {
			int cargaTrabalhoMinutos = new Integer(pesquisarEquipeActionForm.getCargaTrabalhoDia()).intValue()*60;
			Equipe equipe = new Equipe();
			equipe.setCargaTrabalho(cargaTrabalhoMinutos);
			fachada.validarInsercaoEquipe(equipe);
			peloMenosUmParametroInformado = true;
			
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.CARGA_TRABALHO, cargaTrabalhoMinutos));
		}
		// Verifica se o campo unidadeOrganizacional foi informado
		if (pesquisarEquipeActionForm.getUnidadeOrganizacionalId() != null 
				&& !pesquisarEquipeActionForm.getUnidadeOrganizacionalId().trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID_UNIDADE_ORGANIZACIONAL, pesquisarEquipeActionForm.getUnidadeOrganizacionalId()));
		}
		// Verifica se o campo perfilServico foi informado
		if (pesquisarEquipeActionForm.getTipoPerfilServicoId() != null 
				&& !pesquisarEquipeActionForm.getTipoPerfilServicoId().trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroEquipe.adicionarParametro(new ParametroSimples(FiltroEquipe.ID_SERVICO_PERFIL_TIPO, pesquisarEquipeActionForm.getTipoPerfilServicoId()));
		}
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}

		Collection colecaoEquipe = (Collection) fachada.pesquisar(filtroEquipe, Equipe.class.getName());
		if (colecaoEquipe == null || colecaoEquipe.isEmpty()) {
			throw new ActionServletException("atencao.naocadastrado", null,	"Equipe");
		} else {
			
			// Aciona o controle de paginação para que sejam pesquisados apenas
			// os registros que aparecem na página
			Map resultado = controlarPaginacao(httpServletRequest, retorno,
													filtroEquipe, Equipe.class.getName());
			colecaoEquipe = (Collection) resultado.get("colecaoRetorno");
			retorno = (ActionForward) resultado.get("destinoActionForward");

			sessao.setAttribute("colecaoEquipe", colecaoEquipe);
		}
		

		return retorno;
	}

}
