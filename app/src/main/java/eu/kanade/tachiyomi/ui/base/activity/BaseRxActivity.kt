package eu.kanade.tachiyomi.ui.base.activity

import android.os.Bundle
import eu.kanade.tachiyomi.App
import eu.kanade.tachiyomi.ui.base.presenter.BasePresenter
import eu.kanade.tachiyomi.util.LocaleHelper
import nucleus.view.NucleusAppCompatActivity

abstract class BaseRxActivity<P : BasePresenter<*>> : NucleusAppCompatActivity<P>(), ActivityMixin {

    init {
        LocaleHelper.updateCfg(this)
    }

    override fun onCreate(savedState: Bundle?) {
        val superFactory = presenterFactory
        setPresenterFactory {
            superFactory.createPresenter().apply {
                val app = application as App
                context = app.applicationContext
            }
        }
        super.onCreate(savedState)
    }

    override fun getActivity() = this

    var resumed = false
        private set

    override fun onResume() {
        super.onResume()
        resumed = true
    }

    override fun onPause() {
        resumed = false
        super.onPause()
    }

}
