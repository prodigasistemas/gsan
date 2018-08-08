package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.Devolucao;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.bean.InserirPagamentoViaCanetaHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

public class InserirPagamentosAction extends GcomAction {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		HttpSession sessao = request.getSession(false);
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		DynaValidatorActionForm form = (DynaValidatorActionForm) actionForm;

		Integer idPagamento = null;
		String tipoInclusao = (String) form.get("tipoInclusao");
		AvisoBancario avisoBancario = pesquisarAviso(form);

		if (tipoInclusao.equalsIgnoreCase("manual")) {
			
			Collection<Pagamento> pagamentos = (Collection<Pagamento>) sessao.getAttribute("colecaoPagamento");
			idPagamento = getFachada().inserirPagamentos(pagamentos, usuarioLogado, avisoBancario);

		} else if (tipoInclusao.equalsIgnoreCase("caneta") || tipoInclusao.equalsIgnoreCase("ficha")) {
			
			Collection<Pagamento> pagamentos = new ArrayList<Pagamento>();
			Collection<Devolucao> devolucoes = new ArrayList<Devolucao>();

			Collection<InserirPagamentoViaCanetaHelper> helper = (Collection<InserirPagamentoViaCanetaHelper>) sessao.getAttribute("colecaoInserirPagamentoViaCanetaHelper");

			if (helper == null || helper.isEmpty()) {
				throw new ActionServletException("atencao.documento_naoinformado");
			}

			if (avisoBancario.getValorArrecadacaoCalculado().compareTo(avisoBancario.getValorArrecadacaoInformado()) == 1) {
				throw new ActionServletException("atencao.soma_dos_valores_maior_informado");
			}

			for (InserirPagamentoViaCanetaHelper pagamentoViaCanetaHelper : helper) {
				pagamentos.addAll(pagamentoViaCanetaHelper.getColecaoPagamento());

				if (pagamentoViaCanetaHelper.getColecaoDevolucao() != null && !pagamentoViaCanetaHelper.getColecaoDevolucao().isEmpty()) {
					devolucoes.addAll(pagamentoViaCanetaHelper.getColecaoDevolucao());
				}
			}

			idPagamento = getFachada().inserirPagamentosCodigoBarras(pagamentos, devolucoes, usuarioLogado, avisoBancario);
		} else {
			throw new ActionServletException("atencao.naoinformado", null, "Tipo de Inclusão");
		}

		sessao.removeAttribute("colecaoFormaArrecadacao");
		sessao.removeAttribute("PagamentoActionForm");
		sessao.removeAttribute("colecaoDocumentoTipo");
		sessao.removeAttribute("colecaoPagamentos");
		sessao.removeAttribute("colecaoInserirPagamentoViaCanetaHelper");

		montarPaginaSucesso(request, "Pagamento inserido com sucesso.", 
				"Inserir outro Pagamento", 
				"exibirInserirPagamentosAction.do?menu=sim", 
				"exibirAtualizarPagamentosAction.do?idRegistroInseridoAtualizar=" + idPagamento, 
				"Atualizar Pagamento Inserido");

		return mapping.findForward("telaSucesso");
	}

	private AvisoBancario pesquisarAviso(DynaValidatorActionForm pagamentoActionForm) {
		String idAvisoBancario = (String) pagamentoActionForm.get("idAvisoBancario");
		Filtro filtro = new FiltroAvisoBancario();
		filtro.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);

		return (AvisoBancario) (getFachada().pesquisar(filtro, AvisoBancario.class.getName())).iterator().next();
	}
}
