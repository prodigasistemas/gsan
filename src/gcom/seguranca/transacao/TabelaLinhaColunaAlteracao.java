package gcom.seguranca.transacao;

import gcom.util.ConstantesSistema;
import gcom.util.Util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class TabelaLinhaColunaAlteracao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String conteudoColunaAnterior;

	private String conteudoColunaAtual;

	private Date ultimaAlteracao;

	private gcom.seguranca.transacao.TabelaColuna tabelaColuna;

	private gcom.seguranca.transacao.TabelaLinhaAlteracao tabelaLinhaAlteracao;

	private short indicadorAtualizada;

	public short getIndicadorAtualizada() {
		return indicadorAtualizada;
	}

	public void setIndicadorAtualizada(short indicadorAtualizada) {
		this.indicadorAtualizada = indicadorAtualizada;
	}

	public TabelaLinhaColunaAlteracao(String conteudoColunaAnterior, String conteudoColunaAtual, Date ultimaAlteracao, gcom.seguranca.transacao.TabelaColuna tabelaColuna,
			gcom.seguranca.transacao.TabelaLinhaAlteracao tabelaLinhaAlteracao) {
		this.conteudoColunaAnterior = conteudoColunaAnterior;
		this.conteudoColunaAtual = conteudoColunaAtual;
		this.ultimaAlteracao = ultimaAlteracao;
		this.tabelaColuna = tabelaColuna;
		this.tabelaLinhaAlteracao = tabelaLinhaAlteracao;
	}

	public TabelaLinhaColunaAlteracao() {
	}

	public TabelaLinhaColunaAlteracao(gcom.seguranca.transacao.TabelaColuna tabelaColuna, gcom.seguranca.transacao.TabelaLinhaAlteracao tabelaLinhaAlteracao) {
		this.tabelaColuna = tabelaColuna;
		this.tabelaLinhaAlteracao = tabelaLinhaAlteracao;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConteudoColunaAnterior() {
		return this.conteudoColunaAnterior;
	}

	/**
	 * Recebe um object e converte para string aplicando a formatação adequada
	 */
	public void setConteudoColunaAnterior(Object conteudoColunaAnterior) {
		this.conteudoColunaAnterior = formatarConteudo(conteudoColunaAnterior);
	}

	/**
	 * Este método determina como será a formatação dos conteudos exibidos no registrar transação. Para cada tipo, é definido uma formatação.
	 */
	private String formatarConteudo(Object conteudo) {
		String retorno = "";
		if (conteudo != null) {
			retorno = conteudo.toString();
			if (conteudo instanceof Date) {
				retorno = Util.formatarDataComHora((Date) conteudo);
			} else if (conteudo instanceof Short && tabelaColuna != null && tabelaColuna.getColuna() != null && tabelaColuna.getColuna().indexOf("_ic") != -1) {
				if (ConstantesSistema.INDICADOR_USO_ATIVO.equals(conteudo)) {
					retorno = "Sim";
				} else {
					retorno = "Nao";
				}
			} else if (conteudo instanceof Integer) {
				Integer conteudoInt = (Integer) conteudo;
				if (tabelaColuna != null && tabelaColuna.getColuna() != null) {
					if (tabelaColuna.getColuna().indexOf("_am") != -1) {
						retorno = Util.formatarAnoMesParaMesAno(conteudoInt);
					} else if (tabelaColuna.getColuna().indexOf("psex_id") != -1) {

						switch (conteudoInt) {
						case 1:
							retorno = "MASCULINO";
							break;
						case 2:
							retorno = "FEMININO";
							break;
						default:
							retorno = "INDEFINIDO";
							break;
						}
					}
				}
			} else if (conteudo instanceof BigDecimal) {
				// Numero do IPTU eh BigDecimal mas nao deve ser formatado com #.###,##
				if (tabelaColuna != null && tabelaColuna.getColuna() != null && (tabelaColuna.getColuna().equals("imec_nniptu") || tabelaColuna.getColuna().equals("imov_nniptu"))) {
					retorno = conteudo + "";
				} else {
					retorno = Util.formataBigDecimal((BigDecimal) conteudo, 2, true);
				}
			}

			if (retorno != null && retorno.length() > 50) {
				if (tabelaColuna != null && tabelaColuna.getColuna() != null) {
					if (tabelaColuna.getColuna().equals("imac_dsoutrasinformacoes"))
						retorno = retorno.substring(0, retorno.length());
					else
						retorno = retorno.substring(0, 49);
				}
			}
		}
		return retorno;
	}

	public String getConteudoColunaAtual() {
		return this.conteudoColunaAtual;
	}

	public void setConteudoColunaAtual(Object conteudoColunaAtual) {
		this.conteudoColunaAtual = formatarConteudo(conteudoColunaAtual);
	}

	public Date getUltimaAlteracao() {
		return this.ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public gcom.seguranca.transacao.TabelaColuna getTabelaColuna() {
		return this.tabelaColuna;
	}

	public void setTabelaColuna(gcom.seguranca.transacao.TabelaColuna tabelaColuna) {
		this.tabelaColuna = tabelaColuna;
	}

	public gcom.seguranca.transacao.TabelaLinhaAlteracao getTabelaLinhaAlteracao() {
		return this.tabelaLinhaAlteracao;
	}

	public void setTabelaLinhaAlteracao(gcom.seguranca.transacao.TabelaLinhaAlteracao tabelaLinhaAlteracao) {
		this.tabelaLinhaAlteracao = tabelaLinhaAlteracao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
}
