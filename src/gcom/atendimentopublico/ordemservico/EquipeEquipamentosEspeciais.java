package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

public class EquipeEquipamentosEspeciais extends ObjetoTransacao {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;
	
	/** persistent field */
	private EquipamentosEspeciais equipamentosEspeciais;
	
	/** persistent field */
	private Integer quantidade;
	
	/** persistent field */
	private Date ultimaAlteracao;
	
	private Equipe equipe; 

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	/** full constructor */
    public EquipeEquipamentosEspeciais (Integer indicadorEquipamentosEspeciais, Integer quantidade, EquipamentosEspeciais equipamentosEspeciais,Date ultimaAlteracao) {
        this.quantidade = quantidade;
        this.equipamentosEspeciais = equipamentosEspeciais;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public EquipamentosEspeciais getEquipamentosEspeciais() {
		return equipamentosEspeciais;
	}

	public void setEquipamentosEspeciais(EquipamentosEspeciais equipamentosEspeciais) {
		this.equipamentosEspeciais = equipamentosEspeciais;
	}

	/** default constructor */
    public EquipeEquipamentosEspeciais() {
    }

	

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	
	
	public Filtro retornaFiltro(){
		FiltroEquipeEquipamentosEspeciais filtroEquipeEquipamentosEspeciais = new FiltroEquipeEquipamentosEspeciais();
		filtroEquipeEquipamentosEspeciais. adicionarCaminhoParaCarregamentoEntidade("equipe");
		filtroEquipeEquipamentosEspeciais. adicionarCaminhoParaCarregamentoEntidade("equipamentosEspeciais");
		filtroEquipeEquipamentosEspeciais. adicionarParametro(new ParametroSimples(FiltroEquipeEquipamentosEspeciais .ID, this.getId()));
		return filtroEquipeEquipamentosEspeciais; 		
	}

	

	@Override
	public String[] retornaCamposChavePrimaria() {
		
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	

	

}
