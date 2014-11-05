package gcom.arrecadacao;

import gcom.cobranca.FiltroDocumentoTipo;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ArrecadacaoForma extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

    private Integer id;
    private String codigoArrecadacaoForma;
    private String descricao;
    private Date ultimaAlteracao;

	public final static Integer DEBITO_AUTOMATICO = new Integer("13");
	public final static Integer GUICHE_CAIXA = new Integer("1");
	public final static Integer FICHA_COMPENSACAO = new Integer("16");
	public final static Integer CARTAO_CREDITO = new Integer("17");
	public final static Integer CARTAO_DEBITO = new Integer("19");
	public final static Integer DEPOSITO = new Integer("14");

	public ArrecadacaoForma() {
	}
	
	public ArrecadacaoForma(Integer id) {
		this.id = id;
	}
	
    public ArrecadacaoForma(String codigoArrecadacaoForma, String descricao, Date ultimaAlteracao) {
        this.codigoArrecadacaoForma = codigoArrecadacaoForma;
        this.descricao = descricao;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoArrecadacaoForma() {
        return this.codigoArrecadacaoForma;
    }

    public void setCodigoArrecadacaoForma(String codigoArrecadacaoForma) {
        this.codigoArrecadacaoForma = codigoArrecadacaoForma;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
	
	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroDocumentoTipo();
		filtro.adicionarParametro(new ParametroSimples(FiltroArrecadacaoForma.CODIGO, this.getId()));		
		return filtro;
	}
}
