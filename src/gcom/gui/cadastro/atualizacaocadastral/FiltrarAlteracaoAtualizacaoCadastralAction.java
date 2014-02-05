package gcom.gui.cadastro.atualizacaocadastral;

import gcom.cadastro.atualizacaocadastral.bean.ConsultarMovimentoAtualizacaoCadastralHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarAlteracaoAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		Fachada fachada = Fachada.getInstancia();

		ActionForward retorno = actionMapping.findForward("filtrarAlteracaoAtualizacaoCadastral");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltrarAlteracaoAtualizacaoCadastralActionForm form = (FiltrarAlteracaoAtualizacaoCadastralActionForm) actionForm;
        
		if (!existeParametroInformado(form)) {
			throw new ActionServletException("atencao.filtro.nenhum_parametro_informado");
		}
		
		FiltrarAlteracaoAtualizacaoCadastralActionHelper helper = new FiltrarAlteracaoAtualizacaoCadastralActionHelper(
				form.getIdEmpresa(), form.getIdLeiturista(), form.getExibirCampos(), form.getColunaImoveisSelecionados(),
				form.getIdLocalidadeInicial(), form.getCdSetorComercialInicial(), form.getCdRotaInicial(),
				form.getIdLocalidadeFinal(), form.getCdSetorComercialFinal(), form.getCdRotaFinal());
		
		Collection<ConsultarMovimentoAtualizacaoCadastralHelper> colecaoConsultarMovimentoAtualizacaoCadastralHelper = 
				fachada.pesquisarMovimentoAtualizacaoCadastral(helper);
        
        if( colecaoConsultarMovimentoAtualizacaoCadastralHelper == null || colecaoConsultarMovimentoAtualizacaoCadastralHelper.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", "exibirFiltrarAlteracaoAtualizacaoCadastralAction.do", null, new String[] {});
        }
        sessao.setAttribute("colecaoConsultarMovimentoAtualizacaoCadastralHelper",colecaoConsultarMovimentoAtualizacaoCadastralHelper);

		return retorno;
	}

	private boolean existeParametroInformado(FiltrarAlteracaoAtualizacaoCadastralActionForm form) {
		boolean peloMenosUmParametroInformado = false;

		if (form.getIdEmpresa() != null
				&& !form.getIdEmpresa().trim().equals(
						"" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;
		}

		if (form.getIdLeiturista() != null
				&& !form.getIdLeiturista().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (form.getIdLocalidadeInicial() != null
				&& !form.getIdLocalidadeInicial().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (form.getCdSetorComercialInicial() != null
				&& !form.getCdSetorComercialInicial().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (form.getCdRotaInicial() != null
				&& !form.getCdRotaInicial().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (form.getIdLocalidadeFinal() != null
				&& !form.getIdLocalidadeFinal().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (form.getCdSetorComercialFinal() != null
				&& !form.getCdSetorComercialFinal().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (form.getCdRotaFinal() != null && !form.getCdRotaFinal().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (form.getExibirCampos() != null
				&& !form.getExibirCampos().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		if (form.getColunaImoveisSelecionados() != null
				&& !form.getColunaImoveisSelecionados().equals("")) {
			peloMenosUmParametroInformado = true;
		}

		return peloMenosUmParametroInformado;
	}
}
