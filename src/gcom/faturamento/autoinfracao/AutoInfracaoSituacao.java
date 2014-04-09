package gcom.faturamento.autoinfracao;

import java.io.Serializable;
import java.util.Date;

public class AutoInfracaoSituacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	private Short indicadorUso;
	
	private Date ultimaAlteracao;
	
	private Short indicadorGerarDebito;
	
	private Short indicadorDataInicioRecurso;
	
	private Short indicadorDataTerminoRecurso;
	
	private Integer numeroDiasPrazoRecursoInfracao;
	
	
	
	public static final Integer AUTO_EM_PRAZO_DE_RECURSO = 1;
	
	public static final Integer AUTO_COM_PRAZO_DE_RECURSO_VENCIDO = 2;
	
	public static final Integer AUTO_COM_RECURSO_EM_ANALISE_JULGAMENTO = 3;
	
	public static final Integer AUTO_COM_RECURSO_JULGADO_PROCEDENTE = 4;
	
	public static final Integer AUTO_COM_RECURSO_JULGADO_IMPROCEDENTE = 5;
	
	public static final Integer AUTO_CANCELADO = 6;
	
	
	
	
	
	public static final Short OBRIGATORIO = 1;
	
	public static final Short OPCIONAL = 2;
	
	public static final Short NULO = 3;
	
	
	public Short getIndicadorDataInicioRecurso() {
		return indicadorDataInicioRecurso;
	}

	public void setIndicadorDataInicioRecurso(Short indicadorDataInicioRecurso) {
		this.indicadorDataInicioRecurso = indicadorDataInicioRecurso;
	}

	public Short getIndicadorDataTerminoRecurso() {
		return indicadorDataTerminoRecurso;
	}

	public void setIndicadorDataTerminoRecurso(Short indicadorDataTerminoRecurso) {
		this.indicadorDataTerminoRecurso = indicadorDataTerminoRecurso;
	}

	public Short getIndicadorGerarDebito() {
		return indicadorGerarDebito;
	}

	public void setIndicadorGerarDebito(Short indicadorGerarDebito) {
		this.indicadorGerarDebito = indicadorGerarDebito;
	}

	public Integer getNumeroDiasPrazoRecursoInfracao() {
		return numeroDiasPrazoRecursoInfracao;
	}

	public void setNumeroDiasPrazoRecursoInfracao(
			Integer numeroDiasPrazoRecursoInfracao) {
		this.numeroDiasPrazoRecursoInfracao = numeroDiasPrazoRecursoInfracao;
	}

	public AutoInfracaoSituacao(){}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
