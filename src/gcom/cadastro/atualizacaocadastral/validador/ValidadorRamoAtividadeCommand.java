package gcom.cadastro.atualizacaocadastral.validador;

import gcom.cadastro.IRepositorioCadastro;
import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;
import gcom.cadastro.atualizacaocadastral.command.DadoAtualizacaoRamoAtividade;
import gcom.cadastro.atualizacaocadastral.command.TipoEconomia;

import java.util.List;
import java.util.Map;

public class ValidadorRamoAtividadeCommand extends ValidadorCommand {

	private IRepositorioCadastro repositorioCadastro;
	
	public ValidadorRamoAtividadeCommand(
			AtualizacaoCadastralImovel cadastroImovel,
			Map<String, String> linha,
			IRepositorioCadastro repositorioCadastro) {
		super(cadastroImovel, linha);
		
		this.repositorioCadastro = repositorioCadastro;
	}

	@Override
	public void execute() throws Exception {

		List<DadoAtualizacaoRamoAtividade> dadosRamoAtividade = cadastroImovel.getDadosRamoAtividade();
		
		if (!dadosRamoAtividade.isEmpty()) {
			
			if (cadastroImovel.getDadosImovel().contemApenasResidencial()) {
				cadastroImovel.addMensagemErro("Categoria residencial não permite ramo de atividade");
				return;
			}
			
			for (DadoAtualizacaoRamoAtividade dadoRamoAtividade : dadosRamoAtividade) {
				boolean existeRamoAtividade = repositorioCadastro.existeRamoAtividade(dadoRamoAtividade.getId());
				
				if (!existeRamoAtividade) {
					cadastroImovel.addMensagemErro("Ramo de atividade com código inválido");
					return;
				}
			}
		} else if (cadastroImovel.getDadosImovel().contemTipoEconomia(TipoEconomia.COMERCIAL, TipoEconomia.PUBLICO, TipoEconomia.INDUSTRIAL)) {
			cadastroImovel.addMensagemErro("Imóvel do tipo comercial, público ou industrial deve possuir ramo de atividade");
		}
	}
}
