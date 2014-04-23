package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.action.ActionForm;

/**
 * [UC0217] Inserir Resolução de Diretoria
 * @author Rafael Corrêa
 * @since 30/03/2006
 */
public class FiltrarEquipeActionForm  extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String codigo;
	
	private String nome;
	
	private String placa;
	
	private String cargaTrabalho;
	
	private String idUnidade;
	
	private String nomeUnidade;
	
	private String idFuncionario;
	
	private String nomeFuncionario;
	
	private String idPerfilServico;
	
	private String descricaoPerfilServico;
	
	private String indicadorUso;
	
	private String atualizar;
	
	private String tipoPesquisa;
	
	private String codigoDdd;
    private String numeroTelefone;
    private String numeroImei;
    
    private String equipamentosEspeciasId;
    
    private String cdUsuarioRespExecServico;
    
    private String nomeUsuarioRespExecServico;
    
    private String indicadorProgramacaoAutomatica;
    

	/**
	 * @return Retorna o campo atualizar.
	 */
	public String getAtualizar() {
		return atualizar;
	}

	/**
	 * @param atualizar O atualizar a ser setado.
	 */
	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

	/**
	 * @return Retorna o campo cargaTrabalho.
	 */
	public String getCargaTrabalho() {
		return cargaTrabalho;
	}

	/**
	 * @param cargaTrabalho O cargaTrabalho a ser setado.
	 */
	public void setCargaTrabalho(String cargaTrabalho) {
		this.cargaTrabalho = cargaTrabalho;
	}

	/**
	 * @return Retorna o campo codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo O codigo a ser setado.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Retorna o campo desricaoPerfilServico.
	 */
	public String getDescricaoPerfilServico() {
		return descricaoPerfilServico;
	}

	/**
	 * @param desricaoPerfilServico O desricaoPerfilServico a ser setado.
	 */
	public void setDescricaoPerfilServico(String descricaoPerfilServico) {
		this.descricaoPerfilServico = descricaoPerfilServico;
	}

	/**
	 * @return Retorna o campo idFuncionario.
	 */
	public String getIdFuncionario() {
		return idFuncionario;
	}

	/**
	 * @param idFuncionario O idFuncionario a ser setado.
	 */
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	/**
	 * @return Retorna o campo idPerfilServico.
	 */
	public String getIdPerfilServico() {
		return idPerfilServico;
	}

	/**
	 * @param idPerfilServico O idPerfilServico a ser setado.
	 */
	public void setIdPerfilServico(String idPerfilServico) {
		this.idPerfilServico = idPerfilServico;
	}

	/**
	 * @return Retorna o campo idUnidade.
	 */
	public String getIdUnidade() {
		return idUnidade;
	}

	/**
	 * @param idUnidade O idUnidade a ser setado.
	 */
	public void setIdUnidade(String idUnidade) {
		this.idUnidade = idUnidade;
	}

	/**
	 * @return Retorna o campo nome.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome O nome a ser setado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Retorna o campo nomeFuncionario.
	 */
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	/**
	 * @param nomeFuncionario O nomeFuncionario a ser setado.
	 */
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	/**
	 * @return Retorna o campo nomeUnidade.
	 */
	public String getNomeUnidade() {
		return nomeUnidade;
	}

	/**
	 * @param nomeUnidade O nomeUnidade a ser setado.
	 */
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	/**
	 * @return Retorna o campo placa.
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa O placa a ser setado.
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * @return Retorna o campo indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}

	/**
	 * @param indicadorUso O indicadorUso a ser setado.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getCodigoDdd() {
		return codigoDdd;
	}

	public void setCodigoDdd(String codigoDdd) {
		this.codigoDdd = codigoDdd;
	}

	public String getNumeroImei() {
		return numeroImei;
	}

	public void setNumeroImei(String numeroImei) {
		this.numeroImei = numeroImei;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getEquipamentosEspeciasId() {
		return equipamentosEspeciasId;
	}

	public void setEquipamentosEspeciasId(String equipamentosEspeciasId) {
		this.equipamentosEspeciasId = equipamentosEspeciasId;
	}

	public String getCdUsuarioRespExecServico() {
		return cdUsuarioRespExecServico;
	}

	public void setCdUsuarioRespExecServico(String cdUsuarioRespExecServico) {
		this.cdUsuarioRespExecServico = cdUsuarioRespExecServico;
	}

	public String getNomeUsuarioRespExecServico() {
		return nomeUsuarioRespExecServico;
	}

	public void setNomeUsuarioRespExecServico(String nomeUsuarioRespExecServico) {
		this.nomeUsuarioRespExecServico = nomeUsuarioRespExecServico;
	}

	public String getIndicadorProgramacaoAutomatica() {
		return indicadorProgramacaoAutomatica;
	}

	public void setIndicadorProgramacaoAutomatica(
			String indicadorProgramacaoAutomatica) {
		this.indicadorProgramacaoAutomatica = indicadorProgramacaoAutomatica;
	}
}
