package hong.sy.chowall

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.view.WindowCompat
import hong.sy.chowall.databinding.ActivityMainBinding
import hong.sy.chowall.list.ListLoadingActivity
import hong.sy.chowall.recommend.RecommendActivity

class MainActivity : HideSoftKey() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBar()
        setRecButton()
        setListButton()
    }

    private fun setStatusBar() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        if (Build.VERSION.SDK_INT >= 30) {	// API 30 에 적용
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    private fun setRecButton() {
        binding.btnMainRec.setOnClickListener {
            val intent_recommend = Intent(this, RecommendActivity::class.java)
            intent_recommend.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent_recommend)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }

    private fun setListButton() {
        binding.btnMainList.setOnClickListener {
            val intent_list_loading = Intent(this, ListLoadingActivity::class.java)
            startActivity(intent_list_loading)
            overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out )
        }
    }
}