package gcom.gui.cadastro.sistemaparametro;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirInformarParametrosSistemaDadosGeraisEmpresaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirInformarParametrosSistemaDadosGeraisEmpresa");

		InformarSistemaParametrosActionForm form = (InformarSistemaParametrosActionForm) actionForm;

		HttpSession sessao = this.getSessao(httpServletRequest);

		SistemaParametro sistemaParametro = (SistemaParametro) sessao.getAttribute("sistemaParametro");

		Fachada fachada = Fachada.getInstancia();

		// Seta os dados no form
		if (httpServletRequest.getParameter("menu") != null) {

			form.setNomeEstado(sistemaParametro.getNomeEstado());
			form.setNomeEmpresa(sistemaParametro.getNomeEmpresa());
			form.setAbreviaturaEmpresa(sistemaParametro.getNomeAbreviadoEmpresa());
			form.setCnpj(sistemaParametro.getCnpjEmpresa());

			if (sistemaParametro.getNumeroImovel() != null) {
				form.setNumero(sistemaParametro.getNumeroImovel());
			}

			form.setQuantidadeDigitosQuadra(sistemaParametro.getNumeroDigitosQuadra() + "");

			if (sistemaParametro.getComplementoEndereco() != null) {
				form.setComplemento(sistemaParametro.getComplementoEndereco());
			}

			if (sistemaParametro.getDddTelefone() != null) {
				form.setDddTelefone(sistemaParametro.getDddTelefone());
			}
			
			if (sistemaParametro.getNumeroTelefone() != null) {
				form.setNumeroTelefone(sistemaParametro.getNumeroTelefone());
			}

			if (sistemaParametro.getNumeroRamal() != null) {
				form.setRamal(sistemaParametro.getNumeroRamal());
			}

			if (sistemaParametro.getNumeroFax() != null) {
				form.setFax(sistemaParametro.getNumeroFax());
			}

			if (sistemaParametro.getDescricaoEmail() != null) {
				form.setEmail(sistemaParametro.getDescricaoEmail());
			}

			if (sistemaParametro.getTituloPagina() != null) {
				form.setTitulosRelatorio(sistemaParametro.getTituloPagina());
			}

			if (sistemaParametro.getUnidadeOrganizacionalIdPresidencia() != null) {
				form.setUnidadeOrganizacionalPresidencia(sistemaParametro.getUnidadeOrganizacionalIdPresidencia().getId().toString());

				form.setNomeUnidadeOrganizacionalPresidencia(sistemaParametro.getUnidadeOrganizacionalIdPresidencia().getDescricao());
			}

			if (sistemaParametro.getClientePresidente() != null) {
				form.setPresidente(sistemaParametro.getClientePresidente().getId().toString());

				form.setNomePresidente(sistemaParametro.getClientePresidente().getDescricao());
			}

			if (sistemaParametro.getClienteDiretorComercial() != null) {
				form.setDiretorComercial(sistemaParametro.getClienteDiretorComercial().getId().toString());

				form.setNomeDiretorComercial(sistemaParametro.getClienteDiretorComercial().getDescricao());
			}

			if (sistemaParametro.getNumero0800Empresa() != null) {
				form.setNumeroTelefoneAtendimento(sistemaParametro.getNumero0800Empresa());
			}

			if (sistemaParametro.getNomeSiteEmpresa() != null) {
				form.setSite(sistemaParametro.getNomeSiteEmpresa());
			}

			if (sistemaParametro.getInscricaoEstadual() != null) {
				form.setInscricaoEstadual(sistemaParametro.getInscricaoEstadual());
			}

			if (sistemaParametro.getInscricaoMunicipal() != null) {
				form.setInscricaoMunicipal(sistemaParametro.getInscricaoMunicipal());
			}

			if (sistemaParametro.getNumeroContratoPrestacaoServico() != null) {
				form.setNumeroContrato(sistemaParametro.getNumeroContratoPrestacaoServico().toString());
			}

			if (sistemaParametro.getImagemLogomarca() != null) {
				form.setImagemLogomarca(sistemaParametro.getImagemLogomarca());
			}

			if (sistemaParametro.getImagemRelatorio() != null) {
				form.setImagemRelatorio(sistemaParametro.getImagemRelatorio());
			}

			if (sistemaParametro.getImagemConta() != null) {
				form.setImagemConta(sistemaParametro.getImagemConta());
			}

			if (sistemaParametro.getNumeroExecucaoResumoNegativacao() != null) {
				form.setNumeroExecucaoResumoNegativacao(sistemaParametro.getNumeroExecucaoResumoNegativacao().toString());
			}

			if (sistemaParametro.getVersaoCelular() != null) {
				form.setVersaoCelular(sistemaParametro.getVersaoCelular());
			}

			if (sistemaParametro.getNumeroDiasBloqueioCelular() != null) {
				form.setNumeroDiasBloqueioCelular(sistemaParametro.getNumeroDiasBloqueioCelular().toString());
			}

			if (sistemaParametro.getPercentualConvergenciaRepavimentacao() != null) {
				form.setPercentualConvergenciaRepavimentacao(Util.formatarBigDecimalParaStringComVirgula(sistemaParametro
						.getPercentualConvergenciaRepavimentacao()));
			}

			form.setIndicadorControlaAutoInfracao("" + sistemaParametro.getIndicadorControlaAutoInfracao());

			form.setIndicadorExibirMensagem("" + sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento());

			form.setIndicadorUsaRota("" + sistemaParametro.getIndicadorUsaRota());

			form.setIndicadorDuplicidadeCliente(sistemaParametro.getIndicadorDuplicidadeCliente().toString());
			form.setIndicadorNomeMenorDez(sistemaParametro.getIndicadorNomeMenorDez().toString());
			form.setIndicadorNomeClienteGenerico(sistemaParametro.getIndicadorNomeClienteGenerico().toString());

			this.pesquisarEndereco(sistemaParametro, httpServletRequest);

			if (sistemaParametro.getIndicadorVariaHierarquiaUnidade() != null) {
				form.setIndicadorVariaHierarquiaUnidade(sistemaParametro.getIndicadorVariaHierarquiaUnidade().toString());
			}

			if (sistemaParametro.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados() != null) {
				form.setClienteFicticioAssociarPagamentosNaoIdentificados(sistemaParametro.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados().getId()
						.toString());

				form.setNomeClienteFicticioAssociarPagamentosNaoIdentificados(sistemaParametro.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados()
						.getDescricao());

				httpServletRequest.setAttribute("clienteFicticioEncontrado", true);
			}

			if (sistemaParametro.getPerfilProgramaEspecial() != null) {
				form.setPerfilProgramaEspecial(sistemaParametro.getPerfilProgramaEspecial().getId().toString());
			}

			if (sistemaParametro.getClienteResponsavelProgramaEspecial() != null) {

				FiltroCliente filtroCliente = new FiltroCliente();

				filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, sistemaParametro.getClienteResponsavelProgramaEspecial().getId()));

				Collection<Cliente> colecaoClientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());

				Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoClientes);

				if (cliente != null) {
					form.setIdClienteResponsavelProgramaEspecial(cliente.getId().toString());
					form.setNomeClienteResponsavelProgramaEspecial(cliente.getNome());
					httpServletRequest.setAttribute("codigoClienteEncontrado", "true");
				}
			}

			if (sistemaParametro.getIndicadorPopupAtualizacaoCadastral() != null) {
				form.setIndicadorPopupAtualizacaoCadastral(sistemaParametro.getIndicadorPopupAtualizacaoCadastral().toString());
			}

			if (sistemaParametro.getValorExtratoFichaComp() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro.getValorExtratoFichaComp());

				form.setValorExtratoFichaComp(valorAux);
			}

			if (sistemaParametro.getValorGuiaFichaComp() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro.getValorGuiaFichaComp());

				form.setValorGuiaFichaComp(valorAux);
			}

			if (sistemaParametro.getValorDemonstrativoParcelamentoFichaComp() != null) {

				String valorAux = Util.formatarMoedaReal(sistemaParametro.getValorDemonstrativoParcelamentoFichaComp());

				form.setValorDemonstrativoParcelamentoFichaComp(valorAux);
			}

			if (sistemaParametro.getIndicadorUsoNMCliReceitaFantasia() != null) {
				form.setIndicadorUsoNMCliReceitaFantasia(sistemaParametro.getIndicadorUsoNMCliReceitaFantasia().toString());
			}

			if (sistemaParametro.getNumeroMaximoParcelasContratosParcelamento() != null
					&& (form.getNumeroMaximoParcelasContratosParcelamento() == null || form.getNumeroMaximoParcelasContratosParcelamento().equals(""))) {
				form.setNumeroMaximoParcelasContratosParcelamento(sistemaParametro.getNumeroMaximoParcelasContratosParcelamento().toString());
			}

		}

		// Flag indicando que o usuário fez uma consulta a partir da tecla Enter
		String objetoConsulta = httpServletRequest.getParameter("objetoConsulta");

		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("1")) {

			// Faz a consulta de Unidade Organizacional
			this.pesquisarUnidadeOrganizacional(form, httpServletRequest);
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("2")) {

			// Faz a consulta de Cliente - Presidente
			this.pesquisarCliente(form, true, httpServletRequest);
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("3")) {

			// Faz a consulta de Cliente - Diretor Comercial
			this.pesquisarCliente(form, false, httpServletRequest);
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("6")) {

			// Faz a consulta de Cliente - Diretor Comercial
			this.pesquisarClientePrograma(form, httpServletRequest);
		}

		if (objetoConsulta != null && !objetoConsulta.trim().equals("") && objetoConsulta.trim().equals("7")) {

			// Faz a consulta de Cliente - Cliente Fictício
			this.pesquisarClienteFicticio(form, httpServletRequest);
		}

		form.setIndicadorCpfCnpj("" + sistemaParametro.getIndicadorConsultaDocumentoReceita());

		if (httpServletRequest.getParameter("pesquisarCliente") != null
				&& httpServletRequest.getParameter("pesquisarCliente").toString().equalsIgnoreCase("sim")) {

			this.setaRequest(httpServletRequest, form);
			this.montarEndereco(form, httpServletRequest);
			this.carregarColecaoPerfisImovel(form, httpServletRequest);

			// Faz a consulta de Cliente ResponsavelProgramaEspecial
			form.setIdClienteResponsavelProgramaEspecial(httpServletRequest.getParameter("codigoCliente").toString());

			this.pesquisarClientePrograma(form, httpServletRequest);
		}

		form.setIndicadorDocumentoObrigatorio("" + sistemaParametro.getIndicadorDocumentoObrigatorio());

		this.setaRequest(httpServletRequest, form);
		this.montarEndereco(form, httpServletRequest);
		this.carregarColecaoPerfisImovel(form, httpServletRequest);

		return retorno;
	}

	private void pesquisarEndereco(SistemaParametro sistemaParametro, HttpServletRequest httpServletRequest) {

		if (this.getSessao(httpServletRequest).getAttribute("colecaoEnderecos") == null) {

			Imovel imovel = new Imovel();

			// Pesquisa o Logradouro Cep
			if (sistemaParametro.getLogradouroCep() != null) {
				FiltroLogradouroCep filtroLogradouroCep = new FiltroLogradouroCep();
				filtroLogradouroCep.adicionarParametro(new ParametroSimples(FiltroLogradouroCep.ID, sistemaParametro.getLogradouroCep().getId()));

				filtroLogradouroCep.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTipo");
				filtroLogradouroCep.adicionarCaminhoParaCarregamentoEntidade("logradouro.logradouroTitulo");
				filtroLogradouroCep.adicionarCaminhoParaCarregamentoEntidade("cep");

				Collection colecaoLogradouroCep = this.getFachada().pesquisar(filtroLogradouroCep, LogradouroCep.class.getName());

				LogradouroCep logradouroCep = (LogradouroCep) Util.retonarObjetoDeColecao(colecaoLogradouroCep);

				imovel.setLogradouroCep(logradouroCep);
			}

			// Pesquisa o Logradouro Bairro
			if (sistemaParametro.getLogradouroBairro() != null) {
				FiltroLogradouroBairro filtroLogradouroBairro = new FiltroLogradouroBairro();
				filtroLogradouroBairro.adicionarParametro(new ParametroSimples(FiltroLogradouroBairro.ID, sistemaParametro.getLogradouroBairro().getId()));

				filtroLogradouroBairro.adicionarCaminhoParaCarregamentoEntidade("bairro.municipio.unidadeFederacao");

				Collection colecaoLogradouroBairro = this.getFachada().pesquisar(filtroLogradouroBairro, LogradouroBairro.class.getName());

				LogradouroBairro logradouroBairro = (LogradouroBairro) Util.retonarObjetoDeColecao(colecaoLogradouroBairro);

				imovel.setLogradouroBairro(logradouroBairro);

			}

			imovel.setEnderecoReferencia(sistemaParametro.getEnderecoReferencia());
			imovel.setNumeroImovel(sistemaParametro.getNumeroImovel());
			imovel.setComplementoEndereco(sistemaParametro.getComplementoEndereco());

			Collection colecaoEndereco = new ArrayList();
			colecaoEndereco.add(imovel);

			this.getSessao(httpServletRequest).setAttribute("colecaoEnderecos", colecaoEndereco);
		}
	}

	private void montarEndereco(InformarSistemaParametrosActionForm form, HttpServletRequest httpServletRequest) {

		// Removendo endereço
		String removerEndereco = httpServletRequest.getParameter("removerEndereco");
		HttpSession sessao = this.getSessao(httpServletRequest);

		if (removerEndereco != null && !removerEndereco.trim().equalsIgnoreCase("")) {

			if (sessao.getAttribute("colecaoEnderecos") != null) {

				Collection enderecos = (Collection) sessao.getAttribute("colecaoEnderecos");

				if (!enderecos.isEmpty()) {
					sessao.removeAttribute("colecaoEnderecos");
				}
			}
		}

		// Caso tenha adicionado o endereço seta os valores dos campos de
		// municipio e bairro
		if (sessao.getAttribute("colecaoEnderecos") != null) {

			Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");

			if (!colecaoEnderecos.isEmpty()) {

				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoEnderecos);

				if (imovel.getLogradouroBairro() != null) {
					form.setLogradouroBairro(imovel.getLogradouroBairro().getId().toString());
				}

				if (imovel.getLogradouroBairro() != null) {
					form.setLogradouroCep(imovel.getLogradouroCep().getId().toString());
				}
				if (imovel.getEnderecoReferencia() != null) {
					form.setEnderecoReferencia(imovel.getEnderecoReferencia().getId().toString());
				}
				form.setNumero(imovel.getNumeroImovel());
				form.setComplemento(imovel.getComplementoEndereco());

			}

		}
	}

	private void pesquisarUnidadeOrganizacional(InformarSistemaParametrosActionForm form, HttpServletRequest httpServletRequest) {

		// Filtro para obter unidade organizacional ativo de id informado
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();

		String idUnidade = form.getUnidadeOrganizacionalPresidencia();

		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, idUnidade));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoUnidade = Fachada.getInstancia().pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoUnidade != null && !colecaoUnidade.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			UnidadeOrganizacional unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidade);

			// Exibe o código e a descrição pesquisa na página
			httpServletRequest.setAttribute("unidadeOrganizacionalPresidenciaInexistente", "true");

			form.setUnidadeOrganizacionalPresidencia(unidadeOrganizacional.getId().toString());
			form.setNomeUnidadeOrganizacionalPresidencia(unidadeOrganizacional.getDescricao());

		} else {

			form.setNomeUnidadeOrganizacionalPresidencia("Unidade Organizacional inexistente");
			form.setUnidadeOrganizacionalPresidencia(null);

		}
	}

	private void pesquisarCliente(InformarSistemaParametrosActionForm form, boolean isPresidente, HttpServletRequest httpServletRequest) {

		String codigoCliente = null;

		if (isPresidente) {
			codigoCliente = form.getPresidente();
		} else {
			codigoCliente = form.getDiretorComercial();
		}
		FiltroCliente filtroCliente = new FiltroCliente();

		filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, new Integer(codigoCliente)));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoCliente = this.getFachada().pesquisar(filtroCliente, Cliente.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);

			if (isPresidente) {
				httpServletRequest.setAttribute("presidenteEncontrado", "true");

				form.setPresidente(cliente.getId().toString());
				form.setNomePresidente(cliente.getNome());
			} else {
				httpServletRequest.setAttribute("diretorComercialEncontrado", "true");

				form.setDiretorComercial(cliente.getId().toString());
				form.setNomeDiretorComercial(cliente.getNome());
			}

		} else {
			if (isPresidente) {
				form.setPresidente(null);
				form.setNomePresidente("Cliente inexistente");
			} else {
				form.setDiretorComercial(null);
				form.setNomeDiretorComercial("Cliente inexistente");
			}
		}
	}

	private void pesquisarClienteFicticio(InformarSistemaParametrosActionForm form, HttpServletRequest httpServletRequest) {

		String codigoClienteFicticio = null;

		codigoClienteFicticio = form.getClienteFicticioAssociarPagamentosNaoIdentificados();
		FiltroCliente filtroClienteFicticio = new FiltroCliente();

		filtroClienteFicticio.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID, new Integer(codigoClienteFicticio)));

		// Pesquisa de acordo com os parâmetros informados no filtro
		Collection colecaoClienteFicticio = this.getFachada().pesquisar(filtroClienteFicticio, Cliente.class.getName());

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoClienteFicticio != null && !colecaoClienteFicticio.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoClienteFicticio);

			httpServletRequest.setAttribute("clienteFicticioEncontrado", true);

			form.setClienteFicticioAssociarPagamentosNaoIdentificados(cliente.getId().toString());
			form.setNomeClienteFicticioAssociarPagamentosNaoIdentificados(cliente.getNome());
		} else {
			httpServletRequest.setAttribute("clienteFicticioEncontrado", false);
			form.setClienteFicticioAssociarPagamentosNaoIdentificados(null);
			form.setNomeClienteFicticioAssociarPagamentosNaoIdentificados("Cliente inexistente");
		}
	}

	private void setaRequest(HttpServletRequest httpServletRequest, InformarSistemaParametrosActionForm form) {

		// Unidade OrganizacionalPresidencia
		if (form.getUnidadeOrganizacionalPresidencia() != null && !form.getUnidadeOrganizacionalPresidencia().equals("")
				&& form.getNomeUnidadeOrganizacionalPresidencia() != null && !form.getNomeUnidadeOrganizacionalPresidencia().equals("")) {

			httpServletRequest.setAttribute("unidadeOrganizacionalPresidenciaEncontrada", "true");
		}

		// Presidente
		if (form.getPresidente() != null && !form.getPresidente().equals("") && form.getNomePresidente() != null && !form.getNomePresidente().equals("")) {

			httpServletRequest.setAttribute("presidenteEncontrado", "true");
		}

		// Diretor Comercial
		if (form.getDiretorComercial() != null && !form.getDiretorComercial().equals("") && form.getNomeDiretorComercial() != null
				&& !form.getNomeDiretorComercial().equals("")) {

			httpServletRequest.setAttribute("diretorComercialEncontrado", "true");
		}
		// Cliente Ficticio
		if (form.getClienteFicticioAssociarPagamentosNaoIdentificados() != null && !form.getClienteFicticioAssociarPagamentosNaoIdentificados().equals("")
				&& form.getNomeClienteFicticioAssociarPagamentosNaoIdentificados() != null
				&& !form.getNomeClienteFicticioAssociarPagamentosNaoIdentificados().equals("")) {

			httpServletRequest.setAttribute("clienteFicticioEncontrado", "true");
		}

	}

	private void pesquisarClientePrograma(InformarSistemaParametrosActionForm form, HttpServletRequest httpServletRequest) {

		FiltroCliente filtroCliente = new FiltroCliente();
		Collection colecaoCliente = null;

		if (form.getIdClienteResponsavelProgramaEspecial() != null && !form.getIdClienteResponsavelProgramaEspecial().equals("")) {
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, new Integer(form.getIdClienteResponsavelProgramaEspecial())));

			// Pesquisa de acordo com os parâmetros informados no filtro
			colecaoCliente = this.getFachada().pesquisar(filtroCliente, Cliente.class.getName());

		}

		// Verifica se a pesquisa retornou algum objeto para a coleção
		if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

			// Obtém o objeto da coleção pesquisada
			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);

			httpServletRequest.setAttribute("codigoClienteEncontrado", "true");

			form.setIdClienteResponsavelProgramaEspecial(cliente.getId().toString());
			form.setNomeClienteResponsavelProgramaEspecial(cliente.getNome());

		} else {
			httpServletRequest.removeAttribute("codigoClienteEncontrado");
			form.setIdClienteResponsavelProgramaEspecial("");
			form.setNomeClienteResponsavelProgramaEspecial("Cliente inexistente");

		}
	}

	private void carregarColecaoPerfisImovel(InformarSistemaParametrosActionForm form, HttpServletRequest httpServletRequest) {

		FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
		filtroImovelPerfil.setConsultaSemLimites(true);
		filtroImovelPerfil.setCampoOrderBy(FiltroImovelPerfil.DESCRICAO);
		filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));

		Collection<ImovelPerfil> colecaoImoveisPerfis = this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

		httpServletRequest.setAttribute("colecaoPerfisImovel", colecaoImoveisPerfis);
	}
}
