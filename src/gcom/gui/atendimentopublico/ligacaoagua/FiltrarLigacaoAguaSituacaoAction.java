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
package gcom.gui.atendimentopublico.ligacaoagua;

import gcom.atendimentopublico.ligacaoagua.FiltroLigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ComparacaoTextoCompleto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0786]Filtrar Situacao de Ligacao de Agua
 * 
 * @author Vinícius Medeiros
 * @date 15/05/2008
 */

public class FiltrarLigacaoAguaSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterLigacaoAguaSituacao");

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		FiltrarLigacaoAguaSituacaoActionForm filtrarLigacaoAguaSituacaoActionForm = (FiltrarLigacaoAguaSituacaoActionForm) actionForm;

		FiltroLigacaoAguaSituacao filtroLigacaoAguaSituacao = new FiltroLigacaoAguaSituacao();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarLigacaoAguaSituacaoActionForm.getId();
		String descricao = filtrarLigacaoAguaSituacaoActionForm.getDescricao();
		String descricaoAbreviada = filtrarLigacaoAguaSituacaoActionForm.getDescricaoAbreviada();
		String consumoMinimoFaturamento = filtrarLigacaoAguaSituacaoActionForm.getConsumoMinimoFaturamento();
		String tipoPesquisa = filtrarLigacaoAguaSituacaoActionForm.getTipoPesquisa();

		// Filtro pelo ID
		if (id != null && !id.trim().equals("")) {
			
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			
			if (achou) {
			
				peloMenosUmParametroInformado = true;
				filtroLigacaoAguaSituacao.adicionarParametro(new ParametroSimples(
						FiltroLigacaoAguaSituacao.ID, id));
			
			}
		}
		
		// Filtro pela Descrição
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			
			peloMenosUmParametroInformado = true;
			
			if (tipoPesquisa != null&& tipoPesquisa.equals(
					ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {

				filtroLigacaoAguaSituacao.adicionarParametro(
						new ComparacaoTextoCompleto(FiltroLigacaoAguaSituacao.DESCRICAO, 
							descricao));
			} else {

				filtroLigacaoAguaSituacao.adicionarParametro(new ComparacaoTexto(
						FiltroLigacaoAguaSituacao.DESCRICAO, descricao));
			}
		}
		
		// Filtro pela Descrição Abreviada
		if (descricaoAbreviada != null && !descricaoAbreviada.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroLigacaoAguaSituacao.DESCRICAO_ABREVIADA,
							descricaoAbreviada));
		} else {

			filtroLigacaoAguaSituacao.adicionarParametro(
					new ComparacaoTexto(FiltroLigacaoAguaSituacao.DESCRICAO_ABREVIADA,
							descricaoAbreviada));
		}

		// Filtro pelo Consumo Minimo Faturamento
		if (consumoMinimoFaturamento != null && !consumoMinimoFaturamento.trim().equalsIgnoreCase("")) {

			peloMenosUmParametroInformado = true;
			
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ComparacaoTextoCompleto(FiltroLigacaoAguaSituacao.CONSUMO_MINIMO,
							consumoMinimoFaturamento));
		} else {

			filtroLigacaoAguaSituacao.adicionarParametro(
					new ComparacaoTexto(FiltroLigacaoAguaSituacao.CONSUMO_MINIMO,
							consumoMinimoFaturamento));
		}
		
		// Verifica se o indicador de faturamento está sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorFaturamentoSituacao().equals( ConstantesSistema.TODOS.toString() ) ){
			
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_FATURAMENTO, 
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorFaturamentoSituacao() ) );
		
		}
		
		// Verifica se o indicador de existencia de rede está sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaRede().equals( ConstantesSistema.TODOS.toString() ) ){
		
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE, 
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaRede() ) );
		
		}
		
		// Verifica se o indicador de existencia de ligação está sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaLigacao().equals( ConstantesSistema.TODOS.toString() ) ){
			
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_LIGACAO, 
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorExistenciaLigacao() ) );
						
		}
		
		//Verifica se o indicador abastecimento está sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorAbastecimento().equals( ConstantesSistema.TODOS.toString() ) ){
		
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_EXISTENCIA_REDE, 
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorAbastecimento() ) );
		
		}
		
		//Verifica se o indicador água ativa está sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaAtiva().equals( ConstantesSistema.TODOS.toString() ) ){
		
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_AGUA_ATIVA, 
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaAtiva() ) );
		
		}
		
		//Verifica se o indicador água desligada está sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaDesligada().equals( ConstantesSistema.TODOS.toString() ) ){
		
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_AGUA_DESLIGADA,
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaDesligada() ) );
		
		}
		
		//Verifica se o indicador água cadastrada está sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaCadastrada().equals( ConstantesSistema.TODOS.toString() ) ){
		
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_AGUA_CADASTRADA,
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorAguaCadastrada() ) );
		
		}
		
		//Verifica se o indicador analise de água está sendo utilizado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorAnalizeAgua().equals( ConstantesSistema.TODOS.toString() ) ){
		
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples( FiltroLigacaoAguaSituacao.INDICADOR_ANALISE_AGUA,
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorAnalizeAgua() ) );
		
		}
		
		// Verifica se o indicador de uso está sendo usado
		if ( !filtrarLigacaoAguaSituacaoActionForm.getIndicadorUso().equals( ConstantesSistema.TODOS.toString() ) ){
			
			peloMenosUmParametroInformado = true;
			filtroLigacaoAguaSituacao.adicionarParametro(
					new ParametroSimples(FiltroLigacaoAguaSituacao.INDICADOR_USO, 
							filtrarLigacaoAguaSituacaoActionForm.getIndicadorUso() ) );
						
		}	
		
		Collection<LigacaoAguaSituacao> colecaoLigacaoAguaSituacao = fachada.pesquisar(
				filtroLigacaoAguaSituacao, LigacaoAguaSituacao.class.getName());

		// Verifica a existencia de uma Situação de Ligação de Água 
		if (colecaoLigacaoAguaSituacao.isEmpty()) {
		
			throw new ActionServletException(
					"atencao.pesquisa_inexistente", null,"Situação de Ligação de Água");
		
		}

		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros
		if (colecaoLigacaoAguaSituacao == null|| colecaoLigacaoAguaSituacao.isEmpty()) {
			
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Situação de Ligação de Água");
		
		} else {
		
			httpServletRequest.setAttribute("colecaoLigacaoAguaSituacao",
					colecaoLigacaoAguaSituacao);
			LigacaoAguaSituacao ligacaoAguaSituacao = new LigacaoAguaSituacao();
			ligacaoAguaSituacao = (LigacaoAguaSituacao) Util
					.retonarObjetoDeColecao(colecaoLigacaoAguaSituacao);
			String idRegistroAtualizacao = ligacaoAguaSituacao.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		
		}

		sessao.setAttribute("filtroLigacaoAguaSituacao", filtroLigacaoAguaSituacao);
		httpServletRequest.setAttribute("filtroLigacaoAguaSituacao",filtroLigacaoAguaSituacao);

		return retorno;

	}
}
