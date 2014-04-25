package gcom.gui.relatorio.cadastro;

import java.util.Date;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioImoveisDoacoesEntidade;
import gcom.relatorio.cadastro.RelatorioImoveisDoacoesImovel;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.SistemaException;
import gcom.util.Util;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1076] - Gerar Relatorio Atualizacao Cadastral Via Internet.
 * @author Daniel Alves
 * @date 24/09/2010 
 */

public class GerarRelatorioImoveisDoacoesAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;
		   
		httpServletRequest.setAttribute("telaSucessoRelatorio",true);
		   
		// Form
		GerarRelatorioImoveisDoacoesActionForm form = 
			(GerarRelatorioImoveisDoacoesActionForm) actionForm;
		
		if(form.getOpcaoRelatorio() != null &&
				!form.getOpcaoRelatorio().equals("")){
			
		}else{			
			throw new ActionServletException("atencao.informe_os_campos_obrigatorios");
		}	
		
		
		if(Util.verificarNaoVazio(form.getPeriodoAdesaoInicial()) 
				&& Util.verificarNaoVazio(form.getPeriodoAdesaoFinal())){
			
			Date periodoInicial = Util.converteStringParaDate(form.getPeriodoAdesaoInicial());
			Date periodoFinal = Util.converteStringParaDate(form.getPeriodoAdesaoInicial());
			
			if(periodoFinal.compareTo(periodoInicial)<0){
				throw new ActionServletException("atencao.data_final_periodo_adesao.anterior.data_inicial_periodo_adesao");
			}
		}
		
		if(Util.verificarNaoVazio(form.getPeriodoCancelamentoInicial()) 
				&& Util.verificarNaoVazio(form.getPeriodoCancelamentoFinal())){
			
			Date periodoInicial = Util.converteStringParaDate(form.getPeriodoCancelamentoInicial());
			Date periodoFinal = Util.converteStringParaDate(form.getPeriodoCancelamentoFinal());
			
			if(periodoFinal.compareTo(periodoInicial)<0){
				throw new ActionServletException("atencao.data_final_periodo_cancelamento.anterior.data_inicial_periodo_cancelamento");
			}
		}
		
		TarefaRelatorio relatorio = null;
		
		//escolhe o formato do relatorio (Por imovel, por entidade beneficente)
		if(form.getOpcaoRelatorio() != null &&
				!form.getOpcaoRelatorio().equals("")){
			
			if(form.getOpcaoRelatorio().equals("1")){
				relatorio = new RelatorioImoveisDoacoesImovel((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			} else if (form.getOpcaoRelatorio().equals("2")){
				relatorio = new RelatorioImoveisDoacoesEntidade((Usuario)
						(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
			}
			
		}else{
			throw new ActionServletException("atencao.informe_os_campos_obrigatorios");
		}
		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		if (tipoRelatorio  == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		if(form.getOpcaoRelatorio().equals("1")){
			relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
			relatorio.addParametro("filtroImovel", form.getImovel());
			
		}else{
			if(form.getOpcaoRelatorio().equals("2")){
				relatorio.addParametro("tipoFormatoRelatorio",Integer.parseInt(tipoRelatorio));
				relatorio.addParametro("filtroPeriodoAdesaoInicial", form.getPeriodoAdesaoInicial());
				relatorio.addParametro("filtroPeriodoAdesaoFinal", form.getPeriodoAdesaoFinal());
				relatorio.addParametro("filtroPeriodoCancelamentoInicial", form.getPeriodoCancelamentoInicial());
				relatorio.addParametro("filtroPeriodoCancelamentoFinal", form.getPeriodoCancelamentoFinal());
				relatorio.addParametro("filtroRefInicioDoacaoInicial", form.getReferenciaInicioDoacaoInicial());
				relatorio.addParametro("filtroRefInicioDoacaoFinal", form.getReferenciaInicioDoacaoFinal());
				relatorio.addParametro("filtroRefFimDoacaoInicial", form.getReferenciaFimDoacaoInicial());
				relatorio.addParametro("filtroRefFimDoacaoFinal", form.getReferenciaFimDoacaoFinal());
				relatorio.addParametro("filtroUsuarioAdesao", form.getUsuarioAdesao());
				relatorio.addParametro("filtroUsuarioCancelamento", form.getUsuarioCancelamento());
				relatorio.addParametro("filtroEntidadeBeneficente", form.getEntidadeBeneficente());
			}
		}
		
		try {	
			
			retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio, httpServletRequest, 
						httpServletResponse, actionMapping);
	
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
