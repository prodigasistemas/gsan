package gcom.cobranca.parcelamento;

import gcom.faturamento.conta.ContaMotivoRevisao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ParcelamentoDescontoAntiguidade extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer quantidadeMinimaMesesDebito;

    /** nullable persistent field */
    private BigDecimal percentualDescontoSemRestabelecimento;

    /** nullable persistent field */
    private BigDecimal percentualDescontoComRestabelecimento;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil;

    /** nullable persistent field */
    private BigDecimal percentualDescontoAtivo;
    
    private ContaMotivoRevisao contaMotivoRevisao;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroParcelamentoDescontoAntiguidade filtroParcelamentoDescontoAntiguidade = new FiltroParcelamentoDescontoAntiguidade();
		
		filtroParcelamentoDescontoAntiguidade. adicionarCaminhoParaCarregamentoEntidade("parcelamentoPerfil");
		filtroParcelamentoDescontoAntiguidade. adicionarParametro(
				new ParametroSimples(FiltroParcelamentoDescontoAntiguidade.ID, this.getId()));
		return filtroParcelamentoDescontoAntiguidade; 
	}
    
    /** full constructor */
    public ParcelamentoDescontoAntiguidade(Integer quantidadeMinimaMesesDebito, BigDecimal percentualDescontoSemRestabelecimento, 
    		BigDecimal percentualDescontoComRestabelecimento, Date ultimaAlteracao,
    		gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil, BigDecimal percentualDescontoAtivo) {
        this.quantidadeMinimaMesesDebito = quantidadeMinimaMesesDebito;
        this.percentualDescontoSemRestabelecimento = percentualDescontoSemRestabelecimento;
        this.percentualDescontoComRestabelecimento = percentualDescontoComRestabelecimento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.parcelamentoPerfil = parcelamentoPerfil;
        this.percentualDescontoAtivo = percentualDescontoAtivo;
    }

    /** default constructor */
    public ParcelamentoDescontoAntiguidade() {
    }

    /** minimal constructor */
    public ParcelamentoDescontoAntiguidade(gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil) {
        this.parcelamentoPerfil = parcelamentoPerfil;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidadeMinimaMesesDebito() {
        return this.quantidadeMinimaMesesDebito;
    }

    public void setQuantidadeMinimaMesesDebito(Integer quantidadeMinimaMesesDebito) {
        this.quantidadeMinimaMesesDebito = quantidadeMinimaMesesDebito;
    }

    public BigDecimal getPercentualDescontoSemRestabelecimento() {
        return this.percentualDescontoSemRestabelecimento;
    }

    public void setPercentualDescontoSemRestabelecimento(BigDecimal percentualDescontoSemRestabelecimento) {
        this.percentualDescontoSemRestabelecimento = percentualDescontoSemRestabelecimento;
    }

    public BigDecimal getPercentualDescontoComRestabelecimento() {
        return this.percentualDescontoComRestabelecimento;
    }

    public void setPercentualDescontoComRestabelecimento(BigDecimal percentualDescontoComRestabelecimento) {
        this.percentualDescontoComRestabelecimento = percentualDescontoComRestabelecimento;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.parcelamento.ParcelamentoPerfil getParcelamentoPerfil() {
        return this.parcelamentoPerfil;
    }

    public void setParcelamentoPerfil(gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil) {
        this.parcelamentoPerfil = parcelamentoPerfil;
    }

	/**
	 * @return Retorna o campo percentualDescontoAtivo.
	 */
	public BigDecimal getPercentualDescontoAtivo() {
		return percentualDescontoAtivo;
	}

	/**
	 * @param percentualDescontoAtivo O percentualDescontoAtivo a ser setado.
	 */
	public void setPercentualDescontoAtivo(BigDecimal percentualDescontoAtivo) {
		this.percentualDescontoAtivo = percentualDescontoAtivo;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public ContaMotivoRevisao getContaMotivoRevisao() {
		return contaMotivoRevisao;
	}

	public void setContaMotivoRevisao(ContaMotivoRevisao contaMotivoRevisao) {
		this.contaMotivoRevisao = contaMotivoRevisao;
	}
}
