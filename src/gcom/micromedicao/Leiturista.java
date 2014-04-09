package gcom.micromedicao;

import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.cliente.FiltroClienteTipo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.funcionario.Funcionario;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class Leiturista extends ObjetoTransacao{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String codigoDDD;

    /** nullable persistent field */
    private String numeroFone;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Cliente cliente;

    /** persistent field */
    private Funcionario funcionario;

    /** persistent field */
    private Set roteiroEmpresas;
    
    /** persistent field */
    private Empresa empresa;
    
    /** persistent field */
    private Long numeroImei;
    
    /** persistent field */
    private Usuario usuario;    

    /** full constructor */
    public Leiturista(Integer id, String codigoDDD, String numeroFone, Short indicadorUso, Date ultimaAlteracao, Cliente cliente, Funcionario funcionario, Set roteiroEmpresas, Usuario usuario) {
        this.id = id;
        this.codigoDDD = codigoDDD;
        this.numeroFone = numeroFone;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.roteiroEmpresas = roteiroEmpresas;
        this.usuario = usuario;
    }

    /** default constructor */
    public Leiturista() {
    }

    /** minimal constructor */
    public Leiturista(Integer id, Date ultimaAlteracao, Cliente cliente, Funcionario funcionario, Set roteiroEmpresas, Usuario usuario) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.roteiroEmpresas = roteiroEmpresas;
        this.usuario = usuario;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNumeroFone() {
        return this.numeroFone;
    }

    public void setNumeroFone(String numeroFone) {
        this.numeroFone = numeroFone;
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

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Set getRoteiroEmpresas() {
        return this.roteiroEmpresas;
    }

    public void setRoteiroEmpresas(Set roteiroEmpresas) {
        this.roteiroEmpresas = roteiroEmpresas;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getCodigoDDD() {
		return codigoDDD;
	}

	public void setCodigoDDD(String codigoDDD) {
		this.codigoDDD = codigoDDD;
	}
	
    /**
     * < <Descrição do método>>
     * 
     * @return Descrição do retorno
     */
        
        public String[] retornaCamposChavePrimaria() {
    		String[] retorno = {"id"};
    		return retorno;
    	}
        
        public Filtro retornaFiltro(){
        	FiltroClienteTipo filtroClienteTipo = new FiltroClienteTipo();

        	filtroClienteTipo.adicionarParametro(new ParametroSimples(FiltroClienteTipo.ID,this.getId()));
        	filtroClienteTipo.adicionarCaminhoParaCarregamentoEntidade("esferaPoder");
 
    		
           	return filtroClienteTipo;
        }

		public Long getNumeroImei() {
			return numeroImei;
		}

		public void setNumeroImei(Long numeroImei) {
			this.numeroImei = numeroImei;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}


}
