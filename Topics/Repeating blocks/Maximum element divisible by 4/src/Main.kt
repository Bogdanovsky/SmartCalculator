fun main() {
    val n = readln().toInt()
    var element = 0
    var num: Int
    repeat(n) {
        num = readln().toInt()
        if (num % 4 == 0 && num > element) {
            element = num
        }
    }
    print(element)
}
