package gcom.gui.relatorio.cadastro.imovel;

import java.util.ArrayList;
import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.imovel.FiltrarRelatorioImoveisConsumoMedioHelper;
import gcom.relatorio.cadastro.imovel.RelatorioImoveisConsumoMedio;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC00727] Gerar Relatório de Imóveis por Consumo Medio
 * 
 * @author Bruno Barros
 *
 * @date 17/12/2007
 */

public class GerarRelatorioImoveisConsumoMedioAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		Fachada fachada = Fachada.getInstancia();
		
		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("emitirRelatorioImoveisConsumoMedio");
		
		// Form
		GerarRelatorioImoveisConsumoMedioActionForm form = 
			(GerarRelatorioImoveisConsumoMedioActionForm) actionForm;
		
		FiltrarRelatorioImoveisConsumoMedioHelper filtro = 
			new FiltrarRelatorioImoveisConsumoMedioHelper();
		
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
		
		// Consumo Medio Agua Inicial
		if (form.getConsumoMedioAguaInicial() != null && 
			!form.getConsumoMedioAguaInicial().equals("") ) {
				
			filtro.setConsumoMedioAguaInicial( new Integer( form.getConsumoMedioAguaInicial() ) );
		}
		
		// Consumo Medio Agua Final
		if (form.getConsumoMedioAguaFinal() != null && 
			!form.getConsumoMedioAguaFinal().equals("") ) {
				
			filtro.setConsumoMedioAguaFinal( new Integer( form.getConsumoMedioAguaFinal() ) );
		}
		
		Collection<Integer> colecao = new ArrayList();
		// Perfil do Imóvel
		if (form.getPerfisImovel() != null && 
			form.getPerfisImovel().length > 0) {
			
			String[] array = form.getPerfisImovel();
			for (int i = 0; i < array.length; i++) {
				if (new Integer(array[i]).intValue() != ConstantesSistema.NUMERO_NAO_INFORMADO) {
					colecao.add(new Integer(array[i]));
				}
			}

			filtro.setColecaoPerfisImovel(colecao);
			
		}
		
		// Consumo Medio Esgoto Inicial
		if (form.getConsumoMedioEsgotoInicial() != null && 
			!form.getConsumoMedioEsgotoInicial().equals("") ) {
				
			filtro.setConsumoMedioEsgotoInicial( new Integer( form.getConsumoMedioEsgotoInicial() ) );
		}
		
		// Consumo Medio Esgoto Final
		if (form.getConsumoMedioEsgotoFinal() != null && 
			!form.getConsumoMedioEsgotoFinal().equals("") ) {
				
			filtro.setConsumoMedioEsgotoFinal( new Integer( form.getConsumoMedioEsgotoFinal() ) );
		}
		
		// Indicador Medicao Com Hidrometro
		if (form.getIndicadorMedicaoComHidrometro() != null 
			&& !form.getIndicadorMedicaoComHidrometro().equals("") ) {
			
			filtro.setIndicadorMedicaoComHidrometro(new Integer(form.getIndicadorMedicaoComHidrometro()));
		}
		
		//Mes ano de Referência
		if (form.getAnoMesReferencia()!= null 
			&& !form.getAnoMesReferencia().equals("")) {
			
			filtro.setAnoMesReferencia(new Integer (Util.formatarMesAnoComBarraParaAnoMes(form.getAnoMesReferencia())));
			
		}

		//verifica se contem algum imovel para gerar o relatorio.
		Integer imovelConsumoMedioCount = fachada.pesquisarTotalRegistroRelatorioImoveisConsumoMedio(filtro);
		
		if ( imovelConsumoMedioCount == null || imovelConsumoMedioCount == 0 ) {
			//Caso a pesquisa não retorne nenhum objeto comunica ao usuário;
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioImoveisConsumoMedio relatorio = 
			new RelatorioImoveisConsumoMedio(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarRelatorioImoveisConsumoMedioHelper", filtro);
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
