package gcom.arrecadacao;

import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.FiltroContratoDemanda;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ContratoDemanda extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
    /** identifier field */
    private Integer id;

    /** persistent field */
    private Date dataContratoInicio;
    
    /** persistent field */
    private Date dataContratoEncerrado;

    /** persistent field */
    private Date dataContratoFim;

    /** persistent field */
    private String numeroContrato;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Imovel imovel;
    
    /** persistent field */
    private ContratoMotivoCancelamento contratoMotivoCancelamento;

    /** full constructor */
    public ContratoDemanda(Integer id, Date dataContratoInicio, Date dataContratoEncerrado, Date dataContratoFim, String numeroContrato, Date ultimaAlteracao, Imovel imovel) {
        this.id = id;
        this.dataContratoInicio = dataContratoInicio;
        this.dataContratoEncerrado = dataContratoEncerrado;
        this.dataContratoFim = dataContratoFim;
        this.numeroContrato = numeroContrato;
        this.ultimaAlteracao = ultimaAlteracao;
        this.imovel = imovel;
    }

    /** default constructor */
    public ContratoDemanda() {
    }

    

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Date getDataContratoFim() {
		return dataContratoFim;
	}

	public void setDataContratoFim(Date dataContratoFim) {
		this.dataContratoFim = dataContratoFim;
	}
	
	public Date getDataContratoEncerrado() {
		return dataContratoEncerrado;
	}

	public void setDataContratoEncerrado(Date dataContratoEncerrado) {
		this.dataContratoEncerrado = dataContratoEncerrado;
	}

	public Date getDataContratoInicio() {
		return dataContratoInicio;
	}

	public void setDataContratoInicio(Date dataContratoInicio) {
		this.dataContratoInicio = dataContratoInicio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ContratoMotivoCancelamento getContratoMotivoCancelamento() {
		return contratoMotivoCancelamento;
	}

	public void setContratoMotivoCancelamento(
			ContratoMotivoCancelamento contratoMotivoCancelamento) {
		this.contratoMotivoCancelamento = contratoMotivoCancelamento;
	}
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroContratoDemanda filtroContratoDemanda = new FiltroContratoDemanda();
		
		filtroContratoDemanda. adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroContratoDemanda. adicionarParametro(
				new ParametroSimples(FiltroContratoDemanda.ID, this.getId()));
		return filtroContratoDemanda; 
	} 

}
