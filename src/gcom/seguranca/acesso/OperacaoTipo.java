package gcom.seguranca.acesso;

import gcom.interceptor.ObjetoGcom;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class OperacaoTipo extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	
	/** constantes que indica o codigo do PESQUISAR */
	public static final int PESQUISAR = 1;

	/** constantes que indica o codigo do REMOVER */
 	public static final int REMOVER = 2;

	/** constantes que indica o codigo do ATUALIZAR */
	public static final int ATUALIZAR = 3;

	/** constantes que indica o codigo do INSERIR */
	public static final int INSERIR = 4;

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String descricao;

    /** persistent field */
    private short indicadorAtualiza;

    /** persistent field */
    private Date ultimaAlteracao;

    /** full constructor */
    public OperacaoTipo(Integer id, String descricao, short indicadorAtualiza, Date ultimaAlteracao) {
        this.id = id;
        this.descricao = descricao;
        this.indicadorAtualiza = indicadorAtualiza;
        this.ultimaAlteracao = ultimaAlteracao;
    }

    /** default constructor */
    public OperacaoTipo() {
    }

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Retorna o campo indicadorAtualiza.
	 */
	public short getIndicadorAtualiza() {
		return indicadorAtualiza;
	}

	/**
	 * @param indicadorAtualiza O indicadorAtualiza a ser setado.
	 */
	public void setIndicadorAtualiza(short indicadorAtualiza) {
		this.indicadorAtualiza = indicadorAtualiza;
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
}
