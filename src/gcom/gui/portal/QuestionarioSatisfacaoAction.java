package gcom.gui.portal;

import gcom.atendimentopublico.portal.QuestionarioSatisfacaoCliente;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * [UC1181] Registrar Informações de Pesquisa de Satisfação
 * 
 * @author Paulo Diniz
 * @date 19/06/2011
 *
 */
public class QuestionarioSatisfacaoAction extends GcomAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("exibirQuestionarioSatisfacao");
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String operacao = (String) sessao.getAttribute("operacao");
		
		if(operacao == null || !operacao.equals("sucesso")){
			QuestionarioSatisfacaoActionForm form = (QuestionarioSatisfacaoActionForm) actionForm;
			
			String indicadorBemAtendido = form.getIndicadorBemAtendido();
			String comentarioBemAtendido = form.getComentarioBemAtendido();
			String indicadorSolicitacaoResolvida = form.getIndicadorSolicitacaoResolvida();
			String comentarioSolicitacaoResolvida = form.getComentarioSolicitacaoResolvida();
			String indicadorContatoEquipeCampo = form.getIndicadorContatoEquipeCampo();
			String comentarioContatoEquipeCampo = form.getComentarioContatoEquipeCampo();
			String nota = form.getNota();
			String comentarioGeral = form.getComentarioGeral();
			String idRegistroAtendimentoEncerrado = form.getIdRegistroAtendimentoEncerrado();
			
			validarCampoObrigatorios(indicadorBemAtendido,
					indicadorSolicitacaoResolvida, indicadorContatoEquipeCampo,
					nota);
			
			QuestionarioSatisfacaoCliente questionario = carregarCamposFormulario(
					indicadorBemAtendido, comentarioBemAtendido,
					indicadorSolicitacaoResolvida, comentarioSolicitacaoResolvida,
					indicadorContatoEquipeCampo, comentarioContatoEquipeCampo,
					nota, comentarioGeral, idRegistroAtendimentoEncerrado);
			
			Fachada.getInstancia().registrarQuestionarioSatisfacaoCliente(questionario);
			
			sessao.setAttribute("operacao", "sucesso");
		}

		return retorno;
	}

	private QuestionarioSatisfacaoCliente carregarCamposFormulario(
			String indicadorBemAtendido, String comentarioBemAtendido,
			String indicadorSolicitacaoResolvida,
			String comentarioSolicitacaoResolvida,
			String indicadorContatoEquipeCampo,
			String comentarioContatoEquipeCampo, String nota,
			String comentarioGeral, String idRegistroAtendimentoEncerrado) {
		
		QuestionarioSatisfacaoCliente questionario = new QuestionarioSatisfacaoCliente();
		if(indicadorBemAtendido != null && !indicadorBemAtendido.equals("")){
			questionario.setIndicadorBemAtendido(new Short(indicadorBemAtendido));
		}
		if(indicadorSolicitacaoResolvida != null && !indicadorSolicitacaoResolvida.equals("")){
			questionario.setIndicadorSolicitacaoResolvida(new Short(indicadorSolicitacaoResolvida));
		}
		if(indicadorContatoEquipeCampo != null && !indicadorContatoEquipeCampo.equals("")){
			questionario.setIndicadorContatoEquipeCampo(new Short(indicadorContatoEquipeCampo));
		}
		if(nota != null && !nota.equals("")){
			questionario.setNota(new Short(nota));
		}
		if(comentarioBemAtendido != null && !comentarioBemAtendido.equals("")){
			questionario.setComentarioBemAtendido(comentarioBemAtendido);
		}
		if(comentarioBemAtendido != null && !comentarioSolicitacaoResolvida.equals("")){
			questionario.setComentarioSolicitacaoResolvida(comentarioSolicitacaoResolvida);
		}
		if(comentarioContatoEquipeCampo != null && !comentarioContatoEquipeCampo.equals("")){
			questionario.setComentarioContatoEquipeCampo(comentarioContatoEquipeCampo);
		}
		if(comentarioGeral != null && !comentarioGeral.equals("")){
			questionario.setComentarioGeral(comentarioGeral);
		}
		questionario.setUltimaAlteracao(new Date());
		RegistroAtendimento registroAtendimento = new RegistroAtendimento();
		registroAtendimento.setId(Integer.parseInt(idRegistroAtendimentoEncerrado));
		questionario.setRegistroAtendimento(registroAtendimento);
		return questionario;
	}

	private void validarCampoObrigatorios(String indicadorBemAtendido,
			String indicadorSolicitacaoResolvida,
			String indicadorContatoEquipeCampo, String nota) {
		if(indicadorBemAtendido == null || indicadorBemAtendido.equals("")){
			ActionServletException ex = new ActionServletException(
					"atencao.campo.informada", null, "Pergunta você foi bem atendido");	
			throw ex;
		}
		
		if(indicadorSolicitacaoResolvida == null || indicadorSolicitacaoResolvida.equals("")){
			ActionServletException ex = new ActionServletException(
					"atencao.campo.informada", null, "Pergunta sua solicitação foi resolvida");	
			throw ex;
		}
		
		if(indicadorContatoEquipeCampo == null || indicadorContatoEquipeCampo.equals("")){
			ActionServletException ex = new ActionServletException(
					"atencao.campo.informada", null, "Pergunta sobre contato com a equipe de campo");	
			throw ex;
		}
		
		if(nota == null || nota.equals("")){
			ActionServletException ex = new ActionServletException(
					"atencao.campo.informada", null, "Nota sobre atendimento");	
			throw ex;
		}
	}

}