package gcom.gui.gerencial.faturamento;

import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.fachada.Fachada;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaFinalHelper;
import gcom.gerencial.faturamento.ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper;
import gcom.gerencial.faturamento.bean.ConsultarResumoSituacaoEspecialHelper;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InformarResumoSituacaoEspecialFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarResumoSituacaoEspecialFaturamento");

		InformarResumoSituacaoEspecialFaturamentoActionForm informarResumoSituacaoEspecialFaturamentoActionForm = (InformarResumoSituacaoEspecialFaturamentoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		if (sessao.getAttribute("totalGeral") != null) {
			sessao.removeAttribute("totalGeral");
		}

		if (sessao.getAttribute("totalQtLigacoesGeral") != null) {
			sessao.removeAttribute("totalQtLigacoesGeral");
		}

		if (sessao.getAttribute("totalPercentualGeral") != null) {
			sessao.removeAttribute("totalPercentualGeral");
		}

		if (sessao.getAttribute("totalFatEstimadoGeral") != null) {
			sessao.removeAttribute("totalFatEstimadoGeral");
		}

/*		FiltroResumoFaturamentoSituacaoEspecial filtroResumoFaturamentoSituacaoEspecial = new FiltroResumoFaturamentoSituacaoEspecial();

		// recupera os ids de situacao tipo e ja gera o filtro.
		int i = 0;
		if (idsSituacaoTipo != null) {
			while (i < idsSituacaoTipo.length) {
				if (!idsSituacaoTipo[i].equals("")) {
					if (i + 1 < idsSituacaoTipo.length) {
						filtroResumoFaturamentoSituacaoEspecial
								.adicionarParametro(new ParametroSimples(
										FiltroResumoFaturamentoSituacaoEspecial.FATURAMENTO_SITUCACAO_TIPO_ID,
										idsSituacaoTipo[i],
										ConectorOr.CONECTOR_OR));
					} else {
						filtroResumoFaturamentoSituacaoEspecial
								.adicionarParametro(new ParametroSimples(
										FiltroResumoFaturamentoSituacaoEspecial.FATURAMENTO_SITUCACAO_TIPO_ID,
										idsSituacaoTipo[i]));
					}
				}
				i++;
			}
		}
		// recupera os ids de situacao motivo e ja gera o filtro.
		if (idsSituacaoMotivo != null) {
			while (i < idsSituacaoMotivo.length) {
				if (!idsSituacaoMotivo[i].equals("")) {
					if (i + 1 < idsSituacaoMotivo.length) {
						filtroResumoFaturamentoSituacaoEspecial
								.adicionarParametro(new ParametroSimples(
										FiltroResumoFaturamentoSituacaoEspecial.FATURAMENTO_SITUCACAO_MOTIVO_ID,
										idsSituacaoMotivo[i],
										ConectorOr.CONECTOR_OR));
					} else {
						filtroResumoFaturamentoSituacaoEspecial
								.adicionarParametro(new ParametroSimples(
										FiltroResumoFaturamentoSituacaoEspecial.FATURAMENTO_SITUCACAO_MOTIVO_ID,
										idsSituacaoMotivo[i]));
					}
				}
				i++;
			}
		}

		filtroResumoFaturamentoSituacaoEspecial
				.setCampoOrderBy(FiltroResumoFaturamentoSituacaoEspecial.GERENCIA_REGIONAL_ID);
		filtroResumoFaturamentoSituacaoEspecial
				.adicionarCaminhoParaCarregamentoEntidade("gerenciaRegional");
		filtroResumoFaturamentoSituacaoEspecial
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoTipo");
		filtroResumoFaturamentoSituacaoEspecial
				.adicionarCaminhoParaCarregamentoEntidade("faturamentoSituacaoMotivo");*/
		
		this.validarCampos(informarResumoSituacaoEspecialFaturamentoActionForm);
		ConsultarResumoSituacaoEspecialHelper helper = this.montarHelper(informarResumoSituacaoEspecialFaturamentoActionForm);
		
		Collection<ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper> colecaoRFSE = fachada
				.pesquisarResumoFatSitEspecial(helper);

		if (colecaoRFSE == null || colecaoRFSE.isEmpty()) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		
		BigDecimal totalGeral = new BigDecimal("0");
		Integer totalQtLigacoesGeral = new Integer("0");
		BigDecimal totalFatEstimadoGeral = new BigDecimal("0.00");
		for (Iterator iter = colecaoRFSE.iterator(); iter.hasNext();) {
			ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper = (ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper) iter.next();

			totalGeral = totalGeral.add(new BigDecimal(
							resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper
							.getTotalGerenciaRegional().toString()));
			
			totalQtLigacoesGeral = totalQtLigacoesGeral
					+ resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper
					.getTotalQtLigacoesGerencia();
			
			totalFatEstimadoGeral = totalFatEstimadoGeral.add(new BigDecimal(
							resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper
							.getTotalFatEstimadoGerencia().toString()));
		}


/*		for (Iterator iter = colecaoRFSE.iterator(); iter.hasNext();) {
			ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper = (ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper) iter
					.next();


		}*/

		BigDecimal totalPercentualGeral = new BigDecimal("0.00");
		
		
		BigDecimal p = new BigDecimal("100");
		if (totalGeral != null && totalQtLigacoesGeral != null) {			
			if (totalQtLigacoesGeral.compareTo(0) == 1){
				totalPercentualGeral= (totalGeral.multiply(p));
				totalPercentualGeral = totalPercentualGeral.divide(new BigDecimal(totalQtLigacoesGeral), 2, RoundingMode.HALF_UP);
			}
		}


/*		for (Iterator iter = colecaoRFSE.iterator(); iter.hasNext();) {
			ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper resumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper = (ResumoFaturamentoSituacaoEspecialConsultaGerenciaRegHelper) iter
					.next();


		}*/

		sessao.setAttribute("totalGeral", totalGeral);
		sessao.setAttribute("totalQtLigacoesGeral", totalQtLigacoesGeral);
		sessao.setAttribute("totalPercentualGeral", totalPercentualGeral);
		sessao.setAttribute("totalFatEstimadoGeral", totalFatEstimadoGeral);

		sessao.setAttribute("colecaoRFSE", colecaoRFSE);
		
		ResumoFaturamentoSituacaoEspecialConsultaFinalHelper resumoFaturamentoSituacaoEspecialConsultaFinalHelper = new ResumoFaturamentoSituacaoEspecialConsultaFinalHelper(colecaoRFSE, new Integer(totalGeral.intValue()), totalPercentualGeral, totalFatEstimadoGeral, totalQtLigacoesGeral);
		sessao.setAttribute("resumoFaturamentoSituacaoEspecialConsultaFinalHelper", resumoFaturamentoSituacaoEspecialConsultaFinalHelper);

		return retorno;
	}
	
	private void validarCampos(InformarResumoSituacaoEspecialFaturamentoActionForm form) {
		
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		
		verificarLocalidade(idLocalidadeInicial, "Inicial");
		verificarLocalidade(idLocalidadeFinal, "Final");
		
		String codigoSetorComericialInicial = form.getCodigoSetorComercialInicial();
		String codigoSetorComericialFinal = form.getCodigoSetorComercialFinal();
		
		verificarSetorComercial(idLocalidadeInicial, codigoSetorComericialInicial, "Inicial");
		verificarSetorComercial(idLocalidadeFinal, codigoSetorComericialFinal, "Final");
		
		if ( ( codigoSetorComericialInicial != null && !codigoSetorComericialInicial.trim().equals("") ) ||
			 ( codigoSetorComericialFinal != null && !codigoSetorComericialFinal.trim().equals("") ) ) {
			
			if (!idLocalidadeInicial.equals(idLocalidadeFinal)) {
				throw new ActionServletException("atencao.localidade_diferente");
			}
			
		}
		
		String codigoRotaInicial = form.getCodigoRotaInicial();
		String codigoRotaFinal = form.getCodigoRotaFinal();
		
		if ( ( codigoRotaInicial != null && !codigoRotaInicial.trim().equals("") ) ||
				 ( codigoRotaFinal != null && !codigoRotaFinal.trim().equals("") ) ) {
			
			if (codigoSetorComericialInicial == null || codigoSetorComericialInicial.trim().equals("")) {
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Setor Comercial");
			}
			
			if (!codigoSetorComericialInicial.equals(codigoSetorComericialFinal)) {
				throw new ActionServletException("atencao.setor_diferente");
			}
				
		}
		
		

	}
	
	private void verificarLocalidade(String idLocalidade, String tipo) {
		Fachada fachada = Fachada.getInstancia();
		
		if (idLocalidade != null && !idLocalidade.trim().equals("")) {
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer(idLocalidade));
			
			if (localidade == null) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Localidade " + tipo);
			}
			
		}
	}
	
	private void verificarSetorComercial(String idLocalidade, String codigoSetorComercial, String tipo) {
		Fachada fachada = Fachada.getInstancia();
		
		
		if (codigoSetorComercial != null && !codigoSetorComercial.trim().equals("")) {
			
			if (idLocalidade == null || idLocalidade.trim().equals("")) {
				throw new ActionServletException("atencao.campo_selecionado.obrigatorio", null, "Localidade");
			}
			
			FiltroSetorComercial filtro = new FiltroSetorComercial();
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
			filtro.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
			
			Collection colecaoSetoresComercias = fachada.pesquisar(filtro, SetorComercial.class.getName());
			
			if (colecaoSetoresComercias == null || colecaoSetoresComercias.isEmpty()) {
				throw new ActionServletException("atencao.pesquisa_inexistente", null, "Setor Comercial " + tipo);
			}
			
		}
	}

	private ConsultarResumoSituacaoEspecialHelper montarHelper(InformarResumoSituacaoEspecialFaturamentoActionForm form) {
		
		ConsultarResumoSituacaoEspecialHelper retorno = new ConsultarResumoSituacaoEspecialHelper();
		
		if (form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			retorno.setIdGerenciaRegional(form.getIdGerenciaRegional());
		}
		
		if (form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().trim().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			retorno.setIdUnidadeNegocio(form.getIdUnidadeNegocio());
		}
		
		if (form.getIdLocalidadeInicial() != null && !form.getIdLocalidadeInicial().trim().equals("")) {
			retorno.setIdLocalidadeInicial(form.getIdLocalidadeInicial());
		}
		
		if (form.getIdLocalidadeFinal() != null && !form.getIdLocalidadeFinal().trim().equals("")) {
			retorno.setIdLocalidadeFinal(form.getIdLocalidadeFinal());
		}
		
		if (form.getCodigoSetorComercialInicial() != null && !form.getCodigoSetorComercialInicial().trim().equals("")) {
			retorno.setCodigoSetorComercialInicial(form.getCodigoSetorComercialInicial());
		}
		
		if (form.getCodigoSetorComercialFinal() != null && !form.getCodigoSetorComercialFinal().trim().equals("")) {
			retorno.setCodigoSetorComercialFinal(form.getCodigoSetorComercialFinal());
		}
		
		if (form.getCodigoRotaInicial() != null && !form.getCodigoRotaInicial().trim().equals("")) {
			retorno.setCodigoRotaInicial(form.getCodigoRotaInicial());
		}
		
		if (form.getCodigoRotaFinal() != null && !form.getCodigoRotaFinal().trim().equals("")) {
			retorno.setCodigoRotaFinal(form.getCodigoRotaFinal());
		}
		
		if (form.getSituacaoTipo() != null) {
			retorno.setSituacaoTipo(form.getSituacaoTipo());
		}
		
		if (form.getSituacaoMotivo() != null) {
			retorno.setSituacaoMotivo(form.getSituacaoMotivo());
		}
		
		return retorno;
	}
}
