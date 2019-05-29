function validatePopup(id, type) {
    if (id === "div_orgEDUCATION" || id === "div_orgEXPERIENCE") {
        if (document.getElementById('orgName' + type).value === "") {
            alert("Name is required field!");
        } else {
            var node = document.createElement('li');
            node.classList.add('orgElement');
            var nextId = document.querySelectorAll('.orgElement').length + 1;
            node.innerHTML = '<img class="addSection" src="img/delete.png" alt="Delete" onclick="this.parentNode.remove()"> '+
                '<input type="hidden" name="' + type + 'OrganizationId" value="' + nextId +
                '"> <input name="' + type + 'OrganizationName" value="' + document.getElementById('orgName' + type).value +
                '"> (<input name="' + type + 'OrganizationUrl" value="' + document.getElementById('orgUrl' + type).value + '">)'+
                ' <img class="addSection" src="img/add.png" alt="Add" onclick="div_show(&quot;div_pos' +  type + '&quot;, this)">'+
                '<ul class="posList"></ul>';
            document.getElementById('orgList' + type).firstChild.before(node);
        }
    }

    if (id === "div_posEDUCATION" || id === "div_posEXPERIENCE") {
        if (document.getElementById('posDateFrom' + type).value === "" ||
            document.getElementById('posDateTo' + type).value === "" ||
            document.getElementById('posName' + type).value === "") {
            alert("Date From, Date To and Name are required fields!");
        } else {
            var elem = document.querySelector('.selected');
            var org = elem.previousElementSibling.previousElementSibling.previousElementSibling;
            var orgId = org.value;
            var node = document.createElement("li");
            node.innerHTML = '<img class="addSection" src="img/delete.png" alt="Delete" onclick="this.parentNode.remove()">' +
                ' <input type=date size=10 name="' + type + orgId + 'PositionFrom" value="' + document.getElementById('posDateFrom' + type).value +
                '"> <input type=date size=10 name="' + type + orgId + 'PositionTo" value="' + document.getElementById('posDateTo' + type).value +
                '"> <input type=text size=30 name="' + type + orgId + 'PositionName" value="' + document.getElementById('posName' + type).value +
                '"> <input type=text size=30 name="' + type + orgId + 'PositionDescription" value="' + document.getElementById('posDesc' + type).value + '">';

            var closestUl = elem.nextElementSibling;
            if (closestUl.firstChild) {
                closestUl.firstChild.before(node);
            } else {
                closestUl.appendChild(node);
            }
        }
    }

    div_hide(id);
}

function div_show(id, elem) {
    clear_popups();
    document.getElementById(id).style.display = "block";
    if (document.getElementsByClassName("selected")[0]) {
        document.getElementsByClassName("selected")[0].classList.remove("selected");
    }
    elem.classList.add("selected");
}

function div_hide(id) {
    document.getElementById(id).style.display = "none";
}

function clear_popups() {
    var elems = document.getElementsByClassName("popupForm");
    for (i = 0; i < elems.length; i++) {
        elems[i].reset();
    }
}