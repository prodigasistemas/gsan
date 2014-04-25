package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.SolicitacaoTipoEspecificacao;
import gcom.atendimentopublico.registroatendimento.Tramite;
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
 * Action que define o pré-processamento da página de consulta de tramites do ra.
 * 
 * @author Leonardo Regis
 * @created 11/08/2006
 */
public class ExibirConsultarRegistroAtendimentoTramiteAction extends GcomAction {
	/**
	 * Excute do Exibir Consultar RA Tramites do Popup
	 *
	 * @author Leonardo Regis
	 * @date 11/08/2006
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return forward
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("consultarRegistroAtendimentoTramite");
		
		Fachada fachada = Fachada.getInstancia();
		
		ConsultarRegistroAtendimentoTramiteActionForm consultarRegistroAtendimentoTramite = 
				(ConsultarRegistroAtendimentoTramiteActionForm) actionForm;
		
		RegistroAtendimento registroAtendimento = 
			pesquisarRegistroAtendimento(new Integer(consultarRegistroAtendimentoTramite.getNumeroRA()));
		
		consultarRegistroAtendimentoTramite.setNumeroRA(""+registroAtendimento.getId());
		
		ObterDescricaoSituacaoRAHelper situacaoRA = 
			fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
		
		consultarRegistroAtendimentoTramite.setSituacaoRA(situacaoRA.getDescricaoSituacao());		
		
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = 
			registroAtendimento.getSolicitacaoTipoEspecificacao();
		
		if(solicitacaoTipoEspecificacao != null){
			
			if(solicitacaoTipoEspecificacao.getSolicitacaoTipo() != null){
				consultarRegistroAtendimentoTramite.setIdTipoSolicitacao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId()+"");
				consultarRegistroAtendimentoTramite.setTipoSolicitacao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao());	
			}
			consultarRegistroAtendimentoTramite.setIdEspecificacao(solicitacaoTipoEspecificacao.getId()+"");
			consultarRegistroAtendimentoTramite.setEspecificacao(solicitacaoTipoEspecificacao.getDescricao());		
		}

		UnidadeOrganizacional unidadeAtual = fachada.obterUnidadeAtualRA(registroAtendimento.getId());
		
		if(unidadeAtual != null){
			consultarRegistroAtendimentoTramite.setIdUnidadeAtual(""+unidadeAtual.getId());
			consultarRegistroAtendimentoTramite.setUnidadeAtual(unidadeAtual.getDescricao());
		}
		
		UnidadeOrganizacional unidadeAtendimento = fachada.obterUnidadeAtendimentoRA(registroAtendimento.getId());
		
		if(unidadeAtendimento != null){
			consultarRegistroAtendimentoTramite.setIdUnidadeAtendimento(""+unidadeAtendimento.getId());
			consultarRegistroAtendimentoTramite.setUnidadeAtendimento(unidadeAtendimento.getDescricao());		
		}
		
		//Trâmites do RA
		Collection<Tramite> colecaoTramite = fachada.obterTramitesRA(registroAtendimento.getId());
		
		if(colecaoTramite != null &&
				!colecaoTramite.isEmpty()) {
			consultarRegistroAtendimentoTramite.setColecaoTramites(colecaoTramite);
		} else {
			throw new ActionServletException("atencao.colsutar_tramites_consulta_vazia"); 
		}
		
		return retorno;
	}

	/**
	 * Consulta o registro atendimento pelo id informado
	 * 
	 * @author Leonardo Regis
	 * @created 11/08/2006
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
