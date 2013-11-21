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
package gcom.relatorio;

import gcom.relatorio.RelatorioBean;

/**
 * Bean responsável de pegar os parametros que serão exibidos na parte de detail
 * do relatório.
 * 
 * @author Sávio Luiz
 * @created 8 de Julho de 2005
 */
public class RelatorioAcompanhamentoMovimentoArrecadadoresBean implements RelatorioBean {

	private String banco;

	private String formaArrecadacao;

	private String dia;

	private String valorDia;

	private String qtdePagamentos;
	
	private String qtdeDocumentos;
	
	private String valorAteDia;
	
	private String qtdePagamentosAteDia;
	
	private String qtdeDocumentosAteDia;

	/**
	 * Construtor da classe RelatorioAcompanhamentoMovimentoArrecadadoresBean
	 */
	public RelatorioAcompanhamentoMovimentoArrecadadoresBean(String banco,
			String formaArrecadacao, String dia, String valorDia,
			String qtdePagamentos, String qtdeDocumentos, String valorAteDia,
			String qtdePagamentosAteDia, String qtdeDocumentosAteDia) {
		
		this.banco = banco;
		this.formaArrecadacao = formaArrecadacao;
		this.dia = dia;
		this.valorDia = valorDia;
		this.qtdePagamentos = qtdePagamentos;
		this.qtdeDocumentos = qtdeDocumentos;
		this.valorAteDia = valorAteDia;
		this.qtdePagamentosAteDia = qtdePagamentosAteDia;
		this.qtdeDocumentosAteDia = qtdeDocumentosAteDia;

	}

	/**
	 * @return Retorna o campo banco.
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco O banco a ser setado.
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * @return Retorna o campo dia.
	 */
	public String getDia() {
		return dia;
	}

	/**
	 * @param dia O dia a ser setado.
	 */
	public void setDia(String dia) {
		this.dia = dia;
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

	/**
	 * @return Retorna o campo qtdeDocumentos.
	 */
	public String getQtdeDocumentos() {
		return qtdeDocumentos;
	}

	/**
	 * @param qtdeDocumentos O qtdeDocumentos a ser setado.
	 */
	public void setQtdeDocumentos(String qtdeDocumentos) {
		this.qtdeDocumentos = qtdeDocumentos;
	}

	/**
	 * @return Retorna o campo qtdeDocumentosAteDia.
	 */
	public String getQtdeDocumentosAteDia() {
		return qtdeDocumentosAteDia;
	}

	/**
	 * @param qtdeDocumentosAteDia O qtdeDocumentosAteDia a ser setado.
	 */
	public void setQtdeDocumentosAteDia(String qtdeDocumentosAteDia) {
		this.qtdeDocumentosAteDia = qtdeDocumentosAteDia;
	}

	/**
	 * @return Retorna o campo qtdePagamentos.
	 */
	public String getQtdePagamentos() {
		return qtdePagamentos;
	}

	/**
	 * @param qtdePagamentos O qtdePagamentos a ser setado.
	 */
	public void setQtdePagamentos(String qtdePagamentos) {
		this.qtdePagamentos = qtdePagamentos;
	}

	/**
	 * @return Retorna o campo qtdePagamentosAteDia.
	 */
	public String getQtdePagamentosAteDia() {
		return qtdePagamentosAteDia;
	}

	/**
	 * @param qtdePagamentosAteDia O qtdePagamentosAteDia a ser setado.
	 */
	public void setQtdePagamentosAteDia(String qtdePagamentosAteDia) {
		this.qtdePagamentosAteDia = qtdePagamentosAteDia;
	}

	/**
	 * @return Retorna o campo valorAteDia.
	 */
	public String getValorAteDia() {
		return valorAteDia;
	}

	/**
	 * @param valorAteDia O valorAteDia a ser setado.
	 */
	public void setValorAteDia(String valorAteDia) {
		this.valorAteDia = valorAteDia;
	}

	/**
	 * @return Retorna o campo valorDia.
	 */
	public String getValorDia() {
		return valorDia;
	}

	/**
	 * @param valorDia O valorDia a ser setado.
	 */
	public void setValorDia(String valorDia) {
		this.valorDia = valorDia;
	}


}
