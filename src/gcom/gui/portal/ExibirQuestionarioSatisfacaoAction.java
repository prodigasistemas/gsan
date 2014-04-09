package gcom.gui.portal;

import gcom.atendimentopublico.registroatendimento.FiltroRegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Classe Responsável por exibir o Questionario de Satisfacao do Cliente
 * 
 * @author Paulo Diniz
 * @date 23/06/2011
 */
public class ExibirQuestionarioSatisfacaoAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirQuestionarioSatisfacao");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String idRegistroAtendimentoEncerrado = httpServletRequest.getParameter("idRegistroAtendimentoEncerrado");
		
		if(idRegistroAtendimentoEncerrado == null || idRegistroAtendimentoEncerrado.equals("")){
			sessao.setAttribute("operacao", "RAnaoEncontrada");
		}else{
			Fachada fachada = Fachada.getInstancia();
			
			//3.1.1HttpSession sessao = httpServletRequest.getSession(false);
			String nomeSolicitante = fachada.obterNomeSolicitanteRA(Integer.parseInt(idRegistroAtendimentoEncerrado));
			//3.1.2
			String localServico = fachada.obterEnderecoOcorrenciaRA(Integer.parseInt(idRegistroAtendimentoEncerrado));
			
			//3.1.3 e 3.1.4
			FiltroRegistroAtendimento filtro = new FiltroRegistroAtendimento();
			filtro.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao");
			filtro.adicionarCaminhoParaCarregamentoEntidade("solicitacaoTipoEspecificacao.servicoTipo");
			filtro.adicionarParametro(new ParametroSimples(FiltroRegistroAtendimento.ID, idRegistroAtendimentoEncerrado));
			
			List<RegistroAtendimento> listaRA = new ArrayList<RegistroAtendimento>(fachada.pesquisar(filtro, RegistroAtendimento.class.getName()));
			if(listaRA == null || listaRA.isEmpty() 
					|| listaRA.get(0).getDataEncerramento() == null){
				sessao.setAttribute("operacao", "RAnaoEncontrada");
			}else{
				
				boolean questionarioRespondido = fachada.verificaExistenciaQuestionarioSatisfacaoRespondido(Integer.parseInt(idRegistroAtendimentoEncerrado));
				if(questionarioRespondido){
					sessao.removeAttribute("operacao");
				}else{
					sessao.setAttribute("nomeSolicitante", nomeSolicitante);
					sessao.setAttribute("localServico", localServico);
					sessao.setAttribute("tipoServico", listaRA.get(0).getSolicitacaoTipoEspecificacao().getServicoTipo().getDescricao());
					sessao.setAttribute("dataConclusao", Util.formatarData(listaRA.get(0).getDataEncerramento()));
					
					sessao.setAttribute("operacao", "exibirQuestionario");
					
					sessao.setAttribute("idRegistroAtendimentoEncerrado", idRegistroAtendimentoEncerrado);
				}
				
			}
			
		}

		return retorno;
	}

	
}
