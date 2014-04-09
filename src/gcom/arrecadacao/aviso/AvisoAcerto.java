package gcom.arrecadacao.aviso;

import gcom.arrecadacao.FiltroAvisoAcerto;
import gcom.arrecadacao.FiltroAvisoBancario;
import gcom.arrecadacao.banco.ContaBancaria;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class AvisoAcerto extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public Filtro retornaFiltro() {
		FiltroAvisoAcerto filtroAvisoAcerto = new FiltroAvisoAcerto();
        filtroAvisoAcerto.adicionarParametro(new ParametroSimples(FiltroAvisoBancario.ID, this.getId()));
        filtroAvisoAcerto.adicionarCaminhoParaCarregamentoEntidade("contaBancaria");
		return filtroAvisoAcerto ;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] ids = {"id"};
		return ids;
	}

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer indicadorCreditoDebito;

    /** nullable persistent field */
    private Date dataAcerto;

    /** nullable persistent field */
    private BigDecimal valorAcerto;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Integer indicadorArrecadacaoDevolucao;

    /** persistent field */
    private ContaBancaria contaBancaria;

    /** persistent field */
    private gcom.arrecadacao.aviso.AvisoBancario avisoBancario;

    /** full constructor */
    public AvisoAcerto(Integer indicadorCreditoDebito, Date dataAcerto, BigDecimal valorAcerto, Date ultimaAlteracao, ContaBancaria contaBancaria, gcom.arrecadacao.aviso.AvisoBancario avisoBancario, Integer indicadorArrecadacaoDevolucao) {
        this.indicadorCreditoDebito = indicadorCreditoDebito;
        this.dataAcerto = dataAcerto;
        this.valorAcerto = valorAcerto;
        this.ultimaAlteracao = ultimaAlteracao;
        this.contaBancaria = contaBancaria;
        this.avisoBancario = avisoBancario;
        this.indicadorArrecadacaoDevolucao = indicadorArrecadacaoDevolucao;
    }

    /** default constructor */
    public AvisoAcerto() {
    }

    /** minimal constructor */
    public AvisoAcerto(ContaBancaria contaBancaria, gcom.arrecadacao.aviso.AvisoBancario avisoBancario) {
        this.contaBancaria = contaBancaria;
        this.avisoBancario = avisoBancario;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndicadorCreditoDebito() {
        return this.indicadorCreditoDebito;
    }

    public void setIndicadorCreditoDebito(Integer indicadorCreditoDebito) {
        this.indicadorCreditoDebito = indicadorCreditoDebito;
    }

    public Date getDataAcerto() {
        return this.dataAcerto;
    }

    public void setDataAcerto(Date dataAcerto) {
        this.dataAcerto = dataAcerto;
    }

    public BigDecimal getValorAcerto() {
        return this.valorAcerto;
    }

    public void setValorAcerto(BigDecimal valorAcerto) {
        this.valorAcerto = valorAcerto;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public ContaBancaria getContaBancaria() {
        return this.contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public gcom.arrecadacao.aviso.AvisoBancario getAvisoBancario() {
        return this.avisoBancario;
    }

    public void setAvisoBancario(gcom.arrecadacao.aviso.AvisoBancario avisoBancario) {
        this.avisoBancario = avisoBancario;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo indicadorArrecadacaoDevolucao.
	 */
	public Integer getIndicadorArrecadacaoDevolucao() {
		return indicadorArrecadacaoDevolucao;
	}

	/**
	 * @param indicadorArrecadacaoDevolucao O indicadorArrecadacaoDevolucao a ser setado.
	 */
	public void setIndicadorArrecadacaoDevolucao(
			Integer indicadorArrecadacaoDevolucao) {
		this.indicadorArrecadacaoDevolucao = indicadorArrecadacaoDevolucao;
	}

}
