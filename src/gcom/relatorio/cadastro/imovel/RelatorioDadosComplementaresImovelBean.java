package gcom.relatorio.cadastro.imovel;

import gcom.relatorio.RelatorioBean;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioDadosComplementaresImovelBean implements RelatorioBean {
	
	private String matriculaImovel;

	private String inscricaoImovel;

	private String situacaoAguaImovel;

	private String situacaoEsgotoImovel;

	private String tarifaConsumo;

	private String quantidadeRetificacoes;

	private String quantidadeParcelamento;

	private String quantidadeReparcelamento;

	private String quantidadeReparcelamentosConsecutivos;

	private String situacaoCobranca;

	private String funcionarioResponsavel;

	private String informacoesComplementares;
	
	private JRBeanCollectionDataSource colecaoVencimentosAlternativos;

	private JRBeanCollectionDataSource colecaoDebitosAutomaticos;

	private JRBeanCollectionDataSource colecaoOcorrenciaCadastro;

	private JRBeanCollectionDataSource colecaoAnormalidadesLocalidadePolo;

	private JRBeanCollectionDataSource colecaoSituacoesEspeciaisFaturamento;

	private JRBeanCollectionDataSource colecaoSituacoesEspeciaisCobranca;

	private JRBeanCollectionDataSource colecaoRamoAtividade;
	
	private JRBeanCollectionDataSource colecaoSituacoesCobranca;

	public RelatorioDadosComplementaresImovelBean() {
	}

	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	public void setInscricaoImovel(String id) {
		this.inscricaoImovel = id;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String codigo) {
		this.matriculaImovel = codigo;
	}

	public String getSituacaoAguaImovel() {
		return situacaoAguaImovel;
	}

	public void setSituacaoAguaImovel(String codAgencia) {
		this.situacaoAguaImovel = codAgencia;
	}

	public String getSituacaoEsgotoImovel() {
		return situacaoEsgotoImovel;
	}

	public void setSituacaoEsgotoImovel(String nomeBanco) {
		this.situacaoEsgotoImovel = nomeBanco;
	}

	public String getTarifaConsumo() {
		return tarifaConsumo;
	}

	public void setTarifaConsumo(String perfilImovel) {
		this.tarifaConsumo = perfilImovel;
	}

	public String getQuantidadeRetificacoes() {
		return quantidadeRetificacoes;
	}

	public void setQuantidadeRetificacoes(String tipoDespejo) {
		this.quantidadeRetificacoes = tipoDespejo;
	}

	public String getQuantidadeParcelamento() {
		return quantidadeParcelamento;
	}

	public void setQuantidadeParcelamento(String areaConstruida) {
		this.quantidadeParcelamento = areaConstruida;
	}

	public String getQuantidadeReparcelamento() {
		return quantidadeReparcelamento;
	}

	public void setQuantidadeReparcelamento(String testadaLote) {
		this.quantidadeReparcelamento = testadaLote;
	}

	public String getQuantidadeReparcelamentosConsecutivos() {
		return quantidadeReparcelamentosConsecutivos;
	}

	public void setQuantidadeReparcelamentosConsecutivos(String volumeInferiorReservatorio) {
		this.quantidadeReparcelamentosConsecutivos = volumeInferiorReservatorio;
	}

	public String getSituacaoCobranca() {
		return situacaoCobranca;
	}

	public void setSituacaoCobranca(String volumeSuperiorReservatorio) {
		this.situacaoCobranca = volumeSuperiorReservatorio;
	}

	public String getFuncionarioResponsavel() {
		return funcionarioResponsavel;
	}

	public void setFuncionarioResponsavel(String volumePiscina) {
		this.funcionarioResponsavel = volumePiscina;
	}

	public String getInformacoesComplementares() {
		return informacoesComplementares;
	}

	public void setInformacoesComplementares(String fonteAbastecimento) {
		this.informacoesComplementares = fonteAbastecimento;
	}

	public JRBeanCollectionDataSource getColecaoVencimentosAlternativos() {
		return colecaoVencimentosAlternativos;
	}

	public void setColecaoVencimentosAlternativos(
			JRBeanCollectionDataSource colecaoClienteImovelHelper) {
		this.colecaoVencimentosAlternativos = colecaoClienteImovelHelper;
	}

	public JRBeanCollectionDataSource getColecaoOcorrenciaCadastro() {
		return colecaoOcorrenciaCadastro;
	}

	public void setColecaoOcorrenciaCadastro(
			JRBeanCollectionDataSource colecaoOcorrenciaCadastro) {
		this.colecaoOcorrenciaCadastro = colecaoOcorrenciaCadastro;
	}

	public JRBeanCollectionDataSource getColecaoAnormalidadesLocalidadePolo() {
		return colecaoAnormalidadesLocalidadePolo;
	}

	public void setColecaoAnormalidadesLocalidadePolo(
			JRBeanCollectionDataSource colecaoAnormalidadesLocalidadePolo) {
		this.colecaoAnormalidadesLocalidadePolo = colecaoAnormalidadesLocalidadePolo;
	}

	public JRBeanCollectionDataSource getColecaoSituacoesEspeciaisFaturamento() {
		return colecaoSituacoesEspeciaisFaturamento;
	}

	public void setColecaoSituacoesEspeciaisFaturamento(
			JRBeanCollectionDataSource colecaoSituacoesEspeciaisFaturamento) {
		this.colecaoSituacoesEspeciaisFaturamento = colecaoSituacoesEspeciaisFaturamento;
	}

	public JRBeanCollectionDataSource getColecaoSituacoesEspeciaisCobranca() {
		return colecaoSituacoesEspeciaisCobranca;
	}

	public void setColecaoSituacoesEspeciaisCobranca(
			JRBeanCollectionDataSource colecaoSituacoesEspeciaisCobranca) {
		this.colecaoSituacoesEspeciaisCobranca = colecaoSituacoesEspeciaisCobranca;
	}
	
	public JRBeanCollectionDataSource getColecaoDebitosAutomaticos() {
		return colecaoDebitosAutomaticos;
	}

	public void setColecaoDebitosAutomaticos(
			JRBeanCollectionDataSource coelcaoClienteImovelEconomia) {
		
		this.colecaoDebitosAutomaticos = coelcaoClienteImovelEconomia;
	}

	public JRBeanCollectionDataSource getColecaoRamoAtividade() {
		return colecaoRamoAtividade;
	}

	public void setColecaoRamoAtividade(
			JRBeanCollectionDataSource colecaoRamoAtividade) {
		this.colecaoRamoAtividade = colecaoRamoAtividade;
	}

	public JRBeanCollectionDataSource getColecaoSituacoesCobranca() {
		return colecaoSituacoesCobranca;
	}

	public void setColecaoSituacoesCobranca(
			JRBeanCollectionDataSource colecaoSituacoesCobranca) {
		this.colecaoSituacoesCobranca = colecaoSituacoesCobranca;
	}

}
