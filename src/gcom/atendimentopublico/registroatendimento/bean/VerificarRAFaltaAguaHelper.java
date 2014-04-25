package gcom.atendimentopublico.registroatendimento.bean;

import java.util.Collection;

/**
 * Esta classe tem por finalidade facilitar a forma como será retornado o
 * resultado do [UC0408] Atualizar Registro de Atendimento [SB0017] - Verificar
 * Registro Atendimento de Falta de Água
 * 
 * @author Sávio Luiz
 * @date 08/08/2006
 * 
 */
public class VerificarRAFaltaAguaHelper {

	private Integer idRAReferencia;

	private Integer idMotivoEncerramento;

	private String casoUso1;

	private String casoUso2;

	private String mensagem;

	// -------------------------------------------

	// parte do [SB0019] - Exibe Registros de Atendimento de Falta de A´gua no
	// imóvel da Área do Bairro

	private Integer idSolicitacaoTipo;

	private String descricaoSolicitacaoTipo;

	private Integer idSolicitacaoTipoEspecificacao;

	private String descricaoSolicitacaoTipoEspecificacao;

	private Integer codigoBairro;

	private String nomeBairro;

	private Integer idBairroArea;

	private String nomeBairroArea;

	private Collection colecaoExibirRAFaltaAguaHelper;

	// -------------------------------------------

	public final static String CONSULTAR_PROGRAMACAO = "UC0440";

	public final static String ENCERRAR_REGISTRO_ATENDIMENTO = "UC0435";

	public final static String INSERIR_REGISTRO_ATENDIMENTO = "UC0366";

	public final static String CONSULTAR_REGISTRO_ATENDIMENTO = "UC0448";

	public final static String EXIBIR_RA_FALTA_AGUA_IMOVEL = "UC0408/SB0019";
	
	public final static String EXIBIR_ADICIONAR_SOLICITANTE = "UC0366/SB0010";

	public VerificarRAFaltaAguaHelper() {
	}

	public Integer getIdMotivoEncerramento() {
		return idMotivoEncerramento;
	}

	public void setIdMotivoEncerramento(Integer idMotivoEncerramento) {
		this.idMotivoEncerramento = idMotivoEncerramento;
	}

	public Integer getIdRAReferencia() {
		return idRAReferencia;
	}

	public void setIdRAReferencia(Integer idRAReferencia) {
		this.idRAReferencia = idRAReferencia;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCasoUso1() {
		return casoUso1;
	}

	public void setCasoUso1(String casoUso1) {
		this.casoUso1 = casoUso1;
	}

	public String getCasoUso2() {
		return casoUso2;
	}

	public void setCasoUso2(String casoUso2) {
		this.casoUso2 = casoUso2;
	}

	public Integer getCodigoBairro() {
		return codigoBairro;
	}

	public void setCodigoBairro(Integer codigoBairro) {
		this.codigoBairro = codigoBairro;
	}

	public Collection getColecaoExibirRAFaltaAguaHelper() {
		return colecaoExibirRAFaltaAguaHelper;
	}

	public void setColecaoExibirRAFaltaAguaHelper(
			Collection colecaoExibirRAFaltaAguaHelper) {
		this.colecaoExibirRAFaltaAguaHelper = colecaoExibirRAFaltaAguaHelper;
	}

	public String getDescricaoSolicitacaoTipo() {
		return descricaoSolicitacaoTipo;
	}

	public void setDescricaoSolicitacaoTipo(String descricaoSolicitacaoTipo) {
		this.descricaoSolicitacaoTipo = descricaoSolicitacaoTipo;
	}

	public String getDescricaoSolicitacaoTipoEspecificacao() {
		return descricaoSolicitacaoTipoEspecificacao;
	}

	public void setDescricaoSolicitacaoTipoEspecificacao(
			String descricaoSolicitacaoTipoEspecificacao) {
		this.descricaoSolicitacaoTipoEspecificacao = descricaoSolicitacaoTipoEspecificacao;
	}

	public Integer getIdBairroArea() {
		return idBairroArea;
	}

	public void setIdBairroArea(Integer idBairroArea) {
		this.idBairroArea = idBairroArea;
	}

	public Integer getIdSolicitacaoTipo() {
		return idSolicitacaoTipo;
	}

	public void setIdSolicitacaoTipo(Integer idSolicitacaoTipo) {
		this.idSolicitacaoTipo = idSolicitacaoTipo;
	}

	public Integer getIdSolicitacaoTipoEspecificacao() {
		return idSolicitacaoTipoEspecificacao;
	}

	public void setIdSolicitacaoTipoEspecificacao(
			Integer idSolicitacaoTipoEspecificacao) {
		this.idSolicitacaoTipoEspecificacao = idSolicitacaoTipoEspecificacao;
	}

	public String getNomeBairro() {
		return nomeBairro;
	}

	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	public String getNomeBairroArea() {
		return nomeBairroArea;
	}

	public void setNomeBairroArea(String nomeBairroArea) {
		this.nomeBairroArea = nomeBairroArea;
	}
	
	

}
