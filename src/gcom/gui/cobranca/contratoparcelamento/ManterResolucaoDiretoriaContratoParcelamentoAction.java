package gcom.gui.cobranca.contratoparcelamento;

import java.util.ArrayList;
import java.util.Collection;

import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.FiltroContratoParcelamentoRD;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de Filtrar Resolucao de Diretoria para Contrato pro Cliente
 * 
 * @author Paulo Diniz
 * @created 16/03/2011
 */
public class ManterResolucaoDiretoriaContratoParcelamentoAction extends
		GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma resolução de diretoria
	 * 
	 * [UC1134] Manter Resolução de Diretoria (RD) para Contratos de Parcelamento por cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	//Obtém a instância da fachada
	Fachada fachada = Fachada.getInstancia();

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping.findForward("manterResolucaoDiretoria");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		FiltroContratoParcelamentoRD filtro = (FiltroContratoParcelamentoRD) sessao.getAttribute("filtroContratoParcelamento");
		String indicadorAtualizar = (String) sessao.getAttribute("indicadorAtualizar");
		Integer totalRegistros = 0;
		 
		totalRegistros = this.getFachada().totalRegistrosPesquisa(filtro,ContratoParcelamentoRD.class.getName());
		
		if(totalRegistros != null && totalRegistros.intValue() == 0){
			throw new ActionServletException(
			"atencao.pesquisa.nenhumresultado");
		}else if(totalRegistros.intValue() == 1 && indicadorAtualizar != null){
			retorno = actionMapping.findForward("atualizarResolucaoDiretoria");
			Collection<ContratoParcelamentoRD> coll = this.getFachada().pesquisar(filtro, ContratoParcelamentoRD.class.getName());
			ContratoParcelamentoRD contratoAtualizar = new ArrayList<ContratoParcelamentoRD>(coll).get(0);
			sessao.setAttribute("numeroContratoParcelamentoRD",contratoAtualizar.getNumero());
		}else{
			retorno = this.controlarPaginacao(httpServletRequest, retorno,	totalRegistros);
			Collection<ContratoParcelamentoRD> coll = this.getFachada().pesquisar(filtro, (Integer)httpServletRequest
					.getAttribute("page.offset") - 1, ContratoParcelamentoRD.class.getName());
			sessao.setAttribute("collContratoParcelamentoRD", coll);
		}
		
		
		return retorno;
	}
		
}
