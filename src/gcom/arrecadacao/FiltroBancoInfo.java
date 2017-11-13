package gcom.arrecadacao;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroBancoInfo extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public FiltroBancoInfo() {
    }
    
    public FiltroBancoInfo(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public final static String ID = "id";
    
    public final static String GUIA_PAGAMENTO_ID = "guiaPagamento.id"; 
    
}
