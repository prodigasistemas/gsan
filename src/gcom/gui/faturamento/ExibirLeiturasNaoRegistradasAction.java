package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0215] Consultar Posição de Faturamento
 * 
 * @author Vinicius Medeiros
 * @date 10/03/2009
 */
public class ExibirLeiturasNaoRegistradasAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Seta o mapeamento para a o popup de adicionar banco
		ActionForward retorno = actionMapping.findForward("exibirLeiturasNaoInformadas");
		
		//Cria uma instância da fachada 
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoLeiturasNaoRegistradas = null;
		
		ExibirLeiturasNaoRegistradasActionForm exibirLeiturasNaoRegistradasActionForm = (ExibirLeiturasNaoRegistradasActionForm) actionForm;
		
		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
		
		Integer idFaturamento = new Integer(exibirLeiturasNaoRegistradasActionForm.getIdFaturamento());
		Integer mesAno = Util.formatarMesAnoComBarraParaAnoMes(exibirLeiturasNaoRegistradasActionForm.getMesAno());
		
		faturamentoGrupo.setId(idFaturamento);
		faturamentoGrupo.setAnoMesReferencia(mesAno);
		
		
		colecaoLeiturasNaoRegistradas = (Collection) fachada.retornarLeiturasNaoRegistradas(faturamentoGrupo);
		
		httpServletRequest.setAttribute("colecaoLeiturasNaoRegistradas",colecaoLeiturasNaoRegistradas);
		
		//Retorna o mapeamento contido na variável retorno 
		return retorno;
	}
}
