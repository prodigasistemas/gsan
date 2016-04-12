package gcom.faturamento.controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.CreateException;

import gcom.arrecadacao.repositorio.PagamentoRepositorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.faturamento.repositorio.DebitoACobrarRepositorio;
import gcom.util.ControladorComum;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

public class ControladorDebitoACobrar extends ControladorComum{
    private static final long serialVersionUID = -6230958913028633457L;

    private DebitoACobrarRepositorio debitoCobrarRepositorio;
	
	private PagamentoRepositorio pagamentoRepositorio;
	
    public void ejbCreate() throws CreateException {
        pagamentoRepositorio    = PagamentoRepositorio.getInstancia();
        debitoCobrarRepositorio = DebitoACobrarRepositorio.getInstancia();
    }
	
	public Collection<DebitoACobrar> debitosCobrarVigentes(Integer idImovel) throws ControladorException{
	    
	    Collection<DebitoACobrar> debitos = new ArrayList<DebitoACobrar>();
        try {
            
            SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
            
            Collection<DebitoACobrar> lista = debitoCobrarRepositorio.debitosCobrarPorImovelComPendenciaESemRevisao(idImovel);
            
            Integer anoMesFaturamento = sistemaParametro.getAnoMesFaturamento();
            
            for (DebitoACobrar debito : lista) {
                if (!(debito.pertenceParcelamento(anoMesFaturamento))){
                    debitos.add(debito);
                }
            }
        } catch (ErroRepositorioException e) {
            throw new ControladorException("Erro ao pesquisar debitos a cobrar vigentes", e);
        }
		
		
		return debitos;
	}

	public Collection<DebitoACobrar> debitosCobrarSemPagamentos(Integer idImovel) throws ControladorException {
	    Collection<DebitoACobrar> debitos = new ArrayList<DebitoACobrar>();
	    
	    try {
            SistemaParametro sistemaParametro = getControladorUtil().pesquisarParametrosDoSistema();
    	    
    		Integer anoMesFaturamento = sistemaParametro.getAnoMesFaturamento();
		
    		Collection<DebitoACobrar> lista = debitoCobrarRepositorio.debitosCobrarPorImovelComPendenciaESemRevisao(idImovel);
            
            for (DebitoACobrar debito : lista) {
                if (!debito.pertenceParcelamento(anoMesFaturamento) && pagamentoRepositorio.debitoSemPagamento(debito.getId())){
                    debitos.add(debito);
                }
            }
        } catch (ErroRepositorioException e) {
            throw new ControladorException("Erro ao pesquisar debitos a cobrar sem pagamentos", e);
        }
		return debitos;
	}
	
	public void atualizarDebitoCobrar(List<DebitoACobrar> debitosCobrar) throws ErroRepositorioException{
		debitoCobrarRepositorio.atualizarDebitoCobrar(debitosCobrar);
	}
}
