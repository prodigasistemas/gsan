package gcom.gui.faturamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.fachada.Fachada;
import gcom.faturamento.conta.Fatura;
import gcom.faturamento.conta.FaturaItem;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *  AdicionarFaturaClienteResponsavelContaPopupAction
 * 
 * @author Flávio Leonardo
 * @created 25/04/2006
 */
public class AtualizarFaturaClienteResponsavelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de segurança
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Instância do formulário que está sendo utilizado
		FiltrarFaturaClienteResponsavelActionForm form = (FiltrarFaturaClienteResponsavelActionForm) actionForm;
		
		Fatura fatura = (Fatura)sessao.getAttribute("fatura");
		
		Collection<FaturaItem> colecaoFaturasItem = (Collection)sessao.getAttribute("colecaoFaturaItem");
		
		if(colecaoFaturasItem.isEmpty()){
			throw new ActionServletException("atencao.fatura.cliente.responsavel.conter.uma");
		}
		
		Collection<FaturaItem> colecaoFaturaItemRemover = (Collection)sessao.getAttribute("colecaoFaturaItemRemover");
		
		fachada.removerFaturaItemFaturaItemHistorico(colecaoFaturaItemRemover, usuario);
		
		int qtdInseridos = fachada.inserirFaturaItemFaturaItemHistorico(colecaoFaturasItem, usuario);
		
		BigDecimal valorTotal = fachada.somarValorFaturasItemFatura(fatura);
		
		Date dataVencimento = fachada.vencimentoFaturasItemFatura(fatura);
		
		fatura.setVencimento(dataVencimento);
		fatura.setDebito(valorTotal);
		
		fachada.alterarVencimentoFaturaFaturaItem(fatura);
		
		Cliente cliente = null;
		
		if(sessao.getAttribute("cliente") != null){
			cliente = (Cliente) sessao.getAttribute("cliente");
		}else{
			FiltroCliente filtroCliente = new FiltroCliente();
			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID, form.getClienteId()));
			Collection colecaoCliente = fachada.pesquisar(filtroCliente, Cliente.class.getName());
			
			cliente = (Cliente)Util.retonarObjetoDeColecao(colecaoCliente);
		}
		
		String qtdRemovidos = "0";
		if(colecaoFaturaItemRemover != null){
			qtdRemovidos = colecaoFaturaItemRemover.size() + "";
		}
		
		montarPaginaSucesso(httpServletRequest, "Foram inseridas "
				+ qtdInseridos + " contas e removidas " 
				+ qtdRemovidos
				+ " contas da fatura do cliente "
				+ cliente.getNome()
				+ " da referência "
				+ form.getMesAno(),
				"Realizar outra Manutenção de Fatura Cliente Responsável",
				"exibirFiltrarFaturaClienteResponsavelAction.do?menu=sim");

		return retorno;

	}
}
