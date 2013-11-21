/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gestão de Serviços de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Araújo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cláudio de Andrade Lira
* Denys Guimarães Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fabíola Gomes de Araújo
* Flávio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento Júnior
* Homero Sampaio Cavalcanti
* Ivan Sérgio da Silva Júnior
* José Edmar de Siqueira
* José Thiago Tenório Lopes
* Kássia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* Márcio Roberto Batista da Silva
* Maria de Fátima Sampaio Leite
* Micaela Maria Coelho de Araújo
* Nelson Mendonça de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corrêa Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Araújo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* Sávio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa é software livre; você pode redistribuí-lo e/ou
* modificá-lo sob os termos de Licença Pública Geral GNU, conforme
* publicada pela Free Software Foundation; versão 2 da
* Licença.
* Este programa é distribuído na expectativa de ser útil, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia implícita de
* COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
* PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
* detalhes.
* Você deve ter recebido uma cópia da Licença Pública Geral GNU
* junto com este programa; se não, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.seguranca.acesso.usuario;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * 
 * Form que exibe o menu
 * 
 * @author Sávio Luiz
 * @date 02/05/2006
 */
public class FiltrarUsuarioActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String usuarioTipo = "";

	private String empresa = "";

	private String idFuncionario = "";

	private String nomeFuncionario = "";

	private String nome = "";

	private String funcionarioNaoEncontrada = "false";

	private String idLotacao = "";

	private String nomeLotacao = "";

	private String lotacaoNaoEncontrada = "false";

	private String dataCadastramentoInicial = "";

	private String dataCadastramentoFinal = "";

	private String dataExpiracaoInicial = "";

	private String dataExpiracaoFinal = "";

	private String[] grupo = null;
	
	private String abrangencia = "";

	private String gerenciaRegional = "";

	private String idElo = "";

	private String nomeElo = "";

	private String eloNaoEncontrada = "false";

	private String idLocalidade = "";

	private String nomeLocalidade = "";

	private String localidadeNaoEncontrada = "false";

	private String idEmpresaFuncionario = "";

	private String usuarioSituacao = "";

	private String indicadorAtualizar = "";
	
	private String indicadorFuncionario = "";
	
	private String parmsUsuarioTipo = "";
	
	private String unidadeNegocio = "";
	
	private String loginUsuario = "";
	
	private String cpf = "";
	
	private String dataNascimento;

	private Short indicadorUsuarioBatch;
	
	private Short indicadorUsuarioInternet;

	/**
	 * @return Retorna o campo nome.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            O nome a ser setado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return Retorna o campo funcionarioNaoEncontrada.
	 */
	public String getFuncionarioNaoEncontrada() {
		return funcionarioNaoEncontrada;
	}

	/**
	 * @param funcionarioNaoEncontrada
	 *            O funcionarioNaoEncontrada a ser setado.
	 */
	public void setFuncionarioNaoEncontrada(String funcionarioNaoEncontrada) {
		this.funcionarioNaoEncontrada = funcionarioNaoEncontrada;
	}

	/**
	 * @return Retorna o campo lotacaoNaoEncontrada.
	 */
	public String getLotacaoNaoEncontrada() {
		return lotacaoNaoEncontrada;
	}

	/**
	 * @param lotacaoNaoEncontrada
	 *            O lotacaoNaoEncontrada a ser setado.
	 */
	public void setLotacaoNaoEncontrada(String lotacaoNaoEncontrada) {
		this.lotacaoNaoEncontrada = lotacaoNaoEncontrada;
	}

	/**
	 * @return Retorna o campo empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa
	 *            O empresa a ser setado.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Retorna o campo idFuncionario.
	 */
	public String getIdFuncionario() {
		return idFuncionario;
	}

	/**
	 * @param idFuncionario
	 *            O idFuncionario a ser setado.
	 */
	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	/**
	 * @return Retorna o campo idLotacao.
	 */
	public String getIdLotacao() {
		return idLotacao;
	}

	/**
	 * @param idLotacao
	 *            O idLotacao a ser setado.
	 */
	public void setIdLotacao(String idLotacao) {
		this.idLotacao = idLotacao;
	}

	/**
	 * @return Retorna o campo nomeFuncionario.
	 */
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	/**
	 * @param nomeFuncionario
	 *            O nomeFuncionario a ser setado.
	 */
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	/**
	 * @return Retorna o campo nomeLotacao.
	 */
	public String getNomeLotacao() {
		return nomeLotacao;
	}

	/**
	 * @param nomeLotacao
	 *            O nomeLotacao a ser setado.
	 */
	public void setNomeLotacao(String nomeLotacao) {
		this.nomeLotacao = nomeLotacao;
	}

	/**
	 * @return Retorna o campo tipoUsuario.
	 */
	public String getUsuarioTipo() {
		return usuarioTipo;
	}

	/**
	 * @param tipoUsuario
	 *            O tipoUsuario a ser setado.
	 */
	public void setUsuarioTipo(String tipoUsuario) {
		this.usuarioTipo = tipoUsuario;
	}

	public String getAbrangencia() {
		return abrangencia;
	}

	public void setAbrangencia(String abrangencia) {
		this.abrangencia = abrangencia;
	}

	public String getEloNaoEncontrada() {
		return eloNaoEncontrada;
	}

	public void setEloNaoEncontrada(String eloNaoEncontrada) {
		this.eloNaoEncontrada = eloNaoEncontrada;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String[] getGrupo() {
		return grupo;
	}

	public void setGrupo(String[] grupo) {
		this.grupo = grupo;
	}

	public String getIdElo() {
		return idElo;
	}

	public void setIdElo(String idElo) {
		this.idElo = idElo;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getLocalidadeNaoEncontrada() {
		return localidadeNaoEncontrada;
	}

	public void setLocalidadeNaoEncontrada(String localidadeNaoEncontrada) {
		this.localidadeNaoEncontrada = localidadeNaoEncontrada;
	}

	public String getNomeElo() {
		return nomeElo;
	}

	public void setNomeElo(String nomeElo) {
		this.nomeElo = nomeElo;
	}

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getDataCadastramentoFinal() {
		return dataCadastramentoFinal;
	}

	public void setDataCadastramentoFinal(String dataCadastramentoFinal) {
		this.dataCadastramentoFinal = dataCadastramentoFinal;
	}

	public String getDataCadastramentoInicial() {
		return dataCadastramentoInicial;
	}

	public void setDataCadastramentoInicial(String dataCadastramentoInicial) {
		this.dataCadastramentoInicial = dataCadastramentoInicial;
	}

	public String getDataExpiracaoFinal() {
		return dataExpiracaoFinal;
	}

	public void setDataExpiracaoFinal(String dataExpiracaoFinal) {
		this.dataExpiracaoFinal = dataExpiracaoFinal;
	}

	public String getDataExpiracaoInicial() {
		return dataExpiracaoInicial;
	}

	public void setDataExpiracaoInicial(String dataExpiracaoInicial) {
		this.dataExpiracaoInicial = dataExpiracaoInicial;
	}

	public String getIdEmpresaFuncionario() {
		return idEmpresaFuncionario;
	}

	public void setIdEmpresaFuncionario(String idEmpresaFuncionario) {
		this.idEmpresaFuncionario = idEmpresaFuncionario;
	}

	public String getUsuarioSituacao() {
		return usuarioSituacao;
	}

	public void setUsuarioSituacao(String usuarioSituacao) {
		this.usuarioSituacao = usuarioSituacao;
	}

	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	public String getIndicadorFuncionario() {
		return indicadorFuncionario;
	}

	public void setIndicadorFuncionario(String indicadorFuncionario) {
		this.indicadorFuncionario = indicadorFuncionario;
	}

	public String getParmsUsuarioTipo() {
		return parmsUsuarioTipo;
	}

	public void setParmsUsuarioTipo(String parmsUsuarioTipo) {
		this.parmsUsuarioTipo = parmsUsuarioTipo;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Short getIndicadorUsuarioBatch() {
		return indicadorUsuarioBatch;
	}

	public void setIndicadorUsuarioBatch(Short indicadorUsuarioBatch) {
		this.indicadorUsuarioBatch = indicadorUsuarioBatch;
	}
	
	public Short getIndicadorUsuarioInternet() {
		return indicadorUsuarioInternet;
	}

	public void setIndicadorUsuarioInternet(Short indicadorUsuarioInternet) {
		this.indicadorUsuarioInternet = indicadorUsuarioInternet;
	}
	

}