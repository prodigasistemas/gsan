package gcom.atendimentopublico.ligacaoesgoto;

import gcom.micromedicao.consumo.ConsumoTipo;

import java.io.Serializable;
import java.util.Date;

public class LigacaoEsgotoSituacaoConsumoTipo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;
	
	private ConsumoTipo consumoTipo;
	
	private short indicadorUso;

    private Date ultimaAlteracao;
    
    public LigacaoEsgotoSituacaoConsumoTipo(){}

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

	public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
		return ligacaoEsgotoSituacao;
	}

	public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
		this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
