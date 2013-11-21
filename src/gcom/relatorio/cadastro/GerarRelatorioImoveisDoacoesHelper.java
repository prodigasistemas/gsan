package gcom.relatorio.cadastro;

import java.util.Date;

/**
 *  [UC1174] Gerar Relatório Imoveis Com Doacoes.
 * 
 * @author Erivan Sousa
 * @date 13/06/2011, 16/06/2011
 */
public class GerarRelatorioImoveisDoacoesHelper {
	
	private Integer idEntidade;
	private Date dataAdesaoInicio;
	private Date dataAdesaoFinal;
	private Date dataCancelamentoInicio;
	private Date dataCancelamentoFinal;
	private Integer idUsuarioAdesao;
	private Integer idUsuarioCancelamento;
	private Integer refInicioDoacaoInicio;
	private Integer refInicioDoacaoFinal;
	private Integer refFimDoacaoInicio;
	private Integer refFimDoacaoFinal;
	private String loginUsuarioAdesao;
	private String loginUsuarioCancelamento;
	
	public GerarRelatorioImoveisDoacoesHelper(){
		
	}
	
	public Date getDataAdesaoFinal() {
		return dataAdesaoFinal;
	}
	public void setDataAdesaoFinal(Date dataAdesaoFinal) {
		this.dataAdesaoFinal = dataAdesaoFinal;
	}
	public Date getDataAdesaoInicio() {
		return dataAdesaoInicio;
	}
	public void setDataAdesaoInicio(Date dataAdesaoInicio) {
		this.dataAdesaoInicio = dataAdesaoInicio;
	}
	public Date getDataCancelamentoFinal() {
		return dataCancelamentoFinal;
	}
	public void setDataCancelamentoFinal(Date dataCancelamentoFinal) {
		this.dataCancelamentoFinal = dataCancelamentoFinal;
	}
	public Date getDataCancelamentoInicio() {
		return dataCancelamentoInicio;
	}
	public void setDataCancelamentoInicio(Date dataCancelamentoInicio) {
		this.dataCancelamentoInicio = dataCancelamentoInicio;
	}
	public Integer getIdEntidade() {
		return idEntidade;
	}
	public void setIdEntidade(Integer idEntidade) {
		this.idEntidade = idEntidade;
	}
	public Integer getIdUsuarioAdesao() {
		return idUsuarioAdesao;
	}
	public void setIdUsuarioAdesao(Integer idUsuarioAdesao) {
		this.idUsuarioAdesao = idUsuarioAdesao;
	}
	public Integer getIdUsuarioCancelamento() {
		return idUsuarioCancelamento;
	}
	public void setIdUsuarioCancelamento(Integer idUsuarioCancelamento) {
		this.idUsuarioCancelamento = idUsuarioCancelamento;
	}
	public Integer getRefFimDoacaoFinal() {
		return refFimDoacaoFinal;
	}
	public void setRefFimDoacaoFinal(Integer refFimDoacaoFinal) {
		this.refFimDoacaoFinal = refFimDoacaoFinal;
	}
	public Integer getRefFimDoacaoInicio() {
		return refFimDoacaoInicio;
	}
	public void setRefFimDoacaoInicio(Integer refFimDoacaoInicio) {
		this.refFimDoacaoInicio = refFimDoacaoInicio;
	}
	public Integer getRefInicioDoacaoFinal() {
		return refInicioDoacaoFinal;
	}
	public void setRefInicioDoacaoFinal(Integer refInicioDoacaoFinal) {
		this.refInicioDoacaoFinal = refInicioDoacaoFinal;
	}
	public Integer getRefInicioDoacaoInicio() {
		return refInicioDoacaoInicio;
	}
	public void setRefInicioDoacaoInicio(Integer refInicioDoacaoInicio) {
		this.refInicioDoacaoInicio = refInicioDoacaoInicio;
	}

	public String getLoginUsuarioAdesao() {
		return loginUsuarioAdesao;
	}

	public void setLoginUsuarioAdesao(String loginUsuarioAdesao) {
		this.loginUsuarioAdesao = loginUsuarioAdesao;
	}

	public String getLoginUsuarioCancelamento() {
		return loginUsuarioCancelamento;
	}

	public void setLoginUsuarioCancelamento(String loginUsuarioCancelamento) {
		this.loginUsuarioCancelamento = loginUsuarioCancelamento;
	}
		
}