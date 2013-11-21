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
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.autoinfracao.AutoInfracaoSituacao;
import gcom.faturamento.autoinfracao.FiltroAutoInfracaoSituacao;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirFiltrarAutoInfracaoAction extends GcomAction {

	/**
	 * Filtrar Autos de Infração
	 * 
	 * @author Rômulo Aurélio - 22/04/2009
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarAutoInfracaoAction");

		HttpSession sessao = httpServletRequest.getSession(false);
		
		if(sessao.getAttribute("objetoAutosInfracao")!=null){
			sessao.removeAttribute("objetoAutosInfracao");
		}

		FiltrarAutoInfracaoActionForm form = (FiltrarAutoInfracaoActionForm) actionForm;
		
		this.carregarDados(form, httpServletRequest);

		this.pesquisarCamposEnter(form, httpServletRequest);

		return retorno;
	}

	private void carregarDados(FiltrarAutoInfracaoActionForm form,
			HttpServletRequest httpServletRequest) {
		
		form.setAtualizar("1");

		FiltroFiscalizacaoSituacao filtroFiscalizacaoSituacao = new FiltroFiscalizacaoSituacao();

		filtroFiscalizacaoSituacao.adicionarParametro(new ParametroSimples(
				FiltroFiscalizacaoSituacao.INDICADOR_ATUALIZACAO_AUTOS_INFRACAO,
				ConstantesSistema.INDICADOR_USO_ATIVO));
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

	private void pesquisarCamposEnter(FiltrarAutoInfracaoActionForm form,
			HttpServletRequest httpServletRequest) {

		String idImovel = (String) form.getIdImovel();

		if (idImovel != null && !idImovel.equals("")) {
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
			filtroImovel
					.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
			filtroImovel.adicionarParametro(new ParametroSimples(
					FiltroImovel.ID, idImovel));

			Collection imoveis = this.getFachada().pesquisar(filtroImovel,
					Imovel.class.getName());

			if (imoveis != null && !imoveis.isEmpty()) {
				Imovel imovel = (Imovel) ((List) imoveis).get(0);
				httpServletRequest.setAttribute("imovel", imovel);
				form.setDescricaoImovel(imovel.getInscricaoFormatada());
			} else {
				httpServletRequest.setAttribute("matriculaInexistente", "true");
				form.setIdImovel("");
				form.setDescricaoImovel("MATRÍCULA INEXISTENTE");
			}
		} else {
			form.setIdImovel("");
			form.setDescricaoImovel("");
		}

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
