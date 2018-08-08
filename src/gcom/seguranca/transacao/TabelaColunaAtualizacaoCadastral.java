package gcom.seguranca.transacao;

import gcom.interceptor.ObjetoGcom;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Date;

public class TabelaColunaAtualizacaoCadastral extends ObjetoGcom {
	private static final long serialVersionUID = 1L;

    private Integer id;
    private String colunaValorAnterior;
    private String colunaValorTransmitido;
    private String colunaValorRevisado;
    private String colunaValorFiscalizado;
    private Short indicadorAutorizado;
    private Date dataValidacao;
    private Date ultimaAlteracao;
    private TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral;
    private TabelaColuna tabelaColuna;
	private Usuario usuario;
	private boolean registroInclusao;
    
    public TabelaColunaAtualizacaoCadastral(Integer id, String colunaValorAnterior, String colunaValorTransmitido, Short indicadorAutorizado, 
    		Date dataValidacao, Date ultimaAlteracao, TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral, TabelaColuna tabelaColuna) {

		this.id = id;
		this.colunaValorAnterior = colunaValorAnterior;
		this.colunaValorTransmitido = colunaValorTransmitido;
		this.indicadorAutorizado = indicadorAutorizado;
		this.dataValidacao = dataValidacao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.tabelaAtualizacaoCadastral = tabelaAtualizacaoCadastral;
		this.tabelaColuna = tabelaColuna;
	}

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

	public String getColunaValorTransmitido() {
		return colunaValorTransmitido;
	}

	public void setColunaValorTransmitido(String colunaValorTransmitido) {
		this.colunaValorTransmitido = colunaValorTransmitido;
	}

	public String getColunaValorRevisado() {
		return colunaValorRevisado;
	}

	public void setColunaValorRevisado(String colunaValorRevisado) {
		this.colunaValorRevisado = colunaValorRevisado;
	}

	public String getColunaValorFiscalizado() {
		return colunaValorFiscalizado;
	}

	public void setColunaValorFiscalizado(String colunaValorFiscalizado) {
		this.colunaValorFiscalizado = colunaValorFiscalizado;
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

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}

	public boolean isRegistroInclusao() {
		return registroInclusao;
	}

	public void setRegistroInclusao(boolean registroInclusao) {
		this.registroInclusao = registroInclusao;
	}
}
