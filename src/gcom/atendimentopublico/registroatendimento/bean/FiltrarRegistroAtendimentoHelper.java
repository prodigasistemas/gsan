package gcom.atendimentopublico.registroatendimento.bean;

import gcom.atendimentopublico.registroatendimento.RegistroAtendimento;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoSolicitante;
import gcom.atendimentopublico.registroatendimento.RegistroAtendimentoUnidade;
import gcom.cadastro.unidade.UnidadeOrganizacional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;


/**
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * de registro de atendimento
 *
 * @author Leonardo Regis
 * @date 08/09/2006
 */
public class FiltrarRegistroAtendimentoHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private RegistroAtendimento registroAtendimento = null;
	private UnidadeOrganizacional unidadeAtendimento = null;
	private UnidadeOrganizacional unidadeAtual = null;
	private UnidadeOrganizacional unidadeSuperior = null;
	private Date dataAtendimentoInicial = null;
	private Date dataAtendimentoFinal = null;
	private Date dataEncerramentoInicial = null;
	private Date dataEncerramentoFinal = null;
	private Date dataTramitacaoInicial = null;
	private Date dataTramitacaoFinal = null;
	private Collection<Integer> colecaoTipoSolicitacaoEspecificacao = null;
	private Collection<Integer> colecaoTipoSolicitacao = null;
	private Collection<Integer> colecaoPerfilImovel = null;
	private Collection<Integer> colecaoAtendimentoMotivoEncerramento = null;	
	private Set colecaoRAPorUnidades = null;
	private String municipioId = null;
	private String bairroId = null;
	private String bairroCodigo = null;
	private String logradouroId = null;
	private Integer numeroPagina = null;
	private Integer quantidadeRAReiteradasIncial = null;
	private Integer quantidadeRAReiteradasFinal = null;
	private Collection<Integer> collectionIdsUnidadeAtual = null;
	
	private RegistroAtendimentoUnidade registroAtendimentoUnidade = null;
	private RegistroAtendimentoSolicitante registroAtendimentoSolicitante = null;
	private UnidadeOrganizacional unidadeAnterior = null;
	
	public FiltrarRegistroAtendimentoHelper() {
	}
	
	

	public Date getDataTramitacaoFinal() {
		return dataTramitacaoFinal;
	}



	public void setDataTramitacaoFinal(Date dataTramitacaoFinal) {
		this.dataTramitacaoFinal = dataTramitacaoFinal;
	}



	public Date getDataTramitacaoInicial() {
		return dataTramitacaoInicial;
	}



	public void setDataTramitacaoInicial(Date dataTramitacaoInicial) {
		this.dataTramitacaoInicial = dataTramitacaoInicial;
	}



	public String getBairroId() {
		return bairroId;
	}

	public void setBairroId(String bairroId) {
		this.bairroId = bairroId;
	}

	public Collection<Integer> getColecaoTipoSolicitacaoEspecificacao() {
		return colecaoTipoSolicitacaoEspecificacao;
	}

	public void setColecaoTipoSolicitacaoEspecificacao(
			Collection<Integer> colecaoTipoSolicitacaoEspecificacao) {
		this.colecaoTipoSolicitacaoEspecificacao = colecaoTipoSolicitacaoEspecificacao;
	}

	public Date getDataAtendimentoFinal() {
		return dataAtendimentoFinal;
	}

	public void setDataAtendimentoFinal(Date dataAtendimentoFinal) {
		this.dataAtendimentoFinal = dataAtendimentoFinal;
	}

	public Date getDataAtendimentoInicial() {
		return dataAtendimentoInicial;
	}

	public void setDataAtendimentoInicial(Date dataAtendimentoInicial) {
		this.dataAtendimentoInicial = dataAtendimentoInicial;
	}

	public Date getDataEncerramentoFinal() {
		return dataEncerramentoFinal;
	}

	public void setDataEncerramentoFinal(Date dataEncerramentoFinal) {
		this.dataEncerramentoFinal = dataEncerramentoFinal;
	}

	public Date getDataEncerramentoInicial() {
		return dataEncerramentoInicial;
	}

	public void setDataEncerramentoInicial(Date dataEncerramentoInicial) {
		this.dataEncerramentoInicial = dataEncerramentoInicial;
	}

	public String getLogradouroId() {
		return logradouroId;
	}

	public void setLogradouroId(String logradouroId) {
		this.logradouroId = logradouroId;
	}

	public String getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(String municipioId) {
		this.municipioId = municipioId;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public RegistroAtendimento getRegistroAtendimento() {
		return registroAtendimento;
	}

	public void setRegistroAtendimento(RegistroAtendimento registroAtendimento) {
		this.registroAtendimento = registroAtendimento;
	}

	public UnidadeOrganizacional getUnidadeAtendimento() {
		return unidadeAtendimento;
	}

	public void setUnidadeAtendimento(UnidadeOrganizacional unidadeAtendimento) {
		this.unidadeAtendimento = unidadeAtendimento;
	}

	public UnidadeOrganizacional getUnidadeAtual() {
		return unidadeAtual;
	}

	public void setUnidadeAtual(UnidadeOrganizacional unidadeAtual) {
		this.unidadeAtual = unidadeAtual;
	}

	public UnidadeOrganizacional getUnidadeSuperior() {
		return unidadeSuperior;
	}

	public void setUnidadeSuperior(UnidadeOrganizacional unidadeSuperior) {
		this.unidadeSuperior = unidadeSuperior;
	}

	public Set getColecaoRAPorUnidades() {
		return colecaoRAPorUnidades;
	}

	public void setColecaoRAPorUnidades(Set colecaoRAPorUnidades) {
		this.colecaoRAPorUnidades = colecaoRAPorUnidades;
	}

	public Collection<Integer> getColecaoTipoSolicitacao() {
		return colecaoTipoSolicitacao;
	}

	public void setColecaoTipoSolicitacao(Collection<Integer> colecaoTipoSolicitacao) {
		this.colecaoTipoSolicitacao = colecaoTipoSolicitacao;
	}



	public RegistroAtendimentoUnidade getRegistroAtendimentoUnidade() {
		return registroAtendimentoUnidade;
	}



	public void setRegistroAtendimentoUnidade(
			RegistroAtendimentoUnidade registroAtendimentoUnidade) {
		this.registroAtendimentoUnidade = registroAtendimentoUnidade;
	}



	public String getBairroCodigo() {
		return bairroCodigo;
	}



	public void setBairroCodigo(String bairroCodigo) {
		this.bairroCodigo = bairroCodigo;
	}



	/**
	 * @return Retorna o campo quantidadeRAReiteradasFinal.
	 */
	public Integer getQuantidadeRAReiteradasFinal() {
		return quantidadeRAReiteradasFinal;
	}



	/**
	 * @param quantidadeRAReiteradasFinal O quantidadeRAReiteradasFinal a ser setado.
	 */
	public void setQuantidadeRAReiteradasFinal(Integer quantidadeRAReiteradasFinal) {
		this.quantidadeRAReiteradasFinal = quantidadeRAReiteradasFinal;
	}



	/**
	 * @return Retorna o campo quantidadeRAReiteradasIncial.
	 */
	public Integer getQuantidadeRAReiteradasIncial() {
		return quantidadeRAReiteradasIncial;
	}



	/**
	 * @param quantidadeRAReiteradasIncial O quantidadeRAReiteradasIncial a ser setado.
	 */
	public void setQuantidadeRAReiteradasIncial(Integer quantidadeRAReiteradasIncial) {
		this.quantidadeRAReiteradasIncial = quantidadeRAReiteradasIncial;
	}



	public Collection<Integer> getCollectionIdsUnidadeAtual() {
		return collectionIdsUnidadeAtual;
	}



	public void setCollectionIdsUnidadeAtual(
			Collection<Integer> collectionIdsUnidadeAtual) {
		this.collectionIdsUnidadeAtual = collectionIdsUnidadeAtual;
	}



	public Collection<Integer> getColecaoAtendimentoMotivoEncerramento() {
		return colecaoAtendimentoMotivoEncerramento;
	}



	public void setColecaoAtendimentoMotivoEncerramento(
			Collection<Integer> colecaoAtendimentoMotivoEncerramento) {
		this.colecaoAtendimentoMotivoEncerramento = colecaoAtendimentoMotivoEncerramento;
	}



	public RegistroAtendimentoSolicitante getRegistroAtendimentoSolicitante() {
		return registroAtendimentoSolicitante;
	}



	public void setRegistroAtendimentoSolicitante(
			RegistroAtendimentoSolicitante registroAtendimentoSolicitante) {
		this.registroAtendimentoSolicitante = registroAtendimentoSolicitante;
	}



	public Collection<Integer> getColecaoPerfilImovel() {
		return colecaoPerfilImovel;
	}



	public void setColecaoPerfilImovel(Collection<Integer> colecaoPerfilImovel) {
		this.colecaoPerfilImovel = colecaoPerfilImovel;
	}



	public UnidadeOrganizacional getUnidadeAnterior() {
		return unidadeAnterior;
	}



	public void setUnidadeAnterior(UnidadeOrganizacional unidadeAnterior) {
		this.unidadeAnterior = unidadeAnterior;
	}
	
	
}
