package gcom.gui.relatorio.gerencial.faturamento;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe gerar o arquivo TXT do orcamento e SINP 
 *
 * @author Tiago Moreno
 * 
 * @date 14/02/2007
 */

public class GerarArquivoOrcamentoSinpAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		//Form
		GerarArquivoOrcamentoSinpActionForm form = 
			(GerarArquivoOrcamentoSinpActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		//[FS0001] - Validar referência (CASO 1 e 2)
		Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoFaturamento());
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		if(sistemaParametro.getAnoMesFaturamento().intValue() < anoMes.intValue()){
			throw new ActionServletException("atencao.mes_ano.faturamento.inferior", 
				null,""+sistemaParametro.getAnoMesFaturamento());
		}
		
		//[FS0001] - Validar referência (CASO 3)		
		fachada.existeDadosUnResumoParcialParaOrcamentoSINP(anoMes);
		
		//Gera do Arquivo texto
		fachada.gerarArquivoTextoOrcamentoSinp(anoMes.intValue());
		
		//pagina de sucesso
		montarPaginaSucesso(httpServletRequest, "Arquivo de Texto para Orçamento e SINP gerado com sucesso.",
				"Gerar outro Arquivo de Texto para Orçamento e SINP",
				"exibirGerarArquivoOrcamentoSinpAction.do?menu=sim" );

		return retorno;
	}
	
		
}
