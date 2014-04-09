package gcom.cobranca;

import gcom.interceptor.ObjetoTransacao;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CobrancaGrupo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private Integer anoMesReferencia;
    
    private ContratoEmpresaServico contratoEmpresaServico;
    
    private String emailResponsavel;
    
    private Short indicadorExecucaoAutomatica;

    public Short getIndicadorExecucaoAutomatica() {
		return indicadorExecucaoAutomatica;
	}

	public void setIndicadorExecucaoAutomatica(Short indicadorExecucaoAutomatica) {
		this.indicadorExecucaoAutomatica = indicadorExecucaoAutomatica;
	}

	/** full constructor */
    public CobrancaGrupo(String descricao, String descricaoAbreviada, Short indicadorUso, Date ultimaAlteracao, Integer anoMesReferencia) {
    	super();
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.anoMesReferencia = anoMesReferencia;
    }

    /** default constructor */
    public CobrancaGrupo() {
    }

    /** minimal constructor */
    public CobrancaGrupo(String descricao, String descricaoAbreviada) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoAbreviada() {
        return this.descricaoAbreviada;
    }

    public void setDescricaoAbreviada(String descricaoAbreviada) {
        this.descricaoAbreviada = descricaoAbreviada;
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

    public Integer getAnoMesReferencia() {
        return this.anoMesReferencia;
    }

    public void setAnoMesReferencia(Integer anoMesReferencia) {
        this.anoMesReferencia = anoMesReferencia;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    
    public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

    @Override
	public Filtro retornaFiltro(){
		FiltroCobrancaGrupo filtroCobrancaGrupo = new FiltroCobrancaGrupo();

		filtroCobrancaGrupo.adicionarParametro(new ParametroSimples(FiltroCobrancaGrupo.ID,
				this.getId()));

		
		return filtroCobrancaGrupo;
	}
    
    /**
     * Retorna o valor de mesAno
     * 
     * @return O valor de mesAno
     */
    public String getMesAno() {
        String mesAno = null;
        String mes = null;
        String ano = null;

        if (this.anoMesReferencia != null
                && !this.anoMesReferencia.toString().trim()
                        .equalsIgnoreCase("")) {
            String anoMes = this.anoMesReferencia.toString();

            mes = anoMes.substring(4, 6);
            ano = anoMes.substring(0, 4);
            mesAno = mes + "/" + ano;
        }
        return mesAno;
    }
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}

	public ContratoEmpresaServico getContratoEmpresaServico() {
		return contratoEmpresaServico;
	}

	public void setContratoEmpresaServico(
			ContratoEmpresaServico contratoEmpresaServico) {
		this.contratoEmpresaServico = contratoEmpresaServico;
	}

	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	public void setEmailResponsavel(String emailResponsavel) {
		this.emailResponsavel = emailResponsavel;
	}
	

}
