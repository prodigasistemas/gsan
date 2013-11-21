package gcom.gui.atendimentopublico.registroatendimento;

import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.RelatorioAcompanhamentoRegistroAtendimentoAnalitico;
import gcom.relatorio.atendimentopublico.RelatorioAcompanhamentoRegistroAtendimentoSinteticoAberto;
import gcom.relatorio.atendimentopublico.RelatorioAcompanhamentoRegistroAtendimentoSinteticoEncerrado;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.SistemaException;
import gcom.util.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1056] Gerar Relatório de Acompanhamento dos Registros de Atendimento
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio de Acompanhamento dos Registros de Atendimento.
 * 
 * @author Hugo Leonardo
 * @date 28/09/2010
 */
public class GerarRelatorioAcompanhamentoRegistroAtendimentoAction extends
		ExibidorProcessamentoTarefaRelatorio {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
				
		// cria a variável de retorno
		ActionForward retorno = null;
		
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		boolean peloMenosUmParametroInformado = false;
		
		FiltrarAcompanhamentoRegistroAtendimentoActionForm form = 
			(FiltrarAcompanhamentoRegistroAtendimentoActionForm) actionForm;
		
		FiltrarAcompanhamentoRegistroAtendimentoHelper helper = new FiltrarAcompanhamentoRegistroAtendimentoHelper();
		
		String dtAtendimentoInicial = form.getPeriodoAtendimentoInicial();
		String dtAtendimentoFinal = form.getPeriodoAtendimentoFinal();
		
		String dtEncerramentoInicial = form.getPeriodoEncerramentoInicial();
		String dtEncerramentoFinal = form.getPeriodoEncerramentoFinal();

		// Situação da RA
		String situacao = "";
		if(Util.verificarNaoVazio(form.getSituacaoRA())){
			
			helper.setSituacaoRA(form.getSituacaoRA());
			situacao += form.getSituacaoRA();
		}
		
		// Situação das RA's Abertas
		if(Util.verificarNaoVazio( form.getSituacaoRAAbertos())){
			
			helper.setSituacaoRAAbertos(form.getSituacaoRAAbertos());
		}
		
		// Motivo de Encerramento
		Collection<Integer> colecaoMotivoEncerramento = null;
		if(!Util.isVazioOrNulo( form.getIdsMotivoEncerramentoSelecionados())){
			
			colecaoMotivoEncerramento = new ArrayList<Integer>();
			
			for (Integer id : form.getIdsMotivoEncerramentoSelecionados()) {
				if (id.intValue() != -1) {
					colecaoMotivoEncerramento.add(new Integer(id));
				}
			}
			
			if (colecaoMotivoEncerramento.size() > 0) {
				
				peloMenosUmParametroInformado = true;
				helper.setIdsMotivoEncerramentoSelecionados(colecaoMotivoEncerramento);
			}	
		}
		
		// Período de Abertura
		String periodoAbertura = "";
		if(dtAtendimentoInicial != null && !dtAtendimentoInicial.equals("")){
			
			if(dtAtendimentoFinal == null || dtAtendimentoFinal.equals("")){
				
				throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",
						null,"atendimento");
			}else{
				
				Date dtInicial = Util.converteStringParaDate(dtAtendimentoInicial);
				Date dtFinal = Util.converteStringParaDate(dtAtendimentoFinal);
				
				if(Util.compararData(dtInicial, dtFinal) == 1){
					
					throw new ActionServletException("atencao.gsan.data_final_menor_data_inicial",null, "Abertura");
				}
				
				Calendar calendario = new GregorianCalendar();
				calendario.setTime(dtInicial);
				Integer numeroDias = new Integer(
						Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
				numeroDias = new Integer(numeroDias-1);
				Date dataLimite = Util.subtrairNumeroDiasDeUmaData(
						Util.converteStringParaDate(dtAtendimentoFinal),numeroDias); 
				
				if(dataLimite.after(dtInicial)){
					throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"atendimento");
				}else{
					
					peloMenosUmParametroInformado = true;
					helper.setPeriodoAtendimentoInicial(dtInicial);
					helper.setPeriodoAtendimentoFinal(dtFinal);
					
					periodoAbertura += dtAtendimentoInicial + " a " + dtAtendimentoFinal;
				}
			}
		}
		
		// Período de Encerramento
		String periodoEncerramento = "";
		if(dtEncerramentoInicial != null && !dtEncerramentoInicial.equals("")){
			
			if(dtEncerramentoFinal==null || dtEncerramentoFinal.equals("")){
				
				throw new ActionServletException("atencao.filtrar_data_final_obrigatorio_quando_inicial",
						null,"encerramento");
			}else{
				
				Date dtInicial = Util.converteStringParaDate(dtEncerramentoInicial);
				Date dtFinal = Util.converteStringParaDate(dtEncerramentoFinal);
				
				if(Util.compararData(dtInicial, dtFinal) == 1){
					
					throw new ActionServletException("atencao.gsan.data_final_menor_data_inicial",
							null, "Encerramento");
				}
				
				Calendar calendario = new GregorianCalendar();
				calendario.setTime(dtInicial);
				Integer numeroDias = new Integer(
						Util.obterUltimoDiaMes(calendario.get(Calendar.MONTH)+1, calendario.get(Calendar.YEAR)));
				numeroDias = new Integer(numeroDias-1);
				Date dataLimite = Util.subtrairNumeroDiasDeUmaData(
						Util.converteStringParaDate(dtEncerramentoFinal),numeroDias); 
				
				if(dataLimite.after(dtInicial)){
					throw new ActionServletException("atencao.filtrar_intervalo_limite",null,"encerramento");
				}else{
					
					peloMenosUmParametroInformado = true;
					helper.setPeriodoEncerramentoInicial(dtInicial);
					helper.setPeriodoEncerramentoFinal(dtFinal);
					
					periodoEncerramento += dtEncerramentoInicial + " a " + dtEncerramentoFinal;
				}
			}
		}
		
		// Id Unidade de Atendimento
		if(Util.verificarNaoVazio( form.getUnidadeAtendimentoId())){
			
			helper.setIdUnidadeAtendimento(form.getUnidadeAtendimentoId());
		}
		
		// Municípios Associados à Localidade
		if (!Util.isVazioOrNulo(form.getMunicipiosAssociados())) {
			Collection<Integer> colecao = new ArrayList();
			String[] array = form.getMunicipiosAssociados();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					peloMenosUmParametroInformado = true;
					colecao.add(new Integer(array[i]));
				}
			}
			helper.setMunicipiosAssociados(colecao);
		}
		
		// Relatório
		TarefaRelatorio relatorio = null;
		if(Util.verificarNaoVazio( form.getOpcaoRelatorio())){
			
			if(form.getOpcaoRelatorio().equals("0")){
				
				relatorio = new RelatorioAcompanhamentoRegistroAtendimentoAnalitico((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				
				relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
				relatorio.addParametro("filtroHelper", helper);
				relatorio.addParametro("situacao", situacao);
				relatorio.addParametro("periodoAbertura", periodoAbertura);
				relatorio.addParametro("periodoEncerramento", periodoEncerramento);
				
			}else if(form.getOpcaoRelatorio().equals("1") 
					&& form.getSituacaoRA().equals("1")){
				
				relatorio = new RelatorioAcompanhamentoRegistroAtendimentoSinteticoEncerrado((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				
				relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
				relatorio.addParametro("filtroHelper", helper);
				relatorio.addParametro("situacao", situacao);
				relatorio.addParametro("periodoAbertura", periodoAbertura);
				relatorio.addParametro("periodoEncerramento", periodoEncerramento);
				
			}else if(form.getOpcaoRelatorio().equals("1") 
					&& form.getSituacaoRA().equals("0")){
				
				relatorio = new RelatorioAcompanhamentoRegistroAtendimentoSinteticoAberto((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
				
				relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
				relatorio.addParametro("filtroHelper", helper);
				relatorio.addParametro("situacao", situacao);
				relatorio.addParametro("periodoAbertura", periodoAbertura);
				relatorio.addParametro("periodoEncerramento", periodoEncerramento);
			}
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
		
		// Tipo Relatório
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		try {	
			
			if(Util.verificarNaoVazio( form.getOpcaoRelatorio())){

				retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
							httpServletResponse, actionMapping);

			}
	
		} catch (SistemaException ex) {
			// manda o erro para a página no request atual
			reportarErros(httpServletRequest, "erro.sistema");
	
			// seta o mapeamento de retorno para a tela de erro de popup
			retorno = actionMapping.findForward("telaErroPopup");
	
		} catch (RelatorioVazioException ex1) {
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
			
		return retorno;
	}

}
