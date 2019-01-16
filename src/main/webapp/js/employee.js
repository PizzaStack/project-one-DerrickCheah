window.onload = function () {
    home();
}

function home() {
    var x = document.getElementById("content")
    
    x.innerHTML = `
    <h1 id="welcome"></h1>
    <div class="card">
    	<div class="card-body">
        	<table class="table">
            	<thead>
               		<tr>
                    	<th scope="col">Username</th>
                        <th scope="col">Birthday</th>
                        <th scope="col">Address</th>
                        <th scope="col">Phone</th>
                        <th scope="col">SSN</th>
                	</tr>
            	</thead>
            	<tbody id="tablecontent">
            	</tbody>
        	</table>
    	</div>
    </div>`;

    var data = document.getElementById("tablecontent");
    var y = document.getElementById("userinfo");
    var z = document.getElementById("welcome");

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "../UserInfo");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let resp = JSON.parse(xhr.responseText);

            y.innerHTML = `<p>Name: ${resp.Name}<br>
            				Email: ${resp.Email}</p>`;

            z.innerHTML = `Welcome ${resp.Name}`;

            data.innerHTML += `<tr>
                                <td>${resp.Username}</td>
                                <td>${resp.Birthdate}</td>
                                <td>${resp.Address}</td>
                                <td>${resp.Number}</td>
                                <td>${resp.SSN}</td>
                            </tr>`;
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
    var x = document.getElementById("content")
    
    x.innerHTML = `
    <h1>Resolved Requests</h1>
    <div class="card">
    	<div class="card-body">
        	<table class="table">
            	<thead>
               		<tr>
                    	<th scope="col">Description</th>
                    	<th scope="col">Cost</th>
                	</tr>
            	</thead>
            	<tbody id="tablecontent">
            	</tbody>
        	</table>
    	</div>
    </div>`;

    var y = document.getElementById("tablecontent")

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "../ResolvedReimbursement");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let resp = JSON.parse(xhr.responseText);
            for (i in resp) {
                json = resp[i];
                y.innerHTML += `<tr>
                                    <td>${json.description}</td>
                                    <td>$${json.cost}</td>
                                </tr>`;
            }
        }
    }
    xhr.send();
}

function openPendingReimbursements() {
	
    var x = document.getElementById("content")
    
    x.innerHTML = `
    <h1>Pending Requests</h1>
    <div class="card">
    	<div class="card-body">
        	<table class="table">
            	<thead>
               		<tr>
                    	<th scope="col">Description</th>
                    	<th scope="col">Cost</th>
                	</tr>
            	</thead>
            	<tbody id="tablecontent">
            	</tbody>
        	</table>
    	</div>
    </div>`;
    
    var y = document.getElementById("tablecontent");

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "../PendingReimbursement");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let resp = [];
        	resp = JSON.parse(xhr.responseText);
            
            for (i in resp) {
                json = resp[i];
                y.innerHTML += `<tr>
                                    <td>${json.description}</td>
                                    <td>$${json.cost}</td>
                                </tr>`;
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
