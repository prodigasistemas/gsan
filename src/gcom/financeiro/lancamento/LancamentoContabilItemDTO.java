package gcom.financeiro.lancamento;

import gcom.arrecadacao.aviso.AvisoBancario;

import java.math.BigDecimal;

public class LancamentoContabilItemDTO {
	
	private Integer idLancamentoContabil;
	private AvisoBancario aviso;
	BigDecimal valorItem;
	
	public LancamentoContabilItemDTO(){
	}
	
	public LancamentoContabilItemDTO(Integer idLancamentoContabil, AvisoBancario aviso){
		this.idLancamentoContabil = idLancamentoContabil;
		this.aviso = aviso;
	}
	
	public LancamentoContabilItemDTO(Integer idLancamentoContabil, Integer idAviso){
		this.idLancamentoContabil = idLancamentoContabil;
		this.aviso = new AvisoBancario(idAviso);
	}
	
	public Integer getIdLancamentoContabil() {
		return idLancamentoContabil;
	}

	public void setIdLancamentoContabil(Integer idLancamentoContabil) {
		this.idLancamentoContabil = idLancamentoContabil;
	}

	public AvisoBancario getAviso() {
		return aviso;
	}

	public void setAviso(AvisoBancario aviso) {
		this.aviso = aviso;
	}

	public BigDecimal getValorItem() {
		return valorItem;
	}

	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	@Override
    public boolean equals(Object obj) {
		if(!(obj instanceof LancamentoContabilItemDTO)) return false; 

        if(obj == this) return true;

        LancamentoContabilItemDTO dto = (LancamentoContabilItemDTO) obj; 

        return this.idLancamentoContabil.equals(dto.getIdLancamentoContabil())
        		&& this.aviso.getId().equals(dto.getAviso().getId());
    } 
	
	@Override
	public int hashCode() {
		return idLancamentoContabil + aviso.getId() * 3;
	}

}
