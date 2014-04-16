package gcom.gui;

import gcom.cadastro.DbVersaoBase;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.util.Internacionalizador;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.Util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.PropertyMessageResources;

public class CarregarParametrosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaLogin");

		Fachada fachada = Fachada.getInstancia();

		// instacia a variavel de aplicacao tituloPagina com o valor cadastrado
		// em sistema parametro.
		if ( !Util.verificarNaoVazio((String)servlet.getServletContext().getAttribute("tituloPagina"))) {

			// Recupera o objeto que contém os parâmetros do sistema
			SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
			
			servlet.getServletContext().setAttribute("tituloPagina",sistemaParametro.getTituloPagina());
			servlet.getServletContext().setAttribute("logoMarca",sistemaParametro.getImagemLogomarca());
			servlet.getServletContext().setAttribute("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());

			if( Util.verificarNaoVazio(sistemaParametro.getUrlhelp())){
				servlet.getServletContext().setAttribute("urlHelp",sistemaParametro.getUrlhelp());
			}
			if( Util.verificarNaoVazio(sistemaParametro.getIndicadorSenhaForte().toString())){
				servlet.getServletContext().setAttribute("indicadorSenhaForte",sistemaParametro.getIndicadorSenhaForte().toString());
			}
		}

		if ( !Util.verificarNaoVazio((String)servlet.getServletContext().getAttribute("rodapePagina"))) {

			// Recupera o objeto que contém as datas de Implementacao e
			// alteracao do Banco			
			DbVersaoBase dbVersaoBase = fachada.pesquisarDbVersaoBase();

			if ( dbVersaoBase != null ) {

				String versaoDataBase = Util.formatarData(dbVersaoBase.getVersaoDataBase());

				servlet.getServletContext().setAttribute("versaoDataBase",versaoDataBase);
			}
			
			try {
				
				if (ServiceLocator.getResource("java:/BatchDS") != null) {
					
					servlet.getServletContext().setAttribute("versaoTipo", "Batch");
				}
				else{
					
					servlet.getServletContext().setAttribute("versaoTipo", "Online");
				}
			} 
			catch (ServiceLocatorException e) {
				
				e.printStackTrace();
			}
		}

		
		Internacionalizador.setLocale(
				(Locale)httpServletRequest.getSession(false).getAttribute(Globals.LOCALE_KEY));
		
		Internacionalizador.setProperties(
				(PropertyMessageResources)servlet.getServletContext().getAttribute(Globals.MESSAGES_KEY));

		return retorno;

	}
}
