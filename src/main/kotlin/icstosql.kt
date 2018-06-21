import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.Scanner
import kotlin.collections.ArrayList

private var lehrer: String? = null
private var fach: String? = null
private var klasse: String? = null
private var klassen_und_lehrer = ArrayList<String>()
private var sqlliste = ArrayList<String>()
private var lehrerliste = lehrer()
fun main(args: Array<String>) {
    val dir = File(System.getProperty("user.dir"))
    val sql = File("klassen.sql")
    val filewriter = FileWriter(sql)
    val bufferedwriter = BufferedWriter(filewriter)
    var scanner: Scanner
    var i = 0
    var line: String
    for (file in dir.listFiles()) {
        if (file.name.contains(".ics")) {
            scanner = Scanner(file)
            while (scanner.hasNextLine()) {
                line = scanner.nextLine()
                when {
                    i == 2 -> {
                        add_everything_to_List()
                        i = 0
                    }
                    line.contains("DESCRIPTION") -> {
                        klassen_und_lehrer.addAll(remove_spaces(return_everything_after_colon(line)))
                        lehrer = klassen_und_lehrer[klassen_und_lehrer.size - 1]
                        if (lehrer!!.last() == '0') lehrer = lehrer!!.substring(0, lehrer!!.lastIndex)
                        lehrerliste.forEach {
                            var a = (it.substring(1, 4) + it.first()).toUpperCase()

                            if (a == lehrer) {
                                lehrer = it
                            }
                        }
                        i++
                    }
                    line.contains("SUMMARY") -> {
                        fach = return_everything_after_colon(line)
                        i++
                    }
                }
            }
        }
    }
    for (s in sqlliste) {
        bufferedwriter.write(s)
        bufferedwriter.newLine()
    }
    bufferedwriter.close()
}


private fun add_everything_to_List() {
    if (!sqlliste.contains(insert_into_fach())) sqlliste.add(insert_into_fach())
    for (i in 0 until klassen_und_lehrer.size - 1) {
        klasse = klassen_und_lehrer[i]
        if (!sqlliste.contains(insert_into_zuweisung())) sqlliste.add(insert_into_zuweisung())
    }
    klassen_und_lehrer.clear()
}

private fun return_everything_after_colon(s: String) = s.split(":")[1]
private fun remove_spaces(s: String): List<String> = s.split("\\s".toRegex())
private fun insert_into_fach() = "INSERT INTO fach VALUES ('$fach', '$lehrer')"
private fun insert_into_zuweisung() = "INSERT INTO zuweisung VALUES ('$klasse', '$fach', '$lehrer')"