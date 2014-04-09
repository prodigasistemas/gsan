package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.bean.RegistroAtendimentoEncerradoLocalOcorrenciaHelper;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Esta classe tem por finalidade exibir para o usuário a tela com os registros de 
 * atendimento encerrados
 * 
 * @author Raphael Rossiter
 * @date 17/07/2006
 */
public class PesquisarRegistrosAtendimentoEncerradosLocalOcorrenciaAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping
				.findForward("pesquisarRegistrosAtendimentoEncerradosLocalOcorrencia");

		Fachada fachada = Fachada.getInstancia();
		
		//HttpSession sessao = httpServletRequest.getSession(false);
		
		PesquisarRegistrosAtendimentoEncerradosLocalOcorrenciaActionForm form = 
		(PesquisarRegistrosAtendimentoEncerradosLocalOcorrenciaActionForm) actionForm;

		Integer idImovel = Util.converterStringParaInteger(httpServletRequest.getParameter("idImovel"));
		Integer idLogradouroCep = Util.converterStringParaInteger(httpServletRequest.getParameter("idLogradouroCep"));
		Integer idLogradouroBairro = Util.converterStringParaInteger(httpServletRequest.getParameter("idLogradouroBairro"));
		Integer idEspecificacao = Util.converterStringParaInteger(httpServletRequest.getParameter("idEspecificacao"));
		
		
		//[SB0015] - Verifica Registro de Atendimento Encerrado para o Local da Ocorrência
		RegistroAtendimentoEncerradoLocalOcorrenciaHelper raEncerradoHelper = fachada
		.verificarRegistroAtendimentoEncerradoLocalOcorrencia(idImovel,idEspecificacao, 
		idLogradouroBairro, idLogradouroCep);
		
		form.setIdSolicitacaoTipo(raEncerradoHelper.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo().getId().toString());
		form.setDescricaoSolicitacaoTipo(raEncerradoHelper.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo().getDescricao());
		form.setIdSolicitacaoTipoEspecificacao(raEncerradoHelper.getSolicitacaoTipoEspecificacao().getId().toString());
		form.setDescricaoSolicitacaoTipoEspecificacao(raEncerradoHelper.getSolicitacaoTipoEspecificacao().getDescricao());
		
		if (raEncerradoHelper.getImovel() != null){
			form.setIdImovel(raEncerradoHelper.getImovel().getId().toString());
			form.setInscricaoImovel(raEncerradoHelper.getImovel().getInscricaoFormatada());
			
			httpServletRequest.setAttribute("enderecoRAEncerrado", raEncerradoHelper.getImovel().getEnderecoFormatadoAbreviado());
		}
		else{
			
			form.setIdImovel("");
			form.setInscricaoImovel("");
			
			httpServletRequest.setAttribute("enderecoRAEncerrado", raEncerradoHelper.getEnderecoLocalOcorrencia());
		}
		
		httpServletRequest.setAttribute("colecaoRegistroAtendimento", raEncerradoHelper.getColecaoRegistroAtendimento());
		
		return retorno;
	}
}
