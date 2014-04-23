package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.CobrancaForma;
import gcom.cobranca.FiltroCobrancaForma;
import gcom.cobranca.contratoparcelamento.ComparatorParcela;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.FiltroQuantidadePrestacoes;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoes;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.filtro.ComparacaoTexto;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action que define o pré-processamento da página de Filtrar Resolucao de Diretoria para Contrato pro Cliente
 * 
 * @author Paulo Diniz
 * @created 16/03/2011
 */
public class ExibirAtualizarResolucaoDiretoriaContratoParcelamentoAction extends
		GcomAction {

	/**
	 * Este caso de uso permite a inclusão de uma resolução de diretoria
	 * 
	 * [UC1134] Manter Resolução de Diretoria (RD) para Contratos de Parcelamento por cliente
	 * 
	 * 
	 * @author Paulo Diniz
	 * @date 16/03/2011
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
		
		//Obtém a instância da fachada
		Fachada fachada = Fachada.getInstancia();
		
		ActionForward retorno = actionMapping.findForward("exibirAtualizar");
		
		HttpSession sessao = httpServletRequest.getSession(false);
		
		String numeroContratoParcelamentoRD =(String) httpServletRequest.getParameter("numeroContratoParcelamentoRD");
		
		//Para no caso da Action seja ativada diretamente pelo filtro
		if(numeroContratoParcelamentoRD == null){
			numeroContratoParcelamentoRD = (String) sessao.getAttribute("numeroContratoParcelamentoRD");
		}
		
		FiltroCobrancaForma filtroCobranca = new FiltroCobrancaForma();
        filtroCobranca.adicionarParametro(new ParametroSimples(FiltroCobrancaForma.IC_USO_CONTRATO_PARCEL_CLIENTE, "1"));
		Collection coll = this.getFachada().pesquisar(filtroCobranca, CobrancaForma.class.getName()); 
		sessao.setAttribute("collFormaPgto", coll);
		
		ContratoParcelamentoRD contratoRDAtualizar = (ContratoParcelamentoRD) sessao.getAttribute("contratoAtualizar");
		List<QuantidadePrestacoes> parcelas = (List<QuantidadePrestacoes>) sessao.getAttribute("parcelas");

		if(numeroContratoParcelamentoRD != null && !numeroContratoParcelamentoRD.equals("")){
			contratoRDAtualizar = this.getFachada().pesquisarContratoParcelamentoRDPorNumero(numeroContratoParcelamentoRD);

			boolean verificaContratoAssociado = fachada.verificaResolucaoDiretoriaAssociadaContratoParcelamentoNaoEncerrado(contratoRDAtualizar.getId());
			if(verificaContratoAssociado){
				throw new ActionServletException("atencao.existe.contrato.parcelamento.associado.atualizar", null, "");
			}
			
			FiltroQuantidadePrestacoes filtro = new FiltroQuantidadePrestacoes(FiltroQuantidadePrestacoes.QTD_FATURAS_PARCELADAS);
			filtro.adicionarParametro(new ComparacaoTexto(
					FiltroQuantidadePrestacoes.CONTRATO_PARCELAMENTO_RD_NUMERO, numeroContratoParcelamentoRD.toUpperCase()));
			parcelas = new ArrayList<QuantidadePrestacoes>(this.getFachada().pesquisar(filtro,QuantidadePrestacoes.class.getName()));
			
			sessao.setAttribute("contratoAntes",contratoRDAtualizar);
		}
		
		if(parcelas == null){
			parcelas = new ArrayList<QuantidadePrestacoes>();
		}
		if (contratoRDAtualizar == null) {
			contratoRDAtualizar = new ContratoParcelamentoRD();
		}
		
		Collections.sort(parcelas, new ComparatorParcela());
		sessao.setAttribute("parcelas", parcelas);
		sessao.setAttribute("contratoAtualizar", contratoRDAtualizar);
        
        
		return retorno;
	}
		
}
