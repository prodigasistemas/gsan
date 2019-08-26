package gcom.gui.cadastro;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConsultarArquivoTextoAtualizacaoCadastralAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessao = request.getSession(false);

		ConsultarArquivoTextoAtualizacaoCadastralActionForm form = (ConsultarArquivoTextoAtualizacaoCadastralActionForm) actionForm;

		sessao.removeAttribute("permissao");
		if (getUsuarioLogado(request).getEmpresa().getIndicadorEmpresaPrincipal().equals(ConstantesSistema.SIM)) {
			sessao.setAttribute("permissao", "1");
		} else {
			sessao.setAttribute("permissao", "2");
		}

		List<ArquivoTextoAtualizacaoCadastral> lista = getFachada().pesquisarArquivoTextoAtualizacaoCadastro(
				form.getIdEmpresa(),
				form.getIdLocalidade(), 
				form.getCodigoSetorComercial(), 
				form.getLeituristaID(), 
				form.getIdSituacaoTransmissao(),
				form.getExibicao());

		if (lista == null || lista.isEmpty()) {
			throw new ActionServletException("atencao.nenhum_arquivo_txt_atualizacao_cadastral");
		}

		sessao.setAttribute("colecaoArquivoTextoAtualizacaoCadastral", lista);
		definirSomatorioDeFaltantesETotal(request, lista);

		return mapping.findForward("consultarArquivoTextoAtualizacaoCadastral");
	}
	
	private void definirSomatorioDeFaltantesETotal(HttpServletRequest httpServletRequest, List<ArquivoTextoAtualizacaoCadastral> lista) {
		int quantidadeTotalFaltantes = 0;
		int quantidadeTotal = 0;
		
		for (ArquivoTextoAtualizacaoCadastral arquivoTextoAtualizacaoCadastral : lista) {
			quantidadeTotalFaltantes += arquivoTextoAtualizacaoCadastral.getQuantidadeImoveisFaltantes();
			quantidadeTotal += arquivoTextoAtualizacaoCadastral.getQuantidadeImovel();
		}
		
		httpServletRequest.setAttribute("quantidadeTotalFaltantes", quantidadeTotalFaltantes);
		httpServletRequest.setAttribute("quantidadeTotal", quantidadeTotal);
	}
}
