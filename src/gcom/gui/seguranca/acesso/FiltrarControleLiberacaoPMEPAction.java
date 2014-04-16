package gcom.gui.seguranca.acesso;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.ControleLiberacaoPermissaoEspecial;
import gcom.seguranca.acesso.FiltroControleLiberacaoPermissaoEspecial;
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
 * [UC0283] Filtrar Controle de Liberação de Permissão Especial
 * 
 * @author Daniel Alves
 * @date 13/08/2010
 * 
 * @param actionMapping
 * @param actionForm
 * @param httpServletRequest
 * @param httpServletResponse
 * @return
 */

public class FiltrarControleLiberacaoPMEPAction extends GcomAction {

	/**
	 * Description of the Method
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

		ActionForward retorno = actionMapping
				.findForward("exibirResultadoPesquisaControleLiberacaoPMEPAction");
		
		FiltrarControleLiberacaoPMEPActionForm filtrarControleLiberacaoPMEPActionForm = (FiltrarControleLiberacaoPMEPActionForm) actionForm;

		
		FiltroControleLiberacaoPermissaoEspecial filtroControleLiberacaoPermissaoEspecial = new FiltroControleLiberacaoPermissaoEspecial();		

		HttpSession sessao = httpServletRequest.getSession(false);
		
		boolean peloMenosUmParametroInformado = false;

		String codigoFuncionalidade = filtrarControleLiberacaoPMEPActionForm.getIdFuncionalidade();

		String codigoPermissaoEspecial = filtrarControleLiberacaoPMEPActionForm.getIdPermissaoEspecial();

		String indicadorUso = filtrarControleLiberacaoPMEPActionForm.getIndicadorUso();


		// Verifica se o campo codigoFuncionalidade foi informado

		if (codigoFuncionalidade != null && !codigoFuncionalidade.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroControleLiberacaoPermissaoEspecial.adicionarParametro(new ParametroSimples(
					FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE_ID, codigoFuncionalidade));			

		}

		// Verifica se o campo codigoPermissaoEspecial foi informado

		if (codigoPermissaoEspecial != null && !codigoPermissaoEspecial.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;

			filtroControleLiberacaoPermissaoEspecial.adicionarParametro(new ComparacaoTexto(
					FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL_ID, codigoPermissaoEspecial));

		}

		// Verifica se o campo indicadorUso foi informado

		if (indicadorUso != null
				&& !indicadorUso.trim().equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			filtroControleLiberacaoPermissaoEspecial.adicionarParametro(new ParametroSimples(
					FiltroControleLiberacaoPermissaoEspecial.INDICADOR_USO, indicadorUso));

		}
		

		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		
		// Verifica se o checkbox Atualizar está marcado e em caso afirmativo
		// manda pelo um request uma variável para o
		// ExibirManterFuncionalidadeAction e nele verificar se irá para o
		// atualizar ou para o manter
		if (filtrarControleLiberacaoPMEPActionForm.getAtualizar() != null
				&& filtrarControleLiberacaoPMEPActionForm.getAtualizar()
						.equalsIgnoreCase("1")) {
			httpServletRequest.setAttribute("atualizar",
					filtrarControleLiberacaoPMEPActionForm.getAtualizar());
			
			
		}
		
		/*filtroControleLiberacaoPermissaoEspecial = new FiltroControleLiberacaoPermissaoEspecial();
		
		
		if(filtroControleLiberacaoPermissaoEspecial == null){			
			filtroControleLiberacaoPermissaoEspecial = (FiltroControleLiberacaoPermissaoEspecial)httpServletRequest.getAttribute("filtroControleLiberacaoPermissaoEspecial");
		}*/
		
		filtroControleLiberacaoPermissaoEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.FUNCIONALIDADE);
		
		filtroControleLiberacaoPermissaoEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroControleLiberacaoPermissaoEspecial.PERMISSAO_ESPECIAL);
		
		
		Collection colecaoResultadoPesquisaControleLiberacaoPMEP = this.getFachada().pesquisar(filtroControleLiberacaoPermissaoEspecial, ControleLiberacaoPermissaoEspecial.class.getName());
		
		String indicadorAtualizar = httpServletRequest
			.getParameter("atualizar");
		
		if(colecaoResultadoPesquisaControleLiberacaoPMEP.size() == 1 && !indicadorAtualizar.equals("")){
						
			retorno = actionMapping.findForward("exibirManterControleLiberacaoPMEPAction");
			
		}
		
		//-----------------------------
		// Manda a colecao pela sessao para o
		// ExibirResultadoPesquisaAction

		sessao.setAttribute("colecaoResultadoPesquisaControleLiberacaoPMEP", colecaoResultadoPesquisaControleLiberacaoPMEP);
		
		return retorno;

	}

}
