package gcom.cobranca.parcelamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ParcDesctoInativVista extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer quantidadeMaximaMesesInatividade;

    /** nullable persistent field */
    private BigDecimal percentualDescontoSemRestabelecimento;

    /** nullable persistent field */
    private BigDecimal percentualDescontoComRestabelecimento;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroParcelamentoDescontoInatividade filtroParcelamentoDescontoInatividade = new FiltroParcelamentoDescontoInatividade();
		
		filtroParcelamentoDescontoInatividade. adicionarCaminhoParaCarregamentoEntidade("parcelamentoPerfil");
		filtroParcelamentoDescontoInatividade. adicionarParametro(
				new ParametroSimples(FiltroParcelamentoDescontoInatividade.ID, this.getId()));
		return filtroParcelamentoDescontoInatividade; 
	}
    
    /** full constructor */
    public ParcDesctoInativVista(Integer quantidadeMaximaMesesInatividade, BigDecimal percentualDescontoSemRestabelecimento, BigDecimal percentualDescontoComRestabelecimento, Date ultimaAlteracao, gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil) {
        this.quantidadeMaximaMesesInatividade = quantidadeMaximaMesesInatividade;
        this.percentualDescontoSemRestabelecimento = percentualDescontoSemRestabelecimento;
        this.percentualDescontoComRestabelecimento = percentualDescontoComRestabelecimento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.parcelamentoPerfil = parcelamentoPerfil;
    }

    /** default constructor */
    public ParcDesctoInativVista() {
    }

    /** minimal constructor */
    public ParcDesctoInativVista(gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil) {
        this.parcelamentoPerfil = parcelamentoPerfil;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidadeMaximaMesesInatividade() {
        return this.quantidadeMaximaMesesInatividade;
    }

    public void setQuantidadeMaximaMesesInatividade(Integer quantidadeMaximaMesesInatividade) {
        this.quantidadeMaximaMesesInatividade = quantidadeMaximaMesesInatividade;
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

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
