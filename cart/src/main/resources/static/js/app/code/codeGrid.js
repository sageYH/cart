function setGridCode(id){
	return new tui.Grid({
		el: getElement(id),
		scrollX: true,
		scrollY: true,
		enableHorizontalScrollbar: 0,
		bodyHeight: 450,
	    rowHeight: 28,
		width: "auto",
		selectionUnit: "row",
	    virtualScrolling: true,
	    columnOptions:{resizable:true},
		columns : [
			{// 코드ID
				name : "cdId",
				header : "코드ID",
				width : 80,
			},
			{// 코드명
				name : "cdNm",
				header : "코드명",
				width : 80,
			},
			{
				name : "grpId",
				header : "그룹코드ID",
				width : 80,
			},
		]
	});
}
