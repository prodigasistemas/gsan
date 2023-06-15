package gcom.gui.portal;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class AguaParaActionForm extends ActionForm  {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String cpf;
	
	private String rg;
	
	private String telefone;
	
	private String idImovel;
	
	private String nis;
	
	private FormFile arquivoRg;
	
	private FormFile arquivoCpf;

	private FormFile arquivoConta;

	private FormFile arquivoBolsaFamiliaNis;


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getNis() {
		return nis;
	}

	public void setNis(String nis) {
		this.nis = nis;
	}

	public FormFile getArquivoRg() {
		return arquivoRg;
	}

	public void setArquivoRg(FormFile arquivoRg) {
		this.arquivoRg = arquivoRg;
	}

	public FormFile getArquivoCpf() {
		return arquivoCpf;
	}

	public void setArquivoCpf(FormFile arquivoCpf) {
		this.arquivoCpf = arquivoCpf;
	}

	public FormFile getArquivoConta() {
		return arquivoConta;
	}

	public void setArquivoConta(FormFile arquivoConta) {
		this.arquivoConta = arquivoConta;
	}

	public FormFile getArquivoBolsaFamiliaNis() {
		return arquivoBolsaFamiliaNis;
	}

	public void setArquivoBolsaFamiliaNis(FormFile arquivoBolsaFamiliaNis) {
		this.arquivoBolsaFamiliaNis = arquivoBolsaFamiliaNis;
	}

}
