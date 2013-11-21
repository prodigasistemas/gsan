package gcom.gui.relatorio.cadastro;

import java.io.Serializable;
import java.util.Date;

/**
 * [UC1170] - Gerar Relatório Acesso ao SPC
 * 
 * @author Diogo Peixoto
 * @since 09/05/2011
 * 
 */
public class FiltrarRelatorioAcessoSPCHelper implements Serializable{

	private static final long serialVersionUID = 1L;

	private Date referenciaInicial;
	private Date referenciaFinal;
	private String loginUsuarioResponsavel;
	private Integer idUnidadaOrganizacional;
	
	public FiltrarRelatorioAcessoSPCHelper(){
	}
	
	public FiltrarRelatorioAcessoSPCHelper(Date referenciaInicial, Date referenciaFinal, String loginUsuarioResponsavel, Integer idUnidade){
		this.referenciaInicial = referenciaInicial;
		this.referenciaFinal = referenciaFinal;
		this.loginUsuarioResponsavel = loginUsuarioResponsavel;
		this.idUnidadaOrganizacional = idUnidade;
	}

	public Date getReferenciaInicial() {
		return referenciaInicial;
	}

	public Date getReferenciaFinal() {
		return referenciaFinal;
	}

	public String getLoginUsuarioResponsavel() {
		return loginUsuarioResponsavel;
	}

	public Integer getIdUnidadaOrganizacional() {
		return idUnidadaOrganizacional;
	}
}