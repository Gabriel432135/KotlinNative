var algo:String = ""
var resposta:String = ""
fun main() {
    println("Olá Mundo!")
    while(resposta.uppercase() != "S"){
        println("Diga alguma coisa: ")
        algo = readln()
        println("Você disse: $algo, essa palavra contém ${algo.length} caracteres.")
        println("Quer encerrar? S/N")
        resposta = readln();
    }
}