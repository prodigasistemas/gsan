package gcom.faturamento.controladores;

import java.util.Collection;
import java.util.List;

import gcom.faturamento.debito.DebitoACobrar;
import gcom.util.ControladorException;
import gcom.util.ErroRepositorioException;

public interface ControladorDebitoACobrarLocal extends javax.ejb.EJBLocalObject {

    public Collection<DebitoACobrar> debitosCobrarVigentes(Integer idImovel) throws ControladorException;
    
    public Collection<DebitoACobrar> debitosCobrarSemPagamentos(Integer idImovel) throws ControladorException;
    
    public void atualizarDebitoCobrar(List<DebitoACobrar> debitosCobrar) throws ErroRepositorioException;
}
