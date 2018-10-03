package gcom.cadastro.imovel;

import gcom.util.filtro.Filtro;

public class FiltroCadastroOcorrencia extends Filtro {

	private static final long serialVersionUID = 1L;

	public FiltroCadastroOcorrencia(String campoOrderBy) {
		this.campoOrderBy = campoOrderBy;
	}

	public FiltroCadastroOcorrencia() {
	}

	public final static String ID = "id";

	public final static String DESCRICAO = "descricao";

	public final static String INDICADOR_USO = "indicadorUso";

	public final static String INDICADOR_VALIDACAO = "indicadorValidacao";
}
