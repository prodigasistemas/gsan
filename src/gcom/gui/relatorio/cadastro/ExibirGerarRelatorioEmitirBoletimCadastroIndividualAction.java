package gcom.gui.relatorio.cadastro;

import gcom.cadastro.imovel.FiltroImovel;
import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
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
 * [UC1011] Gerar Relatorio Emitir Boletim Cadastro Individual.
 * 
 * @author Hugo Leonardo
 *
 * @date 23/03/2010
 */

public class ExibirGerarRelatorioEmitirBoletimCadastroIndividualAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirGerarRelatorioEmitirBoletimCadastroIndividualAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);

		GerarRelatorioEmitirBoletimCadastroIndividualActionForm gerarRelatorioEmitirBoletimCadastroIndividualActionForm = (GerarRelatorioEmitirBoletimCadastroIndividualActionForm) actionForm;
		
		// Verifica se entrou apartir
		// do menu
		//
		if(httpServletRequest.getParameter("menu")!=null
				&& httpServletRequest.getParameter("menu").toString().equalsIgnoreCase("sim")){
			
			this.limpar(gerarRelatorioEmitirBoletimCadastroIndividualActionForm, sessao);
			
		}
		
		// Pesquisa Imovel
		// Enter ou Reload PopUp
		//
		if (gerarRelatorioEmitirBoletimCadastroIndividualActionForm.getMatriculaImovel() != null 
				&& !gerarRelatorioEmitirBoletimCadastroIndividualActionForm.getMatriculaImovel().trim().equals("")) {
			
			pesquisarImovel( gerarRelatorioEmitirBoletimCadastroIndividualActionForm, httpServletRequest);

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
	private void pesquisarImovel(GerarRelatorioEmitirBoletimCadastroIndividualActionForm form,
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
				enderecoFormatado = fachada.pesquisarEndereco(imovel.getId());
				sessao.setAttribute("enderecoFormatado",enderecoFormatado);
				sessao.setAttribute("inscricaoImovelEncontrada", "true");
				form.setMatriculaImovel(imovel.getId().toString());
				form.setInscricaoImovel(imovel.getInscricaoFormatada());
			} else {			
				this.limpar(form, sessao);
				sessao.removeAttribute("inscricaoImovelEncontrada");
				form.setMatriculaImovel("");
				form.setInscricaoImovel("Matrícula inexistente");
				
			}
	}
	
	private void limpar(GerarRelatorioEmitirBoletimCadastroIndividualActionForm form,HttpSession sessao){

		form.setInscricaoImovel(null);
		form.setMatriculaImovel(null);
		sessao.removeAttribute("enderecoFormatado");
		sessao.removeAttribute("inscricaoImovelEncontrada");
	}
	
}
