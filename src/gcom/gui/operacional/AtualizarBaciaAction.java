package gcom.gui.operacional;





import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.operacional.Bacia;

import gcom.operacional.SistemaEsgoto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarBaciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarBaciaActionForm atualizarBaciaActionForm = (AtualizarBaciaActionForm) actionForm;

		Bacia bacia= (Bacia) sessao.getAttribute("atualizarBacia");
		
		bacia.setId(new Integer(atualizarBaciaActionForm.getId()));
		bacia.setDescricao(atualizarBaciaActionForm.getDescricao());
		bacia.setIndicadorUso(new Short (atualizarBaciaActionForm.getIndicadorUso()));
		
		
		String idBacia = atualizarBaciaActionForm.getId();		
        String descricaoBacia= atualizarBaciaActionForm
        .getDescricao();
        Short indicadorUsoBacia = atualizarBaciaActionForm
        .getIndicadorUso();
        SistemaEsgoto sistemaEsgoto = new SistemaEsgoto();
        sistemaEsgoto.setId(new Integer(atualizarBaciaActionForm.getSistemaEsgoto()));
		bacia.setSistemaEsgoto(sistemaEsgoto);
        
		
        bacia.setDescricao(descricaoBacia);
        
        bacia.setId(new Integer( idBacia));
        
        bacia.setIndicadorUso(new Short(indicadorUsoBacia));

    
        
		
		fachada.atualizar(bacia);

		
		montarPaginaSucesso(httpServletRequest, "Bacia "
				+ descricaoBacia + " atualizado com sucesso.",
				"Realizar outra Manutenção de Bacia ",
				"exibirFiltrarBaciaAction.do?menu=sim");        
        
		return retorno;
	}
}
