package hr.dumanic.tonci.verysimpletodolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var editTextNewToDo: EditText
    lateinit var buttonAddToDo : Button
    lateinit var listViewToDos: ListView

    var toDosArrayList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNewToDo = findViewById(R.id.editTextForNewToDo)
        buttonAddToDo = findViewById(R.id.buttonAddNewToDo)
        listViewToDos = findViewById(R.id.listOfToDos)

        val fileHelper = FileHelper()

        toDosArrayList = fileHelper.readData(this@MainActivity)

        val arrayAdapter = ArrayAdapter(this@MainActivity,android.R.layout.simple_list_item_1,android.R.id.text1,toDosArrayList)

        listViewToDos.adapter = arrayAdapter

        buttonAddToDo.setOnClickListener {

            val toDoName =  editTextNewToDo.text.toString()

            toDosArrayList.add(toDoName)

            editTextNewToDo.setText("")

            fileHelper.writeData(toDosArrayList,this@MainActivity)

            arrayAdapter.notifyDataSetChanged()

        }


        listViewToDos.setOnItemClickListener { adapterView, view, position, id ->

            val deleteAlert = AlertDialog.Builder(this)
            deleteAlert.setTitle(R.string.deleteToDo)
            deleteAlert.setMessage(R.string.deleteMessage)
            deleteAlert.setCancelable(false)
            deleteAlert.setNegativeButton(
                R.string.noStr,
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

            deleteAlert.setPositiveButton(R.string.yesStr, DialogInterface.OnClickListener { dialog, which ->
                toDosArrayList.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                fileHelper.writeData(toDosArrayList, this@MainActivity)
            })
            deleteAlert.create().show()

        }

        }
}