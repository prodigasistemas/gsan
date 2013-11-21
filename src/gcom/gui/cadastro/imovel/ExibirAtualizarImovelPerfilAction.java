package gcom.gui.cadastro.imovel;

import gcom.cadastro.imovel.FiltroImovelPerfil;
import gcom.cadastro.imovel.ImovelPerfil;
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
 *  
 * @author Wallace Thierre
 * @date 04/10/2010
 * 
 */
public class ExibirAtualizarImovelPerfilAction extends GcomAction{
	
	/**
	 * Método responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping, 
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		ActionForward retorno = actionMapping
				.findForward("ImovelPerfilAtualizar");
		
		AtualizarImovelPerfilActionForm atualizarImovelPerfilActionForm =
				(AtualizarImovelPerfilActionForm) actionForm;
		
		Fachada fachada = Fachada.getInstancia();
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String id = httpServletRequest.getParameter("idRegistroAtualizacao");
				
		if (httpServletRequest.getParameter("manter") != null){
			sessao.setAttribute("manter", true);			
		} else if (httpServletRequest.getParameter("filtrar") != null){
			sessao.removeAttribute("manter");
		}
		
		if (id == null ){
			if(httpServletRequest.getAttribute("idRegistroAtualizacao") == null){
				id = (String) sessao.getAttribute("idRegistroAtualizacao");
			} else {
				id = (String) httpServletRequest.getAttribute(
				"idRegistroAtualizacao").toString();
			}
		} else {
			sessao.setAttribute("i", true);
		}
		
		if(id != null && !id.trim().equals("")){
			
			FiltroImovelPerfil filtroImovelPerfil = new FiltroImovelPerfil();
			filtroImovelPerfil.adicionarParametro
					(new ParametroSimples(FiltroImovelPerfil.ID, id));
			Collection colecaoImovelPerfil = fachada.pesquisar
				(filtroImovelPerfil, ImovelPerfil.class.getName());
			
			if (colecaoImovelPerfil != null && 
						!colecaoImovelPerfil.isEmpty()){
				
				ImovelPerfil imovelPerfil = (ImovelPerfil) Util
						.retonarObjetoDeColecao(colecaoImovelPerfil);		
				
				atualizarImovelPerfilActionForm.setId(imovelPerfil.getId().toString());
				
				atualizarImovelPerfilActionForm.setDescricao(imovelPerfil.getDescricao());
				
				atualizarImovelPerfilActionForm.setIndicadorUso(""
						+ imovelPerfil.getIndicadorUso());
				
				atualizarImovelPerfilActionForm.setIndicadorGeracaoAutomatica("" 
						+ imovelPerfil.getIndicadorGeracaoAutomatica());
				
				atualizarImovelPerfilActionForm.setIndicadorInserirManterPerfil(""
						+ imovelPerfil.getIndicadorInserirManterPerfil());
				
				atualizarImovelPerfilActionForm.setIndicadorGerarDadosLeitura(""
						+ imovelPerfil.getIndicadorGerarDadosLeitura());
				
				atualizarImovelPerfilActionForm.setIndicadorBloquearReatificacao(""
						+ imovelPerfil.getIndicadorBloquearRetificacao());
				
				atualizarImovelPerfilActionForm.setIndicadorGrandeConsumidor(""
						+ imovelPerfil.getIndicadorGrandeConsumidor());
				
				atualizarImovelPerfilActionForm.setIndicadorBloquearDadosSocial(""
						+ imovelPerfil.getIndicadorBloqueaDadosSocial());
				
				atualizarImovelPerfilActionForm.setIndicadorGeraDebitoSegundaViaConta(""
						+ imovelPerfil.getIndicadorGeraDebitoSegundaViaConta());
				
				sessao.setAttribute("atualizarImovelPerfil", imovelPerfil);
			}			
			
			if(sessao.getAttribute("colecaoImovelPerfil") != null){
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/filtrarImovelPerfilAction.do");
			} else {
				sessao.setAttribute("caminhoRetornoVoltar", "/gsan/exibirFiltrarImovelPerfilAction.do");
			}			
			
		}
		
		return retorno;
	}
		
	


}
