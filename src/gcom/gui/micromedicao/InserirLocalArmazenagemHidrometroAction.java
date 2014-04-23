package gcom.gui.micromedicao;


import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroLocalArmazenagem;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirLocalArmazenagemHidrometroAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um local de armazenagem do hidrômetro
	 * 
	 * [UC0834] Inserir Inserir Local de Armazenagem do Hidrometro
	 * 
	 * @author Arthur Carvalho
	 * @date 06/08/2008
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

		InserirLocalArmazenagemHidrometroActionForm inserirLocalArmazenagemHidrometroActionForm = (InserirLocalArmazenagemHidrometroActionForm) actionForm;

		String descricao = inserirLocalArmazenagemHidrometroActionForm.getDescricao();
		String descricaoAbreviada = inserirLocalArmazenagemHidrometroActionForm.getDescricaoAbreviada();
		Short indicadorOficina = inserirLocalArmazenagemHidrometroActionForm.getIndicadorOficina();
		
		Collection colecaoPesquisa = null;

		// Descrição
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		//Descrição Abreviada
		if (descricaoAbreviada == null || "".equals(descricaoAbreviada)) {
			throw new ActionServletException("atencao.required", null,"Descrição Abreviada");
		}
		
		//Indicador de Oficina
		if (indicadorOficina == null || "".equals(indicadorOficina)) {
			throw new ActionServletException("atencao.required", null,"Indicador de Oficina ");
		}
		
		FiltroHidrometroLocalArmazenagem filtroHidrometroLocalArmazenagem= new FiltroHidrometroLocalArmazenagem();
			filtroHidrometroLocalArmazenagem.adicionarParametro(
					new ParametroSimples(FiltroHidrometroLocalArmazenagem.DESCRICAO,descricao));
		
		colecaoPesquisa = (Collection) 
			this.getFachada().pesquisar(filtroHidrometroLocalArmazenagem, HidrometroLocalArmazenagem.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			throw new ActionServletException("atencao.hidrometro_ja_existente", null,descricao);
		} else {
		

			HidrometroLocalArmazenagem hidrometroLocalArmazenagem = new HidrometroLocalArmazenagem();
			hidrometroLocalArmazenagem.setDescricao(descricao);
			hidrometroLocalArmazenagem.setDescricaoAbreviada(descricaoAbreviada);
			hidrometroLocalArmazenagem.setIndicadorOficina(indicadorOficina);
			hidrometroLocalArmazenagem.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			hidrometroLocalArmazenagem.setUltimaAlteracao(new Date());
			

			Integer idHidrometroLocalArmazenagem = (Integer) this.getFachada().inserir(hidrometroLocalArmazenagem);

			montarPaginaSucesso(httpServletRequest,
					"Local de Armazenagem do Hidrômetro " + descricao
							+ " inserido com sucesso.",
					"Inserir outro Local de Armazenagem do Hidrômetro",
					"exibirInserirLocalArmazenagemHidrometroAction.do?menu=sim",
					"exibirAtualizarLocalArmazenagemHidrometroAction.do?idRegistroAtualizacao="
							+ idHidrometroLocalArmazenagem,
					"Atualizar Local de Armazenagem do Hidrômetro Inserida");

			this.getSessao(httpServletRequest).removeAttribute("InserirLocalArmazenagemHidrometroActionForm");

			return retorno;
		}

	}
}		
