package gcom.gui.cadastro;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Arthur Carvalho
 * @date 14/05/2008
 */
public class InserirEmpresaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String descricao;
	
	private String descricaoAbreviada;
	
	private String indicadorEmpresa;
	
	private String email;
	
	private Short indicadorUso;
	
	private Short indicadorEmpresaPrincipal;
    
	private String indicadorEmpresaCobranca;
    
	private String dataInicioContratoCobranca;
    
	private String percentualPagamento;

	private Short indicadorLeitura;
	
	private String quantidadeMinimaContas;
    
	private String percentualDaFaixa;
    
	private String percentualDaFaixaInformado;
    
    public Short getIndicadorLeitura() {
		return indicadorLeitura;
	}



	public void setIndicadorLeitura(Short indicadorLeitura) {
		this.indicadorLeitura = indicadorLeitura;
	}



	public String getDataInicioContratoCobranca() {
        return dataInicioContratoCobranca;
    }


    
    public void setDataInicioContratoCobranca(String dataInicioContratoCobranca) {
        this.dataInicioContratoCobranca = dataInicioContratoCobranca;
    }


    
    public String getPercentualPagamento() {
        return percentualPagamento;
    }


    
    public void setPercentualPagamento(String percentualPagamento) {
        this.percentualPagamento = percentualPagamento;
    }


    public String getIndicadorEmpresaCobranca() {
        return indicadorEmpresaCobranca;
    }

    
    public void setIndicadorEmpresaCobranca(String indicadorEmpresaCobranca) {
        this.indicadorEmpresaCobranca = indicadorEmpresaCobranca;
    }

    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIndicadorEmpresa() {
		return indicadorEmpresa;
	}

	public void setIndicadorEmpresa(String indicadorEmpresa) {
		this.indicadorEmpresa = indicadorEmpresa;
	}

	public Short getIndicadorEmpresaPrincipal() {
		return indicadorEmpresaPrincipal;
	}

	public void setIndicadorEmpresaPrincipal(Short indicadorEmpresaPrincipal) {
		this.indicadorEmpresaPrincipal = indicadorEmpresaPrincipal;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getPercentualDaFaixa() {
		return percentualDaFaixa;
	}

	public void setPercentualDaFaixa(String percentualDaFaixa) {
		this.percentualDaFaixa = percentualDaFaixa;
	}

	public String getQuantidadeMinimaContas() {
		return quantidadeMinimaContas;
	}

	public void setQuantidadeMinimaContas(String quantidadeMinimaContas) {
		this.quantidadeMinimaContas = quantidadeMinimaContas;
	}

	public String getPercentualDaFaixaInformado() {
		return percentualDaFaixaInformado;
	}

	public void setPercentualDaFaixaInformado(String percentualDaFaixaInformado) {
		this.percentualDaFaixaInformado = percentualDaFaixaInformado;
	}
	
}
