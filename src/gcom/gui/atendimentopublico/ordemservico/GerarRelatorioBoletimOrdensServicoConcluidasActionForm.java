package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0766] Gerar Relatorio Boletim Ordens Servico Concluidas
 * 
 * @author Ivan Sérgio
 * @date 06/05/2008
 * 
 */
public class GerarRelatorioBoletimOrdensServicoConcluidasActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idFirma;
	private String nomeFirma;
	private String idLocalidade;
	private String nomeLocalidade;
	private String idSetorComercial;
	private String codigoSetorComercial;
	private String nomeSetorComercial;	
	private String anoMesReferenciaEncerramento;
	private String situacao;
	
	//private static final String SITUACAO_NAO_FICALIZADAS_APROVADAS = "1";
	//private static final String SITUACAO_REPROVADAS = "2";
	
	public String getAnoMesReferenciaEncerramento() {
		return anoMesReferenciaEncerramento;
	}
	public void setAnoMesReferenciaEncerramento(String anoMesReferenciaEncerramento) {
		this.anoMesReferenciaEncerramento = anoMesReferenciaEncerramento;
	}
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getIdFirma() {
		return idFirma;
	}
	public void setIdFirma(String idFirma) {
		this.idFirma = idFirma;
	}
	public String getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public String getNomeFirma() {
		return nomeFirma;
	}
	public void setNomeFirma(String nomeFirma) {
		this.nomeFirma = nomeFirma;
	}
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	public String getNomeSetorComercial() {
		return nomeSetorComercial;
	}
	public void setNomeSetorComercial(String nomeSetorComercial) {
		this.nomeSetorComercial = nomeSetorComercial;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
