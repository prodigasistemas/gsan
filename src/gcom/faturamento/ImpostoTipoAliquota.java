package gcom.faturamento;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author Administrador
 * @date 24/05/2006
 */
public class ImpostoTipoAliquota {

    private java.lang.Integer id;

    private java.lang.Integer anoMesReferencia;

    private java.math.BigDecimal percentualAliquota;

    private Date ultimaAlteracao;

    private gcom.faturamento.ImpostoTipoAliquota impostoTipoAliquota;

	public ImpostoTipoAliquota() {

	}

	public ImpostoTipoAliquota(Integer id, Integer anoMesReferencia, BigDecimal percentualAliquota, Date ultimaAlteracao, ImpostoTipoAliquota impostoTipoAliquota) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.anoMesReferencia = anoMesReferencia;
		this.percentualAliquota = percentualAliquota;
		this.ultimaAlteracao = ultimaAlteracao;
		this.impostoTipoAliquota = impostoTipoAliquota;
	}

	/**
	 * @return Retorna o campo anoMesReferencia.
	 */
	public java.lang.Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	/**
	 * @param anoMesReferencia O anoMesReferencia a ser setado.
	 */
	public void setAnoMesReferencia(java.lang.Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public java.lang.Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo impostoTipoAliquota.
	 */
	public gcom.faturamento.ImpostoTipoAliquota getImpostoTipoAliquota() {
		return impostoTipoAliquota;
	}

	/**
	 * @param impostoTipoAliquota O impostoTipoAliquota a ser setado.
	 */
	public void setImpostoTipoAliquota(
			gcom.faturamento.ImpostoTipoAliquota impostoTipoAliquota) {
		this.impostoTipoAliquota = impostoTipoAliquota;
	}

	/**
	 * @return Retorna o campo percentualAliquota.
	 */
	public java.math.BigDecimal getPercentualAliquota() {
		return percentualAliquota;
	}

	/**
	 * @param percentualAliquota O percentualAliquota a ser setado.
	 */
	public void setPercentualAliquota(java.math.BigDecimal percentualAliquota) {
		this.percentualAliquota = percentualAliquota;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
