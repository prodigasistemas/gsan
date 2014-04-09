package gcom.relatorio.cadastro;

import gcom.relatorio.RelatorioBean;

public class RelatorioResumoQtdeImoveisExcluidosTarifaSocialBean implements RelatorioBean {

	private String descricaoGerencia;
	
	private String idGerencia ;
	
	private String descricaoLocalidade ;
	
	private String idLocalidade ;
	
	private String descricaoUnidadeNegocio ;
	
	private String idUnidadeNegocio ;
	
    private String motivoExclusao;
    
    private Integer qtdeCartas;
    
    private Integer qtdeExcluidos;
    
    private String descricaoMotivoExclusao;

	public RelatorioResumoQtdeImoveisExcluidosTarifaSocialBean(String descricaoGerencia, String idGerencia, String descricaoLocalidade, String idLocalidade, String descricaoUnidadeNegocio, String idUnidadeNegocio, Integer qtdeExcluidos) {
		super();
		// TODO Auto-generated constructor stub
		this.descricaoGerencia = descricaoGerencia;
		this.idGerencia = idGerencia;
		this.descricaoLocalidade = descricaoLocalidade;
		this.idLocalidade = idLocalidade;
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.qtdeExcluidos = qtdeExcluidos;
	}

	public RelatorioResumoQtdeImoveisExcluidosTarifaSocialBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RelatorioResumoQtdeImoveisExcluidosTarifaSocialBean(String descricaoGerencia, String idGerencia, String descricaoLocalidade, String idLocalidade, String descricaoUnidadeNegocio, String idUnidadeNegocio, String motivoExclusao, Integer qtdeCartas, Integer qtdeExcluidos, String descricaoMotivoExclusao) {
		super();
		// TODO Auto-generated constructor stub
		this.descricaoGerencia = descricaoGerencia;
		this.idGerencia = idGerencia;
		this.descricaoLocalidade = descricaoLocalidade;
		this.idLocalidade = idLocalidade;
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.motivoExclusao = motivoExclusao;
		this.qtdeCartas = qtdeCartas;
		this.qtdeExcluidos = qtdeExcluidos;
		this.descricaoMotivoExclusao = descricaoMotivoExclusao;
	}

	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}

	public void setDescricaoGerencia(String descricaoGerencia) {
		this.descricaoGerencia = descricaoGerencia;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getDescricaoMotivoExclusao() {
		return descricaoMotivoExclusao;
	}

	public void setDescricaoMotivoExclusao(String descricaoMotivoExclusao) {
		this.descricaoMotivoExclusao = descricaoMotivoExclusao;
	}

	public String getDescricaoUnidadeNegocio() {
		return descricaoUnidadeNegocio;
	}

	public void setDescricaoUnidadeNegocio(String descricaoUnidadeNegocio) {
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
	}

	public String getIdGerencia() {
		return idGerencia;
	}

	public void setIdGerencia(String idGerencia) {
		this.idGerencia = idGerencia;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(String idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getMotivoExclusao() {
		return motivoExclusao;
	}

	public void setMotivoExclusao(String motivoExclusao) {
		this.motivoExclusao = motivoExclusao;
	}

	public Integer getQtdeCartas() {
		return qtdeCartas;
	}

	public void setQtdeCartas(Integer qtdeCartas) {
		this.qtdeCartas = qtdeCartas;
	}

	public Integer getQtdeExcluidos() {
		return qtdeExcluidos;
	}

	public void setQtdeExcluidos(Integer qtdeExcluidos) {
		this.qtdeExcluidos = qtdeExcluidos;
	}


}
