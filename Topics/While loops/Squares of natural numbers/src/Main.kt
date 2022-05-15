fun main() {
    val n = readln().toInt()
    var x = 1
    while (x * x <= n) {
        println(x * x)
        x++
    }
}