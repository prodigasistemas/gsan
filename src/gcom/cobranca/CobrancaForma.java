package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class CobrancaForma extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    public final static Integer COBRANCA_EM_CONTA = new Integer(1);
    public final static Integer COBRANCA_EM_CARTAO_CREDITO = new Integer(3);
    public final static Integer COBRANCA_POR_ICMS = new Integer(4);
    public final static Integer COBRANCA_POR_EXTRATO = new Integer(5);
	
    private Integer id;
    private String descricao;
    private String descricaoAbreviada;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    
    private Short indicadorUsoContratoParcelamentoCliente;

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
    
    public CobrancaForma(String descricao, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public CobrancaForma() {
    }

    public CobrancaForma(Integer id) {
    	this.id = id;
    }
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    @Override
	public Filtro retornaFiltro() {
    	FiltroCobrancaForma filtro = new FiltroCobrancaForma();
    	
    	filtro.adicionarParametro(new ParametroSimples(FiltroCobrancaForma.ID,this.getId()));
    	
    	return filtro;

	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
		getDescricao();
	}

	public Short getIndicadorUsoContratoParcelamentoCliente() {
		return indicadorUsoContratoParcelamentoCliente;
	}

	public void setIndicadorUsoContratoParcelamentoCliente(
			Short indicadorUsoContratoParcelamentoCliente) {
		this.indicadorUsoContratoParcelamentoCliente = indicadorUsoContratoParcelamentoCliente;
	}

}
