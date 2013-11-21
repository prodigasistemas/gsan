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
package gcom.gui.arrecadacao.banco;

import gcom.arrecadacao.ArrecadacaoForma;
import gcom.arrecadacao.DeducaoTipo;
import gcom.arrecadacao.FiltroArrecadacaoForma;
import gcom.arrecadacao.FiltroAvisoAcerto;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.FiltroAvisoDeducoes;
import gcom.arrecadacao.FiltroDeducaoTipo;
import gcom.arrecadacao.aviso.AvisoAcerto;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.aviso.AvisoDeducoes;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Action de exibir atulizar o aviso bancario
 *
 * @author thiago toscano
 * @date 10/03/2006
 */
public class ExibirAtualizarAvisoBancarioAction  extends GcomAction {
	
    /**
     * Método responsavel por responder a requisicao
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

        ActionForward retorno = actionMapping.findForward("exibir");

        AvisoBancarioActionForm form = (AvisoBancarioActionForm) actionForm;

        HttpSession sessao = httpServletRequest.getSession(false);
        
        // pegando a acao a ser executada
        String acao = form.getAcao();
        
        //preencherObjeto ( form, httpServletRequest);
        
        String idAvisoBancario = form.getIdAvisoBancario();
        
        Map<String, String[]> requestMap = httpServletRequest.getParameterMap();
        
        sessao.removeAttribute("filtrar_manter");
        
        // Carregar a data corrente do sistema
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();

		// Data Corrente
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));

        
        if (idAvisoBancario == null || idAvisoBancario.equalsIgnoreCase("")){
			if (httpServletRequest.getAttribute("idAvisoBancario") == null){
				idAvisoBancario = (String) sessao.getAttribute("idAvisoBancario");
			}else{
				idAvisoBancario = httpServletRequest.getAttribute("idAvisoBancario").toString();
			}
			
		} else {
			sessao.setAttribute("i", true);
		}
        
		// Pesquisar ArrecadacaoForma
        FiltroArrecadacaoForma filtroArrecadacaoForma = new FiltroArrecadacaoForma();
        filtroArrecadacaoForma.setConsultaSemLimites(true);
        filtroArrecadacaoForma.setCampoOrderBy(FiltroArrecadacaoForma.DESCRICAO);
        Collection collectionArrecadacaoForma = Fachada.getInstancia().pesquisar(filtroArrecadacaoForma, ArrecadacaoForma.class.getName());
        httpServletRequest.setAttribute("collectionArrecadacaoForma", collectionArrecadacaoForma);
        
		FiltroServicoTipo filtroTipo = new FiltroServicoTipo();
		filtroTipo.adicionarParametro(new ParametroSimples(
				FiltroServicoTipo.INDICADORTERCEIRIZADO, 1));
		filtroTipo.setConsultaSemLimites(true);
		filtroTipo.setCampoOrderBy(FiltroServicoTipo.DESCRICAO);
			
		sessao.setAttribute("idAvisoBancario", idAvisoBancario);
		String valorDeducao = form.getValorDeducao();
        if ("adicionarDeducao".equals(acao)) {
        	
        // adiciona a deducao	
        	String idTipoDeducao = form.getIdTipoDeducao();
        	//boolean jaTemDeducao = false;
        	Collection collAvisoDeducoes = (Collection)sessao.getAttribute("avisoDeducoes");
        	Iterator it = collAvisoDeducoes.iterator();
        	while (it.hasNext()) {
        		AvisoDeducoes avisoDeducoes = (AvisoDeducoes) it.next();
        		if (avisoDeducoes.getDeducaoTipo().getId().toString().equals(idTipoDeducao)){
        			throw new ActionServletException("atencao.avisoBancario.tipo_deducao_ja_informada");
        		}
        	}

        	FiltroDeducaoTipo filtroDeducaoTipo = new FiltroDeducaoTipo();
        	filtroDeducaoTipo.adicionarParametro(new ParametroSimples(FiltroDeducaoTipo.ID,idTipoDeducao));
        	Collection coll = Fachada.getInstancia().pesquisar(filtroDeducaoTipo, DeducaoTipo.class.getSimpleName());

        	AvisoDeducoes avisoDeducoes = new AvisoDeducoes();
        	avisoDeducoes.setDeducaoTipo((DeducaoTipo)coll.iterator().next());
        	avisoDeducoes.setValorDeducao(Util.formatarMoedaRealparaBigDecimal(valorDeducao));
        	avisoDeducoes.setUltimaAlteracao(new Date(System.currentTimeMillis()));

        	if(avisoDeducoes.getValorDeducao().compareTo(Util.formatarMoedaRealparaBigDecimal(form.getValorArrecadacao())) == 1)
    		{
    			throw new ActionServletException("atencao.valor.deducao.maior.valor.arrecadacao");
    		}  
        	
        	collAvisoDeducoes.add(avisoDeducoes);

        } else if ("removerDeducao".equals(acao)) {
        // remover a deducao

        	if (sessao.getAttribute("avisoDeducoes") != null && 
            		sessao.getAttribute("avisoDeducoes") instanceof Collection &&
            		!((Collection)sessao.getAttribute("avisoDeducoes")).isEmpty()) {
            		
            		int posicao = -1;
            	    try {
            	    	// pegando a posicao a ser removida
            	    	posicao = Integer.parseInt(form.getPosicao());

	            	    Collection avisoNovo = new Vector();
	            	    Collection avisoRemover = null;
	            	    // pega a colecao de objetos a ser removidos. 
	            	    // Caso nao tenha a colecao entao cria e coloca na sessao 	
	            	    if (sessao.getAttribute("avisoDeducoesRemover") != null) {
	            	    	avisoRemover = (Collection) sessao.getAttribute("avisoDeducoesRemover");
	            	    } else {
	            	    	avisoRemover = new Vector();
	            	    	sessao.setAttribute("avisoDeducoesRemover",avisoRemover);
	            	    }
	            	    
	            	    // para todos os collecao deducoes
	            	    Collection coll = (Collection)sessao.getAttribute("avisoDeducoes");
	            	    Iterator it = coll.iterator();
	            	    int count = 0;
	            	    while (it.hasNext()) {
	            	    	AvisoDeducoes avisoDeducoes = (AvisoDeducoes) it.next(); 
	            	    	if (count == posicao ) {
	            	    		if (avisoDeducoes.getComp_id() != null) 
	            	    			avisoRemover.add(avisoDeducoes);
	            	    	} else {
	            	    		avisoNovo.add(avisoDeducoes);
	            	    	}
	            	    	count++;
	            	    }
	            	    sessao.setAttribute("avisoDeducoes",avisoNovo);
	            	    sessao.setAttribute("avisoDeducoesRemover",avisoRemover);
            	    } catch (Exception e) {}
            	    // nao faz nada pois a posicao nao foi passada
        		}
        } else  if ("adicionarAcerto".equals(acao)) {

        	String idContaBancaria = form.getIdContaBancaria();
        	String tipoAcesso = form.getTipoAcesso();
        	String acessar = form.getAcerto();
        	String dataAcerto = form.getDataAcerto();
        	String valorAcerto = form.getValorAcerto();

			Date data = Util.converteStringParaDate(form.getDataRealizacao());
			if (data == null) {
				throw new ActionServletException("atencao.avisoBancario.data_realizacao_acerto_invalida");
			}
			if (Util.converteStringParaDate(dataAcerto).getTime() > new Date(System.currentTimeMillis()).getTime()) {
				throw new ActionServletException("atencao.avisoBancario.data_realizacao_acerto_menor_que_hoje",null,Util.formatarData(new Date(System.currentTimeMillis())));
			}
			if (Util.converteStringParaDate(dataAcerto).getTime() < data.getTime()) {
				throw new ActionServletException("atencao.avisoBancario.data_realizacao_acerto_menor_que_data_realizacao",Util.formatarData(Util.converteStringParaDate(dataAcerto)), Util.formatarData(data));
			}
        	FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();
        	filtroContaBancaria.adicionarParametro(new ParametroSimples(FiltroContaBancaria.ID,idContaBancaria));
        	filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia");
        	filtroContaBancaria.adicionarCaminhoParaCarregamentoEntidade("agencia.banco");
        	Collection coll = Fachada.getInstancia().pesquisar(filtroContaBancaria, ContaBancaria.class.getSimpleName());

        	AvisoAcerto avisoAcerto = new AvisoAcerto();
        	avisoAcerto.setContaBancaria((ContaBancaria)coll.iterator().next());
        	avisoAcerto.setDataAcerto(Util.converteStringParaDate(dataAcerto));
        	avisoAcerto.setIndicadorCreditoDebito(new Integer(tipoAcesso));
        	avisoAcerto.setIndicadorArrecadacaoDevolucao(new Integer(acessar));
        	avisoAcerto.setUltimaAlteracao(new Date(System.currentTimeMillis()));
        	avisoAcerto.setValorAcerto(Util.formatarMoedaRealparaBigDecimal(valorAcerto));

        	coll = (Collection) sessao.getAttribute("avisoAcerto");
        	coll.add(avisoAcerto);

        } else if ("removerAcerto".equals(acao)) {
        // caso a acao seja para remover um acerto	

        	if (sessao.getAttribute("avisoAcerto") != null && 
            		sessao.getAttribute("avisoAcerto") instanceof Collection &&
            		!((Collection)sessao.getAttribute("avisoAcerto")).isEmpty()) {
            		
            		int posicao = -1;
            	    try {
            	    	// pegando a posicao a ser removida
            	    	posicao = Integer.parseInt(form.getPosicao());

	            	    Collection avisoNovo = new Vector();
	            	    Collection avisoRemover = null;
	            	    // pega a colecao de objetos a ser removidos. 
	            	    // Caso nao tenha a colecao entao cria e coloca na sessao 	
	            	    if (sessao.getAttribute("avisoAcertoRemover") != null) {
	            	    	avisoRemover = (Collection) sessao.getAttribute("avisoAcertoRemover");
	            	    } else {
	            	    	avisoRemover = new Vector();	            	    	
	            	    }

	            	    // para todos os collecao deducoes
	            	    Collection coll = (Collection)sessao.getAttribute("avisoAcerto");
	            	    Iterator it = coll.iterator();
	            	    int count = 0;
	            	    while (it.hasNext()) {
	            	    	AvisoAcerto avisoAcerto = (AvisoAcerto) it.next();
	            	    	
	            	    	if (count == posicao ) {
	            	    		if (avisoAcerto.getId() != null) 
	            	    			avisoRemover.add(avisoAcerto);
	            	    	} else {
	            	    		avisoNovo.add(avisoAcerto);
	            	    	}
	            	    	count++;
	            	    }
	            	    sessao.setAttribute("avisoAcerto",avisoNovo);
	            	    sessao.setAttribute("avisoAcertoRemover",avisoRemover);
            	    } catch (Exception e) {}
            	    // nao faz nada pois a posicao nao foi passada
        		}
        } else if (!"calcular".equals(acao)) {
            sessao.removeAttribute("avisoBancario");
            sessao.removeAttribute("avisoDeducoes");
            sessao.removeAttribute("avisoAcerto");
        
	        FiltroAvisoBancario filtroAvisoBancario = new FiltroAvisoBancario();
	        filtroAvisoBancario.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, idAvisoBancario));
	        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR);
	        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADADOR_CLIENTE);
	        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.CONTA_BANCARIA);
	        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.AGENCIA);
	        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.BANCO);
	        filtroAvisoBancario.adicionarCaminhoParaCarregamentoEntidade(FiltroAvisoBancario.ARRECADACAO_FORMA);
	
	        Collection coll = Fachada.getInstancia().pesquisar(filtroAvisoBancario,AvisoBancario.class.getName());
	        if (coll != null && !coll.isEmpty()) {
	        	AvisoBancario avisoBancario = (AvisoBancario) coll.iterator().next();
	        	
	        	if (avisoBancario.getDataRealizada() == null) {
	        		String texto = avisoBancario.getArrecadador().getCliente().getNome() + " - " +
	        						Util.formatarData(avisoBancario.getDataLancamento())  + " - " +
	        						avisoBancario.getNumeroSequencial();
	        		throw new ActionServletException("atencao.avisoBancario.nao_realizado",null,texto);
	        	}
	        	if (avisoBancario.getDataLancamento() != null) {
	        		sessao.setAttribute("dataLancamento",avisoBancario.getDataLancamento());
	        	}
	        	if (avisoBancario.getValorArrecadacaoInformado() != null) {
	        		form.setValorArrecadacao(Util.formatarMoedaReal(avisoBancario.getValorArrecadacaoInformado()));
	        	}
	        	if (avisoBancario.getValorDevolucaoInformado() != null) {
	        		form.setValorDevolucao(Util.formatarMoedaReal(avisoBancario.getValorDevolucaoInformado()));
	        	}
	        	sessao.setAttribute("avisoBancario",avisoBancario);
	        	
	        	if (avisoBancario.getArrecadacaoForma() != null) {
	        		form.setIdFormaArrecadacao("" + avisoBancario.getArrecadacaoForma().getId());
	        	}
	        	
	            FiltroAvisoDeducoes filtroAvisoDeducoes = new FiltroAvisoDeducoes();
	            filtroAvisoDeducoes.adicionarCaminhoParaCarregamentoEntidade("deducaoTipo");
	            filtroAvisoDeducoes.adicionarCaminhoParaCarregamentoEntidade("comp_id");
	            filtroAvisoDeducoes.adicionarParametro(new ParametroSimples("avisoBancario.id",idAvisoBancario));
	            coll = Fachada.getInstancia().pesquisar(filtroAvisoDeducoes,AvisoDeducoes.class.getName());
	            sessao.setAttribute("avisoDeducoes",coll);
	        	
	            FiltroAvisoAcerto filtroAvisoAcerto = new FiltroAvisoAcerto();
	            filtroAvisoAcerto.adicionarParametro(new ParametroSimples("avisoBancario.id",idAvisoBancario));
	            filtroAvisoAcerto.adicionarCaminhoParaCarregamentoEntidade("contaBancaria");
	            filtroAvisoAcerto.adicionarCaminhoParaCarregamentoEntidade("contaBancaria.agencia");
	            filtroAvisoAcerto.adicionarCaminhoParaCarregamentoEntidade("contaBancaria.agencia.banco");
	            coll = Fachada.getInstancia().pesquisar(filtroAvisoAcerto, AvisoAcerto.class.getName());
	            sessao.setAttribute("avisoAcerto",coll);
	        }
        }
        BigDecimal valorDeducao2 = new BigDecimal("0.00");
        if (sessao.getAttribute("avisoDeducoes") != null) {
//        	if(valorDeducao != null && !valorDeducao.equalsIgnoreCase("")){
//        		valorDeducao2 = Util.formatarMoedaRealparaBigDecimal(valorDeducao);
//        	}
        	 
			Collection colecaoAvisoDeducao = (Collection)sessao.getAttribute("avisoDeducoes");

			Iterator colecaoAvisoDeducaoIterator = colecaoAvisoDeducao
					.iterator();
			
			String valor = null;
        	BigDecimal valor2 = new BigDecimal ("0.00"); 
        	int i = 0;
        	
			while (colecaoAvisoDeducaoIterator.hasNext()) {
				AvisoDeducoes avisoDeducoesCalculo = (AvisoDeducoes) colecaoAvisoDeducaoIterator
						.next();

				// valor minimo
    			if (requestMap.get("posicaoAvisoDeducao_"+ i) != null){

    				valor = (requestMap.get("posicaoAvisoDeducao_" + i))[0];
    				
    				if ( Util.verificarNaoVazio(valor)) {
    					valor2 = Util.formatarMoedaRealparaBigDecimal(valor);
    					if(valor2.compareTo(Util.formatarMoedaRealparaBigDecimal(form.getValorArrecadacao())) == 1)
    		    		{
    		    			throw new ActionServletException("atencao.valor.deducao.maior.valor.arrecadacao");
    		    		}
    				}
    				avisoDeducoesCalculo.setValorDeducao(valor2);
    				valorDeducao2 = valorDeducao2.add(valor2);
    			}else{
    				
    				valorDeducao2 = valorDeducao2.add(avisoDeducoesCalculo
    						.getValorDeducao());
    			}
				i++;
			}

			if (valorDeducao2 != null) {
				form.setValorDeducoes(Util.formatarMoedaReal(valorDeducao2));
			} else {
				form.setValorDeducoes("");
			}

		}

        
        BigDecimal valorAviso = null;

		if (form.getValorArrecadacao() != null
				&& !form.getValorArrecadacao().equals("")) {
			valorAviso = Util.formatarMoedaRealparaBigDecimal(form.getValorArrecadacao());
			
			AvisoBancario avisoBancario = (AvisoBancario) sessao.getAttribute("avisoBancario");
			
			avisoBancario.setValorArrecadacaoInformado(valorAviso);
		}

		if (form.getValorDevolucao() != null
				&& !form.getValorDevolucao().equals("")) {
			if (valorAviso == null) {
				valorAviso = new BigDecimal("0.00");
			}
			valorAviso = valorAviso.subtract(Util.formatarMoedaRealparaBigDecimal(form.getValorDevolucao()));
		}

		if (valorDeducao2 != null) {
			if (valorAviso == null) {
				valorAviso = new BigDecimal("0.00");
			}
			valorAviso = valorAviso.subtract(valorDeducao2);
		}

		if (valorAviso != null) {
			form.setValorAviso(Util.formatarMoedaReal(valorAviso));
		} else {
			form.setValorAviso("");
		}

		// caso ainda não tenha sido setado o nome campo(na primeira vez)
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", "manter");
		}
		if (form.getDataRealizacao() != null){
			sessao.setAttribute("dataRealizacao", form.getDataRealizacao());
		}
		
        return retorno;
    }

}