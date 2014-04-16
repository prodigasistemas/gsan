package gcom.gui.relatorio.faturamento.conta;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.conta.RelatorioContasEmitidas;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Geração do relatório [UC0345] Gerar Relatório de Resumo do Arrecadacao
 * 
 * @author Vivianne Sousa
 */

public class GerarRelatorioContasEmitidasAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
		GerarRelatorioContasEmitidasActionForm form = (GerarRelatorioContasEmitidasActionForm) actionForm;

		String mesAno = form.getMesAno();
		String idGrupoFaturamento = form.getGrupoFaturamento();
		
		if (idGrupoFaturamento == null ||
				idGrupoFaturamento.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)){
			throw new ActionServletException("atencao.required", null,
			"Grupo de Faturamento");
		}

		int mesAnoTratado = 0;
		if ((mesAno != null) && !mesAno.equalsIgnoreCase("")) {

			mesAnoTratado = Integer.parseInt(Util.formatarMesAnoParaAnoMesSemBarra(mesAno));

			// [FS0002] Validar referência da arrecadação
			if (Util.validarAnoMes(Util.formatarMesAnoReferencia(mesAnoTratado))) {
				throw new ActionServletException("atencao.ano_mes_invalido");
			} 
			
		} else {
			throw new ActionServletException("atencao.required", null,
					"Mês/Ano Referência");
		}
	
	
		Collection<Integer> colecaoIdEsferaPoder = new ArrayList();
		if (form.getEsferaPoder() != null && form.getEsferaPoder().length > 0) {
			String[] idEsferaPoder = form.getEsferaPoder();
			for (int i = 0; i < idEsferaPoder.length; i++) {
				if (!idEsferaPoder[i].equals("") && 
						new Integer(idEsferaPoder[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecaoIdEsferaPoder.add(new Integer(idEsferaPoder[i]));
				}else{
					throw new ActionServletException("atencao.required", null,"Esfera Poder");
				}
				
			}
		}else{
			throw new ActionServletException("atencao.required", null,"Esfera Poder");
		}			
			
		// Parte que vai mandar o relatório para a tela

		// cria uma instância da classe do relatório
		RelatorioContasEmitidas relatorioContasEmitidas = new RelatorioContasEmitidas(
				(Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
		relatorioContasEmitidas.addParametro("mesAnoInteger", mesAnoTratado);
		relatorioContasEmitidas.addParametro("idGrupoFaturamento",new Integer(idGrupoFaturamento));
		relatorioContasEmitidas.addParametro("colecaoIdEsferaPoder",colecaoIdEsferaPoder);
		relatorioContasEmitidas.addParametro("tipoImpressao",form.getTipoImpressao());

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioContasEmitidas.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioContasEmitidas,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de atenção de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}

}
