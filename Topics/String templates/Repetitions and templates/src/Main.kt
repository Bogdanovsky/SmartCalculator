fun main() {
    val word = readln()
    var conc: String = ""
    for (i in 1..word.length) { conc += word }
    println("${word.length} repetitions of the word $word: $conc")
}
