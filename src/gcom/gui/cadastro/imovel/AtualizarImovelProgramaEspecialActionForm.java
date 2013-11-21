package gcom.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;

public class AtualizarImovelProgramaEspecialActionForm extends
		ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String matriculaImovel;
	private String inscricaoImovel;
	private String enderecoImovel;
	private String descricaoDocumentos;
	private String mesAnoSuspensaoPrograma;
	private String dataApresentacaoDocumentos;
	private String idImovelPerfil;
	private String idUsuarioResponsavel;
	private String numeroBolsaFamilia;
	
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getEnderecoImovel() {
		return enderecoImovel;
	}
	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}
	public String getDescricaoDocumentos() {
		return descricaoDocumentos;
	}
	public void setDescricaoDocumentos(String descricaoDocumentos) {
		this.descricaoDocumentos = descricaoDocumentos;
	}
	public String getMesAnoSuspensaoPrograma() {
		return mesAnoSuspensaoPrograma;
	}
	public void setMesAnoSuspensaoPrograma(String mesAnoSuspensaoPrograma) {
		this.mesAnoSuspensaoPrograma = mesAnoSuspensaoPrograma;
	}
	public String getDataApresentacaoDocumentos() {
		return dataApresentacaoDocumentos;
	}
	public void setDataApresentacaoDocumentos(String dataApresentacaoDocumentos) {
		this.dataApresentacaoDocumentos = dataApresentacaoDocumentos;
	}
	public String getIdImovelPerfil() {
		return idImovelPerfil;
	}
	public void setIdImovelPerfil(String idImovelPerfil) {
		this.idImovelPerfil = idImovelPerfil;
	}
	public String getIdUsuarioResponsavel() {
		return idUsuarioResponsavel;
	}
	public void setIdUsuarioResponsavel(String idUsuarioResponsavel) {
		this.idUsuarioResponsavel = idUsuarioResponsavel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumeroBolsaFamilia() {
		return numeroBolsaFamilia;
	}
	public void setNumeroBolsaFamilia(String numeroBolsaFamilia) {
		this.numeroBolsaFamilia = numeroBolsaFamilia;
	}
	
	

}
