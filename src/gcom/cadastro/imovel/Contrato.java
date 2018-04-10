package gcom.cadastro.imovel;

import gcom.arrecadacao.ContratoMotivoCancelamento;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Contrato  extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
    private Integer id;
    private Date dataContratoInicio;
    private Date dataContratoEncerrado;
    private Date dataContratoFim;
    private String numeroContrato;
    private Date ultimaAlteracao;

    private Imovel imovel;
    private ContratoMotivoCancelamento contratoMotivoCancelamento;
    private ContratoTipo contratoTipo;

    public Contrato(Integer id, Date dataContratoInicio, Date dataContratoEncerrado, Date dataContratoFim, String numeroContrato, Date ultimaAlteracao, Imovel imovel, ContratoTipo contratoTipo) {
        this.id = id;
        this.dataContratoInicio = dataContratoInicio;
        this.dataContratoEncerrado = dataContratoEncerrado;
        this.dataContratoFim = dataContratoFim;
        this.numeroContrato = numeroContrato;
        this.ultimaAlteracao = ultimaAlteracao;
        this.imovel = imovel;
        this.contratoTipo = contratoTipo;
    }

    public Contrato() {
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
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

	public void setContratoMotivoCancelamento(ContratoMotivoCancelamento contratoMotivoCancelamento) {
		this.contratoMotivoCancelamento = contratoMotivoCancelamento;
	}
    
	public ContratoTipo getContratoTipo() {
		return contratoTipo;
	}

	public void setContratoTipo(ContratoTipo contratoTipo) {
		this.contratoTipo = contratoTipo;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroContrato filtroContrato = new FiltroContrato();
		
		filtroContrato. adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroContrato. adicionarParametro(new ParametroSimples(FiltroContrato.ID, this.getId()));
		
		return filtroContrato; 
	} 
}
