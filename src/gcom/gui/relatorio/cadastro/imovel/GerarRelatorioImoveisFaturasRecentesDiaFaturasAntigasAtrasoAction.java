package gcom.gui.relatorio.cadastro.imovel;

import java.util.ArrayList;
import java.util.Collection;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00730] Gerar Relatório de Imóveis com Faturas Recentes em Dia e Faturas Antigas em Atraso
 * 
 * @author Rafael Pinto
 * @date 08/01/2008
 */
public class GerarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoAction");
		
		// Form
		GerarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoActionForm form = 
			(GerarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoActionForm) actionForm;
		
		FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper filtro = 
			new FiltrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper();
		
		// Gerência Regional
		if (form.getGerenciaRegional() != null && 
			!form.getGerenciaRegional().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setGerenciaRegional(new Integer(form.getGerenciaRegional()));
		}
		
		// Unidade de Negocio
		if (form.getUnidadeNegocio() != null && 
			!form.getUnidadeNegocio().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setUnidadeNegocio(new Integer(form.getUnidadeNegocio()));
		}
			
		// Localidade Inicial
		if (form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") ) {
				
			filtro.setLocalidadeInicial(new Integer(form.getLocalidadeInicial()));
		}
		
		// Setor Comercial Inicial
		if (form.getSetorComercialInicial() != null && 
			!form.getSetorComercialInicial().equals("") ) {
				
			filtro.setSetorComercialInicial(new Integer(form.getSetorComercialInicial()));
		}

		// Rota Inicial
		if (form.getRotaInicial() != null && 
			!form.getRotaInicial().equals("") ) {
				
			filtro.setRotaInicial(new Integer(form.getRotaInicial()));
		}

		// Sequencial Rota Inicial
		if (form.getSequencialRotaInicial() != null && 
			!form.getSequencialRotaInicial().equals("") ) {
				
			filtro.setSequencialRotalInicial(new Integer(form.getSequencialRotaInicial()));
		}

		// Localidade Final
		if (form.getLocalidadeFinal() != null && 
			!form.getLocalidadeFinal().equals("") ) {
				
			filtro.setLocalidadeFinal(new Integer(form.getLocalidadeFinal()));
		}

		// Setor Comercial Final
		if (form.getSetorComercialFinal() != null && 
			!form.getSetorComercialFinal().equals("") ) {
				
			filtro.setSetorComercialFinal(new Integer(form.getSetorComercialFinal()));
		}
		
		// Rota Final
		if (form.getRotaFinal() != null && 
			!form.getRotaFinal().equals("") ) {
				
			filtro.setRotaFinal(new Integer(form.getRotaFinal()));
		}
		
		// Sequencial Rota Final
		if (form.getSequencialRotaFinal() != null && 
			!form.getSequencialRotaFinal().equals("") ) {
				
			filtro.setSequencialRotalFinal(new Integer(form.getSequencialRotaFinal()));
		}
		
		// Situacao da Ligacao de agua
		if (form.getSituacaoLigacaoAgua() != null && 
			form.getSituacaoLigacaoAgua().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getSituacaoLigacaoAgua();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setSituacaoLigacaoAgua(colecao);
		}
		
		// Categorias
		if (form.getCategorias() != null && 
			form.getCategorias().length > 0) {
			
			Collection<Integer> colecao = new ArrayList();
			
			String[] array = form.getCategorias();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setCategorias(colecao);
		}
		// Referencia de Faturas Dia Inicial
		if (form.getReferenciaFaturasDiaInicial() != null && 
			!form.getReferenciaFaturasDiaInicial().equals("") ) {
				
			filtro.setReferenciaFaturasDiaInicial( Util.formatarMesAnoComBarraParaAnoMes( form.getReferenciaFaturasDiaInicial() ) );
		}
		
		// Referencia de Faturas Dia Final
		if (form.getReferenciaFaturasDiaFinal() != null && 
			!form.getReferenciaFaturasDiaFinal().equals("") ) {
				
			filtro.setReferenciaFaturasDiaFinal( Util.formatarMesAnoComBarraParaAnoMes( form.getReferenciaFaturasDiaFinal() ) );
		}	
		
		
		// Referencia de Faturas Atraso Inicial
		if (form.getReferenciaFaturasAtrasoInicial() != null && 
			!form.getReferenciaFaturasAtrasoInicial().equals("") ) {
				
			filtro.setReferenciaFaturasAtrasoInicial( Util.formatarMesAnoComBarraParaAnoMes( form.getReferenciaFaturasAtrasoInicial() ) );
		}
		
		// Referencia de Faturas Atraso Final
		if (form.getReferenciaFaturasAtrasoFinal() != null && 
			!form.getReferenciaFaturasAtrasoFinal().equals("") ) {
				
			filtro.setReferenciaFaturasAtrasoFinal( Util.formatarMesAnoComBarraParaAnoMes( form.getReferenciaFaturasAtrasoFinal() ) );
		}	
		
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso relatorio = 
			new RelatorioImoveisFaturasRecentesDiaFaturasAntigasAtraso(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarRelatorioImoveisFaturasRecentesDiaFaturasAntigasAtrasoHelper", filtro);
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
