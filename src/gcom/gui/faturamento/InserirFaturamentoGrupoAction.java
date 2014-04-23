package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
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
 * Descrição da classe
 * 
 * @author Vinícius Medeiros
 * @date 17/04/2008
 */
public class InserirFaturamentoGrupoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de um grupo de faturamento
	 * 
	 * [UC0770] Inserir Grupo de Faturamento
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 17/04/2008
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

		InserirFaturamentoGrupoActionForm inserirFaturamentoGrupoActionForm = (InserirFaturamentoGrupoActionForm) actionForm;

		HttpSession sessao = httpServletRequest.getSession(false);

		Fachada fachada = Fachada.getInstancia();

		
		String descricao = inserirFaturamentoGrupoActionForm.getDescricao();
		
		String anoMesReferencia = inserirFaturamentoGrupoActionForm.getAnoMesReferencia();
		
		String indicadorVencimentoMesFatura = inserirFaturamentoGrupoActionForm
				.getIndicadorVencimentoMesFatura();

		FaturamentoGrupo faturamentoGrupo = new FaturamentoGrupo();
		Collection colecaoPesquisa = null;

		String mes = anoMesReferencia.substring(0, 2);
		String ano = anoMesReferencia.substring(3, 7);

		anoMesReferencia = ano + "" + mes;
		faturamentoGrupo.setAnoMesReferencia(new Integer(anoMesReferencia));
		
		//Código do grupo de faturamento - referente ao ftgr_id na tabela
		if(!"".equals(inserirFaturamentoGrupoActionForm.getCodigo())){
			if(fachada.verificarExistenciaIdGrupoFaturamento(Integer.parseInt(inserirFaturamentoGrupoActionForm.getCodigo()))){
				throw new ActionServletException("atencao.id.existente.grupo.faturamento", null,
				"Faturamento Grupo");			
			}else{
				faturamentoGrupo.setId(Integer.parseInt(inserirFaturamentoGrupoActionForm.getCodigo()));	
			}
			
		}
		
		// Descrição
		if (!"".equals(inserirFaturamentoGrupoActionForm.getDescricao())) {
			faturamentoGrupo.setDescricao(inserirFaturamentoGrupoActionForm
					.getDescricao());
		} else {
			throw new ActionServletException("atencao.required", null,
					"descrição");
		}
		
		//Descrição Abreviada
		if (!"".equals(inserirFaturamentoGrupoActionForm
				.getDescricaoAbreviada())) {
			faturamentoGrupo
					.setDescricaoAbreviada(inserirFaturamentoGrupoActionForm
							.getDescricaoAbreviada());
		} else {
			throw new ActionServletException("atencao.required", null,
					"Descrição Abreviada");
		}

		// Dia do Vencimento
		if (!"".equals(inserirFaturamentoGrupoActionForm
				.getDiaVencimento())) {
			faturamentoGrupo
					.setDiaVencimento(new Short(inserirFaturamentoGrupoActionForm
							.getDiaVencimento()));
		} else {
			throw new ActionServletException("atencao.required", null,
					"Dia do Vencimento");
		}
		
		
		// Indicador de Vencimento
        if (indicadorVencimentoMesFatura == null 
        		|| indicadorVencimentoMesFatura.equals("")){
        	throw new ActionServletException("atencao.required",null,"Indicador de Vencimento");
        }else{  
        	faturamentoGrupo.setIndicadorVencimentoMesFatura(new Short(indicadorVencimentoMesFatura));
        }
		
		
		faturamentoGrupo.setUltimaAlteracao(new Date());

		Short iu = 1;
		faturamentoGrupo.setIndicadorUso(iu);

		FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo();

		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.DESCRICAO, faturamentoGrupo
						.getDescricao()));
		filtroFaturamentoGrupo.adicionarParametro(new ParametroSimples(
				FiltroFaturamentoGrupo.DESCRICAO_ABREVIADA, faturamentoGrupo
						.getDescricaoAbreviada()));

		
		colecaoPesquisa = (Collection) fachada.pesquisar(
				filtroFaturamentoGrupo, FaturamentoGrupo.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			// 
			throw new ActionServletException(
					"atencao.faturamento_grupo_ja_cadastrada", null,
					faturamentoGrupo.getDescricao());
		} else {
			faturamentoGrupo.setDescricao(descricao);

			Integer idFaturamentoGrupo = (Integer) fachada
					.inserir(faturamentoGrupo);

			montarPaginaSucesso(httpServletRequest,
					"Grupo de Faturamento " + descricao
							+ " inserido com sucesso.",
					"Inserir outro Grupo de Faturamento",
					"exibirInserirFaturamentoGrupoAction.do?menu=sim",
					"exibirAtualizarFaturamentoGrupoAction.do?idRegistroAtualizacao="
							+ idFaturamentoGrupo,
					"Atualizar Grupo de Faturamento Inserido");

			sessao.removeAttribute("InserirFaturamentoGrupoActionForm");

			return retorno;
		}

	}
}
