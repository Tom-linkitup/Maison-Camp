let today = new Date()
    let curMonth = today.getMonth()
    let curYear = today.getFullYear()

    let months = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"]
    let title = document.getElementById("mmYY")

    showCalendar(curMonth, curYear)

    function howManyDaysInMonth(mm, yy){
        //pass 32 in the third parameter will return the subtract day of the next month
        //for example: pass (Feb, 2020, 32) will return the 4th March, because Feb only have 28 days
        return 32 - new Date(yy, mm, 32).getDate()
    }
    
    function showCalendar(month, year){
        let firstDayOfMonth = (new Date(year, month)).getDay()
        let content = document.getElementById("body")
        content.innerHTML = ""
        title.innerHTML = `${curYear} / ${months[curMonth]}`

        let date = 1
        let volumn = 2
        let price = 11000
        for(let i = 0; i < 6; i++){
            let weekRow = document.createElement("tr")
            for(let j = 0; j < 7; j++){
                if(i === 0 && j < firstDayOfMonth){
                    let column = document.createElement("td")
                    let columnText = document.createTextNode("")
                    column.appendChild(columnText)
                    column.classList.add("gray")
                    weekRow.appendChild(column)
                }else if(date > howManyDaysInMonth(curMonth, curYear)){
                    break;
                }else {
                    let column = document.createElement("td")
                    let breakline = document.createElement("br")
                    let breakline2 = document.createElement("br")
                    let dateline = document.createElement("span")
                    let volumnline = document.createElement("span")
                    let priceline = document.createElement("span")
                    let columnText = document.createTextNode(date)
                    let columnVolumn = document.createTextNode("剩餘 " + volumn)
                    let columnPrice = document.createTextNode("價格" + price)
                    
                    column.classList.add("tdClass")
                    column.addEventListener("click", function(){
                    	column.classList.add("active")
                    })
                    // 設定文字顏色
                    dateline.appendChild(columnText)
                    dateline.style.color = '#acacac'
                    
                    volumnline.appendChild(columnVolumn)
                    volumnline.style.color = '#666666'
                    
                    priceline.appendChild(columnPrice)
                    priceline.style.color = '#c15c61'
                    
                    // 將資料放入單格內
                    column.appendChild(dateline)
                    column.appendChild(breakline)
                    column.appendChild(volumnline)
                    column.appendChild(breakline2)
                    column.appendChild(priceline)
                    weekRow.appendChild(column)
                    date++
                }
            }
            content.appendChild(weekRow)        
        }
        //選擇單格及滑過顏色
        let tds = $(".tdClass")
	    tds.hover(function(){
	    	$(this).toggleClass("hoverme")
	    })
	    tds.click(function(){
	    	tds.removeClass("active");
	    	$(this).addClass("active");
	    })
    }

    function prev(){
        curYear = (curMonth === 0) ? curYear - 1 : curYear
        curMonth = (curMonth === 0) ? 11 : curMonth - 1
        showCalendar(curMonth, curYear)
    }

    function next(){
        curYear = (curMonth === 11) ? curYear + 1 : curYear
        curMonth = (curMonth + 1) % 12
        showCalendar(curMonth, curYear)
    }