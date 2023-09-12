/**
 *  confirmacao de exclusao de um contato
 * @author mochi
 * @param idev
 */

	function confirmar(idev){
	 let resposta = confirm("Confirma a exclusão deste evento?")
	 if(resposta === true){
		// alert(idev)
		window.location.href = "delete?idev=" + idev
	 }
 }