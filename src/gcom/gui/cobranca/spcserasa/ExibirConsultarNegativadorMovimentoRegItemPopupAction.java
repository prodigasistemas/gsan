package gcom.gui.cobranca.spcserasa;

import gcom.cobranca.DocumentoTipo;
import gcom.cobranca.NegativadorMovimentoReg;
import gcom.cobranca.NegativadorMovimentoRegItem;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.spcserasa.FiltroNegativadorMovimentoRegItem;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0674] Pesquisar Movimento Negativador
 * 
 * @author Yara Taciane
 * @date 27/12/2007
 * 
 */
public class ExibirConsultarNegativadorMovimentoRegItemPopupAction extends GcomAction {

	/**
	 * 
	 * [UC0438] Este caso de uso efetua pesquisa de Movimento do Negativador
	 * 
	 * 
	 * @author Yara Taciane
	 * @date 03/08/2006
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

		HttpSession sessao = httpServletRequest.getSession(false);	
		
		ActionForward retorno = actionMapping.findForward("negativadorMovimentoRegItemConsultarPopup");
		
		ConsultarNegativadorMovimentoRegItemPopupActionForm form = (ConsultarNegativadorMovimentoRegItemPopupActionForm) actionForm;
		
		NegativadorMovimentoReg negativadorMovimentoReg = (NegativadorMovimentoReg) sessao.getAttribute("negativadorMovimentoReg");
		
		Fachada fachada = Fachada.getInstancia();
		
		
		if(negativadorMovimentoReg != null && !negativadorMovimentoReg.equals("")){
			
			
			  form.setNegativador(negativadorMovimentoReg.getNegativadorMovimento().getNegativador().getCliente().getNome());
//			  if(negativadorMovimentoReg.getImovel()!= null){
//				  form.setMatriculaImovel(negativadorMovimentoReg.getImovel().getId().toString()); 
//			  }
//			 if(negativadorMovimentoReg.getImovel()!= null && negativadorMovimentoReg.getImovel().getInscricaoFormatada()!= null){
//				 form.setInscricao(negativadorMovimentoReg.getImovel().getInscricaoFormatada());
//			 }
			  
			
			  //situacao da ligacao da agua
			  //situacao da ligacao esgoto
				
			  FiltroNegativadorMovimentoRegItem filtroNegativadorMovimentoRegItem = new FiltroNegativadorMovimentoRegItem();
			  filtroNegativadorMovimentoRegItem.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.NEGATIVADOR_MOVIMENTO_REG_ID,
								new Integer(negativadorMovimentoReg.getId())));
			  
			  filtroNegativadorMovimentoRegItem.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.DOCUMENTO_TIPO_ID,
					  new Integer(DocumentoTipo.CONTA)));
				  
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimentoReg");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimentoReg.imovel");
			  
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("guiaPagamentoGeral");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoCreditoSituacaoAtual");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.debitoCreditoSituacaoAtual");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("contaGeral");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("contaGeral.conta");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("contaGeral.contaHistorico");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("cobrancaDebitoSituacao");
			  filtroNegativadorMovimentoRegItem.adicionarCaminhoParaCarregamentoEntidade("cobrancaDebitoSituacaoAposExclusao");
			  
			  Collection<NegativadorMovimentoRegItem> collNegativadorMovimentoRegItem = fachada.pesquisar(filtroNegativadorMovimentoRegItem,
					  NegativadorMovimentoRegItem.class.getName());
			  
			  sessao.setAttribute("collNegativadorMovimentoRegItem", collNegativadorMovimentoRegItem);
				
				
				//-----------------------------------------------------------------------------------------------
        		  FiltroNegativadorMovimentoRegItem filtroNMRIGuiaPagamento = new FiltroNegativadorMovimentoRegItem();
        		  
        		  filtroNMRIGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.NEGATIVADOR_MOVIMENTO_REG_ID,
									negativadorMovimentoReg.getId()));				  
        		  filtroNMRIGuiaPagamento.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoRegItem.DOCUMENTO_TIPO_ID,
						  DocumentoTipo.GUIA_PAGAMENTO));
					  
				  
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimentoReg");
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("negativadorMovimentoReg.imovel");				  
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("guiaPagamentoGeral");				  
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("guiaPagamentoGeral.guiaPagamento");
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("guiaPagamentoGeral.guiaPagamentoHistorico");				  
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("guiaPagamentoGeral.guiaPagamento.debitoCreditoSituacaoAtual");
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("documentoTipo");
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral");
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrar.debitoCreditoSituacaoAtual");
        		  filtroNMRIGuiaPagamento.adicionarCaminhoParaCarregamentoEntidade("debitoACobrarGeral.debitoACobrarHistorico.debitoCreditoSituacaoAtual");


        		  Collection<NegativadorMovimentoRegItem> collNMIGuiaPagamento = fachada.pesquisar(filtroNMRIGuiaPagamento,
        				  NegativadorMovimentoRegItem.class.getName());	
        		  
        		  sessao.setAttribute("collNegativadorMovimentoRegItem2", collNMIGuiaPagamento);
		}
		
		
		
		
		
		
		return retorno;
	}
	
}
