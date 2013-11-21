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
package gcom.gui.atendimentopublico.ligacaoesgoto;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
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
 * [UC0789] Filtrar Situacao de Ligação de Esgoto
 * 
 * @author Bruno Barros
 *
 * @date 15/05/2008
 */
public class FiltrarLigacaoEsgotoSituacaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirManterLigacaoEsgotoSituacao");
		
		// Sessão
		HttpSession sessao = httpServletRequest.getSession(false);

		// Form
		FiltrarLigacaoEsgotoSituacaoActionForm filtrarSistemaEsgotoActionForm 
			= (FiltrarLigacaoEsgotoSituacaoActionForm) actionForm;
		
		// Verificamos os campos obrigatórios foram informados
		boolean informouAoMenos1Parametro = false;
		
		// Criamos o filtro para a pesquisa
		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();
		
		// Verificamos se o código foi informado
		if ( filtrarSistemaEsgotoActionForm.getCodigo() != null &&
			 !filtrarSistemaEsgotoActionForm.getCodigo().equals( "" ) ){
			filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.ID, filtrarSistemaEsgotoActionForm.getCodigo() )  );
			informouAoMenos1Parametro = true;
		}
		
		// Verificamos se a descrição foi informada
		if (filtrarSistemaEsgotoActionForm.getDescricao() != null && 
			!filtrarSistemaEsgotoActionForm.getDescricao().trim().equalsIgnoreCase("")) {
			informouAoMenos1Parametro = true;
			if (filtrarSistemaEsgotoActionForm.getDescricao() != null &&
				filtrarSistemaEsgotoActionForm.getDescricao().equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString())) {
				filtroLigacaoEsgotoSituacao
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroLigacaoEsgotoSituacao.DESCRICAO, filtrarSistemaEsgotoActionForm.getDescricao() ) );
			} else {
				filtroLigacaoEsgotoSituacao.adicionarParametro(new ComparacaoTexto(
						FiltroLigacaoEsgotoSituacao.DESCRICAO, filtrarSistemaEsgotoActionForm.getDescricao() ) );
			}
		}
		
		// Verificamos se a descrição abreviada foi informada
		if ( filtrarSistemaEsgotoActionForm.getDescricaoAbreviada() != null &&
			 !filtrarSistemaEsgotoActionForm.getDescricaoAbreviada().equals( "" ) ){
			filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.DESCRICAOABREVIADA, filtrarSistemaEsgotoActionForm.getDescricaoAbreviada() )  );
			informouAoMenos1Parametro = true;			
		}
		
		// Verificamos se o Consumo Minimo foi informado
		if ( filtrarSistemaEsgotoActionForm.getConsumoMinimoFaturamento() != null &&
			 !filtrarSistemaEsgotoActionForm.getConsumoMinimoFaturamento().equals( "" ) ){
			// Verificamos se o volume minimo é um número válido
			if ( Util.validarStringNumerica( filtrarSistemaEsgotoActionForm.getConsumoMinimoFaturamento() ) ){
				filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.VOLUMEMINIMOFATURAMENTO, filtrarSistemaEsgotoActionForm.getConsumoMinimoFaturamento() ) );
				informouAoMenos1Parametro = true;
			} else {
				throw new ActionServletException( "atencao.campo_texto.numero_obrigatorio", null, "Consumo Mínimo" );
			}
		}
		
		// Verificamos se o indicador de faturamento está sendo usado
		if ( !filtrarSistemaEsgotoActionForm.getIndicadorFaturamento().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.INDICADORFATURAMENTOSITUACAO, filtrarSistemaEsgotoActionForm.getIndicadorFaturamento() ) );
			informouAoMenos1Parametro = true;			
		}
		
		// Verificamos se o indicador de existencia de rede está sendo usado
		if ( !filtrarSistemaEsgotoActionForm.getIndicadorExistenciaRede().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.INDICADOREXISTENCIAREDE, filtrarSistemaEsgotoActionForm.getIndicadorExistenciaRede() ) );
			informouAoMenos1Parametro = true;			
		}
		
		// Verificamos se o indicador de existencia de ligação está sendo usado
		if ( !filtrarSistemaEsgotoActionForm.getIndicadorExistenciaLigacao().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.INDICADOREXISTENCIALIGACAO, filtrarSistemaEsgotoActionForm.getIndicadorExistenciaLigacao() ) );
			informouAoMenos1Parametro = true;			
		}
		
		// Verificamos se o indicador de uso está sendo usado
		if ( !filtrarSistemaEsgotoActionForm.getIndicadorUso().equals( ConstantesSistema.TODOS.toString() ) ){
			filtroLigacaoEsgotoSituacao.adicionarParametro( new ParametroSimples( FiltroLigacaoEsgotoSituacao.INDICADORUSO, filtrarSistemaEsgotoActionForm.getIndicadorUso() ) );
			informouAoMenos1Parametro = true;			
		}	
		
		// FS0001 - Verificar preenchimento dos campos
		if ( !informouAoMenos1Parametro ){
			throw new ActionServletException( "atencao.filtrar_informar_um_filtro" );
		}		
		
		// Pega a instancia da fachada		
		Fachada fachada = Fachada.getInstancia();
		
		Collection<LigacaoEsgotoSituacao> colecaoLigacaoEsgotoSituacao = fachada
				.pesquisar( filtroLigacaoEsgotoSituacao, LigacaoEsgotoSituacao.class.getName() );

		// Pesquisa sem registros
		if (colecaoLigacaoEsgotoSituacao == null
				|| colecaoLigacaoEsgotoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Situação de Ligação de Esgoto");
		} else {
			// Guardamos a colecao no request e o id do registro que será atualizado
			httpServletRequest.setAttribute( "colecaoLigacaoEsgotoSituacao", colecaoLigacaoEsgotoSituacao );
			LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
			
			ligacaoEsgotoSituacao = (LigacaoEsgotoSituacao) Util
					.retonarObjetoDeColecao(colecaoLigacaoEsgotoSituacao);
			
			String idRegistroAtualizacao = ligacaoEsgotoSituacao.getId().toString();
			sessao.setAttribute("idRegistroAtualizacao", idRegistroAtualizacao);
		}
		
		// Colocamos o filtro na sessao		
		sessao.setAttribute("filtroLigacaoEsgotoSituacao", filtroLigacaoEsgotoSituacao);
		
		httpServletRequest.setAttribute("filtroLigacaoEsgotoSituacao",
				filtroLigacaoEsgotoSituacao);

		return retorno;
	}
}