package gcom.gui.cobranca;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarArquivoTextoContasCobrancaEmpresaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
		GerarArquivoTextoContasCobrancaEmpresaActionForm form = (GerarArquivoTextoContasCobrancaEmpresaActionForm) actionForm;

		Integer[] idsRegistros = form.getIdRegistros();
		Collection<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < idsRegistros.length; i++) {
			ids.add(idsRegistros[i]);
		}

		getFachada().inserirProcessoIniciadoContasCobranca(ids, new Integer(form.getIdEmpresa()), (Usuario) request.getSession(false).getAttribute("usuarioLogado"));

		montarPaginaSucesso(request, "Arquivo Texto Contas Cobrança por Empresa Enviado para Processamento", "Voltar", "exibirGerarArquivoTextoContasCobrancaEmpresaAction.do");

		return mapping.findForward("telaSucesso");
	}
}