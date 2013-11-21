package gcom.cobranca;

import java.math.BigDecimal;
import java.util.Date;

public class MetaCiclo {


	private Integer id;
	
	private Integer anoMesReferencia;
	
	private CobrancaAcao cobrancaAcao;
	
	private Integer metaTotal;
	
	private BigDecimal valorLimite;
	
	private Date ultimaAlteracao;

	/**
	 * @return Retorna o campo anoMesReferencia.
	 */
	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	/**
	 * @param anoMesReferencia O anoMesReferencia a ser setado.
	 */
	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	/**
	 * @return Retorna o campo cobrancaAcao.
	 */
	public CobrancaAcao getCobrancaAcao() {
		return cobrancaAcao;
	}

	/**
	 * @param cobrancaAcao O cobrancaAcao a ser setado.
	 */
	public void setCobrancaAcao(CobrancaAcao cobrancaAcao) {
		this.cobrancaAcao = cobrancaAcao;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo metaTotal.
	 */
	public Integer getMetaTotal() {
		return metaTotal;
	}

	/**
	 * @param metaTotal O metaTotal a ser setado.
	 */
	public void setMetaTotal(Integer metaTotal) {
		this.metaTotal = metaTotal;
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

	/**
	 * @return Retorna o campo valorLimite.
	 */
	public BigDecimal getValorLimite() {
		return valorLimite;
	}

	/**
	 * @param valorLimite O valorLimite a ser setado.
	 */
	public void setValorLimite(BigDecimal valorLimite) {
		this.valorLimite = valorLimite;
	}

}
