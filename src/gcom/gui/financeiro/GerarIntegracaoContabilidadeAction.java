package gcom.gui.financeiro;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarIntegracaoContabilidadeAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		GerarIntegracaoContabilidadeActionForm gerarIntegracaoContabilidadeActionForm = (GerarIntegracaoContabilidadeActionForm) actionForm;

		String idLancamentoOrigem = gerarIntegracaoContabilidadeActionForm.getIdLacamentoOrigem();
		String dataLancamento = gerarIntegracaoContabilidadeActionForm.getDataLancamento();
		
		if(idLancamentoOrigem == null || idLancamentoOrigem.trim().equals("")){
			throw new ActionServletException("atencao.naoinformado",null, "Lançamento Origem");
		}
		
		if(dataLancamento == null || dataLancamento.trim().equals("")){
			throw new ActionServletException("atencao.naoinformado",null, "Data de Lançamento");
		}else{
			//[FS0002 - Validar data do lançamento]
	        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

	        try {
	            dataFormato.parse(dataLancamento);
	        } catch (ParseException ex) {
	        	throw new ActionServletException("atencao.data_pagamento_invalida");
	        }
		}
		
        String mes = dataLancamento.substring(3, 5);
        String ano = dataLancamento.substring(6, 10);
        String anoMes = ano + mes;
        
        Fachada.getInstancia().gerarIntegracaoContabilidade(idLancamentoOrigem, anoMes, dataLancamento);
		
		montarPaginaSucesso(httpServletRequest, "Gerando a Integração para a Contabilidade.",
				"Gerar Integração para a Contabilidade", "/exibirGerarIntegracaoContabilidadeAction.do");

		return retorno;
	}
}
