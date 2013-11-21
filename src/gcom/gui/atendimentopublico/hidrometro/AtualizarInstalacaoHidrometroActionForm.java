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
* Rômulo Aurélio de Melo Souza Filho
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
package gcom.gui.atendimentopublico.hidrometro;

import gcom.micromedicao.hidrometro.HidrometroInstalacaoHistorico;
import gcom.micromedicao.hidrometro.HidrometroLocalInstalacao;
import gcom.micromedicao.hidrometro.HidrometroProtecao;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.util.Util;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * <<Descrição da classe>>
 * 
 * @author lms
 * @date 17/07/2006
 */
public class AtualizarInstalacaoHidrometroActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	String idOrdemServico;
	String nomeOrdemServico;
	String matriculaImovel;	
	String inscricaoImovel;
	String clienteUsuario;
	String cpfCnpjCliente;	
	String situacaoLigacaoAgua;
	String situacaoLigacaoEsgoto;
	String medicaoTipo;
	String hidrometro;
	String localInstalacao;
	String protecao;
	String indicadorExistenciaCavalete;
	String leituraInstalacao;
	String numeroSelo;
	String usuarioNaoEncontrado;
	String numeroLacre;	
	String leituraCorte;
	String leituraSupressao;
	String leituraRetirada;
	private String dataInstalacao;
	private String tipoPoco;
	
	String reset = "false";
	private String veioEncerrarOS;
	private Date dataCorrente;
	
    /*
	 * Colocado por Rômulo Aurelio em 02/08/2008
	 * 
	 */
    private String idImovel;
    
    private String permissaoAtualizarInstalacaoHidrometrosemRA;
    
    private String idLigacaoOrigem;
	
	
	public String getVeioEncerrarOS() {
		return veioEncerrarOS;
	}

	public void setVeioEncerrarOS(String veioEncerrarOS) {
		this.veioEncerrarOS = veioEncerrarOS;
	}

	public void reset() {
		this.idOrdemServico = null;
		this.nomeOrdemServico = null;
		this.matriculaImovel = null;	
		this.inscricaoImovel = null;
		this.clienteUsuario = null;
		this.cpfCnpjCliente = null;	
		this.situacaoLigacaoAgua = null;
		this.situacaoLigacaoEsgoto = null;
		resetHidrometro();		
	}
	
	public void resetHidrometro() {
		this.dataInstalacao = null;
		this.medicaoTipo = null;
		this.hidrometro = null;
		this.localInstalacao = null;
		this.protecao = null;
		this.indicadorExistenciaCavalete = null;
		this.leituraInstalacao = null;
		this.numeroSelo = null;
		this.numeroLacre = null;
		this.leituraCorte = null;
		this.leituraSupressao = null;
		this.leituraRetirada = null;
		this.reset = "false";
	}	
	
	
	
	public String getDataInstalacao() {
		return dataInstalacao;
	}

	public void setDataInstalacao(String dataInstalacao) {
		this.dataInstalacao = dataInstalacao;
	}

	public String getNomeOrdemServico() {
		return nomeOrdemServico;
	}
	public void setNomeOrdemServico(String nomeOrdemServico) {
		this.nomeOrdemServico = nomeOrdemServico;
	}
	public String getIdOrdemServico() {
		return idOrdemServico;
	}
	public void setIdOrdemServico(String idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	public String getClienteUsuario() {
		return clienteUsuario;
	}
	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}
	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}
	public String getHidrometro() {
		return hidrometro;
	}
	public void setHidrometro(String hidrometro) {
		this.hidrometro = hidrometro;
	}
	public String getIndicadorExistenciaCavalete() {
		return indicadorExistenciaCavalete;
	}
	public void setIndicadorExistenciaCavalete(String indicadorExistenciaCavalete) {
		this.indicadorExistenciaCavalete = indicadorExistenciaCavalete;
	}
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}
	public String getLeituraCorte() {
		return leituraCorte;
	}
	public void setLeituraCorte(String leituraCorte) {
		this.leituraCorte = leituraCorte;
	}
	public String getLeituraInstalacao() {
		return leituraInstalacao;
	}
	public void setLeituraInstalacao(String leituraInstalacao) {
		this.leituraInstalacao = leituraInstalacao;
	}
	public String getLeituraRetirada() {
		return leituraRetirada;
	}
	public void setLeituraRetirada(String leituraRetirada) {
		this.leituraRetirada = leituraRetirada;
	}
	public String getLeituraSupressao() {
		return leituraSupressao;
	}
	public void setLeituraSupressao(String leituraSupressao) {
		this.leituraSupressao = leituraSupressao;
	}
	public String getLocalInstalacao() {
		return localInstalacao;
	}
	public void setLocalInstalacao(String localInstalacao) {
		this.localInstalacao = localInstalacao;
	}
	public String getMatriculaImovel() {
		return matriculaImovel;
	}
	public void setMatriculaImovel(String matriculaImovel) {
		this.matriculaImovel = matriculaImovel;
	}
	public String getNumeroSelo() {
		return numeroSelo;
	}
	public void setNumeroSelo(String numeroSelo) {
		this.numeroSelo = numeroSelo;
	}
	public String getProtecao() {
		return protecao;
	}
	public void setProtecao(String protecao) {
		this.protecao = protecao;
	}
	public String getSituacaoLigacaoAgua() {
		return situacaoLigacaoAgua;
	}
	public void setSituacaoLigacaoAgua(String situacaoLigacaoAgua) {
		this.situacaoLigacaoAgua = situacaoLigacaoAgua;
	}
	public String getSituacaoLigacaoEsgoto() {
		return situacaoLigacaoEsgoto;
	}
	public void setSituacaoLigacaoEsgoto(String situacaoLigacaoEsgoto) {
		this.situacaoLigacaoEsgoto = situacaoLigacaoEsgoto;
	}
	public String getMedicaoTipo() {
		return medicaoTipo;
	}
	public void setMedicaoTipo(String medicaoTipo) {
		this.medicaoTipo = medicaoTipo;
	}
	
	public HidrometroInstalacaoHistorico setFormValues(HidrometroInstalacaoHistorico hidrometroInstalacaoHistorico) {
		
		/*
		 * Campos obrigatórios
		 */
		
		//número leitura instalação
		hidrometroInstalacaoHistorico.setNumeroLeituraInstalacao(Integer.parseInt(getLeituraInstalacao()));		
		//medição tipo
		MedicaoTipo medicaoTipo = new MedicaoTipo();
		medicaoTipo.setId(Integer.parseInt(getMedicaoTipo()));
		hidrometroInstalacaoHistorico.setMedicaoTipo(medicaoTipo);
		//hidrômetro local instalação
		HidrometroLocalInstalacao hidrometroLocalInstalacao = new HidrometroLocalInstalacao();
		hidrometroLocalInstalacao.setId(Integer.parseInt(getLocalInstalacao()));		
		hidrometroInstalacaoHistorico.setHidrometroLocalInstalacao(hidrometroLocalInstalacao);
		//proteção
		HidrometroProtecao hidrometroProtecao = new HidrometroProtecao();
		hidrometroProtecao.setId(Integer.parseInt(getProtecao()));
		hidrometroInstalacaoHistorico.setHidrometroProtecao(hidrometroProtecao);
		//cavalete
		hidrometroInstalacaoHistorico.setIndicadorExistenciaCavalete(Short.parseShort(getIndicadorExistenciaCavalete()));
		
		//data última alteração
		hidrometroInstalacaoHistorico.setUltimaAlteracao(getDataCorrente());
		
		/*
		 * Campos opcionais 
		 */

		//data retirada hidrômetro??? Não aparece no formulário
		//leitura retirada
		hidrometroInstalacaoHistorico.setNumeroLeituraRetirada(Util.converterStringParaInteger(getLeituraRetirada()));
		//leitura corte
		hidrometroInstalacaoHistorico.setNumeroLeituraCorte(Util.converterStringParaInteger(getLeituraCorte()));
		//leitura supressão
		hidrometroInstalacaoHistorico.setNumeroLeituraSupressao(Util.converterStringParaInteger(getLeituraSupressao()));
		//numero selo
		hidrometroInstalacaoHistorico.setNumeroSelo(getNumeroSelo());
		hidrometroInstalacaoHistorico.setDataInstalacao(Util.converteStringParaDate(getDataInstalacao()));
		
        // Numero do lacre
        if ( getNumeroLacre() != null ){
        	hidrometroInstalacaoHistorico.setNumeroLacre( getNumeroLacre() );
        }		
		
		return hidrometroInstalacaoHistorico;
	}

	public String getReset() {
		return reset;
	}

	public void setReset(String reset) {
		this.reset = reset;
	}

	public Date getDataCorrente() {
		return dataCorrente;
	}

	public void setDataCorrente(Date dataCorrente) {
		this.dataCorrente = dataCorrente;
	}

	public String getNumeroLacre() {
		return numeroLacre;
	}

	public void setNumeroLacre(String numeroLacre) {
		this.numeroLacre = numeroLacre;
	}

	public String getUsuarioNaoEncontrado() {
		return usuarioNaoEncontrado;
	}

	public void setUsuarioNaoEncontrado(String usuarioNaoEncontrado) {
		this.usuarioNaoEncontrado = usuarioNaoEncontrado;
	}

	public String getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}

	public String getIdLigacaoOrigem() {
		return idLigacaoOrigem;
	}

	public void setIdLigacaoOrigem(String idLigacaoOrigem) {
		this.idLigacaoOrigem = idLigacaoOrigem;
	}

	public String getPermissaoAtualizarInstalacaoHidrometrosemRA() {
		return permissaoAtualizarInstalacaoHidrometrosemRA;
	}

	public void setPermissaoAtualizarInstalacaoHidrometrosemRA(
			String permissaoAtualizarInstalacaoHidrometrosemRA) {
		this.permissaoAtualizarInstalacaoHidrometrosemRA = permissaoAtualizarInstalacaoHidrometrosemRA;
	}

	public String getTipoPoco() {
		return tipoPoco;
	}

	public void setTipoPoco(String tipoPoco) {
		this.tipoPoco = tipoPoco;
	}
	
	
}
