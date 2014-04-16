package gcom.relatorio;


/**
 * Representa um relatorio processado
 * com os dados e o tipo de relatorio 
 * a ser apresentado 
 *
 * @author Thiago Toscano
 * @date 25/05/2006
 */
public class RelatorioProcessado {

	private byte[] dados = null;
	private int tipoRelatorio;

	public RelatorioProcessado(byte[] dados, int tipoRelatorio) {
		this.dados = dados;  
		this.tipoRelatorio = tipoRelatorio;
	}

	/**
	 * @return Retorna o campo dados.
	 */
	public byte[] getDados() {
		return dados;
	}
	/**
	 * @return Retorna o campo tipoRelatorio.
	 */
	public int getTipoRelatorio() {
		return tipoRelatorio;
	}
}
