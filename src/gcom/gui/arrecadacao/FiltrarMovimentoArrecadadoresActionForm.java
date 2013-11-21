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
package gcom.gui.arrecadacao;

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Esta classe tem por finalidade gerar o formulário que receberá os parâmetros para realização
 * da filtragem dos movimentos dos arrecadadores 
 *
 * @author Raphael Rossiter
 * @date 23/02/2006
 */
public class FiltrarMovimentoArrecadadoresActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String banco;
	private String descricaoBanco;
	private String remessa;
	private String identificacaoServico;
	private String nsa;
	private String dataGeracaoMovimentoInicio;
	private String dataGeracaoMovimentoFim;
	private String dataProcessamentoMovimentoInicio;
	private String dataProcessamentoMovimentoFim;
	private String movimentoItemOcorrencia;
	private String movimentoItemAceito;
	private String movimentoAbertoFechado;
	private String formaArrecadacao;
	private String indicadorRelatorio;
  
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getDataGeracaoMovimentoFim() {
		return dataGeracaoMovimentoFim;
	}
	public void setDataGeracaoMovimentoFim(String dataGeracaoMovimentoFim) {
		this.dataGeracaoMovimentoFim = dataGeracaoMovimentoFim;
	}
	public String getDataGeracaoMovimentoInicio() {
		return dataGeracaoMovimentoInicio;
	}
	public void setDataGeracaoMovimentoInicio(String dataGeracaoMovimentoInicio) {
		this.dataGeracaoMovimentoInicio = dataGeracaoMovimentoInicio;
	}
	public String getDataProcessamentoMovimentoFim() {
		return dataProcessamentoMovimentoFim;
	}
	public void setDataProcessamentoMovimentoFim(
			String dataProcessamentoMovimentoFim) {
		this.dataProcessamentoMovimentoFim = dataProcessamentoMovimentoFim;
	}
	public String getDataProcessamentoMovimentoInicio() {
		return dataProcessamentoMovimentoInicio;
	}
	public void setDataProcessamentoMovimentoInicio(
			String dataProcessamentoMovimentoInicio) {
		this.dataProcessamentoMovimentoInicio = dataProcessamentoMovimentoInicio;
	}
	public String getIdentificacaoServico() {
		return identificacaoServico;
	}
	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}
	public String getMovimentoAbertoFechado() {
		return movimentoAbertoFechado;
	}
	public void setMovimentoAbertoFechado(String movimentoAbertoFechado) {
		this.movimentoAbertoFechado = movimentoAbertoFechado;
	}
	public String getMovimentoItemAceito() {
		return movimentoItemAceito;
	}
	public void setMovimentoItemAceito(String movimentoItemAceito) {
		this.movimentoItemAceito = movimentoItemAceito;
	}
	public String getMovimentoItemOcorrencia() {
		return movimentoItemOcorrencia;
	}
	public void setMovimentoItemOcorrencia(String movimentoItemOcorrencia) {
		this.movimentoItemOcorrencia = movimentoItemOcorrencia;
	}
	public String getNsa() {
		return nsa;
	}
	public void setNsa(String nsa) {
		this.nsa = nsa;
	}
	public String getRemessa() {
		return remessa;
	}
	public void setRemessa(String remessa) {
		this.remessa = remessa;
	}
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	    /**@todo: finish this method, this is just the skeleton.*/
	    return null;
	}
	
	public String getDescricaoBanco() {
		return descricaoBanco;
	}
	public void setDescricaoBanco(String descricaoBanco) {
		this.descricaoBanco = descricaoBanco;
	}
	/**
	 * @return Retorna o campo formaArrecadacao.
	 */
	public String getFormaArrecadacao() {
		return formaArrecadacao;
	}
	/**
	 * @param formaArrecadacao O formaArrecadacao a ser setado.
	 */
	public void setFormaArrecadacao(String formaArrecadacao) {
		this.formaArrecadacao = formaArrecadacao;
	}
	public String getIndicadorRelatorio() {
		return indicadorRelatorio;
	}
	public void setIndicadorRelatorio(String indicadorRelatorio) {
		this.indicadorRelatorio = indicadorRelatorio;
	}
	
}
