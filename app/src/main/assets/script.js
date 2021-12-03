let hiddenItemsSet = new Set();
let marginSize = 0;

function script(mutationsList, observer) {
    let elements = [];
    elements.push(document.getElementById('openedCell'));
    elements.push(document.getElementById('themesCell'));
    elements.push(document.getElementById('captionbar'));

    let el_height = []; /* создаем массив для элементов чтобы увеличить высоту*/
    el_height.push(document.getElementById('LeftColumn0_div'));
    el_height.push(document.getElementById('mainArea'));
    el_height.push(document.getElementById('form0_$scrl'));
    el_height.push(document.getElementById('Container_LeftColumn_0'));
    el_height.push(document.getElementById('pagesArea'));
    zada4 = document.getElementById('form0_ДеревоЗадач_div'); /* Основной блок Задачи */

    let pagesArea = document.getElementById('pagesArea');
    let emptySpace = 0;
    if (pagesArea) {
        elements.forEach(e => {
            if (e && !e.style.display) {
                emptySpace += Number.parseInt(e.style.height);
                e.style.display = 'none';
                e.style.height = '10px';
                pagesArea.style.position = 'initial';
                if (!hiddenItemsSet.has(e.id)) {
                    hiddenItemsSet.add(e.id);
                }
            }
        });
    }

    if (zada4) { /* при наличии блока Задачи увеличиваем высоту блоков */
        el_height.forEach(e => {
            if (e && !e.style.display) {
                e.style.height = (e.offsetHeight + 100) + 'px';
            }
        });
        zada4.style.height = (zada4.offsetHeight + 100) + 'px';
    }
}

setTimeout(() => {
    let targetNode = document.getElementById('mainSurface');

    var config = { attributes: false, childList: true, subtree: false };
    let observer = new MutationObserver(script);

    observer.observe(targetNode, config);
}, 5000);