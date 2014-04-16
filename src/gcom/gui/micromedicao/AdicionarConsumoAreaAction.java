package gcom.gui.micromedicao;

import gcom.cadastro.imovel.Categoria;import gcom.cadastro.imovel.Subcategoria;import gcom.gui.ActionServletException;import gcom.gui.GcomAction;import gcom.micromedicao.ConsumoMinimoArea;import gcom.util.ConstantesSistema;import java.math.BigDecimal;import java.util.Collection;import java.util.Collections;import java.util.Comparator;import java.util.Date;import java.util.Iterator;import java.util.List;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import javax.servlet.http.HttpSession;import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurelio
 * @date 21/05/2008
 */
public class AdicionarConsumoAreaAction extends GcomAction {
	/**
	 * Este caso de uso permite a inclusão ou alteração de um novo consumo pela
	 * área
	 * 
	 * [UC0781] Informar Consumo por Área
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 12/05/2008
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

		ActionForward retorno = actionMapping
				.findForward("exibirAdicionarConsumoArea");

		HttpSession sessao = httpServletRequest.getSession(false);

		AdicionarConsumoAreaActionForm form = (AdicionarConsumoAreaActionForm) actionForm;

		String numeroConsumo = form.getNumeroConsumo();

		String numeroAreaFormatado = form.getNumeroArea().replace(".", "")
				.replace(",", ".");

		BigDecimal numeroArea = new BigDecimal(numeroAreaFormatado);
		
		Collection<ConsumoMinimoArea> colecaoConsumoMinimoAreaAux = (Collection) sessao
		.getAttribute("colecaoConsumoMinimoArea");

		if (sessao.getAttribute("colecaoConsumoMinimoArea") != null) {

			Collection<ConsumoMinimoArea> colecaoConsumoMinimoArea = (Collection) sessao
					.getAttribute("colecaoConsumoMinimoArea");

			if (httpServletRequest.getParameter("adicionar") != null) {

				String ano = form.getMesAnoReferencia().substring(3, 7);

				String mes = form.getMesAnoReferencia().substring(0, 2);

				String anoMesInformado = ano + mes;

				String idCategoria = (String) sessao
						.getAttribute("idCategoria");

				String idSubCategoria = (String) sessao
						.getAttribute("idSubCategoria");

				ConsumoMinimoArea consumoMinimoArea = new ConsumoMinimoArea();

				consumoMinimoArea.setAnoMesReferencia((new Integer(
						anoMesInformado)).intValue());

				Categoria categoria = new Categoria();

				categoria.setId(new Integer(idCategoria));

				consumoMinimoArea.setCategoria(categoria);

				Subcategoria subcategoria = new Subcategoria();

				subcategoria.setId(new Integer(idSubCategoria));

				consumoMinimoArea.setSubCategoria(subcategoria);

				consumoMinimoArea.setNumeroConsumo(new Integer(numeroConsumo));

				consumoMinimoArea.setNumeroAreaFinal(numeroArea);

				consumoMinimoArea
						.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

				consumoMinimoArea.setUltimaAlteracao(new Date());

				/**
				 * [FS0006]- Validar área igual
				 */

				Iterator colecaoConsumoMinimoAreaAuxIterator = colecaoConsumoMinimoAreaAux
				.iterator();

					
					while (colecaoConsumoMinimoAreaAuxIterator.hasNext()) {

					
					ConsumoMinimoArea consumoMinimoAreaAux = (ConsumoMinimoArea) colecaoConsumoMinimoAreaAuxIterator
							.next();

					if (consumoMinimoArea.getAnoMesReferencia() == consumoMinimoAreaAux
							.getAnoMesReferencia()
							&& consumoMinimoArea.getCategoria().getId()
									.intValue() == consumoMinimoAreaAux
									.getCategoria().getId().intValue()
							&& consumoMinimoArea.getSubCategoria().getId()
									.intValue() == consumoMinimoAreaAux
									.getSubCategoria().getId().intValue()
							&& consumoMinimoArea.getNumeroAreaFinal()
									.compareTo(
											consumoMinimoAreaAux
													.getNumeroAreaFinal()) == 0) {
						throw new ActionServletException(
								"atencao.area_ja_informada", form
										.getNumeroArea(), form
										.getMesAnoReferencia(), form
										.getCategoria(), form.getSubCategoria());

					}

				}

				/**
				 * FIM ---[FS0006]- Validar área igual
				 */

				colecaoConsumoMinimoArea.add(consumoMinimoArea);

				sessao.setAttribute("colecaoConsumoMinimoArea",
						colecaoConsumoMinimoArea);

			} else {// atualizar

				int posicaoComponente;

				if (sessao.getAttribute("posicaoComponente") != null) {

					posicaoComponente = (Integer) sessao
							.getAttribute("posicaoComponente");

				} else {

					posicaoComponente = 0;
				}
				//sessao.removeAttribute("posicaoComponente");

				int index = 0;

				

				Iterator colecaoConsumoMinimoAreaIterator = colecaoConsumoMinimoArea
						.iterator();

				Iterator colecaoConsumoMinimoAreaAuxIterator = colecaoConsumoMinimoAreaAux
				.iterator();

				while (colecaoConsumoMinimoAreaIterator.hasNext()) {

					index++;

					ConsumoMinimoArea consumoMinimoArea = (ConsumoMinimoArea) colecaoConsumoMinimoAreaIterator
							.next();

					if (index == posicaoComponente) {

						consumoMinimoArea.setNumeroConsumo(new Integer(numeroConsumo));

						consumoMinimoArea.setNumeroAreaFinal(numeroArea);

						consumoMinimoArea
								.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
						while (colecaoConsumoMinimoAreaAuxIterator.hasNext()) {

							ConsumoMinimoArea consumoMinimoAreaAux = (ConsumoMinimoArea) colecaoConsumoMinimoAreaAuxIterator
									.next();

							if (consumoMinimoArea.getId().intValue() != consumoMinimoAreaAux
									.getId().intValue()
									&& consumoMinimoArea.getAnoMesReferencia() == consumoMinimoAreaAux
											.getAnoMesReferencia()
									&& consumoMinimoArea.getCategoria().getId()
											.intValue() == consumoMinimoAreaAux
											.getCategoria().getId().intValue()
									&& consumoMinimoArea.getSubCategoria()
											.getId().intValue() == consumoMinimoAreaAux
											.getSubCategoria().getId()
											.intValue()
									&& consumoMinimoArea
											.getNumeroAreaFinal()
											.compareTo(
													consumoMinimoAreaAux
															.getNumeroAreaFinal()) == 0) {
								throw new ActionServletException(
										"atencao.area_ja_informada", form
												.getNumeroArea(), form
												.getMesAnoReferencia(), form
												.getCategoria(), form
												.getSubCategoria());

							}

						}
						
						Collections.sort((List) colecaoConsumoMinimoArea, new Comparator() {
                            public int compare(Object a, Object b) {
                                    BigDecimal area1 = ((ConsumoMinimoArea) a).getNumeroAreaFinal();
                                    BigDecimal area2 = ((ConsumoMinimoArea) b).getNumeroAreaFinal();

                                    return area1.compareTo(area2);
                            }
						});


						

						sessao.setAttribute("colecaoConsumoMinimoArea",
								colecaoConsumoMinimoArea);

					}
				}
			}

			httpServletRequest.setAttribute("reload", true);

		}
		sessao.setAttribute("adicionar", true);
		sessao.setAttribute("informar", true);
		return retorno;
	}
}
