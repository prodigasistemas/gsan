package gcom.gui.faturamento.consumotarifa;

import gcom.faturamento.consumotarifa.FiltroConsumoTarifaVigencia;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.MaiorQue;
import gcom.util.filtro.MenorQue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Filtra as tarifas de consumo
 * 
 * @author Tiago Moreno,Rafael Santos
 */
public class FiltrarConsumoTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterConsumoTarifa");

		FiltrarConsumoTarifaActionForm filtrarConsumoTarifaActionForm = (FiltrarConsumoTarifaActionForm) actionForm;

		//Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		// Variavel para testar se o campo naum obrigatorio esta vazio

		String descTarifa = filtrarConsumoTarifaActionForm.getDescTarifa();
		String dataVigenciaInicial = filtrarConsumoTarifaActionForm.getDataVigencia();
		String dataVigenciaFinal = filtrarConsumoTarifaActionForm
				.getDataVigenciaFim();
		String atualizar = (String)httpServletRequest.getParameter("atualizarFiltro");
		
		//valida o intervalo de datas
		if ((dataVigenciaInicial.trim().length() == 10)
				&& (dataVigenciaFinal.trim().length() == 10)) {

			Calendar calendarInicio = new GregorianCalendar();
			Calendar calendarFim = new GregorianCalendar();

			calendarInicio.set(Calendar.DAY_OF_MONTH, new Integer(dataVigenciaInicial
					.substring(0, 2)).intValue());
			calendarInicio.set(Calendar.MONTH, new Integer(dataVigenciaInicial
					.substring(3, 5)).intValue());
			calendarInicio.set(Calendar.YEAR, new Integer(dataVigenciaInicial
					.substring(6, 10)).intValue());

			calendarFim.set(Calendar.DAY_OF_MONTH, new Integer(dataVigenciaFinal
					.substring(0, 2)).intValue());
			calendarFim.set(Calendar.MONTH, new Integer(dataVigenciaFinal
					.substring(3, 5)).intValue());
			calendarFim.set(Calendar.YEAR, new Integer(dataVigenciaFinal
					.substring(6, 10)).intValue());
			// joga exessão
			if (calendarFim.compareTo(calendarInicio) < 0) {
				throw new ActionServletException(
						"atencao.data_vigencia_final.menor.data_vigencia_inicial");
			}
		}

		FiltroConsumoTarifaVigencia filtroConsumoTarifaVigencia = new FiltroConsumoTarifaVigencia();
		/*filtroConsumoTarifaVigencia
				.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaVigencia.CONSUMO_TARIFA_DESCRICAO);*/
        filtroConsumoTarifaVigencia.adicionarCaminhoParaCarregamentoEntidade(FiltroConsumoTarifaVigencia.CONSUMO_TARIFA);
        
		boolean parametroPesquisaInformando = false;
		
		//Descricao da Tarifa 
		if (descTarifa != null
			&& !descTarifa.trim().equalsIgnoreCase("")) {
				
				filtroConsumoTarifaVigencia.adicionarParametro(new ComparacaoTexto(
				FiltroConsumoTarifaVigencia.CONSUMO_TARIFA_DESCRICAO,
				descTarifa));
				parametroPesquisaInformando = true;
		}
		
		//data de Vigencia Inicial
		if (dataVigenciaInicial != null
			&& !dataVigenciaInicial.trim().equalsIgnoreCase("")) {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Date dataInicial = null;
		
			try {
				dataInicial = formatoData.parse(dataVigenciaInicial);
			} catch (ParseException e) {
				dataInicial = null;
			}
		
			filtroConsumoTarifaVigencia.adicionarParametro(new MaiorQue(
				FiltroConsumoTarifaVigencia.DATA_VIGENCIA, dataInicial));
			parametroPesquisaInformando = true;
		}
		
		//data de Vigencia final
		if (dataVigenciaFinal != null
			&& !dataVigenciaFinal.trim().equalsIgnoreCase("")) {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFinal = null;
		
			try {
				dataFinal = formatoData.parse(dataVigenciaFinal);
			} catch (ParseException e) {
				dataFinal = null;
			}
		
			filtroConsumoTarifaVigencia.adicionarParametro(new MenorQue(
				FiltroConsumoTarifaVigencia.DATA_VIGENCIA, dataFinal));
			parametroPesquisaInformando = true;
		}
		
		if(!parametroPesquisaInformando){
			// Exception que nao foi digitado nada!!!
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}


		filtroConsumoTarifaVigencia.setCampoOrderBy(
				FiltroConsumoTarifaVigencia.CONSUMO_TARIFA_DESCRICAO,
				FiltroConsumoTarifaVigencia.DATA_VIGENCIA);
		
		sessao.setAttribute("filtroConsumoTarifaVigencia", filtroConsumoTarifaVigencia);
		sessao.setAttribute("indicadorAtualizar", atualizar);	
		
		return retorno;
	}
}
