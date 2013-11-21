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
package gcom.relatorio.cobranca;

import java.math.BigDecimal;


public class ProtocoloDocumentoCobrancaRelatorioHelper {
	
	private Integer idGerencia;
	private String gerencia;
	private String nomeGerencia;
	private Integer idLocalidade;
	private String localidade;
	private Integer idSetorComercial;
	private Integer quantidade;
	private BigDecimal valorDocumentos;
    private Integer seqInicial;
    private Integer seqFinal;
    private String empresa;
    
    private Integer idUnidadeNegocio;
	private String nomeUnidadeNegocio;

    public ProtocoloDocumentoCobrancaRelatorioHelper(Integer idGerencia, String gerencia, String nomeGerencia, Integer idLocalidade, 
    		String localidade, Integer idSetorComercial, Integer quantidade, BigDecimal valorDocumentos, Integer seqInicial,
    		Integer seqFinal, String empresa) {

		this.idGerencia = idGerencia;
		this.gerencia = gerencia;
		this.nomeGerencia = nomeGerencia;
		this.idLocalidade = idLocalidade;
		this.localidade = localidade;
		this.idSetorComercial = idSetorComercial;
		this.quantidade = quantidade;
		this.valorDocumentos = valorDocumentos;
		this.seqInicial = seqInicial;
		this.seqFinal = seqFinal;
		this.empresa = empresa;
	}
    
    public ProtocoloDocumentoCobrancaRelatorioHelper(Integer idGerencia, String gerencia, String nomeGerencia, 
    		Integer idUnidadeNegocio, String nomeUnidadeNegocio, 
    		Integer idLocalidade, String localidade, Integer idSetorComercial, 
    		Integer quantidade, BigDecimal valorDocumentos, Integer seqInicial,
    		Integer seqFinal, String empresa) {

		this.idGerencia = idGerencia;
		this.gerencia = gerencia;
		this.nomeGerencia = nomeGerencia;
		this.idUnidadeNegocio = idUnidadeNegocio;
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
		this.idLocalidade = idLocalidade;
		this.localidade = localidade;
		this.idSetorComercial = idSetorComercial;
		this.quantidade = quantidade;
		this.valorDocumentos = valorDocumentos;
		this.seqInicial = seqInicial;
		this.seqFinal = seqFinal;
		this.empresa = empresa;
	}

	public ProtocoloDocumentoCobrancaRelatorioHelper() {
    }

	/**
	 * @return Retorna o campo gerencia.
	 */
	public String getGerencia() {
		return gerencia;
	}

	/**
	 * @param gerencia O gerencia a ser setado.
	 */
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	/**
	 * @return Retorna o campo idGerencia.
	 */
	public Integer getIdGerencia() {
		return idGerencia;
	}

	/**
	 * @param idGerencia O idGerencia a ser setado.
	 */
	public void setIdGerencia(Integer idGerencia) {
		this.idGerencia = idGerencia;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo idSetorComercial.
	 */
	public Integer getIdSetorComercial() {
		return idSetorComercial;
	}

	/**
	 * @param idSetorComercial O idSetorComercial a ser setado.
	 */
	public void setIdSetorComercial(Integer idSetorComercial) {
		this.idSetorComercial = idSetorComercial;
	}

	/**
	 * @return Retorna o campo localidade.
	 */
	public String getLocalidade() {
		return localidade;
	}

	/**
	 * @param localidade O localidade a ser setado.
	 */
	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	/**
	 * @return Retorna o campo quantidade.
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade O quantidade a ser setado.
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return Retorna o campo valorDocumentos.
	 */
	public BigDecimal getValorDocumentos() {
		return valorDocumentos;
	}

	/**
	 * @param valorDocumentos O valorDocumentos a ser setado.
	 */
	public void setValorDocumentos(BigDecimal valorDocumentos) {
		this.valorDocumentos = valorDocumentos;
	}

	/**
	 * @return Retorna o campo empresa.
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * @param empresa O empresa a ser setado.
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * @return Retorna o campo seqFinal.
	 */
	public Integer getSeqFinal() {
		return seqFinal;
	}

	/**
	 * @param seqFinal O seqFinal a ser setado.
	 */
	public void setSeqFinal(Integer seqFinal) {
		this.seqFinal = seqFinal;
	}

	/**
	 * @return Retorna o campo seqInicial.
	 */
	public Integer getSeqInicial() {
		return seqInicial;
	}

	/**
	 * @param seqInicial O seqInicial a ser setado.
	 */
	public void setSeqInicial(Integer seqInicial) {
		this.seqInicial = seqInicial;
	}

	/**
	 * @return Retorna o campo nomeGerencia.
	 */
	public String getNomeGerencia() {
		return nomeGerencia;
	}

	/**
	 * @param nomeGerencia O nomeGerencia a ser setado.
	 */
	public void setNomeGerencia(String nomeGerencia) {
		this.nomeGerencia = nomeGerencia;
	}

	public Integer getIdUnidadeNegocio() {
		return idUnidadeNegocio;
	}

	public void setIdUnidadeNegocio(Integer idUnidadeNegocio) {
		this.idUnidadeNegocio = idUnidadeNegocio;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public void setNomeUnidadeNegocio(String nomeUnidadeNegocio) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

}
