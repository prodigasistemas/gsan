package gcom.gui.operacional;


import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.Bacia;
import gcom.operacional.FiltroBacia;
import gcom.operacional.SistemaEsgoto;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Descrição da classe
 * 
 * @author Arthur Carvalho
 * @date 22/05/2008
 */
public class InserirBaciaAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Bacia
	 * 
	 * [UC0782] Inserir Bacia
	 * 
	 * @author Arthur Carvalho
	 * 
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

		ActionForward retorno = actionMapping.findForward("telaSucesso");

		InserirBaciaActionForm inserirBaciaActionForm = (InserirBaciaActionForm) actionForm;

		String descricao = inserirBaciaActionForm.getDescricao();
		
		Collection colecaoPesquisa = null;

		// Descrição
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		// Sistema Esgoto
		if (inserirBaciaActionForm.getSistemaEsgoto() == null || 
			"".equals(inserirBaciaActionForm.getSistemaEsgoto()) ) {
			throw new ActionServletException("atencao.required", null,"SistemaEsgoto");
		}
		

		FiltroBacia filtroBacia = new FiltroBacia();
		filtroBacia.adicionarParametro(
			new ParametroSimples(FiltroBacia.DESCRICAO,descricao));
		
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroBacia, Bacia.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.bacia_ja_cadastrada", null,descricao);
		} else {
			SistemaEsgoto sistemaEsgotoObjeto = new SistemaEsgoto();
			sistemaEsgotoObjeto.setId(new Integer(inserirBaciaActionForm
					.getSistemaEsgoto()));

			Bacia bacia = new Bacia();
			bacia.setSistemaEsgoto(sistemaEsgotoObjeto);
			bacia.setDescricao(descricao);
			bacia.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			bacia.setUltimaAlteracao(new Date());
			

			Integer idBacia = (Integer) this.getFachada().inserir(bacia);

			montarPaginaSucesso(httpServletRequest,
					"Bacia " + descricao
							+ " inserido com sucesso.",
					"Inserir outra Bacia",
					"exibirInserirBaciaAction.do?menu=sim",
					"exibirAtualizarBaciaAction.do?idRegistroAtualizacao="
							+ idBacia,
					"Atualizar Bacia Inserida");

			this.getSessao(httpServletRequest).removeAttribute("InserirBaciaActionForm");

			return retorno;
		}

	}
}		
