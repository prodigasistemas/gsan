package gcom.gui.cadastro.imovel;

import java.util.Collection;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.FiltroImovelProgramaEspecial;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelProgramaEspecial;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExibirAtualizarImovelProgramaEspecialAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirAtualizarImovelProgramaEspecialAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();

		AtualizarImovelProgramaEspecialActionForm atualizarImovelProgramaEspecialActionForm = (AtualizarImovelProgramaEspecialActionForm) actionForm;
		
		ImovelProgramaEspecial imovelProgramaEspecial = (ImovelProgramaEspecial) sessao.getAttribute("imovelProgramaEspecial");
		
		if(imovelProgramaEspecial == null){
				
			String imovelProgramaEspecialID =	(String) httpServletRequest.getParameter("imovelProgramaEspecialID");
			
			atualizarImovelProgramaEspecialActionForm.setId(imovelProgramaEspecialID);
			
			FiltroImovelProgramaEspecial filtroImovelProgramaEspecial = new FiltroImovelProgramaEspecial();
			
			filtroImovelProgramaEspecial.adicionarParametro(new ParametroSimples(FiltroImovelProgramaEspecial.ID,new Integer(imovelProgramaEspecialID)));
			
			filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.IMOVEL);
			filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.LOCALIDADE);
			filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.SETOR_COMERCIAL);
			filtroImovelProgramaEspecial.adicionarCaminhoParaCarregamentoEntidade(FiltroImovelProgramaEspecial.QUADRA);
			
			imovelProgramaEspecial = (ImovelProgramaEspecial) fachada.pesquisar(filtroImovelProgramaEspecial,ImovelProgramaEspecial.class.getName()).iterator().next();
				
		}else{
			
			atualizarImovelProgramaEspecialActionForm.setId(imovelProgramaEspecial.getId().toString());
			
		}
		

		
		if(imovelProgramaEspecial.getImovel().getId()!=null){
			atualizarImovelProgramaEspecialActionForm.setMatriculaImovel(imovelProgramaEspecial.getImovel().getId().toString());
			atualizarImovelProgramaEspecialActionForm.setInscricaoImovel(imovelProgramaEspecial.getImovel().getInscricaoFormatada());
			sessao.setAttribute("inscricaoImovelEncontrada", "true");
		}
		if(imovelProgramaEspecial.getDescricaoDocumentos()!=null){
			atualizarImovelProgramaEspecialActionForm.setDescricaoDocumentos(imovelProgramaEspecial.getDescricaoDocumentos());
		}
		if(imovelProgramaEspecial.getDataApresentacaoDocumentos()!=null){
			
			String data = Util.formatarData(imovelProgramaEspecial.getDataApresentacaoDocumentos());
			atualizarImovelProgramaEspecialActionForm.setDataApresentacaoDocumentos(data);
			
		}
		
		if(imovelProgramaEspecial.getNumeroBolsaFamilia()!=null
				&& !imovelProgramaEspecial.getNumeroBolsaFamilia().equals("")){
			atualizarImovelProgramaEspecialActionForm
				.setNumeroBolsaFamilia(imovelProgramaEspecial.getNumeroBolsaFamilia().toString());
		}
		
		// Verifica se entrou apartir
		// do menu
		//
		if(httpServletRequest.getParameter("menu")!=null
				&& httpServletRequest.getParameter("menu").toString().equalsIgnoreCase("sim")){
			
			this.limpar(atualizarImovelProgramaEspecialActionForm, sessao);
			
		}
	
		// Pesquisa Imovel
		// Enter ou Reload PopUp
		//
		if(httpServletRequest.getParameter("pesquisarImovel")!=null
				&& httpServletRequest.getParameter("pesquisarImovel").toString().equalsIgnoreCase("sim")){
			
			if(httpServletRequest.getParameter("idImovel")!=null
					&& !httpServletRequest.getParameter("idImovel").toString().equals("")){
				
				atualizarImovelProgramaEspecialActionForm.setMatriculaImovel(httpServletRequest.getParameter("idImovel"));	
			}
			
			if(atualizarImovelProgramaEspecialActionForm.getMatriculaImovel()!=null && 
				!atualizarImovelProgramaEspecialActionForm.getMatriculaImovel().equals("")){
								
				getImovel(atualizarImovelProgramaEspecialActionForm,httpServletRequest);
			}
			
		}
		
		if((imovelProgramaEspecial!=null)){
			sessao.setAttribute("idRegistroAtualizar", imovelProgramaEspecial.getId());
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
	private void getImovel(AtualizarImovelProgramaEspecialActionForm form,
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
				Imovel imovel = (Imovel) Util.retonarObjetoDeColecao(colecaoImovel);				
				sessao.setAttribute("inscricaoImovelEncontrada", "true");
				form.setInscricaoImovel(imovel.getInscricaoFormatada());
			} else {			
				this.limpar(form, sessao);
				form.setMatriculaImovel("");
				form.setInscricaoImovel("Matrícula inexistente");
				
			}
	}
	
	private void limpar(AtualizarImovelProgramaEspecialActionForm form,HttpSession sessao){
		form.setInscricaoImovel(null);
		form.setMatriculaImovel(null);
		form.setDescricaoDocumentos(null);
		form.setDataApresentacaoDocumentos(null);
		sessao.removeAttribute("inscricaoImovelEncontrada");
	}
}	
