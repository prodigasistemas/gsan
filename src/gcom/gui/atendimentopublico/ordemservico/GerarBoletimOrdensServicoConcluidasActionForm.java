package gcom.gui.atendimentopublico.ordemservico;


import org.apache.struts.validator.ValidatorActionForm;

/**
 * [UC0765] Gerar Boletim Ordens Servico Concluidas
 * 
 * @author Ivan Sérgio
 * @date 18/04/2008
 * 
 */
public class GerarBoletimOrdensServicoConcluidasActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	
	private String idFirma;
	private String nomeFirma;
	private String idLocalidade;
	private String nomeLocalidade;
	private String anoMesReferenciaEncerramento;
	
	private Integer totalNaoFiscalizadas = 0;
	private Integer totalAprovadas = 0;
	private Integer totalNaoFiscalizadasAprovadas = 0;
	private Integer totalReprovadas = 0;
	private Integer totalEncerradasMesesAnterioresAprovadasMes = 0;	
	private Integer totalBoletim = 0;
	
	private Integer totalHidrometrosInstaladosMuro = 0;
	private Integer totalHidrometrosInstaladosCalcada = 0;
	private Integer totalHidrometrosInstaladosJardim = 0;
	
	private Integer totalHidrometrosSubstituidosSemTrocaCaixa = 0;
	private Integer totalHidrometrosSubstituidosComTrocaCaixaMuro = 0;
	private Integer totalHidrometrosSubstituidosComTrocaCaixaCalcada = 0;
	
	private Integer totalTrocaRegistro = 0;	
	
	public Integer getTotalAprovadas() {
		return totalAprovadas;
	}
	public void setTotalAprovadas(Integer totalAprovadas) {
		this.totalAprovadas = totalAprovadas;
	}
	public Integer getTotalBoletim() {
		return totalBoletim;
	}
	public void setTotalBoletim(Integer totalBoletim) {
		this.totalBoletim = totalBoletim;
	}
	public Integer getTotalEncerradasMesesAnterioresAprovadasMes() {
		return totalEncerradasMesesAnterioresAprovadasMes;
	}
	public void setTotalEncerradasMesesAnterioresAprovadasMes(
			Integer totalEncerradasMesesAnterioresAprovadasMes) {
		this.totalEncerradasMesesAnterioresAprovadasMes = totalEncerradasMesesAnterioresAprovadasMes;
	}
	public Integer getTotalHidrometrosInstaladosCalcada() {
		return totalHidrometrosInstaladosCalcada;
	}
	public void setTotalHidrometrosInstaladosCalcada(
			Integer totalHidrometrosInstaladosCalcada) {
		this.totalHidrometrosInstaladosCalcada = totalHidrometrosInstaladosCalcada;
	}
	public Integer getTotalHidrometrosInstaladosJardim() {
		return totalHidrometrosInstaladosJardim;
	}
	public void setTotalHidrometrosInstaladosJardim(
			Integer totalHidrometrosInstaladosJardim) {
		this.totalHidrometrosInstaladosJardim = totalHidrometrosInstaladosJardim;
	}
	public Integer getTotalHidrometrosInstaladosMuro() {
		return totalHidrometrosInstaladosMuro;
	}
	public void setTotalHidrometrosInstaladosMuro(
			Integer totalHidrometrosInstaladosMuro) {
		this.totalHidrometrosInstaladosMuro = totalHidrometrosInstaladosMuro;
	}
	public Integer getTotalHidrometrosSubstituidosComTrocaCaixaCalcada() {
		return totalHidrometrosSubstituidosComTrocaCaixaCalcada;
	}
	public void setTotalHidrometrosSubstituidosComTrocaCaixaCalcada(
			Integer totalHidrometrosSubstituidosComTrocaCaixaCalcada) {
		this.totalHidrometrosSubstituidosComTrocaCaixaCalcada = totalHidrometrosSubstituidosComTrocaCaixaCalcada;
	}
	public Integer getTotalHidrometrosSubstituidosComTrocaCaixaMuro() {
		return totalHidrometrosSubstituidosComTrocaCaixaMuro;
	}
	public void setTotalHidrometrosSubstituidosComTrocaCaixaMuro(
			Integer totalHidrometrosSubstituidosComTrocaCaixaMuro) {
		this.totalHidrometrosSubstituidosComTrocaCaixaMuro = totalHidrometrosSubstituidosComTrocaCaixaMuro;
	}
	public Integer getTotalHidrometrosSubstituidosSemTrocaCaixa() {
		return totalHidrometrosSubstituidosSemTrocaCaixa;
	}
	public void setTotalHidrometrosSubstituidosSemTrocaCaixa(
			Integer totalHidrometrosSubstituidosSemTrocaCaixa) {
		this.totalHidrometrosSubstituidosSemTrocaCaixa = totalHidrometrosSubstituidosSemTrocaCaixa;
	}
	public Integer getTotalNaoFiscalizadas() {
		return totalNaoFiscalizadas;
	}
	public void setTotalNaoFiscalizadas(Integer totalNaoFiscalizadas) {
		this.totalNaoFiscalizadas = totalNaoFiscalizadas;
	}
	public Integer getTotalTrocaRegistro() {
		return totalTrocaRegistro;
	}
	public void setTotalTrocaRegistro(Integer totalTrocaRegistro) {
		this.totalTrocaRegistro = totalTrocaRegistro;
	}
	public Integer getTotalReprovadas() {
		return totalReprovadas;
	}
	public void setTotalReprovadas(Integer totalReprovadas) {
		this.totalReprovadas = totalReprovadas;
	}
	public String getAnoMesReferenciaEncerramento() {
		return anoMesReferenciaEncerramento;
	}
	public void setAnoMesReferenciaEncerramento(String anoMesReferenciaEncerramento) {
		this.anoMesReferenciaEncerramento = anoMesReferenciaEncerramento;
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
	public Integer getTotalNaoFiscalizadasAprovadas() {
		return totalNaoFiscalizadasAprovadas;
	}
	public void setTotalNaoFiscalizadasAprovadas(
			Integer totalNaoFiscalizadasAprovadas) {
		this.totalNaoFiscalizadasAprovadas = totalNaoFiscalizadasAprovadas;
	}
}
