package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.contratoparcelamento.PrestacaoContratoParcelamentoHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.cobranca.contratoparcelamento.RelatorioEmitirExtratoContratoParcelamentoPorCliente;
import gcom.relatorio.cobranca.contratoparcelamento.EmitirExtratoContratoParcelamentoPorClienteHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1201] Emitir Extrato de Contrato de Parcelamento por Cliente
 * 
 * Este caso de uso permite emitir o extrato de uma ou todas as prestações 
 *  do contrato de parcelamento por cliente.
 * 
 * @author Mariana Victor
 * @since 29/07/2011
 */
public class EmitirExtratoContratoParcelamentoPorClienteAction extends ExibidorProcessamentoTarefaRelatorio  {

	/**
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException  {
		
		ActionForward retorno = null;

		EmitirExtratoContratoParcelamentoPorClienteActionForm form = (EmitirExtratoContratoParcelamentoPorClienteActionForm) actionForm;
	
		HttpSession sessao = httpServletRequest.getSession(false);
	    
	    Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		// [FS0001] - Verificar preenchimento dos campos
		if ((form.getParcelaEmissao() == null
				|| form.getParcelaEmissao().toString().trim().equals(""))
			&& (form.getInicioParcelas() == null
					|| form.getInicioParcelas().toString().trim().equals("")
				|| form.getFimParcelas() == null
				|| form.getFimParcelas().toString().trim().equals(""))) {
			
			throw new ActionServletException("atencao.nenhum_parametro_informado");
			
		}
		
		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		
		Collection<PrestacaoContratoParcelamentoHelper> colecaoHelper = (Collection<PrestacaoContratoParcelamentoHelper>)
			sessao.getAttribute("colecaoPrestacaoContratoParcelamentoHelper");
			
		RelatorioEmitirExtratoContratoParcelamentoPorCliente relatorio = 
			new RelatorioEmitirExtratoContratoParcelamentoPorCliente(
				(Usuario) (httpServletRequest.getSession(false))
				.getAttribute("usuarioLogado"));
		
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}
		
		EmitirExtratoContratoParcelamentoPorClienteHelper helper = this.montarHelper(form);
		
		relatorio.addParametro("tipoFormatoRelatorio", 
				Integer.parseInt(tipoRelatorio));
		relatorio.addParametro("relatorioEmitirExtratoContratoParcelamentoPorClienteHelper", 
				helper);
		relatorio.addParametro("colecaoPrestacaoContratoParcelamentoHelper", 
				colecaoHelper);
		relatorio.addParametro("usuarioLogado", 
				usuarioLogado);
		
		//[SB0001] ? Emitir Extrato de Débito do Cliente em Formato PDF
		retorno = processarExibicaoRelatorio(relatorio, tipoRelatorio,
				httpServletRequest, httpServletResponse, actionMapping);
		
		return retorno;
	}
	
	private EmitirExtratoContratoParcelamentoPorClienteHelper montarHelper(
			EmitirExtratoContratoParcelamentoPorClienteActionForm form){
		EmitirExtratoContratoParcelamentoPorClienteHelper helper = new EmitirExtratoContratoParcelamentoPorClienteHelper();

		Integer parcelaInicial = null;
		Integer parcelaFinal = null;
		
		if (form.getInicioParcelas() != null 
				&& !form.getInicioParcelas().toString().trim().equals("")) {
			parcelaInicial = new Integer(form.getInicioParcelas());
		}
		
		if (form.getFimParcelas() != null 
				&& !form.getFimParcelas().toString().trim().equals("")) {
			parcelaFinal = new Integer(form.getFimParcelas());
		}
		
		helper.setNumeroContrato(form.getNumeroContrato());
		helper.setParcelaEmissao(form.getParcelaEmissao());
		helper.setInicioParcelas(parcelaInicial);
		helper.setFimParcelas(parcelaFinal);
		helper.setNomeCliente(form.getNomeCliente());
		helper.setCodigoCliente(form.getCodigoCliente());
		
		return helper;
	}
	

}
