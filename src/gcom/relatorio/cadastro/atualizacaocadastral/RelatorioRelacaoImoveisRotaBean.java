package gcom.relatorio.cadastro.atualizacaocadastral;

import gcom.relatorio.RelatorioBean;
import gcom.util.Util;

public class RelatorioRelacaoImoveisRotaBean implements RelatorioBean {
	
	private String idLocalidade;
	private String nomeLocalidade;
	private String codigoSetorComercial;
	private String numQuadra;
	private String numLote;
	private String numSubLote;
	private String idImovel;
	private String descCategorias;
	private String descSituacaoLigacaoAgua;
	private String descSituacaoImovelRecadastramento;
	
	public RelatorioRelacaoImoveisRotaBean() {
		
	}
	
	public String getLocalizador() {
		return Util.adicionarZerosEsquedaNumero(3, this.idLocalidade) + "." 
			 + Util.adicionarZerosEsquedaNumero(3, this.codigoSetorComercial) + "." 
			 + this.numQuadra + "." 
			 + Util.adicionarZerosEsquedaNumero(4, this.numLote) + "." 
			 + Util.adicionarZerosEsquedaNumero(3, this.numSubLote);
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public void setNumQuadra(String numQuadra) {
		this.numQuadra = numQuadra;
	}

	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}

	public void setNumSubLote(String numSubLote) {
		this.numSubLote = numSubLote;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getDescCategorias() {
		return descCategorias;
	}

	public void setDescCategorias(String descCategorias) {
		this.descCategorias = descCategorias;
	}

	public String getDescSituacaoLigacaoAgua() {
		return descSituacaoLigacaoAgua;
	}

	public void setDescSituacaoLigacaoAgua(String descSituacaoLigacaoAgua) {
		this.descSituacaoLigacaoAgua = descSituacaoLigacaoAgua;
	}

	public String getDescSituacaoImovelRecadastramento() {
		return descSituacaoImovelRecadastramento;
	}

	public void setDescSituacaoImovelRecadastramento(String descSituacaoImovelRecadastramento) {
		this.descSituacaoImovelRecadastramento = descSituacaoImovelRecadastramento;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}
	
}
