package gcom.faturamento.autoinfracao;

import gcom.atendimentopublico.ordemservico.FiscalizacaoSituacao;
import gcom.atendimentopublico.ordemservico.OrdemServico;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.debito.DebitoTipo;
import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

public class AutosInfracao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Date dataEmissao;
	
	private Date dataInicioRecurso;
	
	private Date dataTerminoRecurso;
	
	private Integer anoMesReferenciaGerada;
	
	private String observacao;
	
	private Integer numeroParcelasDebito;
	
	private Imovel imovel;
	
	private OrdemServico ordemServico;
	
	private Funcionario funcionario;
	
	private FiscalizacaoSituacao fiscalizacaoSituacao;
	
	private AutoInfracaoSituacao autoInfracaoSituacao;
	
	private Date ultimaAlteracao;
	
	private DebitoTipo debitoTipo;
	
	private Usuario usuario;
	
	public AutosInfracao(){}

	public Integer getAnoMesReferenciaGerada() {
		return anoMesReferenciaGerada;
	}

	public void setAnoMesReferenciaGerada(Integer anoMesReferenciaGerada) {
		this.anoMesReferenciaGerada = anoMesReferenciaGerada;
	}

	public AutoInfracaoSituacao getAutoInfracaoSituacao() {
		return autoInfracaoSituacao;
	}

	public void setAutoInfracaoSituacao(AutoInfracaoSituacao autoInfracaoSituacao) {
		this.autoInfracaoSituacao = autoInfracaoSituacao;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataInicioRecurso() {
		return dataInicioRecurso;
	}

	public void setDataInicioRecurso(Date dataInicioRecurso) {
		this.dataInicioRecurso = dataInicioRecurso;
	}

	public Date getDataTerminoRecurso() {
		return dataTerminoRecurso;
	}

	public void setDataTerminoRecurso(Date dataTerminoRecurso) {
		this.dataTerminoRecurso = dataTerminoRecurso;
	}

	public FiscalizacaoSituacao getFiscalizacaoSituacao() {
		return fiscalizacaoSituacao;
	}

	public void setFiscalizacaoSituacao(FiscalizacaoSituacao fiscalizacaoSituacao) {
		this.fiscalizacaoSituacao = fiscalizacaoSituacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public DebitoTipo getDebitoTipo() {
		return debitoTipo;
	}

	public void setDebitoTipo(DebitoTipo debitoTipo) {
		this.debitoTipo = debitoTipo;
	}

	
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroAutosInfracao filtroAutosInfracao = new FiltroAutosInfracao();
		
		filtroAutosInfracao. adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroAutosInfracao. adicionarCaminhoParaCarregamentoEntidade("ordemServico");
		filtroAutosInfracao. adicionarCaminhoParaCarregamentoEntidade("funcionario");
		filtroAutosInfracao. adicionarCaminhoParaCarregamentoEntidade("fiscalizacaoSituacao");
		filtroAutosInfracao. adicionarCaminhoParaCarregamentoEntidade("autoInfracaoSituacao");
		filtroAutosInfracao. adicionarCaminhoParaCarregamentoEntidade("debitoTipo");
		
		filtroAutosInfracao. adicionarParametro(
				new ParametroSimples(FiltroAutosInfracao.ID, this.getId()));
		return filtroAutosInfracao; 
	}

	/**
	 * @return Retorna o campo numeroParcelasDebito.
	 */
	public Integer getNumeroParcelasDebito() {
		return numeroParcelasDebito;
	}

	/**
	 * @param numeroParcelasDebito O numeroParcelasDebito a ser setado.
	 */
	public void setNumeroParcelasDebito(Integer numeroParcelasDebito) {
		this.numeroParcelasDebito = numeroParcelasDebito;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
