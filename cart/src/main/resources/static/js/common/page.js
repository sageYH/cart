class pageCls {
	constructor(idList,func, dspCountId){
		//idList:page list, func:page func, dspCountId:page count
		if (idList == undefined || idList == null) return null;
		if (func == undefined || func == null) return null;
		this._idList = idList;
		this._func = func;
		if (dspCountId == undefined || dspCountId == null) dspCountId = "";
		this._dspCountId = dspCountId;
		//current page index, offset, start row, end row
		this._pageRows = 10;
		this._pageIdx = 1;
		this._pageIdxCount = 10;	//page list count
		this._pageOffset = 0;	//1.oracle/mssql, 0.postgresql/mysql
		this._startRow = this._pageRows * (this._pageIdx - 1) + this._pageOffset;
		this._endRow = (this._pageRows * (this._pageIdx - 1)) + (this._pageRows - 1) + this._pageOffset;
		//page rows Index, default page rows
		this._totalRows = 0;
		
		return this;
	}
	/**
     * @param {number} val
     */
	set pageRows(val){
		if (val == undefined || val == null || isNaN(val)) return;
		this._pageRows = val;
	}
	/**
     * @param {number} val
     */
	set pageIdx(val){
		if (val == undefined || val == null || isNaN(val)) return;
		this._pageIdx = val;
	}
	/**
     * @param {number} val
     */
	set pageOffset(val){
		if (val == undefined || val == null || isNaN(val)) return;
		this._pageOffset = val;
	}
	/**
     * @param {number[]} val
	set pageRowsIdx(val){
		if (val == undefined || val == null || Array.isArray(val)==false) return;
		this._pageRowsIdx = val;
	}
     */
	/**
     * @param {any} val
     */
	set totalRows(val){
		if (val == undefined || val == null || Array.isArray(val)==false) return;
		this._totalRows = val;
	}
	setStartRow(){
		this._startRow = this._pageRows * (this._pageIdx - 1) + this._pageOffset;
	}
	setEndRow(){
		this._endRow = (this._pageRows * (this._pageIdx - 1)) + (this._pageRows - 1) + this._pageOffset;
	}
	//page info(pageIdx,pageRows)
	getPageInfo(map){
		map["pageIdx"] = this._pageIdx;
		map["pageRows"] = this._pageRows;
		map["startRow"] = this._pageRows * (this._pageIdx - 1);
		map["endRow"] = (this._pageRows * (this._pageIdx - 1)) + (this._pageRows - 1);
		return map;
	}
	setPageRows(val){
		if (val==undefined || val==null || isNaN(val)) return;
		this._pageRows = val;
		if (this._func == undefined || this._func == null) return;
		this._func();
	}
	setTotalRowsReload(val){
		if (val==undefined || val==null || isNaN(val)) return;
		this._totalRows = val;
		this.setPageList();
		//count
		
		if (this._dspCountId == "") return;
		
		let obj = document.getElementById(this._dspCountId);
		if (obj == undefined || obj == null) return;
		
		let vtag = obj.nodeName;
		if (vtag=="INPUT"){
			if (obj.type=="text" || obj.type=="hidden"){
				obj.value = val;
			}
		}
		if (vtag=="SPAN" || vtag=="DIV"){
			obj.innerHTML = "Total : " + val;
		}
	}
	//page List
	setPageList(){
		let dvList = document.getElementById(this._idList);
		dvList.innerHTML = "";
		if (this._totalRows < 1) return;
		
		dvList.style.textAlign = "center";
		this.setStartRow();
		//page start index
		let pageStartIdx = Math.floor((parseFloat(this._pageIdx) - 1) / parseFloat(this._pageIdxCount)) + 1;
		let pageLastIdx = Math.ceil(parseFloat(this._totalRows) / parseFloat(this._pageRows));
		let prePageIdx = Number(this._pageIdx) - 1;
		if (prePageIdx < 1)  prePageIdx = 1;
		let postPageIdx = Number(this._pageIdx) + 1;
		if (postPageIdx > pageLastIdx) postPageIdx = pageLastIdx;

		//<<
		let btnLeftD = document.createElement('A');
		btnLeftD.setAttribute("pageIdx",1);
		btnLeftD.setAttribute("href","javascript:void(0);");
		btnLeftD.className = "first";
		btnLeftD.innerHTML = "&nbsp;";
		btnLeftD.onclick = () => {
			this._pageIdx = btnLeftD.getAttribute("pageIdx");
			this._func();
		};
		dvList.appendChild(btnLeftD);
		//<
		let btnLeft = document.createElement('A');
		btnLeft.setAttribute("pageIdx",prePageIdx);
		btnLeft.setAttribute("href","javascript:void(0);");
		btnLeft.className = "prev";
		btnLeft.innerHTML = "<span>&nbsp;</span>";
		btnLeft.onclick = () => {
			this._pageIdx = btnLeft.getAttribute("pageIdx");
			this._func();
		};
		dvList.appendChild(btnLeft);
		
		for(var i=0;i<this._pageIdxCount;i++){
			if (i >= pageLastIdx) break;
			let currIdx = pageStartIdx + i;
			let btnList = document.createElement('A');
			btnList.setAttribute("pageIdx", currIdx);
			btnList.setAttribute("href","javascript:void(0);");
			if (this._pageIdx == currIdx){
				btnList.className = "on";
				btnList.innerHTML = "<span>"+currIdx+"</span>";
			}else{
				btnList.innerHTML = "<span>"+currIdx+"</span>";
			}
			btnList.onclick = () => {
				if (this._pageIdx == currIdx) return;
				this._pageIdx = btnList.getAttribute("pageIdx");
				this._func();
			};
			dvList.appendChild(btnList);
		}

		//>
		let btnRight = document.createElement('A');
		btnRight.setAttribute("pageIdx",postPageIdx);
		btnRight.setAttribute("href","javascript:void(0);");
		btnRight.className = "next";
		btnRight.innerHTML = "<span>&nbsp;</span>";
		btnRight.onclick = () => {
			this._pageIdx = btnRight.getAttribute("pageIdx");
			this._func();
		};
		dvList.appendChild(btnRight);
		//>>
		let btnRightD = document.createElement('A');
		btnRightD.setAttribute("pageIdx",pageLastIdx);
		btnRightD.setAttribute("href","javascript:void(0);");
		btnRightD.className = "end";
		btnRightD.innerHTML = "<span>&nbsp;</span>";
		btnRightD.onclick = () => {
			this._pageIdx = btnRightD.getAttribute("pageIdx");
			this._func();
		};
		dvList.appendChild(btnRightD);
	}
}
