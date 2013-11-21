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
package gcom.relatorio.atendimentopublico.ordemservico;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.atendimentopublico.OSExecutadasRelatorioHelper;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [UC1163]  Gerar  Relatório de OS executadas por Prestadora de Serviço
 * 
 * @author Vivianne Sousa
 * @date 15/04/2011
 */

public class RelatorioOSExecutadasPrestadoraServico extends TarefaRelatorio {

	private static final long serialVersionUID = -7034984685957706140L;
		
	public RelatorioOSExecutadasPrestadoraServico(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_OS_EXECUTADAS_PRESTADORA_SERVICO);
	}
	
	@Deprecated
	public RelatorioOSExecutadasPrestadoraServico() {
		super(null, "");
	}

	
	private Collection<RelatorioOSExecutadasPrestadoraServicoBean> inicializarBeanRelatorio(
			Collection colecaoOSExecutadasRelatorioHelper,Date dataEncerramentoInicial,Date dataEncerramentoFinal) {
		
		Collection<RelatorioOSExecutadasPrestadoraServicoBean> retorno = null;

		if(colecaoOSExecutadasRelatorioHelper != null && !colecaoOSExecutadasRelatorioHelper.isEmpty()){
			retorno = new ArrayList<RelatorioOSExecutadasPrestadoraServicoBean>();
			Iterator iterHelper = colecaoOSExecutadasRelatorioHelper.iterator();
			Integer idLocalidadeAnterior = null;
			Collection colecaoSubrelatorio = null;
			
			while (iterHelper.hasNext()) {
				
				OSExecutadasRelatorioHelper helper = (OSExecutadasRelatorioHelper) iterHelper.next();

				Integer idLocalidade = helper.getIdLocalidade();
				if(!idLocalidade.equals(idLocalidadeAnterior)){
					
					Collection colecaoServicosPorLocalidade = Fachada.getInstancia().
					recuperaTotalServicoOSExecutadas(dataEncerramentoInicial,dataEncerramentoFinal,idLocalidade);
					
					if(colecaoServicosPorLocalidade != null && !colecaoServicosPorLocalidade.isEmpty()){
						
						colecaoSubrelatorio = new ArrayList();
						Iterator iterator = colecaoServicosPorLocalidade.iterator();
						
						while (iterator.hasNext()) {
							OSExecutadasRelatorioHelper dadosSubrelatorio = (OSExecutadasRelatorioHelper) iterator.next();
							
							RelatorioOSExecutadasPrestadoraServicoDetailBean detailBean = 
								new RelatorioOSExecutadasPrestadoraServicoDetailBean(dadosSubrelatorio);
							colecaoSubrelatorio.add(detailBean);
						}
						
					}
					
				}
				
				RelatorioOSExecutadasPrestadoraServicoBean bean = 
					new RelatorioOSExecutadasPrestadoraServicoBean(
							helper.getNumeroOS(), 
							helper.getCodigoServico(), 
							helper.getDescServico(), 
							helper.getDescTipoPavimento(), 
							helper.getMaterialrede(), 
							helper.getDiametroRede(), 
							Util.formatarData(helper.getDataConclusao()), 
							helper.getCodigoExcedente(), 
//							helper.getCodigoMaterial(), 
							helper.getDescMaterial(), 
							helper.getQtdeExcedente(), 
							helper.getProfundRede(), 
							helper.getDimenBuraco(), 
							helper.getIdLocalidade().toString(), 
							helper.getNomeLocalidade(), 
							helper.getEnderecoRA(),
							helper.getNomeGerencia(),
							"idGerencia",
							helper.getNomeUnidade(),
							"idUnidadeNegocio",
							colecaoSubrelatorio);
				
				retorno.add(bean);
				
				idLocalidadeAnterior = helper.getIdLocalidade();
				
			}
		}
		return retorno;
	}

	/**
	 * Método que executa a tarefa
	 * 
	 * @return Object
	 * 
	 */
	public Object executar() throws TarefaException {
		
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		
		Fachada fachada = Fachada.getInstancia();
        
		Integer idGerencia = (Integer)getParametro("idGerencia"); 
		Integer idUnidade = (Integer)getParametro("idUnidade"); 
		Integer idLocalidade = (Integer)getParametro("idLocalidade"); 
		String[] idsRegiao = (String[])getParametro("idsRegiao"); 
		String[] idsMicroregiao = (String[])getParametro("idsMicroregiao"); 
		String[] idsMunicipio = (String[])getParametro("idsMunicipio");  
		Date dataEncerramentoInicial = (Date)getParametro("dataEncerramentoInicial"); 
		Date dataEncerramentoFinal = (Date)getParametro("dataEncerramentoFinal"); 
        
		String filtroGerencia = (String)getParametro("filtroGerencia");
		String filtroUnidade = (String)getParametro("filtroUnidade");
		String filtroLocalidade = (String)getParametro("filtroLocalidade");
		String filtroRegiao = (String)getParametro("filtroRegiao");
		String filtroMicroregiao = (String)getParametro("filtroMicroregiao");
		String filtroMunicipio = (String)getParametro("filtroMunicipio");
        
		Collection colecaoQtdeImoveisExcluidostarifaSocialHelper = fachada.recuperaOSExecutadas(
			dataEncerramentoInicial,dataEncerramentoFinal,idGerencia,idUnidade,
			idLocalidade,idsRegiao, idsMicroregiao, idsMunicipio) ;
		
		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");
		
		// valor de retorno
		byte[] retorno = null;

		// Parâmetros do relatório
		Map<String, String> parametros = new HashMap();
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		String referencia = Util.formatarData(dataEncerramentoInicial) + " a " + Util.formatarData(dataEncerramentoFinal);
		
		parametros.put("periodoencerramento", referencia);
		parametros.put("gerencia", filtroGerencia);
		parametros.put("unidade", filtroUnidade);
		parametros.put("localidade", filtroLocalidade);
		parametros.put("regiao", filtroRegiao);
		parametros.put("microregiao", filtroMicroregiao);
		parametros.put("municipio", filtroMunicipio);
		parametros.put("prestadoraServico", "PONTO FORTE");
		parametros.put("dataEmissao", Util.formatarData(new Date()));
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("imagemConta", sistemaParametro.getImagemConta());
//		parametros.put("nomeEmpresa",sistemaParametro.getNomeAbreviadoEmpresa());
//		parametros.put("cnpjEmpresa",sistemaParametro.getCnpjEmpresa().toString());

		Collection dadosRelatorio = colecaoQtdeImoveisExcluidostarifaSocialHelper;

		Collection<RelatorioOSExecutadasPrestadoraServicoBean> colecaoBean = this
				.inicializarBeanRelatorio(dadosRelatorio,dataEncerramentoInicial,dataEncerramentoFinal);

		if (colecaoBean == null || colecaoBean.isEmpty()) {
			// Não existem dados para a exibição do relatório.
			throw new RelatorioVazioException("atencao.relatorio.vazio");
		}

		RelatorioDataSource ds = new RelatorioDataSource((java.util.List) colecaoBean);

		retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_OS_EXECUTADAS_PRESTADORA_SERVICO, parametros, ds,tipoFormatoRelatorio);
		
		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.GERAR_RELATORIO_OS_EXECUTADAS_PRESTADORA_SERVICO,idFuncionalidadeIniciada);
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

		return 0;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioOSExecutadasPrestadoraServico", this);
	}
	
}
