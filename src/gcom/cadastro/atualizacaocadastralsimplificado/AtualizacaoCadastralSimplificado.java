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
package gcom.cadastro.atualizacaocadastralsimplificado;

import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.Util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class AtualizacaoCadastralSimplificado {

	private Integer id;
	private String nome;
	private String comentario;
	private AtualizacaoCadastralSimplificadoBinario binario;
	private Usuario usuario;
	private Integer qtdeTotalImoveis;
	private Integer qtdeImoveisComHidrometro;
	private Integer qtdeImoveisComHidrometroAtualizados;
	private Integer qtdeImoveisSemHidrometro;
	private Integer qtdeImoveisComEconomiasAtualizados;
	private Integer qtdeImoveisComMedidorEnergiaAtualizados;
	private Date ultimaAlteracao;
	private Collection<AtualizacaoCadastralSimplificadoLinha> linhas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public AtualizacaoCadastralSimplificadoBinario getBinario() {
		return binario;
	}

	public void setBinario(AtualizacaoCadastralSimplificadoBinario binario) {
		this.binario = binario;
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getQtdeTotalImoveis() {
		return qtdeTotalImoveis;
	}

	public void setQtdeTotalImoveis(Integer qtdeTotalImoveis) {
		this.qtdeTotalImoveis = qtdeTotalImoveis;
	}

	public Integer getQtdeImoveisComHidrometro() {
		return qtdeImoveisComHidrometro;
	}

	public void setQtdeImoveisComHidrometro(Integer qtdeImoveisComHidrometro) {
		this.qtdeImoveisComHidrometro = qtdeImoveisComHidrometro;
	}

	public Integer getQtdeImoveisComHidrometroAtualizados() {
		return qtdeImoveisComHidrometroAtualizados;
	}

	public void setQtdeImoveisComHidrometroAtualizados(
			Integer qtdeImoveisComHidrometroAtualizados) {
		this.qtdeImoveisComHidrometroAtualizados = qtdeImoveisComHidrometroAtualizados;
	}

	public Integer getQtdeImoveisSemHidrometro() {
		return qtdeImoveisSemHidrometro;
	}

	public void setQtdeImoveisSemHidrometro(Integer qtdeImoveisSemHidrometro) {
		this.qtdeImoveisSemHidrometro = qtdeImoveisSemHidrometro;
	}

	public Integer getQtdeImoveisComEconomiasAtualizados() {
		return qtdeImoveisComEconomiasAtualizados;
	}

	public void setQtdeImoveisComEconomiasAtualizados(
			Integer qtdeImoveisComEconomiasAtualizados) {
		this.qtdeImoveisComEconomiasAtualizados = qtdeImoveisComEconomiasAtualizados;
	}

	public Integer getQtdeImoveisComMedidorEnergiaAtualizados() {
		return qtdeImoveisComMedidorEnergiaAtualizados;
	}

	public void setQtdeImoveisComMedidorEnergiaAtualizados(
			Integer qtdeImoveisComMedidorEnergiaAtualizados) {
		this.qtdeImoveisComMedidorEnergiaAtualizados = qtdeImoveisComMedidorEnergiaAtualizados;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Collection<AtualizacaoCadastralSimplificadoLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(Collection<AtualizacaoCadastralSimplificadoLinha> linhas) {
		this.linhas = linhas;
	}

	public String getIndiceAtualizacaoHidrometro() {
		if (qtdeImoveisComHidrometro != null && qtdeImoveisComHidrometro > 0) {
		BigDecimal indice = new BigDecimal(
				this.qtdeImoveisComHidrometroAtualizados).divide(
				new BigDecimal(this.qtdeImoveisComHidrometro), 4,
				BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		return Util.formataBigDecimal(indice, 2, true);
		} else {
			return Util.formataBigDecimal(BigDecimal.ZERO, 2, true);
		}
	}

	public String getIndiceAtualizacaoEconomias() {
		BigDecimal indice = new BigDecimal(
				this.qtdeImoveisComEconomiasAtualizados).divide(
				new BigDecimal(this.qtdeTotalImoveis), 4,
				BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		return Util.formataBigDecimal(indice, 2, true);
	}
	
	public String getIndiceAtualizacaoMedidoresEnergia() {
		BigDecimal indice = new BigDecimal(0);
		if (qtdeImoveisComMedidorEnergiaAtualizados != null){
			indice = new BigDecimal(
					this.qtdeImoveisComMedidorEnergiaAtualizados).divide(
							new BigDecimal(this.qtdeTotalImoveis), 4,
							BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		}
		
		return Util.formataBigDecimal(indice, 2, true);
	}

}
