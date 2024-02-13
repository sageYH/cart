//getGridObj 삭제
//getInstanceGrid 삭제
//getGridId 삭제
//setData -> resetData
//setDataGrid 변경
window.addEventListener('DOMContentLoaded', function(){
	document.querySelectorAll('input')
	.forEach(item => {item.setAttribute('autocomplete','off')});
	//gridListTable
	let selObjs = document.querySelectorAll('.gridListTable');
	for(let i=0;i<selObjs.length;i++){
		if (isEmpty(selObjs[i].id)) continue;
		setTblLoadEndEvt(selObjs[i].id);
	}
})

function keyEnterClick(arr,act){
	if (isEmpty(arr) || isEmpty(arr.length)) return false;
	if (isEmpty(act)) return false;
	for(var i=0;i<arr.length;i++){
		var va1 = arr[i];
		document.querySelector("input[id='"+va1+"']").addEventListener(
			"keypress",function(e){
		    	if (e.keyCode==13){
					getEleByObj(act).click();
		    	}
			}
		)
	}
}

function msgSend(func,ds){
	var form 	= document.getElementById("MSGSendForm");
	var iframe 	= document.getElementById("MSGSendIframe");
	if (isEmpty(form)){
		form = document.createElement("form");
		form.setAttribute("id", "MSGSendForm");
		form.setAttribute("name", "MSGSendForm");
		form.setAttribute("style", "display:none;");
		form.setAttribute("method", "POST");
		document.body.appendChild(form);
		while(true) {
			if (document.getElementById("MSGSendForm") ) break;
			setTimeout(function(){}, 20);
		}
	}
	if (isEmpty(ds)==false){
		form.setAttribute("mapDataUp", JSON.stringify(ds.getMap()));
	}
	if (isEmpty(iframe)){
		iframe = document.createElement("iframe");
		iframe.setAttribute("id", "MSGSendIframe");
		iframe.setAttribute("name", "MSGSendIframe");
		iframe.setAttribute("style", "display:none;");
		iframe.setAttribute("method", "POST");
		document.body.appendChild(iframe);
		while(true) {
			if ( document.getElementById("MSGSendIframe")) break;
			setTimeout(function(){}, 20);
		}
	}
	form.target ="MSGSendIframe";
	form.action = "/fo/common/msg.do";
	form.submit();

	window.reqFunc = func;
	document.querySelector("#MSGSendIframe").addEventListener("load",function(e){
		iLoadResult(this);
	});
	document.querySelector("#MSGSendIframe").submit();
}

var iLoadResult = function(iObj){
	var doc = getIframeDoc(iObj);
	if (isEmpty(doc)){
		if (isEmpty(window.reqFunc)==false){
			var ds1 = new TDataSet();
			ds1.setValue("errorCode",0,"JErr01");
			ds1.setValue("errorMsg",0,"작업중 오류발생.");
			window.reqFunc(ds1);
		}
		return true;
	}
	var docRoot = doc.body ? doc.body : doc.documentElement;
	var data = docRoot.innerHTML;
	//data is returned from server.
	if (data!= null && data!=""){
		var ds = new TDataSet();
		try{
			ds.setData(eval('(' + data + ')'));
		}
		catch(Exception){
			ds.setValue("errorCode",0,"JErr01");
			ds.setValue("errorMsg",0,"작업중 오류가 발생했습니다.");
		}
		if (isEmpty(window.reqFunc)==false){
			window.reqFunc(ds);
			return true;
		}
	}
	if (isEmpty(window.reqFunc)==false){
		window.reqFunc(new TDataSet());
		return true;
	}
};

// get today
function getTodayYmd() {
	var d1 = new Date();
	var v1 = d1.getFullYear();
	var sv1 = d1.getMonth() + 1;
	if (Number(sv1) < 10)
		sv1 = '0' + sv1;
	v1 += ('' + sv1);
	sv1 = d1.getDate();
	if (Number(sv1) < 10)
		sv1 = '0' + sv1;
	v1 += ('' + sv1);
	return v1;
}

//get today month
function getTodayYm() {
	var d1 = new Date();
	var v1 = d1.getFullYear();
	var sv1 = d1.getMonth() + 1;
	
	if (Number(sv1) < 10)
		sv1 = '0' + sv1;
	v1 += ('' + sv1);
	return v1;
}

/**
 *  ex)   (12시1분1초) ==> 120101
 * @returns {String}
 */
function getHms() {
	var d1 = new Date();
	var sv1 = d1.getHours();
	var v1 = '';
	if (Number(sv1) < 10)
		sv1 = '0'+sv1;
	v1  = (''+sv1);
	sv1 = d1.getMinutes();
	if(Number(sv1) < 10)
		 sv1 = '0'+sv1;
	v1 += (''+sv1);
	sv1 = d1.getSeconds();
	if(Number(sv1) < 10) 
		sv1 = '0'+sv1;
	v1 += (''+sv1);
	return v1;
}
function getTodayYmdHms(){
	return getTodayYmd()+getHms();
}
/**
 * 특정 날자로 부터 지정한 날 수만큼 이동한 날자를 반환
 * @param baseDate 지정한 날자 ('YYYYMMDD')
 * @param days 이동할 날 수(days>0: 지정날자 이후, days<0: 지정날자 이전
 * @returns {String}('YYYYMMDD')
 */
function addDateYmd(baseDate, days) {
	var d1 = new Date();
	var year = "";
	var month = "";
	var day = "";
	if (baseDate == ""){
		return baseDate;
	}
	if (baseDate.length==6){
		year = baseDate.substr(0,4);
		month = baseDate.substr(4,2);
	}
	if (baseDate.length==8){
		year = baseDate.substr(0,4);
		month = baseDate.substr(4,2);
		day = baseDate.substr(6,2);
	}
	
	d1.setFullYear(Number(year), Number(month)-1, Number(day));
	var date = d1.getDate();
	d1.setDate(date + days);
	var v1 = d1.getFullYear();
	var sv1 = d1.getMonth() + 1;
	if (Number(sv1) < 10)
		sv1 = '0' + sv1;
	v1 += ('' + sv1);
	sv1 = d1.getDate();
	if (Number(sv1) < 10)
		sv1 = '0' + sv1;
	v1 += ('' + sv1);
	return v1;
}
function getDateDiff(v1,v2){
	v1 = clearFormatDay(v1);
	v2 = clearFormatDay(v2);
	//v1-v2(빼는일자포함)
	if (isDate(v1)==false) return 0;
	if (isDate(v2)==false) return 0;
	var d1 = new Date(v1.substr(0,4),Number(v1.substr(4,2))-1,v1.substr(6));
	var d2 = new Date(v2.substr(0,4),Number(v2.substr(4,2))-1,v2.substr(6));
	return (d1-d2)/(1000*60*60*24)+1;
}

/**
 * 문자열로된 날짜를 포맷에 맞게 변경
 * @param date 날짜(YYYYMMDDHH24MISS)
 * @param f   변경할날짜포맷형태(ex : YYYY-MM-DD or YYYY-MM-DD HH24:MI:SS)
 * @returns {String}
 */
function getDateFormat(date, f) {
	
	if(isEmpty(date)) return;
	var resultDate = "";
	if ( !isEmpty(f) ) {
		var upperFormat = f.toUpperCase();
		resultDate = upperFormat.replace(/(YYYY|YY|MM|DD|HH24|MI|SS)/gi, function($1) {
	        switch ($1) {
	            case "YYYY": return date.substr(0,4);
	            case "YY": return date.substr(2,2);
	            case "MM": return date.substr(4,2);
	            case "DD": return date.substr(6,2);
	            case "HH24": return date.substr(8,2);
	            case "MI": return date.substr(10,2);
	            case "SS": return date.substr(12,2);
	            default: return $1;
	        }
	    });	
	}
	return resultDate;
}

// 시간 shift
function dateAdd(date, interval, units) {
	var ret = new Date(date); //don't change original date
	switch(interval.toLowerCase()) {
	case 'year'   :  ret.setFullYear(ret.getFullYear() + units);  break;
	case 'quarter':  ret.setMonth(ret.getMonth() + 3*units);  break;
	case 'month'  :  ret.setMonth(ret.getMonth() + units);  break;
	case 'week'   :  ret.setDate(ret.getDate() + 7*units);  break;
	case 'day'    :  ret.setDate(ret.getDate() + units);  break;
	case 'hour'   :  ret.setTime(ret.getTime() + units*3600000);  break;
	case 'minute' :  ret.setTime(ret.getTime() + units*60000);  break;
	case 'second' :  ret.setTime(ret.getTime() + units*1000);  break;
	default       :  ret = undefined;  break;
	}
	return ret;
}
function getDateToYmd(d1){
	var v1 = d1.getFullYear();
	var sv1 = d1.getMonth() + 1;
	if (Number(sv1) < 10)
		sv1 = '0' + sv1;
	v1 += ('' + sv1);
	sv1 = d1.getDate();
	if (Number(sv1) < 10)
		sv1 = '0' + sv1;
	v1 += ('' + sv1);
	return v1;
}

/**
 * 지역번호 유효성 검사 (2014-09-29 yim 우리은행 사이트 참조했음)
 * 
 * @param no :
 *            전화번호(핸드폰) 앞자리
 * @param type :
 *            T-일반전화, H-핸드폰, A-모두
 * @returns {Boolean}
 */
function validatePhoneNo(no, type) {
	var tnos = "1588.0130.02.0303.031.032.033.041.042.043.044.0502.0503.0504.0505.0506.0507.051.052.053.054.055.061.062.063.064.070.080";
	var hnos = "1588.010.011.0130.016.017.018.019.0502.0503.0504.0505.0506.0507.080";

	if (type == "T" && tnos.indexOf(no) < 0) {
		return false;
	} else if (type == "H" && hnos.indexOf(no) < 0) {
		return false;
	} else if (type == "A" && tnos.indexOf(no) < 0 && hnos.indexOf(no) < 0) {
		return false;
	}

	return true;
}

/**
 * object length check
 * 
 * @param obj
 * @param minLen
 * @param maxLen
 * @returns {Boolean}
 */
function validateLength(obj, minLen, maxLen) {
	var len = obj.length;
	var charByte = 0;
	for(var i = 0 ;  i < len ; i++){
		var oneChar = obj.charAt(i);   //한글자 추출
		if(escape(oneChar).length > 4){ 
			charByte +=2;   //한글이면 2를 더한다
		}else{
			charByte++;     //한글아니면 1을 다한다
		}
	}

	if (charByte < minLen || charByte > maxLen) {
		return false;
	}
	return true;
}

function isEnter(event) {
	
	if(event == null) return false;
	var key = "";
	if(window.CSSBS_ie == 'ie') {
		key = event.keyCode || 0;
	} else {
		key = event.which || event.charCode || 0;
	}
	if(  key == 13) 
		return true;
	else 
		return false;
}

function setFYMD(id){
	//YYYY-MM-DD
	var obj = null;
	if (typeof id == 'object'){
		obj = id;
	}
	else{
		obj = getElement(id);
	}
	if (isEmpty(obj)) return false;
	
	var v1 = obj.value;
	v1 = getFormatDay(v1);
	obj.value = v1;
	return true;
}

function autoFYMD(arr){
	if (isEmpty(arr) || isEmpty(arr.length)) return false;
	for(var i=0;i<arr.length;i++){
		var va1 = arr[i];
		document.querySelector("input[id='"+va1+"']").addEventListener("keyup",function(){
	    	setFYMD(this);
		});
	}
}
function setFHMS(id){
	//HH:MI:SS
	var obj = null;
	if (typeof id == 'object'){
		obj = id;
	}
	else{
		obj = getElement(id);
	}
	if (isEmpty(obj)) return false;
	
	var v1 = obj.value;
	v1 = getFormatHms(v1);
	obj.value = v1;
	return true;
}

function autoFHMS(arr){
	if (isEmpty(arr) || isEmpty(arr.length)) return false;
	for(var i=0;i<arr.length;i++){
		var va1 = arr[i];
		document.querySelector("input[id='"+va1+"']").addEventListener("keyup",function(){
	    	setFHMS(this);
		});
	}
}
function autoFAmount(arr){
	if (isEmpty(arr) || isEmpty(arr.length)) return false;
	for(var i=0;i<arr.length;i++){
		var va1 = arr[i];
		document.querySelector("input[id='"+va1+"']").addEventListener("keyup",function(){
	    	setFormatNum(this);
		});
	}
}
function autoFYH(arr){
	if (isEmpty(arr) || isEmpty(arr.length)) return false;
	for(var i=0;i<arr.length;i++){
		var va1 = arr[i];
		document.querySelector("input[id='"+va1+"']").addEventListener("keyup",function(){
	    	setFormatYmdHms(this);
		});
	}
}
function setFPhone(id){
	var obj = null;
	if (typeof id == 'object'){
		obj = id;
	}
	else{
		obj = getElement(id);
	}
	if (isEmpty(obj)) return false;
	
	var v1 = obj.value;
	v1 = getFormatPhone(v1);
	obj.value = v1;
	return true;
}
function autoFPhone(arr){
	if (isEmpty(arr) || isEmpty(arr.length)) return false;
	for(var i=0;i<arr.length;i++){
		var va1 = arr[i];
		document.querySelector("input[id='"+va1+"']").addEventListener("keyup",function(){
			setFPhone(this);
		});
	}
}
//select as to focus at object
function selectionObj(arr){
	if (isEmpty(arr) || isEmpty(arr.length)) return false;
	for(var i=0;i<arr.length;i++){
		var va1 = arr[i];
		document.querySelector("input[id='"+va1+"']").addEventListener("focus",function(e){
			this.select();
		});
	}
}

//change map key
function chgMapKey(map,postFix){
	//postFix : ex. "_1"
	for(var key in map){
		var key1 = key + postFix;
		map[key1] = map[key];
		delete map[key];
	}
	return map
}
//change back map key
function chgBackMapKey(map,postFix){
	//postFix : ex. "_1"
	for(var key in map){
		var key1 = key.replace(postFix,"");
		map[key1] = map[key];
		delete map[key];
	}
	return map;
}

/**
 * get data
 */
function getDataDS(url, param) {
	var ds = new TDataSet();
	ajaxPost(url, param, function(data) {
		ds.setData(data);
	});
	return ds;
}

/**
 * get page data
 */
function getDataDSPage(url, param, pageId, func) {
	var vo = $('#' + pageId);
	if (vo != undefined && vo != null) {
		var pageSize = $('#' + pageId).attr('pageSize') == undefined ? 15
				: (isNaN($('#' + pageId).attr('pageSize')) ? 15 : parseInt($(
						'#' + pageId).attr('pageSize')));
		var currentIndex = $('#' + pageId).attr('currentIndex') == undefined ? 1
				: (isNaN($('#' + pageId).attr('currentIndex')) ? 1
						: parseInt($('#' + pageId).attr('currentIndex')));
		var orderBy = $('#' + pageId).attr('orderBy') == undefined ? "" : $(
				'#' + pageId).attr('orderBy');
		if (orderBy == "") {
			orderBy = $('#' + pageId).attr('baseOrderBy') == undefined ? ""
					: $('#' + pageId).attr('baseOrderBy');
		}

		if (typeof (param) == "object") {
			for ( var key in param) {
				// set page info to the first map data.
				var pm = param[key];
				if (typeof (pm) != "object")
					break;
				pm["pageSize"] = pageSize;
				pm["currentIndex"] = currentIndex;
				pm["orderBy"] = orderBy;
				break;
			}
		}
		getElement(pageId).reqFunc = func;
	}
	var ds = new TDataSet();
	ajaxPost(url, param, function(data) {
		ds.setData(data);
	});
	$('#' + pageId).attr('totalRows', ds.getValue("totalRows", 0));
	pageView(pageId);
	return ds;
}

/**
 * get http data
 */
function httpSend(url, params) {
	var obj = null;
	httpPost(url, params, function(res) {
		let d1 = res;
		if (res != null && typeof res == "string"){
			d1 = d1.trim();
			if (d1 != ""){
			d1 = JSON.parse(res);
			}
		}
		obj = d1;
		if (obj["result"] != null){
			if (typeof obj["result"] == "string"){
				let d2 = obj["result"].trim();
				obj["result"] = jsonObject(d2);
			}
		}
		if (obj["data"] != null){
			if (typeof obj["data"] == "string"){
				let d3 = obj["data"].trim();
				obj["data"] = jsonObject(d3);
			}
		}
	});
	return obj;
}
function httpForm(url, id) {
	var obj = null;
	httpPostForm(url, id, function(res) {
		let d1 = res;
		if (res != null && typeof res == "string"){
			d1 = d1.trim();
			if (d1 != ""){
			d1 = JSON.parse(res);
			}
		}
		obj = d1;
		if (obj["result"] != null){
			if (typeof obj["result"] == "string"){
				let d2 = obj["result"].trim();
				obj["result"] = jsonObject(d2);
			}
		}
		if (obj["data"] != null){
			if (typeof obj["data"] == "string"){
				let d3 = obj["data"].trim();
				obj["data"] = jsonObject(d3);
			}
		}
	});
	return obj;
}
function httpSendPage(url, params, pageId, func) {
	let vo = $('#' + pageId);
	if (vo != undefined && vo != null) {
		let pageSize = $('#' + pageId).attr('pageSize') == undefined ? 15
				: (isNaN($('#' + pageId).attr('pageSize')) ? 15 : parseInt($(
						'#' + pageId).attr('pageSize')));
		let currentIndex = $('#' + pageId).attr('currentIndex') == undefined ? 1
				: (isNaN($('#' + pageId).attr('currentIndex')) ? 1
						: parseInt($('#' + pageId).attr('currentIndex')));
		let orderBy = $('#' + pageId).attr('orderBy') == undefined ? "" : $(
				'#' + pageId).attr('orderBy');
		if (orderBy == "") {
			orderBy = $('#' + pageId).attr('baseOrderBy') == undefined ? ""
					: $('#' + pageId).attr('baseOrderBy');
		}
		let startPage = ((currentIndex-1)*pageSize) + 1;
    	let endPage = (currentIndex*pageSize);

		if (typeof (params) == "object") {
			//for ( let key in params) {
			//	// set page info to the first map data.
			//	let pm = params[key];
			//	if (typeof (pm) != "object")
			//		break;
			//	pm["pageSize"] = pageSize;
			//	pm["currentIndex"] = currentIndex;
			//	pm["orderBy"] = orderBy;
			//	break;
			//}
			params["page"] = currentIndex;
			params["pageSize"] = pageSize;
			params["startPage"] = startPage;
			params["endPage"] = endPage;
		}
		getElement(pageId).reqFunc = func;
	}
	let obj = httpSend(url, params);	//data, result, pages
	if (obj != null && obj["pages"] != null && obj["pages"]["totalCount"] != null){
		let totCnt = obj["pages"]["totalCount"];
		$('#' + pageId).attr('totalRows', totCnt);
		pageView(pageId);
	}
	return obj;
}

/**
 * set select tag Option for Ajax json result
 */
function viewSelect(id, val, yn, cols, url, param) {
	// id:id, val:base value, yn:Y.title include,cols:key/value field name,
	// url:url, param:param
	if (cols == null)
		return false;
	ajaxPost(url, param, function(data) {
		var vo = data.list;
		var rdt = {};
		var varr = [];
		for (var i = 0; i < vo.length; i++) {
			rdt[vo[i][cols[0]]] = vo[i][cols[1]];
			varr[varr.length] = vo[i][cols[0]];
		}
		setSelectOpt(id, rdt, val, yn, varr);
	});
}
/**
 * set select tag Option for Ajax TDataSet result
 */
function viewSelectDS(id, val, yn, cols, url, param) {
	if (cols == null)
		return false;
	ajaxPost(url, param, function(data) {
		var rdt = {};
		var varr = [];
		var ds = new TDataSet();
		ds.setData(data);
		var rows = ds.getRowCount(cols[0]);
		for (var i = 0; i < rows; i++) {
			rdt[ds.getValue(cols[0], i)] = ds.getValue(cols[1], i);
			varr[varr.length] = ds.getValue(cols[0], i);
		}
		setSelectOpt(id, rdt, val, yn, varr);
	});
}

//////////////////////////////////////////////// grid
function chgAbleModeGrid(gObj,ev){
	if (ev.value=="Y"){
		enableRowKeyGrid(gObj,ev.rowKey);
		var $eg = getElementKeyGrid(gObj,ev.rowKey,ev.columnName);
		if (isEmpty($eg)==false){
			$Obj = $eg.querySelector("[type=checkbox]");	//find("[type=checkbox]");
			if (isEmpty($Obj)==false){
				$Obj.setAttribute("checked",true);
			}
		}
	}
	else{
		disableRowKeyGrid(gObj,ev.rowKey);
		var $eg = getElementKeyGrid(gObj,ev.rowKey,ev.columnName);
		if (isEmpty($eg)==false){
			$Obj = $eg.querySelector("[type=checkbox]");
			if (isEmpty($Obj)==false){
				$Obj.setAttribute("checked",true);
			}
		}
	}
	return true;
}

function toggleListTr(obj){
	var $O = getEleByObj(obj);
	if (isEmpty($O)) return;
	
	$O.closest("tbody").addEventListenerAll('[tblGridCurr="Y"]')
	.forEach(item => {item.setAttribute('tblGridCurr','N')});

	$O.setAttribute('tblGridCurr',"Y");
}
function getToggleTr(id){
	var $O = getEleByObj(id);
	if (isEmpty($O)) return;

	var $FO = $O.querySelector('[tblGridCurr="Y"]');
	if (isEmpty($FO)) return null;
	return $FO;
}

function toggleGrid(obj,rowKey) {
	if (isEmpty(rowKey)) return;
	var $OG = getEleByObj(obj.el.id);
	var $O2 = $OG.querySelector('.tui-grid-lside-area td[data-row-key="'+rowKey+'"]');
	if (isEmpty($O2)==false){
		var $O = $O2.closest("tr");
		if (isEmpty($O) == false){
			$O.closest("tbody").querySelectorAll('[tblGridCurr="Y"]')
			.forEach(item => {item.setAttribute('tblGridCurr','N')});
	
			$O.setAttribute('tblGridCurr',"Y");
		}
	}
	
	var $O1 = $OG.querySelector('.tui-grid-rside-area td[data-row-key="'+rowKey+'"]').closest("tr");
	if (isEmpty($O1) == false){
		$O1.closest("tbody").querySelectorAll('[tblGridCurr="Y"]')
		.forEach(item => {item.setAttribute('tblGridCurr','N')});
		
		$O1.setAttribute('tblGridCurr',"Y");
	}
}

//get rowKey
function getRowKeyGrid(gObj,iRow){
	if (isEmpty(gObj)) return "";
	var rw = gObj.getRowAt(iRow);
	return rw["rowKey"];
}
//append one row
function setAppendRowGrid(gObj,ds,row,disableYn){
	if (isEmpty(gObj)) return;
	
	try{
		var colArr = gObj.getColumns();
		if (isEmpty(colArr)) return;
		var colCnt = colArr.length;
		var rMap = {};
		for(var k=0;k<colCnt;k++){
			var vnm = colArr[k].name;
			rMap[vnm] = ds.getValue(vnm,row);
		}
		gObj.appendRows([rMap]);
		if (isEmpty(disableYn)==false && disableYn==true){
			var rCnt = gObj.getRowCount();
			if (rCnt > 0){
				disableRowGrid(gObj,(rCnt-1));
			}
		}
	}
	catch(Exception){}
}
//create new row
function setCreateRowGrid(gObj,disableYn){
	if (isEmpty(gObj)) return -1;
	
	try{
		var cnt = gObj.getRowCount();
		gObj.appendRow(cnt,{});
		if (isEmpty(disableYn)==false && disableYn==true){
			var rCnt = gObj.getRowCount();
			if (rCnt > 0){
				disableRowGrid(gObj,(rCnt-1));
			}
		}
		return cnt;
	}
	catch(Exception){}
	return -1;
}
//clear grid
function setClearGrid(gObj){
	if (isEmpty(gObj)) return;
	try{
		var obj = gObj.el;
		var $O = obj.querySelector('tr');
		if (isEmpty($O) == false){
			$O.closest("tbody").querySelectorAll('[tblGridCurr="Y"]')
			.forEach(item => {item.setAttribute('tblGridCurr','N')});
		}
		gObj.clear();
	}
	catch(Exception){}
}
function chgZeroCol(gObj,nm){
	if (isEmpty(gObj)) return null;
	try{
		var $o1 = (gObj.el);
		var $th = $o1.querySelector('[data-column-name="'+nm+'"]');
		if (isEmpty($th) == false) return null;
		var ord = prevAll($th).length;
		var $oCl = $th.closest('table').querySelectorAll("colgroup col");
		return $oCl[ord].width();
	}
	catch(Exception){return null;}
}
function fixedColGrid(gObj){
	if (isEmpty(gObj)) return null;
	try{
		var vArr = gObj.getColumns();
		var cnt = vArr.length;
		if (vArr.length < 1) return null;
		for(var i=0;i<cnt;i++){
			var w1 = chgZeroCol(gObj,vArr[i].name);
			if (isNumber(w1)==false) continue;
			vArr[i].width = w1;
		}
		gObj.setColumns(vArr);
	}
	catch(Exception){return null;}
}
//set all data
function setDataGrid(gObj,ds,func,disableYn){
	if (isEmpty(gObj)) return;

	try{
		fixedColGrid(gObj);	//fixed width
		gObj.el.style.display = "none";

		var colArr = gObj.getColumns();
		if (isEmpty(colArr)) return;
		var colCnt = colArr.length;
		var cnt = ds.length;
		if (cnt < 1) return;
		var dataArr = new Array();
		for(var i=0;i<cnt;i++){
			var rowMap = {};
			for(var k=0;k<colCnt;k++){
				var vnm = colArr[k].name;
				rowMap[vnm] = ds[i][vnm];
			}
			if (isEmpty(disableYn)==false && disableYn==true){
				rowMap._extraData = {rowState: 'DISABLED'};
			}
			
			dataArr.splice(i,0,rowMap);
		}
		gObj.resetData(dataArr,{});
		if (isEmpty(func)==false){
			func;
		}
	}
	catch(Exception){}
	finally{
		gObj.el.style.display = "";
	}
}

//get a row data
function getRowDataGrid(gObj,row){
	if (isEmpty(gObj)) return null;
	
	var dsMap = {};
	try{
		var rw = gObj.getRowAt(row);
		if (isEmpty(rw)) return null;
		var colArr = gObj.getColumns();
		if (isEmpty(colArr)) return;
		var colCnt = colArr.length;
		for(var k=0;k<colCnt;k++){
			var vnm = colArr[k].name;
			dsMap[vnm] = rw[vnm];
		}
	}
	catch(Exception){}
	return dsMap;
}
function getRowKeyDataGrid(gObj,rowKey){
	if (isEmpty(gObj)) return null;

	var dsMap = {};
	try{
		var rw = gObj.getRow(rowKey,false);
		if (isEmpty(rw)) return null;
		var colArr = gObj.getColumns();
		if (isEmpty(colArr)) return;
		var colCnt = colArr.length;
		for(var k=0;k<colCnt;k++){
			var vnm = colArr[k].name;
			dsMap[vnm] = rw[vnm];
		}
	}
	catch(Exception){}
	return dsMap;
}
//get focused row data
function getFocusedRowDataGrid(gObj){
	if (isEmpty(gObj)) return null;
	
	var rowKey = getFocusedKeyGrid(gObj);
	if (isEmpty(rowKey)) return null;

	return getRowKeyDataGrid(gObj,rowKey);
}
//get column value by rowKey
function getValueKeyColGrid(gObj,rowKey,colNm){
	if (isEmpty(gObj)) return null;
	try{
		return gObj.getValue(rowKey,colNm,false);
	}
	catch(Exception){}
	return null;
}
//get column value
function getValueColGrid(gObj,row,colNm){
	if (isEmpty(gObj)) return null;
	try{
		var rowKey = getRowKeyGrid(gObj,row);
		return gObj.getValue(rowKey,colNm,false);
	}
	catch(Exception){}
	return null;
}
//set all column apply(editYn:true,false)
function setValColAllGrid(gObj,colNm,val,editYn){
	gObj.el.style.display = "none";
	if (isEmpty(gObj)) return;
	try{
		gObj.setColumnValues(colNm,val,editYn);
	}
	catch(Exception){}
	gObj.el.style.display = "";
}
//set column value by rowKey
function setValueKeyColGrid(gObj,rowKey,colNm,val){
	if (isEmpty(gObj)) return;
	try{
		gObj.setValue(rowKey,colNm,val);
	}
	catch(Exception){}
}
//set column value
function setValueColGrid(gObj,row,colNm,val){
	if (isEmpty(gObj)) return;
	try{
		var rowKey = getRowKeyGrid(gObj,row);
		gObj.setValue(rowKey,colNm,val);
	}
	catch(Exception){}
}
//get Focused rowKey
function getFocusedKeyGrid(gObj){
	if (isEmpty(gObj)) return null;
	var $OG = getEleByObj(gObj.el.id);
	var $O = $OG.querySelector('[tblGridCurr="Y"]');
	if (isEmpty($O)) return null;
	var row = $O.querySelectorAll("td")[0].getAttribute("data-row-key");
	
	return row;

}
/*
function getFocusedRowGrid(gObj){
	if (isEmpty(gObj)) return null;
	var $OG = getEleByObj(gObj.el.id);
	var $O = $OG.querySelector('[tblGridCurr="Y"]');
	if (isEmpty($O)) return null;
	var ro = $O.querySelector("td").closest("tr").childeNodes[0];
	
	return ro;
}
*/
//get All data of grid
function getAllDataGrid(gObj){
	if (isEmpty(gObj)) return null;
	var rCnt = gObj.getRowCount();

	var ds = new Array();
	try{
		var colArr = gObj.getColumns();
		if (isEmpty(colArr)) return;
		var colCnt = colArr.length;
		for(var i=0;i<rCnt;i++){
			var rw = gObj.getRowAt(i);
			var dsMap = {};
			for(var k=0;k<colCnt;k++){
				var vnm = colArr[k].name;
				dsMap[vnm] = rw[vnm];
			}
			ds.put(dsMap);
		}
	}
	catch(Exception){}
	return ds;
}
//get state data of grid
function getStateDataGrid(gObj,state){
	//state : "create","update","delete"
	if (isEmpty(gObj)) return null;

	var uRows = null;
	try{
		var mrs = gObj.getModifiedRows();
		if (isEmpty(mrs)) return null;
		
		if (state=="create"){
			uRows = mrs["createdRows"];
		}
		else if (state=="update"){
			uRows = mrs["updatedRows"];
		}
		else if (state=="delete"){
			uRows = mrs["deletedRows"];
		}
		if (isEmpty(uRows)) return null;

		return uRows;
	}
	catch(Exception){}
	return null;
}
//delete row
function deleteRowGrid(gObj,row){
	//row:row 순서(rowKey 아님)
	if (isEmpty(gObj)) return null;
	try{
		var rk = getRowKeyGrid(gObj,row);
		gObj.removeRow(rk,{removeOriginalData:false,"keepRowSpanData":true});
	}
	catch(Exception){}
}
function deleteRowKeyGrid(gObj,rowKey){
	//row:row 순서(rowKey 아님)
	if (isEmpty(gObj)) return null;
	try{
		gObj.removeRow(rowKey,{removeOriginalData:false,"keepRowSpanData":true});
	}
	catch(Exception){}
}
//get checked data
function getCheckedDataGrid(gObj,colNm){
	//colNm : checked column name, value : "Y"기준
	var ds = getAllDataGrid(gObj);
	var cnt = ds.length;
	var ds1 = new Array();
	if (cnt < 1) return null;

	for(var i=0;i<cnt;i++){
		var vChk = ds[i][colNm];
		if (isEmpty(vChk)) continue;
		if (vChk == "Y"){
			ds.put(ds[i]);
		}
	}
	return ds1;
}
//focus
function focusAtGrid(gObj,rowKey, columnName, setScroll){
	if (isEmpty(gObj)) return null;
	try{
		gObj.focus(rowKey, columnName, setScroll);
	}
	catch(Exception){}
}
//focusAt
function focusAtGrid(gObj,rowIndex,columnIndex,setScroll){
	if (isEmpty(gObj)) return null;
	try{
		gObj.focusAt(rowIndex,columnIndex,setScroll);
	}
	catch(Exception){}
}
//enable Row
function enableRowKeyGrid(gObj,rowKey){
	if (isEmpty(gObj)) return null;
	try{
		gObj.enableRow(rowKey);
	}
	catch(Exception){}
}
function enableRowGrid(gObj,row){
	if (isEmpty(gObj)) return null;
	try{
		var rowKey = getRowKeyGrid(gObj,row);
		gObj.enableRow(rowKey);
	}
	catch(Exception){}
}
//disable Row
function disableRowKeyGrid(gObj,rowKey){
	if (isEmpty(gObj)) return null;
	try{
		gObj.disableRow(rowKey);
	}
	catch(Exception){}
}
function disableRowGrid(gObj,row){
	if (isEmpty(gObj)) return null;
	try{
		var rowKey = getRowKeyGrid(gObj,row);
		gObj.disableRow(rowKey);
	}
	catch(Exception){}
}
//get element of rowKey
function getElementKeyGrid(gObj,rowKey,colNm){
	if (isEmpty(gObj)) return null;
	try{
		return gObj.getElement(rowKey,colNm);
	}
	catch(Exception){}
	return null;
}
//get element of row
function getElementGrid(gObj,row,colNm){
	if (isEmpty(gObj)) return null;
	try{
		var rowKey = getRowKeyGrid(gObj,row);
		return gObj.getElement(rowKey,colNm);
	}
	catch(Exception){}
	return null;
}
//get column title
function getColInfoAll(gObj,dv){
	//dv:title,name
	if (isEmpty(gObj)) return [];
	var vo = gObj.getColumns();
	var cnt = vo.length;
	if (cnt<1) return;
	var vArr = new Array();
	for(var i=0;i<cnt;i++){
		if (dv=="header"){
			vArr[vArr.length] = vo[i]["header"];
		}
		if (dv=="name"){
			vArr[vArr.length] = vo[i]["name"];
		}
	}
	return vArr;
}
//get column title without hidden
function getColInfo(gObj,dv){
	if (isEmpty(gObj)) return [];
	var vo = gObj.getColumns();
	var cnt = vo.length;
	if (cnt<1) return;
	var vArr = new Array();
	for(var i=0;i<cnt;i++){
		if (vo[i]["hidden"]!= true) {
			if (dv=="header"){
				vArr[vArr.length] = vo[i]["header"];
			}
			if (dv=="name"){
				vArr[vArr.length] = vo[i]["name"];
			}
		}
	}
	return vArr;
}
//get column excel
function getColInfoXls(gObj,dv){
	if (isEmpty(gObj)) return [];
	var vo = gObj.getColumns();
	var cnt = vo.length;
	if (cnt<1) return;
	var vArr = new Array();
	for(var i=0;i<cnt;i++){
		//exceptExcel
		if (isEmpty(vo[i]["exceptExcel"])) {
			if (dv=="header"){
				vArr[vArr.length] = vo[i]["header"];
			}
			if (dv=="name"){
				vArr[vArr.length] = vo[i]["name"];
			}
		}
	}
	return vArr;
}
////////////////////////////////////////////////grid end.
////////////////////////////////////////////////layer popup
function layerToggle(id,map){
	//mode : 1.show 2.hide assignW: left, center
	//assignH: top, middle
	let titleHeight = "35px";
	let vo1 = document.querySelector('#'+id);
	let body1 = document.querySelector('html body');
	if (vo1 == null) return null;

	let mode = map["mode"];
	let top = map["top"];
	let left = map["left"];
	let zIndex = map["zIndex"];
	let assignW = map["assignW"];
	let assignH = map["assignH"];
	let titleYn = map["titleYn"];
	let scrollYn = map["scrollYn"];
	
	if (isEmpty(zIndex)){
		zIndex = "100";	//default value
	}
	if (isEmpty(assignW)){
		assignW = "center";
	}
	if (isEmpty(assignH)){
		assignH = "middle";
	}
	let zBackIndex = zIndex - 1;
	if (isEmpty(titleYn)) titleYn = "Y";
	if (titleYn != 'N'){
		vo1.style.borderRadius = "5px";
	}
	if (isEmpty(scrollYn)) scrollYn = "";
	//round
	//vo1.style.borderRadius = "5px";
	
	if (vo1.style.zIndex != zIndex){
		vo1.style.zIndex = zIndex;
		vo1.style.backgroundColor = "#fff";
		vo1.style.position = "absolute";
		//vo1.style.overflow = "hidden";

		let dvTop = vo1.getAttribute("top");
		let dvLeft = vo1.getAttribute("left");
		let popLeft = 0;
		let popTop = 0;
		if (!isEmpty(dvTop)){
			popTop = dvTop;
		}
		if (!isEmpty(dvLeft)){
			popLeft = dvLeft;
		}
		if (!isEmpty(top)){
			popTop = top;
		}
		if (!isEmpty(left)){
			popLeft = left;
		}
		if (assignW == "center" && isEmpty(left)){
			vo1.style.display = "";
			let popWidth = vo1.clientWidth;
			let WinWidth = window.innerWidth;
			//let WinWidth = document.querySelector('html main').clientWidth;
			popLeft = Math.round((WinWidth - popWidth) / 2);
			if (popLeft < 0) popLeft = 0;
			vo1.style.display = "none";
		}
		if (assignH == "middle" && isEmpty(top)){
			vo1.style.display = "";
			let popHeight1 = vo1.clientHeight;
			let WinHeight1 = window.innerHeight;
			popTop = Math.round((WinHeight1 - popHeight1) / 2);
			if (popTop < 0) popTop = 0;
			vo1.style.display = "none";
		}
		vo1.style.top = popTop + "px";
		vo1.style.left = popLeft + "px";
	}
	let vTitle = vo1.getAttribute("title");

	let vTitle1 = id+"_headerTitle";
	let vBack = id+"_back";
	let obEnd = vo1.querySelector('#'+vTitle1);
	if (obEnd == null){
		let obback=document.createElement("DIV");
		obback.setAttribute("id",vBack);
		obback.style.background = "rgba(200,200,200,0.8)";
		obback.style.width = "100%";
		obback.style.height = "100%";
		obback.style.position = "fixed";
		obback.style.zIndex = zBackIndex;
		obback.style.top = "0px";
		obback.style.left = "0px";
		obback.style.display = "none";
		obback.addEventListener('click', function (e) {
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
			return false;
		});
		body1.prepend(obback);
		
		let ob1=document.createElement("DIV");
		ob1.setAttribute("id",vTitle1);
		ob1.style.backgroundColor = "#3A96FC";
		ob1.style.height = titleHeight;
		let so1=document.createElement("SPAN");
		so1.style.color = "#ffffff";
		so1.style.display = "inline-block";
		so1.style.lineHeight = titleHeight;
		so1.style.marginLeft = "10px";
		so1.style.fontWeight = "bold";
		so1.style.verticalAlign = "middle";
		so1.style.cursor = "default";
		so1.innerHTML = vTitle;
		if (titleYn == 'N'){
			ob1.style.display = "none";
		}
		ob1.append(so1);

		let so2=document.createElement("SPAN");
		so2.style.color = "#ffffff";
		so2.style.display = "inline-block";
		so2.style.lineHeight = titleHeight;
		so2.style.marginRight = "10px";
		so2.style.fontWeight = "bold";
		so2.style.verticalAlign = "middle";
		so2.style.float = "right";
		so2.style.fontSize = "large";
		so2.style.cursor = "default";
		so2.innerHTML = "X";
		if (titleYn == 'N'){
			so2.style.display = "none";
		}
		so2.addEventListener('click', function () {
			vo1.style.display = "none";
			document.querySelector('#'+vBack).style.display = "none";
		});
		ob1.append(so2);

		ob1.addEventListener('mouseover', function (e) {
			ob1.leftPageX = "";
			ob1.topPageY = "";
		});
		ob1.addEventListener('mousedown', function (e) {
			ob1.leftPageX = e.pageX;
			ob1.topPageY = e.pageY;
		});
		ob1.addEventListener('mousemove', function (e) {
			if (isNumber(ob1.leftPageX)==false || isNumber(ob1.topPageY)==false) return;
			let mDown = false;
			mDown = e.buttons === undefined ? e.which === 1 : e.buttons === 1;
			if (mDown == false) return;
			let eX = e.pageX;
			let eY = e.pageY;
			let x = parseInt(eX) - parseInt(ob1.leftPageX);
			let y = parseInt(eY) - parseInt(ob1.topPageY);
			if (isNumber(x)==false || isNumber(y)==false) return;
			vo1.style.left = (vo1.offsetLeft + x)+"px";
			vo1.style.top = (vo1.offsetTop + y)+"px";
			ob1.leftPageX = eX;
			ob1.topPageY = eY;
		});

		vo1.prepend(ob1);
	}

	if (mode == 1){
		vo1.style.display = "";
		document.querySelector('#'+vBack).style.display = "";
		
		if (scrollYn == "Y") window.scrollTo(0, 0);
	}
	else{
		vo1.style.display = "none";
		document.querySelector('#'+vBack).style.display = "none";
	}
}
////////////////////////////////////////////////layer popup end.
