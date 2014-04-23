package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroZonaPressao;
import gcom.operacional.ZonaPressao;
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
 * @date 21/05/2008
 */
public class ExibirAtualizarZonaPressaoAction extends GcomAction {

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

	private String distritoOperacionalID;

	private Collection colecaoPesquisa;

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("zonaPressaoAtualizar");

		AtualizarZonaPressaoActionForm atualizarZonaPressaoActionForm = (AtualizarZonaPressaoActionForm) actionForm;

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		ZonaPressao zonaPressao = new ZonaPressao();

		String id = httpServletRequest.getParameter("idRegistroAtualizacao");

		String objetoConsulta = (String) httpServletRequest
				.getParameter("objetoConsulta");

		FiltroZonaPressao filtroZonaPressao = new FiltroZonaPressao();
		
		filtroZonaPressao.adicionarCaminhoParaCarregamentoEntidade(FiltroZonaPressao.DISTRITO_OPERACIONAL);	
		
		String zonaPressaoID = null;
		if ((objetoConsulta == null || objetoConsulta.equalsIgnoreCase(""))
				&& (httpServletRequest.getParameter("desfazer") == null)) {
			
			// Recupera o id da Zona de Pressao que vai ser atualizada
			if (httpServletRequest.getParameter("idRegistroInseridoAtualizar") != null) {

				zonaPressaoID = httpServletRequest.getParameter("idRegistroInseridoAtualizar");

				// Define a volta do botão Voltar para Filtrar Zona de Pressão
				sessao.setAttribute("voltar", "filtrar");
				sessao.setAttribute("idRegistroAtualizar", zonaPressaoID);
				sessao.setAttribute("indicadorUso", "3");
			
			} else if (httpServletRequest.getParameter("idRegistroAtualizar") == null) {
				
				zonaPressaoID = (String) sessao.getAttribute("idRegistroAtualizar");
				// Define a volta do botão Voltar para Filtrar Zona de Pressão
				sessao.setAttribute("voltar", "filtrar");
			
			} else if (httpServletRequest.getParameter("idRegistroAtualizar") != null) {
			
				zonaPressaoID = httpServletRequest.getParameter("idRegistroAtualizar");

				// Define a volta do botão Voltar para Manter Zona de Pressão
				sessao.setAttribute("voltar", "manter");
				sessao.setAttribute("idRegistroAtualizar", zonaPressaoID);
			
			}

		} else {
			
			zonaPressaoID = (String) sessao.getAttribute("idRegistroAtualizar");
		
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equalsIgnoreCase("")) {

			// Recebe o valor do campo distritoOperacionalID do formulário.
			distritoOperacionalID = atualizarZonaPressaoActionForm.getDistritoOperacionalID();

			switch (Integer.parseInt(objetoConsulta)) {

			// Distrito Operacional
			case 1:

				FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

				filtroDistritoOperacional.adicionarParametro(
						new ParametroSimples(FiltroDistritoOperacional.ID,
								distritoOperacionalID));

				filtroDistritoOperacional.adicionarParametro(
						new ParametroSimples(FiltroDistritoOperacional.INDICADORUSO,
								ConstantesSistema.INDICADOR_USO_ATIVO));

				// Retorna Distrito Operacional
				colecaoPesquisa = fachada.pesquisar(
						filtroDistritoOperacional,DistritoOperacional.class.getName());

				if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
					// Distrito Operacional nao encontrado
					// Limpa o campo distritoOperacionalID do formulário
					atualizarZonaPressaoActionForm.setDistritoOperacionalID("");
					atualizarZonaPressaoActionForm.setDistritoOperacionalDescricao("Distrito operacional inexistente.");
					httpServletRequest.setAttribute("corDistritoOperacional","exception");

					httpServletRequest.setAttribute("nomeCampo","distritoOperacionalID");
					
				} else {
					
					DistritoOperacional objetoDistritoOperacional = 
						(DistritoOperacional) Util.retonarObjetoDeColecao(colecaoPesquisa);
					
					atualizarZonaPressaoActionForm.setDistritoOperacionalID(
							String.valueOf(objetoDistritoOperacional.getId()));
					atualizarZonaPressaoActionForm.setDistritoOperacionalDescricao(
							objetoDistritoOperacional.getDescricao());
					
					httpServletRequest.setAttribute("corDistritoOperacional","valor");
					httpServletRequest.setAttribute("nomeCampo","distritoOperacionalID");

				}

				break;

			}
		}

		// Verifica se veio do Filtrar ou do Manter
		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}

		if (id == null) {
			
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
				
			} else {
				
				id = (String) httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			
			}
			
		} else {
			sessao.setAttribute("i", true);
		}
		
		if (zonaPressao.getDistritoOperacional() != null) {
			
			atualizarZonaPressaoActionForm.setDistritoOperacionalID(
					zonaPressao.getDistritoOperacional().getId().toString());
			atualizarZonaPressaoActionForm.setDistritoOperacionalDescricao(
					zonaPressao.getDistritoOperacional().getDescricao());

		}

		if (zonaPressao == null) {

			if (id != null && !id.equals("")) {

				FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();
				filtroDistritoOperacional.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
			
				filtroDistritoOperacional.adicionarParametro(
						new ParametroSimples(FiltroDistritoOperacional.ID, id));

				Collection colecaoZonaPressao = fachada.pesquisar(
						filtroDistritoOperacional, DistritoOperacional.class.getName());

				if (colecaoZonaPressao != null && !colecaoZonaPressao.isEmpty()) {
					
					zonaPressao = (ZonaPressao) Util.retonarObjetoDeColecao(colecaoZonaPressao);
				
				}
			}
		}

		if (id != null && !id.trim().equals("") && Integer.parseInt(id) > 0) {

			filtroZonaPressao.adicionarParametro(
					new ParametroSimples(FiltroZonaPressao.ID, id));
			
			Collection colecaoZonaPressao = fachada.pesquisar(
					filtroZonaPressao, ZonaPressao.class.getName());
			
			if (colecaoZonaPressao != null && !colecaoZonaPressao.isEmpty()) {
			
				zonaPressao = (ZonaPressao) Util.retonarObjetoDeColecao(colecaoZonaPressao);
			
			}


			if (id == null || id.trim().equals("")) {

				atualizarZonaPressaoActionForm.setId(zonaPressao.getId().toString());
				atualizarZonaPressaoActionForm.setDescricao(zonaPressao.getDescricaoZonaPressao());
				atualizarZonaPressaoActionForm.setDescricaoAbreviada(zonaPressao.getDescricaoAbreviada());
				
				atualizarZonaPressaoActionForm.setDistritoOperacionalID(
						zonaPressao.getDistritoOperacional().getId().toString());
				atualizarZonaPressaoActionForm.setDistritoOperacionalDescricao(
						zonaPressao.getDistritoOperacional().getDescricao());

			}

			atualizarZonaPressaoActionForm.setId(id);
			atualizarZonaPressaoActionForm.setDescricao(zonaPressao.getDescricaoZonaPressao());
			atualizarZonaPressaoActionForm.setDescricaoAbreviada(zonaPressao.getDescricaoAbreviada());
			atualizarZonaPressaoActionForm.setIndicadorUso(""+ zonaPressao.getIndicadorUso());

			atualizarZonaPressaoActionForm.setDistritoOperacionalID(
					zonaPressao.getDistritoOperacional().getId().toString());
			atualizarZonaPressaoActionForm.setDistritoOperacionalDescricao(
					zonaPressao.getDistritoOperacional().getDescricao());

			sessao.setAttribute("atualizarZonaPressao", zonaPressao);

			if (sessao.getAttribute("colecaoZonaPressao") != null) {
				
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/filtrarZonaPressaoAction.do");
			} else {
				
				sessao.setAttribute("caminhoRetornoVoltar",
						"/gsan/exibirFiltrarZonaPressaoAction.do");
			
			}

		}

		return retorno;
	}
}
