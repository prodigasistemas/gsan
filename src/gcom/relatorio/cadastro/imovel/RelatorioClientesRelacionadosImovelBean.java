package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioClientesRelacionadosImovelBean implements RelatorioBean {
	
	private String matriculaImovel;

	private String inscricaoImovel;

	private String situacaoAguaImovel;

	private String situacaoEsgotoImovel;

	private String enderecoImovel;

	private JRBeanCollectionDataSource colecaoClienteImovel;

	private JRBeanCollectionDataSource colecaoImovelSubcategoriaHelper;

	public RelatorioClientesRelacionadosImovelBean() {
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String id) {
		this.inscricaoImovel = id;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String codigo) {
		this.matriculaImovel = codigo;
	}

	public String getSituacaoAguaImovel() {
		return situacaoAguaImovel;
	}

	public void setSituacaoAguaImovel(String codAgencia) {
		this.situacaoAguaImovel = codAgencia;
	}

	public String getSituacaoEsgotoImovel() {
		return situacaoEsgotoImovel;
	}

	public void setSituacaoEsgotoImovel(String nomeBanco) {
		this.situacaoEsgotoImovel = nomeBanco;
	}

	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	public void setEnderecoImovel(
			String enderecoCorrespondenciaCliente) {
		this.enderecoImovel = enderecoCorrespondenciaCliente;
	}

	public JRBeanCollectionDataSource getColecaoClienteImovel() {
		return colecaoClienteImovel;
	}

	public void setColecaoClienteImovel(
			JRBeanCollectionDataSource colecaoClienteImovelHelper) {
		this.colecaoClienteImovel = colecaoClienteImovelHelper;
	}

	public JRBeanCollectionDataSource getColecaoImovelSubcategoriaHelper() {
		return colecaoImovelSubcategoriaHelper;
	}

	public void setColecaoImovelSubcategoriaHelper(
			JRBeanCollectionDataSource coelcaoClienteImovelEconomia) {
		
		this.colecaoImovelSubcategoriaHelper = coelcaoClienteImovelEconomia;
	}
}
