package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.spcserasa.FiltroNegativadorMovimento;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativadorMovimento extends ObjetoTransacao implements Serializable {

	public Filtro retornaFiltro() {
		FiltroNegativadorMovimento filtroNegativadorExclusaoMotivo = new FiltroNegativadorMovimento();
		filtroNegativadorExclusaoMotivo.adicionarParametro(new ParametroSimples(FiltroNegativadorMovimento.ID,this.getId()));		
		return filtroNegativadorExclusaoMotivo;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public static short CODIGO_MOVIMENTO_INCLUSAO = 1;
	
	public static short CODIGO_MOVIMENTO_EXCLUSAO = 2;
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private short codigoMovimento;

    /** nullable persistent field */
    private Date dataEnvio;

    /** persistent field */
    private Date dataProcessamentoEnvio;

    /** nullable persistent field */
    private Date dataRetorno;

    /** nullable persistent field */
    private Date dataProcessamentoRetorno;

    /** nullable persistent field */
    private Integer numeroSequencialEnvio;

    /** nullable persistent field */
    private Integer numeroSequencialRetorno;

    /** nullable persistent field */
    private Integer numeroRegistrosEnvio;

    /** nullable persistent field */
    private Integer numeroRegistrosRetorno;

    /** nullable persistent field */
    private BigDecimal valorTotalEnvio;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.Negativador negativador;

    /** persistent field */
    private gcom.cobranca.NegativacaoComando negativacaoComando;

    /** persistent field */
    private Set negativadorMovimentoReg;

    /** full constructor */
    public NegativadorMovimento(Integer id, short codigoMovimento, Date dataEnvio, Date dataProcessamentoEnvio, Date dataRetorno, Date dataProcessamentoRetorno, Integer numeroSequencialEnvio, Integer numeroSequencialRetorno, Integer numeroRegistrosEnvio, Integer numeroRegistrosRetorno, BigDecimal valorTotalEnvio, Date ultimaAlteracao, gcom.cobranca.Negativador negativador, gcom.cobranca.NegativacaoComando negativacaoComando, Set negativadorMovimentoReg) {
        this.id = id;
        this.codigoMovimento = codigoMovimento;
        this.dataEnvio = dataEnvio;
        this.dataProcessamentoEnvio = dataProcessamentoEnvio;
        this.dataRetorno = dataRetorno;
        this.dataProcessamentoRetorno = dataProcessamentoRetorno;
        this.numeroSequencialEnvio = numeroSequencialEnvio;
        this.numeroSequencialRetorno = numeroSequencialRetorno;
        this.numeroRegistrosEnvio = numeroRegistrosEnvio;
        this.numeroRegistrosRetorno = numeroRegistrosRetorno;
        this.valorTotalEnvio = valorTotalEnvio;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.negativacaoComando = negativacaoComando;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    /** default constructor */
    public NegativadorMovimento() {
    }

    /** minimal constructor */
    public NegativadorMovimento(Integer id, short codigoMovimento, Date dataProcessamentoEnvio, gcom.cobranca.Negativador negativador, gcom.cobranca.NegativacaoComando negativacaoComando, Set negativadorMovimentoReg) {
        this.id = id;
        this.codigoMovimento = codigoMovimento;
        this.dataProcessamentoEnvio = dataProcessamentoEnvio;
        this.negativador = negativador;
        this.negativacaoComando = negativacaoComando;
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getCodigoMovimento() {
        return this.codigoMovimento;
    }

    public void setCodigoMovimento(short codigoMovimento) {
        this.codigoMovimento = codigoMovimento;
    }

    public Date getDataEnvio() {
        return this.dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Date getDataProcessamentoEnvio() {
        return this.dataProcessamentoEnvio;
    }

    public void setDataProcessamentoEnvio(Date dataProcessamentoEnvio) {
        this.dataProcessamentoEnvio = dataProcessamentoEnvio;
    }

    public Date getDataRetorno() {
        return this.dataRetorno;
    }

    public void setDataRetorno(Date dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public Date getDataProcessamentoRetorno() {
        return this.dataProcessamentoRetorno;
    }

    public void setDataProcessamentoRetorno(Date dataProcessamentoRetorno) {
        this.dataProcessamentoRetorno = dataProcessamentoRetorno;
    }

    public Integer getNumeroSequencialEnvio() {
        return this.numeroSequencialEnvio;
    }

    public void setNumeroSequencialEnvio(Integer numeroSequencialEnvio) {
        this.numeroSequencialEnvio = numeroSequencialEnvio;
    }

    public Integer getNumeroSequencialRetorno() {
        return this.numeroSequencialRetorno;
    }

    public void setNumeroSequencialRetorno(Integer numeroSequencialRetorno) {
        this.numeroSequencialRetorno = numeroSequencialRetorno;
    }

    public Integer getNumeroRegistrosEnvio() {
        return this.numeroRegistrosEnvio;
    }

    public void setNumeroRegistrosEnvio(Integer numeroRegistrosEnvio) {
        this.numeroRegistrosEnvio = numeroRegistrosEnvio;
    }

    public Integer getNumeroRegistrosRetorno() {
        return this.numeroRegistrosRetorno;
    }

    public void setNumeroRegistrosRetorno(Integer numeroRegistrosRetorno) {
        this.numeroRegistrosRetorno = numeroRegistrosRetorno;
    }

    public BigDecimal getValorTotalEnvio() {
        return this.valorTotalEnvio;
    }

    public void setValorTotalEnvio(BigDecimal valorTotalEnvio) {
        this.valorTotalEnvio = valorTotalEnvio;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cobranca.Negativador getNegativador() {
        return this.negativador;
    }

    public void setNegativador(gcom.cobranca.Negativador negativador) {
        this.negativador = negativador;
    }

    public gcom.cobranca.NegativacaoComando getNegativacaoComando() {
        return this.negativacaoComando;
    }

    public void setNegativacaoComando(gcom.cobranca.NegativacaoComando negativacaoComando) {
        this.negativacaoComando = negativacaoComando;
    }

    public Set getNegativadorMovimentoReg() {
        return this.negativadorMovimentoReg;
    }

    public void setNegativadorMovimentoReg(Set negativadorMovimentoReg) {
        this.negativadorMovimentoReg = negativadorMovimentoReg;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

}
