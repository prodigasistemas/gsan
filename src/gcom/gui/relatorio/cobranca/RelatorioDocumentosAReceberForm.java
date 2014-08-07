package gcom.gui.relatorio.cobranca;

import gcom.cadastro.cliente.EsferaPoder;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.CategoriaTipo;
import gcom.cadastro.imovel.ImovelPerfil;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.UnidadeNegocio;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

public class RelatorioDocumentosAReceberForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mesAno;
	
	private Collection<CategoriaTipo> colecaoCategoriasTipo;
	private Collection<Categoria> colecaoCategorias;
	private Collection<ImovelPerfil> colecaoPerfisImovel;
	private Collection<EsferaPoder> colecaoEsferasPoder;
	
	private String idCategoriaTipo;	
	private String[] idsCategoria;	
	private String[] idsPerfilImovel;	
	private String[] idsEsferaPoder;
	private String idGerencia;
	private String idUnidade;
	private String idOpcaoTotalizacao;
	
	private Collection<FaixaHelper> colecaoFaixas;
	private String icInformouFaixa;
	
	private Collection<OpcaoTotalizacaoHelper> colecaoOpcoesTotalizacoes;
	private Collection<UnidadeNegocio> colecaoUnidades;
	private Collection<GerenciaRegional> colecaoGerencias;
	private String idLocalidade;
	private String nomeLocalidade;
	
	/**TODO:COSANPA
	 * @author Adriana Muniz
	 * @date 29/03/2012
	 * 
	 * Acrescimo de mais um filtro na geração do relatório R0990 
	 * */
	private String indicadorGuiaPagamento;
	private String indicadorInclusaoValorSemParcelas;
	
	public String getMesAno() {
		return mesAno;
	}
	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}
	public String[] getIdsCategoria() {
		return idsCategoria;
	}
	public void setIdsCategoria(String[] idsCategoria) {
		this.idsCategoria = idsCategoria;
	}
	public String[] getIdsPerfilImovel() {
		return idsPerfilImovel;
	}
	public void setIdsPerfilImovel(String[] idsPerfilImovel) {
		this.idsPerfilImovel = idsPerfilImovel;
	}
	public String[] getIdsEsferaPoder() {
		return idsEsferaPoder;
	}
	public void setIdsEsferaPoder(String[] idsEsferaPoder) {
		this.idsEsferaPoder = idsEsferaPoder;
	}
	public Collection<CategoriaTipo> getColecaoCategoriasTipo() {
		return colecaoCategoriasTipo;
	}
	public void setColecaoCategoriasTipo(
			Collection<CategoriaTipo> colecaoCategoriasTipo) {
		this.colecaoCategoriasTipo = colecaoCategoriasTipo;
	}
	public Collection<Categoria> getColecaoCategorias() {
		return colecaoCategorias;
	}
	public void setColecaoCategorias(Collection<Categoria> colecaoCategorias) {
		this.colecaoCategorias = colecaoCategorias;
	}
	public Collection<ImovelPerfil> getColecaoPerfisImovel() {
		return colecaoPerfisImovel;
	}
	public void setColecaoPerfisImovel(Collection<ImovelPerfil> colecaoPerfisImovel) {
		this.colecaoPerfisImovel = colecaoPerfisImovel;
	}
	public Collection<EsferaPoder> getColecaoEsferasPoder() {
		return colecaoEsferasPoder;
	}
	public void setColecaoEsferasPoder(Collection<EsferaPoder> colecaoEsferasPoder) {
		this.colecaoEsferasPoder = colecaoEsferasPoder;
	}
	public Collection<UnidadeNegocio> getColecaoUnidades() {
		return colecaoUnidades;
	}
	public void setColecaoUnidades(Collection<UnidadeNegocio> colecaoUnidades) {
		this.colecaoUnidades = colecaoUnidades;
	}
	public Collection<GerenciaRegional> getColecaoGerencias() {
		return colecaoGerencias;
	}
	public void setColecaoGerencias(Collection<GerenciaRegional> colecaoGerencias) {
		this.colecaoGerencias = colecaoGerencias;
	}
	public Collection<OpcaoTotalizacaoHelper> getColecaoOpcoesTotalizacoes() {
		return colecaoOpcoesTotalizacoes;
	}
	public void setColecaoOpcoesTotalizacoes(
			Collection<OpcaoTotalizacaoHelper> colecaoOpcoesTotalizacoes) {
		this.colecaoOpcoesTotalizacoes = colecaoOpcoesTotalizacoes;
	}
	public Collection<FaixaHelper> getColecaoFaixas() {
		return colecaoFaixas;
	}
	public void setColecaoFaixas(Collection<FaixaHelper> colecaoFaixas) {
		this.colecaoFaixas = colecaoFaixas;
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
	public String getIdCategoriaTipo() {
		return idCategoriaTipo;
	}
	public void setIdCategoriaTipo(String idCategoriaTipo) {
		this.idCategoriaTipo = idCategoriaTipo;
	}
	public String getIdGerencia() {
		return idGerencia;
	}
	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}
	public String getIdUnidade() {
		return idUnidade;
	}
	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}
	public String getIdOpcaoTotalizacao() {
		return idOpcaoTotalizacao;
	}
	public void setIdOpcaoTotalizacao(String idOpcaoTotalizacao) {
		this.idOpcaoTotalizacao = idOpcaoTotalizacao;
	}
	public String getIcInformouFaixa() {
		return icInformouFaixa;
	}
	public void setIcInformouFaixa(String icInformouFaixa) {
		this.icInformouFaixa = icInformouFaixa;
	}
	public String getIndicadorGuiaPagamento() {
		return indicadorGuiaPagamento;
	}
	public void setIndicadorGuiaPagamento(String indicadorGuiaPagamento) {
		this.indicadorGuiaPagamento = indicadorGuiaPagamento;
	}
	public String getIndicadorInclusaoValorSemParcelas() {
		return indicadorInclusaoValorSemParcelas;
	}
	public void setIndicadorInclusaoValorSemParcelas(String indicadorInclusaoValorSemParcelas) {
		this.indicadorInclusaoValorSemParcelas = indicadorInclusaoValorSemParcelas;
	}
}
