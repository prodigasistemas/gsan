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
package gcom.cadastro.imovel;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;

import java.util.Date;



/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ImovelEloAnormalidade extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	private static final int ATRIBUTOS_OCORRENCIA_CADASTRO_ANORMALIDADE_IMOVEL = 698;
	// Operacao.OPERACAO_OCORRENCIA_ANORMALIDADE_INSERIR

    /** identifier field */
    private Integer id;

    /** persistent field */
    @ControleAlteracao(funcionalidade={ATRIBUTOS_OCORRENCIA_CADASTRO_ANORMALIDADE_IMOVEL})
    private Date dataAnormalidade;

    /** nullable persistent field */
    private byte[] fotoAnormalidade;

    /** persistent field */
    private Date ultimaAlteracao;

    /** persistent field */
    @ControleAlteracao(value=FiltroImovelEloAnormalidade.ELO_ANORMALIDADE, funcionalidade={ATRIBUTOS_OCORRENCIA_CADASTRO_ANORMALIDADE_IMOVEL})
    private gcom.cadastro.imovel.EloAnormalidade eloAnormalidade;

    /** persistent field */
    private gcom.cadastro.imovel.Imovel imovel;

	public Date getDataAnormalidade() {
		return dataAnormalidade;
	}

	public ImovelEloAnormalidade() {

	}

	public ImovelEloAnormalidade(Integer id, Date dataAnormalidade, byte[] fotoAnormalidade, Date ultimaAlteracao, EloAnormalidade eloAnormalidade, Imovel imovel) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.dataAnormalidade = dataAnormalidade;
		this.fotoAnormalidade = fotoAnormalidade;
		this.ultimaAlteracao = ultimaAlteracao;
		this.eloAnormalidade = eloAnormalidade;
		this.imovel = imovel;
	}

	public void setDataAnormalidade(Date dataAnormalidade) {
		this.dataAnormalidade = dataAnormalidade;
	}

	public gcom.cadastro.imovel.EloAnormalidade getEloAnormalidade() {
		return eloAnormalidade;
	}

	public void setEloAnormalidade(
			gcom.cadastro.imovel.EloAnormalidade eloAnormalidade) {
		this.eloAnormalidade = eloAnormalidade;
	}

	public byte[] getFotoAnormalidade() {
		return fotoAnormalidade;
	}

	public void setFotoAnormalidade(byte[] fotoAnormalidade) {
		this.fotoAnormalidade = fotoAnormalidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public gcom.cadastro.imovel.Imovel getImovel() {
		return imovel;
	}

	public void setImovel(gcom.cadastro.imovel.Imovel imovel) {
		this.imovel = imovel;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public String[] retornaCamposChavePrimaria(){
		String[] retorno = new String[1];
		retorno[0] = "id";
		return retorno;
	}
	
	public Filtro retornaFiltro(){
		FiltroImovelEloAnormalidade filtroImovelEloAnormalidade = new FiltroImovelEloAnormalidade();
		
		filtroImovelEloAnormalidade. adicionarCaminhoParaCarregamentoEntidade("imovel");
		filtroImovelEloAnormalidade. adicionarCaminhoParaCarregamentoEntidade("eloAnormalidade");

		filtroImovelEloAnormalidade. adicionarParametro(
				new ParametroSimples(FiltroImovelEloAnormalidade.ID, this.getId()));
		return filtroImovelEloAnormalidade; 
	}
	
	public String getDescricaoParaRegistroTransacao(){
		return eloAnormalidade.getId() + " - " + eloAnormalidade.getDescricao();	
	}
}
