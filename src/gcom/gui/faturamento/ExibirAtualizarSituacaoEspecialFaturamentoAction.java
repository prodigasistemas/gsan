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
package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoSituacaoComando;
import gcom.faturamento.FiltroFaturamentoSituacaoComando;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarSituacaoEspecialFaturamentoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarSituacaoEspecialFaturamentoActionForm atualizarSituacaoEspecialFaturamentoActionForm = (AtualizarSituacaoEspecialFaturamentoActionForm) actionForm;
		
		String id = obterIdRegistroASerAtualizado(httpServletRequest, sessao);

		FaturamentoSituacaoComando faturamentoSituacaoComando= null;
						
		if (Util.verificarNaoVazio(id) && Integer.parseInt(id) > 0) {

			faturamentoSituacaoComando = obterRegistroASerAtualizadoAPartirId(id);			
			
			copiarObjetoParaForm(
					atualizarSituacaoEspecialFaturamentoActionForm,faturamentoSituacaoComando);
			
			sessao.setAttribute("atualizarFaturamentoSituacaoComando", faturamentoSituacaoComando);

			if (sessao.getAttribute("colecaoFaturamentoSituacaoComando") == null) {
				sessao.setAttribute("caminhoRetornoVoltar",
					"/gsan/exibirFiltrarSituacaoEspecialFaturamentoAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar",
					"/gsan/filtrarSituacaoEspecialFaturamentoAction.do");
			}

		}

		return actionMapping.findForward("situacaoEspecialFaturamentoAtualizar");
	}

	/**
	 * Esse método copia os atributos do objeto para o form,
	 * afim de que os dados possam ser exibidos na tela de alteração.
	 *
	 *@since 13/08/2009
	 *@author Marlon Patrick
	 */
	private void copiarObjetoParaForm(
			AtualizarSituacaoEspecialFaturamentoActionForm atualizarSituacaoEspecialFaturamentoActionForm,
			FaturamentoSituacaoComando faturamentoSituacaoComando) {

		if ( faturamentoSituacaoComando != null) {
			
			if(faturamentoSituacaoComando.getId()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm.
					setIdFaturamentoSituacaoComando(faturamentoSituacaoComando.getId().toString());
			}

			if(faturamentoSituacaoComando.getImovel()!= null 
					&& faturamentoSituacaoComando.getImovel().getId()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm.
					setMatricula(faturamentoSituacaoComando.getImovel().getId().toString());					

				atualizarSituacaoEspecialFaturamentoActionForm.
					setIdImovel(faturamentoSituacaoComando.getImovel().getId().toString());					
			}

			if(faturamentoSituacaoComando.getLocalidadeInicial()!=null 
					&& faturamentoSituacaoComando.getLocalidadeInicial().getId()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
				.setLocalidadeOrigemID(faturamentoSituacaoComando.getLocalidadeInicial().getId().toString());					
			}

			if(faturamentoSituacaoComando.getLocalidadeFinal()!=null 
					&& faturamentoSituacaoComando.getLocalidadeFinal().getId()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
					.setLocalidadeDestinoID(faturamentoSituacaoComando.getLocalidadeFinal().getId().toString());
			}

			if(faturamentoSituacaoComando.getCodigoSetorComercialInicial()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
					.setSetorComercialOrigemCD(faturamentoSituacaoComando.getCodigoSetorComercialInicial().toString());
			}

			if(faturamentoSituacaoComando.getCodigoSetorComercialFinal()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
				.setSetorComercialDestinoCD(faturamentoSituacaoComando.getCodigoSetorComercialFinal().toString());					
			}

			if(faturamentoSituacaoComando.getNumeroQuadraInicial()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
					.setQuadraOrigemNM(faturamentoSituacaoComando.getNumeroQuadraInicial().toString());
			}
			
			if(faturamentoSituacaoComando.getNumeroQuadraFinal()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
					.setQuadraDestinoNM(faturamentoSituacaoComando.getNumeroQuadraFinal().toString());
			}
			
			if(faturamentoSituacaoComando.getNumeroLoteInicial()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
				.setLoteOrigem(faturamentoSituacaoComando.getNumeroLoteInicial().toString());					
			}
			
			if(faturamentoSituacaoComando.getNumeroLoteFinal()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
				.setLoteDestino(faturamentoSituacaoComando.getNumeroLoteFinal().toString());					
			}
			
			if(faturamentoSituacaoComando.getNumeroSubLoteInicial()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
				.setSubloteOrigem(faturamentoSituacaoComando.getNumeroLoteInicial().toString());					
			}
			
			if(faturamentoSituacaoComando.getNumeroSubLoteFinal()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
				.setSubloteDestino(faturamentoSituacaoComando.getNumeroLoteFinal().toString());					
			}
			
			if(faturamentoSituacaoComando.getCodigoRotaInicial()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
				.setCdRotaInicial(faturamentoSituacaoComando.getCodigoRotaInicial().toString());					
			}
			
			if(faturamentoSituacaoComando.getCodigoRotaFinal()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
				.setCdRotaFinal(faturamentoSituacaoComando.getCodigoRotaFinal().toString());					
			}
			
			if(faturamentoSituacaoComando.getSequencialRotaInicial()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
				.setSequencialRotaInicial(faturamentoSituacaoComando.getSequencialRotaInicial().toString());					
			}
			
			if(faturamentoSituacaoComando.getSequencialRotaFinal()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
				.setSequencialRotaFinal(faturamentoSituacaoComando.getSequencialRotaFinal().toString());					
			}

			if(faturamentoSituacaoComando.getCategoria1()!=null
					&& faturamentoSituacaoComando.getCategoria1().getId()!=null){
				
				atualizarSituacaoEspecialFaturamentoActionForm
				.setCategoria1ID(faturamentoSituacaoComando.getCategoria1().getId().toString());					
			}

			if(faturamentoSituacaoComando.getCategoria2()!=null
					&& faturamentoSituacaoComando.getCategoria2().getId()!=null){
				
				atualizarSituacaoEspecialFaturamentoActionForm
				.setCategoria2ID(faturamentoSituacaoComando.getCategoria2().getId().toString());					
			}

			if(faturamentoSituacaoComando.getCategoria3()!=null
					&& faturamentoSituacaoComando.getCategoria3().getId()!=null){
				
				atualizarSituacaoEspecialFaturamentoActionForm
				.setCategoria3ID(faturamentoSituacaoComando.getCategoria3().getId().toString());					
			}

			if(faturamentoSituacaoComando.getCategoria4()!=null
					&& faturamentoSituacaoComando.getCategoria4().getId()!=null){
				
				atualizarSituacaoEspecialFaturamentoActionForm
				.setCategoria4ID(faturamentoSituacaoComando.getCategoria4().getId().toString());					
			}

			if(faturamentoSituacaoComando.getIndicadorConsumo()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
				.setIndicadorConsumoImovel(faturamentoSituacaoComando.getIndicadorConsumo().toString());					
			}

			if(faturamentoSituacaoComando.getQuantidadeImoveis()!=null){
				atualizarSituacaoEspecialFaturamentoActionForm
				.setQuantidadeImoveisAtualizados(faturamentoSituacaoComando.getQuantidadeImoveis().toString());					
			}
			
			if(faturamentoSituacaoComando.getFaturamentoSituacaoTipo()!=null
					&& Util.verificarNaoVazio(faturamentoSituacaoComando.getFaturamentoSituacaoTipo()
							.getDescricao())){
				
				atualizarSituacaoEspecialFaturamentoActionForm
					.setTipoSituacaoEspecialFaturamento(faturamentoSituacaoComando.
						getFaturamentoSituacaoTipo().getDescricao());					
			}

			if(faturamentoSituacaoComando.getFaturamentoSituacaoMotivo()!=null
					&& Util.verificarNaoVazio(faturamentoSituacaoComando.getFaturamentoSituacaoMotivo()
							.getDescricao())){
				
				atualizarSituacaoEspecialFaturamentoActionForm
					.setMotivoSituacaoEspecialFaturamento(
							faturamentoSituacaoComando.getFaturamentoSituacaoMotivo().getDescricao());
			}
			
			if(faturamentoSituacaoComando.getAnoMesInicialSituacaoFaturamento()!=null){
				
				atualizarSituacaoEspecialFaturamentoActionForm
					.setMesAnoReferenciaFaturamentoInicial(Util.formatarAnoMesParaMesAno(
							faturamentoSituacaoComando.getAnoMesInicialSituacaoFaturamento().toString()));
			}
			
			if(faturamentoSituacaoComando.getAnoMesFinalSituacaoFaturamento()!=null){
				
				atualizarSituacaoEspecialFaturamentoActionForm
					.setMesAnoReferenciaFaturamentoFinal(Util.formatarAnoMesParaMesAno(
							faturamentoSituacaoComando.getAnoMesFinalSituacaoFaturamento().toString()));
			}
			
			if(Util.verificarNaoVazio(faturamentoSituacaoComando.getObservacao())){
				
				atualizarSituacaoEspecialFaturamentoActionForm
					.setObservacao(faturamentoSituacaoComando.
								getObservacao());
			}

		}
	}

	/**
	 * Esse método monta o filtro para consultar o registro a ser atualizado
	 * baseado no seu id. Havendo resultado ele retorna o objeto, caso contrário retorna null.
	 *
	 *@since 13/08/2009
	 *@author Marlon Patrick
	 */
	private FaturamentoSituacaoComando obterRegistroASerAtualizadoAPartirId(String id) {
		
		Fachada fachada = Fachada.getInstancia();
		FaturamentoSituacaoComando faturamentoSituacaoComando = null;		
		FiltroFaturamentoSituacaoComando filtroFaturamentoSituacaoComando= new FiltroFaturamentoSituacaoComando();

		filtroFaturamentoSituacaoComando.
			adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.IMOVEL);

		filtroFaturamentoSituacaoComando.
			adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.LOCALIDADE_INICIAL);

		filtroFaturamentoSituacaoComando.
			adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.LOCALIDADE_FINAL);
		
		filtroFaturamentoSituacaoComando.
			adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.CATEGORIA_1);

		filtroFaturamentoSituacaoComando.
			adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.CATEGORIA_2);

		filtroFaturamentoSituacaoComando.
			adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.CATEGORIA_3);

		filtroFaturamentoSituacaoComando.
			adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.CATEGORIA_4);

		filtroFaturamentoSituacaoComando.
			adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.FATURAMENTO_SITUACAO_TIPO);

		filtroFaturamentoSituacaoComando.
			adicionarCaminhoParaCarregamentoEntidade(FiltroFaturamentoSituacaoComando.FATURAMENTO_SITUACAO_MOTIVO);

		filtroFaturamentoSituacaoComando.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoSituacaoComando.ID, id));
		
		Collection<FaturamentoSituacaoComando> colecaoFaturamentoSituacaoComando = fachada.pesquisar(
				filtroFaturamentoSituacaoComando, FaturamentoSituacaoComando.class.getName());

		if ( !Util.isVazioOrNulo(colecaoFaturamentoSituacaoComando)) {
			faturamentoSituacaoComando= colecaoFaturamentoSituacaoComando.iterator().next();
		}
		return faturamentoSituacaoComando;
	}

	/**
	 * Esse método faz a verificação tanto na sessão quanto no request
	 * na tentativa de encontrar o atributo ou parametro que represente
	 * o id do objeto a ser atualizado.
	 *
	 *@since 13/08/2009
	 *@author Marlon Patrick
	 */
	private String obterIdRegistroASerAtualizado(HttpServletRequest httpServletRequest, HttpSession sessao) {
		
		//TODO verificar o comportamento de todos esses atributos e parametros
		
		String id = null;
		
		if (httpServletRequest.getParameter("idRegistroAtualizacao") == null){
			id = ((FaturamentoSituacaoComando) sessao.getAttribute("faturamentoSituacaoComando")).getId().toString();
		}else{
			id = httpServletRequest.getParameter("idRegistroAtualizacao");
		}
		
		if (id == null) {
			if (httpServletRequest.getAttribute("idRegistroAtualizacao") == null) {
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = httpServletRequest.getAttribute("idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
		return id;
	}

}
