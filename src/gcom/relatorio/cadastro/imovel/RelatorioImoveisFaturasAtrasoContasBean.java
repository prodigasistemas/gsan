package gcom.relatorio.cadastro.imovel;

import java.math.BigDecimal;
import java.util.Date;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

/**
 * @author Rafael Corrêa
 * @date 01/09/2006
 */
public class RelatorioImoveisFaturasAtrasoContasBean implements RelatorioBean {

	private String referencia;

	private BigDecimal valorSemEncargos;

	private BigDecimal valorComEncargos;

	private Date vencimento;

	public RelatorioImoveisFaturasAtrasoContasBean() {

	}


	public RelatorioImoveisFaturasAtrasoContasBean(RelatorioImoveisFaturasAtrasoHelper helper) {
		this.referencia = Util.formatarAnoMesParaMesAno(helper.getReferenciaFaturasAtrasoInicial());
		this.valorSemEncargos = helper.getValorFaturasAtrasoSemEncargos();
		this.valorComEncargos = helper.getValorFaturasAtrasoComEncargos();
		this.vencimento = helper.getVencimento();
	}

	public RelatorioImoveisFaturasAtrasoContasBean(String referencia, BigDecimal valor, Date vencimento) {
		this.referencia = referencia;
		this.valorSemEncargos = valor;
		this.vencimento = vencimento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public BigDecimal getValorSemEncargos() {
		return valorSemEncargos;
	}

	public void setValorSemEncargos(BigDecimal valor) {
		this.valorSemEncargos = valor;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public BigDecimal getValorComEncargos() {
		return valorComEncargos;
	}

	public void setValorComEncargos(BigDecimal valorComEncargos) {
		this.valorComEncargos = valorComEncargos;
	}

}
