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

/**
 * Gerar integração para contabilidade.
 *
 * @author Pedro Alexandre
 * @date 28/05/2007
 */
public class GerarIntegracaoContabilidadeAction extends GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno para a tela de sucesso.
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		GerarIntegracaoContabilidadeActionForm gerarIntegracaoContabilidadeActionForm = (GerarIntegracaoContabilidadeActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		//recupera os parâmetros informados pelo usuário.
		String idLancamentoOrigem = gerarIntegracaoContabilidadeActionForm.getIdLacamentoOrigem();
		String dataLancamento = gerarIntegracaoContabilidadeActionForm.getDataLancamento();
		
		//verifica se a origem do lançamento foi informada.
		if(idLancamentoOrigem == null || idLancamentoOrigem.trim().equals("")){
			throw new ActionServletException("atencao.naoinformado",null, "Lançamento Origem");
		}
		
		/*
		 * Caso a data não tenha sido informada levanta a exceção para o usuário.
		 */
		if(dataLancamento == null || dataLancamento.trim().equals("")){
			throw new ActionServletException("atencao.naoinformado",null, "Data de Lançamento");
		}else{
			//[FS0002 - Validar data do lançamento]
			//cria o formato da data
	        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");

	        try {
	        	//tenta converter a data de lançamento
	            dataFormato.parse(dataLancamento);

	            //se não conseguir converter
	        } catch (ParseException ex) {
	        	//levanta a exceção para a próxima camada
	        	throw new ActionServletException("atencao.data_pagamento_invalida");
	        }
		}
		
		//recupera o mês e o ano informados
        String mes = dataLancamento.substring(3, 5);
        String ano = dataLancamento.substring(6, 10);
        String anoMes = ano + mes;
        
        //chama o metódo para gerar o txt de integração para contabilidade 
        fachada.gerarIntegracaoContabilidade(idLancamentoOrigem, anoMes, dataLancamento);
		
	
		// montando página de sucesso
		montarPaginaSucesso(httpServletRequest, "Gerando a Integração para a Contabilidade.",
				"Gerar Integração para a Contabilidade", "/exibirGerarIntegracaoContabilidadeAction.do");

		return retorno;
	}
}
