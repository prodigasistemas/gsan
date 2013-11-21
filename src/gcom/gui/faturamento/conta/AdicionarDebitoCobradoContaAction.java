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
package gcom.gui.faturamento.conta;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ParametroSimples;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.debito.DebitoCobrado;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;

import gcom.cadastro.sistemaparametro.SistemaParametro;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import gcom.util.ConstantesSistema;
import gcom.util.Util;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AdicionarDebitoCobradoContaAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirAdicionarDebitoCobradoConta");

        Fachada fachada = Fachada.getInstancia();
        
        //Mudar isso quando tiver esquema de segurança
        HttpSession sessao = httpServletRequest.getSession(false);

        //Instância do formulário que está sendo utilizado
        AdicionarDebitoCobradoContaActionForm adicionarDebitoCobradoContaActionForm = 
        (AdicionarDebitoCobradoContaActionForm) actionForm;
        
        //Parãmetros recebidos para adicionar um débito cobrado
        String debitoTipoID = adicionarDebitoCobradoContaActionForm.getDebitoTipoID();
        String mesAnoDebito = adicionarDebitoCobradoContaActionForm.getMesAnoDebito();
        String mesAnoCobranca = adicionarDebitoCobradoContaActionForm.getMesAnoCobranca();
        String valorDebito = adicionarDebitoCobradoContaActionForm.getValorDebito();
        String idImovelSeleted = adicionarDebitoCobradoContaActionForm.getImovelID();
        
        
        //Gerando o objeto DebitoCobrado que será inserido na coleção
        DebitoCobrado objDebitoCobrado = new DebitoCobrado();
        objDebitoCobrado.setUltimaAlteracao(new Date());
        
        //Verificação da matrícula do imóvel selecionado
        if (idImovelSeleted == null || idImovelSeleted.equalsIgnoreCase("")){
        	throw new ActionServletException(
                    "atencao.adicionar_debito_imovel_obrigatorio");
        }
        
        
        //Verificação do mês e ano de débito
        if (mesAnoDebito != null && !mesAnoDebito.equalsIgnoreCase("")){
        	
        	//[FS0002] - Validar ano e mês de referência
        	if (Util.validarAnoMes(mesAnoDebito)){
        		throw new ActionServletException(
                "atencao.adicionar_debito_ano_mes_referencia_invalido");
        	}
        	//[FS0004] - Verifica ano e mês do faturamento
        	
        	//Invertendo para AnoMês e retirando a barra
            mesAnoDebito = Util.formatarMesAnoParaAnoMesSemBarra(mesAnoDebito);
        	
    		FiltroImovel filtroImovel = new FiltroImovel();
        	
        	//Objetos que serão retornados pelo hibernate
        	//filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo.anoMesReferencia");
            filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");
            
        	filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, idImovelSeleted));
        	
        	Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
        	
        	if (colecaoImovel == null || colecaoImovel.isEmpty()){
        		throw new ActionServletException(
                "atencao.adicionar_debito_ano_mes_debito_invalido");
        	}
    		Imovel objImovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);
    		Integer mesAnoFaturamentoImovel = objImovel.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia();
    	
    		if (!compararAnoMesReferencia(mesAnoFaturamentoImovel, new Integer(mesAnoDebito), ">")){
    			throw new ActionServletException(
                "atencao.adicionar_debito_ano_mes_debito_invalido");
    		}
    			//Inserir o mesAnoReferencia do débito no objeto final
    		objDebitoCobrado.setAnoMesReferenciaDebito(new Integer(mesAnoDebito));
        	
        }
        
        
        
        
        //Verificação do mês e ano de cobrança
        if (mesAnoCobranca != null && !mesAnoCobranca.equalsIgnoreCase("")){
        	
        	//[FS0002] - Validar ano e mês de referência
        	if (Util.validarAnoMes(mesAnoCobranca)){
        		throw new ActionServletException(
                "atencao.adicionar_debito_ano_mes_referencia_invalido");
        	}
        	//[FS0011] - Verifica ano e mês da cobrança
        
        	
        	//Invertendo para AnoMês e retirando a barra
        	mesAnoCobranca = Util.formatarMesAnoParaAnoMesSemBarra(mesAnoCobranca);
    		
        	SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
        	
        	if (sistemaParametro == null){
        		throw new ActionServletException(
                "atencao.adicionar_debito_ano_mes_cobranca_invalido");
        	}
        	else if (compararAnoMesReferencia(new Integer(mesAnoCobranca), new Integer(sistemaParametro.getAnoMesArrecadacao()), ">")){
        		throw new ActionServletException(
                "atencao.adicionar_debito_ano_mes_faturamento_invalido");
        	}
        	else{
    			//Inserir o mesAnoCobranca do débito no objeto final
    			objDebitoCobrado.setAnoMesCobrancaDebito(new Integer(mesAnoCobranca));
    		}
        	
        }
        
        
        //[FS0012] - Verificar valor do débito cobrado igual a zero
        BigDecimal objValorDebito = Util.formatarMoedaRealparaBigDecimal(valorDebito);
        if (objValorDebito.equals(new BigDecimal("0"))){
        	throw new ActionServletException(
            "atencao.adicionar_debito_valor_debito_igual_zero");
        }
    	//Inserir o valor do débito no objeto final
		objDebitoCobrado.setValorPrestacao(objValorDebito);        
        
        //Realizando consulta para obter os dados do tipo do débito selecionado
        FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        
        filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
    	
        filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID,
        		debitoTipoID));
        
        filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.INDICADOR_USO,
    			ConstantesSistema.INDICADOR_USO_ATIVO));
    
    	Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
    		DebitoTipo.class.getName());
    
    	if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()){
    	throw new ActionServletException(
                "atencao.pesquisa.nenhum_registro_tabela", null,
                "DEBITO_TIPO");
    	}
		DebitoTipo objDebitoTipo = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
		//Inserindo o tipo do débito selecionado
		objDebitoCobrado.setDebitoTipo(objDebitoTipo);
    	
    	
    	//Número de prestações
    	objDebitoCobrado.setNumeroPrestacao(new Short("1").shortValue());
    	
    	//Número da prestação 
    	objDebitoCobrado.setNumeroPrestacaoDebito(new Short("1").shortValue());
    	
    	
    	//Colocando o objeto gerado na coleção que ficará na sessão
        if (sessao.getAttribute("colecaoDebitoCobrado") == null){
        	Collection colecaoDebitoCobrado = new Vector();
        	colecaoDebitoCobrado.add(objDebitoCobrado);
        	sessao.setAttribute("colecaoDebitoCobrado", colecaoDebitoCobrado);
        	
        	httpServletRequest.setAttribute("reloadPage", "OK");
        	
        	//Definindo o caso de uso que receberá o retorno
        	if (sessao.getAttribute("UseCase").equals("INSERIRCONTA")){
        		httpServletRequest.setAttribute("reloadPageURL", "INSERIRCONTA");
        	}
        	else{
        		httpServletRequest.setAttribute("reloadPageURL", "MANTERCONTA");
        	}
        	
        }
        else{
        	Collection colecaoDebitoCobrado = (Collection) sessao.getAttribute("colecaoDebitoCobrado");
        	//[FS0014] - Verificar débito já existente
        	if (!verificarDebitoJaExistente(colecaoDebitoCobrado, objDebitoCobrado)){
        		colecaoDebitoCobrado.add(objDebitoCobrado);
        		
        		httpServletRequest.setAttribute("reloadPage", "OK");
        	
        		//Definindo o caso de uso que receberá o retorno
            	if (sessao.getAttribute("UseCase").equals("INSERIRCONTA")){
            		httpServletRequest.setAttribute("reloadPageURL", "INSERIRCONTA");
            	}
            	else{
            		httpServletRequest.setAttribute("reloadPageURL", "MANTERCONTA");
            	}
        	}
        	else{
        		throw new ActionServletException(
                "atencao.adicionar_debito_ja_existente");
        	}
        }
        
        //Limpando o formulário para inserir um novo débito
        adicionarDebitoCobradoContaActionForm.setDebitoTipoID("");
        adicionarDebitoCobradoContaActionForm.setMesAnoCobranca("");
        adicionarDebitoCobradoContaActionForm.setMesAnoDebito("");
        adicionarDebitoCobradoContaActionForm.setValorDebito("");
        
        
        /*
		 * Colocado por Raphael Rossiter em 29/03/2007
		 * Objetivo: Manipulação dos objetos que serão exibidos no formulário de acordo com a empresa
		 */
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		httpServletRequest.setAttribute("empresaNome", sistemaParametro.getNomeAbreviadoEmpresa().trim());
        
        
        return retorno;
    }
    
    
    /**
     * Compara dois objetos no formato anoMesReferencia de acordo com o sinal logico passado.
     * @param anoMesReferencia1
     * @param anoMesReferencia2
     * @param sinal
     * @return um boleano
     */
    private boolean compararAnoMesReferencia(Integer anoMesReferencia1, Integer anoMesReferencia2, String sinal){
    	boolean retorno = true;
    	
    	//Separando os valores de mês e ano para realizar a comparação
    	String mesReferencia1 = String.valueOf(anoMesReferencia1.intValue()).substring(4, 6);
        String anoReferencia1 = String.valueOf(anoMesReferencia1.intValue()).substring(0, 4);
        
        String mesReferencia2 = String.valueOf(anoMesReferencia2.intValue()).substring(4, 6);
        String anoReferencia2 = String.valueOf(anoMesReferencia2.intValue()).substring(0, 4);
        
        if (sinal.equalsIgnoreCase("=")){
        	if (!Integer.valueOf(anoReferencia1).equals(Integer.valueOf(anoReferencia2))){
        		retorno = false;
        	}
        	else if (!Integer.valueOf(mesReferencia1).equals(Integer.valueOf(mesReferencia2))){
        		retorno = false;
        	}
        }
        else if (sinal.equalsIgnoreCase(">")){
        	if (Integer.valueOf(anoReferencia1).intValue() < Integer.valueOf(anoReferencia2).intValue()){
        		retorno = false;
        	}
        	else if (Integer.valueOf(anoReferencia1).equals(Integer.valueOf(anoReferencia2)) &&
        			 Integer.valueOf(mesReferencia1).intValue() <= Integer.valueOf(mesReferencia2).intValue()){
        		retorno = false;
        	}
        }
        else{
        	if (Integer.valueOf(anoReferencia2).intValue() < Integer.valueOf(anoReferencia1).intValue()){
        		retorno = false;
        	}
        	else if (Integer.valueOf(anoReferencia2).equals(Integer.valueOf(anoReferencia1)) &&
        			 Integer.valueOf(mesReferencia2).intValue() <= Integer.valueOf(mesReferencia1).intValue()){
        		retorno = false;
        	}
        }

    	
    	return retorno;
    }
    
    
    /**
     * [FS0014] - Caso o tipo do débito selecionado já esteja na lista
     * @param colecaoDebitoCobrado
     * @param debitoCobradoInsert
     * @return um boleano
     */
    private boolean verificarDebitoJaExistente(Collection colecaoDebitoCobrado, DebitoCobrado debitoCobradoInsert){
    	boolean retorno = false;
    	
    	Iterator colecaoDebitoCobradoIt = colecaoDebitoCobrado.iterator();
    	DebitoCobrado debitoCobradoColecao;
    	
    	while (colecaoDebitoCobradoIt.hasNext()){
    		debitoCobradoColecao = (DebitoCobrado) colecaoDebitoCobradoIt.next();
    		if (debitoCobradoColecao.getDebitoTipo().getId().equals(debitoCobradoInsert.getDebitoTipo().getId())){
    			retorno = true;
    			break;
    		}
    	}
    	
    	return retorno;
    }

}

