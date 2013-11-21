package gcom.gui.cadastro.projeto;

import gcom.cadastro.projeto.FiltroProjeto;
import gcom.cadastro.projeto.Projeto;
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

public class ExibirAtualizarProjetoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		//Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("exibirAtualizarProjeto");
		
		AtualizarProjetoForm form = (AtualizarProjetoForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Fachada fachada = Fachada.getInstancia();
		
		Projeto projeto = (Projeto) sessao.getAttribute("projeto");
		
		if(projeto == null){
				
			String idProjeto =	(String) httpServletRequest.getParameter("projetoID");
		 
			FiltroProjeto filtroProjeto = new FiltroProjeto();
			
			filtroProjeto.adicionarParametro(new ParametroSimples(FiltroProjeto.ID,new Integer(idProjeto)));
			filtroProjeto.adicionarCaminhoParaCarregamentoEntidade("orgaoFinanciador");
			
			projeto = (Projeto) fachada.pesquisar(filtroProjeto,Projeto.class.getName()).iterator().next();
			
			
		}
		
		form.setNome(projeto.getNome());
		form.setDataInicio(Util.formatarData(projeto.getDataInicio()));
		
		if(projeto.getNomeAbreviado()!=null && !projeto.getNomeAbreviado().equals("")){
			form.setNomeAbreviado(projeto.getNomeAbreviado());
		}
		if(projeto.getOrgaoFinanciador()!=null){
			form.setIdOrgaoFinanciador(projeto.getOrgaoFinanciador().getId().toString());
			form.setNomeOrgaoFinanciador(projeto.getOrgaoFinanciador().getNome());
		}
		if(projeto.getDataFim()!=null && !projeto.getDataFim().equals("")){
			form.setDataFim(Util.formatarData(projeto.getDataFim()));
		}
		if(projeto.getValorFinanciamento()!=null && !projeto.getValorFinanciamento().equals("")){
			form.setValorFinanciamento(Util.formatarMoedaReal(projeto.getValorFinanciamento()));
		}
		if(projeto.getObservacao()!=null && !projeto.getObservacao().equals("")){
			form.setObservacao(projeto.getObservacao());
		}
		
		sessao.setAttribute("idProjeto", projeto.getId());
		
		return retorno;
	}
}
