package gcom.cobranca;

import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.cobranca.parcelamento.FiltroParcelamentoPerfil;
import gcom.interceptor.ObjetoTransacao;
import gcom.spcserasa.FiltroNegativadorContrato;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class NegativadorContrato extends ObjetoTransacao implements Serializable  {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String numeroContrato;

    /** nullable persistent field */
    private String codigoConvenio;

    /** nullable persistent field */
    private Date dataContratoInicio;

    /** nullable persistent field */
    private Date dataContratoFim;

    /** nullable persistent field */
    private Date dataContratoEncerramento;

    /** nullable persistent field */
    private String descricaoEmailEnvioArquivo;

    /** persistent field */
    private int numeroSequencialEnvio;

    /** persistent field */
    private int numeroSequencialRetorno;

    /** persistent field */
    private BigDecimal valorContrato;

    /** nullable persistent field */
    private BigDecimal valorTarifaInclusao;

    /** nullable persistent field */
    private Integer numeroInclusoesContratadas;

    /** nullable persistent field */
    private Integer numeroInclusoesEnviadas;

    /** nullable persistent field */
    private Integer numeroExclusoesEnviadas;

    /** persistent field */
    private int numeroTamanhoRegistroMovimento;

    /** persistent field */
    private short numeroPrazoInclusao;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.cobranca.Negativador negativador;

    /** persistent field */
    private ContratoMotivoCancelamento contratoMotivoCancelamento;

    /** full constructor */
    public NegativadorContrato(Integer id, String numeroContrato, String codigoConvenio, Date dataContratoInicio, Date dataContratoFim, Date dataContratoEncerramento, String descricaoEmailEnvioArquivo, int numeroSequencialEnvio, int numeroSequencialRetorno, BigDecimal valorContrato, BigDecimal valorTarifaInclusao, Integer numeroInclusoesContratadas, Integer numeroInclusoesEnviadas, Integer numeroExclusoesEnviadas, int numeroTamanhoRegistroMovimento, short numeroPrazoInclusao, Date ultimaAlteracao, gcom.cobranca.Negativador negativador, ContratoMotivoCancelamento contratoMotivoCancelamento) {
        this.id = id;
        this.numeroContrato = numeroContrato;
        this.codigoConvenio = codigoConvenio;
        this.dataContratoInicio = dataContratoInicio;
        this.dataContratoFim = dataContratoFim;
        this.dataContratoEncerramento = dataContratoEncerramento;
        this.descricaoEmailEnvioArquivo = descricaoEmailEnvioArquivo;
        this.numeroSequencialEnvio = numeroSequencialEnvio;
        this.numeroSequencialRetorno = numeroSequencialRetorno;
        this.valorContrato = valorContrato;
        this.valorTarifaInclusao = valorTarifaInclusao;
        this.numeroInclusoesContratadas = numeroInclusoesContratadas;
        this.numeroInclusoesEnviadas = numeroInclusoesEnviadas;
        this.numeroExclusoesEnviadas = numeroExclusoesEnviadas;
        this.numeroTamanhoRegistroMovimento = numeroTamanhoRegistroMovimento;
        this.numeroPrazoInclusao = numeroPrazoInclusao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.contratoMotivoCancelamento = contratoMotivoCancelamento;
    }

    /** default constructor */
    public NegativadorContrato() {
    }

    /** minimal constructor */
    public NegativadorContrato(Integer id, String numeroContrato, int numeroSequencialEnvio, int numeroSequencialRetorno, BigDecimal valorContrato, int numeroTamanhoRegistroMovimento, short numeroPrazoInclusao, Date ultimaAlteracao, gcom.cobranca.Negativador negativador, ContratoMotivoCancelamento contratoMotivoCancelamento) {
        this.id = id;
        this.numeroContrato = numeroContrato;
        this.numeroSequencialEnvio = numeroSequencialEnvio;
        this.numeroSequencialRetorno = numeroSequencialRetorno;
        this.valorContrato = valorContrato;
        this.numeroTamanhoRegistroMovimento = numeroTamanhoRegistroMovimento;
        this.numeroPrazoInclusao = numeroPrazoInclusao;
        this.ultimaAlteracao = ultimaAlteracao;
        this.negativador = negativador;
        this.contratoMotivoCancelamento = contratoMotivoCancelamento;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroContrato() {
        return this.numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getCodigoConvenio() {
        return this.codigoConvenio;
    }

    public void setCodigoConvenio(String codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }

    public Date getDataContratoInicio() {
        return this.dataContratoInicio;
    }

    public void setDataContratoInicio(Date dataContratoInicio) {
        this.dataContratoInicio = dataContratoInicio;
    }

    public Date getDataContratoFim() {
        return this.dataContratoFim;
    }

    public void setDataContratoFim(Date dataContratoFim) {
        this.dataContratoFim = dataContratoFim;
    }

    public Date getDataContratoEncerramento() {
        return this.dataContratoEncerramento;
    }

    public void setDataContratoEncerramento(Date dataContratoEncerramento) {
        this.dataContratoEncerramento = dataContratoEncerramento;
    }

    public String getDescricaoEmailEnvioArquivo() {
        return this.descricaoEmailEnvioArquivo;
    }

    public void setDescricaoEmailEnvioArquivo(String descricaoEmailEnvioArquivo) {
        this.descricaoEmailEnvioArquivo = descricaoEmailEnvioArquivo;
    }

    public int getNumeroSequencialEnvio() {
        return this.numeroSequencialEnvio;
    }

    public void setNumeroSequencialEnvio(int numeroSequencialEnvio) {
        this.numeroSequencialEnvio = numeroSequencialEnvio;
    }

    public int getNumeroSequencialRetorno() {
        return this.numeroSequencialRetorno;
    }

    public void setNumeroSequencialRetorno(int numeroSequencialRetorno) {
        this.numeroSequencialRetorno = numeroSequencialRetorno;
    }

    public BigDecimal getValorContrato() {
        return this.valorContrato;
    }

    public void setValorContrato(BigDecimal valorContrato) {
        this.valorContrato = valorContrato;
    }

    public BigDecimal getValorTarifaInclusao() {
        return this.valorTarifaInclusao;
    }

    public void setValorTarifaInclusao(BigDecimal valorTarifaInclusao) {
        this.valorTarifaInclusao = valorTarifaInclusao;
    }

    public Integer getNumeroInclusoesContratadas() {
        return this.numeroInclusoesContratadas;
    }

    public void setNumeroInclusoesContratadas(Integer numeroInclusoesContratadas) {
        this.numeroInclusoesContratadas = numeroInclusoesContratadas;
    }

    public Integer getNumeroInclusoesEnviadas() {
        return this.numeroInclusoesEnviadas;
    }

    public void setNumeroInclusoesEnviadas(Integer numeroInclusoesEnviadas) {
        this.numeroInclusoesEnviadas = numeroInclusoesEnviadas;
    }

    public Integer getNumeroExclusoesEnviadas() {
        return this.numeroExclusoesEnviadas;
    }

    public void setNumeroExclusoesEnviadas(Integer numeroExclusoesEnviadas) {
        this.numeroExclusoesEnviadas = numeroExclusoesEnviadas;
    }

    public int getNumeroTamanhoRegistroMovimento() {
        return this.numeroTamanhoRegistroMovimento;
    }

    public void setNumeroTamanhoRegistroMovimento(int numeroTamanhoRegistroMovimento) {
        this.numeroTamanhoRegistroMovimento = numeroTamanhoRegistroMovimento;
    }

    public short getNumeroPrazoInclusao() {
        return this.numeroPrazoInclusao;
    }

    public void setNumeroPrazoInclusao(short numeroPrazoInclusao) {
        this.numeroPrazoInclusao = numeroPrazoInclusao;
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

    public ContratoMotivoCancelamento getContratoMotivoCancelamento() {
        return this.contratoMotivoCancelamento;
    }

    public void setContratoMotivoCancelamento(ContratoMotivoCancelamento contratoMotivoCancelamento) {
        this.contratoMotivoCancelamento = contratoMotivoCancelamento;
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
	
	public Filtro retornaFiltro(){
		FiltroNegativadorContrato filtroNegativadorContrato = new FiltroNegativadorContrato();
		
		filtroNegativadorContrato. adicionarCaminhoParaCarregamentoEntidade("negativador.cliente");
		filtroNegativadorContrato. adicionarCaminhoParaCarregamentoEntidade("contratoMotivoCancelamento");		
		filtroNegativadorContrato. adicionarParametro(new ParametroSimples(FiltroParcelamentoPerfil.ID, this.getId()));
		return filtroNegativadorContrato; 
	}
	
	
	
	

}
