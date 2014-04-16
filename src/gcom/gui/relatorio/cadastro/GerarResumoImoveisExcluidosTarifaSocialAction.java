package gcom.gui.relatorio.cadastro;

import gcom.cadastro.localidade.FiltroLocalidade;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.cadastro.RelatorioResumoQtdeImoveisExcluidosTarifaSocial;
import gcom.relatorio.cadastro.RelatorioResumoQtdeImoveisExcluidosTarifaSocialTipo2;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1164] Gerar Resumo dos Imóveis Excluídos da Tarifa Social
 * 
 * @author Vivianne Sousa
 * @date 07/04/2011
 */
public class GerarResumoImoveisExcluidosTarifaSocialAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = null;
        GerarResumoImoveisExcluidosTarifaSocialActionForm form = (GerarResumoImoveisExcluidosTarifaSocialActionForm) actionForm;

        validarCamposObrigatorios(form);

        Integer idGerenciaRegional = null;
        Integer idUnidadeNegocio = null;
        Integer idLocalidade = null;   
        Integer anoMesPesquisaInicial = Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaInicial());
        Integer anoMesPesquisaFinal = Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaFinal());
        String motivoExclusao = form.getMotivoExclusao();
        
		if(form.getGerenciaRegionalId() != null && !form.getGerenciaRegionalId().equals("")
		&& !form.getGerenciaRegionalId().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			idGerenciaRegional = new Integer(form.getGerenciaRegionalId());
		}
		if(form.getUnidadeNegocioId() != null && !form.getUnidadeNegocioId().equals("")
		&& !form.getUnidadeNegocioId().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
			idUnidadeNegocio= new Integer(form.getUnidadeNegocioId());
		}
		if(form.getCodigoLocalidade() != null && !form.getCodigoLocalidade().equals("")){
			idLocalidade = new Integer(form.getCodigoLocalidade());
		}
    
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		
        if(motivoExclusao.equals("1") || motivoExclusao.equals("2")){
        	RelatorioResumoQtdeImoveisExcluidosTarifaSocial relatorio = new RelatorioResumoQtdeImoveisExcluidosTarifaSocial
			((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
			relatorio.addParametro("anoMesPesquisaInicial", anoMesPesquisaInicial);
			relatorio.addParametro("anoMesPesquisaFinal", anoMesPesquisaFinal);
			relatorio.addParametro("motivoExclusao", motivoExclusao);
			relatorio.addParametro("idGerenciaRegional", idGerenciaRegional);
			relatorio.addParametro("idUnidadeNegocio", idUnidadeNegocio);
			relatorio.addParametro("idLocalidade", idLocalidade);
			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			
			try {
				retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

			} catch (RelatorioVazioException ex) {
				// manda o erro para a página no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

				// seta o mapeamento de retorno para a tela de atenção de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}
        } else if(motivoExclusao.equals("3")){
        
        	RelatorioResumoQtdeImoveisExcluidosTarifaSocialTipo2 relatorio = new RelatorioResumoQtdeImoveisExcluidosTarifaSocialTipo2
			((Usuario)(httpServletRequest.getSession(false)).getAttribute("usuarioLogado"));
		
			relatorio.addParametro("anoMesPesquisaInicial", anoMesPesquisaInicial);
			relatorio.addParametro("anoMesPesquisaFinal", anoMesPesquisaFinal);
			relatorio.addParametro("motivoExclusao", motivoExclusao);
			relatorio.addParametro("idGerenciaRegional", idGerenciaRegional);
			relatorio.addParametro("idUnidadeNegocio", idUnidadeNegocio);
			relatorio.addParametro("idLocalidade", idLocalidade);
			relatorio.addParametro("tipoFormatoRelatorio", Integer.parseInt(tipoRelatorio));
			
			try {
				retorno = processarExibicaoRelatorio(relatorio,
				tipoRelatorio, httpServletRequest, httpServletResponse,actionMapping);

			} catch (RelatorioVazioException ex) {
				// manda o erro para a página no request atual
				reportarErros(httpServletRequest, "atencao.relatorio.vazio");

				// seta o mapeamento de retorno para a tela de atenção de popup
				retorno = actionMapping.findForward("telaAtencaoPopup");
			}
        }
		
		return retorno;
	}
	

	private void validarCamposObrigatorios(GerarResumoImoveisExcluidosTarifaSocialActionForm form) {
		
		if(form.getAnoMesPesquisaInicial() == null || form.getAnoMesPesquisaInicial().equals("")){
			throw new ActionServletException("atencao.campo.informado", null, "Período do Comando inicial");
		}
		  
		if(form.getAnoMesPesquisaFinal() == null || form.getAnoMesPesquisaFinal().equals("")){
			throw new ActionServletException("atencao.campo.informado", null, "Período do Comando final");
		}
        if(form.getMotivoExclusao() == null || form.getMotivoExclusao().equals("")){
        	throw new ActionServletException("atencao.campo.informado", null, "Motivo da Exclusão");
        }
		
        if((Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaInicial()).compareTo(Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaFinal()))) == 1){
        	throw new ActionServletException("atencao.referencia.final.menor.referencia.inicial");
        }
        
        if((Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaInicial()).compareTo(Util.recuperaAnoMesDaData(new Date()))) == 1){
        	throw new ActionServletException("atencao.ano_mes_comando.maior.ano_mes_atual");
        }
        if((Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesPesquisaFinal()).compareTo(Util.recuperaAnoMesDaData(new Date()))) == 1){
        	throw new ActionServletException("atencao.ano_mes_comando.maior.ano_mes_atual");
        }
        
        if(form.getCodigoLocalidade() != null && !form.getCodigoLocalidade().equals("")){
        	FiltroLocalidade filtroLocalidade = new FiltroLocalidade();
    		filtroLocalidade.adicionarParametro(new ParametroSimples(FiltroLocalidade.ID, form.getCodigoLocalidade()));

    		Collection<Localidade> localidadePesquisada = Fachada.getInstancia().pesquisar(
    				filtroLocalidade, Localidade.class.getName());

    		// Se nenhuma localidade for encontrada a mensagem é enviada para a página
    		if (localidadePesquisada == null || localidadePesquisada.isEmpty()) {
    			throw new ActionServletException("atencao.seituacao_servico_tipo_invalida", null, "localidade");
    		}
    
        }
        
	}

	
}
