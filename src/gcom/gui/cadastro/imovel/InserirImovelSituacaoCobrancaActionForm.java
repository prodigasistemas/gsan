package gcom.gui.cadastro.imovel;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Administrador
 */
public class InserirImovelSituacaoCobrancaActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	private String codigoImovel;
	private String matriculaImovel;
	private String situacaoCobranca;
	private String dataImplantacao;
	private String idEscritorio;
	private String nomeEscritorio;
	private String idAdvogado;
	private String nomeAdvogado;
	private String idClienteAlvo;
	private String nomeClienteAlvo;
	private String anoMesReferenciaInicio;
	private String anoMesReferenciaFim;
	
	public String getAnoMesReferenciaFim() {
		return anoMesReferenciaFim;
	}
	public void setAnoMesReferenciaFim(String anoMesReferenciaFim) {
		this.anoMesReferenciaFim = anoMesReferenciaFim;
	}
	public String getAnoMesReferenciaInicio() {
		return anoMesReferenciaInicio;
	}
	public void setAnoMesReferenciaInicio(String anoMesReferenciaInicio) {
		this.anoMesReferenciaInicio = anoMesReferenciaInicio;
	}
	public String getCodigoImovel() {
		return codigoImovel;
	}
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}
	public String getDataImplantacao() {
		return dataImplantacao;
	}
	public void setDataImplantacao(String dataImplantacao) {
		this.dataImplantacao = dataImplantacao;
	}
	public String getIdClienteAlvo() {
		return idClienteAlvo;
	}
	public void setIdClienteAlvo(String idClienteAlvo) {
		this.idClienteAlvo = idClienteAlvo;
	}

	public String getSituacaoCobranca() {
		return situacaoCobranca;
	}
	public void setSituacaoCobranca(String situacaoCobranca) {
		this.situacaoCobranca = situacaoCobranca;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getNomeClienteAlvo() {
		return nomeClienteAlvo;
	}
	public void setNomeClienteAlvo(String nomeClienteAlvo) {
		this.nomeClienteAlvo = nomeClienteAlvo;
	}
	public String getIdAdvogado() {
		return idAdvogado;
	}
	public void setIdAdvogado(String idAdvogado) {
		this.idAdvogado = idAdvogado;
	}
	public String getIdEscritorio() {
		return idEscritorio;
	}
	public void setIdEscritorio(String idEscritorio) {
		this.idEscritorio = idEscritorio;
	}
	public String getNomeAdvogado() {
		return nomeAdvogado;
	}
	public void setNomeAdvogado(String nomeAdvogado) {
		this.nomeAdvogado = nomeAdvogado;
	}
	public String getNomeEscritorio() {
		return nomeEscritorio;
	}
	public void setNomeEscritorio(String nomeEscritorio) {
		this.nomeEscritorio = nomeEscritorio;
	}

}
