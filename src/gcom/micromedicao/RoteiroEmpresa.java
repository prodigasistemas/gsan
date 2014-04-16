package gcom.micromedicao;

import gcom.cadastro.empresa.Empresa;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class RoteiroEmpresa extends ObjetoTransacao{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Short indicadorUso;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private Empresa empresa;

    /** persistent field */
    private gcom.micromedicao.Leiturista leiturista;

    /** full constructor */
    public RoteiroEmpresa(Integer id, Short indicadorUso, Date ultimaAlteracao, Empresa empresa, gcom.micromedicao.Leiturista leiturista) {
        this.id = id;
        this.indicadorUso = indicadorUso;
        this.ultimaAlteracao = ultimaAlteracao;
        this.empresa = empresa;
        this.leiturista = leiturista;
    }

    /** default constructor */
    public RoteiroEmpresa() {
    }

    /** minimal constructor */
    public RoteiroEmpresa(Integer id, Date ultimaAlteracao, Empresa empresa, gcom.micromedicao.Leiturista leiturista) {
        this.id = id;
        this.ultimaAlteracao = ultimaAlteracao;
        this.empresa = empresa;
        this.leiturista = leiturista;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public gcom.micromedicao.Leiturista getLeiturista() {
        return this.leiturista;
    }

    public void setLeiturista(gcom.micromedicao.Leiturista leiturista) {
        this.leiturista = leiturista;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .toString();
    }

	@Override
	public Filtro retornaFiltro() {
		FiltroRoteiroEmpresa filtro = new FiltroRoteiroEmpresa();
		
		filtro. adicionarCaminhoParaCarregamentoEntidade("empresa");
		filtro. adicionarCaminhoParaCarregamentoEntidade("leiturista");
		filtro. adicionarCaminhoParaCarregamentoEntidade("leiturista.cliente");
		filtro. adicionarCaminhoParaCarregamentoEntidade("leiturista.funcionario");
		filtro. adicionarParametro(
				new ParametroSimples(FiltroRoteiroEmpresa.ID_ROTEIRO, this.getId()));
		return filtro; 
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	@Override
	public String[] retornarAtributosSelecionadosRegistro() {
		String[] atributos = {"empresa", "leiturista"};
		return atributos;
	}
	
	@Override
	public String[] retornarAtributosInformacoesOperacaoEfetuada() {
		String[] atributos = {"id", "empresa.descricao", "leiturista.cliente.nome", 
			"leiturista.funcionario.nome"};
		return atributos;
	}
	
	@Override
	public String[] retornarLabelsInformacoesOperacaoEfetuada() {
		String[] labels = {"Código do Roteiro Empresa", "Empresa", "Nome do Cliente", 
			"Nome do Funcionário"};
		return labels;
	}
}
