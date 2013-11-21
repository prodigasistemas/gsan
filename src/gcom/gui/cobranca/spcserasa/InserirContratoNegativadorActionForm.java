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

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form responsável pelas propiedades do caso de uso de inserir o
 * contrato do negativador.
 * 
 * @author Yara Taciane de Souza
 * @date 18/12/2007
 */
public class InserirContratoNegativadorActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;

	private String idNegativador;

	private Collection colecaoNegativador;

	private String numeroContrato;

	private String descricaoEmailEnvioArquivo;

	private String codigoConvenio;

	private String valorContrato;

	private String valorTarifaInclusao;	
	
	private String numeroPrazoInclusao;

	private String dataContratoInicio;

	private String dataContratoFim;

	/**
	 * <Breve descrição sobre o caso de uso>
	 *
	 * <Identificador e nome do caso de uso>
	 *
	 * <Breve descrição sobre o subfluxo>
	 *
	 * <Identificador e nome do subfluxo>	
	 *
	 * <Breve descrição sobre o fluxo secundário>
	 *
	 * <Identificador e nome do fluxo secundário> 
	 *
	 * @author Desenvolvedor 02
	 * @date 19/12/2007
	 *
	 * @param arg0
	 * @param arg1
	 */
	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		// TODO Auto-generated method stub
		//super.reset(arg0, arg1);
		
		this.idNegativador="";

		this.colecaoNegativador= new ArrayList();

		this.numeroContrato="";

		this.descricaoEmailEnvioArquivo="";

		this.codigoConvenio="";

		this.valorContrato="";

		this.valorTarifaInclusao="";	
		
		this.numeroPrazoInclusao="";

		this.dataContratoInicio="";

		this.dataContratoFim="";
	}
	
	
	/**
	 * @return Retorna o campo codigoConvenio.
	 */
	public String getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @param codigoConvenio
	 *            O codigoConvenio a ser setado.
	 */
	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}
	
	/**
	 * @return Retorna o campo dataContratoFim.
	 */
	public String getDataContratoFim() {
		return dataContratoFim;
	}

	/**
	 * @param dataContratoFim
	 *            O dataContratoFim a ser setado.
	 */
	public void setDataContratoFim(String dataContratoFim) {
		this.dataContratoFim = dataContratoFim;
	}

	/**
	 * @return Retorna o campo dataContratoInicio.
	 */
	public String getDataContratoInicio() {
		return dataContratoInicio;
	}

	/**
	 * @param dataContratoInicio
	 *            O dataContratoInicio a ser setado.
	 */
	public void setDataContratoInicio(String dataContratoInicio) {
		this.dataContratoInicio = dataContratoInicio;
	}

	/**
	 * @return Retorna o campo descricaoEmailEnvioArquivo.
	 */
	public String getDescricaoEmailEnvioArquivo() {
		return descricaoEmailEnvioArquivo;
	}

	/**
	 * @param descricaoEmailEnvioArquivo
	 *            O descricaoEmailEnvioArquivo a ser setado.
	 */
	public void setDescricaoEmailEnvioArquivo(String descricaoEmailEnvioArquivo) {
		this.descricaoEmailEnvioArquivo = descricaoEmailEnvioArquivo;
	}

	/**
	 * @return Retorna o campo numeroContrato.
	 */
	public String getNumeroContrato() {
		return numeroContrato;
	}

	/**
	 * @param numeroContrato
	 *            O numeroContrato a ser setado.
	 */
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	/**
	 * @return Retorna o campo numeroPrazoInclusao.
	 */
	public String getNumeroPrazoInclusao() {
		return numeroPrazoInclusao;
	}

	/**
	 * @param numeroPrazoInclusao
	 *            O numeroPrazoInclusao a ser setado.
	 */
	public void setNumeroPrazoInclusao(String numeroPrazoInclusao) {
		this.numeroPrazoInclusao = numeroPrazoInclusao;
	}

	/**
	 * @return Retorna o campo valorContrato.
	 */
	public String getValorContrato() {
		return valorContrato;
	}

	/**
	 * @param valorContrato
	 *            O valorContrato a ser setado.
	 */
	public void setValorContrato(String valorContrato) {
		this.valorContrato = valorContrato;
	}

	/**
	 * @return Retorna o campo valorTarifaInclusao.
	 */
	public String getValorTarifaInclusao() {
		return valorTarifaInclusao;
	}

	/**
	 * @param valorTarifaInclusao
	 *            O valorTarifaInclusao a ser setado.
	 */
	public void setValorTarifaInclusao(String valorTarifaInclusao) {
		this.valorTarifaInclusao = valorTarifaInclusao;
	}

	/**
	 * @return Retorna o campo colecaoNegativador.
	 */
	public Collection getColecaoNegativador() {
		return colecaoNegativador;
	}

	/**
	 * @param colecaoNegativador O colecaoNegativador a ser setado.
	 */
	public void setColecaoNegativador(Collection colecaoNegativador) {
		this.colecaoNegativador = colecaoNegativador;
	}

	/**
	 * @return Retorna o campo idNegativador.
	 */
	public String getIdNegativador() {
		return idNegativador;
	}

	/**
	 * @param idNegativador O idNegativador a ser setado.
	 */
	public void setIdNegativador(String idNegativador) {
		this.idNegativador = idNegativador;
	}


	

}