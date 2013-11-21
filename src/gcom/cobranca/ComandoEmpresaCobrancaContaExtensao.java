package gcom.cobranca;

import gcom.cadastro.funcionario.FiltroFuncionario;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

public class ComandoEmpresaCobrancaContaExtensao extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;
	
	
	/** nullable persistent field */
	private Integer referenciaContaInicial;
	
	/** nullable persistent field */
	private Integer referenciaContaFinal;
	
	/** nullable persistent field */
	private Date dataExecucao;
	
	/** nullable persistent field */
	private Date ultimaAlteracao;
	
	private Usuario usuario;
	
	private ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta;

	public Date getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReferenciaContaFinal() {
		return referenciaContaFinal;
	}

	public void setReferenciaContaFinal(Integer referenciaContaFinal) {
		this.referenciaContaFinal = referenciaContaFinal;
	}

	public Integer getReferenciaContaInicial() {
		return referenciaContaInicial;
	}

	public void setReferenciaContaInicial(Integer referenciaContaInicial) {
		this.referenciaContaInicial = referenciaContaInicial;
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

	/**
	 * Construtor de ComandoEmpresaCobrancaContaExtensao 
	 * 
	 * @param id
	 * @param referenciaContaInicial
	 * @param referenciaContaFinal
	 * @param dataExecucao
	 * @param ultimaAlteracao
	 * @param usuario
	 */
	public ComandoEmpresaCobrancaContaExtensao(Integer id, Integer referenciaContaInicial, Integer referenciaContaFinal, Date dataExecucao, Date ultimaAlteracao, Usuario usuario,ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.referenciaContaInicial = referenciaContaInicial;
		this.referenciaContaFinal = referenciaContaFinal;
		this.dataExecucao = dataExecucao;
		this.ultimaAlteracao = ultimaAlteracao;
		this.usuario = usuario;
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	@Override
	public Filtro retornaFiltro() {
		
		FiltroComandoEmpresaCobrancaContaExtensao filtroComandoEmpresaCobrancaContaExtensao = new FiltroComandoEmpresaCobrancaContaExtensao();
		
		filtroComandoEmpresaCobrancaContaExtensao. adicionarCaminhoParaCarregamentoEntidade("usuario");
		filtroComandoEmpresaCobrancaContaExtensao. adicionarCaminhoParaCarregamentoEntidade("comandoEmpresaCobrancaConta");
		filtroComandoEmpresaCobrancaContaExtensao. adicionarParametro(
				new ParametroSimples(FiltroFuncionario.ID, this.getId()));
		return filtroComandoEmpresaCobrancaContaExtensao; 
		
	}

	public ComandoEmpresaCobrancaConta getComandoEmpresaCobrancaConta() {
		return comandoEmpresaCobrancaConta;
	}

	public void setComandoEmpresaCobrancaConta(
			ComandoEmpresaCobrancaConta comandoEmpresaCobrancaConta) {
		this.comandoEmpresaCobrancaConta = comandoEmpresaCobrancaConta;
	}

	/**
	 * Construtor de ComandoEmpresaCobrancaContaExtensao 
	 * 
	 */
	public ComandoEmpresaCobrancaContaExtensao() {
		super();
		// TODO Auto-generated constructor stub
	}

}
