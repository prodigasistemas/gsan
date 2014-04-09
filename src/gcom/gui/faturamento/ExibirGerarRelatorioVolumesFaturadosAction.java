package gcom.gui.faturamento;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que faz a exibição da tela para o usuário setar os parâmetros
 * necessário para a geração do relatório
 * 
 * [UC0637] Gerar Relatórios Volumes Faturados
 * 
 * @author Rafael Corrêa
 * @since 12/09/2007
 */
public class ExibirGerarRelatorioVolumesFaturadosAction extends GcomAction {

	/**
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

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioVolumesFaturados");

		GerarRelatorioVolumesFaturadosActionForm gerarRelatorioVolumesFaturadosActionForm = (GerarRelatorioVolumesFaturadosActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		if (httpServletRequest.getParameter("menu") != null
				&& !httpServletRequest.getParameter("menu").equals("")) {
			
			gerarRelatorioVolumesFaturadosActionForm.setOpcaoRelatorio("analitico");
			
		} else {

			String idLocalidade = gerarRelatorioVolumesFaturadosActionForm
					.getIdLocalidade();

			if (idLocalidade != null && !idLocalidade.equals("")) {
				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(new ParametroSimples(
						FiltroLocalidade.ID, idLocalidade));

				Collection colecaoLocalidades = fachada.pesquisar(
						filtroLocalidade, Localidade.class.getName());

				if (colecaoLocalidades != null && !colecaoLocalidades.isEmpty()) {
					Localidade localidade = (Localidade) Util
							.retonarObjetoDeColecao(colecaoLocalidades);

					gerarRelatorioVolumesFaturadosActionForm
							.setIdLocalidade(localidade.getId().toString());
					gerarRelatorioVolumesFaturadosActionForm
							.setNomeLocalidade(localidade.getDescricao());
					httpServletRequest.setAttribute("nomeCampo", "mesAno");

				} else {
					gerarRelatorioVolumesFaturadosActionForm
							.setIdLocalidade("");
					gerarRelatorioVolumesFaturadosActionForm
							.setNomeLocalidade("Localidade Inexistente");

					httpServletRequest.setAttribute(
							"idLocalidadeNaoEncontrada", "true");

					httpServletRequest
							.setAttribute("nomeCampo", "idLocalidade");
				}
			}

		}

		return retorno;

	}

}
