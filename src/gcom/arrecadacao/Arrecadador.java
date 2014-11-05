package gcom.arrecadacao;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Arrecadador extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

    private Integer id;
    private String numeroInscricaoEstadual;
    private Short codigoAgente;
    private Date ultimaAlteracao;
    private Short indicadorUso;
    private Cliente cliente;
    private Imovel imovel;

    public Arrecadador() {
    }
    
    public Arrecadador(Integer id) {
    	this.id = id;
    }
    
    public Arrecadador(String numeroInscricaoEstadual, Short codigoAgente, Date ultimaAlteracao, Short indicadorUso, Cliente cliente, Imovel imovel) {
        this.numeroInscricaoEstadual = numeroInscricaoEstadual;
        this.codigoAgente = codigoAgente;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorUso = indicadorUso;
        this.cliente = cliente;
        this.imovel = imovel;
    }

    public Arrecadador(Cliente cliente, Imovel imovel) {
        this.cliente = cliente;
        this.imovel = imovel;
    }

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroInscricaoEstadual() {
        return this.numeroInscricaoEstadual;
    }

    public void setNumeroInscricaoEstadual(String numeroInscricaoEstadual) {
        this.numeroInscricaoEstadual = numeroInscricaoEstadual;
    }

    public Short getCodigoAgente() {
        return this.codigoAgente;
    }

    public void setCodigoAgente(Short codigoAgente) {
        this.codigoAgente = codigoAgente;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
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
		FiltroArrecadador filtroArrecadador = new FiltroArrecadador();
		
		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("cliente");
		filtroArrecadador.adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroArrecadador.adicionarParametro(
				new ParametroSimples(FiltroArrecadador.ID, this.getId()));
		return filtroArrecadador; 
	}
}