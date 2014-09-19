package gcom.gerencial.cobranca.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Este caso de uso gera o resumo da pendência
 *
 * [UC0335] Gerar Resumo da Pendência
 *
 * @author Roberta Costa
 * @date 19/05/2006
 */
public class ResumoPendenciaAcumuladoHelper implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * @since 02/06/2006
	 */
	private String estado;
	private String tipoCategoria;
	private String categoria;
	private String tipoSituacaoAguaEsgoto;
	private Integer anoMesReferencia;
	private Integer quantidadeLigacoes;
	private Integer quantidadeDocumento;
	private BigDecimal valorPendente;

	/**
	 * @return Retorna o campo estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado O estado a ser setado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Retorna o campo tipoSituacaoAguaEsgoto.
	 */
	public String getTipoSituacaoAguaEsgoto() {
		return tipoSituacaoAguaEsgoto;
	}

	/**
	 * @param tipoSituacaoAguaEsgoto O tipoSituacaoAguaEsgoto a ser setado.
	 */
	public void setTipoSituacaoAguaEsgoto(String tipoSituacaoAguaEsgoto) {
		this.tipoSituacaoAguaEsgoto = tipoSituacaoAguaEsgoto;
	}

	/**
	 * Construtor de ResumoPendenciaAcumuladoHelper 
	 * 
	 */
	public ResumoPendenciaAcumuladoHelper() {
		super();
		
	}

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
	 * @return Retorna o campo categoria.
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return Retorna o campo quantidadeDocumento.
	 */
	public Integer getQuantidadeDocumento() {
		return quantidadeDocumento;
	}

	/**
	 * @param quantidadeDocumento O quantidadeDocumento a ser setado.
	 */
	public void setQuantidadeDocumento(Integer quantidadeDocumento) {
		this.quantidadeDocumento = quantidadeDocumento;
	}

	/**
	 * @return Retorna o campo quantidadeLigacoes.
	 */
	public Integer getQuantidadeLigacoes() {
		return quantidadeLigacoes;
	}

	/**
	 * @param quantidadeLigacoes O quantidadeLigacoes a ser setado.
	 */
	public void setQuantidadeLigacoes(Integer quantidadeLigacoes) {
		this.quantidadeLigacoes = quantidadeLigacoes;
	}

	/**
	 * @return Retorna o campo tipoCategoria.
	 */
	public String getTipoCategoria() {
		return tipoCategoria;
	}

	/**
	 * @param tipoCategoria O tipoCategoria a ser setado.
	 */
	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	/**
	 * @return Retorna o campo valorPendente.
	 */
	public BigDecimal getValorPendente() {
		return valorPendente;
	}

	/**
	 * @param valorPendente O valorPendente a ser setado.
	 */
	public void setValorPendente(BigDecimal valorPendente) {
		this.valorPendente = valorPendente;
	}

	/**
	 * Construtor de ResumoPendenciaAcumuladoHelper 
	 * 
	 * @param estado
	 * @param tipoCategoria
	 * @param categoria
	 * @param tipoSituacaoAguaEsgoto
	 * @param anoMesReferencia
	 * @param quantidadeLigacoes
	 * @param quantidadeDocumento
	 * @param valorPendente
	 */
	public ResumoPendenciaAcumuladoHelper(String estado, String tipoCategoria, String categoria, String tipoSituacaoAguaEsgoto, Integer anoMesReferencia, Integer quantidadeLigacoes, Integer quantidadeDocumento, BigDecimal valorPendente) {
		super();
		
		this.estado = estado;
		this.tipoCategoria = tipoCategoria;
		this.categoria = categoria;
		this.tipoSituacaoAguaEsgoto = tipoSituacaoAguaEsgoto;
		this.anoMesReferencia = anoMesReferencia;
		this.quantidadeLigacoes = quantidadeLigacoes;
		this.quantidadeDocumento = quantidadeDocumento;
		this.valorPendente = valorPendente;
	}
}
