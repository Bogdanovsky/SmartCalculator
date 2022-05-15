fun main() {
    val text = readLine()!!
    print(text.replace("[aA]+".toRegex(), "a"))
    // print(text.replace("[aA]+".toRegex(), "a"))
}
