package gcom.gui.relatorio.cobranca;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.gui.cadastro.imovel.ConsultarImovelActionForm;
import gcom.gui.cobranca.ConsultarDebitoClienteActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioConsultarDebitos;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRelatorioDebitosConsultarAction extends ExibidorProcessamentoTarefaRelatorio {

	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;

		HttpSession sessao = httpServletRequest.getSession(false);

		String pesquisaCliente = httpServletRequest.getParameter("pesquisaCliente");
		String relatorioEndereco = httpServletRequest.getParameter("relatorioEndereco");

		Collection colecaoContaValores = new ArrayList();
		Collection colecaoDebitoACobrar = new ArrayList();
		Collection colecaoCreditoARealizar = new ArrayList();
		Collection colecaoGuiasPagamento = new ArrayList();
		String valorTotalDebitos = null;
		String valorTotalDebitosAtualizado = null;

		if (sessao.getAttribute("colecaoContaValores") != null) {
			colecaoContaValores = (Collection) sessao.getAttribute("colecaoContaValores");
		}

		if (sessao.getAttribute("colecaoDebitoACobrar") != null) {
			colecaoDebitoACobrar = (Collection) sessao.getAttribute("colecaoDebitoACobrar");
		}

		if (sessao.getAttribute("colecaoCreditoARealizar") != null) {
			colecaoCreditoARealizar = (Collection) sessao.getAttribute("colecaoCreditoARealizar");
		}

		if (sessao.getAttribute("colecaoGuiaPagamentoValores") != null) {
			colecaoGuiasPagamento = (Collection) sessao.getAttribute("colecaoGuiaPagamentoValores");
		}

		if (sessao.getAttribute("valorTotalSemAcrescimo") != null) {
			valorTotalDebitos = (String) sessao.getAttribute("valorTotalSemAcrescimo");
		}

		if (sessao.getAttribute("valorTotalComAcrescimo") != null) {
			valorTotalDebitosAtualizado = (String) sessao.getAttribute("valorTotalComAcrescimo");
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioConsultarDebitos relatorio = new RelatorioConsultarDebitos((Usuario) (httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		relatorio.addParametro("colecaoContaValores", colecaoContaValores);
		relatorio.addParametro("colecaoDebitoACobrar", colecaoDebitoACobrar);
		relatorio.addParametro("colecaoCreditoARealizar", colecaoCreditoARealizar);
		relatorio.addParametro("colecaoGuiasPagamento", colecaoGuiasPagamento);
		
		if (actionForm instanceof ConsultarImovelActionForm) {

			ConsultarImovelActionForm form = (ConsultarImovelActionForm) actionForm;

			String endereco = getFachada().pesquisarEndereco(new Integer(form.getIdImovelDebitos()));

			Cliente cliente = getFachada().pesquisarClienteUsuarioImovelExcluidoOuNao(new Integer(form.getIdImovelDebitos()));

			relatorio.addParametro("idImovel", form.getIdImovelDebitos());
			relatorio.addParametro("inscricao", form.getMatriculaImovelDebitos());
			relatorio.addParametro("endereco", endereco);
			String cpfCnpjCliente = "";

			if (cliente != null) {
				relatorio.addParametro("clienteUsuario", cliente.getNome());
				if (cliente.getCnpj() != null) {
					cpfCnpjCliente = cliente.getCnpjFormatado();
				} else if (cliente.getCpf() != null) {
					cpfCnpjCliente = cliente.getCpfFormatado();
				}
			} else {
				relatorio.addParametro("clienteUsuario", "");
			}

			relatorio.addParametro("cpfCnpjCliente", cpfCnpjCliente);

		} else {
			ConsultarDebitoClienteActionForm form = (ConsultarDebitoClienteActionForm) actionForm;
			ClienteRelacaoTipo tipoRelacao = (ClienteRelacaoTipo) sessao.getAttribute("tipoRelacao");

			if (form.getCodigoCliente() != null && !form.getCodigoCliente().trim().equals("")) {
				relatorio.addParametro("codigoCliente", form.getCodigoCliente());
				relatorio.addParametro("nomeCliente", form.getNomeCliente());
				relatorio.addParametro("cpfCnpj", form.getCpfCnpj());
				relatorio.addParametro("tipoRelacao", tipoRelacao);
			} else {
				relatorio.addParametro("codigoCliente", form.getCodigoClienteSuperior());
				relatorio.addParametro("nomeCliente", form.getNomeClienteSuperior());
				relatorio.addParametro("cpfCnpj", form.getCpfCnpj());
				relatorio.addParametro("tipoRelacao", null);
			}

			relatorio.addParametro("periodoReferenciaInicial", form.getReferenciaInicial());
			relatorio.addParametro("periodoReferenciaFinal", form.getReferenciaFinal());
			relatorio.addParametro("dataVencimentoInicial", form.getDataVencimentoInicial());
			relatorio.addParametro("dataVencimentoFinal", form.getDataVencimentoFinal());
		}

		relatorio.addParametro("pesquisaCliente", pesquisaCliente);
		relatorio.addParametro("relatorioEndereco", relatorioEndereco);
		relatorio.addParametro("valorTotalDebitos", valorTotalDebitos);
		relatorio.addParametro("valorTotalDebitosAtualizado", valorTotalDebitosAtualizado);

		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));

		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, httpServletResponse, actionMapping);

		return retorno;
	}
}
