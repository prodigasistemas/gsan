package gcom.gui.micromedicao;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.bean.InformarSubdivisoesDeRotaHelper;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @see gcom.gui.micromedicao.InformarSubdivisoesDeRotaAction
 * @see gcom.gui.micromedicao.InformarSubdivisoesDeRotaActionForm
 * @see gcom.gui.micromedicao.ExibirInformarSubdivisoesDeRotaAction
 * 
 * @author Victor Cisneiros
 * @date 28/09/2008
 */
public class InformarSubdivisoesDeRotaAction extends GcomAction {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("telaSucesso");
		HttpSession session = request.getSession();
		Fachada fachada = Fachada.getInstancia();
		InformarSubdivisoesDeRotaActionForm form = (InformarSubdivisoesDeRotaActionForm) actionForm;
		
		List<InformarSubdivisoesDeRotaActionForm> subdivisoes = 
			(List<InformarSubdivisoesDeRotaActionForm>) session.getAttribute("subdivisoesDeRota");
		
		if (subdivisoes == null || subdivisoes.size() == 0) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Subdivisoes de Rota");
		}
		
		List<InformarSubdivisoesDeRotaHelper> helpers = new ArrayList<InformarSubdivisoesDeRotaHelper>();
		for (InformarSubdivisoesDeRotaActionForm subdivisao : subdivisoes) {
			InformarSubdivisoesDeRotaHelper helper = new InformarSubdivisoesDeRotaHelper();
			helper.setIdRota(new Integer(subdivisao.getIdRota()));
			helper.setCodigoRota(new Short(subdivisao.getCodigoRota()));
			helper.setQuadraInicial(new Integer(subdivisao.getQuadraInicial()));
			helper.setQuadraFinal(new Integer(subdivisao.getQuadraFinal()));
			
			helper.setIndicadorTransmissaoOffline(ConstantesSistema.NAO);
			
			if(subdivisao.getIdLeiturista() != null && !subdivisao.getIdLeiturista().equals("")){
			  helper.setIdLeiturista(new Integer(subdivisao.getIdLeiturista()));
			}else{
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio",null,"Indicador Sequencial de Leitura");
			}
			helpers.add(helper);
		}
		
		for (int i = 0; i < helpers.size() -1; i++) {
			helpers.get(i).setQuadraFinal(helpers.get(i +1).getQuadraInicial() -1);
		}
		
		InformarSubdivisoesDeRotaHelper rotaOriginal = helpers.remove(0);
		rotaOriginal.setQuadraInicial(1);
		
		if (helpers.size() > 0) {
			helpers.get(helpers.size() -1).setQuadraFinal(9999);
		}
		
		Integer idRotaOriginal = new Integer(form.getIdRota());
		
		fachada.informarSubdivisoesDeRota(idRotaOriginal, rotaOriginal, helpers);
		
		montarPaginaSucesso(request, "Subdivisões da Rota de código " + 
				idRotaOriginal + " realizadas com sucesso.", "Informar outra subdivisao",
				"exibirInformarSubdivisoesDeRotaAction.do?menu=sim");

		return retorno;
	}

}
