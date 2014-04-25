package gcom.gui.operacional;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.gui.GcomAction;
import gcom.operacional.FiltroProducaoAgua;
import gcom.operacional.ProducaoAgua;
import gcom.util.ConstantesSistema;
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
 * 
 * @author Vinicius Medeiros
 * @date 11/06/2008
 */
public class ExibirAtualizarProducaoAguaAction extends GcomAction {

	
	private Collection colecaoPesquisa;

	
	/**
	 * Método responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho do retorno
		ActionForward retorno = actionMapping.findForward("producaoAguaAtualizar");

		AtualizarProducaoAguaActionForm atualizarProducaoAguaActionForm =(AtualizarProducaoAguaActionForm) actionForm;

		// Mudar isso quando houver a implementação do esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String id = httpServletRequest.getParameter("idRegistroAtualizacao");
		
		if (id == null) {
			
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") != null) {
			
				id = (String) httpServletRequest.getAttribute("idRegistroAtualizacao");
			
			}
		}
		
		FiltroProducaoAgua filtroProducaoAgua = new FiltroProducaoAgua();
		filtroProducaoAgua.adicionarCaminhoParaCarregamentoEntidade(FiltroProducaoAgua.LOCALIDADE);

		ProducaoAgua producaoAgua = new ProducaoAgua();
		
		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			filtroProducaoAgua.adicionarParametro(
				new ParametroSimples(FiltroProducaoAgua.ID, id));
			
			Collection colecaoProducaoAgua = 
				this.getFachada().pesquisar(filtroProducaoAgua, ProducaoAgua.class.getName());
			
			if (colecaoProducaoAgua != null && !colecaoProducaoAgua.isEmpty()) {
		
				producaoAgua = (ProducaoAgua) Util.retonarObjetoDeColecao(colecaoProducaoAgua);
			
			}

			if (producaoAgua != null) {

				atualizarProducaoAguaActionForm.setId(producaoAgua.getId().toString());
				atualizarProducaoAguaActionForm.setAnoMesReferencia(
					Util.formatarAnoMesParaMesAno(producaoAgua.getAnoMesReferencia()));

				atualizarProducaoAguaActionForm.setVolumeProduzido(
					Util.formataBigDecimal(producaoAgua.getVolumeProduzido(),2,true));

				atualizarProducaoAguaActionForm.setLocalidadeID(
					producaoAgua.getLocalidade().getId().toString());

				atualizarProducaoAguaActionForm.setLocalidadeDescricao(
					producaoAgua.getLocalidade().getDescricao());

			}

		}
		
		sessao.setAttribute("atualizarProducaoAgua", producaoAgua);
		
		String objetoConsulta = (String) httpServletRequest.getParameter("objetoConsulta");
		
		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {

			// Localidade
			case 1:

				// Recebe o valor do campo localidadeID do formulário.
				String localidadeID = atualizarProducaoAguaActionForm.getLocalidadeID();

				FiltroLocalidade filtroLocalidade = new FiltroLocalidade();

				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.ID,localidadeID));

				filtroLocalidade.adicionarParametro(
					new ParametroSimples(FiltroLocalidade.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Localidade
				colecaoPesquisa = 
					this.getFachada().pesquisar(filtroLocalidade,Localidade.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Localidade nao encontrado
					// Limpa o campo localidadeID do formulário
					atualizarProducaoAguaActionForm.setLocalidadeID("");
					atualizarProducaoAguaActionForm.setLocalidadeDescricao("Localidade inexistente.");
					
					httpServletRequest.setAttribute("corLocalidade","exception");
					httpServletRequest.setAttribute("nomeCampo","localidadeID");
			
				} else {
					
					Localidade objetoLocalidade = 
						(Localidade) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					atualizarProducaoAguaActionForm.setLocalidadeID(
						String.valueOf(objetoLocalidade.getId()));
					
					atualizarProducaoAguaActionForm.setLocalidadeDescricao(
						objetoLocalidade.getDescricao());
					
					httpServletRequest.setAttribute("corLocalidade","valor");
					httpServletRequest.setAttribute("nomeCampo","localidadeID");
				}

				break;
			}
		}

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (sessao.getAttribute("colecaoProducaoAgua") != null) {
			sessao.setAttribute("caminhoRetornoVoltar","/gsan/filtrarProducaoAguaAction.do");
		} else {
			sessao.setAttribute("caminhoRetornoVoltar","/gsan/exibirFiltrarProducaoAguaAction.do");
		}


		return retorno;
	}
}
