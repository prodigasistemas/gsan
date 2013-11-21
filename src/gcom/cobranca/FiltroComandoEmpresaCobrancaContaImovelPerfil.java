package gcom.cobranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroComandoEmpresaCobrancaContaImovelPerfil extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroComandoEmpresaCobrancaContaImovelPerfil(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLocalidade
     */
    public FiltroComandoEmpresaCobrancaContaImovelPerfil() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    public final static String COMANDO_EMPRESA_COBRANCA_CONTA = "comandoEmpresaCobrancaConta";
    
    public final static String ID_COMANDO_EMPRESA_COBRANCA_CONTA = "comandoEmpresaCobrancaConta.id";
    
    public final static String ID_IMOVEL_PERFIL = "imovelPerfil.id";
    
    public final static String IMOVEL_PERFIL = "imovelPerfil";

    public final static String COMP_ID_COMANDO_EMPRESA_COBRANCA_CONTA_ID = "comp_id.cobrancaBoletimMedicaoId";
    
    public final static String COMP_ID_IMOVEL_PERFIL_ID = "comp_id.imovelPerfilId";
}
