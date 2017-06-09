package gcom.cobranca.repositorios.dto;

import java.math.BigDecimal;

public class CancelarParcelamentoDTO {

	private Integer idParcelamento;

	private Integer idImovel;

	private BigDecimal saldoDevedor;

	public CancelarParcelamentoDTO() {
		super();
	}

	public CancelarParcelamentoDTO(Object[] dados) {
		super();
		this.idParcelamento = (Integer) dados[0];
		this.idImovel = (Integer) dados[1];
		this.saldoDevedor = (BigDecimal) dados[2];
	}

	public Integer getIdParcelamento() {
		return idParcelamento;
	}

	public void setIdParcelamento(Integer idParcelamento) {
		this.idParcelamento = idParcelamento;
	}

	public Integer getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Integer idImovel) {
		this.idImovel = idImovel;
	}

	public BigDecimal getSaldoDevedor() {
		return saldoDevedor;
	}

	public void setSaldoDevedor(BigDecimal saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}
}