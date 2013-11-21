/**
 * 
 */
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
package gcom.cobranca.bean;

import gcom.arrecadacao.pagamento.GuiaPagamento;
import gcom.cadastro.imovel.Imovel;
import gcom.faturamento.conta.Conta;
import gcom.faturamento.credito.CreditoARealizar;
import gcom.faturamento.debito.DebitoACobrar;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.Date;

public class TransferenciasDebitoHelper {
	
	private static final long serialVersionUID = 1L;
	
	private Conta conta;
	
	private DebitoACobrar debitoACobrar;
	
	private CreditoARealizar creditoARealizar;
	
	private GuiaPagamento guiaPagamento;
	
	private Imovel imovelOrigem;
	
	private Imovel imovelDestino;
	
	private Usuario usuario;
	
	private Date dataTransferencia;
	
	public TransferenciasDebitoHelper() {}
	
	/**
	 * @return Retorna o campo dataTransferencia.
	 */
	public Date getDataTransferencia() {
		return dataTransferencia;
	}

	/**
	 * @param dataTransferencia O dataTransferencia a ser setado.
	 */
	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	/**
	 * @return Retorna o campo usuario.
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario O usuario a ser setado.
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return Retorna o campo imovelDestino.
	 */
	public Imovel getImovelDestino() {
		return imovelDestino;
	}

	/**
	 * @param imovelDestino O imovelDestino a ser setado.
	 */
	public void setImovelDestino(Imovel imovelDestino) {
		this.imovelDestino = imovelDestino;
	}

	/**
	 * @return Retorna o campo imovelOrigem.
	 */
	public Imovel getImovelOrigem() {
		return imovelOrigem;
	}

	/**
	 * @param imovelOrigem O imovelOrigem a ser setado.
	 */
	public void setImovelOrigem(Imovel imovelOrigem) {
		this.imovelOrigem = imovelOrigem;
	}

	/**
	 * @return Retorna o campo conta.
	 */
	public Conta getConta() {
		return conta;
	}

	/**
	 * @param conta O conta a ser setado.
	 */
	public void setConta(Conta conta) {
		this.conta = conta;
	}

	/**
	 * @return Retorna o campo creditoARealizar.
	 */
	public CreditoARealizar getCreditoARealizar() {
		return creditoARealizar;
	}

	/**
	 * @param creditoARealizar O creditoARealizar a ser setado.
	 */
	public void setCreditoARealizar(CreditoARealizar creditoARealizar) {
		this.creditoARealizar = creditoARealizar;
	}

	/**
	 * @return Retorna o campo debitoACobrar.
	 */
	public DebitoACobrar getDebitoACobrar() {
		return debitoACobrar;
	}

	/**
	 * @param debitoACobrar O debitoACobrar a ser setado.
	 */
	public void setDebitoACobrar(DebitoACobrar debitoACobrar) {
		this.debitoACobrar = debitoACobrar;
	}

	/**
	 * @return Retorna o campo guiaPagamento.
	 */
	public GuiaPagamento getGuiaPagamento() {
		return guiaPagamento;
	}

	/**
	 * @param guiaPagamento O guiaPagamento a ser setado.
	 */
	public void setGuiaPagamento(GuiaPagamento guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}

}
