package gcom.gui.cadastro.atualizacaocadastral;

import org.apache.struts.validator.ValidatorActionForm;

public class ExibirAtualizarDadosImovelAtualizacaoCadastralPopupActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String idImovel;
	private String descricaoImovel;
	private String idLocalidade;
	private String descricaoLocalidade;
	private String idSetorComercial;
	private String codigoSetorComercial;
	private String descricaoSetorComercial;
	private String idQuadra;
	private String numeroQuadra;
	private String idRegistrosNaoAutorizados;
	private String idRegistrosAutorizados;
	private String situacao;
	private boolean temPermissaoAprovarImovel;
	private String idRegistrosFiscalizados;
	private String idRegistrosNaoFiscalizados;
	private String idTipoAlteracao;
	
	public String getIdTipoAlteracao() {
		return idTipoAlteracao;
	}

	public void setIdTipoAlteracao(String idTipoAlteracao) {
		this.idTipoAlteracao = idTipoAlteracao;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdQuadra() {
		return idQuadra;
	}

	public void setIdQuadra(String idQuadra) {
		this.idQuadra = idQuadra;
	}

	public String getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoSetorComercial() {
		return descricaoSetorComercial;
	}

	public void setDescricaoSetorComercial(String descricaoSetorComercial) {
		this.descricaoSetorComercial = descricaoSetorComercial;
	}

	public String getIdRegistrosNaoAutorizados() {
		return idRegistrosNaoAutorizados;
	}

	public void setIdRegistrosNaoAutorizados(String idRegistrosNaoAutorizados) {
		this.idRegistrosNaoAutorizados = idRegistrosNaoAutorizados;
	}

	public String getIdRegistrosAutorizados() {
		return idRegistrosAutorizados;
	}

	public void setIdRegistrosAutorizados(String idRegistrosAutorizados) {
		this.idRegistrosAutorizados = idRegistrosAutorizados;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public void limparCampos() {
		this.setDescricaoLocalidade("");
		this.setDescricaoSetorComercial("");
		this.setNumeroQuadra("");
		this.setSituacao("");
	}

	public boolean getTemPermissaoAprovarImovel() {
		return temPermissaoAprovarImovel;
	}

	public void setTemPermissaoAprovarImovel(boolean temPermissaoAprovarImovel) {
		this.temPermissaoAprovarImovel = temPermissaoAprovarImovel;
	}

	public String getIdRegistrosFiscalizados() {
		return idRegistrosFiscalizados;
	}

	public void setIdRegistrosFiscalizados(String idRegistrosFiscalizados) {
		this.idRegistrosFiscalizados = idRegistrosFiscalizados;
	}

	public String getIdRegistrosNaoFiscalizados() {
		return idRegistrosNaoFiscalizados;
	}

	public void setIdRegistrosNaoFiscalizados(String idRegistrosNaoFiscalizados) {
		this.idRegistrosNaoFiscalizados = idRegistrosNaoFiscalizados;
	}
	
	
}
