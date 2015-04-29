errorColor = "#FF0000"

/**
 * Vérification de l'équivalence des valeurs de deux champs
 * @param element1CssDescriptor Le descripteur css unique du champ 1
 * @param element2CssDescriptor Le descripteur css unique du champ 2
 * @param errorMessageElementDescriptor Le descripteur css unique du block de message d'erreur
 * @param errorMmessage Le message d'erreur à afficher
 * @returns {boolean} True si la verification est OK, false sinon
 */
function verifierEquivalence(element1CssDescriptor, element2CssDescriptor,errorMessageElementDescriptor,errorMmessage) {
    element1 = document.querySelector(element1CssDescriptor)
    element2 = document.querySelector(element2CssDescriptor)
    erroElement = document.querySelector(errorMessageElementDescriptor)
    if (element1 !== null && element1.value !== null && element1.value !== ""
        && element2 !== null&& element2.value !== null && element2.value !== ""
        && element1.value !== element2.value) {
        element2.style.border = "thin solid " + errorColor;
        erroElement.innerHTML = errorMmessage
        erroElement.style.display = "block"
        return false;
    }
    else {
        element2.style.removeProperty('border')
        erroElement.style.display = "none"
        erroElement.innerHTML = ""
        return true;
    }
}

/**
 * Verification du formulaire d'un utilisateur , autre uqe les contrainte sur les élément html dans la page
 * @param messageErreurEmail Le message d'erreur de non equivalence des deux champs email
 * @param messageErreurPassword Le message d'erreur de non equivalence des deux champs mot de passe
 * @returns {boolean|*} True si la verification est Ok.
 */
function verifierFormulaireUtilisateur(messageErreurEmail,messageErreurPassword){
    verificationEmail = verifierEquivalence("input#email","input#emailUtilisateurControle","div#erreurEmailConfirmation",messageErreurEmail)
    verificationPassword = verifierEquivalence("input#motPasse","input#passwordUtilisateurControle","div#erreurMotPasseConfirmation",messageErreurPassword)
    return verificationEmail &&  verificationPassword;
}