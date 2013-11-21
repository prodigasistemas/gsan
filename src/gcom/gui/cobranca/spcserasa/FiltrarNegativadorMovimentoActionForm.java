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
 * Yara Taciane de Souza
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
package gcom.gui.cobranca.spcserasa;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author Yara Taciane de Souza
 * @created 07/01/2008
 */

public class FiltrarNegativadorMovimentoActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String idNegativador;

	private Collection collNegativador;

	private String tipoMovimento;

	private String idImovel; 
	private String nomeImovel;
	private String imovelNaoEncontrado;

	private String numeroSequencialArquivo;

	private String dataProcessamentoInicial;

	private String dataProcessamentoFinal;

	private String movimento;

	private String movimentoRegistro;

	private String movimentoCorrigido;

	private String indicadorAtualizar;
	
	private String  gerenciaRegional;
	private String[] arrayGerenciaRegional;	
	
	private String  unidadeNegocio;
	private String[] arrayUnidadeNegocio;
	
	private String idEloPolo;
	
	private String descricaoEloPolo;
	
	private String idLocalidade;
	
	private String descricaoLocalidade;
	
	private String  motivoRejeicao;
	private String[] arrayMotivoRejeicao;

	/**
	 * @return Retorna o campo collNegativador.
	 */
	public Collection getCollNegativador() {
		return collNegativador;
	}

	/**
	 * @param collNegativador
	 *            O collNegativador a ser setado.
	 */
	public void setCollNegativador(Collection collNegativador) {
		this.collNegativador = collNegativador;
	}

	/**
	 * @return Retorna o campo dataProcessamentoFinal.
	 */
	public String getDataProcessamentoFinal() {
		return dataProcessamentoFinal;
	}

	/**
	 * @param dataProcessamentoFinal
	 *            O dataProcessamentoFinal a ser setado.
	 */
	public void setDataProcessamentoFinal(String dataProcessamentoFinal) {
		this.dataProcessamentoFinal = dataProcessamentoFinal;
	}

	/**
	 * @return Retorna o campo dataProcessamentoInicial.
	 */
	public String getDataProcessamentoInicial() {
		return dataProcessamentoInicial;
	}

	/**
	 * @param dataProcessamentoInicial
	 *            O dataProcessamentoInicial a ser setado.
	 */
	public void setDataProcessamentoInicial(String dataProcessamentoInicial) {
		this.dataProcessamentoInicial = dataProcessamentoInicial;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador
	 *            O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}

	/**
	 * @return Retorna o campo indicadorAtualizar.
	 */
	public String getIndicadorAtualizar() {
		return indicadorAtualizar;
	}

	/**
	 * @param indicadorAtualizar
	 *            O indicadorAtualizar a ser setado.
	 */
	public void setIndicadorAtualizar(String indicadorAtualizar) {
		this.indicadorAtualizar = indicadorAtualizar;
	}

	/**
	 * @return Retorna o campo movimento.
	 */
	public String getMovimento() {
		return movimento;
	}

	/**
	 * @param movimento
	 *            O movimento a ser setado.
	 */
	public void setMovimento(String movimento) {
		this.movimento = movimento;
	}

	/**
	 * @return Retorna o campo movimentoRegistro.
	 */
	public String getMovimentoRegistro() {
		return movimentoRegistro;
	}

	/**
	 * @param movimentoRegistro
	 *            O movimentoRegistro a ser setado.
	 */
	public void setMovimentoRegistro(String movimentoRegistro) {
		this.movimentoRegistro = movimentoRegistro;
	}

	/**
	 * @return Retorna o campo numeroSequencialArquivo.
	 */
	public String getNumeroSequencialArquivo() {
		return numeroSequencialArquivo;
	}

	/**
	 * @param numeroSequencialArquivo
	 *            O numeroSequencialArquivo a ser setado.
	 */
	public void setNumeroSequencialArquivo(String numeroSequencialArquivo) {
		this.numeroSequencialArquivo = numeroSequencialArquivo;
	}

	/**
	 * @return Retorna o campo tipoMovimento.
	 */
	public String getTipoMovimento() {
		return tipoMovimento;
	}

	/**
	 * @param tipoMovimento
	 *            O tipoMovimento a ser setado.
	 */
	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	/**
	 * @return Retorna o campo movimentoCorrigido.
	 */
	public String getMovimentoCorrigido() {
		return movimentoCorrigido;
	}

	/**
	 * @param movimentoCorrigido
	 *            O movimentoCorrigido a ser setado.
	 */
	public void setMovimentoCorrigido(String movimentoCorrigido) {
		this.movimentoCorrigido = movimentoCorrigido;
	}

	/**
	 * @return Retorna o campo idImovel.
	 */
	public String getIdImovel() {
		return idImovel;
	}

	/**
	 * @param idImovel
	 *            O idImovel a ser setado.
	 */
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	
	
	

	/**
	 * @return Retorna o campo imovelNaoEncontrado.
	 */
	public String getImovelNaoEncontrado() {
		return imovelNaoEncontrado;
	}

	/**
	 * @param imovelNaoEncontrado O imovelNaoEncontrado a ser setado.
	 */
	public void setImovelNaoEncontrado(String imovelNaoEncontrado) {
		this.imovelNaoEncontrado = imovelNaoEncontrado;
	}

	/**
	 * @return Retorna o campo nomeImovel.
	 */
	public String getNomeImovel() {
		return nomeImovel;
	}

	/**
	 * @param nomeImovel O nomeImovel a ser setado.
	 */
	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}

	public void reset(ActionMapping arg0, ServletRequest arg1) {
		// TODO Auto-generated method stub
		super.reset(arg0, arg1);

		this.idNegativador = "";
		this.collNegativador = new ArrayList();
		this.tipoMovimento = "";
		this.idImovel = "";		
		this.nomeImovel = "";
		this.imovelNaoEncontrado ="false";
		this.numeroSequencialArquivo = "";
		this.dataProcessamentoInicial = "";
		this.dataProcessamentoFinal = "";
		this.movimento = "";
		this.movimentoRegistro = "";
		this.indicadorAtualizar = "";
		this.movimentoCorrigido = "";

	}

	public String[] getArrayGerenciaRegional() {
		return arrayGerenciaRegional;
	}

	public void setArrayGerenciaRegional(String[] arrayGerenciaRegional) {
		this.arrayGerenciaRegional = arrayGerenciaRegional;
	}

	public String[] getArrayMotivoRejeicao() {
		return arrayMotivoRejeicao;
	}

	public void setArrayMotivoRejeicao(String[] arrayMotivoRejeicao) {
		this.arrayMotivoRejeicao = arrayMotivoRejeicao;
	}

	public String[] getArrayUnidadeNegocio() {
		return arrayUnidadeNegocio;
	}

	public void setArrayUnidadeNegocio(String[] arrayUnidadeNegocio) {
		this.arrayUnidadeNegocio = arrayUnidadeNegocio;
	}

	public String getDescricaoEloPolo() {
		return descricaoEloPolo;
	}

	public void setDescricaoEloPolo(String descricaoEloPolo) {
		this.descricaoEloPolo = descricaoEloPolo;
	}

	public String getDescricaoLocalidade() {
		return descricaoLocalidade;
	}

	public void setDescricaoLocalidade(String descricaoLocalidade) {
		this.descricaoLocalidade = descricaoLocalidade;
	}

	public String getGerenciaRegional() {
		return gerenciaRegional;
	}

	public void setGerenciaRegional(String gerenciaRegional) {
		this.gerenciaRegional = gerenciaRegional;
	}

	public String getIdEloPolo() {
		return idEloPolo;
	}

	public void setIdEloPolo(String idEloPolo) {
		this.idEloPolo = idEloPolo;
	}

	public String getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}
 
	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

	public String getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setUnidadeNegocio(String unidadeNegocio) {
		this.unidadeNegocio = unidadeNegocio;
	}

	
	
	

}
