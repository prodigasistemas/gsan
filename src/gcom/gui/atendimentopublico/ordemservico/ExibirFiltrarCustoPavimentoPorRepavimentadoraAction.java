package gcom.gui.atendimentopublico.ordemservico;

import gcom.cadastro.imovel.FiltroPavimentoCalcada;
import gcom.cadastro.imovel.FiltroPavimentoRua;
import gcom.cadastro.imovel.PavimentoCalcada;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1108] Filtrar Custo de Pavimento por Repavimentadora
 * 
 * @author Hugo Leonardo
 * @date 21/12/2010
 */
public class ExibirFiltrarCustoPavimentoPorRepavimentadoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("filtrarCustoPavimentoPorRepavimentadora");

		FiltrarCustoPavimentoPorRepavimentadoraActionForm form = (FiltrarCustoPavimentoPorRepavimentadoraActionForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		if (httpServletRequest.getParameter("menu") != null
				&& httpServletRequest.getParameter("menu").equals("sim")) {
			
			form.reset();
			form.setAtualizar("1");
			
			if(sessao.getAttribute("colecaoUnidadeRepavimentadora") == null){
				this.pesquisarUnidadeRepavimentadora(sessao);
			}
			
			if(sessao.getAttribute("colecaoPavimentoRua") == null){
				this.pesquisarTipoPavimentoRua(sessao);
			}
			
			if(sessao.getAttribute("colecaoPavimentoCalcada") == null){
				this.pesquisarTipoPavimentoCalcada(sessao);
			}
		}

		return retorno;
	}

	/**
	 * Pesquisar Unidade Repavimentadora 
	 *
	 * @author Hugo Leonardo
	 * @date 22/12/2010
	 */
	private void pesquisarUnidadeRepavimentadora(HttpSession sessao) {

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.SIM));
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, "R"));
		filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);

		Collection<UnidadeOrganizacional> colecaoUnidadeRepavimentadora = Fachada.getInstancia().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if(!Util.isVazioOrNulo(colecaoUnidadeRepavimentadora)){
			
			sessao.setAttribute("colecaoUnidadeRepavimentadora", colecaoUnidadeRepavimentadora);
		}else{
			
			throw new ActionServletException("atencao.naocadastrado", null, "Unidade Repavimentadora");
		}
	}
	
	/**
	 * Pesquisar Tipo do Pavimento de Rua  
	 *
	 * @author Hugo Leonardo
	 * @date 22/12/2010
	 */
	private void pesquisarTipoPavimentoRua(HttpSession sessao) {

		FiltroPavimentoRua filtroPavimentoRua = new FiltroPavimentoRua();
		filtroPavimentoRua.adicionarParametro(new ParametroSimples(
				FiltroPavimentoRua.INDICADOR_USO, ConstantesSistema.SIM));
		filtroPavimentoRua.setConsultaSemLimites(true);
		
		filtroPavimentoRua.setCampoOrderBy(FiltroPavimentoRua.DESCRICAO);
		
		Collection<PavimentoRua> colecaoPavimentoRua = Fachada.getInstancia().pesquisar(
				filtroPavimentoRua, PavimentoRua.class.getName());

		if(!Util.isVazioOrNulo(colecaoPavimentoRua)){
			
			sessao.setAttribute("colecaoPavimentoRua", colecaoPavimentoRua);
		}else{
			
			throw new ActionServletException("atencao.naocadastrado", null, "Pavimento Rua");
		}
	}
	
	/**
	 * Pesquisar Tipo do Pavimento de Calçada
	 *
	 * @author Hugo Leonardo
	 * @date 22/12/2010
	 */
	private void pesquisarTipoPavimentoCalcada(HttpSession sessao) {

		FiltroPavimentoCalcada filtroPavimentoCalcada = new FiltroPavimentoCalcada();
		filtroPavimentoCalcada.adicionarParametro(new ParametroSimples(
				FiltroPavimentoCalcada.INDICADOR_USO, ConstantesSistema.SIM));
		filtroPavimentoCalcada.setConsultaSemLimites(true);
		
		filtroPavimentoCalcada.setCampoOrderBy(FiltroPavimentoCalcada.DESCRICAO);
		
		Collection<PavimentoCalcada> colecaoPavimentoCalcada = Fachada.getInstancia().pesquisar(
				filtroPavimentoCalcada, PavimentoCalcada.class.getName());

		if(!Util.isVazioOrNulo(colecaoPavimentoCalcada)){
			
			sessao.setAttribute("colecaoPavimentoCalcada", colecaoPavimentoCalcada);
		}
	}
	
}
