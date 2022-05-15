fun convertStringToDouble(input: String): Double {
    return try {
        input.toDouble()
    } catch (e: RuntimeException) {
        0.0
    }
}
