package gcom.gui.cadastro.sistemaparametro;

import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.endereco.FiltroLogradouroBairro;
import gcom.cadastro.endereco.FiltroLogradouroCep;
import gcom.cadastro.endereco.LogradouroBairro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirConsultarParametrosSistemaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("consultarParametrosSistema");

		ConsultarParametrosSistemaActionForm consultarSistemaParametroActionForm = (ConsultarParametrosSistemaActionForm) actionForm;

		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();

		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("logradouro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("hidrometroCapacidade");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("cep");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("contaBancaria");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clientePresidente");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("unidadeOrganizacionalIdPresidencia");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteDiretorComercial");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteFicticioParaAssociarOsPagamentosNaoIdentificados");
		filtroSistemaParametro.adicionarCaminhoParaCarregamentoEntidade("clienteResponsavelNegativacao");

		Collection colecaoSistemaParametro = this.getFachada().pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());

		SistemaParametro sistemaParametro = (SistemaParametro) colecaoSistemaParametro.iterator().next();

		// 1ª Tabela
		this.montarSistemaParametro1Tabela(consultarSistemaParametroActionForm, sistemaParametro);

		// 2ª Tabela
		this.montarSistemaParametro2Tabela(consultarSistemaParametroActionForm, sistemaParametro);

		// 3ª Tabela
		this.montarSistemaParametro3Tabela(consultarSistemaParametroActionForm, sistemaParametro);

		// 4ª Tabela
		this.montarSistemaParametro4Tabela(consultarSistemaParametroActionForm, sistemaParametro);

		// 5ª Tabela
		this.montarSistemaParametro5Tabela(consultarSistemaParametroActionForm, sistemaParametro);

		this.setarDownloadsLoja(consultarSistemaParametroActionForm, httpServletRequest);

		if (httpServletRequest.getParameter("modo") != null && !httpServletRequest.getParameter("modo").equals("")) {
			// Retorna o arquivo do decreto
			if (httpServletRequest.getParameter("modo").equals("verDecreto")) {
				this.retornaArquivo("decreto", httpServletResponse, sistemaParametro);
			}
			// Retorna o arquivo de lei de tarifa
			if (httpServletRequest.getParameter("modo").equals("verLeiTarifa")) {
				this.retornaArquivo("leiTarifa", httpServletResponse, sistemaParametro);
			}
			// retorna o arquivo de lei de Normas de Medição
			if (httpServletRequest.getParameter("modo").equals("verLeiNormaMedicao")) {
				this.retornaArquivo("leiNormaMedicao", httpServletResponse, sistemaParametro);
			}
			// Retorna o arquivo de Norma CO
			if (httpServletRequest.getParameter("modo").equals("verNormaCO")) {
				this.retornaArquivo("normaCO", httpServletResponse, sistemaParametro);
			}
			// Retorna o arquivo de Norma CM
			if (httpServletRequest.getParameter("modo").equals("verNormaCM")) {
				this.retornaArquivo("normaCM", httpServletResponse, sistemaParametro);
			}

		}

		this.pesquisarEndereco(sistemaParametro, httpServletRequest);
		this.montarEndereco(consultarSistemaParametroActionForm, httpServletRequest);

		FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
		filtroContaBancaria.setCampoOrderBy(FiltroContaBancaria.ID);

		Collection<ContaBancaria> colecaoContaBancaria = this.getFachada().pesquisar(filtroContaBancaria, ContaBancaria.class.getName());

		if (colecaoContaBancaria == null || colecaoContaBancaria.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null, "Tabela Conta Bancaria");
		}

		httpServletRequest.setAttribute("colecaoContaBancaria", colecaoContaBancaria);

		return retorno;
	}

	private void montarSistemaParametro1Tabela(ConsultarParametrosSistemaActionForm form, SistemaParametro sistemaParametro) {

		Fachada fachada = Fachada.getInstancia();

		form.setNomeEstado(sistemaParametro.getNomeEstado());
		form.setNomeEmpresa(sistemaParametro.getNomeEmpresa());
		form.setAbreviaturaEmpresa(sistemaParametro.getNomeAbreviadoEmpresa());
		form.setCnpj(sistemaParametro.getCnpjEmpresa());

		if (sistemaParametro.getNumeroImovel() != null) {
			form.setNumero(sistemaParametro.getNumeroImovel());
		}

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

		if (sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento() != null) {
			form.setIndicadorExibirMensagem(sistemaParametro.getIndicadorExibeMensagemNaoReceberPagamento().toString());
		}
		if (sistemaParametro.getClienteResponsavelProgramaEspecial() != null) {

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, sistemaParametro.getClienteResponsavelProgramaEspecial().getId()));

			Collection<Cliente> colecaoClientes = fachada.pesquisar(filtroCliente, Cliente.class.getName());

			Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoClientes);

			if (cliente != null) {
				form.setIdClienteResponsavelPrograma(cliente.getId().toString());
				form.setNomeClienteResponsavelPrograma(cliente.getNome());
			}
		}
		if (sistemaParametro.getPerfilProgramaEspecial() != null) {

			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();

			filtroImovelPerfil.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID, sistemaParametro.getPerfilProgramaEspecial().getId()));

			Collection<ImovelPerfil> colecaoImoveisPerfis = this.getFachada().pesquisar(filtroImovelPerfil, ImovelPerfil.class.getName());

			ImovelPerfil perfilPrograma = (ImovelPerfil) Util.retonarObjetoDeColecao(colecaoImoveisPerfis);

			form.setPerfilProgramaEspecial(perfilPrograma.getDescricao());
		}

		// Percentual de Convergencia Repavimentacao
		if (sistemaParametro.getPercentualConvergenciaRepavimentacao() != null) {
			form.setPercentualConvergenciaRepavimentacao(Util.formatarBigDecimalParaStringComVirgula(sistemaParametro.getPercentualConvergenciaRepavimentacao()));
		}

		if (sistemaParametro.getIndicadorPopupAtualizacaoCadastral() != null) {
			form.setIndicadorPopupAtualizacaoCadastral(sistemaParametro.getIndicadorPopupAtualizacaoCadastral().toString());
		}

		if (sistemaParametro.getValorGuiaFichaComp() != null) {
			form.setValorGuiaFichaComp(Util.formatarMoedaReal(sistemaParametro.getValorGuiaFichaComp()));
		}

		if (sistemaParametro.getValorDemonstrativoParcelamentoFichaComp() != null) {
			form.setValorDemonstrativoParcelamentoFichaComp(Util.formatarMoedaReal(sistemaParametro.getValorDemonstrativoParcelamentoFichaComp()));
		}

		if (sistemaParametro.getIndicadorVariaHierarquiaUnidade() != null) {
			form.setIndicadorVariaHierarquiaUnidade(sistemaParametro.getIndicadorVariaHierarquiaUnidade().toString());
		}

		if (sistemaParametro.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados() != null) {
			form.setClienteFicticioAssociarPagamentosNaoIdentificados(sistemaParametro.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados().getId()
					.toString());

			form.setNomeClienteFicticioAssociarPagamentosNaoIdentificados(sistemaParametro.getClienteFicticioParaAssociarOsPagamentosNaoIdentificados()
					.getDescricao());
		}

		if (sistemaParametro.getIndicadorUsoNMCliReceitaFantasia() != null) {
			form.setIndicadorUsoNMCliReceitaFantasia(sistemaParametro.getIndicadorUsoNMCliReceitaFantasia().toString());
		}

		form.setIndicadorControlaAutoInfracao("" + sistemaParametro.getIndicadorControlaAutoInfracao());

		form.setIndicadorUsaRota("" + sistemaParametro.getIndicadorUsaRota());
		form.setIndicadorDocumentoObrigatorio("" + sistemaParametro.getIndicadorDocumentoObrigatorio());
		form.setIndicadorConsultaSpc("" + sistemaParametro.getIndicadorConsultaDocumentoReceita());

		if (sistemaParametro.getValorExtratoFichaComp() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro.getValorExtratoFichaComp());

			form.setValorExtratoFichaComp(valorAux);
		}

		if (sistemaParametro.getNumeroDiasBloqueioCelular() != null) {

			form.setNumeroDiasBloqueioCelular(sistemaParametro.getNumeroDiasBloqueioCelular().toString());
		}

		if (sistemaParametro.getUltimoDiaVencimentoAlternativo() != null) {

			form.setUltimoDiaVencimentoAlternativo(sistemaParametro.getUltimoDiaVencimentoAlternativo().toString());
		}

	}

	private void montarSistemaParametro2Tabela(ConsultarParametrosSistemaActionForm form, SistemaParametro sistemaParametro) {

		String anoMesFaturamento = Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesFaturamento().toString());

		form.setMesAnoReferencia(anoMesFaturamento);
		form.setMenorConsumo(sistemaParametro.getMenorConsumoGrandeUsuario().toString());

		if (sistemaParametro.getValorMinimoEmissaoConta() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro.getValorMinimoEmissaoConta());

			form.setMenorValor(valorAux);
		}

		form.setQtdeEconomias(sistemaParametro.getMenorEconomiasGrandeUsuario().toString());

		if (sistemaParametro.getMesesMediaConsumo() != null) {
			form.setMesesCalculoMedio(sistemaParametro.getMesesMediaConsumo().toString());
		}

		if (sistemaParametro.getNumeroMinimoDiasEmissaoVencimento() != null) {
			form.setDiasMinimoVencimento(sistemaParametro.getNumeroMinimoDiasEmissaoVencimento().toString());
		}

		if (sistemaParametro.getNumeroDiasAdicionaisCorreios() != null) {
			form.setDiasMinimoVencimentoCorreio(sistemaParametro.getNumeroDiasAdicionaisCorreios().toString());
		}

		if (sistemaParametro.getNumeroMesesValidadeConta() != null) {
			form.setNumeroMesesValidadeConta(sistemaParametro.getNumeroMesesValidadeConta().toString());
		}

		if (sistemaParametro.getNumeroMesesMinimoAlteracaoVencimento() != null) {
			form.setNumeroMesesAlteracaoVencimento(sistemaParametro.getNumeroMesesMinimoAlteracaoVencimento().toString());
		}

		if (sistemaParametro.getNumeroMesesMaximoCalculoMedia() != null) {
			form.setNumeroMesesMaximoCalculoMedia(sistemaParametro.getNumeroMesesMaximoCalculoMedia().toString());
		}

		if (sistemaParametro.getNumeroMesesCalculoCorrecao() != null) {
			form.setNumeroMesesCalculoCorrecao(sistemaParametro.getNumeroMesesCalculoCorrecao().toString());
		}

		if (sistemaParametro.getNumeroVezesSuspendeLeitura() != null) {
			form.setNumeroVezesSuspendeLeitura(sistemaParametro.getNumeroVezesSuspendeLeitura().toString());
		}

		if (sistemaParametro.getNumeroMesesLeituraSuspensa() != null) {
			form.setNumeroMesesLeituraSuspensa(sistemaParametro.getNumeroMesesLeituraSuspensa().toString());
		}

		if (sistemaParametro.getNumeroMesesReinicioSitEspFaturamento() != null) {
			form.setNumeroMesesReinicioSitEspFatu(sistemaParametro.getNumeroMesesReinicioSitEspFaturamento().toString());
		}

		if (sistemaParametro.getIndicadorRoteiroEmpresa() != null) {
			form.setIndicadorRoteiroEmpresa(sistemaParametro.getIndicadorRoteiroEmpresa().toString());
		}

		if (sistemaParametro.getIndicadorLimiteAlteracaoVencimento() != null) {
			form.setIndicadorLimiteAlteracaoVencimento(sistemaParametro.getIndicadorLimiteAlteracaoVencimento().toString());
		}

		if (sistemaParametro.getIndicadorCalculaVencimento() != null) {
			form.setIndicadorCalculoVencimento(sistemaParametro.getIndicadorCalculaVencimento().toString());
		}

		if (sistemaParametro.getIndicadorTarifaCategoria() != null) {
			form.setIndicadorTarifaCategoria(sistemaParametro.getIndicadorTarifaCategoria().toString());
		}

		form.setIndicadorAtualizacaoTarifaria("" + sistemaParametro.getIndicadorAtualizacaoTarifaria());

		if (sistemaParametro.getAnoMesAtualizacaoTarifaria() != null) {

			String anoMes = Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesAtualizacaoTarifaria().toString());

			form.setMesAnoAtualizacaoTarifaria(anoMes);
		}

		if (sistemaParametro.getIndicadorFaturamentoAntecipado() != null) {
			form.setIndicadorFaturamentoAntecipado(sistemaParametro.getIndicadorFaturamentoAntecipado().toString());
		}

		if (sistemaParametro.getValorSalarioMinimo() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro.getValorSalarioMinimo());

			form.setSalarioMinimo(valorAux);
		}

		if (sistemaParametro.getAreaMaximaTarifaSocial() != null) {
			form.setAreaMaxima(sistemaParametro.getAreaMaximaTarifaSocial().toString());
		}

		if (sistemaParametro.getConsumoEnergiaMaximoTarifaSocial() != null) {
			form.setConsumoMaximo(sistemaParametro.getConsumoEnergiaMaximoTarifaSocial().toString());
		}

		if (sistemaParametro.getIndicadorTarifaCategoria() != null) {
			form.setConsumoMaximo(sistemaParametro.getConsumoEnergiaMaximoTarifaSocial().toString());
		}
		if (sistemaParametro.getIndicadorRetificacaoValorMenor() != null) {
			form.setIndicadorRetificacaoValorMenor("" + sistemaParametro.getIndicadorRetificacaoValorMenor());
		}

		if (sistemaParametro.getIndicadorTransferenciaComDebito() != null) {
			form.setIndicadorTransferenciaComDebito("" + sistemaParametro.getIndicadorTransferenciaComDebito());
		}

		if (sistemaParametro.getIndicadorNaoMedidoTarifa() != null) {
			form.setIndicadorNaoMedidoTarifa("" + sistemaParametro.getIndicadorNaoMedidoTarifa());
		}

		if (sistemaParametro.getIndicadorQuadraFace() != null) {
			form.setIndicadorQuadraFace("" + sistemaParametro.getIndicadorQuadraFace());
		}

		if (sistemaParametro.getNumeroDiasVariacaoConsumo() != null) {
			form.setNumeroDiasVariacaoConsumo(sistemaParametro.getNumeroDiasVariacaoConsumo().toString());
		}

		if (sistemaParametro.getNumeroDiasPrazoRecursoAutoInfracao() != null) {
			form.setNnDiasPrazoRecursoAutoInfracao(sistemaParametro.getNumeroDiasPrazoRecursoAutoInfracao().toString());
		}

		if (sistemaParametro.getDiasVencimentoAlternativo() != null) {
			form.setDiasVencimentoAlternativo(sistemaParametro.getDiasVencimentoAlternativo());
		}
		if (sistemaParametro.getIndicadorBloqueioContaMobile() != null) {
			form.setIndicadorBloqueioContaMobile(sistemaParametro.getIndicadorBloqueioContaMobile().toString());
		}

		if (sistemaParametro.getValorContaFichaComp() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro.getValorContaFichaComp());

			form.setValorContaFichaComp(valorAux);
		}

		if (sistemaParametro.getNumeroMesesRetificarConta() != null) {

			String valorAux = sistemaParametro.getNumeroMesesRetificarConta().toString();

			form.setNumeroMesesRetificarConta(valorAux);
		}

		if (sistemaParametro.getIndicadorNormaRetificacao() != null) {
			form.setIndicadorNormaRetificacao(sistemaParametro.getIndicadorNormaRetificacao().toString());
		}

		if (sistemaParametro.getMensagemContaBraile() != null) {
			form.setMensagemContaBraile(sistemaParametro.getMensagemContaBraile());
		}

		if (sistemaParametro.getCodigoTipoCalculoNaoMedido() != null) {
			if (sistemaParametro.getCodigoTipoCalculoNaoMedido().compareTo(new Integer(1)) == 0) {
				form.setCodigoTipoCalculoNaoMedido("AREA CONSTRUIDA");
			} else if (sistemaParametro.getCodigoTipoCalculoNaoMedido().compareTo(new Integer(2)) == 0) {
				form.setCodigoTipoCalculoNaoMedido("PONTOS DE UTILIZAÇÃO");
			} else if (sistemaParametro.getCodigoTipoCalculoNaoMedido().compareTo(new Integer(3)) == 0) {
				form.setCodigoTipoCalculoNaoMedido("NUMERO DE MORADORES");
			}
		}
	}
		
	private void montarSistemaParametro3Tabela(ConsultarParametrosSistemaActionForm form, SistemaParametro sistemaParametro) {

		String anoMesArrecadacao = Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao().toString());

		form.setMesAnoReferenciaArrecadacao("" + anoMesArrecadacao);

		if (sistemaParametro.getCodigoEmpresaFebraban() != null) {
			form.setCodigoEmpresaFebraban(sistemaParametro.getCodigoEmpresaFebraban().toString());
		}

		if (sistemaParametro.getNumeroLayoutFebraban() != null) {
			form.setNumeroLayOut(sistemaParametro.getNumeroLayoutFebraban().toString());
		}

		if (sistemaParametro.getContaBancaria() != null) {
			form.setIndentificadorContaDevolucao(sistemaParametro.getContaBancaria().getId().toString());
		}

		if (sistemaParametro.getPercentualFinanciamentoEntradaMinima() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro.getPercentualFinanciamentoEntradaMinima());

			form.setPercentualEntradaMinima(valorAux);
		}

		if (sistemaParametro.getNumeroMaximoParcelasFinanciamento() != null) {
			form.setMaximoParcelas(sistemaParametro.getNumeroMaximoParcelasFinanciamento().toString());
		}

		if (sistemaParametro.getPercentualMaximoAbatimento() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro.getPercentualMaximoAbatimento());

			form.setPercentualMaximoAbatimento(valorAux);
		}

		if (sistemaParametro.getPercentualTaxaJurosFinanciamento() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro.getPercentualTaxaJurosFinanciamento());

			form.setPercentualTaxaFinanciamento(valorAux);
		}

		if (sistemaParametro.getNumeroMaximoParcelaCredito() != null) {
			form.setNumeroMaximoParcelaCredito(sistemaParametro.getNumeroMaximoParcelaCredito().toString());
		}

		if (sistemaParametro.getPercentualMediaIndice() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro.getPercentualMediaIndice());

			form.setPercentualCalculoIndice(valorAux);
		}
		if (sistemaParametro.getNumeroModuloDigitoVerificador() != null) {
			form.setNumeroModuloDigitoVerificador(sistemaParametro.getNumeroModuloDigitoVerificador().toString());
		}
		if (sistemaParametro.getNumeroMesesPesquisaImoveisRamaisSuprimidos() != null) {
			form.setNumeroMesesPesquisaImoveisRamaisSuprimidos(sistemaParametro.getNumeroMesesPesquisaImoveisRamaisSuprimidos().toString());
		}
		if (sistemaParametro.getNumeroAnoQuitacao() != null) {
			form.setNumeroAnoQuitacao(sistemaParametro.getNumeroAnoQuitacao().toString());
		}
		if (sistemaParametro.getIndicadorContaParcelada() != null) {
			form.setIndicadorContaParcelada(sistemaParametro.getIndicadorContaParcelada().toString());
		}
		if (sistemaParametro.getIndicadorCobrancaJudical() != null) {
			form.setIndicadorCobrancaJudical(sistemaParametro.getIndicadorCobrancaJudical().toString());
		}
		if (sistemaParametro.getIndicadorValorMovimentoArrecadador() != null) {
			form.setIndicadorValorMovimentoArrecadador(String.valueOf(sistemaParametro.getIndicadorValorMovimentoArrecadador()));
		}
		if (sistemaParametro.getNumeroMesesAnterioresParaDeclaracaoQuitacao() != null) {
			form.setNumeroMesesAnterioresParaDeclaracaoQuitacao(sistemaParametro.getNumeroMesesAnterioresParaDeclaracaoQuitacao().toString());
		}

	}

	private void montarSistemaParametro4Tabela(ConsultarParametrosSistemaActionForm form, SistemaParametro sistemaParametro) {

		if (sistemaParametro.getHidrometroCapacidade() != null) {
			form.setDescricaoMenorCapacidade(sistemaParametro.getHidrometroCapacidade().getDescricao());
		}

		if (sistemaParametro.getIndicadorFaixaFalsa() != null) {
			form.setIndicadorGeracaoFaixaFalsa(sistemaParametro.getIndicadorFaixaFalsa().toString());
		}

		if (sistemaParametro.getIndicadorUsoFaixaFalsa() != null) {
			form.setIndicadorPercentualGeracaoFaixaFalsa(sistemaParametro.getIndicadorUsoFaixaFalsa().toString());
		}

		if (sistemaParametro.getPercentualFaixaFalsa() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro.getPercentualFaixaFalsa());

			form.setPercentualGeracaoFaixaFalsa(valorAux);
		}

		if (sistemaParametro.getIndicadorPercentualFiscalizacaoLeitura() != null) {
			form.setIndicadorPercentualGeracaoFiscalizacaoLeitura(sistemaParametro.getIndicadorPercentualFiscalizacaoLeitura().toString());
		}

		if (sistemaParametro.getIndicadorUsoFiscalizadorLeitura() != null) {
			form.setIndicadorGeracaoFiscalizacaoLeitura(sistemaParametro.getIndicadorUsoFiscalizadorLeitura().toString());
		}

		if (sistemaParametro.getPercentualFiscalizacaoLeitura() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro.getPercentualFiscalizacaoLeitura());

			form.setPercentualGeracaoFiscalizacaoLeitura(valorAux);
		}

		if (sistemaParametro.getIndicadorUsoFaixaFalsa() != null) {
			form.setIndicadorPercentualGeracaoFaixaFalsa(sistemaParametro.getIndicadorUsoFaixaFalsa().toString());
		}

		if (sistemaParametro.getPercentualFaixaFalsa() != null) {

			String valorAux = Util.formatarMoedaReal(sistemaParametro.getPercentualFaixaFalsa());

			form.setPercentualGeracaoFaixaFalsa(valorAux);
		}

		if (sistemaParametro.getIncrementoMaximoConsumoRateio() != null) {

			form.setIncrementoMaximoConsumo(sistemaParametro.getIncrementoMaximoConsumoRateio().toString());
		}

		if (sistemaParametro.getDecrementoMaximoConsumoRateio() != null) {
			form.setDecrementoMaximoConsumo(sistemaParametro.getDecrementoMaximoConsumoRateio().toString());
		}

		if (sistemaParametro.getPercentualToleranciaRateio() != null) {

			String valorAux = Util.formataBigDecimal(sistemaParametro.getPercentualToleranciaRateio(), 1, false);

			form.setPercentualToleranciaRateioConsumo(valorAux);
		}

		if (sistemaParametro.getNumeroDiasVencimentoCobranca() != null) {
			form.setDiasVencimentoCobranca(sistemaParametro.getNumeroDiasVencimentoCobranca().toString());
		}

		if (sistemaParametro.getNumeroMaximoMesesSancoes() != null) {
			form.setNumeroMaximoMesesSancoes(sistemaParametro.getNumeroMaximoMesesSancoes().toString());
		}

		form.setValorSegundaVia(Util.formatarMoedaReal(sistemaParametro.getValorSegundaVia()));

		form.setIndicadorCobrarTaxaExtrato("" + sistemaParametro.getIndicadorCobrarTaxaExtrato());

		if (sistemaParametro.getCodigoPeriodicidadeNegativacao() != null) {
			form.setCodigoPeriodicidadeNegativacao(sistemaParametro.getCodigoPeriodicidadeNegativacao().toString());
		}

		form.setNumeroDiasCalculoAcrescimos("" + sistemaParametro.getNumeroDiasCalculoAcrescimos());

		form.setNumeroDiasValidadeExtrato(sistemaParametro.getNumeroDiasValidadeExtrato().toString());

		if (sistemaParametro.getNumeroDiasValidadeExtratoPermissaoEspecial() != null) {
			form.setNumeroDiasValidadeExtratoPermissaoEspecial(sistemaParametro.getNumeroDiasValidadeExtratoPermissaoEspecial().toString());
		}

		form.setIndicadorParcelamentoConfirmado("" + sistemaParametro.getIndicadorParcelamentoConfirmado());

		form.setindicadorTabelaPrice("" + sistemaParametro.getIndicadorTabelaPrice());

		form.setNumeroDiasVencimentoEntradaParcelamento("" + sistemaParametro.getNumeroDiasVencimentoEntradaParcelamento().toString());

		if (sistemaParametro.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo() != null) {
			form.setNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo(sistemaParametro.getNumeroDiasEncerrarOsFiscalizacaoDecursoPrazo().toString());
		}

		if (sistemaParametro.getResolucaoDiretoria() != null && sistemaParametro.getResolucaoDiretoria().getId() != null) {
			form.setIdResolucaoDiretoria(sistemaParametro.getResolucaoDiretoria().getId().toString());
		}

		if (sistemaParametro.getIndicadorBloqueioContasContratoParcelDebitos() != null) {
			form.setIndicadorBloqueioContasContratoParcelDebitos(sistemaParametro.getIndicadorBloqueioContasContratoParcelDebitos() + "");
		}

		if (sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta() != null) {
			form.setIndicadorBloqueioContasContratoParcelManterConta(sistemaParametro.getIndicadorBloqueioContasContratoParcelManterConta() + "");
		}

		if (sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito() != null) {
			form.setIndicadorBloqueioGuiasOuAcresContratoParcelDebito(sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelDebito() + "");
		}

		if (sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta() != null) {
			form.setIndicadorBloqueioGuiasOuAcresContratoParcelManterConta(sistemaParametro.getIndicadorBloqueioGuiasOuAcresContratoParcelManterConta() + "");
		}

		if (sistemaParametro.getNumeroMaximoParcelasContratosParcelamento() != null) {
			form.setNumeroMaximoParcelasContratosParcelamento(sistemaParametro.getNumeroMaximoParcelasContratosParcelamento().toString());
		}

		if (sistemaParametro.getClienteResponsavelNegativacao() != null) {
			form.setIdClienteResponsavelNegativacao(sistemaParametro.getClienteResponsavelNegativacao().getId().toString());
			form.setNomeClienteResponsavelNegativacao(sistemaParametro.getClienteResponsavelNegativacao().getNome());
		}
	}
	
	private void montarSistemaParametro5Tabela(ConsultarParametrosSistemaActionForm form, SistemaParametro sistemaParametro) {

		Fachada fachada = Fachada.getInstancia();

		if (sistemaParametro.getIndicadorSugestaoTramite() != null) {
			form.setIndicadorSugestaoTramite(sistemaParametro.getIndicadorSugestaoTramite().toString());
		}

		if (sistemaParametro.getDiasReativacao() != null) {
			form.setDiasMaximoReativarRA(sistemaParametro.getDiasReativacao().toString());
		}

		if (sistemaParametro.getDiasMaximoAlterarOS() != null) {
			form.setDiasMaximoAlterarOS(sistemaParametro.getDiasMaximoAlterarOS().toString());
		}

		if (sistemaParametro.getNumeroDiasEncerramentoOrdemServico() != null) {
			form.setNumeroDiasEncerramentoOrdemServico(sistemaParametro.getNumeroDiasEncerramentoOrdemServico().toString());
		}

		if (sistemaParametro.getNumeroDiasEncerramentoOSSeletiva() != null) {
			form.setNumeroDiasEncerramentoOSSeletiva(sistemaParametro.getNumeroDiasEncerramentoOSSeletiva().toString());
		}

		if (sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior() != null) {
			form.setNumeroDiasAlteracaoVencimentoPosterior(sistemaParametro.getNumeroDiasAlteracaoVencimentoPosterior().toString());
		}

		if (sistemaParametro.getUltimoRAManual() != null) {
			form.setUltimoIDGeracaoRA(sistemaParametro.getUltimoRAManual().toString());
		}

		if (sistemaParametro.getNumeroDiasExpiracaoAcesso() != null) {
			form.setDiasMaximoExpirarAcesso(sistemaParametro.getNumeroDiasExpiracaoAcesso().toString());
		}

		if (sistemaParametro.getNumeroDiasMensagemExpiracao() != null) {
			form.setDiasMensagemExpiracaoSenha(sistemaParametro.getNumeroDiasMensagemExpiracao().toString());
		}

		if (sistemaParametro.getNumeroMaximoLoginFalho() != null) {
			form.setNumeroMaximoTentativasAcesso(sistemaParametro.getNumeroMaximoLoginFalho().toString());
		}

		if (sistemaParametro.getIndicadorControleTramitacaoRA() != null) {
			form.setIndicadorControleTramitacaoRA("" + sistemaParametro.getIndicadorControleTramitacaoRA());
		}

		if (sistemaParametro.getNumeroMaximoFavorito() != null) {
			form.setNumeroMaximoFavoritosMenu(sistemaParametro.getNumeroMaximoFavorito().toString());
		}

		if (sistemaParametro.getIpServidorSmtp() != null) {
			form.setIpServidorSmtp(sistemaParametro.getIpServidorSmtp());
		}

		if (sistemaParametro.getIpServidorModuloGerencial() != null) {
			form.setIpServidorGerencial(sistemaParametro.getIpServidorModuloGerencial());
		}

		if (sistemaParametro.getDsEmailResponsavel() != null) {
			form.setEmailResponsavel(sistemaParametro.getDsEmailResponsavel());
		}

		if (sistemaParametro.getMensagemSistema() != null) {
			form.setMensagemSistema(sistemaParametro.getMensagemSistema());
		}

		if (sistemaParametro.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos() != null) {
			form.setDiasVencimentoCertidaoNegativa("" + sistemaParametro.getNumeroDiasVencimentoDebitoGeracaoCertidaoNegativaDebitos());
		}

		if (sistemaParametro.getIndicadorDocumentoValido() != null) {
			form.setIndicadorDocumentoValido("" + sistemaParametro.getIndicadorDocumentoValido());
		}

		if (sistemaParametro.getIndicadorValidarLocalizacaoEncerramentoOS() != null) {
			form.setIndicadorValidacaoLocalidadeEncerramentoOS("" + sistemaParametro.getIndicadorValidarLocalizacaoEncerramentoOS());
		} else {
			form.setIndicadorValidacaoLocalidadeEncerramentoOS("" + ConstantesSistema.NAO);
		}

		form.setIndicadorDebitoACobrarValidoCertidaoNegativa("" + sistemaParametro.getIndicadorDebitoACobrarValidoCertidaoNegativa());

		form.setIndicadorLoginUnico("" + sistemaParametro.getIndicadorLoginUnico());

		form.setIndicadorDebitoACobrarValidoCertidaoNegativa("" + sistemaParametro.getIndicadorDebitoACobrarValidoCertidaoNegativa());

		form.setIndicadorCertidaoNegativaEfeitoPositivo("" + sistemaParametro.getIndicadorCertidaoNegativaEfeitoPositivo());

		if (sistemaParametro.getIndicadorControleExpiracaoSenhaPorGrupo() != null) {
			form.setIndicarControleExpiracaoSenhaPorGrupo(sistemaParametro.getIndicadorControleExpiracaoSenhaPorGrupo().toString());
		}
		if (sistemaParametro.getIndicadorControleBloqueioSenhaAnterior() != null) {
			form.setIndicarControleBloqueioSenha(sistemaParametro.getIndicadorControleBloqueioSenhaAnterior().toString());
		}
		if (sistemaParametro.getIndicadorSenhaForte() != null) {
			form.setIndicadorSenhaForte(sistemaParametro.getIndicadorSenhaForte().toString());
		}
		if (sistemaParametro.getDescricaoDecreto() != null) {
			form.setDescricaoDecreto(sistemaParametro.getDescricaoDecreto().toString());
		}
		if (sistemaParametro.getDescricaoLeiEstTarif() != null) {
			form.setDescricaoLeiEstTarif(sistemaParametro.getDescricaoLeiEstTarif().toString());
		}
		if (sistemaParametro.getDescricaoLeiIndividualizacao() != null) {
			form.setDescricaoLeiIndividualizacao(sistemaParametro.getDescricaoLeiIndividualizacao().toString());
		}
		if (sistemaParametro.getDescricaoNormaCM() != null) {
			form.setDescricaoNormaCM(sistemaParametro.getDescricaoNormaCM().toString());
		}
		if (sistemaParametro.getDescricaoNormaCO() != null) {
			form.setDescricaoNormaCO(sistemaParametro.getDescricaoNormaCO().toString());
		}

		if (sistemaParametro.getArquivoDecreto() != null && sistemaParametro.getArquivoDecreto().length != 0) {
			form.setArquivoDecreto(sistemaParametro.getArquivoDecreto());
		}

		if (sistemaParametro.getArquivoLeiEstTarif() != null && sistemaParametro.getArquivoLeiEstTarif().length != 0) {
			form.setArquivoLeiEstTarif(sistemaParametro.getArquivoLeiEstTarif());
		}

		if (sistemaParametro.getArquivoLeiIndividualizacao() != null && sistemaParametro.getArquivoLeiIndividualizacao().length != 0) {
			form.setArquivoLeiIndividualizacao(sistemaParametro.getArquivoLeiIndividualizacao());
		}

		if (sistemaParametro.getArquivoNormaCM() != null && sistemaParametro.getArquivoNormaCM().length != 0) {
			form.setArquivoNormaCM(sistemaParametro.getArquivoNormaCM());
		}

		if (sistemaParametro.getArquivoNormaCO() != null && sistemaParametro.getArquivoNormaCO().length != 0) {
			form.setArquivoNormaCO(sistemaParametro.getArquivoNormaCO());
		}

		if (sistemaParametro.getUnidadeOrganizacionalTramiteGrandeConsumidor() != null) {
			FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
			filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacional.ID, sistemaParametro
					.getUnidadeOrganizacionalTramiteGrandeConsumidor().getId()));

			Collection<UnidadeOrganizacional> colecao = fachada.pesquisar(filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

			UnidadeOrganizacional unidade = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecao);

			form.setIdUnidadeDestinoGrandeConsumidor(unidade.getId().toString());
			form.setNomeUnidadeDestinoGrandeConsumidor(unidade.getDescricao());

		}

		if (sistemaParametro.getNumeroDiasRevisaoComPermEspecial() != null) {
			form.setNumeroDiasRevisaoConta(sistemaParametro.getNumeroDiasRevisaoComPermEspecial().toString());
		}

		if (sistemaParametro.getQtdeDiasValidadeOSFiscalizacao() != null) {
			form.setQtdeDiasValidadeOSFiscalizacao(sistemaParametro.getQtdeDiasValidadeOSFiscalizacao().toString());
		}

		if (sistemaParametro.getQtdeDiasEncerraOSFiscalizacao() != null) {
			form.setQtdeDiasEncerraOSFiscalizacao(sistemaParametro.getQtdeDiasEncerraOSFiscalizacao().toString());
		}

		if (sistemaParametro.getQtdeDiasEnvioEmailConta() != null) {
			form.setQtdeDiasEnvioEmailConta(sistemaParametro.getQtdeDiasEnvioEmailConta().toString());
		}
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

	private void montarEndereco(ConsultarParametrosSistemaActionForm form, HttpServletRequest httpServletRequest) {
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

				if (imovel.getLogradouroCep() != null) {
					form.setLogradouroCep(imovel.getLogradouroCep().getId().toString());
				}
				if (imovel.getEnderecoReferencia() != null) {
					form.setEnderecoReferencia(imovel.getEnderecoReferencia().getId().toString());
				} else {
					form.setEnderecoReferencia("");
				}
				form.setNumero(imovel.getNumeroImovel());
				form.setComplemento(imovel.getComplementoEndereco());
			}
		}
	}
	
	private void retornaArquivo(String arquivo, HttpServletResponse httpServletResponse, SistemaParametro sistemaParametro) {
		String mimeType = ConstantesSistema.CONTENT_TYPE_PDF;
		OutputStream out = null;
		byte[] file = null;

		if (arquivo.equalsIgnoreCase("decreto")) {
			file = sistemaParametro.getArquivoDecreto();
		}
		if (arquivo.equalsIgnoreCase("leiTarifa")) {
			file = sistemaParametro.getArquivoLeiEstTarif();
		}
		if (arquivo.equalsIgnoreCase("leiNormaMedicao")) {
			file = sistemaParametro.getArquivoLeiIndividualizacao();
		}
		if (arquivo.equalsIgnoreCase("normaCO")) {
			file = sistemaParametro.getArquivoNormaCO();
		}
		if (arquivo.equalsIgnoreCase("normaCM")) {
			file = sistemaParametro.getArquivoNormaCM();
		}

		try {
			httpServletResponse.setContentType(mimeType);
			out = httpServletResponse.getOutputStream();

			out.write(file);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new ActionServletException("erro.sistema", e);
		}

	}

	private void setarDownloadsLoja(ConsultarParametrosSistemaActionForm form, HttpServletRequest request) {
		if (form.getArquivoDecreto() != null && form.getArquivoDecreto().length != 0) {
			request.setAttribute("arquivoDecreto", true);
		} else {
			request.removeAttribute("arquivoDecreto");
		}

		if (form.getArquivoLeiEstTarif() != null && form.getArquivoLeiEstTarif().length != 0) {
			request.setAttribute("arquivoLeiTarifa", true);
		} else {
			request.removeAttribute("arquivoLeiTarifa");
		}

		if (form.getArquivoLeiIndividualizacao() != null && form.getArquivoLeiIndividualizacao().length != 0) {
			request.setAttribute("arquivoLeiNormaMedicao", true);
		} else {
			request.removeAttribute("arquivoLeiNormaMedicao");
		}

		if (form.getArquivoNormaCM() != null && form.getArquivoNormaCM().length != 0) {
			request.setAttribute("arquivoNormaCM", true);
		} else {
			request.removeAttribute("arquivoNormaCM");
		}

		if (form.getArquivoNormaCO() != null && form.getArquivoNormaCO().length != 0) {
			request.setAttribute("arquivoNormaCO", true);
		} else {
			request.removeAttribute("arquivoNormaCO");
		}
	}
}
