package gcom.relatorio.gerencial.faturamento;

import gcom.batch.Relatorio;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gerencial.faturamento.bean.FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper;
import gcom.gui.ActionServletException;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RelatorioDemonstrativoSinteticoLigacoes extends TarefaRelatorio {
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioDemonstrativoSinteticoLigacoes(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DEMONSTRATIVO_SINTETICO_LIGACOES);
	}

	@Deprecated
	public RelatorioDemonstrativoSinteticoLigacoes() {
		super(null, "");
	}

	public Object executar() throws TarefaException {

		// valor de retorno
		byte[] retorno = null;

		// ------------------------------------
		Integer idFuncionalidadeIniciada = this.getIdFuncionalidadeIniciada();
		// ------------------------------------

		FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper filtro = 
			(FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper) getParametro("filtrarRelatorioDemonstrativoSinteticoLigacoesHelper");

		int tipoFormatoRelatorio = (Integer) getParametro("tipoFormatoRelatorio");

		// coleção de beans do relatório
		List relatorioBeans = new ArrayList();

		Fachada fachada = Fachada.getInstancia();


		Collection<Object> colecaoResumoLigacaoEconomia = 
			fachada.pesquisarResumoLigacaoEconomiaRelatorioDemonstrativo(filtro);
		
		Collection<Object> colecaoResumoConsumoAgua =
			fachada.pesquisarResumoConsumoAguaRelatorioDemonstrativo(filtro);
		
		Collection<Object> colecaoResumoLeituraAnormalidade =
			fachada.pesquisaResumoLeituraAnormalidadeRelatorioDemonstrativo(filtro);
		
		Collection<Object> colecaoInstalacaoHidrometro =
			fachada.pesquisaResumoInstalacaoHidrometroRelatorioDemonstrativo(filtro);
		
		
		RelatorioDemonstrativoSinteticoLigacoesBean relatorioBean = null;
		
		//cria variaveis para gerar o total por relatorio.
					
		Long sumAgua_movLig_existentes = new Long(0);
		Long sumAgua_movLig_funcionando = new Long(0);
		Long sumAgua_movLig_cortadas = new Long(0);
		Long sumAgua_movLig_suprimidas = new Long(0);
		Long sumAgua_movLig_corteMes = new Long(0);
		Long sumAgua_movLig_ligacaoMes = new Long(0);
		Long sumAgua_movLig_religacaoMes = new Long(0);
		Long sumAgua_eco_existentes = new Long(0);				
		Long sumAgua_eco_funcionando = new Long(0);
		Long sumAgua_eco_cortadas = new Long(0);
		Long sumAgua_eco_suprimidas = new Long(0);
		Long sumAgua_eco_ecoExist_residencial = new Long(0);
		Long sumAgua_eco_ecoExist_comercial = new Long(0);
		Long sumAgua_eco_ecoExist_publica = new Long(0);
		Long sumAgua_eco_ecoExist_industrial = new Long(0);
		Long sumAgua_eco_ecoFunc_residencial = new Long(0);
		Long sumAgua_eco_ecoFunc_comercial = new Long(0);
		Long sumAgua_eco_ecoFunc_publica = new Long(0);
		Long sumAgua_eco_ecoFunc_industrial = new Long(0);
		Long sumAgua_hid_funcionando = new Long(0);
		Long sumAgua_hid_cortados = new Long(0);
		Long sumAgua_hid_lidos = new Long(0);
		Long sumAgua_hid_instalados = new Long(0);
		Long sumAgua_hid_parados = new Long(0);
		Long sumAgua_cons_estNHidrometrado = new Long(0);
		Long sumAgua_cons_estHidrometrado = new Long(0);
		Long sumAgua_cons_realHidrometrado = new Long(0);
		Long sumAgua_cons_faturado = new Long(0);
		Long sumEsgoto_movLig_existente = new Long(0);
		Long sumEsgoto_movLig_funcionando = new Long(0);
		Long sumEsgoto_movLig_cortadas = new Long(0);
		Long sumEsgoto_movLig_suprimidas = new Long(0);
		Long sumEsgoto_eco_existentes = new Long(0);
		Long sumEsgoto_eco_funcionando = new Long(0);
		Long sumEsgoto_eco_cortadas = new Long(0);
		Long sumEsgoto_eco_supridas = new Long(0);
		Long sumEsgoto_eco_ecoExist_residencial = new Long(0);
		Long sumEsgoto_eco_ecoExist_comercial = new Long(0);
		Long sumEsgoto_eco_ecoExist_publica = new Long(0);
		Long sumEsgoto_eco_ecoExist_industrial = new Long(0);
		Long sumEsgoto_eco_ecoFunc_residencial = new Long(0);
		Long sumEsgoto_eco_ecoFunc_comercial = new Long(0);
		Long sumEsgoto_eco_ecoFunc_publica = new Long(0);
		Long sumEsgoto_eco_ecoFunc_industrial = new Long(0);
		

		if (colecaoResumoLigacaoEconomia != null && !colecaoResumoLigacaoEconomia.isEmpty()) {
			

		    Iterator iteratorRELE = colecaoResumoLigacaoEconomia.iterator();
		    Iterator iteratorRECA = colecaoResumoConsumoAgua.iterator();
		    Iterator iteratorRELT = colecaoResumoLeituraAnormalidade.iterator();
		    Iterator iteratorREIH = colecaoInstalacaoHidrometro.iterator();
		    

			while (iteratorRELE.hasNext() || iteratorRECA.hasNext() || 
				   iteratorRELT.hasNext() || iteratorREIH.hasNext() ) {
					
				relatorioBean = new RelatorioDemonstrativoSinteticoLigacoesBean();								
				Object[] objRELE = (Object[]) iteratorRELE.next();
				
				
				relatorioBean.setAgua_movLig_existentes((Long)objRELE[0]);
				relatorioBean.setAgua_movLig_funcionando((Long)objRELE[1]);
				relatorioBean.setAgua_movLig_cortadas((Long)objRELE[2]);
				relatorioBean.setAgua_movLig_suprimidas((Long)objRELE[3]);				
				relatorioBean.setAgua_movLig_corteMes((Long)objRELE[4]);
				relatorioBean.setAgua_movLig_ligacaoMes((Long)objRELE[5]);				
				relatorioBean.setAgua_movLig_religacaoMes((Long)objRELE[6]);
				relatorioBean.setAgua_eco_existentes((Long)objRELE[7]);				
				relatorioBean.setAgua_eco_funcionando((Long)objRELE[8]);
				relatorioBean.setAgua_eco_cortadas((Long)objRELE[9]);
				relatorioBean.setAgua_eco_suprimidas((Long)objRELE[10]);
				relatorioBean.setAgua_eco_ecoExist_residencial((Long)objRELE[11]);
				relatorioBean.setAgua_eco_ecoExist_comercial((Long)objRELE[12]);
				relatorioBean.setAgua_eco_ecoExist_publica((Long)objRELE[13]);
				relatorioBean.setAgua_eco_ecoExist_industrial((Long)objRELE[14]);
				relatorioBean.setAgua_eco_ecoFunc_residencial((Long)objRELE[15]);
				relatorioBean.setAgua_eco_ecoFunc_comercial((Long)objRELE[16]);
				relatorioBean.setAgua_eco_ecoFunc_publica((Long)objRELE[17]);
				relatorioBean.setAgua_eco_ecoFunc_industrial((Long)objRELE[18]);
				relatorioBean.setAgua_hid_funcionando((Long)objRELE[19]);
				relatorioBean.setAgua_hid_cortados((Long)objRELE[20]);
			
				relatorioBean.setEsgoto_movLig_existente((Long)objRELE[21]);
				relatorioBean.setEsgoto_movLig_funcionando((Long)objRELE[22]);
				relatorioBean.setEsgoto_movLig_cortadas((Long)objRELE[23]);
				relatorioBean.setEsgoto_movLig_suprimidas((Long)objRELE[24]);
				relatorioBean.setEsgoto_eco_existentes((Long)objRELE[25]);
				relatorioBean.setEsgoto_eco_funcionando((Long)objRELE[26]);
				relatorioBean.setEsgoto_eco_cortadas((Long)objRELE[27]);
				relatorioBean.setEsgoto_eco_supridas((Long)objRELE[28]);
				relatorioBean.setEsgoto_eco_ecoExist_residencial((Long)objRELE[29]);
				relatorioBean.setEsgoto_eco_ecoExist_comercial((Long)objRELE[30]);
				relatorioBean.setEsgoto_eco_ecoExist_publica((Long)objRELE[31]);
				relatorioBean.setEsgoto_eco_ecoExist_industrial((Long)objRELE[32]);
				relatorioBean.setEsgoto_eco_ecoFunc_residencial((Long)objRELE[33]);
				relatorioBean.setEsgoto_eco_ecoFunc_comercial((Long)objRELE[34]);
				relatorioBean.setEsgoto_eco_ecoFunc_publica((Long)objRELE[35]);
				relatorioBean.setEsgoto_eco_ecoFunc_industrial((Long)objRELE[36]);
				
				relatorioBean.setGerenciaRegionalID((String)objRELE[37]);
				relatorioBean.setGerenciaRegional((String)objRELE[38]);
				relatorioBean.setUnidadeNegocioID((String)objRELE[39]);
				relatorioBean.setUnidadeNegocio((String)objRELE[40]);
				relatorioBean.setLocalidadeID((String)objRELE[41]);
				relatorioBean.setLocalidade((String)objRELE[42]);
				relatorioBean.setMunicipioID((String)objRELE[43]);
				relatorioBean.setMunicipio((String)objRELE[44]);
				

				Object[] objRECA = null;				
				if(colecaoResumoConsumoAgua != null && colecaoResumoConsumoAgua.size() != 0 && iteratorRECA.hasNext()){
					objRECA = (Object[]) iteratorRECA.next();					
					relatorioBean.setAgua_cons_faturado((Long)objRECA[0]);
				    relatorioBean.setAgua_cons_estNHidrometrado((Long)objRECA[1]);
					relatorioBean.setAgua_cons_estHidrometrado((Long)objRECA[2]);
					relatorioBean.setAgua_cons_realHidrometrado((Long)objRECA[3]);
				}else{
					relatorioBean.setAgua_cons_faturado(new Long (0));
				    relatorioBean.setAgua_cons_estNHidrometrado(new Long (0));
					relatorioBean.setAgua_cons_estHidrometrado(new Long (0));
					relatorioBean.setAgua_cons_realHidrometrado(new Long (0));
				}				
				
				
				Object[] objRELT = null;
				if(colecaoResumoLeituraAnormalidade != null && colecaoResumoLeituraAnormalidade.size() != 0 && iteratorRELT.hasNext()){
					objRELT = (Object[]) iteratorRELT.next();
					relatorioBean.setAgua_hid_lidos((Long)objRELT[0]);
				    relatorioBean.setAgua_hid_parados((Long)objRELT[1]);
				}else{
					relatorioBean.setAgua_hid_lidos(new Long(0));
				    relatorioBean.setAgua_hid_parados(new Long(0));
				}
				
				
				Object[] objREIH = null;
				if(colecaoInstalacaoHidrometro != null && colecaoInstalacaoHidrometro.size() != 0 && iteratorREIH.hasNext()){
					objREIH = (Object[]) iteratorREIH.next();					
					relatorioBean.setAgua_hid_instalados((Long)objREIH[0]);
				}else{
					relatorioBean.setAgua_hid_instalados(new Long(0));
				}

				relatorioBeans.add(relatorioBean);
				
				//se opcao de totalizacao for estado por ...,  soma os valores
				if (filtro.getOpcaoTotalizacao() == 2 || filtro.getOpcaoTotalizacao() == 3 ||
						filtro.getOpcaoTotalizacao() == 4 || filtro.getOpcaoTotalizacao() == 5){
					
					sumAgua_movLig_existentes += (Long)objRELE[0];
					sumAgua_movLig_funcionando += (Long)objRELE[1];
					sumAgua_movLig_cortadas += (Long)objRELE[2];
					sumAgua_movLig_suprimidas += (Long)objRELE[3];
					sumAgua_movLig_corteMes += (Long)objRELE[4];
					sumAgua_movLig_ligacaoMes += (Long)objRELE[5];
					sumAgua_movLig_religacaoMes += (Long)objRELE[6];					
					sumAgua_eco_existentes += (Long)objRELE[7];				
					sumAgua_eco_funcionando += (Long)objRELE[8];
					sumAgua_eco_cortadas += (Long)objRELE[9];
					sumAgua_eco_suprimidas += (Long)objRELE[10];
					sumAgua_eco_ecoExist_residencial += (Long)objRELE[11];
					sumAgua_eco_ecoExist_comercial += (Long)objRELE[12];
					sumAgua_eco_ecoExist_publica += (Long)objRELE[13];
					sumAgua_eco_ecoExist_industrial += (Long)objRELE[14];
					sumAgua_eco_ecoFunc_residencial += (Long)objRELE[15];
					sumAgua_eco_ecoFunc_comercial += (Long)objRELE[16];
					sumAgua_eco_ecoFunc_publica += (Long)objRELE[17];
					sumAgua_eco_ecoFunc_industrial += (Long)objRELE[18];
					sumAgua_hid_funcionando += (Long)objRELE[19];
					sumAgua_hid_cortados += (Long)objRELE[20];
					
					sumEsgoto_movLig_existente += (Long)objRELE[21];
					sumEsgoto_movLig_funcionando += (Long)objRELE[22];
					sumEsgoto_movLig_cortadas += (Long)objRELE[23];
					sumEsgoto_movLig_suprimidas += (Long)objRELE[24];
					sumEsgoto_eco_existentes += (Long)objRELE[25];
					sumEsgoto_eco_funcionando += (Long)objRELE[26];
					sumEsgoto_eco_cortadas += (Long)objRELE[27];
					sumEsgoto_eco_supridas += (Long)objRELE[28];
					sumEsgoto_eco_ecoExist_residencial += (Long)objRELE[29];
					sumEsgoto_eco_ecoExist_comercial += (Long)objRELE[30];
					sumEsgoto_eco_ecoExist_publica += (Long)objRELE[31];
					sumEsgoto_eco_ecoExist_industrial += (Long)objRELE[32];
					sumEsgoto_eco_ecoFunc_residencial += (Long)objRELE[33];
					sumEsgoto_eco_ecoFunc_comercial += (Long)objRELE[34];
					sumEsgoto_eco_ecoFunc_publica += (Long)objRELE[35];
					sumEsgoto_eco_ecoFunc_industrial += (Long)objRELE[36];
					
					
					if(objRECA != null){
						sumAgua_cons_faturado += (Long)objRECA[0];
					    sumAgua_cons_estNHidrometrado += (Long)objRECA[1];
					    sumAgua_cons_estHidrometrado += (Long)objRECA[2];
					    sumAgua_cons_realHidrometrado += (Long)objRECA[3];
					}
					
					if(objRELT != null){
						sumAgua_hid_lidos += (Long)objRELT[0];
					    sumAgua_hid_parados += (Long)objRELT[1];
					}
					
					if(objREIH != null){
						sumAgua_hid_instalados += (Long)objREIH[0];
					}
					
				}				
			}
			
			//caso a opcao de totalizacao for estado por ..., adiciona o total do estado
			if (filtro.getOpcaoTotalizacao() == 2 || filtro.getOpcaoTotalizacao() == 3 ||
					filtro.getOpcaoTotalizacao() == 4 || filtro.getOpcaoTotalizacao() == 5){
				
				relatorioBean = new RelatorioDemonstrativoSinteticoLigacoesBean();
				
				relatorioBean.setAgua_movLig_existentes(Long.valueOf(sumAgua_movLig_existentes));
				relatorioBean.setAgua_movLig_funcionando(Long.valueOf(sumAgua_movLig_funcionando));
				relatorioBean.setAgua_movLig_cortadas(Long.valueOf(sumAgua_movLig_cortadas));
				relatorioBean.setAgua_movLig_suprimidas(Long.valueOf(sumAgua_movLig_suprimidas));
				relatorioBean.setAgua_movLig_corteMes(Long.valueOf(sumAgua_movLig_corteMes));							
				relatorioBean.setAgua_movLig_ligacaoMes(Long.valueOf(sumAgua_movLig_ligacaoMes));
				relatorioBean.setAgua_movLig_religacaoMes(Long.valueOf(sumAgua_movLig_religacaoMes));
				relatorioBean.setAgua_eco_existentes(Long.valueOf(sumAgua_eco_existentes));				
				relatorioBean.setAgua_eco_funcionando(Long.valueOf(sumAgua_eco_funcionando));
				relatorioBean.setAgua_eco_cortadas(Long.valueOf(sumAgua_eco_cortadas));
				relatorioBean.setAgua_eco_suprimidas(Long.valueOf(sumAgua_eco_suprimidas));
				relatorioBean.setAgua_eco_ecoExist_residencial(Long.valueOf(sumAgua_eco_ecoExist_residencial));
				relatorioBean.setAgua_eco_ecoExist_comercial(Long.valueOf(sumAgua_eco_ecoExist_comercial));
				relatorioBean.setAgua_eco_ecoExist_publica(Long.valueOf(sumAgua_eco_ecoExist_publica));
				relatorioBean.setAgua_eco_ecoExist_industrial(Long.valueOf(sumAgua_eco_ecoExist_industrial));
				relatorioBean.setAgua_eco_ecoFunc_residencial(Long.valueOf(sumAgua_eco_ecoFunc_residencial));
				relatorioBean.setAgua_eco_ecoFunc_comercial(Long.valueOf(sumAgua_eco_ecoFunc_comercial));
				relatorioBean.setAgua_eco_ecoFunc_publica(Long.valueOf(sumAgua_eco_ecoFunc_publica));
				relatorioBean.setAgua_eco_ecoFunc_industrial(Long.valueOf(sumAgua_eco_ecoFunc_industrial));
				relatorioBean.setAgua_hid_funcionando(Long.valueOf(sumAgua_hid_funcionando));
				relatorioBean.setAgua_hid_cortados(Long.valueOf(sumAgua_hid_cortados));

				relatorioBean.setAgua_cons_faturado(Long.valueOf(sumAgua_cons_faturado));
				relatorioBean.setAgua_cons_estNHidrometrado(Long.valueOf(sumAgua_cons_estNHidrometrado));
				relatorioBean.setAgua_cons_estHidrometrado(Long.valueOf(sumAgua_cons_estHidrometrado));
				relatorioBean.setAgua_cons_realHidrometrado(Long.valueOf(sumAgua_cons_realHidrometrado));
				
				relatorioBean.setAgua_hid_lidos(Long.valueOf(sumAgua_hid_lidos));
				relatorioBean.setAgua_hid_parados(Long.valueOf(sumAgua_hid_parados));
				
				relatorioBean.setAgua_hid_instalados(Long.valueOf(sumAgua_hid_instalados));
				
				relatorioBean.setEsgoto_movLig_existente(Long.valueOf(sumEsgoto_movLig_existente));
				relatorioBean.setEsgoto_movLig_funcionando(Long.valueOf(sumEsgoto_movLig_funcionando));
				relatorioBean.setEsgoto_movLig_cortadas(Long.valueOf(sumEsgoto_movLig_cortadas));
				relatorioBean.setEsgoto_movLig_suprimidas(Long.valueOf(sumEsgoto_movLig_suprimidas));
				relatorioBean.setEsgoto_eco_existentes(Long.valueOf(sumEsgoto_eco_existentes));
				relatorioBean.setEsgoto_eco_funcionando(Long.valueOf(sumEsgoto_eco_funcionando));
				relatorioBean.setEsgoto_eco_cortadas(Long.valueOf(sumEsgoto_eco_cortadas));
				relatorioBean.setEsgoto_eco_supridas(Long.valueOf(sumEsgoto_eco_supridas));
				relatorioBean.setEsgoto_eco_ecoExist_residencial(Long.valueOf(sumEsgoto_eco_ecoExist_residencial));
				relatorioBean.setEsgoto_eco_ecoExist_comercial(Long.valueOf(sumEsgoto_eco_ecoExist_comercial));
				relatorioBean.setEsgoto_eco_ecoExist_publica(Long.valueOf(sumEsgoto_eco_ecoExist_publica));
				relatorioBean.setEsgoto_eco_ecoExist_industrial(Long.valueOf(sumEsgoto_eco_ecoExist_industrial));
				relatorioBean.setEsgoto_eco_ecoFunc_residencial(Long.valueOf(sumEsgoto_eco_ecoFunc_residencial));
				relatorioBean.setEsgoto_eco_ecoFunc_comercial(Long.valueOf(sumEsgoto_eco_ecoFunc_comercial));
				relatorioBean.setEsgoto_eco_ecoFunc_publica(Long.valueOf(sumEsgoto_eco_ecoFunc_publica));
				relatorioBean.setEsgoto_eco_ecoFunc_industrial(Long.valueOf(sumEsgoto_eco_ecoFunc_industrial));
				
				//colocar zero para informar que esse bean é o total do estado
				relatorioBean.setGerenciaRegionalID((String)"0");
				relatorioBean.setUnidadeNegocioID((String)"0");
				relatorioBean.setLocalidadeID((String)"0");
				relatorioBean.setMunicipioID((String)"0");
				
				relatorioBeans.add(relatorioBean);
			}
			
		}
		// __________________________________________________________________

		// Parâmetros do relatório
		Map parametros = new HashMap();
		
		// adiciona os parâmetros do relatório
		// adiciona o laudo da análise
		
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		parametros.put("imagem", sistemaParametro.getImagemRelatorio());
		parametros.put("anoMesReferencia", Util.formatarAnoMesParaMesAno(filtro.getAnoMesReferencia()));
		parametros.put("codOpcaoTotalizacao", filtro.getOpcaoTotalizacao());
		parametros.put("tipoFormatoRelatorio", "R1003");

		// cria uma instância do dataSource do relatório
		RelatorioDataSource ds = new RelatorioDataSource(relatorioBeans);

		retorno = gerarRelatorio(ConstantesRelatorios.RELATORIO_DEMONSTRATIVO_SINTETICO_LIGACOES,
				parametros, ds, tipoFormatoRelatorio);

		// ------------------------------------
		// Grava o relatório no sistema
		try {
			persistirRelatorioConcluido(retorno, Relatorio.RELATORIO_DEMONSTRATIVO_SINTETICO_LIGACOES,
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
		
		FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper filtro = 
			(FiltrarRelatorioDemonstrativoSinteticoLigacoesHelper) getParametro("filtrarRelatorioDemonstrativoSinteticoLigacoesHelper");

		Fachada fachada = Fachada.getInstancia();

		int retorno = 0;		
		
		retorno = (Integer) fachada.pesquisarResumoLigacaoEconomiaRelatorioDemonstrativo(filtro).size();
		
	    if(retorno == 0){
			throw new ActionServletException("atencao.pesquisa.nenhumresultado", null, "");
		}
		    
		return retorno;
	}

	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDemonstrativoSinteticoLigacoes", this);

	}

}