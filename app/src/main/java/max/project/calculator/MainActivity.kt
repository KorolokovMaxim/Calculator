package max.project.calculator

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression

//TODO Поменять метод equals , уменьшить код (добавить интерфей)
//TODO проверить код
//TODO тесты
//TODO Удалить лишнее
class MainActivity : AppCompatActivity() {

    private lateinit var display: EditText

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


    fun zeroBTN(view: View) {
        updateText("0")
    }

    fun one(view: View) {
        updateText("1")
    }

    fun two(view: View) {
        updateText("2")
    }

    fun three(view: View) {
        updateText("3")
    }

    fun four(view: View) {
        updateText("4")
    }

    fun five(view: View) {
        updateText("5")
    }

    fun six(view: View) {
        updateText("6")
    }

    fun seven(view: View) {
        updateText("7")
    }

    fun eight(view: View) {
        updateText("8")
    }

    fun nine(view: View) {
        updateText("9")
    }

    fun add(view: View) {
        updateText("+")
    }

    fun clear(view: View) {
        display.setText("")
    }

    fun exponent(view: View) {
        updateText("^")
    }

    fun parentheses(view: View) {
        val cursorPos: Int = display.selectionStart
        var openPar: Int = 0
        var closedPar: Int = 0
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

    fun divide(view: View) {
        updateText("/")
    }

    fun multiply(view: View) {
        updateText("*")
    }

    fun subtract(view: View) {
        updateText("-")
    }

    fun plusMinus(view: View) {
        updateText("-")
    }

    fun point(view: View) {
        updateText(".")
    }

    fun equalsCal(view: View) {
        val userExp: String = display.text.toString()
        val exp: Expression = Expression(userExp)
        val result: String = exp.calculate().toString()
        display.setText(result)
        display.setSelection(result.length)
    }

    fun backspace(view: View) {
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