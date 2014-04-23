package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroDiametro;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarHidrometroDiametroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarHidrometroDiametroActionForm atualizarHidrometroDiametroActionForm = (AtualizarHidrometroDiametroActionForm) actionForm;

		HidrometroDiametro hidrometroDiametro = (HidrometroDiametro) sessao.getAttribute("atualizarHidrometroDiametro");
		
		hidrometroDiametro.setDescricao(atualizarHidrometroDiametroActionForm.getDescricao());
		hidrometroDiametro.setDescricaoAbreviada(atualizarHidrometroDiametroActionForm.getDescricaoAbreviada());
		
		if(!atualizarHidrometroDiametroActionForm.getNumeroOrdem().equals("")){
			hidrometroDiametro.setNumeroOrdem(new Short (atualizarHidrometroDiametroActionForm.getNumeroOrdem()));
		}else{
			hidrometroDiametro.setNumeroOrdem(null);	
		}
		
		hidrometroDiametro.setIndicadorUso(new Short (atualizarHidrometroDiametroActionForm.getIndicadorUso()));

        String descricaoHidrometroDiametro = atualizarHidrometroDiametroActionForm
        .getDescricao();
        String descricaoAbreviadaHidrometroDiametro = atualizarHidrometroDiametroActionForm
        .getDescricaoAbreviada();    
        String numeroOrdem = atualizarHidrometroDiametroActionForm.getNumeroOrdem();
        String indicadordeUso = atualizarHidrometroDiametroActionForm
        .getIndicadorUso();
		
        hidrometroDiametro.setDescricao(descricaoHidrometroDiametro);
        hidrometroDiametro.setDescricaoAbreviada(descricaoAbreviadaHidrometroDiametro);
      	hidrometroDiametro.setNumeroOrdem( new Short(numeroOrdem));
        hidrometroDiametro.setUltimaAlteracao( new Date() );	
        hidrometroDiametro.setIndicadorUso( new Short(indicadordeUso));
		
		fachada.atualizar(hidrometroDiametro);

		montarPaginaSucesso(httpServletRequest, "Diâmetro do Hidrômetro "
				+ atualizarHidrometroDiametroActionForm.getId().toString() + " atualizado com sucesso.",
				"Realizar outra Manutenção de Diâmetro do Hidrômetro ",
				"exibirFiltrarHidrometroDiametroAction.do?menu=sim");        
        
		return retorno;
	}
}
