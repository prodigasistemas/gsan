package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * Classe que representa o filtro da equipe
 * 
 * @author Leonardo Regis
 * @date 31/07/2006
 */
public class FiltroEquipe extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor Default
	 */
	public FiltroEquipe() {
	}

	/**
	 * Construtor passando order by
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */
	public FiltroEquipe(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Id da equipe
	 */
	public final static String ID = "id";

	/**
	 * Nome da equipe
	 */
	public final static String NOME = "nome";

	/**
	 * Placa do Veículo
	 */
	public final static String PLACA_VEICULO = "placaVeiculo";
	
	/**
     * Carga Horária
     */
    public final static String NUMERO_IMEI = "numeroImei";

	/**
     * Id da Unidade Organizacional
     */
    public final static String ID_UNIDADE_ORGANIZACIONAL = "unidadeOrganizacional.id";

    /**
     * Id do Perfil do Tipo de Serviço
     */
    public final static String ID_SERVICO_PERFIL_TIPO = "servicoPerfilTipo.id";
    
    /**
     * Numero imei
     */
    public final static String CARGA_TRABALHO = "cargaTrabalho";
    
    public final static String INDICADOR_USO = "indicadorUso";
    
    public final static String USUARIO_RESP_EXECUSSAO_SERVICO = "usuarioRespExecServico";
    
    public final static String INDICADOR_PROGRAMACAO_AUTOMATICA = "indicadorProgramacaoAutomatica";
}
