fun main() {
    val n = readln().toInt()
    var a = 0
    repeat (n) {
        if (readln().toInt() > 0) {
            a++
        }
    }
    print(a)
}
