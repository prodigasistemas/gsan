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
package gcom.gui.cadastro.imovel;

import gcom.atendimentopublico.ligacaoesgoto.LigacaoEsgotoEsgotamento;
import gcom.cadastro.endereco.Cep;
import gcom.cadastro.endereco.EnderecoReferencia;
import gcom.cadastro.endereco.Logradouro;
import gcom.cadastro.endereco.LogradouroCep;
import gcom.cadastro.funcionario.Funcionario;
import gcom.cadastro.imovel.Categoria;
import gcom.cadastro.imovel.Despejo;
import gcom.cadastro.imovel.FiltroSubCategoria;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.imovel.ImovelContaEnvio;
import gcom.cadastro.imovel.ImovelSubcategoria;
import gcom.cadastro.imovel.ImovelTipoCobertura;
import gcom.cadastro.imovel.ImovelTipoConstrucao;
import gcom.cadastro.imovel.ImovelTipoHabitacao;
import gcom.cadastro.imovel.ImovelTipoPropriedade;
import gcom.cadastro.imovel.PiscinaVolumeFaixa;
import gcom.cadastro.imovel.PocoTipo;
import gcom.cadastro.imovel.ReservatorioVolumeFaixa;
import gcom.cadastro.imovel.Subcategoria;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasHelper;
import gcom.cadastro.imovel.bean.ImovelAbaCaracteristicasRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaConclusaoRetornoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaEnderecoHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeHelper;
import gcom.cadastro.imovel.bean.ImovelAbaLocalidadeRetornoHelper;
import gcom.cadastro.imovel.bean.InserirImovelHelper;
import gcom.fachada.Fachada;
import gcom.faturamento.consumotarifa.ConsumoTarifa;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.seguranca.acesso.PermissaoEspecial;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;
import gcom.seguranca.acesso.Funcionalidade;

/**
 * Classe responável pela conclusão do cadastro de um imóvel 
 *
 * @author Raphael Rossiter
 * @date 11/05/2009
 */
public class InserirImovelAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// localiza o action no objeto actionmapping
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obtendo uma instancia da sessao
		HttpSession sessao = httpServletRequest.getSession(false);

		DynaValidatorForm inserirImovelActionForm = (DynaValidatorForm) sessao
				.getAttribute("InserirImovelActionForm");

		Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

		// Cria Variaveis
		String idLocalidade = (String) inserirImovelActionForm.get("idLocalidade");
		String idSetorComercial = (String) inserirImovelActionForm.get("idSetorComercial");
		String idQuadra = (String) inserirImovelActionForm.get("idQuadra");
		String idQuadraFace = (String) inserirImovelActionForm.get("idQuadraFace");
		String lote = (String) inserirImovelActionForm.get("lote");
		String subLote = (String) inserirImovelActionForm.get("subLote");
		String testadaLote = (String) inserirImovelActionForm.get("testadaLote");
		String areaConstruida = (String) inserirImovelActionForm.get("areaConstruida");
		String faixaAreaConstruida = (String) inserirImovelActionForm.get("faixaAreaConstruida");
		String volumeReservatorioSuperior = (String) inserirImovelActionForm.get("reservatorioSuperior");
		String faixaVolumeReservatorioSuperior = (String) inserirImovelActionForm.get("faixaResevatorioSuperior");
		String volumeReservatorioInferior = (String) inserirImovelActionForm.get("reservatorioInferior");
		String faixaVolumeReservaotirio = (String) inserirImovelActionForm.get("faixaReservatorioInferior");
		String piscina = (String) inserirImovelActionForm.get("piscina");
		String jardim = (String) inserirImovelActionForm.get("jardim");
		String faixaPiscina = (String) inserirImovelActionForm.get("faixaPiscina");
		String pavimentoCalcada = (String) inserirImovelActionForm.get("pavimentoCalcada");
		String pavimentoRua = (String) inserirImovelActionForm.get("pavimentoRua");
		String fonteAbastecimento = (String) inserirImovelActionForm.get("fonteAbastecimento");
		String situacaoLigacaoAgua = (String) inserirImovelActionForm.get("situacaoLigacaoAgua");
		String situacaoLigacaoEsgoto = (String) inserirImovelActionForm.get("situacaoLigacaoEsgoto");
		String perfilImovel = (String) inserirImovelActionForm.get("perfilImovel");
		String poco = (String) inserirImovelActionForm.get("poco");
		String idLigacaoEsgotoEsgotamento = (String) inserirImovelActionForm.get("idLigacaoEsgotoEsgotamento");

		String tipoHabitacao = (String) inserirImovelActionForm.get("imovelTipoHabitacao");
		String tipoPropriedade = (String) inserirImovelActionForm.get("imovelTipoPropriedade");
		String tipoConstrucao = (String) inserirImovelActionForm.get("imovelTipoConstrucao");
		String tipoCobertura = (String) inserirImovelActionForm.get("imovelTipoCobertura");
		
		String pontoUtilizaco = (String) inserirImovelActionForm.get("numeroPontos");
		String numeroMoradores = (String) inserirImovelActionForm.get("numeroMoradores");
		String numeroIptu = (String) inserirImovelActionForm.get("numeroIptu");
		String numeroContratoCelpe = (String) inserirImovelActionForm.get("numeroContratoCelpe");
		String imovelContaEnvioForm = (String) inserirImovelActionForm.get("imovelContaEnvio");
		String cordenadasX = (String) inserirImovelActionForm.get("cordenadasUtmX");
		String cordenadasY = (String) inserirImovelActionForm.get("cordenadasUtmY");
		String extratoResponsavel = (String) inserirImovelActionForm.get("extratoResponsavel");
		// String nomeContaForm = (String) inserirImovelActionForm
		// .get("tipoOcupacao");
		String tipoDespejo = (String) inserirImovelActionForm.get("tipoDespejo");
		String idImovelPrincipal = (String) inserirImovelActionForm.get("idImovel");
		String sequencialRota = (String) inserirImovelActionForm.get("sequencialRota");
		String sequencialRotaEntrega = (String) inserirImovelActionForm.get("sequencialRotaEntrega");
		String idRotaEntrega = (String) inserirImovelActionForm.get("idRota");
		String idRotaAlternativa = (String) inserirImovelActionForm.get("idRotaAlternativa");
		String idFuncionario = (String) inserirImovelActionForm.get("idFuncionario"); 
		String numeroMedidorEnergia = (String) inserirImovelActionForm.get("numeroMedidorEnergia");
		String dataVisitaComercialInformada  = (String) inserirImovelActionForm.get("dataVisitaComercial");
		String numeroQuadraEntrega = (String) inserirImovelActionForm.get("numeroQuadraEntrega");
		
		String informacoesComplementares = (String) inserirImovelActionForm.get("informacoesComplementares");
		
		String indicadorNivelInstalacaoEsgoto = (String) inserirImovelActionForm.get("indicadorNivelInstalacaoEsgoto");
		
		// Obtém a instância da Fachada
		Fachada fachada = Fachada.getInstancia();
		
		Imovel imovel = new Imovel();
		
		// ABA LOCALIDADE
		ImovelAbaLocalidadeHelper helperLocalidade = new ImovelAbaLocalidadeHelper();
		helperLocalidade.setIdLocalidade(idLocalidade);
		helperLocalidade.setCodigoSetorComercial(idSetorComercial);
		helperLocalidade.setNumeroQuadra(idQuadra);
		helperLocalidade.setIdQuadraFace(idQuadraFace);
		helperLocalidade.setLote(lote);
		helperLocalidade.setSublote(subLote);
		ImovelAbaLocalidadeRetornoHelper resultadoAbaLocalidade = fachada.validarImovelAbaLocalidade(helperLocalidade);
		
		imovel.setLocalidade(resultadoAbaLocalidade.getLocalidade());
		imovel.setSetorComercial(resultadoAbaLocalidade.getSetorComercial());
		imovel.setQuadra(resultadoAbaLocalidade.getQuadra());
		imovel.setQuadraFace(resultadoAbaLocalidade.getQuadraFace());
		imovel.setLote(new Short(lote).shortValue());
		imovel.setSubLote(new Short(subLote).shortValue());

		if (naoVazio(testadaLote)) {
			imovel.setTestadaLote(new Short(testadaLote).shortValue());
		}
		if (naoVazio(sequencialRota)) {
			imovel.setNumeroSequencialRota(new Integer(sequencialRota));
		}
		
		// ABA ENDERECO
		Collection colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecos");
		//**********************************************************************
		// Autor: Ivan Sergio
		// Data: 23/07/2009
		// CRC2103
		// Guarda o endereco do Imovel para o caso em que o Inserir/Manter
		// cliente é invocado pelo Inserir/Manter Imovel como PopUp
		//**********************************************************************
		if (sessao.getAttribute("POPUP") != null) {
			if (sessao.getAttribute("POPUP").equals("true")) {
				if (colecaoEnderecos != null && !colecaoEnderecos.isEmpty()) {
					Object obj = (Object) colecaoEnderecos.iterator().next();
					if (!(obj instanceof Imovel)) {
						sessao.removeAttribute("colecaoEnderecos");
					}
					if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
						colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecosImovel");
					}
				}else if (sessao.getAttribute("colecaoEnderecosImovel") != null) {
					colecaoEnderecos = (Collection) sessao.getAttribute("colecaoEnderecosImovel");
				}
			}
		}
		//**********************************************************************
		
		ImovelAbaEnderecoHelper helperEndereco = new ImovelAbaEnderecoHelper();
		helperEndereco.setImovelEnderecos(colecaoEnderecos);
		helperEndereco.setSetorComercial(resultadoAbaLocalidade.getSetorComercial());
		helperEndereco.setUsuarioLogado(usuario);
		helperEndereco.setIdFuncionalidade(Funcionalidade.INSERIR_IMOVEL);
		
		fachada.validarImovelAbaEndereco(helperEndereco);
		
		Imovel imovelEndereco = (Imovel) Util.retonarObjetoDeColecao(colecaoEnderecos);
		
		Logradouro logradouro = null;
		if (imovelEndereco.getLogradouroCep() != null && 
			imovelEndereco.getLogradouroCep().getLogradouro() != null && 
			!imovelEndereco.getLogradouroCep().getLogradouro().equals("")) {
			Integer idLogradouro = imovelEndereco.getLogradouroCep().getLogradouro().getId();
			logradouro = new Logradouro();
			logradouro.setId(idLogradouro);
		} else {
			// ALTERAÇÃO FEITTA PARA FUNCIONAMENTO DA APLICAÇÃO, APAGAR
			// A CONDIÇÃO DO "SE NÃO",
			// ALTERAÇÃO DO VALOR DO LOGRADOURO ESTA SENDO ANALIZADA POR ARYED,
			// ANA E LEO NARDO NO DIA 16/03/2006
			logradouro = new Logradouro();
			logradouro.setId(new Integer("0"));
		}
		
		LogradouroCep logradouroCep = null;
		if (imovelEndereco.getLogradouroCep() != null && 
			imovelEndereco.getLogradouroCep().getCep() != null) {
			Integer cep = imovelEndereco.getLogradouroCep().getCep().getCepId();
			Cep cepObj = new Cep();
			cepObj.setCepId(cep);
			logradouroCep = fachada.pesquisarAssociacaoLogradouroCep(cepObj.getCepId(), logradouro.getId());
		}
		
		EnderecoReferencia enderecoReferencia = null;
		if (imovelEndereco.getEnderecoReferencia() != null) {
			Integer idEnderecoReferencia = imovelEndereco.getEnderecoReferencia().getId();
			if (idEnderecoReferencia != null && !idEnderecoReferencia.toString().trim().equals("")) {
				enderecoReferencia = new EnderecoReferencia();
				enderecoReferencia.setId(idEnderecoReferencia);
			}
		}
		
		imovel.setNumeroImovel(imovelEndereco.getNumeroImovel());
		imovel.setComplementoEndereco(imovelEndereco.getComplementoEndereco());
		imovel.setLogradouroCep(logradouroCep);
		imovel.setEnderecoReferencia(enderecoReferencia);
		imovel.setLogradouroBairro(imovelEndereco.getLogradouroBairro());
		imovel.setPerimetroInicial(imovelEndereco.getPerimetroInicial());
		imovel.setPerimetroFinal(imovelEndereco.getPerimetroFinal());
		
		// ABA CLIENTES
		Collection clientes = (Collection) sessao.getAttribute("imovelClientesNovos");
		fachada.validarImovelAbaCliente(clientes, usuario);
		
		// ABA SUBCATEGORIA ECONOMIAS
        Collection subcategorias = (Collection) sessao.getAttribute("colecaoImovelSubCategorias");
        Collection ramosAtividades = (Collection) sessao.getAttribute("colecaoImovelRamosAtividade");
		fachada.validarAbaInserirImovelSubcategoria(
				subcategorias, PermissaoEspecial.INSERIR_IMOVEL_PARA_ORGAO_PUBLICO, usuario);
		
		// ABA CARACTERISTICA
		ImovelAbaCaracteristicasHelper helperCaracteristica = new ImovelAbaCaracteristicasHelper();
		helperCaracteristica.setAreaConstruida(areaConstruida);
		helperCaracteristica.setIdAreaConstruidaFaixa(faixaAreaConstruida);
		helperCaracteristica.setIdPavimentoCalcada(pavimentoCalcada);
		helperCaracteristica.setIdPavimentoRua(pavimentoRua);
		helperCaracteristica.setIdFonteAbastecimento(fonteAbastecimento);
		helperCaracteristica.setIdLigacaoAguaSituacao(situacaoLigacaoAgua);
		helperCaracteristica.setIdLigacaoEsgotoSituacao(situacaoLigacaoEsgoto);
		helperCaracteristica.setIdLigacaoEsgotoEsgotamento(idLigacaoEsgotoEsgotamento);
		helperCaracteristica.setIdImovelPerfil(perfilImovel);
	
		
		
		//*************************************************
		// Autor: Ivan Sergio
		// Data: 23/04/2009
		// CRC1657
		//*************************************************
		// [FS0023] - Verificar Setor e Quadra
		//*************************************************
		helperCaracteristica.setIdSetorComercial(idSetorComercial);
		helperCaracteristica.setIdQuadra(idQuadra);
		//*************************************************
		
		// Autor: Nathalia santos
		// Data: 13/07/2011
		// RR201106690
		helperCaracteristica.setIdNivelInstalacaoEsgoto(indicadorNivelInstalacaoEsgoto);
		//*************************************************
		
		ImovelAbaCaracteristicasRetornoHelper resultadoAbaCaracteristicas = 
			fachada.validarImovelAbaCaracteristicas(helperCaracteristica);
		
		if (naoVazio(areaConstruida)) {
			imovel.setAreaConstruida(Util.formatarMoedaRealparaBigDecimal(areaConstruida));
		}
		imovel.setAreaConstruidaFaixa(resultadoAbaCaracteristicas.getAreaConstruidaFaixa());

		if (Util.verificarIdNaoVazio(faixaVolumeReservaotirio)) {
			ReservatorioVolumeFaixa reservatorioVolumeFaixaInf = new ReservatorioVolumeFaixa();
			reservatorioVolumeFaixaInf.setId(new Integer(faixaVolumeReservaotirio));
			imovel.setReservatorioVolumeFaixaInferior(reservatorioVolumeFaixaInf);
		}
		if (naoVazio(volumeReservatorioInferior)) {
			imovel.setVolumeReservatorioInferior(Util.formatarMoedaRealparaBigDecimal(volumeReservatorioInferior));
		}
		if (Util.verificarIdNaoVazio(faixaVolumeReservatorioSuperior)) {
			ReservatorioVolumeFaixa reservatorioVolumeFaixaSup = new ReservatorioVolumeFaixa();
			reservatorioVolumeFaixaSup.setId(new Integer(faixaVolumeReservatorioSuperior));
			imovel.setReservatorioVolumeFaixaSuperior(reservatorioVolumeFaixaSup);
		}
		if (naoVazio(volumeReservatorioSuperior)) {
			imovel.setVolumeReservatorioSuperior(Util.formatarMoedaRealparaBigDecimal(volumeReservatorioSuperior));
		}
		if (Util.verificarIdNaoVazio(faixaPiscina)) {
			PiscinaVolumeFaixa piscinaVolumeFaixa = new PiscinaVolumeFaixa();
			piscinaVolumeFaixa.setId(new Integer(faixaPiscina));
			imovel.setPiscinaVolumeFaixa(piscinaVolumeFaixa);
		}
		if (naoVazio(piscina)) {
			imovel.setVolumePiscina(Util.formatarMoedaRealparaBigDecimal(piscina));
		}
		if (naoVazio(jardim)) {
			imovel.setIndicadorJardim(new Short(jardim));
		}
		imovel.setPavimentoCalcada(resultadoAbaCaracteristicas.getPavimentoCalcada());
		imovel.setPavimentoRua(resultadoAbaCaracteristicas.getPavimentoRua());
		imovel.setFonteAbastecimento(resultadoAbaCaracteristicas.getFonteAbastecimento());
		imovel.setLigacaoAguaSituacao(resultadoAbaCaracteristicas.getLigacaoAguaSituacao());
		imovel.setLigacaoEsgotoSituacao(resultadoAbaCaracteristicas.getLigacaoEsgotoSituacao());
		imovel.setImovelPerfil(resultadoAbaCaracteristicas.getImovelPerfil());
		
		if(indicadorNivelInstalacaoEsgoto != null && !indicadorNivelInstalacaoEsgoto.equals("")){
			imovel.setIndicadorNivelInstalacaoEsgoto(new Short(indicadorNivelInstalacaoEsgoto));
		}else{
			imovel.setIndicadorNivelInstalacaoEsgoto(new Short("2"));
		}
	
		LigacaoEsgotoEsgotamento ligacaoEsgotoEsgotamento = resultadoAbaCaracteristicas.getLigacaoEsgotoEsgotamento();
		
		if (Util.verificarIdNaoVazio(poco)) {
			PocoTipo pocoTipo = new PocoTipo();
			pocoTipo.setId(new Integer(poco));
			imovel.setPocoTipo(pocoTipo);
		}
		if (Util.verificarIdNaoVazio(tipoDespejo)) {
			Despejo despejo = new Despejo();
			despejo.setId(new Integer(tipoDespejo));
			imovel.setDespejo(despejo);
		}
		if (Util.verificarIdNaoVazio(tipoHabitacao)) {
			ImovelTipoHabitacao imovelTipoHabitacao = new ImovelTipoHabitacao();
			imovelTipoHabitacao.setId(new Integer(tipoHabitacao));
			imovel.setImovelTipoHabitacao(imovelTipoHabitacao);
		}
		if (Util.verificarIdNaoVazio(tipoPropriedade)) {
			ImovelTipoPropriedade imovelTipoPropriedade = new ImovelTipoPropriedade();
			imovelTipoPropriedade.setId(new Integer(tipoPropriedade));
			imovel.setImovelTipoPropriedade(imovelTipoPropriedade);
		}
		if (Util.verificarIdNaoVazio(tipoConstrucao)) {
			ImovelTipoConstrucao imovelTipoConstrucao = new ImovelTipoConstrucao();
			imovelTipoConstrucao.setId(new Integer(tipoConstrucao));
			imovel.setImovelTipoConstrucao(imovelTipoConstrucao);
		}
		if (Util.verificarIdNaoVazio(tipoCobertura)) {
			ImovelTipoCobertura imovelTipoCobertura = new ImovelTipoCobertura();
			imovelTipoCobertura.setId(new Integer(tipoCobertura));
			imovel.setImovelTipoCobertura(imovelTipoCobertura);
		}
		
		// ABA CONCLUSAO
		ImovelAbaConclusaoHelper helperConclusao = new ImovelAbaConclusaoHelper();
		helperConclusao.setSetorComercial(resultadoAbaLocalidade.getSetorComercial());
		helperConclusao.setIdQuadraImovel(idQuadra);
		helperConclusao.setIdImovelPrincipal(idImovelPrincipal);
		helperConclusao.setNumeroIptu(numeroIptu);
		helperConclusao.setNumeroContratoCelpe(numeroContratoCelpe);
		helperConclusao.setCoordenadasUtmX(cordenadasX);
		helperConclusao.setCoordenadasUtmY(cordenadasY);
		helperConclusao.setSequencialRotaEntrega(sequencialRotaEntrega);
		helperConclusao.setNumeroQuadraEntrega(numeroQuadraEntrega);
		helperConclusao.setIdRotaEntrega(idRotaEntrega);
		helperConclusao.setIdRotaAlternativa(idRotaAlternativa);
		helperConclusao.setImoveisClientes(clientes);
		helperConclusao.setNumeroMedidorEnergia(numeroMedidorEnergia);
		helperConclusao.setInformacoesComplementares(informacoesComplementares);
		ImovelAbaConclusaoRetornoHelper resultadoAbaConclusao = fachada.validarImovelAbaConclusao(helperConclusao);
		
		if (resultadoAbaConclusao.getSequencialRotaEntrega() != null) {
			imovel.setNumeroSequencialRotaEntrega(new Integer(resultadoAbaConclusao.getSequencialRotaEntrega()));
		}
		if (resultadoAbaConclusao.getRotaEntrega() != null) {
			imovel.setRotaEntrega(resultadoAbaConclusao.getRotaEntrega());
		}
		
		if(resultadoAbaConclusao.getRotaAlternativa() != null){
			imovel.setRotaAlternativa(resultadoAbaConclusao.getRotaAlternativa());
		}
		
		if (numeroQuadraEntrega != null && !numeroQuadraEntrega.equals("")){
			
			imovel.setNumeroQuadraEntrega(new Integer(numeroQuadraEntrega));
		}
		
		if(informacoesComplementares != null && !informacoesComplementares.equals("")){
			imovel.setInformacoesComplementares(informacoesComplementares);
		}
		imovel.setNumeroPontosUtilizacao(naoVazio(pontoUtilizaco) ? new Short(pontoUtilizaco) : null);
		imovel.setNumeroMorador(naoVazio(numeroMoradores) ? new Short(numeroMoradores) : null);
		imovel.setNumeroIptu(naoVazio(numeroIptu) ? new BigDecimal(numeroIptu) : null);
		imovel.setNumeroCelpe(naoVazio(numeroContratoCelpe) ? new Long(numeroContratoCelpe) : null);
		imovel.setCoordenadaX(naoVazio(cordenadasX) ? new String(cordenadasX.replace(',','.')) : null);
		imovel.setCoordenadaY(naoVazio(cordenadasY) ? new String(cordenadasY.replace(',','.')) : null);
		if (Util.verificarIdNaoVazio(idImovelPrincipal)) {
			Imovel imovelPrincipal = new Imovel();
			imovelPrincipal.setId(new Integer(idImovelPrincipal));
			imovel.setImovelPrincipal(imovelPrincipal);
		}
		if (Util.verificarIdNaoVazio(idFuncionario)) {
			Funcionario funcionario = new Funcionario();
			funcionario.setId(new Integer(idFuncionario));
			imovel.setFuncionario(funcionario);
		}
		if (Util.verificarIdNaoVazio(numeroMedidorEnergia)) {
			
			imovel.setNumeroMedidorEnergia(numeroMedidorEnergia);
		}
		if (dataVisitaComercialInformada !=null && !dataVisitaComercialInformada.equals("")) {
			
			Date dataVisitaComercial = Util.converteStringParaDate(dataVisitaComercialInformada);
			imovel.setDataVisitaComercial(dataVisitaComercial);
		}
		
		
		// OUTROS
		imovel.setUltimaAlteracao(new Date());
		imovel.setIndicadorExclusao(ConstantesSistema.NAO);
		imovel.setIndicadorImovelCondominio(ConstantesSistema.INDICADOR_USO_DESATIVO);
		imovel.setIndicadorDebitoConta(ConstantesSistema.INDICADOR_USO_DESATIVO);
		imovel.setIndicadorEmissaoExtratoFaturamento(naoVazio(extratoResponsavel) ? new Short(extratoResponsavel) : ConstantesSistema.NAO);
		
		ConsumoTarifa consumoTarifa = new ConsumoTarifa();
		consumoTarifa.setId(ConsumoTarifa.CONSUMO_NORMAL);
		imovel.setConsumoTarifa(consumoTarifa);
		
		short quantidadeEconomias = 0;
		Iterator iteratorSubcategorias = subcategorias.iterator();
		while (iteratorSubcategorias.hasNext()) {
			ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iteratorSubcategorias.next();
			quantidadeEconomias += imovelSubcategoria.getQuantidadeEconomias();
		}
		imovel.setQuantidadeEconomias(quantidadeEconomias);
		
		ImovelContaEnvio imovelContaEnvio = new ImovelContaEnvio();
		if (Util.verificarIdNaoVazio(imovelContaEnvioForm)) {
			imovelContaEnvio = new ImovelContaEnvio();
			imovelContaEnvio.setId(new Integer(imovelContaEnvioForm));
		} else {
			imovelContaEnvio = new ImovelContaEnvio();
			imovelContaEnvio.setId(new Integer(2));
		}
		imovel.setImovelContaEnvio(imovelContaEnvio);
		
		if (imovel.getIndicadorEmissaoExtratoFaturamento() != null){
			if (imovel.getIndicadorEmissaoExtratoFaturamento().equals(new Short("0"))){
				imovel.setIndicadorEmissaoExtratoFaturamento(new Short("2"));
			}
		} else {
			imovel.setIndicadorEmissaoExtratoFaturamento(new Short("2"));
		}
		
		imovel.setIndicadorImovelAreaComum(ConstantesSistema.NAO);
		
		//CRC2258 solicitacao do analista Adriano Brito
		//data alteracao: 13/07/2009
		imovel.setIndicadorVencimentoMesSeguinte(new Short("2"));
		//fim da alteracao
		
		// CRC3184 
		// Desenvolvedor:Hugo Amorim Analista:Rosana
		// Data: 15/12/2009
		if(imovel.getImovelPerfil()!=null){
		boolean achou = false;	
			if(imovel.getImovelPerfil().getSubcategoria()!=null){

				for (Iterator iterator = subcategorias.iterator(); iterator.hasNext();) {
					ImovelSubcategoria imovelSubcategoria = (ImovelSubcategoria) iterator.next();
					Subcategoria subcategoria = imovelSubcategoria.getComp_id().getSubcategoria();
					if(subcategoria.getId().compareTo(imovel.getImovelPerfil().getSubcategoria().getId())==0){
						achou = true;
					}
				}
			
			
			if(!achou || subcategorias.size()>1){
				
				FiltroSubCategoria filtroSubCategoria = new FiltroSubCategoria();
				
				filtroSubCategoria.adicionarParametro(new ParametroSimples(FiltroSubCategoria.ID, imovel.getImovelPerfil().getSubcategoria().getId()));
				
				Collection<Subcategoria> colecaoSubs =
					 fachada.pesquisar(filtroSubCategoria, Subcategoria.class.getName());
				
				Subcategoria subcategoria = (Subcategoria) Util.retonarObjetoDeColecao(colecaoSubs);
				
				throw new ActionServletException("atencao.perfil_exige_subcategoria",subcategoria.getDescricao());
			}
			}
		}	
		
		
		
		//Fim da Alteração CRC3184


		//**********************************************************************
		// Autor: Rodrigo Cabral
		// Data: 13/09/2011
		// Inserir a Principal Categoria e SubCategoria do Imovel
		//**********************************************************************
		
		Categoria principalCategoria = fachada.obterPrincipalCategoria(subcategorias);
		
		if (principalCategoria != null){
			
			imovel.setCategoriaPrincipalId(principalCategoria.getId());
			

			ImovelSubcategoria principalSubCategoria = 
				fachada.obterPrincipalSubcategoria(principalCategoria.getId(), subcategorias);
	
			imovel.setSubCategoriaPrincipalId(principalSubCategoria.getComp_id().getSubcategoria().getId());
		}
		
		
		/**
		 * alterado por pedro alexandre dia 17/11/2006 Recupera o usuário logado
		 * para passar no metódo de inserir imóvel para verificar se o usuário
		 * tem abrangência para inserir o imóvel na localidade informada.
		 */
		Usuario usuarioLogado = (Usuario) sessao.getAttribute(Usuario.USUARIO_LOGADO);
	
		//Colocado por Raphael Rossiter em 19/08/2008 - Analista: Rosana Carvalho
		InserirImovelHelper inserirImovelHelper = new InserirImovelHelper(imovel, subcategorias, ramosAtividades, colecaoEnderecos,
		clientes, ligacaoEsgotoEsgotamento, usuarioLogado);
		
		Integer id = fachada.inserirImovelRetorno(inserirImovelHelper);
		
		//**********************************************************************
		// Autor: Ivan Sergio
		// Data: 24/07/2009
		// CRC2103 - [FS0026] - Verificar existencia de operacao inserir/manter
		// cliente pendente de atualizacao do imovel.
		//**********************************************************************
		fachada.verificarAtualizarOperacaoPendente(id, clientes, usuarioLogado.getId());
		//**********************************************************************

		sessao.removeAttribute("colecaoImovelSubCategorias");
		sessao.removeAttribute("imovelClientesNovos");

		montarPaginaSucesso(httpServletRequest, "Imóvel de matrícula " + id
				+ " incluído com sucesso.", "Inserir outro Imóvel",
				"exibirInserirImovelAction.do?menu=sim",
				"exibirAtualizarImovelAction.do?idRegistroAtualizacao=" + id
						+ "&sucesso=sucesso", "Atualizar Imóvel Inserido",
				"Informar Ocorrências / Anormalidades",
				"exibirInformarOcorrenciaCadastroAnormalidadeEloAction.do?idImovel="
						+ id);

		return retorno;
	}
	
	private boolean naoVazio(String valor) {
		if (valor == null || valor.trim().equals("")) {
			return false;
		} else {
			return true;
		}
	}

}
