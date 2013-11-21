package gcom.arrecadacao.bean;


public class PesquisarAnaliseArrecadacaoHelper {
	
	private Integer mesAno;
	private Boolean porEstado;
	private Boolean porArrecadador;
	private Boolean porFormaArrecadacao;
	private Integer idArrecadador;
	private Integer idFormaArrecadacao;

	public PesquisarAnaliseArrecadacaoHelper() { }

	/**
	 * @return Retorna o campo idArrecadador.
	 */
	public Integer getIdArrecadador() {
		return idArrecadador;
	}

	/**
	 * @param idArrecadador O idArrecadador a ser setado.
	 */
	public void setIdArrecadador(Integer idArrecadador) {
		this.idArrecadador = idArrecadador;
	}

	/**
	 * @return Retorna o campo idFormaArrecadacao.
	 */
	public Integer getIdFormaArrecadacao() {
		return idFormaArrecadacao;
	}

	/**
	 * @param idFormaArrecadacao O idFormaArrecadacao a ser setado.
	 */
	public void setIdFormaArrecadacao(Integer idFormaArrecadacao) {
		this.idFormaArrecadacao = idFormaArrecadacao;
	}

	/**
	 * @return Retorna o campo mesAno.
	 */
	public Integer getMesAno() {
		return mesAno;
	}

	/**
	 * @param mesAno O mesAno a ser setado.
	 */
	public void setMesAno(Integer mesAno) {
		this.mesAno = mesAno;
	}

	/**
	 * @return Retorna o campo porArrecadador.
	 */
	public Boolean getPorArrecadador() {
		return porArrecadador;
	}

	/**
	 * @param porArrecadador O porArrecadador a ser setado.
	 */
	public void setPorArrecadador(Boolean porArrecadador) {
		this.porArrecadador = porArrecadador;
	}

	/**
	 * @return Retorna o campo porEstado.
	 */
	public Boolean getPorEstado() {
		return porEstado;
	}

	/**
	 * @param porEstado O porEstado a ser setado.
	 */
	public void setPorEstado(Boolean porEstado) {
		this.porEstado = porEstado;
	}

	/**
	 * @return Retorna o campo porFormaArrecadacao.
	 */
	public Boolean getPorFormaArrecadacao() {
		return porFormaArrecadacao;
	}

	/**
	 * @param porFormaArrecadacao O porFormaArrecadacao a ser setado.
	 */
	public void setPorFormaArrecadacao(Boolean porFormaArrecadacao) {
		this.porFormaArrecadacao = porFormaArrecadacao;
	}
	
}
