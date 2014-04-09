package gcom.gui.micromedicao;





import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarLocalArmazenagemHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarLocalArmazenagemHidrometroActionForm atualizarLocalArmazenagemHidrometroActionForm = (AtualizarLocalArmazenagemHidrometroActionForm) actionForm;

		HidrometroLocalArmazenagem hidromedroLocalArmazenagem= (HidrometroLocalArmazenagem) sessao.getAttribute("atualizarLocalArmazenagemHidrometro");
		
		
		hidromedroLocalArmazenagem.setDescricao(atualizarLocalArmazenagemHidrometroActionForm.getDescricao());
		hidromedroLocalArmazenagem.setDescricaoAbreviada(atualizarLocalArmazenagemHidrometroActionForm.getDescricaoAbreviada());
		hidromedroLocalArmazenagem.setId(new Integer(atualizarLocalArmazenagemHidrometroActionForm.getId()));
		hidromedroLocalArmazenagem.setIndicadorUso(new Short (atualizarLocalArmazenagemHidrometroActionForm.getIndicadorUso()));
		hidromedroLocalArmazenagem.setIndicadorOficina(new Short (atualizarLocalArmazenagemHidrometroActionForm.getIndicadorOficina()));
		
		String idHidrometroLocalArmazenagem = atualizarLocalArmazenagemHidrometroActionForm.getId();		
        String descricaoHidrometroLocalArmazenagem = atualizarLocalArmazenagemHidrometroActionForm.getDescricao();
        String descricaoAbreviadaHidrometroLocalArmazenagem = atualizarLocalArmazenagemHidrometroActionForm.getDescricaoAbreviada();
        Short indicadorUsoHidrometroLocalArmazenagem  = atualizarLocalArmazenagemHidrometroActionForm.getIndicadorUso();
        Short indicadorOficinaHidrometroLocalArmazenagem  = atualizarLocalArmazenagemHidrometroActionForm.getIndicadorOficina();
        
		
        hidromedroLocalArmazenagem.setDescricao(descricaoHidrometroLocalArmazenagem);
        hidromedroLocalArmazenagem.setDescricaoAbreviada(descricaoAbreviadaHidrometroLocalArmazenagem);
        hidromedroLocalArmazenagem.setId(new Integer(idHidrometroLocalArmazenagem));
        hidromedroLocalArmazenagem.setIndicadorUso(new Short(indicadorUsoHidrometroLocalArmazenagem));
        hidromedroLocalArmazenagem.setIndicadorOficina(new Short(indicadorOficinaHidrometroLocalArmazenagem));
        
		
		fachada.atualizar(hidromedroLocalArmazenagem);

		
		montarPaginaSucesso(httpServletRequest, "Local de Armazenagem do Hidrômetro "
				+ descricaoHidrometroLocalArmazenagem + " atualizado com sucesso.",
				"Realizar outra Manutenção de Local de Armazenagem do Hidrômetro ",
				"exibirFiltrarLocalArmazenagemHidrometroAction.do?menu=sim");        
        
		return retorno;
	}
}
