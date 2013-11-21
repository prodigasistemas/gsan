package gcom.atendimentopublico.ordemservico.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class OSPavimentoSomatorioPorTipoPavimentoHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String descricaoPvtRua;
	private BigDecimal valorPvtRua;
	private String descricaoPvtRuaRetorno;
	private BigDecimal valorPvtRuaRetorno;
	
	
	
	public OSPavimentoSomatorioPorTipoPavimentoHelper() {
	}
	
	
	public OSPavimentoSomatorioPorTipoPavimentoHelper(String descricaoPvtRua,
			BigDecimal valorPvtRua, String descricaoPvtRuaRetorno,
			BigDecimal valorPvtRuaRetorno) {
		this.descricaoPvtRua = descricaoPvtRua;
		this.valorPvtRua = valorPvtRua;
		this.descricaoPvtRuaRetorno = descricaoPvtRuaRetorno;
		this.valorPvtRuaRetorno = valorPvtRuaRetorno;
	}
	
	
	public String getDescricaoPvtRua() {
		return descricaoPvtRua;
	}
	public void setDescricaoPvtRua(String descricaoPvtRua) {
		this.descricaoPvtRua = descricaoPvtRua;
	}
	public BigDecimal getValorPvtRua() {
		return valorPvtRua;
	}
	public void setValorPvtRua(BigDecimal valorPvtRua) {
		this.valorPvtRua = valorPvtRua;
	}
	public String getDescricaoPvtRuaRetorno() {
		return descricaoPvtRuaRetorno;
	}
	public void setDescricaoPvtRuaRetorno(String descricaoPvtRuaRetorno) {
		this.descricaoPvtRuaRetorno = descricaoPvtRuaRetorno;
	}
	public BigDecimal getValorPvtRuaRetorno() {
		return valorPvtRuaRetorno;
	}
	public void setValorPvtRuaRetorno(BigDecimal valorPvtRuaRetorno) {
		this.valorPvtRuaRetorno = valorPvtRuaRetorno;
	}
	@Override
	public int hashCode() {
		return descricaoPvtRua.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;
		
		if (obj instanceof OSPavimentoSomatorioPorTipoPavimentoHelper) {
			OSPavimentoSomatorioPorTipoPavimentoHelper helper = 
				(OSPavimentoSomatorioPorTipoPavimentoHelper) obj;
			
			retorno = this.getDescricaoPvtRua().equalsIgnoreCase(helper.getDescricaoPvtRua())
				&&  this.getDescricaoPvtRuaRetorno().equalsIgnoreCase(helper.getDescricaoPvtRuaRetorno());
			
		}
		return retorno;
	}
}
