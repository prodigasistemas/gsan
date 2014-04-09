package gcom.gui.relatorio.financeiro;

import gcom.gui.financeiro.GerarRelatorioResumoReceitaActionForm;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.financeiro.RelatorioResumoReceita;
import gcom.relatorio.financeiro.RelatorioResumoReceitaAnalitico;
import gcom.relatorio.financeiro.ResumoReceitaHelper;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Flavio Leonardo
 *
 * @date 16/04/2010
 */

public class GerarRelatorioResumoReceitaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		
		// Form
		GerarRelatorioResumoReceitaActionForm form = 
			(GerarRelatorioResumoReceitaActionForm) actionForm;
		 
//		FiltroResumoReceita filtro = 
//			new FiltroResumoReceita();
		
		ResumoReceitaHelper resumoReceitaHelper = new ResumoReceitaHelper();
		
		//Gerência Regional
		if (form.getMesAnoReferencial() != null && 
			!form.getMesAnoReferencial().equals("") ) {
			
//			filtro.adicionarParametro(new ParametroSimples(FiltroResumoReceita.ANO_MES_REFERENCIA,
//					Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencial())));
			
			resumoReceitaHelper.setAnoMes(Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencial())+"");
			
			
		}
		
		// Gerência Regional
		if (form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			resumoReceitaHelper.setGerenciaRegionalId(new Integer(form.getGerenciaRegional()));
//			filtro.adicionarParametro(new ParametroSimples(FiltroResumoReceita.GERENCIA_REGIONAL,form.getGerenciaRegional()));
		}
			
		// Localidade Inicial
		if (form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") ) {
			if(form.getLocalidadeFinal() != null && !form.getLocalidadeFinal().equals("")){
				resumoReceitaHelper.setLocalidadeInicial(form.getLocalidadeInicial());
				resumoReceitaHelper.setLocalidadeFinal(form.getLocalidadeFinal());
//				filtro.adicionarParametro(new MaiorQue(FiltroResumoReceita.LOCALIDADE, form.getLocalidadeInicial()));
//				filtro.adicionarParametro(new MenorQue(FiltroResumoReceita.LOCALIDADE, form.getLocalidadeFinal()));
			}else{
//				filtro.adicionarParametro(new ParametroSimples(FiltroResumoReceita.LOCALIDADE, form.getLocalidadeInicial()));
				resumoReceitaHelper.setLocalidadeInicial(form.getLocalidadeInicial());
			}
		}
		
		//Gerência Regional
		if (form.getCategoria() != null && 
			!form.getCategoria().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			resumoReceitaHelper.setCategoriaId(new Integer(form.getCategoria()));
			
//			filtro.adicionarParametro(new ParametroSimples(FiltroResumoReceita.CATEGORIA_ID,form.getCategoria()));
		}

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		if(form.getTipoRelatorio() != null && form.getTipoRelatorio().equals("1")){
			RelatorioResumoReceitaAnalitico relatorio = 
				new RelatorioResumoReceitaAnalitico(this.getUsuarioLogado(httpServletRequest));
			
			relatorio.addParametro("resumoReceitaHelper", resumoReceitaHelper);
			relatorio.addParametro("mesAno", form.getMesAnoReferencial());
			relatorio.addParametro("gerenciaRegional", form.getGerenciaRegional());
			relatorio.addParametro("localidadeInicial", form.getLocalidadeInicial());
			relatorio.addParametro("localidadeFinal", form.getLocalidadeFinal());
			relatorio.addParametro("categoria", form.getCategoria());
			
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
	
			relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			
			retorno = 
				processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
					httpServletResponse, actionMapping);
		}else{
			RelatorioResumoReceita relatorio = 
				new RelatorioResumoReceita(this.getUsuarioLogado(httpServletRequest));
			
			relatorio.addParametro("resumoReceitaHelper", resumoReceitaHelper);
			relatorio.addParametro("mesAno", form.getMesAnoReferencial());
			relatorio.addParametro("gerenciaRegional", form.getGerenciaRegional());
			relatorio.addParametro("localidadeInicial", form.getLocalidadeInicial());
			relatorio.addParametro("localidadeFinal", form.getLocalidadeFinal());
			relatorio.addParametro("categoria", form.getCategoria());
			
			if (tipoRelatorio  == null) {
				tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
			}
	
			relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			
			retorno = 
				processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
					httpServletResponse, actionMapping);
		}
		
		

		return retorno;
	}
	
}
