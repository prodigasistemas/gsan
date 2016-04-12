package gcom.faturamento.controladores;

import gcom.cadastro.imovel.Imovel;
import gcom.util.ControladorException;

public interface ControladorAnaliseGeracaoContaLocal extends javax.ejb.EJBLocalObject {

    public boolean verificarGeracaoConta(boolean aguaEsgotoZerados, int anoMesFaturamento, Imovel imovel) throws ControladorException;
    
    public boolean verificarDebitosECreditosParaGerarConta(int anoMesFaturamento, Imovel imovel) throws ControladorException;
    
    public boolean verificarSituacaoImovelParaGerarConta(boolean valoresAguaEsgotoZerados, Imovel imovel);
}
