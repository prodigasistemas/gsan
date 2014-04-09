package gcom.gui.operacional;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * @author Vinícius Medeiros
 * @date 10/06/2008
 */

public class ExibirFiltrarProducaoAguaAction extends GcomAction {

	private String localidadeID;

	private Collection colecaoPesquisa;

	/*
	 * @param actionMapping @param actionForm @param httpServletRequest @param
	 * httpServletResponse @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("filtrarProducaoAgua");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando houver implementação do esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarProducaoAguaActionForm filtrarProducaoAguaActionForm = (FiltrarProducaoAguaActionForm) actionForm;

		String primeiraVez = httpServletRequest.getParameter("menu");
		if (primeiraVez != null && !primeiraVez.equals("")) {
			
			sessao.setAttribute("indicadorAtualizar", "1");
		
		}

		if (filtrarProducaoAguaActionForm.getIndicadorAtualizar() == null) {
		
			filtrarProducaoAguaActionForm.setIndicadorAtualizar("1");
		}
		

		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");

		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

			// Recebe o valor do campo localidadeID do formulário.
			localidadeID = filtrarProducaoAguaActionForm.getLocalidadeID();

			switch (Integer.parseInt(objetoConsulta)) {

			// Distrito Operacional
			case 1:
				pesquisarLocalidade(
						filtrarProducaoAguaActionForm, fachada,httpServletRequest);
				break;
				
			default:
				break;
			}
		}

		if (httpServletRequest.getParameter("desfazer") != null	
				&& httpServletRequest.getParameter("desfazer").equalsIgnoreCase("S")) {

			filtrarProducaoAguaActionForm.setId("");
			filtrarProducaoAguaActionForm.setVolumeProduzido("");
			filtrarProducaoAguaActionForm.setAnoMesReferencia("");
			filtrarProducaoAguaActionForm.setLocalidadeID("");

		}
		return retorno;

	}

	private void pesquisarLocalidade(
			FiltrarProducaoAguaActionForm filtrarProducaoAguaActionForm,Fachada fachada, 
			HttpServletRequest httpServletRequest) {
		
		if (localidadeID == null || localidadeID.trim().equalsIgnoreCase("")) {
			
			// Limpa o campo localidadeID do formulario
			filtrarProducaoAguaActionForm.setLocalidadeDescricao("Informe Localidade");
			httpServletRequest.setAttribute("corLocalidade", "exception");
		
		} else {
		
			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(
					FiltroLocalidade.ID, localidadeID));

			// Retorna Localidade
			colecaoPesquisa = fachada.pesquisar(
					filtroLocalidade,Localidade.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// Distrito Operacional nao encontrada
				// Limpa o campo DistritoOperacionalID do formulário
				filtrarProducaoAguaActionForm.setLocalidadeID("");
				filtrarProducaoAguaActionForm.setLocalidadeDescricao("Localidade inexistente.");
				httpServletRequest.setAttribute("corLocalidade", "exception");

				httpServletRequest.setAttribute("nomeCampo", "localidadeID");
			
			} else {
			
				Localidade objetoLocalidade = (Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
				filtrarProducaoAguaActionForm.setLocalidadeID(String.valueOf(objetoLocalidade.getId()));
				filtrarProducaoAguaActionForm.setLocalidadeDescricao(objetoLocalidade.getDescricao());
				httpServletRequest.setAttribute("corLocalidade", "valor");

			}
		}
	}
}
