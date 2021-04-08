package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var PLAYER=true
    private var TURN_COUNT=0

    private var boardStatus=Array(3) {
        IntArray(3)
    }

    private lateinit var board:Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf (
                arrayOf(button1,button2,button3),
                arrayOf(button4,button5,button6),
                arrayOf(button7,button8,button9)
        )

        for(i in board){
            for(btn in i){
                btn.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        resetBtn.setOnClickListener {
            PLAYER=true
            TURN_COUNT=0
            initializeBoardStatus()
        }

    }

    private fun initializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j]=-1
            }
        }

        for(i in board){
            for(btn in i){
                btn.isEnabled=true
                btn.text=""
            }
        }
        updateText("Player X Turn")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button1->{
                updateValue(row=0,col=0,player=PLAYER)
            }
            R.id.button2->{
                updateValue(row=0,col=1,player=PLAYER)
            }
            R.id.button3->{
                updateValue(row=0,col=2,player=PLAYER)
            }
            R.id.button4->{
                updateValue(row=1,col=0,player=PLAYER)
            }
            R.id.button5->{
                updateValue(row=1,col=1,player=PLAYER)
            }
            R.id.button6->{
                updateValue(row=1,col=2,player=PLAYER)
            }
            R.id.button7->{
                updateValue(row=2,col=0,player=PLAYER)
            }
            R.id.button8->{
                updateValue(row=2,col=1,player=PLAYER)
            }
            R.id.button9->{
                updateValue(row=2,col=2,player=PLAYER)
            }
        }
        TURN_COUNT+=1
        PLAYER=!PLAYER


        if(PLAYER==true){
            updateText("Player X Turn")
        }
        else{
            updateText("Player O Turn")
        }
        if(TURN_COUNT==9){
            updateText("Game Draw")
        }

        checkWinner()
    }

    private fun checkWinner() {
        //winning logic

        //row
        for(i in 0..2){
            if((boardStatus[i][0]==boardStatus[i][1])&&(boardStatus[i][0]==boardStatus[i][2])){
                if(boardStatus[i][0]==1){
                    updateText("Player X Winner")
                }
                else if(boardStatus[i][0]==0){
                    updateText("Player O Winner")
                }
            }
        }

        //column
        for(i in 0..2){
            if((boardStatus[0][i]==boardStatus[1][i])&&(boardStatus[0][i]==boardStatus[2][i])){
                if(boardStatus[0][i]==1){
                    updateText("Player X Winner")
                }
                else if(boardStatus[0][i]==0){
                    updateText("Player 0 Winner")
                }
            }
        }

        //first diagonal
        if((boardStatus[0][0]==boardStatus[1][1])&&(boardStatus[1][1]==boardStatus[2][2])){
            if(boardStatus[0][0]==1){
                updateText("Player X Winner")
            }
            else if(boardStatus[0][0]==0){
                updateText("Player O Winner")
            }
        }

        //second diagonal
        if(boardStatus[2][0]==boardStatus[1][1]&&boardStatus[1][1]==boardStatus[0][2]){
            if(boardStatus[2][0]==1){
                updateText("Player X Winner")
            }
            else if(boardStatus[2][0]==0){
                updateText("Player O Winner")
            }
        }

    }

    private fun updateText(s: String) {
        tv.text=s
        if(s.contains("Winner")){
          for(i in board){
              for(btn in i){
                  btn.isEnabled=false
              }
          }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        var text=if(player) "X" else "O"
        var value=if(player) 1 else 0

        board[row][col].isEnabled=false
        board[row][col].text=text
        boardStatus[row][col]=value
    }


}