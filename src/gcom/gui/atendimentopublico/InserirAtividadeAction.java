package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ordemservico.Atividade;
import gcom.atendimentopublico.ordemservico.FiltroAtividade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0773] Inserir Atividade
 * 
 * @author Vinícius Medeiros
 * @date 17/04/2008
 */
public class InserirAtividadeAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Atividade
	 * 
	 * [UC0773] Inserir Atividade
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 28/04/2008
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

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirAtividadeActionForm inserirAtividadeActionForm = (InserirAtividadeActionForm) actionForm;

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		String descricao = inserirAtividadeActionForm.getDescricao();
		String descricaoAbreviada = inserirAtividadeActionForm.getDescricaoAbreviada();
		String indicadorAtividadeUnica = inserirAtividadeActionForm.getIndicadorAtividadeUnica();
		
		Atividade atividade = new Atividade();
		Collection colecaoPesquisa = null;

		// Verifica se a Descrição foi passada
		if (!"".equals(inserirAtividadeActionForm.getDescricao())) {
			atividade.setDescricao(inserirAtividadeActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
		
		// Verifica se o Indicador de Atividade Única foi passado
        if (indicadorAtividadeUnica == null 
        		|| indicadorAtividadeUnica.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de atividade única");
        }else{  
        	atividade.setIndicadorAtividadeUnica(new Short(indicadorAtividadeUnica));
        }
		
		atividade.setDescricao(descricao);
		atividade.setDescricaoAbreviada(descricaoAbreviada);
		atividade.setUltimaAlteracao(new Date());
		atividade.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		FiltroAtividade filtroAtividade = new FiltroAtividade();

		filtroAtividade.adicionarParametro(
			new ParametroSimples(
				FiltroAtividade.DESCRICAO, 
				atividade.getDescricao()));
		
		filtroAtividade.adicionarParametro(
			new ParametroSimples(
				FiltroAtividade.DESCRICAOABREVIADA, 
				atividade.getDescricaoAbreviada()));
		
		colecaoPesquisa = 
			(Collection) this.getFachada().pesquisar(
				filtroAtividade, Atividade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			
			// Caso já haja uma Atividade com a Descrição e a Descrição Abreviada passada
			throw new ActionServletException("atencao.atividade_ja_cadastrada", 
				null,atividade.getDescricao());
		} else {

			Integer idAtividade = (Integer) this.getFachada().inserir(atividade);

			montarPaginaSucesso(httpServletRequest,
				"Atividade " + descricao+ " inserido com sucesso.",
				"Inserir outra Atividade",
				"exibirInserirAtividadeAction.do?menu=sim",
				"exibirAtualizarAtividadeAction.do?idRegistroAtualizacao="+ idAtividade,
				"Atualizar Atividade Inserida");

			sessao.removeAttribute("InserirAtividadeActionForm");

			return retorno;
		}

	}
}
