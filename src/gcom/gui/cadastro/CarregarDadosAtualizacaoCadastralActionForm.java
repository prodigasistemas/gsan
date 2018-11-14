package gcom.gui.cadastro;

import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class CarregarDadosAtualizacaoCadastralActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private FormFile arquivo;
	
	private Map<String, List<String>> colecaoErrosCadastro;
	
	private String nomeArquivo;
	
	private String totalImoveis;
	
	private String totalImoveisComErro;
	
	private String tipoArquivo;

	public FormFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(FormFile arquivo) {
		this.arquivo = arquivo;
	}

	public Map<String, List<String>> getColecaoErrosCadastro() {
		return colecaoErrosCadastro;
	}

	public void setColecaoErrosCadastro(
			Map<String, List<String>> colecaoErrosCadastro) {
		this.colecaoErrosCadastro = colecaoErrosCadastro;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getTotalImoveis() {
		return totalImoveis;
	}

	public void setTotalImoveis(String totalImoveis) {
		this.totalImoveis = totalImoveis;
	}

	public String getTotalImoveisComErro() {
		return totalImoveisComErro;
	}

	public void setTotalImoveisComErro(String totalImoveisComErro) {
		this.totalImoveisComErro = totalImoveisComErro;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
}
