/*
* Copyright (C) 2007-2007 the GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
* Foundation, Inc., 59 Temple Place – Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN – Sistema Integrado de Gestão de Serviços de Saneamento
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
package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.atendimentopublico.ordemservico.bean.OSTipoPavimentoHelper;
import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cadastro.unidade.FiltroUnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeOrganizacional;
import gcom.cadastro.unidade.UnidadeTipo;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.bean.RelatorioBoletimCustoPavimentoBean;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;
import gcom.util.filtro.ParametroSimples;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * classe responsável por criar o relatório de Boletim de Custo de Pavimento
 * 
 * [UC1110] Gerar Boletim de Custo de Repavimentação por Tipo de Pavimento
 * 
 * @author Hugo Leonardo
 *
 * @date 03/01/2011
 */
public class RelatorioBoletimCustoPavimento extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioBoletimCustoPavimento(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_BOLETIM_CUSTO_PAVIMENTO);
	}

	@Deprecated
	public RelatorioBoletimCustoPavimento() {
		super(null, "");
	}

	/**
	 * < <Descrição do método>>
	 * 
	 */
	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarBoletimCustoPavimentoHelper relatorioHelper = 
			(FiltrarBoletimCustoPavimentoHelper) getParametro("filtrarBoletimCustoPavimentoHelper");
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		Usuario usuario = (Usuario) getParametro("usuario");
		
		// Flag's
		Boolean achouPrimeiraOcorrenciaDemandadas = false;
		Boolean indicadorPrimeiraOcorrenciaDemandadas = false;
		
		Boolean achouPrimeiraOcorrenciaDemandadas3Meses = false;
		Boolean indicadorPrimeiraOcorrenciaDemandadas3Meses = false;
		
		Boolean achouPrimeiraOcorrenciaAceitas = false;
		Boolean indicadorPrimeiraOcorrenciaAceitas = false;
		
		Boolean achouPrimeiraOcorrenciaTotal = false;
		Boolean indicadorPrimeiraOcorrenciaTotal = false;
		
		Boolean usuarioUnidadeRepavimentadora = false;
		
		Boolean flagPrimeiraVez = true;

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();

		RelatorioBoletimCustoPavimentoBean relatorioBean = null;

		String mesAno = (String)getParametro("mesAnoGeracao");
		
		String mesAnoAnteriores = (String)getParametro("mesAnoAnteriores");
		
		// Verifica se o usuário pertence a uma unidade repavimentadora.
		if ( usuario != null && usuario.getUnidadeOrganizacional() != null && 
				usuario.getUnidadeOrganizacional().getUnidadeTipo() != null && 
				usuario.getUnidadeOrganizacional().getUnidadeTipo().getId() != null &&
				(usuario.getUnidadeOrganizacional().getUnidadeTipo().getId().intValue() == 
					UnidadeTipo.UNIDADE_TIPO_REPAVIMENTADORA_ID.intValue()) ) { 
			
			usuarioUnidadeRepavimentadora = true;
		}
		
		Date dtmenos3Meses = Util.adcionarOuSubtrairMesesAData( 
				Util.gerarDataInicialApartirAnoMesRefencia( 
						new Integer (relatorioHelper.getMesAnoReferenciaGeracao())), 
						-3, new Integer(relatorioHelper.getMesAnoReferenciaGeracao()).intValue());
		
		Date dtInicio = Util.gerarDataInicialApartirAnoMesRefencia( 
				new Integer (relatorioHelper.getMesAnoReferenciaGeracao()));
		
		Date dtFim = Util.gerarDataApartirAnoMesRefencia( 
				new Integer (relatorioHelper.getMesAnoReferenciaGeracao()));
		
		Collection colecaoTotaisPorTipoPavimentoRua = fachada.pesquisarTotaisPorTipoPavimentoRua(relatorioHelper);
		
		Collection colecaoTipoPavimentoRua = fachada.pesquisarTipoPavimentoRuaBoletimCustoPavimento(relatorioHelper);
		
		// Se Analítico
		if(relatorioHelper.getIndicadorTipoBoletim().equals("1")){
		
			Collection<RelatorioBoletimCustoPavimentoHelper> colecao = fachada.pesquisarRelatorioBoletimCustoPavimento(relatorioHelper);

			// se a coleção de parâmetros da analise não for vazia
			if (colecao != null && !colecao.isEmpty()) {
	
				// laço para criar a coleção de parâmetros da analise
				for (RelatorioBoletimCustoPavimentoHelper helper : colecao) {
					
					// Indicador Quebra: 1-Demandadas, 2-Demandadas nos 3 ultimos meses, 3-Aceitas
					String indicadorQuebra = "";
					
					if (helper.getOrdemServico() != null && helper.getOrdemServico().getDataEncerramento() != null &&
							Util.compararDataTime(helper.getOrdemServico().getDataEncerramento(), dtInicio) >= 1 &&
							Util.compararDataTime(helper.getOrdemServico().getDataEncerramento(), dtFim) <= 0 && 
							helper.getOrdemServicoPavimento().getIndicadorAceite() == null){
						
						// Demandadas
						indicadorQuebra = "1";
						
						if(indicadorPrimeiraOcorrenciaDemandadas == false && achouPrimeiraOcorrenciaDemandadas == false){
							indicadorPrimeiraOcorrenciaDemandadas = true;
							achouPrimeiraOcorrenciaDemandadas = true;
							
						}else{
							indicadorPrimeiraOcorrenciaDemandadas = false;
						}
						
					}else if(helper.getOrdemServico() != null && helper.getOrdemServico().getDataEncerramento() != null &&
							Util.compararDataTime(helper.getOrdemServico().getDataEncerramento(), dtmenos3Meses) >= 1 &&
							Util.compararDataTime(helper.getOrdemServico().getDataEncerramento(), dtInicio) < 0 &&
							helper.getOrdemServicoPavimento().getMotivoRejeicao() == null && 
							(helper.getOrdemServicoPavimento().getPavimentoRuaRetorno() == null || 
									helper.getOrdemServicoPavimento().getPavimentoRuaRetorno() != null && 
									helper.getOrdemServicoPavimento().getIndicadorAceite() == null)){
						
						// Demandadas nos 3 Ultimos meses
						indicadorQuebra = "2";
						
						if(indicadorPrimeiraOcorrenciaDemandadas3Meses == false && achouPrimeiraOcorrenciaDemandadas3Meses == false){
							indicadorPrimeiraOcorrenciaDemandadas3Meses = true;
							achouPrimeiraOcorrenciaDemandadas3Meses = true;
							
						}else{
							indicadorPrimeiraOcorrenciaDemandadas3Meses = false;
						}
					}else{
						
						// Aceitas
						indicadorQuebra = "3";
					}
					
					// NúmeroOS
					String numeroOS = "";				
					if (helper.getOrdemServico() != null) {
						numeroOS = helper.getOrdemServico().getId().toString();
					}
					
					// Matricula do imóvel
					String matricula = "";				
					if (helper.getOrdemServico()!= null && helper.getOrdemServico().getImovel() != null) {
						matricula = helper.getOrdemServico().getImovel().getId().toString();
					}
					
					// Endereco
					String endereco = "";				
					if (helper.getEndereco() != null) {
						endereco = helper.getEndereco();
					}
					
					// Tipo pvto rua
					String tipoPvtoRua = "";				
					if (helper.getOrdemServicoPavimento() != null &&
						helper.getOrdemServicoPavimento().getPavimentoRua()!= null &&
						helper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada()!= null) {
						
						tipoPvtoRua = helper.getOrdemServicoPavimento().getPavimentoRua().getId() + "-" +
							helper.getOrdemServicoPavimento().getPavimentoRua().getDescricaoAbreviada();
					}
					
					// Metr. (m²) indicada.
					BigDecimal metragem = null;				
					if (helper.getOrdemServicoPavimento() != null && 
						helper.getOrdemServicoPavimento().getAreaPavimentoRua()!= null) {
						
						metragem = helper.getOrdemServicoPavimento().getAreaPavimentoRua();
					}
					
					// Tipo pvto rua retorno.
					String tipoPvtoRuaRetorno = "";
					if (helper.getOrdemServicoPavimento() != null &&
						helper.getOrdemServicoPavimento().getPavimentoRuaRetorno()!= null &&
						helper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada()!= null ) {
						
						tipoPvtoRuaRetorno = helper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getId() + "-" +
							helper.getOrdemServicoPavimento().getPavimentoRuaRetorno().getDescricaoAbreviada();
					}
					
					// Metr. (m²) indicada retorno.
					BigDecimal metragemRetorno = null;				
					if (helper.getOrdemServicoPavimento() != null && 
						helper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno()!= null) {
						
						metragemRetorno = helper.getOrdemServicoPavimento().getAreaPavimentoRuaRetorno();
					}
					
					// Data da Retorno
					String dataRetorno = "";				
					if (helper.getOrdemServicoPavimento()!= null && 
						helper.getOrdemServicoPavimento().getDataExecucao()!= null) {
						
						dataRetorno = Util.formatarData(helper.getOrdemServicoPavimento().getDataExecucao());
					}
					
					// Data da rejeição
					String dataRejeicao = "";				
					if (helper.getOrdemServicoPavimento()!= null && 
						helper.getOrdemServicoPavimento().getDataRejeicao()!= null) {
						
						dataRejeicao = Util.formatarData(helper.getOrdemServicoPavimento().getDataRejeicao());
					}
					
					// Indicador de Aceite
					String indicadorAceite = "";
					if (helper.getOrdemServicoPavimento()!= null && 
						helper.getOrdemServicoPavimento().getIndicadorAceite() != null){
						
						if(helper.getOrdemServicoPavimento().getIndicadorAceite().compareTo(new Short("1")) == 0){
							indicadorAceite = "Aceita";
						}else if(helper.getOrdemServicoPavimento().getIndicadorAceite().compareTo(new Short("2")) == 0){
							indicadorAceite = "Não Aceita";
						}
					}
					
					// Data Aceite
					String dataAceite = "";
					if (helper.getOrdemServicoPavimento()!= null && 
					    helper.getOrdemServicoPavimento().getDataAceite() != null){
						
						dataAceite = Util.formatarData(helper.getOrdemServicoPavimento().getDataAceite());
					}
					
					// Motivo Rejeição
					String motivoRejeicao = "";
					if (helper.getOrdemServicoPavimento()!= null && 
						helper.getOrdemServicoPavimento().getMotivoRejeicao() != null){
						
						motivoRejeicao = helper.getOrdemServicoPavimento().getMotivoRejeicao().getDescricao();
					}
					
					// Motivo Aceite
					String motivoAceite = "";
					if (helper.getOrdemServicoPavimento()!= null &&
							helper.getOrdemServicoPavimento().getDescricaoMotivoAceite() != null){
						
						motivoAceite = helper.getOrdemServicoPavimento().getDescricaoMotivoAceite();
					}
					
					relatorioBean = 
						new RelatorioBoletimCustoPavimentoBean(
								numeroOS,
								matricula,
								endereco,
								tipoPvtoRua,
								metragem,
								tipoPvtoRuaRetorno,
								metragemRetorno,
								dataRetorno,
								dataRejeicao,
								indicadorAceite,
								dataAceite,
								motivoRejeicao,
								motivoAceite
						);	
					
					relatorioBean.setIndicadorQuebra(indicadorQuebra);
					relatorioBean.setIndicadorPrimeiraOcorrenciaDemandadas(indicadorPrimeiraOcorrenciaDemandadas);
					relatorioBean.setIndicadorPrimeiraOcorrenciaDemandadas3Meses(indicadorPrimeiraOcorrenciaDemandadas3Meses);
					relatorioBean.setIndicadorPrimeiraOcorrenciaAceitas(indicadorPrimeiraOcorrenciaAceitas);
				
					// adiciona o bean a coleção
					if(!usuarioUnidadeRepavimentadora){
						
						relatorioBeans.add(relatorioBean);
					}else{
						
						Date dtAceite = Util.converteStringParaDate( relatorioBean.getDataAceite());
						
						if(relatorioBean.getIndicadorAceite().equals("Aceita") && !relatorioBean.getDataAceite().equals("") && 
								Util.compararDataTime(dtAceite, dtInicio) >= 0 && Util.compararDataTime(dtAceite, dtFim) <= 0){
							
							relatorioBeans.add(relatorioBean);
						}
					}
				}
			}
		}

		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());	
		
		parametros.put("mesAno", mesAno);
		
		parametros.put("mesAnoAnteriores", mesAnoAnteriores);
		
		String titulo = "";
		if(relatorioHelper.getIndicadorTipoBoletim().equals("1")){
			
			titulo = "Boletim Analítico de Custo de Repavimentação por Tipo de Pavimento";
		}else{
			
			titulo = "Boletim Sintético de Custo de Repavimentação por Tipo de Pavimento";
		}
		
		parametros.put("titulo", titulo);
		
		parametros.put("tipoBoletim", relatorioHelper.getIndicadorTipoBoletim());
		
		UnidadeOrganizacional unidadeOrganizacional = null;
		
		FiltroUnidadeOrganizacional filtroUnidadeOrganizacional = new FiltroUnidadeOrganizacional();
	    filtroUnidadeOrganizacional.adicionarParametro(	new ParametroSimples(
	    		FiltroUnidadeOrganizacional.ID, relatorioHelper.getIdUnidadeRepavimentadora()));		
	    
		Collection colecaoUnidadeOrganizacional = fachada.pesquisar(filtroUnidadeOrganizacional, 
				UnidadeOrganizacional.class.getName());
		
		if(!Util.isVazioOrNulo(colecaoUnidadeOrganizacional)){
			
			unidadeOrganizacional = (UnidadeOrganizacional) Util.retonarObjetoDeColecao(colecaoUnidadeOrganizacional);
			parametros.put("unidadeRepavimentadora", unidadeOrganizacional.getDescricao());	
		}
		
		ArrayList<RelatorioBoletimCustoPavimentoBean> colecaoBeans = 
			(ArrayList<RelatorioBoletimCustoPavimentoBean>) relatorioBeans;
		
		for (RelatorioBoletimCustoPavimentoBean relatorioBean2 : colecaoBeans) {
			
			Date dtAceite = Util.converteStringParaDate( relatorioBean2.getDataAceite());
			
			if(((relatorioBean2.getIndicadorQuebra().equals("1") || relatorioBean2.getIndicadorQuebra().equals("2")) && 
					relatorioBean2.getIndicadorAceite().equals("Aceita") && !relatorioBean2.getDataAceite().equals("") && 
					Util.compararDataTime(dtAceite, dtInicio) >= 0 && Util.compararDataTime(dtAceite, dtFim) <= 0) || 
					relatorioBean2.getIndicadorQuebra().equals("3") ){
				
				if(indicadorPrimeiraOcorrenciaAceitas == false && achouPrimeiraOcorrenciaAceitas == false){
					indicadorPrimeiraOcorrenciaAceitas = true;
					achouPrimeiraOcorrenciaAceitas = true;
					
				}else{
					indicadorPrimeiraOcorrenciaAceitas = false;
				}

				if(relatorioBean2.getIndicadorQuebra().equals("3")){
					
					relatorioBean2.setIndicadorPrimeiraOcorrenciaDemandadas(false);
					relatorioBean2.setIndicadorPrimeiraOcorrenciaDemandadas3Meses(false);
					relatorioBean2.setIndicadorPrimeiraOcorrenciaAceitas(indicadorPrimeiraOcorrenciaAceitas);
				}
			}
		}

		List relatorioBeansAux = new ArrayList();
		
		// Monta os beans referentes a TOTALIZAÇÃO
		if(!Util.isVazioOrNulo(colecaoTipoPavimentoRua)){
			
			// Obtém os Tipos de Pavimento.
			Iterator iterator = colecaoTipoPavimentoRua.iterator();	
			
			RelatorioBoletimCustoPavimentoBean relatorioBean3 = null;
			RelatorioBoletimCustoPavimentoBean relatorioBean4 = null;
			
			while (iterator.hasNext()) {
				
				flagPrimeiraVez = true;
				
				relatorioBean3 = new RelatorioBoletimCustoPavimentoBean();
				relatorioBean4 = new RelatorioBoletimCustoPavimentoBean();
				
				OSTipoPavimentoHelper helper1 = (OSTipoPavimentoHelper) iterator.next();
				
				if (colecaoTotaisPorTipoPavimentoRua != null && !colecaoTotaisPorTipoPavimentoRua.isEmpty()) {
					
					relatorioBean3.setIndicadorQuebra("4");
					relatorioBean3.setDescricaoTotal1(helper1.getDescricao());
					relatorioBean3.setDescricaoTotal2("Metragem (m2)");
					relatorioBean3.setDescricaoTotal3("Valor");
					
					if(indicadorPrimeiraOcorrenciaTotal == false && achouPrimeiraOcorrenciaTotal == false){
						indicadorPrimeiraOcorrenciaTotal = true;
						achouPrimeiraOcorrenciaTotal = true;
						
						relatorioBean3.setIndicadorPrimeiraOcorrenciaTotal(indicadorPrimeiraOcorrenciaTotal);
						
					}else{
						indicadorPrimeiraOcorrenciaTotal = false;
						relatorioBean3.setIndicadorPrimeiraOcorrenciaTotal(indicadorPrimeiraOcorrenciaTotal);
					}
					
					Iterator it1 = colecaoTotaisPorTipoPavimentoRua.iterator();
					while (it1.hasNext()) {
						
						Object[] aux1 = (Object[]) it1.next();
						
						relatorioBean4 = new RelatorioBoletimCustoPavimentoBean();
						
						Integer dados1 = (Integer) aux1[0];
						Integer dados2 = (Integer) aux1[1];
						BigDecimal dados3 = (BigDecimal) aux1[2];
						BigDecimal dados4 = (BigDecimal) aux1[3];
						
						// se for Demandadas e do tipo do pavimento
						if(dados1.toString().equals("1") && dados2.toString().equals(helper1.getId().toString())){
							
							relatorioBean4.setIndicadorQuebra("4");
							relatorioBean4.setDescricaoTotal("Estimado");
							relatorioBean4.setIndicadorPrimeiraOcorrenciaTotal(false);
							relatorioBean4.setTotalMetragem(dados3);
							relatorioBean4.setTotalValor(dados4);
						}
						// se for Demandadas nos 3 ultimos mese e do tipo do pavimento
						else if(dados1.toString().equals("2") && dados2.toString().equals(helper1.getId().toString())){
							
							relatorioBean4.setIndicadorQuebra("4");
							relatorioBean4.setDescricaoTotal("Residual " + mesAnoAnteriores);
							relatorioBean4.setIndicadorPrimeiraOcorrenciaTotal(false);
							relatorioBean4.setTotalMetragem(dados3);
							relatorioBean4.setTotalValor(dados4);
						}
						// se for Aceitas e do tipo do pavimento
						else if(dados1.toString().equals("3") && dados2.toString().equals(helper1.getId().toString())){
							
							relatorioBean4.setIndicadorQuebra("4");
							relatorioBean4.setDescricaoTotal("A Pagar");
							relatorioBean4.setIndicadorPrimeiraOcorrenciaTotal(false);
							relatorioBean4.setTotalMetragem(dados3);
							relatorioBean4.setTotalValor(dados4);
						}
						
						if(dados2.toString().equals(helper1.getId().toString()) && 
								(dados1.toString().equals("1") || dados1.toString().equals("2") || dados1.toString().equals("3"))){
							
							if(!usuarioUnidadeRepavimentadora){
								
								
								if(flagPrimeiraVez){
									
									relatorioBeansAux.add(relatorioBean3);
									flagPrimeiraVez = false;
								}
								
								relatorioBeansAux.add(relatorioBean4);
							}else{
								
								if(relatorioBean4.getDescricaoTotal().equals("A Pagar")){
									
									if(flagPrimeiraVez){
										
										relatorioBeansAux.add(relatorioBean3);
										flagPrimeiraVez = false;
									}
									
									relatorioBeansAux.add(relatorioBean4);
								}
							}
						}
					}
				}
			}
		}
		
		if(!Util.isVazioOrNulo(relatorioBeansAux)){
			
//			relatorioBean = new RelatorioBoletimCustoPavimentoBean();
//			relatorioBeansAux.add(relatorioBean);
			colecaoBeans.addAll(relatorioBeansAux);
		}
		
//		colecaoBeans.addAll(relatorioBeansAux);
		
		// classifica a lista 
		Collections.sort((List) colecaoBeans,
				new Comparator() {
					public int compare(Object a, Object b) {
						String codigo1 = ((RelatorioBoletimCustoPavimentoBean) a)
								.getIndicadorQuebra();
						String codigo2 = ((RelatorioBoletimCustoPavimentoBean) b)
							.getIndicadorQuebra();
						if (codigo1 == null || codigo1.equals("")) {
							return -1;
						} else {
							return codigo1.compareTo(codigo2);
						}
					}
				});
		
		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(colecaoBeans);
		
		if(colecaoBeans != null && colecaoBeans.size() > 0){
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_BOLETIM_CUSTO_PAVIMENTO,
					parametros, ds, tipoFormatoRelatorio);
		}else{
			
			this.nomeRelatorio = ConstantesRelatorios.RELATORIO_VAZIO;
			
			retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_VAZIO,
					parametros, ds, tipoFormatoRelatorio);
		}
			
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_BOLETIM_CUSTO_PAVIMENTO,
					idFuncionalidadeIniciada);
		} catch (ControladorException e) {
			e.printStackTrace();
			throw new TarefaException("Erro ao gravar relatório no sistema", e);
		}
		// ------------------------------------

		// retorna o relatório gerado
		return retorno;
	}

	@Override
	public int calcularTotalRegistrosRelatorio() {
		
		int retorno = 2;
   
		if (retorno == 0) {
			// Caso a pesquisa não retorne nenhum resultado comunica ao
			// usuário;
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}
		
		return retorno;		
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioBoletimCustoPavimento", this);
	}

}