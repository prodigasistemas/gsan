package gcom.atendimentopublico.ordemservico;

import gcom.cadastro.funcionario.Funcionario;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class EquipeComponentes extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	public final static short INDICADOR_RESPONSAVEL_SIM = 1;
	public final static short INDICADOR_RESPONSAVEL_NAO = 2;
	
    /** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorResponsavel;

    /** nullable persistent field */
    private String componentes;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private gcom.atendimentopublico.ordemservico.Equipe equipe;

    /** persistent field */
    private Funcionario funcionario;

    /** full constructor */
    public EquipeComponentes(short indicadorResponsavel, String componentes, Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.Equipe equipe, Funcionario funcionario) {
        this.indicadorResponsavel = indicadorResponsavel;
        this.componentes = componentes;
        this.ultimaAlteracao = ultimaAlteracao;
        this.equipe = equipe;
        this.funcionario = funcionario;
    }

    /** default constructor */
    public EquipeComponentes() {
    }

    /** minimal constructor */
    public EquipeComponentes(short indicadorResponsavel, Date ultimaAlteracao, gcom.atendimentopublico.ordemservico.Equipe equipe, Funcionario funcionario) {
        this.indicadorResponsavel = indicadorResponsavel;
        this.ultimaAlteracao = ultimaAlteracao;
        this.equipe = equipe;
        this.funcionario = funcionario;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getIndicadorResponsavel() {
        return this.indicadorResponsavel;
    }

    public void setIndicadorResponsavel(short indicadorResponsavel) {
        this.indicadorResponsavel = indicadorResponsavel;
    }

    public String getComponentes() {
        return this.componentes;
    }

    public void setComponentes(String componentes) {
        this.componentes = componentes;
    }

    public Date getUltimaAlteracao() {
        return this.ultimaAlteracao;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public gcom.atendimentopublico.ordemservico.Equipe getEquipe() {
        return this.equipe;
    }

    public void setEquipe(gcom.atendimentopublico.ordemservico.Equipe equipe) {
        this.equipe = equipe;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroEquipeComponentes filtroEquipeComponentes = new FiltroEquipeComponentes();
		filtroEquipeComponentes. adicionarCaminhoParaCarregamentoEntidade("funcionario");
		filtroEquipeComponentes. adicionarCaminhoParaCarregamentoEntidade("equipe");
		filtroEquipeComponentes. adicionarParametro(new ParametroSimples(FiltroEquipeComponentes.ID, this.getId()));
		return filtroEquipeComponentes; 		
	}        
}
