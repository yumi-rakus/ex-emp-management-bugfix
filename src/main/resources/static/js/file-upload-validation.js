/**
 * 
 */

$(function() {

	result = true;

	$("#form").submit(function() {

		if (result == false) {
			return false;
		}

	})

	$("#image").bind(
			"change",
			function() {
				var fileSize = this.files[0].size;
				
				if(fileSize >= 0){
					$("#imageResult").text("");
				}

				if (fileSize >= 1000000) {
					$("#file-size-error").text("1MB以下の画像を指定してください").css(
							"color", "red");
					result = false;
				} else {
					$("#file-size-error").text("");
					result = true;
				}
			})

});