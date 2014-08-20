package gcom.gui.cobranca;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cobranca.CobrancaAtividade;
import gcom.fachada.Fachada;
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
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0243] Inserir Comando de Ação de Conbrança - Tipo de Comando Cronograma
 */
public class InserirComandoAcaoCobrancaEventualCriterioComandoConcluirAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		HttpSession sessao = request.getSession(false);

		Fachada fachada = Fachada.getInstancia();
		
		InserirComandoAcaoCobrancaEventualCriterioComandoActionForm form = null;
		if (sessao.getAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm") != null) {
			form = (InserirComandoAcaoCobrancaEventualCriterioComandoActionForm) 
					sessao.getAttribute("inserirComandoAcaoCobrancaEventualCriterioComandoActionForm");
		}

		String idComando = request.getParameter("idComando");

		String idLocalidadeInicial = form.getLocalidadeOrigemID();
		String idLocalidadeFinal = form.getLocalidadeDestinoID();
		String codigoSetorComercialInicial = form.getSetorComercialOrigemCD();
		String codigoSetorComercialFinal = form.getSetorComercialDestinoCD();

		validarLocalidadeInicial(fachada, idLocalidadeInicial);
		validarSetorComercialInicial(fachada, idLocalidadeInicial, codigoSetorComercialInicial);
		validarLocalidadeFinal(fachada, idLocalidadeInicial, idLocalidadeFinal);
		validarSetorComercialFinal(fachada, idLocalidadeInicial, idLocalidadeFinal, codigoSetorComercialInicial, codigoSetorComercialFinal);

		String codigoRotaInicial = form.getRotaInicial();
		String idRotaInicial = validarRotaInicial(fachada, idLocalidadeInicial, codigoSetorComercialInicial, codigoRotaInicial);
		String codigoRotaFinal = form.getRotaFinal();
		String idRotaFinal = validarRotaFinal(fachada, idLocalidadeFinal, codigoSetorComercialFinal, codigoRotaFinal);

		validarDataVencimentoContaFinal(form);
		
		Collection colecaoCobrancaAcaoAtividadeComando = fachada.concluirComandoAcaoCobranca(
				form.getPeriodoInicialConta(), form.getPeriodoFinalConta(), form.getPeriodoVencimentoContaInicial(),
				form.getPeriodoVencimentoContaFinal(), form.getCobrancaAcao(), form.getCobrancaAtividade(),
				form.getCobrancaGrupo(), form.getGerenciaRegional(), form.getLocalidadeOrigemID(),
				form.getLocalidadeDestinoID(), form.getSetorComercialOrigemCD(), form.getSetorComercialDestinoCD(),
				form.getIdCliente(), form.getClienteRelacaoTipo(), form.getIndicador(), idRotaInicial, idRotaFinal,
				form.getSetorComercialOrigemID(), form.getSetorComercialDestinoID(), idComando, form.getUnidadeNegocio(),
				this.getUsuarioLogado(request), form.getTitulo(), form.getDescricaoSolicitacao(), form.getPrazoExecucao(),
				form.getQuantidadeMaximaDocumentos(), form.getValorLimiteObrigatoria(), form.getIndicadorImoveisDebito(),
				form.getIndicadorGerarBoletimCadastro(), form.getCodigoClienteSuperior(), codigoRotaInicial, codigoRotaFinal,
				form.getLogradouroId(), form.getConsumoMedioInicial(), form.getConsumoMedioFinal(), form.getTipoConsumo(),
				form.getPeriodoInicialFiscalizacao(), form.getPeriodoFinalFiscalizacao(), form.getSituacaoFiscalizacao(),
				form.getNumeroQuadraInicial(), form.getNumeroQuadraFinal());

		CobrancaAtividade cobrancaAtividade = fachada.consultarCobrancaAtividade(form.getCobrancaAtividade());

		montarPaginaSucesso(request, " " + colecaoCobrancaAcaoAtividadeComando.size()
				+ " Ação(ões) de cobrança para a atividade " + cobrancaAtividade.getDescricaoCobrancaAtividade()
				+ " comandada(s) com sucesso.", "Inserir outra Comando de Ação de Cobrança",
				"exibirInserirComandoAcaoCobrancaAction.do?limparForm=OK&menu=sim");

		return retorno;
	}

	private void validarDataVencimentoContaFinal(InserirComandoAcaoCobrancaEventualCriterioComandoActionForm form) {
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
	
	private String validarRotaFinal(Fachada fachada, String idLocalidadeFinal, String codigoSetorComercialFinal,
			String codigoRotaFinal) {
		
		String idRotaFinal = null;
		
		if ((idLocalidadeFinal != null && !idLocalidadeFinal.equals(""))
				&& (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.equals(""))
				&& (codigoRotaFinal != null && !codigoRotaFinal.equals(""))) {
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.limparListaParametros();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID, idLocalidadeFinal));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetorComercialFinal));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, codigoRotaFinal));
			Collection rotas = fachada.pesquisar(filtroRota, Rota.class.getName());
			
			if (rotas != null && !rotas.isEmpty()) {
				idRotaFinal = ((Rota) rotas.iterator().next()).getId().toString();
			} else {
				throw new ActionServletException("atencao.pesquisa.rota_final_inexistente");
			}
		}
		return idRotaFinal;
	}

	private String validarRotaInicial(Fachada fachada, String idLocalidadeInicial, String codigoSetorComercialInicial,
			String codigoRotaInicial) {
		
		String idRotaInicial = null;
		
		if ((idLocalidadeInicial != null && !idLocalidadeInicial.equals(""))
				&& (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.equals(""))
				&& (codigoRotaInicial != null && !codigoRotaInicial.equals(""))) {
			FiltroRota filtroRota = new FiltroRota();
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.CODIGO_ROTA, codigoRotaInicial));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.LOCALIDADE_ID, idLocalidadeInicial));
			filtroRota.adicionarParametro(new ParametroSimples(FiltroRota.SETOR_COMERCIAL_CODIGO, codigoSetorComercialInicial));

			Collection rotas = fachada.pesquisar(filtroRota, Rota.class.getName());
			if (rotas != null && !rotas.isEmpty()) {
				idRotaInicial = ((Rota) rotas.iterator().next()).getId().toString();
			} else {
				throw new ActionServletException("atencao.pesquisa.rota_inicial_inexistente");
			}
		}
		return idRotaInicial;
	}

	private void validarSetorComercialFinal(Fachada fachada, String idLocalidadeInicial, String idLocalidadeFinal,
			String codigoSetorComercialInicial, String codigoSetorComercialFinal) {
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.toString().trim().equalsIgnoreCase("")) {
			if (idLocalidadeInicial != null && !idLocalidadeInicial.toString().trim().equalsIgnoreCase("")) {
				filtroSetorComercial.limparListaParametros();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,
						new Integer(idLocalidadeFinal)));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						new Integer(codigoSetorComercialFinal)));

				Collection setorComerciais = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				if (setorComerciais == null || setorComerciais.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.setor_final_inexistente");
				}
			}
		}
	}

	private void validarLocalidadeFinal(Fachada fachada, String idLocalidadeInicial, String idLocalidadeFinal) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		if (idLocalidadeInicial != null && !idLocalidadeInicial.toString().trim().equalsIgnoreCase("")) {
			filtroLocalidade.limparListaParametros();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, new Integer(idLocalidadeFinal)));

			Collection localidades = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			if (localidades == null || localidades.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.localidade_final_inexistente");
			}
		}
	}

	private void validarSetorComercialInicial(Fachada fachada, String idLocalidadeInicial,
			String codigoSetorComercialInicial) {
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.toString().trim().equalsIgnoreCase("")) {
			if (idLocalidadeInicial != null && !idLocalidadeInicial.toString().trim().equalsIgnoreCase("")) {
				filtroSetorComercial.limparListaParametros();
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.INDICADORUSO,
						ConstantesSistema.INDICADOR_USO_ATIVO));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE,
						new Integer(idLocalidadeInicial)));
				filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL,
						new Integer(codigoSetorComercialInicial)));

				Collection setorComerciais = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
				if (setorComerciais == null || setorComerciais.isEmpty()) {
					throw new ActionServletException("atencao.pesquisa.setor_inicial_inexistente");
				}
			}
		}
	}

	private void validarLocalidadeInicial(Fachada fachada, String idLocalidadeInicial) {
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		if (idLocalidadeInicial != null && !idLocalidadeInicial.toString().trim().equalsIgnoreCase("")) {
			filtroLocalidade.limparListaParametros();
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
			filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, new Integer(idLocalidadeInicial)));

			Collection localidades = fachada.pesquisar(filtroLocalidade, Localidade.class.getName());
			if (localidades == null || localidades.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa.localidade_inicial_inexistente");
			}
		}
	}

}
