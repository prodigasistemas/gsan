package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * Classe que representa o filtro do componente da equipe
 * 
 * @author Leonardo Regis
 * @date 20/09/2006
 */
public class FiltroEquipeComponentes extends Filtro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor Default
	 */
	public FiltroEquipeComponentes() {
	}

	/**
	 * Construtor passando order by
	 * 
	 * @param campoOrderBy
	 */
	public FiltroEquipeComponentes(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	/**
	 * Id da equipe
	 */
	public final static String ID = "id";
	
	public final static String ID_EQUIPE = "equipe.id";
	
	public final static String NOME_EQUIPE = "nome";
	
	public final static String PLACA_VEICULO_EQUIPE = "equipe.placaVeiculo";
	
	public final static String CARGA_TRABALHO_EQUIPE = "equipe.cargaTrabalho";
	
	public final static String INDICADOR_USO_EQUIPE = "equipe.indicadorUso";
	
	public final static String ID_UNIDADE_EQUIPE = "equipe.unidadeOrganizacional.id";
	
	public final static String ID_FUNCIONARIO = "funcionario.id";
	
	public final static String ID_SERVICO_PERFIL_TIPO_EQUIPE = "equipe.servicoPerfilTipo.id";

	/**
	 * Componente da equipe
	 */
	public final static String COMPONENTES = "componentes";

}
