package gcom.relatorio.cobranca.contratoparcelamento;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import gcom.relatorio.RelatorioBean;

public class RelatorioEmitirExtratoContratoParcelamentoPorClienteBean implements RelatorioBean {
	
	private String sacadoParte01;
	
	private String sacadoParte03;
	
	private String nossoNumero;

	private String nomeCliente1;
	
	private String codigoClienteResponsavel1;
	
	private String enderecoCliente1;
	
	private String seqDocCobranca1;
	
	private String numeroParcelamento1;
	
	private String numeroParcela1;
	
	private String vencimentoFatura1;
	
	private String valorFatura1;
	
	private String dataEmissao1;
	
	private String valorTotalContas1;
	
	private JRBeanCollectionDataSource arrayJRSubrelatorioBean1;
	
	private String subRelatorio1;
	
	private String representacaoNumericaCodBarraFormatada1;
	
	private String representacaoNumericaCodBarraSemDigito1;
	
	private String totalParcelas;

	public JRBeanCollectionDataSource getArrayJRSubrelatorioBean1() {
		return arrayJRSubrelatorioBean1;
	}

	public void setArrayJRSubrelatorioBean1(
			JRBeanCollectionDataSource arrayJRSubrelatorioBean1) {
		this.arrayJRSubrelatorioBean1 = arrayJRSubrelatorioBean1;
	}

	public String getCodigoClienteResponsavel1() {
		return codigoClienteResponsavel1;
	}

	public void setCodigoClienteResponsavel1(String codigoClienteResponsavel1) {
		this.codigoClienteResponsavel1 = codigoClienteResponsavel1;
	}

	public String getDataEmissao1() {
		return dataEmissao1;
	}

	public void setDataEmissao1(String dataEmissao1) {
		this.dataEmissao1 = dataEmissao1;
	}

	public String getEnderecoCliente1() {
		return enderecoCliente1;
	}

	public void setEnderecoCliente1(String enderecoCliente1) {
		this.enderecoCliente1 = enderecoCliente1;
	}

	public String getNomeCliente1() {
		return nomeCliente1;
	}

	public void setNomeCliente1(String nomeCliente1) {
		this.nomeCliente1 = nomeCliente1;
	}

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getNumeroParcela1() {
		return numeroParcela1;
	}

	public void setNumeroParcela1(String numeroParcela1) {
		this.numeroParcela1 = numeroParcela1;
	}

	public String getNumeroParcelamento1() {
		return numeroParcelamento1;
	}

	public void setNumeroParcelamento1(String numeroParcelamento1) {
		this.numeroParcelamento1 = numeroParcelamento1;
	}

	public String getRepresentacaoNumericaCodBarraFormatada1() {
		return representacaoNumericaCodBarraFormatada1;
	}

	public void setRepresentacaoNumericaCodBarraFormatada1(
			String representacaoNumericaCodBarraFormatada1) {
		this.representacaoNumericaCodBarraFormatada1 = representacaoNumericaCodBarraFormatada1;
	}

	public String getRepresentacaoNumericaCodBarraSemDigito1() {
		return representacaoNumericaCodBarraSemDigito1;
	}

	public void setRepresentacaoNumericaCodBarraSemDigito1(
			String representacaoNumericaCodBarraSemDigito1) {
		this.representacaoNumericaCodBarraSemDigito1 = representacaoNumericaCodBarraSemDigito1;
	}

	public String getSacadoParte01() {
		return sacadoParte01;
	}

	public void setSacadoParte01(String sacadoParte01) {
		this.sacadoParte01 = sacadoParte01;
	}

	public String getSacadoParte03() {
		return sacadoParte03;
	}

	public void setSacadoParte03(String sacadoParte03) {
		this.sacadoParte03 = sacadoParte03;
	}

	public String getSeqDocCobranca1() {
		return seqDocCobranca1;
	}

	public void setSeqDocCobranca1(String seqDocCobranca1) {
		this.seqDocCobranca1 = seqDocCobranca1;
	}

	public String getSubRelatorio1() {
		return subRelatorio1;
	}

	public void setSubRelatorio1(String subRelatorio1) {
		this.subRelatorio1 = subRelatorio1;
	}

	public String getValorFatura1() {
		return valorFatura1;
	}

	public void setValorFatura1(String valorFatura1) {
		this.valorFatura1 = valorFatura1;
	}

	public String getValorTotalContas1() {
		return valorTotalContas1;
	}

	public void setValorTotalContas1(String valorTotalContas1) {
		this.valorTotalContas1 = valorTotalContas1;
	}

	public String getVencimentoFatura1() {
		return vencimentoFatura1;
	}

	public void setVencimentoFatura1(String vencimentoFatura1) {
		this.vencimentoFatura1 = vencimentoFatura1;
	}

	public String getTotalParcelas() {
		return totalParcelas;
	}

	public void setTotalParcelas(String totalParcelas) {
		this.totalParcelas = totalParcelas;
	}
	
}
