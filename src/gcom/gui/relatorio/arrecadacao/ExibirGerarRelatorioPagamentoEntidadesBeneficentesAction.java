package gcom.gui.relatorio.arrecadacao;

import gcom.cadastro.imovel.EntidadeBeneficente;
import gcom.cadastro.imovel.FiltroEntidadeBeneficente;
import gcom.cadastro.localidade.FiltroGerenciaRegional;
import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.FiltroUnidadeNegocio;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Pré-processamento da primeira página de [UC0978] Gerar Relatório de Pagamento para
 * Entidades Beneficentes
 * 
 * @author Daniel Alves
 * @created 13/01/2010
 */
public class ExibirGerarRelatorioPagamentoEntidadesBeneficentesAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioPagamentoEntidadesBeneficentes");

		
		GerarRelatorioPagamentoEntidadesBeneficentesActionForm form = (GerarRelatorioPagamentoEntidadesBeneficentesActionForm) actionForm;
		form.setTipo("sintetico");
		
		Fachada fachada = Fachada.getInstancia();
        HttpSession sessao = httpServletRequest.getSession(false);
        
		FiltroEntidadeBeneficente filtroEntidadeBeneficente = new FiltroEntidadeBeneficente();
		filtroEntidadeBeneficente.adicionarCaminhoParaCarregamentoEntidade("cliente");
		
		Collection colecaoEntidadesBeneficentes = 
			fachada.pesquisar(filtroEntidadeBeneficente, EntidadeBeneficente.class.getName());
		
		httpServletRequest.setAttribute("colecaoEntidadesBeneficentes", colecaoEntidadesBeneficentes);		

		String mesAnoInicial = httpServletRequest.getParameter("mesAnoInicial");
		String mesAnoFinal = httpServletRequest.getParameter("mesAnoFinal");        
        
		//Verifica se a Data Final é maior que a Inicial
        if ( mesAnoFinal != null && !mesAnoFinal.equals("")
        		&& mesAnoInicial != null && !mesAnoInicial.equals("")){
        	
        	Date dtInicial = Util.converteStringParaDate(mesAnoInicial);
        	Date dtFinal = Util.converteStringParaDate(mesAnoFinal);
        	
        	if (Util.compararData(dtFinal, dtInicial)== -1){
        		
        		throw new ActionServletException("atencao.data.intervalo.invalido", null ,"Data Invalida" );
        		
        	}        	
        }			

		FiltroGerenciaRegional filtroGerenciaRegional = new FiltroGerenciaRegional();
		filtroGerenciaRegional.setCampoOrderBy(FiltroGerenciaRegional.NOME);
		Collection<GerenciaRegional> colecaoGerenciaRegional = fachada.pesquisar(
				filtroGerenciaRegional, GerenciaRegional.class.getName());

		sessao.setAttribute("colecaoGerenciaRegional", colecaoGerenciaRegional);


		FiltroUnidadeNegocio filtroUnidadeNegocio = new FiltroUnidadeNegocio();
		filtroUnidadeNegocio.setCampoOrderBy(FiltroUnidadeNegocio.NOME);
		Collection<UnidadeNegocio> colecaoUnidadeNegocio = fachada.pesquisar(
				filtroUnidadeNegocio, UnidadeNegocio.class.getName());

		httpServletRequest.setAttribute("colecaoUnidadeNegocio", colecaoUnidadeNegocio);
		
		
		FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
		filtroLocalidade.setCampoOrderBy(FiltroLocalidade.DESCRICAO);
		Collection<Localidade> colecaoLocalidade = fachada.pesquisar(
				filtroLocalidade, Localidade.class.getName());

		httpServletRequest.setAttribute("colecaoLocalidade", colecaoLocalidade);		

		return retorno;
	}
}
