import java.util.Scanner

fun main() {
    var i = 0
    val sc = Scanner(System.`in`)
    while(sc.hasNextInt() && sc.nextInt() != 0) {
        i++
    }
    println(i)
    
}
