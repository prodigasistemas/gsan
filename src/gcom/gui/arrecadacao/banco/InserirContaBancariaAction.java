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

import gcom.arrecadacao.banco.Agencia;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
import gcom.arrecadacao.banco.FiltroContaBancaria;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.Operacao;
import gcom.seguranca.acesso.OperacaoEfetuada;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirContaBancariaAction extends GcomAction {

	/**
	 * Este caso de uso permite inserir uma Agência Bancária
	 * 
	 * [UC0518] Inserir Conta Bancária
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @author Thiago Tenório
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Obtém a sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		InserirContaBancariaActionForm inserirContaBancariaActionForm = (InserirContaBancariaActionForm) actionForm;

		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_AGENCIA_BANCARIA_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		String banco = inserirContaBancariaActionForm.getBanco();
		String agenciaBancaria = inserirContaBancariaActionForm
				.getAgenciaBancaria();
		String contaBanco = inserirContaBancariaActionForm.getContaBanco();
		String contaContabil = inserirContaBancariaActionForm
				.getContaContabil();

		ContaBancaria contaBancariaInserir = new ContaBancaria();
		Collection colecaoPesquisa = null;

		sessao.removeAttribute("tipoPesquisaRetorno");

		if (Util.validarNumeroMaiorQueZERO(inserirContaBancariaActionForm
				.getBanco())) {
			// Constrói o filtro para pesquisa do Banco
			FiltroBanco filtroBanco = new FiltroBanco();
			filtroBanco.adicionarParametro(new ParametroSimples(
					FiltroContaBancaria.AGENCIA_BANCO_ID,
					inserirContaBancariaActionForm.getBanco()));
		}

		if (Util.validarNumeroMaiorQueZERO(inserirContaBancariaActionForm
				.getAgenciaBancaria())) {
			// Constrói o filtro para pesquisa da Agencia
			FiltroAgencia filtroAgencia = new FiltroAgencia();
			filtroAgencia.adicionarParametro(new ParametroSimples(
					FiltroAgencia.ID, inserirContaBancariaActionForm
							.getAgenciaBancaria()));
			filtroAgencia.adicionarCaminhoParaCarregamentoEntidade("banco");

			// Realiza a pesquisa serviço Agência
			Agencia agencia = (Agencia) fachada.pesquisar(filtroAgencia,
					Agencia.class.getName()).iterator().next();
			contaBancariaInserir.setAgencia(agencia);
		}

		// O numero da Conta é obrigatório.
		if (contaBanco == null || contaBanco.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null,
					"Conta Bancária");
		}
		
		contaBancariaInserir.setNumeroConta(contaBanco);
		
		if (contaContabil != null && !contaContabil.trim().equals("")) {
			contaBancariaInserir.setNumeroContaContabil(new Integer(contaContabil));
		}
		

		// O numero da Conta Contabil é obrigatório.
//		if (contaContabil == null || contaContabil.equalsIgnoreCase("")) {
//			throw new ActionServletException("atencao.required", null,
//					"Nome da Agência Bancária");
//		}

		// Banco.
		if (banco != null
				&& !banco.equalsIgnoreCase(String
						.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

			FiltroBanco filtroBanco = new FiltroBanco();

			filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID,
					banco));

			filtroBanco.adicionarParametro(new ParametroSimples(
					FiltroBanco.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Agencia.
			if (agenciaBancaria != null
					&& !agenciaBancaria.equalsIgnoreCase(String
							.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

				FiltroAgencia filtroAgencia = new FiltroAgencia();

				filtroAgencia.adicionarParametro(new ParametroSimples(
						FiltroAgencia.ID, agenciaBancaria));

				// Ultima alteração
				contaBancariaInserir.setUltimaAlteracao(new Date());

				FiltroContaBancaria filtroContaBancaria = new FiltroContaBancaria();

				filtroContaBancaria.adicionarParametro(new ParametroSimples(
						FiltroContaBancaria.ID, contaBancariaInserir.getId()));

				if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
					// Conta Bancária já existe
					throw new ActionServletException(
							"atencao.pesquisa_agencia_ja_cadastrada", null,
							contaBanco);
				} else {
					Integer idContaBancaria = null;

					idContaBancaria = fachada
							.inserirContaBancaria(contaBancariaInserir);

					montarPaginaSucesso(httpServletRequest,
							"Conta Bancaria de código  "
									+ contaBancariaInserir.getId()
									+ " inserida com sucesso.",
							"Inserir outra Conta Bancaria",
							"exibirInserirContaBancariaAction.do?menu=sim",
							"exibirAtualizarContaBancariaAction.do?inserir=sim&idRegistroAtualizacao="
									+ idContaBancaria,
							"Atualizar Agencia Conta Inserida");

				}

			}
		}
		return retorno;

	}
}