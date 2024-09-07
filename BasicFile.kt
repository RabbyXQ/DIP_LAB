import java.io.File;

fun main(){
    println("Hello World");
    var file = File("input.txt");
    
    val buff = file.bufferedReader();


    var line = buff.readLine();

    while(line != null){
        println(line);
        line = buff.readLine();
    }

    buff.close();


}