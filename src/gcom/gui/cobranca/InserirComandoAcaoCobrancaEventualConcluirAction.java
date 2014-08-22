package gcom.gui.cobranca;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaAtividade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroRota;
import gcom.micromedicao.Rota;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de Ação de Conbrança - Tipo de Comando Cronograma
 * Executado qdo o usuário clica em Concluir estando na tela de
 * comandar_acao_cobranca_eventual_inserir_processo2.jsp
 */
public class InserirComandoAcaoCobrancaEventualConcluirAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form = (InserirComandoAcaoCobrancaEventualCriterioRotaActionForm) actionForm;

		String idLocalidadeInicial = form.getLocalidadeOrigemID();
		String idLocalidadeFinal = form.getLocalidadeDestinoID();
		String codigoSetorComercialInicial = form.getSetorComercialOrigemCD();
		String codigoSetorComercialFinal = form.getSetorComercialDestinoCD();

		validarLocalidadeInicial(idLocalidadeInicial);
		validarSetorComercialInicial(idLocalidadeInicial, codigoSetorComercialInicial);
		validarLocalidadeFinal(idLocalidadeInicial, idLocalidadeFinal);
		validarSetorComercialFinal(idLocalidadeInicial, codigoSetorComercialInicial, idLocalidadeFinal, codigoSetorComercialFinal);

		String codigoRotaInicial = form.getRotaInicial();
		String idRotaInicial = validarRotaInicial(idLocalidadeInicial, codigoSetorComercialInicial, codigoRotaInicial);

		String codigoRotaFinal = form.getRotaFinal();
		String idRotaFinal = validarRotaFinal(idLocalidadeFinal, codigoSetorComercialFinal, codigoRotaFinal);

		validarDataVencimentoContaFinal(form); 
		
		Collection colecaoCobrancaAcaoAtividadeComando = this.getFachada().concluirComandoAcaoCobranca(
				form.getPeriodoInicialConta(), form.getPeriodoFinalConta(), form.getPeriodoVencimentoContaInicial(),
				form.getPeriodoVencimentoContaFinal(), form.getCobrancaAcao(), form.getCobrancaAtividade(),
				form.getCobrancaGrupo(), form.getGerenciaRegional(), form.getLocalidadeOrigemID(),
				form.getLocalidadeDestinoID(), form.getSetorComercialOrigemCD(), form.getSetorComercialDestinoCD(),
				form.getIdCliente(), form.getClienteRelacaoTipo(), form.getIndicador(), idRotaInicial, idRotaFinal,
				form.getSetorComercialOrigemID(), form.getSetorComercialDestinoID(), null, form.getUnidadeNegocio(),
				this.getUsuarioLogado(httpServletRequest), form.getTitulo(), form.getDescricaoSolicitacao(),
				form.getPrazoExecucao(), form.getQuantidadeMaximaDocumentos(), form.getValorLimiteObrigatoria(),
				form.getIndicadorImoveisDebito(), form.getIndicadorGerarBoletimCadastro(),
				form.getCodigoClienteSuperior(), codigoRotaInicial, codigoRotaFinal, form.getLogradouroId(),
				form.getConsumoMedioInicial(), form.getConsumoMedioFinal(), form.getTipoConsumo(),
				form.getPeriodoInicialFiscalizacao(), form.getPeriodoFinalFiscalizacao(),
				form.getSituacaoFiscalizacao(), form.getNumeroQuadraInicial(), form.getNumeroQuadraFinal());

		CobrancaAtividade cobrancaAtividade = this.getFachada().consultarCobrancaAtividade(form.getCobrancaAtividade());

		montarPaginaSucesso(httpServletRequest, " " + colecaoCobrancaAcaoAtividadeComando.size()
				+ " Ação(ões) de cobrança para a atividade " + cobrancaAtividade.getDescricaoCobrancaAtividade()
				+ " comandada(s) com sucesso.", "Inserir outra Comando de Ação de Cobrança",
				"exibirInserirComandoAcaoCobrancaAction.do?limparForm=OK&menu=sim");

		return retorno;
	}

	private void validarLocalidadeInicial(String idLocalidadeInicial) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		if (idLocalidadeInicial != null && !idLocalidadeInicial.toString().trim().equalsIgnoreCase("")) {

			filtroLocalidade.limparListaParametros();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,
					new Integer(idLocalidadeInicial)));
			
			Collection localidades = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if (localidades == null || localidades.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.localidade_inicial_inexistente");
			}
		}
	}

	private void validarSetorComercialInicial(String idLocalidade, String codigoSetorComercial) {
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (codigoSetorComercial != null && !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {

			if (idLocalidade != null && !idLocalidade.toString().trim().equalsIgnoreCase("")) {

				filtroSetorComercial.limparListaParametros();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,
						new Integer(idLocalidade)));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						new Integer(codigoSetorComercial)));
				
				Collection setorComerciais = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (setorComerciais == null || setorComerciais.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.setor_inicial_inexistente");
				}
			}

		}
	}

	private void validarLocalidadeFinal(String idLocalidade, String idLocalidadeFinal) {
		FiltroLocalidade filtroLocalidade;
		filtroLocalidade = new FiltroLocalidade();
		if (idLocalidade != null && !idLocalidade.toString().trim().equalsIgnoreCase("")) {

			filtroLocalidade.limparListaParametros();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID,
					new Integer(idLocalidadeFinal)));

			Collection localidades = this.getFachada().pesquisar(filtroLocalidade, Localidade.class.getName());

			if (localidades == null || localidades.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.localidade_final_inexistente");
			}
		}
	}

	private void validarSetorComercialFinal(String idLocalidade, String codigoSetorComercial, String idLocalidadeFinal,
			String codigoSetorComercialFinal) {
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (codigoSetorComercial != null && !codigoSetorComercial.toString().trim().equalsIgnoreCase("")) {

			if (idLocalidade != null && !idLocalidade.toString().trim().equalsIgnoreCase("")) {

				filtroSetorComercial.limparListaParametros();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,
						new Integer(idLocalidadeFinal)));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						new Integer(codigoSetorComercialFinal)));

				Collection setorComerciais = this.getFachada().pesquisar(filtroSetorComercial, SetorComercial.class.getName());

				if (setorComerciais == null || setorComerciais.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.setor_final_inexistente");
				}
			}
		}
	}

	private String validarRotaInicial(String idLocalidade, String codigoSetorComercial, String codigoRotaInicial) {
		String idRotaInicial = null;
		if ((idLocalidade != null && !idLocalidade.equals(""))
				&& (codigoSetorComercial != null && !codigoSetorComercial.equals(""))
				&& (codigoRotaInicial != null && !codigoRotaInicial.equals(""))) {

			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, codigoRotaInicial));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID, idLocalidade));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetorComercial));

			Collection rotas = this.getFachada().pesquisar(filtroRota, Rota.class.getName());
			
			if (rotas != null && !rotas.isEmpty()) {
				idRotaInicial = ((Rota) rotas.iterator().next()).getId().toString();
			} else {
				throw new ActionServletException("atencao.pesquisa.rota_inicial_inexistente");
			}
		}
		return idRotaInicial;
	}

	private String validarRotaFinal(String idLocalidadeFinal, String codigoSetorComercialFinal, String codigoRotaFinal) {
		String idRotaFinal = null;

		if ((idLocalidadeFinal != null && !idLocalidadeFinal.equals(""))
				&& (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.equals(""))
				&& (codigoRotaFinal != null && !codigoRotaFinal.equals(""))) {
			
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.limparListaParametros();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID, idLocalidadeFinal));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetorComercialFinal));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, codigoRotaFinal));
			
			Collection rotas = this.getFachada().pesquisar(filtroRota, Rota.class.getName());
			
			if (rotas != null && !rotas.isEmpty()) {
				idRotaFinal = ((Rota) rotas.iterator().next()).getId().toString();
			} else {
				throw new ActionServletException("atencao.pesquisa.rota_final_inexistente");
			}
		}
		
		return idRotaFinal;
	}

	private void validarDataVencimentoContaFinal(InserirComandoAcaoCobrancaEventualCriterioRotaActionForm form) {
		try {
			DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date periodoVencimentoContaFinal = (Date) formato.parse(form.getPeriodoVencimentoContaFinal());
			
			Short numeroDiasVencimentoCobranca = this.getFachada().pesquisarParametrosDoSistema().getNumeroDiasVencimentoCobranca();
			Date dataVencimentoFinalCobranca = Util.subtrairNumeroDiasDeUmaData(new Date(), numeroDiasVencimentoCobranca.intValue());
			dataVencimentoFinalCobranca = Util.formatarDataFinal(dataVencimentoFinalCobranca);
			
			if (periodoVencimentoContaFinal.after(dataVencimentoFinalCobranca)) {
				throw new ActionServletException("atencao.periodo_final_invalido");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
