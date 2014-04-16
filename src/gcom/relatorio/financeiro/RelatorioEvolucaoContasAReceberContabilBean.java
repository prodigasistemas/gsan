package gcom.relatorio.financeiro;

import gcom.relatorio.RelatorioBean;

import java.math.BigDecimal;

public class RelatorioEvolucaoContasAReceberContabilBean implements RelatorioBean {

	private BigDecimal[] valorItem;

	private String descricaoItemContabil;

	private String descricaoGrupo;
	
	private String tipoGrupo ;
	
//	private String descricaoLocalidade ;
//	
//	private String localidade ;
//	
//	private String descricaoUnidadeNegocio ;
//	
//	private String unidadeNegocio ;

	public RelatorioEvolucaoContasAReceberContabilBean(BigDecimal[] valorItem, String descricaoItemContabil) {
		super();
		// TODO Auto-generated constructor stub
		this.valorItem = valorItem;
		this.descricaoItemContabil = descricaoItemContabil;
	}

	public String getDescricaoGrupo() {
		return descricaoGrupo;
	}

	public void setDescricaoGrupo(String descricaoGrupo) {
		this.descricaoGrupo = descricaoGrupo;
	}

	public String getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public String getDescricaoItemContabil() {
		return descricaoItemContabil;
	}

	public void setDescricaoItemContabil(String descricaoItemContabil) {
		this.descricaoItemContabil = descricaoItemContabil;
	}

	public BigDecimal getValorItem1() {
		BigDecimal valorItem0 = valorItem[0];
		
		if (valorItem0 == null) {
			valorItem0 = new BigDecimal(0);
		}
		
		return valorItem0;
	}

	public BigDecimal getValorItem2() {
		BigDecimal valorItem1 = valorItem[1];
		
		if (valorItem1 == null) {
			valorItem1 = new BigDecimal(0);
		}
		
		return valorItem1;
	}

	public BigDecimal getValorItem3() {
		return this.getValorItem1().add(getValorItem2());
	}


}
