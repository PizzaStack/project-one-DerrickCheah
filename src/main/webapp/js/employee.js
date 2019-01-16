window.onload = function() {
	home();
}

function home() {
    var x = document.getElementById("content");
    var y = document.getElementById("userinfo");

    let xhr = new XMLHttpRequest();
    
    xhr.open("GET", "../UserInfo");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let resp = JSON.parse(xhr.responseText);
            x.innerHTML = `<h1>Welcome ${resp.Name}<br>
            Email: ${resp.Email}<br>
            Username: ${resp.Username}<br>
            Birthday: ${resp.Birthdate}<br>
            Address: ${resp.Address}<br>
            Phone Number: ${resp.Number}<br>
            SSN: ${resp.SSN}<br></h1>`;
            
            y.innerHTML = `<p>Name: ${resp.Name}<br>
            				Email: ${resp.Email}</p>`;
        }
    }
    xhr.send();
}

function openReimbursementRequest() {
	
    var x = document.getElementById("content");
   
    x.innerHTML = 
        `<form id="reimbursementform" method="POST" action="../SubmitReimbursementServlet">
            <h1>Reimbursement Form</h1>

            <label for="description"><b>Description</b></label>
            <textarea maxlength="100" name="description" placeholder="Enter Description..." required></textarea>

            <label for="expensecost"><b>Cost</b></label>
            <input type="number" step="0.01" name="expensecost" min="0" required>

            <label for="file"><b>Upload Document (Optional)</b></label>
            <input type="file" class="file" multiple>
            
            <button type="submit" id="submitreimbursement">Submit</button>
        </form>
        `;
}

function openResolvedReimbursements() {
    var x = document.getElementById("content");
    x.innerHTML = `<h1>Resolved Reimbursements</h1>`;
    
    let xhr = new XMLHttpRequest();
    
    xhr.open("GET", "../ResolvedReimbursement");
    
    xhr.onreadystatechange = function() {
    	if (xhr.readyState === 4 && xhr.status === 200) {
    		let resp = JSON.parse(xhr.responseText);
    		for (i in resp) {
    			json = resp[i];
    			x.innerHTML += `<p>Description: ${json.description}, Cost: $${json.cost}</p><br>`;
    		}
    	}
    }
    xhr.send();
}

function openPendingReimbursements() {
    var x = document.getElementById("content");
    x.innerHTML = "<h1>Pending Requests</h1>";
    
    let xhr = new XMLHttpRequest();
    
    xhr.open("GET", "../PendingReimbursement");
    xhr.onreadystatechange = function() {
    	if (xhr.readyState === 4 && xhr.status === 200) {
    		let resp = JSON.parse(xhr.responseText);
    		for (i in resp) {
    			json = resp[i];
    			x.innerHTML += `<p>Description: ${json.description}, Cost: $${json.cost}</p><br>`;
    		}
    	}
    }
    xhr.send();
}

var dropdown = document.getElementsByClassName("dropdown");
var i;

for (i = 0; i < dropdown.length; i++) {
    dropdown[i].addEventListener("click", function () {
        var dropdowncontent = this.nextElementSibling;
        if (dropdowncontent.style.display === "block") {
            dropdowncontent.style.display = "none";
        } else {
            dropdowncontent.style.display = "block";
        }
    });
}
