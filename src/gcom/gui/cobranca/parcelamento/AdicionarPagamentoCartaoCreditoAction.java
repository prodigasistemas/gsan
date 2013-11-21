package gcom.gui.cobranca.parcelamento;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroCliente;
import gcom.cobranca.bean.ParcelamentoCartaoCreditoHelper;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;
import gcom.arrecadacao.ArrecadacaoForma;

public class AdicionarPagamentoCartaoCreditoAction extends GcomAction {

	/**
	 * @param args
	 * Gsan
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
				
		//Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("adicionarPagamentoCartaoCreditoAction");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		Fachada fachada = Fachada.getInstancia();
		
		ParcelamentoCartaoConfirmarForm form = (ParcelamentoCartaoConfirmarForm) actionForm;
		
		if(form.getIdCliente()!=null && 
				!form.getIdCliente().equals("")){
			
		
			FiltroCliente filtroCliente = new FiltroCliente();

			filtroCliente.adicionarParametro(new ParametroSimples(FiltroCliente.ID,
					form.getIdCliente()));
			filtroCliente.adicionarParametro(new ParametroSimples(
					FiltroCliente.INDICADOR_USO,
					ConstantesSistema.INDICADOR_USO_ATIVO));

			Collection clienteEncontrado = fachada.pesquisar(filtroCliente,
					Cliente.class.getName());

			if (clienteEncontrado==null || (clienteEncontrado != null && clienteEncontrado.isEmpty())) {
				throw new ActionServletException("atencao.cliente.inexistente");
			}
		}
		
		Collection colecaoTransacao = null;
		

		if (sessao.getAttribute("colecaoTransacao") != null) {
			colecaoTransacao = (Collection) sessao
					.getAttribute("colecaoTransacao");
		}else{
			colecaoTransacao = new ArrayList();
		}
		
		ParcelamentoCartaoCreditoHelper helper = new ParcelamentoCartaoCreditoHelper();
		
		helper.setIdArrecadador(form.getCartaoCredito());
		helper.setNumeroCartao(form.getNumeroCartao());
		helper.setDocumentoCartao(form.getDocumentoCartao());
		helper.setAutorizacaoCartao(form.getAutorizacaoCartao());
		helper.setValidadeCartao(form.getValidadeCartao());
		helper.setIdCliente(form.getIdCliente());
		helper.setValorTransacao(form.getValorTransacao());
		helper.setQtdParcelas(form.getQtdParcelas());
		helper.setUsuario(usuarioLogado);
		helper.setTempoInclusao(System.currentTimeMillis()+"");
		helper.setNumeroIdentificacaoTransacao(form.getNumeroIdentificacaoTransacao());
		
		if (form.getModalidadeCartao().equals(ConstantesSistema.MODALIDADE_CARTAO_CREDITO.toString())){
			
			//[FS0008] – Verificar validade da data
			fachada.verificarValidadeData(Util.converteStringParaDate(form.getDataOperadora()),
			Integer.valueOf(form.getCartaoCredito()), ArrecadacaoForma.CARTAO_CREDITO);
		}
		else{
			
			//[FS0008] – Verificar validade da data
			fachada.verificarValidadeData(Util.converteStringParaDate(form.getDataOperadora()),
			Integer.valueOf(form.getCartaoCredito()), ArrecadacaoForma.CARTAO_DEBITO);
		}
		
		
		helper.setDataConfirmacaoOperadora(Util.converteStringParaDate(form.getDataOperadora()));
		
		colecaoTransacao.add(helper);
		
		if(!colecaoTransacao.isEmpty()){		
			sessao.setAttribute("colecaoTransacao",colecaoTransacao);
			httpServletRequest.setAttribute("reload", true);
		}else{
			sessao.setAttribute("colecaoPagamentos",null);
			httpServletRequest.setAttribute("reload", true);
		}
		
		return retorno;
	}

}
