// show_hide_password.js
$(document).ready(function() {
		var pass = document.getElementById("password");
		var msg = document.getElementById("message");
		var str = document.getElementById("strenght");

		pass.addEventListener('input', () => {
			if (pass.value.length > 0) {
				msg.style.display = "block";
			} else {
				msg.style.display = "none";
			}

			if (pass.value.length < 4) {
				str.innerHTML = "weak";
				pass.style.borderColor="#ff5925";
				msg.style.color="#ff5925";
			} else if (pass.value.length >= 4 && pass.value.length < 8) {
				str.innerHTML = "medium";
						pass.style.borderColor="#FFA500";
				msg.style.color="#FFA500";
			} else if (pass.value.length >= 8) {
				str.innerHTML = "strong";
						pass.style.borderColor="#4CBB17";
				msg.style.color="#4CBB17";
			}
		});
});