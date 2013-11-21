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
* Magno Jean Gouveia Silveira
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
package gcom.atendimentopublico.ordemservico;

import gcom.interceptor.ObjetoTransacao;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;

/**
 * [UC12008] Cadastrar Mensagens de Acompanhamento de Serviços
 * 
 * @author Magno Gouveia
 * @since 11/08/2011
 */
public class MensagemAcompanhamentoServico extends ObjetoTransacao {

	private static final long serialVersionUID = 1L;
	
	public static final Integer INDICADOR_SITUACAO_RECEBIDA = 1;
	
	public static final Integer INDICADOR_SITUACAO_CADASTRADA = 2;

	private Integer id;
	
	private ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico;
	
	private String descricaoMensagem;
	
	private Usuario usuario;
	
	private Integer indicadorSituacao;
	
	private Date ultimaAlteracao;

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;		
	}

	@Override
	public Filtro retornaFiltro() {

		FiltroMensagemAcompanhamentoServico filtroMensagemAcompanhamentoServico = new FiltroMensagemAcompanhamentoServico();
		filtroMensagemAcompanhamentoServico.adicionarParametro(new ParametroSimples(FiltroMensagemAcompanhamentoServico.ID, this.getId()));

		return filtroMensagemAcompanhamentoServico;
	}

	@Override
	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}

	public ArquivoTextoAcompanhamentoServico getArquivoTextoAcompanhamentoServico() {
		return arquivoTextoAcompanhamentoServico;
	}

	public void setArquivoTextoAcompanhamentoServico(ArquivoTextoAcompanhamentoServico arquivoTextoAcompanhamentoServico) {
		this.arquivoTextoAcompanhamentoServico = arquivoTextoAcompanhamentoServico;
	}

	public String getDescricaoMensagem() {
		return descricaoMensagem;
	}

	public void setDescricaoMensagem(String descricaoMensagem) {
		this.descricaoMensagem = descricaoMensagem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIndicadorSituacao() {
		return indicadorSituacao;
	}

	public void setIndicadorSituacao(Integer indicadorSituacao) {
		this.indicadorSituacao = indicadorSituacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}