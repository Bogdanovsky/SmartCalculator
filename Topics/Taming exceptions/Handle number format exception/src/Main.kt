fun parseCardNumber(cardNumber: String): Long {
    val template = Regex(
        "[0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9]"
    )
    if (!cardNumber.matches(template)) {
        throw java.lang.Exception("Incorrect input")
    } else {
        return cardNumber.replace(" ","").toLong()
    }
}

