package gcom.seguranca;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class FiltroConsultaCadastroCdl extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
    /**
     * Constructor for the FiltroCliente object
     * 
     * @param campoOrderBy
     *            Description of the Parameter
     */
    public FiltroConsultaCadastroCdl(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    /**
     * Construtor da classe FiltroLigacaoAguaSituacao
     */
    public FiltroConsultaCadastroCdl() {
    }

    /**
     * Description of the Field
     */
    public final static String ID = "id";

    /**
     * Description of the Field
     */
    public final static String CPF_CLIENTE = "cpfCliente";
    
    /**
     * Description of the Field
     */
    public final static String COD_CLIENTE_GSAN = "codigoCliente";
    
    /**
     * Description of the Field
     */
    public final static String CNPJ_CLIENTE = "cnpjCliente";
   
    /**
     * Description of the Field
     */
    public final static String NOME_CLIENTE = "nomeCliente";
  
  	/**
  	 * Description of the Field
  	 */
    public final static String ID_FUNCIONARIO = "idFuncionario";
   
	/**
  	 * Description of the Field
  	 */
    public final static String NOME_USUARIO = "nomeUsuario";
    
    /**
  	 * Description of the Field
  	 */
    public final static String CPF_USUARIO = "cpfUsuario";
   
    /**
  	 * Description of the Field
  	 */
    public final static String LOGIN_USUARIO = "loginUsuario";
   
    /**
  	 * Description of the Field
  	 */
    public final static String DATA_ULTIMA_ALTERACAO = "ultimaAlteracao";

    /**
  	 * Description of the Field
  	 */
    public final static String ACAO_OPERADOR = "codigoAcaoOperador";
}
