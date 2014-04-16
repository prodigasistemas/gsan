package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * [UC0536]FILTRAR FERIADO
 * 
 * @author Kássia Albuquerque
 * @date 19/01/2006
 */



public class ExibirFiltrarFeriadoAction extends GcomAction{
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("filtrarFeriado");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltrarFeriadoActionForm form = (FiltrarFeriadoActionForm) actionForm;
		
		if (httpServletRequest.getParameter("menu") != null) {
			form.setIndicadorTipoFeriado("3");
			sessao.setAttribute("indicadorAtualizar", "1");
		}
		
		// -- [UC0536]FILTRAR FERIADO
		// -- [FS0001] Verificar Existência do Municipio --
		
		
		
		if ((form.getIdMunicipio() != null && !form.getIdMunicipio().equals(""))) {
		
			FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();
		
			filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID, 
					form.getIdMunicipio()));
		
			Collection colecaoMunicipio = fachada.pesquisar(filtroTabelaAuxiliarAbreviada,Municipio.class.getName());
		
			if (colecaoMunicipio != null && !colecaoMunicipio.isEmpty()) {
				
				Municipio municipio = (Municipio) colecaoMunicipio.iterator().next();
				form.setDescricaoMunicipio(municipio.getNome());
				
			} else {
				httpServletRequest.setAttribute("municipioEncontrado", "exception");
				form.setIdMunicipio("");
				form.setDescricaoMunicipio("MUNICIPIO INEXISTENTE");
			}
		
		}
		if (httpServletRequest.getParameter("menu")!= null && !httpServletRequest.getParameter("menu").equals("")){
			httpServletRequest.setAttribute("nomeCampo", "codigoFeriado"); 
		}
		return retorno;
	}
}
