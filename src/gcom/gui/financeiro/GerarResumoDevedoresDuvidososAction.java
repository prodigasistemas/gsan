package gcom.gui.financeiro;

import gcom.batch.Processo;
import gcom.batch.ProcessoIniciado;
import gcom.batch.ProcessoSituacao;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Gerar resumo dos devedores duvidosos
 *
 * @author Pedro Alexandre
 * @date 28/05/2007
 */
public class GerarResumoDevedoresDuvidososAction extends GcomAction {
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
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		GerarResumoDevedoresDuvidososActionForm gerarResumoDevedoresDuvidososActionForm = (GerarResumoDevedoresDuvidososActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();

		//Recpera o ano/mês contábil informado pelo usuário
		//e formata para o formato de AAAAMM
		String anoMesReferenciaContabil = gerarResumoDevedoresDuvidososActionForm.getAnoMesReferenciaContabil();
        String mes = anoMesReferenciaContabil.substring(0, 2);
        String ano = anoMesReferenciaContabil.substring(3, 7);
        String anoMesReferenciaContabilFormatado = ano + mes;
        
        //Cria o map que vai armazenar os dados para iniciar o processamento do batch
        Map<String, Object> dadosProcessamento = new HashMap();
        dadosProcessamento.put("anoMesReferenciaContabil",anoMesReferenciaContabilFormatado);
		
        //Indica que o processo vai ser o de Gerar Resumo dos Devedores Duvidosos.
        int idProcesso = Processo.GERAR_RESUMO_DEVEDORES_DUVIDOSOS;

        //Monta as informações para iniciar o processo
		Date dataHoraAgendamento = new Date();
		ProcessoIniciado processoIniciado = new ProcessoIniciado();
		Processo processo = new Processo();
		processo.setId(idProcesso);
		processoIniciado.setDataHoraAgendamento(dataHoraAgendamento);
		
		//Adiciona a situação e o usuário ao objeto.
		ProcessoSituacao processoSituacao = new ProcessoSituacao();
		processoIniciado.setProcesso(processo);
		processoIniciado.setProcessoSituacao(processoSituacao);
		processoIniciado.setUsuario(this.getUsuarioLogado(httpServletRequest));
		
		//Inseri o processo retornando qual o id gerado para o processo 
		//que foi iniciado no banco de dados.
		Integer codigoProcessoIniciadoGerado = (Integer) fachada.gerarResumoDevedoresDuvidosos(processoIniciado, dadosProcessamento);
	
		//Monta a página de sucesso.
		montarPaginaSucesso(httpServletRequest, "Gerando Resumo dos Devedores Duvidosos. Codigo do Processo: " + codigoProcessoIniciadoGerado,
				"Gerar Resumo dos Devedores Duvidosos", "/exibirGerarResumoDevedoresDuvidososAction.do");

		return retorno;
	}
}
