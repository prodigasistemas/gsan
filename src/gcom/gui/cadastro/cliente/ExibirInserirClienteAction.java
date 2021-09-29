package gcom.gui.cadastro.cliente;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.imovel.Imovel;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

/**
 * [UC0000] Inserir Cliente
 */
public class ExibirInserirClienteAction extends GcomAction {

	@SuppressWarnings("rawtypes")
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("inserirClienteNomeTipo");

		HttpSession sessao = request.getSession(false);
		sessao.removeAttribute("colecaoClienteFone");

		DynaValidatorForm form = (DynaValidatorForm) actionForm;

		// Guarda o endereco do Imovel para o caso em que o Inserir/Manter cliente é invocado pelo Inserir/Manter Imovel como PopUp
		if (sessao.getAttribute("colecaoEnderecos") != null) {
			Collection colecaoEndereco = (Collection) sessao.getAttribute("colecaoEnderecos");
			Object obj = (Object) colecaoEndereco.iterator().next();
			if (obj instanceof Imovel) {
				sessao.setAttribute("colecaoEnderecosImovel", sessao.getAttribute("colecaoEnderecos"));
			}
		}

		sessao.removeAttribute("colecaoEnderecos");
		sessao.removeAttribute("foneTipos");
		sessao.removeAttribute("municipios");
		sessao.removeAttribute("colecaoResponsavelSuperiores");
		sessao.removeAttribute("InserirEnderecoActionForm");
		sessao.removeAttribute("InserirClienteActionForm");
		sessao.removeAttribute("tipoPesquisaRetorno");

		// Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard("inserirClienteWizardAction", "inserirClienteAction",
				"cancelarInserirClienteAction", "exibirInserirClienteAction.do");
		
		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(1, "ClienteNomeTipoA.gif",
				"ClienteNomeTipoD.gif", "exibirInserirClienteNomeTipoAction", "inserirClienteNomeTipoAction"));
		
		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(2, "ClientePessoaA.gif",
				"ClientePessoaD.gif", "exibirInserirClientePessoaAction", "inserirClientePessoaAction"));
		
		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(3, "ClienteEnderecoA.gif",
				"ClienteEnderecoD.gif", "exibirInserirClienteEnderecoAction", "inserirClienteEnderecoAction"));
		
		statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(4, "ClienteTelefoneA.gif",
				"ClienteTelefoneD.gif", "exibirInserirClienteTelefoneAction", "inserirClienteTelefoneAction"));

		sessao.setAttribute("statusWizard", statusWizard);

		form.set("indicadorPessoaFisicaJuridica", ClienteTipo.INDICADOR_PESSOA_FISICA.toString());
		form.set("indicadorGeraFaturaAntecipada", ConstantesSistema.NAO.toString());

		if (request.getParameter("desfazer") != null) {
			form.set("codigoCliente", null);
			form.set("nome", null);
			form.set("nomeAbreviado", null);
			form.set("email", null);
			form.set("tipoPessoa", null);
			form.set("tipoPessoaAntes", null);
			form.set("cpf", null);
			form.set("rg", null);
			form.set("dataEmissao", null);
			form.set("idOrgaoExpedidor", null);
			form.set("idUnidadeFederacao", null);
			form.set("dataNascimento", null);
			form.set("idProfissao", null);
			form.set("idPessoaSexo", null);
			form.set("nomeMae", null);
			form.set("cnpj", null);
			form.set("idRamoAtividade", null);
			form.set("codigoClienteResponsavel", null);
			form.set("nomeClienteResponsavel", null);
			form.set("setorComercialOrigemCD", null);
			form.set("enderecoCorrespondenciaSelecao", null);
			form.set("ddd", null);
			form.set("idTipoTelefone", null);
			form.set("indicadorTelefonePadrao", null);
			form.set("ramal", null);
			form.set("contato", null);
			form.set("telefone", null);
			form.set("dddTelefone", null);
			form.set("botaoClicado", null);
			form.set("botaoAdicionar", null);
			form.set("botaoRemover", null);
			form.set("idMunicipio", null);
			form.set("descricaoMunicipio", null);
			form.set("idRegistroRemocao", null);
			form.set("textoSelecionado", null);
			form.set("idRegistrosRemocao", null);
			form.set("indicadorUso", null);
			form.set("indicadorAcrescimos", null);
			form.set("diaVencimento", null);
			form.set("indicadorVencimentoMesSeguinte", null);
			form.set("numeroNIS", null);
		}

		if (request.getParameter("idCliente") != null && !request.getParameter("idCliente").toString().equals("")) {
			pesquisarCliente(request, form);
		}

		// Recupera o action de retorno do inserir cliente exibido como um PopUp
		// **********************************************************************
		if (request.getParameter("PopUpInserirClienteRetorno") != null) {
			String actionRetorno = request.getParameter("PopUpInserirClienteRetorno");
			sessao.setAttribute("PopUpInserirClienteRetorno", actionRetorno);
		}

		if (request.getParameter("idClienteRelacaoTipo") != null) {
			sessao.setAttribute("idClienteRelacaoTipo", request.getParameter("idClienteRelacaoTipo"));
		}
		// **********************************************************************

		return retorno;
	}

	@SuppressWarnings("rawtypes")
	private void pesquisarCliente(HttpServletRequest request, DynaValidatorForm form) {
		Filtro filtro = new FiltroCliente();
		filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID, request.getParameter("idCliente")));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_TIPO);

		Collection clientes = getFachada().pesquisar(filtro, Cliente.class.getName());

		if (clientes != null && !clientes.isEmpty()) {
			Cliente cliente = (Cliente) clientes.iterator().next();
			form.set("nome", cliente.getNome());
			form.set("tipoPessoa", new Short(cliente.getClienteTipo().getId().toString()));
			form.set("indicadorPessoaFisicaJuridica", cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().toString());
			request.setAttribute("nome", cliente.getNome());
			request.setAttribute("tipoPessoa", new Short(cliente.getClienteTipo().getId().toString()));
			request.setAttribute("indicadorPessoaFisicaJuridica", cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica());
		}
	}
}
