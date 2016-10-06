package gcom.cadastro.cliente;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.transacao.FiltroTabela;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ClienteFoneAtualizacaoCadastral  extends ObjetoTransacao implements IClienteFone{
	
	private static final long serialVersionUID = 1L;
	
	public static final int ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL = 1502;

    private Integer id;
    
    private Integer idClienteAtualizacaoCadastral;
    
    private Cliente cliente;

	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private FoneTipo foneTipo;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String ddd;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String telefone;

    /** nullable persistent field */
	@ControleAlteracao(funcionalidade={ATRIBUTOS_CARREGAR_DADOS_ATUALIZACAO_CADASTRAL})
    private String ramal;

    /** persistent field */
    private Date ultimaAlteracao;
    
    private Short indicadorFonePadrao;

    public ClienteFoneAtualizacaoCadastral(Integer id, Integer idClienteAtualizacaoCadastral, Integer idFoneTipo, String ddd, String telefone, String ramal, Date ultimaAlteracao) {
		this.id = id;
		this.idClienteAtualizacaoCadastral = idClienteAtualizacaoCadastral;
		this.foneTipo = new FoneTipo(idFoneTipo);
		this.ddd = ddd;
		this.telefone = telefone;
		this.ramal = ramal;
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/** default constructor */
    public ClienteFoneAtualizacaoCadastral() {
    }
    
	public Integer getIdClienteAtualizacaoCadastral() {
		return idClienteAtualizacaoCadastral;
	}

	public void setIdClienteAtualizacaoCadastral(
			Integer idClienteAtualizacaoCadastral) {
		this.idClienteAtualizacaoCadastral = idClienteAtualizacaoCadastral;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Integer getIdCliente() {
		return cliente.getId();
	}

	public void setIdCliente(Integer idCliente) {
		if(cliente == null){
			this.cliente = new Cliente(idCliente);
		}else{
			this.cliente.setId(idCliente);
		}
	}

    public String getDdd() {
        return this.ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRamal() {
        return this.ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
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

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getId())
            .toHashCode();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	@Override
	public Filtro retornaFiltro() {
		Filtro filtro = retornaFiltro();
		filtro.adicionarParametro(new ParametroSimples(FiltroTabela.ID, this.getId()));
		return filtro;

	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		return null;
	}

	public Short getIndicadorFonePadrao() {
		return indicadorFonePadrao;
	}

	public void setIndicadorFonePadrao(Short indicadorFonePadrao) {
		this.indicadorFonePadrao = indicadorFonePadrao;
	}

	public FoneTipo getFoneTipo() {
		return foneTipo;
	}

	public void setFoneTipo(FoneTipo foneTipo) {
		this.foneTipo = foneTipo;
	}
	
	public Integer getIdFoneTipo() {
		return foneTipo.getId();
	}

	public void setIdFoneTipo(Integer idFoneTipo) {
		if(foneTipo == null){
			foneTipo = new FoneTipo(idFoneTipo);
		}else{
			foneTipo.setId(idFoneTipo);
		}
	}

	public Short getIndicadorTelefonePadrao() {
		
		return null;
	}

	public void setIndicadorTelefonePadrao(Short indicadorTelefonePadrao) {
		
		
	}
}
