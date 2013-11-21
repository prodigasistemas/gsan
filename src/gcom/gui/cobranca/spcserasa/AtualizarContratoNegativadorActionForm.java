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
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AtualizarContratoNegativadorActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
    private String id;
	
    private String numeroContrato;

    private String codigoConvenio;
  
    private String dataContratoInicio;

    private String dataContratoFim;
 
    private String dataContratoEncerramento;

    private String descricaoEmailEnvioArquivo;

    private String numeroSequencialEnvio;
 
    private String numeroSequencialRetorno;

    private String valorContrato;
    
    private String valorTarifaInclusao;

    private String numeroInclusoesContratadas;

    private String numeroInclusoesEnviadas;

    private String numeroExclusoesEnviadas;
    
    private String numeroTamanhoRegistroMovimento;

    private String numeroPrazoInclusao;

    private String ultimaAlteracao;

    private String idNegativador;
    
    private String negativadorCliente;

    private Collection colecaoContratoMotivoCancelamento;
    
    private String idContratoMotivoCancelamento;
    
    private String time;
    
    private String vigente;
    

    
    
	
		

	/**
	 * @return Retorna o campo codigoConvenio.
	 */
	public String getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @param codigoConvenio O codigoConvenio a ser setado.
	 */
	public void setCodigoConvenio(String codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}
	

	/**
	 * @return Retorna o campo dataContratoEncerramento.
	 */
	public String getDataContratoEncerramento() {
		return dataContratoEncerramento;
	}

	/**
	 * @param dataContratoEncerramento O dataContratoEncerramento a ser setado.
	 */
	public void setDataContratoEncerramento(String dataContratoEncerramento) {
		this.dataContratoEncerramento = dataContratoEncerramento;
	}

	/**
	 * @return Retorna o campo dataContratoFim.
	 */
	public String getDataContratoFim() {
		return dataContratoFim;
	}

	/**
	 * @param dataContratoFim O dataContratoFim a ser setado.
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
	 * @param dataContratoInicio O dataContratoInicio a ser setado.
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
	 * @param descricaoEmailEnvioArquivo O descricaoEmailEnvioArquivo a ser setado.
	 */
	public void setDescricaoEmailEnvioArquivo(String descricaoEmailEnvioArquivo) {
		this.descricaoEmailEnvioArquivo = descricaoEmailEnvioArquivo;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id O id a ser setado.
	 */
	public void setId(String id) {
		this.id = id;
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

	/**
	 * @return Retorna o campo numeroContrato.
	 */
	public String getNumeroContrato() {
		return numeroContrato;
	}

	/**
	 * @param numeroContrato O numeroContrato a ser setado.
	 */
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	/**
	 * @return Retorna o campo numeroExclusoesEnviadas.
	 */
	public String getNumeroExclusoesEnviadas() {
		return numeroExclusoesEnviadas;
	}

	/**
	 * @param numeroExclusoesEnviadas O numeroExclusoesEnviadas a ser setado.
	 */
	public void setNumeroExclusoesEnviadas(String numeroExclusoesEnviadas) {
		this.numeroExclusoesEnviadas = numeroExclusoesEnviadas;
	}

	/**
	 * @return Retorna o campo numeroInclusoesContratadas.
	 */
	public String getNumeroInclusoesContratadas() {
		return numeroInclusoesContratadas;
	}

	/**
	 * @param numeroInclusoesContratadas O numeroInclusoesContratadas a ser setado.
	 */
	public void setNumeroInclusoesContratadas(String numeroInclusoesContratadas) {
		this.numeroInclusoesContratadas = numeroInclusoesContratadas;
	}

	/**
	 * @return Retorna o campo numeroInclusoesEnviadas.
	 */
	public String getNumeroInclusoesEnviadas() {
		return numeroInclusoesEnviadas;
	}

	/**
	 * @param numeroInclusoesEnviadas O numeroInclusoesEnviadas a ser setado.
	 */
	public void setNumeroInclusoesEnviadas(String numeroInclusoesEnviadas) {
		this.numeroInclusoesEnviadas = numeroInclusoesEnviadas;
	}

	/**
	 * @return Retorna o campo numeroPrazoInclusao.
	 */
	public String getNumeroPrazoInclusao() {
		return numeroPrazoInclusao;
	}

	/**
	 * @param numeroPrazoInclusao O numeroPrazoInclusao a ser setado.
	 */
	public void setNumeroPrazoInclusao(String numeroPrazoInclusao) {
		this.numeroPrazoInclusao = numeroPrazoInclusao;
	}

	/**
	 * @return Retorna o campo numeroSequencialEnvio.
	 */
	public String getNumeroSequencialEnvio() {
		return numeroSequencialEnvio;
	}

	/**
	 * @param numeroSequencialEnvio O numeroSequencialEnvio a ser setado.
	 */
	public void setNumeroSequencialEnvio(String numeroSequencialEnvio) {
		this.numeroSequencialEnvio = numeroSequencialEnvio;
	}

	/**
	 * @return Retorna o campo numeroSequencialRetorno.
	 */
	public String getNumeroSequencialRetorno() {
		return numeroSequencialRetorno;
	}

	/**
	 * @param numeroSequencialRetorno O numeroSequencialRetorno a ser setado.
	 */
	public void setNumeroSequencialRetorno(String numeroSequencialRetorno) {
		this.numeroSequencialRetorno = numeroSequencialRetorno;
	}

	/**
	 * @return Retorna o campo numeroTamanhoRegistroMovimento.
	 */
	public String getNumeroTamanhoRegistroMovimento() {
		return numeroTamanhoRegistroMovimento;
	}

	/**
	 * @param numeroTamanhoRegistroMovimento O numeroTamanhoRegistroMovimento a ser setado.
	 */
	public void setNumeroTamanhoRegistroMovimento(
			String numeroTamanhoRegistroMovimento) {
		this.numeroTamanhoRegistroMovimento = numeroTamanhoRegistroMovimento;
	}

	/**
	 * @return Retorna o campo ultimaAlteracao.
	 */
	public String getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	/**
	 * @param ultimaAlteracao O ultimaAlteracao a ser setado.
	 */
	public void setUltimaAlteracao(String ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	/**
	 * @return Retorna o campo valorContrato.
	 */
	public String getValorContrato() {
		return valorContrato;
	}

	/**
	 * @param valorContrato O valorContrato a ser setado.
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
	 * @param valorTarifaInclusao O valorTarifaInclusao a ser setado.
	 */
	public void setValorTarifaInclusao(String valorTarifaInclusao) {
		this.valorTarifaInclusao = valorTarifaInclusao;
	}

	public ActionErrors validate(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
        /** @todo: finish this method, this is just the skeleton. */
        return null;
    }

	public void reset(ActionMapping arg0, ServletRequest arg1) {
    	
       super.reset(arg0, arg1);	
       this.id="";
    	
       this.numeroContrato="";

       this.codigoConvenio="";
      
       this.dataContratoInicio="";

       this.dataContratoFim="";
     
       this.dataContratoEncerramento="";

       this.descricaoEmailEnvioArquivo="";

       this.numeroSequencialEnvio="";
     
       this.numeroSequencialRetorno="";

       this.valorContrato="";
        
       this.valorTarifaInclusao="";

       this.numeroInclusoesContratadas="";

       this.numeroInclusoesEnviadas="";

       this.numeroExclusoesEnviadas="";
        
       this.numeroTamanhoRegistroMovimento="";

       this.numeroPrazoInclusao="";

       this.ultimaAlteracao="";

       this.idNegativador="";
        
       this.negativadorCliente="";

       this.colecaoContratoMotivoCancelamento=new ArrayList();
        
       this.idContratoMotivoCancelamento="";
        
       this.time="";
        
       this.vigente="true";
    	
    	
    }

	/**
	 * @return Retorna o campo negativadorCliente.
	 */
	public String getNegativadorCliente() {
		return negativadorCliente;
	}

	/**
	 * @param negativadorCliente O negativadorCliente a ser setado.
	 */
	public void setNegativadorCliente(String negativadorCliente) {
		this.negativadorCliente = negativadorCliente;
	}

	/**
	 * @return Retorna o campo idContratoMotivoCancelamento.
	 */
	public String getIdContratoMotivoCancelamento() {
		return idContratoMotivoCancelamento;
	}

	/**
	 * @param idContratoMotivoCancelamento O idContratoMotivoCancelamento a ser setado.
	 */
	public void setIdContratoMotivoCancelamento(String idContratoMotivoCancelamento) {
		this.idContratoMotivoCancelamento = idContratoMotivoCancelamento;
	}

	/**
	 * @return Retorna o campo colecaoContratoMotivoCancelamento.
	 */
	public Collection getColecaoContratoMotivoCancelamento() {
		return colecaoContratoMotivoCancelamento;
	}

	/**
	 * @param colecaoContratoMotivoCancelamento O colecaoContratoMotivoCancelamento a ser setado.
	 */
	public void setColecaoContratoMotivoCancelamento(
			Collection colecaoContratoMotivoCancelamento) {
		this.colecaoContratoMotivoCancelamento = colecaoContratoMotivoCancelamento;
	}

	/**
	 * @return Retorna o campo time.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time O time a ser setado.
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return Retorna o campo vigente.
	 */
	public String getVigente() {
		return vigente;
	}

	/**
	 * @param vigente O vigente a ser setado.
	 */
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}

	

}

