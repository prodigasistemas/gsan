package gcom.gui.faturamento;

import java.util.Collection;
import java.util.List;

import gcom.atendimentopublico.AgenciaReguladora;
import gcom.cadastro.geografico.FiltroMunicipio;
import gcom.cadastro.geografico.Municipio;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarRelatorioAMAEAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioAMAE");
		HttpSession sessao = httpServletRequest.getSession(false);

		GerarRelatorioAMAEActionForm form = (GerarRelatorioAMAEActionForm) actionForm;
		// recupera a flag de limpar o form
		String limparForm = httpServletRequest.getParameter("limparForm");
		Fachada fachada = Fachada.getInstancia();
		String codigoDigitadoMunicipioEnter = null;

		
		if (limparForm != null && !limparForm.equals("")) {
			form.setIdAgenciaReguladora(null);
			form.setDescricaoAgenciaReguladora(null);
			form.setMesAno(null);
		} else {
			List<AgenciaReguladora> agencias = fachada.obterAgenciasReguladorasAtivas();
			sessao.setAttribute("colecaoAgencias", agencias);
		}

		return retorno;
	}

}
