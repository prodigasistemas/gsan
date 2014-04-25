package gcom.cadastro.unidade;

import java.util.Date;

import gcom.cadastro.geografico.Municipio;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;


/** @author Hibernate CodeGenerator */
public class UnidadeOrganizacionalMunicipio extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;
    
    private UnidadeOrganizacional idUnidadeRepavimentadora;
    
    private Municipio idMunicipio;

	private Date dataVinculacao;
	
	private Date dataDesvinculacao;
	
	private Date ultimaAlteracao;
	
	
	public UnidadeOrganizacionalMunicipio(){
	}

	/**
	 * @return Returns the dataDesvinculacao.
	 */
	public Date getDataDesvinculacao() {
		return dataDesvinculacao;
	}


	/**
	 * @param dataDesvinculacao The dataDesvinculacao to set.
	 */
	public void setDataDesvinculacao(Date dataDesvinculacao) {
		this.dataDesvinculacao = dataDesvinculacao;
	}


	/**
	 * @return Returns the dataVinculacao.
	 */
	public Date getDataVinculacao() {
		return dataVinculacao;
	}


	/**
	 * @param dataVinculacao The dataVinculacao to set.
	 */
	public void setDataVinculacao(Date dataVinculacao) {
		this.dataVinculacao = dataVinculacao;
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
	 * @return Returns the idMunicipio.
	 */
	public Municipio getIdMunicipio() {
		return idMunicipio;
	}


	/**
	 * @param idMunicipio The idMunicipio to set.
	 */
	public void setIdMunicipio(Municipio idMunicipio) {
		this.idMunicipio = idMunicipio;
	}


	/**
	 * @return Returns the idUnidadeRepavimentadora.
	 */
	public UnidadeOrganizacional getIdUnidadeRepavimentadora() {
		return idUnidadeRepavimentadora;
	}


	/**
	 * @param idUnidadeRepavimentadora The idUnidadeRepavimentadora to set.
	 */
	public void setIdUnidadeRepavimentadora(
			UnidadeOrganizacional idUnidadeRepavimentadora) {
		this.idUnidadeRepavimentadora = idUnidadeRepavimentadora;
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
   
	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}	
	
	public Filtro retornaFiltro(){
		FiltroUnidadeOrganizacionalMunicipio filtro = new FiltroUnidadeOrganizacionalMunicipio();
		
		filtro.adicionarParametro(new ParametroSimples(
				FiltroUnidadeOrganizacionalMunicipio.ID, this.getId()));
		return filtro; 
	}
   

   
}
