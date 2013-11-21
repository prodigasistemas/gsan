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
package gcom.gui.cadastro.cliente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.ClienteTipo;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;

/**
 * Description of the Class
 * 
 * @author Rodrigo
 */
public class InserirClientePessoaAction extends GcomAction {

	/**
	 * Description of the Method
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

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping
				.findForward("gerenciadorProcesso");

		// Pega o actionform da sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		DynaValidatorForm form = (DynaValidatorForm) sessao
				.getAttribute("ClienteActionForm");

		Fachada fachada = Fachada.getInstancia();

		// Verifica se o usuário informou o CPF/RG - CNPJ
		String cpf = (String) form.get("cpf");
		String rg = (String) form.get("rg");
		String cnpj = (String) form.get("cnpj");

		// O usuário é pessoa física
		if (cpf != null && !cpf.trim().equalsIgnoreCase("")) {

			boolean igual = true;
			Integer i = 0;

//			Integer tam = cpf.length();
//
//			while (i < tam - 1) {
//				if ((cpf.charAt(i)) == (cpf.charAt(i + 1))) {
//					i = i + 1;
//				} else {
//					igual = false;
//				}
//				i = i + 1;
//			}
            
            Integer tam = cpf.length() - 1;
            
            while ( i < tam ) {
                if ( (cpf.charAt(i)) != (cpf.charAt(i + 1)) ){
                    igual = false;
                    break;
                } else {
                    i++;
                }
            }

			if (igual) {
				throw new ActionServletException("atencao.cpf_invalido");
			}

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CPF, cpf));

			Collection colecaoClienteComCpf = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (!colecaoClienteComCpf.isEmpty()) {
				Cliente clienteComCpf = (Cliente) colecaoClienteComCpf
						.iterator().next();

				throw new ActionServletException(
						"atencao.cpf.cliente.ja_cadastrado", null, ""
								+ clienteComCpf.getId());
			}
		}

		if (rg != null && !rg.trim().equalsIgnoreCase("")) {
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.RG, rg));

			Integer idOrgaoExpedidor = new Integer(form.get("idOrgaoExpedidor")
					.toString());

			// filtroCliente.adicionarCaminhoParaCarregamentoEntidade("orgaoExpedidorRg");

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ORGAO_EXPEDIDOR_RG_ID, idOrgaoExpedidor
							.intValue()));

			Integer idUnidadeFederacao = new Integer(form.get(
					"idUnidadeFederacao").toString());
			filtroCliente
					.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.UNIDADE_FEDERACAO_ID, idUnidadeFederacao
							.intValue()));

			Collection colecaoClienteComRg = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (!colecaoClienteComRg.isEmpty()) {
				Cliente clienteComRg = (Cliente) colecaoClienteComRg.iterator()
						.next();

				throw new ActionServletException(
						"atencao.rg.cliente.ja_cadastrado", null, ""
								+ clienteComRg.getId());
			}
		}

		// O usuário é pessoa jurídica
		if (cnpj != null && !cnpj.trim().equalsIgnoreCase("")) {
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.CNPJ, cnpj));

			Collection colecaoClienteComCnpj = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (!colecaoClienteComCnpj.isEmpty()) {
				Cliente clienteComCnpj = (Cliente) colecaoClienteComCnpj
						.iterator().next();

				throw new ActionServletException(
						"atencao.cnpj.cliente.ja_cadastrado", null, ""
								+ clienteComCnpj.getId());
			}
		}

		// Pega o codigo do cliente(que vem de pessoa jurídica)
		String codigoClienteResponsavel = (String) form
				.get("codigoClienteResponsavel");

		// Verificar se a data de emissao do rg é menor que a data atual do
		// sistema
		String dataEmissaoRg = (String) form.get("dataEmissao");

		if (dataEmissaoRg != null && !dataEmissaoRg.trim().equals("")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			try {
				if (!verificarDataMenorQueDataCorrente(dateFormat
						.parse(dataEmissaoRg))) {
					throw new ActionServletException(
							"atencao.data_menor_que_atual", null,
							"emissão do RG");
				}
			} catch (ParseException ex) {
				throw new ActionServletException("erro.sistema");
			}
		}

		// Verificar se a data de nascimento é menor que a data atual do sistema
		String dataNascimento = (String) form.get("dataNascimento");

		if (dataNascimento != null && !dataNascimento.trim().equals("")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			try {
				if (!verificarDataMenorQueDataCorrente(dateFormat
						.parse(dataNascimento))) {
					throw new ActionServletException(
							"atencao.data_menor_que_atual", null, "nascimento");
				}
			} catch (ParseException ex) {
				throw new ActionServletException("erro.sistema");
			}
		}

		// Verificar se a data de nascimento é maior que a data de emissão de rg
		if (dataNascimento != null && !dataNascimento.trim().equals("")
				&& dataEmissaoRg != null && !dataEmissaoRg.trim().equals("")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			try {
				if (dateFormat.parse(dataNascimento).after(
						dateFormat.parse(dataEmissaoRg))) {
					throw new ActionServletException(
							"atencao.data_nascimento_maior_que_data_emissao");
				}
			} catch (ParseException ex) {
				throw new ActionServletException("erro.sistema");
			}
		}

		// Verifica o código se ele estiver presente
		if (codigoClienteResponsavel != null
				&& !codigoClienteResponsavel.trim().equals("")) {
			FiltroCliente filtro = new FiltroCliente();

			filtro.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
					codigoClienteResponsavel));
			filtro.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));
			filtro.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");

			// Pesquisa o código para verificar a existência dele
			Collection clienteEncontrado = fachada.pesquisar(filtro,
					Cliente.class.getName());

			// Se o código não existir então o usuário passou um código que não
			// foi pesquisado na página
			if (clienteEncontrado.isEmpty()) {
				// Mostra o erro
				throw new ActionServletException(
						"atencao.cliente.responsavel.inexitente");
			}

			// O cliente foi encontrado
			Cliente encontrado = (Cliente) ((List) clienteEncontrado).get(0);

			if (encontrado.getClienteTipo().getIndicadorPessoaFisicaJuridica()
					.equals(ClienteTipo.INDICADOR_PESSOA_FISICA)) {
				// O usuário digitou uma pessoa física
				// limpa a seleção do usuário do form
				throw new ActionServletException(
						"atencao.responsavel.pessoa_juridica");
			}
		}

		return retorno;
	}
}
