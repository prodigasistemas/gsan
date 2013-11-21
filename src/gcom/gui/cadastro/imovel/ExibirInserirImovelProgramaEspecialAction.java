package gcom.gui.cadastro.imovel;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0973] Inserir Imóvel em Programa Especial
 * 
 * @author Hugo Amorim
 * @date 17/12/2009
 */
public class ExibirInserirImovelProgramaEspecialAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirImovelProgramaEspecialAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		InserirImovelProgramaEspecialActionForm inserirImovelProgramaEspecialActionForm = (InserirImovelProgramaEspecialActionForm) actionForm;
		
		// Verifica se entrou apartir
		// do menu
		//
		if(httpServletRequest.getParameter("menu")!=null
				&& httpServletRequest.getParameter("menu").toString().equalsIgnoreCase("sim")){
			
			this.limpar(inserirImovelProgramaEspecialActionForm, sessao);
			
		}
		
		
		
		// Pesquisa Imovel
		// Enter ou Reload PopUp
		//
		if(httpServletRequest.getParameter("pesquisarImovel")!=null
				&& httpServletRequest.getParameter("pesquisarImovel").toString().equalsIgnoreCase("sim")){
			
			if(httpServletRequest.getParameter("idImovel")!=null
					&& !httpServletRequest.getParameter("idImovel").toString().equals("")){
				
				inserirImovelProgramaEspecialActionForm.setMatriculaImovel(httpServletRequest.getParameter("idImovel"));	
			}
			
			if(inserirImovelProgramaEspecialActionForm.getMatriculaImovel()!=null && 
				!inserirImovelProgramaEspecialActionForm.getMatriculaImovel().equals("")){
								
				getImovel(inserirImovelProgramaEspecialActionForm,httpServletRequest);
			}
			
		}
		
		
		return retorno;
	}
	
	/**
	 * Recupera Imóvel 
	 *
	 *
	 * @param ParcelamentoCartaoConfirmarForm
	 * @param fachada
	 */
	private void getImovel(InserirImovelProgramaEspecialActionForm form,
			HttpServletRequest httpServletRequest) {
		
			Fachada fachada = Fachada.getInstancia();
		
			HttpSession sessao = httpServletRequest.getSession(false);

			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, 
					form.getMatriculaImovel()));
			
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("localidade");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("setorComercial");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra");
			

			Collection colecaoImovel = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			if (colecaoImovel != null && !colecaoImovel.isEmpty()) {				
			
				
				String enderecoFormatado = null;				
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);				
				/**
				 * @author Arthur Carvalho
				 * @date 02/08/2011
				 *  Restricao para inserir o imovel em programa especial, caso o cliente usuario tenha CPF
				 */
				Cliente cliente = fachada.pesquisarClienteUsuarioImovel(imovel.getId());
				if ( cliente != null && ( cliente.getCpf() == null || cliente.getCpf().equals("")) ) {
					throw new ActionServletException("atencao.cliente_usuario_nao_possui_cpf");
				}
				
				enderecoFormatado = fachada.pesquisarEndereco(imovel.getId());
				sessao.setAttribute("enderecoFormatado",enderecoFormatado);
				sessao.setAttribute("inscricaoImovelEncontrada", "true");
				form.setInscricaoImovel(imovel.getInscricaoFormatada());
				form.setEnderecoImovel(enderecoFormatado);
			} else {			
				this.limpar(form, sessao);
				sessao.removeAttribute("inscricaoImovelEncontrada");
				form.setMatriculaImovel("");
				form.setInscricaoImovel("Matrícula inexistente");
				
			}
	}
	
	private void limpar(InserirImovelProgramaEspecialActionForm form,HttpSession sessao){

		form.setInscricaoImovel(null);
		form.setMatriculaImovel(null);
		form.setDescricaoDocumentos(null);
		form.setDataApresentacaoDocumentos(null);
		sessao.removeAttribute("enderecoFormatado");
		sessao.removeAttribute("inscricaoImovelEncontrada");
	}

}
