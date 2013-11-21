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
package gcom.gui.atendimentopublico.ordemservico;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descrição da classe
 * 
 * @author Rômulo Aurélio
 * @date 04/08/2006
 */
public class InserirTipoServicoReferenciaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String descricao;

	private String abreviatura;

	private String indicadorExistenciaOsReferencia;

	private String situacaoOSAntes;

	private String situacaoOSApos;

	private String tipoServico;

	private String nomeTipoServico;
	
	private String indicadorDiagnostico;
	
	private String indicadorFiscalizacao;
	
	public void reset(){
		this.descricao = null;
		this.abreviatura  = null;
		this.indicadorExistenciaOsReferencia = null;
		this.situacaoOSAntes = null;
		this.situacaoOSApos = null;
		this.tipoServico = null;
		this.nomeTipoServico = null;		
		this.indicadorDiagnostico = null;		
		this.indicadorFiscalizacao = null;
	}
	public String getIndicadorDiagnostico() {
		return indicadorDiagnostico;
	}

	public void setIndicadorDiagnostico(String indicadorDiagnostico) {
		this.indicadorDiagnostico = indicadorDiagnostico;
	}

	public String getIndicadorFiscalizacao() {
		return indicadorFiscalizacao;
	}

	public void setIndicadorFiscalizacao(String indicadorFiscalizacao) {
		this.indicadorFiscalizacao = indicadorFiscalizacao;
	}

	/**
	 * @return Retorna o campo nomeTipoServico.
	 */
	public String getNomeTipoServico() {
		return nomeTipoServico;
	}

	/**
	 * @param nomeTipoServico O nomeTipoServico a ser setado.
	 */
	public void setNomeTipoServico(String nomeTipoServico) {
		this.nomeTipoServico = nomeTipoServico;
	}

	/**
	 * @return Retorna o campo abreviatura.
	 */
	public String getAbreviatura() {
		return abreviatura;
	}

	/**
	 * @param abreviatura
	 *            O abreviatura a ser setado.
	 */
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo indicadorExistenciaOsReferencia.
	 */
	public String getIndicadorExistenciaOsReferencia() {
		return indicadorExistenciaOsReferencia;
	}

	/**
	 * @param indicadorExistenciaOsReferencia
	 *            O indicadorExistenciaOsReferencia a ser setado.
	 */
	public void setIndicadorExistenciaOsReferencia(
			String indicadorExistenciaOsReferencia) {
		this.indicadorExistenciaOsReferencia = indicadorExistenciaOsReferencia;
	}

	/**
	 * @return Retorna o campo situacaoOSAntes.
	 */
	public String getSituacaoOSAntes() {
		return situacaoOSAntes;
	}

	/**
	 * @param situacaoOSAntes
	 *            O situacaoOSAntes a ser setado.
	 */
	public void setSituacaoOSAntes(String situacaoOSAntes) {
		this.situacaoOSAntes = situacaoOSAntes;
	}

	/**
	 * @return Retorna o campo situacaoOSApos.
	 */
	public String getSituacaoOSApos() {
		return situacaoOSApos;
	}

	/**
	 * @param situacaoOSApos
	 *            O situacaoOSApos a ser setado.
	 */
	public void setSituacaoOSApos(String situacaoOSApos) {
		this.situacaoOSApos = situacaoOSApos;
	}

	/**
	 * @return Retorna o campo tipoServicao.
	 */
	public String getTipoServico() {
		return tipoServico;
	}

	/**
	 * @param tipoServicao
	 *            O tipoServicao a ser setado.
	 */
	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

}
