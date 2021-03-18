package max.project.calculator

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression

//TODO Поменять метод equals , уменьшить код (добавить интерфей)
//TODO проверить код
//TODO тесты
//TODO Удалить лишнее
class MainActivity : AppCompatActivity() {

    private lateinit var display: EditText
    private lateinit var clearBtn: Button
    private lateinit var parenthesesBtn: Button
    private lateinit var equalBtn: Button
    private lateinit var backspaceBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.input)
        display.showSoftInputOnFocus = false
        display.setOnClickListener {
            if (getString(R.string.display) == display.text.toString()) {
                display.setText("")
            }
        }

        clearBtn = findViewById(R.id.clear)
        clearBtn.setOnClickListener {
            display.setText("")
        }

        parenthesesBtn = findViewById(R.id.parentheses)
        parenthesesBtn.setOnClickListener{
           parentheses()
        }

        equalBtn = findViewById(R.id.equals)
        equalBtn.setOnClickListener{
            equalsCal()
        }

        backspaceBtn = findViewById(R.id.backspace)
        backspaceBtn.setOnClickListener{
            backspace()
        }
    }

    private fun updateText(strToAdd: String) {


        val oldString: String = display.text.toString()
        val cursorPosition: Int = display.selectionStart
        val leftStr: String = oldString.substring(0, cursorPosition)
        val rightStr: String = oldString.substring(cursorPosition)


        if (getString(R.string.display) == display.text.toString()) {
            display.setText(strToAdd)
            display.setSelection(cursorPosition + 1)
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr))
            display.setSelection(cursorPosition + 1)
        }

    }

    fun getBtnText(view: View) {
        view as Button
        updateText(view.text.toString())
    }


    private fun parentheses() {
        val cursorPos: Int = display.selectionStart
        var openPar = 0
        var closedPar = 0
        val textLen: Int = display.text.length
        for (i in 0 until cursorPos) {
            if (display.text.toString().substring(i, i + 1) == "(") {
                openPar += 1
            }

            if (display.text.toString().substring(i, i + 1) == ")") {
                closedPar += 1
            }
        }

        if (closedPar == openPar || display.text.toString()
                .substring(textLen - 1, textLen) == "("
        ) {
            updateText("(")
            display.setSelection(cursorPos + 1)
        } else if (closedPar < openPar && display.text.toString()
                .substring(textLen - 1, textLen) != "("
        ) {
            updateText(")")
        }
        display.setSelection(cursorPos + 1)


    }

    private fun equalsCal() {
        val userExp: String = display.text.toString()
        val exp = Expression(userExp)
        val result: String = exp.calculate().toString()
        display.setText(result)
        display.setSelection(result.length)
    }

    private fun backspace() {
        val cursorPos: Int = display.selectionStart
        val textLen: Int = display.text.length

        if (cursorPos != 0 && textLen != 0) {
            val selection: SpannableStringBuilder = display.text as SpannableStringBuilder
            selection.replace(cursorPos - 1, cursorPos, "")
            display.text = selection
            display.setSelection(cursorPos - 1)
        }
    }


}