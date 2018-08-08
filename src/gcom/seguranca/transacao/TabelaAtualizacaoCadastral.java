package gcom.seguranca.transacao;

import gcom.cadastro.ArquivoTextoAtualizacaoCadastral;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.OperacaoEfetuada;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/** @author Hibernate CodeGenerator */
public class TabelaAtualizacaoCadastral {
	private Integer id;
	
	private Long idRegistroAlterado;
	
	private Short indicadorPrincipal;
	
    private Date ultimaAlteracao;
	
	private Tabela tabela;
	
	private OperacaoEfetuada operacaoEfetuada;
	
	private Leiturista leiturista;
	
	private AlteracaoTipo alteracaoTipo;
	
	private ArquivoTextoAtualizacaoCadastral arquivoTextoAtualizacaoCadastral;
	
    private Short indicadorAutorizado;
    
    private Integer codigoImovel;
    
    private Long codigoCliente;
    
    private Date dataProcessamento;
    
    private TabelaAtualizacaoCadastralSituacao tabelaAtualizacaoCadastralSituacao;
    
    private String complemento;
    
    private boolean registroInclusao;

	public TabelaAtualizacaoCadastral(Integer id, Long idRegistroAlterado, Short indicadorPrincipal, Date ultimaAlteracao, Tabela tabela, OperacaoEfetuada operacaoEfetuada, Leiturista leiturista, AlteracaoTipo alteracaoTipo, ArquivoTextoAtualizacaoCadastral arquivoTextoAtualizacaoCadastral, Short indicadorAutorizado, Integer codigoImovel, Long codigoCliente, String complemento) {
		this.id = id;
		this.idRegistroAlterado = idRegistroAlterado;
		this.indicadorPrincipal = indicadorPrincipal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.tabela = tabela;
		this.operacaoEfetuada = operacaoEfetuada;
		this.leiturista = leiturista;
		this.alteracaoTipo = alteracaoTipo;
		this.arquivoTextoAtualizacaoCadastral = arquivoTextoAtualizacaoCadastral;
		this.indicadorAutorizado = indicadorAutorizado;
		this.codigoImovel = codigoImovel;
		this.codigoCliente = codigoCliente;
		this.complemento = complemento;
	}

	/** default constructor */
    public TabelaAtualizacaoCadastral() {
    }

 
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof TabelaAtualizacaoCadastral))
			return false;
		TabelaAtualizacaoCadastral castOther = (TabelaAtualizacaoCadastral) other;
		return new EqualsBuilder().append(this.getId(),
				castOther.getId()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}
	
	 public String[] retornaCamposChavePrimaria(){
			String[] retorno = new String[1];
			retorno[0] = "id";
			return retorno;
	}

	public AlteracaoTipo getAlteracaoTipo() {
		return alteracaoTipo;
	}

	public void setAlteracaoTipo(AlteracaoTipo alteracaoTipo) {
		this.alteracaoTipo = alteracaoTipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getIdRegistroAlterado() {
		return idRegistroAlterado;
	}

	public void setIdRegistroAlterado(Long idRegistroAlterado) {
		this.idRegistroAlterado = idRegistroAlterado;
	}

	public Short getIndicadorPrincipal() {
		return indicadorPrincipal;
	}

	public void setIndicadorPrincipal(Short indicadorPrincipal) {
		this.indicadorPrincipal = indicadorPrincipal;
	}

	public Leiturista getLeiturista() {
		return leiturista;
	}

	public void setLeiturista(Leiturista leiturista) {
		this.leiturista = leiturista;
	}

	public OperacaoEfetuada getOperacaoEfetuada() {
		return operacaoEfetuada;
	}

	public void setOperacaoEfetuada(OperacaoEfetuada operacaoEfetuada) {
		this.operacaoEfetuada = operacaoEfetuada;
	}

	public Tabela getTabela() {
		return tabela;
	}

	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public ArquivoTextoAtualizacaoCadastral getArquivoTextoAtualizacaoCadastral() {
		return arquivoTextoAtualizacaoCadastral;
	}

	public void setArquivoTextoAtualizacaoCadastral(
			ArquivoTextoAtualizacaoCadastral arquivoTextoAtualizacaoCadastral) {
		this.arquivoTextoAtualizacaoCadastral = arquivoTextoAtualizacaoCadastral;
	}

	public Short getIndicadorAutorizado() {
		return indicadorAutorizado;
	}

	public void setIndicadorAutorizado(Short indicadorAutorizado) {
		this.indicadorAutorizado = indicadorAutorizado;
	}
	
	public Long getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Integer getCodigoImovel() {
		return codigoImovel;
	}

	public void setCodigoImovel(Integer codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public TabelaAtualizacaoCadastralSituacao getTabelaAtualizacaoCadastralSituacao() {
		return tabelaAtualizacaoCadastralSituacao;
	}

	public void setTabelaAtualizacaoCadastralSituacao(
			TabelaAtualizacaoCadastralSituacao tabelaAtualizacaoCadastralSituacao) {
		this.tabelaAtualizacaoCadastralSituacao = tabelaAtualizacaoCadastralSituacao;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public boolean isRegistroInclusao() {
		return registroInclusao;
	}

	public void setRegistroInclusao(boolean registroInclusao) {
		this.registroInclusao = registroInclusao;
	}
	
}
