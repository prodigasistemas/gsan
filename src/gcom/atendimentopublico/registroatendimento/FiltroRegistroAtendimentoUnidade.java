package gcom.atendimentopublico.registroatendimento;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

/**
 * Filtro para pesquisar o Registro de Atendimento Unidade
 */
public class FiltroRegistroAtendimentoUnidade extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;

	public FiltroRegistroAtendimentoUnidade() {
	}

	public FiltroRegistroAtendimentoUnidade(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public final static String ID = "id";
	public final static String REGISTRO_ATENDIMENTO_ID = "registroAtendimento.id";
	public final static String UNIDADE_ORGANIZACIONAL_ID = "unidadeOrganizacional.id";
	public final static String ATENDIMENTO_RELACAO_TIPO = "atendimentoRelacaoTipo.id";
	public final static String USUARIO = "usuario";
}
