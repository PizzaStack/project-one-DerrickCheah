function search() {
    var x = document.getElementById("content");
    x.innerHTML = `
                    <h1>Search Requests</h1>
                    <form id="searchemployee" method="POST" action="../SearchEmployeeServlet">
                        <label for="description"><b>Employee ID</b></label>
                        <input type="text" name="id" required>
                        <button type="submit" id="search" onclick="searchRequests()">Search</button>
                    </form>`;
}
