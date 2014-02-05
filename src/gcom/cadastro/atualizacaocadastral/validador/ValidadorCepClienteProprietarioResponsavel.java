package gcom.cadastro.atualizacaocadastral.validador;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import gcom.cadastro.atualizacaocadastral.command.AtualizacaoCadastralImovel;

public class ValidadorCepClienteProprietarioResponsavel extends ValidadorCommand {

	public ValidadorCepClienteProprietarioResponsavel(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha) {
		super(cadastroImovel, linha);
	}
	
	@Override
	public void execute() throws Exception {
		validarCepClienteProprietario(cadastroImovel, linha);
		validarCepClienteResposanvel(cadastroImovel, linha);
	}

	private void validarCepClienteProprietario(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha) {
		String matriculaProprietario = linha.get("matriculaProprietario"); 
		String cepProprietario = linha.get("cepProprietario");
		String nomeProprietario = linha.get("nomeProprietario");
		if((!StringUtils.isEmpty(matriculaProprietario) && !StringUtils.containsOnly(matriculaProprietario,  new char[]{'0'}))
			|| (StringUtils.containsOnly(nomeProprietario,  new char[]{'0'}) && !StringUtils.isEmpty(nomeProprietario))) {
			if(StringUtils.isEmpty(cepProprietario)) {
				cadastroImovel.addMensagemErro("E obrigatório o preenchimento do CEP para o cliente Proprietário.");
				return;
			}
			
			if(!StringUtils.isNumeric(cepProprietario) || StringUtils.containsOnly(cepProprietario,  new char[]{'0'})){
				cadastroImovel.addMensagemErro("Número do CEP do cliente Proprietário é inválido");
			}
		}
	}
	
	private void validarCepClienteResposanvel(AtualizacaoCadastralImovel cadastroImovel, Map<String, String> linha) {
		String matriculaResponsavel = linha.get("matriculaResponsavel"); 
		String cepResponsavel = linha.get("cepResponsavel");
		String nomeResponsavel = linha.get("nomeResponsavel");
		if((!StringUtils.isEmpty(matriculaResponsavel) && !StringUtils.containsOnly(matriculaResponsavel,  new char[]{'0'}))
			|| (StringUtils.containsOnly(matriculaResponsavel,  new char[]{'0'}) && !StringUtils.isEmpty(nomeResponsavel))) {
			if(StringUtils.isEmpty(cepResponsavel)) {
				cadastroImovel.addMensagemErro("É obrigatório o preenchimento do CEP para o cliente Responsável.");
				return;
			}
				
			if(!StringUtils.isNumeric(cepResponsavel) || StringUtils.containsOnly(cepResponsavel,  new char[]{'0'})){
				cadastroImovel.addMensagemErro("Número do CEP do cliente Responsável é inválido");
			}
		}
	}
}
