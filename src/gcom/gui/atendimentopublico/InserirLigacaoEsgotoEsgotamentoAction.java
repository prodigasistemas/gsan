package gcom.gui.atendimentopublico;


import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoEsgotamento;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.faturamento.FaturamentoSituacaoMotivo;
import gcom.faturamento.FaturamentoSituacaoTipo;
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


public class InserirLigacaoEsgotoEsgotamentoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma ligacao de esgoto esgotamento
	 * 
	 * [UC0834] Inserir Ligação de esgoto esgotamento
	 * 
	 * @author Arthur Carvalho
	 * @date 25/08/2008
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

		InserirLigacaoEsgotoEsgotamentoActionForm form = (InserirLigacaoEsgotoEsgotamentoActionForm) actionForm;

		
		String descricao = form.getDescricao();
		String faturamentoSituacaoTipo = form.getFaturamentoSituacaoTipo();
		String faturamentoSituacaoMotivo = form.getFaturamentoSituacaoMotivo();
		
		LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento = new LigacaoEsgotoEsgotamento();
		
		Collection colecaoPesquisa = null;
		
		
		
		FiltroLigacaoEsgotoEsgotamento filtroLigacaoEsgotoEsgotamento = new FiltroLigacaoEsgotoEsgotamento();
		filtroLigacaoEsgotoEsgotamento.adicionarParametro(
				new ParametroSimples(FiltroLigacaoEsgotoEsgotamento.DESCRICAO,descricao));
		
		colecaoPesquisa = (Collection)
		this.getFachada().pesquisar(filtroLigacaoEsgotoEsgotamento, LigacaoEsgotoEsgotamento.class.getName());
				
		if( colecaoPesquisa !=null && !colecaoPesquisa.isEmpty()){
			throw new ActionServletException("atencao.descricao_existente", null, descricao);
		}
		
		//Descrição
		if (!"".equals(form.getDescricao())) {
			ligacaoEsgotoEsgotamento.setDescricao(form
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		//Quantidade Meses Situacao Especial
		if (!"".equals(form.getQuantidadeMesesSituacaoEspecial())) {
			ligacaoEsgotoEsgotamento.setQuantidadeMesesSituacaoEspecial(new Integer(form
					.getQuantidadeMesesSituacaoEspecial()));
		} else {
			throw new ActionServletException("atencao.required", null,"Quantidade de Meses para Situação Especial de Faturamento");
		}
		
		//Tipo Situação Especial Faturamento
		if(faturamentoSituacaoTipo != null && 
			!faturamentoSituacaoTipo.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FaturamentoSituacaoTipo faturamentoTipo = new FaturamentoSituacaoTipo();
			faturamentoTipo.setId(new Integer(form
					.getFaturamentoSituacaoTipo()));
			
			ligacaoEsgotoEsgotamento.setFaturamentoSituacaoTipo(faturamentoTipo);
			
		}
		
		//Motivo Situacao Especial Faturamento
		if(faturamentoSituacaoMotivo != null && 
			!faturamentoSituacaoMotivo.equals(""+ConstantesSistema.NUMERO_NAO_INFORMADO)){
			
			FaturamentoSituacaoMotivo faturamentoMotivo = new FaturamentoSituacaoMotivo();
			faturamentoMotivo.setId(new Integer(form
					.getFaturamentoSituacaoMotivo()));
			
			ligacaoEsgotoEsgotamento.setFaturamentoSituacaoMotivo(faturamentoMotivo);

		}

		ligacaoEsgotoEsgotamento.setDescricao(descricao);
		ligacaoEsgotoEsgotamento.setUltimaAlteracao(new Date());
		ligacaoEsgotoEsgotamento.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);

		Integer idLigacaoEsgotoEsgotamento = (Integer) this.getFachada().inserir(ligacaoEsgotoEsgotamento);

		montarPaginaSucesso(httpServletRequest,
				"Ligação de Esgoto Esgotamento " + descricao
						+ " inserido com sucesso.",
				"Inserir outra Ligação de Esgoto Esgotamento",
				"exibirInserirLigacaoEsgotoEsgotamentoAction.do?menu=sim",
				"exibirAtualizarLigacaoEsgotoEsgotamentoAction.do?idRegistroAtualizacao="
						+ idLigacaoEsgotoEsgotamento,
				"Atualizar Ligação de Esgoto Esgotamento Inserida");

		return retorno;
		
		
	}
}		
