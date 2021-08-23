let hiddenItemsSet = new Set();
let marginSize = 0;

function script(mutationsList, observer) {
    let elements = [];
    elements.push(document.getElementById('openedCell'));
    elements.push(document.getElementById('themesCell'));
    elements.push(document.getElementById('captionbar'));
    let pagesArea = document.getElementById('pagesArea');
    let emptySpace = 0;
    if (pagesArea) {
        elements.forEach(e => {
            if (e && !e.style.display) {
                emptySpace += Number.parseInt(e.style.height);
                e.style.display = 'none';
                e.style.height = '1px';
                pagesArea.style.position = 'initial';
                if (!hiddenItemsSet.has(e.id)) {
                    hiddenItemsSet.add(e.id);
                }
            }
        });
    }
}

setTimeout(() => {
    let targetNode = document.getElementById('mainSurface');

    var config = { attributes: false, childList: true, subtree: false };
    let observer = new MutationObserver(script);

    observer.observe(targetNode, config);
}, 5000);