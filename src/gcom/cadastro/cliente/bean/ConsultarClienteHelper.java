package gcom.cadastro.cliente.bean;

/**
 * 
 * Helper usado para o [UC0474] Consultar Cliente
 *  
 *
 * @author Rafael Santos
 * @date 18/09/2006
 */
public class ConsultarClienteHelper {

	private String tipoEndereco;
	
	private String indicadorEndereco;
	
	private String enderecoClliente;

	/**
	 * @return Retorna o campo enderecoClliente.
	 */
	public String getEnderecoClliente() {
		return enderecoClliente;
	}

	/**
	 * @param enderecoClliente O enderecoClliente a ser setado.
	 */
	public void setEnderecoClliente(String enderecoClliente) {
		this.enderecoClliente = enderecoClliente;
	}

	/**
	 * @return Retorna o campo indicadorEndereco.
	 */
	public String getIndicadorEndereco() {
		return indicadorEndereco;
	}

	/**
	 * @param indicadorEndereco O indicadorEndereco a ser setado.
	 */
	public void setIndicadorEndereco(String indicadorEndereco) {
		this.indicadorEndereco = indicadorEndereco;
	}

	/**
	 * @return Retorna o campo tipoEndereco.
	 */
	public String getTipoEndereco() {
		return tipoEndereco;
	}

	/**
	 * @param tipoEndereco O tipoEndereco a ser setado.
	 */
	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
	
}
