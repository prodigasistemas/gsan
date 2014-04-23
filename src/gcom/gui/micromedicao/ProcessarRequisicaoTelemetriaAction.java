package gcom.gui.micromedicao;

import gcom.gui.GcomAction;
import gcom.micromedicao.FiltroTelemetriaRetMot;
import gcom.micromedicao.TelemetriaLog;
import gcom.micromedicao.TelemetriaRetMot;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action utilizado
 * 
 * 
 * 
 * @author Rodrigo Silveira , Thiago Nascimento e Yara Taciane
 * @since 28/07/2008
 */
public class ProcessarRequisicaoTelemetriaAction extends GcomAction {

	/**
	 * Action que captura as requisições vindas da Telametria.
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		ActionForward actionForward = null;
		
		String dadosTelemetria = request.getParameter("dadosTelemetria");
		
		TelemetriaLog telemetriaLog= 
			this.getFachada().processarLeituraViaTelemetria(dadosTelemetria);

		// 2*2008-07-25 15:25:00*2008-07-25
		// 15:00:00;123456;987654;11111;3333;1*2008-07-25
		// 14:00:00;55555;77777;4444;2222;1

		// quantidade de consumidores
		// data/hora de envio
		// data/hora de leitura
		// inscrição
		// matrícula
		// leitura
		// número do hidrometro
		// Tipo de Medição (1 - Água | 2 - Poço)

		/*Vector<DadosMovimentacao> v = new Vector<DadosMovimentacao>();

		String dadosTelemetria = request.getParameter("dadosTelemetria");

		String[] dados = dadosTelemetria.split("\\*");

		int quantidadeConsumidores = 0;

		SimpleDateFormat formatacaoData = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println("____________________________________________________________________________________");

		// QuantidadeConsumidores
		quantidadeConsumidores = Integer.parseInt(dados[0]);
		System.out
				.println("quantidadeConsumidores = " + quantidadeConsumidores);

		// Data Envio
		Date dataEnvio = null;
		try {
			dataEnvio = formatacaoData.parse(dados[1]);
			System.out.println("dataEnvio = " + Util.formatarData(dataEnvio));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("........................................................");
		// Apartir da posição 2 do vetor.
		// Processar o resto dos dados
		for (int i = 2; i < dados.length; i++) {			

			String medicao = dados[i];

			String[] medicaoDados = medicao.split(";");

			Date dataLeitura = null;
			try {
				dataLeitura = formatacaoData.parse(medicaoDados[0]);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			String matricula = medicaoDados[2];
			System.out.println("matricula = " + matricula);

			int leitura = Integer.parseInt(medicaoDados[3]);

			int tipoMedicao = Integer.parseInt(medicaoDados[5]);

			v.add(new DadosMovimentacao(new Integer(matricula), new Integer(
					leitura), new Integer(0), dataLeitura, null, new Byte(
					(byte) 0), new Integer(tipoMedicao)));

		}
		System.out.println("........................................................");
*/
		try {
			//Fachada.getInstancia().atualizarLeituraTelemetria(v);

			URL url = new URL("http", "192.168.1.10", 33128,
					"http://www.smartok.com.br/fechamento/retorno.php");

			System.out.println("conectando : " + url);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);

			OutputStreamWriter writer = new OutputStreamWriter(connection
					.getOutputStream(), "UTF-8");
			
			FiltroTelemetriaRetMot retorno = new FiltroTelemetriaRetMot();
			retorno.adicionarParametro(
				new ParametroSimples(FiltroTelemetriaRetMot.ID,
					telemetriaLog.getTelemetriaRetMot().getId()));
			
			Collection colecaoRetorno = 
				this.getFachada().pesquisar(retorno, TelemetriaRetMot.class.getName());

			TelemetriaRetMot telemetriaRetMot	= 
				(TelemetriaRetMot) Util.retonarObjetoDeColecao(colecaoRetorno);
			
			String msgRetorno = "";
			if(telemetriaRetMot != null){
				msgRetorno = telemetriaRetMot.getDescricaoRetorno();
			}
			
			writer.write("retornoDado=" + dadosTelemetria + " " + msgRetorno);

			writer.flush();

			writer.close();

			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			writer.close();
			reader.close();

			connection.disconnect();

			System.out.println("disconectado");

		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

		return actionForward;

	}

}
