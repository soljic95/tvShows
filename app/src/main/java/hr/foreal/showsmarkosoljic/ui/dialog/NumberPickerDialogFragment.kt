package hr.foreal.showsmarkosoljic.ui.dialog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hr.foreal.showsmarkosoljic.R
import hr.foreal.showsmarkosoljic.ui.addEpisode.AddEpisodeFragment
import kotlinx.android.synthetic.main.number_picker_dialog_layout.*

class NumberPickerDialogFragment() : DialogFragment() {
    companion object {
        @JvmStatic
        fun newInstance(): NumberPickerDialogFragment {
            return NumberPickerDialogFragment()
        }
    }

    private lateinit var fragment: AddEpisodeFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.number_picker_dialog_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment = fragmentManager?.findFragmentByTag(AddEpisodeFragment.ADD_EPISODE_FRAGMENT_TAG) as AddEpisodeFragment
        setupNumberPickers()
        tvBtnSave.setOnClickListener { onBtnSaveClicked() }
    }

    private fun setupNumberPickers() {
        npSeason.minValue = 1
        npSeason.maxValue = 20
        npEpisode.minValue = 1
        npEpisode.maxValue = 99
    }

    private fun onBtnSaveClicked() {
        fragment.setEpisodeInfo(
            npSeason.value, npEpisode.value
        )
        dismiss()
    }


}
