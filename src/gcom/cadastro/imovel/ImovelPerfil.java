package gcom.cadastro.imovel;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@ControleAlteracao()

public class ImovelPerfil extends ObjetoTransacao{
	
	private static final long serialVersionUID = 1L;
	
	public static final int  OPERACAO_IMOVEL_PERFIL_INSERIR = 1687;
	public static final int  OPERACAO_IMOVEL_PERFIL_ATUALIZAR = 1702;
	public static final int OPERACAO_IMOVEL_PERFIL_REMOVER = 1698;	

    private Integer id;

    private String descricao;

    private Short indicadorUso;

    private Date ultimaAlteracao;
    
    private Short indicadorGeracaoAutomatica;
    
	private String descricaoComId;
	
	private Short indicadorGerarDadosLeitura;
	
	private Short indicadorBloquearRetificacao;
	
	private Short indicadorGrandeConsumidor; 
	
	private Subcategoria subcategoria;
	
	private Short indicadorInserirManterPerfil;
	
	private Short indicadorBloqueaDadosSocial;
	
	private Short indicadorGeraDebitoSegundaViaConta;	
	
	public final static Integer NORMAL = new Integer(5);

    public final static Integer GRANDE = new Integer(1);

    public final static Integer TARIFA_SOCIAL = new Integer(4);
    
    public final static Integer GRANDE_NO_MES = new Integer(2);
    
    public final static Integer CORPORATIVO = new Integer(3);
    
    public final static Integer GRANDE_TELEMEDIDO = new Integer(7);
    
    public final static Integer VIVA_AGUA = new Integer(6);
    
    public final static Integer CORPORATIVO_TELEMED = new Integer(8);
    
    public final static Integer CADASTRO_PROVISORIO = new Integer(9);
    
    public final static Integer COLABORADORES = new Integer(10);
    
    public final static Integer BOLSA_AGUA = new Integer(11);
    
    public final static Short SIM = new Short((short)1);
    
    public final static Short NAO = new Short((short)2);    
    
    public ImovelPerfil(String descricao, Short indicadorUso,
            Date ultimaAlteracao) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public ImovelPerfil(String descricao, Short indicadorUso,
            Date ultimaAlteracao, Short indicadorGeracaoAutomatica) {
        this.descricao = descricao;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
    }

    public ImovelPerfil() {
    }

    public ImovelPerfil(Integer id) {
		this.id = id;
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
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

	public Short getIndicadorGeracaoAutomatica() {
		return indicadorGeracaoAutomatica;
	}

	public void setIndicadorGeracaoAutomatica(Short indicadorGeracaoAutomatica) {
		this.indicadorGeracaoAutomatica = indicadorGeracaoAutomatica;
	}
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = {"id"};
		return retorno;
	}

	public Filtro retornaFiltro() {
		Filtro filtro = new FiltroImovelPerfil();
		filtro.adicionarParametro(new ParametroSimples(FiltroImovelPerfil.ID,
				this.getId()));		
		return filtro;
	}
	
	public String getDescricaoComId() {
		
		if(this.getId().compareTo(10) == -1){
			descricaoComId = "0" + getId()+ " - " + getDescricao();
		}else{
			descricaoComId = getId()+ " - " + getDescricao();
		}
		
		return descricaoComId;
	}
	
	@Override
	public String getDescricaoParaRegistroTransacao() {
		return getDescricao();
	}
	
	@Override
	public void initializeLazy() {
		retornaCamposChavePrimaria();
	}

	public Short getIndicadorGerarDadosLeitura() {
		return indicadorGerarDadosLeitura;
	}

	public void setIndicadorGerarDadosLeitura(Short indicadorGerarDadosLeitura) {
		this.indicadorGerarDadosLeitura = indicadorGerarDadosLeitura;
	}

	public Short getIndicadorBloquearRetificacao() {
		return indicadorBloquearRetificacao;
	}

	public void setIndicadorBloquearRetificacao(Short indicadorBloquearRetificacao) {
		this.indicadorBloquearRetificacao = indicadorBloquearRetificacao;
	}

	public Short getIndicadorGrandeConsumidor() {
		return indicadorGrandeConsumidor;
	}

	public void setIndicadorGrandeConsumidor(Short indicadorGrandeConsumidor) {
		this.indicadorGrandeConsumidor = indicadorGrandeConsumidor;
	}

	public Short getIndicadorGeraDebitoSegundaViaConta() {
		return indicadorGeraDebitoSegundaViaConta;
	}

	public void setIndicadorGeraDebitoSegundaViaConta(
			Short indicadorGeraDebitoSegundaViaConta) {
		this.indicadorGeraDebitoSegundaViaConta = indicadorGeraDebitoSegundaViaConta;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String []labels = { "descricao"};
		return labels;		
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String []labels = {"Descricao"};
		return labels;		
	}
	
	public Short getIndicadorBloqueaDadosSocial() {
		return indicadorBloqueaDadosSocial;
	}
	
	public void setIndicadorBloqueaDadosSocial(Short indicadorBloqueaDadosSocial) {
		this.indicadorBloqueaDadosSocial = indicadorBloqueaDadosSocial;
	}
	
	public Short getIndicadorInserirManterPerfil() {
		return indicadorInserirManterPerfil;
	}
	
	public void setIndicadorInserirManterPerfil(Short indicadorInserirManterPerfil) {
		this.indicadorInserirManterPerfil = indicadorInserirManterPerfil;
	}
	
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}
	
	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
}
