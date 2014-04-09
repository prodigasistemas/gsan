package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroImovelPerfil extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroImovelPerfil(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroFonteAbastecimento
     */
    public FiltroImovelPerfil() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String INDICADOR_USO = "indicadorUso";
    
    /**
     * Description of the Field
     */ 
    public final static String DESCRICAO = "descricao";
    
    /**
     * Description of the Field
     */ 
    public final static String INDICADOR_GERACAO_AUTOMATICA = "indicadorGeracaoAutomatica";
    
    /**
     * Description of the Field
     */ 
    public final static String INDICADOR_INSERIR_MANTER_PERFIL = "indicadorInserirManterPerfil";
    
    /**
     * Description of the Field
     */    
    public final static String INDICADOR_GERAR_DADOS_LEITURA = "indicadorGerarDadosLeitura";
    
    /**
     * Description of the Field
     */    
    public final static String INDICADOR_BLOQUEAR_RETIFICACAO = "indicadorBloquearRetificacao";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_GRANDE_CONSUMIDOR = "indicadorGrandeConsumidor";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_BLOQUEAR_DADOS_SOCIAL = "indicadorBloqueaDadosSocial";
    
    /**
     * Description of the Field
     */
    public final static String INDICADOR_GERA_DEBITO_SEGUNDA_VIA_CONTA = "indicadorGeraDebitoSegundaViaConta";
    
}
