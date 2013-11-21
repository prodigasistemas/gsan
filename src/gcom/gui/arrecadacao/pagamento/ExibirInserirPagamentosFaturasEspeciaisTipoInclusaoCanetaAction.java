/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperCodigoBarras;
import gcom.arrecadacao.pagamento.bean.InserirPagamentoViaCanetaHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que inicializa a segunda página do processo de inserir pagamentos 
 * se o tipo de inclusão for por leitura de código de barras 
 * Este action também é responsável por inserir ou remover um documento na lista de códigos de barra
 * e gerar os pagamento para cada código de barra informado por leitura óptica ou por digitação 
 * 
 * [UC0971] Inserir Pagamentos para Faturas Especiais
 * 
 * @author 	Vivianne Sousa
 * @created	21/12/2009
 */
public class ExibirInserirPagamentosFaturasEspeciaisTipoInclusaoCanetaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
        //Seta o mapeamento de retorno de retorno para po iniserir pagamento por caneta 
        ActionForward retorno = actionMapping.findForward("inserirPagamentosFaturasEspeciaisTipoInclusaoCaneta");

        //Recupera o form de pagamento
        PagamentosFaturasEspeciaisActionForm pagamentoActionForm = (PagamentosFaturasEspeciaisActionForm) actionForm;
        
        //Cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Verifica se o usuário selecionou um documento para remoção da coleçaõde códgos de barra
        String codigoBarraRemocao = httpServletRequest.getParameter("codigoBarraRemocao");
        
        //Recupera a data do pagamento e verifica se a data é uma data válida
        //Caso contrário levanta uma exceção para o usuário indicando que a data de
        //pagamento não é uma data válida
        String dataPagamentoString = pagamentoActionForm.getDataPagamento();
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataFormato.parse(dataPagamentoString);
        } catch (ParseException ex) {
        	throw new ActionServletException("atencao.data_pagamento_invalida");
        }
        
        //Recupera os parâmetros do sistemas 
        SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        
        //Recupera a coleção dos objetos que armazenam as informações necessárias dos códigos de barra
        Collection<InserirPagamentoViaCanetaHelper> colecaoInserirPagamentoViaCanetaHelper = (Collection<InserirPagamentoViaCanetaHelper>) sessao.getAttribute("colecaoInserirPagamentoViaCanetaHelper");
        
        //Se nenhum documento de código de barra na sessão
        if(colecaoInserirPagamentoViaCanetaHelper == null ){
        	//InstÂncia a coleção de documentos
        	colecaoInserirPagamentoViaCanetaHelper = new ArrayList();
        	
        	//Caso exista documentos de código de barra na sessão
        }else if(!colecaoInserirPagamentoViaCanetaHelper.isEmpty()){
        	
        	//Verifica se o usuário indicou um documento para ser removido da coleção
        	if(codigoBarraRemocao != null && !codigoBarraRemocao.trim().equalsIgnoreCase("")){
        		
        		Iterator iteratorRemocaoCodigoBarra = colecaoInserirPagamentoViaCanetaHelper.iterator();
        		
        		//Laço para encontrar o documento para ser removido
        		lacoWhile : while(iteratorRemocaoCodigoBarra.hasNext()){
        			//Recupera o documento do iterator
        			InserirPagamentoViaCanetaHelper inserirPagamentoViaCanetaHelperParaRemocao = (InserirPagamentoViaCanetaHelper) iteratorRemocaoCodigoBarra.next();
        			
        			//Se o código de barra indicado pelo usuário for igual ao 
        			//código de barra do documento
        			if(inserirPagamentoViaCanetaHelperParaRemocao.getCodigoBarra().equalsIgnoreCase(codigoBarraRemocao)){
        				
        				//Remove o documento e termina o laço
        				iteratorRemocaoCodigoBarra.remove();
        				
        				break lacoWhile;
        			}
        		}
        	}
        }
        
        //Cria a variável que vai armazenar o valor do pagamento
        BigDecimal valorPagamento = null;
        
        //Cria a variável que vai armazenar o código de barras
        String codigoBarra = null;
        
        //Cria as variáveis que vão armazenar o código de barra separado por campos
        //e seus respectivos dígitos verificadores se existirem
        String codigoBarraDigitadoCampo1 = null;
        String codigoBarraDigitadoDigitoVerificadorCampo1 = null;
        String codigoBarraDigitadoCampo2 = null; 
        String codigoBarraDigitadoDigitoVerificadorCampo2 = null;
        String codigoBarraDigitadoCampo3 = null;
        String codigoBarraDigitadoDigitoVerificadorCampo3 = null;
        String codigoBarraDigitadoCampo4 = null;
        String codigoBarraDigitadoDigitoVerificadorCampo4 = null;
        
        
        //Verifica se o código de barras foi lido por caneta óptica
        String codigoBarraLeituraOtica = pagamentoActionForm.getCodigoBarraPorLeituraOtica();
        
        //Caso o código de barras foi indicado por leitura ótica, recupera todos os campos do código de barra
        //Caso contrário recupera os campos de código de barra digitado e seus dígitos verificadores
        if(codigoBarraLeituraOtica != null && !codigoBarraLeituraOtica.equalsIgnoreCase("")){
        	codigoBarraDigitadoCampo1                  = codigoBarraLeituraOtica.substring(0,11);
        	codigoBarraDigitadoCampo2                  = codigoBarraLeituraOtica.substring(11,22);
        	codigoBarraDigitadoCampo3                  = codigoBarraLeituraOtica.substring(22,33);
        	codigoBarraDigitadoCampo4                  = codigoBarraLeituraOtica.substring(33,44);

        }else{
        	codigoBarraDigitadoCampo1                  = pagamentoActionForm.getCodigoBarraDigitadoCampo1();
        	codigoBarraDigitadoDigitoVerificadorCampo1 = pagamentoActionForm.getCodigoBarraDigitadoDigitoVerificadorCampo1();
        	codigoBarraDigitadoCampo2                  = pagamentoActionForm.getCodigoBarraDigitadoCampo2();
        	codigoBarraDigitadoDigitoVerificadorCampo2 = pagamentoActionForm.getCodigoBarraDigitadoDigitoVerificadorCampo2();
        	codigoBarraDigitadoCampo3                  = pagamentoActionForm.getCodigoBarraDigitadoCampo3();
        	codigoBarraDigitadoDigitoVerificadorCampo3 = pagamentoActionForm.getCodigoBarraDigitadoDigitoVerificadorCampo3();
        	codigoBarraDigitadoCampo4                  = pagamentoActionForm.getCodigoBarraDigitadoCampo4();
        	codigoBarraDigitadoDigitoVerificadorCampo4 = pagamentoActionForm.getCodigoBarraDigitadoDigitoVerificadorCampo4();
        }
        
        //Monta o código de barra sem os dígitos verificadores
        codigoBarra = codigoBarraDigitadoCampo1 + 
    				  codigoBarraDigitadoCampo2 +
    				  codigoBarraDigitadoCampo3 +
    				  codigoBarraDigitadoCampo4 ;
        
        //Se o código de barra não estiver vazio e o tamanho for igual a 44(quarenta e quatro)
        if(codigoBarra != null && !codigoBarra.trim().equalsIgnoreCase("") && codigoBarra.trim().length() == 44){
        	
        	
          //Caso o usuário não tenha informado a remoção de código de barra	
          if(codigoBarraRemocao == null || codigoBarraRemocao.trim().equalsIgnoreCase("")){	
        	
        	//Caso o código de barra tenha sido informado por digitação 
            if(codigoBarraLeituraOtica == null || codigoBarraLeituraOtica.equalsIgnoreCase("")){	
              //[FS0003] E [FS0005] - Validar dígito verificador do código de barra
              this.validarDigitoVerificador(codigoBarraDigitadoCampo1,codigoBarraDigitadoDigitoVerificadorCampo1,codigoBarraDigitadoCampo2,codigoBarraDigitadoDigitoVerificadorCampo2,codigoBarraDigitadoCampo3,codigoBarraDigitadoDigitoVerificadorCampo3,codigoBarraDigitadoCampo4,codigoBarraDigitadoDigitoVerificadorCampo4);
            }
          
            //Caso o código de barra já tenha sido informado pelo usuário e esteja na lista de documentos
            //[FS0004] - Verificar existência do documento na lista 
            if(this.verificarExistenciaDocumentoNaLista(codigoBarra, colecaoInserirPagamentoViaCanetaHelper)){
              //Levanta a exceção para o usuário indicando que o documento já foi informado	
        	  throw new ActionServletException("atencao.documento_informado");
            }else{
            	
            	//[SB0001] Processar fatura com codigo de barras
            	PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = fachada.
            		processarFaturaComCodigoBarras(codigoBarra,sistemaParametro);
            	
        	  //Recupera a descrição a ocorrência do movimento
         	  String descricaoOcorrenciaMovimento = pagamentoHelperCodigoBarras.getDescricaoOcorrencia();
         	  
         	  //Recupera o inidicador de aceitação do registro do movimento
        	  Integer indicadorAceitacaoRegistroMovimento = Integer.parseInt(pagamentoHelperCodigoBarras.getIndicadorAceitacaoRegistro());        	

        	  //Caso o inidicador de aceitação do registro do movimento seja igual a 1 (SIM) e
        	  //a descrição a ocorrência do movimento seja igual a "OK"
        	  //Caso contrário levanta uma exceção com a descrição da ocorrência do movimento
        	  if(indicadorAceitacaoRegistroMovimento == 1 && descricaoOcorrenciaMovimento.equalsIgnoreCase("OK")){
        		
        		//Cria o documento para o código de barras informado pelo usuário
                InserirPagamentoViaCanetaHelper inserirPagamentoViaCanetaHelper = new InserirPagamentoViaCanetaHelper();  
        		
        		//Chama o caso de uso [UC0264] para recuperar todos os dados contidos no códigode barras
        		RegistroHelperCodigoBarras distribuirDadosCodigoBarras = fachada.distribuirDadosCodigoBarras(codigoBarra);

        		//Recupera o valor do pagamento do código de barra 
        		valorPagamento = (Util.formatarMoedaRealparaBigDecimal(distribuirDadosCodigoBarras.getValorPagamento())).divide(new BigDecimal("100.00"));
        		
                inserirPagamentoViaCanetaHelper.setCodigoBarra(codigoBarraDigitadoCampo1 + codigoBarraDigitadoCampo2 + codigoBarraDigitadoCampo3 + codigoBarraDigitadoCampo4);
                inserirPagamentoViaCanetaHelper.setValorPagamento(valorPagamento);
                inserirPagamentoViaCanetaHelper.setRegistroHelperCodigoBarras(distribuirDadosCodigoBarras);
                
                //Adiciona o documento na coleção
                colecaoInserirPagamentoViaCanetaHelper.add(inserirPagamentoViaCanetaHelper);
                
                //Limpa os códigos de barras do form
                pagamentoActionForm.setCodigoBarraDigitadoCampo1("");
            	pagamentoActionForm.setCodigoBarraDigitadoDigitoVerificadorCampo1("");
            	pagamentoActionForm.setCodigoBarraDigitadoCampo2("");
            	pagamentoActionForm.setCodigoBarraDigitadoDigitoVerificadorCampo2("");
            	pagamentoActionForm.setCodigoBarraDigitadoCampo3("");
            	pagamentoActionForm.setCodigoBarraDigitadoDigitoVerificadorCampo3("");
            	pagamentoActionForm.setCodigoBarraDigitadoCampo4("");
            	pagamentoActionForm.setCodigoBarraDigitadoDigitoVerificadorCampo4("");
            	pagamentoActionForm.setCodigoBarraPorLeituraOtica("");  
        	  }else{
        		throw new ActionServletException("atencao.descricao_ocorrencia_movimento",null,descricaoOcorrenciaMovimento);
        	  }
            }
          }
        }
        
        
        //Seta na sessão a coleção de documento de códigos de barra informados e a coleção de pagamentos gerados
        sessao.setAttribute("colecaoInserirPagamentoViaCanetaHelper",colecaoInserirPagamentoViaCanetaHelper);
        
        //Retorna o mapeamento contido na variável retorno
        return retorno;
    }
    
    /**
     * Verifica se o códigode barras informado pelo usuário já está contido na lista de documentos
     *
     * [FS0004] Verificar existência do documento na lista
     */
    private boolean verificarExistenciaDocumentoNaLista(String codigoBarra, Collection<InserirPagamentoViaCanetaHelper> colecaoInserirPagamentoViaCanetaHelper){
    	
    	//Flag para indicar se o códigode barras já está contido na lista de documentos
    	boolean retorno = false;
    	
    	//Caso a lista de documentos não esteja vazia
    	if(colecaoInserirPagamentoViaCanetaHelper != null && !colecaoInserirPagamentoViaCanetaHelper.isEmpty()){
    		
    		//Laço para verificar se o documento já esta na coleção
    		loopPagamento : for(InserirPagamentoViaCanetaHelper inserirPagamentoViaCanetaHelper : colecaoInserirPagamentoViaCanetaHelper){
    			//Caso o código de barras do documento seja igual ao informado pelo usuário
    			if(inserirPagamentoViaCanetaHelper.getCodigoBarra().equals(codigoBarra)){
    				//Indica que o documento já existe na coleção
    				retorno = true;
    				
    				//Para o loop
    				break loopPagamento;
    			}
    		}
    	}
    	
    	//Retorna a flag indicando se o documento está na coleção
    	return retorno;
    }
    
    /**
     * Valida o código de barras, caso esse tenha sido digitado com seus  dígitos verificadores
     *
     * [FS0005] Validar Dígito verificador do Código de Barras 
     */
    private void validarDigitoVerificador(String campo1,String dvCampo1, String campo2,String dvCampo2,String campo3,String dvCampo3,String campo4,String dvCampo4){
    	
    	//Cria as variáveis quevai armazenar os dígitos verificadores do módulo 11 e 10 respectivamente
    	String dvModulo11 = null;
    	String dvModulo10 = null; 
    	
    	//Caso a terceira posição do primeiro campo do código de barras for igual a 6(seis)
    	//Obtém o digito verificador para o modulo 10(dez)
    	if((campo1.substring(2,3)).equals("6")){
    	  dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(campo1)).toString();
    	  if(!dvModulo10.equals(dvCampo1)){
    		  throw new ActionServletException("atencao.digitoverificador_invalido");  
    	  }
    	  
    	  dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(campo2)).toString();
    	  if(!dvModulo10.equals(dvCampo2)){
    		  throw new ActionServletException("atencao.digitoverificador_invalido");
    	  }
    	  
    	  dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(campo3)).toString();
    	  if(!dvModulo10.equals(dvCampo3)){
    		  throw new ActionServletException("atencao.digitoverificador_invalido");
    	  }
    	  
    	  dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(campo4)).toString();
    	  if(!dvModulo10.equals(dvCampo4)){
    		  throw new ActionServletException("atencao.digitoverificador_invalido");
    	  }
    	  
    	//Caso a terceira posição do primeiro campo do código de barras for igual a 8(oito)
      	//Obtém o digito verificador para o modulo 11(onze)
    	}else if((campo1.substring(2,3)).equals("8")){
    		dvModulo11 = Util.obterDigitoVerificadorModulo11(new Long(campo1)).toString();
    		if(!dvModulo11.equals(dvCampo1)){
    			throw new ActionServletException("atencao.digitoverificador_invalido");
        	  }
    		
    		dvModulo11 = Util.obterDigitoVerificadorModulo11(new Long(campo2)).toString();
    		if(!dvModulo11.equals(dvCampo2)){
    			throw new ActionServletException("atencao.digitoverificador_invalido");
        	  }

    		dvModulo11 = Util.obterDigitoVerificadorModulo11(new Long(campo3)).toString();
    		if(!dvModulo11.equals(dvCampo3)){
    			throw new ActionServletException("atencao.digitoverificador_invalido");
        	  }

    		dvModulo11 = Util.obterDigitoVerificadorModulo11(new Long(campo4)).toString();
    		if(!dvModulo11.equals(dvCampo4)){
    			throw new ActionServletException("atencao.digitoverificador_invalido");
        	  }

    		//Caso a terceira posição do primeiro campo do código de barras não for igual a 6(seis)
    		//ou não for igual a 8(oito), levanta uma exceção indicando que a indicação do módulo é inválida
    	}else{
    		throw new ActionServletException("atencao.indicacaomodulo_invalido");
    	}
    }    
}
