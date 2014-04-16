package gcom.relatorio.atendimentopublico.ordemservico;

import java.io.Serializable;
import java.util.Date;


/**
 * [UC1120] Gerar Relatório de religação de clientes inadimplentes
 * 
 * @author Hugo Leonardo
 * @date 25/01/2011
 */
public class FiltrarRelatorioReligacaoClientesInadiplentesHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer gerenciaRegional;
	private Integer unidadeNegocio;
	private Integer localidade;
	private Integer setorComercial;
	private Integer cliente;
	private Integer usuario;
	private Date dataInicioEncerramento;
	private Date dataFimEncerramento;
	private Date dataInicioRecorrencia;
	private Date dataFimRecorrencia;
	private Integer escolhaRelatorio;
	
	public Integer getCliente() {
		return cliente;
	}
	
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	
	public Date getDataFimEncerramento() {
		return dataFimEncerramento;
	}
	
	public void setDataFimEncerramento(Date dataFimEncerramento) {
		this.dataFimEncerramento = dataFimEncerramento;
	}
	
	public Date getDataFimRecorrencia() {
		return dataFimRecorrencia;
	}
	
	public void setDataFimRecorrencia(Date dataFimRecorrencia) {
		this.dataFimRecorrencia = dataFimRecorrencia;
	}
	
	public Date getDataInicioEncerramento() {
		return dataInicioEncerramento;
	}
	
	public void setDataInicioEncerramento(Date dataInicioEncerramento) {
		this.dataInicioEncerramento = dataInicioEncerramento;
	}

	public Date getDataInicioRecorrencia() {
		return dataInicioRecorrencia;
	}
	
	public void setDataInicioRecorrencia(Date dataInicioRecorrencia) {
		this.dataInicioRecorrencia = dataInicioRecorrencia;
	}
	
	public Integer getEscolhaRelatorio() {
		return escolhaRelatorio;
	}
	
	public void setEscolhaRelatorio(Integer escolhaRelatorio) {
		this.escolhaRelatorio = escolhaRelatorio;
	}
	
	public Integer getGerenciaRegional() {
		return gerenciaRegional;
	}
	
	public void setGerenciaRegional(Integer gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}
	
	public Integer getLocalidade() {
		return localidade;
	}
	
	public void setLocalidade(Integer localidade) {
		this.localidade = localidade;
	}
	
	public Integer getSetorComercial() {
		return setorComercial;
	}
	
	public void setSetorComercial(Integer setorComercial) {
		this.setorComercial = setorComercial;
	}
	
	public Integer getUnidadeNegocio() {
		return unidadeNegocio;
	}
	
	public void setUnidadeNegocio(Integer unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}
	
	public Integer getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	
}
