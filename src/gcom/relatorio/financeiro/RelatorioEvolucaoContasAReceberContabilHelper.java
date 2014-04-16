package gcom.relatorio.financeiro;

import java.math.BigDecimal;
/**
 * UC0718 Gerar Relatório da Evolução do Contas a Receber Contábil
 * @author Francisco do Nascimento
 *
 */
public class RelatorioEvolucaoContasAReceberContabilHelper {

	private String tipoGrupo;
	
	private String descricaoGrupo;
	
	private int ordemGrupo;
	
	private String descricaoElementoGrupo;

	private int sequenciaTipoLancamento;
	
	private String descricaoLancamento;
	
	private String idCategoriaTipo;	
	
	private BigDecimal valorItem;

	public RelatorioEvolucaoContasAReceberContabilHelper(String tipoGrupo, String descricaoGrupo, int ordemGrupo, String descricaoElementoGrupo, int sequenciaTipoLancamento, String descricaoLancamento, String idCategoriaTipo, BigDecimal valorItem) {
		super();
		// TODO Auto-generated constructor stub
		this.tipoGrupo = tipoGrupo;
		this.descricaoGrupo = descricaoGrupo;
		this.ordemGrupo = ordemGrupo;
		this.descricaoElementoGrupo = descricaoElementoGrupo;
		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
		this.descricaoLancamento = descricaoLancamento;
		this.idCategoriaTipo = idCategoriaTipo;
		this.valorItem = valorItem;
	}

	public String getDescricaoElementoGrupo() {
		return descricaoElementoGrupo;
	}

	public void setDescricaoElementoGrupo(String descricaoElementoGrupo) {
		this.descricaoElementoGrupo = descricaoElementoGrupo;
	}

	public String getDescricaoGrupo() {
		return descricaoGrupo;
	}

	public void setDescricaoGrupo(String descricaoGrupo) {
		this.descricaoGrupo = descricaoGrupo;
	}

	public int getOrdemGrupo() {
		return ordemGrupo;
	}

	public void setOrdemGrupo(int ordemGrupo) {
		this.ordemGrupo = ordemGrupo;
	}

	public String getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public BigDecimal getValorItem() {
		return valorItem;
	}

	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}

	public String getDescricaoLancamento() {
		return descricaoLancamento;
	}

	public void setDescricaoLancamento(String descricaoLancamento) {
		this.descricaoLancamento = descricaoLancamento;
	}

	public String getIdCategoriaTipo() {
		return idCategoriaTipo;
	}

	public void setIdCategoriaTipo(String idCategoriaTipo) {
		this.idCategoriaTipo = idCategoriaTipo;
	}

	public int getSequenciaTipoLancamento() {
		return sequenciaTipoLancamento;
	}

	public void setSequenciaTipoLancamento(int sequenciaTipoLancamento) {
		this.sequenciaTipoLancamento = sequenciaTipoLancamento;
	}
	


}
