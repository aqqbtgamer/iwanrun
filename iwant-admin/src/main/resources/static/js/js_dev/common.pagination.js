var currPage = 0;
var showPageNum = 6;
var totalPages = 0;
var pageSize = 20;

function pagination(totalPagesParam, pageSize){
	if (totalPagesParam == null || totalPagesParam == undefined) {
		return;
	}
	
	totalPages = totalPagesParam;
	
	if(goFlag == 'last' || (currPage == 0 && goFlag != 'previous') || (goFlag == 'previous' && (currPage + 1) % showPageNum == 0) || (goFlag == 'recede' && (currPage + 1) % showPageNum == 1)){
		var start;
		var viewPageNum;
		
		if(goFlag == 'last'){
			if(totalPages % showPageNum == 0){
				viewPageNum = showPageNum;
			}else{
				viewPageNum = totalPages % showPageNum;
			}
			start = showPageNum * parseInt(totalPages / showPageNum);
				
		}else if(goFlag == 'previous'){
			start = currPage - showPageNum + 1;
			viewPageNum = showPageNum;
		}else{
			start = currPage;
			if(totalPages - (currPage + 1) < showPageNum){
				viewPageNum = totalPages - currPage;
			}else{
				viewPageNum = showPageNum;
			}
		}
		
		var page = "<a href='#' title='First Page' onClick='goFirst()'>« 首页</a>"
			+ "<a href='#' title='Previous Page' onClick='goPrevious()'>« 前一页</a>";
		
		for(var i = 0; i < viewPageNum; i++){
			var goPageLineNum = start + i;
			var pageLineNum = start + i + 1;
			
			if(goFlag == 'previous' || goFlag == 'last'){
				if(currPage > 0 && i == (viewPageNum - 1)){
					page += ("<a id='page"+pageLineNum+"' href='#' class='number current' title='"+(pageLineNum)+"' onClick='goPage("+goPageLineNum+")'>"+(pageLineNum)+"</a>");
				}else{
					page += ("<a id='page"+pageLineNum+"' href='#' class='number' title='"+(pageLineNum)+"' onClick='goPage("+goPageLineNum+")'>"+(pageLineNum)+"</a>");
				}
			}else{
				if(i == 0){
					page += ("<a id='page"+pageLineNum+"' href='#' class='number current' title='"+(pageLineNum)+"' onClick='goPage("+goPageLineNum+")'>"+(pageLineNum)+"</a>");
				}else{
					page += ("<a id='page"+pageLineNum+"' href='#' class='number' title='"+(pageLineNum)+"' onClick='goPage("+goPageLineNum+")'>"+(pageLineNum)+"</a>");
				}
			}
			
			/*if(i == 0){
				page += ("<a id='page"+pageLineNum+"' href='#' class='number current' title='"+(pageLineNum)+"' onClick='goPage("+goPageLineNum+")'>"+(pageLineNum)+"</a>");
			}else{
				page += ("<a id='page"+pageLineNum+"' href='#' class='number' title='"+(pageLineNum)+"' onClick='goPage("+goPageLineNum+")'>"+(pageLineNum)+"</a>");
			}*/
		}
		
	    page += ("<a href='#' title='Next Page' onClick='goRecede()'>后一页 »</a>"
	    		+ "<a href='#' title='Last Page' onClick='goLast()'>尾页 »</a>");
	    
	    document.getElementById("pagination").innerHTML = page;
	}
}

var goFlag;

function goFirst(){
	goPage(0);
}
function goPrevious(){
	goPage(currPage - 1, 'previous');
}
function goPage(pageNum, goFlagParam){
	if(pageNum < 0){
		return;
	}
	goFlag = goFlagParam;
	currPage = pageNum;
	
	var currentEle = $("a.current")[0];
	$(currentEle).attr('class', 'number');
	//currPage是页数索引值，页面显示页数要加1
	$("#page" + (currPage + 1)).attr('class', 'number current');
	//loadListData();
	search();
}
function goRecede(){
	if(currPage + 1 >= totalPages){
		return;
	}
	goPage(currPage + 1, 'recede');
}
function goLast(){
	goPage(totalPages-1, 'last');
}