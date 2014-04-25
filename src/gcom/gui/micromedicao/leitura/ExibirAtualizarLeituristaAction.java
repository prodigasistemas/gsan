package gcom.gui.micromedicao.leitura;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.micromedicao.leitura.AtualizarLeituristaActionForm;
import gcom.micromedicao.FiltroLeiturista;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Thiago Tenório e Thiago Nascimento
 * @date 30/10/2006
 */
public class ExibirAtualizarLeituristaAction extends GcomAction {
	/**
	 * [UC0589] Manter Leiturista
	 * 
	 * Este caso de uso permite alterar os dados do leiturista
	 * 
	 * @author Thiago Tenório e Thiago Nascimento
	 * @date 31/10/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarLeiturista");

		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarLeituristaActionForm form = (AtualizarLeituristaActionForm) actionForm;

		Integer id = null;
		
		Leiturista leiturista = null;
		
		Fachada fachada = Fachada.getInstancia();
		
		//Usuário
		if (form.getLoginUsuario() != null &&
			!form.getLoginUsuario().equals("")) {
			
			getUsuario(form, fachada,
					form.getLoginUsuario(), sessao);
		}		
		

		if (httpServletRequest.getParameter("idRegistroAtualizacao") != null
				&& !httpServletRequest.getParameter("idRegistroAtualizacao").toString().trim().equals("")) {
			
			id = new Integer(httpServletRequest.getParameter("idRegistroAtualizacao").toString());				
						
			if(id!=null){
				leiturista = this.buscarLeiturista(id,fachada);
				this.exibirDadosLeiturista(form,leiturista,sessao);				
			}		
		}
		
		if (leiturista == null) {
			leiturista = (Leiturista) sessao.getAttribute("leiturista");
			exibirDadosLeiturista(form, leiturista,sessao);
		}
		
		// Bloquear o campo Empresa quando a empresa do usuario nao seja a empresa do sistema
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
		if (usuario.getEmpresa() == null) {
			throw new ActionServletException("atencao.pesquisa_inexistente", null, "Empresa do usuário logado.");
		}
		if (!usuario.getEmpresa().getDescricao().equals(sistemaParametro.getNomeAbreviadoEmpresa())) {
			sessao.setAttribute("bloquearEmpresa", true);
			form.setEmpresaID(usuario.getEmpresa().getId().toString());
		}

		FiltroEmpresa filtroEmpresa = new FiltroEmpresa();

		Collection colecaoEmpresa = fachada.pesquisar(filtroEmpresa,
				Empresa.class.getName());

		sessao.setAttribute("colecaoEmpresa", colecaoEmpresa);
		
		// Desfazer
		if (httpServletRequest.getParameter("desfazer") != null
				&& httpServletRequest.getParameter("desfazer")
						.equalsIgnoreCase("S")) {
			if (sessao.getAttribute("leiturista") != null) {
				leiturista = (Leiturista)sessao.getAttribute("leiturista");
			}else{
				leiturista = this.buscarLeiturista(new Integer(form.getId()),fachada);
			}
			 if(leiturista!=null){       	
				 this.exibirDadosLeiturista(form,leiturista,sessao);
				 sessao.setAttribute("leiturista", leiturista);
			 }
		    
			
		}else{
			
			if ((form.getIdCliente() != null && !form.getIdCliente().trim().equals(""))) {
				this.buscarCliente(form,fachada,httpServletRequest);
			}
			
			if(form.getIdFuncionario() != null && !form.getIdFuncionario().trim().equals("")){
				this.buscarFuncionario(form,fachada,httpServletRequest);
			}
			if (sessao.getAttribute("leiturista") == null) {
				if(leiturista==null){					 
					 leiturista = this.buscarLeiturista(new Integer(form.getId()),fachada);
				 }
				sessao.setAttribute("leiturista", leiturista);
			}				
			
		
		
			
			
		}
			

		return retorno;

	}
	
	
	/**
	 * 
	 * Metodo auxiliar para preencher o form com os valores do leiturista
	 * 
	 * @param form
	 * @param id
	 * @param fachada
	 */
	private void exibirDadosLeiturista(AtualizarLeituristaActionForm form, Leiturista leiturista,HttpSession sessao){
			        	 	
        	form.setDdd(leiturista.getCodigoDDD());
        	
        	if(leiturista.getCliente()!=null){
        		form.setDescricaoCliente(leiturista.getCliente().getNome());
        		form.setIdCliente(leiturista.getCliente().getId().toString());
        	}else{
        		form.setDescricaoCliente("");
        		form.setIdCliente("");
        	}
        	
        	if(leiturista.getFuncionario()!=null){
        		form.setDescricaoFuncionario(leiturista.getFuncionario().getNome());
        		form.setIdFuncionario(leiturista.getFuncionario().getId().toString());
        	}else{
        		form.setDescricaoFuncionario("");
        		form.setIdFuncionario("");
        	}
        	
        	form.setEmpresaID(leiturista.getEmpresa().getId().toString());
        	
        	form.setId(leiturista.getId().toString());
        	
        	form.setNumeroImei(leiturista.getNumeroImei());
        	
        	form.setTelefone(leiturista.getNumeroFone());
        	
        	form.setIndicadorUso(leiturista.getIndicadorUso().toString());
        	
        	if ( leiturista.getUsuario() != null ){
    			// Filtra Usuario
        		if(form.getLoginUsuario() != null && !form.getLoginUsuario().equals("") && 
        				leiturista.getUsuario() != null && !leiturista.getUsuario().getLogin().equals(form.getLoginUsuario())){
	    			FiltroUsuario filtroUsuario = new FiltroUsuario();
	    			filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, form.getLoginUsuario() ) );		
	    			
	    			// Recupera Usuário
	    			Collection<Usuario> colecaoUsuario = Fachada.getInstancia().pesquisar(filtroUsuario, Usuario.class.getName());
	    			if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
	    				Usuario usuario = colecaoUsuario.iterator().next();
	    				
	    				form.setLoginUsuario( usuario.getLogin() );
	    				form.setNomeUsuario( usuario.getNomeUsuario() );
	    				sessao.setAttribute("usuarioEncontrado","SIM");
	    			}else{
	    				form.setLoginUsuario( "" );
	    				form.setNomeUsuario( "USUARIO INEXISTENTE" ); 
	    				sessao.removeAttribute("usuarioEncontrado");
	    			}
        		}else{
        			Usuario usuario = leiturista.getUsuario();
    				
    				form.setLoginUsuario( usuario.getLogin() );
    				form.setNomeUsuario( usuario.getNomeUsuario() );
    				sessao.setAttribute("usuarioEncontrado","SIM");
        		}
        	} 
	}
	
	/**
	 * 
	 * Método Auxiliar para buscar os dados do cliente
	 * 
	 * @param form
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void buscarCliente(AtualizarLeituristaActionForm form , Fachada fachada, HttpServletRequest httpServletRequest){
		

			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.ID, form.getIdCliente()));

			Collection colecaoCliente = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (colecaoCliente != null && !colecaoCliente.isEmpty()) {

				Cliente cliente = (Cliente) colecaoCliente.iterator().next();

				form.setIdCliente(cliente.getId().toString());
				form.setDescricaoCliente(cliente.getNome());

			} else {
				httpServletRequest.setAttribute("clienteEncontrado",
						"exception");
				form.setIdCliente("");
				form.setDescricaoCliente("CLIENTE INEXISTENTE");
			}

		
	}
	
	/**
	 * 
	 * Método auxilixar para buscar os dados do funcionário
	 * 
	 * @param form
	 * @param fachada
	 * @param httpServletRequest
	 */
	private void buscarFuncionario(AtualizarLeituristaActionForm form , Fachada fachada, HttpServletRequest httpServletRequest){
		FiltroFuncionario filtroFuncionario = new FiltroFuncionario();

		filtroFuncionario.adicionarParametro(new ParametroSimples(
				FiltroFuncionario.ID, form.getIdFuncionario()));

		Collection colecaoFuncionario = fachada.pesquisar(
				filtroFuncionario, Funcionario.class.getName());

		if (colecaoFuncionario != null && !colecaoFuncionario.isEmpty()) {

			Funcionario funcionario = (Funcionario) colecaoFuncionario
					.iterator().next();
			form.setIdFuncionario(funcionario.getId().toString());
			form.setDescricaoFuncionario(funcionario.getNome());

		} else {
			httpServletRequest.setAttribute("funcionarioEncontrado",
					"exception");
			form.setIdFuncionario("");
			form.setDescricaoFuncionario("FUNCIONARIO INEXISTENTE");
		}
	}
	
	/**
	 * 
	 * Método auxiliar para buscar um Leiturista
	 * 
	 * @param id
	 * @param fachada
	 * @return
	 */
	private Leiturista buscarLeiturista(Integer id, Fachada fachada){
		
		Leiturista leiturista = null;
		
		FiltroLeiturista filtroLeiturista = new FiltroLeiturista();
		filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.CLIENTE);
        filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.FUNCIONARIO);
        filtroLeiturista.adicionarCaminhoParaCarregamentoEntidade(FiltroLeiturista.USUARIO);
        filtroLeiturista.adicionarParametro(new ParametroSimples(FiltroLeiturista.ID,id));
               
        Collection colecao = fachada.pesquisar(filtroLeiturista,Leiturista.class.getName());
        if(colecao!=null && !colecao.isEmpty()){
        	leiturista = (Leiturista)colecao.iterator().next();
        }
        
        return leiturista;
	}
	
	/**
	 * Recupera o Usuário
	 *
	 * @author Bruno Barros
	 * @date 11/12/2006
	 *
	 * @param atualizarLeituristaActionForm
	 * @param fachada
	 * @param idUsuario
	 * @return Descrição da Unidade Filtrada
	 */
	private void getUsuario(AtualizarLeituristaActionForm atualizarLeituristaActionForm, 
			Fachada fachada, String idUsuario, HttpSession sessao) {
		
		// Filtra Usuario
		FiltroUsuario filtroUsuario = new FiltroUsuario();
		filtroUsuario.adicionarParametro(new ComparacaoTexto(FiltroUsuario.LOGIN, idUsuario));		
		
		// Recupera Usuário
		Collection<Usuario> colecaoUsuario = fachada.pesquisar(filtroUsuario, Usuario.class.getName());
		if (colecaoUsuario != null && !colecaoUsuario.isEmpty()) {
			Usuario usuario = colecaoUsuario.iterator().next();
			
			sessao.setAttribute("usuarioEncontrado","true");
			atualizarLeituristaActionForm.setNomeUsuario(usuario.getNomeUsuario());
		} else {
			
			sessao.removeAttribute("usuarioEncontrado");
			atualizarLeituristaActionForm.setLoginUsuario("");
			atualizarLeituristaActionForm.setNomeUsuario("Usuário Inexistente");
		}
	}
}
