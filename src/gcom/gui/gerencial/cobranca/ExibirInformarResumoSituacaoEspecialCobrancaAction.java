package gcom.gui.gerencial.cobranca;

import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroSetorComercial;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.SetorComercial;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.cobranca.CobrancaSituacaoMotivo;
import gcom.cobranca.CobrancaSituacaoTipo;
import gcom.cobranca.FiltroCobrancaSituacaoMotivo;
import gcom.cobranca.FiltroCobrancaSituacaoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class ExibirInformarResumoSituacaoEspecialCobrancaAction extends
		GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInformarResumoSituacaoEspecialCobrancaAction");

		Fachada fachada = Fachada.getInstancia();
		
		InformarResumoSituacaoEspecialCobrancaActionForm form = (InformarResumoSituacaoEspecialCobrancaActionForm) actionForm;

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional(FiltroGerenciaRegional.NOME);
		Collection colecaoGerenciaRegional = fachada.pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class
				.getName()); 
		
		httpServletRequest.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);
		
		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio(FiltroUnidadeNegocio.NOME);
		Collection colecaoUnidadeNegocio = fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class
				.getName()); 
		
		httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);

		FiltroCobrancaSituacaoTipo filtroCobrancaSituacaoTipo = new FiltroCobrancaSituacaoTipo(FiltroCobrancaSituacaoTipo.DESCRICAO);
		Collection colecaoCobSitTipo = fachada.pesquisar(
				filtroCobrancaSituacaoTipo, CobrancaSituacaoTipo.class
						.getName());

		httpServletRequest.setAttribute("colecaoCobSitTipo", colecaoCobSitTipo);

		FiltroCobrancaSituacaoMotivo filtroCobrancaSituacaoMotivo = new FiltroCobrancaSituacaoMotivo(FiltroCobrancaSituacaoMotivo.DESCRICAO);
		Collection colecaoCobSitMotivo = fachada.pesquisar(
				filtroCobrancaSituacaoMotivo,
				CobrancaSituacaoMotivo.class.getName());

		httpServletRequest.setAttribute("colecaoCobSitMotivo", colecaoCobSitMotivo);
		
		pesquisarLocalidadeInicial(httpServletRequest, form);
		pesquisarLocalidadeFinal(httpServletRequest, form);
		pesquisarSetorComercialInicial(httpServletRequest, form);
		pesquisarSetorComercialFinal(httpServletRequest, form);

		return retorno;

	}
	
	private void pesquisarLocalidadeInicial(HttpServletRequest httpServletRequest, InformarResumoSituacaoEspecialCobrancaActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		
		if (idLocalidadeInicial != null && !idLocalidadeInicial.trim().equals("")) {
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer (idLocalidadeInicial));
			
			if (localidade != null) {
				form.setNomeLocalidadeInicial(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");
			} else {
				form.setIdLocalidadeInicial("");
				form.setNomeLocalidadeInicial("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "idLocalidadeInicial");
				httpServletRequest.setAttribute("localidadeInicialInexistente",	true);
			}
			
		} else {
			form.setNomeLocalidadeInicial("");
		}
	}
	
	private void pesquisarLocalidadeFinal(HttpServletRequest httpServletRequest, InformarResumoSituacaoEspecialCobrancaActionForm form) {
		
		Fachada fachada = Fachada.getInstancia();
		
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		
		if (idLocalidadeFinal != null && !idLocalidadeFinal.trim().equals("")) {
			Localidade localidade = fachada.pesquisarLocalidadeDigitada(new Integer (idLocalidadeFinal));
			
			if (localidade != null) {
				form.setNomeLocalidadeFinal(localidade.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialFinal");
			} else {
				form.setIdLocalidadeFinal("");
				form.setNomeLocalidadeFinal("LOCALIDADE INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "idLocalidadeFinal");
				httpServletRequest.setAttribute("localidadeFinalInexistente", true);
			}
			
		} else {
			form.setNomeLocalidadeFinal("");
		}
	}
	
	private void pesquisarSetorComercialInicial(HttpServletRequest httpServletRequest, InformarResumoSituacaoEspecialCobrancaActionForm form) {
		
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		String codigoSetorComercialInicial = form.getCodigoSetorComercialInicial();
		
		if (codigoSetorComercialInicial != null && !codigoSetorComercialInicial.trim().equals("")) {
			SetorComercial setorComercial = pesquisarSetorComercial(idLocalidadeInicial, codigoSetorComercialInicial);
			
			if (setorComercial != null) {
				form.setDescricaoSetorComercialInicial(setorComercial.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "codigoRotaInicial");
			} else {
				form.setCodigoSetorComercialInicial("");
				form.setDescricaoSetorComercialInicial("SETOR COMERCIAL INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");
				httpServletRequest.setAttribute("setorComercialInicialInexistente", true);
			}
		} else {
			form.setDescricaoSetorComercialInicial("");
		}
	}
	
	private void pesquisarSetorComercialFinal(HttpServletRequest httpServletRequest, InformarResumoSituacaoEspecialCobrancaActionForm form) {
		
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		String codigoSetorComercialFinal = form.getCodigoSetorComercialFinal();
		
		if (codigoSetorComercialFinal != null && !codigoSetorComercialFinal.trim().equals("")) {
			SetorComercial setorComercial = pesquisarSetorComercial(idLocalidadeFinal, codigoSetorComercialFinal);
			
			if (setorComercial != null) {
				form.setDescricaoSetorComercialFinal(setorComercial.getDescricao());
				httpServletRequest.setAttribute("nomeCampo", "codigoRotaInicial");
			} else {
				form.setCodigoSetorComercialFinal("");
				form.setDescricaoSetorComercialFinal("SETOR COMERCIAL INEXISTENTE");
				httpServletRequest.setAttribute("nomeCampo", "codigoSetorComercialInicial");
				httpServletRequest.setAttribute("setorComercialFinalInexistente", true);
			}
		} else {
			form.setDescricaoSetorComercialFinal("");
		}
	}
	
	private SetorComercial pesquisarSetorComercial(String idLocalidade, String codigoSetorComercial) {
		
		SetorComercial retorno = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		FiltroSetorComercial filtroSetorComercial = new FiltroSetorComercial();
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.ID_LOCALIDADE, idLocalidade));
		filtroSetorComercial.adicionarParametro(new ParametroSimples(FiltroSetorComercial.CODIGO_SETOR_COMERCIAL, codigoSetorComercial));
		
		Collection colecaoSetorComercial = fachada.pesquisar(filtroSetorComercial, SetorComercial.class.getName());
		
		if (colecaoSetorComercial != null && !colecaoSetorComercial.isEmpty()) {
			retorno = (SetorComercial) Util.retonarObjetoDeColecao(colecaoSetorComercial);
		}
		
		return retorno;
		
	}

}
