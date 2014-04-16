package gcom.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.ligacaoagua.LigacaoAguaSituacao;
import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoSituacao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EspecificacaoImovSitCriterio extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Short indicadorHidrometroLigacaoAgua;
    
    /** nullable persistent field */
    private Short indicadorHidrometroPoco;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao especificacaoImovelSituacao;

    /** persistent field */
    private LigacaoEsgotoSituacao ligacaoEsgotoSituacao;

    /** persistent field */
    private LigacaoAguaSituacao ligacaoAguaSituacao;

    /** full constructor */
    public EspecificacaoImovSitCriterio(Short indicadorHidrometroLigacaoAgua, Short indicadorHidrometroPoco, Date ultimaAlteracao, gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao especificacaoImovelSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.indicadorHidrometroLigacaoAgua = indicadorHidrometroLigacaoAgua;
        this.indicadorHidrometroPoco = indicadorHidrometroPoco;
        this.ultimaAlteracao = ultimaAlteracao;
        this.especificacaoImovelSituacao = especificacaoImovelSituacao;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    /** default constructor */
    public EspecificacaoImovSitCriterio() {
    }

    /** minimal constructor */
    public EspecificacaoImovSitCriterio(Date ultimaAlteracao, gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao especificacaoImovelSituacao, LigacaoEsgotoSituacao ligacaoEsgotoSituacao, LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.ultimaAlteracao = ultimaAlteracao;
        this.especificacaoImovelSituacao = especificacaoImovelSituacao;
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Short getIndicadorHidrometroLigacaoAgua() {
		return indicadorHidrometroLigacaoAgua;
	}

	public void setIndicadorHidrometroLigacaoAgua(
			Short indicadorHidrometroLigacaoAgua) {
		this.indicadorHidrometroLigacaoAgua = indicadorHidrometroLigacaoAgua;
	}

	public Short getIndicadorHidrometroPoco() {
		return indicadorHidrometroPoco;
	}

	public void setIndicadorHidrometroPoco(Short indicadorHidrometroPoco) {
		this.indicadorHidrometroPoco = indicadorHidrometroPoco;
	}

	public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao getEspecificacaoImovelSituacao() {
        return this.especificacaoImovelSituacao;
    }

    public void setEspecificacaoImovelSituacao(gcom.atendimentopublico.registroatendimento.EspecificacaoImovelSituacao especificacaoImovelSituacao) {
        this.especificacaoImovelSituacao = especificacaoImovelSituacao;
    }

    public LigacaoEsgotoSituacao getLigacaoEsgotoSituacao() {
        return this.ligacaoEsgotoSituacao;
    }

    public void setLigacaoEsgotoSituacao(LigacaoEsgotoSituacao ligacaoEsgotoSituacao) {
        this.ligacaoEsgotoSituacao = ligacaoEsgotoSituacao;
    }

    public LigacaoAguaSituacao getLigacaoAguaSituacao() {
        return this.ligacaoAguaSituacao;
    }

    public void setLigacaoAguaSituacao(LigacaoAguaSituacao ligacaoAguaSituacao) {
        this.ligacaoAguaSituacao = ligacaoAguaSituacao;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
	/**
	 * @param other Descrição do parâmetro
	 * @return Descrição do retorno
	 */
	public boolean equals(Object other) {
		
		boolean equals = false;
		
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof EspecificacaoImovSitCriterio)) {
			return false;
		}
		
		EspecificacaoImovSitCriterio castOther = (EspecificacaoImovSitCriterio) other;

		if(castOther.getLigacaoAguaSituacao() != null && this.getLigacaoAguaSituacao() != null){
			
			equals = (castOther.getLigacaoAguaSituacao().getId().intValue() == 
				this.getLigacaoAguaSituacao().getId().intValue());
			
		}else if(castOther.getLigacaoAguaSituacao() == null && this.getLigacaoAguaSituacao() == null){
			equals = true;
		}

		if(!equals){
			return equals;
		}

		if(castOther.getLigacaoEsgotoSituacao() != null && this.getLigacaoEsgotoSituacao() != null){
			
			equals = equals && 
				(castOther.getLigacaoEsgotoSituacao().getId().intValue() == 
					this.getLigacaoEsgotoSituacao().getId().intValue());
			
		}else if(castOther.getLigacaoEsgotoSituacao() == null && this.getLigacaoEsgotoSituacao() == null){
			equals = equals && true;
		}else if(castOther.getLigacaoEsgotoSituacao() != null || this.getLigacaoEsgotoSituacao() != null){
			equals = false;
		}

		if(!equals){
			return equals;
		}

		if(castOther.getIndicadorHidrometroLigacaoAgua() != null && this.getIndicadorHidrometroLigacaoAgua() != null){
			
			equals = equals && 
				(castOther.getIndicadorHidrometroLigacaoAgua().shortValue() == 
					this.getIndicadorHidrometroLigacaoAgua().shortValue());
			
		}else if(castOther.getIndicadorHidrometroLigacaoAgua() == null && this.getIndicadorHidrometroLigacaoAgua() == null){
			equals = equals && true;
		}else if(castOther.getIndicadorHidrometroLigacaoAgua() != null || this.getIndicadorHidrometroLigacaoAgua() != null){
			equals = false;
		}

		if(!equals){
			return equals;
		}
		
		if(castOther.getIndicadorHidrometroPoco() != null && this.getIndicadorHidrometroPoco() != null){
			
			equals = equals && 
				(castOther.getIndicadorHidrometroPoco().shortValue() == 
					this.getIndicadorHidrometroPoco().shortValue());
			
		}else if(castOther.getIndicadorHidrometroPoco() == null && this.getIndicadorHidrometroPoco() == null){
			equals = equals && true;
		}else if(castOther.getIndicadorHidrometroPoco() != null || this.getIndicadorHidrometroPoco() != null){
			equals = false;
		}
		
		
		return equals;
	}
    
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroEspecificacaoImovelSituacaoCriterio filtro = new FiltroEspecificacaoImovelSituacaoCriterio();

		filtro.adicionarCaminhoParaCarregamentoEntidade("especificacaoImovelSituacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoEsgotoSituacao");
		filtro.adicionarCaminhoParaCarregamentoEntidade("ligacaoAguaSituacao");
		
		filtro.adicionarParametro(
				new ParametroSimples(FiltroEspecificacaoImovelSituacaoCriterio.ID, this.getId()));
		
		return filtro; 
	}	

}
