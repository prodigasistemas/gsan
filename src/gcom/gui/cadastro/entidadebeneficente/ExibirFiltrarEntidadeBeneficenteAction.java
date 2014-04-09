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


