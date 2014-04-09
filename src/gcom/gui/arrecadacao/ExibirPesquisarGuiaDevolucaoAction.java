package gcom.gui.arrecadacao;

import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteFone;
import gcom.cadastro.cliente.IClienteFone;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.FiltroDocumentoTipo;
import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.faturamento.debito.DebitoCreditoSituacao;
import gcom.faturamento.debito.FiltroDebitoCreditoSituacao;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pesquisar Guia Devolucao - Exibir
 * 
 *  Action para Exibir a página de consulta de guias de devolução
 * 
 * @author Fernanda Paiva - 02/03/2006
 */
public class ExibirPesquisarGuiaDevolucaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("pesquisarGuiaDevolucao");

		// Instacia a fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarGuiaDevolucaoActionForm pesquisarGuiaDevolucaoActionForm = (PesquisarGuiaDevolucaoActionForm) actionForm;
		
		// Recupera os parametros
		String tela = httpServletRequest.getParameter("tela");
		String tipo = httpServletRequest.getParameter("tipo");
		
		if ((tipo != null && !tipo.equals("")) || (tela != null && !tela.equals("")))  {
			pesquisarGuiaDevolucaoActionForm.setCodigoCliente("");
			pesquisarGuiaDevolucaoActionForm.setNomeCliente("");
			pesquisarGuiaDevolucaoActionForm.setCodigoImovel("");
			pesquisarGuiaDevolucaoActionForm.setInscricaoImovel("");
			pesquisarGuiaDevolucaoActionForm.reset(actionMapping,httpServletRequest);

		}

		if(httpServletRequest.getParameter("objetoConsulta") == null
				&& httpServletRequest.getParameter("tipoConsulta") == null
				&& httpServletRequest.getParameter("voltarPesquisa") == null
				&& sessao.getAttribute("flag") == null)
		{
			pesquisarGuiaDevolucaoActionForm.setCodigoCliente("");
			pesquisarGuiaDevolucaoActionForm.setNomeCliente("");
			pesquisarGuiaDevolucaoActionForm.setCodigoImovel("");
			pesquisarGuiaDevolucaoActionForm.setInscricaoImovel("");
			pesquisarGuiaDevolucaoActionForm.reset(actionMapping,httpServletRequest);

			sessao.removeAttribute("caminhoRetornoTelaPesquisaCliente");
			sessao.removeAttribute("caminhoRetornoTelaPesquisaImovel");
			if (httpServletRequest.getParameter("novaPesquisa") == null
					|| httpServletRequest.getParameter("novaPesquisa").equals(
							"")) {
				sessao.removeAttribute("caminhoRetorno");
			}
			sessao.setAttribute("flag","1");
		}
		// Recupera os dados do formulário 
		String codigoCliente = (String) pesquisarGuiaDevolucaoActionForm.getCodigoCliente();
		String codigoImovel = (String) pesquisarGuiaDevolucaoActionForm.getCodigoImovel();
		
		//Verifica se o tipoConsulta é diferente de nulo ou vazio esse tipo
		// consulta vem do
		// localidade_resultado_pesquisa.jsp ou do
		// cliente_resultado_pesquisa.jsp ou do imovel_resultado_pesquisa.jsp.
		// É feita essa verificação pois pode ser que ainda não tenha
		// feito a pesquisa de cliente ou imovel.
		if (httpServletRequest.getParameter("tipoConsulta") != null
				&& !httpServletRequest.getParameter("tipoConsulta").equals("")) {
			// Verifica se o tipo da consulta de guia devolucao é de imovel
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina guia_devolucao_pesquisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta")
					.equals("imovel")) {

				pesquisarGuiaDevolucaoActionForm.setCodigoImovel(httpServletRequest
						.getParameter("idCampoEnviarDados"));

				pesquisarGuiaDevolucaoActionForm
						.setInscricaoImovel(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
				
				pesquisarGuiaDevolucaoActionForm.setCodigoCliente("");

				pesquisarGuiaDevolucaoActionForm
						.setNomeCliente("");

			}

			// Verifica se o tipo da consulta de arrecadador é de cliente
			// se for os parametros de enviarDadosParametros serão mandados para
			// a pagina arrecadador_pesuisar.jsp
			if (httpServletRequest.getParameter("tipoConsulta").equals(
					"cliente")) {

				pesquisarGuiaDevolucaoActionForm.setCodigoCliente(httpServletRequest
						.getParameter("idCampoEnviarDados"));
				pesquisarGuiaDevolucaoActionForm
						.setNomeCliente(httpServletRequest
								.getParameter("descricaoCampoEnviarDados"));
				
				pesquisarGuiaDevolucaoActionForm.setCodigoImovel("");

				pesquisarGuiaDevolucaoActionForm
						.setInscricaoImovel("");

			}

		} else {
			// Verifica se o código do imóvel foi digitado
			if (codigoImovel != null
					&& !codigoImovel.trim().equals("")
					&& Integer.parseInt(codigoImovel) > 0) {
				FiltroImovel filtroImovel = new FiltroImovel();
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("localidade");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
				filtroImovel
						.adicionarCaminhoParaCarregamentoEntidade("quadra");
				
				filtroImovel.adicionarParametro(new ParametroSimples(
						FiltroImovel.ID, codigoImovel));
	
				Collection imovelEncontrado = fachada.pesquisar(filtroImovel,
						Imovel.class.getName());
	
				if (imovelEncontrado != null && !imovelEncontrado.isEmpty()) {
					// O imovel foi encontrado
					pesquisarGuiaDevolucaoActionForm.setCodigoImovel(""
							+ ((Imovel) ((List) imovelEncontrado).get(0)).getId());
					pesquisarGuiaDevolucaoActionForm.setInscricaoImovel(""
							+ ((Imovel) ((List) imovelEncontrado).get(0)).getInscricaoFormatada());
				} else {
					pesquisarGuiaDevolucaoActionForm.setCodigoImovel("");
					httpServletRequest.setAttribute(
							"codigoImovelNaoEncontrado", "exception");
					pesquisarGuiaDevolucaoActionForm
							.setInscricaoImovel("MATRÍCULA INEXISTENTE");
					httpServletRequest.setAttribute("nomeCampo","codigoImovel");
				}
			}
			// Verifica se o código do cliente foi digitado
			if (codigoCliente != null
					&& !codigoCliente.trim().equals("")
					&& Integer.parseInt(codigoCliente) > 0) {
				
				FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
	
				filtroClienteEndereco.adicionarParametro(new ParametroSimples(
						FiltroClienteEndereco.CLIENTE_ID, codigoCliente));
	
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("cliente.clienteTipo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("cliente.profissao");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("cliente.ramoAtividade");
	
				Collection clienteEncontrado = fachada.pesquisar(filtroClienteEndereco,
						ClienteEndereco.class.getName());
	
				if (clienteEncontrado != null && !clienteEncontrado.isEmpty()) {
				
					ClienteEndereco clienteEndereco = (ClienteEndereco) ((List) clienteEncontrado).get(0);
					// O endereço do cliente foi encontrado
					if (clienteEndereco.getCliente().getId() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setCodigoCliente(""
								+ clienteEndereco.getCliente().getId());
					}
					if (clienteEndereco.getCliente().getNome() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setNomeCliente(""
								+ clienteEndereco.getCliente().getNome());
					}
					if (clienteEndereco.getCliente().getClienteTipo().getIndicadorPessoaFisicaJuridica() == 1 ){
						if (clienteEndereco.getCliente().getCpfFormatado() != null) 
						{
							pesquisarGuiaDevolucaoActionForm.setCpfCnpj(""
									+ clienteEndereco.getCliente().getCpfFormatado());
						}
					}
					else
					{
						if (clienteEndereco.getCliente().getCnpjFormatado() != null) 
						{
							pesquisarGuiaDevolucaoActionForm.setCpfCnpj(""
									+ clienteEndereco.getCliente().getCnpjFormatado());
						}
					}
					if (clienteEndereco.getCliente().getProfissao() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setProfissao(""
								+ clienteEndereco.getCliente().getProfissao().getDescricao());
					}
					if (clienteEndereco.getCliente().getRamoAtividade() != null) 
					{
						pesquisarGuiaDevolucaoActionForm.setRamoAtividade(""
								+ clienteEndereco.getCliente().getRamoAtividade().getDescricao());
					}
					pesquisarGuiaDevolucaoActionForm.setEnderecoCliente(""
							+ ((ClienteEndereco) ((List) clienteEncontrado)
									.get(0)).getEnderecoFormatado());
					
					FiltroClienteFone filtroClienteFone = new FiltroClienteFone();
	
					filtroClienteFone.adicionarParametro(new ParametroSimples(
							FiltroClienteFone.CLIENTE_ID, codigoCliente));
	
					
					Collection colecaoClienteFone = fachada.pesquisar(
							filtroClienteFone, ClienteFone.class.getName());
	
					if (colecaoClienteFone != null
							&& !colecaoClienteFone.isEmpty()) {
						// O telefone foi encontrado
						pesquisarGuiaDevolucaoActionForm.setClienteFone(""
								+ ((IClienteFone) ((List) colecaoClienteFone)
										.get(0)).getTelefone());
					}
	
				} else {
					pesquisarGuiaDevolucaoActionForm.setCodigoCliente("");
					pesquisarGuiaDevolucaoActionForm
							.setNomeCliente(ConstantesSistema.CODIGO_CLIENTE_INEXISTENTE);
					httpServletRequest.setAttribute(
							"idClienteNaoEncontrado", "exception");
					httpServletRequest.setAttribute("nomeCampo","codigoCliente");
				}
			}
		}		
		// Coleção de Tipo de Crédito
		FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
		Collection<CreditoTipo> collectionTipoCredito = fachada.pesquisar(
				filtroCreditoTipo, CreditoTipo.class.getName());

		httpServletRequest.setAttribute("collectionTipoCredito",
				collectionTipoCredito);

		// Coleção de Tipo de Documento
		FiltroDocumentoTipo filtroDocumentoTipo = new FiltroDocumentoTipo();
		Collection<DocumentoTipo> collectionTipoDocumento = fachada.pesquisar(
				filtroDocumentoTipo, DocumentoTipo.class.getName());

		httpServletRequest.setAttribute("collectionTipoDocumento",
				collectionTipoDocumento);

		// Coleção de Debito Crédito Situacao
		FiltroDebitoCreditoSituacao filtroDebitoCreditoSituacao = new FiltroDebitoCreditoSituacao();
		Collection<DebitoCreditoSituacao> collectionSituacaoGuia = fachada.pesquisar(
				filtroDebitoCreditoSituacao, DebitoCreditoSituacao.class.getName());

		httpServletRequest.setAttribute("collectionSituacaoGuia",
				collectionSituacaoGuia);
		
		return retorno;

	}
}
