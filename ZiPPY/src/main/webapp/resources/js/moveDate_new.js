var today = new Date();
var date = new Date();

build();

function beforeMonth()
{
	today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
	build();
}

function nextMonth()
{
	today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
	build();
}

function build() 
{
	var thisYear = today.getFullYear();
	var thisMon = today.getMonth() + 1;
	var nMonth = new Date(today.getFullYear(), today.getMonth(), 1);		
	var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);
	var tbcal = document.getElementById("calendarTable");
	var thisMonth = document.getElementById("thisMonth");
	thisMonth.innerHTML = today.getFullYear() + " / " + (today.getMonth() + 1);

	if (today.getMonth() + 1 == 12)
	{
		document.getElementById("beforeMonth").innerHTML = "<< " + (today.getMonth());
		document.getElementById("nextMonth").innerHTML = "1 >>";
	}
	else if (today.getMonth() + 1 == 1)
	{
		document.getElementById("beforeMonth").innerHTML = "<< 12";
		document.getElementById("nextMonth").innerHTML = ">> " + (today.getMonth() + 2);
	}
	else
	{
		document.getElementById("beforeMonth").innerHTML = "<< " +(today.getMonth());
		document.getElementById("nextMonth").innerHTML = (today.getMonth() + 2) + " >>";
	}

	while (tbcal.rows.length > 2) 
	{
		tbcal.deleteRow(tbcal.rows.length - 1);
	}
	
	var row = null;
	row = tbcal.insertRow();
	var cnt = 0;

	for (var i = 0; i < nMonth.getDay(); i++) 
	{
		cell = row.insertCell();
		cnt = cnt + 1;
	}

	for (var i = 1; i <= lastDate.getDate(); i++)
	{
		cell = row.insertCell();
		cnt = cnt + 1;
		if (cnt % 7 == 1) 
		{
			cell.innerHTML = "<div  class='cal_sun' onclick='selectedDate(" + thisYear + ", " + thisMon + ", " + i + ");'> " + i + "</div>";
		}
		else if (cnt % 7 == 0) 
		{
			cell.innerHTML = "<div  class='cal_sat' onclick='selectedDate("+ thisYear + ", "+ thisMon + ", " + i + ");'> " + i + "</div>";
			row = tbcal.insertRow();
		}
		else 
		{
			cell.innerHTML = "<div  class='cal_day' onclick='selectedDate("+ thisYear + ", " + thisMon + ", " + i + ");'> " + i + "</div>";
		}

		if (today.getFullYear() == date.getFullYear() && today.getMonth() == date.getMonth() && i == date.getDate())
		{
			cell.bgColor = "#22DD22";
		}
	}
}

 function selectedDate(year, month, date) 
 {
	 document.getElementById("value").value = year + "-" + month + "-" + date;
	 toggleModal();
 }

var modal = document.querySelector(".MsgInfoPop");

function toggleModal()
{
	modal.classList.toggle("show-modal");
}

function windowOnClick(event)
{
	if (event.target === modal) 
	{
		toggleModal();
	}
}

window.addEventListener("click", windowOnClick);

function openCalendar()
{
	modal.classList.toggle("show-modal");
}