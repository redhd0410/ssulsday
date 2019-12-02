/**
 * net_ajax 호출 함수
 *
 * @param sUrl
 *            ajax URL
 * @param sParams
 *            파라미터 정보
 * @param _cbkResFunc
 *            통신 성공 시 호출 함수
 * @param _cbErrFunc
 *            통신 실패 시 호출 함수
 */
function net_ajax(sUrl, sParams, _cbkResFunc, _cbErrFunc) {
	console.log(sParams);
	try {
		$.ajax({
			type : "POST",
			url : sUrl,
			data : sParams,
			async : true,
			dataType : "json",
			timeout : 10*60*1000,
			success : function(data) {
				_cbkResFunc(data);
			},
	        error : function(xhr, ajaxOptions, thrownError) {
				if (!_cbErrFunc)
					alert('network failed.');
				else {
					_cbErrFunc();
				}
	        }
		});
	}
	catch(e) {
		alert('Error(net_ajax):' + e);
	}
}

function onNetCallbackDefaultError() {
	alert('오류가 발생하였습니다.\n잠시후에 이용하여 주시기 바랍니다.');
}
