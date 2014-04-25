package gcom.gui.cadastro.atualizacaocadastral;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * Pesquisar Arquivo Texto para Atualização Cadastral
 *
 * @author Ana Maria
 * @date 10/06/2009
 */
public class PesquisarArquivoTextoAtualizacaoCadastralActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private String idLeiturista;
	private String nomeLeiturista;
	private String idSituacaoTransmissao;
	
	public String getNomeLeiturista() {
		return nomeLeiturista;
	}
	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}
	public String getIdLeiturista() {
		return idLeiturista;
	}
	public void setIdLeiturista(String idLeiturista) {
		this.idLeiturista = idLeiturista;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getIdSituacaoTransmissao() {
		return idSituacaoTransmissao;
	}
	public void setIdSituacaoTransmissao(String idSituacaoTransmissao) {
		this.idSituacaoTransmissao = idSituacaoTransmissao;
	}




}
