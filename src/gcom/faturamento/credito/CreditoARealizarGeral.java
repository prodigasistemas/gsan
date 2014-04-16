package gcom.faturamento.credito;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class CreditoARealizarGeral extends ObjetoTransacao {
	private static final long serialVersionUID = 1L;
    /** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorHistorico;

    /** persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private CreditoARealizarHistorico creditoARealizarHistorico;

    /** nullable persistent field */
    private CreditoARealizar creditoARealizar;

    /** full constructor */
    public CreditoARealizarGeral(Integer id, short indicadorHistorico, Date ultimaAlteracao, CreditoARealizarHistorico creditoARealizarHistorico, CreditoARealizar creditoARealizar) {
        this.id = id;
        this.indicadorHistorico = indicadorHistorico;
        this.ultimaAlteracao = ultimaAlteracao;
        this.creditoARealizarHistorico = creditoARealizarHistorico;
        this.creditoARealizar = creditoARealizar;
    }

    /** default constructor */
    public CreditoARealizarGeral() {
    }

    /** minimal constructor */
    public CreditoARealizarGeral(Integer id, short indicadorHistorico, Date ultimaAlteracao) {
        this.id = id;
        this.indicadorHistorico = indicadorHistorico;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("crarId", getId())
            .toString();
    }

	/**
	 * @return Retorna o campo creditoARealizar.
	 */
	public CreditoARealizar getCreditoARealizar() {
		return creditoARealizar;
	}

	/**
	 * @param creditoARealizar O creditoARealizar a ser setado.
	 */
	public void setCreditoARealizar(CreditoARealizar creditoARealizar) {
		this.creditoARealizar = creditoARealizar;
	}

	/**
	 * @return Retorna o campo creditoARealizarHistorico.
	 */
	public CreditoARealizarHistorico getCreditoARealizarHistorico() {
		return creditoARealizarHistorico;
	}

	/**
	 * @param creditoARealizarHistorico O creditoARealizarHistorico a ser setado.
	 */
	public void setCreditoARealizarHistorico(
			CreditoARealizarHistorico creditoARealizarHistorico) {
		this.creditoARealizarHistorico = creditoARealizarHistorico;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo indicadorHistorico.
	 */
	public short getIndicadorHistorico() {
		return indicadorHistorico;
	}

	/**
	 * @param indicadorHistorico O indicadorHistorico a ser setado.
	 */
	public void setIndicadorHistorico(short indicadorHistorico) {
		this.indicadorHistorico = indicadorHistorico;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
    public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroCreditoARealizarGeral filtroCreditoARealizarGeral = new FiltroCreditoARealizarGeral();
		
		filtroCreditoARealizarGeral.adicionarParametro(new ParametroSimples(FiltroCreditoARealizarGeral.ID, this.getId()));		
		
		filtroCreditoARealizarGeral.adicionarCaminhoParaCarregamentoEntidade("creditoARealizarHistorico");
		filtroCreditoARealizarGeral.adicionarCaminhoParaCarregamentoEntidade("creditoARealizar");
		
		return filtroCreditoARealizarGeral; 
	}

}
