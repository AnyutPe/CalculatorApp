package com.example.application.fragments.standard

import androidx.lifecycle.ViewModel
import com.example.application.fragments.adapters.ExpressionAdapter
import kotlin.math.pow
import kotlin.math.sqrt

class StandardCalcViewModel : ViewModel() {

    var lastDigit: String = ""
    var prevOperator: String = ""
    var currentOperator: String = ""
    var resultRemember: Double = 0.0
    var opCount: Int = 0
    var lastStringIsDigitOrOp: Boolean = true

    var lastLogicResult: String = ""
    var prevLogicResult: String = ""
    var lastStringIsLogicResultOrNot = false

    var result: String = ""


//    var resultText: String = ""
//
//        set(value) {
//            field = value
//            resultText = result.text.toString()
//            btnSignMinusOrPlus.isEnabled = value != "0"
//        }
//
//    @SuppressLint("WrongConstant")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)


//    /**
//     *  Initializing recycler view
//     */
//
//    val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this, 0, true)
//    val adapter = ExpressionAdapter(ArrayList(), this)
//    expression_rv.layoutManager = layoutManager
//    expression_rv.adapter = adapter

//    Recyclerview buttons- arrows to left and to right


    /**
     * DIGITS
     */


    fun btnOne() {
        appendOnExpression("1", true, adapter)
    }

    fun btnTwo() {
        appendOnExpression("2", true, adapter)
    }

    fun btnThree() {
        appendOnExpression("3", true, adapter)
    }

    fun btnFour() {
        appendOnExpression("4", true, adapter)
    }

    fun btnFive() {
        appendOnExpression("5", true, adapter)
    }

    fun btnSix() {
        appendOnExpression("6", true, adapter)
    }

    fun btnSeven() {
        appendOnExpression("7", true, adapter)
    }

    fun btnEight() {
        appendOnExpression("8", true, adapter)
    }

    fun btnNine() {
        appendOnExpression("9", true, adapter)
    }

    fun btnZero() {
        appendOnExpression("0", true, adapter)
    }


//        btnOne.setOnClickListener { appendOnExpression("1", true, adapter) }
//        btnTwo.setOnClickListener { appendOnExpression("2", true, adapter) }
//        btnThree.setOnClickListener { appendOnExpression("3", true, adapter) }
//        btnFour.setOnClickListener { appendOnExpression("4", true, adapter) }
//        btnFive.setOnClickListener { appendOnExpression("5", true, adapter) }
//        btnSix.setOnClickListener { appendOnExpression("6", true, adapter) }
//        btnSeven.setOnClickListener { appendOnExpression("7", true, adapter) }
//        btnEight.setOnClickListener { appendOnExpression("8", true, adapter) }
//        btnNine.setOnClickListener { appendOnExpression("9", true, adapter) }
//        btnZero.setOnClickListener { appendOnExpression("0", true, adapter) }


    fun btnDot() {
        if (lastDigit.isEmpty()) {
            lastDigit = "0."
        }

        appendOnExpression(".", true, adapter)
    }


//    btnDot.setOnClickListener
//    {
//
//        if (lastDigit.isEmpty()) {
//            lastDigit = "0."
//        }
//
//        appendOnExpression(".", true, adapter)
//    }


    /**
     * OPERATORS
     */

    fun btnOpDivision() {
        appendOnExpression(" ÷ ", false, adapter)
    }

    fun btnOpMultiplication() {
        appendOnExpression(" × ", false, adapter)
    }

    fun btnOpSubstraction() {
        appendOnExpression(" - ", false, adapter)
    }

    fun btnOpAddition() {
        appendOnExpression(" + ", false, adapter)
    }


//    btnOpDivision.setOnClickListener
//    { appendOnExpression(" ÷ ", false, adapter) }
//
//    btnOpMultiplication.setOnClickListener
//    { appendOnExpression(" × ", false, adapter) }
//
//    btnOpSubstraction.setOnClickListener
//    { appendOnExpression(" - ", false, adapter) }
//
//    btnSignAddition.setOnClickListener
//    { appendOnExpression(" + ", false, adapter) }


    /**
     * EQUALS
     */

    fun btnEquals() {
        lastDigit = result
        calculate(prevOperator)
        lastDigit = resultRemember.toString()

        adapter.clearAll()
        lastStringIsDigitOrOp = true
        opCount = 0

    }


//    btnEquals.setOnClickListener
//    {
//
//        lastDigit = result.text.toString()
//        calculate(prevOperator)
//        lastDigit = resultRemember.toString()
//
//        adapter.clearAll()
//        lastStringIsDigitOrOp = true
//        opCount = 0
//
//    }

    /**
     * SIGN OPERATOR
     */

    fun btnSignMinusOrPlus() {

        var x: Double = result.toDouble()

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
        result = lastDigit
        prevLogicResult = lastLogicResult
        lastStringIsLogicResultOrNot = true
        lastStringIsDigitOrOp = true

    }


//    btnSignMinusOrPlus.setOnClickListener
//    {
//        var x: Double = result.text.toString().toDouble()
//
//        if (!lastStringIsDigitOrOp) {
//            if (!lastStringIsLogicResultOrNot) {
//                lastLogicResult = buildNegateExpression(x.toString())
//                adapter.addItem(lastLogicResult)
//            } else {
//                lastLogicResult = buildNegateExpression(prevLogicResult)
//                adapter.replace(prevLogicResult, lastLogicResult)
//            }
//        }
//        x = -x
//        lastDigit = x.toString()
//        result.text = lastDigit
//        prevLogicResult = lastLogicResult
//        lastStringIsLogicResultOrNot = true
//        lastStringIsDigitOrOp = true
//
//    }


    /**
     *  LOGIC OPERATORS
     */

    fun btnLogicOpFraction() {
        var x: Double = result.toDouble()
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
            result = lastDigit
            prevLogicResult = lastLogicResult
        } else {
            adapter.addItem(buildFractionExpression(x.toString()))
            result.textSize = 40.0F
            result = "Cannot divide by zero"
        }

        lastStringIsLogicResultOrNot = true
        lastStringIsDigitOrOp = false

    }

//    btnLogicOpFraction.setOnClickListener
//    {
//
//        var x: Double = result.text.toString().toDouble()
//        if (x != 0.0) {
//            if (!lastStringIsLogicResultOrNot) {
//                lastLogicResult = buildFractionExpression(x.toString())
//                adapter.addItem(lastLogicResult)
//            } else {
//                lastLogicResult = buildFractionExpression(prevLogicResult)
//                adapter.replace(prevLogicResult, lastLogicResult)
//            }
//            x = 1 / x
//            lastDigit = x.toString()
//            result.text = lastDigit
//            prevLogicResult = lastLogicResult
//        } else {
//            adapter.addItem(buildFractionExpression(x.toString()))
//            result.textSize = 40.0F
//            result.text = "Cannot divide by zero"
//        }
//
//
//        lastStringIsLogicResultOrNot = true
//        lastStringIsDigitOrOp = false
//
//    }


    fun btnLogicOpSquare() {

        var x: Double = result.toDouble()

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
            result = lastDigit
        } else {
            result = "Overflow"
        }
        prevLogicResult = lastLogicResult

        lastStringIsLogicResultOrNot = true
        lastStringIsDigitOrOp = false
    }


//    btnLogicOpSquare.setOnClickListener
//    {
//
//        var x: Double = result.text.toString().toDouble()
//
//        if (!lastStringIsLogicResultOrNot) {
//            lastLogicResult = buildSquareExpression(x.toString())
//            adapter.addItem(lastLogicResult)
//        } else {
//            lastLogicResult = buildSquareExpression(prevLogicResult)
//            adapter.replace(prevLogicResult, lastLogicResult)
//        }
//        x = x.pow(2.0)
//        lastDigit = x.toString()
//        if (lastDigit.toDouble() < Double.MAX_VALUE) {
//            result.text = lastDigit
//        } else {
//            result.text = "Overflow"
//        }
//        prevLogicResult = lastLogicResult
//
//        lastStringIsLogicResultOrNot = true
//        lastStringIsDigitOrOp = false
//
//    }

    fun btnLogicOpSquareRoot() {

        var x: Double = result.toDouble()
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
            result = lastDigit
            prevLogicResult = lastLogicResult
        } else {
            adapter.addItem(buildSquareRootExpression(x.toString()))
            result = "Invalid input"
        }

        lastStringIsLogicResultOrNot = true
        lastStringIsDigitOrOp = false
    }


//    btnLogicOpSquareRoot.setOnClickListener
//    {
//
//        var x: Double = result.text.toString().toDouble()
//        if (x >= 0) {
//            if (!lastStringIsLogicResultOrNot) {
//                lastLogicResult = buildSquareRootExpression(x.toString())
//                adapter.addItem(lastLogicResult)
//            } else {
//                lastLogicResult = buildSquareRootExpression(prevLogicResult)
//                adapter.replace(prevLogicResult, lastLogicResult)
//            }
//
//            x = sqrt(x)
//            lastDigit = x.toString()
//            result.text = lastDigit
//            prevLogicResult = lastLogicResult
//        } else {
//            adapter.addItem(buildSquareRootExpression(x.toString()))
//            result.text = "Invalid input"
//        }
//
//        lastStringIsLogicResultOrNot = true
//        lastStringIsDigitOrOp = false
//    }


    fun btnLogicOpPercentage() {
        when {
            adapter.items.isEmpty() -> lastDigit = "0"
            !lastStringIsDigitOrOp -> lastDigit = result
        }

        val x: Double = resultRemember * lastDigit.toDouble() / 100
        if (!lastStringIsLogicResultOrNot) {
            lastLogicResult = x.toString()
            adapter.addItem(lastLogicResult)
        } else {
            lastLogicResult = x.toString()
            adapter.replace(prevLogicResult, lastLogicResult)

        }

//    btnLogicOpPercentage.setOnClickListener
//    {
//        when {
//            adapter.items.isEmpty() -> lastDigit = "0"
//            !lastStringIsDigitOrOp -> lastDigit = result.text.toString()
//        }
//
//        val x: Double = resultRemember * lastDigit.toDouble() / 100
//        if (!lastStringIsLogicResultOrNot) {
//            lastLogicResult = x.toString()
//            adapter.addItem(lastLogicResult)
//        } else {
//            lastLogicResult = x.toString()
//            adapter.replace(prevLogicResult, lastLogicResult)
//
//        }


        lastDigit = x.toString()
        result = lastDigit
        prevLogicResult = lastLogicResult

        lastStringIsLogicResultOrNot = true
        lastStringIsDigitOrOp = false


    }

    /**
     * CLEAR SIGNS
     */

    fun btnClearEntry() {
        lastDigit = ""
        result = ""
    }

//    btnClearEntry.setOnClickListener
//    {
//        lastDigit = ""
//        result.text = ""
//
//    }


    fun btnClearAll() {

        adapter.clearAll()
        lastDigit = ""
        result = ""
        resultRemember = 0.0

    }

//    btnCLearAll.setOnClickListener
//    {
//        adapter.clearAll()
//
//        lastDigit = ""
//        result.text = ""
//        resultRemember = 0.0
//    }

    fun btnBack() {

        if (result.length > 1) {
            lastDigit =
                result.substring(0, result.length - 1)
            result = lastDigit
        } else {
            if (result.length == 1) {
                lastDigit = "0"
                result = lastDigit
            }
        }
    }


//    btnBack.setOnClickListener
//    {
//        if (result.text.toString().length > 1) {
//            lastDigit =
//                result.text.toString().substring(0, result.text.toString().length - 1)
//            result.text = lastDigit
//        } else {
//            if (result.text.toString().length == 1) {
//                lastDigit = "0"
//                result.text = lastDigit
//            }
//        }
//    }


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
            lastStringIsLogicResultOrNot
            when {
                lastStringIsLogicResultOrNot -> {
                    adapter.replace(lastLogicResult, "")
                    lastDigit = string
                }
                lastDigit == "0" -> {
                    lastDigit = string

                    result = lastDigit   //result.text = lastDigit
                }

                lastDigit == resultRemember.toString() -> lastDigit = string
                else -> lastDigit += string
            }

            result = lastDigit //result.text = lastDigit
            lastStringIsDigitOrOp = true

        } else {
            currentOperator = string

            if (lastStringIsDigitOrOp) {
                opCount++

                result = lastDigit //result.text = lastDigit

                if (result.isEmpty()) {  //result.text.isEmpty()

                    lastDigit = "0"
                    result = lastDigit  //result.text = lastDigit
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
                result = resultRemember.toString() // result.text = resultRemember.toString()
            }
            " - " -> {
                resultRemember -= lastDigit.toDouble()
                result = resultRemember.toString() // result.text = resultRemember.toString()
            }
            " ÷ " -> {
                if (lastDigit == "0") {
                    result.textSize = 40.0F
                    result = "Cannot divide by zero" // result.text = "Cannot divide by zero"
                } else {
                    resultRemember /= lastDigit.toDouble()
                    result = resultRemember.toString()  //result.text = resultRemember.toString()
                }
            }
            " × " -> {
                resultRemember *= lastDigit.toDouble()
                result = resultRemember.toString() //result.text = resultRemember.toString()
            }

        }

    }
}

