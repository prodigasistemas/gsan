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

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
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
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 20/03/2007
 */
public class AlterarSituacaoLigacaoAction extends GcomAction {

	/**
	 * [UC0555] Alterar Situacao da Ligacao
	 * 
	 * Este caso de uso permite alterar a situacao da ligacao de agua e/ou
	 * esgoto de acordo com o indicadorde rede e Ordem de Servico gerada.
	 * 
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		AlterarSituacaoLigacaoActionForm form = (AlterarSituacaoLigacaoActionForm) actionForm;

		//eeIntegracaoComercialHelper integracaoComercialHelper = new IntegracaoComercialHelper();

		OrdemServico ordemServico = (OrdemServico) sessao
				.getAttribute("ordemServico");
		
		boolean veioEncerrarOS = false;

		// Usuario logado no sistema
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		
		String idOrdemServico = form.getIdOrdemServico();
		String indicadorTipoLigacao = form.getIndicadorTipoLigacao();
		String idSituacaoLigacaoAguaNova = form.getSituacaoLigacaoAguaNova();
		String idSituacaoLigacaoEsgotoNova = form
				.getSituacaoLigacaoEsgotoNova();
		
		if (idOrdemServico == null) {
			throw new ActionServletException("atencao.required", null,
					"Ordem de Serviço");
		} 

		if (indicadorTipoLigacao == null) {
			throw new ActionServletException("atencao.required", null,
					"Tipo de Ligacao a ser Removida");
		}
		
		if (indicadorTipoLigacao == null) {
			throw new ActionServletException("atencao.required", null,
					"Tipo de Ligacao a ser Removida");
		}
		if (indicadorTipoLigacao != null){
			if(indicadorTipoLigacao.equalsIgnoreCase("1") 
				&& idSituacaoLigacaoAguaNova.equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
					"Nova Situação da Ligação de Água");
			}
		}
		
		if (indicadorTipoLigacao != null){
			if(indicadorTipoLigacao.equalsIgnoreCase("2") 
				&& idSituacaoLigacaoEsgotoNova.equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
					"Nova Situação da Ligação de Esgoto");
			}
		}
		
		if (indicadorTipoLigacao != null){
			if(indicadorTipoLigacao.equalsIgnoreCase("3") 
				&& idSituacaoLigacaoAguaNova.equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)
				&& idSituacaoLigacaoEsgotoNova.equalsIgnoreCase(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				throw new ActionServletException("atencao.required", null,
					"Nova Situação da Ligação de Água e de Esgoto");
			}
		}
		
		fachada.validarOrdemServicoAlterarSituacaoLigacao(ordemServico,veioEncerrarOS);
		
		Imovel imovel = ordemServico.getImovel();

		FiltroImovel filtroImovel = new FiltroImovel();

		filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID,
				imovel.getId()));

		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoAgua");
		filtroImovel.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgoto");

		Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class
				.getName());

		imovel = (Imovel) colecaoImovel.iterator().next();
		
		Integer idImovel = fachada.alterarSituacaoLigacao( imovel,  indicadorTipoLigacao,  idSituacaoLigacaoAguaNova, 
				 idSituacaoLigacaoEsgotoNova,  idOrdemServico, 
				 usuarioLogado);

		
		montarPaginaSucesso(httpServletRequest, "A Alteração da Situação da Ligação do Imóvel "
				+ idImovel+ " efetuada com sucesso.",
				"Alterar outra Situação da Ligação",
				"exibirAlterarSituacaoLigacaoAction.do?menu=sim");
		return retorno;
	}
}
