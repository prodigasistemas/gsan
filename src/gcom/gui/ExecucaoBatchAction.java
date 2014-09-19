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
		throw new ActionServletException("não implementado");
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
		throw new ActionServletException("não implementado");
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
		throw new ActionServletException("Caso de uso não passado");
	}

/*
 * struts-execucaoBatch.xml
 * 
 * gerarResumoPendenciaAction.do gerarDebitosACobrarAction.do
 * gerarResumoAnormalidadeAction.do
 * 
 * UC0302 - Gerar Débitos a Cobrar de Acréscimos por Impontualidade Fernanda
 * UC0275 - Gerar Resumo das Ligações/Economias Toscano UC0209 - Gerar Taxa de
 * Entrega em Outro Endereço Toscano UC0341 - Gerar Resumo da Situação Especial
 * de Faturamento Toscano UC0346 - Gerar Resumo da Situação Especial de Cobrança
 * Toscano UC0335 - Gerar Resumo da Pendência Roberta UC0276 - Encerrar a
 * Arrecadação do Mês Pedro UC0348 - Gerar Lançamentos Contábeis da Arrecadação
 * Santos UC0300 - Classificar Pagamentos e Devoluções Rossiter UC0301 - Gerar
 * Dados Diários da Arrecadação Pedro UC0343 - Gerar Resumo das Anormalidades.
 * Flávio UC0352 - Emitir Contas Sávio UC0349 - Emitir Documento de Cobrança
 * Rossiter UC0320 - Gerar Fatura de Cliente Responsável Pedro UC0321 - Emitir
 * Fatura de Cliente Responsável Pedro
 * 
 */
	
	httpServletRequest.setAttribute("labelPaginaAtualizacao", "");
	httpServletRequest.setAttribute("caminhoAtualizarRegistro", "");

	httpServletRequest.setAttribute("labelPaginaSucesso", "Sucesso na execução do Batch do caso de uso " + casoUso);
	httpServletRequest.setAttribute("mensagemPaginaSucesso", "");
	httpServletRequest.setAttribute("caminhoFuncionalidade", "");

	 return retorno;
    }}
