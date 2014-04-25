package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoRD;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de Filtrar Resolucao de Diretoria para Contrato pro Cliente
 * 
 * @author Paulo Diniz
 * @created 16/03/2011
 */
public class FiltrarResolucaoDiretoriaContratoParcelamentoAction extends
		GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma resolução de diretoria
	 * 
	 * [UC1134] Manter Resolução de Diretoria (RD) para Contratos de Parcelamento por cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obtém a instância da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("exibirManter");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarResolucaoDiretoriaContratoParcelamentoActionForm form = (FiltrarResolucaoDiretoriaContratoParcelamentoActionForm) actionForm;
		FiltroContratoParcelamentoRD filtroContratoParcelamento = new FiltroContratoParcelamentoRD(FiltroContratoParcelamentoRD.NUMERO);
		
		String numero = form.getNumero();
		String assunto = form.getAssunto();
		String dataVigenciaInicial = form.getDataVigenciaInicial();
		String dataVigenciaFinal = form.getDataVigenciaFinal();
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		boolean peloMenosUmParametroInformado = false;
		
		if(numero != null && !numero.equals("")){
			peloMenosUmParametroInformado = true;
		}
		
		if(assunto != null && !assunto.equals("")){
			peloMenosUmParametroInformado = true;
		}
		
		if(dataVigenciaInicial != null && !"".equals(dataVigenciaInicial)){
			peloMenosUmParametroInformado = true;
			
			if(dataVigenciaFinal == null || "".equals(dataVigenciaFinal)){
				dataVigenciaFinal = dataVigenciaInicial;
			}
		}
		
		//atencao.data.invalida
		if (!"".equals(dataVigenciaInicial) && !"".equals(dataVigenciaFinal)) {
			Date dataInicial = Util
					.converteStringParaDate(dataVigenciaInicial);
			Date dataFinal = Util.converteStringParaDate(dataVigenciaFinal);
			
			if(dataInicial == null || dataFinal == null){
				throw new ActionServletException(
						"atencao.data.invalida", null, "Data");
			}
			
			peloMenosUmParametroInformado = true;

			if (dataFinal.getTime() < dataInicial.getTime()) {
				throw new ActionServletException(
						"atencao.data.intervalo.invalido", null, Util
								.formatarData(new Date()));
			}
		}
		
		//Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		if (numero != null && !numero.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroContratoParcelamento.adicionarParametro(new ComparacaoTexto(
					FiltroContratoParcelamentoRD.NUMERO, numero.toUpperCase()));
		}
		
		if (assunto != null && !assunto.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			filtroContratoParcelamento.adicionarParametro(new ComparacaoTexto(
					FiltroContratoParcelamentoRD.ASSUNTO, assunto.toUpperCase()));
		}
		
		if (dataVigenciaInicial != null
				&& !dataVigenciaInicial.trim().equalsIgnoreCase("")) {
			Date dataVigenciaInicialConvertida = Util
					.converteStringParaDate(dataVigenciaInicial);
			peloMenosUmParametroInformado = true;
			filtroContratoParcelamento.adicionarParametro(new MaiorQue(
					FiltroContratoParcelamentoRD.DATA_VIGENCIA_INICIO,
					dataVigenciaInicialConvertida));
		}
		if (dataVigenciaFinal != null
				&& !dataVigenciaFinal.trim().equalsIgnoreCase("")) {
			Date dataVigenciaFinalConvertida = Util
					.converteStringParaDate(dataVigenciaFinal);
			peloMenosUmParametroInformado = true;
			filtroContratoParcelamento.adicionarParametro(new MenorQue(
					FiltroContratoParcelamentoRD.DATA_VIGENCIA_FIM,
					dataVigenciaFinalConvertida));
		}
		
		sessao.setAttribute("filtroContratoParcelamento", filtroContratoParcelamento);
		sessao.setAttribute("indicadorAtualizar",indicadorAtualizar);
		
		return retorno;
	}
		
}
