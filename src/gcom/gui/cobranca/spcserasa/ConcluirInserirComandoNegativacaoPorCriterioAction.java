package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteRelacaoTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.NegativacaoComando;
import gcom.cobranca.NegativacaoCriterio;
import gcom.cobranca.Negativador;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.spcserasa.bean.InserirComandoNegativacaoPorCriterioHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ConcluirInserirComandoNegativacaoPorCriterioAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirComandoNegativacaoActionForm form = (InserirComandoNegativacaoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		InserirComandoNegativacaoPorCriterioHelper helper = new InserirComandoNegativacaoPorCriterioHelper();

		// Valida��o Aba 2
		validarDadosDebito(form, fachada);

		// Valida��o Aba 3
		validarDadosImovel(form, fachada);

		// Valida��o Aba 4
		validarDadosLocalizacao(form, fachada);

		// [SB0004] - Incluir Comando Negativa��o por Crit�rio
		// Dados Negativa��o Comando
		NegativacaoComando negativacaoComando = new NegativacaoComando();
		negativacaoComando.setIndicadorSimulacao(new Short(form.getSimular()));
		negativacaoComando.setIndicadorComandoCriterio(new Short("1"));
		negativacaoComando.setDataPrevista(Util.converteStringParaDate(form.getDataPrevista()));
		negativacaoComando.setDataHoraComando(new Date());
		Usuario usuario = new Usuario();
		usuario.setId(new Integer(form.getUsuario()));
		negativacaoComando.setUsuario(usuario);
		Negativador negativador = new Negativador();
		negativador.setId(new Integer(form.getIdNegativador()));
		negativacaoComando.setNegativador(negativador);
		
		if (form.getIdComandoSimulado() != null && !form.getIdComandoSimulado().equals("")) {
			NegativacaoComando negComandoSimulacao = new NegativacaoComando();
			negComandoSimulacao.setId(new Integer(form.getIdComandoSimulado()));
			negativacaoComando.setComandoSimulacao(negComandoSimulacao);
		}

		if (form.getIndicadorContaNomeCliente() != null) {
			negativacaoComando.setIndicadorContaNomeCliente(new Short(form.getIndicadorContaNomeCliente()));
		} else {
			negativacaoComando.setIndicadorContaNomeCliente(new Short("1"));
		}

		if (form.getIndicadorImovelCategoriaPublico() != null) {
			negativacaoComando.setIndicadorOrgaoPublico(new Short(form.getIndicadorImovelCategoriaPublico()));
		} else {
			negativacaoComando.setIndicadorOrgaoPublico(new Short("2"));
		}
		helper.setNegativacaoComando(negativacaoComando);

		// Dados Negativacao Crit�rio
		NegativacaoCriterio negativacaoCriterio = new NegativacaoCriterio();
		negativacaoCriterio.setDescricaoTitulo(form.getTitulo());
		negativacaoCriterio.setDescricaoSolicitacao(form.getSolicitacao());
		
		if (form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().equals("")) {
			Localidade localidade = new Localidade();
			localidade.setId(new Integer(form.getIdLocalidadeInicial()));
			negativacaoCriterio.setLocalidadeInicial(localidade);
		}
		
		if (form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().equals("")) {
			Localidade localidade = new Localidade();
			localidade.setId(new Integer(form.getIdLocalidadeFinal()));
			negativacaoCriterio.setLocalidadeFinal(localidade);
		}
		
		if (form.getCodigoSetorComercialInicial() != null && !form.getCodigoSetorComercialInicial().equals("")) {
			negativacaoCriterio.setCodigoSetorComercialInicial(new Integer(form.getCodigoSetorComercialInicial()));
		}
		
		if (form.getCodigoSetorComercialFinal() != null && !form.getCodigoSetorComercialFinal().equals("")) {
			negativacaoCriterio.setCodigoSetorComercialFinal(new Integer(form.getCodigoSetorComercialFinal()));
		}
		
		if (form.getReferenciaInicial() != null && !form.getReferenciaInicial().equals("")) {
			negativacaoCriterio.setAnoMesReferenciaContaInicial(Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaInicial()));
		} else {
			Date dataReferenciaInicial = null;
			if (form.getReferenciaFinal() != null && !form.getReferenciaFinal().equals("")) {
				dataReferenciaInicial = Util.subtrairNumeroAnosDeUmaData(Util.converteStringParaDate("01/" + form.getReferenciaFinal()), -5);
			} else {
				dataReferenciaInicial = Util.subtrairNumeroAnosDeUmaData(new Date(), -5);
			}
			negativacaoCriterio.setAnoMesReferenciaContaInicial(Util.formataAnoMes(dataReferenciaInicial));
		}
		
		if (form.getReferenciaFinal() != null && !form.getReferenciaFinal().equals("")) {
			negativacaoCriterio.setAnoMesReferenciaContaFinal(Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaFinal()));
		} else {
			negativacaoCriterio.setAnoMesReferenciaContaFinal(Util.formataAnoMes(new Date()));
		}
		
		if (form.getDataVencimentoInicial() != null && !form.getDataVencimentoInicial().equals("")) {
			negativacaoCriterio.setDataVencimentoDebitoInicial(Util.converteStringParaDate(form.getDataVencimentoInicial()));
		} else {
			Date dataVencimentoDebitoInicial = null;
			if (form.getDataVencimentoFinal() != null && !form.getDataVencimentoFinal().equals("")) {
				dataVencimentoDebitoInicial = Util.subtrairNumeroAnosDeUmaData(Util.converteStringParaDate(form.getDataVencimentoFinal()), -5);
			} else {
				dataVencimentoDebitoInicial = Util.subtrairNumeroAnosDeUmaData(new Date(), -5);
			}
			negativacaoCriterio.setDataVencimentoDebitoInicial(dataVencimentoDebitoInicial);
		}
		
		if (form.getDataVencimentoFinal() != null && !form.getDataVencimentoFinal().equals("")) {
			negativacaoCriterio.setDataVencimentoDebitoFinal(Util.converteStringParaDate(form.getDataVencimentoFinal()));
		} else {
			negativacaoCriterio.setDataVencimentoDebitoFinal(new Date());
		}
		
		if (form.getQtdMaximaInclusao() != null && !form.getQtdMaximaInclusao().equals("")) {
			negativacaoCriterio.setQuantidadeMaximaInclusoes(new Integer(form.getQtdMaximaInclusao()));
		}
		
		if (form.getImovSitEspecialCobranca() != null && !form.getImovSitEspecialCobranca().equals("")) {
			negativacaoCriterio.setIndicadorNegativacaoImovelParalisacao(new Short(form.getImovSitEspecialCobranca()));
		} else {
			negativacaoCriterio.setIndicadorNegativacaoImovelParalisacao(ConstantesSistema.INDICADOR_USO_ATIVO);
		}
		
		if (form.getImovSitCobranca() != null && !form.getImovSitCobranca().equals("")) {
			negativacaoCriterio.setIndicadorNegativacaoImovelSituacaoCobranca(new Short(form.getImovSitCobranca()));
		} else {
			negativacaoCriterio.setIndicadorNegativacaoImovelSituacaoCobranca(ConstantesSistema.INDICADOR_USO_DESATIVO);
		}
		
		if (form.getContasRevisao() != null && !form.getContasRevisao().equals("")) {
			negativacaoCriterio.setIndicadorNegativacaoContaRevisao(new Short(form.getContasRevisao()));
		} else {
			negativacaoCriterio.setIndicadorNegativacaoContaRevisao(ConstantesSistema.INDICADOR_USO_DESATIVO);
		}
		
		if (form.getGuiasPagamento() != null && !form.getGuiasPagamento().equals("")) {
			negativacaoCriterio.setIndicadorNegativacaoGuiaPagamento(new Short(form.getGuiasPagamento()));
		} else {
			negativacaoCriterio.setIndicadorNegativacaoGuiaPagamento(ConstantesSistema.INDICADOR_USO_DESATIVO);
		}
		
		if (form.getParcelaAtraso() != null && !form.getParcelaAtraso().equals("")) {
			negativacaoCriterio.setIndicadorParcelamentoAtraso(new Short(form.getParcelaAtraso()));
		} else {
			negativacaoCriterio.setIndicadorParcelamentoAtraso(ConstantesSistema.INDICADOR_USO_DESATIVO);
		}
		
		if (form.getDiasAtrasoParcelamento() != null && !form.getDiasAtrasoParcelamento().equals("")) {
			negativacaoCriterio.setNumeroDiasParcelamentoAtraso(new Integer(form.getDiasAtrasoParcelamento()));
		}
		
		if (form.getCartaParcelamentoAtraso() != null && !form.getCartaParcelamentoAtraso().equals("")) {
			negativacaoCriterio.setIndicadorNegativacaoRecebimentoCartaParcelamento(new Short(form.getCartaParcelamentoAtraso()));
		} else {
			negativacaoCriterio.setIndicadorNegativacaoRecebimentoCartaParcelamento(ConstantesSistema.INDICADOR_USO_DESATIVO);
		}
		
		if (form.getDiasAtrasoRecebimentoCarta() != null && !form.getDiasAtrasoRecebimentoCarta().equals("")) {
			negativacaoCriterio.setNumeroDiasAtrasoRecebimentoCartaParcelamento(new Short(form.getDiasAtrasoRecebimentoCarta()));
		}
		
		if (form.getIdCliente() != null && !form.getIdCliente().equals("")) {
			Cliente cliente = new Cliente();
			cliente.setId(new Integer(form.getIdCliente()));
			negativacaoCriterio.setCliente(cliente);
			if (form.getTipoRelacao() != null && !form.getTipoRelacao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
				ClienteRelacaoTipo relacaoTipo = new ClienteRelacaoTipo();
				relacaoTipo.setId(new Integer(form.getTipoRelacao()));
				negativacaoCriterio.setClienteRelacaoTipo(relacaoTipo);
			}
		}
		
		negativacaoCriterio.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		if (form.getValorDebitoInicial() != null && !form.getValorDebitoInicial().equals("")) {
			negativacaoCriterio.setValorMinimoDebito(Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoInicial()));
		} else {
			negativacaoCriterio.setValorMinimoDebito(Util.formatarMoedaRealparaBigDecimal("0"));
		}
		
		if (form.getValorDebitoFinal() != null && !form.getValorDebitoFinal().equals("")) {
			negativacaoCriterio.setValorMaximoDebito(Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoFinal()));
		} else {
			negativacaoCriterio.setValorMaximoDebito(Util.formatarMoedaRealparaBigDecimal("99999999999,99"));
		}
		
		if (form.getNumeroContasInicial() != null && !form.getNumeroContasInicial().equals("")) {
			negativacaoCriterio.setQuantidadeMinimaContas(new Integer(form.getNumeroContasInicial()));
		} else {
			negativacaoCriterio.setQuantidadeMinimaContas(0);
		}
		
		if (form.getNumeroContasFinal() != null && !form.getNumeroContasFinal().equals("")) {
			negativacaoCriterio.setQuantidadeMaximaContas(new Integer(form.getNumeroContasFinal()));
		} else {
			negativacaoCriterio.setQuantidadeMaximaContas(999999999);
		}

		if (form.getQuantidadeDias() != null && !form.getQuantidadeDias().equals("")) {
			negativacaoCriterio.setNumeroDiasRetorno(new Integer(form.getQuantidadeDias()));
		}

		helper.setNegativacaoCriterio(negativacaoCriterio);

		// Situa��o Especial Cobranca
		if (negativacaoCriterio.getIndicadorNegativacaoImovelParalisacao() == ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()
				&& form.getCobrancaSituacaoTipo() != null && form.getCobrancaSituacaoTipo().length > 0) {

			helper.setIdsCobrancaSituacaoTipo(form.getCobrancaSituacaoTipo());
		}

		// Situa��o Cobranca
		if (negativacaoCriterio.getIndicadorNegativacaoImovelSituacaoCobranca() == ConstantesSistema.INDICADOR_USO_ATIVO.shortValue()
				&& form.getCobrancaSituacao() != null && form.getCobrancaSituacao().length > 0) {

			helper.setIdsCobrancaSituacao(form.getCobrancaSituacao());
		}

		// Negativacao Criterio CPF Tipo
		helper.setColecaoNegativacaoCriterioCpfTipo((Collection) sessao.getAttribute("colecaoNegativacaoCriterioCpfTipo"));

		// Situa��o da Liga��o de �gua
		if (form.getLigacaoAguaSituacao() != null && form.getLigacaoAguaSituacao().length > 0) {
			helper.setIdsLigacaoAguaSituacao(form.getLigacaoAguaSituacao());
		}
		
		// Situa��o da Liga��o de Esgoto
		if (form.getLigacaoEsgotoSituacao() != null && form.getLigacaoEsgotoSituacao().length > 0) {
			helper.setIdsLigacaoEsgotoSituacao(form.getLigacaoEsgotoSituacao());
		}
		
		// SubCategoria
		if (form.getSubCategoria() != null && form.getSubCategoria().length > 0) {
			helper.setIdsSubcategoria(form.getSubCategoria());
		}
		
		// Perfil de Im�vel
		if (form.getPerfilImovel() != null && form.getPerfilImovel().length > 0) {
			helper.setIdsPerfilImovel(form.getPerfilImovel());
		}
		
		// Tipo de Cliente
		if (form.getTipoCliente() != null && form.getTipoCliente().length > 0) {
			helper.setIdsTipoCliente(form.getTipoCliente());
		}

		// GrupoCobranca
		if (form.getCobrancaGrupo() != null && form.getCobrancaGrupo().length > 0) {
			helper.setIdsCobrancaGrupo(form.getCobrancaGrupo());
		}
		
		// Ger�ncia Regional
		if (form.getGerenciaRegional() != null && form.getGerenciaRegional().length > 0) {
			helper.setIdsGerenciaRegional(form.getGerenciaRegional());
		}
		
		// Unidade Neg�cio
		if (form.getUnidadeNegocio() != null && form.getUnidadeNegocio().length > 0) {
			helper.setIdsUnidadeNegocio(form.getUnidadeNegocio());
		}
		
		// Elo
		if (form.getEloPolo() != null && form.getEloPolo().length > 0) {
			helper.setIdsEloPolo(form.getEloPolo());
		}

		if (form.getIndicadorBaixaRenda() != null && !form.getIndicadorBaixaRenda().equals("")) {
			helper.setIndicadorBaixaRenda(form.getIndicadorBaixaRenda());
		} else {
			helper.setIndicadorBaixaRenda(ConstantesSistema.INDICADOR_USO_ATIVO.toString());
		}

		// Motivo de Retorno
		if (form.getMotivoRetorno() != null && form.getMotivoRetorno().length > 0) {
			helper.setIdsMotivoRetorno(form.getMotivoRetorno());
		}

		// [SB0004]- Incluir Comando de Negativa��o por Crit�rio
		Integer idNegativadorComando = fachada.inserirComandoNegativacao(helper);

		// Monta a p�gina de sucesso
		montarPaginaSucesso(httpServletRequest, "Comando Negativa��o de c�digo " + idNegativadorComando + " inserido com sucesso.",
				"Inserir outro Comando Negativa��o", "exibirInserirComandoNegativacaoTipoComandoAction.do?menu=sim");

		return retorno;
	}

	private void validarDadosLocalizacao(InserirComandoNegativacaoActionForm form, Fachada fachada) {
		// Pesquisa Localidade Incial
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		if (idLocalidadeInicial != null && !idLocalidadeInicial.equals("")) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeInicial));
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.localidade_inicial_inexistente");
			}
		}

		// Pesquisa Localidade Final
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		if (idLocalidadeFinal != null && !idLocalidadeFinal.equals("")) {

			FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, idLocalidadeFinal));
			Collection colecaoLocalidade = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());

			if (colecaoLocalidade == null || colecaoLocalidade.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.localidade_final_inexistent");
			}
		}

		// Pesquisa Setor Comercial inicial
		String codigoSetorComercialInicial = form.getCodigoSetorComercialInicial();
		if ((codigoSetorComercialInicial != null && !codigoSetorComercialInicial.toString().trim().equalsIgnoreCase(""))
				&& (idLocalidadeInicial != null && !idLocalidadeInicial.toString().trim().equalsIgnoreCase(""))) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, new Integer(idLocalidadeInicial)));
			filtroSetorComercial
					.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(codigoSetorComercialInicial)));

			Collection<SetorComercial> setorComerciais = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (setorComerciais == null || setorComerciais.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.setor_inicial_inexistente");
			}
		}

		// Pesquisa Setor Comercial final
		String codigoSetorComercialFinal = form.getCodigoSetorComercialFinal();
		if ((codigoSetorComercialFinal != null && !codigoSetorComercialFinal.toString().trim().equalsIgnoreCase(""))
				&& (idLocalidadeFinal != null && !idLocalidadeFinal.toString().trim().equalsIgnoreCase(""))) {

			FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, new Integer(idLocalidadeFinal)));
			filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, new Integer(codigoSetorComercialFinal)));

			Collection<SetorComercial> setorComerciais = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());

			if (setorComerciais == null || setorComerciais.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.setor_final_inexistente");
			}
		}
	}

	private void validarDadosImovel(InserirComandoNegativacaoActionForm form, Fachada fachada) {
		// Pesquisa Cliente
		String idCliente = form.getIdCliente();
		if (idCliente != null && !idCliente.equals("")) {

			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
			Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());

			if (colecaoCliente == null || colecaoCliente.isEmpty()) {
				throw new ActionServletException("atencao.cliente.inexistente");
			}
		} else if (form.getTipoRelacao() != null && !form.getTipoRelacao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			throw new ActionServletException("atencao.tipo_relacao_nao_pode_ser_informado");
		}
	}

	private void validarDadosDebito(InserirComandoNegativacaoActionForm form, Fachada fachada) {
		Date dataAtual = new Date();
		Integer referenciaDataAtual = Util.getAnoMesComoInteger(dataAtual);

		// Per�odo de Refer�ncia do D�bito
		if (form.getReferenciaInicial() != null && !form.getReferenciaInicial().equals("") && form.getReferenciaFinal() != null
				&& !form.getReferenciaFinal().equals("")) {
			Integer referenciaInicial = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaInicial());
			Integer referenciaFinal = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaFinal());
			if (!Util.validarMesAno(form.getReferenciaInicial()) || !Util.validarMesAno(form.getReferenciaFinal())) {
				throw new ActionServletException("atencao.adicionar_debito_ano_mes_referencia_invalido", null, "do d�bito");
			} else if (referenciaInicial > referenciaFinal) {
				throw new ActionServletException("atencao.referencia.final.menor.referencia.inicial");
			} else if (referenciaDataAtual < referenciaInicial) {
				throw new ActionServletException("atencao.referencia.posterior");
			} else if (referenciaDataAtual < referenciaFinal) {
				throw new ActionServletException("atencao.referencia.posterior");
			}
		}

		FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
		Collection<SistemaParametro> collectionSistemaParametro = fachada.pesquisar(filtroSistemaParametro, SistemaParametro.class.getName());
		SistemaParametro sistemaParametro = (SistemaParametro) collectionSistemaParametro.iterator().next();

		// Per�odo de refer�ncia do d�bito
		Integer referenciaMinima = Util.subtrairAnoAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao(), 5);

		if (form.getReferenciaInicial() != null && !form.getReferenciaInicial().equals("")) {
			Integer referenciaDebInicialInformado = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaInicial());
			if (referenciaDebInicialInformado < referenciaMinima) {
				throw new ActionServletException("atencao.periodo_referencia_debito_minimo");
			}
		}

		if (form.getReferenciaFinal() != null && !form.getReferenciaFinal().equals("")) {
			Integer referenciaDebFinalInformado = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaFinal());
			if (referenciaDebFinalInformado < referenciaMinima) {
				throw new ActionServletException("atencao.periodo_referencia_debito_minimo");
			}
		}

		// Per�odo de vencimento do d�bito
		Integer numeroDiasVencimentoCobranca = new Integer(sistemaParametro.getNumeroDiasVencimentoCobranca());
		Date dataMinima = Util.subtrairNumeroAnosDeUmaData(Util.subtrairNumeroDiasDeUmaData(new Date(), numeroDiasVencimentoCobranca), -5);

		if (form.getDataVencimentoInicial() != null && !form.getDataVencimentoInicial().equals("")) {
			if (Util.validarDiaMesAno(form.getDataVencimentoInicial())) {
				throw new ActionServletException("atencao.datavencimentodebinicial.invalida");
			}
			Date vencimentoDebInicialInformado = Util.converteStringParaDate(form.getDataVencimentoInicial());
			if (Util.compararData(vencimentoDebInicialInformado, dataAtual) == 1) {
				throw new ActionServletException("atencao.data_inicial.posterior.hoje", null, Util.formatarData(new Date()));
			}
			if (Util.compararData(vencimentoDebInicialInformado, dataMinima) == -1) {
				throw new ActionServletException("atencao.periodo_vencimento_debito_minimo");
			}
		}

		if (form.getDataVencimentoFinal() != null && !form.getDataVencimentoFinal().equals("")) {
			Date vencimentoDebFinalInformado = Util.converteStringParaDate(form.getDataVencimentoFinal());
			if (Util.validarDiaMesAno(form.getDataVencimentoFinal())) {
				throw new ActionServletException("atencao.datavencimentodebfinal.invalida");
			} else if (Util.compararData(vencimentoDebFinalInformado, dataAtual) == 1) {
				throw new ActionServletException("atencao.data_final.posterior.hoje", null, Util.formatarData(new Date()));
			} else if (Util.compararData(vencimentoDebFinalInformado, dataMinima) == -1) {
				throw new ActionServletException("atencao.periodo_vencimento_debito_minimo");
			}
		}

		if (form.getDataVencimentoInicial() != null && !form.getDataVencimentoInicial().equals("") && form.getDataVencimentoFinal() != null
				&& !form.getDataVencimentoFinal().equals("")) {
			Date vencimentoDebInicial = Util.converteStringParaDate(form.getDataVencimentoInicial());
			Date vencimentoDebFinal = Util.converteStringParaDate(form.getDataVencimentoFinal());

			if (Util.compararData(vencimentoDebInicial, vencimentoDebFinal) == 1) {
				throw new ActionServletException(// Data Final do Per�odo �
													// anterior � Data Inicial
													// do Per�odo
						"atencao.data_final_periodo.anterior.data_inicial_periodo");
			}
		}

		// Valor do D�bito
		if (form.getValorDebitoInicial() != null && !form.getValorDebitoInicial().equals("") && form.getValorDebitoFinal() != null
				&& !form.getValorDebitoFinal().equals("")) {
			BigDecimal valorDebInicial = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoInicial());
			BigDecimal valorDebFinal = Util.formatarMoedaRealparaBigDecimal(form.getValorDebitoFinal());
			if (valorDebInicial.compareTo(valorDebFinal) == 1) {
				throw new ActionServletException("atencao.debito_inicial_maior_debito_final");
			}
		}

		// N�mero de Contas
		if (form.getNumeroContasInicial() != null && !form.getNumeroContasInicial().equals("") && form.getNumeroContasFinal() != null
				&& !form.getNumeroContasFinal().equals("")) {
			Integer contaInicial = new Integer(form.getNumeroContasInicial());
			Integer contaFinal = new Integer(form.getNumeroContasFinal());
			if (contaInicial.compareTo(contaFinal) == 1) {
				throw new ActionServletException("atencao.numero_conta_inicial_maior_final");
			}
		}

		// Parcela em Atraso
		if ((form.getParcelaAtraso() != null && form.getParcelaAtraso().equals("1"))
				&& (form.getDiasAtrasoParcelamento() == null || form.getDiasAtrasoParcelamento().equals(""))) {
			throw new ActionServletException("atencao.informar_dias_atraso_parcelamento");
		}

		// Recebeu Carta de Parcelamento em Atraso
		if ((form.getCartaParcelamentoAtraso() != null && form.getCartaParcelamentoAtraso().equals("1"))
				&& (form.getDiasAtrasoRecebimentoCarta() == null || form.getDiasAtrasoRecebimentoCarta().equals(""))) {
			throw new ActionServletException("atencao.informar_dias_atraso_parcelamento");
		}
	}

}
