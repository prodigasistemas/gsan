package gcom.gui.cadastro.imovel;

import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class ValidarLigacaoAguaEsgotoAction extends GcomAction {

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
	 *            Description of the Parameter*
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = null;
/*
		ImovelOutrosCriteriosActionForm imovelOutrosCriteriosActionForm = (ImovelOutrosCriteriosActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		boolean peloMenosUmParametroInformado = false;
		HttpSession session = httpServletRequest.getSession(false);
		
		FiltroClienteImovel filtroClienteImovel = (FiltroClienteImovel) session
				.getAttribute("filtroClienteImovel");

		// Inicio situacaoAgua
		String situacaoAgua = imovelOutrosCriteriosActionForm.getSituacaoAgua();
		if (situacaoAgua != null && !situacaoAgua.trim().equalsIgnoreCase(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			peloMenosUmParametroInformado = true;
			if(filtroClienteImovel == null){
				filtroClienteImovel = new FiltroClienteImovel();
			}
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_SITUACAOAGUA_ID, situacaoAgua));
		}
		// Fim situacaoAgua

		// Inicio situacaoLigacaoEsgoto
		String situacaoLigacaoEsgoto = imovelOutrosCriteriosActionForm
				.getSituacaoLigacaoEsgoto();
		if (situacaoLigacaoEsgoto != null
				&& !situacaoLigacaoEsgoto.trim().equalsIgnoreCase(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new ParametroSimples(
					FiltroClienteImovel.IMOVEL_LIGACAO_ESGOTO_SITUACAO_ID,
					situacaoLigacaoEsgoto));
		}
		// Fim situacaoLigacaoEsgoto

		// Inicio tipoMedicao
		String tipoMedicao = imovelOutrosCriteriosActionForm.getTipoMedicao();
		if (tipoMedicao != null && !tipoMedicao.trim().equalsIgnoreCase(ConstantesSistema.NUMERO_NAO_INFORMADO + "")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel
					.adicionarParametro(new ParametroSimples(
							FiltroClienteImovel.IMOVEL_MEDICAO_HISTORICOS_MEDICAO_TIPO_ID,
							tipoMedicao));
		}
		// Fim tipoMedicao

		//Imicio consumoMinimoEsgoto
		String consumoMinimoFixadoEsgotoInicial = imovelOutrosCriteriosActionForm
				.getConsumoMinimoFixadoEsgotoInicial();
		String consumoMinimoFixadoEsgotoFinal = imovelOutrosCriteriosActionForm	
				.getConsumoMinimoFixadoEsgotoFinal();
		if (consumoMinimoFixadoEsgotoInicial != null
				&& !consumoMinimoFixadoEsgotoInicial.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new Intervalo(
					FiltroClienteImovel.IMOVEL_LIGACAO_ESGOTO_CONSUMO_MINIMO,
					consumoMinimoFixadoEsgotoInicial, consumoMinimoFixadoEsgotoFinal));
		}
		//Fim consumoMinimoEsgoto

		//Imicio consumoMinimoAgua
		String consumoMinimoAguaInicial = imovelOutrosCriteriosActionForm
				.getConsumoMinimoFixadoEsgotoInicial();
		String consumoMinimoAguaFinal = imovelOutrosCriteriosActionForm	
				.getConsumoMinimoFixadoEsgotoFinal();
		if (consumoMinimoFixadoEsgotoInicial != null
				&& !consumoMinimoFixadoEsgotoInicial.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new Intervalo(
					FiltroClienteImovel.IMOVEL_LIGACAO_ESGOTO_CONSUMO_MINIMO,
					consumoMinimoFixadoEsgotoInicial, consumoMinimoFixadoEsgotoFinal));
		}
		//Fim consumoMinimoAgua

		//Imicio PercentualEsgoto
		String intervaloPercentualEsgotoInicial = imovelOutrosCriteriosActionForm
				.getIntervaloPercentualEsgotoInicial();
		String intervaloPercentualEsgotoFinal = imovelOutrosCriteriosActionForm	
				.getIntervaloPercentualEsgotoInicial();
		if (intervaloPercentualEsgotoInicial != null
				&& !intervaloPercentualEsgotoInicial.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new Intervalo(
					FiltroClienteImovel.IMOVEL_LIGACAO_ESGOTO_PERCENTUAL,
					intervaloPercentualEsgotoInicial, intervaloPercentualEsgotoFinal));
		}
		//Fim PercentualEsgoto	

		//Imicio intervaloMediaMininaHidrometro
		String intervaloMediaMinimaHidrometroInicio = imovelOutrosCriteriosActionForm
				.getIntervaloMediaMinimaHidrometroInicio();		
		String intervaloMediaMinimaHidrometroFinal = imovelOutrosCriteriosActionForm	
				.getIntervaloMediaMinimaHidrometroFinal();		
		if (intervaloMediaMinimaHidrometroInicio != null
				&& !intervaloMediaMinimaHidrometroInicio.trim().equalsIgnoreCase("")) {			
			peloMenosUmParametroInformado = true;
			filtroClienteImovel.adicionarParametro(new Intervalo(
					FiltroClienteImovel.IMOVEL_LIGACAO_ESGOTO_PERCENTUAL,
					intervaloMediaMinimaHidrometroInicio, intervaloMediaMinimaHidrometroFinal));
		}
		//Fim PercentualEsgoto
		*/
		
		//Imovel imovel = 

		
		return retorno;
	}
}
