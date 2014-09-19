package gcom.cobranca;

import java.io.Serializable;
import java.util.Collection;

public class GerarExtensaoComandoContasCobrancaEmpresaHelper implements
		Serializable {
	private static final long serialVersionUID = 1L;
	
	private GerarArquivoTextoContasCobrancaEmpresaHelper gerarArquivoTextoContasCobrancaEmpresaHelper;
	
	private Collection <ExtensaoComandoContasCobrancaEmpresaHelper> colecaoExtensaoComandoContasCobrancaEmpresaHelper;

	private String anoMesInicial;
	
	private String anoMesFinal;
	

	public GerarArquivoTextoContasCobrancaEmpresaHelper getGerarArquivoTextoContasCobrancaEmpresaHelper() {
		return gerarArquivoTextoContasCobrancaEmpresaHelper;
	}

	public void setGerarArquivoTextoContasCobrancaEmpresaHelper(
			GerarArquivoTextoContasCobrancaEmpresaHelper gerarArquivoTextoContasCobrancaEmpresaHelper) {
		this.gerarArquivoTextoContasCobrancaEmpresaHelper = gerarArquivoTextoContasCobrancaEmpresaHelper;
	}


	/**
	 * Construtor de GerarExtensaoComandoContasCobrancaEmpresaHelper
	 * 
	 */
	public GerarExtensaoComandoContasCobrancaEmpresaHelper() {
		super();
		
	}

	public Collection<ExtensaoComandoContasCobrancaEmpresaHelper> getColecaoExtensaoComandoContasCobrancaEmpresaHelper() {
		return colecaoExtensaoComandoContasCobrancaEmpresaHelper;
	}

	public void setColecaoExtensaoComandoContasCobrancaEmpresaHelper(
			Collection<ExtensaoComandoContasCobrancaEmpresaHelper> colecaoExtensaoComandoContasCobrancaEmpresaHelper) {
		this.colecaoExtensaoComandoContasCobrancaEmpresaHelper = colecaoExtensaoComandoContasCobrancaEmpresaHelper;
	}

	public String getAnoMesFinal() {
		return anoMesFinal;
	}

	public void setAnoMesFinal(String anoMesFinal) {
		this.anoMesFinal = anoMesFinal;
	}

	public String getAnoMesInicial() {
		return anoMesInicial;
	}

	public void setAnoMesInicial(String anoMesInicial) {
		this.anoMesInicial = anoMesInicial;
	}

	
	

}
