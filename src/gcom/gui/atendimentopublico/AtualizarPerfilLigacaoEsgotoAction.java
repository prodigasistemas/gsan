package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoPerfil;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarPerfilLigacaoEsgotoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarPerfilLigacaoEsgotoActionForm atualizarPerfilLigacaoEsgotoActionForm = (AtualizarPerfilLigacaoEsgotoActionForm) actionForm;

		LigacaoEsgotoPerfil ligacaoEsgotoPerfil= (LigacaoEsgotoPerfil) sessao.getAttribute("atualizarPerfilLigacaoEsgoto");
		
		ligacaoEsgotoPerfil.setId(new Integer(atualizarPerfilLigacaoEsgotoActionForm.getId()));
		ligacaoEsgotoPerfil.setDescricao(atualizarPerfilLigacaoEsgotoActionForm.getDescricao());
		ligacaoEsgotoPerfil.setIndicadorUso(new Short (atualizarPerfilLigacaoEsgotoActionForm.getIndicadorUso()));
		ligacaoEsgotoPerfil.setIndicadorPrincipal(new Short (atualizarPerfilLigacaoEsgotoActionForm.getIndicadorPrincipal()));
		ligacaoEsgotoPerfil.setPercentualEsgotoConsumidaColetada(Util.formatarMoedaRealparaBigDecimal(atualizarPerfilLigacaoEsgotoActionForm.getPercentualEsgotoConsumidaColetada()));
		
		String idLigEsg = atualizarPerfilLigacaoEsgotoActionForm.getId();		
        String descricaoLigEsg = atualizarPerfilLigacaoEsgotoActionForm.getDescricao();
        Short indicadorUsoLigEsg = atualizarPerfilLigacaoEsgotoActionForm.getIndicadorUso();
        Short indicadorPrincipalLigEsg = atualizarPerfilLigacaoEsgotoActionForm.getIndicadorPrincipal();
        ligacaoEsgotoPerfil.setDescricao(descricaoLigEsg);
        
        ligacaoEsgotoPerfil.setId(new Integer( idLigEsg));
        
        ligacaoEsgotoPerfil.setIndicadorUso(new Short(indicadorUsoLigEsg));

        ligacaoEsgotoPerfil.setIndicadorPrincipal(new Short(indicadorPrincipalLigEsg));
       
        ligacaoEsgotoPerfil.setPercentualEsgotoConsumidaColetada(Util.formatarMoedaRealparaBigDecimal(atualizarPerfilLigacaoEsgotoActionForm.getPercentualEsgotoConsumidaColetada()));
		
        
        
		fachada.atualizar(ligacaoEsgotoPerfil);

		montarPaginaSucesso(httpServletRequest, "Perfil da Ligação de Esgoto "
				+ ligacaoEsgotoPerfil.getDescricao()
				+ " atualizado com sucesso.",
				"Realizar outra Manutenção do Perfil da Ligação de Esgoto",
				"exibirFiltrarPerfilLigacaoEsgotoAction.do?menu=sim");

		return retorno;

	}
}
