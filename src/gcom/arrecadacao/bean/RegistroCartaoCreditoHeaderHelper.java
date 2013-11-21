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
package gcom.arrecadacao.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Header do TXT do retorno do cartão de crédito
 * 
 * @author Raphael Rossiter
 * @date 28/01/2010
 */
public class RegistroCartaoCreditoHeaderHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Short tipoRegistro;
	
	private String estabelecimentoMatriz;
	
	private Date dataProcessamento;
	
	private Date dataPeriodoInicial;
	
	private Date dataPeriodoFinal;
	
	private String produto;
	
	private String sequencia;
	
	private String empresa;
	
	private String opcaoExtrato;
	
	private String transmissao;
	
	private String descricaoOpcaoExtrato;
	
	//CONSTANTE HEADER
	public final static Short CODIGO_HEADER = new Short("0");
	
	
	public RegistroCartaoCreditoHeaderHelper(){}

	public Date getDataPeriodoFinal() {
		return dataPeriodoFinal;
	}

	public void setDataPeriodoFinal(Date dataPeriodoFinal) {
		this.dataPeriodoFinal = dataPeriodoFinal;
	}

	public Date getDataPeriodoInicial() {
		return dataPeriodoInicial;
	}

	public void setDataPeriodoInicial(Date dataPeriodoInicial) {
		this.dataPeriodoInicial = dataPeriodoInicial;
	}

	public Date getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}

	public String getDescricaoOpcaoExtrato() {
		return descricaoOpcaoExtrato;
	}

	public void setDescricaoOpcaoExtrato(String descricaoOpcaoExtrato) {
		this.descricaoOpcaoExtrato = descricaoOpcaoExtrato;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEstabelecimentoMatriz() {
		return estabelecimentoMatriz;
	}

	public void setEstabelecimentoMatriz(String estabelecimentoMatriz) {
		this.estabelecimentoMatriz = estabelecimentoMatriz;
	}

	public String getOpcaoExtrato() {
		return opcaoExtrato;
	}

	public void setOpcaoExtrato(String opcaoExtrato) {
		this.opcaoExtrato = opcaoExtrato;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getSequencia() {
		return sequencia;
	}

	public void setSequencia(String sequencia) {
		this.sequencia = sequencia;
	}

	public Short getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Short tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getTransmissao() {
		return transmissao;
	}

	public void setTransmissao(String transmissao) {
		this.transmissao = transmissao;
	}
}
