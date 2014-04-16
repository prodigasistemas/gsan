package gcom.gui.faturamento;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0596] Inserir Qualidade de Agua
 * 
 * @author Kássia Albuquerque, Rômulo Aurélio
 * @date 24/07/2007 , 16/09/2008
 */
public class InserirQualidadeAguaDadosAction extends GcomAction {
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

		ActionForward retorno = null;

		InserirQualidadeAguaActionForm form = (InserirQualidadeAguaActionForm) actionForm;

		// [FS0001 - VALIDAR REFERÊNCIA]
		if ((form.getReferencia() != null && !form.getReferencia().equals(""))) {

			String anoMesReferencia = Util.formatarMesAnoParaAnoMesSemBarra(form.getReferencia());
			
			SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
			String anoMesFuturo = 
				""+Util.somaUmMesAnoMesReferencia(sistemaParametro.getAnoMesFaturamento());
			
			if (Util.compararAnoMesReferencia(anoMesReferencia,anoMesFuturo,">")){
				httpServletRequest.setAttribute("nomeCampo", "referencia");	
				throw new ActionServletException("atencao.mes_ano_menor");
			} 

		}

		// Setor Comercial

		if (form.getIdSetorComercial() != null
				&& !form.getIdSetorComercial().toString().trim()
						.equalsIgnoreCase("")) {
			if (form.getIdSetorComercial() != null
					&& !form.getIdLocalidade().toString().trim()
							.equalsIgnoreCase("")) {

				FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();

				filtroSetorComercial.limparListaParametros();

				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.ID_LOCALIDADE, new Integer(form
								.getIdLocalidade())));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(
						FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						new Integer(form.getIdSetorComercial())));

				Collection colecaoSetorComerciais = this.getFachada()
						.pesquisar(filtroSetorComercial,
								SetorComercial.class.getName());

				if (colecaoSetorComerciais == null
						|| colecaoSetorComerciais.isEmpty()) {
					throw new ActionServletException(
							"atencao.setor_comercial.inexistente");
				}

			}
		}

		// Verifica qual é o próximo passo para a execução do processo
		// String destinoPagina = httpServletRequest.getParameter("destino");

		return retorno;

	}

}
