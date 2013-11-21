package gcom.gui.cadastro.projeto;

import gcom.cadastro.projeto.FiltroProjeto;
import gcom.cadastro.projeto.Projeto;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.gui.ManutencaoRegistroActionForm;
import gcom.util.filtro.ParametroSimplesIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class RemoverProjetoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ManutencaoRegistroActionForm manutencaoRegistroActionForm = (ManutencaoRegistroActionForm) actionForm;
		
		// Obtém os ids de remoção
		String[] ids = manutencaoRegistroActionForm.getIdRegistrosRemocao();

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");
		
		// Obtém a sessão
		// HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		// mensagem de erro quando o usuário tenta excluir sem ter selecionado
		// nenhum
		// registro
		if (ids == null || ids.length == 0) {
			throw new ActionServletException(
					"atencao.registros.nao_selecionados");
		}
		
		
		FiltroProjeto filtroProjeto = new FiltroProjeto();
		
		Collection idsProjetos = new ArrayList(ids.length);
		
		for (int i = 0; i < ids.length; i++) {
			idsProjetos.add(new Integer(ids[i]));
		}
		
		filtroProjeto.adicionarParametro(new ParametroSimplesIn(FiltroProjeto.ID,idsProjetos));

		Collection projetosRemocao = fachada.pesquisar(filtroProjeto,Projeto.class.getName());
		
		Iterator itera = projetosRemocao.iterator();
		
		while(itera.hasNext()){
		
			Projeto projeto = (Projeto) itera.next();
			
			fachada.remover(projeto);
			
		}

		Integer idQt = ids.length;
		
		//Monta a página de sucesso
		if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
			montarPaginaSucesso(httpServletRequest, idQt.toString()
					+ " Projeto(s) removido(s) com sucesso.",
					"Realizar outra manutenção de Projeto",
					"exibirFiltrarProjetoAction.do?menu=sim");
		}
	
		return retorno;
	}	
}
