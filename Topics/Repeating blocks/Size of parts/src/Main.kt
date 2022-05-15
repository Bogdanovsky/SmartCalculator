fun main() {
    val n = readln().toInt()
    var perfect = 0
    var larger = 0
    var smaller = 0

    for (i in 0 until n) {
        when (readln().toInt()) {
            -1 -> smaller++
            0 -> perfect++
            1 -> larger++
        }
    }
    print("$perfect $larger $smaller")
}