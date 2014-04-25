package gcom.gui.cobranca;

import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que faz a exibição da tela para o usuário setar os campos e permitir
 * que ele insera uma resolução de diretoria [UC0217] Inserir Resolução de
 * Diretoria
 * 
 * @author Rafael Corrêa
 * @since 30/03/2006
 */
public class ExibirInserirResolucaoDiretoriaAction extends GcomAction {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirResolucaoDiretoria");
		
		InserirResolucaoDiretoriaActionForm inserirResolucaoDiretoriaActionForm = (InserirResolucaoDiretoriaActionForm) actionForm;

		inserirResolucaoDiretoriaActionForm.setIndicadorParcelamentoUnico(ConstantesSistema.SIM.toString());
		inserirResolucaoDiretoriaActionForm.setIndicadorUtilizacaoLivre(ConstantesSistema.SIM.toString());
		inserirResolucaoDiretoriaActionForm.setIndicadorDescontoSancoes(ConstantesSistema.SIM.toString());
		inserirResolucaoDiretoriaActionForm.setIndicadorParcelasEmAtraso(ConstantesSistema.NAO.toString());
		inserirResolucaoDiretoriaActionForm.setIndicadorParcelamentoEmAndamento(ConstantesSistema.NAO.toString());
		inserirResolucaoDiretoriaActionForm.setIndicadorNegociacaoSoAVista(ConstantesSistema.NAO.toString());
		inserirResolucaoDiretoriaActionForm.setIndicadorDescontoSoEmContaAVista(ConstantesSistema.NAO.toString());
		inserirResolucaoDiretoriaActionForm.setIndicadorParcelamentoLojaVirtual(ConstantesSistema.NAO.toString());
		
		return retorno;

	}

}
