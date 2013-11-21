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
 * Rômulo Aurélio de Melo Souza Filho
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
package gcom.gui.faturamento.autoinfracao;

import org.apache.struts.validator.ValidatorActionForm;

public class FiltrarAutoInfracaoActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String idImovel;

	private String descricaoImovel;

	private String idFuncionario;

	private String descricaoFuncionario;

	private String idFiscalizacaoSituacao;

	private String descricaoFiscalizacaoSituacao;

	private String idAutoInfracaoSituacao;

	private String descricaoAutoInfracaoSituacao;

	private String dataEmissaoInicial;

	private String dataEmissaoFinal;

	private String dataInicioRecursoInicial;

	private String dataInicioRecursoFinal;

	private String dataTerminoRecursoInicial;

	private String dataTerminoRecursoFinal;
	
	private String atualizar;
	
	

	public String getDataEmissaoFinal() {
		return dataEmissaoFinal;
	}

	public void setDataEmissaoFinal(String dataEmissaoFinal) {
		this.dataEmissaoFinal = dataEmissaoFinal;
	}

	public String getDataEmissaoInicial() {
		return dataEmissaoInicial;
	}

	public void setDataEmissaoInicial(String dataEmissaoInicial) {
		this.dataEmissaoInicial = dataEmissaoInicial;
	}

	public String getDataInicioRecursoFinal() {
		return dataInicioRecursoFinal;
	}

	public void setDataInicioRecursoFinal(String dataInicioRecursoFinal) {
		this.dataInicioRecursoFinal = dataInicioRecursoFinal;
	}

	public String getDataInicioRecursoInicial() {
		return dataInicioRecursoInicial;
	}

	public void setDataInicioRecursoInicial(String dataInicioRecursoInicial) {
		this.dataInicioRecursoInicial = dataInicioRecursoInicial;
	}

	public String getDataTerminoRecursoFinal() {
		return dataTerminoRecursoFinal;
	}

	public void setDataTerminoRecursoFinal(String dataTerminoRecursoFinal) {
		this.dataTerminoRecursoFinal = dataTerminoRecursoFinal;
	}

	public String getDataTerminoRecursoInicial() {
		return dataTerminoRecursoInicial;
	}

	public void setDataTerminoRecursoInicial(String dataTerminoRecursoInicial) {
		this.dataTerminoRecursoInicial = dataTerminoRecursoInicial;
	}

	public String getDescricaoAutoInfracaoSituacao() {
		return descricaoAutoInfracaoSituacao;
	}

	public void setDescricaoAutoInfracaoSituacao(
			String descricaoAutoInfracaoSituacao) {
		this.descricaoAutoInfracaoSituacao = descricaoAutoInfracaoSituacao;
	}

	public String getDescricaoFiscalizacaoSituacao() {
		return descricaoFiscalizacaoSituacao;
	}

	public void setDescricaoFiscalizacaoSituacao(
			String descricaoFiscalizacaoSituacao) {
		this.descricaoFiscalizacaoSituacao = descricaoFiscalizacaoSituacao;
	}

	public String getDescricaoFuncionario() {
		return descricaoFuncionario;
	}

	public void setDescricaoFuncionario(String descricaoFuncionario) {
		this.descricaoFuncionario = descricaoFuncionario;
	}

	public String getDescricaoImovel() {
		return descricaoImovel;
	}

	public void setDescricaoImovel(String descricaoImovel) {
		this.descricaoImovel = descricaoImovel;
	}

	public String getIdAutoInfracaoSituacao() {
		return idAutoInfracaoSituacao;
	}

	public void setIdAutoInfracaoSituacao(String idAutoInfracaoSituacao) {
		this.idAutoInfracaoSituacao = idAutoInfracaoSituacao;
	}

	public String getIdFiscalizacaoSituacao() {
		return idFiscalizacaoSituacao;
	}

	public void setIdFiscalizacaoSituacao(String idFiscalizacaoSituacao) {
		this.idFiscalizacaoSituacao = idFiscalizacaoSituacao;
	}

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getAtualizar() {
		return atualizar;
	}

	public void setAtualizar(String atualizar) {
		this.atualizar = atualizar;
	}

}
