package gcom.gui.operacional;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.operacional.FiltroZonaPressao;
import gcom.operacional.ZonaPressao;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Vinícius Medeiros
 * @date 20/05/2008
 */
public class InserirZonaPressaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Zona de Pressao
	 * 
	 * [UC0796] Inserir Zona de Pressao
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 20/05/2008
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirZonaPressaoActionForm inserirZonaPressaoActionForm = (InserirZonaPressaoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirZonaPressaoActionForm.getDescricao();
		String descricaoAbreviada = inserirZonaPressaoActionForm.getDescricaoAbreviada();
		String distritoOperacionalID = inserirZonaPressaoActionForm.getDistritoOperacionalID();
		
		ZonaPressao zonaPressao = new ZonaPressao();
		Collection colecaoPesquisa = null;

		// Descrição
		if (!"".equals(inserirZonaPressaoActionForm.getDescricao())) {
			zonaPressao.setDescricaoZonaPressao(inserirZonaPressaoActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
		
		if (distritoOperacionalID != null
				&& !distritoOperacionalID.equalsIgnoreCase("")) {

			FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
					FiltroDistritoOperacional.ID, distritoOperacionalID));

			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
					FiltroDistritoOperacional.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Distrito Operacional
			colecaoPesquisa = fachada.pesquisar(filtroDistritoOperacional,
					DistritoOperacional.class.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				throw new ActionServletException(
						"atencao.pesquisa.distrito_operacional_inexistente");
			} else {
				DistritoOperacional objetoDistritoOperacional = (DistritoOperacional) Util
						.retonarObjetoDeColecao(colecaoPesquisa);
				zonaPressao.setDistritoOperacional(objetoDistritoOperacional);
			}
		}
		
		
		zonaPressao.setUltimaAlteracao(new Date());

		Short iu = 1;
		zonaPressao.setIndicadorUso(iu);

		FiltroZonaPressao filtroZonaPressao = new FiltroZonaPressao();

		filtroZonaPressao.adicionarParametro(new ParametroSimples(
				FiltroZonaPressao.DESCRICAO, zonaPressao.getDescricaoZonaPressao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroZonaPressao, ZonaPressao.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.zona_pressao_ja_cadastrada", null,
					zonaPressao.getDescricaoZonaPressao());
		} else {
			zonaPressao.setDescricaoZonaPressao(descricao);
			
			if(inserirZonaPressaoActionForm.getDescricaoAbreviada() != null && !inserirZonaPressaoActionForm.getDescricaoAbreviada().equals("")){
				zonaPressao.setDescricaoAbreviada(descricaoAbreviada);
			} else {
				zonaPressao.setDescricaoAbreviada(null);
			}

			Integer idZonaPressao = (Integer) fachada
					.inserir(zonaPressao);

			montarPaginaSucesso(httpServletRequest,
					"Zona de Pressão " + descricao
							+ " inserida com sucesso.",
					"Inserir outra Zona de Pressão",
					"exibirInserirZonaPressaoAction.do?menu=sim",
					"exibirAtualizarZonaPressaoAction.do?idRegistroAtualizacao="
							+ idZonaPressao,
					"Atualizar Zona de Pressão Inserida");

			sessao.removeAttribute("InserirZonaPressaoActionForm");

			return retorno;
		}

	}
}
