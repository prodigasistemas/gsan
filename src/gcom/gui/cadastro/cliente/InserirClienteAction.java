package gcom.gui.cadastro.cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import gcom.cadastro.cliente.CadastroUnico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCadastroUnico;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.descricaogenerica.DescricaoGenerica;
import gcom.cadastro.descricaogenerica.FiltroDescricaoGenerica;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.integracao.webservice.spc.ConsultaWebServiceTest;
import gcom.seguranca.AtributoGrupo;
import gcom.seguranca.ConsultaCdl;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class InserirClienteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		HttpSession sessao = request.getSession(false);

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		DynaValidatorForm form = (DynaValidatorForm) actionForm;

		// Código do cliente que será inserido
		Integer codigoCliente = null;

		// Pega a coleção de endereços do cliente
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");

		// Pega a coleção de telefones do cliente
		Collection colecaoFones = (Collection) sessao.getAttribute("colecaoClienteFone");
		
		// Cria o objeto do cliente para ser inserido
		String nome = ((String) form.get("nome")).toUpperCase();
		String nomeAbreviado = ((String) form.get("nomeAbreviado")).toUpperCase();
		String rg = (String) form.get("rg");
		String cpf = (String) form.get("cpf");
		String dataEmissao = (String) form.get("dataEmissao");
		String dataNascimento = (String) form.get("dataNascimento");
		String cnpj = (String) form.get("cnpj");
		String email = (String) form.get("email");

		Short indicadorUsoNomeFantasiaConta = ConstantesSistema.NAO;

		if (form.get("indicadorExibicaoNomeConta") != null
				&& !form.get("indicadorExibicaoNomeConta").toString().equals("")) {

			String indicadorExibicaoNomeConta = null;
			indicadorExibicaoNomeConta = (String) form.get("indicadorExibicaoNomeConta").toString();

			if (indicadorExibicaoNomeConta.equals(Cliente.INDICADOR_NOME_FANTASIA.toString())) {
				indicadorUsoNomeFantasiaConta = ConstantesSistema.SIM;
			}
		}

		OrgaoExpedidorRg orgaoExpedidorRg = null;

		if (form.get("idOrgaoExpedidor") != null
				&& ((Integer) form.get("idOrgaoExpedidor")).intValue() > 0) {

			orgaoExpedidorRg = new OrgaoExpedidorRg();

			orgaoExpedidorRg.setId((Integer) form.get("idOrgaoExpedidor"));

		}

		PessoaSexo pessoaSexo = null;

		if (form.get("idPessoaSexo") != null
				&& ((Integer) form.get("idPessoaSexo")).intValue() > 0) {

			pessoaSexo = new PessoaSexo();

			pessoaSexo.setId((Integer) form.get("idPessoaSexo"));

		}

		Profissao profissao = null;

		if (form.get("idProfissao") != null
				&& ((Integer) form.get("idProfissao")).intValue() > 0) {

			profissao = new Profissao();

			profissao.setId((Integer) form.get("idProfissao"));
		}

		UnidadeFederacao unidadeFederacao = null;

		if (form.get("idUnidadeFederacao") != null
				&& ((Integer) form.get("idUnidadeFederacao")).intValue() > 0) {

			unidadeFederacao = new UnidadeFederacao();

			unidadeFederacao.setId((Integer) form.get("idUnidadeFederacao"));
		}

		// Seta o clienteTipo
		ClienteTipo clienteTipo = new ClienteTipo();

		clienteTipo.setId(new Integer(((Short) form.get("tipoPessoa")).intValue()));

		// Faz a verificação se o tipo do cliente é pessoa jurídica e se o cnpj foi preenchido
		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();
		filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID, clienteTipo.getId().toString()));

		Short tipoPessoa = ((ClienteTipo) getFachada().pesquisar(filtroClienteTipo, ClienteTipo.class.getName()).iterator().next()).getIndicadorPessoaFisicaJuridica();

		if (tipoPessoa != null) {
			if (tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
				if (form.get("idPessoaSexo").equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					// O Sexo é obrigatório caso o tipo de pessoa seja física
					throw new ActionServletException("atencao.informe_campo", null, "Sexo");
				}
			}
		}

		// Verifica se pelo menos um endereço de correspondência foi informado
		if (colecaoEnderecos == null || colecaoEnderecos.isEmpty()) {
			throw new ActionServletException("atencao.informe_campo", null, "Endereço(s) do Cliente");
		}

		// FS0016] Verificar Duplicidade de cliente
		if (this.getSistemaParametro().getIndicadorDuplicidadeCliente().toString()
				.equals(ConstantesSistema.SIM.toString())) {

			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(
					new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial
					.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID,
							PermissaoEspecial.INSERIR_CLIENTE_COM_MESMO_NOME_E_ENDERECO));

			Collection colecaoUsuarioPermisao = getFachada().pesquisar(filtroUsuarioPemissaoEspecial,
					UsuarioPermissaoEspecial.class.getName());

			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
				filtroClienteEndereco
						.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.NOME, nome.toUpperCase()));

				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroClienteEndereco
						.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("cliente");

				Collection<ClienteEndereco> colecaoClienteEndereco = getFachada().pesquisar(filtroClienteEndereco, ClienteEndereco.class.getName());

				if (colecaoClienteEndereco != null && !colecaoClienteEndereco.isEmpty()) {
					Iterator iterator = colecaoClienteEndereco.iterator();

					while (iterator.hasNext()) {
						ClienteEndereco clienteEnderecoIterator = (ClienteEndereco) iterator.next();

						Iterator iteratorEnderecos = colecaoEnderecos.iterator();
						while (iteratorEnderecos.hasNext()) {
							ClienteEndereco clienteEndereco = (ClienteEndereco) iteratorEnderecos.next();

							if (clienteEndereco.getEnderecoFormatado()
									.equals(clienteEnderecoIterator.getEnderecoFormatado())) {
								throw new ActionServletException("atencao.duplicidade.cliente", null, "Cliente");
							}
						}
					}
				}

			}

		}

		// [FS0017] Verificar Nome de Cliente com menos de 10 posições
		if (this.getSistemaParametro().getIndicadorNomeMenorDez().toString().equals(ConstantesSistema.NAO.toString())) {

			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(
					new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial
					.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID,
							PermissaoEspecial.INSERIR_NOMES_COM_MENOS_DE_10_CARACTERES));

			Collection colecaoUsuarioPermisao = getFachada().pesquisar(filtroUsuarioPemissaoEspecial,
					UsuarioPermissaoEspecial.class.getName());

			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				String nomeFormatado = nome.replaceAll(" ", "");
				if (nomeFormatado.length() < 10) {
					throw new ActionServletException("atencao.nome.cliente.menos.dez.posicoes", null,
							"Nome do Cliente");
				}
			}

		}

		// [FS0018] Verificar Nome de Cliente com Descrição Genérica
		if (this.getSistemaParametro().getIndicadorNomeClienteGenerico().toString()
				.equals(ConstantesSistema.NAO.toString())) {

			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(
					new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial
					.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID,
							PermissaoEspecial.INSERIR_NOME_CLIENTE_GENERICO));

			Collection colecaoUsuarioPermisao = getFachada().pesquisar(filtroUsuarioPemissaoEspecial,
					UsuarioPermissaoEspecial.class.getName());

			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				FiltroDescricaoGenerica filtroDescricaoGenerica = new FiltroDescricaoGenerica();
				Collection colecaoDescricaoGenerica = getFachada().pesquisar(filtroDescricaoGenerica, DescricaoGenerica.class.getName());

				if (colecaoDescricaoGenerica != null || !colecaoDescricaoGenerica.isEmpty()) {
					String nomeFormatado = nome.replaceAll(" ", "");
					Iterator iteratorDescricaoGenerica = colecaoDescricaoGenerica.iterator();

					while (iteratorDescricaoGenerica.hasNext()) {
						DescricaoGenerica descricaoGenerica = (DescricaoGenerica) iteratorDescricaoGenerica.next();
						String nomeGenerico = descricaoGenerica.getNomeGenerico();
						String nomeGenericoFormatado = nomeGenerico.replaceAll(" ", "");

						if (nomeGenerico.equalsIgnoreCase(nome) || nomeGenericoFormatado.equalsIgnoreCase(nome)
								|| nomeGenerico.equalsIgnoreCase(nomeFormatado)
								|| nomeGenericoFormatado.equalsIgnoreCase(nomeFormatado)) {
							throw new ActionServletException("atencao.nome.cliente.descricao.generica", null,
									"Nome do Cliente");
						}
					}
				}
			}
		}

		RamoAtividade ramoAtividade = null;

		// Caso o ramo ra
		if (form.get("idRamoAtividade") != null
				&& ((Integer) form.get("idRamoAtividade")).intValue() > 0) {

			ramoAtividade = new RamoAtividade();

			ramoAtividade.setId((Integer) form.get("idRamoAtividade"));
		}

		Cliente clienteResponsavel = null;

		if (form.get("codigoClienteResponsavel") != null
				&& !((String) form.get("codigoClienteResponsavel")).trim().equalsIgnoreCase("")) {
			// Cria o objeto do cliente responsável
			clienteResponsavel = new Cliente();

			clienteResponsavel.setId(new Integer((String) form.get("codigoClienteResponsavel")));

		}

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		
		// Flag para determinar se foi atualizado perfil do imóvel relacionado ao cliente atualizado
		boolean imovelPerfilAtualizado = false;

		ConsultaCdl clienteCadastradoNaReceita = new ConsultaCdl();
		try {

			if (cpf != null && cpf.equals("")) {
				cpf = null;
			}

			if (cnpj != null && cnpj.equals("")) {
				cnpj = null;
			}

			Cliente cliente = new Cliente(
					nome, 
					nomeAbreviado, 
					cpf, 
					rg,
					dataEmissao != null && !dataEmissao.trim().equalsIgnoreCase("") ? formatoData.parse(dataEmissao) : null,
					dataNascimento != null && !dataNascimento.trim().equalsIgnoreCase("") ? formatoData.parse(dataNascimento) : null,
					cnpj,
					email,
					ConstantesSistema.INDICADOR_USO_ATIVO,
					new Date(),
					orgaoExpedidorRg,
					clienteResponsavel,
					pessoaSexo,
					profissao,
					unidadeFederacao,
					clienteTipo,
					indicadorUsoNomeFantasiaConta,
					ramoAtividade);
			
			// Numero do NIS
			String numeroNIS = (String) form.get("numeroNIS");
			if (numeroNIS != null && !numeroNIS.trim().equals("")) {
				cliente.setNumeroNIS(numeroNIS.trim());
				validarCadastroUnico(cliente);
			} 

			// Insere o indicador para Cobranca Acrescimos
			cliente.setIndicadorAcrescimos(new Short("1"));

			if (form.get("diaVencimento") != null && !(form.get("diaVencimento").equals(""))) {
				String diaVencimento = (String) form.get("diaVencimento");
				cliente.setDataVencimento(new Short(diaVencimento));
			} else {
				cliente.setDataVencimento(null);
			}

			// Nome da Mãe
			if (form.get("nomeMae") != null && (!(form.get("nomeMae").equals("")))) {
				cliente.setNomeMae(((String) form.get("nomeMae")).toUpperCase());
			}

			if (form.get("indicadorGeraFaturaAntecipada") != null
					&& !form.get("indicadorGeraFaturaAntecipada").equals("")) {
				cliente.setIndicadorGeraFaturaAntecipada(new Short((String) form.get("indicadorGeraFaturaAntecipada")));
			} else {
				cliente.setIndicadorGeraFaturaAntecipada(ConstantesSistema.NAO);
			}

			if (form.get("diaVencimento") != null 
					&& !(form.get("diaVencimento").equals(""))
					&& (form.get("indicadorVencimentoMesSeguinte") != null
					&& !form.get("indicadorVencimentoMesSeguinte").equals(""))) {
				cliente.setIndicadorVencimentoMesSeguinte(new Short((String) form.get("indicadorVencimentoMesSeguinte")));
			} else {
				cliente.setIndicadorVencimentoMesSeguinte(ConstantesSistema.NAO);
			}
			
			// Insere o Indicador de Negativação do Cliente
			if (form.get("indicadorPermiteNegativacao") != null
					&& form.get("indicadorPermiteNegativacao").equals(ConstantesSistema.SIM.toString())) {

				cliente.setIndicadorPermiteNegativacao(ConstantesSistema.NAO);
			} else {
				cliente.setIndicadorPermiteNegativacao(ConstantesSistema.SIM);
			}

			// Verifica se a funcionalidade esta sendo executada dentro de um popup
			if (sessao.getAttribute("POPUP") != null) {
				if (sessao.getAttribute("POPUP").equals("true")) {
					if (sessao.getAttribute("idImovel") == null) {
						cliente.setId2(-1);
						colecaoEnderecos = this.setaId2ClienteEnderecos(colecaoEnderecos, -1);
						colecaoFones = this.setaId2ClienteFones(colecaoFones, -1);
					} else {
						Integer idImovel = new Integer(sessao.getAttribute("idImovel").toString());
						cliente.setId2(idImovel);
						colecaoEnderecos = this.setaId2ClienteEnderecos(colecaoEnderecos, idImovel);
						colecaoFones = this.setaId2ClienteFones(colecaoFones, idImovel);
					}

					if (sessao.getAttribute("idClienteRelacaoTipo") != null) {
						Integer idClienteRelacaoTipo = new Integer(
								sessao.getAttribute("idClienteRelacaoTipo").toString());

						Integer idAtributoGrupo = null;
						switch (idClienteRelacaoTipo) {
						case 1:
							idAtributoGrupo = AtributoGrupo.ATRIBUTOS_DO_PROPRIETARIO;
							break;
						case 2:
							idAtributoGrupo = AtributoGrupo.ATRIBUTOS_DO_USUARIO;
							break;
						}

						if (idAtributoGrupo != null) {
							AtributoGrupo atributoGrupo = new AtributoGrupo();
							atributoGrupo.setId(idAtributoGrupo);

							OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
							operacaoEfetuada.setAtributoGrupo(atributoGrupo);

							cliente.setOperacaoEfetuada(operacaoEfetuada);
						}
					}
				}
			}
			// *************************************************************************

			String confirmado = null;
			if (request.getParameter("confirmado") != null) {
				confirmado = request.getParameter("confirmado");
			}

			Short indicadorConsultaDocumentoReceita = this.getSistemaParametro().getIndicadorConsultaDocumentoReceita();
			if (cpf == null || cnpj == null) {
				indicadorConsultaDocumentoReceita = ConstantesSistema.NAO;
			}

			if (confirmado == null
					&& indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())) {

				ConsultaWebServiceTest consultaWebService = new ConsultaWebServiceTest();

				try {
					if (cpf != null) {
						clienteCadastradoNaReceita = consultaWebService.consultarPessoaFisica(nome, cpf);
						System.out.println("CONSULTA SPC INSERIR CLIENTE CPF: " + cpf);
					} else if (cnpj != null) {
						clienteCadastradoNaReceita = consultaWebService.consultaPessoaJuridica(nome, cnpj);
						System.out.println("CONSULTA SPC INSERIR CLIENTE CNPJ: " + cnpj);
					}
				} catch (Exception e) {
					clienteCadastradoNaReceita.setMensagemRetorno("Erro ao consultar o CDL.");
				}

				if (clienteCadastradoNaReceita.getNomeCliente() != null
						&& !clienteCadastradoNaReceita.getNomeCliente().equals("NOME NAO CADASTRADO")
						&& !clienteCadastradoNaReceita.getNomeCliente().equals("EMPRESA NAO CADASTRADA")) {

					System.out.println("NOME RETORNADO CDL " + cpf + ":" + clienteCadastradoNaReceita.getNomeCliente());

					form.set("nomeClienteReceitaFederal", clienteCadastradoNaReceita.getNomeCliente());

				} else {
					clienteCadastradoNaReceita.setNomeCliente(null);
					clienteCadastradoNaReceita.setMensagemRetorno("Erro ao consultar o CDL.");
				}

				sessao.setAttribute("clienteCadastradoNaReceita", clienteCadastradoNaReceita);
			}

			short codigoAcao = ConstantesSistema.NUMERO_NAO_INFORMADO;
			boolean ehParaInserirImovel = true;

			// Caso o spc esteja fora, não realizar acao de atualizacao do cliente e dos dados do spc
			if (clienteCadastradoNaReceita != null && clienteCadastradoNaReceita.getMensagemRetorno() != null) {
				ehParaInserirImovel = false;
				retorno = this.montaTelaAtencao(actionMapping, request, "atencao.cliente_nao_foi_inserido_spc_fora", false);
			}

			if (confirmado == null && clienteCadastradoNaReceita.getNomeCliente() != null && !clienteCadastradoNaReceita.getNomeCliente().equals(nome)) {

				request.setAttribute("nomeBotao1", "Aceitar");
				request.setAttribute("nomeBotao3", "Rejeitar");

				return montarPaginaConfirmacaoWizard("atencao.confirmacao_nome_receita_federal", request,
						actionMapping, nome, clienteCadastradoNaReceita.getNomeCliente());

			} else if (confirmado == null && clienteCadastradoNaReceita.getNomeCliente() != null
					&& clienteCadastradoNaReceita.getNomeCliente().equals(nome)) {
				clienteCadastradoNaReceita = (ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");

				codigoAcao = 3;
				clienteCadastradoNaReceita.setCodigoAcaoOperador(codigoAcao);

			} else if (confirmado != null && confirmado.trim().equalsIgnoreCase("ok")) {

				clienteCadastradoNaReceita = (ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");

				cliente.setNome(clienteCadastradoNaReceita.getNomeCliente());

				if (clienteCadastradoNaReceita.getNomeMae() != null) {
					cliente.setNomeMae(clienteCadastradoNaReceita.getNomeMae());
				}

				if (clienteCadastradoNaReceita.getDataNascimento() != null) {
					cliente.setDataNascimento(clienteCadastradoNaReceita.getDataNascimento());
				}

				codigoAcao = 1;
				clienteCadastradoNaReceita.setCodigoAcaoOperador(codigoAcao);

			} else if ((clienteCadastradoNaReceita.getMensagemRetorno() == null
					|| clienteCadastradoNaReceita.getMensagemRetorno().equals("")) && (confirmado != null)) {

				clienteCadastradoNaReceita = (ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");

				codigoAcao = 2;
				clienteCadastradoNaReceita.setCodigoAcaoOperador(codigoAcao);
				ehParaInserirImovel = false;

				request.setAttribute("naoExibirBotaoVoltarTelaAtencao", true);
				reportarErros(request, "atencao.cliente_nao_foi_inserido");

				retorno = actionMapping.findForward("telaAtencao");
			}

			// Insere o cliente
			if (ehParaInserirImovel) {
				codigoCliente = this.getFachada().inserirCliente(cliente, colecaoFones, colecaoEnderecos, usuario);
			}

			Cliente clienteAux = null;

			if (codigoCliente != null) {
				clienteAux = new Cliente();
				clienteAux.setId(codigoCliente);
			}

			if (confirmado != null || (clienteCadastradoNaReceita.getCodigoAcaoOperador() != null
					&& clienteCadastradoNaReceita.getCodigoAcaoOperador() == 3)) {

				ConsultaCdl clienteCadastradoNaReceitaAtualiza = (ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");
				clienteCadastradoNaReceitaAtualiza.setCodigoCliente(clienteAux);
				clienteCadastradoNaReceitaAtualiza.setUsuario(usuario);
				clienteCadastradoNaReceitaAtualiza.setCpfUsuario(usuario.getCpf());
				clienteCadastradoNaReceitaAtualiza.setLoginUsuario(usuario.getLogin());
				clienteCadastradoNaReceitaAtualiza.setUltimaAlteracao(new Date());

				this.getFachada().inserir(clienteCadastradoNaReceitaAtualiza);
			}

			// limpa a sessão
			sessao.removeAttribute("clienteCadastradoNaReceita");
			sessao.removeAttribute("colecaoClienteFone");
			sessao.removeAttribute("colecaoEnderecos");
			sessao.removeAttribute("foneTipos");
			sessao.removeAttribute("municipios");
			sessao.removeAttribute("colecaoResponsavelSuperiores");
			sessao.removeAttribute("InserirEnderecoActionForm");
			sessao.removeAttribute("ClienteActionForm");
			sessao.removeAttribute("tipoPesquisaRetorno");

		} catch (ParseException ex) {
			reportarErros(request, "erro.sistema", ex);
			retorno = actionMapping.findForward("telaErro");
		}

		// Verifica se a funcionalidade esta sendo executada dentro de um popup
		boolean exibirTelaSucesso = true;
		if (sessao.getAttribute("POPUP") != null) {
			if (sessao.getAttribute("POPUP").equals("true")) {
				retorno = actionMapping.findForward("inserirClientePopUp");
				sessao.setAttribute("codigoCliente", codigoCliente);
				sessao.setAttribute("nomeCliente", nome);
				request.setAttribute("colecaoTipoPessoa", null);
				exibirTelaSucesso = false;
			}
		}

		if (exibirTelaSucesso) {
			if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				String mensagemSucesso = "Cliente de código " + codigoCliente + " inserido com sucesso.";
				montarPaginaSucesso(request, mensagemSucesso,
						"Inserir outro Cliente",
						"exibirInserirClienteAction.do",
						"exibirAtualizarClienteAction.do?idRegistroAtualizacao=" + codigoCliente,
						"Atualizar Cliente Inserido");
			} else {
				retorno = actionMapping.findForward("telaAtencao");
			}
		}

		return retorno;
	}
	
	private void validarCadastroUnico(Cliente cliente) {
		Filtro filtro = new FiltroCadastroUnico();
		filtro.adicionarParametro(new ParametroSimples(FiltroCadastroUnico.NUMERO_NIS, cliente.getNumeroNIS()));
		
		CadastroUnico cadastroUnico = (CadastroUnico) Util.retonarObjetoDeColecao(getFachada().pesquisar(filtro, CadastroUnico.class.getName()));
		
		if (cadastroUnico != null) {
			cliente.setIndicadorBolsaFamilia(ConstantesSistema.SIM);
		}else {
			cliente.setIndicadorBolsaFamilia(ConstantesSistema.NAO);
		}
	}

	private Collection setaId2ClienteEnderecos(Collection colecaoEnderecos, Integer id2) {
		Collection retorno = null;

		if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
			retorno = new ArrayList();
			Iterator iColecaoEnderecos = colecaoEnderecos.iterator();

			while (iColecaoEnderecos.hasNext()) {
				ClienteEndereco endereco = (ClienteEndereco) iColecaoEnderecos.next();
				endereco.setId2(id2);
				retorno.add(endereco);
			}
		}

		return retorno;
	}

	private Collection setaId2ClienteFones(Collection colecaoFones, Integer id2) {
		Collection retorno = null;

		if (colecaoFones != null && !colecaoFones.isEmpty()) {
			retorno = new ArrayList();
			Iterator iColecaoFones = colecaoFones.iterator();

			while (iColecaoFones.hasNext()) {
				ClienteFone fone = (ClienteFone) iColecaoFones.next();
				fone.setId2(id2);
				retorno.add(fone);
			}
		}

		return retorno;
	}

	private ActionForward montaTelaAtencao(ActionMapping actionMapping, HttpServletRequest httpServletRequest,
			String chave, boolean naoExibirBotaoVoltarTelaAtencao) {

		httpServletRequest.setAttribute("naoExibirBotaoVoltarTelaAtencao", naoExibirBotaoVoltarTelaAtencao);
		reportarErros(httpServletRequest, chave);

		return actionMapping.findForward("telaAtencao");
	}
}
