package gcom.gui;

import gcom.fachada.FachadaBatch;
import gcom.micromedicao.Rota;
import gcom.util.email.ErroEmailException;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExecucaoBatchAction extends GcomAction {

public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws ErroEmailException {

    ActionForward retorno = actionMapping.findForward("telaSucesso");

	String casoUso = httpServletRequest.getParameter("casoUso");

	if ("UC0302".equals(casoUso)) {
		Collection rotas = new ArrayList();
        
        Rota rota = new Rota();
        rota.setId(3);
        
        rotas.add(rota);
        
    } else if ("UC0275".equals(casoUso)) {
    	
	} else if ("UC0209".equals(casoUso)) {

		Collection colecaoRotas = new ArrayList();		
		String idRotas = httpServletRequest.getParameter("idRotas");
		if (idRotas != null) {
			String[] idContaArray = idRotas.split(",");
			for(int i=0;i<idContaArray.length;i++){
				Rota rota = new Rota();
				Integer id = new Integer(idContaArray[i]);
				rota.setId(id);
				colecaoRotas.add(rota);
			}
		}


	} else if ("UC0341".equals(casoUso)) {

	} else if ("UC0346".equals(casoUso)) {
		FachadaBatch.getInstancia().gerarResumoSituacaoEspecialCobranca();

	} else if ("UC0335".equals(casoUso)) {
		
		
	} else if ("UC0276".equals(casoUso)) {
	} else if ("UC0341".equals(casoUso)) {
		throw new ActionServletException("n�o implementado");
	} else if ("UC0300".equals(casoUso)) {
		
		FachadaBatch.getInstancia().classificarPagamentosDevolucoes();
		
	} else if ("UC0301".equals(casoUso)) {
	} else if ("UC0352".equals(casoUso)) {
	} else if ("UC0348".equals(casoUso)) {
		Integer anoMes = null;
		
		if (httpServletRequest.getParameter("anoMes") != null) {
			anoMes = new Integer(httpServletRequest.getParameter("anoMes")) ;
			FachadaBatch.getInstancia().gerarLancamentoContabeisArrecadacao(anoMes);
		}				
	} else if ("UC0342".equals(casoUso)) {
	} else if ("UC0342".equals(casoUso)) {
		throw new ActionServletException("n�o implementado");
	} else if ("UC0349".equals(casoUso)) {
	} else if ("UC0320".equals(casoUso)) {
    	FachadaBatch.getInstancia().gerarFaturaClienteResponsavel(1);
	} else if ("UC0321".equals(casoUso)) {
	}else if("UC0343C".equals(casoUso)){
		FachadaBatch.getInstancia().gerarResumoAnormalidadeConsumo();
	}else if("UC0343".equals(casoUso)){
		FachadaBatch.getInstancia().gerarResumoAnormalidadeLeitura();
	}else if ("UC0213".equals(casoUso)) {
		FachadaBatch.getInstancia().desfazerParcelamentosPorEntradaNaoPaga();
	} else 
	  if("UC1111".equals(casoUso)){
		  Rota rota = new Rota();
		  rota.setId(1043);
	  }else{
		throw new ActionServletException("Caso de uso n�o passado");
	}

/*
 * struts-execucaoBatch.xml
 * 
 * gerarResumoPendenciaAction.do gerarDebitosACobrarAction.do
 * gerarResumoAnormalidadeAction.do
 * 
 * UC0302 - Gerar D�bitos a Cobrar de Acr�scimos por Impontualidade Fernanda
 * UC0275 - Gerar Resumo das Liga��es/Economias Toscano UC0209 - Gerar Taxa de
 * Entrega em Outro Endere�o Toscano UC0341 - Gerar Resumo da Situa��o Especial
 * de Faturamento Toscano UC0346 - Gerar Resumo da Situa��o Especial de Cobran�a
 * Toscano UC0335 - Gerar Resumo da Pend�ncia Roberta UC0276 - Encerrar a
 * Arrecada��o do M�s Pedro UC0348 - Gerar Lan�amentos Cont�beis da Arrecada��o
 * Santos UC0300 - Classificar Pagamentos e Devolu��es Rossiter UC0301 - Gerar
 * Dados Di�rios da Arrecada��o Pedro UC0343 - Gerar Resumo das Anormalidades.
 * Fl�vio UC0352 - Emitir Contas S�vio UC0349 - Emitir Documento de Cobran�a
 * Rossiter UC0320 - Gerar Fatura de Cliente Respons�vel Pedro UC0321 - Emitir
 * Fatura de Cliente Respons�vel Pedro
 * 
 */
	
	httpServletRequest.setAttribute("labelPaginaAtualizacao", "");
	httpServletRequest.setAttribute("caminhoAtualizarRegistro", "");

	httpServletRequest.setAttribute("labelPaginaSucesso", "Sucesso na execu��o do Batch do caso de uso " + casoUso);
	httpServletRequest.setAttribute("mensagemPaginaSucesso", "");
	httpServletRequest.setAttribute("caminhoFuncionalidade", "");

	 return retorno;
    }}
