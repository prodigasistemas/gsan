package gcom.gui.cadastro.cliente;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.validator.DynaValidatorForm;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteFone;
import gcom.cadastro.imovel.Imovel;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

/**
 * Action para a pré-exibição da página de Atualizar Cliente
 */
public class ExibirAtualizarClienteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("atualizarClienteNomeTipo");

		HttpSession sessao = request.getSession(false);

		String codigoCliente = null;

		StatusWizard statusWizard = null;

		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		if (request.getParameter("desfazer") == null) {

			if (request.getParameter("idRegistroAtualizacao") != null) {
				codigoCliente = (String) request.getParameter("idRegistroAtualizacao");
			} else if (request.getAttribute("idRegistroAtualizacao") != null) {
				codigoCliente = (String) request.getAttribute("idRegistroAtualizacao");
			}

			// [UC1049] - Atualizar Dados Cadastrais PROMAIS
			// link para tela de sucesso
			String linkSucesso = (String) request.getParameter("linkSucesso");

			if (linkSucesso != null && !linkSucesso.equals("")) {

				sessao.setAttribute("linkSucesso", linkSucesso);
			}

			// Verifica se chegou no atualizar cliente atraves da tela de
			// filtrar devido a um unico registro
			// ou atraves da lista de imoveis no manter cliente
			if (sessao.getAttribute("atualizar") != null || request.getParameter("sucesso") != null) {
				statusWizard = new StatusWizard("atualizarClienteWizardAction", "atualizarClienteAction",
						"cancelarAtualizarClienteAction", "exibirFiltrarClienteAction",
						"exibirAtualizarClienteAction.do", codigoCliente);

				sessao.removeAttribute("atualizar");

			} else if (request.getParameter("promais") != null) {
				statusWizard = new StatusWizard("atualizarClienteWizardAction", "atualizarClienteAction",
						"cancelarAtualizarClienteAction", "exibirConsultarImovelAction",
						"exibirAtualizarClienteAction.do", codigoCliente);

			} else {
				statusWizard = new StatusWizard("atualizarClienteWizardAction", "atualizarClienteAction",
						"cancelarAtualizarClienteAction", "exibirManterClienteAction",
						"exibirAtualizarClienteAction.do", codigoCliente);
			}

			statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(1, "ClienteNomeTipoA.gif",
					"ClienteNomeTipoD.gif", "exibirAtualizarClienteNomeTipoAction", "atualizarClienteNomeTipoAction"));
			statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(2, "ClientePessoaA.gif",
					"ClientePessoaD.gif", "exibirAtualizarClientePessoaAction", "atualizarClientePessoaAction"));
			statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(3, "ClienteEnderecoA.gif",
					"ClienteEnderecoD.gif", "exibirAtualizarClienteEnderecoAction", "atualizarClienteEnderecoAction"));
			statusWizard.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(4, "ClienteTelefoneA.gif",
					"ClienteTelefoneD.gif", "exibirAtualizarClienteTelefoneAction", "atualizarClienteTelefoneAction"));

			// manda o statusWizard para a sessao
			sessao.setAttribute("statusWizard", statusWizard);

		} else {
			statusWizard = (StatusWizard) sessao.getAttribute("statusWizard");

			codigoCliente = statusWizard.getId();
		}

		// limpa a sessão
		sessao.removeAttribute("colecaoClienteFone");

		// **********************************************************************
		// Autor: Ivan Sergio
		// Data: 23/07/2009
		// CRC2103
		// Guarda o endereco do Imovel para o caso em que o Inserir/Manter
		// cliente é invocado pelo Inserir/Manter Imovel como PopUp
		// **********************************************************************
		if (sessao.getAttribute("colecaoEnderecos") != null) {
			Collection colecaoEndereco = (Collection) sessao.getAttribute("colecaoEnderecos");
			Object obj = (Object) colecaoEndereco.iterator().next();
			if (obj instanceof Imovel) {
				sessao.setAttribute("colecaoEnderecosImovel", sessao.getAttribute("colecaoEnderecos"));
			}
		}
		sessao.removeAttribute("colecaoEnderecos");
		// **********************************************************************
		sessao.removeAttribute("foneTipos");
		sessao.removeAttribute("municipios");
		sessao.removeAttribute("colecaoResponsavelSuperiores");
		sessao.removeAttribute("InserirEnderecoActionForm");

		// Retira o actionForm da sessão para criar um novo mais abaixo na linha 192
		sessao.removeAttribute("ClienteActionForm");

		sessao.removeAttribute("tipoPesquisaRetorno");
		sessao.removeAttribute("clienteAtualizacao");

		// Cria o filtro para cliente
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, codigoCliente));
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade(FiltroCliente.CLIENTE_RESPONSAVEL);
		filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");

		// pesquisa a coleçao de cliente(s)
		Collection clientes = getFachada().pesquisar(filtroCliente, Cliente.class.getName());

		// Caso a coleção esteja vazia, indica erro inesperado
		if (clientes == null || clientes.isEmpty()) {
			throw new ActionServletException("erro.sistema");
		} else {
			// O cliente que será atualizado
			Cliente cliente = (Cliente) ((List) clientes).get(0);

			if (cliente.getId() != null && !cliente.getId().equals("")) {
				statusWizard.adicionarItemHint("Código:", cliente.getId().toString());
			}
			if (cliente.getNome() != null && !cliente.getNome().equals("")) {
				statusWizard.adicionarItemHint("Nome:", cliente.getNome());
			}
			if (cliente.getCnpj() != null && !cliente.getCnpj().equals("")) {
				statusWizard.adicionarItemHint("CNPJ:", cliente.getCnpjFormatado());
			} else if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
				statusWizard.adicionarItemHint("CPF:", cliente.getCpfFormatado());
			}

			Collection colecaoClienteCadastradoTarifaSocial = getFachada().verificarClienteUsuarioCadastradoTarifaSocial(cliente.getId());

			if (colecaoClienteCadastradoTarifaSocial != null && !colecaoClienteCadastradoTarifaSocial.isEmpty()) {

				if (!getFachada().verificarPermissaoAtualizarUsuarioTarifaSocial(usuarioLogado)) {
					throw new ActionServletException("atencao.usuario.sem.permissao.atualizar.usuario.tarifa_social");
				}
			}

			/**
			 * [UC0009] Manter Cliente [FS0008] - Verificar permissão especial para cliente de imóvel público
			 */
			if (Util.verificarNaoVazio(codigoCliente)) {

				if (getFachada().verificarExistenciaImovelPublico(cliente.getId())) {

					boolean possuiPermissaoAtualizarClienteImoveisPublicos = getFachada().verificarPermissaoEspecialAtiva(
							PermissaoEspecial.ALTERAR_CLIENTE_PARA_IMOVEIS_PUBLICOS, usuarioLogado);

					if (!possuiPermissaoAtualizarClienteImoveisPublicos) {
						throw new ActionServletException("atencao.nao_usuario_nao_possui_permissao_alterar_cliente");
					}
				}
			}

			// Manda o cliente na sessão
			sessao.setAttribute("clienteAtualizacao", cliente);

			// Formato para a conversão de datas
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

			/******************************************************************************************/
			/**
			 * Código para criar um actionForm totalmente novo - Esta foi a solução
			 * encontrada para
			 **/
			/**
			 * a passagem do inserir direto para o atualizar, fazendo as substituição dos
			 * dados do
			 **/
			/**
			 * form de mesmo nome corretamente, é preciso pegar o form pelo
			 * httpServletRequest no
			 **/
			/** exibir da primeira aba (neste caso ExibirAtualizarClienteNomeTipoAction) **/
			/******************************************************************************************/

			ModuleConfig module = actionMapping.getModuleConfig();
			FormBeanConfig formBeanConfig = module.findFormBeanConfig("ClienteActionForm");
			DynaActionFormClass dynaClass = DynaActionFormClass.createDynaActionFormClass(formBeanConfig);
			DynaValidatorForm clienteActionForm = null;
			try {
				clienteActionForm = (DynaValidatorForm) dynaClass.newInstance();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
			/******************************************************************************************/
			clienteActionForm.set("codigoCliente", cliente.getId().toString());
			clienteActionForm.set("nome", formatarResultado(cliente.getNome()));
			clienteActionForm.set("nomeAbreviado", formatarResultado(cliente.getNomeAbreviado()));

			clienteActionForm.set("indicadorExibicaoNomeConta",
					formatarResultado(cliente.getIndicadorUsoNomeFantasiaConta() + ""));

			clienteActionForm.set("email", formatarResultado(cliente.getEmail()));
			
			if (cliente.getNumeroNIS() != null) {
				clienteActionForm.set("numeroNIS", String.valueOf(cliente.getNumeroNIS()));
			}
			
			clienteActionForm.set("indicadorBolsaFamilia", cliente.getIndicadorBolsaFamilia().toString());

			if (cliente.getDataVencimento() != null) {
				clienteActionForm.set("diaVencimento", cliente.getDataVencimento().toString());
			} else {
				clienteActionForm.set("diaVencimento", "");
			}

			clienteActionForm.set("indicadorPessoaFisicaJuridica",
					cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().toString());
			clienteActionForm.set("tipoPessoa", new Short("" + cliente.getClienteTipo().getId()));
			// Criado para comparação
			clienteActionForm.set("tipoPessoaAntes", new Short("" + cliente.getClienteTipo().getId()));
			clienteActionForm.set("cpf", formatarResultado("" + cliente.getCpf()));
			clienteActionForm.set("rg", formatarResultado("" + cliente.getRg()));
			if (cliente.getDataEmissaoRg() != null) {
				clienteActionForm.set("dataEmissao", formatoData.format(cliente.getDataEmissaoRg()));
			} else {
				clienteActionForm.set("dataEmissao", "");

			}
			clienteActionForm.set("idOrgaoExpedidor", formatarResultado(cliente.getOrgaoExpedidorRg()));
			clienteActionForm.set("idUnidadeFederacao", formatarResultado(cliente.getUnidadeFederacao()));

			if (cliente.getDataNascimento() != null) {
				clienteActionForm.set("dataNascimento", formatoData.format(cliente.getDataNascimento()));
			} else {
				clienteActionForm.set("dataNascimento", "");

			}

			clienteActionForm.set("idProfissao", formatarResultado(cliente.getProfissao()));
			clienteActionForm.set("idPessoaSexo", formatarResultado(cliente.getPessoaSexo()));
			clienteActionForm.set("nomeMae", formatarResultado(cliente.getNomeMae()));
			if (cliente.getCnpj() != null) {
				clienteActionForm.set("cnpj", cliente.getCnpj().toString());
			}
			clienteActionForm.set("idRamoAtividade", formatarResultado(cliente.getRamoAtividade()));
			if (cliente.getCliente() != null) {
				clienteActionForm.set("codigoClienteResponsavel",
						formatarResultado(cliente.getCliente().getId().toString()));
				clienteActionForm.set("nomeClienteResponsavel", formatarResultado(cliente.getCliente().getNome()));
			}

			if (cliente.getIndicadorAcaoCobranca() != null) {

				clienteActionForm.set("indicadorAcaoCobranca", "" + cliente.getIndicadorAcaoCobranca());
			} else {
				clienteActionForm.set("indicadorAcaoCobranca", "" + ConstantesSistema.INDICADOR_USO_ATIVO);
			}

			// Seta a coleção de endereços do usuário
			FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
			filtroClienteEndereco
					.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.CLIENTE_ID, cliente.getId()));
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroClienteEndereco
					.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
			filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");

			Collection enderecosCliente = getFachada().pesquisar(filtroClienteEndereco, ClienteEndereco.class.getName());
			Iterator iterator = null;

			if (enderecosCliente != null) {
				iterator = enderecosCliente.iterator();

				// Percorrer a coleção dos endereços para setar no form qual o
				// endereço do cliente que
				// foi selecionado como o de correspondência
				while (iterator.hasNext()) {
					ClienteEndereco clienteEndereco = (ClienteEndereco) iterator.next();
					if (clienteEndereco.getLogradouroBairro() != null)

						if (clienteEndereco.getIndicadorEnderecoCorrespondencia()
								.equals(ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA)) {
							clienteActionForm.set("enderecoCorrespondenciaSelecao",
									new Long(obterTimestampIdObjeto(clienteEndereco)));
							break;
						}
				}
				sessao.setAttribute("colecaoEnderecos", enderecosCliente);
			}

			// Seta a coleção de telefones do usuário
			FiltroClienteFone filtroClienteFone = new FiltroClienteFone();
			filtroClienteFone.adicionarParametro(new ParametroSimples(FiltroClienteFone.CLIENTE_ID, cliente.getId()));

			/*
			 * filtroClienteFone
			 * .adicionarCaminhoParaCarregamentoEntidade("foneTipo.descricao");
			 */

			filtroClienteFone.adicionarCaminhoParaCarregamentoEntidade("foneTipo");

			Collection telefonesCliente = getFachada().pesquisar(filtroClienteFone, ClienteFone.class.getName());

			if (telefonesCliente != null) {

				iterator = telefonesCliente.iterator();

				// Percorrer a coleção dos telefones para setar no form qual o
				// telefone do cliente que
				// foi selecionado como o principal
				while (iterator.hasNext()) {
					ClienteFone clienteFone = (ClienteFone) iterator.next();

					if (clienteFone != null) {
						if (clienteFone.getIndicadorTelefonePadrao() != null && clienteFone.getIndicadorTelefonePadrao()
								.equals(ConstantesSistema.INDICADOR_TELEFONE_PRINCIPAL)) {
							clienteActionForm.set("indicadorTelefonePadrao",
									new Long(obterTimestampIdObjeto(clienteFone)));
							break;
						}
					}
				}
			}

			// Verifica o indicador de uso
			clienteActionForm.set("indicadorUso", cliente.getIndicadorUso().toString());

			clienteActionForm.set("indicadorAcrescimos", cliente.getIndicadorAcrescimos().toString());

			clienteActionForm.set("indicadorGeraFaturaAntecipada", cliente.getIndicadorGeraFaturaAntecipada().toString());
			
			if (cliente.getDataVencimento() != null) {
				clienteActionForm.set("indicadorVencimentoMesSeguinte",
						cliente.getIndicadorVencimentoMesSeguinte().toString());
			}

			if (cliente.getIndicadorPermiteNegativacao() != null) {

				if (cliente.getIndicadorPermiteNegativacao().equals(ConstantesSistema.SIM)) {
					clienteActionForm.set("indicadorPermiteNegativacao", ConstantesSistema.NAO.toString());
				} else {
					clienteActionForm.set("indicadorPermiteNegativacao", ConstantesSistema.SIM.toString());
				}
			}

			// Seta a coleção de telefones do usuário
			sessao.setAttribute("colecaoClienteFone", telefonesCliente);

			// Seta o form na sessão
			// sessao.setAttribute("ClienteActionForm", clienteActionForm);

			// Manda o form para a primeira página do processo para que depois
			// ela seja colocada na sessão
			request.setAttribute("ClienteActionForm", clienteActionForm);

			/**
			 * Autor: Paulo Diniz Data: 11/07/2011 [RR2011061059] [UC0009] Manter Cliente
			 * [FS0013] Verificar permissÃ£o especial alterar cliente inativo
			 */
			if (cliente.getIndicadorUso() != null && cliente.getIndicadorUso().intValue() == 2) {
				sessao.setAttribute("desabilitarCampos", true);
			} else {
				sessao.setAttribute("desabilitarCampos", false);
			}
		}

		if (request.getParameter("promais") != null) {
			sessao.setAttribute("promais", request.getParameter("promais"));
			sessao.setAttribute("idImovel", request.getParameter("idImovel"));
			sessao.setAttribute("caminhoVoltarPromais", statusWizard.getCaminhoActionVoltarFiltro());
		}

		// devolve o mapeamento de retorno
		return retorno;
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param parametro Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private String formatarResultado(String parametro) {
		if (parametro != null && !parametro.trim().equals("") && !parametro.trim().equals("null")) {
			return parametro.trim();
		} else {
			return "";
		}
	}

	/**
	 * < <Descrição do método>>
	 * 
	 * @param parametro Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	private Integer formatarResultado(Object parametro) {
		if (parametro != null) {
			try {
				return (Integer) parametro.getClass().getMethod("getId", (Class[]) null).invoke(parametro,
						(Object[]) null);
			} catch (SecurityException ex) {
				return null;
			} catch (NoSuchMethodException ex) {
				return null;
			} catch (InvocationTargetException ex) {
				return null;
			} catch (IllegalArgumentException ex) {
				return null;
			} catch (IllegalAccessException ex) {
				return null;
			}
		} else {
			return new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO);
		}
	}

}
