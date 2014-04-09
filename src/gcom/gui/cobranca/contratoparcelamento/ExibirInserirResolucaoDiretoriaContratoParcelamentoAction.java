package gcom.gui.cobranca.contratoparcelamento;

import gcom.cobranca.CobrancaForma;
import gcom.cobranca.FiltroCobrancaForma;
import gcom.cobranca.contratoparcelamento.ComparatorParcela;
import gcom.cobranca.contratoparcelamento.ContratoParcelamentoRD;
import gcom.cobranca.contratoparcelamento.QuantidadePrestacoes;
import gcom.gui.GcomAction;
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
 * Action que define o pré-processamento da página de Inserir Resolucao de Diretoria para Contrato pro Cliente
 * 
 * @author Paulo Diniz
 * @created 16/03/2011
 */
public class ExibirInserirResolucaoDiretoriaContratoParcelamentoAction extends GcomAction {
	
	/**
	 * Este caso de uso permite a inclusão de uma resolução de diretoria
	 * 
	 * [UC1133] Inserir Resolução de Diretoria para Contratos de Parcelamento por Cliente
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

        //Seta o retorno
        ActionForward retorno = actionMapping.findForward("exibirInserir");
        HttpSession sessao = httpServletRequest.getSession(false);

        FiltroCobrancaForma filtroCobranca = new FiltroCobrancaForma();
        filtroCobranca.adicionarParametro(new ParametroSimples(FiltroCobrancaForma.IC_USO_CONTRATO_PARCEL_CLIENTE, "1"));
        Collection coll = this.getFachada().pesquisar(filtroCobranca, CobrancaForma.class.getName()); 
        sessao.setAttribute("collFormaPgto", coll);
        
        ContratoParcelamentoRD contratoCadastrar = (ContratoParcelamentoRD) sessao.getAttribute("contratoCadastrar");
		if (contratoCadastrar == null) {
			contratoCadastrar = new ContratoParcelamentoRD();
		}
		
		List<QuantidadePrestacoes> parcelas = (List<QuantidadePrestacoes>) sessao.getAttribute("parcelas");
		if(parcelas == null){
			parcelas = new ArrayList<QuantidadePrestacoes>();
		}
		
		Collections.sort(parcelas, new ComparatorParcela());
		sessao.setAttribute("parcelas", parcelas);
		sessao.setAttribute("contratoCadastrar", contratoCadastrar);
        
        
        return retorno;

    }
        
    								    
}
