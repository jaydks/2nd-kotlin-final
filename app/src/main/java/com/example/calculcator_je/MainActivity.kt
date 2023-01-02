package com.example.calculcator_je

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.calculcator_je.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var expression: TextView
    private lateinit var doubleZero: TextView
    private lateinit var zero: TextView
    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView
    private lateinit var five: TextView
    private lateinit var six: TextView
    private lateinit var seven: TextView
    private lateinit var eight: TextView
    private lateinit var nine: TextView
    private lateinit var plus: TextView
    private lateinit var minus: TextView
    private lateinit var multi: TextView
    private lateinit var div: TextView
    private lateinit var percent: TextView
    private lateinit var dot: TextView
    private lateinit var equal: TextView
    private lateinit var back: TextView
    private lateinit var cancel: TextView
    private lateinit var cancelAll: TextView
    private lateinit var changeSign: TextView
    private lateinit var mc: TextView
    private lateinit var mr: TextView
    private lateinit var mMinus: TextView
    private lateinit var mPlus: TextView
    private lateinit var hms: TextView
    private lateinit var root: TextView
    private lateinit var gt: TextView
    private lateinit var roundingSelectorGroup: RadioGroup
    private lateinit var roundSelectF: RadioButton
    private lateinit var roundSelectCut: RadioButton
    private lateinit var roundSelectHalf: RadioButton
    private lateinit var showNums: RadioGroup
    private lateinit var show4: RadioButton
    private lateinit var show3: RadioButton
    private lateinit var show2: RadioButton
    private lateinit var show1: RadioButton
    private lateinit var show0: RadioButton
    private lateinit var showAdd: RadioButton
    private lateinit var opGroup: LinearLayout
    private lateinit var opGT: TextView
    private lateinit var opM: TextView
    private lateinit var opK: TextView
    private lateinit var opPlus: TextView
    private lateinit var opMinus: TextView
    private lateinit var opMulti: TextView
    private lateinit var opDiv: TextView
    private lateinit var error: TextView


    var numList = arrayListOf<String>("", "", "")

    var str: String = ""

    var result: Double = 0.0
    var isError: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        expression = binding.mainTvExpression
        doubleZero = binding.btnNums00
        zero = binding.btnNums0
        one = binding.btnNums1
        two = binding.btnNums2
        three = binding.btnNums3
        four = binding.btnNums4
        five = binding.btnNums5
        six = binding.btnNums6
        seven = binding.btnNums7
        eight = binding.btnNums8
        nine = binding.btnNums9
        plus = binding.btnPlus
        minus = binding.btnMinus
        multi = binding.btnMulti
        div = binding.btnDiv
        percent = binding.btnPercent
        dot = binding.btnDot
        equal = binding.btnEqual
        back = binding.btnBack
        cancel = binding.btnCancel
        cancelAll = binding.btnCancelAll
        changeSign = binding.btnChangeSign
        mc = binding.btnMc
        mr = binding.btnMr
        mMinus = binding.btnMMinus
        mPlus = binding.btnMPlus

        hms = binding.btnHms
        root = binding.btnRoot
        gt = binding.btnGt
        roundingSelectorGroup = binding.selectRoudingSelectorGroup
        roundSelectF = binding.selectRoudingF
        roundSelectCut = binding.selectRoudingCUT
        roundSelectHalf = binding.selectRoudingHalf
        showNums = binding.selectShowNumsGroup
        show4 = binding.selectShowNums4
        show3 = binding.selectShowNums3
        show2 = binding.selectShowNums2
        show1 = binding.selectShowNums1
        show0 = binding.selectShowNums0
        showAdd = binding.selectShowNumsADDS2

        opGroup = binding.linearOperator
        opGT = binding.operatorGt
        opM = binding.operatorM
        opK = binding.operatorK
        opPlus = binding.operatorPlus
        opMinus = binding.operatorMinus
        opMulti = binding.operatorMulti
        opDiv = binding.operatorDiv

        error = binding.tvError


        doubleZero.setOnClickListener {
            // numList[0] 이나 numList[2] 숫자가 없으면 00 눌러도 0으로 들어가기
            if (numList[0].isEmpty()) { // numList[0] : 0
                Log.d("확인", numList.toString())
            } else {
                clickedNum("00")
            }
        }

        zero.setOnClickListener {
            if (numList[0].isEmpty()) { // numList[0] : 0
                Log.d("확인?", numList.toString()) // + 0.1   + 00.1
            } else {
                clickedNum("0")
            }
        }


        one.setOnClickListener {
            clickedNum("1")
        }

        two.setOnClickListener {
            clickedNum("2")
        }

        three.setOnClickListener {
            clickedNum("3")
        }

        four.setOnClickListener {
            clickedNum("4")
        }

        five.setOnClickListener {
            clickedNum("5")
        }

        six.setOnClickListener {
            clickedNum("6")
        }
        seven.setOnClickListener {
            clickedNum("7")
        }

        eight.setOnClickListener {
            clickedNum("8")
        }

        nine.setOnClickListener {
            clickedNum("9")
        }

        plus.setOnClickListener {
            if (isError == true) {
                return@setOnClickListener
            } else {
                clickedOperator("+")
                showOperator("+")
            }
        }

        minus.setOnClickListener {
            if (isError == true) {
                return@setOnClickListener
            } else {
                clickedOperator("-")
                showOperator("-")
            }
        }

        multi.setOnClickListener {
            if (isError == true) {
                return@setOnClickListener
            } else {
                clickedOperator("*")
                showOperator("*")
            }
        }

        div.setOnClickListener {
            if (isError == true) {
                return@setOnClickListener
            } else {
                clickedOperator("/")
                showOperator("/")
            }
        }

        percent.setOnClickListener { // TODO : % 연산자 기능 확인 필요
            Toast.makeText(this, "기능 구현 예정입니다", Toast.LENGTH_SHORT).show()
        }

        dot.setOnClickListener {
            clickedNum(".")
        }

        equal.setOnClickListener {
            if (isError == true) {
                return@setOnClickListener
            } else {
                calcuate("=")
            }
        }

        back.setOnClickListener {
            clickedBack()
        }

        cancel.setOnClickListener {
            if (isError == true) {
                return@setOnClickListener
            } else {
                clickedCancel()
            }
        }

        cancelAll.setOnClickListener {
            if (isError == true) {
                isError = false
                error.visibility = View.INVISIBLE
            }
            expressionText("0")
            hideOperator()
            numList = arrayListOf("", "", "")
            Log.d("확인", numList.toString())
        }

        changeSign.setOnClickListener {
            changeSign()
        }

        mc.setOnClickListener {

        }

        mr.setOnClickListener {

        }

        mMinus.setOnClickListener {

        }

        mPlus.setOnClickListener {

        }

        hms.setOnClickListener {
            Toast.makeText(this, "기능 구현 예정입니다", Toast.LENGTH_SHORT).show()
        }

        root.setOnClickListener {

        }

        gt.setOnClickListener {

        }

        roundingSelectorGroup.setOnClickListener {

        }

        roundSelectF.setOnClickListener {

        }

        roundSelectCut.setOnClickListener {

        }

        roundSelectHalf.setOnClickListener {

        }

        showNums.setOnClickListener {

        }

        show4.setOnClickListener {

        }

        show3.setOnClickListener {

        }

        show2.setOnClickListener {

        }

        show1.setOnClickListener {

        }

        show0.setOnClickListener {

        }

        showAdd.setOnClickListener {

        }

    }

    private fun clickedNum(num: String) {
        if (isError == true) { // 에러 o
            return
        } else { // 에러 x
            if (numList[0].isEmpty()) { // 0
                if (num == "." && !numList[0].contains(".")) { // 처음부터  . -> 0.1
                    str = expression.text.toString() + num
                    expressionText(str)
                    changeNumList(0, str)
                } else {
                    str = expression.text.toString().replace("0", "") + num
                    expressionText(str)
                    changeNumList(0, str)
                }
            } else if (!numList[0].isEmpty() && numList[1].isEmpty()) { // 1
                if (num == "." && numList[0].contains(".")) { // 1. .있으면 막음
                    Log.d("확인", numList.toString())
                    return
                } else { // 1
                    str = expression.text.toString() + num
                    expressionText(str)
                    changeNumList(0, str)
                }
            } else if (!numList[0].isEmpty() && !numList[1].isEmpty() && numList[2].isEmpty()) { // 1 +
                if (num == "." && !numList[2].contains(".")) { // 처음부터  . -> 0.1
                    str = "0" + num
                    expressionText(str)
                    changeNumList(2, str)
                } else if (num == "0" || num == "00") { // 1 + 0
                    str = "0"
                    expressionText(str)
                    changeNumList(2, str)
                } else {
                    expressionText("")
                    str = expression.text.toString() + num
                    expressionText(str)
                    changeNumList(2, num)

                }
            } else if (!numList[0].isEmpty() && !numList[1].isEmpty() && !numList[2].isEmpty()) { // 1 + 2
                if (num == "." && numList[2].contains(".")) { //  // 1. .있으면 막음
                    return
                } else if (num == "." && !numList[2].contains(".")) {
                    str = expression.text.toString() + num
                    expressionText(str)
                    changeNumList(2, num)
                } else if (numList[2].startsWith("0") && !numList[2].contains(".")) { // 1 + 0
                    str = "0"
                    expressionText(str)
                } else {
                    str = expression.text.toString() + num
                    expressionText(str)
                    changeNumList(2, str)
                }
            }
        }

    }

    private fun clickedOperator(operator: String) {

        if (numList[0].isEmpty()) { // 입력된 숫자가 없음
            return
        } else if (!numList[0].isEmpty() && numList[1].isEmpty()) {
            changeNumList(1, operator)
        } else if (!numList[0].isEmpty() && !numList[1].isEmpty() && numList[2].isEmpty()) { // 1 +
            changeNumList(1, operator)
        } else if (!numList[0].isEmpty() && !numList[1].isEmpty() && !numList[2].isEmpty()) { // 1 + 2
            calcuate(operator)
        }

    }

    private fun clickedBack() {
        if (!numList[2].isEmpty()) { // 1 + 21
            str = expression.text.toString().substring(0, expression.text.length - 1)
            expressionText(str)
            changeNumList(2, str)
            if(numList[2] == ""){
                str = "0"
                expressionText(str)
            }
        } else if (!numList[0].isEmpty() && !numList[1].isEmpty() && numList[2].isEmpty()) { // 1 +
            str = "0"
            expressionText(str)
            hideOperator()
            numList = arrayListOf("", "", "")
            Log.d("확인", numList.toString())
        } else if (!numList[0].isEmpty() && numList[1].isEmpty()) { // 1
            str = expression.text.toString().substring(0, expression.text.length - 1)
            expressionText(str)
            hideOperator()
            changeNumList(0, str)
        }
    }

    private fun clickedCancel() {
        if (!numList[2].isEmpty()) { // 1 + 2
            str = "0"
            expressionText(str)
            changeNumList(2, str)
        } else if (numList[2].isEmpty() && (!numList[0].isEmpty() || !numList[1].isEmpty())) { // 1 + or 1
            str = "0"
            expressionText(str)
            numList = arrayListOf("", "", "")
            Log.d("확인", numList.toString())
        }
    }

    private fun showOperator(operator: String) {
        opGroup.visibility = View.VISIBLE
        when (operator) {
            "+" -> {
                opPlus.visibility = View.VISIBLE
                opMinus.visibility = View.INVISIBLE
                opMulti.visibility = View.INVISIBLE
                opDiv.visibility = View.INVISIBLE
            }
            "-" -> {
                opPlus.visibility = View.INVISIBLE
                opMinus.visibility = View.VISIBLE
                opMulti.visibility = View.INVISIBLE
                opDiv.visibility = View.INVISIBLE
            }
            "*" -> {
                opPlus.visibility = View.INVISIBLE
                opMinus.visibility = View.INVISIBLE
                opMulti.visibility = View.VISIBLE
                opDiv.visibility = View.INVISIBLE
            }
            "/" -> {
                opPlus.visibility = View.INVISIBLE
                opMinus.visibility = View.INVISIBLE
                opMulti.visibility = View.INVISIBLE
                opDiv.visibility = View.VISIBLE
            }
            else -> return
        }
    }

    private fun calcuate(operator: String) {
        var calOp = numList[1] // 1 + 2 <- "+"
        when (calOp) {
            "+" -> {
                Log.d("확인_계산", "${numList}")
                result = numList[0].toDouble() + numList[2].toDouble()
            }
            "-" -> {
                Log.d("확인_계산", "${numList}")
                result = numList[0].toDouble() - numList[2].toDouble()
            }
            "*" -> {
                Log.d("확인_계산", "${numList}")
                result = numList[0].toDouble() * numList[2].toDouble()
            }
            "/" -> {
                if (numList[2] == "0") { // 나누는 숫자가 0이면
                    error.visibility = View.VISIBLE
                    isError = true
                }
                Log.d("확인_계산", "${numList}")
                result = numList[0].toDouble() / numList[2].toDouble()
            }
        }
        numList = arrayListOf("", "", "")
        if (result - result.toLong() == 0.0) { // 정수
            changeNumList(0, result.toLong().toString())
        } else { // 소수
            changeNumList(0, result.toString())
        }
        if (operator != "=") {
            changeNumList(1, operator)
        }
        str = numList[0]
        expressionText(str)
    }

    private fun changeSign(){
        if (numList[2].isNotEmpty()){ // 1 + 2
            checkPlusAndChangeSign(2)
        } else {
            if(!numList[0].isEmpty() && numList[1].isEmpty()){ // 1
                checkPlusAndChangeSign(0)
            } else if(!numList[0].isEmpty() && !numList[1].isEmpty()){ // 1 +
                checkPlusAndChangeSign(0)
            }
        }
    }

    private fun checkPlusAndChangeSign(i: Int){
        var changedNum = ""
        if (numList[i].toDouble() > 0){ // 양수 -> 음수
            changedNum = "-" + numList[i]
        } else if (numList[i].toDouble() < 0) {
            changedNum = numList[i].substring(1, numList[i].length)

        }
        Log.d("확인-쁠마", changedNum)

        val numToDouble = changedNum.toDouble()
        if (numToDouble - numToDouble.toLong() == 0.0) { // 정수
            changeNumList(i, numToDouble.toLong().toString())
        } else { // 소수
            changeNumList(i, numToDouble.toString())
        }
        expressionText(numList[i])
        Log.d("확인", numList.toString())
    }

    private fun hideOperator() {
        opGroup.visibility = View.GONE
    }

    private fun expressionText(str: String) {
        expression.text = str
    }

    private fun changeNumList(i: Int, num: String) {
        numList[i] = num
        Log.d("확인", numList.toString())
    }

}