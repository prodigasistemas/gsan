package gcom.gui.relatorio.faturamento;


import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.faturamento.debito.DebitoTipo;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorActionForm;

/**
 *[UC0958] - Gerar Relatorio de Juros, Multas e Debitos Cancelados.
 *
 * @author Marlon Patrick
 * @since 07/10/2009
 */
public class GerarRelatorioJurosMultasDebitosCanceladosActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String mesAnoFaturamento;

	private String dataCancelamentoInicial;	
	private String dataCancelamentoFinal;

	private String idUnidadeNegocio;	
	
	private String idLocalidade;
	private String nomeLocalidade;
	private boolean isLocalidadeExistente;
	
	private String[] idsTiposDebito;

	private String[] idsCategoria;
		
	private String[] idsPerfilImovel;	

	private String[] idsEsferaPoder;
	
	private String idUsuarioCancelamento;
	private String nomeUsuarioCancelamento;	
	private boolean isUsuarioExistente;
	
	private Collection<UnidadeNegocio> colecaoUnidadesNegocios;
	private Collection<DebitoTipo> colecaoTiposDebito;
	private Collection<Categoria> colecaoCategorias;
	private Collection<ImovelPerfil> colecaoPerfisImovel;
	private Collection<EsferaPoder> colecaoEsferasPoder;
	
	public void reset(){
		
		this.mesAnoFaturamento = "";

		this.dataCancelamentoInicial = "";		
		this.dataCancelamentoFinal = "";

		this.idUnidadeNegocio = null;

		this.idLocalidade = "";
		this.nomeLocalidade = "";
		this.isLocalidadeExistente = false;
		
		this.idsTiposDebito = null;
		this.idsCategoria = null;
		this.idsPerfilImovel = null;
		this.idsEsferaPoder = null;
		
		this.idUsuarioCancelamento = "";
		this.nomeUsuarioCancelamento = "";
		this.isUsuarioExistente = false;
		
		this.colecaoUnidadesNegocios = new ArrayList<UnidadeNegocio>();
		this.colecaoTiposDebito = new ArrayList<DebitoTipo>();
		this.colecaoCategorias = new ArrayList<Categoria>();
		this.colecaoPerfisImovel = new ArrayList<ImovelPerfil>();
		this.colecaoEsferasPoder = new ArrayList<EsferaPoder>();

	}

	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public String getDataCancelamentoInicial() {
		return dataCancelamentoInicial;
	}

	public void setDataCancelamentoInicial(String dataCancelamentoInicial) {
		this.dataCancelamentoInicial = dataCancelamentoInicial;
	}

	public String getDataCancelamentoFinal() {
		return dataCancelamentoFinal;
	}

	public void setDataCancelamentoFinal(String dataCancelamentoFinal) {
		this.dataCancelamentoFinal = dataCancelamentoFinal;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String unidadeNegocio) {
		this.idUnidadeNegocio = unidadeNegocio;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String[] getIdsTiposDebito() {
		return idsTiposDebito;
	}

	public void setIdsTiposDebito(String[] tipoDebito) {
		this.idsTiposDebito = tipoDebito;
	}

	public String[] getIdsCategoria() {
		return idsCategoria;
	}

	public void setIdsCategoria(String[] categorias) {
		this.idsCategoria = categorias;
	}

	public String[] getIdsPerfilImovel() {
		return idsPerfilImovel;
	}

	public void setIdsPerfilImovel(String[] perfisImovel) {
		this.idsPerfilImovel = perfisImovel;
	}

	public String[] getIdsEsferaPoder() {
		return idsEsferaPoder;
	}

	public void setIdsEsferaPoder(String[] esferasPoder) {
		this.idsEsferaPoder = esferasPoder;
	}

	public String getIdUsuarioCancelamento() {
		return idUsuarioCancelamento;
	}

	public void setIdUsuarioCancelamento(
			String idUsuarioResponsavelCancelamento) {
		this.idUsuarioCancelamento = idUsuarioResponsavelCancelamento;
	}

	public String getNomeUsuarioCancelamento() {
		return nomeUsuarioCancelamento;
	}

	public void setNomeUsuarioCancelamento(
			String nomeUsuarioResponsavelCancelamento) {
		this.nomeUsuarioCancelamento = nomeUsuarioResponsavelCancelamento;
	}

	public Collection<UnidadeNegocio> getColecaoUnidadesNegocios() {
		return colecaoUnidadesNegocios;
	}

	public void setColecaoUnidadesNegocios(
			Collection<UnidadeNegocio> colecaoUnidadesNegocios) {
		this.colecaoUnidadesNegocios = colecaoUnidadesNegocios;
	}

	public Collection<Categoria> getColecaoCategorias() {
		return colecaoCategorias;
	}

	public void setColecaoCategorias(Collection<Categoria> colecaoCategorias) {
		this.colecaoCategorias = colecaoCategorias;
	}

	public Collection<EsferaPoder> getColecaoEsferasPoder() {
		return colecaoEsferasPoder;
	}

	public void setColecaoEsferasPoder(Collection<EsferaPoder> colecaoEsferasPoder) {
		this.colecaoEsferasPoder = colecaoEsferasPoder;
	}

	public Collection<DebitoTipo> getColecaoTiposDebito() {
		return colecaoTiposDebito;
	}

	public void setColecaoTiposDebito(Collection<DebitoTipo> colecaoTiposDebito) {
		this.colecaoTiposDebito = colecaoTiposDebito;
	}

	public Collection<ImovelPerfil> getColecaoPerfisImovel() {
		return colecaoPerfisImovel;
	}

	public void setColecaoPerfisImovel(Collection<ImovelPerfil> colecaoPerfisImovel) {
		this.colecaoPerfisImovel = colecaoPerfisImovel;
	}

	public boolean isLocalidadeExistente() {
		return isLocalidadeExistente;
	}

	public void setLocalidadeExistente(boolean isLocalidadeExistente) {
		this.isLocalidadeExistente = isLocalidadeExistente;
	}

	public boolean isUsuarioExistente() {
		return isUsuarioExistente;
	}

	public void setUsuarioExistente(boolean isUsuarioExistente) {
		this.isUsuarioExistente = isUsuarioExistente;
	}	
}
