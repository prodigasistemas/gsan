package gcom.relatorio.faturamento;

import gcom.faturamento.bean.DebitoCobradoAgrupadoHelper;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


/**
 *[UC0958] - Gerar Relatorio de Juros, Multas e Debitos Cancelados.
 *
 * @author Marlon Patrick
 * @since 07/10/2009
 */
public class RelatorioJurosMultasDebitosCanceladosHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Date dataCancelamento;
	
	private String responsavel;
	
	private String inscricao;
	
	private String matricula;
	
	private String endereco;
	
	private Integer anoMesReferencia;
	
	private Integer idConta;
		
	/**Será usado tanto para os dados vindo de debito cobrados historico quanto debito cobrados**/
	private Collection<DebitoCobradoAgrupadoHelper> colecaoDebitosCobrados;

	private String tabelaOrigem;

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getAnoMesReferencia() {
		return anoMesReferencia;
	}

	public void setAnoMesReferencia(Integer anoMesReferencia) {
		this.anoMesReferencia = anoMesReferencia;
	}

	public String getTabelaOrigem() {
		return tabelaOrigem;
	}

	public void setTabelaOrigem(String tabelaOrigem) {
		this.tabelaOrigem = tabelaOrigem;
	}

	public Collection<DebitoCobradoAgrupadoHelper> getColecaoDebitosCobrados() {
		return colecaoDebitosCobrados;
	}

	public void setColecaoDebitosCobrados(
			Collection<DebitoCobradoAgrupadoHelper> colecaoDebitosACobrar) {
		this.colecaoDebitosCobrados = colecaoDebitosACobrar;
	}

	/**Os métodos equals e hashCode precisam ser sobrescritos para que o objeto possa ser comparado
	 * por exemplo, com os métodos da API collection (contains, remove etc.)
	 * **/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((anoMesReferencia == null) ? 0 : anoMesReferencia.hashCode());
		result = prime
				* result
				+ ((dataCancelamento == null) ? 0 : dataCancelamento.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result
				+ ((inscricao == null) ? 0 : inscricao.hashCode());
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result
				+ ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result
				+ ((tabelaOrigem == null) ? 0 : tabelaOrigem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelatorioJurosMultasDebitosCanceladosHelper other = (RelatorioJurosMultasDebitosCanceladosHelper) obj;
		if (anoMesReferencia == null) {
			if (other.anoMesReferencia != null)
				return false;
		} else if (!anoMesReferencia.equals(other.anoMesReferencia))
			return false;
		if (dataCancelamento == null) {
			if (other.dataCancelamento != null)
				return false;
		} else if (!dataCancelamento.equals(other.dataCancelamento))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (inscricao == null) {
			if (other.inscricao != null)
				return false;
		} else if (!inscricao.equals(other.inscricao))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (tabelaOrigem == null) {
			if (other.tabelaOrigem != null)
				return false;
		} else if (!tabelaOrigem.equals(other.tabelaOrigem))
			return false;
		return true;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
}
