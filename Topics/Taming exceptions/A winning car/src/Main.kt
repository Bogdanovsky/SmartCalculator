fun findCar(): String {
    val maxSpeed = readln().toInt()
    val accTime = readln().toInt()

    if (maxSpeed < 120 || accTime > 4 || accTime < 1) {
        throw Exception("The race can't be won with this car")
    }
    return "I will definitely win!"

}