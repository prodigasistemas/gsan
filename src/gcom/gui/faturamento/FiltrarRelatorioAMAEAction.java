package gcom.gui.faturamento;

import java.util.Collection;
import java.util.List;

import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarRelatorioAMAEAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioAMAE");
		
		GerarRelatorioAMAEActionForm form = (GerarRelatorioAMAEActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		String codigoDigitadoMunicipioEnter = null;
		// Código do Município
		if (form.getCodigoMunicipio() != null) 
			codigoDigitadoMunicipioEnter = form.getCodigoMunicipio().toString();
		

		// Verifica se o código foi digitado
		if (codigoDigitadoMunicipioEnter != null
				&& !codigoDigitadoMunicipioEnter.trim().equals("")
				&& Integer.parseInt(codigoDigitadoMunicipioEnter) > 0) {
			FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID, codigoDigitadoMunicipioEnter));
			filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.INDICADOR_USO,ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection municipioEncontrado = fachada.pesquisar(filtroMunicipio,
					Municipio.class.getName());

			if (municipioEncontrado != null && !municipioEncontrado.isEmpty()) {
				// O municipio foi encontrado
				form.setCodigoMunicipio(((Municipio) ((List) municipioEncontrado).get(0)).getId());
				form.setDescricaoMunicipio(((Municipio) ((List) municipioEncontrado).get(0)).getNome());
//				form.set("codigoMunicipio", ""+ ((Municipio) ((List) municipioEncontrado).get(0)).getId());
//				form.set("descricaoMunicipioCliente",((Municipio) ((List) municipioEncontrado).get(0)).getNome());
			} else {
				form.setCodigoMunicipio(null);
				form.setDescricaoMunicipio("Município inexistente");
//				form.set("codigoMunicipio", "");
//				form.set("descricaoMunicipio","Município inexistente");
				httpServletRequest.setAttribute("municipioNaoEncontrado","exception");
			}
		}
		
		return retorno;
	}


}
