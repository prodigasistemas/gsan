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
import gcom.cadastro.cliente.ClienteEndereco;
import gcom.cadastro.cliente.FiltroClienteEndereco;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.FiltroUsuarioPemissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioPermissaoEspecial;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Iterator;

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
public class AtualizarClienteEnderecoAction extends GcomAction {

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

		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        Fachada fachada = Fachada.getInstancia();
        
		Collection colecaoEnderecos = (Collection) sessao
				.getAttribute("colecaoEnderecos");

		if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {

			DynaValidatorForm clienteActionForm = (DynaValidatorForm) actionForm;

			// Radio que indica qual o endereco de correspondencia
			Long enderecoCorrespondenciaSelecionado = (Long) clienteActionForm
					.get("enderecoCorrespondenciaSelecao");
	        String nome = clienteActionForm.get("nome").toString();

			// Se o end. de correspondencia for escolhido então o objeto deve
			// ser alterado
			if (enderecoCorrespondenciaSelecionado != null) {
				Iterator iterator = colecaoEnderecos.iterator();

				// Varre a colecão para descobrir o objeto que tem o endereço de
				// correspondencia
				while (iterator.hasNext()) {
					ClienteEndereco clienteEndereco = (ClienteEndereco) iterator
							.next();

					if (obterTimestampIdObjeto(clienteEndereco) == enderecoCorrespondenciaSelecionado
							.longValue()) {
						// Indica que o objeto possui o endereço de
						// correspondencia
						clienteEndereco
								.setIndicadorEnderecoCorrespondencia(ConstantesSistema.INDICADOR_ENDERECO_CORRESPONDENCIA);
					} else {
						// Indica que o objeto não possui o endereço de
						// correspondencia
						clienteEndereco
								.setIndicadorEnderecoCorrespondencia(ConstantesSistema.INDICADOR_NAO_ENDERECO_CORRESPONDENCIA);
					}
				}

			} else {
				// Nenhum endereço foi indicado como endereço de correspondencia
				throw new ActionServletException(
						"atencao.endereco_correspondencia.nao_selecionado");

			}
			

			/**
			 * Autor: Mariana Victor
			 * Data:  28/12/2010
			 * RM_3320 - [FS0010] Verificar Duplicidade de cliente
			 */
			if (this.getSistemaParametro().getIndicadorDuplicidadeCliente().toString()
					.equals(ConstantesSistema.SIM.toString())) {
				
				FiltroUsuarioPemissaoEspecial filtroUsuarioPemissaoEspecial = new FiltroUsuarioPemissaoEspecial();
				filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.USUARIO_ID, usuario.getId()));
				filtroUsuarioPemissaoEspecial.adicionarParametro(new ParametroSimples(FiltroUsuarioPemissaoEspecial.PERMISSAO_ESPECIAL_ID, PermissaoEspecial.INSERIR_CLIENTE_COM_MESMO_NOME_E_ENDERECO));
										
				Collection colecaoUsuarioPermisao = fachada.pesquisar(filtroUsuarioPemissaoEspecial, UsuarioPermissaoEspecial.class.getName());
				
				if (colecaoUsuarioPermisao == null || colecaoUsuarioPermisao.isEmpty()) {
					FiltroClienteEndereco filtroClienteEndereco = new FiltroClienteEndereco();
					filtroClienteEndereco.adicionarParametro(new ParametroSimples(FiltroClienteEndereco.NOME, nome.toUpperCase()));

					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.logradouro.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoReferencia");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroBairro.bairro.municipio.unidadeFederacao");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("logradouroCep.cep");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroInicial.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("perimetroFinal.logradouroTitulo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("enderecoTipo");
					filtroClienteEndereco.adicionarCaminhoParaCarregamentoEntidade("cliente");
					
					Collection<ClienteEndereco> colecaoClienteEndereco = fachada.pesquisar(filtroClienteEndereco, ClienteEndereco.class.getName());
					
					if (colecaoClienteEndereco != null && !colecaoClienteEndereco.isEmpty()){
						Iterator iterator = colecaoClienteEndereco.iterator();

						// Pega o cliente que foi selecionado para atualização
						Cliente clienteAtualizacao = (Cliente) sessao
								.getAttribute("clienteAtualizacao");
						
						while (iterator.hasNext()) {
							ClienteEndereco clienteEnderecoIterator = (ClienteEndereco) iterator.next();
							
							Iterator iteratorEnderecos = colecaoEnderecos.iterator();
							while (iteratorEnderecos.hasNext()) {
								ClienteEndereco clienteEndereco = (ClienteEndereco) iteratorEnderecos
										.next();
								
								if (clienteEndereco.getEnderecoFormatado().equals(
										clienteEnderecoIterator.getEnderecoFormatado())
										&& !clienteAtualizacao.getId().equals(
												clienteEnderecoIterator.getCliente().getId())) {
									throw new ActionServletException("atencao.duplicidade.cliente", null,
										"Cliente");
								}
							}
						}
					}	
					
				}
				
			}

		} else {
			// O usuário deve preencher pelo menos um endereço e marcá-lo como
			// endereço de correspondência
			// Mostra o erro
			throw new ActionServletException(
					"atencao.endereco_correspondencia.nao_selecionado");

		}
		return retorno;
	}
}
