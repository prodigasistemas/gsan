
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

import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacaoAuxiliar;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacaoAuxiliar.GROUP_BY_AUX;
import gcom.arrecadacao.bean.FiltrarDadosDiariosArrecadacaoHelper;
import gcom.batch.FiltroFuncionalidadeIniciada;
import gcom.batch.FuncionalidadeIniciada;
import gcom.batch.FuncionalidadeSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarDadosDiariosArrecadacaoComTarifaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Collection<BigDecimal> colecaoValorTotal = new ArrayList<BigDecimal>();
		BigDecimal valorTotalPeriodo = new BigDecimal(0.0);
		
		String dataAtualAux = null;
		
        /** filtro para verificar se a funcionalidade de gerar dados diários de arrecadação esta executando */
        FiltroFuncionalidadeIniciada filtroFuncionalidadeIniciada = new FiltroFuncionalidadeIniciada();
        filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.FUNCIONALIDADE_ID,Funcionalidade.GERAR_DADOS_DIARIOS_ARRECADACAO));
        filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.FUNCIONALIDADE_SITUACAO,FuncionalidadeSituacao.EM_ESPERA, ConectorOr.CONECTOR_OR, 2));
        filtroFuncionalidadeIniciada.adicionarParametro(new ParametroSimples(FiltroFuncionalidadeIniciada.FUNCIONALIDADE_SITUACAO,FuncionalidadeSituacao.EM_PROCESSAMENTO));
        
        Collection<FuncionalidadeIniciada> colecaoFuncionalidadeEmProcessamento = fachada.pesquisar(filtroFuncionalidadeIniciada,FuncionalidadeIniciada.class.getName());
        
        /*
         * Caso a funcionalidade esteja emprocessamento ou em espera
         * envia uma mensagem ao usuário negando o acesso a consulta.  
         */
        if(colecaoFuncionalidadeEmProcessamento != null && !colecaoFuncionalidadeEmProcessamento.isEmpty()){
        	throw new ActionServletException("atencao.funcionalidade.processando");
        }


		retorno = actionMapping.findForward("consultarDadosDiariosArrecadacaoComTarifa");
		
		// Pega o formulário
		FiltrarDadosDiariosArrecadacaoComTarifaActionForm filtrarDadosDiariosArrecadacaoComTarifaActionForm = (FiltrarDadosDiariosArrecadacaoComTarifaActionForm) actionForm;

		// Recupera os parâmetros do form
		String periodoArrecadacaoInicial = filtrarDadosDiariosArrecadacaoComTarifaActionForm.getPeriodoArrecadacaoInicio();
		String periodoArrecadacaoFinal = filtrarDadosDiariosArrecadacaoComTarifaActionForm.getPeriodoArrecadacaoFim();
		String localidade = filtrarDadosDiariosArrecadacaoComTarifaActionForm.getLocalidade();
		String idArrecadador = filtrarDadosDiariosArrecadacaoComTarifaActionForm.getIdArrecadador();
		String idGerenciaRegional = filtrarDadosDiariosArrecadacaoComTarifaActionForm.getIdGerenciaRegional();
		String idElo = filtrarDadosDiariosArrecadacaoComTarifaActionForm.getIdElo();
		String[] idsDocumentosTipos = filtrarDadosDiariosArrecadacaoComTarifaActionForm.getDocumentoTipo();


		verificaPeloMenosUmParametroInformado(
				filtrarDadosDiariosArrecadacaoComTarifaActionForm,
				periodoArrecadacaoInicial, localidade, idArrecadador,
				idGerenciaRegional, idElo, idsDocumentosTipos);

		FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro = new FiltroConsultarDadosDiariosArrecadacaoAuxiliar();
		filtro.setAgrupamento(GROUP_BY_AUX.ANO_MES);
		filtro.setIdArrecadador(idArrecadador);
		filtro.setIdElo(idElo);
		filtro.setIdGerenciaRegional(idGerenciaRegional);
		filtro.setIdLocalidade(localidade);
		filtro.setIdsDocumentoTipoAgregador(idsDocumentosTipos);
		
		verificarExistenciaDadosDiariosArrecadacaoAuxiliar(fachada, 
				filtro, periodoArrecadacaoInicial, periodoArrecadacaoFinal);
		
		sessao.setAttribute("filtroConsultarDadosDiariosArrecadacaoAuxiliar", filtro);
		sessao.setAttribute("periodoArrecadacaoInicial", 
			Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoInicial));
		sessao.setAttribute("periodoArrecadacaoFinal", 
				Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoFinal));
		
		if (filtro != null){
			
			filtro = filtro.clone();
			
			Integer periodoArrecadacaoInicialInt = Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoInicial);
			Integer periodoArrecadacaoFinalInt = Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoFinal);
			
			filtro.setAgrupamento(GROUP_BY_AUX.ANO_MES);
			Map<Integer, Collection<FiltrarDadosDiariosArrecadacaoHelper>> 
			 mapDadosDiariosAnoMes = fachada.filtrarDadosDiariosArrecadacaoAuxiliar(
				periodoArrecadacaoInicialInt,
				periodoArrecadacaoFinalInt,
				filtro);
			
			Map<Integer, FiltrarDadosDiariosArrecadacaoHelper> mapDadosProcessamento = 
				new TreeMap<Integer, FiltrarDadosDiariosArrecadacaoHelper>();
			
	        for(Integer itemAnoMes : mapDadosDiariosAnoMes.keySet()){
	        
	        	BigDecimal valorTotal = new BigDecimal(0.0);
	        	
	        	for (FiltrarDadosDiariosArrecadacaoHelper helper : mapDadosDiariosAnoMes.get(itemAnoMes)){
	        		valorTotal = valorTotal.add(helper.getValorArrecadacaoLiquida());
	        	}            	
	    		colecaoValorTotal.add(valorTotal);
	    		valorTotalPeriodo = valorTotalPeriodo.add(valorTotal);
	    		
	    		Date dataMesInformado = fachada.pesquisarDataProcessamentoMes(itemAnoMes);
        		Date dataAtual = fachada.pesquisarDataProcessamentoMes(this.getSistemaParametro().getAnoMesArrecadacao());
	    		
	    		FiltrarDadosDiariosArrecadacaoHelper helperDadasProcessamento =
        			new FiltrarDadosDiariosArrecadacaoHelper();
        		
        		if(dataMesInformado!=null){
        			helperDadasProcessamento
        				.setUltimoProcessamentoMesInformado(Util.formatarDataComHora(dataMesInformado));
        		}
        		if(dataAtual!=null){
        			helperDadasProcessamento
        				.setUltimoProcessamentoAtualSistema(Util.formatarDataComHora(dataAtual));
        			
        			dataAtualAux = Util.formatarDataComHora(dataAtual);
        			
        		}
        		
        		if ( itemAnoMes >= this.getSistemaParametro().getAnoMesArrecadacao() ){
        			
//    				httpServletRequest.setAttribute("tipoProcessamento","provisorio");
        			helperDadasProcessamento.setTipoProcessamento("provisorio");
    				
    			}else{
    				
//    				httpServletRequest.setAttribute("tipoProcessamento","definitivo");
    				helperDadasProcessamento.setTipoProcessamento("definitivo");
    				
    			}
	    		
	    		mapDadosProcessamento.put(itemAnoMes, helperDadasProcessamento);
	    		
			}
	        
	        if ( dataAtualAux != null ){

		        httpServletRequest.setAttribute("dataAtual", dataAtualAux);
	        	
	        }
	        
	        httpServletRequest.setAttribute("mapDadosDiariosAnoMes", mapDadosDiariosAnoMes);
	        
	        httpServletRequest.setAttribute("colecaoValorTotal", colecaoValorTotal);
			
	        httpServletRequest.setAttribute("valorTotalPeriodo", valorTotalPeriodo);
			
		}
		
		// Devolve o mapeamento de retorno
		return retorno;
	}

	public void verificaPeloMenosUmParametroInformado(
			FiltrarDadosDiariosArrecadacaoComTarifaActionForm filtrarDadosDiariosArrecadacaoComTarifaActionForm,
			String periodoArrecadacaoInicial, String localidade,
			String idArrecadador, String idGerenciaRegional, String idElo,
			String[] idsDocumentosTipos) {
		boolean peloMenosUmParametroInformado = false;

		// Período Arrecadação
		if (periodoArrecadacaoInicial != null
				&& !periodoArrecadacaoInicial.equals("")) {
			peloMenosUmParametroInformado = true;

		}

		// Localidade
		if (localidade != null
				&& !localidade.equals("") && !localidade
				.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;

		}
		else{
			filtrarDadosDiariosArrecadacaoComTarifaActionForm.setLocalidade("");
			filtrarDadosDiariosArrecadacaoComTarifaActionForm.setDescricaoLocalidade("");
		}

		// Gerencia Regional
		if (idGerenciaRegional != null
				&& !idGerenciaRegional.equals("") && (!(idGerenciaRegional
						.equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())))

				) {
			peloMenosUmParametroInformado = true;

		}
		else{
			filtrarDadosDiariosArrecadacaoComTarifaActionForm.setIdGerenciaRegional("");
			filtrarDadosDiariosArrecadacaoComTarifaActionForm.setNomeGerenciaRegional("");
		}

		// Arrecadador
		if (idArrecadador != null
				&& !idArrecadador.equals("") && !idArrecadador
				.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;

		}
		else{
			filtrarDadosDiariosArrecadacaoComTarifaActionForm.setIdArrecadador("");
			filtrarDadosDiariosArrecadacaoComTarifaActionForm.setNomeArrecadador("");
		}


		// Elo
		if (idElo != null
				&& !idElo.equals("") && !idElo
				.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;

		}
		else{
			filtrarDadosDiariosArrecadacaoComTarifaActionForm.setIdElo("");
			filtrarDadosDiariosArrecadacaoComTarifaActionForm.setNomeElo("");
		}


		// Tipo do Documento
		int i = 0;
		i = 0;
		if (idsDocumentosTipos != null) {
			while (i < idsDocumentosTipos.length) {
				if (!idsDocumentosTipos[i].equals("")) {
					peloMenosUmParametroInformado = true;
				}
				i++;
			}
		}

		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}
	}

	public void verificarExistenciaDadosDiariosArrecadacaoAuxiliar( Fachada fachada,
			FiltroConsultarDadosDiariosArrecadacaoAuxiliar filtro,
			String periodoArrecadacaoInicial, String periodoArrecadacaoFinal) {
		
		
		boolean existeDados =
				fachada.verificarExistenciaDadosDiariosArrecadacaoAuxiliar(
						Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoInicial),
						Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoFinal), 
						filtro);
		if (!existeDados) {
			// Nenhum dados diarios de arrecadacao cadastrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
	}
}
