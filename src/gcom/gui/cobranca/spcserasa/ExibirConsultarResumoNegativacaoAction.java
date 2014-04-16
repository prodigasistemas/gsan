package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Description of the Class
 * 
 * @author Marcio Roberto
 * @Date 26/02/2008
**/
public class ExibirConsultarResumoNegativacaoAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirConsultarResumoNegativacao");
		
		//obtém a instância da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection <NegativacaoDescricaoResumoHelper> colecaoDescricaoResumo = new ArrayList<NegativacaoDescricaoResumoHelper>();
		
		InformarDadosConsultaNegativacaoActionForm form = (InformarDadosConsultaNegativacaoActionForm) actionForm;
		
		String periodoEnvioNegativacaoInicio = (String)sessao.getAttribute("periodoEnvioNegativacaoInicio"); 
		String periodoEnvioNegativacaoFim = (String)sessao.getAttribute("periodoEnvioNegativacaoFim");
		
		form.setPeriodoEnvioNegativacaoInicio(periodoEnvioNegativacaoInicio);
		form.setPeriodoEnvioNegativacaoFim(periodoEnvioNegativacaoFim);
		
		
		// [UC0676] Gerar Resumo Diário da Negativação.
        //-------------------------------------------------------------------------------------------
		// Alterado por :  Yara Taciane  - data : 28/07/2008 
		// Analista :  Fátima Sampaio
        //-------------------------------------------------------------------------------------------			
		
		Fachada fachada = Fachada.getInstancia();
		// Pega as informações de Sistema Parâmetros
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema(); 
		
		Date ultimaAtualizacao = fachada.getDataUltimaAtualizacaoResumoNegativacao(sistemaParametro.getNumeroExecucaoResumoNegativacao());
		form.setUltimaAtualizacao(Util.formatarDataComHora(ultimaAtualizacao));
		//-------------------------------------------------------------------------------------------
		
		NegativacaoDescricaoResumoHelper descricaoResumo = new NegativacaoDescricaoResumoHelper();
		
		descricaoResumo.setDescricao("NEGATIVAÇÕES INCLUÍDAS");
		colecaoDescricaoResumo.add(descricaoResumo);

		descricaoResumo = new NegativacaoDescricaoResumoHelper();
		descricaoResumo.setDescricao("NEGATIVAÇÕES INCLUÍDAS E CONFIRMADAS");
		colecaoDescricaoResumo.add(descricaoResumo);

		descricaoResumo = new NegativacaoDescricaoResumoHelper();
		descricaoResumo.setDescricao("NEGATIVAÇÕES INCLUÍDAS E NÃO CONFIRMADAS");
		colecaoDescricaoResumo.add(descricaoResumo);

		sessao.setAttribute("colecaoDescricaoResumo", colecaoDescricaoResumo);

		return retorno;
	}
}
