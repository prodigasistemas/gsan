package gcom.gui.cobranca;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInserirResolucaoDiretoriaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("exibirInserirResolucaoDiretoria");

		InserirResolucaoDiretoriaActionForm form = (InserirResolucaoDiretoriaActionForm) actionForm;

		form.setIndicadorParcelamentoUnico(ConstantesSistema.SIM.toString());
		form.setIndicadorUtilizacaoLivre(ConstantesSistema.SIM.toString());
		form.setIndicadorDescontoFaixaReferenciaConta(ConstantesSistema.NAO.toString());
		form.setIndicadorDescontoSancoes(ConstantesSistema.SIM.toString());
		form.setIndicadorParcelasEmAtraso(ConstantesSistema.NAO.toString());
		form.setIndicadorParcelamentoEmAndamento(ConstantesSistema.NAO.toString());
		form.setIndicadorNegociacaoSoAVista(ConstantesSistema.NAO.toString());
		form.setIndicadorDescontoSoEmContaAVista(ConstantesSistema.NAO.toString());
		form.setIndicadorParcelamentoLojaVirtual(ConstantesSistema.NAO.toString());

		return retorno;

	}

}
