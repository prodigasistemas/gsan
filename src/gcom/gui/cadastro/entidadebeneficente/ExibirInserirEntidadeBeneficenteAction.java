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
package gcom.gui.cadastro.entidadebeneficente;

import gcom.cadastro.cliente.Cliente;import gcom.cadastro.cliente.FiltroCliente;import gcom.cadastro.empresa.Empresa;import gcom.cadastro.empresa.FiltroEmpresa;import gcom.fachada.Fachada;import gcom.faturamento.debito.DebitoTipo;import gcom.faturamento.debito.FiltroDebitoTipo;import gcom.gui.ActionServletException;import gcom.gui.GcomAction;import gcom.util.ConstantesSistema;import gcom.util.Util;import gcom.util.filtro.ParametroSimples;import java.util.Collection;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import org.apache.struts.action.ActionForm;import org.apache.struts.action.ActionForward;import org.apache.struts.action.ActionMapping;

/**
 * Carrega os dados necessários e redireciona para a página que invocará o [UC0915] Inserir Entidade Beneficente.
 * Pré-valida algumas informações ao usuário utilizar a tecla enter para selecionar o cliente e o tipo de débito.
 *  
 * @author Samuel Valerio
 * @date 11/06/2009
 * @since 4.1.6.4
 *
 */
public class ExibirInserirEntidadeBeneficenteAction extends GcomAction {
	
	@Override
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		 ActionForward retorno = actionMapping.findForward("inserirEntidadeBeneficente");
		 
		 Fachada fachada = Fachada.getInstancia();
		 
		 EntidadeBeneficenteActionForm form = (EntidadeBeneficenteActionForm) actionForm;
		 
		 httpServletRequest.setAttribute("nomeCampo", "entidadeBeneficente.cliente.id");
		 
		 Cliente cliente = form.getEntidadeBeneficente().getCliente();
		 
		 
		 if (cliente.getId() != null) {
			 FiltroCliente filtroCliente = new FiltroCliente();
			 
			 filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, cliente.getId()));
			 filtroCliente.adicionarCaminhoParaCarregamentoEntidade("clienteTipo");
			 
			 Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
		
			// [FS0001] - Verificar existência do cliente
			if (colecaoCliente == null || colecaoCliente.isEmpty()) {
				if (!new Integer(0).equals(cliente.getId()))
					cliente.setNome("CLIENTE INEXISTENTE");
				cliente.setId(null);
				httpServletRequest.setAttribute("existeCliente","exception");	
				httpServletRequest.setAttribute("nomeCampo", "entidadeBeneficente.cliente.id");
			}else{
				
				Cliente clienteEncontrado = (Cliente) Util.retonarObjetoDeColecao(colecaoCliente);
							
				// [FS0002] - Verificar se cliente é pessoa jurídica
				fachada.validarSeClienteEhPessoaJuridica(clienteEncontrado); 
				
				form.getEntidadeBeneficente().setCliente(clienteEncontrado);
				httpServletRequest.setAttribute("nomeCampo","entidadeBeneficente.debitoTipo.id");
			}
				 
			 
		 }
		 
		DebitoTipo debitoTipo = form.getEntidadeBeneficente().getDebitoTipo();
		 
		if (debitoTipo.getId() != null) {
			
			FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
			filtroDebitoTipo.adicionarParametro(new ParametroSimples(
					FiltroDebitoTipo.ID, debitoTipo.getId()));
		
			Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo,
					DebitoTipo.class.getName());
			
			// [FS0003] - Verificar existência do tipo de débito
			if (colecaoDebitoTipo == null || colecaoDebitoTipo.isEmpty()) {
				if (!new Integer(0).equals(debitoTipo.getId()))
					debitoTipo.setDescricao("TIPO DE DÉBITO INEXISTENTE");
				debitoTipo.setId(null);
				httpServletRequest.setAttribute("existeDebitoTipo","exception");	
				httpServletRequest.setAttribute("nomeCampo", "entidadeBeneficente.debitoTipo.id");
			} else {
				DebitoTipo debitoTipoEncontrado = (DebitoTipo) Util.retonarObjetoDeColecao(colecaoDebitoTipo);
				
				// [FS0004] - Verificar se tipo de débito não é gerado automaticamente
				fachada.validarSeDebitoTipoNaoEhGeradoAutomaticamente(debitoTipoEncontrado);
				
				form.getEntidadeBeneficente().setDebitoTipo(debitoTipoEncontrado);
				httpServletRequest.setAttribute("nomeCampo", "entidadeBeneficente.empresa.id");
			}
		
		} 
		 
		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		filtroEmpresa.adicionarParametro(new ParametroSimples(FiltroEmpresa.INDICADORUSO, ConstantesSistema.INDICADOR_USO_ATIVO));
		filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);
		 
		Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());
		 
		if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) { 
			throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Empresa");
		}

		httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
		 
		return retorno;
	}
	
}
