package gcom.gui.cobranca.spcserasa;

import java.util.Collection;
import java.util.Date;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade validar as informações da 2° aba do processo de inserção
 * de um Comando de Negativação
 *
 * @author Ana Maria	
 * @date 06/11/2007
 */
public class InserirComandoNegativacaoDadosDebitoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("");
        
        Fachada fachada = Fachada.getInstancia();
        
        InserirComandoNegativacaoActionForm form = (InserirComandoNegativacaoActionForm) actionForm;
        
  	    //Pesquisar Sistema Parametro 
  	    FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
   	    Collection<SistemaParametro> collectionSistemaParametro = fachada
			.pesquisar(filtroSistemaParametro,
					SistemaParametro.class.getName());
   	   		SistemaParametro sistemaParametro = (SistemaParametro) collectionSistemaParametro
   	   		.iterator().next();
   	   		
		//Período de referência do débito	
	  	Integer referenciaMinima = Util.subtrairAnoAnoMesReferencia(sistemaParametro.getAnoMesArrecadacao(), 5);	  			

		if(form.getReferenciaInicial() != null && !form.getReferenciaInicial().equals("")){
			Integer referenciaDebInicialInformado = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaInicial());	
			if(referenciaDebInicialInformado < referenciaMinima){
				throw new ActionServletException(
						"atencao.periodo_referencia_debito_minimo");				
			}
		}

		if(form.getReferenciaFinal() != null && !form.getReferenciaFinal().equals("")){
			Integer referenciaDebFinalInformado = Util.formatarMesAnoComBarraParaAnoMes(form.getReferenciaFinal());			
			if(referenciaDebFinalInformado < referenciaMinima){
				throw new ActionServletException(
						"atencao.periodo_referencia_debito_minimo");				
			}
		}
        
		//Período de vencimento do débito	
		Integer numeroDiasVencimentoCobranca = new Integer(sistemaParametro.getNumeroDiasVencimentoCobranca());			
		Date dataMinima = Util.subtrairNumeroAnosDeUmaData(Util.subtrairNumeroDiasDeUmaData(new Date(), numeroDiasVencimentoCobranca), -5);

		if(form.getDataVencimentoInicial() != null && !form.getDataVencimentoInicial().equals("")){
			Date vencimentoDebInicialInformado = Util.converteStringParaDate(form.getDataVencimentoInicial());	
			if(Util.compararData(vencimentoDebInicialInformado, dataMinima) == -1){
				throw new ActionServletException(
						"atencao.periodo_vencimento_debito_minimo");				
			}
		}

		if(form.getDataVencimentoFinal() != null && !form.getDataVencimentoFinal().equals("")){
			Date vencimentoDebFinalInformado = Util.converteStringParaDate(form.getDataVencimentoFinal());			
			if(Util.compararData(vencimentoDebFinalInformado, dataMinima) == -1){
				throw new ActionServletException(
						"atencao.periodo_vencimento_debito_minimo");				
			}
		}
				
        return retorno;
	}

}
