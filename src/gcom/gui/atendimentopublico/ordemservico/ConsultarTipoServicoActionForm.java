package gcom.gui.atendimentopublico.ordemservico;


import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Form Bean do Pesquisar Equipe
 * 
 * @author Leandro Cavalcanti
 * @date 08/08/2006
 */
public class ConsultarTipoServicoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	private String idTipoServico;
	private String dsTipoServico;
	private String dsAbreviadaTipoServico;
    private String subgrupoTipoServico;
    private String indicadorPavimento;
    private String valorServico;
    private String indicadorAtualizacaoComercio;
    private String indicadorServicoTerceirizado;
    private String codigoServico;
    private String tempoMedioExecucaoInicial;
    private String tempoMedioExecucaoFinal;
    private String tipoDebito;
    private String tipoCredito;
    private String prioridadeServico;
    private String perfilServco;
    private String tipoServicoReferencia;
    private String atividadesTipoServico;
    private String ordemExecucao;
    private String tipoServicoMaterial;
    private String qtdPadrao;
    private Collection arrayAtividade = new ArrayList();
    private Collection arrayMateriais = new ArrayList();

    // Controle
    private String mostrarPavimento;
    private String mostrarComercial;
    private String mostrarTerceirizado;
    private String mostrarServico;
	
    public String getAtividadesTipoServico() {
		return atividadesTipoServico;
	}
	public void setAtividadesTipoServico(String atividadesTipoServico) {
		this.atividadesTipoServico = atividadesTipoServico;
	}
	public String getCodigoServico() {
		return codigoServico;
	}
	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}
	public String getDsAbreviadaTipoServico() {
		return dsAbreviadaTipoServico;
	}
	public void setDsAbreviadaTipoServico(String dsAbreviadaTipoServico) {
		this.dsAbreviadaTipoServico = dsAbreviadaTipoServico;
	}
	public String getDsTipoServico() {
		return dsTipoServico;
	}
	public void setDsTipoServico(String dsTipoServico) {
		this.dsTipoServico = dsTipoServico;
	}
	public String getIndicadorAtualizacaoComercio() {
		return indicadorAtualizacaoComercio;
	}
	public void setIndicadorAtualizacaoComercio(String indicadorAtualizacaoComercio) {
		this.indicadorAtualizacaoComercio = indicadorAtualizacaoComercio;
	}
	public String getIndicadorPavimento() {
		return indicadorPavimento;
	}
	public void setIndicadorPavimento(String indicadorPavimento) {
		this.indicadorPavimento = indicadorPavimento;
	}
	public String getIndicadorServicoTerceirizado() {
		return indicadorServicoTerceirizado;
	}
	public void setIndicadorServicoTerceirizado(String indicadorServicoTerceirizado) {
		this.indicadorServicoTerceirizado = indicadorServicoTerceirizado;
	}
	public String getMostrarComercial() {
		return mostrarComercial;
	}
	public void setMostrarComercial(String mostrarComercial) {
		this.mostrarComercial = mostrarComercial;
	}
	public String getMostrarPavimento() {
		return mostrarPavimento;
	}
	public void setMostrarPavimento(String mostrarPavimento) {
		this.mostrarPavimento = mostrarPavimento;
	}
	public String getMostrarServico() {
		return mostrarServico;
	}
	public void setMostrarServico(String mostrarServico) {
		this.mostrarServico = mostrarServico;
	}
	public String getMostrarTerceirizado() {
		return mostrarTerceirizado;
	}
	public void setMostrarTerceirizado(String mostrarTerceirizado) {
		this.mostrarTerceirizado = mostrarTerceirizado;
	}
	public String getPerfilServco() {
		return perfilServco;
	}
	public void setPerfilServco(String perfilServco) {
		this.perfilServco = perfilServco;
	}
	public String getPrioridadeServico() {
		return prioridadeServico;
	}
	public void setPrioridadeServico(String prioridadeServico) {
		this.prioridadeServico = prioridadeServico;
	}
	public String getSubgrupoTipoServico() {
		return subgrupoTipoServico;
	}
	public void setSubgrupoTipoServico(String subgrupoTipoServico) {
		this.subgrupoTipoServico = subgrupoTipoServico;
	}
	public String getTempoMedioExecucaoInicial() {
		return tempoMedioExecucaoInicial;
	}
	public void setTempoMedioExecucaoInicial(String tempoMedioExecucaoInicial) {
		this.tempoMedioExecucaoInicial = tempoMedioExecucaoInicial;
	}
	public String getTipoCredito() {
		return tipoCredito;
	}
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}
	public String getTipoDebito() {
		return tipoDebito;
	}
	public void setTipoDebito(String tipoDebito) {
		this.tipoDebito = tipoDebito;
	}
	public String getTipoServicoMaterial() {
		return tipoServicoMaterial;
	}
	public void setTipoServicoMaterial(String tipoServicoMaterial) {
		this.tipoServicoMaterial = tipoServicoMaterial;
	}
	public String getTipoServicoReferencia() {
		return tipoServicoReferencia;
	}
	public void setTipoServicoReferencia(String tipoServicoReferencia) {
		this.tipoServicoReferencia = tipoServicoReferencia;
	}
	public String getValorServico() {
		return valorServico;
	}
	public void setValorServico(String valorServico) {
		this.valorServico = valorServico;
	}
	public String getIdTipoServico() {
		return idTipoServico;
	}
	public void setIdTipoServico(String idTipoServico) {
		this.idTipoServico = idTipoServico;
	}
	public String getTempoMedioExecucaoFinal() {
		return tempoMedioExecucaoFinal;
	}
	public void setTempoMedioExecucaoFinal(String tempoMedioExecucaoFinal) {
		this.tempoMedioExecucaoFinal = tempoMedioExecucaoFinal;
	}
	public String getOrdemExecucao() {
		return ordemExecucao;
	}
	public void setOrdemExecucao(String ordemExecucao) {
		this.ordemExecucao = ordemExecucao;
	}
	public String getQtdPadrao() {
		return qtdPadrao;
	}
	public void setQtdPadrao(String qtdPadrao) {
		this.qtdPadrao = qtdPadrao;
	}
	public Collection getArrayAtividade() {
		return arrayAtividade;
	}
	public void setArrayAtividade(Collection arrayAtividade) {
		this.arrayAtividade = arrayAtividade;
	}
	public Collection getArrayMateriais() {
		return arrayMateriais;
	}
	public void setArrayMateriais(Collection arrayMateriais) {
		this.arrayMateriais = arrayMateriais;
	}
	
    
    
	
   
}
