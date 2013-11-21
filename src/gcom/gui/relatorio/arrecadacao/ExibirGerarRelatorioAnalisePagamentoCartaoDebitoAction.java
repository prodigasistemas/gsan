package gcom.gui.relatorio.arrecadacao;

import java.util.Collection;

import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.FiltroUsuario;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * 
 * [UC1043] Gerar Relatório Análise Pagamento Cartão Débito
 * 
 * @author Hugo Amorim
 * @since 21/07/2010
 *
 */
public class ExibirGerarRelatorioAnalisePagamentoCartaoDebitoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	
		RelatorioAnalisePagamentoCartaoDebitoForm form 
			= (RelatorioAnalisePagamentoCartaoDebitoForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		if(httpServletRequest.getParameter("pesquisarUsuarioConfirmacao")!=null
				&& httpServletRequest.getParameter("pesquisarUsuarioConfirmacao").toString().equalsIgnoreCase("SIM")
				&& form.getIdUsuarioConfirmacao()!=null 
				&& !form.getIdUsuarioConfirmacao().equals("")){
			
			this.pesquisarUsuario(form, fachada, httpServletRequest);
			
		}	
		
		return 
			actionMapping.findForward(
					"exibirGerarRelatorioAnalisePagamentoCartaoDebitoAction");
	}
	
	
	
	/*
	 *	Realiza pesquisa caso usuário tenha digitado o id do usuário e 
	 * pressionado enter.
	 * 
	 */
	private void pesquisarUsuario(
			RelatorioAnalisePagamentoCartaoDebitoForm form,
			Fachada fachada,
			HttpServletRequest httpServletRequest) {
		
		FiltroUsuario filtro = new FiltroUsuario();
		
		filtro.adicionarParametro(new ParametroSimples(FiltroUsuario.ID, form.getIdUsuarioConfirmacao()));
		
		Collection<Usuario> colecaoUsuariosConfirmacao = fachada.pesquisar(filtro, Usuario.class.getName());
		
		if(colecaoUsuariosConfirmacao!=null && !colecaoUsuariosConfirmacao.isEmpty()){
			
			Usuario usuarioConfirmacao = colecaoUsuariosConfirmacao.iterator().next();
			
			form.setIdUsuarioConfirmacao(usuarioConfirmacao.getId().toString());
			form.setDescricaoUsuarioConfirmacao(usuarioConfirmacao.getNomeUsuario());	
			
		}else{
			form.setIdUsuarioConfirmacao("");
			form.setDescricaoUsuarioConfirmacao("USUARIO INEXISTENTE");
			httpServletRequest.setAttribute("usuarioConfirmacaoInexistente",true);		
		}
		
	}
}
