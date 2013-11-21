package gcom.gui.cadastro.imovel;

import java.util.Collection;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
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

/**
 * [UC0975] Filtrar Imóvel em Programa Especial
 * 
 * @author Hugo Amorim
 * @date 17/12/2009
 */
public class ExibirFiltrarImovelProgramaEspecialAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirFiltrarImovelProgramaEspecialAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltrarImovelProgramaEspecialActionForm filtrarImovelProgramaEspecialActionForm = (FiltrarImovelProgramaEspecialActionForm) actionForm;
		
		String indicadorAtualizar = httpServletRequest.getParameter("atualizar");
		
		//Indicador Atualizar
		if(indicadorAtualizar!=null && (indicadorAtualizar.equals("sim") || indicadorAtualizar.equals("1"))){
			httpServletRequest.setAttribute("atualizar","1");
		}

		// Verifica se entrou apartir
		// do menu
		if(httpServletRequest.getParameter("menu")!=null
				&& httpServletRequest.getParameter("menu").toString().equalsIgnoreCase("sim")){
			
			httpServletRequest.setAttribute("atualizar","1");
			this.limpar(filtrarImovelProgramaEspecialActionForm, sessao);
			
		}
		
		// Pesquisa Imovel
		// Enter ou Reload PopUp
		//
		if(httpServletRequest.getParameter("pesquisarImovel")!=null
				&& httpServletRequest.getParameter("pesquisarImovel").toString().equalsIgnoreCase("sim")){
			
			if(httpServletRequest.getParameter("idImovel")!=null
					&& !httpServletRequest.getParameter("idImovel").toString().equals("")){
				
				filtrarImovelProgramaEspecialActionForm
					.setMatriculaImovel(httpServletRequest.getParameter("idImovel"));	
			}
			
			if(filtrarImovelProgramaEspecialActionForm.getMatriculaImovel()!=null && 
				!filtrarImovelProgramaEspecialActionForm.getMatriculaImovel().equals("")){
								
				getImovel(filtrarImovelProgramaEspecialActionForm,httpServletRequest);
			}
			
		}
		
		return retorno;
		
	}
	
	/**
	 * Recupera Imóvel 
	 *
	 */
	private void getImovel(FiltrarImovelProgramaEspecialActionForm form,
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
				sessao.removeAttribute("inscricaoImovelEncontrada");
				form.setMatriculaImovel("");
				form.setInscricaoImovel("Matrícula inexistente");
				
			}
	}
	
	private void limpar(FiltrarImovelProgramaEspecialActionForm form,HttpSession sessao){
		form.setMatriculaImovel(null);
		form.setInscricaoImovel(null);
		form.setDataApresentacaoDocumentosFinal(null);
		form.setDataApresentacaoDocumentosInicial(null);
		form.setMesAnoReferenciaEntradaPrograma(null);
		form.setMesAnoReferenciaSaidaPrograma(null);

	}
}
