package io.github.achmadhafid.sample_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.achmadhafid.zpack.ktx.setToolbar

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbar(R.id.toolbar)
    }
}
