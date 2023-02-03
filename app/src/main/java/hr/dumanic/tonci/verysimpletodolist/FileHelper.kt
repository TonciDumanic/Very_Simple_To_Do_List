package hr.dumanic.tonci.verysimpletodolist

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * @author Tonci Dumanic
 *
 * Class for writing and reading inside a file
 *
 */
class FileHelper {

    private val FILENAME = "toDoListInfo.dat"

    /**
     * Writing data to file defined by class constant FILENAME
     */
    fun writeData(toDoItem: ArrayList<String>, context: Context) {

        val fileOutputStream: FileOutputStream =
            context.openFileOutput(FILENAME, Context.MODE_PRIVATE)

        val objectOutputStream = ObjectOutputStream(fileOutputStream)

        objectOutputStream.writeObject(toDoItem)

        objectOutputStream.close()

    }

    /**
     * Reading data from file defined by class constant FILENAME
     *
     * @return ArrayList<String> of toDoItems
     */
    fun readData(context: Context): ArrayList<String> {
        var toDoItemsList = ArrayList<String>()


        try {
        val fileInputStream: FileInputStream = context.openFileInput(FILENAME)



        val objectInputStream = ObjectInputStream(fileInputStream)
        toDoItemsList = objectInputStream.readObject() as ArrayList<String>
         }
        catch (e : FileNotFoundException){

        }

        return toDoItemsList
    }
}


