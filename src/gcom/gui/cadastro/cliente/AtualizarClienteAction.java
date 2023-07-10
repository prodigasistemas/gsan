package gcom.gui.cadastro.cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.RepositorioCadastroHBM;
import gcom.cadastro.cliente.CadastroUnico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteConta;
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.ClienteFone;
import gcom.cadastro.cliente.ClienteImovel;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCadastroUnico;
import gcom.cadastro.cliente.FiltroClienteConta;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteImovel;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.cliente.PessoaSexo;
import gcom.cadastro.cliente.Profissao;
import gcom.cadastro.cliente.RamoAtividade;
import gcom.cadastro.cliente.RepositorioClienteImovelHBM;
import gcom.cadastro.descricaogenerica.DescricaoGenerica;
import gcom.cadastro.descricaogenerica.FiltroDescricaoGenerica;
import gcom.cadastro.empresa.RepositorioEmpresaHBM;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ControladorImovelLocal;
import gcom.cadastro.imovel.ControladorImovelLocalHome;
import gcom.cadastro.imovel.FiltroImovelSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.RepositorioImovelHBM;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.localidade.RepositorioSetorComercialHBM;
import gcom.cobranca.RepositorioCobrancaHBM;
import gcom.faturamento.RepositorioFaturamentoHBM;
import gcom.faturamento.conta.Conta;
import gcom.financeiro.ContaContabil;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.integracao.webservice.spc.ConsultaWebServiceTest;
import gcom.micromedicao.RepositorioMicromedicaoHBM;
import gcom.seguranca.AtributoGrupo;
import gcom.seguranca.ConsultaCdl;
import gcom.seguranca.FiltroConsultaCadastroCdl;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesJNDI;
import gcom.util.ConstantesSistema;
import gcom.util.ErroRepositorioException;
import gcom.util.RepositorioUtilHBM;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class AtualizarClienteAction extends GcomAction {
	
	private ActionForward retorno;
	private HttpServletRequest request;
	private HttpSession sessao;

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.retorno = actionMapping.findForward("telaSucesso");
		this.request = request;
		this.sessao = request.getSession(false);
		
		DynaValidatorForm form = (DynaValidatorForm) actionForm;

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		Short tipoPessoa = (Short) form.get("tipoPessoa");

		String tipoPessoaForm = tipoPessoa.toString();

		FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

		filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID, tipoPessoaForm));
		tipoPessoa = ((ClienteTipo) getFachada().pesquisar(filtroClienteTipo, ClienteTipo.class.getName()).iterator().next())
				.getIndicadorPessoaFisicaJuridica();

		Short indicadorUsoNomeFantasiaConta = ConstantesSistema.NAO;

		if (form.get("indicadorExibicaoNomeConta") != null) {

			String indicadorExibicaoNomeConta = null;
			indicadorExibicaoNomeConta = (String) form.get("indicadorExibicaoNomeConta").toString();

			if (indicadorExibicaoNomeConta.equals(Cliente.INDICADOR_NOME_FANTASIA.toString())) {

				indicadorUsoNomeFantasiaConta = ConstantesSistema.SIM;
			}
		}

		// Verifica o destino porque se o usuário tentar concluir o processo
		// nesta página, não é necessário verificar a tela de confirmação
		// if (destinoPagina != null && !destinoPagina.trim().equals("")) {
		if (tipoPessoa != null && tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_JURIDICA)) {
			// Vai para Pessoa Juridica mas tem dados existentes em pessoa fisica
			String cpf = (String) form.get("cpf");
			String rg = (String) form.get("rg");
			String dataEmissao = (String) form.get("dataEmissao");
			Integer idOrgaoExpedidor = (Integer) form.get("idOrgaoExpedidor");
			Integer idUnidadeFederacao = (Integer) form.get("idUnidadeFederacao");
			String dataNascimento = (String) form.get("dataNascimento");
			Integer idProfissao = (Integer) form.get("idProfissao");
			Integer idPessoaSexo = (Integer) form.get("idPessoaSexo");

			if ((idPessoaSexo != null && idPessoaSexo != ConstantesSistema.NUMERO_NAO_INFORMADO)
					|| (cpf != null && !cpf.trim().equalsIgnoreCase(""))
					|| (rg != null && !rg.trim().equalsIgnoreCase(""))
					|| (dataEmissao != null && !dataEmissao.trim().equalsIgnoreCase(""))
					|| (dataNascimento != null && !dataNascimento.trim().equalsIgnoreCase(""))
					|| (idOrgaoExpedidor != null && idOrgaoExpedidor != ConstantesSistema.NUMERO_NAO_INFORMADO)
					|| (idUnidadeFederacao != null && idUnidadeFederacao != ConstantesSistema.NUMERO_NAO_INFORMADO)
					|| (idProfissao != null && idProfissao != ConstantesSistema.NUMERO_NAO_INFORMADO)) {

				// Limpar todo o conteúdo da página de pessoa física
				form.set("cpf", "");
				form.set("rg", "");
				form.set("dataEmissao", "");
				form.set("idOrgaoExpedidor", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
				form.set("idUnidadeFederacao", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
				form.set("dataNascimento", "");
				form.set("idProfissao", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
				form.set("idPessoaSexo", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
			}
		} else if (tipoPessoa != null && tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
			// Vai para Pessoa Fisica mas tem dados existentes em pessoa juridica

			String cnpj = (String) form.get("cnpj");
			Integer idRamoAtividade = (Integer) form.get("idRamoAtividade");
			String codigoClienteResponsavel = (String) form.get("codigoClienteResponsavel");

			if ((cnpj != null && !cnpj.trim().equalsIgnoreCase(""))
					|| (idRamoAtividade != null && idRamoAtividade != ConstantesSistema.NUMERO_NAO_INFORMADO)
					|| (codigoClienteResponsavel != null && !codigoClienteResponsavel.trim().equalsIgnoreCase(""))) {
				// Limpa os dados da página de pessoa jurídica
				form.set("cnpj", "");
				form.set("idRamoAtividade", new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO));
				form.set("codigoClienteResponsavel", "");
				form.set("nomeClienteResponsavel", "");
			}
		}

		// Pega o cliente que foi selecionado para atualização
		Cliente clienteAtualizacao = (Cliente) sessao.getAttribute("clienteAtualizacao");

		// Pega a coleção de endereços do cliente
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");

		// Pega a coleção de telefones do cliente
		Collection colecaoFones = (Collection) sessao.getAttribute("colecaoClienteFone");

		// Cria o objeto do cliente para ser inserido
		String nome = ((String) form.get("nome")).toUpperCase();

		/**
		 * Autor: Paulo Diniz Data: 11/07/2011 [RR2011061059] [UC0009]
		 */
		if (clienteAtualizacao.getIndicadorUso() != null && clienteAtualizacao.getIndicadorUso().intValue() == 2) {
			// [FS0013] Verificar permissÃ£o especial alterar cliente inativo
			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(
					new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(
					FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.ALTERAR_CLIENTE_INATIVO));

			Collection colecaoUsuarioPermisao = getFachada().pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				throw new ActionServletException("atencao.usuario.sem.permissao.para.alteracao.cliente.inativo");
			}

		}

		/**
		 * Autor: Mariana Victor Data: 28/12/2010 RM_3320 - [FS0010] Verificar
		 * Duplicidade de cliente
		 */
		if (this.getSistemaParametro().getIndicadorDuplicidadeCliente().toString()
				.equals(ConstantesSistema.SIM.toString())) {

			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(
					new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial
					.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID,
							PermissaoEspecial.INSERIR_CLIENTE_COM_MESMO_NOME_E_ENDERECO));

			Collection colecaoUsuarioPermisao = getFachada().pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());

			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
				filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.NOME, nome.toUpperCase()));
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
				filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
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
									.equals(clienteEnderecoIterator.getEnderecoFormatado())
									&& !clienteAtualizacao.getId()
											.equals(clienteEnderecoIterator.getCliente().getId())) {
								throw new ActionServletException("atencao.duplicidade.cliente", null, "Cliente");
							}
						}
					}
				}
			}
		}

		 // [FS0011] Verificar Nome de Cliente com menos de 10 posições
		if (this.getSistemaParametro().getIndicadorNomeMenorDez().toString().equals(ConstantesSistema.NAO.toString())) {

			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_NOMES_COM_MENOS_DE_10_CARACTERES));

			Collection colecaoUsuarioPermisao = getFachada().pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());

			if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
				String nomeFormatado = nome.replaceAll(" ", "");
				if (nomeFormatado.length() < 10) {
					throw new ActionServletException("atencao.nome.sobrenome.cliente.menos.dez.posicoes", null, nome);
				}
			}
		}

		// [FS0012] Verificar Nome de Cliente com Descrição Genérica
		if (this.getSistemaParametro().getIndicadorNomeClienteGenerico().toString()
				.equals(ConstantesSistema.NAO.toString())) {

			FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
			filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_NOME_CLIENTE_GENERICO));

			Collection colecaoUsuarioPermisao = getFachada().pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());

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

						if (nomeGenerico.equalsIgnoreCase(nome) ||
							nomeGenericoFormatado.equalsIgnoreCase(nome) ||
							nomeGenerico.equalsIgnoreCase(nomeFormatado) ||
							nomeGenericoFormatado.equalsIgnoreCase(nomeFormatado)) {
							
							throw new ActionServletException("atencao.nome.cliente.descricao.generica", null, "Nome do Cliente");
						}
					}
				}
			}
		}

		String nomeAbreviado = ((String) form.get("nomeAbreviado")).toUpperCase();
		String rg = (String) form.get("rg");
		String cpf = (String) form.get("cpf");
		if (cpf != null && cpf.trim().equals("")) {
			cpf = null;
		}
		
		String dataEmissao = (String) form.get("dataEmissao");
		String dataNascimento = (String) form.get("dataNascimento");
		String cnpj = (String) form.get("cnpj");
		if (cnpj != null && cnpj.trim().equals("")) {
			cnpj = null;
		}
		
		String indicadorAcaoCobranca = (String) form.get("indicadorAcaoCobranca");

		String email = (String) form.get("email");

		Short indicadorUso = null;

		if (form.get("indicadorUso") != null) {
			indicadorUso = new Short((String) form.get("indicadorUso"));
		} else {
			indicadorUso = new Short("1");
		}
		
		
		String indicadorAutorizacaoEnvioEmail = (String) form.get("indicadorAutorizacaoEnvioEmail");

		Short indicadorAcrescimos = null;
		if (form.get("indicadorAcrescimos") != null) {
			indicadorAcrescimos = new Short((String) form.get("indicadorAcrescimos"));
		} else {
			indicadorAcrescimos = new Short("1");
		}

		// Verificar se o usuário digitou os 4 campos relacionados com o RG de pessoa física ou se ele não digitou nenhum
		Integer idOrgaoExpedidor = (Integer) form.get("idOrgaoExpedidor");
		Integer idUnidadeFederacao = (Integer) form.get("idUnidadeFederacao");

		if (!(((rg != null && !rg.trim().equalsIgnoreCase(""))
				&& (idOrgaoExpedidor != null && !idOrgaoExpedidor.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)))
				&& (idUnidadeFederacao != null && !idUnidadeFederacao.equals(ConstantesSistema.NUMERO_NAO_INFORMADO))
				|| ((rg != null && rg.trim().equalsIgnoreCase(""))
						&& (idOrgaoExpedidor != null && idOrgaoExpedidor.equals(ConstantesSistema.NUMERO_NAO_INFORMADO))
						&& (idUnidadeFederacao != null
								&& idUnidadeFederacao.equals(ConstantesSistema.NUMERO_NAO_INFORMADO))))) {
			
			throw new ActionServletException("atencao.rg_campos_relacionados.nao_preenchidos");
		}

		OrgaoExpedidorRg orgaoExpedidorRg = null;
		if (form.get("idOrgaoExpedidor") != null && ((Integer) form.get("idOrgaoExpedidor")).intValue() > 0) {
			orgaoExpedidorRg = new OrgaoExpedidorRg();
			orgaoExpedidorRg.setId((Integer) form.get("idOrgaoExpedidor"));
		}

		PessoaSexo pessoaSexo = null;
		if (form.get("idPessoaSexo") != null && ((Integer) form.get("idPessoaSexo")).intValue() > 0) {
			pessoaSexo = new PessoaSexo();
			pessoaSexo.setId((Integer) form.get("idPessoaSexo"));
		}

		Profissao profissao = null;
		if (form.get("idProfissao") != null && ((Integer) form.get("idProfissao")).intValue() > 0) {
			profissao = new Profissao();
			profissao.setId((Integer) form.get("idProfissao"));
		}

		UnidadeFederacao unidadeFederacao = null;
		if (form.get("idUnidadeFederacao") != null && ((Integer) form.get("idUnidadeFederacao")).intValue() > 0) {
			unidadeFederacao = new UnidadeFederacao();
			unidadeFederacao.setId((Integer) form.get("idUnidadeFederacao"));
		}

		ClienteTipo clienteTipo = new ClienteTipo();
		clienteTipo.setId(new Integer(((Short) form.get("tipoPessoa")).intValue()));

		RamoAtividade ramoAtividade = null;
		if (form.get("idRamoAtividade") != null && ((Integer) form.get("idRamoAtividade")).intValue() > 0) {
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

		// Verifica se o usuário adicionou um endereço de correspondência
		Long enderecoCorrespondenciaSelecao = (Long) form.get("enderecoCorrespondenciaSelecao");

		if (enderecoCorrespondenciaSelecao == null || enderecoCorrespondenciaSelecao == 0) {
			throw new ActionServletException("atencao.endereco_correspondencia.nao_selecionado");
		}

		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

		// Verifica se o nome do Cliente é o mesmo encontrado na R. Federal de acordo com o CPF digitado
		ConsultaCdl clienteCadastradoNaReceita = new ConsultaCdl();
		
		// Flag para determinar se foi atualizado perfil do imóvel relacionado ao cliente atualizado
		boolean imovelPerfilAtualizado = false;
		
		Short indicadorbolsaFamilia = null;
		
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
					indicadorUso,
					indicadorAcrescimos,
					clienteAtualizacao.getUltimaAlteracao(),
					orgaoExpedidorRg,
					clienteResponsavel,
					pessoaSexo,
					profissao,
					unidadeFederacao,
					clienteTipo,
					ramoAtividade,
					indicadorUsoNomeFantasiaConta);
			
			// Seta o id do cliente atualizado para ser identificado no BD na atualização
			cliente.setId(clienteAtualizacao.getId());

			cliente.setIndicadorBolsaFamilia(clienteAtualizacao.getIndicadorBolsaFamilia());
			
			// Numero do NIS
			String numeroNIS = (String) form.get("numeroNIS");
			if (numeroNIS != null && !numeroNIS.trim().equals("")) {
				isNisValido(numeroNIS, tipoPessoa);
				cliente.setNumeroNIS(numeroNIS.trim());
				validarCadastroUnico(cliente);
				imovelPerfilAtualizado = atualizarImovelPerfilBolsaAgua(cliente);
			} else {
				cliente.setIndicadorBolsaFamilia(CadastroUnico.NAO_TEM_NIS);
				imovelPerfilAtualizado = atualizarImovelPerfilBolsaAgua(cliente);
			}
				 
			cliente.setIndicadorAcaoCobranca(new Integer(indicadorAcaoCobranca).shortValue());

			cliente.setIndicadorGeraArquivoTexto(clienteAtualizacao.getIndicadorGeraArquivoTexto());

			cliente.setDiaVencimento(clienteAtualizacao.getDiaVencimento());

			// Permissao Especial Validar Acrescimos Impontualidade
			boolean validarAcrescimoImpontualidade = getFachada().verificarPermissaoValAcrescimosImpontualidade(usuario);

			request.setAttribute("validarAcrescimoImpontualidade", validarAcrescimoImpontualidade);

			if (form.get("diaVencimento") != null && !(form.get("diaVencimento").equals(""))) {
				String diaVencimento = (String) form.get("diaVencimento");
				cliente.setDataVencimento(new Short(diaVencimento));
			} else {
				cliente.setDataVencimento(null);
			}

			// Nome da Mãe
			if (form.get("nomeMae") != null && !form.get("nomeMae").equals("")) {
				cliente.setNomeMae(((String) form.get("nomeMae")).toUpperCase());
			}

			if (form.get("indicadorGeraFaturaAntecipada") != null && !form.get("indicadorGeraFaturaAntecipada").equals("")) {
				cliente.setIndicadorGeraFaturaAntecipada(new Short((String) form.get("indicadorGeraFaturaAntecipada")));
			} else {
				cliente.setIndicadorGeraFaturaAntecipada(ConstantesSistema.NAO);
			}

			if (form.get("diaVencimento") != null && !(form.get("diaVencimento").equals("")) 
					&& (form.get("indicadorVencimentoMesSeguinte") != null && !form.get("indicadorVencimentoMesSeguinte").equals(""))) {
				cliente.setIndicadorVencimentoMesSeguinte(new Short((String) form.get("indicadorVencimentoMesSeguinte")));
			} else {
				cliente.setIndicadorVencimentoMesSeguinte(ConstantesSistema.NAO);
			}

			if (form.get("indicadorAcaoCobranca") != null && !(form.get("indicadorAcaoCobranca").equals(""))) {
				cliente.setIndicadorAcaoCobranca(new Integer((String) form.get("indicadorAcaoCobranca")).shortValue());
			}

			if (form.get("indicadorPermiteNegativacao") != null && form.get("indicadorPermiteNegativacao").equals(ConstantesSistema.SIM.toString())) {
				cliente.setIndicadorPermiteNegativacao(ConstantesSistema.NAO);
			} else {
				cliente.setIndicadorPermiteNegativacao(ConstantesSistema.SIM);
			}
			
			// Indicador de Autorização de EMAIL
			if (form.get("indicadorAutorizacaoEnvioEmail") != null && !form.get("indicadorAutorizacaoEnvioEmail").equals("")) {
				cliente.setIndicadorAutorizacaoEnvioEmail(new Short((String) form.get("indicadorAutorizacaoEnvioEmail")));
			} else {
				cliente.setIndicadorAutorizacaoEnvioEmail(ConstantesSistema.NAO);
			}

			// Indicador de Autorização de EMAIL
			if (form.get("indicadorAutorizacaoEnvioSMS") != null && !form.get("indicadorAutorizacaoEnvioSMS").equals("")) {
				cliente.setIndicadorAutorizacaoEnvioSMS(new Short((String) form.get("indicadorAutorizacaoEnvioSMS")));
			} else {
				cliente.setIndicadorAutorizacaoEnvioSMS(ConstantesSistema.NAO);
			}
			
			// Indicador de recusa de subsidio
			if (form.get("indicadorRecusaSubsidio") != null && !form.get("indicadorRecusaSubsidio").equals("")) {
				cliente.setIndicadorRecusaSubsidio(new Short( (Short) form.get("indicadorRecusaSubsidio")));
			} else {
				cliente.setIndicadorRecusaSubsidio(ConstantesSistema.NAO);
			}

			// Verifica se a funcionalidade esta sendo executada dentro de um popup
			if (sessao.getAttribute("POPUP") != null) {
				if (sessao.getAttribute("POPUP").equals("true")) {
					Integer idImovel = null;
					if (sessao.getAttribute("idImovel") != null && !sessao.getAttribute("idImovel").equals("")) {
						idImovel = new Integer(sessao.getAttribute("idImovel").toString());
					} else if (sessao.getAttribute("imovelAtualizacao") != null) {
						Imovel imovel = (Imovel) sessao.getAttribute("imovelAtualizacao");
						idImovel = new Integer(imovel.getId());
					}

					if (idImovel == null) {
						cliente.setId2(-1);
						colecaoEnderecos = this.setaId2ClienteEnderecos(colecaoEnderecos, -1);
						colecaoFones = this.setaId2ClienteFones(colecaoFones, -1);
					} else {
						// Integer idImovel = new Integer(sessao.getAttribute("idImovel").toString());
						cliente.setId2(idImovel);
						colecaoEnderecos = this.setaId2ClienteEnderecos(colecaoEnderecos, idImovel);
						colecaoFones = this.setaId2ClienteFones(colecaoFones, idImovel);

						// Recupera o Tipo de Relacao do Cliente
						FiltroClienteImovel filtro = new FiltroClienteImovel();
						filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.CLIENTE_RELACAO_TIPO);
						filtro.adicionarParametro(
								new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, cliente.getId()));
						filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.IMOVEL_ID, idImovel));

						ClienteImovel clienteImovel = (ClienteImovel) Util.retonarObjetoDeColecao(getFachada().pesquisar(filtro, ClienteImovel.class.getName()));

						if (clienteImovel != null) {
							if (clienteImovel.getClienteRelacaoTipo() != null) {
								Integer idAtributoGrupo = null;
								switch (clienteImovel.getClienteRelacaoTipo().getId()) {
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
				}
			}

			String confirmado = null;
			if (request.getParameter("confirmado") != null) {
				confirmado = request.getParameter("confirmado");
			}

			ConsultaCdl consultaCdl = null;
			FiltroConsultaCadastroCdl filtroConsultaCadastroCdl = new FiltroConsultaCadastroCdl();

			Short indicadorConsultaDocumentoReceita = this.getSistemaParametro().getIndicadorConsultaDocumentoReceita();

			if (cpf != null || cnpj != null) {

				if (cpf != null) {
					filtroConsultaCadastroCdl
							.adicionarParametro(new ParametroSimples(FiltroConsultaCadastroCdl.CPF_CLIENTE, cpf));
				}

				if (cnpj != null) {
					filtroConsultaCadastroCdl
							.adicionarParametro(new ParametroSimples(FiltroConsultaCadastroCdl.CNPJ_CLIENTE, cnpj));
				}

				Collection colecaoConsultaCadastroCdl = getFachada().pesquisar(filtroConsultaCadastroCdl, ConsultaCdl.class.getName());

				consultaCdl = (ConsultaCdl) Util.retonarObjetoDeColecao(colecaoConsultaCadastroCdl);
			} else {
				indicadorConsultaDocumentoReceita = ConstantesSistema.NAO;
			}

			if (confirmado == null && consultaCdl == null
					&& indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())) {

				ConsultaWebServiceTest consultaWebService = new ConsultaWebServiceTest();

				try {
					if (cpf != null) {
						clienteCadastradoNaReceita = consultaWebService.consultarPessoaFisica(nome, cpf);
						System.out.println("CONSULTA SPC ATUALIZAR CLIENTE CPF: " + cpf);
					} else if (cnpj != null) {
						clienteCadastradoNaReceita = consultaWebService.consultaPessoaJuridica(nome, cnpj);
						System.out.println("CONSULTA SPC ATUALIZAR CLIENTE CNPJ: " + cnpj);
					}
				} catch (Exception e) {
					e.printStackTrace();
					
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

			} else if (confirmado == null && consultaCdl != null
					&& indicadorConsultaDocumentoReceita.toString().equals(ConstantesSistema.SIM.toString())) {

				clienteCadastradoNaReceita.setNomeCliente(consultaCdl.getNomeCliente());

				sessao.setAttribute("clienteCadastradoNaReceita", clienteCadastradoNaReceita);
			}

			short codigoAcao = ConstantesSistema.NUMERO_NAO_INFORMADO;
			boolean atualizaCliente = true;

			// Caso o spc esteja fora, não realizar acao de atualizacao do cliente e dos dados do spc
			if (clienteCadastradoNaReceita != null && clienteCadastradoNaReceita.getMensagemRetorno() != null) {

				atualizaCliente = false;
				retorno = this.montaTelaAtencao(actionMapping, "atencao.cliente_nao_foi_atualizado_spc_fora", false);
			}

			if (confirmado == null && clienteCadastradoNaReceita.getNomeCliente() != null
					&& !clienteCadastradoNaReceita.getNomeCliente().equals(nome)) {

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

			} else if ((clienteCadastradoNaReceita.getMensagemRetorno() == null || clienteCadastradoNaReceita.getMensagemRetorno().equals("")) && (confirmado != null)) {

				clienteCadastradoNaReceita = (ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");

				codigoAcao = 2;
				clienteCadastradoNaReceita.setCodigoAcaoOperador(codigoAcao);

				atualizaCliente = false;

				retorno = this.montaTelaAtencao(actionMapping, "atencao.cliente_nao_foi_atualizado", true);
			}

			if (atualizaCliente) {
				this.getFachada().atualizarCliente(cliente, colecaoFones, colecaoEnderecos, usuario);
			}
			
			indicadorbolsaFamilia = cliente.getIndicadorBolsaFamilia();

			inserirClienteCadastradoNaReceita(usuario, clienteCadastradoNaReceita, cliente, confirmado, consultaCdl, codigoAcao);

			limparSessao();
		} catch (ParseException ex) {
			reportarErros(request, "erro.sistema", ex);
			retorno = actionMapping.findForward("telaErro");
		}

		boolean exibirTelaSucesso = verificarExibicaoTelaSucesso(actionMapping, clienteAtualizacao, nomeAbreviado, cpf, cnpj);
		if (exibirTelaSucesso) {
			montarTelaSucesso(request, retorno, clienteAtualizacao, imovelPerfilAtualizado, indicadorbolsaFamilia);
		}

		return retorno;
	}

	private void validarCadastroUnico(Cliente cliente) throws Exception {
		Filtro filtro = new FiltroCadastroUnico();
		filtro.adicionarParametro(new ParametroSimples(FiltroCadastroUnico.NUMERO_NIS, cliente.getNumeroNIS()));

		CadastroUnico cadastroUnico = (CadastroUnico) Util
				.retonarObjetoDeColecao(getFachada().pesquisar(filtro, CadastroUnico.class.getName()));

		if (cadastroUnico != null) {
			if (cadastroUnico.getIdSeaster().equals(CadastroUnico.SIASTER)) {
				cliente.setIndicadorBolsaFamilia(CadastroUnico.TEM_NIS);
			} else if (cadastroUnico.getIdSeaster().equals(CadastroUnico.CAIXA)) {
				cliente.setIndicadorBolsaFamilia(CadastroUnico.NIS_CAIXA);
			}
		} else if (cadastroUnico == null) {
			cliente.setIndicadorBolsaFamilia(CadastroUnico.NIS_SEM_REGISTRO_OFICIAL);
		}
	}

	private boolean atualizarImovelPerfilBolsaAgua(Cliente cliente) throws ErroRepositorioException {
		Collection<ClienteImovel> clienteImoveis = pesquisarImoveisPorCliente(cliente);
		Collection<ClienteImovel> clienteImovelOrdenado = ordenacaoSituacaoAgua(clienteImoveis);
		Boolean imovelElegivel = true;
		if (cliente.getIndicadorBolsaFamilia().equals(CadastroUnico.TEM_NIS) 
				|| cliente.getIndicadorBolsaFamilia().equals(CadastroUnico.NIS_SEM_REGISTRO_OFICIAL)
				|| cliente.getIndicadorBolsaFamilia().equals(CadastroUnico.NIS_CAIXA)) {
			for (ClienteImovel clienteImovel : clienteImovelOrdenado) {
				Integer idPerfil = clienteImovel.getImovel().getImovelPerfil().getId();
				if (idPerfil.equals(ImovelPerfil.BOLSA_AGUA)) {
					imovelElegivel = false;
					break;
				}
			}
			if (imovelElegivel == true) {
				for (ClienteImovel clienteImovel : clienteImovelOrdenado) {
					Collection<ImovelSubcategoria> subCategorias = pesquisarCategoria(
							clienteImovel.getImovel().getId());
					for (ImovelSubcategoria subCategoria : subCategorias) {
						if (clienteImovel.getImovel().getImovelPerfil().getId().equals(ImovelPerfil.NORMAL)
								&& (subCategoria.getSubcategoria().getId().equals(Subcategoria.RESIDENCIAL_R1) 
								||	subCategoria.getSubcategoria().getId().equals(Subcategoria.RESIDENCIAL_R2)
								||  subCategoria.getSubcategoria().getId().equals(Subcategoria.RESIDENCIAL_R3)
								||  subCategoria.getSubcategoria().getId().equals(Subcategoria.RESIDENCIAL_R4))
								&& clienteImovel.getIndicadorNomeConta().equals(ConstantesSistema.SIM)) {
							Imovel imovel = clienteImovel.getImovel();
							imovel.setImovelPerfil(new ImovelPerfil(ImovelPerfil.BOLSA_AGUA));
							imovel.setUltimaAlteracao(new Date());
							getFachada().atualizar(imovel);
							return true;
						}
					}
				}
			}
		} /* else {
			for (ClienteImovel clienteImovel : clienteImovelOrdenado) {
				Integer idPerfil = clienteImovel.getImovel().getImovelPerfil().getId();
				if (idPerfil.equals(ImovelPerfil.BOLSA_AGUA)) {
					imovelElegivel = false;
					break;
				}
			}
			if (imovelElegivel == false) {
				for (ClienteImovel clienteImovel : clienteImovelOrdenado) {
					if (clienteImovel.getImovel().getImovelPerfil().getId().equals(ImovelPerfil.BOLSA_AGUA)
							&& clienteImovel.getIndicadorNomeConta().equals(ConstantesSistema.SIM)) {
						Imovel imovel = clienteImovel.getImovel();
						imovel.setImovelPerfil(new ImovelPerfil(ImovelPerfil.NORMAL));
						imovel.setUltimaAlteracao(new Date());
						getFachada().atualizar(imovel);
						return true;
					}
				}
			}
		} */
		return false;
	}
	
	private Collection<ClienteImovel> ordenacaoSituacaoAgua(Collection<ClienteImovel> clienteImoveis) {
		Collection<ClienteImovel> clienteImovelOrdenado = new ArrayList();
		Collection<ClienteImovel> clienteImovelLigado = new ArrayList();
		Collection<ClienteImovel> clienteImovelEmFiscalizacao = new ArrayList();
		Collection<ClienteImovel> clienteImovelCortado = new ArrayList();		
		Collection<ClienteImovel> clienteImovelSuprimido = new ArrayList();
		Collection<ClienteImovel> clienteImovelSuprimidoParcial = new ArrayList();
		Collection<ClienteImovel> clienteImovelSuprimidoParcialPedido = new ArrayList();
		Collection<ClienteImovel> clienteImovelFactivel = new ArrayList();
		Collection<ClienteImovel> clienteImovelPotencial = new ArrayList();
		for (ClienteImovel clienteImovel : clienteImoveis) {
			if (clienteImovel.getImovel().getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.LIGADO)) {
				clienteImovelLigado.add(clienteImovel);
			}
			if (clienteImovel.getImovel().getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.EM_FISCALIZACAO)) {
				clienteImovelEmFiscalizacao.add(clienteImovel);
			}
			if (clienteImovel.getImovel().getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.CORTADO)) {
				clienteImovelCortado.add(clienteImovel);
			}
			if (clienteImovel.getImovel().getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.SUPRIMIDO)) {
				clienteImovelSuprimido.add(clienteImovel);
			}
			if (clienteImovel.getImovel().getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.SUPR_PARC)) {
				clienteImovelSuprimidoParcial.add(clienteImovel);
			}
			if (clienteImovel.getImovel().getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.SUPR_PARC_PEDIDO)) {
				clienteImovelSuprimidoParcialPedido.add(clienteImovel);
			}
			if (clienteImovel.getImovel().getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.FACTIVEL)) {
				clienteImovelFactivel.add(clienteImovel);
			}	
			if (clienteImovel.getImovel().getLigacaoAguaSituacao().getId().equals(LigacaoAguaSituacao.POTENCIAL)) {
				clienteImovelPotencial.add(clienteImovel);
			}								
		}
		clienteImovelOrdenado.addAll(clienteImovelLigado);
		clienteImovelOrdenado.addAll(clienteImovelEmFiscalizacao);
		clienteImovelOrdenado.addAll(clienteImovelCortado);
		clienteImovelOrdenado.addAll(clienteImovelSuprimido);
		clienteImovelOrdenado.addAll(clienteImovelSuprimidoParcial);
		clienteImovelOrdenado.addAll(clienteImovelSuprimidoParcialPedido);
		clienteImovelOrdenado.addAll(clienteImovelFactivel);
		clienteImovelOrdenado.addAll(clienteImovelPotencial);
	
		return clienteImovelOrdenado;
	}

	private Collection<ClienteImovel> pesquisarImoveisPorCliente(Cliente cliente) {
		Filtro filtro = new FiltroClienteImovel();
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_ID, cliente.getId()));
		filtro.adicionarParametro(new ParametroSimples(FiltroClienteImovel.CLIENTE_RELACAO_TIPO, ClienteRelacaoTipo.USUARIO));
		filtro.adicionarParametro(new ParametroNulo(FiltroClienteImovel.DATA_FIM_RELACAO));
		filtro.adicionarCaminhoParaCarregamentoEntidade(FiltroClienteImovel.IMOVEL);

		return getFachada().pesquisar(filtro, ClienteImovel.class.getName());
	}
	
    private Collection <ImovelSubcategoria> pesquisarCategoria (Integer idImovel){
         Filtro filtro = new FiltroImovelSubCategoria();
         filtro.adicionarParametro(new ParametroSimples(FiltroImovelSubCategoria.IMOVEL_ID, idImovel));
    	   	
    	return getFachada().pesquisar(filtro, ImovelSubcategoria.class.getName());
    }

	private void inserirClienteCadastradoNaReceita(
			Usuario usuario, 
			ConsultaCdl clienteCadastradoNaReceita,
			Cliente cliente,
			String confirmado, 
			ConsultaCdl consultaCdl, 
			short codigoAcao) {
		
		if ((confirmado != null) || (clienteCadastradoNaReceita.getCodigoAcaoOperador() != null && clienteCadastradoNaReceita.getCodigoAcaoOperador() == 3)) {

			if (consultaCdl == null) {
				ConsultaCdl cadastro = (ConsultaCdl) sessao.getAttribute("clienteCadastradoNaReceita");

				cadastro.setCodigoAcaoOperador(codigoAcao);
				cadastro.setCodigoCliente(cliente);
				cadastro.setUsuario(usuario);
				cadastro.setCpfUsuario(usuario.getCpf());
				cadastro.setLoginUsuario(usuario.getLogin());
				cadastro.setUltimaAlteracao(new Date());

				this.getFachada().inserir(cadastro);
			}
		}
	}

	private void limparSessao() {
		sessao.removeAttribute("clienteCadastradoNaReceita");
		sessao.removeAttribute("colecaoClienteFone");
		sessao.removeAttribute("colecaoEnderecos");
		sessao.removeAttribute("foneTipos");
		sessao.removeAttribute("municipios");
		sessao.removeAttribute("colecaoResponsavelSuperiores");
		sessao.removeAttribute("InserirEnderecoActionForm");
		sessao.removeAttribute("ClienteActionForm");
		sessao.removeAttribute("tipoPesquisaRetorno");
		sessao.removeAttribute("clienteAtualizacao");
	}
	
	/** 
	 * Verifica se a funcionalidade esta sendo executada dentro de um popup
	 */
	private boolean verificarExibicaoTelaSucesso(
			ActionMapping actionMapping,
			Cliente clienteAtualizacao,
			String nome,
			String cpf,
			String cnpj) {
		
		boolean exibirTelaSucesso = true;
		
		if (sessao.getAttribute("POPUP") != null) {
			if (sessao.getAttribute("POPUP").equals("true")) {
				retorno = actionMapping.findForward("atualizarClientePopUp");
				sessao.setAttribute("codigoCliente", clienteAtualizacao.getId());
				sessao.setAttribute("nomeCliente", nome);
				
				if (cpf != null) {
					sessao.setAttribute("cpfCnpjCliente", Util.formatarCpf(cpf));
				} else if (cnpj != null) {
					sessao.setAttribute("cpfCnpjCliente", Util.formatarCnpj(cnpj));
				}

				request.setAttribute("colecaoTipoPessoa", null);
				exibirTelaSucesso = false;
			}
		}
		
		return exibirTelaSucesso;
	}

	private void montarTelaSucesso(HttpServletRequest request, ActionForward retorno, Cliente clienteAtualizacao, boolean imovelPerfilAtualizado, Short indicadorbolsaFamilia) {
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {

			String linkSucesso = (String) sessao.getAttribute("linkSucesso");
			String mensagemSucesso = "Cliente de código " + clienteAtualizacao.getId() + " atualizado com sucesso.";
			
			if (imovelPerfilAtualizado) {
				if(!indicadorbolsaFamilia.equals(CadastroUnico.NAO_TEM_NIS)) {
					mensagemSucesso += " Perfil do Imóvel relacionado ao cliente atualizado para BOLSA ÁGUA.";
				} /* else {
					mensagemSucesso += " Perfil do Imóvel relacionado ao cliente atualizado para NORMAL.";
				}		*/			
			} else {
				mensagemSucesso += " Não foi possível atualizar Perfil de Imóvel relacionado ao cliente. ";
			}

			if (linkSucesso != null && !linkSucesso.equals("")) {

				montarPaginaSucesso(request, mensagemSucesso, 
						"Realizar outra Manutenção de Cliente",
						"exibirManterClienteAction.do?menu=sim", 
						linkSucesso, 
						"Retornar ao Consultar Imóvel.");

				sessao.removeAttribute("linkSucesso");

			} else if (sessao.getAttribute("caminhoVoltarPromais") != null) {

				montarPaginaSucesso(request, mensagemSucesso, 
						"Realizar outra Manutenção de Cliente",
						"exibirManterClienteAction.do?menu=sim",
						(String) sessao.getAttribute("caminhoVoltarPromais") + ".do?promais=nao",
						"Retornar ao Consultar Imóvel.");

				sessao.setAttribute("promaisExecutado", "sim");
				sessao.setAttribute("idImovelPromaisExecutado", Integer.parseInt((String) sessao.getAttribute("idImovel")));
				sessao.removeAttribute("idImovel");
				sessao.removeAttribute("caminhoVoltarPromais");

			} else {
				montarPaginaSucesso(request,
						mensagemSucesso,
						"Realizar outra Manutenção de Cliente",
						"exibirManterClienteAction.do?menu=sim");
			}
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
		} else {
			retorno = colecaoEnderecos;
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
		} else {
			retorno = colecaoFones;
		}

		return retorno;
	}

	private ActionForward montaTelaAtencao(ActionMapping actionMapping, String chave, boolean naoExibirBotaoVoltarTelaAtencao) {
		request.setAttribute("naoExibirBotaoVoltarTelaAtencao", naoExibirBotaoVoltarTelaAtencao);
		reportarErros(request, chave);

		return actionMapping.findForward("telaAtencao");
	}
	
	private void isNisValido(String numeroNIS, Short tipoPessoa) {
		if(numeroNIS.trim().length() != 11) {
        	throw new ActionServletException("atencao.erro_tamanho_do_nis_invalido");
		}
		if (!this.getFachada().pesquisarNisJaCadastradoInserirCliente(numeroNIS)) {
			throw new ActionServletException("atencao.erro_nis_ja_cadastrado");
		}
		if (!tipoPessoa.equals(ClienteTipo.INDICADOR_PESSOA_FISICA) && !numeroNIS.equals("")) {
			throw new ActionServletException("atencao.erro_pessoa_juridica_com_nis");
		}
	}
	
}
