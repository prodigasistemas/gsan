package gcom.gerencial.cobranca;

/** 
 *
 * @author Tiago Moreno
 * @date 20/05/2006
 */
public class ResumoCobrancaSituacaoEspecialConsultaHelper {

	private Integer idGerenciaRegional;
	private String gerenciaRegional;
	private Integer idLocalidade;
	private String localidadeDescricao;
	private Integer idSituacao;
	private String situacaoDescricao;
	private Integer idMotivo;
	private String motivoDescricao;
	private Integer periodoInicio;
	private Integer periodoFinal;
	private Integer qtParalisada;

	
	public String getGerenciaRegional() {
		return gerenciaRegional;
	}
	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	public Integer getIdLocalidade() {
		return idLocalidade;
	}
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}
	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}
	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}
	public String getMotivoDescricao() {
		return motivoDescricao;
	}
	public void setMotivoDescricao(String motivoDescricao) {
		this.motivoDescricao = motivoDescricao;
	}
	public Integer getPeriodoFinal() {
		return periodoFinal;
	}
	public void setPeriodoFinal(Integer periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	public Integer getPeriodoInicio() {
		return periodoInicio;
	}
	public void setPeriodoInicio(Integer periodoInicio) {
		this.periodoInicio = periodoInicio;
	}
	public Integer getQtParalisada() {
		return qtParalisada;
	}
	public void setQtParalisada(Integer qtParalisada) {
		this.qtParalisada = qtParalisada;
	}
	public String getSituacaoDescricao() {
		return situacaoDescricao;
	}
	public void setSituacaoDescricao(String situacaoDescricao) {
		this.situacaoDescricao = situacaoDescricao;
	}
	public Integer getIdGerenciaRegional() {
		return idGerenciaRegional;
	}
	public void setIdGerenciaRegional(Integer idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}
	public Integer getIdMotivo() {
		return idMotivo;
	}
	public void setIdMotivo(Integer idMotivo) {
		this.idMotivo = idMotivo;
	}
	public Integer getIdSituacao() {
		return idSituacao;
	}
	public void setIdSituacao(Integer idSituacao) {
		this.idSituacao = idSituacao;
	}

	public ResumoCobrancaSituacaoEspecialConsultaHelper(Integer idGerenciaRegional, String gerenciaRegional, Integer idLocalidade, String localidadeDescricao, Integer idSituacao, String situacaoDescricao, Integer idMotivo, String motivoDescricao, Integer periodoInicio, Integer periodoFinal, Integer qtParalisada) {
		super();
		
		this.idGerenciaRegional = idGerenciaRegional;
		this.gerenciaRegional = gerenciaRegional;
		this.idLocalidade = idLocalidade;
		this.localidadeDescricao = localidadeDescricao;
		this.idSituacao = idSituacao;
		this.situacaoDescricao = situacaoDescricao;
		this.idMotivo = idMotivo;
		this.motivoDescricao = motivoDescricao;
		this.periodoInicio = periodoInicio;
		this.periodoFinal = periodoFinal;
		this.qtParalisada = qtParalisada;
	}
	
}
