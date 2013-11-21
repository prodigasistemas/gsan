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
package gcom.gui.arrecadacao.pagamento;

import gcom.arrecadacao.bean.PagamentoHelperCodigoBarras;
import gcom.arrecadacao.bean.RegistroHelperFichaCompensacao;
import gcom.arrecadacao.pagamento.bean.InserirPagamentoViaCanetaHelper;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

/**
 * @author 	Raphael Rossiter
 * @date	25/11/2010
 */
public class ExibirInserirPagamentosTipoInclusaoFichaCompensacaoAction extends GcomAction {

    
	/**
     * Inserir pagamentos no sistema
     *
     * [UC0265] Inserir Pagamentos
     *
     * @author Raphael Rossiter 
     * @date 26/11/2010
     *
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					 ActionForm actionForm, 
            					 HttpServletRequest httpServletRequest,
            					 HttpServletResponse httpServletResponse) {
    	
        //Seta o mapeamento de retorno de retorno para po iniserir pagamento por caneta 
        ActionForward retorno = actionMapping.findForward("inserirPagamentosTipoInclusaoFichaCompensacao");

        //Recupera o form de pagamento
        DynaValidatorActionForm pagamentoActionForm = (DynaValidatorActionForm) actionForm;
        
        //Cria uma instância da sessão
        HttpSession sessao = httpServletRequest.getSession(false);

        //Cria uma instância da fachada
        Fachada fachada = Fachada.getInstancia();
        
        //Usuario Logado
        Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
        
        //Verifica se o usuário selecionou um documento para remoção da coleçaõde códgos de barra
        String codigoBarraRemocao = httpServletRequest.getParameter("codigoBarraRemocao");
        
        //Recupera a data do pagamento e verifica se a data é uma data válida
        //Caso contrário levanta uma exceção para o usuário indicando que a data de
        //pagamento não é uma data válida
        String dataPagamentoString = (String)pagamentoActionForm.get("dataPagamento");
        Date dataPagamento = null;
        SimpleDateFormat dataFormato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dataPagamento = dataFormato.parse(dataPagamentoString);
        } catch (ParseException ex) {
        	throw new ActionServletException("atencao.data_pagamento_invalida");
        }
        
        //Recupera o código da forma de arrecadação
        String idFormaArrecadacao = (String)pagamentoActionForm.get("idFormaArrecadacao");
        
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
        String codigoBarraDigitadoCampo1Parte1 = null;
        String codigoBarraDigitadoCampo1Parte2 = null;
        
        String codigoBarraDigitadoCampo2 = null;
        String codigoBarraDigitadoCampo2Parte1 = null;
        String codigoBarraDigitadoCampo2Parte2 = null;
        
        String codigoBarraDigitadoCampo3 = null;
        String codigoBarraDigitadoCampo3Parte1 = null;
        String codigoBarraDigitadoCampo3Parte2 = null;
        
        String codigoBarraDigitadoDigitoVerificador = null;
        
        String codigoBarraDigitadoCampo4 = null;
        
        
        //Verifica se o código de barras foi lido por caneta óptica
        String codigoBarraLeituraOtica = (String)pagamentoActionForm.get("codigoBarraPorLeituraOtica");
        
        //Caso o código de barras foi indicado por leitura ótica, recupera todos os campos do código de barra
        //Caso contrário recupera os campos de código de barra digitado e seus dígitos verificadores
        if(codigoBarraLeituraOtica != null && !codigoBarraLeituraOtica.equalsIgnoreCase("")){
        	
        	//PRIMEIRO QUADRANTE
        	/*codigoBarraDigitadoCampo1Parte1            			= codigoBarraLeituraOtica.substring(0,5);
        	codigoBarraDigitadoCampo1Parte2						= codigoBarraLeituraOtica.substring(5,10);
        	
        	codigoBarraDigitadoCampo1							= codigoBarraDigitadoCampo1Parte1 + codigoBarraDigitadoCampo1Parte2;
        	
        	//SEGUNDO QUADRANTE
        	codigoBarraDigitadoCampo2Parte1            			= codigoBarraLeituraOtica.substring(10,15);
        	codigoBarraDigitadoCampo2Parte2						= codigoBarraLeituraOtica.substring(15,21);
        	
        	codigoBarraDigitadoCampo2							= codigoBarraDigitadoCampo2Parte1 + codigoBarraDigitadoCampo2Parte2;
        	
        	//TERCEIRO QUADRANTE
        	codigoBarraDigitadoCampo3Parte1            			= codigoBarraLeituraOtica.substring(21,26);
        	codigoBarraDigitadoCampo3Parte2						= codigoBarraLeituraOtica.substring(26,32);
        	
        	codigoBarraDigitadoCampo3							= codigoBarraDigitadoCampo3Parte1 + codigoBarraDigitadoCampo3Parte2;
        	
        	//DÍGITO VERIFICADOR DO MÓDULO 11
        	codigoBarraDigitadoDigitoVerificador				= codigoBarraLeituraOtica.substring(32,33);
        	
        	//FATOR DE VENCIMENTO E VALOR DO TÍTULO
        	codigoBarraDigitadoCampo4							= codigoBarraLeituraOtica.substring(33,47);*/
        	
        	codigoBarra = fachada.obterRepresentacaoNumericaCodigoBarraFichaCompensacao(codigoBarraLeituraOtica);
        	codigoBarra = codigoBarra.replace(".", "");
        	codigoBarra = codigoBarra.replace(" ", "");

        }
        else{
        	
        	//PRIMEIRO QUADRANTE
        	codigoBarraDigitadoCampo1Parte1            			= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo1Parte1");
        	
        	if (pagamentoActionForm.get("codigoBarraDigitadoCampo1Parte2") != null &&
        		((String)pagamentoActionForm.get("codigoBarraDigitadoCampo1Parte2")).length() == 5){
        		
        		codigoBarraDigitadoCampo1Parte2					= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo1Parte2");
            	
            	codigoBarraDigitadoCampo1						= codigoBarraDigitadoCampo1Parte1 + codigoBarraDigitadoCampo1Parte2;
        	}
        	else{
        		
        		codigoBarraDigitadoCampo1Parte2					= "";
            	
            	codigoBarraDigitadoCampo1						= "";
        	}
        	
        	
        	//SEGUNDO QUADRANTE
        	codigoBarraDigitadoCampo2Parte1            			= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo2Parte1");
        	
        	if (pagamentoActionForm.get("codigoBarraDigitadoCampo2Parte2") != null &&
            	((String)pagamentoActionForm.get("codigoBarraDigitadoCampo2Parte2")).length() == 6){
            		
        		codigoBarraDigitadoCampo2Parte2					= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo2Parte2");
            	
            	codigoBarraDigitadoCampo2						= codigoBarraDigitadoCampo2Parte1 + codigoBarraDigitadoCampo2Parte2;
            }
            else{
            		
            	codigoBarraDigitadoCampo2Parte2					= "";
            	
            	codigoBarraDigitadoCampo2						= "";
            }
        	
        	
        	//TERCEIRO QUADRANTE
        	codigoBarraDigitadoCampo3Parte1            			= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo3Parte1");
        	
        	if (pagamentoActionForm.get("codigoBarraDigitadoCampo3Parte2") != null &&
            	((String)pagamentoActionForm.get("codigoBarraDigitadoCampo3Parte2")).length() == 6){
            		
        		codigoBarraDigitadoCampo3Parte2					= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo3Parte2");
            	
            	codigoBarraDigitadoCampo3						= codigoBarraDigitadoCampo3Parte1 + codigoBarraDigitadoCampo3Parte2;
            }
            else{
            		
            	codigoBarraDigitadoCampo3Parte2					= "";
            	
            	codigoBarraDigitadoCampo3						= "";
            }
        	
        	//DÍGITO VERIFICADOR DO MÓDULO 11
        	codigoBarraDigitadoDigitoVerificador				= (String)pagamentoActionForm.get("codigoBarraDigitadoDigitoVerificador");
        	
        	//FATOR DE VENCIMENTO E VALOR DO TÍTULO
        	codigoBarraDigitadoCampo4							= (String)pagamentoActionForm.get("codigoBarraDigitadoCampo4");
        	
        	//Monta o código de barra sem os dígitos verificadores
            codigoBarra = codigoBarraDigitadoCampo1 +
        				  codigoBarraDigitadoCampo2 +
        				  codigoBarraDigitadoCampo3 +
        				  codigoBarraDigitadoDigitoVerificador +
        				  codigoBarraDigitadoCampo4 ;
        }
        
        
        
        //Se o código de barra não estiver vazio e o tamanho for igual a 44(quarenta e quatro)
        if(codigoBarra != null && !codigoBarra.trim().equalsIgnoreCase("") && codigoBarra.trim().length() == 47){
        	
        	
          //Caso o usuário não tenha informado a remoção de código de barra	
          if(codigoBarraRemocao == null || codigoBarraRemocao.trim().equalsIgnoreCase("")){	
        	
        	RegistroHelperFichaCompensacao codigoBarraFichaCompensacaoHelper = fachada.distribuirDadosFichaCompensacao(codigoBarra);
        	
        	StringBuilder nossoNumero = fachada.obterNossoNumeroFichaCompensacao(codigoBarraFichaCompensacaoHelper.getIdDocumentoTipo(), 
        	codigoBarraFichaCompensacaoHelper.getIdCobrancaDocumento()) ;
        	    	
        	String nossoNumeroSemDV = nossoNumero.toString().substring(0,17);
        	  
        	//Caso o código de barra tenha sido informado por digitação 
            if(codigoBarraLeituraOtica == null || codigoBarraLeituraOtica.equalsIgnoreCase("")){	
              //[FS0003] E [FS0005] - Validar dígito verificador do código de barra
            	this.validarDigitoVerificador(fachada, codigoBarraFichaCompensacaoHelper, nossoNumeroSemDV);
            }
          
            //Caso o código de barra já tenha sido informado pelo usuário e esteja na lista de documentos
            //[FS0004] - Verificar existência do documento na lista 
            if(this.verificarExistenciaDocumentoNaLista(codigoBarra, colecaoInserirPagamentoViaCanetaHelper)){
              //Levanta a exceção para o usuário indicando que o documento já foi informado	
        	  throw new ActionServletException("atencao.documento_informado");
            }else{
        	
        	  //[SB0008] – Processar Pagamento Ficha de Compensação.
        	  PagamentoHelperCodigoBarras pagamentoHelperCodigoBarras = fachada.processarPagamentosFichaCompensacao(
        	  sistemaParametro, dataPagamento, codigoBarraFichaCompensacaoHelper.getValorDocumento(), nossoNumeroSemDV 
        	  ,new Integer(idFormaArrecadacao), usuarioLogado);  
        	  
        	  //Recupera a descrição a ocorrência do movimento
         	  String descricaoOcorrenciaMovimento = pagamentoHelperCodigoBarras.getDescricaoOcorrencia();
         	  
         	  //Recupera o inidicador de aceitação do registro do movimento
        	  Integer indicadorAceitacaoRegistroMovimento = Integer.parseInt(pagamentoHelperCodigoBarras.getIndicadorAceitacaoRegistro());        	

        	  //Caso o inidicador de aceitação do registro do movimento seja igual a 1 (SIM) e
        	  //a descrição a ocorrência do movimento seja igual a "OK"
        	  //Caso contrário levanta uma exceção com a descrição da ocorrência do movimento
        	  if(indicadorAceitacaoRegistroMovimento == 1 && descricaoOcorrenciaMovimento.equalsIgnoreCase("OK")){
        		
        		/*
        		 * Colocado por Raphael Rossiter em 30/10/2007
        		 * OBJ: Gerar as devolucões
        		 */
        		  
        		//Cria o documento para o código de barras informado pelo usuário
                InserirPagamentoViaCanetaHelper inserirPagamentoViaCanetaHelper = new InserirPagamentoViaCanetaHelper();  
        		
        		//Adiciona a coleção de pagamentos retornada pelo [SB0003]  
                inserirPagamentoViaCanetaHelper.setColecaoPagamento(pagamentoHelperCodigoBarras.getColecaoPagamentos());
                
                //Adiciona a devolucao retornada pelo [SB0003]  
                inserirPagamentoViaCanetaHelper.setColecaoDevolucao(pagamentoHelperCodigoBarras.getColecaoDevolucao());

        		//Recupera o valor do pagamento do código de barra 
        		//valorPagamento = (Util.formatarMoedaRealparaBigDecimal(distribuirDadosCodigoBarras.getValorPagamento())).divide(new BigDecimal("100.00"));
        		valorPagamento = codigoBarraFichaCompensacaoHelper.getValorDocumento();
        		
        		if(codigoBarraLeituraOtica == null || codigoBarraLeituraOtica.equals("")) {
                inserirPagamentoViaCanetaHelper.setCodigoBarra(codigoBarraDigitadoCampo1 + codigoBarraDigitadoCampo2 + codigoBarraDigitadoCampo3 + 
                codigoBarraDigitadoDigitoVerificador + codigoBarraDigitadoCampo4);
        		}
        		else {
        			inserirPagamentoViaCanetaHelper.setCodigoBarra(codigoBarra);
        		}
                inserirPagamentoViaCanetaHelper.setValorPagamento(valorPagamento);
              
                //Adiciona o documento na coleção
                colecaoInserirPagamentoViaCanetaHelper.add(inserirPagamentoViaCanetaHelper);
                
                //Limpa os códigos de barras do form
                pagamentoActionForm.set("codigoBarraDigitadoCampo1Parte1","");
            	pagamentoActionForm.set("codigoBarraDigitadoCampo1Parte2","");
            	pagamentoActionForm.set("codigoBarraDigitadoCampo2Parte1","");
            	pagamentoActionForm.set("codigoBarraDigitadoCampo2Parte2","");
            	pagamentoActionForm.set("codigoBarraDigitadoCampo3Parte1","");
            	pagamentoActionForm.set("codigoBarraDigitadoCampo3Parte2","");
            	pagamentoActionForm.set("codigoBarraDigitadoDigitoVerificador","");
            	pagamentoActionForm.set("codigoBarraDigitadoCampo4","");
            	pagamentoActionForm.set("codigoBarraPorLeituraOtica","");  
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
     *
     * @author Pedro Alexandre
     * @date 16/02/2006
     *
     * @param codigoBarra
     * @param colecaoPagamentos
     * @return
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
     *
     * @author Pedro Alexandre
     * @date 16/02/2006
     */
    private void validarDigitoVerificador(Fachada fachada, RegistroHelperFichaCompensacao codigoBarraFichaCompensacaoHelper,
    		String nossoNumeroSemDV){
    	
    	//Cria as variáveis quevai armazenar os dígitos verificadores do módulo 11 e 10 respectivamente
    	String dvModulo11 = null;
    	String dvModulo10 = null; 
    	
    	dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(codigoBarraFichaCompensacaoHelper.getCampo1SemDigito())).toString();
    	if(!dvModulo10.equals(codigoBarraFichaCompensacaoHelper.getDigitoVerificadorModulo10Campo1())){
    		throw new ActionServletException("atencao.digitoverificador_invalido");  
    	}
    	  
    	dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(codigoBarraFichaCompensacaoHelper.getCampo2SemDigito())).toString();
    	if(!dvModulo10.equals(codigoBarraFichaCompensacaoHelper.getDigitoVerificadorModulo10Campo2())){
    		throw new ActionServletException("atencao.digitoverificador_invalido");
    	}
    	  
    	dvModulo10 = Util.obterDigitoVerificadorModulo10(new Long(codigoBarraFichaCompensacaoHelper.getCampo3SemDigito())).toString();
    	if(!dvModulo10.equals(codigoBarraFichaCompensacaoHelper.getDigitoVerificadorModulo10Campo3())){
    		throw new ActionServletException("atencao.digitoverificador_invalido");
    	}
    	  
    	//DIGITO VERIFICADOR GERAL
    	String especificacaoCodigoBarra = fachada.obterEspecificacaoCodigoBarraFichaCompensacao(
    			ConstantesSistema.CODIGO_BANCO_FICHA_COMPENSACAO, ConstantesSistema.CODIGO_MOEDA_FICHA_COMPENSACAO, 
    			codigoBarraFichaCompensacaoHelper.getValorDocumento(), nossoNumeroSemDV.toString(), 
    			ConstantesSistema.CARTEIRA_FICHA_COMPENSACAO, codigoBarraFichaCompensacaoHelper.getFatorVencimento());
    	
    	dvModulo11 = especificacaoCodigoBarra.substring(4, 5);
    	if (!dvModulo11.equals(codigoBarraFichaCompensacaoHelper.getDigitoVerificadorModulo11())){
    		throw new ActionServletException("atencao.digitoverificador_invalido");
    	}
    }    
}
