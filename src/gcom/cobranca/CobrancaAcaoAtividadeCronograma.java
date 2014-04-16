package gcom.cobranca;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao
public class CobrancaAcaoAtividadeCronograma extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CANCELAR_DOCUMENTOS_COBRANCA = 1472; //Operacao.OPERACAO_IMOVEL_ATUALIZAR

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date dataPrevista;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CANCELAR_DOCUMENTOS_COBRANCA})
    private Date realizacao;

    /** nullable persistent field */
    private Date comando;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CANCELAR_DOCUMENTOS_COBRANCA})
    private Integer quantidadeDocumentos;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CANCELAR_DOCUMENTOS_COBRANCA})
    private BigDecimal valorDocumentos;

    /** nullable persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_CANCELAR_DOCUMENTOS_COBRANCA})
    private Integer quantidadeItensCobrados;

    /** persistent field */
    private gcom.cobranca.CobrancaAcaoCronograma cobrancaAcaoCronograma;

    /** persistent field */
    private gcom.cobranca.CobrancaAtividade cobrancaAtividade;
    
    private Integer quantidadeMaximaDocumentos;
    
    public Integer getQuantidadeMaximaDocumentos() {
		return quantidadeMaximaDocumentos;
	}

	public void setQuantidadeMaximaDocumentos(Integer quantidadeMaximaDocumentos) {
		this.quantidadeMaximaDocumentos = quantidadeMaximaDocumentos;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroCobrancaAcaoAtividadeCronograma filtroCobrancaAcaoAtividadeCronograma = new FiltroCobrancaAcaoAtividadeCronograma();
		filtroCobrancaAcaoAtividadeCronograma. adicionarCaminhoParaCarregamentoEntidade("cobrancaAcaoCronograma");
		filtroCobrancaAcaoAtividadeCronograma. adicionarCaminhoParaCarregamentoEntidade("cobrancaAtividade");
		filtroCobrancaAcaoAtividadeCronograma. adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_ACAO);
		filtroCobrancaAcaoAtividadeCronograma. adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_GRUPO_CRONOGRAMA_MES);
		filtroCobrancaAcaoAtividadeCronograma. adicionarCaminhoParaCarregamentoEntidade(FiltroCobrancaAcaoAtividadeCronograma.COBRANCA_GRUPO_CRONOGRAMA_MES_COBRANCA_GRUPO);
		filtroCobrancaAcaoAtividadeCronograma. adicionarParametro(
		new ParametroSimples(FiltroCobrancaAcaoAtividadeCronograma.ID, this.getId()));
		
		return filtroCobrancaAcaoAtividadeCronograma; 
	}

    /** full constructor */
    public CobrancaAcaoAtividadeCronograma(Date dataPrevista, Date realizacao, Date comando, Date ultimaAlteracao, Integer quantidadeDocumentos, BigDecimal valorDocumentos, Integer quantidadeItensCobrados, gcom.cobranca.CobrancaAcaoCronograma cobrancaAcaoCronograma, gcom.cobranca.CobrancaAtividade cobrancaAtividade) {
        this.dataPrevista = dataPrevista;
        this.realizacao = realizacao;
        this.comando = comando;
        this.ultimaAlteracao = ultimaAlteracao;
        this.quantidadeDocumentos = quantidadeDocumentos;
        this.valorDocumentos = valorDocumentos;
        this.quantidadeItensCobrados = quantidadeItensCobrados;
        this.cobrancaAcaoCronograma = cobrancaAcaoCronograma;
        this.cobrancaAtividade = cobrancaAtividade;
    }

    /** default constructor */
    public CobrancaAcaoAtividadeCronograma() {
    }

    /** minimal constructor */
    public CobrancaAcaoAtividadeCronograma(gcom.cobranca.CobrancaAcaoCronograma cobrancaAcaoCronograma, gcom.cobranca.CobrancaAtividade cobrancaAtividade) {
        this.cobrancaAcaoCronograma = cobrancaAcaoCronograma;
        this.cobrancaAtividade = cobrancaAtividade;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataPrevista() {
        return this.dataPrevista;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public Date getRealizacao() {
        return this.realizacao;
    }

    public void setRealizacao(Date realizacao) {
        this.realizacao = realizacao;
    }

    public Date getComando() {
        return this.comando;
    }

    public void setComando(Date comando) {
        this.comando = comando;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Integer getQuantidadeDocumentos() {
        return this.quantidadeDocumentos;
    }

    public void setQuantidadeDocumentos(Integer quantidadeDocumentos) {
        this.quantidadeDocumentos = quantidadeDocumentos;
    }

    public BigDecimal getValorDocumentos() {
        return this.valorDocumentos;
    }

    public void setValorDocumentos(BigDecimal valorDocumentos) {
        this.valorDocumentos = valorDocumentos;
    }

    public Integer getQuantidadeItensCobrados() {
        return this.quantidadeItensCobrados;
    }

    public void setQuantidadeItensCobrados(Integer quantidadeItensCobrados) {
        this.quantidadeItensCobrados = quantidadeItensCobrados;
    }

    public gcom.cobranca.CobrancaAcaoCronograma getCobrancaAcaoCronograma() {
        return this.cobrancaAcaoCronograma;
    }

    public void setCobrancaAcaoCronograma(gcom.cobranca.CobrancaAcaoCronograma cobrancaAcaoCronograma) {
        this.cobrancaAcaoCronograma = cobrancaAcaoCronograma;
    }

    public gcom.cobranca.CobrancaAtividade getCobrancaAtividade() {
        return this.cobrancaAtividade;
    }

    public void setCobrancaAtividade(gcom.cobranca.CobrancaAtividade cobrancaAtividade) {
        this.cobrancaAtividade = cobrancaAtividade;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = {"cobrancaAcaoCronograma.cobrancaAcao.descricaoCobrancaAcao","cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.cobrancaGrupo.descricao",
				"cobrancaAcaoCronograma.cobrancaGrupoCronogramaMes.anoMesReferencia"};
		return labels;		
	}
	
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Acao de cobranca", "Grupo", "Ano/Mes"};
		return labels;		
	}
    
	public String getDescricaoParaRegistroTransacao() {
		return getId() + "";
	}
	
	@Override
	public void initializeLazy() {
		getCobrancaAcaoCronograma();
		getCobrancaAtividade();
	}
}
