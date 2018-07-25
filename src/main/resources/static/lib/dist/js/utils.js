
/**
 * To format 'Description' Cell in the DataTable
 * @param objArray  the objects array that comes from the server
 * @param tblId   the table id attribute to process its cells
 */
function formatCommentsCell(objArray, tblId) {
	console.log("format cell");
    for (oInd in objArray) {
    	
        //create cell id
        var tdDescriptionId = 'tdDesc_' + objArray[oInd]['filmId'];
        console.log(tdDescriptionId);
        var description = ' ';
        if (objArray[oInd]['description'] == null) {
        	description = ' ';
        }  else{
        	description = objArray[oInd]['description'];
        	console.log(tdDescriptionId);
        	console.log(description);
        }
        $(tblId + ' td[id=' + tdDescriptionId + ']').html(description);
    }
    
    $(tblId).DataTable()
        .columns.adjust()
        .responsive.recalc();

}

