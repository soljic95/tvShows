package hr.foreal.shows_markosoljic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTvMain()
    }

    private fun setTvMain() {
        tv_main.text = getString(R.string.main_activity_this_is_the_main_text_view)
    }
}
