errorColor = "#FF0000"

function verifierEquivalence(elementId1, elementId2,errorElementId,message) {
    element1 = document.querySelector("input#"+elementId1)
    element2 = document.getElementById(elementId2)
    erroElement = document.getElementById(errorElementId)
    if (element1.value !== null && element1.value !== ""
        && element2.value !== null && element2.value !== ""
        && element1.value !== element2.value) {
        element2.style.border = "thin solid " + errorColor;
        erroElement.innerHTML = message
        erroElement.style.removeProperty('display')
        return false;
    }
    else {
        element2.style.removeProperty('border')
        erroElement.style.display = "none"
        erroElement.innerHTML = ""
        return true;
    }
}