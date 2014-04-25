package gcom.operacional.abastecimento;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.Municipio;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AbastecimentoProgramacao extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferencia;

    /** persistent field */
    private Date dataInicio;

    /** persistent field */
    private Date dataFim;

    /** persistent field */
    private Date horaInicio;

    /** persistent field */
    private Date horaFim;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private gcom.operacional.SistemaAbastecimento sistemaAbastecimento;

    /** persistent field */
    private Municipio municipio;

    /** persistent field */
    private Bairro bairro;

    /** persistent field */
    private gcom.operacional.SetorAbastecimento setorAbastecimento;

    /** persistent field */
    private gcom.operacional.DistritoOperacional distritoOperacional;

    /** persistent field */
    private gcom.operacional.ZonaAbastecimento zonaAbastecimento;

    /** persistent field */
    private BairroArea bairroArea;

    /** full constructor */
    public AbastecimentoProgramacao(int anoMesReferencia, Date dataInicio, Date dataFim, Date horaInicio, Date horaFim, Date ultimaAlteracao, gcom.operacional.SistemaAbastecimento sistemaAbastecimento, Municipio municipio, Bairro bairro, gcom.operacional.SetorAbastecimento setorAbastecimento, gcom.operacional.DistritoOperacional distritoOperacional, gcom.operacional.ZonaAbastecimento zonaAbastecimento, BairroArea bairroArea) {
        this.anoMesReferencia = anoMesReferencia;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.ultimaAlteracao = ultimaAlteracao;
        this.sistemaAbastecimento = sistemaAbastecimento;
        this.municipio = municipio;
        this.bairro = bairro;
        this.setorAbastecimento = setorAbastecimento;
        this.distritoOperacional = distritoOperacional;
        this.zonaAbastecimento = zonaAbastecimento;
        this.bairroArea = bairroArea;
    }

    /** default constructor */
    public AbastecimentoProgramacao() {
    }

    /** minimal constructor */
    public AbastecimentoProgramacao(int anoMesReferencia, Date dataInicio, Date dataFim, Date horaInicio, Date horaFim, Date ultimaAlteracao, Municipio municipio, Bairro bairro, gcom.operacional.SetorAbastecimento setorAbastecimento, gcom.operacional.DistritoOperacional distritoOperacional, gcom.operacional.ZonaAbastecimento zonaAbastecimento, BairroArea bairroArea) {
        this.anoMesReferencia = anoMesReferencia;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.ultimaAlteracao = ultimaAlteracao;
        this.municipio = municipio;
        this.bairro = bairro;
        this.setorAbastecimento = setorAbastecimento;
        this.distritoOperacional = distritoOperacional;
        this.zonaAbastecimento = zonaAbastecimento;
        this.bairroArea = bairroArea;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(int anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public Date getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return this.horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.operacional.SistemaAbastecimento getSistemaAbastecimento() {
        return this.sistemaAbastecimento;
    }

    public void setSistemaAbastecimento(gcom.operacional.SistemaAbastecimento sistemaAbastecimento) {
        this.sistemaAbastecimento = sistemaAbastecimento;
    }

    public Municipio getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Bairro getBairro() {
        return this.bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public gcom.operacional.SetorAbastecimento getSetorAbastecimento() {
        return this.setorAbastecimento;
    }

    public void setSetorAbastecimento(gcom.operacional.SetorAbastecimento setorAbastecimento) {
        this.setorAbastecimento = setorAbastecimento;
    }

    public gcom.operacional.DistritoOperacional getDistritoOperacional() {
        return this.distritoOperacional;
    }

    public void setDistritoOperacional(gcom.operacional.DistritoOperacional distritoOperacional) {
        this.distritoOperacional = distritoOperacional;
    }

    public gcom.operacional.ZonaAbastecimento getZonaAbastecimento() {
        return this.zonaAbastecimento;
    }

    public void setZonaAbastecimento(gcom.operacional.ZonaAbastecimento zonaAbastecimento) {
        this.zonaAbastecimento = zonaAbastecimento;
    }

    public BairroArea getBairroArea() {
        return this.bairroArea;
    }

    public void setBairroArea(BairroArea bairroArea) {
        this.bairroArea = bairroArea;
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
		FiltroAbastecimentoProgramacao filtro = new FiltroAbastecimentoProgramacao();
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroAbastecimentoProgramacao.ID, this.getId()));
		
		filtro.adicionarCaminhoParaCarregamentoEntidade("sistemaAbastecimento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("setorAbastecimento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("distritoOperacional");
		filtro.adicionarCaminhoParaCarregamentoEntidade("zonaAbastecimento");
		filtro.adicionarCaminhoParaCarregamentoEntidade("municipio");
		filtro.adicionarCaminhoParaCarregamentoEntidade("bairro");
		filtro.adicionarCaminhoParaCarregamentoEntidade("bairroArea");
		
		
		return filtro; 
	}    

}
