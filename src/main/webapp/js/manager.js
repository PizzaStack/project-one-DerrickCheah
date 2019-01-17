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
            	                <tbody id="tablecontent"></tbody>
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

            data.innerHTML += `
                                <tr>
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

function openResolvedReimbursements() {
    var x = document.getElementById("content");
    x.innerHTML = `
                    <h1>Pending Requests</h1>
                    <div class="card">
    	                <div class="card-body">
        	                <table class="table">
            	                <thead>
                                    <tr>
                                        <th scope="col">Ref ID</th>
                    	                <th scope="col">Employee ID</th>
                                        <th scope="col">Description</th>
                                        <th scope="col">Cost</th>
                                        <th scope="col">Manager</th>
                	                </tr>
            	                </thead>
            	                <tbody id="tablecontent"></tbody>
        	                </table>
    	                </div>
                    </div>`;

    var y = document.getElementById("tablecontent");

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "../ResolvedReimbursementManager");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let resp = JSON.parse(xhr.responseText);
            for (i in resp) {
                json = resp[i];
                y.innerHTML += `
                                <tr>
                                    <td>${json.refid}</td>
                                    <td>${json.id}</td>
                                    <td>${json.description}</td>
                                    <td>${json.cost}</td>
                                    <td>${json.manager}</td>
                                </tr>`;
            }
        }
    }
    xhr.send();
}

function openPendingReimbursements() {
    var x = document.getElementById("content");
    x.innerHTML = `
                    <h1>Pending Requests</h1>
                    <div class="card">
    	                <div class="card-body">
        	                <table class="table">
            	                <thead>
                                    <tr>
                                        <th scope="col">Ref ID</th>
                    	                <th scope="col">Employee ID</th>
                                        <th scope="col">Description</th>
                                        <th scope="col">Cost</th>
                                        <th scope="col">Approve/Deny</th>
                	                </tr>
            	                </thead>
            	                <tbody id="tablecontent"></tbody>
        	                </table>
    	                </div>
                    </div>`;

    var y = document.getElementById("tablecontent");

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "../PendingReimbursementManager");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let resp = JSON.parse(xhr.responseText);
            for (i in resp) {
                json = resp[i];
                y.innerHTML += `
                                <tr>
                                    <td>${json.refid}</td>
                                    <td>${json.id}</td>
                                    <td>${json.description}</td>
                                    <td>${json.cost}</td>
                                    <td><button class="btn-approval" onclick="approveRequest(${json.refid})">Approve</button>
						                    <button class="btn-deny" onclick="denyRequest(${json.refid})">Deny</button></td>
                                </tr>`;
            }
        }
    }
    xhr.send();
}

function viewEmployees() {
    var x = document.getElementById("content")

    x.innerHTML = `
    <h1>Employee List</h1>
    <div class="card">
    	<div class="card-body">
        	<table class="table">
            	<thead>
               		<tr>
                    	<th scope="col">Employee ID</th>
                        <th scope="col">Employee Name</th>
                	</tr>
            	</thead>
            	<tbody id="tablecontent">
            	</tbody>
        	</table>
    	</div>
    </div>`;

    var y = document.getElementById("tablecontent");

    let xhr = new XMLHttpRequest();

    xhr.open("GET", "../ViewEmployee");

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let resp = JSON.parse(xhr.responseText);
            for (i in resp) {
                json = resp[i];
                y.innerHTML += `
                                <tr>
                                    <td>${json.id}</td>
                                    <td>${json.firstName + " " + json.lastName}</td>
                                </tr>`;
            }
        }
    }
    xhr.send();
}

function searchRequests(employeeid) {
    var x = document.getElementById("content");
    x.innerHTML += `
                    <div class="card">
    	                <div class="card-body">
        	                <table class="table">
            	                <thead>
                                    <tr>
                                        <th scope="col">Ref ID</th>
                                        <th scope="col">Description</th>
                                        <th scope="col">Cost</th>
                                        <th scope="col">Approve/Deny</th>
                	                </tr>
            	                </thead>
            	                <tbody id="tablecontent"></tbody>
        	                </table>
    	                </div>
                    </div>`;

    var y = document.getElementById("tablecontent");

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "../SearchEmployee");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let resp = JSON.parse(xhr.responseText);
            for (i in resp) {
                json = resp[i];
                y.innerHTML += `
                                <tr>
                                    <td>${json.refid}</td>
                                    <td>${json.description}</td>
                                    <td>${json.cost}</td>
                                    <td><button class="btn-approval" onclick="approveRequest(${json.refid})">Approve</button>
						                    <button class="btn-deny" onclick="denyRequest(${json.refid})">Deny</button></td>
                                </tr>`;
            }
        }
    }
    xhr.send(employeeid);
}

function searchForm() {
    var x = document.getElementById("content");
    x.innerHTML = `
                    <h1>Search Requests</h1>
                    <form id="searchemployee">
                        <label for="description"><b>Employee ID</b></label>
                        <input type="number" id="employeeid" min="1" step="1" name="employeeid" required>
                        <button type="button" id="search" onclick="searchRequests(employeeid.value)">Search</button>
                    </form>`;
    return false;
}

function approveRequest(refid) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "../ApproveRequest");
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

        }
    }
    xhr.send(`refid=${refid}`);
}

function denyRequest(refid) {
    console.log(refid);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "../DenyRequest");
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

        }
    }
    xhr.send(`refid=${refid}`);
}

function openSettings() {
    var x = document.getElementById("content");
    x.innerHTML = `<h1>Settings</h1>
                    <form id="settingsform" method="POST" action="../UpdateSettings">

                        <label for="firstname"><b>First Name</b></label>
                        <input type="text" placeholder="First Name" name="firstname><br>

                        <label for="lastname"><b>Last Name</b></label>
                        <input type="text" placeholder="Last Name" name="lastname"><br>

                        <label for="password"><b>Password</b></label>
                        <input type="password" placeholder="Password" name="password"><br>

                        <label for="address"><b>Address</b></label>
                        <input type="text" placeholder="Address" name="address"><br>

                        <label for="phone"><b>Phone Number</b></label>
                        <input type="text" placeholder="XXX-XXX-XXXX" name="number"><br>  
    
                        <button type="submit" id="submitsettings">Update</button>
                        </form>`;
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
