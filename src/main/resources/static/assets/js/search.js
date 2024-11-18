document.addEventListener("DOMContentLoaded", () => {
    const boxSearch = document.querySelector(".box-search");
    if (boxSearch) {
        const input = boxSearch.querySelector("input[name='keyword']");
        const boxSuggest = boxSearch.querySelector(".inner-suggest");
        const userId = localStorage.getItem("userId")
        input.addEventListener("keyup", () => {
            const keyword = input.value;
            const link = `/projects/search/suggest?keyword=${keyword}`;
            fetch(link)
                .then(res => res.json())
                .then(data => {
                    const projects = data
                    if (projects.length > 0) {
                        boxSuggest.classList.add("show");
                        const htmls = projects.map(project => {
                            return `
                                  <a class="inner-item" href="/projects/${project.id}?userId=${userId}">
                                    <div class="inner-image">
                                        <img src="${project.image}" alt="Ảnh tượng trưng"/>
                                    </div>
                                    <div class="inner-info">
                                        <div class="inner-title">${project.name}</div>
                                        <div class="inner-amount">${project.amountNeeded}</div>
                                    </div>
                                  </a>`
                                ;
                        });
                        const boxList = boxSuggest.querySelector(".inner-list");
                        boxList.innerHTML = htmls.join("");
                    } else {
                        boxSuggest.classList.remove("show");
                    }
                })
        })
    }
})
