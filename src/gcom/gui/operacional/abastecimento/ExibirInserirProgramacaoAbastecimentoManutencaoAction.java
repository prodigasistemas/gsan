package gcom.gui.operacional.abastecimento;

import gcom.cadastro.geografico.Bairro;
import gcom.cadastro.geografico.BairroArea;
import gcom.cadastro.geografico.FiltroBairro;
import gcom.cadastro.geografico.Municipio;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.operacional.DistritoOperacional;
import gcom.operacional.SetorAbastecimento;
import gcom.operacional.SistemaAbastecimento;
import gcom.operacional.ZonaAbastecimento;
import gcom.operacional.abastecimento.AbastecimentoProgramacao;
import gcom.operacional.abastecimento.ManutencaoProgramacao;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0414] - Informar Programação de Abastecimento e Manutenção
 * 
 * [SB0001] - Inserir Programação de Abastecimento
 * [SB0003] - Inserir Programação de Manutenção
 *
 * @author Rafael Pinto
 * 
 * @date 09/11/2006
 */

public class ExibirInserirProgramacaoAbastecimentoManutencaoAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		ActionForward retorno = actionMapping.findForward("inserirProgramacaoAbastecimentoManutencao");

		InformarProgramacaoAbastecimentoManutencaoActionForm form = 
			(InformarProgramacaoAbastecimentoManutencaoActionForm) actionForm;
		
		String tipoOperacao = httpServletRequest.getParameter("tipoOperacao");
		
		if(tipoOperacao != null && !tipoOperacao.equals("") && tipoOperacao.equals("I")){
			
			this.verificarExistenciaProgramacao(form);

			Municipio municipio = new Municipio();
			municipio.setId(new Integer(form.getMunicipio()));
			Bairro bairro = new Bairro();
			BairroArea bairroArea = new BairroArea();
			if (form.getBairro() != null && !form.getBairro().equals("")) {
				bairro = 
					this.consultarBairro(new Integer(form.getBairro()),municipio.getId());
	
				
				bairroArea.setId(new Integer(form.getAreaBairro()));
			}
			DistritoOperacional distritoOperacional = null;
			SetorAbastecimento setorAbastecimento = null;
			SistemaAbastecimento sistemaAbastecimento = null;
			ZonaAbastecimento zonaAbastecimento = null;
			
			if(form.getDistritoOperacional() != null && !form.getDistritoOperacional().equals("")){
				distritoOperacional = new DistritoOperacional();
				distritoOperacional.setId(new Integer(form.getDistritoOperacional()));
			}
			
			if(form.getSetorAbastecimento() != null && !form.getSetorAbastecimento().equals("")){
				setorAbastecimento = new SetorAbastecimento();
				setorAbastecimento.setId(new Integer(form.getSetorAbastecimento()));
				
			}
			
			if(form.getSistemaAbastecimento() != null && !form.getSistemaAbastecimento().equals("")){
				sistemaAbastecimento = new SistemaAbastecimento();
				sistemaAbastecimento.setId(new Integer(form.getSistemaAbastecimento()));
			}
			
			if(form.getZonaAbastecimento() != null && !form.getZonaAbastecimento().equals("")){
				zonaAbastecimento = new ZonaAbastecimento();
				zonaAbastecimento.setId(new Integer(form.getZonaAbastecimento()));
			}
			
			Date dataInicio = Util.converteStringParaDate(form.getDataInicio());
			Date dataFim = Util.converteStringParaDate(form.getDataFim());
			Date horaInicio = Util.converterStringParaHoraMinuto(form.getHoraInicio());
			Date horaFim = Util.converterStringParaHoraMinuto(form.getHoraFim());

			String mes = form.getMesAnoReferencia().substring(0, 2);
			String ano = form.getMesAnoReferencia().substring(3, 7);

			String anoMesReferencia = ano + mes;
			
			Date ultimaAlteracao = (Date) this.getSessao(httpServletRequest).getAttribute("ultimaAlteracao");

			if(form.getTipoProgramacao().equals("A")){
				
				AbastecimentoProgramacao abastecimentoProgramacao = new AbastecimentoProgramacao();
				
				abastecimentoProgramacao.setAnoMesReferencia(new Integer(anoMesReferencia));
				abastecimentoProgramacao.setBairro(bairro);
				abastecimentoProgramacao.setBairroArea(bairroArea);
				abastecimentoProgramacao.setDistritoOperacional(distritoOperacional);
				abastecimentoProgramacao.setMunicipio(municipio);
				abastecimentoProgramacao.setSetorAbastecimento(setorAbastecimento);
				abastecimentoProgramacao.setSistemaAbastecimento(sistemaAbastecimento);
				abastecimentoProgramacao.setZonaAbastecimento(zonaAbastecimento);
				
				abastecimentoProgramacao.setDataInicio(dataInicio);
				abastecimentoProgramacao.setDataFim(dataFim);
				abastecimentoProgramacao.setHoraInicio(horaInicio);
				abastecimentoProgramacao.setHoraFim(horaFim);
				
				abastecimentoProgramacao.setUltimaAlteracao(ultimaAlteracao);
				
				form.getAbastecimentoProgramacao().add(abastecimentoProgramacao);
				
			}else{
				
				ManutencaoProgramacao manutencaoProgramacao = new ManutencaoProgramacao();
				
				manutencaoProgramacao.setAnoMesReferencia(new Integer(anoMesReferencia));
				manutencaoProgramacao.setBairro(bairro);
				manutencaoProgramacao.setBairroArea(bairroArea);
				manutencaoProgramacao.setDistritoOperacional(distritoOperacional);
				manutencaoProgramacao.setMunicipio(municipio);
				manutencaoProgramacao.setSetorAbastecimento(setorAbastecimento);
				manutencaoProgramacao.setSistemaAbastecimento(sistemaAbastecimento);
				manutencaoProgramacao.setZonaAbastecimento(zonaAbastecimento);

				manutencaoProgramacao.setDescricao(form.getDescricaoManutencaoProgramacao());
				manutencaoProgramacao.setSituacao(new Short(form.getSituacaoManutencaoProgramacao()));

				manutencaoProgramacao.setDataInicio(dataInicio);
				manutencaoProgramacao.setDataFim(dataFim);
				manutencaoProgramacao.setHoraInicio(horaInicio);
				manutencaoProgramacao.setHoraFim(horaFim);
				
				manutencaoProgramacao.setUltimaAlteracao(ultimaAlteracao);
				
				form.getManutencaoProgramacao().add(manutencaoProgramacao);
			}
			
			httpServletRequest.setAttribute("fechaPopup", "true");			
			
		}else{
			
			String tipoProgramacao = httpServletRequest.getParameter("tipoProgramacao");
			form.setTipoProgramacao(tipoProgramacao);
			
			this.resetPopup(form);
		}
		
		return retorno;
	}
	
	/**
	 * Reseta informações vindas do popup 
	 *
	 * @author Rafael Pinto
	 * @date 14/11/2006
	 *
	 * @param InformarProgramacaoAbastecimentoManutencaoActionForm
	 */
	private void resetPopup(InformarProgramacaoAbastecimentoManutencaoActionForm form) {

		form.setDescricaoManutencaoProgramacao(null);
		form.setSituacaoManutencaoProgramacao(null);
		
		form.setDataInicio(null);
		form.setDataFim(null);
		form.setHoraInicio(null);
		form.setHoraFim(null);
	}

	/**
	 * [FS0009] - Verificar Existência de Programação de Abastecimento 
	 *
	 * @author Rafael Pinto
	 * @date 15/11/2006
	 *
	 * @param InformarProgramacaoAbastecimentoManutencaoActionForm
	 */
	private void verificarExistenciaProgramacao(InformarProgramacaoAbastecimentoManutencaoActionForm form){
		
		Collection colecao = null;
		
		if(form.getTipoProgramacao().equals("A")){
			colecao = form.getAbastecimentoProgramacao();
		}else{
			colecao = form.getManutencaoProgramacao();
		}

		if(colecao != null && !colecao.isEmpty()){
			Iterator itera = colecao.iterator();
			
			while(itera.hasNext()){
				
				Object object = (Object) itera.next();

				Date dataInicio = Util.converteStringParaDate(form.getDataInicio());
				Date dataFim = Util.converteStringParaDate(form.getDataFim());
				Date horaInicio = Util.converterStringParaHoraMinuto(form.getHoraInicio());
				Date horaFim = Util.converterStringParaHoraMinuto(form.getHoraFim());
				
				Date dataInicioProgramacao = null;
				Date dataFimProgramacao = null;
				Date horaInicioProgramacao = null;
				Date horaFimProgramacao = null;
				
				if(object instanceof AbastecimentoProgramacao){
					
					AbastecimentoProgramacao abastecimentoProgramacao = (AbastecimentoProgramacao) object;
					
					dataInicioProgramacao = abastecimentoProgramacao.getDataInicio();
					dataFimProgramacao = abastecimentoProgramacao.getDataFim();
					
					horaInicioProgramacao = abastecimentoProgramacao.getHoraInicio();
					horaFimProgramacao = abastecimentoProgramacao.getHoraFim();

				}else{
					
					ManutencaoProgramacao manutencaoProgramacao = (ManutencaoProgramacao) object;
					
					dataInicioProgramacao = manutencaoProgramacao.getDataInicio();
					dataFimProgramacao = manutencaoProgramacao.getDataFim();
					
					horaInicioProgramacao = manutencaoProgramacao.getHoraInicio();
					horaFimProgramacao = manutencaoProgramacao.getHoraFim();

					
				}
				String hora = Util.formatarHoraSemData(horaInicioProgramacao);
				horaInicioProgramacao = Util.converterStringParaHoraMinuto(hora);
				
				hora = Util.formatarHoraSemData(horaFimProgramacao);
				horaFimProgramacao = Util.converterStringParaHoraMinuto(hora);
				
				Date dataMontandaProgInicio = this.montarDataParaIntervalo(dataInicioProgramacao,horaInicioProgramacao);
				Date dataMontandaProgFim = this.montarDataParaIntervalo(dataFimProgramacao,horaFimProgramacao);
				Date dataMontandaInicio = this.montarDataParaIntervalo(dataInicio,horaInicio);
				Date dataMontandaFim = this.montarDataParaIntervalo(dataFim,horaFim);
				
				if(Util.verifcarIntervaloData(dataMontandaProgInicio,dataMontandaProgFim,dataMontandaInicio) || 
					Util.verifcarIntervaloData(dataMontandaProgInicio,dataMontandaProgFim,dataMontandaFim)){
					
					String[] msg = new String[5];
					if(form.getTipoProgramacao().equals("A")){
						msg[0] = "abastecimento";
					}else{
						msg[0] = "manutenção";
					}
					 
					msg[1] = Util.formatarData(dataInicioProgramacao);
					msg[2] = Util.formatarData(dataFimProgramacao);
					msg[3] = Util.formatarHoraSemData(horaInicioProgramacao);
					msg[4] = Util.formatarHoraSemData(horaFimProgramacao);
					
					throw new ActionServletException("atencao.ja_existe_programacao_no_periodo",
						null, msg);

				}
				
			}
		}
	}
	
	/**
	 * Pesquisar Bairro
	 * 
	 * @author Rafael Pinto
	 * @date 16/11/2006
	 */	
	private Bairro consultarBairro(Integer codigoBairro,Integer idMunicipio){
		
		FiltroBairro filtroBairro = new FiltroBairro();

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.CODIGO, codigoBairro));

		filtroBairro.adicionarParametro(
			new ParametroSimples(FiltroBairro.MUNICIPIO_ID, idMunicipio));

		Collection colecaBairro = 
			this.getFachada().pesquisar(filtroBairro,Bairro.class.getName());

		Bairro bairro = (Bairro) colecaBairro.iterator().next();

		return bairro;
	}
	
	/**
	 * Montar a data adcionando a hora e o minuto
	 * 
	 * @author Rafael Pinto
	 * @date 30/11/2006
	 */		
	private Date montarDataParaIntervalo(Date data,Date dataHora){
		
		Calendar calendarHora = new GregorianCalendar();
		calendarHora.setTime(dataHora);

		int hora = calendarHora.get(Calendar.HOUR_OF_DAY);
		int minuto = calendarHora.get(Calendar.MINUTE);
		
		Calendar calendarData = new GregorianCalendar();
		calendarData.setTime(data);
		
		calendarData.set(Calendar.HOUR_OF_DAY,hora);
		calendarData.set(Calendar.MINUTE,minuto);
		calendarData.set(Calendar.SECOND,0);
		
		return calendarData.getTime();
	}
	
}
