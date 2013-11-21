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
 * Rômulo Aurélio de Melo Souza Filho
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
package gcom.gui.faturamento.autoinfracao;

import gcom.atendimentopublico.ordemservico.FiltroFiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.fachada.Fachada;
import gcom.faturamento.autoinfracao.AutoInfracaoSituacao;
import gcom.faturamento.autoinfracao.AutosInfracao;
import gcom.faturamento.autoinfracao.FiltroAutoInfracaoSituacao;
import gcom.faturamento.autoinfracao.FiltroAutosInfracao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarAutoInfracaoAction extends GcomAction {

	/**
	 * 
	 * [UC0896] Manter Autos de Infração
	 * 
	 * 
	 * @author Rômulo Aurélio
	 * @date 05/05/2009
	 * 
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarAutoInfracao");

		AtualizarAutoInfracaoActionForm form = (AtualizarAutoInfracaoActionForm) actionForm;

		Fachada fachada = Fachada.getInstancia();

		HttpSession sessao = httpServletRequest.getSession(false);

		this.carregarDados(httpServletRequest);

		// Verifica se veio do filtrar ou do manter

		if (httpServletRequest.getParameter("manter") != null) {
			sessao.setAttribute("manter", true);
		} else if (httpServletRequest.getParameter("filtrar") != null) {
			sessao.removeAttribute("manter");
		}
		
		AutosInfracao autosInfracao = null;
		
		if (httpServletRequest.getParameter("reload") == null) {
			// Verifica se o autoInfracao já está na sessão, em caso afirmativo
			// significa que o usuário já entrou na tela e apenas selecionou
			// algum
			// item que deu um reload na tela e em caso negativo significa que
			// ele
			// está entrando pela primeira vez
			if (sessao.getAttribute("objetoAutosInfracao") != null) {

				autosInfracao = (AutosInfracao) sessao
						.getAttribute("objetoAutosInfracao");

				sessao.setAttribute("idAutoInfracao", autosInfracao.getId()
						.toString());

				form.setIdAutoInfracao(autosInfracao.getId().toString());

				form.setIdFuncionario(autosInfracao.getFuncionario().getId()
						.toString());

				form.setDescricaoFuncionario(autosInfracao.getFuncionario()
						.getNome());

				form.setIdImovel(autosInfracao.getImovel().getId().toString());

				String inscricaoImovel = fachada
						.pesquisarInscricaoImovel(autosInfracao.getImovel()
								.getId());

				form.setDescricaoImovel(inscricaoImovel);

				String nomeCliente = fachada
						.pesquisarNomeClientePorImovel(autosInfracao
								.getImovel().getId());

				form.setNomeCliente(nomeCliente);
				
				if (autosInfracao.getNumeroParcelasDebito() != null) {
					form.setQuantidadeParcelas(autosInfracao.getNumeroParcelasDebito().toString());
				}


				form.setDataEmissao(Util.formatarData(autosInfracao
						.getDataEmissao()));

				form.setDataInicioRecurso(Util.formatarData(autosInfracao
						.getDataInicioRecurso()));

				form.setDataTerminoRecurso(Util.formatarData(autosInfracao
						.getDataTerminoRecurso()));

				form.setIdAutoInfracaoSituacao(autosInfracao
						.getAutoInfracaoSituacao().getId().toString());

				form.setIdFiscalizacaoSituacao(autosInfracao
						.getFiscalizacaoSituacao().getId().toString());
				
				form.setObservacao(autosInfracao.getObservacao());

				sessao.setAttribute("objetoAutosInfracao", autosInfracao);

			} else {

				String idAutoInfracao = null;

				if (httpServletRequest.getParameter("id") == null
						|| httpServletRequest.getParameter("id").equals("")) {
					idAutoInfracao = (String) sessao.getAttribute("id");
				} else {
					idAutoInfracao = (String) httpServletRequest
							.getParameter("id");
					sessao.setAttribute("id", idAutoInfracao);
				}

				httpServletRequest.setAttribute("id", idAutoInfracao);
				sessao.setAttribute("id", idAutoInfracao);

				FiltroAutosInfracao filtroAutosInfracao = new FiltroAutosInfracao();
				filtroAutosInfracao
						.adicionarCaminhoParaCarregamentoEntidade(FiltroAutosInfracao.FISCALIZACAO_SITUACAO);
				filtroAutosInfracao
						.adicionarCaminhoParaCarregamentoEntidade(FiltroAutosInfracao.AUTO_INFRACAO_SITUACAO);

				filtroAutosInfracao
						.adicionarCaminhoParaCarregamentoEntidade(FiltroAutosInfracao.FUNCIONARIO);

				filtroAutosInfracao.adicionarParametro(new ParametroSimples(
						FiltroAutosInfracao.ID, idAutoInfracao));

				Collection<AutosInfracao> colecaoAutosInfracao = fachada
						.pesquisar(filtroAutosInfracao, AutosInfracao.class
								.getName());

				if (colecaoAutosInfracao == null
						|| colecaoAutosInfracao.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoAutosInfracao",
						colecaoAutosInfracao);

				autosInfracao = (AutosInfracao) colecaoAutosInfracao.iterator()
						.next();

				form.setIdAutoInfracao(autosInfracao.getId().toString());

				form.setIdFuncionario(autosInfracao.getFuncionario().getId()
						.toString());

				form.setDescricaoFuncionario(autosInfracao.getFuncionario()
						.getNome());

				form.setIdImovel(autosInfracao.getImovel().getId().toString());

				String inscricaoImovel = fachada
						.pesquisarInscricaoImovel(autosInfracao.getImovel()
								.getId());

				form.setDescricaoImovel(inscricaoImovel);

				String nomeCliente = fachada
						.pesquisarNomeClientePorImovel(autosInfracao
								.getImovel().getId());

				form.setNomeCliente(nomeCliente);

				if (autosInfracao.getNumeroParcelasDebito() != null) {
					form.setQuantidadeParcelas(autosInfracao.getNumeroParcelasDebito().toString());
				}
				
				form.setDataEmissao(Util.formatarData(autosInfracao
						.getDataEmissao()));

				form.setDataInicioRecurso(Util.formatarData(autosInfracao
						.getDataInicioRecurso()));

				form.setDataTerminoRecurso(Util.formatarData(autosInfracao
						.getDataTerminoRecurso()));

				form.setIdAutoInfracaoSituacao(autosInfracao
						.getAutoInfracaoSituacao().getId().toString());

				form.setIdFiscalizacaoSituacao(autosInfracao
						.getFiscalizacaoSituacao().getId().toString());
				
				form.setObservacao(autosInfracao.getObservacao());

				sessao.setAttribute("objetoAutosInfracao", autosInfracao);

			}

		}

		// -------------- bt DESFAZER ---------------

		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {

			String idAutoInfracao = null;

			if (httpServletRequest.getParameter("id") == null
					|| httpServletRequest.getParameter("id").equals("")) {
				idAutoInfracao = (String) sessao.getAttribute("id");
			} else {
				idAutoInfracao = (String) httpServletRequest.getParameter("id");
				sessao.setAttribute("id", idAutoInfracao);
			}

			if (idAutoInfracao != null && idAutoInfracao.equalsIgnoreCase("")) {
				idAutoInfracao = null;
			}

			if (idAutoInfracao == null) {

				autosInfracao = (AutosInfracao) sessao
						.getAttribute("objetoAutosInfracao");

				sessao.setAttribute("id", autosInfracao.getId().toString());

				form.setIdAutoInfracao(autosInfracao.getId().toString());

				form.setIdFuncionario(autosInfracao.getFuncionario().getId()
						.toString());

				form.setDescricaoFuncionario(autosInfracao.getFuncionario()
						.getNome());

				form.setIdImovel(autosInfracao.getImovel().getId().toString());

				String inscricaoImovel = fachada
						.pesquisarInscricaoImovel(autosInfracao.getImovel()
								.getId());

				form.setDescricaoImovel(inscricaoImovel);

				String nomeCliente = fachada
						.pesquisarNomeClientePorImovel(autosInfracao
								.getImovel().getId());

				form.setNomeCliente(nomeCliente);
				
				if (autosInfracao.getNumeroParcelasDebito() != null) {
					form.setQuantidadeParcelas(autosInfracao.getNumeroParcelasDebito().toString());
				}

				form.setDataEmissao(Util.formatarData(autosInfracao
						.getDataEmissao()));

				form.setDataInicioRecurso(Util.formatarData(autosInfracao
						.getDataInicioRecurso()));

				form.setDataTerminoRecurso(Util.formatarData(autosInfracao
						.getDataTerminoRecurso()));

				form.setIdAutoInfracaoSituacao(autosInfracao
						.getAutoInfracaoSituacao().getId().toString());

				form.setIdFiscalizacaoSituacao(autosInfracao
						.getFiscalizacaoSituacao().getId().toString());
				
				form.setObservacao(autosInfracao.getObservacao());

				sessao.setAttribute("autosInfracaoAtualizar", autosInfracao);
				sessao.removeAttribute("autosInfracao");
			}

			if (idAutoInfracao != null) {

				FiltroAutosInfracao filtroAutosInfracao = new FiltroAutosInfracao();
				filtroAutosInfracao
						.adicionarCaminhoParaCarregamentoEntidade(FiltroAutosInfracao.FISCALIZACAO_SITUACAO);
				filtroAutosInfracao
						.adicionarCaminhoParaCarregamentoEntidade(FiltroAutosInfracao.AUTO_INFRACAO_SITUACAO);
				
				filtroAutosInfracao
				.adicionarCaminhoParaCarregamentoEntidade(FiltroAutosInfracao.FUNCIONARIO);

				filtroAutosInfracao.adicionarParametro(new ParametroSimples(
						FiltroAutosInfracao.ID, idAutoInfracao));

				Collection<AutosInfracao> colecaoAutosInfracao = fachada
						.pesquisar(filtroAutosInfracao, AutosInfracao.class
								.getName());

				if (colecaoAutosInfracao == null
						|| colecaoAutosInfracao.isEmpty()) {
					throw new ActionServletException(
							"atencao.atualizacao.timestamp");
				}

				httpServletRequest.setAttribute("colecaoAutosInfracao",
						colecaoAutosInfracao);

				autosInfracao = (AutosInfracao) colecaoAutosInfracao.iterator()
						.next();

				form.setIdAutoInfracao(autosInfracao.getId().toString());

				form.setIdFuncionario(autosInfracao.getFuncionario().getId()
						.toString());

				form.setDescricaoFuncionario(autosInfracao.getFuncionario()
						.getNome());

				form.setIdImovel(autosInfracao.getImovel().getId().toString());

				String inscricaoImovel = fachada
						.pesquisarInscricaoImovel(autosInfracao.getImovel()
								.getId());

				form.setDescricaoImovel(inscricaoImovel);

				String nomeCliente = fachada
						.pesquisarNomeClientePorImovel(autosInfracao
								.getImovel().getId());

				form.setNomeCliente(nomeCliente);

				form.setDataEmissao(Util.formatarData(autosInfracao
						.getDataEmissao()));

				form.setDataInicioRecurso(Util.formatarData(autosInfracao
						.getDataInicioRecurso()));

				form.setDataTerminoRecurso(Util.formatarData(autosInfracao
						.getDataTerminoRecurso()));

				form.setIdAutoInfracaoSituacao(autosInfracao
						.getAutoInfracaoSituacao().getId().toString());

				form.setIdFiscalizacaoSituacao(autosInfracao
						.getFiscalizacaoSituacao().getId().toString());
				
				form.setObservacao(autosInfracao.getObservacao());
			}
		}
		// -------------- bt DESFAZER ---------------

		this.pesquisarCamposEnter(form,httpServletRequest);
		this.verificarObrigatoriedade(form, sessao);
		
		return retorno;

	}

	private void carregarDados(HttpServletRequest httpServletRequest) {

		FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();
		
		filtroFiscalizacaoSituacao.adicionarParametro(new ParametroSimples(
				FiltroFiscalizacaoSituacao.INDICADOR_ATUALIZACAO_AUTOS_INFRACAO,ConstantesSistema.INDICADOR_USO_ATIVO));

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<FiscalizacaoSituacao> colecaoFiscalizacaoSituacao = this
				.getFachada().pesquisar(filtroFiscalizacaoSituacao,
						FiscalizacaoSituacao.class.getName());

		if (colecaoFiscalizacaoSituacao == null
				|| colecaoFiscalizacaoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Fiscalizacao Situacao");
		}

		httpServletRequest.setAttribute("colecaoFiscalizacaoSituacao",
				colecaoFiscalizacaoSituacao);

		FiltroAutoInfracaoSituacao filtroAutoInfracaoSituacao = new FiltroAutoInfracaoSituacao();

		filtroAutoInfracaoSituacao.adicionarParametro(new ParametroSimples(
				FiltroAutoInfracaoSituacao.INDICADOR_USO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
		
		
		

		// Verifica se os dados foram informados da tabela existem e joga numa
		// colecao

		Collection<AutoInfracaoSituacao> colecaoAutoInfracaoSituacao = this
				.getFachada().pesquisar(filtroAutoInfracaoSituacao,
						AutoInfracaoSituacao.class.getName());

		if (colecaoAutoInfracaoSituacao == null
				|| colecaoAutoInfracaoSituacao.isEmpty()) {
			throw new ActionServletException(
					"atencao.entidade_sem_dados_para_selecao", null,
					"Tabela Auto Infracao Situacao");
		}

		httpServletRequest.setAttribute("colecaoAutoInfracaoSituacao",
				colecaoAutoInfracaoSituacao);
	}

	public void verificarObrigatoriedade(AtualizarAutoInfracaoActionForm form,
			HttpSession sessao) {

		if (form.getIdAutoInfracaoSituacao() != null
				&& !form.getIdAutoInfracaoSituacao().equals("-1")) {

			FiltroAutoInfracaoSituacao filtroAutoInfracaoSituacao = new FiltroAutoInfracaoSituacao();

			filtroAutoInfracaoSituacao.adicionarParametro(new ParametroSimples(
					FiltroAutoInfracaoSituacao.ID, form
							.getIdAutoInfracaoSituacao()));

			Collection<AutoInfracaoSituacao> colecaoAutoInfracaoSituacao = this
					.getFachada().pesquisar(filtroAutoInfracaoSituacao,
							AutoInfracaoSituacao.class.getName());

			AutoInfracaoSituacao autoInfracaoSituacao = (AutoInfracaoSituacao) Util
					.retonarObjetoDeColecao(colecaoAutoInfracaoSituacao);

			if (!autoInfracaoSituacao.getIndicadorDataInicioRecurso().equals(
					AutoInfracaoSituacao.NULO)) {

				sessao.setAttribute("dataInicialRecurso", true);

			} else {

				sessao.removeAttribute("dataInicialRecurso");
			}

			if (!autoInfracaoSituacao.getIndicadorDataTerminoRecurso().equals(
					AutoInfracaoSituacao.NULO)) {

				sessao.setAttribute("dataTerminoRecurso", true);

			} else {

				sessao.removeAttribute("dataTerminoRecurso");
			}

		}else{
			sessao.removeAttribute("dataInicialRecurso");
			sessao.removeAttribute("dataTerminoRecurso");
		}

	}
	
	private void pesquisarCamposEnter(AtualizarAutoInfracaoActionForm form,
			HttpServletRequest httpServletRequest) {

		
		String idFuncionario = (String) form.getIdFuncionario();

		if (idFuncionario != null && !idFuncionario.equals("")) {
			FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

			filtroFuncionario.adicionarParametro(new ParametroSimples(
					FiltroFuncionario.ID, idFuncionario));

			Collection colecaoFuncionario = this.getFachada().pesquisar(
					filtroFuncionario, Funcionario.class.getName());

			if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

				Funcionario funcionario = (Funcionario) Util
						.retonarObjetoDeColecao(colecaoFuncionario);

				httpServletRequest.setAttribute("funcionario", funcionario
						.getId());
				form.setDescricaoFuncionario(funcionario.getNome());
			} else {
				httpServletRequest.setAttribute("funcionarioInexistente",
						"true");
				form.setIdFuncionario("");
				form.setDescricaoFuncionario("FUNCIONÁRIO INEXISTENTE");
			}
		} else {
			form.setIdFuncionario("");
			form.setDescricaoFuncionario("");
		}

	}

}
