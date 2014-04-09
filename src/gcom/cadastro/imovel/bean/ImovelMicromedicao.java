package gcom.cadastro.imovel.bean;

import java.io.Serializable;

/**
 * @author Hibernate CodeGenerator
 * @created 1 de Junho de 2004
 */
public class ImovelMicromedicao implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private gcom.cadastro.imovel.Imovel imovel;
    
    private gcom.micromedicao.medicao.MedicaoHistorico medicaoHistorico;
    
    private gcom.micromedicao.consumo.ConsumoHistorico consumoHistorico;
    
    private gcom.micromedicao.consumo.ConsumoHistorico consumoHistoricoEsgoto;
    
    private String qtdDias;
    
    private Integer anoMesGrupoFaturamento;
    
    public Integer getAnoMesGrupoFaturamento() {
		return anoMesGrupoFaturamento;
	}

	public void setAnoMesGrupoFaturamento(Integer anoMesGrupoFaturamento) {
		this.anoMesGrupoFaturamento = anoMesGrupoFaturamento;
	}

	public String getQtdDias() {
		return qtdDias;
	}

	public void setQtdDias(String qtdDias) {
		this.qtdDias = qtdDias;
	}

	public ImovelMicromedicao(){}

	public gcom.micromedicao.consumo.ConsumoHistorico getConsumoHistorico() {
		return consumoHistorico;
	}

	public void setConsumoHistorico(
			gcom.micromedicao.consumo.ConsumoHistorico consumoHistorico) {
		this.consumoHistorico = consumoHistorico;
	}

	public gcom.cadastro.imovel.Imovel getImovel() {
		return imovel;
	}

	public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
		this.imovel = imovel;
	}

	public gcom.micromedicao.medicao.MedicaoHistorico getMedicaoHistorico() {
		return medicaoHistorico;
	}

	public void setMedicaoHistorico(
			gcom.micromedicao.medicao.MedicaoHistorico medicaoHistorico) {
		this.medicaoHistorico = medicaoHistorico;
	}
	
	public boolean equals(Object other) {
        if ((this == other)) {
            return true;
        }
        if (!(other instanceof ImovelMicromedicao)) {
            return false;
        }
        ImovelMicromedicao castOther = (ImovelMicromedicao) other;

        return (this.getImovel().getId().equals(castOther.getImovel().getId()));
    }

	public gcom.micromedicao.consumo.ConsumoHistorico getConsumoHistoricoEsgoto() {
		return consumoHistoricoEsgoto;
	}

	public void setConsumoHistoricoEsgoto(
			gcom.micromedicao.consumo.ConsumoHistorico consumoHistoricoEsgoto) {
		this.consumoHistoricoEsgoto = consumoHistoricoEsgoto;
	}
}
