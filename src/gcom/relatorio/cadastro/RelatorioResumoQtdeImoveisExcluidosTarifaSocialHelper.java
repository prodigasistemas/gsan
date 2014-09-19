package gcom.relatorio.cadastro;


public class RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper {
	
	private String descricaoGerencia;
	private Integer idGerencia ;
	private String descricaoLocalidade ;
	private Integer idLocalidade ;
	private String descricaoUnidadeNegocio ;
	private Integer idUnidadeNegocio ;
    private Integer motivoExclusao;
    private Integer qtdeCartas;
    private Integer qtdeExcluidos;
    private String descricaoMotivoExclusao;
    
	public RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper() {
		super();
		
	}

	

	public RelatorioResumoQtdeImoveisExcluidosTarifaSocialHelper(String descricaoGerencia, Integer idGerencia, String descricaoLocalidade, Integer idLocalidade, String descricaoUnidadeNegocio, Integer idUnidadeNegocio, Integer motivoExclusao, Integer qtdeCartas, Integer qtdeExcluidos, String descricaoMotivoExclusao) {
		super();
		
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

	public String getDescricaoUnidadeNegocio() {
		return descricaoUnidadeNegocio;
	}

	public void setDescricaoUnidadeNegocio(String descricaoUnidadeNegocio) {
		this.descricaoUnidadeNegocio = descricaoUnidadeNegocio;
	}

	public Integer getIdGerencia() {
		return idGerencia;
	}

	public void setIdGerencia(Integer idGerencia) {
		this.idGerencia = idGerencia;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public Integer getMotivoExclusao() {
		return motivoExclusao;
	}

	public void setMotivoExclusao(Integer motivoExclusao) {
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

	public String getDescricaoMotivoExclusao() {
		return descricaoMotivoExclusao;
	}

	public void setDescricaoMotivoExclusao(String descricaoMotivoExclusao) {
		this.descricaoMotivoExclusao = descricaoMotivoExclusao;
	}
	
}
