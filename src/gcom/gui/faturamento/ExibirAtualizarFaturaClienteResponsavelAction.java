package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItem;
import gcom.faturamento.conta.FiltroFaturaItem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import java.math.BigDecimal;
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
 * @author Flávio Leonardo
 * @date 26/11/2008
 */
public class ExibirAtualizarFaturaClienteResponsavelAction extends GcomAction {

	/**
	 * Método responsavel por responder a requisicao
	 * 
	 * @param actionMapping
	 *            Descrição do parâmetro
	 * @param actionForm
	 *            Descrição do parâmetro
	 * @param httpServletRequest
	 *            Descrição do parâmetro
	 * @param httpServletResponse
	 *            Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o caminho de retorno
		ActionForward retorno = actionMapping
				.findForward("atualizarFaturaClienteResponsavel");

		FiltrarFaturaClienteResponsavelActionForm form = (FiltrarFaturaClienteResponsavelActionForm) actionForm;

		// Cria uma instância da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar quando houver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		FiltroFaturaItem filtroFaturaItem = (FiltroFaturaItem) sessao
			.getAttribute("filtroFaturaItem");
		
		Collection<FaturaItem> colecaoFaturaItem = null;

		if(sessao.getAttribute("colecaoFaturaItem") == null){
			colecaoFaturaItem = fachada.pesquisar(filtroFaturaItem, FaturaItem.class.getName());
		}else{
			colecaoFaturaItem = (Collection)sessao.getAttribute("colecaoFaturaItem");
		}
		

		if(colecaoFaturaItem.isEmpty()){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado");
		}else{
			sessao.setAttribute("colecaoFaturaItem", colecaoFaturaItem);
			
			FaturaItem faturaItem = (FaturaItem)colecaoFaturaItem.iterator().next();
			
			Fatura fatura = faturaItem.getFatura();
			sessao.setAttribute("fatura", fatura);
			form.setValorFatura(Util.formatarMoedaReal(fatura.getDebito()));
			form.setDataVencimentoFatura(Util.formatarData(fatura.getVencimento()));
		}
		
		
		String[] faturaItemRemoverId = form.getIdRegistrosRemocao();

		Collection colecaoFaturaItemRemover = null;
		if(sessao.getAttribute("colecaoFaturaItemRemover") != null){
			colecaoFaturaItemRemover = (Collection)sessao.getAttribute("colecaoFaturaItemRemover");
		}else{
			colecaoFaturaItemRemover = new ArrayList();
		}
		
		if(faturaItemRemoverId != null && !faturaItemRemoverId.equals("")){
			
			for(int i=0; i< faturaItemRemoverId.length; i++){
				Iterator iterator = colecaoFaturaItem.iterator();
					
				while(iterator.hasNext()){
					FaturaItem faturaItemRemover = (FaturaItem) iterator.next();
					if(faturaItemRemover.getImovel().getId() != null && 
							faturaItemRemover.getImovel().getId().equals(new Integer(faturaItemRemoverId[i]))){
						colecaoFaturaItemRemover.add(faturaItemRemover);
						iterator.remove();
						break;
					}
				}
					
			}
			
			sessao.setAttribute("colecaoFaturaItemRemover",colecaoFaturaItemRemover);
		}
		
		if(!colecaoFaturaItemRemover.isEmpty()){
			Iterator iteratorRemovidos = colecaoFaturaItemRemover.iterator();
			
			while(iteratorRemovidos.hasNext()){
				FaturaItem faturaItemRemovido = (FaturaItem)iteratorRemovidos.next();
				Iterator iterator = colecaoFaturaItem.iterator();
				
				while(iterator.hasNext()){
					FaturaItem faturaItemRemover = (FaturaItem) iterator.next();
					if(faturaItemRemover.getImovel().getId() != null && faturaItemRemovido.getImovel().getId() != null
						&& faturaItemRemover.getImovel().getId().equals(faturaItemRemovido.getImovel().getId())){
						iterator.remove();
						break;
					}
				}
			}
		}
		
		if(colecaoFaturaItem.isEmpty()){
			throw new ActionServletException("atencao.fatura.cliente.responsavel.conter.uma");
		}else{
			Iterator iteratorSoma = colecaoFaturaItem.iterator();
			BigDecimal valorTotal = BigDecimal.ZERO;
			
			while(iteratorSoma.hasNext()){
				FaturaItem faturaItemSoma = (FaturaItem) iteratorSoma.next();
				
				valorTotal = valorTotal.add(faturaItemSoma.getValorConta());
				
				form.setValorFatura(Util.formatarMoedaReal(valorTotal));
			}
		}

		return retorno;
	}
}
