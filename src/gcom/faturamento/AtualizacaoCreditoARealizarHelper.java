package gcom.faturamento;

import gcom.faturamento.credito.CreditoARealizar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class AtualizacaoCreditoARealizarHelper {
    
    private BigDecimal valorTotalCreditos;
    private BigDecimal valorTotalDebitos;
    
    private Collection<ItemCreditoARealizar> creditos = new ArrayList<ItemCreditoARealizar>();
    
    class ItemCreditoARealizar {
        private CreditoARealizar creditoARelizar;
        
        private BigDecimal creditoCalculado;

        public ItemCreditoARealizar(CreditoARealizar creditoARelizar, BigDecimal creditoCalculado) {
            this.creditoARelizar = creditoARelizar;
            this.creditoCalculado = creditoCalculado;
        }

        public CreditoARealizar getCreditoARelizar() {
            return creditoARelizar;
        }

        public BigDecimal getCreditoCalculado() {
            return creditoCalculado;
        }
    }
    
    public void addCreditoARealizar(BigDecimal creditoCalculado, CreditoARealizar credito){
        this.creditos.add(new ItemCreditoARealizar(credito, creditoCalculado));
    }

    public BigDecimal getValorTotalCreditos() {
        return valorTotalCreditos;
    }

    public BigDecimal getValorTotalDebitos() {
        return valorTotalDebitos;
    }

    public Collection<ItemCreditoARealizar> getCreditos() {
        return creditos;
    }

    public void setValorTotalCreditos(BigDecimal valorTotalCreditos) {
        this.valorTotalCreditos = valorTotalCreditos;
    }

    public void setValorTotalDebitos(BigDecimal valorTotalDebitos) {
        this.valorTotalDebitos = valorTotalDebitos;
    }
}
