/**
 * 공통으로 사용하는 함수
 *
 */

function strToInt(s) {
	var	v = 0;
	try {
		if (s != null && s != '') {
			v = parseInt(s.toString());
			if (isNaN(v))
				v = 0;
		}
	} catch(e) { }
	return v;
}

function toStr(n) {
	var	v = '';
	try {
		if (n != null) {
			v = n.toString();
		}
	} catch(e) { }
	return v;
}


/**
 * 에러처리 및 디버그 함수들
 * 
 */

function cmLog(s) {
	console.log(s);
}

function cmErrMsgBox(e) {
	alert('ErrMsg: ' + e);
}

/**
 * 텍스트 표현 관련 함수들
 * CR/LF --> <BR>
 */
function replaceBR(s) {
	if (s === undefined || s == null)
		return '';
	return s.replace(/\n|\r\n|\r/g, '<br/>');
}


/**
 * 아이디 체크 | 특수문자.
 */
function checkId(sValue) {
    var oIDMatch = new RegExp(/^[A-Z0-9]{8,20}$/i);
    return oIDMatch.test(sValue);
}

function checkPass(sValue) {
    var oIDMatch = new RegExp(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,10}$/);
    return oIDMatch.test(sValue);
}

function checkNumber(num) {
	var reg = new RegExp(/^[0-9]+$/);

	if (reg.test(num) == false) {
		alert("숫자만 입력할 수 있습니다.");
		return false;
	}

	return true;
}

function checkEmail(str) {
	var reg = new RegExp(/^[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+)*@[0-9a-zA-Z-]+(\.)+([0-9a-zA-Z-]+)([\.0-9a-zA-Z-])*$/);

	if (reg.test(str) == false) 
	{
		return false;
	}
	return true;
}

/**
 * 숫자를 체크하고 문자열 반환
 * @param num
 * @returns
 */
function checkInputNumber(num) {
	var reg = new RegExp(/^[0-9]+$/);
	if (reg.test(num) == false) {
		alert("숫자만 입력할 수 있습니다.");
		return '';
	}		    
	return num;
}

/**
 * 처리 내용 : HTML TAG 제거 후 텍스트만 리턴
 */
function parseHtmlToText(html) {
	if (html == null || html === undefined) {
		return '';
	}
	
	var   ohtml = $.parseHTML( html );
	return $(ohtml).text();
}
