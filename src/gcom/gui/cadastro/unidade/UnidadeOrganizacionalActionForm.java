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
package gcom.gui.cadastro.unidade;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * < <Descrição da Classe>>
 * 
 * @author Ana Maria
 * @date 20/11/2006
 */
public class UnidadeOrganizacionalActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;

	private String idUnidade;
 
	private String indicadorAtualizar;
	
	private String idTipoUnidade;

	private String nivelHierarquico;

	private String idLocalidade;

	private String descricaoLocalidade;

	private String descricao;

	private String sigla;

	private String idUnidadeSuperior;

	private String descricaoUnidadeSuperior;

	private String idUnidadeCentralizadora;
	
	private String idUnidadeRepavimentadora;

	private String unidadeAbreRA;

	private String unidadeTramitacao;

	private String idMeioSolicitacao;

	private String indicadorUso;
	
	private String idGerenciaRegional;
	
	private String tipoPesquisa;
	
	private String idEmpresa;
	
	private String unidadeEsgoto;
	
	private String ordernarPor;

	/**
	 * @return Retorna o campo unidadeEsgoto.
	 */
	public String getUnidadeEsgoto() {
		return unidadeEsgoto;
	}

	/**
	 * @param unidadeEsgoto O unidadeEsgoto a ser setado.
	 */
	public void setUnidadeEsgoto(String unidadeEsgoto) {
		this.unidadeEsgoto = unidadeEsgoto;
	}

	/**
	 * @return Retorna o campo idEmpresa.
	 */
	public String getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa O idEmpresa a ser setado.
	 */
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo descricaoLocalidade.
	 */
	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	/**
	 * @param descricaoLocalidade O descricaoLocalidade a ser setado.
	 */
	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idMeioSolicitacao.
	 */
	public String getIdMeioSolicitacao() {
		return idMeioSolicitacao;
	}

	/**
	 * @param idMeioSolicitacao O idMeioSolicitacao a ser setado.
	 */
	public void setIdMeioSolicitacao(String idMeioSolicitacao) {
		this.idMeioSolicitacao = idMeioSolicitacao;
	}

	/**
	 * @return Retorna o campo idTipoUnidade.
	 */
	public String getIdTipoUnidade() {
		return idTipoUnidade;
	}

	/**
	 * @param idTipoUnidade O idTipoUnidade a ser setado.
	 */
	public void setIdTipoUnidade(String idTipoUnidade) {
		this.idTipoUnidade = idTipoUnidade;
	}

	/**
	 * @return Retorna o campo idUnidadeCentralizadora.
	 */
	public String getIdUnidadeCentralizadora() {
		return idUnidadeCentralizadora;
	}

	/**
	 * @param idUnidadeCentralizadora O idUnidadeCentralizadora a ser setado.
	 */
	public void setIdUnidadeCentralizadora(String idUnidadeCentralizadora) {
		this.idUnidadeCentralizadora = idUnidadeCentralizadora;
	}

	/**
	 * @return Retorna o campo idUnidadeSuperior.
	 */
	public String getIdUnidadeSuperior() {
		return idUnidadeSuperior;
	}

	/**
	 * @param idUnidadeSuperior O idUnidadeSuperior a ser setado.
	 */
	public void setIdUnidadeSuperior(String idUnidadeSuperior) {
		this.idUnidadeSuperior = idUnidadeSuperior;
	}

	/**
	 * @return Retorna o campo indicadorAtualizar.
	 */
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	/**
	 * @param indicadorAtualizar O indicadorAtualizar a ser setado.
	 */
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
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

	/**
	 * @return Retorna o campo nivelHierarquico.
	 */
	public String getNivelHierarquico() {
		return nivelHierarquico;
	}

	/**
	 * @param nivelHierarquico O nivelHierarquico a ser setado.
	 */
	public void setNivelHierarquico(String nivelHierarquico) {
		this.nivelHierarquico = nivelHierarquico;
	}

	/**
	 * @return Retorna o campo sigla.
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla O sigla a ser setado.
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return Retorna o campo unidadeAbreRA.
	 */
	public String getUnidadeAbreRA() {
		return unidadeAbreRA;
	}

	/**
	 * @param unidadeAbreRA O unidadeAbreRA a ser setado.
	 */
	public void setUnidadeAbreRA(String unidadeAbreRA) {
		this.unidadeAbreRA = unidadeAbreRA;
	}

	/**
	 * @return Retorna o campo unidadeTramitacao.
	 */
	public String getUnidadeTramitacao() {
		return unidadeTramitacao;
	}

	/**
	 * @param unidadeTramitacao O unidadeTramitacao a ser setado.
	 */
	public void setUnidadeTramitacao(String unidadeTramitacao) {
		this.unidadeTramitacao = unidadeTramitacao;
	}

	/**
	 * @return Retorna o campo tipoPesquisa.
	 */
	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	/**
	 * @param tipoPesquisa O tipoPesquisa a ser setado.
	 */
	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	/**
	 * @return Retorna o campo descricaoUnidadeSuperior.
	 */
	public String getDescricaoUnidadeSuperior() {
		return descricaoUnidadeSuperior;
	}

	/**
	 * @param descricaoUnidadeSuperior O descricaoUnidadeSuperior a ser setado.
	 */
	public void setDescricaoUnidadeSuperior(String descricaoUnidadeSuperior) {
		this.descricaoUnidadeSuperior = descricaoUnidadeSuperior;
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
	 * @return Retorna o campo idUnidadeRepavimentadora.
	 */
	public String getIdUnidadeRepavimentadora() {
		return idUnidadeRepavimentadora;
	}

	/**
	 * @param idUnidadeRepavimentadora O idUnidadeRepavimentadora a ser setado.
	 */
	public void setIdUnidadeRepavimentadora(String idUnidadeRepavimentadora) {
		this.idUnidadeRepavimentadora = idUnidadeRepavimentadora;
	}

	public String getOrdernarPor() {
		return ordernarPor;
	}

	public void setOrdernarPor(String ordernarPor) {
		this.ordernarPor = ordernarPor;
	}

	
	/**
	 * Description of the Method
	 * 
	 * @param actionMapping
	 *            Description of the Parameter
	 * @param httpServletRequest
	 *            Description of the Parameter
	 */
	
	/*public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		// não usar pois vai dar problema com o filtrar
		anoFabricacao = null;

		dataAquisicao = null;

		faixaFinal = null;

		faixaInicial = null;

		fixo = null;

		idHidrometroCapacidade = null;

		idHidrometroClasseMetrologica = null;

		idHidrometroDiametro = null;

		idHidrometroTipo = null;

		idLocalArmazenagem = null;

		localArmazenagemDescricao = null;

		localArmazenagemDescricaoOrigem = null;

		localArmazenagemDescricaoDestino = null;

		idHidrometroMarca = null;

		idNumeroDigitosLeitura = null;

		indicadorMacromedidor = null;

		numeroHidrometro = null;

		hidrometroSelectID = null;

		dataMovimentacaoInicial = null;

		dataMovimentacaoFinal = null;

		horaMovimentacaoInicial = null;

		horaMovimentacaoFinal = null;

		motivoMovimentacao = null;

		localArmazenagemDestino = null;

		localArmazenagemOrigem = null;

		funcionario = null;
		
		nomeFuncionario = null;

		idHidrometroSituacao = null;

	}*/
}
