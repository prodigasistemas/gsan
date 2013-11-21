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
package gcom.relatorio.atendimentopublico;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioRoteiroProgramacaoBean implements RelatorioBean {
	
	private String unidade;
	
	private String data;

	private String codigoEquipe;

	private String nomeEquipe;

	private String placa;

	private String encarregado;

	private String posicaoComponente;
	
	private String nomeComponente;
	
	private String posicaoComponente2;
	
	private String nomeComponente2;
	
	private String sequencial;

	private String numeroRA;

	private String numeroOS;

	private String tipoServico;

	private String endereco;
	
	private String observacao;

	/**
	 * Construtor da classe RelatorioAnaliseFisicoQuimicaAguaBean
	 * 
	 * @param codigo
	 *            Description of the Parameter
	 * @param nome
	 *            Description of the Parameter
	 * @param municipio
	 *            Description of the Parameter
	 * @param codPref
	 *            Description of the Parameter
	 * @param indicadorUso
	 *            Description of the Parameter
	 */
	public RelatorioRoteiroProgramacaoBean(String unidade, String data, String codigoEquipe, String nomeEquipe,
			String placa, String encarregado, String posicaoComponente,
			String nomeComponente, String posicaoComponente2, String nomeComponente2, String sequencial, String numeroRA,
			String numeroOS, String tipoServico, String endereco, String observacao) {
		this.unidade = unidade;
		this.data = data;
		this.codigoEquipe = codigoEquipe;
		this.nomeEquipe = nomeEquipe;
		this.placa = placa;
		this.encarregado = encarregado;
		this.posicaoComponente = posicaoComponente;
		this.nomeComponente = nomeComponente;
		this.posicaoComponente2 = posicaoComponente2;
		this.nomeComponente2 = nomeComponente2;
		this.sequencial = sequencial;
		this.numeroRA = numeroRA;
		this.nomeComponente = nomeComponente;
		this.sequencial = sequencial;
		this.numeroRA = numeroRA;
		this.numeroOS = numeroOS;
		this.tipoServico = tipoServico;
		this.endereco = endereco;
		this.observacao = observacao;
	}

	/**
	 * Gets e Sets the codigo attribute of the RelatorioRoteiroProgramacaoBean object
	 * 
	 * @return The codigo value
	 */

	public String getCodigoEquipe() {
		return codigoEquipe;
	}

	public void setCodigoEquipe(String codigoEquipe) {
		this.codigoEquipe = codigoEquipe;
	}

	public String getEncarregado() {
		return encarregado;
	}

	public void setEncarregado(String encarregado) {
		this.encarregado = encarregado;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNomeComponente() {
		return nomeComponente;
	}

	public void setNomeComponente(String nomeComponente) {
		this.nomeComponente = nomeComponente;
	}

	public String getNomeEquipe() {
		return nomeEquipe;
	}

	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public String getNumeroRA() {
		return numeroRA;
	}

	public void setNumeroRA(String numeroRA) {
		this.numeroRA = numeroRA;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getPosicaoComponente() {
		return posicaoComponente;
	}

	public void setPosicaoComponente(String posicaoComponente) {
		this.posicaoComponente = posicaoComponente;
	}

	public String getSequencial() {
		return sequencial;
	}

	public void setSequencial(String sequencial) {
		this.sequencial = sequencial;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getNomeComponente2() {
		return nomeComponente2;
	}

	public void setNomeComponente2(String nomeComponente2) {
		this.nomeComponente2 = nomeComponente2;
	}

	public String getPosicaoComponente2() {
		return posicaoComponente2;
	}

	public void setPosicaoComponente2(String posicaoComponente2) {
		this.posicaoComponente2 = posicaoComponente2;
	}

}
