package gcom.relatorio.micromedicao;

import gcom.relatorio.RelatorioBean;

public class RelatorioRotasOnlinePorEmpresaBean implements RelatorioBean {
	
	private Integer idSetorComercial;
	private Integer codigoSetorComercial;
	
	private Integer idRota;
	private Integer codigoRota;
	
	private Integer idLocalidade;
	private String nomeLocalidade;
	
	private Integer idFaturamentoGrupo;
	private String nomeFaturamentoGrupo;
	
	private Integer idLeiturista;
	private String nomeLeiturista;
	
	private Integer numeroQuadraMinimo;
	private Integer numeroQuadraMaximo;
	private Integer quantidadeQuadras;
	private Integer quantidadeLeituras;
	
	public RelatorioRotasOnlinePorEmpresaBean() { }

	public Integer getCodigoRota() {
		return codigoRota;
	}

	public void setCodigoRota(Integer codigoRota) {
		this.codigoRota = codigoRota;
	}

	public Integer getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	public void setCodigoSetorComercial(Integer codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	public Integer getIdFaturamentoGrupo() {
		return idFaturamentoGrupo;
	}

	public void setIdFaturamentoGrupo(Integer idFaturamentoGrupo) {
		this.idFaturamentoGrupo = idFaturamentoGrupo;
	}

	public Integer getIdLeiturista() {
		return idLeiturista;
	}

	public void setIdLeiturista(Integer idLeiturista) {
		this.idLeiturista = idLeiturista;
	}

	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	public Integer getNumeroQuadraMaximo() {
		return numeroQuadraMaximo;
	}

	public void setNumeroQuadraMaximo(Integer numeroQuadraMaximo) {
		this.numeroQuadraMaximo = numeroQuadraMaximo;
	}

	public Integer getNumeroQuadraMinimo() {
		return numeroQuadraMinimo;
	}

	public void setNumeroQuadraMinimo(Integer numeroQuadraMinimo) {
		this.numeroQuadraMinimo = numeroQuadraMinimo;
	}

	public Integer getQuantidadeQuadras() {
		return quantidadeQuadras;
	}

	public void setQuantidadeQuadras(Integer quantidadeQuadras) {
		this.quantidadeQuadras = quantidadeQuadras;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getNomeFaturamentoGrupo() {
		return nomeFaturamentoGrupo;
	}

	public void setNomeFaturamentoGrupo(String nomeFaturamentoGrupo) {
		this.nomeFaturamentoGrupo = nomeFaturamentoGrupo;
	}

	public String getNomeLeiturista() {
		return nomeLeiturista;
	}

	public void setNomeLeiturista(String nomeLeiturista) {
		this.nomeLeiturista = nomeLeiturista;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public Integer getQuantidadeLeituras() {
		return quantidadeLeituras;
	}

	public void setQuantidadeLeituras(Integer quantidadeLeituras) {
		this.quantidadeLeituras = quantidadeLeituras;
	}

	public Integer getIdRota() {
		return idRota;
	}

	public void setIdRota(Integer idRota) {
		this.idRota = idRota;
	}

}
