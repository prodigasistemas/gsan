package gcom.gui.cadastro.geografico;

import gcom.cadastro.geografico.BairroArea;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.FiltroDistritoOperacional;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Vivianne Sousa
 * @date 20/12/2006
 */
public class InserirAreaBairroPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping
				.findForward("inserirAreaBairroPopupAction");

		Fachada fachada = Fachada.getInstancia();

		AdicionarAreaBairroActionForm adicionarAreaBairroActionForm = (AdicionarAreaBairroActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		BairroArea bairroAreaInserir = new BairroArea();

		String areaBairroNome = adicionarAreaBairroActionForm
				.getAreaBairroNome();

		String distritoOperacionalID = adicionarAreaBairroActionForm
				.getDistritoOperacionalID();

		Collection<BairroArea> colecaoBairroArea = null;
		
		DistritoOperacional distritoOperacional = null;

		if (sessao.getAttribute("colecaoBairroArea") != null
				&& !sessao.getAttribute("colecaoBairroArea").equals("")) {

			colecaoBairroArea = (Collection) sessao
					.getAttribute("colecaoBairroArea");
		} else {
			colecaoBairroArea = new ArrayList<BairroArea>();
		}

		if (areaBairroNome == null || areaBairroNome.equals("")) {
			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", null,
					"Nome da Área de Bairro");
		}

		// Alteracao Solicitada por Ana Breda Data:22/02/2007
		// Distrito Operacional não é mais obrigatório

		/*
		 * if(distritoOperacionalID == null ||
		 * distritoOperacionalID.equals("")){ throw new
		 * ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Distrito
		 * Operacional"); }
		 */

		BairroArea bairroAreaAtualizar = null;
		if (sessao.getAttribute("atualizar") != null) {
			// remove area bairro e depois insere a area bairro alterada
			String ultimaAlteracao = (String) sessao.getAttribute("atualizar");

			Iterator iter = colecaoBairroArea.iterator();

			while (iter.hasNext()) {
				BairroArea bairroArea = (BairroArea) iter.next();

				if (bairroArea.getUltimaAlteracao().getTime() == Long
						.parseLong(ultimaAlteracao)) {
					bairroAreaAtualizar = bairroArea;
				}

			}
		}

		// Alteracao Solicitada por Ana Breda Data:22/02/2007
		// Distrito Operacional não é mais obrigatório

		if (distritoOperacionalID != null
				&& !distritoOperacionalID.equalsIgnoreCase("-1")) {
			// [FS0006] Verificar existência do distrito operacional
			FiltroDistritoOperacional filtroDistritoOperacional = new FiltroDistritoOperacional();

			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
					FiltroDistritoOperacional.ID, distritoOperacionalID));

			filtroDistritoOperacional.adicionarParametro(new ParametroSimples(
					FiltroDistritoOperacional.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna Distrito Operacional
			Collection colecaoPesquisa = fachada.pesquisar(
					filtroDistritoOperacional, DistritoOperacional.class
							.getName());

			
			if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
				distritoOperacional = (DistritoOperacional) Util
						.retonarObjetoDeColecao(colecaoPesquisa);

			} else {
				throw new ActionServletException(
						"atencao.pesquisa_inexistente", null,
						"Distrito Operacional");
			}
		}

		if (bairroAreaAtualizar != null) {

			// [FS0007] Verificar existência da área de bairro
			if (colecaoBairroArea != null && !colecaoBairroArea.isEmpty()) {

				Iterator iteratorBairroArea = colecaoBairroArea.iterator();

				while (iteratorBairroArea.hasNext()) {
					BairroArea bairroArea = (BairroArea) iteratorBairroArea
							.next();
					if (bairroArea.getNome().trim().equals(
							areaBairroNome.trim())
							&& !bairroArea.getNome().trim().equals(
									bairroAreaAtualizar.getNome().trim())) {
						throw new ActionServletException(
								"atencao.area_bairro_ja_informada", null,
								bairroArea.getNome().trim());
					}
				}
			}

			bairroAreaAtualizar.setNome(areaBairroNome.toUpperCase());

			bairroAreaAtualizar.setDistritoOperacional(distritoOperacional);

		} else {

			// [FS0007] Verificar existência da área de bairro
			if (colecaoBairroArea != null && !colecaoBairroArea.isEmpty()) {

				Iterator iteratorBairroArea = colecaoBairroArea.iterator();

				while (iteratorBairroArea.hasNext()) {
					BairroArea bairroArea = (BairroArea) iteratorBairroArea
							.next();
					if (bairroArea.getNome().trim().equals(
							areaBairroNome.trim())) {
						throw new ActionServletException(
								"atencao.area_bairro_ja_informada", null,
								bairroArea.getNome().trim());
					}
				}
			}

			bairroAreaInserir.setNome(areaBairroNome.toUpperCase());
			
			bairroAreaInserir.setDistritoOperacional(distritoOperacional);
			
			bairroAreaInserir.setUltimaAlteracao(new Date());

			colecaoBairroArea.add(bairroAreaInserir);
		}

		sessao.setAttribute("colecaoBairroArea", colecaoBairroArea);
		httpServletRequest.setAttribute("fechaPopup", "true");

		return retorno;
	}
}
