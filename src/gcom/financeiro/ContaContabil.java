package gcom.financeiro;

import java.io.Serializable;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class ContaContabil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static int RECEITA_AGUA = 1;
	public static int RECEITA_ESGOTO = 2;
	public static int RECEITA_DIVIDA_ATIVA = 3;
	public static int RECEITA_CREDITO = 10;
	public static int RECEITA_IMPOSTOS = 11;
	public static int OUTRAS_RECEITAS = 12;
	public static int PAGAMENTO_NAO_CLASSIFICADO = 13;
	public static int HISTORICO_PAG_NAO_CLASSIFICADO = 14;
	public static int DEVOLUCAO_AVISO_BANCARIO = 15;
	public static int PAG_HISTORICO_SEM_CORRESPONDENTE = 16;
	public static int RECEITA_SERVICOS = 17;
		
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String prefixoContabil;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private String numeroDigito;
    
    /** nullable persistent field */
    private Short indicadorLinha;
    
    /** nullable persistent field */
    private String numeroTerceiros;
    
    /** nullable persistent field */
    private String numeroConta;
    
    /** nullable persistent field */
    private String nomeConta;
    
    /** nullable persistent field */
    private Short indicadorCentroCusto;


    /** full constructor */
    public ContaContabil(String prefixoContabil, Date ultimaAlteracao, String descricao) {
                
        this.prefixoContabil = prefixoContabil;
        this.ultimaAlteracao = ultimaAlteracao;

    }
    
    public ContaContabil(Integer id, String prefixoContabil, Date ultimaAlteracao, String descricao) {
        this.id = id;
        this.prefixoContabil = prefixoContabil;
        this.ultimaAlteracao = ultimaAlteracao;
   
    }

    /** default constructor */
    public ContaContabil() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrefixoContabil() {
		return prefixoContabil;
	}

	public void setPrefixoContabil(String prefixoContabil) {
		this.prefixoContabil = prefixoContabil;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Short getIndicadorLinha() {
		return indicadorLinha;
	}

	public void setIndicadorLinha(Short indicadorLinha) {
		this.indicadorLinha = indicadorLinha;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getNumeroDigito() {
		return numeroDigito;
	}

	public void setNumeroDigito(String numeroDigito) {
		this.numeroDigito = numeroDigito;
	}

	public String getNumeroTerceiros() {
		return numeroTerceiros;
	}

	public void setNumeroTerceiros(String numeroTerceiros) {
		this.numeroTerceiros = numeroTerceiros;
	}

	public Short getIndicadorCentroCusto() {
		return indicadorCentroCusto;
	}

	public void setIndicadorCentroCusto(Short indicadorCentroCusto) {
		this.indicadorCentroCusto = indicadorCentroCusto;
	}
	
}

