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
package gcom.gui.cobranca;



import gcom.cobranca.CobrancaSituacao;
import gcom.cobranca.FiltroCobrancaSituacao;
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
 * [UC0838]FILTRAR LIGACAO DE ESGOTO ESGOTAMENTO
 * 
 * @author Arthur Carvalho
 * @date 25/08/08
 */

public class FiltrarCobrancaSituacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("exibirManterCobrancaSituacao");

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		FiltrarCobrancaSituacaoActionForm filtrarCobrancaSituacaoActionForm = (FiltrarCobrancaSituacaoActionForm) actionForm;

		FiltroCobrancaSituacao filtroCobrancaSituacao = new FiltroCobrancaSituacao();

		boolean peloMenosUmParametroInformado = false;

		String id = filtrarCobrancaSituacaoActionForm.getId();
		String contaMotivoRevisao = filtrarCobrancaSituacaoActionForm.getContaMotivoRevisao();
		String descricao = filtrarCobrancaSituacaoActionForm.getDescricao();
		String indicadorUso = filtrarCobrancaSituacaoActionForm.getIndicadorUso();
		String indicadorExigenciaAdvogado =  filtrarCobrancaSituacaoActionForm.getIndicadorExigenciaAdvogado();
		String tipoPesquisa = filtrarCobrancaSituacaoActionForm.getTipoPesquisa();
		String indicadorBloqueioParcelamento = filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioParcelamento();
		String indicadorBloqueioRetirada = filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioRetirada();
		String indicadorBloqueioInclusao = filtrarCobrancaSituacaoActionForm.getIndicadorBloqueioInclusao();
		String profissao = filtrarCobrancaSituacaoActionForm.getProfissao();
		String ramoAtividade = filtrarCobrancaSituacaoActionForm.getRamoAtividade();
		String indicadorSelecaoApenasComPermissao = filtrarCobrancaSituacaoActionForm.getIndicadorSelecaoApenasComPermissao();
		String indicadorPrescricaoImoveisParticulares = filtrarCobrancaSituacaoActionForm.getIndicadorPrescricaoImoveisParticulares();
		
		//Indicador Atualizar
		String indicadorAtualizar = httpServletRequest.getParameter("indicadorAtualizar");
		
		if (indicadorAtualizar != null && !indicadorAtualizar.equals("")) {			
			sessao.setAttribute("indicadorAtualizar", indicadorAtualizar);
		} else {			
			sessao.removeAttribute("indicadorAtualizar");
		}
		
		//CODIGO
		if (id != null && !id.trim().equals("")) {
			boolean achou = fachada.verificarExistenciaAgente(new Integer(id));
			if (achou) {
				peloMenosUmParametroInformado = true;
				
				filtroCobrancaSituacao.adicionarParametro(
					new ParametroSimples(
							FiltroCobrancaSituacao.ID, 
						id));
			}
		}
		
		//Motivo da situacal especial de faturamento
		if (contaMotivoRevisao != null && 
			!contaMotivoRevisao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(
						FiltroCobrancaSituacao.CONTA_MOTIVO_REVISAO, 
						contaMotivoRevisao));
		}
		
		// Descricao
		if (descricao != null && !descricao.trim().equalsIgnoreCase("")) {
			peloMenosUmParametroInformado = true;
			if (tipoPesquisa != null
					&& tipoPesquisa
							.equals(ConstantesSistema.TIPO_PESQUISA_COMPLETA
									.toString())) {

				filtroCobrancaSituacao
						.adicionarParametro(new ComparacaoTextoCompleto(
								FiltroCobrancaSituacao.DESCRICAO, descricao));
			} else {

				filtroCobrancaSituacao.adicionarParametro(new ComparacaoTexto(
						FiltroCobrancaSituacao.DESCRICAO, descricao));
			}
		}
	
		// Exige Advogado
		if (indicadorExigenciaAdvogado != null && !indicadorExigenciaAdvogado.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_EXIGENCIA_ADVOGADO, indicadorExigenciaAdvogado));
		}
		
		//Bloqueia Parcelamento
		if (indicadorBloqueioParcelamento != null && !indicadorBloqueioParcelamento.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_BLOQUEIO_PARCELAMENTO, indicadorBloqueioParcelamento));
		}
		
		//Bloqueia Retirada
		if ( indicadorBloqueioRetirada != null && !indicadorBloqueioRetirada.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_BLOQUEIO_RETIRADA, indicadorBloqueioRetirada));
		}
		//Indicador Selecao Apenas Com Permissao
		if ( indicadorSelecaoApenasComPermissao != null && !indicadorSelecaoApenasComPermissao.trim().equals("") ) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_SELECAO_APENAS_COM_PERMISSAO, indicadorSelecaoApenasComPermissao));
		}
		
		// Indicador Prescricao Imoveis Particulares
		if(indicadorPrescricaoImoveisParticulares != null && 
				!indicadorPrescricaoImoveisParticulares.trim().equals("") ){
			
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro( 
				new ParametroSimples( FiltroCobrancaSituacao.INDICADOR_PRESCRICAO_IMOVEIS_PARTICULARES, 
						indicadorPrescricaoImoveisParticulares));
		}
		
		//Bloqueia Retirada
		if ( indicadorBloqueioInclusao != null && !indicadorBloqueioInclusao.trim().equals("") ) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_BLOQUEIO_INCLUSAO, indicadorBloqueioInclusao));
		}

		
		// Indicador uso
		if (indicadorUso != null && !indicadorUso.trim().equals("")) {
			peloMenosUmParametroInformado = true;
			filtroCobrancaSituacao.adicionarParametro(new ParametroSimples(
					FiltroCobrancaSituacao.INDICADOR_USO, indicadorUso));
		}

		//Profissao
		if (profissao != null && 
			!profissao.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(
						FiltroCobrancaSituacao.PROFISSAO, 
						profissao));
		}
		

		//Ramo Atividade
		if (ramoAtividade != null && 
			!ramoAtividade.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)) {
			
			peloMenosUmParametroInformado = true;
			
			filtroCobrancaSituacao.adicionarParametro(
				new ParametroSimples(
						FiltroCobrancaSituacao.RAMO_ATIVIDADE, 
						ramoAtividade));
		}
		
		filtroCobrancaSituacao.adicionarCaminhoParaCarregamentoEntidade("contaMotivoRevisao"); 
		
		Collection <CobrancaSituacao> colecaoCobrancaSituacao = fachada
				.pesquisar(filtroCobrancaSituacao, CobrancaSituacao.class
						.getName());
		
		
		// Filtragem sem parametros
		if (!peloMenosUmParametroInformado == true) {
			throw new ActionServletException(
					"atencao.filtro.nenhum_parametro_informado");

		}
		
		// Pesquisa sem registros
		if (colecaoCobrancaSituacao == null
				|| colecaoCobrancaSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.pesquisa.nenhumresultado", null, "Situação de Cobrança");
		} else {
			httpServletRequest.setAttribute("colecaoCobrancaSituacao",
					colecaoCobrancaSituacao);
			CobrancaSituacao cobrancaSituacao= new CobrancaSituacao();
			cobrancaSituacao = (CobrancaSituacao) Util
					.retonarObjetoDeColecao(colecaoCobrancaSituacao);
			String idRegistroAtualizar = cobrancaSituacao.getId().toString();
			sessao.setAttribute("idRegistroAtualizar", idRegistroAtualizar);
		}

		sessao.setAttribute("filtroCobrancaSituacao", filtroCobrancaSituacao);

		httpServletRequest.setAttribute("filtroCobrancaSituacao",
				filtroCobrancaSituacao);
		
		return retorno;
	}
}
