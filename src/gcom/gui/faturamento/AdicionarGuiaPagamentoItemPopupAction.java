package gcom.gui.faturamento;

import gcom.arrecadacao.pagamento.GuiaPagamentoItem;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.conta.ContaGeral;
import gcom.faturamento.debito.DebitoTipo;
import gcom.faturamento.debito.FiltroDebitoTipo;
import gcom.financeiro.FinanciamentoTipo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Adicionar Guia Pagamento Item Popup
 * 
 * @author Flávio Leonardo
 * @created 25/04/2006
 */
public class AdicionarGuiaPagamentoItemPopupAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping.findForward("exibirAdicionarGuiaPagamentoItemPopup");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);

		// Instância do formulário que está sendo utilizado
		AdicionarGuiaPagamentoItemActionForm form = (AdicionarGuiaPagamentoItemActionForm) actionForm;

		String idDebitoTipo = form.getIdTipoDebito();
		
		//Criando o guia pagamento item
		GuiaPagamentoItem guiaPagamentoItem = new GuiaPagamentoItem();
		
		guiaPagamentoItem.setUltimaAlteracao(new Date());
		guiaPagamentoItem.setValorDebito(Util.formatarMoedaRealparaBigDecimal(form.getValorTotalServico()));
		

		FiltroDebitoTipo filtroDebitoTipo = new FiltroDebitoTipo();
        filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("financiamentoTipo");
        filtroDebitoTipo.adicionarParametro(new ParametroSimples(FiltroDebitoTipo.ID, idDebitoTipo));
        filtroDebitoTipo.adicionarCaminhoParaCarregamentoEntidade("lancamentoItemContabil");
        
		Collection colecaoDebitoTipo = fachada.pesquisar(filtroDebitoTipo, DebitoTipo.class.getName());
		
		//String desabilitaCampo = httpServletRequest.getParameter("desabilitaIdDebito");
		
		/*if(desabilitaCampo != null && !desabilitaCampo.equals("")){
			httpServletRequest.setAttribute("desabilitaIdDebito", "sim");
		}*/
		
		if(colecaoDebitoTipo.isEmpty()){
			
			throw new ActionServletException(
			"pesquisa.registro.inexistente");
		}
		
		//[FS0021] Valor informado maior ou igual que valor da conta selecionada.
		Integer idConta = null;
		if (httpServletRequest.getParameter("conta") != null){
			
			idConta = Integer.valueOf(httpServletRequest.getParameter("conta"));
			
			Conta conta = (Conta) Util.retonarObjetoDeColecao(fachada.obterConta(idConta));
			
			ContaGeral contaGeral = new ContaGeral();
			contaGeral.setId(idConta);
			contaGeral.setConta(conta);
			
			guiaPagamentoItem.setContaGeral(contaGeral);
		}
		
		fachada.validarValorTotalServicoParaPagamentoParcial(idConta, Integer.valueOf(idDebitoTipo), 
		guiaPagamentoItem.getValorDebito());
		
		DebitoTipo debitoTipo = (DebitoTipo) colecaoDebitoTipo.iterator().next();
		debitoTipo.setId(new Integer(idDebitoTipo));

		// [FS0006] - Verificar tipo de financaimento do tipo de débito
		if (debitoTipo.getFinanciamentoTipo().getId().intValue() != FinanciamentoTipo.SERVICO_NORMAL.intValue()
				|| debitoTipo.getIndicadorGeracaoAutomatica().shortValue() == 1) {

			throw new ActionServletException(
					"atencao.tipo_financiamento.tipo_debito.nao.permite.guia_pagamento_item",
					debitoTipo.getFinanciamentoTipo().getDescricao(),
					debitoTipo.getDescricao());
		}
		
		// [FS0024] - Verificar bloqueio do tipo do débito para inclusão on-line da guia
		if (debitoTipo.getIndicadorJurosParCliente().toString().equals(ConstantesSistema.CONFIRMADA)){
			
			throw new ActionServletException(
					"atencao.tipo_financiamento.tipo_debito.nao.permite.tipo_exclusivo",
					debitoTipo.getDescricaoAbreviada());
		}
		

		guiaPagamentoItem.setDebitoTipo(debitoTipo);
		
		Collection colecaoGuiaPagamentoItemSessao = (Collection)sessao.getAttribute("colecaoGuiaDebitoTipo");
		boolean existe = false;
		
		if(colecaoGuiaPagamentoItemSessao != null){
			
			if(this.pagamentoParcialJaInformado(colecaoGuiaPagamentoItemSessao, debitoTipo)){
				
				throw new ActionServletException(
				"atencao.guia_pagamento_item_excedeu_limite");
			}
			else if(colecaoGuiaPagamentoItemSessao.size() < 25){
					
				Iterator iterator = colecaoGuiaPagamentoItemSessao.iterator();
				while(iterator.hasNext()){
					GuiaPagamentoItem guiaPagamentoItemColecao = (GuiaPagamentoItem)iterator.next();
						
					if(!debitoTipo.getFinanciamentoTipo().getId().equals(guiaPagamentoItemColecao.getDebitoTipo().getFinanciamentoTipo().getId())){
						throw new ActionServletException(
								"atencao.tipo_financiamento.tipo_debito.diferente");
					}
						
					if(guiaPagamentoItemColecao.getDebitoTipo().getId().equals(debitoTipo.getId())){
						existe = true;
					}
				}
					
				if(!existe){
					colecaoGuiaPagamentoItemSessao.add(guiaPagamentoItem);
				}
				else{
					throw new ActionServletException(
					"atencao.tipo_financiamento.tipo_debito.ja_existe_na_lista");
				}
			}
			else{
					
				throw new ActionServletException(
				"atencao.tipo_financiamento.tipo_debito.maior_que_vinte_cinco");
			}
		}
		else{
			colecaoGuiaPagamentoItemSessao = new ArrayList();
			colecaoGuiaPagamentoItemSessao.add(guiaPagamentoItem);
		}
		
		form.setIdTipoDebito("");
		form.setDescricaoTipoDebito("");
		form.setValorTotalServico("");
		form.setMatriculaImovel("");
		
		sessao.removeAttribute("colecaoContaParaPagamentoParcial");
		
		sessao.setAttribute("colecaoGuiaDebitoTipo",colecaoGuiaPagamentoItemSessao);
		
		httpServletRequest.setAttribute("desabilitaIdDebito", "sim");
		httpServletRequest.setAttribute("reloadPage",1);
		
		return retorno;
	}
	
	private boolean pagamentoParcialJaInformado(Collection colecaoGuiaPagamentoItemSessao,
			DebitoTipo debitoTipo){
		
		boolean retorno = false;
		
		if (colecaoGuiaPagamentoItemSessao.size() > 0 &&
			debitoTipo.getId().equals(DebitoTipo.PAGAMENTO_PARCIAL_CONTA)){
			
			retorno = true;
		}
		
		if (!retorno){
			
			Iterator iterator = colecaoGuiaPagamentoItemSessao.iterator();
			
			while(iterator.hasNext()){
				
				GuiaPagamentoItem guiaPagamentoItemColecao = (GuiaPagamentoItem)iterator.next();
				
				if (guiaPagamentoItemColecao.getDebitoTipo().getId()
					.equals(DebitoTipo.PAGAMENTO_PARCIAL_CONTA)){
					
					retorno = true;
					break;
				}
				
			}
		}
		
		return retorno;
	}

}
