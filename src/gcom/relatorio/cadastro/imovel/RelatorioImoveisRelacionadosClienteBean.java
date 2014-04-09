package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioImoveisRelacionadosClienteBean implements RelatorioBean {
	
	private String cliente;

	private String profissaoCliente;

	private String ramoAtividadeCliente;

	private String cpfCnpjCliente;

	private String enderecoCorrespondenciaCliente;

	private Integer quantidadeImoveis;

	private JRBeanCollectionDataSource colecaoClienteImovelHelper;

	private JRBeanCollectionDataSource colecaoClienteImovelEconomia;

	public RelatorioImoveisRelacionadosClienteBean() {
	}

	public String getProfissaoCliente() {
		return profissaoCliente;
	}

	public void setProfissaoCliente(String id) {
		this.profissaoCliente = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String codigo) {
		this.cliente = codigo;
	}

	public String getRamoAtividadeCliente() {
		return ramoAtividadeCliente;
	}

	public void setRamoAtividadeCliente(String codAgencia) {
		this.ramoAtividadeCliente = codAgencia;
	}

	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}

	public void setCpfCnpjCliente(String nomeBanco) {
		this.cpfCnpjCliente = nomeBanco;
	}

	public String getEnderecoCorrespondenciaCliente() {
		return enderecoCorrespondenciaCliente;
	}

	public void setEnderecoCorrespondenciaCliente(
			String enderecoCorrespondenciaCliente) {
		this.enderecoCorrespondenciaCliente = enderecoCorrespondenciaCliente;
	}

	public JRBeanCollectionDataSource getColecaoClienteImovelHelper() {
		return colecaoClienteImovelHelper;
	}

	public void setColecaoClienteImovelHelper(
			JRBeanCollectionDataSource colecaoClienteImovelHelper) {
		this.colecaoClienteImovelHelper = colecaoClienteImovelHelper;
	}

	public JRBeanCollectionDataSource getColecaoClienteImovelEconomia() {
		return colecaoClienteImovelEconomia;
	}

	public void setColecaoClienteImovelEconomia(
			JRBeanCollectionDataSource coelcaoClienteImovelEconomia) {
		
		this.colecaoClienteImovelEconomia = coelcaoClienteImovelEconomia;
	}

	public Integer getQuantidadeImoveis() {
		return quantidadeImoveis;
	}

	public void setQuantidadeImoveis(Integer quantidadeImoveis) {
		this.quantidadeImoveis = quantidadeImoveis;
	}
}
