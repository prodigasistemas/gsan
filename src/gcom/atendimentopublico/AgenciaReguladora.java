package gcom.atendimentopublico;

import java.io.Serializable;
import java.util.Date;

public class AgenciaReguladora implements Serializable{

	private static final long serialVersionUID = -8821712763770043132L;

	private Integer id;
	private String nome;
	private Integer referenciaInicialRepasse;
	private Integer percentualRepasse;
	private Date dataInicioVigencia;
	private Date dataFimVigencia;
	private Date ultimaAlteracao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getReferenciaInicialRepasse() {
		return referenciaInicialRepasse;
	}
	public void setReferenciaInicialRepasse(Integer referenciaInicialRepasse) {
		this.referenciaInicialRepasse = referenciaInicialRepasse;
	}
	public Integer getPercentualRepasse() {
		return percentualRepasse;
	}
	public void setPercentualRepasse(Integer percentualRepasse) {
		this.percentualRepasse = percentualRepasse;
	}
	public Date getDataInicioVigencia() {
		return dataInicioVigencia;
	}
	public void setDataInicioVigencia(Date dataInicioVigencia) {
		this.dataInicioVigencia = dataInicioVigencia;
	}
	public Date getDataFimVigencia() {
		return dataFimVigencia;
	}
	public void setDataFimVigencia(Date dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
