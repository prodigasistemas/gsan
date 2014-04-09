package gcom.relatorio.seguranca;

import java.io.Serializable;
import java.util.Collection;


/**
 * [UC1093] Gerar Relatório Solicitação de Acesso
 * 
 * Classe que irá auxiliar no formato de entrada da pesquisa 
 * do relatorio Solicitação de Acesso.
 *
 * @author Hugo Leonardo
 * @date 23/11/2010
 */
public class FiltrarRelatorioSolicitacaoAcessoHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String idFuncionario; 
	private String idFuncionarioSolicitante; 
	private String idFuncionarioSuperior; 
	private String idEmpresa;
	private String nomeUsuario; 
	private String idLotacao; 
	private String dataInicial; 
	private String dataFinal;
	private Collection<Integer> idsSituacao;
	
	public String getDataFinal() {
		return dataFinal;
	}
	
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getIdFuncionarioSolicitante() {
		return idFuncionarioSolicitante;
	}

	public void setIdFuncionarioSolicitante(String idFuncionarioSolicitante) {
		this.idFuncionarioSolicitante = idFuncionarioSolicitante;
	}

	public String getIdFuncionarioSuperior() {
		return idFuncionarioSuperior;
	}

	public void setIdFuncionarioSuperior(String idFuncionarioSuperior) {
		this.idFuncionarioSuperior = idFuncionarioSuperior;
	}

	public String getIdLotacao() {
		return idLotacao;
	}

	public void setIdLotacao(String idLotacao) {
		this.idLotacao = idLotacao;
	}

	public Collection<Integer> getIdsSituacao() {
		return idsSituacao;
	}

	public void setIdsSituacao(Collection<Integer> idsSituacao) {
		this.idsSituacao = idsSituacao;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
}
