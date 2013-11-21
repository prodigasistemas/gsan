/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
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
		// Fernanda
		Collection rotas = new ArrayList();
        
        Rota rota = new Rota();
        rota.setId(3);
        
        rotas.add(rota);
        
        //Integer indicadorGeracaoMulta = 1;
        
        //Integer indicadorGeracaoJuros = 1;
        
        //Integer indicadorGeracaoAtualizacao = 1;
        
        /*
		 * try { Collection gerarDados =
		 * Fachada.getInstancia().gerarDebitosACobrarDeAcrescimosPorImpontualidade(rotas,
		 * indicadorGeracaoMulta, indicadorGeracaoJuros,
		 * indicadorGeracaoAtualizacao); } catch (ControladorException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
    } else if ("UC0275".equals(casoUso)) {
    	// Toscano
    	//FachadaBatch.getInstancia().gerarResumoLigacoesEconomias();
    	
	} else if ("UC0209".equals(casoUso)) {
// Toscano
		//Integer anoMes = null;
		
		//if (httpServletRequest.getParameter("anoMes") != null) {
		//	anoMes = new Integer(httpServletRequest.getParameter("anoMes")) ;
		//}

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

		//Fachada.getInstancia().gerarTaxaEntregaDeContaEmOutroEndereco(colecaoRotas, anoMes);

	} else if ("UC0341".equals(casoUso)) {
// Toscano
		//FachadaBatch.getInstancia().gerarResumoSituacaoEspecialFaturamento();

	} else if ("UC0346".equals(casoUso)) {
// Toscano
		FachadaBatch.getInstancia().gerarResumoSituacaoEspecialCobranca();

	} else if ("UC0335".equals(casoUso)) {
		
		//FachadaBatch.getInstancia().gerarResumoPendencia();
		
	} else if ("UC0276".equals(casoUso)) {
		//FachadaBatch.getInstancia().encerrarArrecadacaoMes();
	} else if ("UC0341".equals(casoUso)) {
		throw new ActionServletException("não implementado");
	} else if ("UC0300".equals(casoUso)) {
		
		FachadaBatch.getInstancia().classificarPagamentosDevolucoes();
		
	} else if ("UC0301".equals(casoUso)) {
		// Pedro
		//FachadaBatch.getInstancia().gerarDadosDiariosArrecadacao();
	} else if ("UC0352".equals(casoUso)) {
		// throw new ActionServletException("não implementado");
		
		// execucaoBatchAction?casoUso=UC0343&idContas=1,2,3,1,2,1
		/*
		 * Collection colecaoContas = new ArrayList(); String idContas =
		 * httpServletRequest.getParameter("idContas"); String[] idContaArray =
		 * idContas.split(","); for(int i=0;i<idContaArray.length;i++){ Conta
		 * conta = new Conta(); Integer idConta = new Integer(idContaArray[i]);
		 * conta.setId(idConta); colecaoContas.add(conta); }
		 */
		//Collection colecaoContas = FachadaBatch.getInstancia().pesquisarIdsTodasConta();
		// FachadaBatch.getInstancia().emitirContas(colecaoContas);
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
		
		//FachadaBatch.getInstancia().emitirDocumentoCobranca();
		
	} else if ("UC0320".equals(casoUso)) {
		// Pedro
    	FachadaBatch.getInstancia().gerarFaturaClienteResponsavel(1);
	} else if ("UC0321".equals(casoUso)) {
		// Pedro
    	//FachadaBatch.getInstancia().emitirFaturaClienteResponsavel();
    	
	}else if("UC0343C".equals(casoUso)){
		FachadaBatch.getInstancia().gerarResumoAnormalidadeConsumo();
	}else if("UC0343".equals(casoUso)){
		FachadaBatch.getInstancia().gerarResumoAnormalidadeLeitura();
	}else if ("UC0213".equals(casoUso)) {
		// Fernanda
		FachadaBatch.getInstancia().desfazerParcelamentosPorEntradaNaoPaga();
	} else 
	  if("UC1111".equals(casoUso)){
		  Rota rota = new Rota();
		  rota.setId(1043);
		  //Fachada.getInstancia().gerarDadosPorLeituraConvencional(rota,200607,1);
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