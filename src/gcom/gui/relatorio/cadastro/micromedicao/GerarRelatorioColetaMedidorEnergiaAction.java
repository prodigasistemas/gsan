package gcom.gui.relatorio.cadastro.micromedicao;

import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cadastro.micromedicao.RelatorioColetaMedidorEnergia;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *  [UC0999] Gerar Relatório de Coleta de Medidor de Energia.
 * 
 * @author Hugo Leonardo
 * @date 09/03/2010
 * 
 */
public class GerarRelatorioColetaMedidorEnergiaAction extends ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirGerarRelatorioColetaMedidorEnergiaAction");
		
		// Form
		GerarRelatorioColetaMedidorEnergiaActionForm form = 
			(GerarRelatorioColetaMedidorEnergiaActionForm) actionForm;
		
		FiltrarRelatorioColetaMedidorEnergiaHelper filtro = 
			new FiltrarRelatorioColetaMedidorEnergiaHelper();
		
		// Grupo Faturamento
		if (form.getFaturamentoGrupo() != null && 
			!form.getFaturamentoGrupo().equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO) ) {
			
			filtro.setIdFaturamentoGrupo(new String(form.getFaturamentoGrupo()));
		}
		
		// Localidade Inicial
		if (form.getLocalidadeInicial() != null && 
			!form.getLocalidadeInicial().equals("") ) {
				
			filtro.setIdLocalidadeInicial(new String(form.getLocalidadeInicial()));
		}
		
		// Setor Comercial Inicial
		if(form.getCodigoSetorComercialInicial() != null && 
				!form.getCodigoSetorComercialInicial().equals("")){
			
			filtro.setIdSetorComercialInicial(new String(form.getCodigoSetorComercialInicial()));
		}

		// Rota Inicial
		if (form.getRotaInicial() != null && 
			!form.getRotaInicial().equals("") ) {
				
			filtro.setRotaInicial(new String(form.getRotaInicial()));
		}

		// Sequencial Rota Inicial
		if (form.getSequencialRotaInicial() != null && 
			!form.getSequencialRotaInicial().equals("") ) {
				
			filtro.setSequencialRotaInicial(new String(form.getSequencialRotaInicial()));
		}

		// Localidade Final
		if (form.getLocalidadeFinal() != null && 
			!form.getLocalidadeFinal().equals("") ) {
				
			filtro.setIdLocalidadeFinal(new String(form.getLocalidadeFinal()));
		}
		
		// Setor Comercial Final
		if(form.getCodigoSetorComercialFinal() != null && 
				!form.getCodigoSetorComercialFinal().equals("")){
			
			filtro.setIdSetorComercialFinal(new String(form.getCodigoSetorComercialFinal()));
		}
		
		// Rota Final
		if (form.getRotaFinal() != null && 
			!form.getRotaFinal().equals("") ) {
				
			filtro.setRotaFinal(new String(form.getRotaFinal()));
		}
		
		// Sequencial Rota Final
		if (form.getSequencialRotaFinal() != null && 
			!form.getSequencialRotaFinal().equals("") ) {
				
			filtro.setSequencialRotaFinal(new String(form.getSequencialRotaFinal()));
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");

		RelatorioColetaMedidorEnergia relatorio = 
			new RelatorioColetaMedidorEnergia(this.getUsuarioLogado(httpServletRequest));
		
		relatorio.addParametro("filtrarRelatorioColetaMedidorEnergiaHelper", filtro);
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
