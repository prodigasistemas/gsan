package gcom.cobranca.parcelamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ParcelamentoQuantidadeReparcelamento extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Short quantidadeMaximaReparcelamento;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    private BigDecimal percentualEntradaSugerida;

    /** persistent field */
    private gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroParcelamentoQuantidadeReparcelamento filtroParcelamentoQuantidadeReparcelamento = new FiltroParcelamentoQuantidadeReparcelamento();
		
		filtroParcelamentoQuantidadeReparcelamento. adicionarCaminhoParaCarregamentoEntidade("parcelamentoPerfil");
		filtroParcelamentoQuantidadeReparcelamento. adicionarParametro(
				new ParametroSimples(FiltroParcelamentoQuantidadeReparcelamento.ID, this.getId()));
		return filtroParcelamentoQuantidadeReparcelamento; 
	}
    
    /** full constructor */
    public ParcelamentoQuantidadeReparcelamento(Short quantidadeMaximaReparcelamento, 
    		Date ultimaAlteracao, gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil,
    		BigDecimal percentualEntradaSugerida) {
        this.quantidadeMaximaReparcelamento = quantidadeMaximaReparcelamento;
        this.ultimaAlteracao = ultimaAlteracao;
        this.parcelamentoPerfil = parcelamentoPerfil;
        this.percentualEntradaSugerida = percentualEntradaSugerida;
    }

    /** default constructor */
    public ParcelamentoQuantidadeReparcelamento() {
    }

    /** minimal constructor */
    public ParcelamentoQuantidadeReparcelamento(gcom.cobranca.parcelamento.ParcelamentoPerfil parcelamentoPerfil) {
        this.parcelamentoPerfil = parcelamentoPerfil;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getQuantidadeMaximaReparcelamento() {
        return this.quantidadeMaximaReparcelamento;
    }

    public void setQuantidadeMaximaReparcelamento(Short quantidadeMaximaReparcelamento) {
        this.quantidadeMaximaReparcelamento = quantidadeMaximaReparcelamento;
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

	public BigDecimal getPercentualEntradaSugerida() {
		return percentualEntradaSugerida;
	}

	public void setPercentualEntradaSugerida(BigDecimal percentualEntradaSugerida) {
		this.percentualEntradaSugerida = percentualEntradaSugerida;
	}

    
}

