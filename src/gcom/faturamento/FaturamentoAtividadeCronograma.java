package gcom.faturamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class FaturamentoAtividadeCronograma extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date dataPrevista;

    /** nullable persistent field */
    private Date dataRealizacao;

    /** nullable persistent field */
    private Date comando;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.faturamento.FaturamentoAtividade faturamentoAtividade;

    /** persistent field */
    private gcom.faturamento.FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal;
    
    /** persistent field */
    private Usuario usuario;
    
    private Set faturamentoAtividadeCronogramaRotas;
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
		filtroFaturamentoAtividadeCronograma. adicionarCaminhoParaCarregamentoEntidade("faturamentoAtividade");
		filtroFaturamentoAtividadeCronograma. adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal");
		filtroFaturamentoAtividadeCronograma. adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtroFaturamentoAtividadeCronograma. adicionarParametro(
				new ParametroSimples(FiltroFaturamentoAtividadeCronograma .ID, this.getId()));
		return filtroFaturamentoAtividadeCronograma; 
	}

    /** full constructor */
    public FaturamentoAtividadeCronograma(Date dataPrevista, Date dataRealizacao, Date comando, Date ultimaAlteracao, gcom.faturamento.FaturamentoAtividade faturamentoAtividade, gcom.faturamento.FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal, Usuario usuario) {
        this.dataPrevista = dataPrevista;
        this.dataRealizacao = dataRealizacao;
        this.comando = comando;
        this.ultimaAlteracao = ultimaAlteracao;
        this.faturamentoAtividade = faturamentoAtividade;
        this.faturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal;
        this.usuario = usuario;
    }

    /** default constructor */
    public FaturamentoAtividadeCronograma() {
    }

    /** minimal constructor */
    public FaturamentoAtividadeCronograma(gcom.faturamento.FaturamentoAtividade faturamentoAtividade, gcom.faturamento.FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal) {
        this.faturamentoAtividade = faturamentoAtividade;
        this.faturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal;
    }
    
    /**
     * Construtor usado no repositorio de faturamento   
     */
    public FaturamentoAtividadeCronograma(Integer id, Date dataPrevista, Date dataRealizacao, Date comando, Date ultimaAlteracao, gcom.faturamento.FaturamentoAtividade faturamentoAtividade, gcom.faturamento.FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal) {
    	this.id = id;
    	this.dataPrevista = dataPrevista;
        this.dataRealizacao = dataRealizacao;
        this.comando = comando;
        this.ultimaAlteracao = ultimaAlteracao;
        this.faturamentoAtividade = faturamentoAtividade;
        this.faturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal;
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

    public Date getDataRealizacao() {
        return this.dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
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

    public gcom.faturamento.FaturamentoAtividade getFaturamentoAtividade() {
        return this.faturamentoAtividade;
    }

    public void setFaturamentoAtividade(gcom.faturamento.FaturamentoAtividade faturamentoAtividade) {
        this.faturamentoAtividade = faturamentoAtividade;
    }

    public gcom.faturamento.FaturamentoGrupoCronogramaMensal getFaturamentoGrupoCronogramaMensal() {
        return this.faturamentoGrupoCronogramaMensal;
    }

    public void setFaturamentoGrupoCronogramaMensal(gcom.faturamento.FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal) {
        this.faturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal;
    }

    /**
     * Retorna o valor de diaAnoMesReferencia
     * 
     * @return O valor de diaAnoMesReferencia
     */
    public String getDiaAnoMesPrevista() {
        String dataReferencia = null;
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

        dataReferencia = data.format(this.dataPrevista);
        return dataReferencia;
    }
    
    
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Set getFaturamentoAtividadeCronogramaRotas() {
		return faturamentoAtividadeCronogramaRotas;
	}

	public void setFaturamentoAtividadeCronogramaRotas(
			Set faturamentoAtividadeCronogramaRotas) {
		this.faturamentoAtividadeCronogramaRotas = faturamentoAtividadeCronogramaRotas;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
