package gcom.gui.faturamento;

import gcom.gui.micromedicao.DadosMovimentacao;

import java.util.Vector;

import org.apache.struts.action.ActionForm;

public class FaturamentoSeletivoActionForm extends ActionForm  {

	private static final long serialVersionUID = 7793346732600789615L;
	private String rota;
	private String descricaoRota;
	private Vector<DadosMovimentacao> dados;
	private Integer indice;
	private Integer total;
	private String[] leituras;
	private String[] anormalidades;
	private String[] datas;
	private String idLocalidade;
	private String codigoSetorComercial;
	private String localidadeDescricao;
	private String setorComercialDescricao;
	private String bloquearCampos;
	private String tipo;
	private String matriculaImovel;
	private String[] idImoveisSelecionados;
	
	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String[] getAnormalidades() {
		return anormalidades;
	}

	public void setAnormalidades(String[] anormalidades) {
		this.anormalidades = anormalidades;
	}

	public String[] getDatas() {
		return datas;
	}

	public void setDatas(String[] datas) {
		this.datas = datas;
	}

	public String[] getLeituras() {
		return leituras;
	}

	public void setLeituras(String[] leituras) {
		this.leituras = leituras;
	}

	public Vector<DadosMovimentacao> getDados() {
		return dados;
	}

	public void setDados(Vector<DadosMovimentacao> dados) {
		this.dados = dados;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getDescricaoRota() {
		return descricaoRota;
	}

	public void setDescricaoRota(String descricaoRota) {
		this.descricaoRota = descricaoRota;
	}

	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getLocalidadeDescricao() {
		return localidadeDescricao;
	}

	public void setLocalidadeDescricao(String localidadeDescricao) {
		this.localidadeDescricao = localidadeDescricao;
	}

	public String getSetorComercialDescricao() {
		return setorComercialDescricao;
	}

	public void setSetorComercialDescricao(String setorComercialDescricao) {
		this.setorComercialDescricao = setorComercialDescricao;
	}

	public String getBloquearCampos() {
		return bloquearCampos;
	}

	public void setBloquearCampos(String bloquearCampos) {
		this.bloquearCampos = bloquearCampos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMatriculaImovel() {
		return matriculaImovel;
	}

	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}

	public String[] getIdImoveisSelecionados() {
		return idImoveisSelecionados;
	}

	public void setIdImoveisSelecionados(String[] idImoveisSelecionados) {
		this.idImoveisSelecionados = idImoveisSelecionados;
	}
	
}
