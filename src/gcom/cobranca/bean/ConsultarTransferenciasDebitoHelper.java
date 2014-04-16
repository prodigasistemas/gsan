package gcom.cobranca.bean;

import java.util.Date;

/**
 * Objeto do caso de uso [UC0216] Calcular Acrescimo por Impontualidade
 *  Valor Multa
 *  Valor Juros de Mora
 *  Valor Atualizacao Monetaria
 * @author Rafael Santos
 * @since 05/01/2006
 *
 */
public class ConsultarTransferenciasDebitoHelper {
	
	private Integer idImovelOrigem;
	
	private Integer idImovelDestino;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private Integer idUsuario;
	
	public ConsultarTransferenciasDebitoHelper() {}
	
	/**
	 * @param valorMulta
	 * @param valorJurosMora
	 * @param valorAtualizacaoMonetaria
	 */
	public ConsultarTransferenciasDebitoHelper(Integer idImovelOrigem, Integer idImovelDestino, Date dataInicial, Date dataFinal, Integer idUsuario) {
		super();
		// TODO Auto-generated constructor stub
		this.idImovelOrigem = idImovelOrigem;
		this.idImovelDestino = idImovelDestino;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.idUsuario = idUsuario;
	}

	/**
	 * @return Retorna o campo dataFinal.
	 */
	public Date getDataFinal() {
		return dataFinal;
	}

	/**
	 * @param dataFinal O dataFinal a ser setado.
	 */
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	/**
	 * @return Retorna o campo dataInicial.
	 */
	public Date getDataInicial() {
		return dataInicial;
	}

	/**
	 * @param dataInicial O dataInicial a ser setado.
	 */
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	/**
	 * @return Retorna o campo idImovelDestino.
	 */
	public Integer getIdImovelDestino() {
		return idImovelDestino;
	}

	/**
	 * @param idImovelDestino O idImovelDestino a ser setado.
	 */
	public void setIdImovelDestino(Integer idImovelDestino) {
		this.idImovelDestino = idImovelDestino;
	}

	/**
	 * @return Retorna o campo idImovelOrigem.
	 */
	public Integer getIdImovelOrigem() {
		return idImovelOrigem;
	}

	/**
	 * @param idImovelOrigem O idImovelOrigem a ser setado.
	 */
	public void setIdImovelOrigem(Integer idImovelOrigem) {
		this.idImovelOrigem = idImovelOrigem;
	}

	/**
	 * @return Retorna o campo idUsuario.
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario O idUsuario a ser setado.
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	
}
