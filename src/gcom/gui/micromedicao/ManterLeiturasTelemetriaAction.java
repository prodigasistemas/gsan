package gcom.gui.micromedicao;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.micromedicao.TelemetriaMovReg;
import gcom.util.ConstantesSistema;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * [UC1069] Consultar Leituras Telemetria
 * 
 * @author Hugo Amorim
 * @date 28/09/2010
 *
 */
public class ManterLeiturasTelemetriaAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		ManutencaoRegistroActionForm form = (ManutencaoRegistroActionForm) actionForm;
			
		// Registros selecionados para reprocessamento.
		String[] ids = form.getIdRegistrosAutorizar();
		
		// Mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum registro;
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
		
		// [FS0002] - Verificar grupo de faturamento
		if(this.getFachada().verificarGruposDiferentesLeiturasTelemetria(ids)){
			throw new ActionServletException("atencao.grupos_dos_imoveis_sao_diferente");
		}
		
		Collection<TelemetriaMovReg> colecaoDados = this.getFachada().perquisarLeiturasTelemetriaPorId(ids);
		
		// Obtem data Atual
		Date dataAtual = new Date();
		
		for (TelemetriaMovReg telemetriaMovReg : colecaoDados) {
					
			boolean flagConsistiu = this.getFachada().consistirLeituraTelemetria(telemetriaMovReg);
				
			if(flagConsistiu){
				
				telemetriaMovReg.setIndicadorProcessado(ConstantesSistema.SIM);
				telemetriaMovReg.setUltimaAlteracao(dataAtual);
					
				this.getFachada().atualizar(telemetriaMovReg);		
			}
	
		}
		
		// Monta a página de sucesso
		montarPaginaSucesso(httpServletRequest, "Leituras Reprocessadas com sucesso.",
				"Realizar outra Consultar Leituras Transmitidas Via Telemetria",
				"exibirFiltrarLeiturasTelemetriaAction.do?menu=sim");

		return retorno;
	}
}
