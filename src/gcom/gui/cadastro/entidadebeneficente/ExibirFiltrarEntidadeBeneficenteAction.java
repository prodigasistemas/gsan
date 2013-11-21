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

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



/**

 * Carrega os dados necessários e redireciona para a página que invocará o [UC0917] Filtrar Entidade Beneficente.

 *  

 * @author Hugo Fernando

 * @date 18/01/2010

 * @since 4.1.6.4

 *

 */

public class ExibirFiltrarEntidadeBeneficenteAction extends GcomAction{

	
	@Override
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		 ActionForward retorno = actionMapping.findForward("filtrarEntidadeBeneficente");

		 
		 Fachada fachada = Fachada.getInstancia();

		 

		 FiltrarEntidadeBeneficenteActionForm form = (FiltrarEntidadeBeneficenteActionForm) actionForm;

		 
			if(form.getAtualizar() == null){
				form.setAtualizar("1");
			}
		 
		
			FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
			
			filtroEmpresa.setCampoOrderBy(FiltroEmpresa.DESCRICAO);

			// Verifica se os dados foram informados da tabela existem e joga numa
			// colecao

			Collection<Empresa> colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
					Empresa.class.getName());

			if (colecaoEmpresa == null || colecaoEmpresa.isEmpty()) {
				throw new ActionServletException(
						"atencao.entidade_sem_dados_para_selecao", null,
						"Empresa");
			}

			httpServletRequest.setAttribute("colecaoEmpresa", colecaoEmpresa);
			
			
			
			// Parte que trata do código quando o usuário tecla enter para pesquisar algum campo.  
			Cliente cliente = form.getCliente();
	        
	        //Verifica se o código do Cliente foi digitado
	        if ( cliente != null && cliente.getId() != null && cliente.getId() > 0) {
	        	
	        	FiltroCliente filtroCliente = new FiltroCliente();

	        	filtroCliente.adicionarParametro(new ParametroSimples(
	        			FiltroCliente.ID, cliente.getId()));
				
				Collection<Cliente> ColecaoclienteEncontrado = fachada.pesquisar(filtroCliente,
						Cliente.class.getName());

				if (ColecaoclienteEncontrado != null && !ColecaoclienteEncontrado.isEmpty()) {
					//o Cliente foi encontrado

					
					Cliente clienteEncontrado = (Cliente) Util.retonarObjetoDeColecao(ColecaoclienteEncontrado);
					
					// resgata os parametros do cliente encontrado.
					Integer idClienteEncontrado  = clienteEncontrado.getId();//((Cliente) ((List) ColecaoclienteEncontrado).get(0)).getId();
					String nomeClienteEncontrado = clienteEncontrado.getNome();//((Cliente) ((List) ColecaoclienteEncontrado).get(0)).getNome();
					
					
					
                    // seta os parametros do cliente encontrado no actionForm.
					cliente.setId(idClienteEncontrado);
					cliente.setNome(nomeClienteEncontrado);
					
					form.setCliente(cliente);
					if(form.getDebitoTipo().getId()!= null && form.getDebitoTipo().equals("0")){
					form.getDebitoTipo().setId(null);
					}
					
					httpServletRequest.setAttribute("idClienteEncontrado","true");
					
					httpServletRequest.setAttribute("nomeCampo", "debitoTipo.id");					

				} 
				else {
                 // nao foi encontrado nenhum Cliente.
					
					cliente.setId(null);
					cliente.setNome("CLIENTE NÃO EMCONTRADO");
					if(form.getDebitoTipo().getId()!= null && form.getDebitoTipo().equals("0")){
					form.getDebitoTipo().setId(null);
					}
					
					form.setCliente(cliente);
					httpServletRequest.setAttribute("nomeCampo", "cliente.id");
				}
				
	        }
	        
	        DebitoTipo debitoTipo = form.getDebitoTipo();
	        
	        // Verifica se o tipo de debito foi digitado
	        if( debitoTipo != null && debitoTipo.getId() != null  && debitoTipo.getId().intValue() > 0){
	        	
	        	
	        	FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
	        	
	        	filtroDebitoTipo.adicionarParametro( new ParametroSimples( FiltroDebitoTipo.ID , debitoTipo.getId() ) );
	        	
	        	
				Collection<DebitoTipo> debitoTipoEncontrado = fachada.pesquisar(filtroDebitoTipo,
						DebitoTipo.class.getName());

				if (debitoTipoEncontrado != null && !debitoTipoEncontrado.isEmpty()) {
					// O Debito tipo foi encontrado.
					
					
                    // resgata os parametros do debitoTipo encontrado.
					Integer idDebitoTipo  = ((DebitoTipo) ((List) debitoTipoEncontrado).get(0)).getId();
					String descricaoDebitoTipo = ((DebitoTipo) ((List) debitoTipoEncontrado).get(0)).getDescricao();
					
					
					 // seta os parametros do debitoTipo encontrado no actionForm.
					form.getDebitoTipo().setId(idDebitoTipo);
					form.getDebitoTipo().setDescricao(descricaoDebitoTipo);
					
					if(form.getCliente().getId()!= null && form.getCliente().equals("0")){
					form.getCliente().setId(null);
					}
					httpServletRequest.setAttribute("idDebitoTipoEncontrado","true");

				} 
				else {
                 // nao foi encontrado nenhum DebitoTipo.
					
					form.getDebitoTipo().setId(null);
					form.getDebitoTipo().setDescricao("CLIENTE NÃO EMCONTRADO");
					
					if(form.getCliente().getId()!= null && form.getCliente().equals("0")){
					form.getCliente().setId(null);
					}
				}
				
				httpServletRequest.setAttribute("nomeCampo", "idDebitoTipo");
	        }
	        //-------Fim de parte que trata do código quando o usuário tecla enter
			
			
			

			
			
	        //-------------- bt DESFAZER ---------------
	        if (httpServletRequest.getParameter("desfazer") != null
	                && httpServletRequest.getParameter("desfazer").equalsIgnoreCase("sim")) {
	        	
	        	
	        	form.setAtualizar("");
	        	form.setCliente(null);
	        	form.setDebitoTipo(null);
	        	form.setEmpresa(null);
	        	form.setFimMesAnoAdesao("");
	        	form.setInicioMesAnoAdesao("");
	        	form.setId(null);
	        	form.setUltimaAlteracao(null);

	        }
	        // Fim------------Desfazer--------------------
	        
	        
	        if( form.getCliente() == null ){
	        	form.setCliente(new Cliente());
	        }
	        if( form.getDebitoTipo() == null ){
	        	form.setDebitoTipo(new DebitoTipo());
	        }
	        if( form.getEmpresa() == null ){
	        	form.setEmpresa(new Empresa());
	        }
	        

		return retorno;

	}	
	
}


