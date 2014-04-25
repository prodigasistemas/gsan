package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.bean.ObterDescricaoSituacaoRAHelper;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.bean.ConsultarImovelRegistroAtendimentoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action responsável pela pre-exibição da pagina de inserir bairro
 * 
 * @author Thiago Tenório
 * @created 28 de Junho de 2004
 */
public class ExibirConsultarRegistroAtendimentoPendentesImovelAction extends
		GcomAction {
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param actionForm
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 * @param httpServletResponse
	 *            Description of the Parameter
	 * @return Description of the Return Value
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Set no mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("consultarRegistroAtendimentoPendentesImovel");
		ConsultarRegistroAtendimentoPendentesImovelActionForm atendimentoPendentesImovelActionForm = (ConsultarRegistroAtendimentoPendentesImovelActionForm) actionForm;
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		// Variavel responsavél pelo preenchimento do imovel no formulário
		/*String idOrdemServico = atendimentoPendentesImovelActionForm
				.getIdOrdemServico(); */

		String idImovel = httpServletRequest.getParameter("idImovel");
		String situacao = httpServletRequest.getParameter("situacao");
		
		sessao.removeAttribute("enderecoImovel");
		sessao.removeAttribute("colecaoConsultarImovelRegistroAtendimentoHelper");
		
		if (idImovel != null && !idImovel.trim().equals("")) {

			Imovel imovelSelecionado = fachada.pesquisarImovelRegistroAtendimento(Util.converterStringParaInteger(idImovel));
			
			if (imovelSelecionado != null){
				
				Collection colecaoConsultarImovelRegistroAtendimentoHelper  = null;
				Collection colecaoRegistroAtendimento = null;
				
				if (situacao != null && !situacao.equalsIgnoreCase("")){
					colecaoRegistroAtendimento = fachada.consultarRegistroAtendimentoImovel(new Integer(idImovel), 
					situacao);
				}
				else{
					colecaoRegistroAtendimento = fachada.consultarRegistroAtendimentoImovel(new Integer(idImovel), 
					null);
				}
				
				
				if(colecaoRegistroAtendimento != null && !colecaoRegistroAtendimento.isEmpty()){
			      
			      Iterator iteratorColecaoRegistroAtendimento = colecaoRegistroAtendimento.iterator();
			      
			      colecaoConsultarImovelRegistroAtendimentoHelper = new ArrayList();
			      
			      while (iteratorColecaoRegistroAtendimento.hasNext()) {
			    	  RegistroAtendimento registroAtendimento = (RegistroAtendimento) iteratorColecaoRegistroAtendimento.next();
			       
			    	  ConsultarImovelRegistroAtendimentoHelper consultarImovelRegistroAtendimentoHelper = new ConsultarImovelRegistroAtendimentoHelper();

				       //id registro atendimento
				       if(registroAtendimento != null  && registroAtendimento.getId() != null ){
				        consultarImovelRegistroAtendimentoHelper.setIdRegistroAtendimento(registroAtendimento.getId().toString());
				       }
				       
				       //tipo de solicitação
				       if(registroAtendimento != null && registroAtendimento.getSolicitacaoTipoEspecificacao() != null 
				         && registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo() != null){
				        
				        consultarImovelRegistroAtendimentoHelper.setTipoSolicitacao(registroAtendimento.getSolicitacaoTipoEspecificacao().getSolicitacaoTipo().getDescricao());
				       }
				       
				       //especificação
				       if(registroAtendimento != null && registroAtendimento.getSolicitacaoTipoEspecificacao() != null){
				        consultarImovelRegistroAtendimentoHelper.setEspecificacao(registroAtendimento.getSolicitacaoTipoEspecificacao().getDescricao());
				       }
				       
				       //data de atendimento
				       if(registroAtendimento != null && registroAtendimento.getRegistroAtendimento() != null ){
				        consultarImovelRegistroAtendimentoHelper.setDataAtendimento(Util.formatarData(registroAtendimento.getRegistroAtendimento()));
				       }
				       
				       //situacao
				       if(registroAtendimento != null && registroAtendimento.getId() != null){
				        ObterDescricaoSituacaoRAHelper obterDescricaoSituacaoRAHelper = 
				         fachada.obterDescricaoSituacaoRA(registroAtendimento.getId());
				        consultarImovelRegistroAtendimentoHelper.setSituacao(obterDescricaoSituacaoRAHelper.getDescricaoSituacao());
				        
				       }
				       
				       colecaoConsultarImovelRegistroAtendimentoHelper.add(consultarImovelRegistroAtendimentoHelper);
			      	}
			      
			      sessao.setAttribute("colecaoConsultarImovelRegistroAtendimentoHelper",colecaoConsultarImovelRegistroAtendimentoHelper);
				}
				else{
					throw new ActionServletException("atencao.imovel_sem_ra_pendente");
				}
				
				atendimentoPendentesImovelActionForm.setMatriculaImovel(imovelSelecionado.getId().toString());
				atendimentoPendentesImovelActionForm.setInscricaoImovel(imovelSelecionado.getInscricaoFormatada());
				atendimentoPendentesImovelActionForm.setSituacaoLigacaoAgua(imovelSelecionado.getLigacaoAguaSituacao().getDescricao());
				atendimentoPendentesImovelActionForm.setSituacaoLigacaoEsgoto(imovelSelecionado.getLigacaoEsgotoSituacao().getDescricao());
			
				httpServletRequest.setAttribute("enderecoImovel", imovelSelecionado.getEnderecoFormatado());
			}
		}
			
			
		return retorno;
	}
}
