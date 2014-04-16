package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;
import gcom.util.tabelaauxiliar.abreviada.FiltroTabelaAuxiliarAbreviada;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0534]	INSERIR FERIADO
 * 
 * @author Kássia Albuquerque
 * @date 12/01/2007
 */
 
public class ExibirInserirFeriadoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		
		ActionForward retorno = actionMapping.findForward("inserirFeriado");	
		
		Fachada fachada = Fachada.getInstancia();
		
		InserirFeriadoActionForm form = (InserirFeriadoActionForm) actionForm;
		
		if (httpServletRequest.getParameter("menu") != null) {
			form.setIndicadorTipoFeriado("2");
		}
		
		// -- [UC0534]	INSERIR FERIADO
		// -- [FS0002]  Verificar Existência do Municipio --
		
		
		
		if ((form.getIdMunicipio() != null && !form.getIdMunicipio().equals(""))) {

			FiltroTabelaAuxiliarAbreviada filtroTabelaAuxiliarAbreviada = new FiltroTabelaAuxiliarAbreviada();

			filtroTabelaAuxiliarAbreviada.adicionarParametro(new ParametroSimples(FiltroTabelaAuxiliarAbreviada.ID, form.getIdMunicipio()));

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

		
		return retorno;
	}
}
