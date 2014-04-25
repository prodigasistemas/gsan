package gcom.gui.relatorio.cobranca;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cobranca.bean.FiltroSupressoesReligacoesReestabelecimentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.RelatorioSupressoesReligacoesRestabelecimentos;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

/**
 * Descrição da classe
 * Classe responsável pela chamada
 * do Relatorio Acompanhamento das Supressões Religações e Reestabelecimentos
 * 
 * @author Anderson Italo
 * @date 01/08/2009
 */
public class GerarRelatorioSupressoesReligacoesRestabelecimentosAction extends ExibidorProcessamentoTarefaRelatorio {
	
	@Override
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm actionForm, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		Fachada fachada = Fachada.getInstancia();
		Usuario usuario = (Usuario) request.getSession(false).getAttribute("usuarioLogado");
		
		int tipoRelatorio = TarefaRelatorio.TIPO_PDF;
		try {
			tipoRelatorio = Integer.parseInt(request.getParameter("tipoRelatorio")); 
		} catch (NumberFormatException e) { }
		
		GerarSupressoesReligacoesReestabelecimentosActionForm form = (GerarSupressoesReligacoesReestabelecimentosActionForm) actionForm;
		FiltroSupressoesReligacoesReestabelecimentoHelper filtro = new FiltroSupressoesReligacoesReestabelecimentoHelper();
		
		//Período de Emissão
		//==============================================================================
		String dataInicial = form.getDataEmissaoInicio();
		String dataFinal = form.getDataEmissaoFim();
		
		if ((dataInicial.trim().length() == 10)
				&& (dataFinal.trim().length() == 10)) {

			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();
            
            calendarInicio.setTime( Util.converteStringParaDate( dataInicial ) );
            calendarFim.setTime( Util.converteStringParaDate( dataFinal ) );

			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_fim_menor_inicio");
			}
			
			filtro.setDataEmissaoInicio(dataInicial + " 00:00:00");
			filtro.setDataEmissaoFim(dataFinal + " 23:59:59");
		}
		
		if (form.getIndicadorTipoRelatorio() != null && !form.getIndicadorTipoRelatorio().equals("")){
			Integer indicadorTipoRelatorio = new Integer(form.getIndicadorTipoRelatorio());
			
			switch (indicadorTipoRelatorio) {
			case 1:
				filtro.setIndicadorTipoRelatorio(1);
				break;
			case 2:
				filtro.setIndicadorTipoRelatorio(2);
				break;
			case 3:
				if (form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals("")
						&& !form.getIdGerenciaRegional().equals("-1")){
					filtro.setIdGerenciaRegional(form.getIdGerenciaRegional());
				}else{
					throw new ActionServletException("atencao.combinacao_parametros_invalida");
				}
				filtro.setIndicadorTipoRelatorio(3);
				break;
			case 4:
				filtro.setIndicadorTipoRelatorio(4);
				break;
			case 5:
				if (form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals("")
						&& !form.getIdUnidadeNegocio().equals("-1")){
					filtro.setIdUnidadeNegocio(form.getIdUnidadeNegocio());
				}else{
					throw new ActionServletException("atencao.combinacao_parametros_invalida");
				}
				filtro.setIndicadorTipoRelatorio(5);
				break;
			case 6:
				filtro.setIndicadorTipoRelatorio(6);
				break;
			case 7:
				if (form.getIdLocalidade() != null && !form.getIdLocalidade().equals("")
						&& !form.getIdLocalidade().equals("-1")){
					filtro.setIdLocalidade(form.getIdLocalidade());
				}else{
					throw new ActionServletException("atencao.combinacao_parametros_invalida");
				}
				filtro.setIndicadorTipoRelatorio(7);
				break;
			case 8:
				if (form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals("")
						&& !form.getIdGerenciaRegional().equals("-1")){
					filtro.setIdGerenciaRegional(form.getIdGerenciaRegional());
				}else{
					throw new ActionServletException("atencao.combinacao_parametros_invalida");
				}
				filtro.setIndicadorTipoRelatorio(8);
				break;
			case 9:
				if (form.getIdGerenciaRegional() != null && !form.getIdGerenciaRegional().equals("")
						&& !form.getIdGerenciaRegional().equals("-1")){
					filtro.setIdGerenciaRegional(form.getIdGerenciaRegional());
				}else{
					throw new ActionServletException("atencao.combinacao_parametros_invalida");
				}
				filtro.setIndicadorTipoRelatorio(9);
				break;
			case 10:
				if (form.getIdUnidadeNegocio() != null && !form.getIdUnidadeNegocio().equals("")
						&& !form.getIdUnidadeNegocio().equals("-1")){
					filtro.setIdUnidadeNegocio(form.getIdUnidadeNegocio());
				}else{
					throw new ActionServletException("atencao.combinacao_parametros_invalida");
				}
				filtro.setIndicadorTipoRelatorio(10);
				break;
			default:
				break;
			}
			
			//Limite Religação após Corte
			if (form.getLimititeReligacaoPosCorte() != null && !form.getLimititeReligacaoPosCorte().equals("")){
				filtro.setLimititeReligacaoPosCorte(form.getLimititeReligacaoPosCorte());
			}else{
				//assume 0
				filtro.setLimititeReligacaoPosCorte("0");
			}
			
			//empresa
			if (form.getIdEmpresa() != null && !form.getIdEmpresa().equals("") && !form.getIdEmpresa().equals("-1")){
				filtro.setIdEmpresa(form.getIdEmpresa());
			}
			
		}else{
			throw new ActionServletException("atencao.combinacao_parametros_invalida");
		}
		
		if (form == null) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		List objetosEncontrados = fachada.filtrarSupressoesReligacoesReestabelecimentos(filtro);
		
		if (objetosEncontrados == null ){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}else if (objetosEncontrados.size() < 1){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}

		RelatorioSupressoesReligacoesRestabelecimentos relatorio = new RelatorioSupressoesReligacoesRestabelecimentos(usuario);
		relatorio.addParametro("filtroSupressoesReligacoesReestabelecimentos", filtro);
		relatorio.addParametro("tipoRelatorio", tipoRelatorio);
		relatorio.addParametro("objetosEncontrados", objetosEncontrados);
		return processarExibicaoRelatorio(
				relatorio, tipoRelatorio, request, response, mapping);
	
		
		
	}
}
