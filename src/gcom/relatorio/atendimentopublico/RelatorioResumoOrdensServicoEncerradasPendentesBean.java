package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * [UC0732] - Gerar Relatório Acompanhamento de Ordem de Serviço de Hidrometro
 * 			  (RelatorioResumoOrdensServicoEncerradasPendentes)
 * 
 * @author Ivan Sérgio
 * @date 12/12/2007, 31/03/2008
 * @alteracao: Novas quebra: Setor Comercial e Motivo Encerramento;
 */
public class RelatorioResumoOrdensServicoEncerradasPendentesBean implements RelatorioBean {

	private String situacao;
	private String tipoServico;
	private String periodoEncerramento;
	private String idLocalidade;
	private String nomeLocalidade;
	private String firma;
	private String nomeFirma;
	private String totalOrdensServico;
	
	private String idSetorComercial;
	private String codigoSetorComercial;
	private String idMotivoEncerramento;
	private String descricaoMotivoEncerramento;
	private String quantidadeMotivo;

	public String getFirma() {
		return firma;
	}
	public void setFirma(String firma) {
		this.firma = firma;
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
	public String getPeriodoEncerramento() {
		return periodoEncerramento;
	}
	public void setPeriodoEncerramento(String periodoEncerramento) {
		this.periodoEncerramento = periodoEncerramento;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}
	public String getTotalOrdensServico() {
		return totalOrdensServico;
	}
	public void setTotalOrdensServico(String totalOrdensServico) {
		this.totalOrdensServico = totalOrdensServico;
	}
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}
	public String getDescricaoMotivoEncerramento() {
		return descricaoMotivoEncerramento;
	}
	public void setDescricaoMotivoEncerramento(String descricaoMotivoEncerramento) {
		this.descricaoMotivoEncerramento = descricaoMotivoEncerramento;
	}
	public String getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}
	public void setIdMotivoEncerramento(String idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}
	public String getIdSetorComercial() {
		return idSetorComercial;
	}
	public void setIdSetorComercial(String idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}
	public String getQuantidadeMotivo() {
		return quantidadeMotivo;
	}
	public void setQuantidadeMotivo(String quantidadeMotivo) {
		this.quantidadeMotivo = quantidadeMotivo;
	}
	
	
	 public boolean equals(Object other) {
	        if ((this == other)) {
	            return true;
	        }
	        if (!(other instanceof RelatorioResumoOrdensServicoEncerradasPendentesBean)) {
	            return false;
	        }

	        RelatorioResumoOrdensServicoEncerradasPendentesBean castOther = (RelatorioResumoOrdensServicoEncerradasPendentesBean) other;

	        return new EqualsBuilder()
	        .append(this.getIdMotivoEncerramento(),castOther.getIdMotivoEncerramento())
	        .append(this.getIdLocalidade(),castOther.getIdLocalidade())
	        .append(this.getIdSetorComercial(),castOther.getIdSetorComercial())
	        .isEquals();
	    }
	
	
}
