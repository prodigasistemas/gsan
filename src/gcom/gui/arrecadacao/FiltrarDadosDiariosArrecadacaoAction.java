
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

import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao;
import gcom.arrecadacao.FiltroConsultarDadosDiariosArrecadacao.GROUP_BY;
import gcom.batch.FiltroFuncionalidadeIniciada;
import gcom.batch.FuncionalidadeIniciada;
import gcom.batch.FuncionalidadeSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;
import gcom.seguranca.acesso.Funcionalidade;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ConectorOr;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarDadosDiariosArrecadacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = null;

		Fachada fachada = Fachada.getInstancia();
		
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

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Pega o formulário
		FiltrarDadosDiariosArrecadacaoActionForm filtrarDadosDiariosArrecadacaoActionForm = (FiltrarDadosDiariosArrecadacaoActionForm) actionForm;

		// Recupera os parâmetros do form
		String periodoArrecadacaoInicial = filtrarDadosDiariosArrecadacaoActionForm.getPeriodoArrecadacaoInicio();
		String periodoArrecadacaoFinal = filtrarDadosDiariosArrecadacaoActionForm.getPeriodoArrecadacaoFim();
		String localidade = filtrarDadosDiariosArrecadacaoActionForm.getLocalidade();
		String idArrecadador = filtrarDadosDiariosArrecadacaoActionForm.getIdArrecadador();
		String idGerenciaRegional = filtrarDadosDiariosArrecadacaoActionForm.getIdGerenciaRegional();
		String idElo = filtrarDadosDiariosArrecadacaoActionForm.getIdElo();
		String[] idsImovelPerfil = filtrarDadosDiariosArrecadacaoActionForm.getImovelPerfil();
		String[] idsLigacaoAgua = filtrarDadosDiariosArrecadacaoActionForm.getLigacaoAgua();
		String[] idsLigacaoEsgoto = filtrarDadosDiariosArrecadacaoActionForm.getLigacaoEsgoto();
		String[] idsCategoria = filtrarDadosDiariosArrecadacaoActionForm.getCategoria();
		String[] idsEsferaPoder = filtrarDadosDiariosArrecadacaoActionForm.getEsferaPoder();
		String[] idsDocumentosTipos = filtrarDadosDiariosArrecadacaoActionForm.getDocumentoTipo();

		retorno = actionMapping.findForward("consultarDadosDiariosParametros");
		
        // Monta o Status do Wizard
		StatusWizard statusWizard = new StatusWizard(
				"consultarDadosDiariosArrecadacaoWizardAction", "exibirFiltrarDadosDiariosArrecadacaoAction",
				"cancelarConsultarDadosDiariosArrecadacaoAction", 
				"exibirFiltrarDadosDiariosArrecadacaoAction",	
				"filtrarDadosDiariosArrecadacaoAction.do");

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						1, "ParametrosPrimeiraAbaA.gif", "ParametrosPrimeiraAbaD.gif",
						"exibirConsultarDadosDiariosParametrosAction",
						""));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						2, "GerenciaIntervaloAbaA.gif", "GerenciaIntervaloAbaD.gif",
						"exibirConsultarDadosDiariosGerenciaAction",
						""));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						3, "ArrecadadorIntervaloAbaA.gif", "ArrecadadorIntervaloAbaD.gif",
						"exibirConsultarDadosDiariosArrecadadorAction",
						""));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						4, "CategoriaIntervaloAbaA.gif", "CategoriaIntervaloAbaD.gif",
						"exibirConsultarDadosDiariosCategoriaAction",
						""));
		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						5, "PerfilIntervaloAbaA.gif", "PerfilIntervaloAbaD.gif",
						"exibirConsultarDadosDiariosPerfilAction",
						""));

		statusWizard
				.inserirNumeroPaginaCaminho(statusWizard.new StatusWizardItem(
						6, "DocumentoUltimaAbaA.gif", "DocumentoUltimaAbaD.gif",
						"exibirConsultarDadosDiariosDocumentoAction",
						""));
        
//		colecaoArrecadacaoDadosDiarios = fachada.filtrarDadosDiariosArrecadacao(Util
//				.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoInicial),
//				Util
//				.formatarMesAnoParaAnoMesSemBarra(periodoArrecadacaoFinal),
//				localidade,
//				idGerenciaRegional,
//				idArrecadador,
//				idElo,
//				idsImovelPerfil,
//				idsLigacaoAgua,
//				idsLigacaoEsgoto,
//				idsDocumentosTipos,
//				idsCategoria,
//				idsEsferaPoder);
		
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
			filtrarDadosDiariosArrecadacaoActionForm.setLocalidade("");
			filtrarDadosDiariosArrecadacaoActionForm.setDescricaoLocalidade("");
		}
		
		// Gerencia Regional
		if (idGerenciaRegional != null
				&& !idGerenciaRegional.equals("") && (!(idGerenciaRegional
				.equals(new Integer(ConstantesSistema.NUMERO_NAO_INFORMADO).toString())))
				
				) {
			peloMenosUmParametroInformado = true;

		}
		else{
			filtrarDadosDiariosArrecadacaoActionForm.setIdGerenciaRegional("");
			filtrarDadosDiariosArrecadacaoActionForm.setNomeGerenciaRegional("");
		}

		// Arrecadador
		if (idArrecadador != null
				&& !idArrecadador.equals("") && !idArrecadador
				.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;

		}
		else{
			filtrarDadosDiariosArrecadacaoActionForm.setIdArrecadador("");
			filtrarDadosDiariosArrecadacaoActionForm.setNomeArrecadador("");
		}

		
		// Elo
		if (idElo != null
				&& !idElo.equals("") && !idElo
				.equals(ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			peloMenosUmParametroInformado = true;

		}
		else{
			filtrarDadosDiariosArrecadacaoActionForm.setIdElo("");
			filtrarDadosDiariosArrecadacaoActionForm.setNomeElo("");
		}

		
		// Imovel Perfil
		int i = 0;
		if (idsImovelPerfil != null) {
			while (i < idsImovelPerfil.length) {
				if (!idsImovelPerfil[i].equals("")) {
					peloMenosUmParametroInformado = true;
				}
				i++;
			}
		}
		
		// Situação Ligação Água 
		i = 0;
		if (idsLigacaoAgua != null) {
			while (i < idsLigacaoAgua.length) {
				if (!idsLigacaoAgua[i].equals("")) {
					peloMenosUmParametroInformado = true;
				}
				i++;
			}
		}

		// Situação Ligação Esgoto 
		i = 0;
		if (idsLigacaoEsgoto != null) {
			while (i < idsLigacaoEsgoto.length) {
				if (!idsLigacaoEsgoto[i].equals("")) {
					peloMenosUmParametroInformado = true;
				}
				i++;
			}
		}
		
		// Tipo do Documento
		i = 0;
		if (idsDocumentosTipos != null) {
			while (i < idsDocumentosTipos.length) {
				if (!idsDocumentosTipos[i].equals("")) {
//					peloMenosUmParametroInformado = true;
				}
				i++;
			}
		}

		// Categoria
		i = 0;
		if (idsCategoria != null) {
			while (i < idsCategoria.length) {
				if (!idsCategoria[i].equals("")) {
//					peloMenosUmParametroInformado = true;					
				}
				i++;
			}
		}

		// Esfera Poder
		i = 0;
		if (idsEsferaPoder != null) {
			while (i < idsEsferaPoder.length) {
				if (!idsEsferaPoder[i].equals("")) {
//					peloMenosUmParametroInformado = true;
				}
				i++;
			}
		}
		
		// Erro caso o usuário mandou filtrar sem nenhum parâmetro
		if (!peloMenosUmParametroInformado) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");
		}

		FiltroConsultarDadosDiariosArrecadacao filtro = new FiltroConsultarDadosDiariosArrecadacao();
		filtro.setAgrupamento(GROUP_BY.ANO_MES);
		filtro.setIdArrecadador(idArrecadador);
		filtro.setIdElo(idElo);
		filtro.setIdGerenciaRegional(idGerenciaRegional);
		filtro.setIdLocalidade(localidade);
		filtro.setIdsCategoria(idsCategoria);
		filtro.setIdsDocumentoTipoAgregador(idsDocumentosTipos);
		filtro.setIdsEsferaPoder(idsEsferaPoder);
		filtro.setIdsImovelPerfil(idsImovelPerfil);
		filtro.setIdsSituacaoLigacaoAgua(idsLigacaoAgua);
		filtro.setIdsSituacaoLigacaoEsgoto(idsLigacaoEsgoto);
				
		boolean existeDados =
			fachada.verificarExistenciaDadosDiariosArrecadacao(
					Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoInicial),
					Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoFinal), 
					filtro);

		
		// [FS0009] Verifica a existência de Dados diarios de arrecadacao
		if (!existeDados) {
			// Nenhum dados diarios de arrecadacao cadastrado
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}
		sessao.setAttribute("filtroConsultarDadosDiariosArrecadacao", filtro);
		sessao.setAttribute("periodoArrecadacaoInicial", 
			Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoInicial));
		sessao.setAttribute("periodoArrecadacaoFinal", 
				Util.formatarMesAnoComBarraParaAnoMes(periodoArrecadacaoFinal));
//			sessao.setAttribute("colecaoArrecadacaoDadosDiarios",
//					colecaoArrecadacaoDadosDiarios);
		
        //manda o statusWizard para a sessão
        sessao.setAttribute("statusWizard", statusWizard);
        
		// Devolve o mapeamento de retorno
		return retorno;
	}
}
