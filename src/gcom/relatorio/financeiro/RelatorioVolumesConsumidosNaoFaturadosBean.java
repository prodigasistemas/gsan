package gcom.relatorio.financeiro;

import java.math.BigDecimal;

import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.localidade.GerenciaRegional;
import gcom.cadastro.localidade.Localidade;
import gcom.cadastro.localidade.UnidadeNegocio;
import gcom.relatorio.RelatorioBean;

public class RelatorioVolumesConsumidosNaoFaturadosBean implements RelatorioBean {
	
	private Boolean totalEstado;
	private GerenciaRegional gerencia;
	private UnidadeNegocio unidade;
	private Localidade localidade;
	private Categoria categoria;
	private Municipio municipio;
	
	private BigDecimal valorDeAgua;
	private BigDecimal valorDeEsgoto;
	private BigDecimal valorTotal;
	
	private String descricaoGerencia;
	private String descricaoUnidade;
	private String descricaoLocalidade;
	private String descricaoCategoria;
	private String descricaoMunicipio;
	private String titulo;

	public RelatorioVolumesConsumidosNaoFaturadosBean() {
		this.totalEstado = false;
		resetValues();
	}
	
	public RelatorioVolumesConsumidosNaoFaturadosBean(String descricaoCategoria) {
		this();
		this.descricaoCategoria = descricaoCategoria;
	}
	
	public void resetValues() {
		valorDeAgua = new BigDecimal(0.0);
		valorDeEsgoto = new BigDecimal(0.0);
		valorTotal = new BigDecimal(0.0);
	}
	
	public void sum(RelatorioVolumesConsumidosNaoFaturadosBean other) {
		this.valorDeAgua = valorDeAgua.add(other.valorDeAgua);
		this.valorDeEsgoto = valorDeEsgoto.add(other.valorDeEsgoto);
		this.valorTotal = valorTotal.add(other.valorTotal);
	}
	
	public void copyEntidades(RelatorioVolumesConsumidosNaoFaturadosBean other) {
		this.totalEstado = other.totalEstado;
		this.setGerencia(other.gerencia);
		this.setUnidade(other.unidade);
	}
	
	public RelatorioVolumesConsumidosNaoFaturadosBean copy() {
		RelatorioVolumesConsumidosNaoFaturadosBean copy =  new RelatorioVolumesConsumidosNaoFaturadosBean();
		
		copy.totalEstado = this.totalEstado;
		copy.gerencia = this.gerencia;
		copy.unidade = this.unidade;
		copy.localidade = this.localidade;
		copy.categoria = this.categoria;
		
		copy.valorDeAgua = this.valorDeAgua.add(new BigDecimal(0.0));
		copy.valorDeEsgoto = this.valorDeEsgoto.add(new BigDecimal(0.0));
		copy.valorTotal = this.valorTotal.add(new BigDecimal(0.0));
		
		copy.descricaoCategoria = this.descricaoCategoria;
		copy.descricaoGerencia = this.descricaoGerencia;
		copy.descricaoLocalidade = this.descricaoLocalidade;
		copy.descricaoUnidade = this.descricaoUnidade;
		
		return copy;
	}

	/**
	 * @return Retorna o campo categoria.
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria O categoria a ser setado.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
		if (categoria == null) {
			descricaoCategoria = "";
		} else {
			descricaoCategoria = categoria.getDescricaoAbreviada();
		}
	}

	/**
	 * @return Retorna o campo descricaoCategoria.
	 */
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	/**
	 * @param descricaoCategoria O descricaoCategoria a ser setado.
	 */
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	/**
	 * @return Retorna o campo descricaoGerencia.
	 */
	public String getDescricaoGerencia() {
		return descricaoGerencia;
	}

	/**
	 * @param descricaoGerencia O descricaoGerencia a ser setado.
	 */
	public void setDescricaoGerencia(String descricaoGerencia) {
		this.descricaoGerencia = descricaoGerencia;
	}

	/**
	 * @return Retorna o campo descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	/**
	 * @param descricaoLocalidade O descricaoLocalidade a ser setado.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	/**
	 * @return Retorna o campo descricaoUnidade.
	 */
	public String getDescricaoUnidade() {
		return descricaoUnidade;
	}

	/**
	 * @param descricaoUnidade O descricaoUnidade a ser setado.
	 */
	public void setDescricaoUnidade(String descricaoUnidade) {
		this.descricaoUnidade = descricaoUnidade;
	}

	/**
	 * @return Retorna o campo gerencia.
	 */
	public GerenciaRegional getGerencia() {
		return gerencia;
	}

	/**
	 * @param gerencia O gerencia a ser setado.
	 */
	public void setGerencia(GerenciaRegional gerencia) {
		this.gerencia = gerencia;
		if (gerencia == null) {
			descricaoGerencia = "";
		} else {
			descricaoGerencia = gerencia.getId() + 
				"-" + gerencia.getNomeAbreviado() + "-" + gerencia.getNome();
		}
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public Localidade getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
		if (localidade == null) {
			descricaoLocalidade = "";
		} else {
			descricaoLocalidade = localidade.getId() + "-" + localidade.getDescricao();
		}
	}

	/**
	 * @return Retorna o campo totalEstado.
	 */
	public Boolean getTotalEstado() {
		return totalEstado;
	}

	/**
	 * @param totalEstado O totalEstado a ser setado.
	 */
	public void setTotalEstado(Boolean totalEstado) {
		this.totalEstado = totalEstado;
	}

	/**
	 * @return Retorna o campo unidade.
	 */
	public UnidadeNegocio getUnidade() {
		return unidade;
	}

	/**
	 * @param unidade O unidade a ser setado.
	 */
	public void setUnidade(UnidadeNegocio unidade) {
		this.unidade = unidade;
		if (unidade == null) {
			descricaoUnidade = "";
		} else {
			descricaoUnidade = unidade.getId() + "-" + 
				unidade.getNomeAbreviado().trim() + "-" + unidade.getNome();
		}
	}

	/**
	 * @return Retorna o campo valorDeAgua.
	 */
	public BigDecimal getValorDeAgua() {
		return valorDeAgua;
	}

	/**
	 * @param valorDeAgua O valorDeAgua a ser setado.
	 */
	public void setValorDeAgua(BigDecimal valorDeAgua) {
		this.valorDeAgua = valorDeAgua;
	}

	/**
	 * @return Retorna o campo valorDeEsgoto.
	 */
	public BigDecimal getValorDeEsgoto() {
		return valorDeEsgoto;
	}

	/**
	 * @param valorDeEsgoto O valorDeEsgoto a ser setado.
	 */
	public void setValorDeEsgoto(BigDecimal valorDeEsgoto) {
		this.valorDeEsgoto = valorDeEsgoto;
	}

	/**
	 * @return Retorna o campo valorTotal.
	 */
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	/**
	 * @param valorTotal O valorTotal a ser setado.
	 */
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	/**
	 * @return Retorna o campo titulo.
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo O titulo a ser setado.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String toString() {
		return getDescricaoGerencia() + " => " +
			getDescricaoUnidade() + " => " + 
			getDescricaoLocalidade() + " => " + 
			getDescricaoCategoria() + " :: " + 
			getValorDeAgua() + " , " + getValorDeEsgoto() + " , " + getValorTotal();
	}

	public String getDescricaoMunicipio() {
		return descricaoMunicipio;
	}

	public void setDescricaoMunicipio(String descricaoMunicipio) {
		this.descricaoMunicipio = descricaoMunicipio;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
		if (municipio == null) {
			this.descricaoMunicipio = "";
		} else {
			this.descricaoMunicipio = municipio.getId() + "-" + 
				this.municipio.getNome().trim();
		}
	}
}