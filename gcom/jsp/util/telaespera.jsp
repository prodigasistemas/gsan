<div id="pleaseWaitDiv" style="display: none;">
<jsp:include page="/jsp/util/espera.jsp"/>
</div>

<script>
function submitForm(oForm)
{
  // Hide the code in first div tag
  document.getElementById('formDiv').style.display = 'none';
 
  // Display code in second div tag
  document.getElementById('pleaseWaitDiv').style.display = 'block';
 
  oForm.submit();
}

window.onunload = function() {

document.getElementById('formDiv').style.display = 'block';
document.getElementById('pleaseWaitDiv').style.display = 'none';

}

</script>