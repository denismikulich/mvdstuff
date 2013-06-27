var CommonLib = {
	switchVisibility : function _switchElementById(id) {
		var element = document.getElementById(id);
		var clazz = element.className;
		if (clazz.indexOf("_hidden") == -1) {
			CommonLib.hideElementById(id);
		} else {
			CommonLib.showElementById(id);
		}
	},

	showElementById : function _showElementById(id) {
		var element = document.getElementById(id);
		var clazz = element.className;
		element.className = clazz.replace("_hidden", "");
	},

	hideElementById : function _hideElementById(id) {
		var element = document.getElementById(id);
		element.className += '_hidden';

	},

	invokeServlet : function _invokeServlet(servletPath, args) {
		var xmlhttp = new XMLHttpRequest();
		var url = servletPath;
		if (args != null) {
			url += "?" + args;
		}
		xmlhttp.open("GET", url, true);
		xmlhttp.onreadystatechange = function() {

		};
		xmlhttp.send();
	},

	invokeJspServlet : function(form, servletPath, args) {
		var url = servletPath;
		if (args != null) {
			url += "?" + args;
		}
		form.method = "POST";
		form.action = url;
		form.submit();
	},

	activateDatePicker : function() {
		if (!Modernizr.inputtypes['date']) {
			$('input[type=date]').datepicker({
				// specify the same format as the spec
	            dateFormat: 'yy-mm-dd'
			});
		}
	},
	
	initYearSelector : function(selectorID) {
		var yearSelector = $("#"+selectorID);
		var date = new Date();
		var year = date.getFullYear();

		for ( var i = 0; i < 12; i++) {
			yearSelector.append('<option value="' + (year - 7 + i) + '">'
					+ (year - 7 + i) + '</option>');
		}
		yearSelector.val(year);
	},

};

/**
 * function set for admin page
 */
var AdminLib = {
	onAddUserBtn : function() {
		var form = document.getElementById("FormBtnAddUser");
		CommonLib.invokeJspServlet(form, "onAddUser", null);
	},

	onEditUserBtn : function _onEditUserBtn() {
		var table = document.getElementById("userListTable");
		var selRow = null;
		for ( var i = 0; i < table.rows.length; i++) {
			row = table.rows[i];
			if (table.rows[i].className == "selRow") {
				selRow = row;
			}
		}
		if (selRow == null) {
			alert("You must select the row!");
		}
		var form = document.getElementById("FormBtnEditUser");
		var param = selRow.id.replace("row_", "");
		CommonLib.invokeJspServlet(form, "onEditUser", "userid=" + param);
	},

	onDeleteUserBtn : function _onEditUserBtn() {
		var table = document.getElementById("userListTable");
		var selRow = null;
		for ( var i = 0; i < table.rows.length; i++) {
			row = table.rows[i];
			if (table.rows[i].className == "selRow") {
				selRow = row;
			}
		}
		if (selRow == null) {
			alert("You must select the row!");
		}
		var form = document.getElementById("FormBtnDeleteUser");
		var param = selRow.id.replace("row_", "");
		CommonLib.invokeJspServlet(form, "onDeleteUser", "userid=" + param);
	}
};
