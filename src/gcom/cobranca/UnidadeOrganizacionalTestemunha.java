package gcom.cobranca;

import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;


/** @author Hibernate CodeGenerator */
public class UnidadeOrganizacionalTestemunha extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	public Filtro retornaFiltro(){
		FiltroUnidadeOrganizacionalTestemunha filtroUnidadeNegocioTestemunha = new FiltroUnidadeOrganizacionalTestemunha();

		filtroUnidadeNegocioTestemunha.adicionarParametro(new ParametroSimples(FiltroUnidadeOrganizacionalTestemunha.ID,
				this.getId()));

		
		return filtroUnidadeNegocioTestemunha;
	}
	
    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Date dataInicioRelacao;

    /** nullable persistent field */
    private Date dataFimRelacao;
    
    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** nullable persistent field */
    private UnidadeOrganizacional unidadeOrganizacional;
    
    /** nullable persistent field */
    private Usuario usuario;
    
    /** default constructor */
    public UnidadeOrganizacionalTestemunha() {
    }

    public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	/**
	 * @return Retorna o campo dataFimRelacao.
	 */
	public Date getDataFimRelacao() {
		return dataFimRelacao;
	}

	/**
	 * @param dataFimRelacao O dataFimRelacao a ser setado.
	 */
	public void setDataFimRelacao(Date dataFimRelacao) {
		this.dataFimRelacao = dataFimRelacao;
	}

	/**
	 * @return Retorna o campo dataInicioRelacao.
	 */
	public Date getDataInicioRelacao() {
		return dataInicioRelacao;
	}

	/**
	 * @param dataInicioRelacao O dataInicioRelacao a ser setado.
	 */
	public void setDataInicioRelacao(Date dataInicioRelacao) {
		this.dataInicioRelacao = dataInicioRelacao;
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

	/**
	 * @return Retorna o campo unidadeNegocio.
	 */
	public UnidadeOrganizacional getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	/**
	 * @param unidadeNegocio O unidadeNegocio a ser setado.
	 */
	public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/** full constructor */
	public UnidadeOrganizacionalTestemunha(Integer id, Date dataInicioRelacao,
			Date dataFimRelacao, Date ultimaAlteracao,
			UnidadeOrganizacional unidadeOrganizacional, Usuario usuario) {
		this.id = id;
		this.dataInicioRelacao = dataInicioRelacao;
		this.dataFimRelacao = dataFimRelacao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.unidadeOrganizacional = unidadeOrganizacional;
		this.usuario = usuario;
	}

 
}
