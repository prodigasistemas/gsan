package gcom.gui.cobranca;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.micromedicao.bean.AssociarConjuntoRotasCriterioCobrancaHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/** 
 * @author Flavio Leonardo, Raphael Rossiter
 * @date 24/01/2008 
 */
public class SelecionarAssociarConjuntoRotasCriterioCobrancaAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirAssociarRotasCriterioCobrancaSelecionar");

		AssociarConjuntoRotasCriterioCobrancaActionForm form = (AssociarConjuntoRotasCriterioCobrancaActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		sessao.setAttribute("AssociarConjuntoRotasCriterioCobrancaActionForm", form);
		
		//CARREGANDO OBJETO HELPER
		AssociarConjuntoRotasCriterioCobrancaHelper parametros = this.carregarHelper(form);
		
		//PESQUISANDO QUANTIDADE DE ROTAS
		String[] qtdRotasArray = fachada.pesquisarQuantidadeRotas(parametros);
		
		
		String qtdRotas = "";
		String qtdRotasComCriterio = "";
		String qtdRotasSemCriterio = "";
		
		if(qtdRotasArray != null){
			qtdRotas = qtdRotasArray[0];
			qtdRotasComCriterio = qtdRotasArray[1];
			qtdRotasSemCriterio = qtdRotasArray[2];
			form.setQtdRotasSelecionadas(qtdRotas);
			form.setQtdRotasComCriterio(qtdRotasComCriterio);
			form.setQtdRotasSemCriterio(qtdRotasSemCriterio);
		}else{
			form.setQtdRotasSelecionadas("0");
			form.setQtdRotasComCriterio("0");
			form.setQtdRotasSemCriterio("0");
		}
		
		sessao.setAttribute("qtdRotas",form.getQtdRotasSelecionadas());
		sessao.setAttribute("qtdRotasCom",form.getQtdRotasComCriterio());
		sessao.setAttribute("qtdRotasSem",form.getQtdRotasSemCriterio());
		
		return retorno;
	}
	
	
	private AssociarConjuntoRotasCriterioCobrancaHelper carregarHelper(AssociarConjuntoRotasCriterioCobrancaActionForm 
			form){
		
		AssociarConjuntoRotasCriterioCobrancaHelper parametros = 
		new AssociarConjuntoRotasCriterioCobrancaHelper();
		
		String idGrupoCobranca = form.getIdGrupoCobranca();
		String idGerencialRegional = form.getIdGerenciaRegional();
		String idUnidadeNegocio = form.getIdUnidadeNegocio();
		String idLocalidadeInicial = form.getIdLocalidadeInicial();
		String idLocalidadeFinal = form.getIdLocalidadeFinal();
		String codigoSetorInicial = form.getCodigoSetorComercialInicial();
		String codigoSetorFinal = form.getCodigoSetorComercialFinal();
		String rotaInicial = form.getNumeroRotaInicial();
		String rotaFinal = form.getNumeroRotaFinal();
		String idCobrancaAcao = form.getIdAcaoCobranca();
		String idCriterio = form.getIdCriterioCobranca();
		
		parametros.setIdCobrancaAcao(idCobrancaAcao != null && 
				!idCobrancaAcao.equals("-1")?new Integer(idCobrancaAcao): null);
		
		// o criterio foi comentado para nao ser utilizado como argumento de pesquisa das quantidades
		
		parametros.setIdCriterioCobranca(idCriterio != null && !idCriterio.equals("")?new Integer(idCriterio): null);
		
		parametros.setIdGrupoCobranca(idGrupoCobranca != null && 
		!idGrupoCobranca.equals("-1")?new Integer(idGrupoCobranca): null);
		parametros.setIdGerencialRegional(idGerencialRegional != null && 
		!idGerencialRegional.equals("-1")?new Integer(idGerencialRegional): null);
		parametros.setIdUnidadeNegocio(idUnidadeNegocio != null && 
		!idUnidadeNegocio.equals("-1")?new Integer(idUnidadeNegocio): null);
		
		parametros.setIdLocalidadeInicial(idLocalidadeInicial != null && 
		!idLocalidadeInicial.equals("")?new Integer(idLocalidadeInicial): null);
		parametros.setIdLocalidadeFinal(idLocalidadeFinal != null && 
		!idLocalidadeFinal.equals("")?new Integer(idLocalidadeFinal): null);
		
		parametros.setCdSetorComercialInicial(codigoSetorInicial != null && 
		!codigoSetorInicial.equals("")?new Integer(codigoSetorInicial): null);
		parametros.setCdSetorComercialFinal(codigoSetorFinal != null && 
		!codigoSetorFinal.equals("")?new Integer(codigoSetorFinal): null);
		
		parametros.setNnRotaInicial(rotaInicial != null && 
		!rotaInicial.equals("-1")?new Integer(rotaInicial): null);
		parametros.setNnRotaFinal(rotaFinal != null && 
		!rotaFinal.equals("-1")?new Integer(rotaFinal): null);
		
		
		return parametros;
	}
}
