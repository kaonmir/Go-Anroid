package com.example.go.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.go.R
import com.example.go.core.ErrorEntity
import com.example.go.core.Go
import com.example.go.core.Point
import com.example.go.core.Stone
import com.example.go.core.Stone.StoneType
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val BoardSize = 19

    private val go = Go(arrayOf(100, 102))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (x in 0 until BoardSize) {
            for (y in 0 until BoardSize) {
                val imageView =
                    LayoutInflater.from(this).inflate(R.layout.board_piece, null) as ImageView
                val param = GridLayout.LayoutParams(
                    GridLayout.spec(GridLayout.UNDEFINED, 1f),
                    GridLayout.spec(GridLayout.UNDEFINED, 1f),
                )
                param.height = 0; param.width = 0

                imageView.layoutParams = param
                imageView.setBackgroundResource(getBoardBackgroundImage(x, y))

                imageView.setOnClickListener {
                    try {
                        go.move(Point(x, y, BoardSize))
                    } catch (e: ErrorEntity) {
                        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                    }
                    updateUI()
                }

                glGoBoard.addView(imageView)
            }
        }
        updateUI()
    }

    private fun updateUI() {
        go.board.forEachIndexed { idx, _ ->
            setStoneImage(glGoBoard[idx] as ImageView, go.board[idx])
        }
        tvScore.text = getString(
            R.string.main_activity_score,
            go.counts[StoneType.BLACK],
            go.counts[StoneType.WHITE]
        )
    }

    private fun setStoneImage(imageView: ImageView, stone: Stone) {
        val resourceId = when (stone.type) {
            StoneType.BLACK -> R.drawable.ic_blackstone
            StoneType.WHITE -> R.drawable.ic_whitestone
            else -> 0
        }

        imageView.setImageResource(resourceId)
    }

    private fun getBoardBackgroundImage(x: Int, y: Int): Int {
        val start = 57
        return when (val cur = x * BoardSize + y) {
            0 -> R.drawable.ic_goboardlefttop
            18 -> R.drawable.ic_goboardrighttop
            342 -> R.drawable.ic_goboardleftbottom
            360 -> R.drawable.ic_goboardrightbottom
            start + 3, start + 9, start + 15,
            start + BoardSize * 6 + 3, start + BoardSize * 6 + 9, start + BoardSize * 6 + 15,
            start + BoardSize * 12 + 3, start + BoardSize * 12 + 9, start + BoardSize * 12 + 15,
            -> R.drawable.ic_goboardcenterwithdot

            else -> when {
                cur / BoardSize == 0 -> R.drawable.ic_goboardtop
                cur % BoardSize == 0 -> R.drawable.ic_goboardleft
                cur % BoardSize == BoardSize - 1 -> R.drawable.ic_goboardright
                cur / BoardSize == BoardSize - 1 -> R.drawable.ic_goboardbottom
                else -> R.drawable.ic_goboardcenter
            }
        }
    }
}

/*
0
19
38
"57"
76
95
 */