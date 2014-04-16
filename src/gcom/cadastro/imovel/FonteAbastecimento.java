package gcom.cadastro.imovel;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class FonteAbastecimento extends ObjetoTransacao {
	
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
    private Short indicadorCalcularVolumeFixo;
    
    private Short indicadorPermitePoco;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    
    /**
     * @since 19/09/2007
     */
    private String descricaoComId;
    
    public final static Short INDICADOR_CALCULAR_VOLUME_FIXO = new Short("1");
    
    /**TODO: COSANPA
     * 
     * Mantis 494 - geração da rota para recadastramento
     * @author Wellington Rocha*/
    public final static Integer NAO_INFORMADO = new Integer (0);
    
    public final static Integer COSANPA = new Integer (1);
    
    public final static Integer PROPRIO = new Integer (2);
    
    public final static Integer MISTO = new Integer (3);

    /** full constructor */
    public FonteAbastecimento(String descricao, String descricaoAbreviada,
    		Short indicadorUso, Short indicadorCalcularVolumeFixo, Date ultimaAlteracao) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.indicadorUso = indicadorUso;
        this.indicadorCalcularVolumeFixo = indicadorCalcularVolumeFixo;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public FonteAbastecimento() {
    }

    public FonteAbastecimento(Integer id) {
    	this.id = id; 
    }
    
    /** minimal constructor */
    public FonteAbastecimento(String descricao, String descricaoAbreviada) {
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

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroFonteAbastecimento();
		filtro.adicionarParametro(new ParametroSimples(FiltroFonteAbastecimento.ID,
				this.getId()));		
		return filtro;
	}
	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * @author Pedro Alexandre
	 * @date 19/09/2007
	 *
	 * @return
	 */
	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getId()+ " - " + getDescricao();
		}else{
			descricaoComId = getId()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}

	/**
	 * @return Retorna o campo indicadorCalcularVolumeFixo.
	 */
	public Short getIndicadorCalcularVolumeFixo() {
		return indicadorCalcularVolumeFixo;
	}

	/**
	 * @param indicadorCalcularVolumeFixo O indicadorCalcularVolumeFixo a ser setado.
	 */
	public void setIndicadorCalcularVolumeFixo(Short indicadorCalcularVolumeFixo) {
		this.indicadorCalcularVolumeFixo = indicadorCalcularVolumeFixo;
	}

	public Short getIndicadorPermitePoco() {
		return indicadorPermitePoco;
	}

	public void setIndicadorPermitePoco(Short indicadorPermitePoco) {
		this.indicadorPermitePoco = indicadorPermitePoco;
	}
	
	
}
