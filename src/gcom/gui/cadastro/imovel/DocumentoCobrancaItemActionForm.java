package gcom.gui.cadastro.imovel;

import org.apache.struts.action.*;

import javax.servlet.http.*;

/**
 * Visualiza em PopUp dos dados do Documento de Cobranca 
 * [UC0472] Consultar Imovel 
 * Aba 8° - Documentos de Cobrança
 * 
 * @author  Rafael Santos
 * @created 19/09/2006
 */
public class DocumentoCobrancaItemActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String situacaoAguaImovel;
	private String situacaoEsgotoImovel;
	private String sequencial;
	private String valorDocumento;
	private String valorDesconto;
	private String formaEmissao;
	private String dataHoraEmissao;
	private String motivoNaoEntregaDocumento;
	private String qtdItens;
	private String nomeUsuario;
	
	public String getFormaEmissao() {
		return formaEmissao;
	}
	public void setFormaEmissao(String formaEmissao) {
		this.formaEmissao = formaEmissao;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getMotivoNaoEntregaDocumento() {
		return motivoNaoEntregaDocumento;
	}
	public void setMotivoNaoEntregaDocumento(String motivoNaoEntregaDocumento) {
		this.motivoNaoEntregaDocumento = motivoNaoEntregaDocumento;
	}
	public String getQtdItens() {
		return qtdItens;
	}
	public void setQtdItens(String qtdItens) {
		this.qtdItens = qtdItens;
	}
	public String getSequencial() {
		return sequencial;
	}
	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}
	public String getSituacaoAguaImovel() {
		return situacaoAguaImovel;
	}
	public void setSituacaoAguaImovel(String situacaoAguaImovel) {
		this.situacaoAguaImovel = situacaoAguaImovel;
	}
	public String getSituacaoEsgotoImovel() {
		return situacaoEsgotoImovel;
	}
	public void setSituacaoEsgotoImovel(String situacaoEsgotoImovel) {
		this.situacaoEsgotoImovel = situacaoEsgotoImovel;
	}
	public String getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(String valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	public String getValorDocumento() {
		return valorDocumento;
	}
	public void setValorDocumento(String valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	    return null;
	}
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	}
	public String getDataHoraEmissao() {
		return dataHoraEmissao;
	}
	public void setDataHoraEmissao(String dataHoraEmissao) {
		this.dataHoraEmissao = dataHoraEmissao;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
}
