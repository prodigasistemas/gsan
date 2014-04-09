package gcom.cobranca;

/**
 * Classe Helper para armazenar os dados obtidos da consulta
 * RepositorioCobranca.pesquisarQuantidadeImoveisPorGrupoLocalidade
 * 
 * @author Francisco do Nascimento
 * @since 23/04/2009
 */
public class GrupoLocalidadeImovelHelper {

	private int idGrupo = 0;
	
	private int idLocalidade = 0;
	
	private int quantidadeImoveis = 0;

	/**
	 * @return Retorna o campo idGrupo.
	 */
	public int getIdGrupo() {
		return idGrupo;
	}

	/**
	 * @param idGrupo O idGrupo a ser setado.
	 */
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public int getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(int idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo quantidadeImoveis.
	 */
	public int getQuantidadeImoveis() {
		return quantidadeImoveis;
	}

	/**
	 * @param quantidadeImoveis O quantidadeImoveis a ser setado.
	 */
	public void setQuantidadeImoveis(int quantidadeImoveis) {
		this.quantidadeImoveis = quantidadeImoveis;
	}

	public GrupoLocalidadeImovelHelper(int idGrupo, int idLocalidade, int quantidadeImoveis) {
		super();
		// TODO Auto-generated constructor stub
		this.idGrupo = idGrupo;
		this.idLocalidade = idLocalidade;
		this.quantidadeImoveis = quantidadeImoveis;
	}

	public GrupoLocalidadeImovelHelper() {
		// TODO Auto-generated constructor stub
	}
	
	

}
