package gcom.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;

/** @author Hibernate CodeGenerator */
public class ServicoTipoBoletim implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
    private Short indicadorPavimento;
    
    private Short indicadorReposicaoAsfalto;
    
    private Short indicadorReposicaoParalelo;
    
    private Short indicadorReposicaoCalcada;
    
    private Date ultimaAlteracao;

    private gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo;
    
	public ServicoTipoBoletim() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServicoTipoBoletim(Short indicadorPavimento, Short indicadorReposicaoAsfalto, Short indicadorReposicaoParalelo, Short indicadorReposicaoCalcada, Date ultimaAlteracao, ServicoTipo servicoTipo) {
		super();
		// TODO Auto-generated constructor stub
		this.indicadorPavimento = indicadorPavimento;
		this.indicadorReposicaoAsfalto = indicadorReposicaoAsfalto;
		this.indicadorReposicaoParalelo = indicadorReposicaoParalelo;
		this.indicadorReposicaoCalcada = indicadorReposicaoCalcada;
		this.ultimaAlteracao = ultimaAlteracao;
		this.servicoTipo = servicoTipo;
	}

	public Short getIndicadorPavimento() {
		return indicadorPavimento;
	}

	public void setIndicadorPavimento(Short indicadorPavimento) {
		this.indicadorPavimento = indicadorPavimento;
	}

	public Short getIndicadorReposicaoAsfalto() {
		return indicadorReposicaoAsfalto;
	}

	public void setIndicadorReposicaoAsfalto(Short indicadorReposicaoAsfalto) {
		this.indicadorReposicaoAsfalto = indicadorReposicaoAsfalto;
	}

	public Short getIndicadorReposicaoCalcada() {
		return indicadorReposicaoCalcada;
	}

	public void setIndicadorReposicaoCalcada(Short indicadorReposicaoCalcada) {
		this.indicadorReposicaoCalcada = indicadorReposicaoCalcada;
	}

	public Short getIndicadorReposicaoParalelo() {
		return indicadorReposicaoParalelo;
	}

	public void setIndicadorReposicaoParalelo(Short indicadorReposicaoParalelo) {
		this.indicadorReposicaoParalelo = indicadorReposicaoParalelo;
	}

	public gcom.atendimentopublico.ordemservico.ServicoTipo getServicoTipo() {
		return servicoTipo;
	}

	public void setServicoTipo(
			gcom.atendimentopublico.ordemservico.ServicoTipo servicoTipo) {
		this.servicoTipo = servicoTipo;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
