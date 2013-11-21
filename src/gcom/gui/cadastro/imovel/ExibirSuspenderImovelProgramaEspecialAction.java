package gcom.gui.cadastro.imovel;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelProgramaEspecial;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroNulo;
import gcom.util.filtro.ParametroSimples;

public class ExibirSuspenderImovelProgramaEspecialAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirSuspenderImovelProgramaEspecialAction");
		
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
				
				
				for (Iterator iterator = colecaoImovel.iterator(); iterator.hasNext();) {
					Imovel imovelTeste = (Imovel) iterator.next();
					
					FiltroImovelProgramaEspecial filtro = new FiltroImovelProgramaEspecial();
					
					filtro.adicionarParametro(new ParametroSimples(
							FiltroImovelProgramaEspecial.IMOVEL_ID, imovelTeste.getId()));
					
					filtro
						.adicionarParametro(new ParametroNulo(
							FiltroImovelProgramaEspecial.DATA_SUSPENSAO));
					
					Collection colecaoImovelPrograma =
						fachada.pesquisar(filtro, ImovelProgramaEspecial.class.getName());
					
					if(colecaoImovelPrograma!=null && colecaoImovelPrograma.isEmpty()){
						throw new ActionServletException("atencao.imovel.nao.cadastrado.no.programa");
					}
					
			
				}	
				String enderecoFormatado = null;				
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);				
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
		sessao.removeAttribute("enderecoFormatado");
		sessao.removeAttribute("inscricaoImovelEncontrada");
	}

}

