package gcom.cadastro.geografico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Municipio extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

    private Integer id;
    private String nome;
    private Integer cepInicio;
    private Integer cepFim;
    private Short ddd;
    private Short indicadorUso;
    private Date ultimaAlteracao;
    private Date dataConcessaoInicio;
    private Date dataConcessaoFim;
    private String codigoIbge;
    private Short indicadorRelacaoQuadraBairro;

    private Microrregiao microrregiao;
    private RegiaoDesenvolvimento regiaoDesenvolvimento;
    private UnidadeFederacao unidadeFederacao;
    
    public Date getDataConcessaoFim() {
        return dataConcessaoFim;
    }

    public void setDataConcessaoFim(Date dataConcessaoFim) {
        this.dataConcessaoFim = dataConcessaoFim;
    }

    public Date getDataConcessaoInicio() {
        return dataConcessaoInicio;
    }

    public void setDataConcessaoInicio(Date dataConcessaoInicio) {
        this.dataConcessaoInicio = dataConcessaoInicio;
    }

    public Municipio(
            String nome,
            Integer cepInicio,
            Integer cepFim,
            Short ddd,
            Short indicadorUso,
            Date ultimaAlteracao,
            gcom.cadastro.geografico.Microrregiao microrregiao,
            gcom.cadastro.geografico.RegiaoDesenvolvimento regiaoDesenvolvimento,
            gcom.cadastro.geografico.UnidadeFederacao unidadeFederacao, String codigoIbge) {
        this.nome = nome;
        this.cepInicio = cepInicio;
        this.cepFim = cepFim;
        this.ddd = ddd;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.microrregiao = microrregiao;
        this.regiaoDesenvolvimento = regiaoDesenvolvimento;
        this.unidadeFederacao = unidadeFederacao;
        this.codigoIbge = codigoIbge;
    }

    public Municipio() {
    }

    public Municipio(Integer id) {
    	this.id = id;
    }
    
    public Municipio(
            gcom.cadastro.geografico.Microrregiao microrregiao,
            gcom.cadastro.geografico.RegiaoDesenvolvimento regiaoDesenvolvimento,
            gcom.cadastro.geografico.UnidadeFederacao unidadeFederacao) {
        this.microrregiao = microrregiao;
        this.regiaoDesenvolvimento = regiaoDesenvolvimento;
        this.unidadeFederacao = unidadeFederacao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCepInicio() {
        return this.cepInicio;
    }

    public void setCepInicio(Integer cepInicio) {
        this.cepInicio = cepInicio;
    }

    public Integer getCepFim() {
        return this.cepFim;
    }

    public void setCepFim(Integer cepFim) {
        this.cepFim = cepFim;
    }

    public Short getDdd() {
        return this.ddd;
    }

    public void setDdd(Short ddd) {
        this.ddd = ddd;
    }

    public Short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(Short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.cadastro.geografico.Microrregiao getMicrorregiao() {
        return this.microrregiao;
    }

    public void setMicrorregiao(
            gcom.cadastro.geografico.Microrregiao microrregiao) {
        this.microrregiao = microrregiao;
    }

    public gcom.cadastro.geografico.RegiaoDesenvolvimento getRegiaoDesenvolvimento() {
        return this.regiaoDesenvolvimento;
    }

    public void setRegiaoDesenvolvimento(
            gcom.cadastro.geografico.RegiaoDesenvolvimento regiaoDesenvolvimento) {
        this.regiaoDesenvolvimento = regiaoDesenvolvimento;
    }

    public gcom.cadastro.geografico.UnidadeFederacao getUnidadeFederacao() {
        return this.unidadeFederacao;
    }

    public void setUnidadeFederacao(
            gcom.cadastro.geografico.UnidadeFederacao unidadeFederacao) {
        this.unidadeFederacao = unidadeFederacao;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
    
    
    public Filtro retornaFiltro(){
		FiltroMunicipio filtroMunicipio = new FiltroMunicipio();

		filtroMunicipio.adicionarParametro(new ParametroSimples(FiltroMunicipio.ID,this.getId()));
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("microrregiao");
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("regiaoDesenvolvimento");
		filtroMunicipio.adicionarCaminhoParaCarregamentoEntidade("unidadeFederacao");
		
		return filtroMunicipio;
	}

	public String getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public Short getIndicadorRelacaoQuadraBairro() {
		return indicadorRelacaoQuadraBairro;
	}

	public void setIndicadorRelacaoQuadraBairro(Short indicadorRelacaoQuadraBairro) {
		this.indicadorRelacaoQuadraBairro = indicadorRelacaoQuadraBairro;
	}
	
	public boolean hasUnidadeFederacao(){
		return this.getUnidadeFederacao() != null 
				&& this.getUnidadeFederacao().getId() != null
				&& this.getUnidadeFederacao().getId().intValue() != 0
				&& this.getUnidadeFederacao().getSigla() != null;
	}
	
}
