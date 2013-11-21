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
import gcom.arrecadacao.banco.Banco;
import gcom.arrecadacao.banco.FiltroAgencia;
import gcom.arrecadacao.banco.FiltroBanco;
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

public class InserirAgenciaBancariaAction extends GcomAction {

	/**
	 * Este caso de uso permite inserir uma Agência Bancária
	 * 
	 * [UC0515] Inserir Agência Bancária
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

		InserirAgenciaBancariaActionForm inserirAgenciaBancariaActionForm = (InserirAgenciaBancariaActionForm) actionForm;

		// ------------ REGISTRAR TRANSAÇÃO ----------------
		Operacao operacao = new Operacao();
		operacao.setId(Operacao.OPERACAO_AGENCIA_BANCARIA_INSERIR);

		OperacaoEfetuada operacaoEfetuada = new OperacaoEfetuada();
		operacaoEfetuada.setOperacao(operacao);
		// ------------ REGISTRAR TRANSAÇÃO ----------------

		String codigo = inserirAgenciaBancariaActionForm.getCodigo();
		String nome = inserirAgenciaBancariaActionForm.getNome();
		String bancoID = inserirAgenciaBancariaActionForm.getBancoID();
		Collection colecaoEnderecos = (Collection) sessao
				.getAttribute("colecaoEnderecos");
		String telefone = inserirAgenciaBancariaActionForm.getTelefone();
		String ramal = inserirAgenciaBancariaActionForm.getRamal();
		String fax = inserirAgenciaBancariaActionForm.getFax();
		String email = inserirAgenciaBancariaActionForm.getEmail();

		Agencia agenciaInserir = new Agencia();
		Collection colecaoPesquisa = null;

		sessao.removeAttribute("tipoPesquisaRetorno");
		
		if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
        	Agencia agenciaEndereco = (Agencia) Util
            .retonarObjetoDeColecao(colecaoEnderecos);
        	
        	agenciaInserir = agenciaEndereco;
        	
        }
//		else {
//        	throw new ActionServletException(
//					"atencao.campo_selecionado.obrigatorio", null, "Endereço");
//        }

		if (Util.validarNumeroMaiorQueZERO(inserirAgenciaBancariaActionForm
				.getBancoID())) {
			// Constrói o filtro para pesquisa do serviço tipo referência
			FiltroBanco filtroBanco = new FiltroBanco();
			filtroBanco.adicionarParametro(new ParametroSimples(
					FiltroBanco.ID, inserirAgenciaBancariaActionForm
							.getBancoID()));
			
			Collection colecaoBancos = (Collection) fachada.pesquisar(
					filtroBanco, Banco.class.getName());

			// setando
			agenciaInserir.setBanco((Banco) colecaoBancos.iterator().next());
		}

        agenciaInserir.setNomeAgencia(nome);
		
		// O código da Agência Bancaria é obrigatório.
		if (codigo == null || codigo.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null, "Código da Agência Bancária");
		}

		// O nome da Agência Bancaria é obrigatório.
		if (nome == null || nome.equalsIgnoreCase("")) {
			throw new ActionServletException("atencao.required", null, "Nome da Agência Bancária");
		}

		agenciaInserir.setCodigoAgencia(codigo);
		agenciaInserir.setNomeAgencia(nome);

		// O telefone é obrigatório caso o ramal tenha sido informado.
		if (ramal != null && !ramal.equalsIgnoreCase("")) {
			agenciaInserir.setNumeroRamal(ramal);
			if (telefone == null || telefone.equalsIgnoreCase("")) {
				throw new ActionServletException(
						"atencao.telefone_agencia_bancaria_nao_informado");
			} else if (telefone.length() < 7) {
				throw new ActionServletException(
						"atencao.telefone_ou_fax_agencia_bancaria_menor_sete_digitos",
						null, "Telefone");
			}
		}

		// Telefone.
		if (telefone != null && !telefone.equalsIgnoreCase("")) {
			if (telefone.length() < 7) {
				throw new ActionServletException(
						"atencao.telefone_ou_fax_agencia_bancaria_menor_sete_digitos",
						null, "Telefone");
			} else {
				agenciaInserir.setNumeroTelefone(telefone);
			}
		}

		// Fax.
		if (fax != null && !fax.equalsIgnoreCase("")) {
			if (fax.length() < 7) {
				throw new ActionServletException(
						"atencao.telefone_ou_fax_agencia_bancaria_menor_sete_digitos",
						null, "Fax");
			} else {
				agenciaInserir.setNumeroFax(fax);
			}
		}

		// E-mail.
		if (email != null && !email.equalsIgnoreCase("")) {
			agenciaInserir.setEmail(email);
		}

		// Banco.
		Banco banco = new Banco();
		if (bancoID != null
				&& !bancoID.equalsIgnoreCase(String
						.valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {

			FiltroBanco filtroBanco = new FiltroBanco();

			filtroBanco.adicionarParametro(new ParametroSimples(FiltroBanco.ID,
					bancoID));

			filtroBanco.adicionarParametro(new ParametroSimples(
					FiltroBanco.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			// Retorna localidade - Elo
			colecaoPesquisa = fachada.pesquisar(filtroBanco, Banco.class
					.getName());

			if (colecaoPesquisa == null || colecaoPesquisa.isEmpty()) {
				// O código do Elo não existe na tabela Localidade
				throw new ActionServletException(
						"atencao.pesquisa_elo_nao_inexistente");
			} else {
				banco = (Banco) Util.retonarObjetoDeColecao(colecaoPesquisa);
				if (banco.getId().intValue() != banco.getId().intValue()) {
					// A localidade escolhida não é um Elo
					throw new ActionServletException(
							"atencao.localidade_nao_e_elo");
				} else {
					agenciaInserir.setBanco(banco);
				}
			}
		}

		// Ultima alteração
		agenciaInserir.setUltimaAlteracao(new Date());

	      FiltroAgencia filtroAgencia = new FiltroAgencia();

	        filtroAgencia.adicionarParametro(new ParametroSimples(
	        		FiltroAgencia.CODIGO_AGENCIA, agenciaInserir.getCodigoAgencia()));
	        filtroAgencia.adicionarParametro(new ParametroSimples(
	        		FiltroAgencia.BANCO_ID, bancoID));

		// Verificar existência da Agência Bancária
		colecaoPesquisa = fachada.pesquisar(filtroAgencia, Agencia.class
				.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// Gerencia Regional já existe
			throw new ActionServletException(
					"atencao.pesquisa_agencia_ja_cadastrada", null, codigo);
		} else {
			Integer idAgencia = null;

			idAgencia = fachada.inserirAgenciaBancaria(agenciaInserir);
			
			montarPaginaSucesso(httpServletRequest,
					"Agência Bancaria de código  "
							+ agenciaInserir.getCodigoAgencia()
							+ " inserida com sucesso.",
					"Inserir outra Agência Bancaria",
					"exibirInserirAgenciaBancariaAction.do?menu=sim",
					"exibirAtualizarAgenciaBancariaAction.do?inserir=sim&idRegistroAtualizacao="
							+ idAgencia, "Atualizar Agencia Bancaria Inserida");

		}

		sessao.removeAttribute("colecaoEnderecos");

		// devolve o mapeamento de retorno
		return retorno;
	}

}
