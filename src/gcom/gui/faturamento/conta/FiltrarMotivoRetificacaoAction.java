package gcom.gui.faturamento.conta;

import gcom.faturamento.conta.FiltroContaMotivoRetificacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela exibição da pagina de filtrar motivo de retificação
 * [UC1119] Filtrar Motivo Retificação
 * 
 * @author Mariana Victor
 * @date 11/01/2011
 */
public class FiltrarMotivoRetificacaoAction extends GcomAction {
	
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterMotivoRetificacaoAction");
		
		FiltrarMotivoRetificacaoActionForm form = (FiltrarMotivoRetificacaoActionForm) actionForm;
	
		boolean peloMenosUmParametroInformado = false;
		
		FiltroContaMotivoRetificacao filtroContaMotivoRetificacao = new FiltroContaMotivoRetificacao();
		
		if (form.getDescricao() != null && !form.getDescricao().equals("")){
			peloMenosUmParametroInformado = true;
			filtroContaMotivoRetificacao.adicionarParametro(
					new ParametroSimples(FiltroContaMotivoRetificacao.DESCRICAO, form.getDescricao()));
		}
		
		if (form.getNumeroOcorrenciasNoAno() != null 
				&& !form.getNumeroOcorrenciasNoAno().equals("")){
			peloMenosUmParametroInformado = true;
			filtroContaMotivoRetificacao.adicionarParametro(
					new ParametroSimples(FiltroContaMotivoRetificacao.NUMERO_OCORRENCIAS_NO_ANO, form.getNumeroOcorrenciasNoAno()));
		}
		
		if (form.getIndicadorCompetenciaConsumo() != null 
				&& !form.getIndicadorCompetenciaConsumo().equals("")
				&& !form.getIndicadorCompetenciaConsumo().equals("3")){
			peloMenosUmParametroInformado = true;
			filtroContaMotivoRetificacao.adicionarParametro(
					new ParametroSimples(FiltroContaMotivoRetificacao.INDICADOR_COMPETENCIA_CONSUMO, form.getIndicadorCompetenciaConsumo()));
		}
		
		if (form.getIndicadorUso() != null
				&& !form.getIndicadorUso().equals("")
				&& !form.getIndicadorUso().equals("3")){
			peloMenosUmParametroInformado = true;
			filtroContaMotivoRetificacao.adicionarParametro(
					new ParametroSimples(FiltroContaMotivoRetificacao.INDICADOR_USO, form.getIndicadorUso()));
		}
		
		// Erro caso o usuário mandou Pesquisar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		if (httpServletRequest.getParameter("atualizar") != null
				&& httpServletRequest.getParameter("atualizar").equals("sim")) {
			sessao.setAttribute("atualizar",
					form.getIndicadorAtualizar());
		} else {
			sessao.removeAttribute("atualizar");
		}
		
		// Manda o filtro pelo sessao para o
		// ExibirManterMotivoRetificacaoAction
		sessao.setAttribute("filtroContaMotivoRetificacao", filtroContaMotivoRetificacao);
		
		return retorno;
	}

}
