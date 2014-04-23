package gcom.gui.relatorio.atendimentopublico.ordemservico;

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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1109] Filtrar Dados para Geração Boletim de Custo de Repavimentação
 * 
 * @author Hugo Leonardo
 *
 * @date 30/12/2010
 */

public class ExibirGerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirGerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimento");
		
		GerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoForm form = 
			(GerarRelatorioBoletimCustoRepavimentacaoPorTipoPavimentoForm) actionForm;
		
		//HttpSession sessao = httpServletRequest.getSession(false);

		this.pesquisarUnidadeRepavimentadora(httpServletRequest);
		
		// seta como Analítico
		form.setIndicadorTipoBoletim("1");
		
		return retorno;
	}
	
	/**
	 * Pesquisar Unidade Repavimentadora 
	 *
	 * @author Hugo Leonardo
	 * @date 30/12/2010
	 */
	private void pesquisarUnidadeRepavimentadora(HttpServletRequest httpServletRequest) {

		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.INDICADOR_USO, ConstantesSistema.SIM));
		filtroUnidadeOrganizacional.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacional.UNIDADE_TIPO_CODIGO, "R"));
		filtroUnidadeOrganizacional.setCampoOrderBy(FiltroUnidadeOrganizacional.DESCRICAO);

		Collection<UnidadeOrganizacional> colecaoUnidadeRepavimentadora = Fachada.getInstancia().pesquisar(
				filtroUnidadeOrganizacional, UnidadeOrganizacional.class.getName());

		if(!Util.isVazioOrNulo(colecaoUnidadeRepavimentadora)){
			
			httpServletRequest.setAttribute("colecaoUnidadeRepavimentadora", colecaoUnidadeRepavimentadora);
		}else{
			
			throw new ActionServletException("atencao.naocadastrado", null, "Unidade Repavimentadora");
		}
	}
	
}
