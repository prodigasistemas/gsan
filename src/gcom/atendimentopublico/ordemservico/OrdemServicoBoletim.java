package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** @author Hibernate CodeGenerator */
public class OrdemServicoBoletim implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
    private Short indicadorPavimento;
    
    private BigDecimal numeroReposicaoAsfalto;
    
    private BigDecimal numeroReposicaoParalelo;
    
    private BigDecimal numeroReposicaoCalcada;
    
    private Date ultimaAlteracao;

    private gcom.atendimentopublico.ordemservico.OrdemServico ordemServico;
    
	public OrdemServicoBoletim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorPavimento() {
		return indicadorPavimento;
	}

	public void setIndicadorPavimento(Short indicadorPavimento) {
		this.indicadorPavimento = indicadorPavimento;
	}

	public BigDecimal getNumeroReposicaoAsfalto() {
		return numeroReposicaoAsfalto;
	}

	public void setNumeroReposicaoAsfalto(BigDecimal numeroReposicaoAsfalto) {
		this.numeroReposicaoAsfalto = numeroReposicaoAsfalto;
	}

	public BigDecimal getNumeroReposicaoCalcada() {
		return numeroReposicaoCalcada;
	}

	public void setNumeroReposicaoCalcada(BigDecimal numeroReposicaoCalcada) {
		this.numeroReposicaoCalcada = numeroReposicaoCalcada;
	}

	public BigDecimal getNumeroReposicaoParalelo() {
		return numeroReposicaoParalelo;
	}

	public void setNumeroReposicaoParalelo(BigDecimal numeroReposicaoParalelo) {
		this.numeroReposicaoParalelo = numeroReposicaoParalelo;
	}

	public gcom.atendimentopublico.ordemservico.OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(
			gcom.atendimentopublico.ordemservico.OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public OrdemServicoBoletim(Integer id, Short indicadorPavimento, BigDecimal numeroReposicaoAsfalto, BigDecimal numeroReposicaoParalelo, BigDecimal numeroReposicaoCalcada, Date ultimaAlteracao, OrdemServico ordemServico) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.indicadorPavimento = indicadorPavimento;
		this.numeroReposicaoAsfalto = numeroReposicaoAsfalto;
		this.numeroReposicaoParalelo = numeroReposicaoParalelo;
		this.numeroReposicaoCalcada = numeroReposicaoCalcada;
		this.ultimaAlteracao = ultimaAlteracao;
		this.ordemServico = ordemServico;
	}

	
}
