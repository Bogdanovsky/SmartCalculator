fun main() {
    val uno = readln().toInt()
    val duo = readln().toInt()
    // val shitHappened = ArithmeticException("Division by zero, please fix the second argument!")
    if (duo == 0) {
        print("Division by zero, please fix the second argument!")
    } else {
        println(uno / duo)
    }
}
