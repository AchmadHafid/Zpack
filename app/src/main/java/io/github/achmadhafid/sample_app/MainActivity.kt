package io.github.achmadhafid.sample_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import io.github.achmadhafid.zpack.delegate.lifecycleValue
import io.github.achmadhafid.zpack.extension.d

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var myInt: Int? by lifecycleValue {
        d("MyInt is destroyed")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById<MaterialToolbar>(R.id.toolbar))
        myInt = 5
    }

}
