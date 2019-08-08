package gcom.seguranca.transacao;

import gcom.interceptor.ObjetoGcom;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;

import java.util.Date;

public class TabelaColunaAtualizacaoCadastral extends ObjetoGcom {
	private static final long serialVersionUID = 1L;
	
	public static String VALOR_CAMPO_PRE_APROVADO = "preaprovado";
	public static String VALOR_CAMPO_FISCALIZADO = "fiscalizado";

    private Integer id;
    private String colunaValorAnterior;
    private String colunaValorTransmitido;
    private String colunaValorRevisado;
    private String colunaValorFiscalizado;
    private String colunaValorPreAprovado;
    private Short indicadorAutorizado;
    private Date dataValidacao;
    private Date ultimaAlteracao;
    private TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral;
    private TabelaColuna tabelaColuna;
	private Usuario usuario;
	private boolean registroInclusao;
	private Short indicadorFiscalizado;
	private String complemento;
    
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
    
    public TabelaColunaAtualizacaoCadastral(Integer id, String colunaValorAnterior, String colunaValorTransmitido, Short indicadorAutorizado, 
    		Date dataValidacao, Date ultimaAlteracao, TabelaAtualizacaoCadastral tabelaAtualizacaoCadastral, TabelaColuna tabelaColuna, String complemento) {

		this.id = id;
		this.colunaValorAnterior = colunaValorAnterior;
		this.colunaValorTransmitido = colunaValorTransmitido;
		this.indicadorAutorizado = indicadorAutorizado;
		this.dataValidacao = dataValidacao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.tabelaAtualizacaoCadastral = tabelaAtualizacaoCadastral;
		this.tabelaColuna = tabelaColuna;
		this.complemento = complemento;
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
	
	public String getColunaValorPreAprovado() {
		return colunaValorPreAprovado;
	}

	public void setColunaValorPreAprovado(String colunaValorPreAprovado) {
		this.colunaValorPreAprovado = colunaValorPreAprovado;
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

	public Short getIndicadorFiscalizado() {
		return indicadorFiscalizado;
	}

	public void setIndicadorFiscalizado(Short indicadorFiscalizado) {
		this.indicadorFiscalizado = indicadorFiscalizado;
	}

	public String getValorAtualizarRetorno() {
		if (this.indicadorFiscalizado != null && this.indicadorFiscalizado.equals(ConstantesSistema.SIM))
			return this.colunaValorFiscalizado;
		else 
			return this.colunaValorPreAprovado;
	}
	
	public void setValorAtualizarRetorno(String valor) {
		if (this.indicadorFiscalizado != null && this.indicadorFiscalizado.equals(ConstantesSistema.SIM))
			this.colunaValorFiscalizado = valor;
		else 
			this.colunaValorPreAprovado = valor;
	}
	
	public String obterValorParaAtualizar(String campo) {
		if (campo.equals("preaprovado"))
			return this.colunaValorPreAprovado;
		else
			if (this.indicadorFiscalizado != null)
				return this.colunaValorFiscalizado;
			else
				return this.colunaValorPreAprovado;
	}
	
	public boolean isTipoColuna(Integer tipo) {
		return tabelaAtualizacaoCadastral != null 
				&& this.tabelaAtualizacaoCadastral.getTabela() != null
				&& this.tabelaAtualizacaoCadastral.getTabela().getId().intValue() == tipo.intValue();
	}
	
	public String getComplemento() {
		return this.complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public boolean possuiValorFiscalizado() {
        return this.colunaValorFiscalizado != null && !this.colunaValorFiscalizado.equals(ConstantesSistema.ZERO.toString());
    }
	
	public boolean possuiValorPreAprovado() {
        return this.colunaValorPreAprovado != null && !this.colunaValorPreAprovado.equals(ConstantesSistema.ZERO.toString());
    }
	
	public boolean possuiValorAnterior() {
        return this.colunaValorAnterior != null && !this.colunaValorAnterior.equals(ConstantesSistema.ZERO.toString());
    }
}
