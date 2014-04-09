package gcom.gui.faturamento;

import gcom.cadastro.imovel.Imovel;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class CalcularPrestacaoDebitoACobrarAction extends GcomAction {

    
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("exibirInserirDebitoACobrar");
        
		HttpSession sessao = httpServletRequest.getSession(false);
        Fachada fachada = Fachada.getInstancia();
        
    	Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");
    	
        InserirDebitoACobrarActionForm inserirDebitoACobrarActionForm = (InserirDebitoACobrarActionForm) actionForm;
        BigDecimal valorEntrada = null;
        
        if(inserirDebitoACobrarActionForm.getValorEntrada() != null  && !inserirDebitoACobrarActionForm.getValorEntrada().equals("")){
        	String valEntrada = inserirDebitoACobrarActionForm.getValorEntrada();
        	valorEntrada = Util.formatarMoedaRealparaBigDecimal(valEntrada);
        }else{
        	valorEntrada = new BigDecimal("0.00");
        }
        
        BigDecimal valorTotalServico = Util.formatarMoedaRealparaBigDecimal(inserirDebitoACobrarActionForm.getValorTotalServico());
        
        BigDecimal percentualAbatimento;
        if(inserirDebitoACobrarActionForm.getPercentualAbatimento() != null && !inserirDebitoACobrarActionForm.getPercentualAbatimento().equals("")){
        	String percAbatimento = inserirDebitoACobrarActionForm.getPercentualAbatimento();
        	percentualAbatimento =  Util.formatarMoedaRealparaBigDecimal(percAbatimento);	
        }else{
        	percentualAbatimento = new BigDecimal("0.00");
        }
        
    	String taxaJurosFinanciamento = inserirDebitoACobrarActionForm.getTaxaJurosFinanciamento();
    	BigDecimal taxaJutosFinanciamento = new BigDecimal("0.00");
    	if(taxaJurosFinanciamento != null && !taxaJurosFinanciamento.equals("")){
    		taxaJutosFinanciamento =  Util.formatarMoedaRealparaBigDecimal(taxaJurosFinanciamento);
    	}
    	
        Integer numeroPrestacoes = new Integer(inserirDebitoACobrarActionForm.getNumeroPrestacoes());
		
        Imovel imovel = null;
		if (sessao.getAttribute("imovelPesquisado") != null) {
			imovel = (Imovel) sessao.getAttribute("imovelPesquisado");
		}
        
        //Chamar o CalcularPrestacao
        ArrayList valores = fachada.calcularValorPrestacao(taxaJutosFinanciamento,numeroPrestacoes,valorTotalServico,
        		valorEntrada,percentualAbatimento,inserirDebitoACobrarActionForm.getIdTipoDebito(),null,imovel, usuario);
        
        
        BigDecimal valorTotal = new BigDecimal(valores.get(0).toString());
        
        inserirDebitoACobrarActionForm.setValorTotalServico(Util.formatarMoedaReal(valorTotal));
        inserirDebitoACobrarActionForm.setValorPrestacao(valores.get(1).toString().replace('.',','));
        inserirDebitoACobrarActionForm.setValorJuros(valores.get(2).toString().replace('.',','));
        inserirDebitoACobrarActionForm.setvalorTotalServicoAParcelar(valores.get(3).toString().replace('.',','));
        
        return retorno;
    }
    
}
