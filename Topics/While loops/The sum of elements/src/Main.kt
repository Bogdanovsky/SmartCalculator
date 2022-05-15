import java.util.*

fun main() {
    var i = 0
    val sc = Scanner(System.`in`)
    do {
        val j = sc.nextInt()
        i += j
    } while (j != 0)
    println(i)
}
