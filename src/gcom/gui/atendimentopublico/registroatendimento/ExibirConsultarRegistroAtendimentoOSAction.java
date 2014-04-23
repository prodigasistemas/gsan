package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
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
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de consulta de os do ra.
 * 
 * @author Leonardo Regis
 * @created 11/08/2006
 */
public class ExibirConsultarRegistroAtendimentoOSAction extends GcomAction {
	/**
	 * Execute do Exibir Consultar RA OS do Popup
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
		ActionForward retorno = actionMapping.findForward("consultarRegistroAtendimentoOS");
		
		Fachada fachada = Fachada.getInstancia();
		
		ConsultarRegistroAtendimentoOSActionForm consultarRegistroAtendimentoOS = 
				(ConsultarRegistroAtendimentoOSActionForm) actionForm;
		
		RegistroAtendimento registroAtendimento = 
			pesquisarRegistroAtendimento(new Integer(consultarRegistroAtendimentoOS.getNumeroRA()));
		
		consultarRegistroAtendimentoOS.setNumeroRA(""+registroAtendimento.getId());
		
		ObterDescricaoSituacaoRAHelper situacaoRA = 
			fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
		consultarRegistroAtendimentoOS.setSituacaoRA(situacaoRA.getDescricaoSituacao());		
		
		SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao = 
			registroAtendimento.getSolicitacaoTipoEspecificacao();
		if(solicitacaoTipoEspecificacao != null){
			if(solicitacaoTipoEspecificacao.getSolicitacaoTipo() != null){
				consultarRegistroAtendimentoOS.setTipoSolicitacao(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getDescricao());	
				consultarRegistroAtendimentoOS.setTipoSolicitacaoId(solicitacaoTipoEspecificacao.getSolicitacaoTipo().getId()+"");
			}
			consultarRegistroAtendimentoOS.setEspecificacao(solicitacaoTipoEspecificacao.getDescricao());
			consultarRegistroAtendimentoOS.setEspecificacaoId(solicitacaoTipoEspecificacao.getId()+"");
		}

		UnidadeOrganizacional unidadeAtual = fachada.obterUnidadeAtualRA(registroAtendimento.getId());
		if(unidadeAtual != null){
			consultarRegistroAtendimentoOS.setIdUnidadeAtual(""+unidadeAtual.getId());
			consultarRegistroAtendimentoOS.setUnidadeAtual(unidadeAtual.getDescricao());
		}
		
		//OS's do RA
		Collection<OrdemServico> colecaoOS = fachada.obterOSRA(registroAtendimento.getId());
		if(colecaoOS != null &&
				!colecaoOS.isEmpty()) {
			consultarRegistroAtendimentoOS.setColecaoOS(colecaoOS);
		} else {
			throw new ActionServletException("atencao.consultar_os_consulta_vazia"); 
		}
		
        
        // o botão encerrar so aparece no popup de consultar ordem de serviço 
        // qd for chamada a partir do manter RA
        HttpSession sessao = httpServletRequest.getSession(false);
        if (httpServletRequest.getParameter("botaoEncerraOs") != null){
            sessao.setAttribute("botaoEncerraOs",httpServletRequest.getParameter("botaoEncerraOs"));
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
