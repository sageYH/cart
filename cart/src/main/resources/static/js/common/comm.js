//getEVal
//getObjVal
//setObjVal
//getRowObj
//getCheckBoxVal
//replSymEtChar
//replScript
//addTrInTbl
//delTrInObj
//setRowsTbl
//setRowsLayer
//appendRowsTbl
//appendRowsLayer
//emptyTrMsg
//getEleByObj : get elememt
//clearObject
//clearObjects
//clearObjectArr
(function(window) {
	var WINSUB = function() {};

	WINSUB.load = function(pageId) {
		var document = window.document;

		var ready = function(fn) {
			document.addEventListener("DOMContentLoaded", function(){
				fn;
			});
		};

		return { id: pageId, document: document, ready: ready };
	};
	window.WINDSUB = window.$W = WINSUB;
})(window);

String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/gi, "");
};
String.prototype.replaceAll = function(str1, str2){
	if(str1 == str2) return this;
	var temp_str = this.trim();
	if(this.trim() == "") return temp_str;

	return temp_str.replace( eval("/" + str1 + "/g"),str2);
};

var tblDataLoadEvt = null;
function waitTime(ms){
	return new Promise((r)=>setTimeout(r,ms));
}

function clearDash(v1) {
	if (isEmpty(v1)) return v1;
	return String(v1).replace(/\-/g,"");
}
function clearComma(v1) {
	if (isEmpty(v1)) return v1;
	return String(v1).replace(/,/g,"");
}
function clearSpace(v1) {
	if (isEmpty(v1)) return v1;
	return String(v1).replace(/\s/g,"");
}

function getAttr(id,arg){
	var obj= $("#"+id).get();
	if(obj == null) return null;
	return $("#"+id).attr(arg);
}
function setAttrs(id,o1){
	var obj;
	if (typeof(id)=="object"){
		obj = id;
	}
	else{
		obj = getElement(id);
	}
	if (obj==null) return;
	for (var key in o1){
		$(obj).attr(key,o1[key]);
	}
}
function deepCopyObj(inObj) {
	var outObj, value, key
	if(typeof inObj !== "object" || inObj === null) {
		return inObj
	}
	outObj = Array.isArray(inObj) ? [] : {}
	for (key in inObj) {
		value = inObj[key]
		outObj[key] = (typeof value === "object" && value !== null) ? deepCopyObj(value) : value
	}
	return outObj
}
//id -> object
function getElement(id){
	return document.getElementById(id);
}
//id -> objects
function getElements(id){
	return document.getElementsByName(id);
}
function jsonObject(val){
	try{
		return eval('(' + val + ')');
	}
	catch(Exception){
		return null;
	}
}
function getEleByObj(id){
	var obj;
	if (typeof(id)=="object"){
		obj = id;
	}
	else{
		obj = getElement(id);
	}
	return obj;
}
function getValFrEl(id){
	var obj = getEleByObj(id);
	if (obj==null) return;
	return obj.value;
}
function setValFrEl(id, val){
	var obj = getEleByObj(id);
	if (obj==null) return;
	obj.value = val;
}
function getFormatDay(v1){
	if (isEmpty(v1)) return "";
	v1 = clearDash(v1);
	if (v1.length==6) v1 = v1.substr(0,4) + "-" + v1.substr(4,2);
	if (v1.length==8) v1 = v1.substr(0,4) + "-" + v1.substr(4,2) + "-" + v1.substr(6,2);
	return v1;
}
function setFormatDay(obj){
	if (obj == null) return false;
	var v1 = getValFrEl(obj);
	setValFrEl(obj,getFormatDay(v1));
}
function clearFormatDay(v1) {
	if (isEmpty(v1)) return v1;
	return String(v1).replace(/[.-]/g,"");
}
function getFormatNum(num) {
	if (num=="" || num == null) return num;
	num = clearComma(num);
	return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}
function setFormatNum(obj){
	if (obj == null) return false;
	var v1 = getValFrEl(obj);
	setValFrEl(obj,getFormatNum(v1));
}
function getFormatHms(v1){
	if (v1 == "") return v1;
	v1 = clearFormatYmdHms(v1);
	if (v1.length==4) v1 = v1.substr(0,2) + ":" + v1.substr(2,2);
	if (v1.length==8) v1 = v1.substr(0,2) + ":" + v1.substr(2,2) + ":" + v1.substr(4,2);
	return v1;
}
function setFormatHms(obj){
	if (obj == null) return false;
	var v1 = getValFrEl(obj);
	setValFrEl(obj,getFormatHms(v1));
}
function getFormatYmdHms(v1){
	var vRtn = "";
	if (v1 == "") return v1;
	v1 = clearFormatYmdHms(v1);
	var vymd = "";
	var vhms = "";
	if (v1.length>8){
		vymd = v1.substr(0,8);
		vhms = v1.substring(8);
	}
	else{
		vymd = v1;
	}
	vymd = getFormatDay(vymd);
	if (isEmpty(vhms)==false){
		vhms = getFormatHms(vhms);
		vRtn = vymd + ' ' + vhms;
	}
	else{
		vRtn = vymd;
	}

	return vRtn;
}
function setFormatYmdHms(obj){
	if (obj == null) return false;
	var v1 = getValFrEl(obj);
	setValFrEl(obj,getFormatYmdHms(v1));
}
function clearFormatYmdHms(v1) {
	if (isEmpty(v1)) return v1;
	return String(v1).replace(/[.-:\s]+/g,"");
}

function getFormatPhone(v1) {
	var RegPhonNum = "";
	var DataForm = "";

	if (isEmpty(v1)) return "";

	v1 = clearDash(v1);
	if (v1.length < 4) return v1;

	/* 지역번호 02일 경우 10자리 이상입력 못하도록 제어함. */
	if (v1.substring(0, 2) == "02" && v1.length > 10) {
		v1 = v1.substring(0, 10);
	}
	if (v1.length > 3 && v1.length < 7) {
		if (v1.substring(0, 2) == "02") {
			DataForm = "$1-$2";
			RegPhonNum = /([0-9]{2})([0-9]+)/;
		} else {
			DataForm = "$1-$2";
			RegPhonNum = /([0-9]{3})([0-9]+)/;
		}
	} else if (v1.length == 7) {
		if (v1.substring(0, 2) == "02") {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{2})([0-9]{3})([0-9]+)/;
		} else {
			DataForm = "$1-$2";
			RegPhonNum = /([0-9]{3})([0-9]{4})/;
		}
	} else if (v1.length == 8) {
		if (v1.substring(0, 2) == "02") {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{2})([0-9]{3})([0-9]+)/;
		} else {
			DataForm = "$1-$2";
			RegPhonNum = /([0-9]{4})([0-9]{4})/;
		}
	} else if (v1.length == 9) {
		if (v1.substring(0, 2) == "02") {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{2})([0-9]{3})([0-9]+)/;
		} else {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{3})([0-9]{3})([0-9]+)/;
		}
	} else if (v1.length == 10) {
		if (v1.substring(0, 2) == "02") {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{2})([0-9]{4})([0-9]+)/;
		} else {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{3})([0-9]{3})([0-9]+)/;
		}
	} else if (v1.length == 11) {
		if (v1.substring(0, 2) == "02") {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{2})([0-9]{4})([0-9]+)/;
		} else {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{3})([0-9]{4})([0-9]+)/;
		}
	} else {
		if (v1.substring(0, 2) == "02") {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{2})([0-9]{3})([0-9]+)/;
		} else {
			DataForm = "$1-$2-$3";
			RegPhonNum = /([0-9]{4})([0-9]{4})([0-9]+)/;
		}
	}
	while (RegPhonNum.test(v1)) {
		v1 = v1.replace(RegPhonNum, DataForm);
	}
	return v1;
}
function setFormatPhone(obj){
	if (obj == null) return false;
	var v1 = getValFrEl(obj);
	setValFrEl(obj,getFormatPhone(v1));
}

function getFormatBiz(v1){
	if (isEmpty(v1)) return "";
	v1 = clearDash(v1);
	if (v1.length > 3 && v1.length < 6){
		v1 = v1.substring(0,3)+"-"+v1.substring(3);
	}
	else if (v1.length > 5){
		v1 = v1.substring(0,3)+"-"+v1.substring(3,5)+"-"+v1.substring(5);
	}
	return v1;
}
function setFormatBiz(obj){
	if (obj == null) return false;
	var v1 = getValFrEl(obj);
	setValFrEl(obj,getFormatBiz(v1));
}

function getFormatRrn(v1){
	if (isEmpty(v1)) return "";
	v1 = clearDash(v1);
	if (v1.length > 6){
		v1 = v1.substring(0,6)+"-"+v1.substring(6);
	}
	return v1;
}
function setFormatRrn(obj){
	if (obj == null) return false;
	var v1 = getValFrEl(obj);
	setValFrEl(obj,getFormatRrn(v1));
}

function trimZero(v1){
	if (v1.length<1) return "";
	while(true){
		if (v1.substring(0,1)!="0") break;
		if (v1.length==1) break;
		v1 = v1.substring(1);
	}
	return v1;
}
function isDate(v1){
	v1 = v1.replaceAll("-","");
	v1 = v1.replaceAll("\\.","");
	if (v1.length!=8 || isNaN(v1)) return false;
	try{
		var y = parseInt(v1.substr(0,4),10);
		var m = parseInt(v1.substr(4,2),10)-1;
		if (m<0 && m>11) return false;
		var d = parseInt(v1.substr(6),10);
		var e = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
		if ((y % 4 == 0 && y % 100!=0) || y % 400 == 0) e[1] = 29;
		return (d>=1 && d<=e[m]);
	}
	catch(Exception){
		return false;
	}
}
function isTime(v1){
	v1 = v1.replaceAll(":","");

	if (v1.length==4) v1 = v1 + "00";
	if (v1.length!=6) return false;

	var v2 = trimZero(v1);

	if (isNumber(v2)==false) return false;
	if (parseInt(v2)<0) return false;

	if (v1.substring(0,2) > "23") return false;
	if (v1.substring(2,4) > "59") return false;
	if (v1.substring(4) > "59")	return false;
	return true;
}
function isNumber(v1){
	if (v1==null || v1==="") return false;
	var v2 = v1 + ""; 
	v2 = v2.replace(/[,원,-]/gm,"");
	if (isNaN(v2)) return false;

	return true;
}
function isEmpty(val){
	var rst = false;
	if (val==undefined || val==null) return true;
	if(typeof val == "object"){
		var cnt = 0;
		for(var property in val){ cnt++ ;}
		if(cnt == 0) rst = true;		
	} else if(typeof val == "string"){
		if(val === "") rst = true;
	} else if(typeof val == "number"){
	} else {
		if(val === "") rst = true;
	}
	return rst;
}
function isRrn(val){
	 // check JuminNumber-type and sex_digit
	fmt = /^\d{6}[1234]\d{6}$/;
	if (!fmt.test(val)) {
	   return false;
	}
	
	// check date-type
	
	birthYear = (val.charAt(6) <= "2") ? "19" : "20";
	birthYear += val.substr(0, 2);
	birthMonth = val.substr(2, 2) - 1;
	birthDate = val.substr(4, 2);
	birth = new Date(birthYear, birthMonth, birthDate);
	
	if ( birth.getYear() % 100 != val.substr(0, 2) ||
	birth.getMonth() != birthMonth ||
	birth.getDate() != birthDate) {
	   return false;
	}
	
	// Check Sum code
	buf = new Array(13);
	for (var i = 0; i < 6; i++)   buf[i] = parseInt(val.charAt(i));
	for (var i = 6; i < 13; i++)  buf[i] = parseInt(val.charAt(i));
	multipliers = [2,3,4,5,6,7,8,9,2,3,4,5];
	for (i = 0, sum = 0; i < 12; i++) sum += (buf[i] *= multipliers[i]);
	
	if ((11 - (sum % 11)) % 10 != buf[12]) {
	   return false;
	}
	return true;
}
/**
 * id에 포함된 objects -> TDataSet
 * ds: 처음은 null
 * fields : 필드명이 다른경우 fields[key] : copy field명, default null
 * ynAll : checkbox or radio에서만 사용. Y.전체 N.check된 elements, default null 
 * */
function getObjVal(id,ds,fields,ynAll) {
	var obj;
	
	if (ds==null) ds = new TDataSet();

	if (typeof(id)=="object"){
		obj = id;
	}
	else{
		obj = getElement(id);
	}
	if (obj==null) return null;
	var nodes = obj.childNodes;
	if (nodes.length<1) return null;
	for (var i=0;i<nodes.length;i++){
		var vobj = nodes.item(i).childNodes;
		if (vobj!=null && vobj.length > 0){
			getObjVal(nodes.item(i),ds,fields,ynAll);
		}
		copyDataSet(nodes.item(i),ds,fields,ynAll);
	}
	return ds;
}
function copyDataSet(obj1,ds,fields,ynAll){
	if (obj1==null) return false;
	var vtag = obj1.nodeName;
	if (vtag=="INPUT"){
		if (obj1.type=="text" || obj1.type=="hidden" || obj1.type=="password"){
			var val = obj1.value;
			if (fields==null || isEmpty(fields[obj1.name])){
				if (isEmpty(obj1.name)) return;
				ds.add(obj1.name,val);
			}
			else{
				if (isEmpty(fields[obj1.name])) return;
				ds.add(fields[obj1.name],val);
			}
		}
		if (obj1.type=="radio" || obj1.type=="checkbox"){
			if (obj1.checked){
				var val = obj1.value;
				if (fields==null || isEmpty(fields[obj1.name])){
					if (isEmpty(obj1.name)) return;
					ds.add(obj1.name,val);
				}
				else{
					if (isEmpty(fields[obj1.name])) return;
					ds.add(fields[obj1.name],val);
				}
			}
			else{
				if (ynAll!=null && ynAll=="Y"){
					var val = "";
					var unval = obj1.getAttribute("unvalue");
					if (isEmpty(unval)==false) val = unval;
					if (fields==null || isEmpty(fields[obj1.name])){
						if (isEmpty(obj1.name)) return;
						ds.add(obj1.name,val);
					}
					else{
						if (isEmpty(fields[obj1.name])) return;
						ds.add(fields[obj1.name],val);
					}
				}
			}
		}
	}
	if (vtag=="SELECT" || vtag=="TEXTAREA"){
		if (fields==null || isEmpty(fields[obj1.name])){
			if (isEmpty(obj1.name)) return;
			ds.add(obj1.name,obj1.value);
		}
		else{
			if (isEmpty(fields[obj1.name])) return;
			ds.add(fields[obj1.name],obj1.value);
		}
	}
	return true;
}
//get Element value
function getEVal(id){
	var val = "";
	
	var obj = getEleByObj(id);
	if (obj==null) return "";

	var vtag = obj.nodeName;
	if (vtag=="INPUT"){
		if (obj.type=="text" || obj.type=="hidden" || obj.type=="password"){
			val = obj.value;
		}
		if (obj.type=="radio" || obj.type=="checkbox"){
			if (obj.checked) val = obj.value;
		}
	}
	if (vtag=="SELECT" || vtag=="TEXTAREA"){
		val = obj.value;
	}
	if (vtag=="SPAN" || vtag=="DIV"){
		val = obj.innerHTML;
	}
	return val;
}
function getIframeDoc(frame){
    var doc = null;
    
    // IE8 cascading access check
    try {
        if (frame.contentWindow) {
            doc = frame.contentWindow.document;
        }
    } catch(err) {
    }

    if (doc) { // successful getting content
        return doc;
    }

    try { // simply checking may throw in ie8 under ssl or mismatched protocol
        doc = frame.contentDocument ? frame.contentDocument : frame.document;
    } catch(err) {
        // last attempt
        doc = frame.document;
    }
    return doc;
}
/**
 * id에 포함된 objects value를 초기화
 * */
function clearObjects(id,mode) {
	var obj;
	
	if (typeof(id)=="object"){
		obj = id;
	}
	else{
		obj = getElement(id);
	}
	if (obj==null) return false;
	var nodes = obj.childNodes;
	if (nodes.length<1) return false;
	for (var i=0;i<nodes.length;i++){
		var vobj = nodes.item(i).childNodes;
		if (vobj!=null && vobj.length > 0){
			clearObjects(nodes.item(i),mode);
		}
		clearObject(nodes.item(i),mode);
	}
}
/**
 * Array에 포함된 objects value를 초기화
 * */
function clearObjectArr(arr1,ind){
	if (arr1.length<1){
		alert("Field가 존재하지 않습니다.");
		return false;
	}
	for (var k=0;k<arr1.length;k++){
		var obj = getElements(arr1[k]);
		if (obj.length<1) continue;
		if (ind!=null){
			if ((obj.length-1)<ind) return false;
			clearObject(obj[ind],1);
			return true;
		}
		for (var i=0;i<obj.length;i++){
			clearObject(obj[i],1);
		}
	}
	return true;
}
//object 1개 clear
function clearObject(obj1,mode){
	if (obj1==null) return false;
	var vtag = obj1.nodeName;
	if (vtag=="INPUT"){
		if (obj1.type=="text" || obj1.type=="hidden" || obj1.type=="password" || obj1.type=="file"){
			obj1.value = "";
		}
		if (obj1.type=="radio" || obj1.type=="checkbox"){
			obj1.checked = false;
		}
	}
	if (vtag=="SELECT" || vtag=="TEXTAREA"){
		obj1.value = "";
	}
	if (mode!=null && mode==1){
		if (vtag=="SPAN" || vtag=="DIV"){
			obj1.innerHTML = "";
		}
	}
	return true;
}
//table clear
function clearTable(id){
	var obj = getEleByObj(id);
	if (obj == null) return;
	if (obj.getElementsByTagName('tbody').length > 0){
		obj.getElementsByTagName('tbody')[0].innerHTML = "";
	}
}
/**
* data id==object.id data copy
* ind : one row for multy rows, null:single row
* data1 : 필드명이 다른경우 data1[key] : copy field명, default null
* data(value)의 key와 data1(필드명)의 key는 동일함.
* target : target id 내에만 적용. default null
*/
function setObjVal(data,ind,data1,target){
	for (var key in data){
		var $obj = null;
		var obj1 = null;
		if (data1==null){
			obj1 = getElements(key);
		}
		else{
			if (data1==null || isEmpty(data1[key])){
				obj1 = getElements(key);
			}
			else{
				obj1 = getElements(data1[key]);
			}
		}
		if (obj1.length < 1) continue;
		if (target!=null){
			try{
				if (getElement(target).contains(obj1[0])==false) continue;
			}catch(Exception){ continue; }
		}
		if (obj1[0].nodeName=="INPUT" && (obj1[0].type=="radio" || obj1[0].type == 'checkbox')){
			for (var i=0;i<obj1.length;i++){
				if (obj1[i].value == data[key]){
					obj1[i].checked = true;
					break;
				}
			}
			continue;
		}

		if (ind==null)
			$obj = obj1[0];
		else
			$obj = obj1[ind];
		if ($obj==null) continue;
		
		var vtag = $obj.nodeName;
		if (vtag=="INPUT"){
			if ($obj.type == 'text' || $obj.type == 'hidden' || $obj.type == 'password') {
				var val = data[key];
				$obj.value = val;
			}
		}
		else if (vtag=="SELECT" || vtag=="TEXTAREA"){
			$obj.value = data[key];
			if ($obj.selectedIndex < 0) $obj.value = "";
		}
		else{
			if (vtag=="SPAN" || vtag=="DIV"){
				$obj.innerHTML = data[key];
			}
		}
	}
	return true;
}
//multi row object 해당 row return
function getRowObj(obj){
	var row = -1;
	if (obj==null) return -1;
	if (obj.id=="") return -1;
	var vo1 = document.getElementsByName(obj.id);
	if (vo1.length>1){
		for(var i=0;i<vo1.length;i++){
			if (obj === vo1[i]){
				row = i;
				break;
			}
		}
	}
	else{
		row = 0;
	}
	return row;
}
//getObjVal : objectToDataSet
function getCheckBoxVal(id,ds,checkid,ynAll,subArr,fields) {
	var obj = getEleByObj(id);
	if (obj == null) return null;
	
	if (ds==null) ds = new TDataSet();

	var nodes = obj.childNodes;
	if (nodes.length<1) return null;
	for (var i=0;i<nodes.length;i++){
		var vobj = nodes.item(i).childNodes;
		if (vobj!=null && vobj.length > 0){
			getCheckBoxVal(nodes.item(i),ds,checkid,ynAll,subArr,fields);
		}
		var vItem = nodes.item(i);
		if (vItem.nodeName!="INPUT") continue;
		if (vItem.type!="checkbox") continue;
		if (ynAll=="N"){
			if (vItem.checked == false) continue;
		}
		if (vItem.name!=checkid) continue;
		copyDataSet(vItem,ds,fields,ynAll);
		if (subArr!=null){
			var orow = getRowObj(vItem);
			for(var k=0;k<subArr.length;k++){
				var vo1 = getElements(subArr[k]);
				if (vo1.length<1) continue;
				if ((vo1.length-1) < orow) continue;
				if (vItem.name==vo1[orow].name) continue;
				copyDataSet(vo1[orow],ds,fields,ynAll);
			}
		}
	}
	return ds;
}

function replSymEtChar(id,obj){
	var vo1 = getEleByObj(id);
	if (vo1 == null) return "";
	var str = vo1.innerHTML;

	str = str.replaceAll("<!--","");
	str = str.replaceAll("-->","").trim();
	if (str==null || str=="") return str;
	if (isEmpty(obj)==false){
		for (var key in obj){
			var v1 = "{{"+key+"}}";
			v1.replaceAll("<","&lt;");
			v1.replaceAll(">","&gt;");
			while(true){
				var idx = str.indexOf(v1);
				if (idx < 0) break;
				var vVal1 = obj[key];
				if (v1 == vVal1){
					//remove '{{','}}'
					vVal1 = vVal1.replaceAll("{","｛");
					vVal1 = vVal1.replaceAll("}","｝");
				}
				str = str.replaceAll(v1,vVal1);
			}
		}
	}
	//clear "{{}}"
	while(true){
		var idx = str.indexOf("{{");
		if (idx < 0) break;
		var idx1 = str.indexOf("}}");
		if (idx1 < 0) break;
		var v1 = str.substring(idx,(idx1+2));
		str = str.replaceAll(v1,"");
	}
	return str;
}
function replScript(id,arr,ds){
	var varr = new Array();
	if (arr.length==undefined || arr.length==null || arr.length < 1) return varr;
	if (isEmpty(ds)) return varr;
	var cnt = ds.getRowCount(arr[0]);
	if (cnt > 0){
		for(var i=0;i<cnt;i++){
			var obj = {};
			for(var k=0;k<arr.length;k++){
				obj[arr[k]] = ds.getValue(arr[k],i);
			}
			varr.push(replSymEtChar(id,obj));
		}
	}
	return varr;
}
function addTrInTbl(tbl,varr,row){
	var tobj = getEleByObj(tbl);
	if (tobj == null) return;
	try{
		var obj = tobj.getElementsByTagName('tbody')[0];
		if (obj == null){
			tobj.appendChild(document.createElement('TBODY'));
			obj = tobj.getElementsByTagName('tbody')[0];
		}
		for(var i=0;i<varr.length;i++){
			var vTmp = document.createElement('template');
			vTmp.innerHTML = varr[i];
			var clone = document.importNode(vTmp.content, true);
			obj.appendChild(clone);
		}
	}catch(e){}
}
function delTrInObj(o1){
	try{
		var obj = getEleByObj(o1);
		if (obj == null) return;
		obj.remove();
	}catch(e){}
}
function setRowsTbl(tableId,dvId,arr,ds,cols){
	clearTable(tableId);

	var vArr = replScript(dvId,arr,ds);
	if (vArr.length > 0){
		addTrInTbl(tableId,vArr);
	}
	else{
		addTrInTbl(tableId,emptyTrMsg(cols));
	}
}
function setRowsLayer(objId,dvId,arr,ds){
	try{
		var vo1 = getEleByObj(objId);
		if (vo1 == null) return;
		vo1.innerHTML = "";
	
		var vArr = replScript(dvId,arr,ds);
		if (vArr.length > 0){
			vo1.innerHTML = vArr.join("");
		}
	}catch(e){}
}
function appendRowsTbl(tableId,dvId,arr,ds,cols){
	var vArr = replScript(dvId,arr,ds);
	if (vArr.length > 0){
		addTrInTbl(tableId,vArr);
	}
	else{
		addTrInTbl(tableId,emptyTrMsg(cols));
	}
}
function appendRowsLayer(id,dvId){
	try{
		var vo1 = getEleByObj(id);
		if (vo1 == null) return;
		var str1 = replSymEtChar(dvId,null);
		var vTmp =document.createElement('template');
		vTmp.innerHTML = str1;
		vo1.appendChild(vTmp.content.cloneNode(true));
	}catch(e){}
}
function emptyTrMsg(cspan, width, cls) {
	var vspn = "";
	var vcls = "";
	if (cspan != null)
		vspn = " colspan='" + cspan + "'";
	if (width == null)
		width = "100%";
	if (cls != null)
		vcls = " class='" + cls + "'";
	var varr = new Array();
	varr.push("<tr><td" + vspn + vcls + " style='width:" + width
			+ ";text-align:center;'>자료가 존재하지 않습니다.</td></tr>");
	return varr;
}
let getSiblings = function (e) {
	var obj = getEleByObj(e);
	let siblings = []; 
	if(!obj.parentNode) return siblings;
	let sibling  = obj.parentNode.firstChild;
	while (sibling) {
		if (sibling.nodeType === 1 && sibling !== obj) {
			siblings.push(sibling);
		}
		sibling = sibling.nextSibling;
	}
	return siblings;
};
let getSiblingAll = function (e) {
	var obj = getEleByObj(e);
	let siblings = []; 
	if(!obj.parentNode) return siblings;
	let sibling  = obj.parentNode.firstChild;
	while (sibling) {
		if (sibling.nodeType === 1) {
			siblings.push(sibling);
		}
		sibling = sibling.nextSibling;
	}
	return siblings;
};
function prevAll(e) {
	var result = [];

	while (e = e.previousElementSibling)
		result.push(e);
	return result;
}

function tgTr(v1) {
	var el = getEleByObj(v1);
	const siblings = getSiblings(el);
	siblings.forEach(function(sibling) {
		sibling.classList.remove("tblCurrent");
	});
	el.classList.add("tblCurrent");
}
function clearTg(id){
	var vo1 = document.querySelector('.tblCurrent');
	if (vo1 != null){
		vo1.classList.remove("tblCurrent");
	}
}
function getTg(id){
	return document.querySelector('.tblCurrent');
}
function setTblLoadEndEvt(id){
	if (tblDataLoadEvt == undefined || tblDataLoadEvt == null){
		tblDataLoadEvt = new CustomEvent("tblDataLoadEvt",{bubbles:true});
	}
	document.getElementById(id).addEventListener("tblDataLoadEvt",function(){
		setClickTrEvt(id);
	});
}
function setClickTrEvt(oid){
	let vo = null;
	if (typeof oid == 'object'){
		vo = $(oid);
	}
	else{
		vo = $("#"+oid);
	}
	vo.find("tbody tr").each(function(){
		$(this).unbind("click").bind("click",function(){
			tgTr(this);
			setTrKey(oid, this);
		});
	});
}
function setTrKey(id, tr){
	if (isEmpty(id) || isEmpty(tr)) return;
	let key = $(tr).attr("key");
	$("#"+id).attr("key",key);
}
function getTrKey(id){
	let key = $("#"+id).attr("key");
	if (isEmpty(key)) key = "";
	return key;
}
/**
* Array objects validation check
* [["object id","type(number,date,time,ssn(주민번호),특정값('값1,값2'),'')","메세지"],..]
*/
function checkValid(arr1){
	if (arr1.length<1){
		alert("Field가 존재하지 않습니다.");
		return false;
	}
	function fCheck(k,vVal){
		//varr1:check array, k:row, vVal:check value
		var ynCheck = true;
		if (arr1[k][1]=="date"){
			if (isDate(vVal)==false) ynCheck = false;
		}
		else if (arr1[k][1]=="month"){
			if (isDate(vVal+"01")==false) ynCheck = false;
		}
		else if (arr1[k][1]=="number"){
			if (isNumber(vVal)==false)  ynCheck=false;
		}
		else if (arr1[k][1]=="time"){
			if (isTime(vVal)==false)  ynCheck=false;
		}
		else if (arr1[k][1]=="ssn"){
			var ssn = vVal;
			if (ssn.length != 13 || !isRrn(ssn)) ynCheck=false;
		}
		else if (arr1[k][1]!=""){
			var arrV1 = arr1[k][1].split(",");
			if (arrV1.length>0){
				var yn1 = true;
				for (var p=0;p<arrV1.length;p++){
					if (arrV1[p]==vVal){
						yn1=false;
						break;
					}
				}
				if (yn1)  ynCheck=false;
			}
		}
		else{
			
			if (vVal.trim()=="")  ynCheck=false;
		}
		return ynCheck;
	}
	for (var k=0;k<arr1.length;k++){
		var obj = getElements(arr1[k][0]);
		if (obj.length<1){
			alert(arr1[k][0] + " object가 존재하지 않습니다.");
			return false;
		}
		if (obj[0].nodeName=="INPUT" && (obj[0].type=="radio" || obj[0].type=="checkbox")){
			var ynCheck = false;
			for (var i=0;i<obj.length;i++){
				if (obj[i].checked==false) continue;
				var vVal = getEVal(obj[i]);
				ynCheck = fCheck(k,vVal);
				if (ynCheck) break;
			}
			if (ynCheck==false){
				alert(arr1[k][2]);
				setFocus(obj[i]);
				return false;
			}
			else{
				continue;
			}
		}
		for (var i=0;i<obj.length;i++){
			var vVal = getEVal(obj[i]);
			var ynCheck = fCheck(k,vVal);
			if (ynCheck==false){
				alert(arr1[k][2]);
				setFocus(obj[i]);
				return false;
			}
		}
	}
	return true;
}
function setFocus(obj){
	if (obj==undefined || obj==null) return;
	var vNm = obj.nodeName;
	obj = getEleByObj(obj);
	var readonly = obj.getAttribute("readonly");
	var rYn = false;
	if(readonly && readonly.toLowerCase()!=='false') rYn = true;
	if (vNm == "INPUT"){
		if ((obj.type=="text" || obj.type=="password" || obj.type=="radio" || obj.type=="checkbox") && rYn==false){
			obj.focus();
		}
	}
	else if ((vNm=="SELECT" || vNm=="TEXTAREA") && rYn==false){
		obj.focus();
	}
}
function addClasses(arr1,cls){
	if (arr1==null) return false;
	
	for (var k=0;k<arr1.length;k++){
		var obj = getElements(arr1[k]);
		if (obj.length<1) continue;
		for (var i=0;i<obj.length;i++){
			var varr = obj[i].className.split(" ");
			
			if (varr.indexOf(cls) == -1){
				if (varr.length > 0){
					if (varr[0]==""){
						obj[i].className = cls;
					}
					else{
						obj[i].className += " " + cls;
					}
				}
				else{
					obj[i].className = cls;
				}
			}
		}
	}
	return true;
}
function removeClasses(arr1,cls){
	if (arr1==null) return false;

	var rExp = new RegExp('('+cls+')','g');
	for (var k=0;k<arr1.length;k++){
		var obj = getElements(arr1[k]);
		if (obj.length<1) continue;
		for (var i=0;i<obj.length;i++){
			var v1 = obj[i].className.replace(rExp,"").trim();
			obj[i].className = obj[i].className.replace(rExp,"");
		}
	}
	return true;
}
/**
 * object Array의 항목들을 disable함
 * */
function disableTrue(arr1){
	if (arr1==null) return false;
	
	for (var k=0;k<arr1.length;k++){
		var obj = getElements(arr1[k]);
		if (obj.length<1) continue;
		for (var i=0;i<obj.length;i++){
			obj[i].disabled = true;
		}
	}
	return true;
}
/**
 * object Array의 항목들을 disable 해제함.
 * */
function disableFalse(arr1){
	if (arr1==null) return false;
	
	for (var k=0;k<arr1.length;k++){
		var obj = getElements(arr1[k]);
		if (obj.length<1) continue;
		for (var i=0;i<obj.length;i++){
			obj[i].disabled = false;
		}
	}
	return true;
}
/**
 * object Array의 항목들을 readOnly함
 * */
function readOnlyTrue(arr1){
	if (arr1==null) return false;
	
	for (var k=0;k<arr1.length;k++){
		var obj = getElements(arr1[k]);
		if (obj.length<1) continue;
		for (var i=0;i<obj.length;i++){
			obj[i].readOnly = true;
		}
	}
	return true;
}
/**
 * object Array의 항목들을 readOnly 해제함.
 * */
function readOnlyFalse(arr1){
	if (arr1==null) return false;
	
	for (var k=0;k<arr1.length;k++){
		var obj = getElements(arr1[k]);
		if (obj.length<1) continue;
		for (var i=0;i<obj.length;i++){
			obj[i].readOnly = false;
		}
	}
	return true;
}
/**
 * object Array의 항목들을 display함
 * */
function displayTrue(arr1){
	if (arr1==null) return false;
	
	for (var k=0;k<arr1.length;k++){
		var obj = getElements(arr1[k]);
		if (obj.length<1) continue;
		for (var i=0;i<obj.length;i++){
			obj[i].style.display = "";
		}
	}
	return true;
}
/**
 * object Array의 항목들을 display 해제함.
 * */
function displayFalse(arr1){
	if (arr1==null) return false;
	
	for (var k=0;k<arr1.length;k++){
		var obj = getElements(arr1[k]);
		if (obj.length<1) continue;
		for (var i=0;i<obj.length;i++){
			obj[i].style.display = "none";
		}
	}
	return true;
}
/**
 * select tag에 option 추가
 * id:select id, data:{value:text,..}, val:선택 value(없으면 null)
 * yn:Y.전체 포함, N.설명줄 없음.
 * arr: option 나열순서(data.value 순서)
 * */
function setSelectOpt(id,data,val,yn,arr){
	var obj = null;
	if (typeof id == 'object'){
		obj = id;
	}
	else{
		obj = getElement(id);
	}
	if (obj==null) return false;
	
	while(obj.options.length>0){
		obj.remove(0);
	}
	if (data==null) return false;
	//data {value:text,...}
	if (yn!=null){
		var optText = yn;
		
		if(yn == "Y") optText = "All";
		if (yn != "N"){
			var opt=document.createElement("OPTION");
			opt.value = "";
			opt.text = optText;
			obj.add(opt);
		}
	}
	if (arr==null){
		for (var key in data){
			var opt=document.createElement("OPTION");
			opt.value = key;
			opt.text = data[key];
			if (key==val) opt.selected = true;
			obj.add(opt);
		}
	}
	else{
		for (var i=0;i<arr.length;i++){
			var opt=document.createElement("OPTION");
			opt.value = arr[i];
			opt.text = data[arr[i]];
			if (arr[i]==val) opt.selected = true;
			obj.add(opt);
		}
	}
	
	return true;
}
function TDataSet(obj){
	this.data = {};
	this.keyArr = new Array();
	this.maxIndex = -1;
	this.keyClear = function(){
		this.keyArr = new Array();
	};
	this.getKey = function(i){
		if (this.keyArr==null || this.keyArr.length<i) return "";
		return this.keyArr[i];
	};
	this.setKey = function(i,key){
		this.keyArr[i] = key;
	};
	this.getKeyLength = function(){
		return this.keyArr.length;
	};
	this.getNextIndex = function(){
		return (++this.maxIndex);
	};
	this.clearMaxIndex = function(){
		this.maxIndex = -1;
	};
	this.setKeyArr = function(arr){
		this.keyArr = arr;
	};
	this.removeKey = function(idx){
		if (this.keyArr.length < 1) return;
		if (!this.keyArr.hasOwnProperty(idx)) return;
		if (isNaN(parseInt(idx))){
			delete this.keyArr[idx];
		}
		else{
			this.keyArr.splice(idx, 1);
		}
	};
	this.removeDsKey = function(dsKey){
		if (!this.data.hasOwnProperty(dsKey)) return;
		delete this.data[dsKey];
	};
	this.setKeyDs = function(idx,key,dsVal){
		//idx.array key(this.keyArr), key.keyArr[idx], dsVal.{keyArr[idx](=key):dsVal}
		this.setKey(idx,key);
		this.setValue(key, 0, dsVal);
	};
	this.deleteKeyDs = function(idx){
		//idx : this.keyArr key
		var dsKey = this.getKey(idx);
		if (isEmpty(dsKey)==false){
			this.removeDsKey(dsKey);
		}
		this.removeKey(idx);
	};
	this.getValue = function(col,row){
		if (isEmpty(this.data)) return "";
		if (row==null) row = 0;
		if (this.data[col]==null || this.data[col][row]==null) return "";
		return this.data[col][row];
	};
	this.getDataSet = function(col,row){
		var vds = new TDataSet();
		if (isEmpty(this.data)) return vds;
		if (row==null) row = 0;
		if (this.data[col]==null || this.data[col][row]==null) return vds;
		vds.setData(this.data[col][row]);
		return vds;
	};
	this.setValue = function(col,row,val){
		if(this.data[col]==null || !(this.data[col] instanceof Array)){
			this.data[col] = new Array();
		}
		this.data[col][row] = val;
	};
	this.insert = function(col,row,val){
		if(this.data[col]==null || !(this.data[col] instanceof Array)){
			this.data[col] = new Array();
		}
		this.data[col].splice(row,0,val);
	};
	this.replace = function(col,row,str1,str2){
		var v1 = this.getValue(col, row);
		v1 = v1.replace(eval("/" + str1 + "/g"),str2);
		this.setValue(col,row,v1);
	};
	this.add = function(col,val){
		if(this.data[col]==null || !(this.data[col] instanceof Array)){
			this.data[col] = new Array();
			this.data[col][0] = val;
		}
		else{
			this.data[col][this.data[col].length] = val;
		}
	};
	this.addDsRow = function(srcRow,dsD,row){
		var ccnt = dsD.getColCount();
		for(var i=0;i<ccnt;i++){
			var key = dsD.getColName(i);
			this.setValue(key,srcRow,dsD.getValue(key,row));
		}
	};
	this.deleteRow = function(row){
		for (var key in this.data){
			if(key != "errorCode" && key != "errorMsg"){
				if (this.data[key].length<(row+1)) continue;
				if (this.data[key].length == 2){
					this.data[key].splice(row,1);
					var vk1 = this.data[key];
					this.data[key] = new Array();
					this.data[key][0] = vk1[0];
				}
				else if (this.data[key].length == 1){
					this.data[key].length = 0;
				}
				else{
					this.data[key].splice(row,1);
				}
			}
		}
		return row;
	};
	this.find = function(key,val1,key1,val2){
		//key/val1:first key/Value, key1/val2:second key/Value
		var cnt = this.getRowCount(key);
		if (cnt<1) return -1;
		for(var i=0;i<cnt;i++){
			var v1 = this.getValue(key, i);
			if (typeof(v1)!="string") continue;
			if (v1==val1){
				if (key1==null){
					return i;
				}
				else{
					var v2 = this.getValue(key1,i);
					if (typeof(v2)!="string") continue;
					if (v2==val2) return i;
				}
			}
		}
		return -1;
	};
	this.findLike = function(key,val1){
		var cnt = this.getRowCount(key);
		if (cnt<1) return -1;
		for(var i=0;i<cnt;i++){
			var v1 = this.getValue(key, i);
			if (typeof(v1)!="string") continue;
			if (v1.indexOf(val1)>-1) return i;
		}
		return -1;
	};
	this.getRowCount = function(col){
		if (col==null){
			for (var key in this.data){
				return this.data[key].length;
			}
		}
		else{
			if (this.data[col]==null) return 0;
			return this.data[col].length;
		}
		return 0;
	};
	this.getColCount = function(){
		var cnt = 0;
		for (var key in this.data){
			cnt++;
		}
		return cnt;
	};
	this.getColName = function(c){
		var cnt = 0;
		for (var key in this.data){
			if (cnt==c){
				return key;
			}
			cnt++;
		}
		return "";
	};
	this.getArrColName = function(){
		var arr1 = new Array();
		var vO = Object.keys(this.data);
		for (var key in vO){
			if (vO[key]=="errorCode" ||
				vO[key]=="errorMsg" || vO[key]=="totalRows"){continue;}
			arr1.push(vO[key]);
		}
		return arr1;
	};
	this.getDsRow = function(row){
		var ds = new TDataSet();
		if (row==null) return ds;
		var ccnt = this.getColCount();
		for(var i=0;i<ccnt;i++){
			var key = this.getColName(i);
			if ((this.getRowCount(key)-1) < row) continue;
			ds.setValue(key,0,this.getValue(key,row));
		}
		return ds;
	};
	this.getMapRow = function(row){
		var map = {};
		if (row==null) return map;
		var ccnt = this.getColCount();
		for(var i=0;i<ccnt;i++){
			var key = this.getColName(i);
			if ((this.getRowCount(key)-1) < row) continue;
			map[key] = this.getValue(key,row);
		}
		return map;
	};
	this.setMapRow = function(map,row){
		if (isEmpty(map) || isEmpty(row)) return;
		for (var key in map){
			this.setValue(key,row,map[key]);
		}
	};
	this.setMap = function(map){
		var ds = new TDataSet();
		if (isEmpty(map)) return ds;
		if (map instanceof Array){
			var cnt = map.length;
			for(var i=0;i<cnt;i++){
				for (var key in map[i]){
					ds.setValue(key,i,map[i][key]);
				}
			}
		}
		else{
			for (var key in map){
				ds.setValue(key,0,map[key]);
			}
		}
		return ds;
	};
	this.copyRow = function(row,dest){
		//row : this.row, dest : destination dataset
		if (row==null || dest==null) return;
		var ccnt = this.getColCount();
		for(var i=0;i<ccnt;i++){
			var key = this.getColName(i);
			if ((this.getRowCount(key)-1) < row) continue;
			dest.add(key,this.getValue(key,row));
		}
	};
	this.getMap = function(ds){
		if (ds==null) ds = this;
		var dsTmp = new TDataSet();
		var ccnt = ds.getColCount();
		var vmap = {};
		if (ccnt<1) return vmap;
		for(var i=0;i<ccnt;i++){
			var rcnt = ds.getRowCount(ds.getColName(i));
			dsTmp.setValue(rcnt,dsTmp.getRowCount(rcnt),ds.getColName(i));
		}
		var c1 = dsTmp.getColCount();	//number key
		for(var i=0;i<c1;i++){
			var cnm = dsTmp.getColName(i);		//key(count)
			var r1 = dsTmp.getRowCount(cnm);	//real key count
			if (parseInt(cnm)==1){
				for(var k=0;k<r1;k++){
					var keynm = dsTmp.getValue(cnm,k);
					var value = ds.getValue(keynm,0);
					var valR = value;
					if (value instanceof TDataSet){
						valR = ds.getMap(value);
					}
					vmap[keynm] = valR;
				}
			}
			else{
				var cnmcnt = parseInt(cnm);	//row
				for(var k=0;k<r1;k++){
					var keynm = dsTmp.getValue(cnm,k);
					var arrVal = new Array();
					for(var m=0;m<cnmcnt;m++){
						var value = ds.getValue(keynm,m);
						var valR = value;
						if (value instanceof TDataSet){
							valR = ds.getMap(value);
						}
						arrVal[m] = valR;
					}
					vmap[keynm] = arrVal;
				}
			}
		}
		return vmap;
	};
	this.getMapDS = function(ds){
		if (ds==null) ds = this;
		var dsTmp = new TDataSet();
		var ccnt = ds.getColCount();
		var vmap = {};
		if (ccnt<1) return vmap;
		for(var i=0;i<ccnt;i++){
			var rcnt = ds.getRowCount(ds.getColName(i));
			dsTmp.setValue(rcnt,dsTmp.getRowCount(rcnt),ds.getColName(i));
		}
		var c1 = dsTmp.getColCount();	//number key
		for(var i=0;i<c1;i++){
			var cnm = dsTmp.getColName(i);		//key(count)
			var r1 = dsTmp.getRowCount(cnm);	//real key count
			var cnmcnt = parseInt(cnm);	//row
			for(var k=0;k<r1;k++){
				var keynm = dsTmp.getValue(cnm,k);
				var arrVal = new Array();
				for(var m=0;m<cnmcnt;m++){
					var value = ds.getValue(keynm,m);
					var valR = value;
					if (value instanceof TDataSet){
						valR = ds.getMapDS(value);
					}
					arrVal[m] = valR;
				}
				vmap[keynm] = arrVal;
			}
		}
		return vmap;
	};
	this.setSort = function(key,type,ord){
		//key : filed name, type:number,string, ord : asc, desc
		var cnt = this.getRowCount(key);
		if (cnt < 1) return;
		var arr = new Array();
		for(var i=0;i<cnt;i++){
			var vo = {"idx":i};
			if (type=="number"){
				var v1 = this.getValue(key,i)+"";
				v1 = v1.replaceAll(",", "");
				vo["val"] = parseFloat(v1);
			}
			else{
				vo["val"] = this.getValue(key,i);
			}
			arr.push(vo);
		}
		if (ord=="asc"){
			if (type=="number"){
				arr.sort(function(a,b){return a.val - b.val;});
			}
			else{
				arr.sort(function(a,b){if(a.val < b.val){return -1;}else{return 1;}});
			}
		}
		else{
			if (type=="number"){
				arr.sort(function(a,b){return b.val - a.val;});
			}
			else{
				arr.sort(function(a,b){if(a.val < b.val){return 1;}else{return -1;}});
			}
		}
		var ds1 = new TDataSet();
		for(var i=0;i<cnt;i++){
			this.copyRow(arr[i]["idx"],ds1);
		}
		this.setData(ds1.getData());
	};
	this.getData = function(){
		return this.data;
	};
	this.setData = function(val){
		this.data = val;
	};
	this.clear = function(){
		this.data = {};
	};
	if (obj!=undefined && obj!==null){
		this.setData(obj);
	}
}

function ajaxPost(url, param, success) {
	for (var key in param){
		if (typeof(param[key])=="object" || typeof(param[key])=="TDataSet"){
			param[key] = JSON.stringify(param[key]);
		}
	}
	var url1 = window.location.protocol + "//" + window.location.host + url;
	$.ajax({
		async : false,
		cache : false,
		url : url1,
		data : param,
        type : 'POST',
        dataType : 'json',
        success: success,
        error:function(request,status,error){
        	try{
        		var vo = jsonObject(JSON.stringify(request.responseText.trim()));
	        	if (vo!=null){
	        		if (vo["message"]!=undefined && vo["message"]!=null){
	            		success({"errorCode":["Err001"],"errorMsg":[vo["message"]]});
	        		}
	        		else{
	            		success({"errorCode":["Err001"],"errorMsg":["작업중 오류가 발생했습니다."]});
	        		}
	        	}
	        	else{
	        		success({"errorCode":["Err002"],"errorMsg":["작업중 오류가 발생했습니다."]});
	        	}
        	}
        	catch(Exception){
        		success({"errorCode":["Err003"],"errorMsg":["서버상태 오류가 발생했습니다."]});
        	}
       	}
    });
}
function httpPost(url, params, success) {
	var pData = params;
	if (typeof(params)!="string"){
		pData = getObjToFormData(params);
		pData = new URLSearchParams(pData).toString();
	}
	//console.log("send data............................" + JSON.stringify(pData));
	var url1 = window.location.protocol + "//" + window.location.host + url;
	$.ajax({
		async : false,
		cache : false,
		url : url1,
		data : pData,
        type : 'POST',
        dataType : 'text',
        success: success,
        error:function(request,status,error){
        	try{
				//pages, data, result{resultCd,resultMsg}
        		var vo = null;
        		try{
					vo = jsonObject(JSON.stringify(request.responseText.trim()));
				}catch(Exception){}
	        	if (vo!=null){
	        		if (vo["result"]!=undefined && vo["result"]!=null){
	            		success({"result":{"resultCd":"Err001","resultMsg":vo["resultMsg"]}});
	        		}
	        		else{
	            		success({"result":{"resultCd":"Err001","resultMsg":"작업중 오류가 발생했습니다."}});
	        		}
	        	}
	        	else{
            		success({"result":{"resultCd":"Err002","resultMsg":"작업중 오류가 발생했습니다."}});
        		}
        	}
        	catch(Exception){
           		success({"result":{"resultCd":"Err003","resultMsg":"서버상태 오류가 발생했습니다."}});
        	}
       	}
    });
}
function httpPostForm(url, id, success) {
	var form1 = new FormData(document.getElementById(id));
	
	//console.log("send data............................" + JSON.stringify(pData));
	var url1 = window.location.protocol + "//" + window.location.host + url;
	$.ajax({
		async : false,
		cache : false,
		url : url1,
		data : form1,
        type : 'POST',
        contentType : false,
        processData : false,
        success: success,
        error:function(request,status,error){
        	try{
				//pages, data, result{resultCd,resultMsg}
        		var vo = null;
        		try{
					vo = jsonObject(JSON.stringify(request.responseText.trim()));
				}catch(Exception){}
	        	if (vo!=null){
	        		if (vo["result"]!=undefined && vo["result"]!=null){
	            		success({"result":{"resultCd":"Err001","resultMsg":vo["resultMsg"]}});
	        		}
	        		else{
	            		success({"result":{"resultCd":"Err001","resultMsg":"작업중 오류가 발생했습니다."}});
	        		}
	        	}
	        	else{
            		success({"result":{"resultCd":"Err002","resultMsg":"작업중 오류가 발생했습니다."}});
        	}
        	}
        	catch(Exception){
           		success({"result":{"resultCd":"Err003","resultMsg":"서버상태 오류가 발생했습니다."}});
        	}
       	}
    });
}
function appendFormData(formData,data,rootName){
	let root = rootName || '';
	if (data instanceof File){
		formData.append(root,data);
	}else if(Array.isArray(data)){
		for(var i=0; i<data.length; i++){
			appendFormData(formData, data[i], root);
//			appendFormData(formData,data[i], root + '[' + i + ']');
		}
	}else if(typeof data === 'object' && data){
		for(var key in data){
			if (data.hasOwnProperty(key)){
				if (root === ''){
					appendFormData(formData, data[key], key);
				}else{
					appendFormData(formData, data[key], root + '.' + key);
				}
			}
		}
	}else{
		if (data != null && typeof data !== 'undefined'){
			formData.append(root, data);
		}
	}
}
function getObjToFormData(data){
	var formData = new FormData();
	appendFormData(formData, data, '');
	return formData;
}

async function getUrlData(url,data){
	try{
		const response = await fetch(url,{
			method:'POST',
			body:data,
			headers: {
//				'Content-Type': 'application/json'
//				'Content-Type': 'application/x-www-form-urlencoded',
			},
		} );
		let result = {};
		if (response != null){
			result = await response.json();
		}
		return result;
	}catch(e){
		console.log(e);
		return {};
	}
}

async function getFetch(url,data){
	try{
		const response = await fetch(url,{
			method:'POST',
			body:data,
			headers: {
//				'Content-Type': 'application/json'
//				'Content-Type': 'application/x-www-form-urlencoded',
			},
		} );
		let result = {};
		if (response != null){
			result = await response.json();
		}
		return result;
	}catch(e){
		console.log(e);
		return {};
	}
}

function getDownFile(url,data){
	try{
		let fileName = "downloadFile";
		fetch(url,{
			method:'POST',
			body:data,
		} )
		.then( res => {
			const disposition = res.headers.get("content-disposition");
			if(disposition && disposition.indexOf('attachment') !== -1) {
				const filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
				const matches = filenameRegex.exec(disposition);
				if (matches != null && matches[1]) {
					fileName = matches[1].replace(/['"]/g, '');
					fileName = decodeURIComponent(fileName);
				}
			}
			if (disposition == null){
				return null;
			}
			else{
				return res.blob();
			}
		} )
		.then( blob => {
			try{
				if (blob == null){
					alert("format error.");
					return;
				}
				var url = window.URL.createObjectURL(blob);
				var a = document.createElement('a');
				a.href = url;
				a.download = fileName;
				document.body.appendChild(a);
				a.click();
				a.remove();
			}catch(e){
				alert("download error.");
			}
		});
	}catch(e){}
}
function mapToFormdata(map){
	var fData = new FormData();
	if (isEmpty(map)) return fData;
	for (var key in map){
		if (isEmpty(map[key])){
			fData.append(key,"");
			continue;
		}
		if (map[key] instanceof Array){
			var cnt = map[key].length;
			for(var i=0;i<cnt;i++){
				if (map[key][i] instanceof Array || 
						map[key][i] instanceof Object){
					fData.append(key+"[]",JSON.stringify(map[key][i]));
				}
				else{
					fData.append(key+"[]",map[key][i]);
				}
			}
		}
		else if (map[key] instanceof Object){
			fData.append(key,JSON.stringify(map[key]));
		}
		else{
			fData.append(key,map[key]);
		}
	}
	return fData;
}

function layerPopWindow(val,vTop,vLeft,zIdx){
	//val:id
	var titleHeight = "40px";
	var vo1 = document.querySelector('#'+val);
	if (vo1 == null) return null;

	var vTitle1 = val+"_headerTitle";
	var obEnd = vo1.querySelector('#'+vTitle1);
	if (obEnd != null){
		vo1.style.display = "";
		return;
	}
	vo1.style.display = "";
	vo1.style.zIndex = "100";
	if (isEmpty(zIdx)==false){
		vo1.style.zIndex = zIdx;
	}
	vo1.style.position = "absolute";
	vo1.style.resize = "both";
	vo1.style.overflow = "auto";
	var vTitle = vo1.getAttribute("title");
	//position
	if (vTop == undefined || vTop == null){
		var h1 = vo1.offsetHeight;
		h1 = parseInt((window.innerHeight - h1) / 2);
		if (h1 < 1) h1 = 1;
		vo1.style.top = h1+"px";
	}else{
		vo1.style.top = vTop+"px";
	}
	if (vLeft == undefined || vLeft == null){
		var w1 = vo1.offsetWidth;
		w1 = parseInt((window.innerWidth - w1) / 2);
		if (w1 < 1) w1 = 1;
		vo1.style.left = w1+"px";
	}else{
		vo1.style.left = vLeft+"px";
	}
	
	window.innerWidth;

	var ob1=document.createElement("DIV");
	ob1.setAttribute("id",vTitle1);
	ob1.style.backgroundColor = "#00008B";
	ob1.style.height = titleHeight;
	var so1=document.createElement("SPAN");
	so1.style.color = "#ffffff";
	so1.style.display = "inline-block";
	so1.style.lineHeight = titleHeight;
	so1.style.marginLeft = "10px";
	so1.style.fontWeight = "bold";
	so1.style.verticalAlign = "middle";
	so1.style.cursor = "default";
	so1.innerHTML = vTitle;
	ob1.append(so1);

	var so2=document.createElement("SPAN");
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
	so2.addEventListener('click', function () {
		vo1.style.display = "none";
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
		var mDown = false;
		var mDown = e.buttons === undefined ? e.which === 1 : e.buttons === 1;
		if (mDown == false) return;
		var eX = e.pageX;
		var eY = e.pageY;
		var x = parseInt(eX) - parseInt(ob1.leftPageX);
		var y = parseInt(eY) - parseInt(ob1.topPageY);
		if (isNumber(x)==false || isNumber(y)==false) return;
		vo1.style.left = (vo1.offsetLeft + x)+"px";
		vo1.style.top = (vo1.offsetTop + y)+"px";
		ob1.leftPageX = eX;
		ob1.topPageY = eY;
	});

	vo1.prepend(ob1);
}
function popOpenWindow(id,url,map,wVal,hVal){
	var popOW = null;
	//map:transData,callbackFunc
	var formId = "commonPopFormId";
	if (isEmpty(id)==false) formId = id;
	if (map == undefined || map == null) return;
	var w1 = 500;
	var h1 = 500;
	if (isEmpty(wVal)==false) w1 = wVal;
	if (isEmpty(hVal)==false) h1 = hVal;
	var t1 = parseInt((screen.height - h1) / 2);
	var l1 = parseInt((screen.width - w1) / 2);
	
	var options = 'top='+t1+', left='+l1+', width='+w1+', height='+h1+', status=no, menubar=no, toolbar=no, resizable=yes';
	
	var oForm = document.getElementById(formId);
	if (oForm == null){
		oForm = document.createElement("FORM");
		oForm.id = formId;
		oForm.method = "post";
		oForm.setAttribute("style", "display:none;");
		document.body.appendChild(oForm);
	}
	else{
		oForm.innerHTML = "";
	}
	for( var key in map ) {
		if ( !isEmpty( map[key] ) ) {
			var vElement = document.createElement("INPUT");
			vElement.type = "hidden";
			vElement.setAttribute("name", key);
			vElement.setAttribute("value", map[key]);
			oForm.appendChild(vElement);
		}
	}
	oForm.action = url;
	oForm.target = formId;
	
	popOW = window.open('', formId, options);
	popOW.resizeTo(w1,h1);
	oForm.submit();
	
	return popOW;
}
/**
 * 언더바 문자열 => 카멜
 * */
function underToCamel(str){
    return str.toLowerCase().replace(/(\_[a-z])/g, function(arg){
        return arg.toUpperCase().replace('_','');
    });
}
/**
 * 카멜 => 문자열
 * */
function camelToUnder(str){
    return str.replace(/([A-Z])/g, function(arg){
        return "_"+arg.toLowerCase();
    }).toUpperCase();
}

var BROWSER = (function BROWSER(){
	function isSafari(){
		var ua = window.navigator.userAgent.toLowerCase();
		return (ua.indexOf('safari') >= 0 && ua.indexOf('chrome') < 0 && ua.indexOf('android') < 0);
	}
	return {
		IE: !!window.navigator.userAgent.match(/Trident/g) || !!window.navigator.userAgent.match(/MSIE/g),
		Edge: !!window.navigator.userAgent.match(/Edge/g),
		Safari: isSafari(),
		UiWebView: /(iPhone|iPod|iPad).*AppleWebKit(?!.*Safari)/i.test(window.navigator.userAgent),
	};
}());

function setIfmMethod(id,method,arg){
	
	var doc = getIframeDoc(getElement(id));
	return doc.execCommand('setEditerData', false, arg);
}

//////////////////////////////////////// BDataSet
function BDataSet(obj){
	this.data = {};
	this.getValue = function(col,row){
		if (isEmpty(this.data)) return "";
		if (row==null)  return "";
		if (this.data[row]==null || this.data[row][col]==null) return "";
		return this.data[row][col];
	};
	this.getRow = function(row){
		var vds = new BDataSet();
		if (isEmpty(this.data)) return vds;
		if (row==null)  return vds;
		return this.data[row];
	};
	this.setValue = function(col,row,val){
		if(!(this.data instanceof Array)){
			this.data = new Array();
		}
		if(!(this.data[row] instanceof Object)){
			this.data[row] = {};
		}
		this.data[row][col] = val;
	};
	this.getMaxVal = function(col){
		let rtn = null;
		var cnt = this.getRowCount();
		if (cnt<1) return rtn;
		for(var i=0;i<cnt;i++){
			let v1 = this.getValue(col, i);
			if (v1 == null) continue;
			if (rtn == null || rtn < v1){
				rtn = v1;
			}
		}
		return rtn;
	};
	this.getMinVal = function(col){
		let rtn = null;
		var cnt = this.getRowCount();
		if (cnt<1) return rtn;
		for(var i=0;i<cnt;i++){
			let v1 = this.getValue(col, i);
			if (v1 == null) continue;
			if (rtn == null || rtn > v1){
				rtn = v1;
			}
		}
		return rtn;
	};
	this.insert = function(col,row,val){
		if(!(this.data instanceof Array)){
			this.data = new Array();
		}
		if(!(this.data[row] instanceof Object)){
			this.data[row] = {};
		}
		this.data[row][col] = val;
	};
	this.replace = function(col,row,str1,str2){
		var v1 = this.getValue(col, row);
		v1 = v1.replace(eval("/" + str1 + "/g"),str2);
		this.setValue(col,row,v1);
	};
	this.add = function(col,val){
		if(!(this.data instanceof Array)){
			this.data = new Array();
		}
		let cnt = this.data.length;
		let row = -1;
		for(let i=0;i<cnt;i++){
			if (this.data[i].hasOwnProperty(col)){
				row++;
			}
		}
		//add row
		row++;
		if(!(this.data[row] instanceof Object)){
			this.data[row] = {};
		}
		this.data[row][col] = val;
	};
	this.addMap = function(val){
		if(!(this.data instanceof Array)){
			this.data = new Array();
		}
		let cnt = this.data.length;
		if (cnt < 0) cnt = 0;
		this.data[cnt] = val;
	};
	this.deleteRow = function(row){
		this.data.splice(row,1);
		if ((this.data.length - 1) < row){
			row = this.data.length - 1;
		}
		return row;
	};
	this.find = function(key,val1,key1,val2){
		//key/val1:first key/Value, key1/val2:second key/Value
		var cnt = this.getRowCount();
		if (cnt<1) return -1;
		for(var i=0;i<cnt;i++){
			var v1 = this.getValue(key, i);
			if (typeof(v1)!="string") continue;
			if (v1==val1){
				if (key1==null){
					return i;
				}
				else{
					var v2 = this.getValue(key1,i);
					if (typeof(v2)!="string") continue;
					if (v2==val2) return i;
				}
			}
		}
		return -1;
	};
	this.findLike = function(key,val1){
		var cnt = this.getRowCount();
		if (cnt<1) return -1;
		for(var i=0;i<cnt;i++){
			var v1 = this.getValue(key, i);
			if (typeof(v1)!="string") continue;
			if (v1.indexOf(val1)>-1) return i;
		}
		return -1;
	};
	this.copyRow = function(row,dest){
		//row : this.row, dest : destination dataset
		if (row==null || dest==null) return;
		if(!(dest.data instanceof Array)){
			dest.data = new Array();
		}
		dest.addMap(this.getRow(row));
	};
	this.setSort = function(key,type,ord){
		//key : filed name, type:number,string, ord : asc, desc
		var cnt = this.getRowCount();
		if (cnt < 1) return;
		var arr = new Array();
		for(var i=0;i<cnt;i++){
			var vo = {"idx":i};
			if (type=="number"){
				var v1 = this.getValue(key,i)+"";
				v1 = v1.replaceAll(",", "");
				vo["val"] = parseFloat(v1);
			}
			else{
				vo["val"] = this.getValue(key,i);
			}
			arr.push(vo);
		}
		if (ord=="asc"){
			if (type=="number"){
				arr.sort(function(a,b){return a.val - b.val;});
			}
			else{
				arr.sort(function(a,b){if(a.val < b.val){return -1;}else{return 1;}});
			}
		}
		else{
			if (type=="number"){
				arr.sort(function(a,b){return b.val - a.val;});
			}
			else{
				arr.sort(function(a,b){if(a.val < b.val){return 1;}else{return -1;}});
			}
		}
		var ds1 = new TDataSet();
		for(var i=0;i<cnt;i++){
			this.copyRow(arr[i]["idx"],ds1);
		}
		this.setData(ds1.getData());
	};
	this.getRowCount = function(){
		if(!(this.data instanceof Array)){
			this.data = new Array();
		}
		return this.data.length;
	};
	this.getData = function(){
		return this.data;
	};
	this.setData = function(val){
		this.data = val;
	};
	this.clear = function(){
		this.data = new Array();
	};
	if (obj!=undefined && obj!==null){
		this.setData(obj);
	}
}

//BDataSet
function setDrawTbl(tableId,dvId,arr,ds,cols){
	clearTable(tableId);

	let letr = replDrawScript(dvId,arr,ds);
	if (letr.length > 0){
		addTrInTbl(tableId,letr);
	}
	else{
		addTrInTbl(tableId,emptyTrMsg(cols));
	}
	if (tblDataLoadEvt != null){
		$('#'+tableId)[0].dispatchEvent(tblDataLoadEvt);
	}
}
//BDataSet
function replDrawScript(id,arr,ds){
	let letr = new Array();
	if (arr.length==undefined || arr.length==null || arr.length < 1) return letr;
	if (isEmpty(ds)) return letr;
	let cnt = ds.getRowCount();
	if (cnt > 0){
		for(let i=0;i<cnt;i++){
			let obj = {};
			for(let k=0;k<arr.length;k++){
				obj[arr[k]] = ds.getValue(arr[k],i);
			}
			letr.push(replSymEtChar(id,obj));
		}
	}
	return letr;
}
//BDataSet
function appendDrawTbl(tableId,dvId,arr,ds,cols){
	var vArr = replDrawScript(dvId,arr,ds);
	if (vArr.length > 0){
		addTrInTbl(tableId,vArr);
	}
	else{
		addTrInTbl(tableId,emptyTrMsg(cols));
	}
	if (tblDataLoadEvt != null){
		$('#'+tableId)[0].dispatchEvent(tblDataLoadEvt);
	}
}

function getEleToBDataSet(id,ds,fields,ynAll) {
	var obj;
	
	if (ds==null) ds = new BDataSet();

	if (typeof(id)=="object"){
		obj = id;
	}
	else{
		obj = getElement(id);
	}
	if (obj==null) return null;
	var nodes = obj.childNodes;
	if (nodes.length<1) return null;
	for (var i=0;i<nodes.length;i++){
		var vobj = nodes.item(i).childNodes;
		if (vobj!=null && vobj.length > 0){
			getEleToBDataSet(nodes.item(i),ds,fields,ynAll);
		}
		eleDataSet(nodes.item(i),ds,fields,ynAll);
	}
	return ds;
}
function eleDataSet(obj1,ds,fields,ynAll){
	if (obj1==null) return false;
	var vtag = obj1.nodeName;
	if (vtag=="INPUT"){
		if (obj1.type=="text" || obj1.type=="hidden" || obj1.type=="password"){
			var val = obj1.value;
			if (fields==null || isEmpty(fields[obj1.name])){
				if (isEmpty(obj1.name)) return;
				ds.add(obj1.name,val);
			}
			else{
				if (isEmpty(fields[obj1.name])) return;
				ds.add(fields[obj1.name],val);
			}
		}
		if (obj1.type=="radio" || obj1.type=="checkbox"){
			if (obj1.checked){
				var val = obj1.value;
				if (fields==null || isEmpty(fields[obj1.name])){
					if (isEmpty(obj1.name)) return;
					ds.add(obj1.name,val);
				}
				else{
					if (isEmpty(fields[obj1.name])) return;
					ds.add(fields[obj1.name],val);
				}
			}
			else{
				if (ynAll!=null && ynAll=="Y"){
					var val = "";
					var unval = $(obj1).attr("unvalue");
					if (isEmpty(unval)==false) val = unval;
					if (fields==null || isEmpty(fields[obj1.name])){
						if (isEmpty(obj1.name)) return;
						ds.add(obj1.name,val);
					}
					else{
						if (isEmpty(fields[obj1.name])) return;
						ds.add(fields[obj1.name],val);
					}
				}
			}
		}
	}
	if (vtag=="SELECT" || vtag=="TEXTAREA"){
		if (fields==null || isEmpty(fields[obj1.name])){
			if (isEmpty(obj1.name)) return;
			ds.add(obj1.name,obj1.value);
		}
		else{
			if (isEmpty(fields[obj1.name])) return;
			ds.add(fields[obj1.name],obj1.value);
		}
	}
	return true;
}

//////////////////////////////////////// BDataSet end.
