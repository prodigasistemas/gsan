package gcom.cadastro.cliente.bean;

/**
 * @author Rafael Corrêa
 * @date 18/09/2006
 */
public class PesquisarClienteResponsavelSuperiorHelper {

	private String nome;
	
	private String cnpj;
	
	private Integer idEsferaPoder;
	
	private Short indicadorUso;
	
	private Short tipoPesquisa;

	/**
	 * @return Retorna o campo cnpj.
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj O cnpj a ser setado.
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * @return Retorna o campo idEsferaPoder.
	 */
	public Integer getIdEsferaPoder() {
		return idEsferaPoder;
	}

	/**
	 * @param idEsferaPoder O idEsferaPoder a ser setado.
	 */
	public void setIdEsferaPoder(Integer idEsferaPoder) {
		this.idEsferaPoder = idEsferaPoder;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public Short getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Retorna o campo nome.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome O nome a ser setado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Retorna o campo tipoPesquisa.
	 */
	public Short getTipoPesquisa() {
		return tipoPesquisa;
	}

	/**
	 * @param tipoPesquisa O tipoPesquisa a ser setado.
	 */
	public void setTipoPesquisa(Short tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}


	
}
