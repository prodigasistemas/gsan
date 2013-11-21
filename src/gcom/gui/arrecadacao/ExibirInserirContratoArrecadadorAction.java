/**
 * 
 */
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
package gcom.gui.arrecadacao;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.ArrecadadorContratoTarifa;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroArrecadador;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
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

/**
 * Descrição da classe
 * 
 * @author Marcio Roberto
 * @date 13/03/2007
 */
public class ExibirInserirContratoArrecadadorAction extends GcomAction {

	/**
	 * [UC0509] Inserir Contrato Arrecadador
	 * 
	 * @author Marcio Roberto
	 * @date 13/03/2007
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("contratoArrecadadorInserir");
		
		Collection colecaoPesquisa = null;

		InserirContratoArrecadadorActionForm inserirContratoArrecadadorActionForm = (InserirContratoArrecadadorActionForm) actionForm;

		// Instancia da Fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Campos do formulario
		String idCliente = inserirContratoArrecadadorActionForm.getIdCliente();

		// Arrecadador
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();

		// Ordena filtro de arrecadador por id do cliente
		filtroArrecadador.setCampoOrderBy(FiltroArrecadador.CLIENTE_ID);
		// Inclui a obejeto de cliente no filtro de arrecadador 
		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
		// Preenche colecao de arrecadador 
		Collection<Arrecadador> colecaoArrecadador = fachada.pesquisar(filtroArrecadador, Arrecadador.class.getName());
		if (colecaoArrecadador == null || colecaoArrecadador.isEmpty()) {
			throw new ActionServletException("atencao.entidade_sem_dados_para_selecao", null,"Arrecadador");
		} else {
			FiltroCliente filtroCliente = new FiltroCliente();
			Iterator iteratorColecaoArrecadador = colecaoArrecadador.iterator();
			Cliente cliente = new Cliente();
			while (iteratorColecaoArrecadador.hasNext()) {
				Arrecadador arrecadador = (Arrecadador) iteratorColecaoArrecadador.next();
				cliente = arrecadador.getCliente(); 
				filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, cliente.getId(),ParametroSimples.CONECTOR_OR));
			}
			Collection colecaoClienteArrecadador = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			if(colecaoClienteArrecadador.isEmpty()){
				sessao.setAttribute("colecaoClienteArrecadador", colecaoClienteArrecadador);
			}else{			
				sessao.setAttribute("colecaoClienteArrecadador", colecaoClienteArrecadador);
			}
		}
		
		String objetoConsulta = (String) httpServletRequest
    	.getParameter("objetoConsulta");
		
		if (objetoConsulta != null
				&& !objetoConsulta.trim().equalsIgnoreCase("")) {

			switch (Integer.parseInt(objetoConsulta)) {	
		
			// Cliente
            	case 1:
            		//Recebe o valor do campo bancoID do formulário.
            		String clienteID = inserirContratoArrecadadorActionForm
                        .getIdCliente();

            		FiltroCliente filtroCliente1 = new FiltroCliente();

            		filtroCliente1
                        .adicionarParametro(new ParametroSimples(
                                FiltroCliente.ID, clienteID));

            		filtroCliente1
                        .adicionarParametro(new ParametroSimples(
                        		FiltroCliente.INDICADOR_USO,
                                ConstantesSistema.INDICADOR_USO_ATIVO));

                //Retorna Cliente
                colecaoPesquisa = fachada.pesquisar(filtroCliente1,
                        Cliente.class.getName());

                if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
                    //Setor censitario nao encontrado
                    //Limpa o campo clienteID do formulário
                	inserirContratoArrecadadorActionForm.setIdCliente("");
                	inserirContratoArrecadadorActionForm
                            .setNomeCliente("Cliente inexistente.");
                    httpServletRequest.setAttribute("existeCliente",
                            "exception");
                    
                    httpServletRequest.setAttribute("nomeCampo", "clienteID");
                } else {
                    Cliente objetoCliente = (Cliente) Util
                            .retonarObjetoDeColecao(colecaoPesquisa);
                    inserirContratoArrecadadorActionForm.setIdCliente(String
                            .valueOf(objetoCliente.getId()));
                    inserirContratoArrecadadorActionForm
                            .setNomeCliente(objetoCliente
                                    .getDescricao());
                    httpServletRequest.setAttribute("existeCliente",
                            "valor");
                    
                    httpServletRequest.setAttribute("nomeCampo", "clienteID");
                }
                break;
                default:

                    break;
                }
    		}
		
		
		// Verificar se o número do cliente não está cadastrado
		if (idCliente != null && !idCliente.trim().equals("")) {
			// Filtro para descobrir id do Cliente
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, idCliente));
            filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			Collection colecaoCliente = fachada.pesquisar(filtroCliente,Cliente.class.getName());
			if (colecaoCliente == null || colecaoCliente.isEmpty()) {
				inserirContratoArrecadadorActionForm.setIdCliente("");
				httpServletRequest.setAttribute("existeCliente", "exception");
				throw new ActionServletException("atencao.cliente.inexistente");
			} else {
				Cliente cliente = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
                 //[FS0004]-Verificar se pessoa física
                if(cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica() != null && 
                        cliente.getClienteTipo().getIndicadorPessoaFisicaJuridica().equals(new Short("2"))){
                    throw new ActionServletException("atencao.cliente_arrecadador_pessoa_fisica");
                }
                
				inserirContratoArrecadadorActionForm.setIdCliente(cliente.getId().toString());
				httpServletRequest.setAttribute("nomeCampo", "idCliente");
			}
		}

		/**
		 * Inserir Arrecadador Contrato Tarifa
		 * @date 28/05/09
		 * @author Arthur Carvalho
		 */
		if (inserirContratoArrecadadorActionForm.getFormaDeArrecadacao() == null
				|| inserirContratoArrecadadorActionForm.getFormaDeArrecadacao().equals("")) {

			FiltroArrecadacaoForma filtroArrecadadorForma = new FiltroArrecadacaoForma();
			filtroArrecadadorForma.setCampoOrderBy(FiltroArrecadacaoForma.CODIGO);
			
			Collection colecaoArrecadacaoForma = fachada.pesquisar( filtroArrecadadorForma,
					ArrecadacaoForma.class.getName() );

			if (colecaoArrecadacaoForma == null || colecaoArrecadacaoForma.isEmpty()) {
				throw new ActionServletException(

				"atencao.pesquisa.nenhum_registro_tabela", null, "Forma de Arrecadação");

			} else {

				sessao.setAttribute("colecaoFormaArrecadacao", colecaoArrecadacaoForma);
			}
		}
		
		
		ArrayList colecaoArrecadadorContratoTarifaSelecionados ;
		ArrecadadorContratoTarifa arrecadadorContratoTarifa = new ArrecadadorContratoTarifa();
		
		//Forma de Arrecadacao
		if (inserirContratoArrecadadorActionForm.getFormaDeArrecadacao() != null
				&& !"-1".equals( inserirContratoArrecadadorActionForm.getFormaDeArrecadacao() ) ) {
			
			FiltroArrecadacaoForma filtroArrecadadorForma = new FiltroArrecadacaoForma();
			filtroArrecadadorForma.adicionarParametro(new ParametroSimples(FiltroArrecadacaoForma.CODIGO, inserirContratoArrecadadorActionForm.getFormaDeArrecadacao()));
			
			Collection colecaoArrecadacaoForma = fachada.pesquisar( filtroArrecadadorForma,
					ArrecadacaoForma.class.getName() );
			
			if (colecaoArrecadacaoForma != null && !colecaoArrecadacaoForma.isEmpty()) {
				ArrecadacaoForma arrecadacaoForma = (ArrecadacaoForma) Util.retonarObjetoDeColecao(colecaoArrecadacaoForma);
				arrecadadorContratoTarifa.setArrecadacaoForma(arrecadacaoForma);
			}
			 
			
		}
		
		//Valor Tarifa
		BigDecimal valorTarifa = null;
		if (inserirContratoArrecadadorActionForm.getValorTarifa() != null
				&& !"".equals( inserirContratoArrecadadorActionForm.getValorTarifa() ) ) {
			valorTarifa =  Util.formatarMoedaRealparaBigDecimal( inserirContratoArrecadadorActionForm.getValorTarifa() ) ;
			
			arrecadadorContratoTarifa.setValorTarifa( valorTarifa );
		}
		
		//Valor Tarifa Percentual
		BigDecimal valorTarifaPercentual = null;
		if ( inserirContratoArrecadadorActionForm.getValorTarifaPercentual() != null &&
				!inserirContratoArrecadadorActionForm.getValorTarifaPercentual().equals("")){
			
			valorTarifaPercentual = Util.formatarMoedaRealparaBigDecimal(inserirContratoArrecadadorActionForm.getValorTarifaPercentual());
			arrecadadorContratoTarifa.setValorTarifaPercentual(valorTarifaPercentual);
			
		}
		
		//Numero de dias Float
		Short nmDiasFloat = null;
		if (inserirContratoArrecadadorActionForm.getNumeroDiaFloat() != null
				&& !"".equals( inserirContratoArrecadadorActionForm.getNumeroDiaFloat() ) ) {
			nmDiasFloat =  new Short( inserirContratoArrecadadorActionForm.getNumeroDiaFloat() ) ;
			arrecadadorContratoTarifa.setNumeroDiaFloat(nmDiasFloat);
		}
		
		//Adiciona o Arrecadador Contrato Tarifa a Colecao
        if (sessao.getAttribute("colecaoArrecadadorContratoTarifaSelecionados") != null){
        	
        	colecaoArrecadadorContratoTarifaSelecionados = (ArrayList) sessao
            		.getAttribute("colecaoArrecadadorContratoTarifaSelecionados");
        }else{
        	colecaoArrecadadorContratoTarifaSelecionados = new ArrayList();
        }
        
        //Verifica se a Data Final é maior que a Inicial
        if ( inserirContratoArrecadadorActionForm.getDtFimContrato() != null && 
        		!inserirContratoArrecadadorActionForm.getDtFimContrato().equals("")
        		&& inserirContratoArrecadadorActionForm.getDtInicioContrato() != null 
        		&& !inserirContratoArrecadadorActionForm.getDtInicioContrato().equals("")){
        	
        	Date dtInicial = Util.converteStringParaDate(inserirContratoArrecadadorActionForm.getDtInicioContrato());
        	Date dtFinal = Util.converteStringParaDate(inserirContratoArrecadadorActionForm.getDtFimContrato());
        	
        	if (Util.compararData(dtFinal, dtInicial)== -1){
        		
        		throw new ActionServletException("atencao.data.intervalo.invalido", null ,"Data Invalida" );
        		
        	}
        	
        }
        
        //Verifica se o usuario clicou no botao adicionar
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("adicionar") &&
        	!inserirContratoArrecadadorActionForm.getFormaDeArrecadacao().equals("-1") && 
        	( !inserirContratoArrecadadorActionForm.getValorTarifa().equals("") || 
        	!inserirContratoArrecadadorActionForm.getValorTarifaPercentual().equals("") ) 
        	&& !inserirContratoArrecadadorActionForm.getNumeroDiaFloat().equals("") ) {
        	
	        	arrecadadorContratoTarifa.setUltimaAlteracao(new Date());
	 
	        	Iterator iteratorColecaoArrecadadorContratoTarifa = colecaoArrecadadorContratoTarifaSelecionados.iterator();
	    		ArrecadadorContratoTarifa contratoTarifa = null;
	    		
	    		if ( inserirContratoArrecadadorActionForm.getValorTarifaPercentual() != null &&
	    				!inserirContratoArrecadadorActionForm.getValorTarifaPercentual().equals("")){
	    			
		    		//Validação do valor da tarifa percentual
		    		BigDecimal valorTarifaPerc = Util.formatarMoedaRealparaBigDecimal
		    			(inserirContratoArrecadadorActionForm.getValorTarifaPercentual());
		    		//Variaveis para comparar valorTarifaPercentual
		    		BigDecimal igualZero = new BigDecimal(0);
		    		BigDecimal maiorQue100 = new BigDecimal(100);
		    		if (valorTarifaPerc.compareTo(igualZero) == 0){
		    			
		    			throw new ActionServletException("atencao.tarifa_invalida", null ,"Tarifa de Contrato" );
		    			
		    		}else if ( valorTarifaPerc.compareTo(maiorQue100) == 1){
		    			
		    			throw new ActionServletException("atencao.tarifa_invalida", null ,"Tarifa de Contrato" );
		    			
		    		}
	    			
	    		}
	
	    		//Valida se ja existe forma de arrecadacao
	    		while (iteratorColecaoArrecadadorContratoTarifa.hasNext()) {
	    			
	    			contratoTarifa = (ArrecadadorContratoTarifa) iteratorColecaoArrecadadorContratoTarifa.next();
	    			
	    			if ( arrecadadorContratoTarifa.getArrecadacaoForma().getId().intValue() == contratoTarifa.getArrecadacaoForma().getId().intValue() ) {
	    				throw new ActionServletException("atencao.forma_ja_cadastrada", null ,"Tarifa de Contrato" );
	    			} 
	        	
	    		}
	        	
				colecaoArrecadadorContratoTarifaSelecionados.add(arrecadadorContratoTarifa);
				
				inserirContratoArrecadadorActionForm.setTamanhoColecao("" + colecaoArrecadadorContratoTarifaSelecionados.size());
	    		inserirContratoArrecadadorActionForm.setNumeroDiaFloat("");
	    		inserirContratoArrecadadorActionForm.setValorTarifa("");
	    		inserirContratoArrecadadorActionForm.setValorTarifaPercentual("");
	    		inserirContratoArrecadadorActionForm.setFormaDeArrecadacao("-1");
        }
        
        //Remover o Contrato Tarifa da Colecao
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("remover") ) {
        	int obj = new Integer(httpServletRequest.getParameter("id")).intValue();
        	
        	if (colecaoArrecadadorContratoTarifaSelecionados.size() >= obj) {
        		colecaoArrecadadorContratoTarifaSelecionados.remove(obj-1);
        	}
        	
        }
        
        //Limpar Form
        if ( httpServletRequest.getParameter("acao") != null && 
        	httpServletRequest.getParameter("acao").equals("limparForm") ) {
        	
        	colecaoArrecadadorContratoTarifaSelecionados.removeAll(colecaoArrecadadorContratoTarifaSelecionados);
        	
        }
        
        sessao.setAttribute("colecaoArrecadadorContratoTarifaSelecionados", colecaoArrecadadorContratoTarifaSelecionados );
		
		inserirContratoArrecadadorActionForm.setFormaDeArrecadacao("-1");
		
		inserirContratoArrecadadorActionForm.setTamanhoMaximoIdentificacaoImovel("8");
		
		return retorno;
	}
}
