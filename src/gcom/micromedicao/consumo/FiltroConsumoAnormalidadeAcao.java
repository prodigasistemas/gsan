package gcom.micromedicao.consumo;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroConsumoAnormalidadeAcao extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroConsumoAnormalidadeAcao(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLigacaoAguaSituacao
     */
    public FiltroConsumoAnormalidadeAcao() {
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
    public final static String CONSUMO_ANORMALIDADE = "consumoAnormalidade";
    
    /**
     * Description of the Field
     */
    public final static String CATEGORIA = "categoria";
   
    /**
     * Description of the Field
     */
    public final static String IMOVEL_PERFIL = "imovelPerfil";
  
  	/**
  	 * Description of the Field
  	 */
    public final static String CONSUMO_COBRAR_MES1 = "leituraAnormalidadeConsumoMes1";
   
    /**
  	 * Description of the Field
  	 */
    public final static String CONSUMO_COBRAR_MES2 = "leituraAnormalidadeConsumoMes2";
    
    /**
  	 * Description of the Field
  	 */
    public final static String CONSUMO_COBRAR_MES3 = "leituraAnormalidadeConsumoMes3";
    
    /**
  	 * Description of the Field
  	 */
    public final static String FATOR_CONSUMO_CALCULO_MES1 = "numerofatorConsumoMes1";
    
    /**
  	 * Description of the Field
  	 */
    public final static String FATOR_CONSUMO_CALCULO_MES2 = "numerofatorConsumoMes2";
    
    /**
  	 * Description of the Field
  	 */
    public final static String FATOR_CONSUMO_CALCULO_MES3 = "numerofatorConsumoMes3";
    
    /**
  	 * Description of the Field
  	 */
    public final static String INDICADOR_GERACAO_CARTA_MES1 = "indicadorGeracaoCartaMes1";
    
    /**
  	 * Description of the Field
  	 */
    public final static String INDICADOR_GERACAO_CARTA_MES2 = "indicadorGeracaoCartaMes2";
    
    /**
  	 * Description of the Field
  	 */
    public final static String INDICADOR_GERACAO_CARTA_MES3 = "indicadorGeracaoCartaMes3";
    
    /**
  	 * Description of the Field
  	 */
    public final static String SERVICO_TIPO_MES1 = "servicoTipoMes1";
    
    /**
  	 * Description of the Field
  	 */
    public final static String SERVICO_TIPO_MES2 = "servicoTipoMes2";
    
    /**
  	 * Description of the Field
  	 */
    public final static String SERVICO_TIPO_MES3 = "servicoTipoMes3";
    
    /**
  	 * Description of the Field
  	 */
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO_MES1 = "solicitacaoTipoEspecificacaoMes1";
    
    /**
  	 * Description of the Field
  	 */
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO_MES2 = "solicitacaoTipoEspecificacaoMes2";
    
    /**
  	 * Description of the Field
  	 */
    public final static String SOLICITACAO_TIPO_ESPECIFICACAO_MES3 = "solicitacaoTipoEspecificacaoMes3";
    
    /**
  	 * Description of the Field
  	 */
    public final static String MENSAGEM_CONTA_MES1 = "descricaoContaMensagemMes1";
    
    /**
  	 * Description of the Field
  	 */
    public final static String MENSAGEM_CONTA_MES2 = "descricaoContaMensagemMes2";
    
    /**
  	 * Description of the Field
  	 */
    public final static String MENSAGEM_CONTA_MES3 = "descricaoContaMensagemMes3";
    
    /**
  	 * Description of the Field
  	 */
    public final static String CONSUMO_ANORMALIDADE_ID = "consumoAnormalidade.id";
   
    
   
 

}
