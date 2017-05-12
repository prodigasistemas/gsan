package gcom.gui.cobranca;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroQuadra;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.Quadra;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.CmdEmpresaCobrancaContaLigacaoAguaSituacao;
import gcom.cobranca.CmdEmpresaCobrancaContaLigacaoAguaSituacaoPK;
import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.ComandoEmpresaCobrancaConta;
import gcom.cobranca.ComandoEmpresaCobrancaContaGerencia;
import gcom.cobranca.ComandoEmpresaCobrancaContaGerenciaPK;
import gcom.cobranca.ComandoEmpresaCobrancaContaImovelPerfil;
import gcom.cobranca.ComandoEmpresaCobrancaContaImovelPerfilPK;
import gcom.cobranca.ComandoEmpresaCobrancaContaUnidadeNegocio;
import gcom.cobranca.ComandoEmpresaCobrancaContaUnidadeNegocioPK;
import gcom.cobranca.FiltroCobrancaSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InformarContasEmCobrancaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) {
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InformarContasEmCobrancaActionForm form = (InformarContasEmCobrancaActionForm) actionForm;

		String idImovel = form.getIdImovel();
		String idCliente = form.getIdCliente();
		Integer idCobrancaSituacao = getFachada().pesquisarCobrancaSituacao(CobrancaSituacao.COBRANCA_EMPRESA_TERCEIRIZADA);
		String idServicoTipo = form.getIdServicoTipo();
		String[] idsCategoria = form.getIdsCategoria();
		String idUnidadeNegocio = null;
		String[] idsUnidadeNegocio = null;

		if (form.getIdsUnidadeNegocio() != null && form.getIdsUnidadeNegocio().length > 0) {
			if (form.getIdsUnidadeNegocio().length == 1) {
				idUnidadeNegocio = form.getIdsUnidadeNegocio()[0];
			} else {
				idsUnidadeNegocio = form.getIdsUnidadeNegocio();
			}
		}
		String idGerenciaRegional = null;
		String[] idsGerenciaRegional = null;
		if (form.getIdsGerenciaRegional() != null && form.getIdsGerenciaRegional().length > 0) {
			if (form.getIdsGerenciaRegional().length == 1) {
				idGerenciaRegional = form.getIdsGerenciaRegional()[0];
			} else {
				idsGerenciaRegional = form.getIdsGerenciaRegional();
			}
		}
		String idImovelPerfil = null;
		String[] idsImovelPerfil = null;
		if (form.getIdsImovelPerfil() != null && form.getIdsImovelPerfil().length > 0) {
			if (form.getIdsImovelPerfil().length == 1) {
				idImovelPerfil = form.getIdsImovelPerfil()[0];
			} else {
				idsImovelPerfil = form.getIdsImovelPerfil();
			}
		}
		String idLigacaoAguaSituacao = null;
		String[] idsLigacaoAguaSituacao = null;
		if (form.getIdsLigacaoAguaSituacao() != null && form.getIdsLigacaoAguaSituacao().length > 0) {
			if (form.getIdsLigacaoAguaSituacao().length == 1) {
				idLigacaoAguaSituacao = form.getIdsLigacaoAguaSituacao()[0];
			} else {
				idsLigacaoAguaSituacao = form.getIdsLigacaoAguaSituacao();
			}
		}
		String idLocalidadeInicial = form.getIdLocalidadeOrigem();
		String idLocalidadeFinal = form.getIdLocalidadeDestino();
		String codigoSetorComercialInicial = form.getCodigoSetorComercialOrigem();
		String codigoSetorComercialFinal = form.getCodigoSetorComercialDestino();
		String referenciaInicial = form.getReferenciaInicial();
		String referenciaFinal = form.getReferenciaFinal();
		String dataVencimentoInicial = form.getDataVencimentoInicial();
		String dataVencimentoFinal = form.getDataVencimentoFinal();
		String valorMaximo = form.getValorMaximo();
		String valorMinimo = form.getValorMinimo();
		String quantidadeContasInicial = form.getQuantidadeContasInicial();
		String quantidadeContasFinal = form.getQuantidadeContasFinal();
		String idEmpresa = form.getIdEmpresa();

		String quantidadeDiasVencimento = form.getQuantidadeDiasVencimento();
		String indicadorCobrancaTelemarketing = form.getIndicadorCobrancaTelemarketing();

		String codigoQuadraInicial = form.getCodigoQuadraInicial();
		String codigoQuadraFinal = form.getCodigoQuadraFinal();
		String dataInicioCiclo = form.getDataInicioCiclo();
		String quantidadeDiasCiclo = form.getQuantidadeDiasCiclo();

		ComandoEmpresaCobrancaConta comando = new ComandoEmpresaCobrancaConta();
		comando.setIndicadorResidencial(ConstantesSistema.NAO.intValue());
		comando.setIndicadorComercial(ConstantesSistema.NAO.intValue());
		comando.setIndicadorIndustrial(ConstantesSistema.NAO.intValue());
		comando.setIndicadorPublico(ConstantesSistema.NAO.intValue());

		if (indicadorCobrancaTelemarketing != null && !indicadorCobrancaTelemarketing.trim().equals("")) {
			comando.setIndicadorCobrancaTelemarketing(new Short(indicadorCobrancaTelemarketing));
		}

		if (form.getQuantidadeMaximaClientes() != null && !form.getQuantidadeMaximaClientes().trim().equals("") && Integer.parseInt(form.getQuantidadeMaximaClientes()) > 0) {
			comando.setQtdMaximaClientes(new Integer(form.getQuantidadeMaximaClientes()));
		}

		// Imóvel
		if (idImovel != null && !idImovel.trim().equals("")) {

			Imovel imovel = getFachada().pesquisarImovelDigitado(new Integer(idImovel));

			if (imovel != null) {
				comando.setImovel(imovel);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Imóvel");
			}

		}

		// Cliente
		if (idCliente != null && !idCliente.trim().equals("")) {

			Cliente cliente = getFachada().pesquisarClienteDigitado(new Integer(idCliente));

			if (cliente != null) {
				comando.setCliente(cliente);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Cliente");
			}

		}

		if (idsCategoria != null) {

			for (int i = 0; i < idsCategoria.length; i++) {

				if (idsCategoria[i].equals(Categoria.COMERCIAL.toString())) {
					comando.setIndicadorComercial(ConstantesSistema.SIM.intValue());
				} else if (idsCategoria[i].equals(Categoria.INDUSTRIAL.toString())) {
					comando.setIndicadorIndustrial(ConstantesSistema.SIM.intValue());
				} else if (idsCategoria[i].equals(Categoria.RESIDENCIAL.toString())) {
					comando.setIndicadorResidencial(ConstantesSistema.SIM.intValue());
				} else if (idsCategoria[i].equals(Categoria.PUBLICO.toString())) {
					comando.setIndicadorPublico(ConstantesSistema.SIM.intValue());
				}

			}
		}

		// Localidade Inicial
		if (idLocalidadeInicial != null && !idLocalidadeInicial.trim().equals("")) {

			Localidade localidadeInicial = null;
			Localidade localidadeFinal = null;

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeInicial));

			Collection colecaoLocalidadeInicial = getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidadeInicial != null && !colecaoLocalidadeInicial.isEmpty()) {
				localidadeInicial = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeInicial);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Localidade Inicial");
			}

			filtroLocalidade.limparListaParametros();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeFinal));

			Collection colecaoLocalidadeFinal = getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidadeFinal != null && !colecaoLocalidadeFinal.isEmpty()) {
				localidadeFinal = (Localidade) Util.retonarObjetoDeColecao(colecaoLocalidadeFinal);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Localidade Final");
			}

			if (localidadeInicial.getId().compareTo(localidadeFinal.getId()) > 0) {
				throw new ActionServletException("atencao.localidade.final.maior.localidade.inicial");
			}

			comando.setLocalidadeInicial(localidadeInicial);
			comando.setLocalidadeFinal(localidadeFinal);
		}

		// Setor Comercial
		if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.trim().equals("")) {

			SetorComercial setorComericialInicial = null;
			SetorComercial setorComericialFinal = null;

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidadeInicial));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialInicial));

			Collection colecaoSetorComercialInicial = getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetorComercialInicial != null && !colecaoSetorComercialInicial.isEmpty()) {
				setorComericialInicial = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialInicial);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Setor Comercial Inicial");
			}

			filtroSetorComercial.limparListaParametros();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidadeFinal));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercialFinal));

			Collection colecaoSetorComercialFinal = getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (colecaoSetorComercialFinal != null && !colecaoSetorComercialFinal.isEmpty()) {
				setorComericialFinal = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercialFinal);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Setor Comercial Final");
			}

			if (setorComericialInicial.getCodigo() > setorComericialFinal.getCodigo()) {
				throw new ActionServletException("atencao.setor.comercial.final.maior.setor.comercial.inicial");
			}

			comando.setCodigoSetorComercialInicial(setorComericialInicial.getCodigo());
			comando.setCodigoSetorComercialFinal(setorComericialFinal.getCodigo());
		}

		// Quadra
		if (codigoQuadraInicial != null && !codigoQuadraInicial.trim().equals("")) {

			Quadra quadraInicial = null;
			Quadra quadraFinal = null;

			FiltroQuadra filtroQuadra = new FiltroQuadra();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, codigoQuadraInicial));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, codigoSetorComercialInicial));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, idLocalidadeInicial));

			Collection colecaoQuadraInicial = getFachada().pesquisar(filtroQuadra, Quadra.class.getName());

			if (colecaoQuadraInicial != null && !colecaoQuadraInicial.isEmpty()) {
				quadraInicial = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadraInicial);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Quadra Inicial");
			}

			filtroQuadra.limparListaParametros();
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.NUMERO_QUADRA, codigoQuadraFinal));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.CODIGO_SETORCOMERCIAL, codigoSetorComercialFinal));
			filtroQuadra.adicionarParametro(new ParametroSimples(FiltroQuadra.ID_LOCALIDADE, idLocalidadeFinal));

			Collection colecaoQuadraFinal = getFachada().pesquisar(filtroQuadra, Quadra.class.getName());

			if (colecaoQuadraFinal != null && !colecaoQuadraFinal.isEmpty()) {
				quadraFinal = (Quadra) Util.retonarObjetoDeColecao(colecaoQuadraFinal);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Localidade Final");
			}

			if (quadraInicial.getId().compareTo(quadraFinal.getId()) > 0) {
				throw new ActionServletException("atencao.quadraInicial.maior.que.quadraFinal");
			}

			comando.setNumeroQuadraInicial(quadraInicial.getNumeroQuadra());
			comando.setNumeroQuadraFinal(quadraFinal.getNumeroQuadra());
		}

		// Referência
		if (referenciaInicial != null && !referenciaInicial.trim().equals("")) {

			Integer referenciaInicialFormatada = Util.formatarMesAnoComBarraParaAnoMes(referenciaInicial);
			Integer referenciaFinalFormatada = null;
			if (referenciaFinal != null && !referenciaFinal.equals("")) {
				referenciaFinalFormatada = Util.formatarMesAnoComBarraParaAnoMes(referenciaFinal);
			} else {
				SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
				referenciaFinalFormatada = sistemaParametro.getAnoMesArrecadacao();
			}

			if (referenciaInicialFormatada.compareTo(referenciaFinalFormatada) > 0) {
				throw new ActionServletException("atencao.referencia.final.menor.referencia.inicial");
			}

			comando.setReferenciaContaInicial(referenciaInicialFormatada);
			comando.setReferenciaContaFinal(referenciaFinalFormatada);
		} else {
			Integer referenciaInicialFormatada = 198001;
			Integer referenciaFinalFormatada = null;
			if (referenciaFinal == null || referenciaFinal.equals("")) {
				SistemaParametro sistemaParametro = getFachada().pesquisarParametrosDoSistema();
				referenciaFinalFormatada = sistemaParametro.getAnoMesArrecadacao();
			} else {
				referenciaFinalFormatada = Util.formatarMesAnoComBarraParaAnoMes(referenciaFinal);
			}

			if (referenciaInicialFormatada.compareTo(referenciaFinalFormatada) > 0) {
				throw new ActionServletException("atencao.referencia.final.menor.referencia.inicial");
			}

			comando.setReferenciaContaInicial(referenciaInicialFormatada);
			comando.setReferenciaContaFinal(referenciaFinalFormatada);
		}

		if (dataVencimentoInicial != null && !dataVencimentoInicial.trim().equals("")) {

			Date dataVencimentoInicialFormatada = Util.converteStringParaDate(dataVencimentoInicial);
			Date dataVencimentoFinalFormatada = Util.converteStringParaDate(dataVencimentoFinal);

			if (dataVencimentoInicialFormatada.compareTo(dataVencimentoFinalFormatada) > 0) {
				throw new ActionServletException("atencao.data.intervalo.invalido");
			}

			comando.setDataVencimentoContaInicial(dataVencimentoInicialFormatada);
			comando.setDataVencimentoContaFinal(dataVencimentoFinalFormatada);
		}

		if (valorMinimo != null && !valorMinimo.trim().equals("")) {
			BigDecimal valorMinimoFormatado = Util.formatarMoedaRealparaBigDecimal(valorMinimo);
			BigDecimal valorMaximoFormatado = Util.formatarMoedaRealparaBigDecimal(valorMaximo);

			if (valorMinimo.length() > 14) {
				throw new ActionServletException("atencao.tamanho.valor.minimo.invalido");
			}

			if (valorMaximo.length() > 14) {
				throw new ActionServletException("atencao.tamanho.valor.maximo.invalido");
			}

			if (valorMinimoFormatado.compareTo(valorMaximoFormatado) > 0) {
				throw new ActionServletException("atencao.situacao.valor.servico.invalida");
			}

			comando.setValorMinimoConta(valorMinimoFormatado);
			comando.setValorMaximoConta(valorMaximoFormatado);
		}

		if (quantidadeContasInicial != null && !quantidadeContasInicial.trim().equals("")) {
			Integer qtdContasInicial = new Integer(quantidadeContasInicial);
			Integer qtdContasFinal = new Integer(quantidadeContasFinal);

			if (qtdContasInicial.compareTo(qtdContasFinal) > 0) {
				throw new ActionServletException("atencao.quantidade.contas_final.menor.quantidade_inicial");
			}

			comando.setQtdContasInicial(qtdContasInicial);
			comando.setQtdContasFinal(qtdContasFinal);
		}

		if (quantidadeDiasVencimento != null && !quantidadeDiasVencimento.trim().equals("")) {
			Integer qtdDiasVencimento = new Integer(quantidadeDiasVencimento);

			comando.setQtdDiasVencimento(qtdDiasVencimento);
		}

		if (idEmpresa != null && !idEmpresa.trim().equals("")) {
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.ID, idEmpresa));

			Collection colecaoEmpresa = getFachada().pesquisar(filtroEmpresa, Empresa.class.getName());

			if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
				Empresa empresa = (Empresa) Util.retonarObjetoDeColecao(colecaoEmpresa);

				// [FS0011]- Verificar se empresa é de Cobranca
				if (empresa.getIndicadorEmpresaContratadaCobranca() != null && empresa.getIndicadorEmpresaContratadaCobranca().intValue() == Empresa.INDICADOR_EMPRESA_COBRANCA.intValue()) {

					comando.setEmpresa(empresa);
				} else {
					throw new ActionServletException("atencao.empresa_nao_cobranca");
				}

			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Empresa");
			}
		}

		if (dataInicioCiclo != null && !dataInicioCiclo.trim().equals("")) {

			Date dataInicioCicloFormatada = Util.converteStringParaDate(dataInicioCiclo);

			if (quantidadeDiasCiclo != null && !quantidadeDiasCiclo.trim().equals("")) {
				Integer quantidadeDias = new Integer(quantidadeDiasCiclo);
				Date dataFimCicloFormatada = Util.adicionarNumeroDiasDeUmaData(dataInicioCicloFormatada, quantidadeDias);
				comando.setDataFimCiclo(dataFimCicloFormatada);
			}

			comando.setDataInicioCiclo(dataInicioCicloFormatada);
		}

		if (idCobrancaSituacao != null) {

			FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(FiltroCobrancaSituacao.ID, idCobrancaSituacao));

			Collection colecaoCobrancaSituacao = getFachada().pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class.getName());

			if (colecaoCobrancaSituacao != null && !colecaoCobrancaSituacao.isEmpty()) {
				CobrancaSituacao cobrancaSituacao = (CobrancaSituacao) Util.retonarObjetoDeColecao(colecaoCobrancaSituacao);

				comando.setCobrancaSituacao(cobrancaSituacao);
			}
		}

		if (idServicoTipo != null && !idServicoTipo.trim().equals("")) {

			FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo();
			filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.ID, idServicoTipo));

			Collection colecaoServicoTipo = getFachada().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());

			if (colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty()) {
				ServicoTipo servicoTipo = (ServicoTipo) Util.retonarObjetoDeColecao(colecaoServicoTipo);

				comando.setServicoTipo(servicoTipo);
			} else {
				throw new ActionServletException("atencao.naocadastrado", null, "Tipo de Serviço");
			}
		}

		comando.setIndicadorGeracaoTxt(new Integer(2));

		// Insere um Comando de Cobrança da Conta na base
		Integer idComandoEmpresaCobrancaConta = getFachada().inserirComandoEmpresaCobrancaConta(comando, this.getUsuarioLogado(request));

		// Insere um Comando de Cobrança da Conta por Unidade de Negócio na base
		if (idsUnidadeNegocio != null && idsUnidadeNegocio.length > 0) {
			for (int i = 0; i < idsUnidadeNegocio.length; i++) {
				if (!idsUnidadeNegocio[i].equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					ComandoEmpresaCobrancaContaUnidadeNegocio comandoEmpresaCobrancaContaUnidadeNegocio = new ComandoEmpresaCobrancaContaUnidadeNegocio();
					ComandoEmpresaCobrancaContaUnidadeNegocioPK pk = new ComandoEmpresaCobrancaContaUnidadeNegocioPK();

					pk.setUnidadeNegocioId(new Integer(idsUnidadeNegocio[i]));
					pk.setComandoEmpresaCobrancaContaId(idComandoEmpresaCobrancaConta);

					comandoEmpresaCobrancaContaUnidadeNegocio.setComp_id(pk);
					comandoEmpresaCobrancaContaUnidadeNegocio.setUltimaAlteracao(new Date());

					getFachada().inserir(comandoEmpresaCobrancaContaUnidadeNegocio);
				}
			}
		}

		// Insere um Comando de Cobrança da Conta por Gerência Regional na base
		if (idsGerenciaRegional != null && idsGerenciaRegional.length > 0) {
			for (int i = 0; i < idsGerenciaRegional.length; i++) {
				if (!idsGerenciaRegional[i].equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					ComandoEmpresaCobrancaContaGerencia comandoEmpresaCobrancaContaGerencia = new ComandoEmpresaCobrancaContaGerencia();
					ComandoEmpresaCobrancaContaGerenciaPK pk = new ComandoEmpresaCobrancaContaGerenciaPK();

					pk.setGerenciaRegionalId(new Integer(idsGerenciaRegional[i]));
					pk.setComandoEmpresaCobrancaContaId(idComandoEmpresaCobrancaConta);

					comandoEmpresaCobrancaContaGerencia.setComp_id(pk);
					comandoEmpresaCobrancaContaGerencia.setUltimaAlteracao(new Date());

					getFachada().inserir(comandoEmpresaCobrancaContaGerencia);
				}
			}
		}

		// Insere um Comando de Cobrança da Conta por Imovel Perfil na base
		if (idsImovelPerfil != null && idsImovelPerfil.length > 0) {
			for (int i = 0; i < idsImovelPerfil.length; i++) {
				if (!idsImovelPerfil[i].equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					ComandoEmpresaCobrancaContaImovelPerfil comandoEmpresaCobrancaContaImovelPerfil = new ComandoEmpresaCobrancaContaImovelPerfil();
					ComandoEmpresaCobrancaContaImovelPerfilPK pk = new ComandoEmpresaCobrancaContaImovelPerfilPK();

					pk.setImovelPerfilId(new Integer(idsImovelPerfil[i]));
					pk.setComandoEmpresaCobrancaContaId(idComandoEmpresaCobrancaConta);

					comandoEmpresaCobrancaContaImovelPerfil.setComp_id(pk);
					comandoEmpresaCobrancaContaImovelPerfil.setUltimaAlteracao(new Date());

					getFachada().inserir(comandoEmpresaCobrancaContaImovelPerfil);
				}
			}
		}

		// Insere um Comando de Cobrança da Conta por Situação de Ligação de
		// Água na base
		if (idsLigacaoAguaSituacao != null && idsLigacaoAguaSituacao.length > 0) {
			for (int i = 0; i < idsLigacaoAguaSituacao.length; i++) {
				if (!idsLigacaoAguaSituacao[i].equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
					CmdEmpresaCobrancaContaLigacaoAguaSituacao cmdEmpresaCobrancaContaLigacaoAguaSituacao = new CmdEmpresaCobrancaContaLigacaoAguaSituacao();
					CmdEmpresaCobrancaContaLigacaoAguaSituacaoPK pk = new CmdEmpresaCobrancaContaLigacaoAguaSituacaoPK();

					pk.setLigacaoAguaSituacaoId(new Integer(idsLigacaoAguaSituacao[i]));
					pk.setComandoEmpresaCobrancaContaId(idComandoEmpresaCobrancaConta);

					cmdEmpresaCobrancaContaLigacaoAguaSituacao.setCels_id(pk);
					cmdEmpresaCobrancaContaLigacaoAguaSituacao.setUltimaAlteracao(new Date());

					getFachada().inserir(cmdEmpresaCobrancaContaLigacaoAguaSituacao);
				}
			}
		}

		montarPaginaSucesso(request, "Comando de cobrança de conta informado com sucesso. ", "Informar outro Comando de Cobrança de Conta", "exibirInformarContasEmCobrancaAction.do?menu=sim");

		return retorno;
	}

}
