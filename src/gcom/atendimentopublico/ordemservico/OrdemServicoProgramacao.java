package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class OrdemServicoProgramacao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;

	/** identifier field */
	private Integer id;

	/** persistent field */
	private short nnSequencialProgramacao;

	/** persistent field */
	private short indicadorAtivo;

	/** persistent field */
	private short indicadorEquipePrincipal;

	/** persistent field */
	private Date ultimaAlteracao;

	/** persistent field */
	private Equipe equipe;

	/** persistent field */
	private Usuario usuarioProgramacao;

	/** persistent field */
	private Usuario usuarioFechamento;

	/** persistent field */
	private ProgramacaoRoteiro programacaoRoteiro;

	/** persistent field */
	private OsProgramNaoEncerMotivo osProgramNaoEncerMotivo;
	
	private EquipamentosEspeciais equipamentoEspecialFaltante;

	/** persistent field */
	private OrdemServico ordemServico;

	private Short situacaoFechamento;
	
	private short indicadorAcompanhamentoServico;

	public final static Short INDICADOR_ATIVO = new Short("1");

	public final static Short INDICADOR_ATIVO_NAO = new Short("2");
	
	// Caso a Ordem de Serviço tenha sido realocada para outra Equipe, então o indicador de 
	// acompanhamento de serviço fica igual a 3.
	public final static Short INDICADOR_ACOMP_SERV_REALOCADA = new Short("3");

	public final static Short SITUACAO_FECHAMENTO = new Short("2");
	
	public final static Short SITUACAO_VAZIO = new Short("0");
	
	public final static Short EQUIPE_PRINCIPAL = new Short("1");

	public OrdemServicoProgramacao() {
	}

	/** full constructor */
	public OrdemServicoProgramacao(Integer id, short nnSequencialProgramacao,
			short indicadorAtivo, short indicadorEquipePrincipal,
			Date ultimaAlteracao, Equipe equipe, Usuario usuarioProgramacao,
			Usuario usuarioFechamento, ProgramacaoRoteiro programacaoRoteiro,
			OsProgramNaoEncerMotivo osProgramNaoEncerMotivo,
			OrdemServico ordemServico) {
		this.id = id;
		this.nnSequencialProgramacao = nnSequencialProgramacao;
		this.indicadorAtivo = indicadorAtivo;
		this.indicadorEquipePrincipal = indicadorEquipePrincipal;
		this.ultimaAlteracao = ultimaAlteracao;
		this.equipe = equipe;
		this.usuarioProgramacao = usuarioProgramacao;
		this.usuarioFechamento = usuarioFechamento;
		this.programacaoRoteiro = programacaoRoteiro;
		this.osProgramNaoEncerMotivo = osProgramNaoEncerMotivo;
		this.ordemServico = ordemServico;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getIndicadorAtivo() {
		return indicadorAtivo;
	}

	public void setIndicadorAtivo(short indicadorAtivo) {
		this.indicadorAtivo = indicadorAtivo;
	}

	public short getIndicadorEquipePrincipal() {
		return indicadorEquipePrincipal;
	}

	public void setIndicadorEquipePrincipal(short indicadorEquipePrincipal) {
		this.indicadorEquipePrincipal = indicadorEquipePrincipal;
	}

	public short getNnSequencialProgramacao() {
		return nnSequencialProgramacao;
	}

	public void setNnSequencialProgramacao(short nnSequencialProgramacao) {
		this.nnSequencialProgramacao = nnSequencialProgramacao;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public OsProgramNaoEncerMotivo getOsProgramNaoEncerMotivo() {
		return osProgramNaoEncerMotivo;
	}

	public void setOsProgramNaoEncerMotivo(
			OsProgramNaoEncerMotivo osProgramNaoEncerMotivo) {
		this.osProgramNaoEncerMotivo = osProgramNaoEncerMotivo;
	}

	public ProgramacaoRoteiro getProgramacaoRoteiro() {
		return programacaoRoteiro;
	}

	public void setProgramacaoRoteiro(ProgramacaoRoteiro programacaoRoteiro) {
		this.programacaoRoteiro = programacaoRoteiro;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuarioFechamento() {
		return usuarioFechamento;
	}

	public void setUsuarioFechamento(Usuario usuarioFechamento) {
		this.usuarioFechamento = usuarioFechamento;
	}

	public Usuario getUsuarioProgramacao() {
		return usuarioProgramacao;
	}

	public void setUsuarioProgramacao(Usuario usuarioProgramacao) {
		this.usuarioProgramacao = usuarioProgramacao;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public Short getSituacaoFechamento() {
		return situacaoFechamento;
	}

	public void setSituacaoFechamento(Short situacaoFechamento) {
		this.situacaoFechamento = situacaoFechamento;
	}

	public EquipamentosEspeciais getEquipamentoEspecialFaltante() {
		return equipamentoEspecialFaltante;
	}

	public void setEquipamentoEspecialFaltante(
			EquipamentosEspeciais equipamentoEspecialFaltante) {
		this.equipamentoEspecialFaltante = equipamentoEspecialFaltante;
	}

	public short getIndicadorAcompanhamentoServico() {
		return indicadorAcompanhamentoServico;
	}

	public void setIndicadorAcompanhamentoServico(
			short indicadorAcompanhamentoServico) {
		this.indicadorAcompanhamentoServico = indicadorAcompanhamentoServico;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroOrdemServicoProgramacao filtroOrdemServicoProgramacao = new FiltroOrdemServicoProgramacao();
		
		filtroOrdemServicoProgramacao.adicionarCaminhoParaCarregamentoEntidade("equipe");
		filtroOrdemServicoProgramacao.adicionarCaminhoParaCarregamentoEntidade("usuarioProgramacao");
		filtroOrdemServicoProgramacao.adicionarCaminhoParaCarregamentoEntidade("usuarioFechamento");
		filtroOrdemServicoProgramacao.adicionarCaminhoParaCarregamentoEntidade("programacaoRoteiro");
		filtroOrdemServicoProgramacao.adicionarCaminhoParaCarregamentoEntidade("osProgramNaoEncerMotivo");
		filtroOrdemServicoProgramacao.adicionarCaminhoParaCarregamentoEntidade("ordemServico");
		filtroOrdemServicoProgramacao.adicionarParametro(
				new ParametroSimples(FiltroOrdemServicoProgramacao.ID, this.getId()));
		
		return filtroOrdemServicoProgramacao; 
	}
	
}
