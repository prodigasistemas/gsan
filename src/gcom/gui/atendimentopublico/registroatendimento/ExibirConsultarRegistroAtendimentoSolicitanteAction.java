package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de consulta de solicitantes do ra.
 * 
 * @author Rafael Pinto
 * @created 14/08/2006
 */
public class ExibirConsultarRegistroAtendimentoSolicitanteAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarRegistroAtendimentoSolicitante");
		
		Fachada fachada = Fachada.getInstancia();
		
		ConsultarRegistroAtendimentoSolicitanteActionForm consultarRegistroAtendimentoSolicitante = 
				(ConsultarRegistroAtendimentoSolicitanteActionForm) actionForm;
		
		RegistroAtendimento registroAtendimento = 
			pesquisarRegistroAtendimento(new Integer(consultarRegistroAtendimentoSolicitante.getNumeroRA()));
		
		consultarRegistroAtendimentoSolicitante.setNumeroRA(""+registroAtendimento.getId());
		
		ObterDescricaoSituacaoRAHelper situacaoRA = 
			fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
		
		consultarRegistroAtendimentoSolicitante.setSituacaoRA(situacaoRA.getDescricaoSituacao());		
		
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = 
			registroAtendimento.getSolicitacaoTipoEspecificacao();
		
		if(solicitacaoTipoEspecificacao != null){
			
			if(solicitacaoTipoEspecificacao.getSolicitacaoTipo() != null){
				consultarRegistroAtendimentoSolicitante.setIdTipoSolicitacao(""+solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId());
				consultarRegistroAtendimentoSolicitante.setTipoSolicitacao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao());	
			}
			consultarRegistroAtendimentoSolicitante.setIdEspecificacao(""+solicitacaoTipoEspecificacao.getId());
			consultarRegistroAtendimentoSolicitante.setEspecificacao(solicitacaoTipoEspecificacao.getDescricao());		
		}

		UnidadeOrganizacional unidadeAtual = fachada.obterUnidadeAtualRA(registroAtendimento.getId());
		
		if(unidadeAtual != null){
			consultarRegistroAtendimentoSolicitante.setIdUnidadeAtual(""+unidadeAtual.getId());
			consultarRegistroAtendimentoSolicitante.setUnidadeAtual(unidadeAtual.getDescricao());
		}
		
		//Solicitantes do RA
		Collection<RegistroAtendimentoSolicitante> colecaoSolicitantes = 
			fachada.obterRASolicitante(registroAtendimento.getId());
		
		if(colecaoSolicitantes != null && !colecaoSolicitantes.isEmpty()) {
			consultarRegistroAtendimentoSolicitante.setColecaoRegistroAtendimentoSolicitante(colecaoSolicitantes);
		} else {
			throw new ActionServletException("atencao.consultar_solicitantes_consulta_vazia"); 
		}
		
		return retorno;
	}

	/**
	 * Consulta o registro atendimento pelo id informado
	 * 
	 * @author Rafael Pinto
	 * @created 14/08/2006
	 */
	private RegistroAtendimento pesquisarRegistroAtendimento(Integer id){

		RegistroAtendimento retorno = null;
		
		Fachada fachada = Fachada.getInstancia();

		Collection colecaoRegistroAtendimento = null; 

		FiltroRegistroAtendimento filtroRegistroAtendimento = new FiltroRegistroAtendimento();

		filtroRegistroAtendimento.adicionarParametro(new ParametroSimples(
				FiltroRegistroAtendimento.ID,id));
		
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao");
		filtroRegistroAtendimento.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao.solicitacaoTipo");

		colecaoRegistroAtendimento = fachada.pesquisar(filtroRegistroAtendimento, 
			RegistroAtendimento.class.getName());

		if (colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()) {
			retorno = (RegistroAtendimento) Util.retonarObjetoDeColecao(colecaoRegistroAtendimento);
		} else {
			throw new ActionServletException("atencao.naocadastrado",null, "Registro Atendimento");
		}
		
		return retorno;
	}
}
