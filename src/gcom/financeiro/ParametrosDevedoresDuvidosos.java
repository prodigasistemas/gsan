package gcom.financeiro;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ParametrosDevedoresDuvidosos extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private int anoMesReferenciaContabil;

    /** persistent field */
    private BigDecimal valorABaixar;

    /** nullable persistent field */
    private Integer anoMesReferenciaArrecadacao;

    /** nullable persistent field */
    private Date dataProcessamento;

    /** nullable persistent field */
    private Date dataIntegracaoContabil;

    /** persistent field */
    private Date ultimaAlteracao;
    
    private BigDecimal valorBaixado;

    /** default constructor */
    public ParametrosDevedoresDuvidosos() { }

	public Integer getAnoMesReferenciaArrecadacao() {
		return anoMesReferenciaArrecadacao;
	}

	public void setAnoMesReferenciaArrecadacao(Integer anoMesReferenciaArrecadacao) {
		this.anoMesReferenciaArrecadacao = anoMesReferenciaArrecadacao;
	}

	public int getAnoMesReferenciaContabil() {
		return anoMesReferenciaContabil;
	}

	public void setAnoMesReferenciaContabil(int anoMesReferenciaContabil) {
		this.anoMesReferenciaContabil = anoMesReferenciaContabil;
	}

	public Date getDataIntegracaoContabil() {
		return dataIntegracaoContabil;
	}

	public void setDataIntegracaoContabil(Date dataIntegracaoContabil) {
		this.dataIntegracaoContabil = dataIntegracaoContabil;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}


	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	public BigDecimal getValorABaixar() {
		return valorABaixar;
	}


	public void setValorABaixar(BigDecimal valorABaixar) {
		this.valorABaixar = valorABaixar;
	}
	
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	
    public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {

		FiltroParametrosDevedoresDuvidosos filtro = new FiltroParametrosDevedoresDuvidosos();
		filtro.adicionarParametro(
			new ParametroSimples(FiltroParametrosDevedoresDuvidosos.ID, this.getId()));
		
		return filtro;
	}

	public BigDecimal getValorBaixado() {
		return valorBaixado;
	}

	public void setValorBaixado(BigDecimal valorBaixado) {
		this.valorBaixado = valorBaixado;
	}
	

}
