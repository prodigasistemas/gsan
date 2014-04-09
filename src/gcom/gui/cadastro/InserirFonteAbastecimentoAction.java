package gcom.gui.cadastro;


import gcom.cadastro.imovel.FiltroFonteAbastecimento;
import gcom.cadastro.imovel.FonteAbastecimento;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class InserirFonteAbastecimentoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma fonte de abastecimento
	 * 
	 * [UC0834] Inserir Fonte de Abastecimento
	 * 
	 * @author Arthur Carvalho
	 * @date 14/08/2008
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

		InserirFonteAbastecimentoActionForm inserirFonteAbastecimentoActionForm = (InserirFonteAbastecimentoActionForm) actionForm;

		
		String descricao = inserirFonteAbastecimentoActionForm.getDescricao();
		String descricaoAbreviada = inserirFonteAbastecimentoActionForm.getDescricaoAbreviada();
		Short indicadorCalcularVolumeFixo = inserirFonteAbastecimentoActionForm.getIndicadorCalcularVolumeFixo();
		Short indicadorPermitePoco = inserirFonteAbastecimentoActionForm.getIndicadorPermitePoco();
		
		Collection colecaoPesquisa = null;

		
		// Descrição
		if (descricao == null || "".equals(descricao)) {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		FiltroFonteAbastecimento filtroFonteAbastecimento = new FiltroFonteAbastecimento();
		filtroFonteAbastecimento.adicionarParametro(
				new ParametroSimples(FiltroFonteAbastecimento.DESCRICAO,descricao));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroFonteAbastecimento, FonteAbastecimento.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		

			FonteAbastecimento fonteAbastecimento= new FonteAbastecimento();
			fonteAbastecimento.setIndicadorCalcularVolumeFixo(indicadorCalcularVolumeFixo);
			fonteAbastecimento.setDescricao(descricao);
			fonteAbastecimento.setDescricaoAbreviada(descricaoAbreviada);
			fonteAbastecimento.setUltimaAlteracao(new Date());
			fonteAbastecimento.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
			fonteAbastecimento.setIndicadorPermitePoco( indicadorPermitePoco );
			Integer idFonteAbastecimento = (Integer) this.getFachada().inserir(fonteAbastecimento);

			montarPaginaSucesso(httpServletRequest,
					"Fonte de Abastecimento " + descricao
							+ " inserido com sucesso.",
					"Inserir outra Fonte de Abastecimento",
					"exibirInserirFonteAbastecimentoAction.do?menu=sim",
					"exibirAtualizarFonteAbastecimentoAction.do?idRegistroAtualizacao="
							+ idFonteAbastecimento,
					"Atualizar Fonte de Abastecimento Inserida");

			this.getSessao(httpServletRequest).removeAttribute("InserirFonteAbastecimentoActionForm");

			return retorno;
		
		
	}
}		
