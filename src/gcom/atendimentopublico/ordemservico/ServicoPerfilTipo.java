package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class ServicoPerfilTipo extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String descricao;

    /** nullable persistent field */
    private String descricaoAbreviada;

    /** nullable persistent field */
    private Short componentesEquipe;

    /** persistent field */
    private short indicadorVeiculoProprio;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.EquipamentosEspeciais equipamentosEspeciais;

    /** full constructor */
    public ServicoPerfilTipo(String descricao, String descricaoAbreviada, Short componentesEquipe, short indicadorVeiculoProprio, short indicadorUso, Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.EquipamentosEspeciais equipamentosEspeciais) {
        this.descricao = descricao;
        this.descricaoAbreviada = descricaoAbreviada;
        this.componentesEquipe = componentesEquipe;
        this.indicadorVeiculoProprio = indicadorVeiculoProprio;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.equipamentosEspeciais = equipamentosEspeciais;
    }

    /** default constructor */
    public ServicoPerfilTipo() {
    }

    /** minimal constructor */
    public ServicoPerfilTipo(short indicadorVeiculoProprio, short indicadorUso, Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.EquipamentosEspeciais equipamentosEspeciais) {
        this.indicadorVeiculoProprio = indicadorVeiculoProprio;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.equipamentosEspeciais = equipamentosEspeciais;
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

    public Short getComponentesEquipe() {
        return this.componentesEquipe;
    }

    public void setComponentesEquipe(Short componentesEquipe) {
        this.componentesEquipe = componentesEquipe;
    }

    public short getIndicadorVeiculoProprio() {
        return this.indicadorVeiculoProprio;
    }

    public void setIndicadorVeiculoProprio(short indicadorVeiculoProprio) {
        this.indicadorVeiculoProprio = indicadorVeiculoProprio;
    }

    public short getIndicadorUso() {
        return this.indicadorUso;
    }

    public void setIndicadorUso(short indicadorUso) {
        this.indicadorUso = indicadorUso;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.ordemservico.EquipamentosEspeciais getEquipamentosEspeciais() {
        return this.equipamentosEspeciais;
    }

    public void setEquipamentosEspeciais(gcom.atendimentopublico.ordemservico.EquipamentosEspeciais equipamentosEspeciais) {
        this.equipamentosEspeciais = equipamentosEspeciais;
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
		FiltroServicoPerfilTipo filtroServicoPerfilTipo = new FiltroServicoPerfilTipo();
		filtroServicoPerfilTipo.adicionarParametro(new ParametroSimples(FiltroServicoPerfilTipo.ID,this.getId()));
		filtroServicoPerfilTipo.adicionarCaminhoParaCarregamentoEntidade("equipamentosEspeciais");

		return filtroServicoPerfilTipo;
	}

}
