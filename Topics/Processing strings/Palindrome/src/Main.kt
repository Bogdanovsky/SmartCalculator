fun main() {
    val input =readln()
    var isPalindrome = "yes"
    for (i in 0..input.length / 2) {
        if (input[i] != input[input.lastIndex - i]) {
            isPalindrome = "no"
            break
        }
    }
    print(isPalindrome)
}