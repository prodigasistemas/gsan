package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroImovelProgramaEspecial extends Filtro implements
		Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroImovelProgramaEspecial(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroFonteAbastecimento
     */
    public FiltroImovelProgramaEspecial() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";
    
    /**
     * Description of the Field
     */
    public final static String IMOVEL_ID = "imovel.id";
    /**
     * Description of the Field
     */
    public final static String IMOVEL = "imovel";
    /**
     * Description of the Field
     */
    public final static String IMOVEL_PERFIL = "imovelPerfil";   
    /**
     * Description of the Field
     */
    public final static String IMOVEL_FATURAMENTO_GRUPO = "imovel.quadra.rota.faturamentoGrupo";
    /**
     * Description of the Field
     */
    public final static String QUADRA = "imovel.quadra";  
    /**
     * Description of the Field
     */
    public final static String LOCALIDADE = "imovel.localidade";
    /**
     * Description of the Field
     */
    public final static String SETOR_COMERCIAL = "imovel.setorComercial";
    /**
     * Description of the Field
     */
    public final static String DATA_SUSPENSAO = "dataSuspensao";
    /**
     * Description of the Field
     */
    public final static String DATA_INICIO = "mesAnoInicioPrograma";
    /**
     * Description of the Field
     */
    public final static String DATA_SAIDA= "mesAnoSaidaPrograma";
    /**
     * Description of the Field
     */
    public final static String DATA_APRESENTACAO_DOCUMENTOS= "dataApresentacaoDocumentos";
    /**
     * Description of the Field
     */
    public final static String FORMA_SUSPENSAO= "formaSuspensao";

}
