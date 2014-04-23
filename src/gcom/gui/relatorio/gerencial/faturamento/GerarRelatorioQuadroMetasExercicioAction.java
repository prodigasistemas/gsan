package gcom.gui.relatorio.gerencial.faturamento;

import gcom.gerencial.bean.FiltrarRelatorioQuadroMetasExercicioHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.gerencial.faturamento.RelatorioQuadroMetasExercicio;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe montar o filtro para pesquisa do relatorio do quadro de metas por exercício 
 *
 * @author Bruno Barros
 * 
 * @date 20/12/2007
 */

public class GerarRelatorioQuadroMetasExercicioAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("emitirQuadroMetasExercicio");
		
		// Form
		EmissaoRelatorioQuadroMetasExercicioActionForm form = 
			(EmissaoRelatorioQuadroMetasExercicioActionForm) actionForm;
		
		FiltrarRelatorioQuadroMetasExercicioHelper filtro = new FiltrarRelatorioQuadroMetasExercicioHelper();
		
		// Ano do Exercicio
		Integer ano = Integer.parseInt( form.getAnoExercicio() );
		
		filtro.setAnoExercicio( ano );

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

		RelatorioQuadroMetasExercicio relatorio = 
			new RelatorioQuadroMetasExercicio( this.getUsuarioLogado( httpServletRequest ) );
		
		relatorio.addParametro("filtrarRelatorioQuadroMetasExercicioHelper", filtro);
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
