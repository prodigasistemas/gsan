package gcom.gui.atendimentopublico;

import gcom.atendimentopublico.ligacaoesgoto.FiltroLigacaoEsgotoSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.fachada.Fachada;
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
 * @date 14/05/2008
 */
public class InserirLigacaoEsgotoSituacaoAction extends GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma Situacao de Ligacao de Esgoto
	 * 
	 * [UC0788] Inserir Situacao de Ligacao de Esgoto
	 * 
	 * 
	 * @author Vinícius Medeiros
	 * @date 14/05/2008
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

		InserirLigacaoEsgotoSituacaoActionForm inserirLigacaoEsgotoSituacaoActionForm = (InserirLigacaoEsgotoSituacaoActionForm) actionForm;

		// Mudar isso quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();

		String descricao = inserirLigacaoEsgotoSituacaoActionForm.getDescricao();
		String descricaoAbreviado = inserirLigacaoEsgotoSituacaoActionForm.getDescricaoAbreviado();
		String volumeMinimoFaturamento = inserirLigacaoEsgotoSituacaoActionForm.getVolumeMinimoFaturamento();
		String indicadorExistenciaLigacao = inserirLigacaoEsgotoSituacaoActionForm.getIndicadorExistenciaLigacao();
		String indicadorExistenciaRede = inserirLigacaoEsgotoSituacaoActionForm.getIndicadorExistenciaRede();
		String indicadorFaturamentoSituacao = inserirLigacaoEsgotoSituacaoActionForm.getIndicadorFaturamentoSituacao();

		LigacaoEsgotoSituacao ligacaoEsgotoSituacao = new LigacaoEsgotoSituacao();
		Collection colecaoPesquisa = null;

		
		// Verifica se o campo Descrição foi preenchido
		if (!"".equals(inserirLigacaoEsgotoSituacaoActionForm.getDescricao())) {
			
			ligacaoEsgotoSituacao.setDescricao(inserirLigacaoEsgotoSituacaoActionForm
					.getDescricao());
		
		} else {
		
			throw new ActionServletException("atencao.required", null,"Descrição");
		}
		
		// Verifica se o campo Descrição Abreviada foi preenchido
		if (!"".equals(inserirLigacaoEsgotoSituacaoActionForm.getDescricaoAbreviado())) {
			
			ligacaoEsgotoSituacao.setDescricaoAbreviado(inserirLigacaoEsgotoSituacaoActionForm
					.getDescricaoAbreviado());
			
		} else {
			
			throw new ActionServletException("atencao.required", null,
					"Descrição Abreviada");
		}
		
		// Verifica se o campo Volume Mínimo da Situação de Ligação foi preenchido
        if (volumeMinimoFaturamento == null || volumeMinimoFaturamento.equals("")){
        	
        	throw new ActionServletException("atencao.required",null,"Volume Mínimo da Situação de Ligação");
        
        }else{  
        
        	ligacaoEsgotoSituacao.setVolumeMinimoFaturamento(new Integer(volumeMinimoFaturamento));
        
        }
		
		
		// Verifica se o campo Indicador de Existência de Ligação foi preenchido
        if (indicadorExistenciaLigacao == null || indicadorExistenciaLigacao.equals("")){
        	
        	throw new ActionServletException("atencao.required",null,"Indicador de Existência de Ligação");
        
        }else{  
        	
        	ligacaoEsgotoSituacao.setIndicadorExistenciaLigacao(new Short(indicadorExistenciaLigacao));
        
        }
        
		// Verifica se o campo Indicador de Existência de Rede foi preenchido
        
        if (indicadorExistenciaRede == null || indicadorExistenciaRede.equals("")){
        	
        	throw new ActionServletException("atencao.required",null,"Indicador de Existência de Rede");
        
        }else{  
        
        	ligacaoEsgotoSituacao.setIndicadorExistenciaRede(new Short(indicadorExistenciaRede));
        
        }
        
		// Verifica se o Indicador de Faturamento foi preenchido
        if (indicadorFaturamentoSituacao == null || indicadorFaturamentoSituacao.equals("")){
        	
        	throw new ActionServletException("atencao.required",null,"Indicador de Faturamento");
        
        }else{  
        
        	ligacaoEsgotoSituacao.setIndicadorFaturamentoSituacao(new Short(indicadorFaturamentoSituacao));
        
        }
		
		
		// Ultima alteração
		ligacaoEsgotoSituacao.setUltimaAlteracao(new Date());
		
        // Indicador de uso
		Short iu = 1;
		ligacaoEsgotoSituacao.setIndicadorUso(iu);

		FiltroLigacaoEsgotoSituacao filtroLigacaoEsgotoSituacao = new FiltroLigacaoEsgotoSituacao();

		filtroLigacaoEsgotoSituacao.adicionarParametro(new ParametroSimples(
				FiltroLigacaoEsgotoSituacao.DESCRICAO, ligacaoEsgotoSituacao.getDescricao()));

		colecaoPesquisa = (Collection) fachada.pesquisar(filtroLigacaoEsgotoSituacao,
				LigacaoEsgotoSituacao.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {
			
			// Caso já tenha uma Situação de Ligação de Esgoto cadastrada
			throw new ActionServletException(
					"atencao.ligacao_situacao_esgoto_ja_cadastrada", null, ligacaoEsgotoSituacao
							.getDescricao());
		
		} else {
			
			ligacaoEsgotoSituacao.setDescricao(descricao);
			ligacaoEsgotoSituacao.setDescricaoAbreviado(descricaoAbreviado);
			ligacaoEsgotoSituacao.setVolumeMinimoFaturamento(new Integer (volumeMinimoFaturamento));
			ligacaoEsgotoSituacao.setIndicadorExistenciaLigacao(new Short (indicadorExistenciaLigacao));
			ligacaoEsgotoSituacao.setIndicadorExistenciaRede(new Short (indicadorExistenciaRede));
			ligacaoEsgotoSituacao.setIndicadorFaturamentoSituacao(new Short (indicadorFaturamentoSituacao));
			
			Integer idSituacaoEsgotoLigacao = (Integer) fachada.inserir(ligacaoEsgotoSituacao);

			montarPaginaSucesso(httpServletRequest,
					"Situação de Ligação de Esgoto " + descricao
							+ " inserida com sucesso.",
					"Inserir outra Situação de Ligação de Esgoto",
					"exibirInserirLigacaoEsgotoSituacaoAction.do?menu=sim",
					"exibirAtualizarLigacaoEsgotoSituacaoAction.do?idRegistroAtualizacao="
							+ idSituacaoEsgotoLigacao,
					"Atualizar Situação de Ligação de Esgoto Inserida");

			sessao.removeAttribute("InserirLigacaoEsgotoSituacaoActionForm");

			return retorno;
		}

	}
}
