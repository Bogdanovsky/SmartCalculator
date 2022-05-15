import java.util.*

fun main() {
    var i = 0
    val sc = Scanner(System.`in`)
    while (sc.hasNextInt()) {
        var num = sc.nextInt()
        i = if(num > i) num else i
    }
    println(i)
}
