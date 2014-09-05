package gcom.cobranca;

import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativacaoComando implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorComandoCriterio;

    /** persistent field */
    private Date dataPrevista;

    /** nullable persistent field */
    private Date dataHoraComando;

    /** nullable persistent field */
    private Date dataHoraRealizacao;

    /** nullable persistent field */
    private Integer quantidadeInclusoes;

    /** nullable persistent field */
    private BigDecimal valorDebito;

    /** nullable persistent field */
    private Integer quantidadeItensIncluidos;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private String descricaoComunicacaoInterna;

    /** persistent field */
    private short indicadorSimulacao;

    /** persistent field */
    private Usuario usuario;

    /** persistent field */
    private Set negativacaoCriterios;
    
    /** persistent field */
    private Negativador negativador;
    
    /** persistent field */
    private NegativacaoComando comandoSimulacao;
    
    private Short indicadorBaixaRenda;
    
    private Short indicadorContaNomeCliente;
    
    private Short indicadorOrgaoPublico;
    
    public final static short SIMULACAO = 1;
    public final static short NAO_SIMULACAO = 2;
    
    private static int quantidadeImoveisJaIncluidos = 0;
    
    public static boolean continuarInclusaoImoveis(int quantidadeMaxima) {
    	if (quantidadeImoveisJaIncluidos <= quantidadeMaxima){
    		return true;
    	} else {
    		return false;
    	}
	}

    public static synchronized  void incrementarQuantidadeImoveisJaIncluidos(){
    	quantidadeImoveisJaIncluidos++;    	
    }
    
    public static synchronized  void resetQuantidadeImoveisJaIncluidos(){
    	quantidadeImoveisJaIncluidos = 0;    	
    }
    
    /** full constructor */
    public NegativacaoComando(Integer id, short indicadorComandoCriterio, Date dataPrevista, Date dataHoraComando, 
    		Date dataHoraRealizacao, Integer quantidadeInclusoes, BigDecimal valorDebito, Integer quantidadeItensIncluidos, 
    		Date ultimaAlteracao, String descricaoComunicacaoInterna, short indicadorSimulacao, Usuario usuario, 
    		Set negativacaoCriterios,NegativacaoComando comandoSimulacao) {
        this.id = id;
        this.indicadorComandoCriterio = indicadorComandoCriterio;
        this.dataPrevista = dataPrevista;
        this.dataHoraComando = dataHoraComando;
        this.dataHoraRealizacao = dataHoraRealizacao;
        this.quantidadeInclusoes = quantidadeInclusoes;
        this.valorDebito = valorDebito;
        this.quantidadeItensIncluidos = quantidadeItensIncluidos;
        this.ultimaAlteracao = ultimaAlteracao;
        this.descricaoComunicacaoInterna = descricaoComunicacaoInterna;
        this.indicadorSimulacao = indicadorSimulacao;
        this.usuario = usuario;
        this.negativacaoCriterios = negativacaoCriterios;
        this.comandoSimulacao = comandoSimulacao;
    }

    /** default constructor */
    public NegativacaoComando() {
    }

    /** minimal constructor */
    public NegativacaoComando(Integer id, short indicadorComandoCriterio, Date dataPrevista, short indicadorSimulacao, Usuario usuario, Set negativacaoCriterios) {
        this.id = id;
        this.indicadorComandoCriterio = indicadorComandoCriterio;
        this.dataPrevista = dataPrevista;
        this.indicadorSimulacao = indicadorSimulacao;
        this.usuario = usuario;
        this.negativacaoCriterios = negativacaoCriterios;
    }

    public NegativacaoComando(Integer id) {
		this.id = id;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getIndicadorComandoCriterio() {
        return this.indicadorComandoCriterio;
    }

    public void setIndicadorComandoCriterio(short indicadorComandoCriterio) {
        this.indicadorComandoCriterio = indicadorComandoCriterio;
    }

    public Date getDataPrevista() {
        return this.dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Date getDataHoraComando() {
        return this.dataHoraComando;
    }

    public void setDataHoraComando(Date dataHoraComando) {
        this.dataHoraComando = dataHoraComando;
    }

    public Date getDataHoraRealizacao() {
        return this.dataHoraRealizacao;
    }

    public void setDataHoraRealizacao(Date dataHoraRealizacao) {
        this.dataHoraRealizacao = dataHoraRealizacao;
    }

    public Integer getQuantidadeInclusoes() {
        return this.quantidadeInclusoes;
    }

    public void setQuantidadeInclusoes(Integer quantidadeInclusoes) {
        this.quantidadeInclusoes = quantidadeInclusoes;
    }

    public BigDecimal getValorDebito() {
        return this.valorDebito;
    }

    public void setValorDebito(BigDecimal valorDebito) {
        this.valorDebito = valorDebito;
    }

    public Integer getQuantidadeItensIncluidos() {
        return this.quantidadeItensIncluidos;
    }

    public void setQuantidadeItensIncluidos(Integer quantidadeItensIncluidos) {
        this.quantidadeItensIncluidos = quantidadeItensIncluidos;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String getDescricaoComunicacaoInterna() {
        return this.descricaoComunicacaoInterna;
    }

    public void setDescricaoComunicacaoInterna(String descricaoComunicacaoInterna) {
        this.descricaoComunicacaoInterna = descricaoComunicacaoInterna;
    }

    public short getIndicadorSimulacao() {
        return this.indicadorSimulacao;
    }

    public void setIndicadorSimulacao(short indicadorSimulacao) {
        this.indicadorSimulacao = indicadorSimulacao;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set getNegativacaoCriterios() {
        return this.negativacaoCriterios;
    }

    public void setNegativacaoCriterios(Set negativacaoCriterios) {
        this.negativacaoCriterios = negativacaoCriterios;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Negativador getNegativador() {
		return negativador;
	}

	public void setNegativador(Negativador negativador) {
		this.negativador = negativador;
	}

	/**
	 * @return Retorna o campo idComandoSimulacao.
	 */
	public NegativacaoComando getComandoSimulacao() {
		return comandoSimulacao;
	}

	/**
	 * @param idComandoSimulacao O idComandoSimulacao a ser setado.
	 */
	public void setComandoSimulacao(NegativacaoComando comandoSimulacao) {
		this.comandoSimulacao = comandoSimulacao;
	}

	public Short getIndicadorBaixaRenda() {
		return indicadorBaixaRenda;
	}

	public void setIndicadorBaixaRenda(Short indicadorBaixaRenda) {
		this.indicadorBaixaRenda = indicadorBaixaRenda;
	}

	public Short getIndicadorContaNomeCliente() {
		return indicadorContaNomeCliente;
	}

	public void setIndicadorContaNomeCliente(Short indicadorContaNomeCliente) {
		this.indicadorContaNomeCliente = indicadorContaNomeCliente;
	}

	public Short getIndicadorOrgaoPublico() {
		return indicadorOrgaoPublico;
	}

	public void setIndicadorOrgaoPublico(
			Short indicadorOrgaoPublico) {
		this.indicadorOrgaoPublico = indicadorOrgaoPublico;
	}

	
}
