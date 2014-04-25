package gcom.cobranca;

import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.spcserasa.FiltroNegativadorMovimentoReg;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/** @author Hibernate CodeGenerator */
public class NegativadorResultadoSimulacao extends ObjetoTransacao implements Serializable {

	public Filtro retornaFiltro() {
		FiltroNegativadorMovimentoReg filtroNegativadorExclusaoMotivo = new FiltroNegativadorMovimentoReg();
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimentoReg.ID,this.getId()));		
		return filtroNegativadorExclusaoMotivo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private BigDecimal valorDebito;

    /** nullable persistent field */
    private String numeroCpf;

    /** nullable persistent field */
    private String numeroCnpj;

    /** persistent field */
    private Date ultimaAlteracao;
    /** persistent field */
    private Imovel imovel;

    /** persistent field */
    private NegativacaoComando negativacaoComando;
    
    private Integer quantidadeItensIncluidos;

    /** full constructor */
	public NegativadorResultadoSimulacao(Integer id, BigDecimal valorDebito, String numeroCpf, String numeroCnpj, Date ultimaAlteracao, Imovel imovel, NegativacaoComando negativacaoComando) {
		this.id = id;
		this.valorDebito = valorDebito;
		this.numeroCpf = numeroCpf;
		this.numeroCnpj = numeroCnpj;
		this.ultimaAlteracao = ultimaAlteracao;
		this.imovel = imovel;
		this.negativacaoComando = negativacaoComando;
	}

    /** default constructor */
    public NegativadorResultadoSimulacao() {
    }

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo imovel.
	 */
	public Imovel getImovel() {
		return imovel;
	}

	/**
	 * @param imovel O imovel a ser setado.
	 */
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	/**
	 * @return Retorna o campo negativacaoComando.
	 */
	public NegativacaoComando getNegativacaoComando() {
		return negativacaoComando;
	}

	/**
	 * @param negativacaoComando O negativacaoComando a ser setado.
	 */
	public void setNegativacaoComando(NegativacaoComando negativacaoComando) {
		this.negativacaoComando = negativacaoComando;
	}

	/**
	 * @return Retorna o campo numeroCnpj.
	 */
	public String getNumeroCnpj() {
		return numeroCnpj;
	}

	/**
	 * @param numeroCnpj O numeroCnpj a ser setado.
	 */
	public void setNumeroCnpj(String numeroCnpj) {
		this.numeroCnpj = numeroCnpj;
	}

	/**
	 * @return Retorna o campo numeroCpf.
	 */
	public String getNumeroCpf() {
		return numeroCpf;
	}

	/**
	 * @param numeroCpf O numeroCpf a ser setado.
	 */
	public void setNumeroCpf(String numeroCpf) {
		this.numeroCpf = numeroCpf;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo valorDebito.
	 */
	public BigDecimal getValorDebito() {
		return valorDebito;
	}

	/**
	 * @param valorDebito O valorDebito a ser setado.
	 */
	public void setValorDebito(BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}

	/**
	 * @return Retorna o campo quantidadeItensIncluidos.
	 */
	public Integer getQuantidadeItensIncluidos() {
		return quantidadeItensIncluidos;
	}

	/**
	 * @param quantidadeItensIncluidos O quantidadeItensIncluidos a ser setado.
	 */
	public void setQuantidadeItensIncluidos(Integer quantidadeItensIncluidos) {
		this.quantidadeItensIncluidos = quantidadeItensIncluidos;
	}


}
