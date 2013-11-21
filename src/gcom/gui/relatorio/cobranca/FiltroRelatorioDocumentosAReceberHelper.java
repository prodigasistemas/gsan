package gcom.gui.relatorio.cobranca;

import java.io.Serializable;
import java.util.Collection;

public class FiltroRelatorioDocumentosAReceberHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mesAno;
	
	private String idCategoriaTipo;	
	private String[] idsCategoria;	
	private String[] idsPerfilImovel;	
	private String[] idsEsferaPoder;
	private String idGerencia;
	private String idUnidade;
	private String idLocalidade;
	private String idOpcaoTotalizacao;
	private String descricaoEstado;
	
	/**TODO:COSANPA
	 * @author Adriana Muniz
	 * @date 29/03/2012
	 * 
	 * Acrescimo de mais um filtro na geração do relatório R0990 
	 * */
	private String indicadorGuiaPagamento;
	
	private Collection<FaixaHelper> colecaoFaixas;
	
	public FiltroRelatorioDocumentosAReceberHelper(String mesAno,
			String idCategoriaTipo, String[] idsCategoria,
			String[] idsPerfilImovel, String[] idsEsferaPoder,
			String idGerencia, String idUnidade, String idLocalidade,
			String idOpcaoTotalizacao, Collection<FaixaHelper> colecaoFaixas,String descricaoEstado) {
		this.mesAno = mesAno;
		this.idCategoriaTipo = idCategoriaTipo;
		this.idsCategoria = idsCategoria;
		this.idsPerfilImovel = idsPerfilImovel;
		this.idsEsferaPoder = idsEsferaPoder;
		this.idGerencia = idGerencia;
		this.idUnidade = idUnidade;
		this.idLocalidade = idLocalidade;
		this.idOpcaoTotalizacao = idOpcaoTotalizacao;
		this.colecaoFaixas = colecaoFaixas;
		this.descricaoEstado = descricaoEstado;
	}

	public FiltroRelatorioDocumentosAReceberHelper(String mesAno,
			String idCategoriaTipo, String[] idsCategoria,
			String[] idsPerfilImovel, String[] idsEsferaPoder,
			String idGerencia, String idUnidade, String idLocalidade,
			String idOpcaoTotalizacao, Collection<FaixaHelper> colecaoFaixas,
			String descricaoEstado, String indicadorGuiaPagamento) {
		this.mesAno = mesAno;
		this.idCategoriaTipo = idCategoriaTipo;
		this.idsCategoria = idsCategoria;
		this.idsPerfilImovel = idsPerfilImovel;
		this.idsEsferaPoder = idsEsferaPoder;
		this.idGerencia = idGerencia;
		this.idUnidade = idUnidade;
		this.idLocalidade = idLocalidade;
		this.idOpcaoTotalizacao = idOpcaoTotalizacao;
		this.colecaoFaixas = colecaoFaixas;
		this.descricaoEstado = descricaoEstado;
		this.indicadorGuiaPagamento = indicadorGuiaPagamento;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public String getIdCategoriaTipo() {
		return idCategoriaTipo;
	}

	public void setIdCategoriaTipo(String idCategoriaTipo) {
		this.idCategoriaTipo = idCategoriaTipo;
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

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdOpcaoTotalizacao() {
		return idOpcaoTotalizacao;
	}

	public void setIdOpcaoTotalizacao(String idOpcaoTotalizacao) {
		this.idOpcaoTotalizacao = idOpcaoTotalizacao;
	}

	public Collection<FaixaHelper> getColecaoFaixas() {
		return colecaoFaixas;
	}

	public void setColecaoFaixas(Collection<FaixaHelper> colecaoFaixas) {
		this.colecaoFaixas = colecaoFaixas;
	}

	public String getDescricaoEstado() {
		return descricaoEstado;
	}

	public void setDescricaoEstado(String descricaoEstado) {
		this.descricaoEstado = descricaoEstado;
	}
	
	

	public String getIndicadorGuiaPagamento() {
		return indicadorGuiaPagamento;
	}

	public void setIndicadorGuiaPagamento(String indicadorGuiaPagamento) {
		this.indicadorGuiaPagamento = indicadorGuiaPagamento;
	}
}
