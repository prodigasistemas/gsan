package gcom.faturamento;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Tiago Moreno - 10/02/2010 */
@ControleAlteracao()
public class MotivoInterferenciaTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** nullable persistent field */
    private Short indicadorUso;

    /** nullable persistent field */
    private Date ultimaAlteracao;
    

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
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }
    /**
      *  Descrição do método>>
      * 
      * @param other
      *            Descrição do parâmetro
      * @return Descrição do retorno
      */
     public boolean equals(Object other) {
         if ((this == other)) {
             return true;
         }
         if (!(other instanceof MotivoInterferenciaTipo)) {
             return false;
         }
         MotivoInterferenciaTipo castOther = (MotivoInterferenciaTipo) other;
 
         return new EqualsBuilder().append(this.getId(), castOther.getId())
                 .isEquals();
     }

    public String[] retornaCamposChavePrimaria(){
 		String[] retorno = new String[1];
 		retorno[0] = "id";
 		return retorno;
 	}
     
 	public Filtro retornaFiltro(){
 		FiltroMotivoInterferenciaTipo filtroMotivoInterferenciaTipo = new FiltroMotivoInterferenciaTipo();
 		
 		filtroMotivoInterferenciaTipo.adicionarParametro(
 				new ParametroSimples(FiltroMotivoInterferenciaTipo.ID, this.getId()));
 		return filtroMotivoInterferenciaTipo; 
 	}
 	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return this.getDescricao();
	}
	
	@Override
	public void initializeLazy() {
		getDescricao();
	}	
}
