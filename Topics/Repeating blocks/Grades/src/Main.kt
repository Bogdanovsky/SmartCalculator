fun main() {
    val n = readln().toInt()
    var a = 0
    var b = 0
    var c = 0
    var d = 0
    repeat(n) {
        when (readln()) {
            "5" -> a++
            "4" -> b++
            "3" -> c++
            "2" -> d++
        }
    }
    print("$d $c $b $a")
}
