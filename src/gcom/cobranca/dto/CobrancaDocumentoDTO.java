package gcom.cobranca.dto;

import gcom.cadastro.cliente.ClienteFone;
import gcom.cobranca.CobrancaDocumento;

public class CobrancaDocumentoDTO {

	private CobrancaDocumento documento;
	private ClienteFone telefone;
	
	public CobrancaDocumento getDocumento() {
		return documento;
	}
	public void setDocumento(CobrancaDocumento documento) {
		this.documento = documento;
	}
	public ClienteFone getTelefone() {
		return telefone;
	}
	public void setTelefone(ClienteFone telefone) {
		this.telefone = telefone;
	}
	
	
}
