const boxSearch=document.querySelector(".box-search");
if(boxSearch){
    const input=boxSearch.querySelector("input[name='keyword']");
    const boxSuggest=boxSearch.querySelector(".inner-suggest");
    input.addEventListener("keyup",()=>{
        const keyword=input.value;
        const link=`/projects/search/suggest?keyword=${keyword}`;
        fetch(link)
            .then(res=>res.json())
            .then(data=>{
                const projects=data.projects;
                if(projects.length>0){
                    boxSuggest.classList.add("show");
                    const htmls =projects.map(project=>{
                        return `
                              <a class="inner-item" href="/projects/${project.id}">
                                <div class="inner-info">
                                    <div class="inner-title">${project.name}</div>
                                    <div class="inner-total-amount">
                                        <i class="fa-solid fa-coins"></i> 
                                        <span th:text="${project.totalAmount}"></span>
                                    </div>
                                </div>
                              </a>`
                            ;
                    });
                    const boxList = boxSuggest.querySelector(".inner-list");
                    boxList.innerHTML=htmls.join("");
                }
                else{
                    boxSuggest.classList.remove("show");
                }
            })
    })
}