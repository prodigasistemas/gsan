package gcom.gui.cobranca.spcserasa;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Esta classe tem por finalidade exibir para o usuário a tela que receberá os parâmetros para realização
 * da inserção de um Comando de Negativação (Aba nº 02 - Dados do Débito) 
 *
 * @author Ana Maria	
 * @date 06/11/2007
 */
public class ExibirInserirComandoNegativacaoDadosDebitoAction extends GcomAction {
	
	
	public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("inserirComandoNegativacaoDadosDebito");
        
        Fachada fachada = Fachada.getInstancia();
        
        InserirComandoNegativacaoActionForm inserirComandoNegativacaoActionForm = (InserirComandoNegativacaoActionForm) actionForm;
        
  	    //Pesquisar Sistema Parametro 
  	    FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
  			
  	   Collection<SistemaParametro> collectionSistemaParametro = fachada
  					.pesquisar(filtroSistemaParametro,
  							SistemaParametro.class.getName());
  	    SistemaParametro sistemaParametro = (SistemaParametro) collectionSistemaParametro
  					.iterator().next();
  	  
  	    //Referência do Débito Final
  	    if(inserirComandoNegativacaoActionForm.getReferenciaFinal() == null ||
  	    		inserirComandoNegativacaoActionForm.getReferenciaFinal().equals("")){
	  	    String anoMesArrecadacao = Util.formatarAnoMesParaMesAno(sistemaParametro.getAnoMesArrecadacao());
	  	    inserirComandoNegativacaoActionForm.setReferenciaFinal(anoMesArrecadacao);
  	    }
  	    //Data do Vencimento Final
  	    if(inserirComandoNegativacaoActionForm.getDataVencimentoFinal() == null || 
  	    		inserirComandoNegativacaoActionForm.getDataVencimentoFinal().equals("")){
			//Período de vencimento do débito	
			Integer numeroDiasVencimentoCobranca = new Integer(sistemaParametro.getNumeroDiasVencimentoCobranca());			
			Date dataVencimentoFinal = Util.subtrairNumeroDiasDeUmaData(new Date(), numeroDiasVencimentoCobranca);
			Date dataVencimentoInicial = Util.subtrairNumeroAnosDeUmaData(dataVencimentoFinal, 5);
			inserirComandoNegativacaoActionForm.setDataVencimentoInicial(Util.formatarData(dataVencimentoInicial));
			inserirComandoNegativacaoActionForm.setDataVencimentoFinal(Util.formatarData(dataVencimentoFinal));
			/*Date dataAtualMenosMes = Util.adcionarOuSubtrairMesesAData(new Date(), -1, 0);
	  	    int mesData = Util.getMes(dataAtualMenosMes);
	  	    int anoData = Util.getAno(dataAtualMenosMes);  	    
	  	    String dataVencimentoFinal = Util.obterUltimoDiaMes(mesData, anoData)+ "/"+ mesData + "/" + anoData;
	  	    inserirComandoNegativacaoActionForm.setDataVencimentoFinal(dataVencimentoFinal);*/
  	    }
  	    
        if(inserirComandoNegativacaoActionForm.getContasRevisao() == null){
        	//Considerar Conta em Revisão - exibir com opção "Não" selecionada    		
        	inserirComandoNegativacaoActionForm.setContasRevisao(ConstantesSistema.NAO_CONFIRMADA);   
        }
	
        if(inserirComandoNegativacaoActionForm.getGuiasPagamento() == null){
        	//Considerar Guias de Pagamento - exibir com opção "Não" selecionada    		
        	inserirComandoNegativacaoActionForm.setGuiasPagamento(ConstantesSistema.NAO_CONFIRMADA);   
        }

        if(inserirComandoNegativacaoActionForm.getParcelaAtraso() == null){
        	//Parcela em Atraso - exibir com opção "Não" selecionada    		
        	inserirComandoNegativacaoActionForm.setParcelaAtraso(ConstantesSistema.NAO_CONFIRMADA);   
        }
        
        if(inserirComandoNegativacaoActionForm.getCartaParcelamentoAtraso() == null){
        	//Recebeu Carta de Parcelamento em Atraso - exibir com opção "Não" selecionada    		
        	inserirComandoNegativacaoActionForm.setCartaParcelamentoAtraso(ConstantesSistema.NAO_CONFIRMADA);   
        }

        if(inserirComandoNegativacaoActionForm.getIndicadorContaNomeCliente() == null){
        	//Exigir ao Menos uma Conta em Nome do Cliente Negativado  - exibir com opção "Sim" selecionada    		
        	inserirComandoNegativacaoActionForm.setIndicadorContaNomeCliente(ConstantesSistema.CONFIRMADA);   
        }
	
        
		// Data Corrente
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataCorrente = new GregorianCalendar();
		httpServletRequest.setAttribute("dataAtual", formatoData
				.format(dataCorrente.getTime()));
    		
    	return retorno;
    }

}
