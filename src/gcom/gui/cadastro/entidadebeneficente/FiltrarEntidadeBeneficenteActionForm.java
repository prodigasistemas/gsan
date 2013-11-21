package gcom.gui.cadastro.entidadebeneficente;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.empresa.Empresa;
import gcom.faturamento.debito.DebitoTipo;

import java.util.Date;
import java.util.Set;

import org.apache.struts.validator.ValidatorActionForm;

public class FiltrarEntidadeBeneficenteActionForm extends ValidatorActionForm{

	private static final long serialVersionUID = 1L;
	
	 /** identifier field */
	private String atualizar;

	  /** identifier field */
    private Integer id;

    /** persistent field */
    private short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Cliente cliente;

    /** persistent field */
    private DebitoTipo debitoTipo;

    /** persistent field */
    private Set imovelDoacoes;    
    
    /** persistent field */
    private Empresa empresa;
    
    /** persistent field */
    private String inicioMesAnoAdesao;
    
    /** persistent field */
    private String fimMesAnoAdesao;

	
    
    
    
	
	public FiltrarEntidadeBeneficenteActionForm() {
		
		this.cliente = new Cliente();
		this.debitoTipo = new DebitoTipo();
		this.empresa = new Empresa();
	}

	/**
	 * @return Returns the atualizar.
	 */
	public String getAtualizar() {
		return atualizar;
	}

	/**
	 * @param atualizar The atualizar to set.
	 */
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

	/**
	 * @return Returns the cliente.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente The cliente to set.
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return Returns the debitoTipo.
	 */
	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	/**
	 * @param debitoTipo The debitoTipo to set.
	 */
	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	/**
	 * @return Returns the empresa.
	 */
	public Empresa getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa The empresa to set.
	 */
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Returns the fimMesAnoAdesao.
	 */
	public String getFimMesAnoAdesao() {
		return fimMesAnoAdesao;
	}

	/**
	 * @param fimMesAnoAdesao The fimMesAnoAdesao to set.
	 */
	public void setFimMesAnoAdesao(String fimMesAnoAdesao) {
		this.fimMesAnoAdesao = fimMesAnoAdesao;
	}

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the imovelDoacoes.
	 */
	public Set getImovelDoacoes() {
		return imovelDoacoes;
	}

	/**
	 * @param imovelDoacoes The imovelDoacoes to set.
	 */
	public void setImovelDoacoes(Set imovelDoacoes) {
		this.imovelDoacoes = imovelDoacoes;
	}

	/**
	 * @return Returns the indicadorUso.
	 */
	public short getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso The indicadorUso to set.
	 */
	public void setIndicadorUso(short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	/**
	 * @return Returns the inicioMesAnoAdesao.
	 */
	public String getInicioMesAnoAdesao() {
		return inicioMesAnoAdesao;
	}

	/**
	 * @param inicioMesAnoAdesao The inicioMesAnoAdesao to set.
	 */
	public void setInicioMesAnoAdesao(String inicioMesAnoAdesao) {
		this.inicioMesAnoAdesao = inicioMesAnoAdesao;
	}

	/**
	 * @return Returns the ultimaAlteracao.
	 */
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao The ultimaAlteracao to set.
	 */
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}


	
	
	
	
}
