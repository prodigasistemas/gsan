package gcom.cobranca.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Gerar Atividade de Ação de Cobrança Quantidade de documentos de cobrança
 * Quantidade de itens cobrados nos documentos Valor dos documentos de cobrança
 * 
 * @author Pedro Alexandre
 * @since 03/02/2006
 */
public class GerarAtividadeAcaoCobrancaHelper {

    /**
     * Quantidade de documentos de cobrança
     */
    private int quantidadeDocumentosCobranca;

    /**
     * Quantidade de itens cobrados nos documentos
     */
    private int quantidadeItensCobradosDocumentos;

    /**
     * Valor dos documentos de cobrança
     */
    private BigDecimal valorDocumentosCobranca;

    /**
     * Coleção de documentos de cobrança
     */
    private Collection colecaoDocumentosCobranca = new ArrayList();

    
    public GerarAtividadeAcaoCobrancaHelper() {

    }

    public GerarAtividadeAcaoCobrancaHelper(int quantidadeDocumentosCobranca, int quantidadeItensCobradosDocumentos, BigDecimal valorDocumentosCobranca) {
        this.quantidadeDocumentosCobranca = quantidadeDocumentosCobranca;
        this.quantidadeItensCobradosDocumentos = quantidadeItensCobradosDocumentos;
        this.valorDocumentosCobranca = valorDocumentosCobranca;
    }

    public int getQuantidadeDocumentosCobranca() {
        return quantidadeDocumentosCobranca;
    }

    public void setQuantidadeDocumentosCobranca(int quantidadeDocumentosCobranca) {
        this.quantidadeDocumentosCobranca = quantidadeDocumentosCobranca;
    }

    public int getQuantidadeItensCobradosDocumentos() {
        return quantidadeItensCobradosDocumentos;
    }

    public void setQuantidadeItensCobradosDocumentos(int quantidadeItensCobradosDocumentos) {
        this.quantidadeItensCobradosDocumentos = quantidadeItensCobradosDocumentos;
    }

    public BigDecimal getValorDocumentosCobranca() {
        return valorDocumentosCobranca;
    }

    public void setValorDocumentosCobranca(BigDecimal valorDocumentosCobranca) {
        this.valorDocumentosCobranca = valorDocumentosCobranca;
    }

    public Collection getColecaoDocumentosCobranca() {
        return colecaoDocumentosCobranca;
    }

    public void setColecaoDocumentosCobranca(Collection colecaoDocumentosCobranca) {
        this.colecaoDocumentosCobranca = colecaoDocumentosCobranca;
    }

}
