package gcom.gui.faturamento;

import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Subcategoria;
import gcom.faturamento.ConsumoMinimoParametro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AdicionarConsumoParametroAction extends GcomAction {

	/**
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirAdicionarConsumoParametro");

		HttpSession sessao = httpServletRequest.getSession(false);

		AdicionarConsumoParametroActionForm form = (AdicionarConsumoParametroActionForm) actionForm;

		String numeroConsumo = form.getNumeroConsumo();

		String numeroParametroFormatado = form.getNumeroParametro().replace(".", "")
				.replace(",", ".");

		BigDecimal numeroParametro = new BigDecimal(numeroParametroFormatado);

		List<ConsumoMinimoParametro> colecaoConsumoMinimoParametroAux = (List) sessao
			.getAttribute("colecaoConsumoMinimoParametro");

		if (sessao.getAttribute("colecaoConsumoMinimoParametro") != null) {

			List<ConsumoMinimoParametro> colecaoConsumoMinimoParametro = (List) sessao
					.getAttribute("colecaoConsumoMinimoParametro");

			if (httpServletRequest.getParameter("adicionar") != null) {

				String ano = form.getMesAnoReferencia().substring(3, 7);
				String mes = form.getMesAnoReferencia().substring(0, 2);
				String anoMesInformado = ano + mes;
				String idCategoria = (String) sessao.getAttribute("idCategoria");
				String idSubCategoria = null;
				
				if (sessao.getAttribute("idSubCategoria") != null
						&& !sessao.getAttribute("idSubCategoria").equals("")
						&& !sessao.getAttribute("idSubCategoria").equals("-1")) {
					idSubCategoria = (String) sessao.getAttribute("idSubCategoria");
				}

				ConsumoMinimoParametro consumoMinimoParametro = new ConsumoMinimoParametro();

				consumoMinimoParametro.setAnoMesReferencia((new Integer(
						anoMesInformado)).intValue());

				Categoria categoria = new Categoria();
				categoria.setId(new Integer(idCategoria));
				
				consumoMinimoParametro.setCategoria(categoria);
				
				if (idSubCategoria != null) {
					Subcategoria subcategoria = new Subcategoria();
					subcategoria.setId(new Integer(idSubCategoria));
					consumoMinimoParametro.setSubCategoria(subcategoria);
				}
				
				consumoMinimoParametro.setNumeroConsumo(new Integer(numeroConsumo));
				consumoMinimoParametro.setNumeroParametroFinal(numeroParametro);
				consumoMinimoParametro.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
				consumoMinimoParametro.setUltimaAlteracao(new Date());

				/**
				 * [FS0006] – Validar parâmetro igual 
				 */
				Iterator colecaoConsumoMinimoParametroAuxIterator = colecaoConsumoMinimoParametroAux.iterator();

					while (colecaoConsumoMinimoParametroAuxIterator.hasNext()) {

					ConsumoMinimoParametro consumoMinimoParametroAux = (ConsumoMinimoParametro) colecaoConsumoMinimoParametroAuxIterator
							.next();

					if (consumoMinimoParametro.getAnoMesReferencia() == consumoMinimoParametroAux.getAnoMesReferencia()
							&& consumoMinimoParametro.getCategoria().getId().intValue() == consumoMinimoParametroAux.getCategoria().getId().intValue()
							&& (consumoMinimoParametro.getSubCategoria() == null || consumoMinimoParametro.getSubCategoria().getId().intValue() == consumoMinimoParametroAux.getSubCategoria().getId().intValue())
							&& consumoMinimoParametro.getNumeroParametroFinal().compareTo(consumoMinimoParametroAux.getNumeroParametroFinal()) == 0) {

						if (consumoMinimoParametro.getSubCategoria() == null) {
							throw new ActionServletException(
									"atencao.parametro_ja_informado.sem_sub_categoria", form
											.getNumeroParametro(), form
											.getMesAnoReferencia(), form
											.getIdCategoria());
						} else {
							throw new ActionServletException(
									"atencao.parametro_ja_informado", form
											.getNumeroParametro(), form
											.getMesAnoReferencia(), form
											.getIdCategoria(), form.getIdSubCategoria());
						}
					}
				}
				/**
				 * FIM - [FS0006] – Validar parâmetro igual 
				 */

				colecaoConsumoMinimoParametro.add(consumoMinimoParametro);

				sessao.setAttribute("colecaoConsumoMinimoParametro",
						colecaoConsumoMinimoParametro);

			} else {// atualizar
				int posicaoComponente;
				
				if (sessao.getAttribute("posicaoComponente") != null) {

					posicaoComponente = (Integer) sessao
							.getAttribute("posicaoComponente");

				} else {
					posicaoComponente = 0;
				}

				ConsumoMinimoParametro consumoMinimoParametro = colecaoConsumoMinimoParametro.get(posicaoComponente - 1);

				consumoMinimoParametro.setNumeroConsumo(new Integer(numeroConsumo));
				consumoMinimoParametro.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

				for (int i = 0; i < colecaoConsumoMinimoParametro.size(); i++) {

					ConsumoMinimoParametro consumoMinimoParametroAux = (ConsumoMinimoParametro) colecaoConsumoMinimoParametro.get(i);

					if ((posicaoComponente - 1) != i
							&& consumoMinimoParametro.getAnoMesReferencia() == consumoMinimoParametroAux.getAnoMesReferencia()
							&& consumoMinimoParametro.getCategoria().getId().intValue() == consumoMinimoParametroAux.getCategoria().getId().intValue()
							&& (consumoMinimoParametro.getSubCategoria() == null || consumoMinimoParametro.getSubCategoria().getId().intValue() == consumoMinimoParametroAux.getSubCategoria().getId().intValue())
							&& numeroParametro.compareTo(consumoMinimoParametroAux.getNumeroParametroFinal()) == 0) {

						if (consumoMinimoParametro.getSubCategoria() == null) {
							throw new ActionServletException(
									"atencao.parametro_ja_informado.sem_sub_categoria", form
											.getNumeroParametro(), form
											.getMesAnoReferencia(), form
											.getIdCategoria());
						} else {
							throw new ActionServletException(
									"atencao.parametro_ja_informado", form
											.getNumeroParametro(), form
											.getMesAnoReferencia(), form
											.getIdCategoria(), form.getIdSubCategoria());
						}
					}
				}
				
				consumoMinimoParametro.setNumeroParametroFinal(numeroParametro);
				
				Collections.sort((List) colecaoConsumoMinimoParametro, new Comparator() {
                    public int compare(Object a, Object b) {
                            BigDecimal area1 = ((ConsumoMinimoParametro) a).getNumeroParametroFinal();
                            BigDecimal area2 = ((ConsumoMinimoParametro) b).getNumeroParametroFinal();
                            return area1.compareTo(area2);
                    }
				});

				sessao.setAttribute("colecaoConsumoMinimoParametro", colecaoConsumoMinimoParametro);
			}

			httpServletRequest.setAttribute("reload", true);

		}

		sessao.setAttribute("adicionar", true);

		sessao.setAttribute("informar", true);

		return retorno;

	}

}
