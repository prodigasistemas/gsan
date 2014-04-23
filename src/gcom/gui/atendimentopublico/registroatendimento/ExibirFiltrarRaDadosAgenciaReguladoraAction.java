package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotReclamacao;
import gcom.atendimentopublico.registroatendimento.AgenciaReguladoraMotRetorno;
import gcom.atendimentopublico.registroatendimento.AtendimentoMotivoEncerramento;
import gcom.atendimentopublico.registroatendimento.FiltroAgenciaReguladoraMotReclamacao;
import gcom.atendimentopublico.registroatendimento.FiltroAgenciaReguladoraMotRetorno;
import gcom.atendimentopublico.registroatendimento.FiltroAtendimentoMotivoEncerramento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0538] Filtrar RAs na Agencia Reguladora
 *
 * @author Kássia Albuquerque
 * @date 02/05/2007
 */

public class ExibirFiltrarRaDadosAgenciaReguladoraAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

			
			ActionForward retorno = actionMapping.findForward("filtrarRaDadosAgenciaReguladora");
	
			FiltrarRaDadosAgenciaReguladoraActionForm form = (FiltrarRaDadosAgenciaReguladoraActionForm) actionForm;
	
			Fachada fachada = Fachada.getInstancia();
	
			
			if (httpServletRequest.getParameter("menu") != null) {
				
				form.setIndicadorSituacaoAgencia(ConstantesSistema.SITUACAO_AGENCIA_TODOS.toString());
				form.setIndicadorSituacaoRA(ConstantesSistema.SITUACAO_RA_AGENCIA_TODOS.toString());
			}

			
			
			// CARREGANDO MOTIVO RECLAMAÇÃO AGENCIA DA TABELA AGENCIA_REGULADORA_MOTIVO_RECLAMAÇÃO
			
			FiltroAgenciaReguladoraMotReclamacao filtroAgenciaReguladoraMotReclamacao = 
						new FiltroAgenciaReguladoraMotReclamacao();
			
			filtroAgenciaReguladoraMotReclamacao.setCampoOrderBy(FiltroAgenciaReguladoraMotReclamacao.DESCRICAO);
			
			Collection colecaoMotivoReclamacao = fachada.pesquisar(
						filtroAgenciaReguladoraMotReclamacao, AgenciaReguladoraMotReclamacao.class.getName());
			
			if (colecaoMotivoReclamacao == null || colecaoMotivoReclamacao.isEmpty() ){
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",null,"Motivo Reclamação da Agência");
			}
			
			httpServletRequest.setAttribute("colecaoMotivoReclamacao",colecaoMotivoReclamacao);
			
			
			
			// CARREGANDO MOTIVO ENCERRAMENTO ATENDIMENTO DA TABELA ATENDIMENTO_MOTIVO_ENCERRAMENTO
			
			FiltroAtendimentoMotivoEncerramento filtroAtendimentoMotivoEncerramento = 
						new FiltroAtendimentoMotivoEncerramento();
			
			filtroAtendimentoMotivoEncerramento.setCampoOrderBy(FiltroAtendimentoMotivoEncerramento.DESCRICAO);
			
			Collection colecaoMotivoEncerramentoAtendimento = fachada.pesquisar(
					filtroAtendimentoMotivoEncerramento,AtendimentoMotivoEncerramento.class.getName());
			
			if (colecaoMotivoEncerramentoAtendimento == null || colecaoMotivoEncerramentoAtendimento.isEmpty() ){
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",null,"Motivo Encerramento do Atendimento");
			}
			
			httpServletRequest.setAttribute("colecaoMotivoEncerramentoAtendimento",colecaoMotivoEncerramentoAtendimento);
			
			
			
			// CARREGANDO MOTIVO ENCERRAMENTO ATENDIMENTO DA TABELA AGENCIA_REGULADORA_MOTIVO_RETORNO
			
			FiltroAgenciaReguladoraMotRetorno filtroAgenciaReguladoraMotRetorno = 
						new FiltroAgenciaReguladoraMotRetorno();
			
			filtroAgenciaReguladoraMotRetorno.setCampoOrderBy(FiltroAgenciaReguladoraMotRetorno.DESCRICAO);
			
			Collection colecaoMotivoRetornoAgencia = fachada.pesquisar(
					filtroAgenciaReguladoraMotRetorno, AgenciaReguladoraMotRetorno.class.getName());
			
			if (colecaoMotivoRetornoAgencia == null || colecaoMotivoRetornoAgencia.isEmpty() ){
				throw new ActionServletException("atencao.entidade_sem_dados_para_selecao",null,"Motivo do Retorno para Agência");
			}
			
			httpServletRequest.setAttribute("colecaoMotivoRetornoAgencia",colecaoMotivoRetornoAgencia);
			
			
			return retorno;
		}
}
