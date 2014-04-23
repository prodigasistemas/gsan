package gcom.gui.relatorio.gerencial.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.gerencial.bean.FiltrarRelatorioQuadroMetasAcumuladoHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.gerencial.faturamento.RelatorioQuadroMetasAcumulado;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe montar o filtro para pesquisa do relatorio do quadro de metas acumulado 
 *
 * @author Bruno Barros
 * 
 * @date 20/12/2007
 */

public class GerarRelatorioQuadroMetasAcumuladoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("emitirQuadroMetasAcumulado");
		
		// Form
		EmissaoRelatorioQuadroMetasAcumuladoActionForm form = 
			(EmissaoRelatorioQuadroMetasAcumuladoActionForm) actionForm;
		
		FiltrarRelatorioQuadroMetasAcumuladoHelper filtro = new FiltrarRelatorioQuadroMetasAcumuladoHelper();
		
		// Mês/Ano do Faturamento
		Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoFaturamento());
		
		SistemaParametro sistemaParametro = this.getFachada().pesquisarParametrosDoSistema();
		
		if(sistemaParametro.getAnoMesFaturamento().intValue() < anoMes.intValue()){
			throw new ActionServletException("atencao.mes_ano.faturamento.inferior", 
				null,""+sistemaParametro.getAnoMesFaturamento());
		}
		
		filtro.setMesAnoReferencia(anoMes);

		// Opção de Totalização
		int opcaoTotalizacao = Integer.parseInt(form.getOpcaoTotalizacao());
		filtro.setOpcaoTotalizacao(opcaoTotalizacao);
		
		// Gerência Regional
		if ((opcaoTotalizacao == 6 || opcaoTotalizacao == 10) &&
			form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setGerenciaRegional( new Integer(form.getGerenciaRegional()) );
		}
		
		// Unidade de Negocio
		if (opcaoTotalizacao == 10 && 
			form.getUnidadeNegocio() != null && 
			!form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			filtro.setUnidadeNegocio( new Integer(form.getUnidadeNegocio()) );
		}
			
		// Localidade		
		if (opcaoTotalizacao == 16 && 
			form.getLocalidade() != null && 
			!form.getLocalidade().equals("") ) {				
			filtro.setLocalidade( new Integer(form.getLocalidade()) );
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioQuadroMetasAcumulado relatorio = 
			new RelatorioQuadroMetasAcumulado(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarRelatorioQuadroMetasAcumuladoHelper", filtro);
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
		
		retorno = 
			processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
				httpServletResponse, actionMapping);

		return retorno;
	}
	
		
}
