package gcom.gui.faturamento;

import gcom.faturamento.QualidadeAgua;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarQualidadeAguaAnaliseAction extends GcomAction {

	/**
	 * Description of the Method
	 */
	/**
	 * < <Descrição do método>>
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

		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarQualidadeAguaAnaliseAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Integer entrada = (Integer)sessao.getAttribute("PrimeiraVez");
		
		if(entrada==1){
		
		QualidadeAgua qualidadeAgua = (QualidadeAgua) sessao.getAttribute("qualidadeAgua"); 
		
		AtualizarQualidadeAguaActionForm form = (AtualizarQualidadeAguaActionForm) actionForm;
		
		form.setQuantidadeTurbidezExigidas(qualidadeAgua
				.getQuantidadeTurbidezExigidas() != null ? qualidadeAgua
						.getQuantidadeTurbidezExigidas().toString() : "");
		
		form.setQuantidadeTurbidezAnalisadas(qualidadeAgua
				.getQuantidadeTurbidezAnalisadas() != null ? qualidadeAgua
				.getQuantidadeTurbidezAnalisadas().toString() : "");
		
		form.setQuantidadeTurbidezConforme(qualidadeAgua
				.getQuantidadeTurbidezConforme() != null ? qualidadeAgua
				.getQuantidadeTurbidezConforme().toString() : "");
		
		form.setQuantidadeCorExigidas(qualidadeAgua
				.getQuantidadeCorExigidas() != null ? qualidadeAgua
				.getQuantidadeCorExigidas().toString() : "");
		
		form.setQuantidadeCorAnalisadas(qualidadeAgua
				.getQuantidadeCorAnalisadas() != null ? qualidadeAgua
				.getQuantidadeCorAnalisadas().toString() : "");
		
		form.setQuantidadeCorConforme(qualidadeAgua
				.getQuantidadeCorConforme() != null ? qualidadeAgua
				.getQuantidadeCorConforme().toString() : "");
		
		form.setQuantidadeCloroExigidas(qualidadeAgua
				.getQuantidadeCloroExigidas() != null ? qualidadeAgua
				.getQuantidadeCloroExigidas().toString() : "");
		
		form.setQuantidadeCloroAnalisadas(qualidadeAgua
				.getQuantidadeCloroAnalisadas() != null ? qualidadeAgua
				.getQuantidadeCloroAnalisadas().toString() : "");
		
		form.setQuantidadeCloroConforme(qualidadeAgua
				.getQuantidadeCloroConforme() != null ? qualidadeAgua
				.getQuantidadeCloroConforme().toString() : "");
		
		form.setQuantidadeFluorExigidas(qualidadeAgua
				.getQuantidadeFluorExigidas() != null ? qualidadeAgua
				.getQuantidadeFluorExigidas().toString() : "");
		
		form.setQuantidadeFluorAnalisadas(qualidadeAgua
				.getQuantidadeFluorAnalisadas() != null ? qualidadeAgua
				.getQuantidadeFluorAnalisadas().toString() : "");
		
		form.setQuantidadeFluorConforme(qualidadeAgua
				.getQuantidadeFluorConforme() != null ? qualidadeAgua
				.getQuantidadeFluorConforme().toString() : "");
		
		form.setQuantidadeEColiExigidas(qualidadeAgua
				.getQuantidadeEColiExigidas() != null ? qualidadeAgua
				.getQuantidadeEColiExigidas().toString() : "");
		
		form.setQuantidadeEColiAnalisadas(qualidadeAgua
				.getQuantidadeEColiAnalisadas() != null ? qualidadeAgua
				.getQuantidadeEColiAnalisadas().toString() : "");
		
		form.setQuantidadeEColiConforme(qualidadeAgua
				.getQuantidadeEColiConforme() != null ? qualidadeAgua
				.getQuantidadeEColiConforme().toString() : "");
		
		form.setQuantidadeColiformesTotaisExigidas(qualidadeAgua
				.getQuantidadeColiformesTotaisExigidas() != null ? qualidadeAgua
				.getQuantidadeColiformesTotaisExigidas().toString() : "");
		
		form.setQuantidadeColiformesTotaisAnalisadas(qualidadeAgua
				.getQuantidadeColiformesTotaisAnalisadas() != null ? qualidadeAgua
				.getQuantidadeColiformesTotaisAnalisadas().toString() : "");
		
		form.setQuantidadeColiformesTotaisConforme(qualidadeAgua
				.getQuantidadeColiformesTotaisConforme() != null ? qualidadeAgua
				.getQuantidadeColiformesTotaisConforme().toString() : "");
		
		form.setQuantidadeColiformesFecaisExigidas(qualidadeAgua
				.getQuantidadeColiformesFecaisExigidas() != null ? qualidadeAgua
				.getQuantidadeColiformesFecaisExigidas().toString() : "");
		
		form.setQuantidadeColiformesFecaisAnalisadas(qualidadeAgua
				.getQuantidadeColiformesFecaisAnalisadas() != null ? qualidadeAgua
				.getQuantidadeColiformesFecaisAnalisadas().toString() : "");
		
		form.setQuantidadeColiformesFecaisConforme(qualidadeAgua
				.getQuantidadeColiformesFecaisConforme() != null ? qualidadeAgua
				.getQuantidadeColiformesFecaisConforme().toString() : "");
		
		form.setQuantidadeColiformesTermotolerantesExigidas(qualidadeAgua
				.getQuantidadeColiformesTermotolerantesExigidas() != null ? qualidadeAgua
				.getQuantidadeColiformesTermotolerantesExigidas().toString() : "");
		
		form.setQuantidadeColiformesTermotolerantesAnalisadas(qualidadeAgua
				.getQuantidadeColiformesTermotolerantesAnalisadas() != null ? qualidadeAgua
				.getQuantidadeColiformesTermotolerantesAnalisadas().toString() : "");
		
		form.setQuantidadeColiformesTermotolerantesConforme(qualidadeAgua
				.getQuantidadeColiformesTermotolerantesConforme() != null ? qualidadeAgua
				.getQuantidadeColiformesTermotolerantesConforme().toString() : "");
		
		form.setQuantidadeAlcalinidadeExigidas(qualidadeAgua
				.getQuantidadeAlcalinidadeExigidas() != null ? qualidadeAgua
				.getQuantidadeAlcalinidadeExigidas().toString() : "");
		
		form.setQuantidadeAlcalinidadeAnalisadas(qualidadeAgua
				.getQuantidadeAlcalinidadeAnalisadas() != null ? qualidadeAgua
				.getQuantidadeAlcalinidadeAnalisadas().toString() : "");
		
		form.setQuantidadeAlcalinidadeConforme(qualidadeAgua
				.getQuantidadeAlcalinidadeConforme() != null ? qualidadeAgua
				.getQuantidadeAlcalinidadeConforme().toString() : "");
		}
		return retorno;
		
		
	}

}
