package gcom.seguranca.transacao;

import gcom.interceptor.ObjetoGcom;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Date;


/** @author Hibernate CodeGenerator */
public class TabelaColunaAtualizacaoCadastral extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String colunaValorAnterior;

    /** nullable persistent field */
    private String colunaValorAtual;

    /** nullable persistent field */

    private Short indicadorAutorizado;

    /** nullable persistent field */
    private Date dataValidacao;

    /** nullable persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    private TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral;

    private TabelaColuna tabelaColuna;

		private Usuario usuario;

		private boolean registroInclusao;

    public TabelaColunaAtualizacaoCadastral(Integer id, String colunaValorAnterior, String colunaValorAtual, Short indicadorAutorizado,
    		Date dataValidacao, Date ultimaAlteracao, TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral, TabelaColuna tabelaColuna) {

		this.id = id;
		this.colunaValorAnterior = colunaValorAnterior;
		this.colunaValorAtual = colunaValorAtual;
		this.indicadorAutorizado = indicadorAutorizado;
		this.dataValidacao = dataValidacao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.tabelaAtualizacaoCadastral = tabelaAtualizacaoCadastral;
		this.tabelaColuna = tabelaColuna;
	}

	/** default constructor */
    public TabelaColunaAtualizacaoCadastral() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getColunaValorAnterior() {
		return colunaValorAnterior;
	}

	public void setColunaValorAnterior(String colunaValorAnterior) {
		this.colunaValorAnterior = colunaValorAnterior;
	}

	public String getColunaValorAtual() {
		return colunaValorAtual;
	}

	public void setColunaValorAtual(String colunaValorAtual) {
		this.colunaValorAtual = colunaValorAtual;
	}

	public Date getDataValidacao() {
		return dataValidacao;
	}

	public void setDataValidacao(Date dataValidacao) {
		this.dataValidacao = dataValidacao;
	}

	public Short getIndicadorAutorizado() {
		return indicadorAutorizado;
	}

	public void setIndicadorAutorizado(Short indicadorAutorizado) {
		this.indicadorAutorizado = indicadorAutorizado;
	}

	public TabelaAtualizacaoCadastral getTabelaAtualizacaoCadastral() {
		return tabelaAtualizacaoCadastral;
	}

	public void setTabelaAtualizacaoCadastral(
			TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral) {
		this.tabelaAtualizacaoCadastral = tabelaAtualizacaoCadastral;
	}

	public TabelaColuna getTabelaColuna() {
		return tabelaColuna;
	}

	public void setTabelaColuna(TabelaColuna tabelaColuna) {
		this.tabelaColuna = tabelaColuna;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isRegistroInclusao() {
		return this.registroInclusao;
	}

	public void setRegistroInclusao(boolean registroInclusao) {
		this.registroInclusao = registroInclusao;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
}
