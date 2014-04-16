package gcom.micromedicao;

import gcom.util.Util;
import java.util.Date;

/**
 * 
 * Helper contendo as informações necessárias para a exibição do
 * grupo de contas a ser alterado 
 *
 * @author bruno
 * @date 26/02/2009
 */

public class AlterarDatasLeiturasHelper {
    
    private String dtLeituraAnterior;
    private String dtLeituraAtual;
    private Integer qtdImoveis;
    private Integer qtdDias;
    private String dtLeituraAnteriorNova;
    private String dtLeituraAtualNova;

    public AlterarDatasLeiturasHelper(
            Date dtLeituraAnterior, 
            Date dtLeituraAtual, 
            Integer qtdImoveis ) {
        super();

        this.dtLeituraAnterior = Util.formatarData( dtLeituraAnterior );
        this.dtLeituraAtual = Util.formatarData( dtLeituraAtual );
        this.qtdImoveis = qtdImoveis;
        this.qtdDias = 
            Util.obterQuantidadeDiasEntreDuasDatas( 
                    dtLeituraAnterior, dtLeituraAtual );
    }

    public String getDtLeituraAnterior() {
        return dtLeituraAnterior;
    }
    
    public String getDtLeituraAtual() {
        return dtLeituraAtual;
    }
    
    public Integer getQtdDias() {
        return qtdDias;
    }
    
    public void setQtdDias(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }
    
    public Integer getQtdImoveis() {
        return qtdImoveis;
    }
    
    public void setQtdImoveis(Integer qtdImoveis) {
        this.qtdImoveis = qtdImoveis;
    }

    
    public String getDtLeituraAnteriorNova() {
        return dtLeituraAnteriorNova;
    }

    
    public void setDtLeituraAnteriorNova(String dtLeituraAnteriorNova) {
        this.dtLeituraAnteriorNova = dtLeituraAnteriorNova;
    }

    
    public String getDtLeituraAtualNova() {
        return dtLeituraAtualNova;
    }

    
    public void setDtLeituraAtualNova(String dtLeituraAtualNova) {
        this.dtLeituraAtualNova = dtLeituraAtualNova;
    }
}
