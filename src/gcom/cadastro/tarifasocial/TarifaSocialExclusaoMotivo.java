package gcom.cadastro.tarifasocial;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class TarifaSocialExclusaoMotivo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /** nullable persistent field */
    private Short indicadorPermiteRecadastramentoImovel;

    /** nullable persistent field */
    private Short indicadorPermiteRecadastramentoCliente;
    
    /**
	 * Indicador de permissão de recadastramento 
	 */
    public final static Short INDICADOR_PERMITE_RECADASTRAMENTO_SIM = new Short("1");
	public final static Short INDICADOR_PERMITE_RECADASTRAMENTO_NAO = new Short("2");

    /** full constructor */
    public TarifaSocialExclusaoMotivo(String descricao, Short indicadorUso,
            Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public TarifaSocialExclusaoMotivo() {
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

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

	/**
	 * @return Retorna o campo indicadorPermiteRecadastramentoCliente.
	 */
	public Short getIndicadorPermiteRecadastramentoCliente() {
		return indicadorPermiteRecadastramentoCliente;
	}

	/**
	 * @param indicadorPermiteRecadastramentoCliente O indicadorPermiteRecadastramentoCliente a ser setado.
	 */
	public void setIndicadorPermiteRecadastramentoCliente(
			Short indicadorPermiteRecadastramentoCliente) {
		this.indicadorPermiteRecadastramentoCliente = indicadorPermiteRecadastramentoCliente;
	}

	/**
	 * @return Retorna o campo indicadorPermiteRecadastramentoImovel.
	 */
	public Short getIndicadorPermiteRecadastramentoImovel() {
		return indicadorPermiteRecadastramentoImovel;
	}

	/**
	 * @param indicadorPermiteRecadastramentoImovel O indicadorPermiteRecadastramentoImovel a ser setado.
	 */
	public void setIndicadorPermiteRecadastramentoImovel(
			Short indicadorPermiteRecadastramentoImovel) {
		this.indicadorPermiteRecadastramentoImovel = indicadorPermiteRecadastramentoImovel;
	}
	
	public String getDescricaoParaRegistroTransacao(){
		return this.descricao;
	}
	
	public String[] retornaCamposChavePrimaria() {
		String[] key = {FiltroTarifaSocialExclusaoMotivo.ID};
		return key;
	}
	
	@Override
	public void initializeLazy() {
		getDescricaoParaRegistroTransacao();
	}
    
	@Override
	public Filtro retornaFiltro() {
		FiltroTarifaSocialExclusaoMotivo filtro = new FiltroTarifaSocialExclusaoMotivo();

		filtro.adicionarParametro(new ParametroSimples(
				FiltroTarifaSocialExclusaoMotivo.ID, this.getId()));
		return filtro;
	}	
}
