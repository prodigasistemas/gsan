package gcom.faturamento.bean;

import gcom.fachada.Fachada;
import gcom.faturamento.credito.CreditoOrigem;
import gcom.faturamento.credito.CreditoTipo;
import gcom.faturamento.credito.FiltroCreditoOrigem;
import gcom.faturamento.credito.FiltroCreditoTipo;
import gcom.gui.ActionServletException;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;


/**
 * @author Vivianne Sousa
 * @date 16/03/2011
 */
public class CreditosHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String tipoCredito;
	private String origemCredito;
	private BigDecimal valorCredito;
	private Integer referenciaCredito;
	private Integer idContaCreditorealizado;
	
	public Integer getIdContaCreditorealizado() {
		return idContaCreditorealizado;
	}
	public void setIdContaCreditorealizado(Integer idContaCreditorealizado) {
		this.idContaCreditorealizado = idContaCreditorealizado;
	}
	public String getOrigemCredito() {
		return origemCredito;
	}
	public void setOrigemCredito(String origemCredito) {
		this.origemCredito = origemCredito;
	}
	public Integer getReferenciaCredito() {
		return referenciaCredito;
	}
	public void setReferenciaCredito(Integer referenciaCredito) {
		this.referenciaCredito = referenciaCredito;
	}
	public String getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public BigDecimal getValorCredito() {
		return valorCredito;
	}
	public void setValorCredito(BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}

public CreditoOrigem getOrigemCreditoID() {
		
//		CreditoOrigem creditoOrigem = new CreditoOrigem();
//		creditoOrigem.setId(CreditoOrigem.CONTAS_PAGAS_EM_DUPLICIDADE_EXCESSO);
		
		
//		Realizando consulta para obter os dados do crédito origem selecionado
        FiltroCreditoOrigem filtroCreditoOrigem = new FiltroCreditoOrigem();
    	
        filtroCreditoOrigem.adicionarParametro(new ParametroSimples(FiltroCreditoOrigem.ID,
        		CreditoOrigem.CONTAS_PAGAS_EM_DUPLICIDADE_EXCESSO));
        
        filtroCreditoOrigem.adicionarParametro(new ParametroSimples(FiltroCreditoOrigem.INDICADOR_USO,
    			ConstantesSistema.INDICADOR_USO_ATIVO));
    
    	Collection colecaoCreditoOrigem = Fachada.getInstancia().pesquisar(filtroCreditoOrigem,
    		CreditoOrigem.class.getName());
    
    	if (colecaoCreditoOrigem == null || colecaoCreditoOrigem.isEmpty()){
    	throw new ActionServletException(
                "atencao.pesquisa.nenhum_registro_tabela", null,
                "CREDITO_ORIGEM");
    	}
		CreditoOrigem objCreditoOrigem = (CreditoOrigem) Util.retonarObjetoDeColecao(colecaoCreditoOrigem);
		
		
		return objCreditoOrigem;
	}
	public CreditoTipo getTipoCreditoID() {
//		CreditoTipo creditoTipo = new CreditoTipo();
//		creditoTipo.setId(CreditoTipo.DEVOLUCAO_PAGAMENTOS_DUPLICIDADE);
//		LancamentoItemContabil lict = new LancamentoItemContabil();
//		lict.setId(LancamentoItemContabil.OUTROS_SERVICOS_AGUA);
//		creditoTipo.setLancamentoItemContabil(lict);
		
		
        //Realizando consulta para obter os dados do tipo do crédito selecionado
        FiltroCreditoTipo filtroCreditoTipo = new FiltroCreditoTipo();
    	
        filtroCreditoTipo.adicionarParametro(new ParametroSimples(FiltroCreditoTipo.ID,
        		CreditoTipo.DEVOLUCAO_PAGAMENTOS_DUPLICIDADE));
        
        filtroCreditoTipo.adicionarParametro(new ParametroSimples(FiltroCreditoTipo.INDICADOR_USO,
    			ConstantesSistema.INDICADOR_USO_ATIVO));
    
    	Collection colecaoCreditoTipo = Fachada.getInstancia().pesquisar(filtroCreditoTipo,
    		CreditoTipo.class.getName());
    
    	if (colecaoCreditoTipo == null || colecaoCreditoTipo.isEmpty()){
    	throw new ActionServletException(
                "atencao.pesquisa.nenhum_registro_tabela", null,
                "CREDITO_TIPO");
    	}
		CreditoTipo objCreditoTipo = (CreditoTipo) Util.retonarObjetoDeColecao(colecaoCreditoTipo);
		
		
		
		
		
		return objCreditoTipo;
	}
	
}
