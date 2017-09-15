package gcom.util;

public enum FormatoData {
    DIA_MES_ANO          ("dd/MM/yyyy"),
    MES_ANO              ("MM/yyyy"),
    ANO_MES              ("yyyyMM"),
    AMERICANO            ("yyyyMMdd"),
    AMERICANO_COM_TRACO  ("yyyy-MM-dd"),
    DIA_MES_ANO_SEM_BARRA("ddMMyyyy");
    
    String formato;
    
    FormatoData(String f) {
        this.formato = f;
    }

    public String getFormato() {
        return formato;
    }
}
