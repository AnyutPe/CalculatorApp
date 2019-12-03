package com.example.application

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {


    var lastDigit: String = ""
    var prevOperator: String = ""
    var currentOperator: String = ""
    var resultRemember: Double = 0.0
    var opCount: Int = 0
    var lastStringIsDigitOrOp: Boolean = true

    var lastLogicResult: String = ""
    var prevLogicResult: String = ""
    var lastStringIsLogicResultOrNot = false


    var resultText: String = ""
        set(value) {
            field = value
            resultText = result.text.toString()
            btnSignMinusOrPlus.isEnabled = value != "0"
        }

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result.text = ""


        //Initializing recycler view
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this, 0, true)
        val adapter = ExpressionAdapter(ArrayList(), this)
        expression_rv.layoutManager = layoutManager
        expression_rv.adapter = adapter

        //DIGITS
        btnOne.setOnClickListener { appendOnExpression("1", true, adapter) }
        btnTwo.setOnClickListener { appendOnExpression("2", true, adapter) }
        btnThree.setOnClickListener { appendOnExpression("3", true, adapter) }
        btnFour.setOnClickListener { appendOnExpression("4", true, adapter) }
        btnFive.setOnClickListener { appendOnExpression("5", true, adapter) }
        btnSix.setOnClickListener { appendOnExpression("6", true, adapter) }
        btnSeven.setOnClickListener { appendOnExpression("7", true, adapter) }
        btnEight.setOnClickListener { appendOnExpression("8", true, adapter) }
        btnNine.setOnClickListener { appendOnExpression("9", true, adapter) }
        btnZero.setOnClickListener { appendOnExpression("0", true, adapter) }
        btnDot.setOnClickListener {

            if (lastDigit.isEmpty()) {
                lastDigit = "0."
            }
            appendOnExpression(".", true, adapter)
        }

        //OPERATORS
        btnOpDivision.setOnClickListener { appendOnExpression(" ÷ ", false, adapter) }
        btnOpMultiplication.setOnClickListener { appendOnExpression(" × ", false, adapter) }
        btnOpSubstraction.setOnClickListener { appendOnExpression(" - ", false, adapter) }
        btnSignAddition.setOnClickListener { appendOnExpression(" + ", false, adapter) }

        //EQUALS
        btnEquals.setOnClickListener {

            lastDigit = result.text.toString()
            calculate(prevOperator)
            lastDigit = resultRemember.toString()

            adapter.clearAll()
            lastStringIsDigitOrOp = true
            opCount = 0

        }


        //SIGN OPERATOR
        btnSignMinusOrPlus.setOnClickListener {


            var x: Double = result.text.toString().toDouble()

            if (!lastStringIsDigitOrOp) {
                if (!lastStringIsLogicResultOrNot) {
                    lastLogicResult = buildNegateExpression(x.toString())
                    adapter.addItem(lastLogicResult)
                } else {
                    lastLogicResult = buildNegateExpression(prevLogicResult)
                    adapter.replace(prevLogicResult, lastLogicResult)
                }
            }
            x = -x
            lastDigit = x.toString()
            result.text = lastDigit
            prevLogicResult = lastLogicResult
            lastStringIsLogicResultOrNot = true
            lastStringIsDigitOrOp = true

        }

        //LOGIC OPERATORS
        btnLogicOpFraction.setOnClickListener {

            var x: Double = result.text.toString().toDouble()
            if (x != 0.0) {
                if (!lastStringIsLogicResultOrNot) {
                    lastLogicResult = buildFractionExpression(x.toString())
                    adapter.addItem(lastLogicResult)
                } else {
                    lastLogicResult = buildFractionExpression(prevLogicResult)
                    adapter.replace(prevLogicResult, lastLogicResult)
                }
                x = 1 / x
                lastDigit = x.toString()
                result.text = lastDigit
                prevLogicResult = lastLogicResult
            } else {
                adapter.addItem(buildFractionExpression(x.toString()))
                result.textSize = 40.0F
                result.text = "Cannot divide by zero"
            }


            lastStringIsLogicResultOrNot = true
            lastStringIsDigitOrOp = false

        }


        btnLogicOpSquare.setOnClickListener {

            var x: Double = result.text.toString().toDouble()

            if (!lastStringIsLogicResultOrNot) {
                lastLogicResult = buildSquareExpression(x.toString())
                adapter.addItem(lastLogicResult)
            } else {
                lastLogicResult = buildSquareExpression(prevLogicResult)
                adapter.replace(prevLogicResult, lastLogicResult)
            }
            x = x.pow(2.0)
            lastDigit = x.toString()
            if (lastDigit.toDouble() < Double.MAX_VALUE) {
                result.text = lastDigit
            } else {
                result.text = "Overflow"
            }
            prevLogicResult = lastLogicResult

            lastStringIsLogicResultOrNot = true
            lastStringIsDigitOrOp = false

        }

        btnLogicOpSquareRoot.setOnClickListener {

            var x: Double = result.text.toString().toDouble()
            if (x >= 0) {
                if (!lastStringIsLogicResultOrNot) {
                    lastLogicResult = buildSquareRootExpression(x.toString())
                    adapter.addItem(lastLogicResult)
                } else {
                    lastLogicResult = buildSquareRootExpression(prevLogicResult)
                    adapter.replace(prevLogicResult, lastLogicResult)
                }

                x = sqrt(x)
                lastDigit = x.toString()
                result.text = lastDigit
                prevLogicResult = lastLogicResult
            } else {
                adapter.addItem(buildSquareRootExpression(x.toString()))
                result.text = "Invalid input"
            }

            lastStringIsLogicResultOrNot = true
            lastStringIsDigitOrOp = false
        }


        btnLogicOpPercentage.setOnClickListener {
            when {
                adapter.items.isEmpty() -> lastDigit = "0"
                !lastStringIsDigitOrOp -> lastDigit = result.text.toString()
            }

            val x: Double = resultRemember * lastDigit.toDouble() / 100
            if (!lastStringIsLogicResultOrNot) {
                lastLogicResult = x.toString()
                adapter.addItem(lastLogicResult)
            } else {
                lastLogicResult = x.toString()
                adapter.replace(prevLogicResult, lastLogicResult)

            }



            lastDigit = x.toString()
            result.text = lastDigit
            prevLogicResult = lastLogicResult

            lastStringIsLogicResultOrNot = true
            lastStringIsDigitOrOp = false


        }


        //CLEAR SIGNS
        btnClearEntry.setOnClickListener {
            lastDigit = ""
            result.text = ""

        }


        btnCLearAll.setOnClickListener {
            adapter.clearAll()

            lastDigit = ""
            result.text = ""
            resultRemember = 0.0
        }


        btnBack.setOnClickListener {
            if (result.text.toString().length > 1) {
                lastDigit =
                    result.text.toString().substring(0, result.text.toString().length - 1)
                result.text = lastDigit
            } else {
                if (result.text.toString().length == 1) {
                    lastDigit = "0"
                    result.text = lastDigit
                }
            }
        }

    }


    private fun buildNegateExpression(lastNegateResult: String): String {
        return "negate(${lastNegateResult})"
    }

    private fun buildFractionExpression(lastSquareResult: String): String {
        return "1/(${lastSquareResult})"
    }

    private fun buildSquareExpression(lastSquareResult: String): String {
        return "sqr(${lastSquareResult})"
    }

    private fun buildSquareRootExpression(lastSquareRootResult: String): String {
        return "√(${lastSquareRootResult})"
    }

    private fun appendOnExpression(
        string: String,
        isDigitOrOperation: Boolean,
        adapter: ExpressionAdapter
    ) {

        if (isDigitOrOperation) {
            when {
                lastStringIsLogicResultOrNot -> {
                    adapter.replace(lastLogicResult, "")
                    lastDigit = string
                }
                lastDigit == "0" -> {
                    lastDigit = string
                    result.text = lastDigit
                }

                lastDigit == resultRemember.toString() -> lastDigit = string
                else -> lastDigit += string
            }

            result.text = lastDigit
            lastStringIsDigitOrOp = true

        } else {
            currentOperator = string

            if (lastStringIsDigitOrOp) {
                opCount++

                result.text = lastDigit

                if (result.text.isEmpty()) {

                    lastDigit = "0"
                    result.text = lastDigit
                    adapter.addItem(lastDigit + string)

                } else {
                    adapter.addItem(lastDigit + string)
                }

                if (opCount == 1) {
                    resultRemember = lastDigit.toDouble()

                } else {
                    calculate(prevOperator)
                }

            } else {

                when {
                    lastStringIsLogicResultOrNot -> {
                        adapter.addItem(string)
                        calculate(string)
                    }

                    else -> {
                        adapter.removeLast()
                        adapter.addItem(currentOperator)
                        opCount--
                    }
                }
            }

            lastStringIsDigitOrOp = false
            lastDigit = ""
            prevOperator = currentOperator

        }

        lastStringIsLogicResultOrNot = false

    }


    private fun calculate(string: String) {

        when (string) {
            " + " -> {
                resultRemember += lastDigit.toDouble()
                result.text = resultRemember.toString()
            }
            " - " -> {
                resultRemember -= lastDigit.toDouble()
                result.text = resultRemember.toString()
            }
            " ÷ " -> {
                if (lastDigit == "0") {
                    result.textSize = 40.0F
                    result.text = "Cannot divide by zero"
                } else {
                    resultRemember /= lastDigit.toDouble()
                    result.text = resultRemember.toString()
                }
            }
            " × " -> {
                resultRemember *= lastDigit.toDouble()
                result.text = resultRemember.toString()
            }

        }

    }
}





