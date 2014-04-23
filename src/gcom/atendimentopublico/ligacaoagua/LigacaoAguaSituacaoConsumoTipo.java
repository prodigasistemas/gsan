package gcom.atendimentopublico.ligacaoagua;

import gcom.micromedicao.consumo.ConsumoTipo;

import java.io.Serializable;
import java.util.Date;

public class LigacaoAguaSituacaoConsumoTipo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private LigacaoAguaSituacao ligacaoAguaSituacao;
	
	private ConsumoTipo consumoTipo;
	
	private short indicadorUso;

    private Date ultimaAlteracao;
    
    public LigacaoAguaSituacaoConsumoTipo(){}

	public ConsumoTipo getConsumoTipo() {
		return consumoTipo;
	}

	public void setConsumoTipo(ConsumoTipo consumoTipo) {
		this.consumoTipo = consumoTipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public LigacaoAguaSituacao getLigacaoAguaSituacao() {
		return ligacaoAguaSituacao;
	}

	public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
		this.ligacaoAguaSituacao = ligacaoAguaSituacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

}
