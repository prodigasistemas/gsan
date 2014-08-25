package gcom.faturamento;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


public class FaturamentoAtividadeCronograma extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;

    private Integer id;
    private Date dataPrevista;
    private Date dataRealizacao;
    private Date comando;
    private Date ultimaAlteracao;
    private FaturamentoAtividade faturamentoAtividade;
    private FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal;
    
    private Usuario usuario;
    
    @SuppressWarnings("rawtypes")
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

    public FaturamentoAtividadeCronograma(Date dataPrevista, Date dataRealizacao, Date comando, Date ultimaAlteracao, gcom.faturamento.FaturamentoAtividade faturamentoAtividade, gcom.faturamento.FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal, Usuario usuario) {
        this.dataPrevista = dataPrevista;
        this.dataRealizacao = dataRealizacao;
        this.comando = comando;
        this.ultimaAlteracao = ultimaAlteracao;
        this.faturamentoAtividade = faturamentoAtividade;
        this.faturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal;
        this.usuario = usuario;
    }

    public FaturamentoAtividadeCronograma() {
    }

    public FaturamentoAtividadeCronograma(gcom.faturamento.FaturamentoAtividade faturamentoAtividade, gcom.faturamento.FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal) {
        this.faturamentoAtividade = faturamentoAtividade;
        this.faturamentoGrupoCronogramaMensal = faturamentoGrupoCronogramaMensal;
    }
    
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

	@SuppressWarnings("rawtypes")
	public Set getFaturamentoAtividadeCronogramaRotas() {
		return faturamentoAtividadeCronogramaRotas;
	}

	@SuppressWarnings("rawtypes")
	public void setFaturamentoAtividadeCronogramaRotas(
			Set faturamentoAtividadeCronogramaRotas) {
		this.faturamentoAtividadeCronogramaRotas = faturamentoAtividadeCronogramaRotas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
