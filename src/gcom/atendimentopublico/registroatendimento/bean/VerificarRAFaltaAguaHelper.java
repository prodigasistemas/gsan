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
