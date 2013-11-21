package gcom.gui.micromedicao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ContratoEmpresaAditivo;
import gcom.micromedicao.InformarItensContratoServicoHelper;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Tela de PopUp de Incluir Aditivo
 * 
 * @author Mariana Victor
 * @date 24/11/2010
 */

public class IncluirAditivoAction extends GcomAction{
	
	/**
     * < <Descrição do método>>
     * 
     * @param actionMapping
     *            Descrição do parâmetro
     * @param actionForm
     *            Descrição do parâmetro
     * @param httpServletRequest
     *            Descrição do parâmetro
     * @param httpServletResponse
     *            Descrição do parâmetro
     * @return Descrição do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {
        
    	ActionForward retorno = actionMapping.findForward("incluirAditivo");

        HttpSession sessao = httpServletRequest.getSession(false);
        
        ExibirInformarItensContratoServicoActionForm form = (ExibirInformarItensContratoServicoActionForm) actionForm;

		List colecaoHelper = (List) sessao.getAttribute("colecaoHelper");

		InformarItensContratoServicoHelper helper = null;

		Integer posicaoComponente = new Integer(form.getIdNumeroContrato());
		
		helper = (InformarItensContratoServicoHelper) colecaoHelper.get(posicaoComponente-1);
		
        ContratoEmpresaAditivo contratoEmpresaAditivo = new ContratoEmpresaAditivo();

        contratoEmpresaAditivo.setUltimaAlteracao(new Date());
        contratoEmpresaAditivo.setContratoEmpresaServico(helper.getContratoEmpresaServico());
        contratoEmpresaAditivo.setDataInicioContrato(Util.converteStringParaDate(form.getDataInicioAditivo()));
        
        if (form.getDataFimAditivo() != null && !form.getDataFimAditivo().equals("")) {
        	contratoEmpresaAditivo.setDataFimContrato(Util.converteStringParaDate(form.getDataFimAditivo()));
        	this.verificarData(form.getDataInicioAditivo(), form.getDataFimAditivo());
        }
        
        if (form.getValorAditivoContrato() != null && !form.getValorAditivoContrato().equals("")) {
    		BigDecimal igualZero = new BigDecimal(0);
    		BigDecimal valorAditivoContratoAtual = null;
    		String valorAditivoContrato = form.getValorAditivoContrato()
						.toString().replace(".", "");
    		
    		valorAditivoContrato = valorAditivoContrato.replace(",", ".");
    		valorAditivoContratoAtual = new BigDecimal(valorAditivoContrato);
    		
    		if (valorAditivoContratoAtual.compareTo(igualZero) <= -1){
    			throw new ActionServletException("atencao.invalido_zero", null ,"O Valor do Aditivo do Contrato" );	
    		}
    		contratoEmpresaAditivo.setValorAditivoContrato(valorAditivoContratoAtual);
		}
        
        if (form.getPercentualTaxaSucessoAditivo() != null
				&& !form.getPercentualTaxaSucessoAditivo()
						.equals("")) {
			
			BigDecimal percentualTaxaSucessoAtual = null;

			String percentualTaxaSucesso = form.getPercentualTaxaSucessoAditivo()
					.toString().replace(".", "");

			percentualTaxaSucesso = percentualTaxaSucesso.replace(",", ".");
			percentualTaxaSucessoAtual = new BigDecimal(percentualTaxaSucesso);
			
			contratoEmpresaAditivo
					.setPercentualTaxaSucesso(percentualTaxaSucessoAtual);

		} else {
			contratoEmpresaAditivo
					.setPercentualTaxaSucesso(null);
		}
        
        ArrayList colecaoAditivo =  new ArrayList();
        if (helper.getContratoEmpresaAditivo() != null && helper.getContratoEmpresaAditivo().size() > 0) {
        	colecaoAditivo = (ArrayList) helper.getContratoEmpresaAditivo();
        }
        colecaoAditivo.add(contratoEmpresaAditivo);
        helper.setContratoEmpresaAditivo((List<ContratoEmpresaAditivo>) colecaoAditivo);
		colecaoHelper.remove(posicaoComponente-1);
		colecaoHelper.add(helper);
		
//		 o sistema classifica a lista de InformarItensContratoServicoHelper recebidas por Empresa
		Collections.sort((List) colecaoHelper,
				new Comparator() {
					public int compare(Object a, Object b) {
						String codigo1 = ((InformarItensContratoServicoHelper) a)
								.getContratoEmpresaServico().getDescricaoContrato();
						String codigo2 = ((InformarItensContratoServicoHelper) b)
								.getContratoEmpresaServico().getDescricaoContrato();
						if (codigo1 == null || codigo1.equals("")) {
							return -1;
						} else {
							return codigo1.compareTo(codigo2);
						}
					}
				});
		
		sessao.setAttribute("colecaoHelper", colecaoHelper);
		form.setDataInicioAditivo("");
		form.setDataFimAditivo("");
		form.setValorAditivoContrato("");
		form.setPercentualTaxaSucessoAditivo("");
        sessao.removeAttribute("colecaoAditivo");
        sessao.setAttribute("colecaoAditivo", colecaoAditivo);
		httpServletRequest.setAttribute("fecharPopup", "OK");
		sessao.setAttribute("fecharPopup", "OK");
        
        return retorno;
    }

	/**
	 * @param form
	 */
	private void verificarData(String dataInicio, String dataFim) {
		
		// Verifica se a Data Final é maior que a Inicial
        if ( dataInicio != null && !dataInicio.equals("")
        		&& dataFim != null && !dataFim.equals("")){
        	
        	Date dtInicial = Util.converteStringParaDate( dataInicio);
        	Date dtFinal = Util.converteStringParaDate( dataFim);
        	
        	if (Util.compararData(dtFinal, dtInicial) == -1){
        		
        		throw new ActionServletException("atencao.data.intervalo.invalido", null ,"Data Inválida" );
        	}
        }
	}
}
