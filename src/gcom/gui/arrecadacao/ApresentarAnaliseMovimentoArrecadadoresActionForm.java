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
 * Esta classe tem por finalidade gerar o formulário que irá apresentar a análise do movimento dos arrecadadores
 * e os avisos bancários associados
 *
 * @author Raphael Rossiter
 * @date 07/03/2006
 */
public class ApresentarAnaliseMovimentoArrecadadoresActionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String codigoNomeArrecadador;
	private String codigoRemessa;
	private String identificacaoServico;
	private String nsa;
	private String dataGeracao;
	private String numeroRegistrosMovimento;
	private String numeroRegistrosOcorrencia;
	private String numeroRegistrosNaoAceitos;
	private String valorTotalMovimento;
	private String dataProcessamento;
	private String horaProcessamento;
	private String situacaoMovimento;
	private String valorTotalAvisosBancarios;
	private String valordiferencaVlMovimentoVlAvisos;
	
	private String ultimaAlteracao;
	
  
	
	public String getCodigoNomeArrecadador() {
		return codigoNomeArrecadador;
	}
	public void setCodigoNomeArrecadador(String codigoNomeArrecadador) {
		this.codigoNomeArrecadador = codigoNomeArrecadador;
	}
	public String getCodigoRemessa() {
		return codigoRemessa;
	}
	public void setCodigoRemessa(String codigoRemessa) {
		this.codigoRemessa = codigoRemessa;
	}
	public String getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(String dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public String getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(String dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	public String getHoraProcessamento() {
		return horaProcessamento;
	}
	public void setHoraProcessamento(String horaProcessamento) {
		this.horaProcessamento = horaProcessamento;
	}
	public String getIdentificacaoServico() {
		return identificacaoServico;
	}
	public void setIdentificacaoServico(String identificacaoServico) {
		this.identificacaoServico = identificacaoServico;
	}
	public String getNsa() {
		return nsa;
	}
	public void setNsa(String nsa) {
		this.nsa = nsa;
	}
	public String getNumeroRegistrosMovimento() {
		return numeroRegistrosMovimento;
	}
	public void setNumeroRegistrosMovimento(String numeroRegistrosMovimento) {
		this.numeroRegistrosMovimento = numeroRegistrosMovimento;
	}
	public String getNumeroRegistrosNaoAceitos() {
		return numeroRegistrosNaoAceitos;
	}
	public void setNumeroRegistrosNaoAceitos(String numeroRegistrosNaoAceitos) {
		this.numeroRegistrosNaoAceitos = numeroRegistrosNaoAceitos;
	}
	public String getNumeroRegistrosOcorrencia() {
		return numeroRegistrosOcorrencia;
	}
	public void setNumeroRegistrosOcorrencia(String numeroRegistrosOcorrencia) {
		this.numeroRegistrosOcorrencia = numeroRegistrosOcorrencia;
	}
	public String getSituacaoMovimento() {
		return situacaoMovimento;
	}
	public void setSituacaoMovimento(String situacaoMovimento) {
		this.situacaoMovimento = situacaoMovimento;
	}
	public String getValorTotalAvisosBancarios() {
		return valorTotalAvisosBancarios;
	}
	public void setValorTotalAvisosBancarios(String valorTotalAvisosBancarios) {
		this.valorTotalAvisosBancarios = valorTotalAvisosBancarios;
	}
	public String getValorTotalMovimento() {
		return valorTotalMovimento;
	}
	public void setValorTotalMovimento(String valorTotalMovimento) {
		this.valorTotalMovimento = valorTotalMovimento;
	}
	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	    /**@todo: finish this method, this is just the skeleton.*/
	    return null;
	}
	public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
	}
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
	public String getValordiferencaVlMovimentoVlAvisos() {
		return valordiferencaVlMovimentoVlAvisos;
	}
	public void setValordiferencaVlMovimentoVlAvisos(
			String valordiferencaVlMovimentoVlAvisos) {
		this.valordiferencaVlMovimentoVlAvisos = valordiferencaVlMovimentoVlAvisos;
	}

	
}

