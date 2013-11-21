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
package gcom.cadastro.imovel.bean;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.cadastro.imovel.Imovel;
import gcom.seguranca.acesso.usuario.Usuario;

import java.io.Serializable;
import java.util.Collection;

/**
 * Esta classe tem a finalidade de encapsular as informações necessárias para inserir um imóvel
 * 
 * @author Raphael Rossiter
 * @date 19/08/2008
 */
public class InserirImovelHelper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Imovel imovel;
	
	private Collection subcategorias;
	
	private Collection ramosAtividades;
	
	private Collection endereco;
	
	private Collection clientes;
	
	private Usuario usuario;
	
	private LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento;
	
	private Collection colecaoClientesImoveisRemovidos;
	
	private Collection colecaoImovelSubcategoriasRemovidas;
	
	private Collection colecaoRamoAtividadesRemovidas;
	
	public InserirImovelHelper(){}
	
	public InserirImovelHelper(Imovel imovel, Collection subcategorias, Collection ramosAtividades, Collection endereco,
			Collection clientes, LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento, 
			Usuario usuario){
		
		this.ramosAtividades = ramosAtividades;
		this.imovel = imovel;
		this.subcategorias = subcategorias;
		this.endereco = endereco;
		this.clientes = clientes;
		this.ligacaoEsgotoEsgotamento = ligacaoEsgotoEsgotamento;
		this.usuario = usuario;
	
	}
	

	public Collection getClientes() {
		return clientes;
	}

	public void setClientes(Collection clientes) {
		this.clientes = clientes;
	}

	public Collection getEndereco() {
		return endereco;
	}

	public void setEndereco(Collection endereco) {
		this.endereco = endereco;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public LigacaoEsgotoEsgotamento getLigacaoEsgotoEsgotamento() {
		return ligacaoEsgotoEsgotamento;
	}

	public void setLigacaoEsgotoEsgotamento(
			LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento) {
		this.ligacaoEsgotoEsgotamento = ligacaoEsgotoEsgotamento;
	}

	public Collection getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(Collection subcategorias) {
		this.subcategorias = subcategorias;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Collection getColecaoClientesImoveisRemovidos() {
		return colecaoClientesImoveisRemovidos;
	}

	public void setColecaoClientesImoveisRemovidos(
			Collection colecaoClientesImoveisRemovidos) {
		this.colecaoClientesImoveisRemovidos = colecaoClientesImoveisRemovidos;
	}

	public Collection getColecaoImovelSubcategoriasRemovidas() {
		return colecaoImovelSubcategoriasRemovidas;
	}

	public void setColecaoImovelSubcategoriasRemovidas(
			Collection colecaoImovelSubcategoriasRemovidas) {
		this.colecaoImovelSubcategoriasRemovidas = colecaoImovelSubcategoriasRemovidas;
	}

	public Collection getRamosAtividades() {
		return ramosAtividades;
	}

	public void setRamosAtividades(Collection ramosAtividades) {
		this.ramosAtividades = ramosAtividades;
	}

	/**
	 * @return Returns the colecaoRamoAtividadesRemovidas.
	 */
	public Collection getColecaoRamoAtividadesRemovidas() {
		return colecaoRamoAtividadesRemovidas;
	}

	/**
	 * @param colecaoRamoAtividadesRemovidas The colecaoRamoAtividadesRemovidas to set.
	 */
	public void setColecaoRamoAtividadesRemovidas(
			Collection colecaoRamoAtividadesRemovidas) {
		this.colecaoRamoAtividadesRemovidas = colecaoRamoAtividadesRemovidas;
	}

	
	
}
